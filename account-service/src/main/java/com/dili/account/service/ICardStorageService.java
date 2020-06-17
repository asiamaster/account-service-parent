package com.dili.account.service;

import com.dili.account.common.Page;
import com.dili.account.dto.CardAddRepositoryDto;
import com.dili.account.dto.CardRepoQueryParam;
import com.dili.account.entity.CardStorageDo;

/**
 * @description： 卡片仓库管理
 * 
 * @author ：WangBo
 * @time ：2020年4月27日上午11:27:20
 */
public interface ICardStorageService {

	/**
	 * 查询列表数据
	 */
	public Page<CardStorageDo> listPage(CardRepoQueryParam queryParam);
	
	/**
	 * 卡片首次入库激活
	 */
	public void addCard(CardAddRepositoryDto cardAddInfo);

	/**
	 * 使用过后更新为激活,表示可以被开卡
	 */
	public CardStorageDo activateCard(String cardNo);

	/**
	 * 更新为使用中，开卡后更新为使用中
	 */
	public CardStorageDo inUse(String cardNo);

	/**
	 * 更新为作废
	 */
	public void voidCard(String cardNo, String remark);

}
