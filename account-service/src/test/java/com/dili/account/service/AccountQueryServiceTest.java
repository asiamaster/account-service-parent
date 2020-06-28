package com.dili.account.service;

import com.alibaba.fastjson.JSON;
import com.dili.account.BaseTest;
import com.dili.account.dto.AccountWithAssociationResponseDto;
import com.dili.account.dto.UserAccountCardQuery;
import com.dili.account.dto.UserAccountCardResponseDto;
import com.dili.account.type.CardType;
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

    @Test
    void testGetByCardNoWithAssociationForRest() {
        AccountWithAssociationResponseDto rest1 = accountQueryService.getByCardNoWithAssociationForRest("888800034670");
        UserAccountCardResponseDto primary = rest1.getPrimary();
        assertTrue(CardType.isMaster(primary.getCardType()));
        LOGGER.info("获取到结果1:{}",JSON.toJSONString(rest1));
        AccountWithAssociationResponseDto rest2 = accountQueryService.getByCardNoWithAssociationForRest("888800034671");
        assertTrue(CardType.isSlave(rest2.getPrimary().getCardType()));
        LOGGER.info("获取到结果2:{}",JSON.toJSONString(rest2));
    }
}
