package com.dili.account.dto;

/**
 * @description： 卡片入库信息
 * 
 * @author ：WangBo
 * @time ：2020年4月28日下午4:14:56
 */
public class CardAddRepositoryDto {

	/** 卡片硬件标识 */
	private String deviceId;
	/** 卡号 */
	private String cardNo;
	/** 卡类型 */
	private Integer category;
	/** 验证码 */
	private String verifyCode;
	/** 卡片机构编码-卡片内读出 */
	private String institutionCode;
	/** 制卡程序版本号 */
	private Integer makerVersion;
	/** 市场编码 */
	private String marketId;
	/** 备注 */
	private String remark;
	/** 入库人 */
	private String createrName;

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

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
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

	public String getMarketId() {
		return marketId;
	}

	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreaterName() {
		return createrName;
	}

	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}

}
