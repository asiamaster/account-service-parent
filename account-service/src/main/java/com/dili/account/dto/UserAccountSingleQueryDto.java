package com.dili.account.dto;

import com.dili.account.common.annotation.AtLeastFieldNotNull;
import com.dili.account.validator.AccountValidator;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * 单个查询账户dto，
 * 从列表上查询详情的时候同事需要accountPkId和cardPkId才能查询出唯一数据
 * @Auther: miaoguoxin
 * @Date: 2020/8/10 13:45
 */
@AtLeastFieldNotNull(includeFieldNames = {
        "accountPkId", "cardPkId", "accountId", "cardNo"
}, groups = AccountValidator.SingleQuery.class, message = "至少需要一个查询条件")
public class UserAccountSingleQueryDto implements Serializable {
    /** */
    private static final long serialVersionUID = 3514423425377026650L;
    /**account主键id*/
    @Min(value = 1, message = "非法的accountPkId", groups = AccountValidator.SingleQuery.class)
    private Long accountPkId;
    /**card主键id*/
    @Min(value = 1, message = "非法的cardPkId", groups = AccountValidator.SingleQuery.class)
    private Long cardPkId;
    /**账户ID */
    @Min(value = 1, message = "非法的账户id", groups = AccountValidator.SingleQuery.class)
    private Long accountId;
    /** 卡号 */
    private String cardNo;

    public Long getAccountPkId() {
        return accountPkId;
    }

    public void setAccountPkId(Long accountPkId) {
        this.accountPkId = accountPkId;
    }

    public Long getCardPkId() {
        return cardPkId;
    }

    public void setCardPkId(Long cardPkId) {
        this.cardPkId = cardPkId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }
}
