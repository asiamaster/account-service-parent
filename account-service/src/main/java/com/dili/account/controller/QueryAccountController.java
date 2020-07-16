package com.dili.account.controller;

import com.alibaba.fastjson.JSON;
import com.dili.account.dto.AccountWithAssociationResponseDto;
import com.dili.account.dto.UserAccountCardQuery;
import com.dili.account.dto.UserAccountCardResponseDto;
import com.dili.account.exception.AccountBizException;
import com.dili.account.service.IAccountQueryService;
import com.dili.account.validator.ConstantValidator;
import com.dili.ss.constant.ResultCode;
import com.dili.ss.domain.BaseOutput;
import com.dili.ss.domain.PageOutput;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 账户信息查询
 * @author ：WangBo
 * @time ：2020年4月28日下午3:30:27
 */
@RestController
@RequestMapping(value = "api/account")
public class QueryAccountController {
    private static final Logger LOGGER = LoggerFactory.getLogger(QueryAccountController.class);

    @Autowired
    private IAccountQueryService accountQueryService;

    /**
     * 判断卡号是否存在
     * @param
     * @return
     * @author miaoguoxin
     * @date 2020/6/24
     */
    @GetMapping("existCard/{cardNo}")
    public BaseOutput<Boolean> existCard(@PathVariable String cardNo) {
        return BaseOutput.successData(accountQueryService.cardExist(cardNo));
    }

    /**
     * 根据卡号查卡账户信息
     */
    @GetMapping("getOneAccountCard/{cardNo}")
    public BaseOutput<UserAccountCardResponseDto> getOneAccountCard(@PathVariable String cardNo) {
        if (StringUtils.isBlank(cardNo)) {
            throw new AccountBizException(ResultCode.PARAMS_ERROR, "卡号不能为空");
        }
        return BaseOutput.successData(accountQueryService.getByCardNoForRest(cardNo));
    }

    /**
     *  根据卡号查询包含关联卡的信息
     * @author miaoguoxin
     * @date 2020/6/28
     */
    @GetMapping("getAssociation/{cardNo}")
    public BaseOutput<AccountWithAssociationResponseDto> getAssociationAccountCard(@PathVariable String cardNo) {
        if (StringUtils.isBlank(cardNo)) {
            throw new AccountBizException(ResultCode.PARAMS_ERROR, "卡号不能为空");
        }
        return BaseOutput.successData(accountQueryService.getByCardNoWithAssociationForRest(cardNo,1));
    }

    /**
     *  根据accountId查询包含关联卡的信息
     * @author miaoguoxin
     * @date 2020/6/28
     */
    @GetMapping("getAssociationByAccountId/{accountId}")
    public BaseOutput<AccountWithAssociationResponseDto> getAssociationAccountCard(@PathVariable Long accountId) {
        if (accountId == null || accountId <= 0) {
            throw new AccountBizException(ResultCode.PARAMS_ERROR, "账户id不能为空");
        }
        return BaseOutput.successData(accountQueryService.getByAccountIdWithAssociationForRest(accountId));
    }

    /**
     * 分页条件查询（有total）
     * @param
     * @return
     * @author miaoguoxin
     * @date 2020/6/19
     */
    @PostMapping("getPage")
    public PageOutput<List<UserAccountCardResponseDto>> getPage(@RequestBody @Validated(ConstantValidator.Page.class)
                                                                        UserAccountCardQuery param) {
        return accountQueryService.getPageByConditionForRest(param);
    }

    /**
     * 分页条件查询（没有total）
     * @param
     * @return
     * @author miaoguoxin
     * @date 2020/6/19
     */
    @PostMapping("/getList")
    public BaseOutput<List<UserAccountCardResponseDto>> getList(@RequestBody UserAccountCardQuery param) {
        LOGGER.info("卡账户查询参数:{}", param);
        return BaseOutput.successData(accountQueryService.getListByConditionForRest(param));
    }

}
