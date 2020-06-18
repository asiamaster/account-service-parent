package com.dili.account.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;
import com.dili.account.entity.UserCardDo;

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
	List<UserCardDo> selectList(UserCardDo userCard);

    /**
     * 新增
     * @param userCard
     * @return
     */
	int save(UserCardDo userCard);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	UserCardDo getById(Long id);
	/**
	* 根据账户id查询
	* @author miaoguoxin
	* @date 2020/6/18
	*/
	UserCardDo getByAccountId(Long accountId);

	/**
	* 根据卡号查询
	* @author miaoguoxin
	* @date 2020/6/18
	*/
	UserCardDo getByCardNo(String cardNo);
    /**
     * 修改
     * @param userCard
     * @return
     */
	int update(UserCardDo userCard);

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
	int updateState(@Param("accountId") Long accountId, @Param("state") Integer state, @Param("version") Integer version);

	/**
	 * 通过账号查询卡信息
	 * @param accountId 卡账号
	 */
	UserCardDo findCardByAccountId(@Param("accountId") Long accountId);
}
