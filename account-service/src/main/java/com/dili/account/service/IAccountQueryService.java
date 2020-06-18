package com.dili.account.service;

import com.dili.account.dto.UserAccountCardQuery;
import com.dili.account.dto.UserAccountCardResponseDto;
import com.dili.account.entity.CardAggregationWrapper;

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
    List<UserAccountCardResponseDto> listAccount(UserAccountCardQuery queryParam);


    /**
     * 查询指定唯一账户数据
     * @return
     */
    UserAccountCardResponseDto getByCardNoForRest(String cardNo);


    /**
     * 查询卡账户、卡、客户的聚合信息
     * @author miaoguoxin
     * @date 2020/6/17
     */
    CardAggregationWrapper getByAccountIdWithNotNull(Long accountId);

    /**
     * 查询卡账户、卡聚合信息
     * @author miaoguoxin
     * @date 2020/6/17
     */
    CardAggregationWrapper getByAccountIdWithNotNull(Long accountId, boolean needCustomerInfo);
}
