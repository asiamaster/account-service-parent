package com.dili.account.common;


public enum ExceptionMsg {

	CARD_NOT_EXIST("卡片不存在"),
	ACCOUNT_NOT_EXIST("卡账号不存在");

	private String name;

	ExceptionMsg( String name) {
		this.name = name;
	}


	public String getName() {
		return name;
	}
}
