package com.dili.account.dto;

import org.apache.commons.lang3.StringUtils;

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
    /**是否排除退还状态*/
    private Integer excludeReturn;
    /**是否排除禁用状态*/
    private Integer excludeDisabled;

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

    public Integer getExcludeReturn() {
        return excludeReturn;
    }

    public void setExcludeReturn(Integer excludeReturn) {
        this.excludeReturn = excludeReturn;
    }

    public Integer getExcludeDisabled() {
        return excludeDisabled;
    }

    public void setExcludeDisabled(Integer excludeDisabled) {
        this.excludeDisabled = excludeDisabled;
    }

    public UserAccountCardQuery setDefSort(String defSort) {
        if (StringUtils.isBlank(super.getSort())) {
            super.setSort(defSort);
        }
        return this;
    }

    public UserAccountCardQuery setDefOrderByColumn(String defColumn) {
        if (StringUtils.isBlank(super.getOrder())) {
            super.setOrder(defColumn);
        }
        return this;
    }

    public UserAccountCardQuery setDefExcludeReturn(Integer defExcludeReturn) {
        if (this.getExcludeReturn() == null) {
            this.setExcludeReturn(defExcludeReturn);
        }
        return this;
    }

    public UserAccountCardQuery setDefExcludeDisabled(Integer defExcludeDisabled) {
        if (this.getExcludeDisabled() == null) {
            this.setExcludeDisabled(defExcludeDisabled);
        }
        return this;
    }

    @Override
    public String toString() {
        return "UserAccountCardQuery{" +
                "customerIds=" + customerIds +
                ", accountIds=" + accountIds +
                ", cardNos=" + cardNos +
                ", parentAccountId=" + parentAccountId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", cardType=" + cardType +
                ", cardState=" + cardState +
                ", excludeReturn=" + excludeReturn +
                ", excludeDisabled=" + excludeDisabled +
                '}';
    }
}

