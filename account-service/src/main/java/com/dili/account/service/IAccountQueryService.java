package com.dili.account.service;

import com.dili.account.dto.AccountSimpleResponseDto;
import com.dili.account.dto.AccountWithAssociationResponseDto;
import com.dili.account.dto.UserAccountCardQuery;
import com.dili.account.dto.UserAccountCardResponseDto;
import com.dili.account.entity.CardAggregationWrapper;
import com.dili.ss.domain.PageOutput;

import java.util.List;
import java.util.Optional;


/**
 * @description： 账户查询服务
 *
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
     * 根据卡号查询唯一数据(不包含退卡状态、不包含禁用状态)
     * @return
     */
    UserAccountCardResponseDto getByCardNoForRest(String cardNo);

    /**
     *  根据id查询唯一数据(不包含退卡状态、不包含禁用状态)
     * @author miaoguoxin
     * @date 2020/6/30
     */
    UserAccountCardResponseDto getByAccountIdForRest(Long accountId);

    /**
     *  根据卡号查询关联的所有卡(不包含禁用状态)
     * @author miaoguoxin
     * @param needReturn 是否需要退换状态的卡信息
     * @date 2020/6/28
     */
    AccountWithAssociationResponseDto getByCardNoWithAssociationForRest(String cardNo, Integer needReturn);

    /**
     *  根据accountId查询关联的所有卡(不包含退卡状态、不包含禁用状态)
     * @author miaoguoxin
     * @date 2020/6/30
     */
    AccountWithAssociationResponseDto getByAccountIdWithAssociationForRest(Long accountId);

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
     * 查询卡账户、卡的聚合信息(不包含退卡状态)
     * @author miaoguoxin
     * @date 2020/6/17
     */
    CardAggregationWrapper getByAccountIdWithNotNull(Long accountId);

    /**
     *  查询卡账户、卡的聚合信息(不包含退卡状态),允许为null
     * @author miaoguoxin
     * @date 2020/6/29
     */
    Optional<CardAggregationWrapper> getByAccountId(Long accountId);

    /**
     * 查询账户信息（包含余额）
     * @author miaoguoxin
     * @date 2020/7/7
     */
    @Deprecated
    AccountSimpleResponseDto getByCardNoWithBalance(String cardNo);
}
