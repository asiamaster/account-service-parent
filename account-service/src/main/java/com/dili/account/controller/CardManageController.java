package com.dili.account.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.dili.account.common.constant.JsonExcludeFilter;
import com.dili.account.dto.CardRequestDto;
import com.dili.account.service.ICardManageService;
import com.dili.account.validator.CardValidator;
import com.dili.ss.domain.BaseOutput;

import cn.hutool.core.util.StrUtil;

/**
 * 卡片管理服务，退卡、挂失、解挂、补卡等
 * @author ：WangBo
 * @time ：2020年4月28日下午4:04:46
 */
@RestController
@RequestMapping(value = "/api/card")
public class CardManageController {

	private static final Logger log = LoggerFactory.getLogger(CardManageController.class);

    @Resource
    private ICardManageService cardManageService;

    /**
     * 退卡
     */
    @PostMapping("/returnCard")
    public BaseOutput<Boolean> returnCard(@RequestBody @Validated(value = {CardValidator.Generic.class}) CardRequestDto cardRequest) {
		log.info("退卡*****" + JSONObject.toJSONString(cardRequest, JsonExcludeFilter.PWD_FILTER));
    	cardManageService.returnCard(cardRequest);
        return BaseOutput.success();
    }

    /**
     * 解挂卡片
     */
    @PostMapping("/unLostCard")
    public BaseOutput<?> unLostCard(@RequestBody CardRequestDto cardParam) {
    	log.info("解挂卡片*****" + JSONObject.toJSONString(cardParam, JsonExcludeFilter.PWD_FILTER));
        if (cardParam.getAccountId() == null) {
            return BaseOutput.failure("账户ID为空");
        }
        if (StrUtil.isBlank(cardParam.getLoginPwd())) {
            return BaseOutput.failure("密码为空");
        }
        cardManageService.unLostCard(cardParam);
        return BaseOutput.success();
    }

    /**
     * 卡片挂失
     * @author miaoguoxin
     * @date 2020/6/19
     */
    @PostMapping("/reportLossCard")
    public BaseOutput<?> reportLossCard(@RequestBody @Validated({CardValidator.Generic.class})
                                                CardRequestDto cardParam) {
    	log.info("卡片挂失*****" + JSONObject.toJSONString(cardParam, JsonExcludeFilter.PWD_FILTER));
        cardManageService.reportLoss(cardParam);
        return BaseOutput.success();
    }

    /**
     * 换卡
     * @author miaoguoxin
     * @date 2020/6/19
     */
    @PostMapping("/changeCard")
    public BaseOutput<?> changeCard(@RequestBody
                                    @Validated({CardValidator.ChangeCard.class})
                                            CardRequestDto cardParam) {
    	log.info("换卡*****" + JSONObject.toJSONString(cardParam, JsonExcludeFilter.PWD_FILTER));
        cardManageService.changeCard(cardParam);
        return BaseOutput.success();
    }


    /**
     * 解锁卡片
     */
    @PostMapping("/unLockCard")
    public BaseOutput<?> unLockCard(@RequestBody CardRequestDto cardParam) {
    	log.info("解锁卡片*****" + JSONObject.toJSONString(cardParam, JsonExcludeFilter.PWD_FILTER));
        if (cardParam.getAccountId() == null) {
            return BaseOutput.failure("账户ID为空");
        }
        if (StrUtil.isBlank(cardParam.getLoginPwd())) {
            return BaseOutput.failure("密码为空");
        }
        cardManageService.unLockCard(cardParam);
        return BaseOutput.success();
    }
}
