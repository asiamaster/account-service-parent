package com.dili.account.service;

import com.dili.account.dto.UserAccountDto;

/**
 * 用户账户service接口
 */
public interface IUserAccountService {

    /**
     * 根据卡号查询账户信息 附带卡信息
     * @param cardNo
     * @return
     */
    UserAccountDto getByCardNo(String cardNo);

    /**
     * 根据账号ID查询账户信息 附带卡信息
     * @param accountId
     * @return
     */
    UserAccountDto getByAccountId(Long accountId);
}
