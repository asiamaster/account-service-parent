package com.dili.account.service.impl;

import com.dili.account.dao.IUserAccountDao;
import com.dili.account.dao.IUserCardDao;
import com.dili.account.dto.CardRequestDto;
import com.dili.account.entity.CardAggregationWrapper;
import com.dili.account.entity.CardStorageDo;
import com.dili.account.entity.UserAccountDo;
import com.dili.account.entity.UserCardDo;
import com.dili.account.exception.AccountBizException;
import com.dili.account.service.IAccountQueryService;
import com.dili.account.service.ICardManageService;
import com.dili.account.service.ICardStorageService;
import com.dili.account.service.IPasswordService;
import com.dili.account.type.CardLastState;
import com.dili.account.type.CardStatus;
import com.dili.ss.constant.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void returnCard(CardRequestDto cardRequest) {
        UserCardDo userCardDo = userCardDao.getByAccountId(cardRequest.getAccountId());
        if (userCardDo == null) {
            throw new AccountBizException(ResultCode.DATA_ERROR, "卡信息不存在");
        }
        if (CardStatus.NORMAL.getCode() != userCardDo.getState()) {
            throw new AccountBizException(ResultCode.DATA_ERROR, "卡非正常状态,不能退卡");
        }
        //密码校验
        passwordService.checkPassword(cardRequest.getAccountId(), cardRequest.getLoginPwd());
        //副卡校验
        List<UserAccountDo> accounts = userAccountDao.findSlavesByParent(cardRequest.getAccountId());
        if (!CollectionUtils.isEmpty(accounts)) {
            throw new AccountBizException(ResultCode.DATA_ERROR, "该卡存在副卡,不能退卡");
        }
        //更新卡状态
        int update = userCardDao.updateState(cardRequest.getAccountId(), CardStatus.RETURNED.getCode(), userCardDo.getVersion());
        if (update == 0) {
            throw new AccountBizException(ResultCode.DATA_ERROR, "退卡操作失败");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reportLoss(CardRequestDto cardParam) {
        CardAggregationWrapper wrapper = accountQueryService.getByAccountIdForGenericOp(cardParam.getAccountId());
        UserCardDo userCard = wrapper.getUserCard();

        this.validateCanReportLoss(userCard, cardParam);
        this.changeState(userCard, CardStatus.LOSS.getCode());
        userCardDao.update(userCard);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserCardDo changeCard(CardRequestDto cardParam) {
        CardAggregationWrapper wrapper = accountQueryService.getByAccountIdForGenericOp(cardParam.getAccountId());

        this.validateCanChangeCard(wrapper, cardParam);
        UserCardDo oldCard = wrapper.getUserCard();
        UserCardDo newCard = this.cloneWhenChangeCard(oldCard, cardParam);
        //退还旧卡
        this.changeState(oldCard, CardStatus.RETURNED.getCode());
        //赋值为旧卡状态
        oldCard.setLast(CardLastState.NO.getCode());

        userCardDao.update(oldCard);
        userCardDao.save(newCard);

        //新卡出库
        //cardStorageService.voidCard(oldCard.getCardNo(), "");
        cardStorageService.inUse(newCard.getCardNo());
        return newCard;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void unLostCard(CardRequestDto cardParam) {
        CardAggregationWrapper wrapper = accountQueryService.getByAccountIdForUnLostCard(cardParam.getAccountId());
        UserCardDo userCard = wrapper.getUserCard();
        if (CardStatus.LOSS.getCode() != userCard.getState()) {//非挂失状态卡片，不允许解挂
            throw new AccountBizException(ResultCode.DATA_ERROR, "该卡为非挂失状态,不能进行此操作");
        }
        passwordService.checkPassword(cardParam.getAccountId(), cardParam.getLoginPwd());
        int i = userCardDao.updateState(cardParam.getAccountId(), CardStatus.NORMAL.getCode(), userCard.getVersion());
        if (i != 1) {
            throw new AccountBizException(ResultCode.DATA_ERROR, "解挂失操作失败");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void unLockCard(CardRequestDto cardParam) {
        CardAggregationWrapper wrapper = accountQueryService.getByAccountIdForGenericOp(cardParam.getAccountId());
        UserCardDo userCard = wrapper.getUserCard();
        if (CardStatus.LOCKED.getCode() != userCard.getState()) {//非锁定状态卡片，不允许解锁
            throw new AccountBizException(ResultCode.DATA_ERROR, "该卡为非锁定状态,不能进行此操作");
        }
        passwordService.checkPassword(cardParam.getAccountId(), cardParam.getLoginPwd());
        int i = userCardDao.updateState(cardParam.getAccountId(), CardStatus.NORMAL.getCode(), userCard.getVersion());
        if (i != 1) {
            throw new AccountBizException(ResultCode.DATA_ERROR, "解锁操作失败");
        }
    }

    private void validateCanReportLoss(UserCardDo userCard, CardRequestDto cardParam) {
        if (userCard.getState() != CardStatus.NORMAL.getCode()) {
            throw new AccountBizException(ResultCode.DATA_ERROR, "该卡为非正常状态，不能进行此操作");
        }
        passwordService.checkPassword(cardParam.getAccountId(), cardParam.getLoginPwd());
    }

    private void validateCanChangeCard(CardAggregationWrapper wrapper, CardRequestDto cardParam) {
        UserCardDo userCard = wrapper.getUserCard();
        if (userCard.getState() != CardStatus.NORMAL.getCode()) {
            throw new AccountBizException(ResultCode.DATA_ERROR, "该卡为非正常状态，不能进行此操作");
        }
        if (userCard.getCardNo().equalsIgnoreCase(cardParam.getNewCardNo())) {
            throw new AccountBizException(ResultCode.DATA_ERROR, "新老卡片的卡号不能相同");
        }
        //换卡可以不需要密码
        if (StringUtils.isNoneBlank(cardParam.getLoginPwd())) {
            passwordService.checkPassword(cardParam.getAccountId(), cardParam.getLoginPwd());
        }
        //主卡换主卡，副卡换副卡
        CardStorageDo cardStorageDo = cardStorageService.getByCardNo(userCard.getCardNo());
        if (!cardStorageDo.getType().equals(userCard.getType())) {
            throw new AccountBizException(ResultCode.DATA_ERROR, "新老卡片类型不一致");
        }
    }

    private void changeState(UserCardDo userCard, Integer targetState) {
        userCard.setState(targetState);
        userCard.setModifyTime(LocalDateTime.now());
    }

    private UserCardDo cloneWhenChangeCard(UserCardDo old, CardRequestDto param) {
        UserCardDo newCard = (UserCardDo) old.clone();
        newCard.setCardNo(param.getNewCardNo());
        newCard.setLast(1);
        newCard.setCreator(param.getOpName());
        newCard.setCreatorId(param.getOpId());
        newCard.setState(CardStatus.NORMAL.getCode());
        newCard.setCreateTime(LocalDateTime.now());
        newCard.setModifyTime(LocalDateTime.now());
        return newCard;
    }
}
