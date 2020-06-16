/**
 * Copyright (c) 2019 www.diligrp.com All rights reserved. <br />
 * 本软件源代码版权归地利集团,未经许可不得任意复制与传播.<br />
 *
 */
package com.dili.account.type;

import java.util.ArrayList;
import java.util.List;

/**
 * 实名认证状态
 */
public enum AuthStatus {
	/** 未认证 */
	UN_AUTH(1, "未认证"),
	/** 已认证 */
	AUTH(2, "已认证");

	private Integer code;
	private String name;

	private AuthStatus(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(Integer code) {
		if (code == null) {
			return "";
		}
		for (AuthStatus type : AuthStatus.values()) {
			if (type.getCode() == code) {
				return type.name;
			}
		}
		return "";
	}

	public static List<AuthStatus> getAllList() {
		AuthStatus[] itemList = AuthStatus.values();
		List<AuthStatus> allItemList = new ArrayList<AuthStatus>();
		for (AuthStatus type : itemList) {
			allItemList.add(type);
		}
		return allItemList;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 账户是否已实名认证
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isAuth(int number) {
		return AUTH.getCode() == number;
	}
}
