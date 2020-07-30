package com.dili.account.validator;

import com.dili.account.common.ExceptionMsg;
import com.dili.account.dto.UserAccountCardResponseDto;
import com.dili.account.entity.CardAggregationWrapper;
import com.dili.account.entity.UserAccountDo;
import com.dili.account.entity.UserCardDo;
import com.dili.account.exception.AccountBizException;
import com.dili.account.type.CardStatus;
import com.dili.account.type.DisableState;
import com.dili.ss.constant.ResultCode;

public class AccountValidator {
    public static final int ONLY_RETURNED = 1;
    public static final int ONLY_DISABLED = 2;
    public static final int ALL = 3;
    public static final int NONE = 4;

    /**
     * 解冻 冻结校验标志
     */
    public interface DisabledState {
    }

    /**
     * 满足不同场景的校验需求
     * @author miaoguoxin
     * @date 2020/7/28
     */
    public static void validateAccount(CardAggregationWrapper wrapper,int flag){
        UserCardDo userCard = wrapper.getUserCard();
        UserAccountDo userAccount = wrapper.getUserAccount();
        switch (flag) {
            case ONLY_RETURNED:
                if (CardStatus.RETURNED.getCode() == userCard.getState()) {
                    throw new AccountBizException(ResultCode.DATA_ERROR, ExceptionMsg.CARD_RETURNED.getName());
                }
                break;
            case ONLY_DISABLED:
                if (DisableState.DISABLED.getCode().equals(userAccount.getDisabledState())) {
                    throw new AccountBizException(ResultCode.DATA_ERROR, ExceptionMsg.ACCOUNT_DISABLED.getName());
                }
                break;
            case ALL:
                if (CardStatus.RETURNED.getCode() == userCard.getState()) {
                    throw new AccountBizException(ResultCode.DATA_ERROR, ExceptionMsg.CARD_RETURNED.getName());
                }
                if (DisableState.DISABLED.getCode().equals(userAccount.getDisabledState())) {
                    throw new AccountBizException(ResultCode.DATA_ERROR, ExceptionMsg.ACCOUNT_DISABLED.getName());
                }
                break;
            default:
                break;
        }
    }


}
