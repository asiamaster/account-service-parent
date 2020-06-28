package com.dili.account.type;

/**
 * @description： 
 *          是否
 * @author ：WangBo
 * @time ：2020年6月23日下午6:05:21
 */
public enum YesNoType {
	YES("是", 1),

	NO("否", 2);

	private String name;
	private int code;

	private YesNoType(String name, int code) {
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
