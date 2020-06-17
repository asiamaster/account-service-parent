package com.dili.account.api;

import cn.hutool.core.util.StrUtil;
import com.dili.account.dto.UserAccountDto;
import com.dili.account.service.IUserAccountService;
import com.dili.ss.domain.BaseOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户账户相关api
 */
@RestController
@RequestMapping(value = "/api/userAccount")
public class UserAccountApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountApi.class);

    @Resource
    private IUserAccountService userAccountService;
    /**
     * 根据卡号查询账户信息 附带卡信息
     * @param cardNo
     * @return
     */
    @RequestMapping(value = "/getByCardNo")
    public BaseOutput<UserAccountDto> getByCardNo(String cardNo) {
        try {
            if (StrUtil.isBlank(cardNo)) {
                return BaseOutput.failure("卡号为空");
            }
            return BaseOutput.success().setData(userAccountService.getByCardNo(cardNo));
        } catch (Exception e) {
            LOGGER.error("getByCardNo", e);
            return BaseOutput.failure();
        }
    }

    /**
     * 根据账号ID查询账户信息 附带卡信息
     * @param accountId
     * @return
     */
    @RequestMapping(value = "/getByAccountId")
    public BaseOutput<UserAccountDto> getByAccountId(Long accountId) {
        try {
            if (accountId == null) {
                return BaseOutput.failure("账户ID为空");
            }
            return BaseOutput.success().setData(userAccountService.getByAccountId(accountId));
        } catch (Exception e) {
            LOGGER.error("getByAccountId", e);
            return BaseOutput.failure();
        }
    }
}
