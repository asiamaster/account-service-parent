package com.dili.account.service.impl;

import org.springframework.stereotype.Service;

/**
 * @description： 
 *          用户账户使用权限服务
 * @author ：WangBo
 * @time ：2020年5月19日下午5:46:43
 */
@Service("usePermissionService")
public class UsePermissionServiceImpl/* implements IUsePermissionService */{

//	@Resource
//	private IUsePermissionDao usePermissionDao;
//	
//	@Override
//	public List<UsePermissionEntity> queryList(UsePermissionEntity usePermission) {
//		return usePermissionDao.selectList(usePermission);
//	}
//
//	@Override
//	public void addPermission(UsePermissionEntity usePermission) {
//		UsePermissionEntity queryParam=new UsePermissionEntity();
//		queryParam.setName(usePermission.getName());
//		UsePermissionEntity namePermission = usePermissionDao.getOnlyOne(queryParam);
//		if(namePermission == null) {
//			usePermissionDao.save(usePermission);
//		}else {
//			UsePermissionEntity updateParam=new UsePermissionEntity();
//			updateParam.setId(namePermission.getId());
//			updateParam.setDelStatus(DelStatus.NORMAL.getCode());
//			updateParam.setModifiedTime(LocalDateTime.now());
//			usePermissionDao.update(updateParam);
//		}
//		
//	}
//
//	@Override
//	public void delPermission(Long id) {
//		UsePermissionEntity updateParam=new UsePermissionEntity();
//		updateParam.setId(id);
//		updateParam.setDelStatus(DelStatus.DELETED.getCode());
//		updateParam.setModifiedTime(LocalDateTime.now());
//		usePermissionDao.update(updateParam);
//	}

}
