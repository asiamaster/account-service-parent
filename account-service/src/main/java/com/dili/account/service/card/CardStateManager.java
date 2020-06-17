package com.dili.account.service.card;

import com.dili.account.dto.CardAggregationDto;
import com.dili.account.dto.CardRequestDto;
import com.dili.account.entity.CardAggregationWrapper;
import com.dili.account.entity.UserCardDo;
import com.dili.account.service.IPasswordService;
import com.dili.account.type.CardStatus;
import com.dili.ss.constant.ResultCode;
import com.dili.ss.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Auther: miaoguoxin
 * @Date: 2020/6/16 21:37
 * @Description:
 */
@Component
public class CardStateManager {
    @Autowired
    private IPasswordService passwordService;


    public void doReportLoss(CardRequestDto cardParam) {
        AbstractCardState state = this.getDefaultState(cardParam);
        state.doTransition(cardParam.getAccountId(), CardStatus.LOSS.getCode());
    }

    public CardAggregationWrapper doChange(CardRequestDto cardParam){
        AbstractCardState state = this.getDefaultState(cardParam);
        return state.doTransition(cardParam.getAccountId(), CardStatus.RETURNED.getCode());
    }

    private AbstractCardState getDefaultState(CardRequestDto cardParam) {
        return new AbstractCardState() {
            @Override
            protected void validateCanTransition(CardAggregationWrapper cardAggregationDto) {
                if (cardAggregationDto.getUserCard().getState() != CardStatus.NORMAL.getCode()) {
                    throw new BusinessException(ResultCode.DATA_ERROR, "该卡为非正常状态，不能进行此操作");
                }
                //passwordService.checkLoginPwd(cardParam.getAccountId(), cardParam.getLoginPwd());
            }
        };
    }
}
