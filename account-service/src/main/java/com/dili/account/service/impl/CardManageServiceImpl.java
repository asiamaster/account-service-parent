package com.dili.account.service.impl;

import com.dili.account.service.card.CardStateManager;
import com.dili.account.type.CardStatus;
import com.dili.ss.exception.BusinessException;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dili.account.dao.IUserCardDao;
import com.dili.account.dto.CardRequestDto;
import com.dili.account.dto.PayAccountDto;
import com.dili.account.entity.UserCardDo;
import com.dili.account.manage.commad.CardCommandCreator;
import com.dili.account.manage.commad.CardCommandType;
import com.dili.account.rpc.resolver.PayRpcResolver;
import com.dili.account.service.ICardManageService;

/**
 * @description： 卡片退卡换卡等操作service实现
 *
 * @author ：WangBo
 * @time ：2020年4月28日下午5:09:47
 */
@Service("cardManageService")
public class CardManageServiceImpl implements ICardManageService {
	@Autowired
	private CardStateManager cardStateManager;
	@Resource 
	private IUserCardDao iUserCardDao;
	@Resource
	private PayRpcResolver payRpcResolver;

	@Override
	@Transactional
	public void returnCard(CardRequestDto cardRequest) {
		UserCardDo userCardDo = iUserCardDao.findCardByAccountId(cardRequest.getAccountId());
		if (userCardDo == null) {
			throw new BusinessException("9999999999","卡信息不存在");
		}
		if (CardStatus.RETURNED.getCode() == userCardDo.getState()) {
			throw new BusinessException("9999999999","该卡已退");
		}
		if (CardStatus.NORMAL.getCode() == userCardDo.getState()) {
			throw new BusinessException("9999999999","卡非正常状态,不能退卡");
		}
		PayAccountDto payAccountDto = payRpcResolver.resolverByUserAccount(cardRequest.getAccountId());
		if (payAccountDto.getBalance() != 0L) {
			throw new BusinessException("9999999999","卡余额不为0,不能退卡");
		}
		int update = iUserCardDao.updateState(cardRequest.getAccountId(), CardStatus.RETURNED.getCode(), userCardDo.getVersion());
		if (update == 0) {
			throw new BusinessException("9999999999","退卡操作失败");
		}
	}

    @Override
    public void reportLoss(CardRequestDto cardParam) {
        cardStateManager.doReportLoss(cardParam);
    }

	@Override
	public void changeCard(CardRequestDto cardParam) {

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
