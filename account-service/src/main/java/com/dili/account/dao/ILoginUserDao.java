package com.dili.account.dao;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import com.dili.account.entity.LoginUserDo;

/**
 * 用户电子登录账号
 * @author bob<>
 */
public interface ILoginUserDao {
	/**
     * 列表查询
     * @param page
     * @param search
     * @return
     */
	List<LoginUserDo> selectList(LoginUserDo loginUser);

    /**
     * 新增
     * @param loginUser
     * @return
     */
	int save(LoginUserDo loginUser);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	LoginUserDo getById(Long id);

    /**
     * 修改
     * @param loginUser
     * @return
     */
	int update(LoginUserDo loginUser);

    /**
     * 删除
     * @param id
     * @return
     */
	int batchRemove(Long[] id);
}
