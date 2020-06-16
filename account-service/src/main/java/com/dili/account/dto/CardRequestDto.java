package com.dili.account.dto;

/**
 *
 * 卡请求相关
 *
 */
public class CardRequestDto {
	/** 卡号 */
	private String cardNo;
	/** 新卡号 */
	private String newCardNo;
	/** 账户ID */
	private Long accountId;
	/** 登录密码 */
	private String loginPwd;

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

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getNewCardNo() {
		return newCardNo;
	}

	public void setNewCardNo(String newCardNo) {
		this.newCardNo = newCardNo;
	}
}
