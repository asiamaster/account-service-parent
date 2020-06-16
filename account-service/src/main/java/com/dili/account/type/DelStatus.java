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
public enum DelStatus {
	/** 未认证 */
	NORMAL(1, "正常"),
	/** 已认证 */
	DELETED(2, "已删除");

	private Integer code;
	private String name;

	private DelStatus(Integer code, String name) {
		this.code = code;
		this.name = name;
	}

	public static String getName(Integer code) {
		if (code == null) {
			return "";
		}
		for (DelStatus type : DelStatus.values()) {
			if (type.getCode() == code) {
				return type.name;
			}
		}
		return "";
	}

	public static List<DelStatus> getAllList() {
		DelStatus[] itemList = DelStatus.values();
		List<DelStatus> allItemList = new ArrayList<DelStatus>();
		for (DelStatus type : itemList) {
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
	 * 是否为已删除状态
	 */
	public static boolean isDeled(int number) {
		return DELETED.getCode() == number;
	}
}
