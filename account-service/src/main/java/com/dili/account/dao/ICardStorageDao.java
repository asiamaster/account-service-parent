package com.dili.account.dao;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import com.dili.account.entity.CardStorageEntity;

/**
 * 卡片仓库，所有新开卡必须来至该表
 * @author bob<>
 */
@Mapper
public interface ICardStorageDao {
	/**
     * 列表查询
     * @param page
     * @param search
     * @return
     */
	List<CardStorageEntity> selectList(CardStorageEntity cardStorage);

    /**
     * 新增
     * @param cardStorage
     * @return
     */
	int save(CardStorageEntity cardStorage);

    /**
     * 根据id查询
     * @param id
     * @return
     */
	CardStorageEntity getById(Long id);

    /**
     * 修改
     * @param cardStorage
     * @return
     */
	int update(CardStorageEntity cardStorage);

    /**
     * 删除
     * @param id
     * @return
     */
	int batchRemove(Long[] id);
}
