package com.dili.account.dto;

import java.util.List;

/**
 * @description： 用户信息查询参数
 *
 * @author ：WangBo
 * @time ：2020年4月26日下午4:30:03
 */
public class UserAccountCardQuery {
	/** 排除的卡类型 */
	private List<Integer> exclusionsCardCategorys;
	/** 包含的卡类型 */
	private List<Integer> cardCategorys;
	/** 多个账户ID */
	private List<Long> accountIds;
	/** 多个卡号 */
	private List<String> cardNos;
	/** 手机号 */
	private String mobile;
	/** 姓名 */
	private String name;
	/** 证件号 */
	private String credentialNo;
	/** 卡号 */
	private String cardNo;
	/** 账户ID */
	private Long accountId;
	/** 主账户ID */
	private Long parentAccountId;

	public List<Integer> getExclusionsCardCategorys() {
		return exclusionsCardCategorys;
	}

	public void setExclusionsCardCategorys(List<Integer> exclusionsCardCategorys) {
		this.exclusionsCardCategorys = exclusionsCardCategorys;
	}

	public List<Integer> getCardCategorys() {
		return cardCategorys;
	}

	public void setCardCategorys(List<Integer> cardCategorys) {
		this.cardCategorys = cardCategorys;
	}

	public List<Long> getAccountIds() {
		return accountIds;
	}

	public void setAccountIds(List<Long> accountIds) {
		this.accountIds = accountIds;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCredentialNo() {
		return credentialNo;
	}

	public void setCredentialNo(String credentialNo) {
		this.credentialNo = credentialNo;
	}

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
