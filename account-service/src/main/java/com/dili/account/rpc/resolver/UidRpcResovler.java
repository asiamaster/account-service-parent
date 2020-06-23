package com.dili.account.rpc.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dili.account.exception.AccountBizException;
import com.dili.account.rpc.UidRpc;
import com.dili.ss.constant.ResultCode;
import com.dili.ss.domain.BaseOutput;

/**
 * @description： 编号生成解析
 * 
 * @author ：WangBo
 * @time ：2020年6月22日下午5:09:37
 */
@Component
public class UidRpcResovler {

	@Autowired
	private UidRpc uidRpc;

	/**
	 * 根据业务类型获取业务号
	 * 
	 * @param type
	 * @return
	 */
	public String bizNumber(String type) {
		BaseOutput<String> baseOutput = uidRpc.bizNumber(type);
		if (!baseOutput.isSuccess()) {
			throw new AccountBizException(ResultCode.DATA_ERROR, "远程调用编号生成服务失败");
		}
		return baseOutput.getData();
	}

	/**
	 * 根据业务类型获取业务号，获取失败后每隔500毫秒进行重试
	 * 
	 * @param type
	 * @param count 重试次数
	 * @return
	 * @throws InterruptedException
	 */
	public String bizNumberRetry(String type, int count) throws InterruptedException {
		int retryCount = 0;
		BaseOutput<String> baseOutput = BaseOutput.failure();
		do {
			baseOutput = uidRpc.bizNumber(type);
			if (baseOutput.isSuccess()) {
				return baseOutput.getData();
			}
			++retryCount;
			Thread.sleep(500L);
		} while (retryCount < count);
		throw new AccountBizException(ResultCode.DATA_ERROR, "远程调用编号生成服务失败");
	}
}
