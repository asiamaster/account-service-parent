package com.dili.account.service.impl;

import com.dili.account.common.ExceptionMsg;
import com.dili.account.dao.IUserAccountDao;
import com.dili.account.dao.IUserCardDao;
import com.dili.account.dto.UserAccountCardQuery;
import com.dili.account.dto.UserAccountCardResponseDto;
import com.dili.account.entity.CardAggregationWrapper;
import com.dili.account.entity.UserAccountDo;
import com.dili.account.entity.UserCardDo;
import com.dili.account.service.IAccountQueryService;
import com.dili.account.type.UsePermissionType;
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
    public List<UserAccountCardResponseDto> listAccount(UserAccountCardQuery queryParam) {
        return null;
    }

    @Override
    public UserAccountCardResponseDto getByCardNoForRest(String cardNo) {
        UserCardDo card = userCardDao.getByCardNo(cardNo);
        Optional.ofNullable(card)
                .orElseThrow(() -> new BusinessException(ResultCode.DATA_ERROR, ExceptionMsg.CARD_NOT_EXIST.getName()));
        UserAccountDo userAccount = userAccountDao.getByAccountId(card.getAccountId());
        Optional.ofNullable(userAccount)
                .orElseThrow(() -> new BusinessException(ResultCode.DATA_ERROR, ExceptionMsg.ACCOUNT_NOT_EXIST.getName()));
        return this.convertFromAccountUnionCard(card, userAccount);
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
        return this.combine(card, userAccount);
    }

    private CardAggregationWrapper combine(UserCardDo card, UserAccountDo account) {
        CardAggregationWrapper cardAggregationDto = new CardAggregationWrapper();
        cardAggregationDto.setFirmId(account.getFirmId());
        cardAggregationDto.setUserAccount(account);
        cardAggregationDto.setUserCard(card);
        return cardAggregationDto;
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
        responseDto.setCustomerId(account.getCustormerId());
        responseDto.setPermissionList(UsePermissionType.getPermissionList(account.getPermissions()));
        responseDto.setParentAccountId(account.getParentAccountId());
        responseDto.setBizUsageType(account.getUsageType());
        return responseDto;
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
