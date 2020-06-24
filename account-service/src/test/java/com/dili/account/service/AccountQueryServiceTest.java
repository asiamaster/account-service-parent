package com.dili.account.service;

import com.alibaba.fastjson.JSON;
import com.dili.account.BaseTest;
import com.dili.account.dto.UserAccountCardQuery;
import com.dili.account.dto.UserAccountCardResponseDto;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.util.List;

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

    @Test
    void cardExist() {
    }

    @Test
    void getByCardNoForRest() {
        UserAccountCardQuery params = new UserAccountCardQuery();
        params.setCustomerIds(Lists.newArrayList(1L));
        List<UserAccountCardResponseDto> listByConditionForRest = accountQueryService.getListByConditionForRest(params);
        LOGGER.info("ffff:{}",JSON.toJSONString(listByConditionForRest));
    }

    @Test
    void getListByConditionForRest() {
    }

    @Test
    void getPageByConditionForRest() {
    }

    @Test
    void getByAccountIdWithNotNull() {
    }
}
