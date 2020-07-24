package com.dili.account.common;


public enum ExceptionMsg {

	CARD_NOT_EXIST("卡片不存在"),
	ACCOUNT_NOT_EXIST("卡账户不存在"),
	ACCOUNT_DISABLED("卡账户已被禁用"),
	ACCOUNT_FROZEN("卡账户已被冻结"),
	;
	private String name;

	ExceptionMsg( String name) {
		this.name = name;
	}


	public String getName() {
		return name;
	}
}
