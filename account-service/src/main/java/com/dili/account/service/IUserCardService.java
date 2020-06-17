package com.dili.account.service;

import com.dili.account.dto.UserCardDto;

/**
 * 客户卡service接口
 */
public interface IUserCardService {

    /**
     * 卡片解挂失
     * @param userCardDto
     */
    void unLost(UserCardDto userCardDto);
}
