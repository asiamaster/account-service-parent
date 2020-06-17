package com.dili.account.dto;

import com.dili.account.common.Page;

/**
 * @description： 卡片仓库查询参数
 * 
 * @author ：WangBo
 * @time ：2020年4月28日下午4:14:56
 */
public class CardRepoQueryParam extends Page {

	/** 卡号 */
	private String cardNo;
	

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
}
