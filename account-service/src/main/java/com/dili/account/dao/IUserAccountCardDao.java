package com.dili.account.dao;

import java.util.List;

import com.dili.account.dto.UserAccountCardQuery;
import com.dili.account.entity.CardAggregationWrapper;
import com.dili.account.entity.CustomerCardWrapper;

/**
 * @Auther: miaoguoxin
 * @Date: 2020/6/19 12:22
 * @Description: 用于account_user_account和account_user_card连表操作
 */
public interface IUserAccountCardDao {

    /**
    * 条件查询卡账户列表（包含退卡状态）
    * @author miaoguoxin
    * @date 2020/6/19
    */
    List<CardAggregationWrapper> getListByCondition(UserAccountCardQuery queryParam);

    /**
    * 获取所有已开卡客户id
    * @author miaoguoxin
    * @date 2020/11/2
    */
    List<Long> getAllCustomerIds(UserAccountCardQuery queryParam);

    /**
     * 根据客户id获取卡号
     * @author miaoguoxin
     * @date 2020/11/2
     */
    List<CustomerCardWrapper> getCarNosByCustomerIds(UserAccountCardQuery queryParam);
}
