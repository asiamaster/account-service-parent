package com.dili.account.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dili.account.common.BizNoServiceType;
import com.dili.account.dao.IUserAccountDao;
import com.dili.account.dao.IUserCardDao;
import com.dili.account.dto.OpenCardDto;
import com.dili.account.dto.OpenCardResponseDto;
import com.dili.account.dto.UserAccountCardQuery;
import com.dili.account.dto.UserAccountCardResponseDto;
import com.dili.account.entity.CardAggregationWrapper;
import com.dili.account.entity.CardStorageDo;
import com.dili.account.entity.UserAccountDo;
import com.dili.account.entity.UserCardDo;
import com.dili.account.exception.BizExceptionProxy;
import com.dili.account.rpc.resolver.PayRpcResolver;
import com.dili.account.rpc.resolver.UidRpcResovler;
import com.dili.account.service.IAccountQueryService;
import com.dili.account.service.ICardStorageService;
import com.dili.account.service.IOpenCardService;
import com.dili.account.type.AccountStatus;
import com.dili.account.type.AccountType;
import com.dili.account.type.AccountUsageType;
import com.dili.account.type.CardCategory;
import com.dili.account.type.CardStatus;
import com.dili.account.type.CardType;
import com.dili.account.type.CustomerSyncModifyHoldinfo;
import com.dili.account.type.CustomerType;
import com.dili.account.type.DisableState;
import com.dili.account.type.UsePermissionType;
import com.dili.account.type.YesNoType;
import com.dili.account.util.PasswordUtils;
import com.google.common.collect.Lists;

/**
 * @description： 开卡service实现
 *
 * @author ：WangBo
 * @time ：2020年6月19日下午5:54:23
 */
@Service("openCardService")
public class OpenCardServiceImpl implements IOpenCardService {

	private static final Logger log = LoggerFactory.getLogger(OpenCardServiceImpl.class);

	@Resource
	private ICardStorageService cardStorageService;
	@Resource
	private IAccountQueryService accountQueryService;
	@Resource
	private UidRpcResovler uidRpcResovler;
	@Resource
	private PayRpcResolver payRpcResolver;
	@Resource
	private IUserAccountDao userAccountDao;
	@Resource
	private IUserCardDao userCardDao;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public OpenCardResponseDto openMasterCard(OpenCardDto openCardInfo) {
		// 判断卡状态是否异常
		if (accountQueryService.cardExist(openCardInfo.getCardNo())) {
			throw BizExceptionProxy.exception("该卡{}已被{}使用，不能开卡!", openCardInfo.getCardNo());
		}
		// 判断卡类型，并将卡片改为使用中
		CardStorageDo cardStorageDo = cardStorageService.inUse(openCardInfo.getCardNo(), openCardInfo.getFirmId());
		if (cardStorageDo.getType() != CardType.MASTER.getCode()) {
			throw BizExceptionProxy.exception("该卡{}不是主卡，操作失败!", openCardInfo.getCardNo());
		}

		// 保存账户信息
//		String accountIdStr = uidRpcResovler.bizNumberRetry(BizNoServiceType.ACCOUNT_ID, 3);
//		Long accountId = Long.parseLong(accountIdStr);
		// 使用支付账户ID，为以后合并做准备
		Long accountId = openCardInfo.getFundAccountId();
		UserAccountDo userAccount = buildUserAccount(openCardInfo, accountId, openCardInfo.getFundAccountId());
		userAccountDao.save(userAccount);

		// 保存卡片信息
		UserCardDo userCard = buildUserCard(openCardInfo, userAccount.getAccountId(), CardType.MASTER);
		userCardDao.save(userCard);

		// 返回数据
		OpenCardResponseDto response = new OpenCardResponseDto();
		response.setAccountId(userAccount.getAccountId());
		response.setFundAccountId(openCardInfo.getFundAccountId());
		return response;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public OpenCardResponseDto openSlaveCard(OpenCardDto openCardInfo) {
		// 判断卡状态是否异常
		if (accountQueryService.cardExist(openCardInfo.getCardNo())) {
			throw BizExceptionProxy.exception("该卡{}已被{}使用，不能开卡!", openCardInfo.getCardNo());
		}

		// 判断卡类型，并将卡片改为使用中
		CardStorageDo cardStorageDo = cardStorageService.inUse(openCardInfo.getCardNo(), openCardInfo.getFirmId());
		if (cardStorageDo.getType() != CardType.SLAVE.getCode()) {
			throw BizExceptionProxy.exception("该卡{}不是副卡，操作失败!", openCardInfo.getCardNo());
		}

		// 获取主卡用户信息
		Optional<CardAggregationWrapper> parentAccount = accountQueryService
				.getByAccountId(openCardInfo.getParentAccountId());
		if (parentAccount.isEmpty()) {
			throw BizExceptionProxy.exception("{}主卡账户{}不存在!", openCardInfo.getCardNo(),
					openCardInfo.getParentAccountId());
		}

		Long parentAccountId = parentAccount.get().getUserAccount().getParentAccountId();
		if (parentAccountId != null && parentAccountId != 0) {
			throw BizExceptionProxy.exception("请刷正确的主卡!{}", openCardInfo.getParentAccountId());
		}

		// 构建账户信息
//		String accountIdStr = uidRpcResovler.bizNumber(BizNoServiceType.ACCOUNT_ID);
//		log.info("编号服务获取账户ID*****{}", accountIdStr);
//		Long accountId = Long.parseLong(accountIdStr);
		// 使用支付账户ID，为以后合并做准备
		Long accountId = openCardInfo.getFundAccountId();
		UserAccountDo userAccount = buildUserAccount(openCardInfo, accountId, openCardInfo.getFundAccountId());
		userAccountDao.save(userAccount);

		// 保存卡片信息
		UserCardDo userCard = buildUserCard(openCardInfo, accountId, CardType.SLAVE);
		userCardDao.save(userCard);

		// 返回数据
		OpenCardResponseDto response = new OpenCardResponseDto();
		response.setAccountId(userAccount.getAccountId());
		response.setFundAccountId(openCardInfo.getFundAccountId());
		return response;
	}

	/**
	 * 构建卡账户数据
	 *
	 * @param openCardInfo
	 * @param accountId     业务主键
	 * @param fundAccountId 资金账号ID
	 * @return
	 */
	private UserAccountDo buildUserAccount(OpenCardDto openCardInfo, Long accountId, Long fundAccountId) {
		UserAccountDo userAccount = new UserAccountDo();
		userAccount.setAccountId(accountId);
		userAccount.setParentAccountId(openCardInfo.getParentAccountId());
		userAccount.setCustomerId(openCardInfo.getCustomerId());
		userAccount.setCustomerCertificateNumber(openCardInfo.getCustomerCertificateNumber());
		userAccount.setCustomerCertificateType(openCardInfo.getCustomerCredentialType());
		userAccount.setCustomerName(openCardInfo.getCustomerName());
		userAccount.setCustomerCode(openCardInfo.getCustomerCode());
		userAccount.setCustomerCharacterType(openCardInfo.getCustomerCharacterType());
		userAccount.setCustomerContactsPhone(openCardInfo.getCustomerContactsPhone());
		userAccount.setFundAccountId(fundAccountId);
		userAccount.setCardExist(YesNoType.YES.getCode());
		userAccount.setSecretKey(PasswordUtils.generateSecretKey());
		userAccount.setLoginPwd(PasswordUtils.encrypt(openCardInfo.getLoginPwd(), userAccount.getSecretKey()));
		userAccount.setPwdChanged(YesNoType.NO.getCode());
		userAccount.setState(AccountStatus.NORMAL.getType());
		userAccount.setSource(openCardInfo.getCreateSource());
		userAccount.setDisabledState(DisableState.ENABLED.getCode());
		userAccount.setFirmId(openCardInfo.getFirmId());
		userAccount.setFirmName(openCardInfo.getFirmName());
		userAccount.setVersion(1);
		LocalDateTime now = LocalDateTime.now();
		userAccount.setCreateTime(now);
		userAccount.setModifyTime(now);
		userAccount.setCreatorId(openCardInfo.getCreatorId());
		userAccount.setCreator(openCardInfo.getCreator());
		userAccount.setHoldName(openCardInfo.getHoldName());
		userAccount.setHoldContactsPhone(openCardInfo.getHoldContactsPhone());
		userAccount.setHoldCertificateNumber(openCardInfo.getHoldCertificateNumber());
		userAccount.setCustomerSyncModifyHoldinfo(openCardInfo.getCustomerSyncModifyHoldinfo());
		setAccountPermissions(userAccount);
		return userAccount;
	}

	/**
	 * 构建资金账户数据
	 *
	 * @param openCardInfo
	 * @param accountId     业务主键
	 * @param fundAccountId 资金账号ID
	 * @return
	 */
//	private FundAccountDto buildFundAccount(OpenCardDto openCardInfo) {
//		FundAccountDto fundAccount = new FundAccountDto();
//		fundAccount.setCustomerId(openCardInfo.getCustomerId());
//		fundAccount.setType(CustomerOrgType.getPayCode(openCardInfo.getCustomerOrganizationType()));
//		fundAccount.setType(1);
//		fundAccount.setUseFor(1); // TODO 寿光只有一个交易账户，其它市场将允许多账户
//		fundAccount.setName(openCardInfo.getCustomerName());
//		fundAccount.setMobile(openCardInfo.getCustomerContactsPhone());
//		fundAccount.setCode(openCardInfo.getCardNo());
//		fundAccount.setPassword(openCardInfo.getLoginPwd());
//		return fundAccount;
//	}

	/**
	 * 根据客户类型设置账户类型及相应权限
	 *
	 * @param userAccount
	 * @param customerType
	 */
	public void setAccountPermissions(UserAccountDo userAccount) {
		// 场景权限，充值/提现/交易
		Integer[] codes = { UsePermissionType.RECHARGE.getCode(), UsePermissionType.TRANSACTION.getCode(),
				UsePermissionType.WITHDRAW.getCode() };
		userAccount.setPermissions(UsePermissionType.getPermissions(codes));
	}

	/**
	 * 构建卡片数据
	 */
	private UserCardDo buildUserCard(OpenCardDto openCardInfo, Long userAccountId, CardType cardType) {
		UserCardDo userCard = new UserCardDo();
		userCard.setAccountId(userAccountId);
		userCard.setVersion(1);
		userCard.setLast(1);
		// 如果是电子卡则生成电子卡号,实体卡则使用卡片上的卡号
		userCard.setCardNo(openCardInfo.getCardNo());
		userCard.setCategory(CardCategory.PARK.getCode());
		userCard.setType(cardType.getCode());
		userCard.setFirmId(openCardInfo.getFirmId());
		userCard.setFirmName(openCardInfo.getFirmName());
		userCard.setState(CardStatus.NORMAL.getCode());
		userCard.setCreatorId(openCardInfo.getCreatorId());
		userCard.setCreator(openCardInfo.getCreator());
		LocalDateTime now = LocalDateTime.now();
		userCard.setCreateTime(now);
		userCard.setModifyTime(now);
		return userCard;
	}
}
