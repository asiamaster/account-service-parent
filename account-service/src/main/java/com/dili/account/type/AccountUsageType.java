package com.dili.account.type;

import java.util.ArrayList;
import java.util.List;

/**
 * @description： 账户用途类型
 *
 * @author ：miaoguoxin
 * @time ：2020年4月22日下午6:50:34
 */
public enum AccountUsageType {
    TRADE("交易账户", 1),

	UTILITIES("水电费账户", 2),
	;

    private String name;
    private int code;

    AccountUsageType(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public static AccountUsageType getAccountUsageType(int code) {
        for (AccountUsageType type : AccountUsageType.values()) {
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
        for (AccountUsageType type : AccountUsageType.values()) {
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
