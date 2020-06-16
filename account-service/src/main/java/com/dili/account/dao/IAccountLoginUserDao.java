package com.dili.account.dao;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import com.dili.account.entity.AccountLoginUserEntity;

/**
 * 用户电子登录账号
 * @author bob<>
 */
@Mapper
public interface IAccountLoginUserDao {
	/**
     * 列表查询
     * @param page
     * @param search
     * @return
     */
	List<AccountLoginUserEntity> selectList(AccountLoginUserEntity accountLoginUser);

    /**
     * 新增
     * @param accountLoginUser
     * @return
     */
	int save(AccountLoginUserEntity accountLoginUser);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	AccountLoginUserEntity getById(Long id);

    /**
     * 修改
     * @param accountLoginUser
     * @return
     */
	int update(AccountLoginUserEntity accountLoginUser);

    /**
     * 删除
     * @param id
     * @return
     */
	int batchRemove(Long[] id);
}
