package com.dili.account.rpc.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dili.account.dto.FundAccountDto;
import com.dili.account.dto.PayAccountDto;
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
	 * @return
	 */
	public String createFundAccount(FundAccountDto accountInfo) {
		BaseOutput<String> baseOutput = BaseOutput.failure();
		baseOutput = payRpc.createFundAccount(accountInfo);
		if (baseOutput.isSuccess()) {
			return baseOutput.getData();
		}
		throw new AccountBizException(ResultCode.DATA_ERROR, "支付服务创建资金账户失败");
	}
}
