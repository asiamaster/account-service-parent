package com.dili.account.service.impl;

import org.springframework.stereotype.Service;

import com.dili.account.service.IAccountQueryService;

/**
 * @description： 用户账户信息查询service实现
 *
 * @author ：WangBo
 * @time ：2020年4月22日下午5:53:40
 */
@Service("accountQueryService")
public class AccountQueryServiceImpl/* implements IAccountQueryService */ {

//	@Resource
//	private IUserAccountCardDao userAccountCardDao;
//
//	@Override
//	public List<UserAccountCardDto> listAccount(UserAccountCardQuery queryParam) {
//		return userAccountCardDao.selectList(queryParam);
//	}
//
//	@Override
//	public UserAccountCardDto getOnly(String cardNo, Long accountId) {
//		UserAccountCardQuery queryParam = new UserAccountCardQuery();
//		queryParam.setCardNo(cardNo);
//		queryParam.setAccountId(accountId);
//		UserAccountCardDto userAccount = userAccountCardDao.getOnly(queryParam);
//		userAccount.setLoginPwd(null);
//		return userAccount;
//	}

}
