package com.dili.account.api;

import cn.hutool.core.util.StrUtil;
import com.dili.account.dto.UserCardDto;
import com.dili.account.service.IUserCardService;
import com.dili.ss.domain.BaseOutput;
import com.dili.ss.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 客户卡相关api
 */
@RestController
@RequestMapping(value = "/api/userCard")
public class UserCardApi {
    public static final Logger LOGGER = LoggerFactory.getLogger(UserCardApi.class);

    @Resource
    private IUserCardService userCardService;

    /**
     * 卡解挂失
     * @param userCardDto
     * @return
     */
    @RequestMapping(value = "/unLost", method = RequestMethod.POST)
    public BaseOutput<?> unLost(@RequestBody UserCardDto userCardDto) {
        try {
            if (userCardDto.getAccountId() == null) {
                return BaseOutput.failure("账户ID为空");
            }
            if (StrUtil.isBlank(userCardDto.getLoginPwd())) {
                return BaseOutput.failure("密码为空");
            }
            if (userCardDto.getOperatorId() == null) {
                return BaseOutput.failure("操作人ID为空");
            }
            if (StrUtil.isBlank(userCardDto.getOperatorName())) {
                return BaseOutput.failure("操作人姓名为空");
            }
            userCardService.unLost(userCardDto);
            return BaseOutput.success();
        } catch (BusinessException e) {
            return BaseOutput.failure(e.getErrorMsg());
        } catch (Exception e) {
            return BaseOutput.failure();
        }
    }
}
