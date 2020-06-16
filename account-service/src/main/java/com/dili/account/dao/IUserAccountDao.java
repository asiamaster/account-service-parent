package com.dili.account.dao;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import com.dili.account.entity.UserAccountEntity;

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
	List<UserAccountEntity> selectList(UserAccountEntity userAccount);

    /**
     * 新增
     * @param userAccount
     * @return
     */
	int save(UserAccountEntity userAccount);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	UserAccountEntity getById(Long id);

    /**
     * 修改
     * @param userAccount
     * @return
     */
	int update(UserAccountEntity userAccount);

    /**
     * 删除
     * @param id
     * @return
     */
	int batchRemove(Long[] id);
}
