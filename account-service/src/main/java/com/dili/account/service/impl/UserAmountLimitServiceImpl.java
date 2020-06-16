package com.dili.account.service.impl;

import org.springframework.stereotype.Service;

/**
 * @description： 
 *          用户账户使用权限服务
 * @author ：WangBo
 * @time ：2020年5月19日下午5:46:43
 */
@Service("userAmountLimitService")
public class UserAmountLimitServiceImpl/* implements IUserAmountLimitService */{

//	@Resource
//	private IUserAmountLimitDao userAmountLimitDao;
//	
//	@Override
//	public List<UserAmountLimitEntity> queryList(UserAmountLimitEntity queryParam) {
//		return userAmountLimitDao.selectList(queryParam);
//	}
//
//	@Override
//	public void save(UserAmountLimitEntity entity) {
//		AssertUtils.notNull(entity.getAccountId(), "账户ID不能为空!");
//		entity.setModifiedTime(LocalDateTime.now());
//		entity.setCreatedTime(LocalDateTime.now());
//		userAmountLimitDao.save(entity);
//	}
//
//	@Override
//	public int updateAmountLimit(UserAmountLimitEntity entity) {
//		entity.setModifiedTime(LocalDateTime.now());
//		return userAmountLimitDao.updateByAccountId(entity);
//	}
}
