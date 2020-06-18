package com.dili.account.controller;

import com.dili.account.dto.UserAccountCardResponseDto;
import com.dili.account.service.IAccountQueryService;
import com.dili.ss.constant.ResultCode;
import com.dili.ss.domain.BaseOutput;
import com.dili.ss.exception.AppException;
import com.dili.ss.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
     * 根据卡号查卡账户信息
     */
    @GetMapping("getOneAccountCard/{cardNo}")
    public BaseOutput<UserAccountCardResponseDto> getOneAccountCard(@PathVariable String cardNo) {
        try {
            if (StringUtils.isBlank(cardNo)) {
                throw new BusinessException(ResultCode.PARAMS_ERROR, "卡号不能为空");
            }
            return BaseOutput.successData(accountQueryService.getByCardNoForRest(cardNo));
        } catch (BusinessException e) {
            return BaseOutput.create(e.getErrorCode(),e.getErrorMsg());
        }
    }
}
