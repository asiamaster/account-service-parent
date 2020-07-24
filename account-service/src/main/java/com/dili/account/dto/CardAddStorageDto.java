package com.dili.account.dto;

import com.dili.account.type.CardType;

/**
 * @description： 卡片入库信息
 * 
 * @author ：WangBo
 * @time ：2020年4月28日下午4:14:56
 */
public class CardAddStorageDto {

	/** 卡片硬件标识 */
	private String deviceId;
	/** 卡号 */
	private String cardNo;
	/** 卡类型 */
	private Integer type;
	/** 验证码 */
	private String verifyCode;
	/** 卡片机构编码-卡片内读出 */
	private String institutionCode;
	/** 制卡程序版本号 */
	private Integer makerVersion;
	/** 商户ID */
	private Long firmId;
	/** 商户名称 */
	private String firmName;
	/** 备注 */
	private String notes;
	/** 入库人 */
	private String creator;
	/** 操作员 */
	private Long creatorId;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getInstitutionCode() {
		return institutionCode;
	}

	public void setInstitutionCode(String institutionCode) {
		this.institutionCode = institutionCode;
	}

	public Integer getMakerVersion() {
		return makerVersion;
	}

	public void setMakerVersion(Integer makerVersion) {
		this.makerVersion = makerVersion;
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

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

}
