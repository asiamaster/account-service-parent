package com.dili.account.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.dili.account.common.constant.JsonExcludeFilter;
import com.dili.account.dto.CardRequestDto;
import com.dili.account.service.IPasswordService;
import com.dili.account.util.AssertUtils;
import com.dili.account.validator.CardValidator;
import com.dili.ss.domain.BaseOutput;

/**
 * 密码相关操作
 */
@RestController
@RequestMapping("/api/card")
public class PasswordManageController {

	private static final Logger log = LoggerFactory.getLogger(PasswordManageController.class);

	@Autowired
	private IPasswordService passwordService;

	/**
	 * 重置登陆密码
	 */
	@RequestMapping(value = "/resetLoginPwd", method = RequestMethod.POST)
	public BaseOutput<Boolean> resetLoginPassword(@RequestBody @Validated(value = { CardValidator.Generic.class,
			CardValidator.ResetPassword.class }) CardRequestDto cardRequest) throws Exception {
		log.info("重置登陆密码>>>>" + JSONObject.toJSONString(cardRequest, JsonExcludeFilter.PWD_FILTER));
		passwordService.resetLoginPwd(cardRequest);
		return BaseOutput.success();
	}

	/**
	 * 修改密码
	 */
	@RequestMapping(value = "/modifyLoginPwd", method = RequestMethod.POST)
	public BaseOutput<Boolean> modifyLoginPwd(@RequestBody @Validated(value = { CardValidator.Generic.class,
			CardValidator.ResetPassword.class }) CardRequestDto cardRequest) throws Exception {
		log.info("修改登陆密码>>>>" + JSONObject.toJSONString(cardRequest, JsonExcludeFilter.PWD_FILTER));
		AssertUtils.notEmpty(cardRequest.getOldLoginPwd(),"原始密码不能为空");
		passwordService.modifyLoginPwd(cardRequest);
		return BaseOutput.success();
	}
	
	
	/**
	 * 校验密码
	 */
	@RequestMapping(value = "/checkPassword", method = RequestMethod.POST)
	public BaseOutput<Boolean> checkPassword(
			@RequestBody @Validated(value = { CardValidator.Generic.class }) CardRequestDto cardRequest)
			throws Exception {
		log.info("校验登陆密码>>>>" + JSONObject.toJSONString(cardRequest, JsonExcludeFilter.PWD_FILTER));
		passwordService.checkPassword(cardRequest.getAccountId(), cardRequest.getLoginPwd());
		return BaseOutput.success();
	}

}
