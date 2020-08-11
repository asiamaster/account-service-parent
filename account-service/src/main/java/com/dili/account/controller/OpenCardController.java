package com.dili.account.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.dili.account.dto.OpenCardDto;
import com.dili.account.dto.OpenCardResponseDto;
import com.dili.account.service.IOpenCardService;
import com.dili.account.util.AssertUtils;
import com.dili.ss.domain.BaseOutput;

/**
 *	开卡服务          
 * @author ：WangBo
 * @time ：2020年4月28日下午3:36:22
 */
/**
 * @author mrkin
 *
 */
@RestController
@RequestMapping(value = "api/account")
public class OpenCardController {

	private static final Logger log = LoggerFactory.getLogger(OpenCardController.class);

	@Resource
	private IOpenCardService openCardService;

	/**
	 * 主卡开卡
	 * 
	 * @throws InterruptedException
	 */
	@PostMapping("openMasterCard")
	public BaseOutput<?> openMasterCard(@RequestBody OpenCardDto openCardInfo) {
		log.info("api/account/openMasterCard *****" + JSONObject.toJSONString(openCardInfo));
		// 主要参数校验
		checkMasterParam(openCardInfo);
		OpenCardResponseDto response = null;
		// 开卡
		response = openCardService.openMasterCard(openCardInfo);
		return BaseOutput.success("success").setData(response);
	}

	/**
	 * 副卡开卡
	 */
	@PostMapping("openSlaveCard")
	public BaseOutput<?> openSlaveCard(@RequestBody OpenCardDto openCardInfo) throws Exception {
		log.info("api/account/openSlaveCard *****" + JSONObject.toJSONString(openCardInfo));
		// 主要参数校验
		AssertUtils.notNull(openCardInfo.getParentAccountId(), "主卡信息不能为空!");
		OpenCardResponseDto response = openCardService.openSlaveCard(openCardInfo);
		return BaseOutput.success("success").setData(response);
	}

	/**
	 * 主卡参数校验
	 * 
	 * @param openCardInfo
	 */
	private void checkMasterParam(OpenCardDto openCardInfo) {
		AssertUtils.notEmpty(openCardInfo.getCustomerName(), "开卡用户名不能为空!");
		AssertUtils.notEmpty(openCardInfo.getCustomerCertificateNumber(), "开卡用户名证件号不能为空!");
		AssertUtils.notEmpty(openCardInfo.getCustomerContactsPhone(), "账户联系电话不能为空!");
		AssertUtils.notNull(openCardInfo.getFirmId(), "开卡市场编码不能为空!");
		AssertUtils.notEmpty(openCardInfo.getLoginPwd(), "账户密码不能为空!");
	}

}
