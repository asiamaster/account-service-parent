package com.dili.account.type;

/**
 * @description： 卡片种类
 *
 * @author ：WangBo
 * @time ：2020年4月24日上午9:58:20
 */
public enum CardCategory {
	PARK("园区卡", 1),

	BANK("银行卡", 2),
	;

	private String name;
	private int code;

	 CardCategory(String name, int code) {
		this.name = name;
		this.code = code;
	}

	public static CardCategory getCardCategory(int code) {
		for (CardCategory category : CardCategory.values()) {
			if (category.getCode() == code) {
				return category;
			}
		}
		return null;
	}

	public static String getName(int code) {
		for (CardCategory category : CardCategory.values()) {
			if (category.getCode() == code) {
				return category.name;
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public int getCode() {
		return code;
	}

}
