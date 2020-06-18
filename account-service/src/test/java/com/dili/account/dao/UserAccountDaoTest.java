package com.dili.account.dao;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.dili.account.BaseTest;
import com.dili.account.entity.UserAccountDo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Auther: miaoguoxin
 * @Date: 2020/6/18 11:29
 * @Description:
 */
class UserAccountDaoTest extends BaseTest {
    @Autowired
    private IUserCardDao userCardDao;
    @Autowired
    private IUserAccountDao userAccountDao;

    private static UserAccountDo account;

    @BeforeAll
    static void setUp() {
        account = createAccount();
    }

    @Test
    //@Transactional
    //@Rollback
    void testSave() {
        int save = userAccountDao.save(account);
        LOGGER.info("获取的到account：{}", JSON.toJSONString(account));
        assertNotNull(account.getId());
        assertEquals(1,save);
    }

    @Test
    void getById() {
    }

    @Test
    void update() {
    }
}
