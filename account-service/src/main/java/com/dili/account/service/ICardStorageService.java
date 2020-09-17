package com.dili.account.service;

import com.dili.account.dto.BatchCardAddStorageDto;
import com.dili.account.dto.CardAddStorageDto;
import com.dili.account.dto.CardRepoQueryParam;
import com.dili.account.entity.CardStorageDo;
import com.dili.ss.domain.PageOutput;

import java.util.List;

/**
 * @description： 卡片入库管理
 * 
 * @author ：WangBo
 * @time ：2020年6月17日下午5:20:23
 */
public interface ICardStorageService {

	/**
	 * 查询列表数据
	 */
	PageOutput<List<CardStorageDo>> listPage(CardRepoQueryParam queryParam);

	/**
	 * 卡片首次入库激活
	 */
	void addCard(CardAddStorageDto cardAddInfo);

	/**
	 * 根据号段批量添加数据,卡号重复时将会抛出唯一索引的异常
	 */
	void batchAddCard(BatchCardAddStorageDto batchCardDto);

	/**
	 * 批量激活，出库激活，只修改状态为未激活的卡
	 */
	void batchActivate(List<String> cardNos, Long firmId);

	/**
	 * 卡片批量删除，根据号段入库ID，如果有非激活状态的卡片则删除失败
	 */
	void delByStorageInId(Long storageInId, Long firmId);

	/**
	 * 使用过后更新为激活,表示可以被开卡
	 */
	CardStorageDo activateCard(String cardNo, Long firmId);

	/**
	 * 更新为使用中，开卡后更新为使用中
	 */
	CardStorageDo inUse(String cardNo, Long firmId);

	/**
	 * 更新为作废
	 */
	void voidCard(String cardNo, String remark, Long firmId);

	/**
	 * 根据卡号修改状态 <br>
	 * <i>只支持修改状态，修改时间为当前时间
	 * 
	 * @param cardStorage
	 * @return
	 */
	int updateByCardNo(CardStorageDo cardStorage);

	/**
	 * 根据卡号查询
	 */
	CardStorageDo getByCardNo(String cardNo, Long firmId);

}
