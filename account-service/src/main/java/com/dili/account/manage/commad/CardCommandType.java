package com.dili.account.manage.commad;

import com.dili.account.type.CardStatus;

public enum CardCommandType {
	
	RETURNED(4, "returnCardCommad");
	
	private int code;
	
	private String name;

	private CardCommandType( int code, String name) {
		this.name = name;
		this.code = code;
	}

	public static CardStatus getCardStatus(int code) {
		for (CardStatus status : CardStatus.values()) {
			if (status.getCode() == code) {
				return status;
			}
		}
		return null;
	}

	public static String getName(int code) {
		for (CardCommandType type : CardCommandType.values()) {
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
}
