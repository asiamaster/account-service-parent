package com.dili.account.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dili.account.dao.IUserCardDao;
import com.dili.account.dto.CardRequestDto;
import com.dili.account.dto.PayAccountDto;
import com.dili.account.entity.UserCardDo;
import com.dili.account.rpc.resolver.PayRpcResolver;
import com.dili.account.service.ICardCommand;
import com.dili.account.type.CardStatus;
import com.dili.ss.exception.BusinessException;

@Service("returnCardCommad")
public class ReturnCardCommad implements ICardCommand {
	
	@Resource 
	private IUserCardDao iUserCardDao;
	@Resource
	private PayRpcResolver payRpcResolver;

	@Override
	public void execute(CardRequestDto cardRequest) {
		UserCardDo userCardDo = iUserCardDao.findCardByAccountId(cardRequest.getAccountId());
		if (userCardDo == null) {
			throw new BusinessException("9999999999","卡信息不存在");
		}
		if (CardStatus.RETURNED.getCode() == userCardDo.getState()) {
			throw new BusinessException("9999999999","该卡已退");
		}
		if (CardStatus.NORMAL.getCode() == userCardDo.getState()) {
			throw new BusinessException("9999999999","卡非正常状态,不能退卡");
		}
		PayAccountDto payAccountDto = payRpcResolver.resolverByUserAccount(cardRequest.getAccountId());
		if (payAccountDto.getBalance() != 0L) {
			throw new BusinessException("9999999999","卡余额不为0,不能退卡");
		}
		int update = iUserCardDao.updateState(cardRequest.getAccountId(), CardStatus.RETURNED.getCode(), userCardDo.getVersion());
		if (update == 0) {
			throw new BusinessException("9999999999","退卡操作失败");
		}
	}

}
