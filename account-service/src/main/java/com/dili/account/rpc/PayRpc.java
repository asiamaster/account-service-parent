package com.dili.account.rpc;

import org.springframework.cloud.openfeign.FeignClient;

/**
 *
 *支付远程调用 相关方法  充值 扣款 查询余额等
 *
 */
@FeignClient(value = "pay-service")
public interface PayRpc {

}
