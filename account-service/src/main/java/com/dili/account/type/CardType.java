package com.dili.account.type;

/**
 * @description： 卡片类型
 * 
 * @author ：WangBo
 * @time ：2020年4月24日上午9:58:20
 */
public enum CardType {
	ELEC_CARD("电子卡", 1),

	PHYSICAL_CARD("物理实体卡", 2);

	private String name;
	private int code;

	private CardType(String name, int code) {
		this.name = name;
		this.code = code;
	}

	public static CardType getCardCategory(int code) {
		for (CardType category : CardType.values()) {
			if (category.getCode() == code) {
				return category;
			}
		}
		return null;
	}

	public static String getName(int code) {
		for (CardType category : CardType.values()) {
			if (category.getCode() == code) {
				return category.name;
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

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}