package com.dili.account.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dili.account.dto.CardRequestDto;
import com.dili.account.service.ICardManageService;
import com.dili.ss.domain.BaseOutput;
import com.dili.ss.exception.BusinessException;

import cn.hutool.core.util.StrUtil;

/**
 * 卡片管理服务，退卡、挂失、解挂、补卡等
 * @author ：WangBo
 * @time ：2020年4月28日下午4:04:46
 */
@RestController
@RequestMapping(value = "/api/account")
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
    public BaseOutput<?> unLostCard(@RequestBody CardRequestDto cardParam) {
        try {
            if (cardParam.getAccountId() == null) {
                return BaseOutput.failure("账户ID为空");
            }
            if (StrUtil.isBlank(cardParam.getLoginPwd())) {
                return BaseOutput.failure("密码为空");
            }
            cardManageService.unLostCard(cardParam);
            return BaseOutput.success();
        } catch (BusinessException e) {
            return BaseOutput.failure(e.getErrorMsg());
        } catch (Exception e) {
            LOGGER.error("unLostCard", e);
            return BaseOutput.failure();
        }
    }
}
