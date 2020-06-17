package com.dili.account.service;

import com.dili.account.dao.IUserCardDao;
import com.dili.account.entity.UserCardEntity;
import com.dili.account.type.CardStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @Auther: miaoguoxin
 * @Date: 2020/6/16 19:53
 * @Description:
 */
@SpringBootTest
class CardManageServiceTest {
    @Autowired
    private ICardManageService cardManageService;

    @MockBean
    private IUserCardDao userCardDao;
    @MockBean
    private IPasswordService passwordService;

    @Test
    void testLostCard() {
        Long id = 1L;
        String loginPwd="12345678";
        UserCardEntity card = this.createCard(CardStatus.NORMAL);
        when(userCardDao.getById(id)).thenReturn(card);
        when(userCardDao.update(card)).thenReturn(1);
        doThrow(new RuntimeException("密码错误")).when(passwordService)
                .checkLoginPwd(id,loginPwd);

        cardManageService.reportLoss(id,loginPwd);

        verify(userCardDao, times(1)).update(card);
    }


    private UserCardEntity createCard(CardStatus cardStatus){
        UserCardEntity userCard = new UserCardEntity();
        userCard.setState(cardStatus.getCode());
        userCard.setCreator("测试人员");
        userCard.setCardNo("12345678");
        return userCard;
    }
}
