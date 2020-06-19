package com.dili.account.exception.global;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author : miaoguoxin
 * @date: 2020/4/8
 */
public enum IErrorCode implements IEnumType {
    UNKNOWN_ERROR("未知系统异常", 5000),
    ILLEGAL_PARAMS("系统参数异常", 5010);

    private static final Stream<IErrorCode> SYSTEM_CODE = Stream.of(values());
    private String name;
    private int code;

    IErrorCode(String name, int code) {
        this.name = name;
        this.code = code;
    }

    public static IErrorCode getSystemCode(int code) {
        Optional<IErrorCode> result = SYSTEM_CODE
                .filter((gender) -> gender.getCode() == code)
                .findFirst();
        return result.orElse(null);
    }

    public static String getName(int code) {
        Optional<IErrorCode> result = SYSTEM_CODE
                .filter((gender) -> gender.getCode() == code)
                .findFirst();
        return result.map((systemCode) -> systemCode.name).orElse(null);
    }

    public static List<IErrorCode> getGenderList() {
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
