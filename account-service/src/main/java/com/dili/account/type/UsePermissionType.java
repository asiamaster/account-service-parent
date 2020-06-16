package com.dili.account.type;

import java.util.Arrays;
import java.util.List;

/**
 * @description： 账户固定权限 充值、提现、交易、二次交易、转账
 * 
 * @author ：WangBo
 * @time ：2018年8月6日下午4:52:05
 */
public enum UsePermissionType {

	/** 充值 */
	RECHARGE("充值", 11),
	/** 提现 */
	WITHDRAW("提现", 12),
	/** 交易 */
	TRANSACTION("交易", 13),
	/** 转账 */
	TRANSFER("转账", 14);

	private String name;
	private int code;

	private UsePermissionType(String name, int code) {
		this.name = name;
		this.code = code;
	}

	public static List<String> getPermissionList(String permissions){
		String[] split = permissions.split(",");
		return Arrays.asList(split);
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
	
	public static void main(String[] args) {
		String permission="12,23,24,,";
		System.out.println(getPermissionList(permission));
	}
}
