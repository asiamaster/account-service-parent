package com.dili.account.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 卡片管理服务，退卡、挂失、解挂、补卡等
 * 
 * @author ：WangBo
 * @time ：2020年4月28日下午4:04:46
 */
@RestController
@RequestMapping(value = "user")
public class CardManageController {

//	@Resource
//	private ICardManageService cardManageService;
//
//	/**
//	 * 退卡
//	 */
//	@PostMapping("returnCard")
//	public Message<?> returnCard(@RequestBody CardManageParamDto cardParam) {
//		AssertUtils.notNull(cardParam.getAccountId(),"卡账号不能为空!");
//		AssertUtils.notEmpty(cardParam.getLoginPwd(),"登录密码不能为空!");
//		cardManageService.returnCard(cardParam);
//		return Message.success();
//	}
//
//	/**
//	 * 换卡
//	 */
//	@PostMapping("changeCard")
//	public Message<?> changeCard(@RequestBody CardManageParamDto cardParam) {
//		AssertUtils.notNull(cardParam.getAccountId(),"卡账号不能为空!");
//		AssertUtils.notEmpty(cardParam.getLoginPwd(),"登录密码不能为空!");
//		AssertUtils.notEmpty(cardParam.getNewCardNo(),"新卡号不能为空!");
//		cardManageService.changeCard(cardParam);
//		return Message.success();
//	}
//
//	/**
//	 * 补卡
//	 */
//	@PostMapping("reissueCard")
//	public Message<?> reissueCard(@RequestBody CardManageParamDto cardParam) {
//		AssertUtils.notNull(cardParam.getAccountId(),"卡账号不能为空!");
//		AssertUtils.notEmpty(cardParam.getLoginPwd(),"登录密码不能为空!");
//		AssertUtils.notEmpty(cardParam.getNewCardNo(),"新卡号不能为空!");
//		cardManageService.reissueCard(cardParam);
//		return Message.success();
//	}
//
//	/**
//	 * 挂失卡片
//	 */
//	@PostMapping("lostCard")
//	public Message<?> lostCard(@RequestBody CardManageParamDto cardParam) {
//		AssertUtils.notNull(cardParam.getAccountId(),"卡账号不能为空!");
//		AssertUtils.notEmpty(cardParam.getLoginPwd(),"登录密码不能为空!");
//		cardManageService.lostCard(cardParam.getAccountId(), cardParam.getLoginPwd());
//		return Message.success();
//	}
//
//	/**
//	 * 解挂卡片
//	 */
//	@PostMapping("unLostCard")
//	public Message<?> unLostCard(@RequestBody CardManageParamDto cardParam) {
//		AssertUtils.notNull(cardParam.getAccountId(),"卡账号不能为空!");
//		AssertUtils.notEmpty(cardParam.getLoginPwd(),"登录密码不能为空!");
//		cardManageService.unLostCard(cardParam.getAccountId(), cardParam.getLoginPwd());
//		return Message.success();
//	}
//	
//	/**
//	 * 解锁卡片
//	 */
//	@PostMapping("unLockCard")
//	public Message<?> unLockCard(@RequestBody CardManageParamDto cardParam) {
//		AssertUtils.notNull(cardParam.getAccountId(),"卡账号不能为空!");
//		cardManageService.unLock(cardParam.getAccountId(), cardParam.getLoginPwd());
//		return Message.success();
//	}
}
