package com.dili.account.service;

import java.util.List;

import com.dili.account.dto.UserAccountCardDto;
import com.dili.account.dto.UserAccountCardQuery;


/**
 * @description： 账户查询服务
 *
 * @author ：WangBo
 * @time ：2020年4月22日下午5:48:22
 */
public interface IAccountQueryService {

	/**
	 * 查询账户数据列表
	 * @return
	 */
	public List<UserAccountCardDto> listAccount(UserAccountCardQuery queryParam);
	
	
	/**
	 * 查询指定唯一账户数据
	 * @return
	 */
	public UserAccountCardDto getOnly(String cardNo,Long accountId);

	

}
