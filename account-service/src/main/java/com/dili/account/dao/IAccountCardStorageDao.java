package com.dili.account.dao;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import com.dili.account.entity.AccountCardStorageEntity;

/**
 * 卡片仓库，所有新开卡必须来至该表
 * @author bob<>
 */
@Mapper
public interface IAccountCardStorageDao {
	/**
     * 列表查询
     * @param page
     * @param search
     * @return
     */
	List<AccountCardStorageEntity> selectList(AccountCardStorageEntity accountCardStorage);

    /**
     * 新增
     * @param accountCardStorage
     * @return
     */
	int save(AccountCardStorageEntity accountCardStorage);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	AccountCardStorageEntity getById(Long id);

    /**
     * 修改
     * @param accountCardStorage
     * @return
     */
	int update(AccountCardStorageEntity accountCardStorage);

    /**
     * 删除
     * @param id
     * @return
     */
	int batchRemove(Long[] id);
}
