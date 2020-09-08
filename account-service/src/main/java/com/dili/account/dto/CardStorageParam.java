package com.dili.account.dto;

/**
 * @description： 卡片仓库参数
 * 
 * @author ：WangBo
 * @time ：2020年9月3日下午6:04:59
 */
public class CardStorageParam {
	/** 商户ID */
	private Long firmId;
	/** 入库记录ID */
	private Long storageInId;

	public Long getFirmId() {
		return firmId;
	}

	public void setFirmId(Long firmId) {
		this.firmId = firmId;
	}

	public Long getStorageInId() {
		return storageInId;
	}

	public void setStorageInId(Long storageInId) {
		this.storageInId = storageInId;
	}

}
