package com.dili.account.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 卡账户额度
 * @author bob
 */
public class UserAmountLimitDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Long id; 
	/** 用户账号ID */
	private Long accountId; 
	/** 卡类别 */
	private Integer cardCategory; 
	/** 每笔提现限额-分 */
	private Long cashOutLimitsTimes; 
	/** 每日提现限额-分 */
	private Long cashOutLimitsDays; 
	/** 每笔付款限额-分 */
	private Long payLimitsTimes; 
	/** 每日付款限额-分 */
	private Long payLimitsDays; 
	/** 创建时间 */
	private LocalDateTime createdTime; 
	/** 修改时间 */
	private LocalDateTime modifiedTime; 
    /**
     * UserAmountLimitEntity constructor
     */
	public UserAmountLimitDto() {
		super();
	}

    /**
     * setter for 
     */
	public void setId(Long id) {
		this.id = id;
	}

    /**
     * getter for 
     */
	public Long getId() {
		return id;
	}

    /**
     * setter for 用户账号ID
     */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

    /**
     * getter for 用户账号ID
     */
	public Long getAccountId() {
		return accountId;
	}

    /**
     * setter for 卡类别
     */
	public void setCardCategory(Integer cardCategory) {
		this.cardCategory = cardCategory;
	}

    /**
     * getter for 卡类别
     */
	public Integer getCardCategory() {
		return cardCategory;
	}

    /**
     * setter for 每笔提现限额-分
     */
	public void setCashOutLimitsTimes(Long cashOutLimitsTimes) {
		this.cashOutLimitsTimes = cashOutLimitsTimes;
	}

    /**
     * getter for 每笔提现限额-分
     */
	public Long getCashOutLimitsTimes() {
		return cashOutLimitsTimes;
	}

    /**
     * setter for 每日提现限额-分
     */
	public void setCashOutLimitsDays(Long cashOutLimitsDays) {
		this.cashOutLimitsDays = cashOutLimitsDays;
	}

    /**
     * getter for 每日提现限额-分
     */
	public Long getCashOutLimitsDays() {
		return cashOutLimitsDays;
	}

    /**
     * setter for 每笔付款限额-分
     */
	public void setPayLimitsTimes(Long payLimitsTimes) {
		this.payLimitsTimes = payLimitsTimes;
	}

    /**
     * getter for 每笔付款限额-分
     */
	public Long getPayLimitsTimes() {
		return payLimitsTimes;
	}

    /**
     * setter for 每日付款限额-分
     */
	public void setPayLimitsDays(Long payLimitsDays) {
		this.payLimitsDays = payLimitsDays;
	}

    /**
     * getter for 每日付款限额-分
     */
	public Long getPayLimitsDays() {
		return payLimitsDays;
	}

    /**
     * setter for 创建时间
     */
	public void setCreatedTime(LocalDateTime createdTime) {
		this.createdTime = createdTime;
	}

    /**
     * getter for 创建时间
     */
	public LocalDateTime getCreatedTime() {
		return createdTime;
	}

    /**
     * setter for 修改时间
     */
	public void setModifiedTime(LocalDateTime modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

    /**
     * getter for 修改时间
     */
	public LocalDateTime getModifiedTime() {
		return modifiedTime;
	}

    /**
     * UserAmountLimitEntity.toString()
     */
    @Override
    public String toString() {
        return "UserAmountLimitEntity{" +
               "id='" + id + '\'' +
               ", accountId='" + accountId + '\'' +
               ", cardCategory='" + cardCategory + '\'' +
               ", cashOutLimitsTimes='" + cashOutLimitsTimes + '\'' +
               ", cashOutLimitsDays='" + cashOutLimitsDays + '\'' +
               ", payLimitsTimes='" + payLimitsTimes + '\'' +
               ", payLimitsDays='" + payLimitsDays + '\'' +
               ", createdTime='" + createdTime + '\'' +
               ", modifiedTime='" + modifiedTime + '\'' +
               '}';
    }

}
