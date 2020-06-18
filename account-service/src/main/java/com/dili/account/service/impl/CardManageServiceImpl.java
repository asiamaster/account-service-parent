package com.dili.account.service.impl;

import com.dili.account.dao.IUserAccountDao;
import com.dili.account.dao.IUserCardDao;
import com.dili.account.dto.CardRequestDto;
import com.dili.account.dto.PayAccountDto;
import com.dili.account.entity.CardAggregationWrapper;
import com.dili.account.entity.UserAccountDo;
import com.dili.account.entity.UserCardDo;
import com.dili.account.rpc.resolver.PayRpcResolver;
import com.dili.account.service.IAccountQueryService;
import com.dili.account.service.ICardManageService;
import com.dili.account.service.ICardStorageService;
import com.dili.account.service.IPasswordService;
import com.dili.account.type.CardStatus;
import com.dili.ss.constant.ResultCode;
import com.dili.ss.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @description： 卡片退卡换卡等操作service实现
 *
 * @author ：WangBo
 * @time ：2020年4月28日下午5:09:47
 */
@Service("cardManageService")
public class CardManageServiceImpl implements ICardManageService {
    @Autowired
    private IPasswordService passwordService;
    @Autowired
    private ICardStorageService cardStorageService;
    @Autowired
    private IAccountQueryService accountQueryService;
    @Autowired
    private IUserCardDao userCardDao;
    @Autowired
    private IUserAccountDao userAccountDao;
    @Resource
    private IUserCardDao iUserCardDao;
    @Resource
    private IUserAccountDao iUserAccountDao;
    @Resource
    private PayRpcResolver payRpcResolver;

    @Override
    @Transactional
    public void returnCard(CardRequestDto cardRequest) {
        UserCardDo userCardDo = iUserCardDao.findCardByAccountId(cardRequest.getAccountId());
        if (userCardDo == null) {
            throw new BusinessException("9999999999", "卡信息不存在");
        }
        if (CardStatus.RETURNED.getCode() == userCardDo.getState()) {
            throw new BusinessException("9999999999", "该卡已退");
        }
        if (CardStatus.NORMAL.getCode() == userCardDo.getState()) {
            throw new BusinessException("9999999999", "卡非正常状态,不能退卡");
        }
        List<UserAccountDo> accounts = iUserAccountDao.findSlavesByParent(cardRequest.getAccountId());
        if (!CollectionUtils.isEmpty(accounts)) {
            throw new BusinessException("9999999999", "该卡存在副卡,不能退卡");
        }
        PayAccountDto payAccountDto = payRpcResolver.resolverByUserAccount(cardRequest.getAccountId());
        if (payAccountDto.getBalance() != 0L) {
            throw new BusinessException("9999999999", "卡余额不为0,不能退卡");
        }
        int update = iUserCardDao.updateState(cardRequest.getAccountId(), CardStatus.RETURNED.getCode(), userCardDo.getVersion());
        if (update == 0) {
            throw new BusinessException("9999999999", "退卡操作失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reportLoss(CardRequestDto cardParam) {
        CardAggregationWrapper wrapper = accountQueryService.getByAccountIdWithNotNull(cardParam.getAccountId());
        UserCardDo userCard = wrapper.getUserCard();

        this.validateCanReportLoss(userCard, cardParam);

        this.changeState(userCard, CardStatus.LOSS.getCode());
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changeCard(CardRequestDto cardParam) {
        CardAggregationWrapper wrapper = accountQueryService.getByAccountIdWithNotNull(cardParam.getAccountId());

        this.validateCanChangeCard(wrapper, cardParam);
        UserCardDo oldCard = wrapper.getUserCard();
        //退还旧卡
        this.changeState(oldCard, CardStatus.RETURNED.getCode());

        UserCardDo newCard = this.cloneWhenChangeCard(oldCard, cardParam);
        userCardDao.save(newCard);

        //老卡作废,新卡出库
        cardStorageService.voidCard(oldCard.getCardNo(), "");
        cardStorageService.inUse(newCard.getCardNo());
    }

    private void validateCanReportLoss(UserCardDo userCard, CardRequestDto cardParam) {
        if (userCard.getState() != CardStatus.NORMAL.getCode()) {
            throw new BusinessException(ResultCode.DATA_ERROR, "该卡为非正常状态，不能进行此操作");
        }
        //passwordService.checkLoginPwd(cardParam.getAccountId(), cardParam.getLoginPwd());
    }

    private void validateCanChangeCard(CardAggregationWrapper wrapper, CardRequestDto cardParam) {
        if (wrapper.getUserCard().getState() == CardStatus.RETURNED.getCode()) {
            throw new BusinessException(ResultCode.DATA_ERROR, "该卡为退还状态，不能进行此操作");
        }
        //passwordService.checkLoginPwd(cardParam.getAccountId(), cardParam.getLoginPwd());
    }

    private void changeState(UserCardDo userCard, Integer targetState) {
        UserCardDo updateDo = new UserCardDo();
        updateDo.setId(userCard.getId());
        updateDo.setState(targetState);
        updateDo.setModifyTime(LocalDateTime.now());
        userCardDao.update(updateDo);
    }

    private UserCardDo cloneWhenChangeCard(UserCardDo old, CardRequestDto param) {
        UserCardDo newCard = (UserCardDo) old.clone();
        newCard.setCardNo(param.getNewCardNo());
        newCard.setLast(1);
        newCard.setCreator(param.getOperator().getOpName());
        newCard.setCreatorId(param.getOperator().getOpId());
        newCard.setState(CardStatus.NORMAL.getCode());
        return newCard;
    }

//
//	@Override
//	@Transactional
//	public void changeCard(CardManageParamDto cardParam) {
//		UserAccountCardDto cardAccount = checkCardStatus(cardParam.getAccountId(), null);
//		if (cardAccount.getStatus() != CardStatus.NORMAL.getCode()) {
//			throw new AppException("该卡{}为非正常状态，不允许换卡，请联系管理员!", cardAccount.getAccountId());
//		}
//		// 校验密码
//		passwordService.checkLoginPwd(cardAccount.getAccountId(), cardParam.getLoginPwd());
//
//		// 修改旧卡状态
//		UserCardEntity oldCard = new UserCardEntity();
//		oldCard.setId(cardAccount.getCardId());
//		oldCard.setStatus(CardStatus.RETURNED.getCode());
//		oldCard.setModifiedTime(LocalDateTime.now());
//		userCardDao.update(oldCard);
//		// 卡片仓库旧卡状态修改
//		UserCardRepositoryEntity oldCardRepo = cardRepositoryService.activateCard(cardAccount.getCardNo());
//
//		// 卡片仓库新卡状态修改
//		UserCardRepositoryEntity newCardRepo = cardRepositoryService.inUse(cardParam.getNewCardNo());
//
//		if (oldCardRepo.getCategory().intValue() != newCardRepo.getCategory()) {
//			String categoryName = CardCategory.getName(oldCardRepo.getCategory());
//			throw new AppException("新卡{}与旧卡的卡类型{}需保持一致!", newCardRepo.getCardNo(), categoryName);
//		}
//		// 新卡数据
//		UserCardEntity newCard = new UserCardEntity(LocalDateTime.now());
//		IKeyGenerator userCardKey = keyGeneratorManager.getKeyGenerator(SequenceKey.USER_CARD);
//		newCard.setId(userCardKey.nextId());
//		newCard.setAccountId(cardAccount.getAccountId());
//		newCard.setCardNo(cardParam.getNewCardNo());
//		newCard.setCategory(cardAccount.getCategory());
//		newCard.setMarketId(cardAccount.getMarketId());
//		newCard.setStatus(CardStatus.NORMAL.getCode());
//		newCard.setSeinsweise(CardType.PHYSICAL_CARD.getCode());
//		newCard.setSystemDisableStatus(SystemDisableStatus.ENABLED.getCode());
//		userCardDao.save(newCard);
//
//		// 更新account表最新卡ID
//		UserAccountEntity updateAccount = new UserAccountEntity();
//		updateAccount.setId(cardAccount.getAccountId());
//		updateAccount.setModifiedTime(LocalDateTime.now());
//		updateAccount.setLatestCardId(newCard.getId());
//		userAccountDao.update(updateAccount);
//	}
//
//	@Override
//	@Transactional
//	public void reissueCard(CardManageParamDto cardParam) {
//		UserAccountCardDto cardAccount = checkCardStatus(cardParam.getAccountId(), null);
//		// 校验密码
//		passwordService.checkLoginPwd(cardAccount.getAccountId(), cardParam.getLoginPwd());
//
//		// 修改旧卡状态
//		UserCardEntity oldCard = new UserCardEntity();
//		oldCard.setId(cardAccount.getCardId());
//		oldCard.setStatus(CardStatus.RETURNED.getCode());
//		oldCard.setModifiedTime(LocalDateTime.now());
//		userCardDao.update(oldCard);
//
//		// 卡片仓库旧卡状态修改
//		cardRepositoryService.activateCard(cardParam.getCardNo());
//
//		// 新卡数据
//		UserCardEntity newCard = new UserCardEntity(LocalDateTime.now());
//		IKeyGenerator userCardKey = keyGeneratorManager.getKeyGenerator(SequenceKey.USER_CARD);
//		newCard.setId(userCardKey.nextId());
//		newCard.setAccountId(cardAccount.getAccountId());
//		newCard.setCardNo(cardParam.getNewCardNo());
//		newCard.setCategory(cardAccount.getCategory());
//		newCard.setMarketId(cardAccount.getMarketId());
//		newCard.setStatus(CardStatus.NORMAL.getCode());
//		newCard.setSeinsweise(CardType.PHYSICAL_CARD.getCode());
//		newCard.setSystemDisableStatus(SystemDisableStatus.ENABLED.getCode());
//		userCardDao.save(newCard);
//
//		// 更新account表最新卡ID
//		UserAccountEntity updateAccount = new UserAccountEntity();
//		updateAccount.setId(cardAccount.getAccountId());
//		updateAccount.setModifiedTime(LocalDateTime.now());
//		updateAccount.setLatestCardId(newCard.getId());
//		userAccountDao.update(updateAccount);
//		// 卡片仓库新卡状态修改
//		cardRepositoryService.inUse(cardParam.getNewCardNo());
//	}
//
//	@Override
//	public void lostCard(Long accountId, String loginPwd) {
//		UserAccountCardDto cardAccount = checkCardStatus(accountId, null);
//		// 校验密码
//		passwordService.checkLoginPwd(cardAccount.getAccountId(), loginPwd);
//
//		// 修改卡状态
//		UserCardEntity lostCard = new UserCardEntity();
//		lostCard.setId(cardAccount.getCardId());
//		lostCard.setStatus(CardStatus.LOSS.getCode());
//		lostCard.setModifiedTime(LocalDateTime.now());
//		userCardDao.update(lostCard);
//	}
//
//	@Override
//	@Transactional
//	public void unLostCard(Long accountId, String loginPwd) {
//		UserAccountCardDto cardAccount = checkCardStatus(accountId, null);
//		if (cardAccount.getStatus() == CardStatus.NORMAL.getCode()) {
//			throw new AppException("该卡{}当前处于正常状态，解挂失败!", cardAccount.getAccountId());
//		}
//		// 校验密码
//		passwordService.checkLoginPwd(cardAccount.getAccountId(), loginPwd);
//		// 修改卡状态
//		UserCardEntity lostCard = new UserCardEntity();
//		lostCard.setId(cardAccount.getCardId());
//		lostCard.setStatus(CardStatus.NORMAL.getCode());
//		lostCard.setModifiedTime(LocalDateTime.now());
//		userCardDao.update(lostCard);
//
//	}
//
//	/**
//	 * 检查卡状态是否为已退卡,非退卡状态则返回该卡部分信息
//	 */
//	private UserAccountCardDto checkCardStatus(Long accountId, String cardNo) {
//		UserAccountCardQuery accountQuery = new UserAccountCardQuery();
//		accountQuery.setAccountId(accountId);
//		accountQuery.setCardNo(cardNo);
//		UserAccountCardDto cardAccount = userAccountCardDao.getOnly(accountQuery);
//		if (cardAccount == null) {
//			throw new AppException("卡账户{}不存在!", accountId);
//		}
//		if (cardAccount.getSystemDisableStatus() == SystemDisableStatus.DISABLED.getCode()) {
//			// TODO 该卡已被管理停用请联系管理员开启
//		}
//		return cardAccount;
//	}
//
//	@Override
//	@Transactional(propagation = Propagation.REQUIRES_NEW)
//	public void lockCard(String cardNo) {
//		checkCardStatus(null, cardNo);
//		UserCardEntity lockCard = new UserCardEntity();
//		lockCard.setCardNo(cardNo);
//		lockCard.setModifiedTime(LocalDateTime.now());
//		lockCard.setStatus(CardStatus.LOCKED.getCode());
//		userCardDao.updateByCardNo(lockCard);
//	}
//
//	@Override
//	@Transactional(propagation = Propagation.REQUIRES_NEW)
//	public void lockCard(Long accountId) {
//		// 检查卡状态
//		checkCardStatus(accountId, null);
//		// 更新数据
//		UserCardEntity lockCard = new UserCardEntity();
//		lockCard.setAccountId(accountId);
//		lockCard.setModifiedTime(LocalDateTime.now());
//		lockCard.setStatus(CardStatus.LOCKED.getCode());
//		userCardDao.updateByAccountId(lockCard);
//	}
//
//	@Override
//	public void unLock(Long accountId, String loginPwd) {
//		// 检查卡状态
//		UserAccountCardDto cardAccount = checkCardStatus(accountId, null);
//		if (cardAccount.getStatus() != CardStatus.LOCKED.getCode()) {
//			throw new AppException("该卡{}当前处于非锁定状态，解锁失败!", cardAccount.getAccountId());
//		}
//		// 校验密码
//		passwordService.checkLoginPwd(cardAccount.getAccountId(), loginPwd);
//		// 更新数据
//		UserCardEntity lockCard = new UserCardEntity();
//		lockCard.setAccountId(accountId);
//		lockCard.setModifiedTime(LocalDateTime.now());
//		lockCard.setStatus(CardStatus.NORMAL.getCode());
//		userCardDao.updateByAccountId(lockCard);
//	}

}
