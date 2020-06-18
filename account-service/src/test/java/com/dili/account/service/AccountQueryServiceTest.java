package com.dili.account.service;

import com.alibaba.fastjson.JSON;
import com.dili.account.BaseTest;
import com.dili.account.dto.UserAccountCardResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Auther: miaoguoxin
 * @Date: 2020/6/18 10:08
 * @Description:
 */
class AccountQueryServiceTest extends BaseTest {

    @Autowired
    private IAccountQueryService accountQueryService;

    @Test
    void testGetByAccountIdWithNotNull() {

    }

    @Test
    void testGetByCardNoForRest() throws IllegalAccessException {
        String cardNo = "2494445928";
        UserAccountCardResponseDto responseDto = accountQueryService.getByCardNoForRest(cardNo);
        assertNotNull(responseDto);
        LOGGER.info("得到的实体:{}", JSON.toJSONString(responseDto));
        Field[] declaredFields = responseDto.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            assertNotNull(declaredField.get(responseDto));
        }
    }
}
