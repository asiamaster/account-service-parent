package com.dili.account.dto;


import java.time.LocalDateTime;

import com.dili.account.type.AccountType;
import com.dili.account.type.CardBizType;
import com.dili.account.type.CardType;
import com.dili.account.type.CreateSource;
import com.dili.account.type.CustomerAreaType;
import com.dili.account.type.UsePermissionType;

/**
 * @description： 开卡所需要的用户信息
 *
 * @author ：WangBo
 * @time ：2020年4月23日下午5:30:18
 */
public class OpenCardDto {
	/** 用户姓名 */
	private String name;
	/** CRM系统客户ID */
	private Long custormerId;
	/** 客户类型 {@link AccountType} */
	private Integer customerType;
	/** 性别 */
	private Integer gender;
	/** 证件类型 */
	private String credentialType;
	/** 证件号 */
	private String credentialNo;
	/** 证件有效期 */
	private LocalDateTime validityDate;
	/** 认证状态（1已认证2未认证） */
	private Integer authStatus;
	/** 手机号 */
	private String mobile;
	/** 市场编码 */
	private String marketId;
	/** 固定电话 */
	private String telphone;
	/** 地址 */
	private String address;
	/** 主营业务 */
	private String businessCategory;
	/** 客户区域 {@link CustomerAreaType} */
	private Integer customerArea;
	/** 法人 */
	private String legalName;
	/** 法人证件类型 */
	private String legalCredentialType;
	/** 法人证件号 */
	private String legalNo;
	/** 资金账户ID */
	private Long fundAccountId;
	/** 账户类型(主、子/副) */
	private Integer accountType;
	/** 登录密码 */
	private String loginPwd;
	/** 交易密码 */
	private String tradePwd;
	/** 卡交易类型 {@link CardBizType} */
	private Integer bizUsageType;
	/** 开户来源 {@link CreateSource} */
	private Integer createSource;
	/** 业务权限集合 {@link UsePermissionType},以逗号分隔 */
	private String usePermission;
	/** 卡号 */
	private String cardNo;
	/** 卡类别 {@link CardCategory} */
	private Integer category;
	/** {@link CardType} */
	private Integer seinsweise;
	/** 父账号ID */
	private Long parentAccountId;
	/** 每笔提现限额-分 */
	private Long cashOutLimitsTimes;
	/** 每日提现限额-分 */
	private Long cashOutLimitsDays;
	/** 每笔付款限额-分 */
	private Long payLimitsTimes;
	/** 每日付款限额-分 */
	private Long payLimitsDays;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCustormerId() {
		return custormerId;
	}

	public void setCustormerId(Long custormerId) {
		this.custormerId = custormerId;
	}

	public Integer getCustomerType() {
		return customerType;
	}

	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getCredentialType() {
		return credentialType;
	}

	public void setCredentialType(String credentialType) {
		this.credentialType = credentialType;
	}

	public String getCredentialNo() {
		return credentialNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCredentialNo(String credentialNo) {
		this.credentialNo = credentialNo;
	}

	public LocalDateTime getValidityDate() {
		return validityDate;
	}

	public void setValidityDate(LocalDateTime validityDate) {
		this.validityDate = validityDate;
	}

	public Integer getAuthStatus() {
		return authStatus;
	}

	public void setAuthStatus(Integer authStatus) {
		this.authStatus = authStatus;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getBusinessCategory() {
		return businessCategory;
	}

	public void setBusinessCategory(String businessCategory) {
		this.businessCategory = businessCategory;
	}

	public Integer getCustomerArea() {
		return customerArea;
	}

	public void setCustomerArea(Integer customerArea) {
		this.customerArea = customerArea;
	}

	public String getLegalName() {
		return legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	public String getLegalCredentialType() {
		return legalCredentialType;
	}

	public void setLegalCredentialType(String legalCredentialType) {
		this.legalCredentialType = legalCredentialType;
	}

	public String getLegalNo() {
		return legalNo;
	}

	public void setLegalNo(String legalNo) {
		this.legalNo = legalNo;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public Integer getBizUsageType() {
		return bizUsageType;
	}

	public void setBizUsageType(Integer bizUsageType) {
		this.bizUsageType = bizUsageType;
	}

	public Integer getCreateSource() {
		return createSource;
	}

	public void setCreateSource(Integer createSource) {
		this.createSource = createSource;
	}

	public String getUsePermission() {
		return usePermission;
	}

	public void setUsePermission(String usePermission) {
		this.usePermission = usePermission;
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

	public Integer getSeinsweise() {
		return seinsweise;
	}

	public void setSeinsweise(Integer seinsweise) {
		this.seinsweise = seinsweise;
	}

	public String getMarketId() {
		return marketId;
	}

	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}

	public Long getParentAccountId() {
		return parentAccountId;
	}

	public void setParentAccountId(Long parentAccountId) {
		this.parentAccountId = parentAccountId;
	}

	public Long getFundAccountId() {
		return fundAccountId;
	}

	public void setFundAccountId(Long fundAccountId) {
		this.fundAccountId = fundAccountId;
	}

	public String getTradePwd() {
		return tradePwd;
	}

	public void setTradePwd(String tradePwd) {
		this.tradePwd = tradePwd;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public Long getCashOutLimitsTimes() {
		return cashOutLimitsTimes;
	}

	public void setCashOutLimitsTimes(Long cashOutLimitsTimes) {
		this.cashOutLimitsTimes = cashOutLimitsTimes;
	}

	public Long getCashOutLimitsDays() {
		return cashOutLimitsDays;
	}

	public void setCashOutLimitsDays(Long cashOutLimitsDays) {
		this.cashOutLimitsDays = cashOutLimitsDays;
	}

	public Long getPayLimitsTimes() {
		return payLimitsTimes;
	}

	public void setPayLimitsTimes(Long payLimitsTimes) {
		this.payLimitsTimes = payLimitsTimes;
	}

	public Long getPayLimitsDays() {
		return payLimitsDays;
	}

	public void setPayLimitsDays(Long payLimitsDays) {
		this.payLimitsDays = payLimitsDays;
	}

}
