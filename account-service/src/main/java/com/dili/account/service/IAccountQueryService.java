package com.dili.account.service;

import com.dili.account.dto.UserAccountCardQuery;
import com.dili.account.dto.UserAccountCardResponseDto;
import com.dili.account.entity.CardAggregationWrapper;
import com.dili.ss.domain.BaseOutput;
import com.dili.ss.domain.PageOutput;

import java.util.List;


/**
 * @description： 账户查询服务
 *
 * @author ：WangBo
 * @time ：2020年4月22日下午5:48:22
 */
public interface IAccountQueryService {

    /**
    * 卡片是否存在
    * @param
    * @return
    * @author miaoguoxin
    * @date 2020/6/24
    */
    Boolean cardExist(String cardNo);
    /**
     * 根据卡号查询唯一数据(不包含退卡状态)
     * @return
     */
    UserAccountCardResponseDto getByCardNoForRest(String cardNo);

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
     * 查询卡账户、卡、客户的聚合信息(不包含退卡状态)
     * @author miaoguoxin
     * @date 2020/6/17
     */
    CardAggregationWrapper getByAccountIdWithNotNull(Long accountId);

}
