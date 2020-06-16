package com.dili.account.type;

import java.util.ArrayList;
import java.util.List;

/**
 * 卡片类型，买家卡或者卖家卡
 */
public enum CardBizType {
	BUYER("买家卡", 1, "purchase"),

	SELLER("卖家卡", 2, "sale"),

	DRIVER("司机", 3, "driver");

	private String name;
	private int code;
	private String crmCode;

	CardBizType(String name, int code, String crmCode) {
		this.name = name;
		this.code = code;
		this.crmCode = crmCode;
	}

	public static CardBizType getBizUsageType(int code) {
		for (CardBizType type : CardBizType.values()) {
			if (type.getCode() == code) {
				return type;
			}
		}
		return null;
	}

	/**
	 * 排除买卖家
	 */
	public static List<CardBizType> getList() {
		List<CardBizType> types = new ArrayList<CardBizType>();
		for (CardBizType type : CardBizType.values()) {
			if (type.getCode() != 3) {
				types.add(type);
			}
		}
		return types;
	}

	public static String getName(int code) {
		for (CardBizType type : CardBizType.values()) {
			if (type.getCode() == code) {
				return type.name;
			}
		}
		return "";
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

	public String getCrmCode() {
		return crmCode;
	}

	public void setCrmCode(String crmCode) {
		this.crmCode = crmCode;
	}
}
