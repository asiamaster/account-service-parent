package com.dili.account.entity;

/**
 * @Auther: miaoguoxin
 * @Date: 2020/11/5 14:23
 * @Description:
 */
public class CustomerCardWrapper {

    private Long customerId;

    private String cardNo;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}
