package com.dili.account.service;

import com.dili.account.dto.AccountSimpleResponseDto;
import com.dili.account.dto.UserAccountCardQuery;
import com.dili.account.dto.UserAccountCardResponseDto;
import com.dili.account.entity.CardAggregationWrapper;
import com.dili.ss.domain.PageOutput;

import java.util.List;
import java.util.Optional;


/**
 * @description： 账户查询服务
 * 查询单个账户默认都校验非正常状态
 * @author ：WangBo
 * @time ：2020年4月22日下午5:48:22
 */
public interface IAccountQueryService {

    /**
     * 卡片是否存在
     * @author miaoguoxin
     * @date 2020/6/24
     */
    Boolean cardExist(String cardNo);

    /**
     * 查询账户信息（包含余额）
     * @author miaoguoxin
     * @date 2020/7/7
     */
    AccountSimpleResponseDto getByCardNoWithBalance(String cardNo, Long firmId);

    /**
     * 单个查询（对外）
     * @author miaoguoxin
     * @date 2020/7/28
     */
    UserAccountCardResponseDto getSingleForRest(UserAccountCardQuery queryParam, boolean needValidate);


    /**
     * 卡列表条件查询
     * @author miaoguoxin
     * @date 2020/6/19
     */
    List<UserAccountCardResponseDto> getListByConditionForRest(UserAccountCardQuery queryParam);

    /**
     * 卡列表条件查询（带分页count）
     * @author miaoguoxin
     * @date 2020/6/19
     */
    PageOutput<List<UserAccountCardResponseDto>> getPageByConditionForRest(UserAccountCardQuery queryParam);

    /**
     * 查询卡账户、卡的聚合信息
     * @author miaoguoxin
     * @date 2020/6/17
     */
    CardAggregationWrapper getSingle(UserAccountCardQuery queryParam, boolean needValidate);

    /**
     * 解挂卡的时候查询卡账户信息
     * @author miaoguoxin
     * @date 2020/7/24
     */
    CardAggregationWrapper getByAccountIdForUnLostCard(Long accountId);

    /**
     * 用于业务操作的时候查询卡账户信息，
     * 会校验卡账户的状态信息
     * @author miaoguoxin
     * @date 2020/7/30
     */
    CardAggregationWrapper getByAccountIdForGenericOp(Long accountId);

    /**
     *  查询卡账户信息
     * @author miaoguoxin
     * @date 2020/6/29
     */
    Optional<CardAggregationWrapper> getByAccountId(Long accountId);


}
