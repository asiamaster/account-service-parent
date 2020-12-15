package com.dili.account.type;

/**
 * @description： 客户信息修改时是否同步修改持卡人信息1-是，2-否
 * 
 * @author ：WangBo
 * @time ：2020年12月15日上午10:59:49
 */
public enum CustomerSyncModifyHoldinfo {
	/** 是 */
	Y(1, "是"),
	/** 否 */
	N(2, "否");

	private Integer code;
	private String name;

	private CustomerSyncModifyHoldinfo(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
