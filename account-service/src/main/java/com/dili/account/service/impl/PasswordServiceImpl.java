package com.dili.account.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.dili.account.common.constant.DictionaryCode;
import com.dili.account.dao.IUserAccountDao;
import com.dili.account.dao.IUserCardDao;
import com.dili.account.dto.CardRequestDto;
import com.dili.account.entity.CardAggregationWrapper;
import com.dili.account.entity.UserAccountDo;
import com.dili.account.entity.UserCardDo;
import com.dili.account.exception.AccountBizException;
import com.dili.account.exception.ErrorCode;
import com.dili.account.rpc.resolver.GenericRpcResolver;
import com.dili.account.service.IAccountQueryService;
import com.dili.account.service.IPasswordService;
import com.dili.account.type.CardStatus;
import com.dili.account.util.AssertUtils;
import com.dili.account.util.PasswordUtils;
import com.dili.ss.constant.ResultCode;
import com.dili.ss.dto.DTOUtils;
import com.dili.ss.redis.service.RedisUtil;
import com.dili.uap.sdk.domain.DataDictionaryValue;
import com.dili.uap.sdk.rpc.DataDictionaryRpc;

import cn.hutool.core.thread.ThreadUtil;

/**
 * @author: xiaosa
 * @date: 2020/4/27
 * @description: 密码相关的操作
 */
@Service
public class PasswordServiceImpl implements IPasswordService {

	/** 密码错误次数在redis中的key */
	final static String PWD_ERROR_KEY = "account:pwd:error:count:";

	/** 默认密码错误最大次数 */
	final static int DEFAULT_PWD_ERROR_COUNT = 99999999;

	public static final Logger log = LoggerFactory.getLogger(PasswordServiceImpl.class);

	@Resource
	private IUserAccountDao userAccountDao;
	@Autowired
	private IAccountQueryService accountQueryService;
	@Autowired
	private RedisUtil redisUtil;
	@Resource
	private IUserCardDao userCardDao;
	@Resource
	private DataDictionaryRpc dataDictionaryRpc;

	@Override
	public void resetLoginPwd(CardRequestDto cardRequestDto) throws Exception {
		CardAggregationWrapper account = accountQueryService.getByAccountIdForUnLostCard(cardRequestDto.getAccountId());
		if (!cardRequestDto.getLoginPwd().equals(cardRequestDto.getSecondLoginPwd())) {
			throw new AccountBizException(ResultCode.DATA_ERROR, "两次密码输入不一致，请重新输入");
		}
		UserAccountDo userAccount = new UserAccountDo();
		userAccount.setFirmId(cardRequestDto.getFirmId());
		userAccount.setId(account.getUserAccount().getId());
		userAccount.setLoginPwd(
				PasswordUtils.encrypt(cardRequestDto.getLoginPwd(), account.getUserAccount().getSecretKey()));
		int update = userAccountDao.update(userAccount);
		if (update == 0) {
			throw new AccountBizException(ResultCode.DATA_ERROR, "密码重置失败");
		}
		// 清除redis中的次数
		cleanPwdErrorCount(userAccount.getAccountId());
		
		// 修改卡状态
		UserCardDo userCard = userCardDao.getByAccountId(account.getUserAccount().getId());
		userCardDao.updateStateById(userCard.getId(), CardStatus.NORMAL.getCode(),
				userCard.getVersion());
		
	}

	@Override
	public void modifyLoginPwd(CardRequestDto cardRequestDto) throws Exception {
		UserAccountDo userAccount = checkPassword(cardRequestDto.getAccountId(), cardRequestDto.getOldLoginPwd());
		if (!cardRequestDto.getLoginPwd().equals(cardRequestDto.getSecondLoginPwd())) {
			throw new AccountBizException(ResultCode.DATA_ERROR, "两次输入密码不匹配");
		}
		UserAccountDo updateParam = new UserAccountDo();
		updateParam.setFirmId(cardRequestDto.getFirmId());
		updateParam.setId(userAccount.getId());
		updateParam.setLoginPwd(PasswordUtils.encrypt(cardRequestDto.getLoginPwd(), userAccount.getSecretKey()));
		int update = userAccountDao.update(updateParam);
		if (update == 0) {
			throw new AccountBizException(ResultCode.DATA_ERROR, "密码修改失败");
		}
	}

	@Override
	public UserAccountDo checkPassword(Long accountId, String password) {
		return checkPassword(accountId, password, true);
	}
	@Override
	public UserAccountDo checkPassword(Long accountId, String password, boolean isLockCard) {
		if (accountId == null || StringUtils.isBlank(password)) {
			throw new AccountBizException(ResultCode.DATA_ERROR, "密码校验参数错误");
		}

		UserAccountDo userAccountDo = userAccountDao.getByAccountId(accountId);

		AssertUtils.notNull(userAccountDo, "卡信息不存在");

		String encryptPwd = PasswordUtils.encrypt(password, userAccountDo.getSecretKey());
		if (!userAccountDo.getLoginPwd().equals(encryptPwd)) {
			log.info("loginPwd[{}],SecretKey[{}],encryptPwd[{}]", userAccountDo.getLoginPwd(),
					userAccountDo.getSecretKey(), encryptPwd);
			if(!isLockCard) {
				throw new AccountBizException(ErrorCode.PASSWORD_ERROR, "密码输入有误");
			}
			// 没有配置密码错误次数，直接返回错误信息
			Integer maxErrorCount = getLockedFirmSetting(userAccountDo.getFirmId());
			if (maxErrorCount == null) {
				throw new AccountBizException(ErrorCode.PASSWORD_ERROR, "密码输入有误");
			}

			// 判断是否需要锁定卡片
			String key = PWD_ERROR_KEY + accountId;
			int pwdErrorCount = addPwdErrorCount(key);
			if (pwdErrorCount >= maxErrorCount) {
				this.pwdErrorLock(accountId);
				throw new AccountBizException(ErrorCode.PASSWORD_ERROR, "密码错误次数已达上限，账户已被锁定");
			}
			throw new AccountBizException(ErrorCode.PASSWORD_ERROR, "密码输入有误");
		} else {
			cleanPwdErrorCount(accountId);
		}

		return userAccountDo;
	}
	
	
	public void pwdErrorLock(Long accountId) {
		log.info("&************************");
		ThreadUtil.execute(new Runnable() {
			@Override
			public void run() {
				UserCardDo userCard = userCardDao.getByAccountId(accountId);
				int updateRet = userCardDao.updateStateById(userCard.getId(), CardStatus.LOCKED.getCode(),
						userCard.getVersion());
				if (updateRet < 1) {
					log.warn("{}密码错误次数达到限制，但更改卡状态为锁定失败!", userCard.getCardNo());
				}
			}
		});
	}

	/**
	 * 获取市场配置的密码错误次数，未配置则返回99999999
	 * 
	 * @param firmId
	 * @return
	 */
	private Integer getLockedFirmSetting(Long firmId) {

		DataDictionaryValue ddv = DTOUtils.newInstance(DataDictionaryValue.class);
		ddv.setDdCode(DictionaryCode.PWD_ERROR_MAX_COUNT);
		ddv.setFirmId(firmId);
		List<DataDictionaryValue> list = GenericRpcResolver.resolver(dataDictionaryRpc.listDataDictionaryValue(ddv),
				"DataDictionaryRpc");
		if (CollectionUtils.isEmpty(list)) {
			log.warn("当前市场[{}]未配置密码错误次数，默认99999999", firmId);
			return DEFAULT_PWD_ERROR_COUNT;
		}
		String value = list.get(0).getCode();
		if (list.size() > 1) {
			log.warn("当前市场[{}]配置了多个密码错误次数，取第一个[{}]", firmId, value);
		}
		return Integer.parseInt(value);
	}

	/**
	 * 检查当前账户的密码错误次数
	 * 
	 * @param key
	 * @param accountId
	 */
	public int addPwdErrorCount(String key) {
		Integer count = redisUtil.get(key, Integer.class);
		if (count == null) {
			count = 1;
			// 24小时内
			redisUtil.set(key, count, 86400L);
		} else {
			++count;
			redisUtil.set(key, count);
		}
		return count;
	}

	/**
	 * 清除密码错误次数
	 * 
	 * @param key
	 * @param accountId
	 */
	public void cleanPwdErrorCount(Long accountId) {
		redisUtil.remove(PWD_ERROR_KEY + accountId);
	}
}
