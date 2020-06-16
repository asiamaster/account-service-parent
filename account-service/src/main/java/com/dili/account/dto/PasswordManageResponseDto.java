package com.dili.account.dto;


import javax.validation.constraints.NotBlank;

/**
 * @author:      xiaosa
 * @date:        2020/4/27
 * @version:     电子结算重构 4.4.0
 * @description: 密码相关操作的数据传输类
 */
public class PasswordManageResponseDto {

    /**
     * 账号ID-具有生成规则
     */
    private Long accountId;

    /**
     * 旧登录密码
     */
    private String oldLoginPwd;

    /**
     * 旧交易密码
     */
    private String oldTradePwd;

    /**
     * 登录密码
     */
    private String loginPwd;

    /**
     * 交易密码
     */
    private String tradePwd;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getOldLoginPwd() {
        return oldLoginPwd;
    }

    public void setOldLoginPwd(String oldLoginPwd) {
        this.oldLoginPwd = oldLoginPwd;
    }

    public String getOldTradePwd() {
        return oldTradePwd;
    }

    public void setOldTradePwd(String oldTradePwd) {
        this.oldTradePwd = oldTradePwd;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getTradePwd() {
        return tradePwd;
    }

    public void setTradePwd(String tradePwd) {
        this.tradePwd = tradePwd;
    }
}
