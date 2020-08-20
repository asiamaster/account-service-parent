package com.dili.account.service;

import com.dili.account.dto.CardRequestDto;
import com.dili.account.entity.UserCardDo;

/**
 * @description： 卡片管理服务，包括退卡，换卡，补卡，挂失，解挂
 *
 * @author ：WangBo
 * @time ：2020年4月26日下午5:59:10
 */
public interface ICardManageService {

    /**
     * 退卡
     */
    void returnCard(CardRequestDto cardParam);

    /**
     * 挂失
     * @author miaoguoxin
     * @date 2020/6/17
     */
    void reportLoss(CardRequestDto cardParam);

    /**
     * 换卡
     * @author miaoguoxin
     * @date 2020/6/17
     */
    UserCardDo changeCard(CardRequestDto cardParam);

    /**
     * 解挂卡片
     */
    void unLostCard(CardRequestDto cardParam);

    /**
     * 解锁卡片
     * @param cardParam
     */
    void unLockCard(CardRequestDto cardParam);
}
