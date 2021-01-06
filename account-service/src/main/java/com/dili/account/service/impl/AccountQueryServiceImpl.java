package com.dili.account.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.dili.account.common.ExceptionMsg;
import com.dili.account.common.constant.ServiceName;
import com.dili.account.dao.IUserAccountCardDao;
import com.dili.account.dao.IUserCardDao;
import com.dili.account.dto.AccountSimpleResponseDto;
import com.dili.account.dto.BalanceResponseDto;
import com.dili.account.dto.CustomerBalanceResponseDto;
import com.dili.account.dto.FundAccountDto;
import com.dili.account.dto.UserAccountCardQuery;
import com.dili.account.dto.UserAccountCardResponseDto;
import com.dili.account.entity.CardAggregationWrapper;
import com.dili.account.entity.CustomerCardWrapper;
import com.dili.account.entity.UserAccountDo;
import com.dili.account.entity.UserCardDo;
import com.dili.account.exception.AccountBizException;
import com.dili.account.exception.ErrorCode;
import com.dili.account.rpc.PayRpc;
import com.dili.account.rpc.resolver.GenericRpcResolver;
import com.dili.account.rpc.resolver.PayRpcResolver;
import com.dili.account.service.IAccountQueryService;
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

/**
 * @description： 用户账户信息查询service实现
 *
 * @author ：WangBo
 * @time ：2020年4月22日下午5:53:40
 */
@Service("accountQueryService")
public class AccountQueryServiceImpl implements IAccountQueryService {
	
	private static final Logger log = LoggerFactory.getLogger(AccountQueryServiceImpl.class);

    @Autowired
    private IUserCardDao userCardDao;
    @Autowired
    private IUserAccountCardDao userAccountCardDao;
    @Autowired
    private PayRpcResolver payRpcResolver;
    
    @Autowired
    private PayRpc payRpc;
    /**需要校验的非法卡状态类型*/
    private static final CardStatus[] VALID_CARD_STATES = new CardStatus[]{CardStatus.LOSS, CardStatus.RETURNED, CardStatus.LOCKED};

    @Override
    public Boolean cardExist(String cardNo) {
        return userCardDao.getByCardNo(cardNo, 0) != null;
    }

    @Override
    public AccountSimpleResponseDto getByCardNoWithBalance(String cardNo, Long firmId) {
        UserAccountCardQuery query = new UserAccountCardQuery();
        query.setCardNos(Lists.newArrayList(cardNo));
        query.setFirmId(firmId);
        UserAccountCardResponseDto userAccount = this.getSingleForRest(query, true);
        BalanceResponseDto fund = payRpcResolver.findBalanceByFundAccountId(userAccount.getFundAccountId(), userAccount.getFirmId() + "");
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
    public List<Long> getAllCustomerIds(UserAccountCardQuery queryParam) {
        return userAccountCardDao.getAllCustomerIds(queryParam);
    }

    @Override
    public Map<String, List<String>> getCardNosGroupByCustomerIds(UserAccountCardQuery param) {
        List<CustomerCardWrapper> list = userAccountCardDao.getCarNosByCustomerIds(param);
        Map<String,List<String>> result = new HashMap<>();
        Map<String, List<CustomerCardWrapper>> collect = list.stream().collect(Collectors.groupingBy(
                customerCardWrapper -> String.valueOf(customerCardWrapper.getCustomerId())));
        collect.forEach((key, value) -> {
            List<String> cardNos = value.stream().map(CustomerCardWrapper::getCardNo).collect(Collectors.toList());
            result.put(key, cardNos);
        });
        return result;
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
        query.setDefExcludeUnusualState(0);
        //  query.setLast(1);
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
        query.setDefExcludeUnusualState(0);
        //  query.setLast(1);
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
            throw new AccountBizException(ErrorCode.ACCOUNT_NOT_EXIST, ExceptionMsg.ACCOUNT_NOT_EXIST.getName());
        }
        CardAggregationWrapper wrapper = list.get(0);
        if (!needValidate) {
            return wrapper;
        }
        UserCardDo userCard = wrapper.getUserCard();
        UserAccountDo userAccount = wrapper.getUserAccount();
        if (DisableState.DISABLED.getCode().equals(userAccount.getDisabledState())) {
            throw new AccountBizException(ResultCode.DATA_ERROR, "该卡账户为冻结状态，不能进行此操作");
        }
        for (CardStatus cardStatus : VALID_CARD_STATES) {
            if (cardStatus.getCode() == userCard.getState()) {
                throw new AccountBizException(ResultCode.DATA_ERROR, String.format("该卡为%s状态，不能进行此操作", cardStatus.getName()));
            }
        }
        //如果是副卡，查询主卡状态
        this.validateMaster(userCard, userAccount);
        return wrapper;
    }

    @Override
	public CustomerBalanceResponseDto getAccountFundByCustomerId(Long customerId, Long firmId) {
		UserAccountCardQuery masterParams = new UserAccountCardQuery();
        masterParams.setCardType(CardType.MASTER.getCode());
        masterParams.setCustomerIds(Lists.newArrayList(customerId));
        masterParams.setFirmId(firmId);
        //按照现在需求，一个customerId只有一张主卡，但是为了需求有变，这里设计成list
        //以防customerId对应多个主卡的情况
        List<UserAccountCardResponseDto> masterList = getListByConditionForRest(masterParams);
        if(CollectionUtils.isEmpty(masterList)) {
        	throw new AccountBizException("您没有开通过账户");
        }
        
        // 查询客户资产情况
        FundAccountDto fundAccountDto = new FundAccountDto();
        fundAccountDto.setCustomerId(customerId);
        CustomerBalanceResponseDto customerBalance = GenericRpcResolver.resolver(payRpc.getAccountFundByCustomerId(firmId, fundAccountDto),ServiceName.PAY);
        if(CollectionUtils.isEmpty(masterList)) {
        	log.warn("未获取到支付接口明细数据");
        }else {
	        customerBalance.getFundAccounts().forEach(accountItem -> {
	        	for(UserAccountCardResponseDto userAccount: masterList) {
	        		if(accountItem.getAccountId().longValue() == userAccount.getFundAccountId()) {
	        			accountItem.setCardNo(userAccount.getCardNo());
	        			accountItem.setCardExist(userAccount.getCardExist());
	        			break;
	        		}
	        	}
	        });
        }
        
		return customerBalance;
	}
    
    
    private void validateMaster(UserCardDo userCard, UserAccountDo userAccount) {
        if (!CardType.isSlave(userCard.getType())) {
            return;
        }
        UserAccountCardQuery query = new UserAccountCardQuery();
        query.setAccountIds(Lists.newArrayList(userAccount.getParentAccountId()));
        query.setDefExcludeUnusualState(0);
        PageHelper.startPage(1, 1, false);
        List<CardAggregationWrapper> parentList = this.getWrapperList(query);

        if (CollectionUtils.isEmpty(parentList)) {
            throw new AccountBizException(ResultCode.DATA_ERROR, "该卡的主卡不存在");
        }
        CardAggregationWrapper masterWrapper = parentList.get(0);
        UserCardDo masterCard = masterWrapper.getUserCard();
        UserAccountDo masterAccount = masterWrapper.getUserAccount();
        String cardNo = masterCard.getCardNo();
        if (DisableState.DISABLED.getCode().equals(masterAccount.getDisabledState())) {
            throw new AccountBizException(ResultCode.DATA_ERROR, String.format("该卡的主卡账户【%s】为冻结状态，不能进行此操作", cardNo));
        }
        for (CardStatus cardStatus : VALID_CARD_STATES) {
            if (cardStatus.getCode() == masterCard.getState()) {
                throw new AccountBizException(ResultCode.DATA_ERROR,
                        String.format("该卡的主卡【%s】为%s状态，不能进行此操作", cardNo, cardStatus.getName()));
            }
        }
    }


    private List<CardAggregationWrapper> getWrapperList(UserAccountCardQuery queryParam) {
        //设置默认排序字段，避免xml写太多判断
        //默认排除非正常状态
        queryParam.setDefSort("card_create_time")
                .setDefOrder("DESC");
        queryParam.setDefExcludeUnusualState(1);
        return userAccountCardDao.getListByCondition(queryParam);
    }


    private UserAccountCardResponseDto convertFromAccountUnionCard(UserCardDo card, UserAccountDo account) {
        UserAccountCardResponseDto responseDto = new UserAccountCardResponseDto();
        responseDto.setAccountPkId(account.getId());
        responseDto.setCardPkId(card.getId());

        responseDto.setCardId(card.getId());
        responseDto.setCardType(card.getType());
        responseDto.setCardNo(card.getCardNo());
        responseDto.setCardState(card.getState());
//        responseDto.setUsageType(AccountUsageType.getUsageTypeList(account.getUsageType()));
        responseDto.setCardCreateTime(card.getCreateTime());
        responseDto.setCreator(card.getCreator());
        responseDto.setCreatorId(card.getCreatorId());

        responseDto.setFirmId(account.getFirmId());
        responseDto.setAccountId(account.getAccountId());
        responseDto.setFundAccountId(account.getFundAccountId());
        responseDto.setPermissionList(UsePermissionType.getPermissionList(account.getPermissions()));
        responseDto.setParentAccountId(account.getParentAccountId());
        responseDto.setAccountTypes(account.getTypes());
        responseDto.setAccountState(account.getState());
        responseDto.setDisabledState(account.getDisabledState());

        responseDto.setCustomerId(account.getCustomerId());
        responseDto.setCustomerName(account.getCustomerName());
        responseDto.setCustomerCode(account.getCustomerCode());
        responseDto.setCustomerCharacterType(account.getCustomerCharacterType());
        responseDto.setCustomerContactsPhone(account.getCustomerContactsPhone());
        responseDto.setCustomerCertificateNumber(account.getCustomerCertificateNumber());
        responseDto.setCustomerCertificateType(account.getCustomerCertificateType());
        responseDto.setHoldName(account.getHoldName());
        responseDto.setHoldContactsPhone(account.getHoldContactsPhone());
        responseDto.setHoldCertificateNumber(account.getHoldCertificateNumber());
        responseDto.setCardExist(account.getCardExist());
        return responseDto;
    }


}
