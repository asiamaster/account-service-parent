package com.dili.account.entity;



/**
 * 卡片聚合信息
 */
public class CardAggregationWrapper {
	/**账户id*/
	private Long accountId;
	/***/
	private String cardNo;
	/** 市场编码 */
	private Long firmId;
	/** 禁用状态（管理员使用:1-启用2-禁用） */
	//private Integer systemDisableStatus;
	/**卡账户*/
	private UserAccountDo userAccount;
	/**卡信息*/
	private UserCardDo userCard;

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getFirmId() {
		return firmId;
	}

	public void setFirmId(Long firmId) {
		this.firmId = firmId;
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

}
