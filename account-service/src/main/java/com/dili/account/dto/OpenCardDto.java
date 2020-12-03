package com.dili.account.dto;

import com.dili.account.type.CardType;
import com.dili.account.type.CreateSource;
import com.dili.account.type.UsePermissionType;

/**
 * @description： 开卡所需要的用户信息
 *
 * @author ：WangBo
 * @time ：2020年6月23日下午5:30:18
 */
public class OpenCardDto {
	/** 用户姓名 */
	private String customerName;
	/** CRM系统客户ID */
	private Long customerId;
	/** 客户类型 */
	private String customerType;
	/** 客户编号 */
	private String customerCode;
	/** 个人、对公 */
	private String customerOrganizationType;
	/** 证件类型 */
	private String customerCredentialType;
	/** 证件号 */
	private String customerCertificateNumber;
	/** 客户联系电话 */
	private String customerContactsPhone;
	/** 持卡人 */
	private String holdName;
	/** 资金账户ID */
	private Long fundAccountId;
	/** 账户类型(主、子/副) */
	private Integer accountType;
	/** 账户类型 */
	private Integer type;
	/** 登录密码 */
	private String loginPwd;
	/** 交易密码 */
	private String tradePwd;
	/** 卡交易类型 {@link com.dili.account.type.AccountUsageType} */
	private Integer usageType;
	/** 开户来源 {@link CreateSource} */
	private Integer createSource;
	/** 业务权限集合 {@link UsePermissionType},以逗号分隔 */
	private String usePermission;
	/** 卡号 */
	private String cardNo;

	/** 操作人员 */
	private Long creatorId;
	/** 员工名称-保留字段 */
	private String creator;
	/** 卡类型 {@link CardType} */
	private Integer cardType;
	/** 父账号ID */
	private Long parentAccountId;
	/** 商户ID */
	private Long firmId;
	/** 商户名称 */
	private String firmName;

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public Integer getAccountType() {
		return accountType;
	}

	public void setAccountType(Integer accountType) {
		this.accountType = accountType;
	}

	public Integer getUsageType() {
		return usageType;
	}

	public void setUsageType(Integer usageType) {
		this.usageType = usageType;
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

	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
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

	public Long getFirmId() {
		return firmId;
	}

	public void setFirmId(Long firmId) {
		this.firmId = firmId;
	}

	public String getFirmName() {
		return firmName;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getCustomerOrganizationType() {
		return customerOrganizationType;
	}

	public void setCustomerOrganizationType(String customerOrganizationType) {
		this.customerOrganizationType = customerOrganizationType;
	}

	public String getCustomerCredentialType() {
		return customerCredentialType;
	}

	public void setCustomerCredentialType(String customerCredentialType) {
		this.customerCredentialType = customerCredentialType;
	}

	public String getCustomerCertificateNumber() {
		return customerCertificateNumber;
	}

	public void setCustomerCertificateNumber(String customerCertificateNumber) {
		this.customerCertificateNumber = customerCertificateNumber;
	}

	public String getCustomerContactsPhone() {
		return customerContactsPhone;
	}

	public void setCustomerContactsPhone(String customerContactsPhone) {
		this.customerContactsPhone = customerContactsPhone;
	}

	public String getHoldName() {
		return holdName;
	}

	public void setHoldName(String holdName) {
		this.holdName = holdName;
	}

}
