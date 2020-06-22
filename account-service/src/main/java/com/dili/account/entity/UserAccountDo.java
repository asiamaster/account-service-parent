package com.dili.account.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户账户信息
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
	/** 资金账号 */
	private Long fundAccountId;
	/** 账号类型-买家账户/卖家账户 */
	private Integer type;
	/** 账户用途-交易账户/水电费账户 */
	private Integer usageType;
	/** 持有人姓名 */
	private String holderName;
	/** 持有人性别 */
	private Integer holderGender;
	/** 持有人手机号 */
	private String holderMobile;
	/** 持有人证件号码 */
	private String holderCertificateNumber;
	/** 持有人证件类型 */
	private String holderCertificateType;
	/** 持有人联系地址 */
	private String holderAddress;
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

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getType() {
		return type;
	}

	public void setUsageType(Integer usageType) {
		this.usageType = usageType;
	}

	public Integer getUsageType() {
		return usageType;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	public String getHolderName() {
		return holderName;
	}

	public void setHolderGender(Integer holderGender) {
		this.holderGender = holderGender;
	}

	public Integer getHolderGender() {
		return holderGender;
	}

	public void setHolderMobile(String holderMobile) {
		this.holderMobile = holderMobile;
	}

	public String getHolderMobile() {
		return holderMobile;
	}

	public void setHolderCertificateNumber(String holderCertificateNumber) {
		this.holderCertificateNumber = holderCertificateNumber;
	}

	public String getHolderCertificateNumber() {
		return holderCertificateNumber;
	}

	public void setHolderCertificateType(String holderCertificateType) {
		this.holderCertificateType = holderCertificateType;
	}

	public String getHolderCertificateType() {
		return holderCertificateType;
	}

	public void setHolderAddress(String holderAddress) {
		this.holderAddress = holderAddress;
	}

	public String getHolderAddress() {
		return holderAddress;
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

}
