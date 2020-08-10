package com.dili.account.dto;

import com.dili.account.common.annotation.AtLeastFieldNotNull;
import com.dili.account.validator.AccountValidator;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * 单个查询账户dto
 * @Auther: miaoguoxin
 * @Date: 2020/8/10 13:45
 */
@AtLeastFieldNotNull(includeFieldNames = {
        "customerIds", "customerCertificateNumber",
        "accountIds", "cardNos"
}, groups = AccountValidator.SingleQuery.class, message = "至少需要一个查询条件")
public class UserAccountSingleQueryDto implements Serializable {
    /**客户id*/
    @Size(max = 1, message = "只允许一个客户id", groups = AccountValidator.SingleQuery.class)
    private List<Long> customerIds;
    /**客户证件号*/
    private String customerCertificateNumber;
    /**账户ID */
    @Size(max = 1, message = "只允许一个账户id", groups = AccountValidator.SingleQuery.class)
    private List<Long> accountIds;
    /** 卡号 */
    @Size(max = 1, message = "只允许一个卡号", groups = AccountValidator.SingleQuery.class)
    private List<String> cardNos;

    public List<Long> getCustomerIds() {
        return customerIds;
    }

    public void setCustomerIds(List<Long> customerIds) {
        this.customerIds = customerIds;
    }

    public String getCustomerCertificateNumber() {
        return customerCertificateNumber;
    }

    public void setCustomerCertificateNumber(String customerCertificateNumber) {
        this.customerCertificateNumber = customerCertificateNumber;
    }

    public List<Long> getAccountIds() {
        return accountIds;
    }

    public void setAccountIds(List<Long> accountIds) {
        this.accountIds = accountIds;
    }

    public List<String> getCardNos() {
        return cardNos;
    }

    public void setCardNos(List<String> cardNos) {
        this.cardNos = cardNos;
    }
}
