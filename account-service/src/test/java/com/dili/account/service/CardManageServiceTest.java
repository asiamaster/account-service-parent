package com.dili.account.service;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSON;
import com.dili.account.BaseTest;
import com.dili.account.dao.IUserAccountDao;
import com.dili.account.dao.IUserCardDao;
import com.dili.account.dto.CardRequestDto;
import com.dili.account.entity.CardAggregationWrapper;
import com.dili.account.entity.UserAccountDo;
import com.dili.account.entity.UserCardDo;
import com.dili.account.type.CardStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    @MockBean
    private ICardStorageService cardStorageService;


    @Test
    void testReportLoss() {
        CardAggregationWrapper wrapper = this.createWrapper(CardStatus.NORMAL);
        CardRequestDto cardParam = this.createParams(wrapper);
        cardParam.setLoginPwd("12345678");
        UserCardDo userCard = wrapper.getUserCard();

        when(accountQueryService.getByAccountIdForUnLostCard(cardParam.getAccountId()))
                .thenReturn(wrapper);
        when(userCardDao.update(userCard)).thenReturn(1);
        when(userAccountDao.getByAccountId(userCard.getAccountId()))
                .thenReturn(wrapper.getUserAccount());
        cardManageService.reportLoss(cardParam);

        assertEquals(CardStatus.LOSS.getCode(), userCard.getState());
        verify(userCardDao, times(1)).update(userCard);
    }


    @Test
    void testChangeCard() {
        CardAggregationWrapper wrapper = this.createWrapper(CardStatus.NORMAL);
        //wrapper.getUserCard().setState(CardStatus.RETURNED.getCode());
        CardRequestDto cardParam = this.createParams(wrapper);
        cardParam.setNewCardNo(RandomUtil.randomString(RANDOM_STR, 10));
        cardParam.setLoginPwd("12345678");
        UserCardDo userCard = wrapper.getUserCard();

        when(accountQueryService.getByAccountIdForUnLostCard(cardParam.getAccountId()))
                .thenReturn(wrapper);
        when(userCardDao.update(userCard)).thenReturn(1);
        when(userAccountDao.getByAccountId(userCard.getAccountId()))
                .thenReturn(wrapper.getUserAccount());

        UserCardDo newCard = cardManageService.changeCard(cardParam);

        verify(userCardDao, times(1)).save(newCard);
        verify(cardStorageService, times(1)).voidCard(userCard.getCardNo(),"");
        verify(cardStorageService, times(1)).inUse(newCard.getCardNo());
        assertNotNull(newCard.getCardNo());
        assertNotEquals(userCard.getCardNo(),newCard.getCardNo());
        assertEquals(CardStatus.RETURNED.getCode(), userCard.getState());
        assertEquals(1,userCardDao.update(userCard));
        assertEquals(userCard.getAccountId(),newCard.getAccountId());
        assertEquals(CardStatus.NORMAL.getCode(),newCard.getState());
        assertNotNull(newCard.getCreateTime());
        assertNotNull(newCard.getModifyTime());
        LOGGER.info("返回的新卡:{}", JSON.toJSONString(newCard));
    }

    private CardRequestDto createParams(CardAggregationWrapper wrapper) {
        UserCardDo userCard = wrapper.getUserCard();
        CardRequestDto cardParam = new CardRequestDto();
        cardParam.setAccountId(wrapper.getUserAccount().getAccountId());
        cardParam.setLoginPwd("12345678");
        cardParam.setOpId(userCard.getCreatorId());
        cardParam.setOpName(userCard.getCreator());
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
