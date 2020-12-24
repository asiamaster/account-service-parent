package com.dili.account.type;

import org.apache.commons.lang.StringUtils;

/**
 * @description： 账户类型，分为经营为、买家、其它类型。 <br>
 * code与数据字典中客户管理系统 》 客户买家角色身份、客户其它角色身份、 客户经营户角色身份对应。可以通过code直接查询客户角色下的子类型
 * 
 * @author ：WangBo
 * @time ：2020年11月27日下午2:50:03
 */
public enum AccountType {
	/** 买家卡 */
	BUYER_CHARACTER("买家卡", "buyer_character_type"),

	/** 经营户 */
	BUSINESS_USER("经营户", "business_user_character_type"),

	/** 其它 */
	OTHER_CHARACTER("其它", "other_character_type");

	private String name;
	private String code;

	private AccountType(String name, String code) {
		this.name = name;
		this.code = code;
	}

	public static AccountType getAccountType(String code) {
		for (AccountType type : AccountType.values()) {
			if (type.getCode().equals(code)) {
				return type;
			}
		}
		return null;
	}

	public static String getName(String code) {
		if (StringUtils.isBlank(code)) {
			return "";
		}
		for (AccountType type : AccountType.values()) {
			if (type.getCode() == code) {
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

	public String toString() {
		return name;
	}
}
