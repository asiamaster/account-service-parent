package com.dili.account.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 卡片仓库，所有新开卡必须来至该表
 * 
 * @author bob
 */
public class CardStorageDo implements Serializable {

	private static final long serialVersionUID = 1L;

	/**  */
	private Long id;
	/** 卡片硬件标识 */
	private String deviceId;
	/** 卡号 */
	private String cardNo;
	/** 卡片类型码 */
	private Integer type;
	/** 卡面（与客户类型值相同,司机园内买园外买卖） */
	private String cardFace;
	/** 入库批次ID */
	private Long storageInId;
	/** 验证码 */
	private String verifyCode;
	/** 制卡程序版本号 */
	private Integer makerVersion;
	/** 卡片状态-激活,在用,作废 */
	private Integer state;
	/** 操作员 */
	private Long creatorId;
	/** 操作员名称 */
	private String creator;
	/** 备注 */
	private String notes;
	/** 商户ID */
	private Long firmId;
	/** 商户名称 */
	private String firmName;
	/** 创建时间 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;
	/** 修改时间 */
	@JSONField(format = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime modifyTime;

	/**
	 * CardStorageEntity constructor
	 */
	public CardStorageDo() {
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
	 * setter for 卡片类型码
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * getter for 卡片类型码
	 */
	public Integer getType() {
		return type;
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
	 * setter for 制卡程序版本号
	 */
	public void setMakerVersion(Integer makerVersion) {
		this.makerVersion = makerVersion;
	}

	/**
	 * getter for 制卡程序版本号
	 */
	public Integer getMakerVersion() {
		return makerVersion;
	}

	/**
	 * setter for 卡片状态-激活,在用,作废
	 */
	public void setState(Integer state) {
		this.state = state;
	}

	/**
	 * getter for 卡片状态-激活,在用,作废
	 */
	public Integer getState() {
		return state;
	}

	/**
	 * setter for 操作员
	 */
	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	/**
	 * getter for 操作员
	 */
	public Long getCreatorId() {
		return creatorId;
	}

	/**
	 * setter for 操作员名称
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * getter for 操作员名称
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * setter for 备注
	 */
	public void setNotes(String notes) {
		this.notes = notes;
	}

	/**
	 * getter for 备注
	 */
	public String getNotes() {
		return notes;
	}

	/**
	 * setter for 商户ID
	 */
	public void setFirmId(Long firmId) {
		this.firmId = firmId;
	}

	/**
	 * getter for 商户ID
	 */
	public Long getFirmId() {
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

	public String getCardFace() {
		return cardFace;
	}

	public void setCardFace(String cardFace) {
		this.cardFace = cardFace;
	}

	public Long getStorageInId() {
		return storageInId;
	}

	public void setStorageInId(Long storageInId) {
		this.storageInId = storageInId;
	}

	/**
	 * CardStorageEntity.toString()
	 */
	@Override
	public String toString() {
		return "CardStorageEntity{" + "id='" + id + '\'' + ", deviceId='" + deviceId + '\'' + ", cardNo='" + cardNo
				+ '\'' + ", type='" + type + '\'' + ", verifyCode='" + verifyCode + '\'' + ", makerVersion='"
				+ makerVersion + '\'' + ", state='" + state + '\'' + ", creatorId='" + creatorId + '\'' + ", creator='"
				+ creator + '\'' + ", notes='" + notes + '\'' + ", firmId='" + firmId + '\'' + ", firmName='" + firmName
				+ '\'' + ", createTime='" + createTime + '\'' + ", modifyTime='" + modifyTime + '\'' + '}';
	}

}
