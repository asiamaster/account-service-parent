package com.dili.account.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dili.account.dto.AccountSimpleResponseDto;
import com.dili.account.dto.UserAccountCardQuery;
import com.dili.account.dto.UserAccountCardResponseDto;
import com.dili.account.dto.UserAccountSingleQueryDto;
import com.dili.account.service.IAccountQueryService;
import com.dili.account.type.CardLastState;
import com.dili.account.type.CardStatus;
import com.dili.account.type.DisableState;
import com.dili.account.util.AssertUtils;
import com.dili.account.validator.AccountValidator;
import com.dili.account.validator.ConstantValidator;
import com.dili.ss.domain.BaseOutput;
import com.dili.ss.domain.PageOutput;
import com.google.common.collect.Lists;

/**
 * 账户信息查询
 * @author ：WangBo
 * @time ：2020年4月28日下午3:30:27
 */
@RestController
@RequestMapping(value = "api/account")
public class AccountQueryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountQueryController.class);

    @Autowired
   private IAccountQueryService accountQueryService;


    /**
     * 根据accountId查卡账户信息
     * @author miaoguoxin
     * @date 2020/7/27
     */
    @GetMapping("/getOneByAccountId/{accountId}")
    public BaseOutput<UserAccountCardResponseDto> getOneByAccountId(@PathVariable Long accountId) {
        AssertUtils.notNull(accountId, "账户id不能为空");
        UserAccountCardQuery queryParam = new UserAccountCardQuery();
        queryParam.setAccountIds(Lists.newArrayList(accountId));
        return BaseOutput.successData(accountQueryService.getSingleForRest(queryParam, true));
    }

    /**
     * 查询单个
     * @author miaoguoxin
     * @date 2020/7/28
     */
    @PostMapping("/getSingle")
    public BaseOutput<UserAccountCardResponseDto> getSingle(@RequestBody @Validated(AccountValidator.SingleQuery.class)
                                                                    UserAccountSingleQueryDto param) {
        LOGGER.info("查询单个getSingle请求参数:{}", JSON.toJSONString(param));
        UserAccountCardQuery query = this.convertQueryParams(param);
        return BaseOutput.successData(accountQueryService.getSingleForRest(query, true));
    }

    /**
     * 查询单个（不校验非正常状态：卡退还、账户禁用）
     * @author miaoguoxin
     * @date 2020/7/30
     */
    @PostMapping("/getSingleWithoutValidate")
    public BaseOutput<UserAccountCardResponseDto> getSingleWithoutValidate(@RequestBody @Validated(AccountValidator.SingleQuery.class)
                                                                                   UserAccountSingleQueryDto param) {
        LOGGER.info("查询单个getSingleWithoutValidate请求参数:{}", JSON.toJSONString(param));
        UserAccountCardQuery query = this.convertQueryParams(param);
        return BaseOutput.successData(accountQueryService.getSingleForRest(query, false));
    }

    /**
     * 分页条件查询（有total）
     * @author miaoguoxin
     * @date 2020/6/19
     */
    @PostMapping("/getPage")
    public PageOutput<List<UserAccountCardResponseDto>> getPage(@RequestBody @Validated(ConstantValidator.Page.class)
                                                                        UserAccountCardQuery param) {
        LOGGER.info("分页条件查询getPage请求参数:{}", JSON.toJSONString(param));
        AssertUtils.notNull(param.getFirmId(),"市场id不能为空");
        //换卡后，老卡不再显示
        param.setLast(CardLastState.YES.getCode());
        return accountQueryService.getPageByConditionForRest(param);
    }

    /**
     * 条件查询（没有total）
     * @author miaoguoxin
     * @date 2020/6/19
     */
    @PostMapping("/getList")
    public BaseOutput<List<UserAccountCardResponseDto>> getList(@RequestBody UserAccountCardQuery param) {
        LOGGER.info("getList请求参数:{}", JSON.toJSONString(param));
        AssertUtils.notNull(param.getFirmId(),"市场id不能为空");
        return BaseOutput.successData(accountQueryService.getListByConditionForRest(param));
    }

    /**
    *  条件查询所有（只排除掉了退卡）
    * @author miaoguoxin
    * @date 2020/9/25
    */
    @PostMapping("/getAllList")
    @ResponseBody
    public BaseOutput<List<UserAccountCardResponseDto>> getListV2(@RequestBody UserAccountCardQuery param) {
        LOGGER.info("条件查询卡账户列表*****{}", JSONObject.toJSONString(param));
        AssertUtils.notNull(param.getFirmId(), "市场id不能为空");
        param.setExcludeUnusualState(0);
        param.setCardStates(Lists.newArrayList(
                CardStatus.NORMAL.getCode(),
                CardStatus.LOSS.getCode(),
                CardStatus.LOCKED.getCode()
        ));
        param.setDisableState(DisableState.ENABLED.getCode());
        List<UserAccountCardResponseDto> list = accountQueryService.getListByConditionForRest(param);
        return BaseOutput.successData(list);
    }

    /**
    *  获取市场所有已开卡客户id
    * @author miaoguoxin
    * @date 2020/11/2
    */
    @PostMapping("/getAllCustomerIds")
    @ResponseBody
    public BaseOutput<List<Long>> getAllCustomerIds(@RequestBody UserAccountCardQuery param){
        AssertUtils.notNull(param.getFirmId(), "市场id不能为空");
        param.setCardStates(Lists.newArrayList(
                CardStatus.NORMAL.getCode(),
                CardStatus.LOSS.getCode(),
                CardStatus.LOCKED.getCode()
        ));
        param.setDisableState(DisableState.ENABLED.getCode());
        return BaseOutput.successData(accountQueryService.getAllCustomerIds(param));
    }

    /**
     *  根据客户id分组卡号
     * @author miaoguoxin
     * @date 2020/11/2
     */
    @PostMapping("/getCardNosGroupByCustomerIds")
    @ResponseBody
    public BaseOutput<Map<String, List<String>>> getCardNosGroupByCustomerIds(@RequestBody UserAccountCardQuery param) {
        List<Long> customerIds = param.getCustomerIds();
        AssertUtils.notNull(param.getFirmId(), "市场id不能为空");
        AssertUtils.isTrue(customerIds != null && customerIds.size() > 0, "请求参数不能为空");
        param.setCardStates(Lists.newArrayList(
                CardStatus.NORMAL.getCode(),
                CardStatus.LOSS.getCode(),
                CardStatus.LOCKED.getCode()
        ));
        param.setDisableState(DisableState.ENABLED.getCode());
        return BaseOutput.successData(accountQueryService.getCardNosGroupByCustomerIds(param));
    }

    /**
     * 账户信息，包含余额
     * @author miaoguoxin
     * @date 2020/7/7
     */
    @GetMapping("/simpleInfo")
    public BaseOutput<AccountSimpleResponseDto> getInfoByCardNo(String cardNo, Long firmId) {
        LOGGER.info("simpleInfo请求参数:{}->>>>{}", cardNo,firmId);
        AssertUtils.notEmpty(cardNo, "卡号不能为空");
        AccountSimpleResponseDto dto = accountQueryService.getByCardNoWithBalance(cardNo, firmId);
        return BaseOutput.successData(dto);
    }


    /**
     * @author miaoguoxin
     * @date 2020/8/12
     */
    private UserAccountCardQuery convertQueryParams(UserAccountSingleQueryDto param) {
        UserAccountCardQuery query = new UserAccountCardQuery();
        if (param.getAccountId() != null) {
            query.setAccountPkId(param.getAccountPkId());
        }
        if (param.getCardPkId() != null) {
            query.setCardPkId(param.getCardPkId());
        }
        if (StringUtils.isNoneBlank(param.getCardNo())) {
            query.setCardNos(Lists.newArrayList(param.getCardNo()));
        }
        if (param.getAccountId() != null) {
            query.setAccountIds(Lists.newArrayList(param.getAccountId()));
        }
        query.setFirmId(param.getFirmId());
        return query;
    }
}
