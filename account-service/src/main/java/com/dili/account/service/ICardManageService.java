package com.dili.account.service;

import com.dili.account.dto.CardManageParamDto;
import com.dili.account.dto.CardRequestDto;

/**
 * @description： 卡片管理服务，包括退卡，换卡，补卡，挂失，解挂
 *
 * @author ：WangBo
 * @time ：2020年4月26日下午5:59:10
 */
public interface ICardManageService {

	/**
	 * 退卡
	 */
	void returnCard(CardRequestDto cardParam);

//	/**
//	 * 换卡
//	 */
//	public void changeCard(CardManageParamDto cardParam);
//
//	/**
//	 * 补卡
//	 */
//	public void reissueCard(CardManageParamDto cardParam);
//
	/**
	* 挂失
	* @author miaoguoxin
	* @date 2020/6/17
	*/
	 void reportLoss(CardRequestDto cardParam);
	 /**
	 * 换卡
	 * @author miaoguoxin
	 * @date 2020/6/17
	 */
	 void changeCard(CardRequestDto cardParam);
//
//	/**
//	 * 解挂卡片
//	 */
//	public void unLostCard(Long accountId, String loginPwd);
//
//	/**
//	 * 根据卡号锁定卡
//	 */
//	public void lockCard(String cardNo);
//
//	/**
//	 * 根据账户ID锁定卡
//	 */
//	public void lockCard(Long accountId);
//
//	/**
//	 * 根据账户ID解锁卡片
//	 */
//	public void unLock(Long accountId, String loginPwd);
}
