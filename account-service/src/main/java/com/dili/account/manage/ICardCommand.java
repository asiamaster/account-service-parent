package com.dili.account.manage;

import com.dili.account.dto.CardRequestDto;

/**
 * 
 * @author wangbo
 *
 */
public interface ICardCommand {
	void execute(CardRequestDto cardRequest);
}
