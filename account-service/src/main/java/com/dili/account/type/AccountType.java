package com.dili.account.type;

import java.util.ArrayList;
import java.util.List;

/**
 * @description： 账户类型
 * 
 * @author ：WangBo
 * @time ：2020年4月22日下午6:50:34
 */
public enum AccountType {
	PERSONAL("个人账户", 1),

	PUBLIC("对公账户", 2),

	/** 临时卡用 **/
	Anonymous("不记名", 3);

	private String name;
	private int code;

	private AccountType(String name, int code) {
		this.name = name;
		this.code = code;
	}

	public static List<AccountType> getEnumItemList() {
		AccountType[] itemList = AccountType.values();
		List<AccountType> filteredItemList = new ArrayList<AccountType>();
		for (AccountType status : itemList) {
			if (status.getCode() != Anonymous.code) {
				filteredItemList.add(status);
			}
		}
		return filteredItemList;
	}

	public static AccountType getAccountType(int code) {
		for (AccountType type : AccountType.values()) {
			if (type.getCode() == code) {
				return type;
			}
		}
		return null;
	}

	public static String getName(int code) {
//    	if(code == null||code == 0) {
//    		return "";
//    	}
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

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String toString() {
		return name;
	}
}
