package com.dili.account.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dili.account.dto.FundAccountDto;
import com.dili.ss.domain.BaseOutput;

/**
 * @description： 
 *          支付服务调用
 * @author ：WangBo
 * @time ：2020年6月22日下午5:52:52
 */
@FeignClient(value = "dili-pay")
public interface PayRpc {
	
	/**
     * 创建资金账户服务
     * @param type
     * @return
     */
    @RequestMapping(value = "/api/createFundAccount", method = RequestMethod.POST)
    BaseOutput<String> createFundAccount(FundAccountDto type);
}
