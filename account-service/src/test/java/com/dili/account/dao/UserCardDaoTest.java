package com.dili.account.dao;

import com.dili.account.BaseTest;
import com.dili.account.entity.UserCardDo;
import com.dili.account.type.CardStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @Auther: miaoguoxin
 * @Date: 2020/6/18 10:10
 * @Description:
 */
class UserCardDaoTest extends BaseTest {

    @Autowired
    private IUserCardDao userCardDao;

    private static UserCardDo card;

    @BeforeAll
    static void init() {
        card = createCard();
    }

    @Test
    void testSave() {
        int save = userCardDao.save(card);
        assertNotNull(card.getId());
        assertEquals(1, save);
    }

    @Test
    void getByAccountId() {
    }

    @Test
    void update() {
        card.setState(CardStatus.LOSS.getCode());
        card.setModifyTime(LocalDateTime.now());
        int update = userCardDao.update(card);
        assertEquals(CardStatus.LOSS.getCode(), card.getState());
        assertEquals(1, update);
    }
}
