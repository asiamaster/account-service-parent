package com.dili.account.dao;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import com.dili.account.entity.AccountUserCardEntity;

/**
 * 用户卡片信息（包括电子卡）
 * @author bob<>
 */
@Mapper
public interface IAccountUserCardDao {
	/**
     * 列表查询
     * @param page
     * @param search
     * @return
     */
	List<AccountUserCardEntity> selectList(AccountUserCardEntity accountUserCard);

    /**
     * 新增
     * @param accountUserCard
     * @return
     */
	int save(AccountUserCardEntity accountUserCard);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	AccountUserCardEntity getById(Long id);

    /**
     * 修改
     * @param accountUserCard
     * @return
     */
	int update(AccountUserCardEntity accountUserCard);

    /**
     * 删除
     * @param id
     * @return
     */
	int batchRemove(Long[] id);
}
