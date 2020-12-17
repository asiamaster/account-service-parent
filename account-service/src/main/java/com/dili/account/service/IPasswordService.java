package com.dili.account.service;

import com.dili.account.dto.CardRequestDto;
import com.dili.account.entity.UserAccountDo;

/**
 * @description： 重置密码修改密码等
 *
 * @author ：WangBo
 * @time ：2020年4月26日下午5:52:54
 */
public interface IPasswordService {

	/**
	 * @author: xiaosa
	 * @date: 2020/4/27
	 * @param: passwordDto
	 * @return: success/fail
	 * @description：修改登陆密码
	 */
	void modifyLoginPwd(CardRequestDto cardRequestDto) throws Exception;

	/**
	 * @author: xiaosa
	 * @date: 2020/4/27
	 * @param: passwordDto
	 * @return: success/fail
	 * @description：重置登陆密码
	 */
	void resetLoginPwd(CardRequestDto cardRequestDto) throws Exception;
	
	/**
	 * 密码校验
	 * @param accountId 账号id
	 * @param password 密码
	 * @return 
	 */
	UserAccountDo checkPassword(Long accountId, String password);
	
	/**
	 * 清除redis中用户的错误次数
	 * @param key
	 */
	public void cleanPwdErrorCount(Long accountId);
}
