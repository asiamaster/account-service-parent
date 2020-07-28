package com.dili.account.service;

import com.alibaba.fastjson.JSON;
import com.dili.account.BaseTest;
import com.dili.account.dto.AccountWithAssociationResponseDto;
import com.dili.account.dto.UserAccountCardQuery;
import com.dili.account.dto.UserAccountCardResponseDto;
import com.dili.account.type.CardType;
import com.dili.account.validator.AccountValidator;
import com.dili.ss.domain.PageOutput;
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
        UserAccountCardQuery query = new UserAccountCardQuery();
        query.setCardNos(Lists.newArrayList(cardNo));
        UserAccountCardResponseDto responseDto = accountQueryService.getSingleForRest(query);
        assertNotNull(responseDto);
        LOGGER.info("得到的实体:{}", JSON.toJSONString(responseDto));
        Field[] declaredFields = responseDto.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            assertNotNull(declaredField.get(responseDto));
        }
    }

    @Test
    void testGetSingleForRest() {
        UserAccountCardQuery query = new UserAccountCardQuery();
        query.setAccountIds(Lists.newArrayList(174L));
        UserAccountCardResponseDto responseDto = accountQueryService.getSingleForRest(query, AccountValidator.NONE);
        assertNotNull(responseDto);
        LOGGER.info("得到的实体:{}", JSON.toJSONString(responseDto));
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
        UserAccountCardQuery params = new UserAccountCardQuery();
        params.setPage(1);
        params.setRows(10);
        params.setCardState(1);
        //params.setCustomerIds(Lists.newArrayList(1L));
        PageOutput<List<UserAccountCardResponseDto>> pageByConditionForRest = accountQueryService.getPageByConditionForRest(params);

    }

    @Test
    void getByAccountIdWithNotNull() {
    }

    @Test
    void testGetByCardNoWithAssociationForRest() {
        UserAccountCardQuery query = new UserAccountCardQuery();
        query.setCardNos(Lists.newArrayList("888800034670"));
        AccountWithAssociationResponseDto rest1 = accountQueryService.getSingleWithAssociationForRest(query);
        UserAccountCardResponseDto primary = rest1.getPrimary();
        assertTrue(CardType.isMaster(primary.getCardType()));
        LOGGER.info("获取到结果1:{}",JSON.toJSONString(rest1));
        AccountWithAssociationResponseDto rest2 = accountQueryService.getSingleWithAssociationForRest(query);
        assertTrue(CardType.isSlave(rest2.getPrimary().getCardType()));
        LOGGER.info("获取到结果2:{}",JSON.toJSONString(rest2));
    }


}
