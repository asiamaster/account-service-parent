package com.dili.account.type;

import java.util.Arrays;
import java.util.List;

/**
 * @description： 客户区域
 * 
 * @author ：WangBo
 * @time ：2018年8月6日下午4:05:50
 */
public enum CustomerAreaType {
	/** 本地*/
	LOCAL("本地", 1),

	/** 外埠 */
	OUTSIDE("外埠", 2);

	private String name;
	private Integer code;

	private CustomerAreaType(String name, Integer code) {
		this.name = name;
		this.code = code;
	}

	public static CustomerAreaType getCustomerAreaType(Integer code) {
		if(code == null || code ==0) {
			return null;
		}
		for (CustomerAreaType type : CustomerAreaType.values()) {
			if (type.getCode() == code.intValue()) {
				return type;
			}
		}
		return null;
	}

	public static List<CustomerAreaType> getList() {
		return Arrays.asList(CustomerAreaType.values());
	}

	public static String getName(Integer code) {
		if(code == null || code ==0) {
			return "";
		}
		for (CustomerAreaType type : CustomerAreaType.values()) {
			if (type.getCode() == code.intValue()) {
				return type.name;
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the code
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(Integer code) {
		this.code = code;
	}

}
