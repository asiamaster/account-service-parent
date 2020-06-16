package com.dili.account.type;

import java.util.ArrayList;
import java.util.List;

/**
 * @description： 卡片类型
 * 
 * @author ：WangBo
 * @time ：2020年4月24日上午9:58:20
 */
public enum CardCategory {
	MASTER("主卡", 10),

	SLAVE("副卡", 20),

	ANONYMOUS("匿名卡", 30),

	UNION("联营卡", 40),

	BANK("银行卡", 60);

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

	public static List<CardCategory> getAllList() {
		CardCategory[] itemList = CardCategory.values();
		List<CardCategory> allItemList = new ArrayList<CardCategory>();
		for (CardCategory type : itemList) {
			allItemList.add(type);
		}
		return allItemList;
	}

	/**
	 * 获取所有主卡类型
	 * 
	 * @return
	 */
	public static List<Integer> getMasterList() {
		List<Integer> allItemList = new ArrayList<Integer>();
		allItemList.add(MASTER.getCode());
		allItemList.add(BANK.getCode());
		allItemList.add(UNION.getCode());
		return allItemList;
	}

	public static Boolean checkIsMasterCard(int code) {
		if (code == CardCategory.MASTER.getCode() || code == CardCategory.UNION.getCode()
				|| code == CardCategory.BANK.getCode()) {
			return true;
		}
		return false;
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
