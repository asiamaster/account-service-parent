package com.dili.account.dto;

import com.dili.ss.domain.BaseDomain;

/**
 * @description： 卡片仓库查询参数
 * 
 * @author ：WangBo
 * @time ：2020年4月28日下午4:14:56
 */
public class CardRepoQueryParam extends BaseDomain {

	/** */
	private static final long serialVersionUID = 1L;
	/** 卡号 */
	private String cardNo;
	

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
}
