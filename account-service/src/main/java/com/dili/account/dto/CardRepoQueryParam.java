package com.dili.account.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
	/**开始时间*/
	private LocalDateTime startDate;
	/**结束时间*/
	private LocalDateTime endDate;
	/** 卡类型 */
	private Integer type;
	/** 卡片在仓库状态 */
	private Integer state;
	private List<String> cardNos;
	/** 卡片起始号 */
	private Long startCardNo;
	/** 卡片结束号 */
	private Long endCardNo;

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

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Long getStartCardNo() {
		return startCardNo;
	}

	public void setStartCardNo(Long startCardNo) {
		this.startCardNo = startCardNo;
	}

	public Long getEndCardNo() {
		return endCardNo;
	}

	public void setEndCardNo(Long endCardNo) {
		this.endCardNo = endCardNo;
	}

}
