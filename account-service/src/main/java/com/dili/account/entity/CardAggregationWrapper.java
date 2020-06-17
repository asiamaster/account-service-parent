package com.dili.account.entity;


import com.dili.account.dto.CustomerResponseDto;
import com.dili.account.dto.UserAccountResponseDto;
import com.dili.account.dto.UserCardResponseDto;

/**
 * 卡片聚合信息
 */
public class CardAggregationWrapper {
	/** 市场编码 */
	private String firmId;
	/** 禁用状态（管理员使用:1-启用2-禁用） */
	private Integer systemDisableStatus;
	/**卡账户*/
	private UserAccountDo userAccount;
	/**卡信息*/
	private UserCardDo userCard;
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

	public UserAccountDo getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccountDo userAccount) {
		this.userAccount = userAccount;
	}

	public UserCardDo getUserCard() {
		return userCard;
	}

	public void setUserCard(UserCardDo userCard) {
		this.userCard = userCard;
	}

	public CustomerResponseDto getCustomerInfo() {
		return customerInfo;
	}

	public void setCustomerInfo(CustomerResponseDto customerInfo) {
		this.customerInfo = customerInfo;
	}
}
