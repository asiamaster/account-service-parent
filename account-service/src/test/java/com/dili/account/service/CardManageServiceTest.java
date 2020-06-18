package com.dili.account.service;

import com.dili.account.BaseTest;
import com.dili.account.dao.IUserAccountDao;
import com.dili.account.dao.IUserCardDao;
import com.dili.account.dto.CardRequestDto;
import com.dili.account.dto.OperatorRequestDto;
import com.dili.account.entity.CardAggregationWrapper;
import com.dili.account.entity.UserAccountDo;
import com.dili.account.entity.UserCardDo;
import com.dili.account.type.CardStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    private IAccountQueryService accountQueryService;
    @MockBean
    private IUserCardDao userCardDao;
    @MockBean
    private IUserAccountDao userAccountDao;


    @Test
    void testReportLoss() {
        CardAggregationWrapper wrapper = this.createWrapper(CardStatus.NORMAL);
        CardRequestDto cardParam = this.createParams(wrapper);
        cardParam.setLoginPwd("12345678");
        UserCardDo userCard = wrapper.getUserCard();

        when(accountQueryService.getByAccountIdWithNotNull(cardParam.getAccountId()))
                .thenReturn(wrapper);
        when(userCardDao.update(userCard)).thenReturn(1);
        when(userAccountDao.getByAccountId(userCard.getAccountId()))
                .thenReturn(wrapper.getUserAccount());
        cardManageService.reportLoss(cardParam);
        assertEquals(CardStatus.LOSS.getCode(), userCard.getState());
        verify(userCardDao, times(1)).update(userCard);
    }


    @Test
    void changeCard() {

    }

    private CardRequestDto createParams(CardAggregationWrapper wrapper) {
        UserCardDo userCard = wrapper.getUserCard();
        CardRequestDto cardParam = new CardRequestDto();
        cardParam.setAccountId(wrapper.getUserAccount().getAccountId());
        cardParam.setLoginPwd("12345678");
        OperatorRequestDto operator = new OperatorRequestDto();
        operator.setOpId(userCard.getCreatorId());
        operator.setOpName(userCard.getCreator());
        cardParam.setOperator(operator);
        return cardParam;
    }

    private CardAggregationWrapper createWrapper(CardStatus cardStatus) {
        UserCardDo card = createCard();
        UserAccountDo account = createAccount();

        card.setAccountId(account.getAccountId());
        card.setState(cardStatus.getCode());

        CardAggregationWrapper wrapper = new CardAggregationWrapper();
        wrapper.setFirmId(card.getFirmId());
        wrapper.setUserCard(card);
        wrapper.setUserAccount(account);
        return wrapper;
    }


}
