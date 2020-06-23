package com.dili.account;

import cn.hutool.core.util.RandomUtil;
import com.dili.account.controller.CardManageController;
import com.dili.account.dto.UserAccountCardQuery;
import com.dili.account.entity.UserAccountDo;
import com.dili.account.entity.UserCardDo;
import com.dili.account.type.AccountStatus;
import com.dili.account.type.AccountType;
import com.dili.account.type.AccountUsageType;
import com.dili.account.type.CardCategory;
import com.dili.account.type.CardType;
import com.dili.account.type.CardStatus;
import com.dili.account.type.CreateSource;
import com.dili.account.type.UsePermissionType;
import com.dili.account.util.AesCipher;
import com.dili.account.util.PasswordUtils;
import com.dili.ss.util.DateUtils;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Auther: miaoguoxin
 * @Date: 2020/6/18 10:08
 * @Description:
 */
@SpringBootTest
@AutoConfigureMockMvc
public class BaseTest {
    protected static Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);

    protected static final String RANDOM_STR = "1234567890";

    protected static UserAccountDo createAccount() {
        UserAccountDo userAccountDo = new UserAccountDo();
        userAccountDo.setAccountId(RandomUtil.randomLong(1000000));
        userAccountDo.setParentAccountId(0L);
        userAccountDo.setFundAccountId(RandomUtil.randomLong(1000000));
        userAccountDo.setFirmId(1L);
        userAccountDo.setFirmName("测试市场");
        userAccountDo.setCustomerId(RandomUtil.randomLong(1000000));
        try {
            userAccountDo.setSecretKey(AesCipher.generateSecretKey());
        } catch (Exception e) {
            e.printStackTrace();
        }
        userAccountDo.setLoginPwd(PasswordUtils.encrypt("12345678", userAccountDo.getSecretKey()));
        userAccountDo.setHolderName("测试小明");
        userAccountDo.setPermissions(genPermission());
        userAccountDo.setUsageType(AccountUsageType.TRADE.getCode());
        userAccountDo.setType(AccountType.PURCHASE.getCode());
        userAccountDo.setSource(CreateSource.COUNTER.getCode());
        userAccountDo.setState(AccountStatus.NORMAL.getType());
        userAccountDo.setVersion(1);
        userAccountDo.setCreateTime(LocalDateTime.now());
        userAccountDo.setModifyTime(LocalDateTime.now());
        return userAccountDo;
    }


    protected static UserCardDo createCard() {
        UserCardDo userCardDo = new UserCardDo();
        userCardDo.setState(CardStatus.NORMAL.getCode());
        userCardDo.setAccountId(RandomUtil.randomLong(1000000));
        userCardDo.setDeviceId("test_" + RandomUtil.randomString(RANDOM_STR, 10));
        userCardDo.setCardNo(RandomUtil.randomString(RANDOM_STR, 10));
        userCardDo.setCreator("测试小哥");
        userCardDo.setCreatorId(1L);
        userCardDo.setFirmId(1L);
        userCardDo.setFirmName("测试市场");
        userCardDo.setLast(1);
        userCardDo.setVersion(1);
        userCardDo.setCreateTime(LocalDateTime.now());
        userCardDo.setModifyTime(LocalDateTime.now());
        userCardDo.setCategory(CardCategory.PARK.getCode());
        userCardDo.setType(CardType.MASTER.getCode());
        userCardDo.setVerifyCode(RandomUtil.randomString(RANDOM_STR, 3));
        return userCardDo;
    }

    private static String genPermission() {
        int code = UsePermissionType.RECHARGE.getCode();
        int code1 = UsePermissionType.TRANSACTION.getCode();
        return code + "," + code1;
    }

    protected static UserAccountCardQuery createQueryParam() {
        UserAccountCardQuery query = new UserAccountCardQuery();
        query.setAccountIds(Lists.newArrayList(862066L));
        return query;
    }

    protected static UserAccountCardQuery createQueryParamDate() {
        UserAccountCardQuery query = new UserAccountCardQuery();
        Date startDate = DateUtils.formatDateStr2Date("2020-06-18 13:21:41");
        query.setStartDate(DateUtils.dateToLocalDateTime(startDate));
        Date endDate = DateUtils.formatDateStr2Date("2020-06-19 13:43:43");
        query.setEndDate(DateUtils.dateToLocalDateTime(endDate));
        return query;
    }

    protected static UserAccountCardQuery createQueryParamAll() {
        UserAccountCardQuery query = new UserAccountCardQuery();
        query.setAccountIds(Lists.newArrayList(862066L));
        query.setCardType(CardType.MASTER.getCode());
//        Date startDate = DateUtils.formatDateStr2Date("2020-06-18 13:21:41");
//        query.setStartDate(DateUtils.dateToLocalDateTime(startDate));
        return query;
    }
}
