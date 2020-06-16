package com.dili.account.dao;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import com.dili.account.entity.LoginUserEntity;

/**
 * 用户电子登录账号
 * @author bob<>
 */
@Mapper
public interface ILoginUserDao {
	/**
     * 列表查询
     * @param page
     * @param search
     * @return
     */
	List<LoginUserEntity> selectList(LoginUserEntity loginUser);

    /**
     * 新增
     * @param loginUser
     * @return
     */
	int save(LoginUserEntity loginUser);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	LoginUserEntity getById(Long id);

    /**
     * 修改
     * @param loginUser
     * @return
     */
	int update(LoginUserEntity loginUser);

    /**
     * 删除
     * @param id
     * @return
     */
	int batchRemove(Long[] id);
}
