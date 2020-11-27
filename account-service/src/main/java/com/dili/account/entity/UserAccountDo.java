package com.dili.account.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户账户信息
 *
 * @author bob
 */
public class UserAccountDo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**  */
	private Long id;
	/** 业务主键，其它业务外键都引用该键 */
	private Long accountId;
	/** 父账号ID */
	private Long parentAccountId;
	/** 客户ID */
	private Long customerId;
	/** 客户姓名（冗余customer） */
	private String customerName;
	/** 客户编号（冗余customer） */
	private String customerCode;
	/** 客户市场类型(冗余customer_market) */
	private String customerMarketType;
	/** 证件类型（冗余customer） */
	private String customerCertificateType;
	/** 证件号（冗余customer） */
	private String customerCertificateNumber;
	/** 电话号码（冗余customer） */
	private String customerContactsPhone;
	/** 持卡人姓名 */
	private String holdName;
	/** 持卡人证件号 */
	private String holdCertificateNumber;
	/** 持卡人联系电话 */
	private String holdContactsPhone;
	/** 资金账号 */
	private Long fundAccountId;
	/** 实体卡是否存在 */
	private Integer cardExist;
	/** 账号类型-买/经营户/其它 */
	private String types;
	/** 账户用途-交易账户/水电费账户 ,多个以逗号分隔 */
	private String usageType;
	/** 使用权限(充值、提现、交费等),多个以逗号分隔 */
	private String permissions;
	/** 登陆密码 */
	private String loginPwd;
	/** 强制修改密码 */
	private Integer pwdChanged;
	/** 最近登陆时间 */
	private LocalDateTime loginTime;
	/** 安全密钥 */
	private String secretKey;
	/** 账户状态-正常/锁定/禁用/注销 */
	private Integer state;
	/** 注册来源-柜台 APP */
	private Integer source;
	/** 数据版本号 */
	private Integer version;
	/** 禁用状态 {@link com.dili.account.type.DisableState} */
	private Integer disabledState;
	/** 操作人员 */
	private Long creatorId;
	/** 员工名称-保留字段 */
	private String creator;
	/** 商户ID */
	private Long firmId;
	/** 商户名称 */
	private String firmName;
	/** 创建时间 */
	private LocalDateTime createTime;
	/** 修改时间 */
	private LocalDateTime modifyTime;

	/**
	 * UserAccountEntity constructor
	 */
	public UserAccountDo() {
		super();
	}

	/**
	 * setter for
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * getter for
	 */
	public Long getId() {
		return id;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setParentAccountId(Long parentAccountId) {
		this.parentAccountId = parentAccountId;
	}

	public Long getParentAccountId() {
		return parentAccountId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public void setFundAccountId(Long fundAccountId) {
		this.fundAccountId = fundAccountId;
	}

	public Long getFundAccountId() {
		return fundAccountId;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public void setUsageType(String usageType) {
		this.usageType = usageType;
	}

	public String getUsageType() {
		return usageType;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setPwdChanged(Integer pwdChanged) {
		this.pwdChanged = pwdChanged;
	}

	public Integer getPwdChanged() {
		return pwdChanged;
	}

	public void setLoginTime(LocalDateTime loginTime) {
		this.loginTime = loginTime;
	}

	public LocalDateTime getLoginTime() {
		return loginTime;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getState() {
		return state;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Integer getSource() {
		return source;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getVersion() {
		return version;
	}

	public void setFirmId(Long firmId) {
		this.firmId = firmId;
	}

	public Long getFirmId() {
		return firmId;
	}

	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

	public String getFirmName() {
		return firmName;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setModifyTime(LocalDateTime modifyTime) {
		this.modifyTime = modifyTime;
	}

	public LocalDateTime getModifyTime() {
		return modifyTime;
	}

	public Integer getCardExist() {
		return cardExist;
	}

	public void setCardExist(Integer cardExist) {
		this.cardExist = cardExist;
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

	public Integer getDisabledState() {
		return disabledState;
	}

	public void setDisabledState(Integer disabledState) {
		this.disabledState = disabledState;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getCustomerMarketType() {
		return customerMarketType;
	}

	public void setCustomerMarketType(String customerMarketType) {
		this.customerMarketType = customerMarketType;
	}

	public String getCustomerCertificateType() {
		return customerCertificateType;
	}

	public void setCustomerCertificateType(String customerCertificateType) {
		this.customerCertificateType = customerCertificateType;
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

	public String getHoldCertificateNumber() {
		return holdCertificateNumber;
	}

	public void setHoldCertificateNumber(String holdCertificateNumber) {
		this.holdCertificateNumber = holdCertificateNumber;
	}

	public String getHoldContactsPhone() {
		return holdContactsPhone;
	}

	public void setHoldContactsPhone(String holdContactsPhone) {
		this.holdContactsPhone = holdContactsPhone;
	}

}
