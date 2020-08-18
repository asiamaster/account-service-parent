package com.dili.account.dto;

import com.dili.account.common.annotation.AtLeastFieldNotNull;
import com.dili.account.validator.AccountValidator;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * 单个查询账户dto
 * @Auther: miaoguoxin
 * @Date: 2020/8/10 13:45
 */
@AtLeastFieldNotNull(includeFieldNames = {
        "accountId", "cardNo"
}, groups = AccountValidator.SingleQuery.class, message = "至少需要一个查询条件")
public class UserAccountSingleQueryDto implements Serializable {
    /** */
	private static final long serialVersionUID = 3514423425377026650L;
	/**账户ID */
    @Min(value = 1, message = "非法的账户id", groups = AccountValidator.SingleQuery.class)
    private Long accountId;
    /** 卡号 */
    private String cardNo;

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
