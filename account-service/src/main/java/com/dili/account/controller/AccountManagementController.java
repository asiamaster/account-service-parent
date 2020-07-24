package com.dili.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dili.account.dto.CardRequestDto;
import com.dili.account.service.IAccountManageService;
import com.dili.ss.domain.BaseOutput;

/**
 * 卡账户管理操作
 * @Auther: miaoguoxin
 * @Date: 2020/6/29 14:39
 */
@RestController
@RequestMapping("/sapi/account")
public class AccountManagementController {

	@Autowired
	private IAccountManageService accountManageService;
	
	/**
	 * 冻结账户
	 */
	@GetMapping("/frozen.action")
	public BaseOutput<Boolean> frozen(@RequestBody CardRequestDto cardRequestDto) {
		accountManageService.frozen(cardRequestDto);
        return BaseOutput.success();
	}
	
	/**
	 * 解冻账户
	 */
	@GetMapping("/unfrozen.action")
	public BaseOutput<Boolean> unfroze(@RequestBody CardRequestDto cardRequestDto) {
		accountManageService.unfrozen(cardRequestDto);
        return BaseOutput.success();
	}
}
