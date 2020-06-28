package com.dili.account.service;

import com.dili.account.dto.OpenCardDto;
import com.dili.account.dto.OpenCardResponseDto;

/**
 * @description： 
 *          用户开卡service接口
 * @author ：WangBo
 * @time ：2020年6月19日下午5:54:10
 */
public interface IOpenCardService {


	/**
	 * 开主卡
	 * @param openCardInfo
	 * @return
	 * @throws InterruptedException 
	 * @throws Exception 
	 */
	public OpenCardResponseDto openMasterCard(OpenCardDto openCardInfo);

	/**
	 * 开副卡
	 * @param openCardInfo
	 * @return
	 */
	public OpenCardResponseDto openSlaveCard(OpenCardDto openCardInfo);

}
