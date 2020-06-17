package com.dili.account.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dili.account.dao.IUserAccountDao;
import com.dili.account.dto.CardRequestDto;
import com.dili.account.service.IPasswordService;

/**
 * @author: xiaosa
 * @date: 2020/4/27
 * @description: 密码相关的操作
 */
@Service
public class PasswordServiceImpl implements IPasswordService{

	private static final Logger log = LoggerFactory.getLogger(PasswordServiceImpl.class);

	@Resource
	private IUserAccountDao userAccountDao;

	@Override
	public void modifyLoginPwd(CardRequestDto cardRequestDto) throws Exception {
		
	}

	@Override
	public void resetLoginPwd(CardRequestDto cardRequestDto) throws Exception {
		
		
	}
	
//	/**
//	 * @author: xiaosa
//	 * @date: 2020/4/27
//	 * @param: passwordDto
//	 * @return:
//	 * @description：修改交易密码
//	 */
//	@Override
//	public void modifyTradePwd(PasswordManageResponseDto passwordDto) throws Exception {
//		UserAccountEntity userAccountDoInfo = userAccountDao.getById(passwordDto.getAccountId());
//		if (userAccountDoInfo == null) {
//			throw new AppException(100000, "该用户不存在");
//		}
//		String inputOldTradePwd = PasswordUtils.encrypt(passwordDto.getOldTradePwd(), Constant.TRADE_PWD_SECRETKEY);
//		if (!inputOldTradePwd.equals(userAccountDoInfo.getTradePwd())) {
//			throw new AppException(100000, "原支付密码不匹配，请重新输入");
//		}
//		userAccountDoInfo.setTradePwd(PasswordUtils.encrypt(passwordDto.getTradePwd(), Constant.TRADE_PWD_SECRETKEY));
//		userAccountDao.update(userAccountDoInfo);
//	}
//
//	/**
//	 * @author: xiaosa
//	 * @date: 2020/4/27
//	 * @param: passwordDto
//	 * @return:
//	 * @description：重置交易密码
//	 */
//	@Override
//	public void resetTradePwd(PasswordManageResponseDto passwordDto) throws Exception {
//		// TODO 应该没有该方法
//	}
//
//	@Override
//	public void checkLoginPwd(Long accountId, String loginPwd) {
//		UserAccountCardQuery accountQuery = new UserAccountCardQuery();
//		accountQuery.setAccountId(accountId);
//		UserAccountCardDto userAccount = userAccountCardDao.getOnly(accountQuery);
//		if (userAccount == null) {
//			throw new AppException("该用户{}不存在!", accountId);
//		}
////		if (userAccount.getStatus() == CardStatus.LOCKED.getCode()) {
////			throw new AppException("该用户{}输入密码超过最大错误次数已被锁定，请先解锁!", accountId);
////		}
//		String encryptPwd = PasswordUtils.encrypt(loginPwd, Constant.LOGIN_PWD_SECRETKEY);
//		if (encryptPwd.equals(userAccount.getLoginPwd())) {
//			cleanPwdErrorTime(userAccount.getMarketId(), userAccount.getAccountId());
//		} else {
//			try {
//				String key = getPwdErrorTimeRedisKey(userAccount.getMarketId(), userAccount.getAccountId());
//				String errTimeStr = redisSystemService.get(key);
//				int errTime = StringUtils.isBlank(errTimeStr) ? 0 : Integer.parseInt(errTimeStr);
//				errTime++;
//				Long expiredSeconds = (getTodayMaxTime().getTime() - new Date().getTime()) / 1000;
//				redisSystemService.setAndExpire(key, errTime + "", expiredSeconds.intValue());
//				if (errTime >= 3) {
//					// 锁定卡状态
//					cardManageService.lockCard(userAccount.getAccountId());
//					throw new AppException("该用户{}输入密码超过最大错误次数，卡已被锁定!", accountId);
//				}
//			} catch (RedisSystemException e) {
//				log.error("密码输入错误次数校验连接redis出错", e);
//				throw new AppException("redis获取数据出错!");
//			}
//			throw new AppException("{}密码输入错误{}", accountId, loginPwd);
//		}
//	}
//
//	@Override
//	public void checkTradePwd(Long accountId, String tradePwd) {
//
//	}
//
//	@Override
//	public void cleanPwdErrorTime(String marketId, Long accountId) {
//		try {
//			redisSystemService.remove(getPwdErrorTimeRedisKey(marketId, accountId));
//		} catch (RedisSystemException e) {
//			String msg = "{}清除密码输入错误次数失败!";
//			log.error(msg, accountId);
//		}
//	}
//
//	/**
//	 * 获取密码输入错误次数
//	 * 
//	 * @param marketId
//	 * @param accountId
//	 */
//	private String getPwdErrorTimeRedisKey(String marketId, Long accountId) {
//		return PWD_ERROR_TIME_REDISKEY + marketId + "_" + accountId;
//	}
//
//	/**
//	 * 获取当天最大的时间
//	 * 
//	 * @return
//	 */
//	private static Date getTodayMaxTime() {
//		Calendar c = Calendar.getInstance();
//		c.set(Calendar.HOUR_OF_DAY, 23);
//		c.set(Calendar.MINUTE, 59);
//		c.set(Calendar.SECOND, 59);
//		return c.getTime();
//	}
}
