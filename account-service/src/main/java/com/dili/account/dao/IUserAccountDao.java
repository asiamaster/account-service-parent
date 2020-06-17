package com.dili.account.dao;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import com.dili.account.entity.UserAccountDo;

/**
 * 用户账户信息
 * @author bob<>
 */
@Mapper
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
}
