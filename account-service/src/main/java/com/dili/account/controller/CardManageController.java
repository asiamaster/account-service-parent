package com.dili.account.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dili.account.dto.CardRequestDto;
import com.dili.account.service.ICardManageService;
import com.dili.ss.domain.BaseOutput;

/**
 * 卡片管理服务，退卡、挂失、解挂、补卡等
 * @author ：WangBo
 * @time ：2020年4月28日下午4:04:46
 */
@RestController
@RequestMapping(value = "/account")
public class CardManageController {

	@Resource
	private ICardManageService cardManageService;

	/**
	 * 退卡
	 */
	@PostMapping("/returnCard")
	public BaseOutput<Boolean> returnCard(@RequestBody CardRequestDto cardRequest) {
		cardManageService.returnCard(cardRequest);
		return BaseOutput.success();
	}
}
