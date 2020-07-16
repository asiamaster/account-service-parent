package com.dili.account.service.impl;

import com.dili.account.common.ExceptionMsg;
import com.dili.account.dao.IUserAccountCardDao;
import com.dili.account.dao.IUserAccountDao;
import com.dili.account.dao.IUserCardDao;
import com.dili.account.dto.AccountWithAssociationResponseDto;
import com.dili.account.dto.UserAccountCardQuery;
import com.dili.account.dto.UserAccountCardResponseDto;
import com.dili.account.entity.CardAggregationWrapper;
import com.dili.account.entity.UserAccountDo;
import com.dili.account.entity.UserCardDo;
import com.dili.account.exception.AccountBizException;
import com.dili.account.service.IAccountQueryService;
import com.dili.account.type.AccountUsageType;
import com.dili.account.type.CardType;
import com.dili.account.type.DisableState;
import com.dili.account.type.UsePermissionType;
import com.dili.account.util.PageUtils;
import com.dili.ss.constant.ResultCode;
import com.dili.ss.domain.PageOutput;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @description： 用户账户信息查询service实现
 *
 * @author ：WangBo
 * @time ：2020年4月22日下午5:53:40
 */
@Service("accountQueryService")
public class AccountQueryServiceImpl implements IAccountQueryService {
    @Autowired
    private IUserAccountDao userAccountDao;
    @Autowired
    private IUserCardDao userCardDao;
    @Autowired
    private IUserAccountCardDao userAccountCardDao;


    @Override
    public Boolean cardExist(String cardNo) {
        return userCardDao.getByCardNo(cardNo, 0) != null;
    }

    @Override
    public UserAccountCardResponseDto getByCardNoForRest(String cardNo) {
        UserCardDo card = userCardDao.getByCardNo(cardNo, 0);
        return this.validateAndBuildAccountCard(card);
    }

    @Override
    public UserAccountCardResponseDto getByAccountIdForRest(Long accountId) {
        UserCardDo card = userCardDao.getByAccountId(accountId);
        return this.validateAndBuildAccountCard(card);
    }

    @Override
    public AccountWithAssociationResponseDto getByCardNoWithAssociationForRest(String cardNo, Integer needReturn) {
        UserCardDo card = userCardDao.getByCardNo(cardNo, needReturn);
        UserAccountCardResponseDto primaryCard = this.validateAndBuildAccountCard(card);
        return this.getAndBuildAssociationAccountCard(primaryCard);
    }

    @Override
    public AccountWithAssociationResponseDto getByAccountIdWithAssociationForRest(Long accountId) {
        UserAccountCardResponseDto primaryCard = this.getByAccountIdForRest(accountId);
        return this.getAndBuildAssociationAccountCard(primaryCard);
    }

    @Override
    public List<UserAccountCardResponseDto> getListByConditionForRest(UserAccountCardQuery queryParam) {
        //设置默认排序字段，避免xml写太多判断
        //默认排除退还状态和禁用状态
        queryParam.setDefSort("card_create_time")
                .setDefOrder("DESC");
        queryParam.setDefExcludeReturn(1)
                .setDefExcludeDisabled(1);
        List<CardAggregationWrapper> list = userAccountCardDao.getListByCondition(queryParam);
        return list.stream().map(wrapper -> this.convertFromAccountUnionCard(
                wrapper.getUserCard(),
                wrapper.getUserAccount()))
                .collect(Collectors.toList());
    }

    @Override
    public PageOutput<List<UserAccountCardResponseDto>> getPageByConditionForRest(UserAccountCardQuery param) {
        Page<?> page = PageHelper.startPage(param.getPage(), param.getRows());
        List<UserAccountCardResponseDto> result = this.getListByConditionForRest(param);
        return PageUtils.convert2PageOutput(page, result);
    }

    @Override
    public CardAggregationWrapper getByAccountIdWithNotNull(Long accountId) {
        UserCardDo card = userCardDao.getByAccountId(accountId);
        Optional.ofNullable(card)
                .orElseThrow(() -> new AccountBizException(ResultCode.DATA_ERROR, ExceptionMsg.CARD_NOT_EXIST.getName()));
        UserAccountDo userAccount = userAccountDao.getByAccountId(card.getAccountId());
        Optional.ofNullable(userAccount)
                .orElseThrow(() -> new AccountBizException(ResultCode.DATA_ERROR, ExceptionMsg.ACCOUNT_NOT_EXIST.getName()));
        return this.combine(card, userAccount);
    }

    @Override
    public Optional<CardAggregationWrapper> getByAccountId(Long accountId) {
        UserCardDo card = userCardDao.getByAccountId(accountId);
        if (card == null) {
            return Optional.empty();
        }
        UserAccountDo userAccount = userAccountDao.getByAccountId(card.getAccountId());
        return Optional.of(this.combine(card, userAccount));
    }

    /**
     *  查询并校验卡账户（不能为禁用状态）
     * @author miaoguoxin
     * @date 2020/6/30
     */
    private UserAccountCardResponseDto validateAndBuildAccountCard(UserCardDo card) {
        Optional.ofNullable(card)
                .orElseThrow(() -> new AccountBizException(ResultCode.DATA_ERROR, ExceptionMsg.CARD_NOT_EXIST.getName()));
        UserAccountDo userAccount = userAccountDao.getByAccountId(card.getAccountId());
        Optional.ofNullable(userAccount)
                .orElseThrow(() -> new AccountBizException(ResultCode.DATA_ERROR, ExceptionMsg.ACCOUNT_NOT_EXIST.getName()));

        if (DisableState.DISABLED.getCode().equals(userAccount.getDisabledState())) {
            throw new AccountBizException(ResultCode.DATA_ERROR, ExceptionMsg.ACCOUNT_DISABLED.getName());
        }
        return this.convertFromAccountUnionCard(card, userAccount);
    }

    private CardAggregationWrapper combine(UserCardDo card, UserAccountDo account) {
        CardAggregationWrapper wrapper = new CardAggregationWrapper();
        wrapper.setAccountId(account.getAccountId());
        wrapper.setFirmId(account.getFirmId());
        wrapper.setUserAccount(account);
        wrapper.setUserCard(card);
        return wrapper;
    }

    private AccountWithAssociationResponseDto getAndBuildAssociationAccountCard(UserAccountCardResponseDto primaryCard) {
        AccountWithAssociationResponseDto result = new AccountWithAssociationResponseDto();
        //查询关联卡，primaryCard为主卡就查副卡，副卡就查主卡
        UserAccountCardQuery param = new UserAccountCardQuery();
        if (CardType.isMaster(primaryCard.getCardType())) {
            param.setParentAccountId(primaryCard.getAccountId());
        } else if (CardType.isSlave(primaryCard.getCardType())) {
            param.setAccountIds(Lists.newArrayList(primaryCard.getParentAccountId()));
        }
        List<UserAccountCardResponseDto> associationCards = this.getListByConditionForRest(param);
        result.setPrimary(primaryCard);
        result.setAssociation(associationCards);
        return result;
    }


    private UserAccountCardResponseDto convertFromAccountUnionCard(UserCardDo card, UserAccountDo account) {
        UserAccountCardResponseDto responseDto = new UserAccountCardResponseDto();
        responseDto.setCardId(card.getId());
        responseDto.setCardType(card.getType());
        responseDto.setCardNo(card.getCardNo());
        responseDto.setCardState(card.getState());
        responseDto.setUsageType(AccountUsageType.getUsageTypeList(account.getUsageType()));
        responseDto.setCardCreateTime(card.getCreateTime());
        responseDto.setCreator(card.getCreator());
        responseDto.setCreatorId(card.getCreatorId());
        responseDto.setFirmId(account.getFirmId());
        responseDto.setAccountId(account.getAccountId());
        responseDto.setFundAccountId(account.getFundAccountId());
        responseDto.setCustomerId(account.getCustomerId());
        responseDto.setPermissionList(UsePermissionType.getPermissionList(account.getPermissions()));
        responseDto.setParentAccountId(account.getParentAccountId());
        responseDto.setAccountType(account.getType());
        responseDto.setAccountState(account.getState());
        return responseDto;
    }
}
