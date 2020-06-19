package com.dili.account.controller;

import cn.hutool.core.util.StrUtil;
import com.dili.account.dto.CardRequestDto;
import com.dili.account.entity.CardAggregationWrapper;
import com.dili.account.exception.AccountBizException;
import com.dili.account.service.ICardManageService;
import com.dili.account.validator.CardValidator;
import com.dili.ss.domain.BaseOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 卡片管理服务，退卡、挂失、解挂、补卡等
 * @author ：WangBo
 * @time ：2020年4月28日下午4:04:46
 */
@RestController
@RequestMapping(value = "/api/card")
public class CardManageController {
    private static Logger LOGGER = LoggerFactory.getLogger(CardManageController.class);

    @Resource
    private ICardManageService cardManageService;

    /**
     * 退卡
     */
    @PostMapping("/returnCard")
    public BaseOutput<Boolean> returnCard(@RequestBody CardRequestDto cardRequest) {
        cardManageService.returnCard(cardRequest);
        return BaseOutput.success();
    }

    /**
     * 解挂卡片
     */
    @PostMapping("/unLostCard")
    public BaseOutput<CardAggregationWrapper> unLostCard(@RequestBody CardRequestDto cardParam) {
        try {
            if (cardParam.getAccountId() == null) {
                return BaseOutput.failure("账户ID为空");
            }
            if (StrUtil.isBlank(cardParam.getLoginPwd())) {
                return BaseOutput.failure("密码为空");
            }
            CardAggregationWrapper wrapper = cardManageService.unLostCard(cardParam);
            return BaseOutput.success().setData(wrapper);
        } catch (AccountBizException e) {
            return BaseOutput.failure(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("unLostCard", e);
            return BaseOutput.failure();
        }
    }

    /**
     *  卡片挂失
     * @author miaoguoxin
     * @date 2020/6/19
     */
    @PostMapping("/reportLossCard")
    public BaseOutput<?> reportLossCard(@RequestBody @Validated({CardValidator.Generic.class})
                                                CardRequestDto cardParam) {
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
                                    @Validated({CardValidator.Generic.class, CardValidator.ChangeCard.class})
                                            CardRequestDto cardParam) {
        cardManageService.changeCard(cardParam);
        return BaseOutput.success();
    }
}
