package com.dili.account.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 密码相关操作的
 * @author:      xiaosa
 * @date:        2020/4/27
 * @version:     电子结算重构 4.4.0
 */
@RestController
@RequestMapping("password")
public class PasswordManageController {
//
//    @Autowired
//    private IPasswordService passwordService;
//
//    private final static Logger logger = LoggerFactory.getLogger(PasswordManageController.class);
//
//    /**
//     * 修改登陆密码
//     * @author:      xiaosa
//     * @date:        2020/4/27
//     * @param:       passwordDto
//     * @return:      success/fail
//     *
//     */
//    @RequestMapping(value = "modifyLoginPwd", method = RequestMethod.POST)
//    public Message<?> modifyLoginPassword(@Validated(ValidatedGroup.ModifyLoginPwdGroup.class) @RequestParam PasswordManageResponseDto passwordDto) throws Exception {
//        logger.info("修改登陆密码");
//
//        passwordService.modifyLoginPwd(passwordDto);
//        return Message.success();
//    }
//
//    /**
//     * 重置登陆密码
//     * @author:      xiaosa
//     * @date:        2020/4/27
//     * @param:       passwordDto
//     * @return:      success/fail
//     */
//    @RequestMapping(value = "resetLoginPwd", method = RequestMethod.POST)
//    public Message<?> resetLoginPassword(@RequestParam PasswordManageResponseDto passwordDto) throws Exception {
//        logger.info("重置登陆密码");
//
//        AssertUtils.notEmpty(passwordDto.getLoginPwd(), "登陆密码不能为空!");
//        passwordService.resetLoginPwd(passwordDto);
//        return Message.success();
//    }
//
//    /**
//     * 修改交易密码
//     * @author:      xiaosa
//     * @date:        2020/4/27
//     * @param:       passwordDto
//     * @return:      success/fail
//     */
//    @RequestMapping(value = "modifyTradePwd", method = RequestMethod.POST)
//    public Message<?> modifyTradePwd(@Validated(ValidatedGroup.ModifyTradePwdGroup.class) @RequestParam PasswordManageResponseDto passwordDto) throws Exception {
//        logger.info("修改交易密码");
//
//        passwordService.modifyTradePwd(passwordDto);
//        return Message.success();
//    }
//
//    /**
//     * 重置交易密码
//     * @author:      xiaosa
//     * @date:        2020/4/27
//     * @param:       passwordDto
//     * @return:      success/fail
//     */
//    @RequestMapping(value = "resetTradePwd", method = RequestMethod.POST)
//    public Message<?> resetTradePwd(@Validated @RequestParam PasswordManageResponseDto passwordDto) throws Exception {
//        logger.info("重置交易密码");
//
//        passwordService.resetTradePwd(passwordDto);
//        return Message.success();
//    }
}
