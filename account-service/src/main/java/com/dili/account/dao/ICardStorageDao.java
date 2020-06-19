package com.dili.account.dao;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;

import com.dili.account.dto.CardRepoQueryParam;
import com.dili.account.entity.CardStorageDo;
import com.dili.account.entity.UserCardDo;

/**
 * 卡片仓库，所有新开卡必须来至该表
 * @author bob<>
 */
@Mapper
public interface ICardStorageDao {
	
	/**
	 * 分页查询
	 * 
	 * @param page
	 * @param search
	 * @return
	 */
	List<UserCardDo> selectForPage(CardRepoQueryParam queryParam);
	/**
     * 列表查询
     * @param page
     * @param search
     * @return
     */
	List<CardStorageDo> selectList(CardRepoQueryParam cardStorage);

    /**
     * 新增
     * @param cardStorage
     * @return
     */
	int save(CardStorageDo cardStorage);

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
	CardStorageDo getByCardNo(String cardNo);

}
