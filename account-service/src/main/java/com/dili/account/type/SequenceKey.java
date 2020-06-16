package com.dili.account.type;

/**
 * @description： 
 * ID生成器KEY,与数据库表字段对应
 * @author ：WangBo
 * @time ：2020年4月23日下午3:01:44
 */
public enum SequenceKey {

	USER_ACCOUNT("USER_ACCOUNT"),

	USER_CARD("USER_CARD"),

	USER_INFO("USER_INFO"),
	
	USER_LEGAL("USER_LEGAL");

	private String key;

	private SequenceKey(String key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return identifier();
	}

	public String identifier() {
		return key;
	}
}
