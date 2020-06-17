package com.dili.account.dto;

import com.dili.account.entity.UserAccountDo;

/**
 * 用户账户DTO
 */
public class UserAccountDto extends UserAccountDo {
    /**
     * 卡号
     */
    private String cardNo;

    /**
     * 卡类型
     */
    private Integer cardType;

    /**
     * 卡状态
     */
    private Integer cardState;
    /** 卡数据版本号*/
    private Integer cardVersion;

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
    public Integer getCardType() {
        return cardType;
    }

    /**
     *
     * @param cardType
     */
    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    /**
     *
     * @return
     */
    public Integer getCardState() {
        return cardState;
    }

    /**
     *
     * @param cardState
     */
    public void setCardState(Integer cardState) {
        this.cardState = cardState;
    }

    public Integer getCardVersion() {
        return cardVersion;
    }

    public void setCardVersion(Integer cardVersion) {
        this.cardVersion = cardVersion;
    }
}
