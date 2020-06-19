package com.dili.account.exception.global;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author : miaoguoxin
 * @date: 2020/4/8
 * 系统模块标识
 */
public enum SystemCodeMark implements IEnumType {
    ORDER_SERVICE(200000,"xtrade-order-service"),
    ;
    private static final Stream<SystemCodeMark> SYSTEM_CODE = Stream.of(values());

    private String name;
    private int code;

    SystemCodeMark(int code,String name) {
        this.name = name;
        this.code = code;
    }

    public static SystemCodeMark getSystemCode(int code) {
        Optional<SystemCodeMark> result = SYSTEM_CODE
                .filter((gender) -> gender.getCode() == code)
                .findFirst();
        return result.orElse(null);
    }

    public static SystemCodeMark getSystemCode(String name) {
        Optional<SystemCodeMark> result = SYSTEM_CODE
                .filter((gender) -> gender.getName().equals(name))
                .findFirst();
        return result.orElse(null);
    }

    public static String getName(int code) {
        Optional<SystemCodeMark> result = SYSTEM_CODE
                .filter((gender) -> gender.getCode() == code)
                .findFirst();
        return result.map((systemCode) -> systemCode.name).orElse(null);
    }

    public static List<SystemCodeMark> getGenderList() {
        return Arrays.asList(values());
    }

    public String getName() {
        return this.name;
    }

    public int getCode() {
        return this.code;
    }

    public String toString() {
        return this.name;
    }
}
