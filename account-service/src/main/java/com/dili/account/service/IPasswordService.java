package com.dili.account.service;

import com.dili.account.dto.PasswordManageResponseDto;

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
	void modifyLoginPwd(PasswordManageResponseDto passwordDto) throws Exception;

	/**
	 * @author: xiaosa
	 * @date: 2020/4/27
	 * @param: passwordDto
	 * @return: success/fail
	 * @description：重置登陆密码
	 */
	void resetLoginPwd(PasswordManageResponseDto passwordDto) throws Exception;

	/**
	 * @author: xiaosa
	 * @date: 2020/4/27
	 * @param: passwordDto
	 * @return: success/fail
	 * @description：修改交易密码
	 */
	void modifyTradePwd(PasswordManageResponseDto passwordDto) throws Exception;

	/**
	 * @author: xiaosa
	 * @date: 2020/4/27
	 * @param: passwordDto
	 * @return: success/fail
	 * @description：重置交易密码
	 */
	void resetTradePwd(PasswordManageResponseDto passwordDto) throws Exception;

	/**
	 * 校验登录密码(明文)，如果校验失败则记录失败次数，如果超出指定次数则锁定该账户
	 *
	 * @param accountId 卡账户ID
	 * @param loginPwd  登录密码(明文)
	 */
	void checkLoginPwd(Long accountId, String loginPwd);

	/**
	 * 校验登录密码(明文)，如果校验失败则记录失败次数，如果超出指定次数则锁定该账户
	 *
	 * @param accountId 卡账户ID
	 * @param tradePwd  交易密码明文
	 */
	void checkTradePwd(Long accountId, String tradePwd);

	/**
	 * 清除密码错误次数
	 * @param marketId
	 * @param accountId
	 * @return
	 */
	void cleanPwdErrorTime(String marketId,Long accountId);
}
