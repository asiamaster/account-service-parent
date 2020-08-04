package com.dili.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dili.account.dto.CardRequestDto;
import com.dili.account.service.IPasswordService;
import com.dili.account.validator.CardValidator;
import com.dili.ss.domain.BaseOutput;

/**
 * 密码相关操作
 */
@RestController
@RequestMapping("/api/card")
public class PasswordManageController {

    @Autowired
    private IPasswordService passwordService;

    /**
     * 重置登陆密码
     */
    @RequestMapping(value = "/resetLoginPwd", method = RequestMethod.POST)
    public BaseOutput<Boolean> resetLoginPassword(@RequestBody @Validated(value = {CardValidator.Generic.class, CardValidator.ResetPassword.class}) CardRequestDto cardRequest) throws Exception {
        passwordService.resetLoginPwd(cardRequest);
        return BaseOutput.success();
    }
    
    /**
     * 校验密码
     */
    @RequestMapping(value = "/checkPassword", method = RequestMethod.POST)
    public BaseOutput<Boolean> checkPassword(@RequestBody @Validated(value = {CardValidator.Generic.class}) CardRequestDto cardRequest) throws Exception {
        passwordService.checkPassword(cardRequest.getAccountId(), cardRequest.getLoginPwd());
        return BaseOutput.success();
    }

}
