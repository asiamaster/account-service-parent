package com.dili.account.service;

import com.dili.account.dto.AccountSimpleResponseDto;
import com.dili.account.dto.AccountWithAssociationResponseDto;
import com.dili.account.dto.UserAccountCardQuery;
import com.dili.account.dto.UserAccountCardResponseDto;
import com.dili.account.entity.CardAggregationWrapper;
import com.dili.account.validator.AccountValidator;
import com.dili.ss.domain.PageOutput;

import java.util.List;
import java.util.Optional;


/**
 * @description： 账户查询服务
 * 提供给对外的接口中，查询单个账户默认都排除账户禁用状态和卡退还状态
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
    AccountSimpleResponseDto getByCardNoWithBalance(String cardNo);

    /**
     * 单个查询（对外）
     * @author miaoguoxin
     * @date 2020/7/28
     */
    UserAccountCardResponseDto getSingleForRest(UserAccountCardQuery queryParam);

    /**
     * 单个查询（对外）
     * @author miaoguoxin
     * @param validateFlag 标记校验规则 {@link AccountValidator#validateAccount(UserAccountCardResponseDto, int)}
     * @date 2020/7/28
     */
    UserAccountCardResponseDto getSingleForRest(UserAccountCardQuery queryParam, int validateFlag);

    /**
     *  根据卡号查询关联的所有卡(包含禁用和退还)
     * @author miaoguoxin
     * @date 2020/6/28
     */
    AccountWithAssociationResponseDto getSingleWithAssociationForRest(UserAccountCardQuery queryParam);

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
    CardAggregationWrapper getSingle(UserAccountCardQuery queryParam);

    /**
     * 卡操作的时候查询卡账户信息(不包含退卡状态)
     * @author miaoguoxin
     * @date 2020/7/24
     */
    CardAggregationWrapper getByAccountIdForCardOp(Long accountId);

    /**
     *  查询卡账户、卡的聚合信息(不包含退卡状态),允许为null
     * @author miaoguoxin
     * @date 2020/6/29
     */
    Optional<CardAggregationWrapper> getByAccountId(Long accountId);


}
