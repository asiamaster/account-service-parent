package com.dili.account.service.impl;

import com.dili.account.common.ExceptionMsg;
import com.dili.account.dao.IUserAccountCardDao;
import com.dili.account.dao.IUserAccountDao;
import com.dili.account.dao.IUserCardDao;
import com.dili.account.dto.UserAccountCardQuery;
import com.dili.account.dto.UserAccountCardResponseDto;
import com.dili.account.entity.CardAggregationWrapper;
import com.dili.account.entity.UserAccountDo;
import com.dili.account.entity.UserCardDo;
import com.dili.account.exception.AccountBizException;
import com.dili.account.service.IAccountQueryService;
import com.dili.account.type.UsePermissionType;
import com.dili.account.util.PageUtils;
import com.dili.ss.constant.ResultCode;
import com.dili.ss.domain.PageOutput;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
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
    public UserAccountCardResponseDto getByCardNoForRest(String cardNo) {
        UserCardDo card = userCardDao.getByCardNo(cardNo);
        Optional.ofNullable(card)
                .orElseThrow(() -> new AccountBizException(ResultCode.DATA_ERROR, ExceptionMsg.CARD_NOT_EXIST.getName()));
        UserAccountDo userAccount = userAccountDao.getByAccountId(card.getAccountId());
        Optional.ofNullable(userAccount)
                .orElseThrow(() -> new AccountBizException(ResultCode.DATA_ERROR, ExceptionMsg.ACCOUNT_NOT_EXIST.getName()));
        return this.convertFromAccountUnionCard(card, userAccount);
    }

    @Override
    public List<UserAccountCardResponseDto> getListByConditionForRest(UserAccountCardQuery queryParam) {
        List<CardAggregationWrapper> list = userAccountCardDao.getListByCondition(queryParam);
        return list.stream().map(wrapper -> this.convertFromAccountUnionCard(
                wrapper.getUserCard(),
                wrapper.getUserAccount()))
                .collect(Collectors.toList());
    }

    @Override
    public PageOutput<List<UserAccountCardResponseDto>> getPageByConditionForRest(UserAccountCardQuery param) {
        Page page = PageHelper.startPage(param.getPageNum(), param.getPageSize());
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

    private CardAggregationWrapper combine(UserCardDo card, UserAccountDo account) {
        CardAggregationWrapper wrapper = new CardAggregationWrapper();
        wrapper.setAccountId(account.getAccountId());
        wrapper.setFirmId(account.getFirmId());
        wrapper.setUserAccount(account);
        wrapper.setUserCard(card);
        return wrapper;
    }

    private UserAccountCardResponseDto convertFromAccountUnionCard(UserCardDo card, UserAccountDo account) {
        UserAccountCardResponseDto responseDto = new UserAccountCardResponseDto();
        responseDto.setCardId(card.getId());
        responseDto.setCardCategory(card.getCategory());
        responseDto.setCardNo(card.getCardNo());
        responseDto.setCardState(card.getState());
        responseDto.setCardUsageType(card.getUsageType());
        responseDto.setFirmId(account.getFirmId());
        responseDto.setAccountId(account.getAccountId());
        responseDto.setFundAccountId(account.getFundAccountId());
        responseDto.setCustomerId(account.getCustomerId());
        responseDto.setPermissionList(UsePermissionType.getPermissionList(account.getPermissions()));
        responseDto.setParentAccountId(account.getParentAccountId());
        responseDto.setBizUsageType(account.getUsageType());
        return responseDto;
    }
}
