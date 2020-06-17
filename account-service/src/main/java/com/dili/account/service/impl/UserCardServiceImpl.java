package com.dili.account.service.impl;

import com.dili.account.dao.IUserCardDao;
import com.dili.account.dto.UserAccountDto;
import com.dili.account.dto.UserCardDto;
import com.dili.account.service.IUserAccountService;
import com.dili.account.service.IUserCardService;
import com.dili.account.type.CardStatus;
import com.dili.ss.exception.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 客户卡service实现
 */
@Service
public class UserCardServiceImpl implements IUserCardService {

    @Resource
    private IUserCardDao userCardDao;

    @Resource
    private IUserAccountService userAccountService;
    /**
     * 解挂失卡
     * @param userCardDto
     */
    @Transactional
    @Override
    public void unLost(UserCardDto userCardDto) {
        UserAccountDto userAccountDto = userAccountService.getByAccountId(userCardDto.getAccountId());
        if (userAccountDto == null) {
            throw new BusinessException("", "卡账户不存在");
        }
        if (!Integer.valueOf(CardStatus.LOSS.getCode()).equals(userAccountDto.getCardState())) {
            throw new BusinessException("", "该卡当前未挂失");
        }
        //TODO xuliang 验证密码
        int i = userCardDao.updateState(userCardDto.getAccountId(), CardStatus.NORMAL.getCode(), userAccountDto.getCardVersion());
        if (i != 1) {
            throw new BusinessException("", "数据已变更,请稍后重试");
        }
        //TODO xuliang 发送操作记录  注意异常处理
    }
}
