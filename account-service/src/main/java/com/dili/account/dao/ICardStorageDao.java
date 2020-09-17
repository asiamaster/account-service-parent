package com.dili.account.dao;

import java.util.List;

import com.dili.account.dto.BatchActivateCardDto;
import com.dili.account.dto.CardRepoQueryParam;
import com.dili.account.entity.CardStorageDo;

/**
 * 卡片仓库，所有新开卡必须来至该表
 * @author bob<>
 */
public interface ICardStorageDao {
	
	/**
     * 列表查询
     * @param page
     * @param search
     * @return
     */
	List<CardStorageDo> selectList(CardRepoQueryParam cardStorage);
	
	/**
	 * 查询记录数
	 */
	Long selectListCount(CardRepoQueryParam cardStorage);

    /**
     * 新增
     * @param cardStorage
     * @return
     */
	int save(CardStorageDo cardStorage);
	
	
	/**
	 * 批量新增
	 * @param list
	 * @return
	 */
	int batchSave(List<CardStorageDo> list);

	/**
	 * 批量激活
	 * @param updateDto
	 * @return
	 */
	int batchActivate(BatchActivateCardDto updateDto);
	
    /**
     * 根据id查询
     * @param id
     * @return
     */
	CardStorageDo getByCardNo(Long id);

    /**
     * 根据卡号修改数据
     * @param cardStorage
     * @return
     */
	int updateByCardNo(CardStorageDo cardStorage);

	/**
	 * 根据卡号查询
	 */
	CardStorageDo getByCardNo(String cardNo, Long firmId);
	
	/**
	 * 删除库存,只删除状态为“未激活”的数据
	 */
	int del(CardStorageDo cardStorage);
	
}
