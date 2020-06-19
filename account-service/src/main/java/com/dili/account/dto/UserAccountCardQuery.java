package com.dili.account.dto;

import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @description： 用户信息查询参数
 *
 * @author ：WangBo
 * @time ：2020年4月26日下午4:30:03
 */
public class UserAccountCardQuery extends BaseDto{
	/**客户id*/
	private List<Long> custormerIds;
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
	/**卡类别 {@link com.dili.account.type.CardCategory}*/
	private Integer cardCategory;
	/**卡状态 {@link com.dili.account.type.CardStatus}*/
	private Integer cardState;

	public Integer getCardCategory() {
		return cardCategory;
	}

	public void setCardCategory(Integer cardCategory) {
		this.cardCategory = cardCategory;
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

	public List<Long> getCustormerIds() {
		return custormerIds;
	}

	public void setCustormerIds(List<Long> custormerIds) {
		this.custormerIds = custormerIds;
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

}
