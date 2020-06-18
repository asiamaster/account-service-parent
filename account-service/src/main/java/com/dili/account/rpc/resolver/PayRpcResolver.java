package com.dili.account.rpc.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dili.account.dto.PayAccountDto;
import com.dili.account.rpc.PayRpc;

/**
 *
 * 支付远程解析器   解析返回值信息
 *
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

}
