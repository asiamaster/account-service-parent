package com.dili.account.dto;

import java.util.List;


public class UserAccountCardDto {
	private Long userInfoId;
	/** 用户姓名 */
	private String name;
	/** 客户类型（个人，对公） */
	private Integer custormerType;
	/** 证件号 */
	private String credentialNo;
	/** 手机号 */
	private String mobile;
	/** 账号ID-具有生成规则 */
	private Long accountId;
	/** 市场编码 */
	private String marketId;
	/** 使用权限(充值、提现、交费等) */
	private String usePermission;
	private List<String> permissionList;

	private String loginPwd;
	/** 资金账号ID */
	private Long fundAccountId;
	/** 卡交易类型: 1-买家 2-卖家 */
	private Integer bizUsageType;
	/** 卡ID */
	private Long cardId;
	/** 卡号 */
	private String cardNo;
	/** 卡类别-主/副/临时/联营 */
	private Integer category;
	/** 禁用状态（管理员使用:1-启用2-禁用） */
	private Integer systemDisableStatus;
	/** 卡片状态 */
	private Integer status;
	/** 父卡账号 */
	private Long parentAccountId;
	/** 1-未认证 2已认证 */
	private Integer authStatus;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCustormerType() {
		return custormerType;
	}

	public void setCustormerType(Integer custormerType) {
		this.custormerType = custormerType;
	}

	public String getCredentialNo() {
		return credentialNo;
	}

	public void setCredentialNo(String credentialNo) {
		this.credentialNo = credentialNo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Long getUserInfoId() {
		return userInfoId;
	}

	public void setUserInfoId(Long userInfoId) {
		this.userInfoId = userInfoId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getCardId() {
		return cardId;
	}

	public void setCardId(Long cardId) {
		this.cardId = cardId;
	}

	public List<String> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<String> permissionList) {
		this.permissionList = permissionList;
	}

	public Long getParentAccountId() {
		return parentAccountId;
	}

	public void setParentAccountId(Long parentAccountId) {
		this.parentAccountId = parentAccountId;
	}

	public Integer getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(Integer authStatus) {
		this.authStatus = authStatus;
	}

	public Long getFundAccountId() {
		return fundAccountId;
	}

	public void setFundAccountId(Long fundAccountId) {
		this.fundAccountId = fundAccountId;
	}

	public Integer getBizUsageType() {
		return bizUsageType;
	}

	public void setBizUsageType(Integer bizUsageType) {
		this.bizUsageType = bizUsageType;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSystemDisableStatus() {
		return systemDisableStatus;
	}

	public void setSystemDisableStatus(Integer systemDisableStatus) {
		this.systemDisableStatus = systemDisableStatus;
	}

	public String getMarketId() {
		return marketId;
	}

	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

}
