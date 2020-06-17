package com.dili.account.service;

import com.dili.account.dto.CardAggregationDto;
import com.dili.account.dto.UserAccountCardQuery;

import java.util.List;


/**
 * @description： 账户查询服务
 *
 * @author ：WangBo
 * @time ：2020年4月22日下午5:48:22
 */
public interface IAccountQueryService {

    /**
     * 查询账户数据列表
     * @return
     */
    List<CardAggregationDto> listAccount(UserAccountCardQuery queryParam);


    /**
     * 查询指定唯一账户数据
     * @return
     */
    CardAggregationDto getOnly(String cardNo, Long accountId);
    /**
    * 查询卡账户、卡、客户的聚合信息
    * @author miaoguoxin
    * @date 2020/6/17
    */
    CardAggregationDto getByAccountIdWithNotNull(Long accountId);
    /**
     * 查询卡账户、卡、客户的聚合信息
     * @author miaoguoxin
     * @date 2020/6/17
     */
    CardAggregationDto getByAccountIdWithNotNull(Long accountId, boolean needCustomerInfo);
}
