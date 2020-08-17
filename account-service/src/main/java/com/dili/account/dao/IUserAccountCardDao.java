package com.dili.account.dao;

import java.util.List;

import com.dili.account.dto.UserAccountCardQuery;
import com.dili.account.entity.CardAggregationWrapper;

/**
 * @Auther: miaoguoxin
 * @Date: 2020/6/19 12:22
 * @Description: 用于account_user_account和account_user_card连表操作
 */
public interface IUserAccountCardDao {

    /**
    * 条件查询卡账户列表（包含退卡状态）
    * @param
    * @return
    * @author miaoguoxin
    * @date 2020/6/19
    */
    List<CardAggregationWrapper> getListByCondition(UserAccountCardQuery queryParam);
}
