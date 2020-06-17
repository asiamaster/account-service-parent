package com.dili.account.service.impl;

import com.dili.account.dao.IUserAccountDao;
import com.dili.account.dto.UserAccountDto;
import com.dili.account.dto.UserAccountQuery;
import com.dili.account.service.IUserAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户账户service实现类
 */
@Service
public class UserAccountServiceImpl implements IUserAccountService {

    @Resource
    private IUserAccountDao userAccountDao;

    @Override
    public UserAccountDto getByCardNo(String cardNo) {
        UserAccountQuery query = new UserAccountQuery();
        query.setCardNo(cardNo);
        return userAccountDao.findOneWithCardInfo(query);
    }

    @Override
    public UserAccountDto getByAccountId(Long accountId) {
        UserAccountQuery query = new UserAccountQuery();
        query.setAccountId(accountId);
        return userAccountDao.findOneWithCardInfo(query);
    }
}
