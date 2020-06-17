package com.dili.account.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import com.dili.account.entity.UserCardEntity;

/**
 * 用户卡片信息（包括电子卡）
 * @author bob<>
 */
@Mapper
public interface IUserCardDao {
	/**
     * 列表查询
     * @param page
     * @param search
     * @return
     */
	List<UserCardEntity> selectList(UserCardEntity userCard);

    /**
     * 新增
     * @param userCard
     * @return
     */
	int save(UserCardEntity userCard);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	UserCardEntity getById(Long id);

    /**
     * 修改
     * @param userCard
     * @return
     */
	int update(UserCardEntity userCard);

    /**
     * 删除
     * @param id
     * @return
     */
	int batchRemove(Long[] id);
	
	/**
	 * 更新卡状态
	 * @param accountId 卡账号
	 * @param state
	 * @param version
	 * @return
	 */
	int updateState(@Param("accountId") Long accountId, @Param("state") Long state, @Param("version") Integer version);
}
