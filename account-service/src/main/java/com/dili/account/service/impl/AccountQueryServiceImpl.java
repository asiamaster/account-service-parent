package com.dili.account.service.impl;

import com.dili.account.common.ExceptionMsg;
import com.dili.account.dao.IUserAccountDao;
import com.dili.account.dao.IUserCardDao;
import com.dili.account.dto.CardAggregationDto;
import com.dili.account.dto.CustomerResponseDto;
import com.dili.account.dto.UserAccountCardQuery;
import com.dili.account.dto.UserAccountResponseDto;
import com.dili.account.dto.UserCardResponseDto;
import com.dili.account.entity.CardAggregationWrapper;
import com.dili.account.entity.UserAccountDo;
import com.dili.account.entity.UserCardDo;
import com.dili.account.service.IAccountQueryService;
import com.dili.ss.constant.ResultCode;
import com.dili.ss.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public List<CardAggregationDto> listAccount(UserAccountCardQuery queryParam) {
        return null;
    }

    @Override
    public CardAggregationDto getOnly(String cardNo, Long accountId) {

        return null;
    }

    @Override
    public CardAggregationWrapper getByAccountIdWithNotNull(Long accountId) {
        return this.getByAccountIdWithNotNull(accountId, false);
    }

    @Override
    public CardAggregationWrapper getByAccountIdWithNotNull(Long accountId, boolean needCustomerInfo) {
        UserCardDo card = userCardDao.getByAccountId(accountId);
        Optional.ofNullable(card)
                .orElseThrow(() -> new BusinessException(ResultCode.DATA_ERROR, ExceptionMsg.CARD_NOT_EXIST.getName()));
        UserAccountDo userAccount = userAccountDao.getByAccountId(card.getAccountId());
        Optional.ofNullable(userAccount)
                .orElseThrow(() -> new BusinessException(ResultCode.DATA_ERROR, ExceptionMsg.ACCOUNT_NOT_EXIST.getName()));
        CustomerResponseDto customer = null;
        //TODO 远程查询客户信息
        if (needCustomerInfo) {
            customer = new CustomerResponseDto();
        }

        return this.combine(card, userAccount, customer);
    }

    private CardAggregationWrapper combine(UserCardDo card, UserAccountDo account, CustomerResponseDto customer) {
        CardAggregationWrapper cardAggregationDto = new CardAggregationWrapper();
        cardAggregationDto.setFirmId(account.getFirmId());
        cardAggregationDto.setUserAccount(account);
        cardAggregationDto.setUserCard(card);
        cardAggregationDto.setCustomerInfo(customer);
        return cardAggregationDto;
    }

//	@Resource
//	private IUserAccountCardDao userAccountCardDao;
//
//	@Override
//	public List<UserAccountCardDto> listAccount(UserAccountCardQuery queryParam) {
//		return userAccountCardDao.selectList(queryParam);
//	}
//
//	@Override
//	public UserAccountCardDto getOnly(String cardNo, Long accountId) {
//		UserAccountCardQuery queryParam = new UserAccountCardQuery();
//		queryParam.setCardNo(cardNo);
//		queryParam.setAccountId(accountId);
//		UserAccountCardDto userAccount = userAccountCardDao.getOnly(queryParam);
//		userAccount.setLoginPwd(null);
//		return userAccount;
//	}

}
