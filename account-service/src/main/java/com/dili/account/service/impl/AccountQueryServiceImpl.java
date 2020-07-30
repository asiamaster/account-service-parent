package com.dili.account.service.impl;

import com.dili.account.common.ExceptionMsg;
import com.dili.account.dao.IUserAccountCardDao;
import com.dili.account.dao.IUserCardDao;
import com.dili.account.dto.AccountSimpleResponseDto;
import com.dili.account.dto.BalanceResponseDto;
import com.dili.account.dto.UserAccountCardQuery;
import com.dili.account.dto.UserAccountCardResponseDto;
import com.dili.account.entity.CardAggregationWrapper;
import com.dili.account.entity.UserAccountDo;
import com.dili.account.entity.UserCardDo;
import com.dili.account.exception.AccountBizException;
import com.dili.account.rpc.resolver.PayRpcResolver;
import com.dili.account.service.IAccountQueryService;
import com.dili.account.type.AccountUsageType;
import com.dili.account.type.CardStatus;
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
import org.springframework.util.CollectionUtils;

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
    private IUserCardDao userCardDao;
    @Autowired
    private IUserAccountCardDao userAccountCardDao;
    @Autowired
    private PayRpcResolver payRpcResolver;


    @Override
    public Boolean cardExist(String cardNo) {
        return userCardDao.getByCardNo(cardNo, 0) != null;
    }

    @Override
    public AccountSimpleResponseDto getByCardNoWithBalance(String cardNo) {
        UserAccountCardQuery query = new UserAccountCardQuery();
        query.setCardNos(Lists.newArrayList(cardNo));
        UserAccountCardResponseDto userAccount = this.getSingleForRest(query, true);
        BalanceResponseDto fund = payRpcResolver.findBalanceByFundAccountId(userAccount.getFundAccountId());
        return new AccountSimpleResponseDto(fund, userAccount);
    }


    @Override
    public UserAccountCardResponseDto getSingleForRest(UserAccountCardQuery queryParam, boolean needValidate) {
        queryParam.setDefExcludeUnusualState(0);
        CardAggregationWrapper wrapper = this.getSingle(queryParam, needValidate);
        return this.convertFromAccountUnionCard(wrapper.getUserCard(), wrapper.getUserAccount());
    }


    @Override
    public List<UserAccountCardResponseDto> getListByConditionForRest(UserAccountCardQuery queryParam) {
        //最多查询50条数据，防止爆库
        PageHelper.startPage(1, 50, false);
        List<CardAggregationWrapper> list = this.getWrapperList(queryParam);
        return list.stream().map(wrapper -> this.convertFromAccountUnionCard(
                wrapper.getUserCard(),
                wrapper.getUserAccount()))
                .collect(Collectors.toList());
    }


    @Override
    public PageOutput<List<UserAccountCardResponseDto>> getPageByConditionForRest(UserAccountCardQuery param) {
        Page<?> page = PageHelper.startPage(param.getPage(), param.getRows());
        List<CardAggregationWrapper> wrapperList = this.getWrapperList(param);
        List<UserAccountCardResponseDto> result = wrapperList.stream().map(wrapper -> this.convertFromAccountUnionCard(
                wrapper.getUserCard(),
                wrapper.getUserAccount()))
                .collect(Collectors.toList());
        return PageUtils.convert2PageOutput(page, result);
    }


    @Override
    public CardAggregationWrapper getByAccountIdForUnLostCard(Long accountId) {
        UserAccountCardQuery query = new UserAccountCardQuery();
        query.setAccountIds(Lists.newArrayList(accountId));
        query.setLast(1);
        query.setDefExcludeUnusualState(0);

        CardAggregationWrapper wrapper = this.getSingle(query, false);
        UserAccountDo userAccount = wrapper.getUserAccount();
        UserCardDo userCard = wrapper.getUserCard();
        if (CardStatus.RETURNED.getCode() == userCard.getState()) {
            throw new AccountBizException(ResultCode.DATA_ERROR, "该卡为退还状态，不能进行此操作");
        }
        if (DisableState.DISABLED.getCode().equals(userAccount.getDisabledState())) {
            throw new AccountBizException(ResultCode.DATA_ERROR, "该卡账户为禁用状态，不能进行此操作");
        }
        return wrapper;
    }

    @Override
    public CardAggregationWrapper getByAccountIdForGenericOp(Long accountId) {
        UserAccountCardQuery query = new UserAccountCardQuery();
        query.setAccountIds(Lists.newArrayList(accountId));
        query.setLast(1);
        query.setDefExcludeUnusualState(0);
        return this.getSingle(query, true);
    }

    @Override
    public Optional<CardAggregationWrapper> getByAccountId(Long accountId) {
        UserAccountCardQuery query = new UserAccountCardQuery();
        query.setAccountIds(Lists.newArrayList(accountId));
        query.setDefExcludeUnusualState(0);
        List<CardAggregationWrapper> wrapperList = this.getWrapperList(query);
        if (wrapperList.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(wrapperList.get(0));
    }

    @Override
    public CardAggregationWrapper getSingle(UserAccountCardQuery queryParam, boolean needValidate) {
        queryParam.setDefExcludeUnusualState(0);
        PageHelper.startPage(1, 1, false);
        List<CardAggregationWrapper> list = this.getWrapperList(queryParam);
        if (CollectionUtils.isEmpty(list)) {
            throw new AccountBizException(ResultCode.DATA_ERROR, ExceptionMsg.ACCOUNT_NOT_EXIST.getName());
        }
        CardAggregationWrapper wrapper = list.get(0);
        if (needValidate) {
            UserCardDo userCard = wrapper.getUserCard();
            UserAccountDo userAccount = wrapper.getUserAccount();
            if (CardStatus.RETURNED.getCode() == userCard.getState()) {
                throw new AccountBizException(ResultCode.DATA_ERROR, "该卡为退还状态，不能进行此操作");
            }
            if (DisableState.DISABLED.getCode().equals(userAccount.getDisabledState())) {
                throw new AccountBizException(ResultCode.DATA_ERROR, "该卡账户为禁用状态，不能进行此操作");
            }
            if (CardStatus.LOSS.getCode() == userCard.getState()) {
                throw new AccountBizException(ResultCode.DATA_ERROR, "该卡为挂失状态，不能进行此操作");
            }
            //如果是副卡，查询主卡状态
            this.validateMaster(queryParam, userCard, userAccount);
        }
        return wrapper;
    }

    private void validateMaster(UserAccountCardQuery queryParam, UserCardDo userCard, UserAccountDo userAccount) {
        if (CardType.isSlave(userCard.getType())) {
            List<CardAggregationWrapper> parentList = this.getWrapperList(queryParam);
            if (CollectionUtils.isEmpty(parentList)) {
                throw new AccountBizException(ResultCode.DATA_ERROR, "主卡不存在");
            }
            CardAggregationWrapper masterWrapper = parentList.get(0);
            if (CardStatus.LOSS.getCode() == masterWrapper.getUserCard().getState()) {
                throw new AccountBizException(ResultCode.DATA_ERROR, "该卡的主卡为挂失状态，不能进行此操作");
            }
            if (DisableState.DISABLED.getCode().equals(userAccount.getDisabledState())) {
                throw new AccountBizException(ResultCode.DATA_ERROR, "该卡的主卡账户为禁用状态，不能进行此操作");
            }
        }
    }


    private List<CardAggregationWrapper> getWrapperList(UserAccountCardQuery queryParam) {
        //设置默认排序字段，避免xml写太多判断
        //默认排除退还状态和禁用状态
        queryParam.setDefSort("card_create_time")
                .setDefOrder("DESC");
        queryParam.setDefExcludeUnusualState(1);
        return userAccountCardDao.getListByCondition(queryParam);
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
        responseDto.setPermissionList(UsePermissionType.getPermissionList(account.getPermissions()));
        responseDto.setParentAccountId(account.getParentAccountId());
        responseDto.setAccountType(account.getType());
        responseDto.setAccountState(account.getState());
        responseDto.setDisabledState(account.getDisabledState());

        responseDto.setCustomerId(account.getCustomerId());
        responseDto.setCustomerName(account.getCustomerName());
        responseDto.setCustomerCode(account.getCustomerCode());
        responseDto.setCustomerMarketType(account.getCustomerMarketType());
        responseDto.setCustomerCellphone(account.getCustomerCellphone());
        responseDto.setCustomerCertificateNumber(account.getCustomerCertificateNumber());
        responseDto.setCustomerCertificateType(account.getCustomerCertificateType());
        return responseDto;
    }


}
