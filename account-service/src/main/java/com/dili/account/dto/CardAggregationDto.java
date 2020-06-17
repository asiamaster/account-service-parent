package com.dili.account.dto;


/**
 * 卡片聚合信息
 */
public class CardAggregationDto {
	/** 市场编码 */
	private String firmId;
	/** 禁用状态（管理员使用:1-启用2-禁用） */
	private Integer systemDisableStatus;
	/**卡账户*/
	private UserAccountResponseDto userAccount;
	/**卡信息*/
	private UserCardResponseDto userCard;
	/**客户信息*/
	private CustomerResponseDto customerInfo;

	public String getFirmId() {
		return firmId;
	}

	public void setFirmId(String firmId) {
		this.firmId = firmId;
	}

	public Integer getSystemDisableStatus() {
		return systemDisableStatus;
	}

	public void setSystemDisableStatus(Integer systemDisableStatus) {
		this.systemDisableStatus = systemDisableStatus;
	}

	public UserAccountResponseDto getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccountResponseDto userAccount) {
		this.userAccount = userAccount;
	}

	public UserCardResponseDto getUserCard() {
		return userCard;
	}

	public void setUserCard(UserCardResponseDto userCard) {
		this.userCard = userCard;
	}

	public CustomerResponseDto getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerResponseDto customerInfo) {
		this.customerInfo = customerInfo;
	}
}
