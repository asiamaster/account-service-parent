package com.dili.account.service;

import com.dili.account.dto.CardRequestDto;

/**
 * 
 * @author wangbo
 *
 */
public interface ICardCommand {
	void execute(CardRequestDto cardRequest);
}
