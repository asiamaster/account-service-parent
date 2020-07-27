package com.dili.account.dto;

import java.util.List;

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
	/** 商户ID */
	private Long firmId;
	/** 操作员ID */
	private Long creatorId;
	private List<String> cardNos;

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public List<String> getCardNos() {
		return cardNos;
	}

	public void setCardNos(List<String> cardNos) {
		this.cardNos = cardNos;
	}

	public Long getFirmId() {
		return firmId;
	}

	public void setFirmId(Long firmId) {
		this.firmId = firmId;
	}

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

}
