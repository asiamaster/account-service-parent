package com.dili.account.dao;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import com.dili.account.entity.AccountUserAccountEntity;

/**
 * 用户账户信息
 * @author bob<>
 */
@Mapper
public interface IAccountUserAccountDao {
	/**
     * 列表查询
     * @param page
     * @param search
     * @return
     */
	List<AccountUserAccountEntity> selectList(AccountUserAccountEntity accountUserAccount);

    /**
     * 新增
     * @param accountUserAccount
     * @return
     */
	int save(AccountUserAccountEntity accountUserAccount);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	AccountUserAccountEntity getById(Long id);

    /**
     * 修改
     * @param accountUserAccount
     * @return
     */
	int update(AccountUserAccountEntity accountUserAccount);

    /**
     * 删除
     * @param id
     * @return
     */
	int batchRemove(Long[] id);
}
