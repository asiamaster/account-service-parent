package com.dili.account.service;

import com.dili.account.dto.OpenCardDto;
import com.dili.account.dto.OpenCardResponseDto;

/**
 * @description： 用户开卡service接口
 *
 * @author ：WangBo
 * @time ：2020年4月22日下午5:48:22
 */
public interface IOpenCardService {


	/**
	 * 开主卡
	 * @param openCardInfo
	 * @return
	 */
	public OpenCardResponseDto openMasterCard(OpenCardDto openCardInfo);

	/**
	 * 开副卡
	 * @param openCardInfo
	 * @return
	 */
	public OpenCardResponseDto openSlaveCard(OpenCardDto openCardInfo);

}
