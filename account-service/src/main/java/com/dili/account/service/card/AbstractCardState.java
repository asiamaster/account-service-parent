package com.dili.account.service.card;

import com.dili.account.dao.IUserCardDao;
import com.dili.account.dto.CardAggregationDto;
import com.dili.account.entity.CardAggregationWrapper;
import com.dili.account.entity.UserCardDo;
import com.dili.account.service.IAccountQueryService;
import com.dili.account.service.state.IState;
import com.dili.ss.util.SpringUtil;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * @Auther: miaoguoxin
 * @Date: 2020/6/16 16:21
 * @Description:
 */
public abstract class AbstractCardState implements IState {

    private IUserCardDao userCardDao;

    private IAccountQueryService accountQueryService;

    public AbstractCardState() {
        this.userCardDao = SpringUtil.getBean(IUserCardDao.class);
        this.accountQueryService = SpringUtil.getBean(IAccountQueryService.class);
    }

    public final CardAggregationWrapper doTransition(Long accountId, Integer targetState) {
        CardAggregationWrapper cardAggregationWrapper = accountQueryService.getByAccountIdWithNotNull(accountId);
        this.validateCanTransition(cardAggregationWrapper);
        UserCardDo userCardDo = new UserCardDo();
        userCardDo.setId(cardAggregationWrapper.getUserCard().getId());
        userCardDo.setState(targetState);
        userCardDo.setModifyTime(LocalDateTime.now());
        userCardDao.update(userCardDo);
        return cardAggregationWrapper;
    }


    /**
     * 校验是否可以进行状态操作，用于回调
     * @param
     * @return
     * @author miaoguoxin
     * @date 2020/6/16
     */
    protected abstract void validateCanTransition(CardAggregationWrapper cardAggregationWrapper);

}
