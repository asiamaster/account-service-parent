package com.dili.account.service;

import com.dili.account.dto.CardRequestDto;

/**
 * 账户操作相关业务
 */
public interface IAccountManageService {

	/**
	 * 冻结账户
	 */
	void frozen(CardRequestDto cardRequestDto);

	/**
	 * 解冻账户
	 */
	void unfrozen(CardRequestDto cardRequestDto);
	
}
