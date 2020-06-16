package com.dili.account.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 用户卡片信息（包括电子卡）
 * @author bob
 */
public class UserCardEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Long id; 
	/** 卡片硬件标识 */
	private String deviceId; 
	/** 用户账号 */
	private Long accountId; 
	/** 卡号 */
	private String cardNo; 
	/** 卡类别-园区卡 银行卡 */
	private Integer category; 
	/** 类型-主/副/临时 */
	private Integer type; 
	/** 用途-买家/卖家卡 */
	private Integer usageType; 
	/** 验证码 */
	private String verifyCode; 
	/** 卡片押金-分 */
	private Integer cashPledge; 
	/** 是否最近卡片-换卡时使用 */
	private Integer last; 
	/** 卡片状态-正常/挂失 */
	private Integer state; 
	/** 数据版本号 */
	private Integer version; 
	/** 操作人员 */
	private Long creatorId; 
	/** 员工名称-保留字段 */
	private String creator; 
	/** 商户ID */
	private String firmId; 
	/** 商户名称 */
	private String firmName; 
	/** 创建时间 */
	private LocalDateTime createTime; 
	/** 修改时间 */
	private LocalDateTime modifyTime; 
    /**
     * UserCardEntity constructor
     */
	public UserCardEntity() {
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

    /**
     * setter for 卡片硬件标识
     */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

    /**
     * getter for 卡片硬件标识
     */
	public String getDeviceId() {
		return deviceId;
	}

    /**
     * setter for 用户账号
     */
	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

    /**
     * getter for 用户账号
     */
	public Long getAccountId() {
		return accountId;
	}

    /**
     * setter for 卡号
     */
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

    /**
     * getter for 卡号
     */
	public String getCardNo() {
		return cardNo;
	}

    /**
     * setter for 卡类别-园区卡 银行卡
     */
	public void setCategory(Integer category) {
		this.category = category;
	}

    /**
     * getter for 卡类别-园区卡 银行卡
     */
	public Integer getCategory() {
		return category;
	}

    /**
     * setter for 类型-主/副/临时
     */
	public void setType(Integer type) {
		this.type = type;
	}

    /**
     * getter for 类型-主/副/临时
     */
	public Integer getType() {
		return type;
	}

    /**
     * setter for 用途-买家/卖家卡
     */
	public void setUsageType(Integer usageType) {
		this.usageType = usageType;
	}

    /**
     * getter for 用途-买家/卖家卡
     */
	public Integer getUsageType() {
		return usageType;
	}

    /**
     * setter for 验证码
     */
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

    /**
     * getter for 验证码
     */
	public String getVerifyCode() {
		return verifyCode;
	}

    /**
     * setter for 卡片押金-分
     */
	public void setCashPledge(Integer cashPledge) {
		this.cashPledge = cashPledge;
	}

    /**
     * getter for 卡片押金-分
     */
	public Integer getCashPledge() {
		return cashPledge;
	}

    /**
     * setter for 是否最近卡片-换卡时使用
     */
	public void setLast(Integer last) {
		this.last = last;
	}

    /**
     * getter for 是否最近卡片-换卡时使用
     */
	public Integer getLast() {
		return last;
	}

    /**
     * setter for 卡片状态-正常/挂失
     */
	public void setState(Integer state) {
		this.state = state;
	}

    /**
     * getter for 卡片状态-正常/挂失
     */
	public Integer getState() {
		return state;
	}

    /**
     * setter for 数据版本号
     */
	public void setVersion(Integer version) {
		this.version = version;
	}

    /**
     * getter for 数据版本号
     */
	public Integer getVersion() {
		return version;
	}

    /**
     * setter for 操作人员
     */
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

    /**
     * getter for 操作人员
     */
	public Long getCreatorId() {
		return creatorId;
	}

    /**
     * setter for 员工名称-保留字段
     */
	public void setCreator(String creator) {
		this.creator = creator;
	}

    /**
     * getter for 员工名称-保留字段
     */
	public String getCreator() {
		return creator;
	}

    /**
     * setter for 商户ID
     */
	public void setFirmId(String firmId) {
		this.firmId = firmId;
	}

    /**
     * getter for 商户ID
     */
	public String getFirmId() {
		return firmId;
	}

    /**
     * setter for 商户名称
     */
	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}

    /**
     * getter for 商户名称
     */
	public String getFirmName() {
		return firmName;
	}

    /**
     * setter for 创建时间
     */
	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

    /**
     * getter for 创建时间
     */
	public LocalDateTime getCreateTime() {
		return createTime;
	}

    /**
     * setter for 修改时间
     */
	public void setModifyTime(LocalDateTime modifyTime) {
		this.modifyTime = modifyTime;
	}

    /**
     * getter for 修改时间
     */
	public LocalDateTime getModifyTime() {
		return modifyTime;
	}

    /**
     * UserCardEntity.toString()
     */
    @Override
    public String toString() {
        return "UserCardEntity{" +
               "id='" + id + '\'' +
               ", deviceId='" + deviceId + '\'' +
               ", accountId='" + accountId + '\'' +
               ", cardNo='" + cardNo + '\'' +
               ", category='" + category + '\'' +
               ", type='" + type + '\'' +
               ", usageType='" + usageType + '\'' +
               ", verifyCode='" + verifyCode + '\'' +
               ", cashPledge='" + cashPledge + '\'' +
               ", last='" + last + '\'' +
               ", state='" + state + '\'' +
               ", version='" + version + '\'' +
               ", creatorId='" + creatorId + '\'' +
               ", creator='" + creator + '\'' +
               ", firmId='" + firmId + '\'' +
               ", firmName='" + firmName + '\'' +
               ", createTime='" + createTime + '\'' +
               ", modifyTime='" + modifyTime + '\'' +
               '}';
    }

}
