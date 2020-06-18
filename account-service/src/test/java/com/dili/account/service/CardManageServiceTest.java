package com.dili.account.service;

import com.dili.account.BaseTest;
import com.dili.account.dao.IUserCardDao;
import com.dili.account.dto.CardRequestDto;
import com.dili.account.entity.UserCardDo;
import com.dili.account.type.CardStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @Auther: miaoguoxin
 * @Date: 2020/6/16 19:53
 * @Description:
 */

class CardManageServiceTest extends BaseTest {
    @Autowired
    private ICardManageService cardManageService;

    @MockBean
    private IUserCardDao userCardDao;
    @MockBean
    private IPasswordService passwordService;

    @Test
    void testLostCard() {
        CardRequestDto cardParam = new CardRequestDto();
        cardParam.setAccountId(1L);
        cardParam.setLoginPwd("12345678");
        UserCardDo card = this.createCard(CardStatus.NORMAL);
        when(userCardDao.getById(cardParam.getAccountId())).thenReturn(card);
        when(userCardDao.update(card)).thenReturn(1);
//        doThrow(new RuntimeException("密码错误")).when(passwordService)
//                .checkLoginPwd(id,loginPwd);

        cardManageService.reportLoss(cardParam);

        verify(userCardDao, times(1)).update(card);
    }


    private UserCardDo createCard(CardStatus cardStatus) {
        UserCardDo userCard = new UserCardDo();
        userCard.setState(cardStatus.getCode());
        userCard.setCreator("测试人员");
        userCard.setCardNo("12345678");
        return userCard;
    }

    @Test
    void changeCard() {

    }
}
