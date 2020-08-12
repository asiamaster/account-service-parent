package com.dili.account.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @description： 用户信息查询参数
 *
 * @author ：WangBo
 * @time ：2020年4月26日下午4:30:03
 */
public class UserAccountCardQuery extends BaseDto {
    /**客户id*/
    private List<Long> customerIds;
    /**客户名称*/
    private String customerName;
    /**客户证件号*/
    private String customerCertificateNumber;
    /**客户市场类型 {@link com.dili.account.type.CustomerType}*/
    private String customerMarketType;
    /** 多个账户ID */
    private List<Long> accountIds;
    /** 多个卡号 */
    private List<String> cardNos;
    /** 主账户ID */
    private Long parentAccountId;
    /**结束时间*/
    private LocalDateTime startDate;
    /**开始时间*/
    private LocalDateTime endDate;
    /**卡类别 {@link com.dili.account.type.CardType}*/
    private Integer cardType;
    /**卡状态 {@link com.dili.account.type.CardStatus}*/
    private Integer cardState;
    /**关键词，用于模糊查询等*/
    private String keyword;
    /**是否要排除异常状态的账户 ex：卡退还、账户被禁用*/
    private Integer excludeUnusualState;
    /**标记是否是最新的卡*/
    @JSONField(deserialize = false)
    private Integer last;

    public String getCustomerMarketType() {
        return customerMarketType;
    }

    public void setCustomerMarketType(String customerMarketType) {
        this.customerMarketType = customerMarketType;
    }

    public Integer getLast() {
        return last;
    }

    public void setLast(Integer last) {
        this.last = last;
    }

    public String getCustomerCertificateNumber() {
        return customerCertificateNumber;
    }

    public void setCustomerCertificateNumber(String customerCertificateNumber) {
        this.customerCertificateNumber = customerCertificateNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public Integer getCardState() {
        return cardState;
    }

    public void setCardState(Integer cardState) {
        this.cardState = cardState;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public List<Long> getCustomerIds() {
        return customerIds;
    }

    public void setCustomerIds(List<Long> customerIds) {
        this.customerIds = customerIds;
    }

    public List<Long> getAccountIds() {
        return accountIds;
    }

    public void setAccountIds(List<Long> accountIds) {
        this.accountIds = accountIds;
    }

    public Long getParentAccountId() {
        return parentAccountId;
    }

    public void setParentAccountId(Long parentAccountId) {
        this.parentAccountId = parentAccountId;
    }

    public List<String> getCardNos() {
        return cardNos;
    }

    public void setCardNos(List<String> cardNos) {
        this.cardNos = cardNos;
    }

    public Integer getExcludeUnusualState() {
        return excludeUnusualState;
    }

    public void setExcludeUnusualState(Integer excludeUnusualState) {
        this.excludeUnusualState = excludeUnusualState;
    }


    public UserAccountCardQuery setDefExcludeUnusualState(Integer flag) {
        if (this.getExcludeUnusualState() == null) {
            this.setExcludeUnusualState(flag);
        }
        return this;
    }

    @Override
    public String toString() {
        return "UserAccountCardQuery{" +
                "customerIds=" + customerIds +
                ", customerName='" + customerName + '\'' +
                ", customerCertificateNumber='" + customerCertificateNumber + '\'' +
                ", accountIds=" + accountIds +
                ", cardNos=" + cardNos +
                ", parentAccountId=" + parentAccountId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", cardType=" + cardType +
                ", cardState=" + cardState +
                ", keyword='" + keyword + '\'' +
                ", excludeUnusualState=" + excludeUnusualState +
                '}';
    }

}

