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
	 * 密码校验,校验失败会锁定卡片状态，所以使用独立事务
	 * @param accountId 账号id
	 * @param password 密码
	 * @return 
	 */
	UserAccountDo checkPassword(Long accountId, String password);
	
	/**
	 * 密码校验,根据参数判断是否需要锁定卡片,(像解锁这种业务的密码校验是不需要锁定卡片的)
	 * @return
	 */
	UserAccountDo checkPassword(Long accountId, String password, boolean isLockCard);
	
	/**
	 * 清除redis中用户的错误次数
	 * @param key
	 */
	public void cleanPwdErrorCount(Long accountId);
	
	/**
	 * 根据帐号ID锁定卡片,当密码错误次数达到时，更改状态操作应该避免被回滚
	 * @param accountId
	 */
	void pwdErrorLock(Long accountId);
}
