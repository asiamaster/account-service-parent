package com.dili.account.manage.commad;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dili.account.dao.IUserCardDao;
import com.dili.account.dto.CardRequestDto;
import com.dili.account.manage.ICardCommand;

@Service("returnCardCommad")
public class ReturnCardCommad implements ICardCommand {
	
	@Resource 
	private IUserCardDao iUserCardDao;

	@Override
	public void execute(CardRequestDto cardRequest) {
	}

}
