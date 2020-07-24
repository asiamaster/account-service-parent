package com.dili.account.dto;

/**
 * @description： 卡片入库信息
 * 
 * @author ：WangBo
 * @time ：2020年4月28日下午4:14:56
 */
public class CardAddStorageBaseDto {

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
}
