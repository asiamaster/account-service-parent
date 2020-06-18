package com.dili.account;

import cn.hutool.core.util.RandomUtil;
import com.dili.account.entity.UserCardDo;
import com.dili.account.type.CardBizType;
import com.dili.account.type.CardCategory;
import com.dili.account.type.CardStatus;
import com.dili.account.type.CardType;
import org.junit.Before;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Random;

/**
 * @Auther: miaoguoxin
 * @Date: 2020/6/18 10:08
 * @Description:
 */
@SpringBootTest
public class BaseTest {
    private static final String RANDOM_STR = "1234567890";


    protected static UserCardDo createCard() {
        UserCardDo userCardDo = new UserCardDo();
        userCardDo.setState(CardStatus.NORMAL.getCode());
        userCardDo.setAccountId(RandomUtil.randomLong(1000000));
        userCardDo.setDeviceId("test_" + RandomUtil.randomString(RANDOM_STR, 10));
        userCardDo.setCardNo(RandomUtil.randomString(RANDOM_STR, 10));
        userCardDo.setCategory(CardCategory.MASTER.getCode());
        userCardDo.setCreator("测试小哥");
        userCardDo.setCreatorId(1L);
        userCardDo.setFirmId("1");
        userCardDo.setFirmName("测试市场");
        userCardDo.setLast(1);
        userCardDo.setVersion(1);
        userCardDo.setCreateTime(LocalDateTime.now());
        userCardDo.setModifyTime(LocalDateTime.now());
        userCardDo.setType(CardType.PHYSICAL_CARD.getCode());
        userCardDo.setUsageType(CardBizType.BUYER.getCode());
        userCardDo.setVerifyCode(RandomUtil.randomString(RANDOM_STR, 3));
        return userCardDo;
    }
}
