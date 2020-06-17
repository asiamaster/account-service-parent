package com.dili.account.dto;

import com.dili.account.common.PageDto;

/**
 * 用户账户查询dto
 */
public class UserAccountQuery extends PageDto {

    /**
     * 卡号
     */
    private String cardNo;

    /**
     * 账户ID
     */
    private Long accountId;

    /**
     *
     * @return
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     *
     * @param cardNo
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    /**
     *
     * @return
     */
    public Long getAccountId() {
        return accountId;
    }

    /**
     *
     * @param accountId
     */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
}
