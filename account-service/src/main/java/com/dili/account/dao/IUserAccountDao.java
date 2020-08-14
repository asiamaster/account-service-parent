package com.dili.account.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.dili.account.entity.UserAccountDo;

/**
 * 用户账户信息
 * @author bob<>
 */
public interface IUserAccountDao {
	/**
     * 列表查询
     * @param page
     * @param search
     * @return
     */
	List<UserAccountDo> selectList(UserAccountDo userAccount);

    /**
     * 新增
     * @param userAccount
     * @return
     */
	int save(UserAccountDo userAccount);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	UserAccountDo getById(Long id);

	/**
	 * 根据卡账户Id查询
	 * @param accountId
	 * @return
	 */
	UserAccountDo getByAccountId(Long accountId);

    /**
     * 修改
     * @param userAccount
     * @return
     */
	int update(UserAccountDo userAccount);

    /**
     * 删除
     * @param id
     * @return
     */
	int batchRemove(Long[] id);
	
	/**
	 * 查询副卡账户信息
	 */
	List<UserAccountDo> findSlavesByParent(Long parentAccountId);
	
	/**
     * 更新卡账户禁用状态
     * @param accountId 卡账号
     * @param disabledState  指定状态  比如 启用 禁用
     * @param version 数据版本号
     */
    boolean updateDisabledState(@Param("accountId") Long accountId,
                    @Param("disabledState") Integer disabledState,
                    @Param("version") Integer version);
    
    /**
     * 更新卡账户状态
     * @param accountId 卡账号
     * @param state  指定状态  比如 禁用 注销 锁定
     * @param version 数据版本号
     */
    boolean updateState(@Param("accountId") Long accountId,
                    @Param("state") Integer state,
                    @Param("version") Integer version);
}
