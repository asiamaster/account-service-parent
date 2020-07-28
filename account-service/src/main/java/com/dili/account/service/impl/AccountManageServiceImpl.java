package com.dili.account.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dili.account.common.ExceptionMsg;
import com.dili.account.dao.IUserAccountDao;
import com.dili.account.dto.CardRequestDto;
import com.dili.account.entity.UserAccountDo;
import com.dili.account.exception.AccountBizException;
import com.dili.account.service.IAccountManageService;
import com.dili.account.type.DisableState;
import com.dili.ss.constant.ResultCode;

@Service("accountManageService")
public class AccountManageServiceImpl implements IAccountManageService {

	@Autowired
	private IUserAccountDao userAccountDao;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void frozen(CardRequestDto cardRequestDto) {
		this.updateDisabledState(cardRequestDto, DisableState.DISABLED);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void unfrozen(CardRequestDto cardRequestDto) {
		this.updateDisabledState(cardRequestDto, DisableState.ENABLED);
	}

	/**
	 * 卡账户禁用启用
	 */
	private void updateDisabledState(CardRequestDto cardRequestDto, DisableState disableState) {
		// 校验账户信息
		UserAccountDo userAccount = userAccountDao.getByAccountId(cardRequestDto.getAccountId());
		Optional.ofNullable(userAccount).orElseThrow(
				() -> new AccountBizException(ResultCode.DATA_ERROR, ExceptionMsg.ACCOUNT_NOT_EXIST.getName()));
		if (userAccount.getDisabledState() == disableState.getCode()) {
			throw new AccountBizException(ResultCode.DATA_ERROR, "卡片账户已"+ (disableState.getCode() == 1 ? "解冻" : "冻结") +"，不能重复操作");
		}
		if (!userAccountDao.updateDisabledState(userAccount.getAccountId(), disableState.getCode(),
				userAccount.getVersion())) {
			throw new AccountBizException(ResultCode.DATA_ERROR, "数据更新错误,冻结失败,请重试");
		}
	}
}
