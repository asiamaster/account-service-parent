package com.dili.account.rpc.resolver;

import com.dili.account.dto.BalanceResponseDto;
import com.dili.account.dto.CreateTradeRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dili.account.dto.FundAccountDto;
import com.dili.account.dto.PayAccountDto;
import com.dili.account.dto.PayCreateFundReponseDto;
import com.dili.account.exception.AccountBizException;
import com.dili.account.rpc.PayRpc;
import com.dili.ss.constant.ResultCode;
import com.dili.ss.domain.BaseOutput;

/**
 * @description： 支付服务调用包装类
 *
 * @author ：WangBo
 * @time ：2020年6月22日下午6:07:02
 */
@Component
public class PayRpcResolver {
	private Long appid = 100101L;
	private String token = "abcd1234";
	@Autowired
	private PayRpc payRpc;

	/**
	 *
	 * @author miaoguoxin
	 * @date 2020/6/18
	 */
	public PayAccountDto resolverByUserAccount(Long account) {
		return new PayAccountDto();
	}

	/**
	 * 调用支付服务
	 *
	 * @param accountInfo
	 * @return 资金账号ID
	 */
	public Long createFundAccount(FundAccountDto accountInfo) {
		BaseOutput<PayCreateFundReponseDto> baseOutput = BaseOutput.failure();
		baseOutput = payRpc.createFundAccount(accountInfo);
		if (baseOutput.isSuccess()) {
			return baseOutput.getData().getAccountId();
		}
		throw new AccountBizException(ResultCode.DATA_ERROR, "支付服务创建资金账户失败");
	}
	/**
	 *  查询余额
	 * @author miaoguoxin
	 * @date 2020/6/30
	 */
	public BalanceResponseDto findBalanceByFundAccountId(Long fundAccountId) {
		CreateTradeRequestDto requestDto = new CreateTradeRequestDto();
		requestDto.setAccountId(fundAccountId);
		BaseOutput<BalanceResponseDto> result = payRpc.getAccountBalance(requestDto);
		if (!result.isSuccess()){
			throw new AccountBizException(ResultCode.DATA_ERROR, result.getMessage());
		}
		return result.getData();
	}
}
