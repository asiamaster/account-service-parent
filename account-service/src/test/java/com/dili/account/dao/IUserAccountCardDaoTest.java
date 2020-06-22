package com.dili.account.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.dili.account.BaseTest;
import com.dili.account.dto.UserAccountCardQuery;
import com.dili.account.entity.CardAggregationWrapper;
import com.dili.account.entity.UserAccountDo;
import com.dili.account.entity.UserCardDo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * @Auther: miaoguoxin
 * @Date: 2020/6/19 13:13
 * @Description:
 */
class IUserAccountCardDaoTest extends BaseTest {

    @Autowired
    private IUserAccountCardDao userAccountCardDao;

    @Test
    void testGetListByConditionWithAccountIds() {
        UserAccountCardQuery queryParam = this.createQueryParam();
        List<CardAggregationWrapper> pageByCondition = userAccountCardDao.getListByCondition(queryParam);
        LOGGER.info("testGetPageByConditionWithAccountIds获取到的实体:{}", JSON.toJSONString(pageByCondition));
        for (CardAggregationWrapper wrapper : pageByCondition) {
            this.assertResult(wrapper);
        }

    }

    @Test
    public void testGetListByConditionWithDate() {
        UserAccountCardQuery queryParam = this.createQueryParamDate();
        List<CardAggregationWrapper> pageByCondition = userAccountCardDao.getListByCondition(queryParam);
        LOGGER.info("testGetListByConditionWithDate获取到的实体:{},长度:{}",
                JSON.toJSONString(pageByCondition),
                pageByCondition.size());
        assertEquals(4, pageByCondition.size());
        for (CardAggregationWrapper wrapper : pageByCondition) {
            this.assertResult(wrapper);
        }
    }

    @Test
    void testGetListByConditionWithAll() {
        UserAccountCardQuery queryParam = this.createQueryParamAll();
        List<CardAggregationWrapper> pageByCondition = userAccountCardDao.getListByCondition(queryParam);
        LOGGER.info("testGetPageByConditionWithAll获取到的实体:{},长度:{}", JSON.toJSONString(pageByCondition), pageByCondition.size());
        for (CardAggregationWrapper wrapper : pageByCondition) {
            this.assertResult(wrapper);
        }
    }

    @Test
    void testGetPageWithDate() {
        UserAccountCardQuery queryParam = this.createQueryParamDate();
        Page<Object> page = PageHelper.startPage(1, 10);
        List<CardAggregationWrapper> pageByCondition = userAccountCardDao.getListByCondition(queryParam);
        LOGGER.info(page.getTotal() + "");
        LOGGER.info("testGetPageByConditionWithAll获取到的实体:{},长度:{}", JSON.toJSONString(pageByCondition), pageByCondition.size());
    }


    private void assertResult(CardAggregationWrapper wrapper) {
        UserAccountDo userAccount = wrapper.getUserAccount();
        UserCardDo userCard = wrapper.getUserCard();
        assertNotNull(wrapper.getAccountId());
        assertNotNull(wrapper.getFirmId());
        assertNotNull(userAccount);
        assertNotNull(userCard);
        assertNotNull(userCard.getCardNo());
        assertNotNull(userCard.getId());
        assertNotNull(userCard.getCategory());
        assertNotNull(userCard.getState());
        assertNotNull(userAccount.getAccountId());
        assertNotNull(userAccount.getId());
        assertNotNull(userAccount.getFundAccountId());
        assertNotNull(userAccount.getCustomerId());
    }

}
