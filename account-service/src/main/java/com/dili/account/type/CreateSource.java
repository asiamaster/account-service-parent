package com.dili.account.type;

/**
 * @description： 地利通用户注册来源
 * 
 * @author ：WangBo
 * @time ：2019年7月29日下午5:03:16
 */
public enum CreateSource {
	/** 实体卡柜台开通*/
	COUNTER("实体卡柜台开通", 1), 
	
	/** 小程序买家端注册*/
	WEAPP_BUYER("小程序买家端注册", 2), 
	
	/** 买家端APP注册*/
	APP_BUYER("买家端APP注册", 3), 
	
	/** 卖家端APP注册*/
	APP_SELLER("卖家端APP注册", 4);

	private String name;
	private int code;

	private CreateSource(String name, int code) {
		this.name = name;
		this.code = code;
	}

	public static CreateSource getDltAccountType(int code) {
		for (CreateSource type : CreateSource.values()) {
			if (type.getCode() == code) {
				return type;
			}
		}
		return null;
	}

	public static String getName(int code) {
		for (CreateSource type : CreateSource.values()) {
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
