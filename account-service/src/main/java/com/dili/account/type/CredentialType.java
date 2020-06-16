package com.dili.account.type;

import java.util.ArrayList;
import java.util.List;

/**
 * @description： 证件类型
 * 
 * @author ：WangBo
 * @time ：2018年8月6日下午4:05:50
 */
public enum CredentialType {
	/** 身份证 */
	ID("身份证", "id"),

	/** 统一社会信用代码 */
	USCC("统一社会信用代码", "uscc"),
	
	/** 港澳台通行证 */
	PASSPORT("港澳台通行证", "passport");

	private  String name;
	private  String code;

	private CredentialType(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public static CredentialType getCredentialType(String code) {
		for (CredentialType type : CredentialType.values()) {
			if (type.getCode() == code) {
				return type;
			}
		}
		return null;
	}

	/**
	 * 个人证件类型，排除对公证件类型（如：统一社会信用代码）
	 */
	public static List<CredentialType> getList() {
		List<CredentialType> list = new ArrayList<>();
		for (CredentialType type : CredentialType.values()) {
			if (type.getCode() == USCC.code) {
				continue;
			}
			list.add(type);
		}
		return list;
	}

	public static String getName(String code) {
		for (CredentialType type : CredentialType.values()) {
			if (type.getCode().equals(code)) {
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
