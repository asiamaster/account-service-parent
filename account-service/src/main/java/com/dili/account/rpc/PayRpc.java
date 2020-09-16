package com.dili.account.rpc;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dili.account.config.PayServiceFeignConfig;
import com.dili.account.dto.BalanceResponseDto;
import com.dili.account.dto.CreateTradeRequestDto;
import com.dili.account.dto.FundAccountDto;
import com.dili.account.dto.PayCreateFundReponseDto;
import com.dili.ss.domain.BaseOutput;

/**
 * @description：
 *          支付服务调用
 * @author ：WangBo
 * @time ：2020年6月22日下午5:52:52
 */
@FeignClient(value = "pay-service", configuration = PayServiceFeignConfig.class)
public interface PayRpc {

    /**
     * 创建资金账户服务
     * @param type
     * @return
     */
    @RequestMapping(value = "/payment/api/gateway.do?service=payment.account.service:register", method = RequestMethod.POST)
    BaseOutput<PayCreateFundReponseDto> createFundAccount(FundAccountDto type);
    /**
     * 查询余额
     * @author miaoguoxin
     * @date 2020/6/30
     */
    @RequestMapping(value = "/payment/api/gateway.do?service=payment.fund.service:query", method = RequestMethod.POST)
    BaseOutput<BalanceResponseDto> getAccountBalance(@RequestBody CreateTradeRequestDto requestDto, @RequestHeader(name = "mchid") String mchid);
}
