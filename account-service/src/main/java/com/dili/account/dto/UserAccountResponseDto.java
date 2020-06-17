package com.dili.account.dto;

import java.io.Serializable;

/**
 * @Auther: miaoguoxin
 * @Date: 2020/6/17 10:38
 * @Description: 卡账户信息
 */
public class UserAccountResponseDto implements Serializable {
    /**卡账号id*/
    private Long accountId;
    /** 父卡账号 */
    private Long parentAccountId;
    /** 卡交易类型: 1-买家 2-卖家 */
    private Integer bizUsageType;
    /**密码*/
    private String loginPwd;
    /** 资金账号ID */
    private Long fundAccountId;


    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Integer getBizUsageType() {
        return bizUsageType;
    }

    public void setBizUsageType(Integer bizUsageType) {
        this.bizUsageType = bizUsageType;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }
}
