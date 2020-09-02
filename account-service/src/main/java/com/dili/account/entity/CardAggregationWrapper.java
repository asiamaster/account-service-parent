package com.dili.account.entity;



/**
 * 卡片聚合信息
 */
public class CardAggregationWrapper {
	/**账户主键id*/
	private Long accountPkId;
	/**卡主键id*/
	private Long cardPkId;
	/** 市场编码 */
	private Long firmId;
	/** 禁用状态（管理员使用:1-启用2-禁用） */
	//private Integer systemDisableStatus;
	/**卡账户*/
	private UserAccountDo userAccount;
	/**卡信息*/
	private UserCardDo userCard;

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
