/**
 * Copyright (c) 2019 www.diligrp.com All rights reserved. <br />
 * 本软件源代码版权归地利集团,未经许可不得任意复制与传播.<br />
 *
 */
package com.dili.account.type;

/**
 * 是否是最新的一张
 */
public enum CardLastState {
    /** 未认证 */
    YES(1, "是"),
    /** 已认证 */
    NO(0, "否");

    private Integer code;
    private String name;

    CardLastState(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
