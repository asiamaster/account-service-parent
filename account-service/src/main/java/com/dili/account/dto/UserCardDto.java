package com.dili.account.dto;

import com.dili.account.entity.UserCardDo;

/**
 * 卡相关DTO
 */
public class UserCardDto extends UserCardDo {

    /** 密码 */
    private String loginPwd;

    /** 操作人ID*/
    private Long operatorId;

    /** 操作人姓名 */
    private String operatorName;

    /**
     *
     * @return
     */
    public String getLoginPwd() {
        return loginPwd;
    }

    /**
     *
     * @param loginPwd
     */
    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public Long getOperatorId() {
        return operatorId;
    }

    public void setOperatorId(Long operatorId) {
        this.operatorId = operatorId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }
}
