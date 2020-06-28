package com.dili.account.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dili.account.common.BizNoServiceType;
import com.dili.account.dao.IUserAccountDao;
import com.dili.account.dao.IUserCardDao;
import com.dili.account.dto.OpenCardDto;
import com.dili.account.dto.OpenCardResponseDto;
import com.dili.account.dto.UserAccountCardQuery;
import com.dili.account.dto.UserAccountCardResponseDto;
import com.dili.account.entity.CardStorageDo;
import com.dili.account.entity.UserAccountDo;
import com.dili.account.entity.UserCardDo;
import com.dili.account.exception.AccountBizException;
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

	@Resource
	private ICardStorageService ICardStorageService;
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
			throw new AccountBizException("该卡{}已被{}使用，不能开卡!", openCardInfo.getCardNo());
		}
		// 判断客户是否已办理过主卡，寿光每个人只能有一张交易主卡,其它市场允许办两张卡，则判断只能一张交易买家卡和一张交易卖家卡
		UserAccountCardQuery queryParam = new UserAccountCardQuery();
		queryParam.setCustomerIds(Lists.newArrayList(openCardInfo.getCustomerId()));
		List<UserAccountCardResponseDto> userAccountList = accountQueryService.getListByConditionForRest(queryParam);
		if (userAccountList.size() > 0) {
			// TODO 是否允许两张卡
			throw new AccountBizException("客户{}已办理过交易主卡{}", openCardInfo.getName(), userAccountList.get(0).getCardNo());
		}
		// 判断卡类型，并将卡片改为使用中
		CardStorageDo cardStorageDo = ICardStorageService.inUse(openCardInfo.getCardNo());
		if (cardStorageDo.getType() != CardType.MASTER.getCode()) {
			throw new AccountBizException("该卡{}不是主卡，操作失败!", openCardInfo.getCardNo());
		}

		// 创建资金账户
//		FundAccountDto fundAccount = new FundAccountDto();
//		String fundAccountIdStr = payRpcResolver.createFundAccount(fundAccount);
//		Long fundAccountId = Long.parseLong(fundAccountIdStr);
		Long fundAccountId = 33333L;

		// 构建账户信息
		String accountIdStr = uidRpcResovler.bizNumberRetry(BizNoServiceType.ACCOUNT_ID, 3);
		Long accountId = Long.parseLong(accountIdStr);
		UserAccountDo userAccount = buildUserAccount(openCardInfo, accountId, fundAccountId);

		// 保存卡片信息
		UserCardDo userCard = buildUserCard(openCardInfo, userAccount.getAccountId());
		userCardDao.save(userCard);

		// 保存账户信息
		userAccountDao.save(userAccount);

		// 返回数据
		OpenCardResponseDto response = new OpenCardResponseDto();
		response.setAccountId(userAccount.getAccountId());
		return response;
	}

	@Override
	public OpenCardResponseDto openSlaveCard(OpenCardDto openCardInfo) {
		// TODO Auto-generated method stub
		return null;
	}

//
//	@Override
//	@Transactional(rollbackFor = Exception.class)
//	public OpenCardResponseDto openSlaveCard(OpenCardDto openCardInfo) {
//		// 判断卡状态是否异常
//		UserCardEntity userCardParam = new UserCardEntity();
//		userCardParam.setCardNo(openCardInfo.getCardNo());
//		UserCardEntity cardExists = userCardDao.getByIdCardNo(userCardParam);
//		if (cardExists != null) {
//			throw new AppException("该卡{}处于{}使用状态，请联系管理员!", openCardInfo.getCardNo(), cardExists.getAccountId());
//		}
//
//		// 卡片改为使用中
//		UserCardRepositoryEntity cardRep = cardRepositoryService.inUse(openCardInfo.getCardNo());
//		if (cardRep.getCategory() != CardCategory.SLAVE.getCode()) {
//			throw new AppException("该卡{}不是副卡，操作失败!", openCardInfo.getCardNo());
//		}
//
//		// 获取主卡用户信息
//		UserAccountEntity parentAccount = userAccountDao.getById(openCardInfo.getParentAccountId());
//		if (parentAccount == null) {
//			throw new AppException("{}主卡账户{}不存在!", openCardInfo.getCardNo(), openCardInfo.getParentAccountId());
//		}
//
//		// 构建账户信息，使用主卡的用户信息
//		UserAccountEntity userAccount = buildUserAccount(openCardInfo, parentAccount.getUserInfoId());
//
//		// 保存卡片信息
//		UserCardEntity userCard = buildUserCard(openCardInfo, userAccount.getId());
//		userCardDao.save(userCard);
//
//		// 保存账户信息
//		userAccount.setParentAccountId(parentAccount.getId());
//		userAccount.setLatestCardId(userCard.getId());
//		userAccountDao.save(userAccount);
//
//		// 保存使用额度权限
//		UserAmountLimitEntity userLimit = buildUserLimit(openCardInfo, userAccount.getId());
//		amountLimitService.save(userLimit);
//
//		// 返回数据
//		OpenCardResponseDto response = new OpenCardResponseDto();
//		response.setAccountId(userAccount.getId());
//		return response;
//	}
	/**
	 * 构建卡账户数据
	 *
	 * @throws Exception
	 */
	private UserAccountDo buildUserAccount(OpenCardDto openCardInfo, Long accountId, Long fundAccountId) {
		UserAccountDo userAccount = new UserAccountDo();
		userAccount.setAccountId(accountId);
		userAccount.setParentAccountId(openCardInfo.getParentAccountId());
		userAccount.setCustomerId(openCardInfo.getCustomerId());
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

		setAccountPermissions(userAccount, openCardInfo.getCustormerType());
		return userAccount;
	}

	/**
	 * 根据客户类型设置账户类型及相应权限
	 * 
	 * @param userAccount
	 * @param customerType
	 */
	public void setAccountPermissions(UserAccountDo userAccount, String customerType) {
		if (CustomerType.PURCHASE.getCode().equalsIgnoreCase(customerType)) {
			// 买家
			userAccount.setType(AccountType.PURCHASE.getCode());

			// 资金账户。 交易/理财
			String usageTypeCodes = AccountUsageType.getCodes(AccountUsageType.TRADE.getCode(),
					AccountUsageType.WEALTH.getCode());
			userAccount.setUsageType(usageTypeCodes);

			// 场景权限，充值/提现/交易/缴费/理财
			Integer[] codes = { UsePermissionType.RECHARGE.getCode(), UsePermissionType.WEALTH.getCode(),
					UsePermissionType.TRANSACTION.getCode(), UsePermissionType.WITHDRAW.getCode(),
					UsePermissionType.PAY_FEES.getCode() };
			userAccount.setPermissions(UsePermissionType.getPermissions(codes));
		} else if (CustomerType.SALE.getCode().equalsIgnoreCase(customerType)) {
			// 卖家
			userAccount.setType(AccountType.SALE.getCode());

			// 资金账户。 交易/理财/水电预存
			String usageTypeCodes = AccountUsageType.getCodes(AccountUsageType.TRADE.getCode(),
					AccountUsageType.WEALTH.getCode(), AccountUsageType.UTILITIES.getCode());
			userAccount.setUsageType(usageTypeCodes);

			// 场景权限，充值/提现/交易/缴费/理财
			Integer[] permissionCodes = { UsePermissionType.RECHARGE.getCode(), UsePermissionType.TRANSACTION.getCode(),
					UsePermissionType.WEALTH.getCode(), UsePermissionType.WITHDRAW.getCode(),
					UsePermissionType.UTILITIES.getCode(), UsePermissionType.PAY_FEES.getCode() };
			userAccount.setPermissions(UsePermissionType.getPermissions(permissionCodes));
		} else if (CustomerType.DRIVER.getCode().equalsIgnoreCase(customerType)) {
			// 司机
			userAccount.setType(AccountType.PAY_FEES.getCode());

			// 资金账户。 交费/理财
			String usageTypeCodes = AccountUsageType.getCodes(AccountUsageType.PAY_FEES.getCode(),
					AccountUsageType.WEALTH.getCode());
			userAccount.setUsageType(usageTypeCodes);

			// 场景权限，充值/提现/缴费/理财
			Integer[] permissionCodes = { UsePermissionType.RECHARGE.getCode(), UsePermissionType.WEALTH.getCode(),
					UsePermissionType.WITHDRAW.getCode(), UsePermissionType.PAY_FEES.getCode() };
			userAccount.setPermissions(UsePermissionType.getPermissions(permissionCodes));
		}
	}

	/**
	 * 构建卡片数据
	 */
	private UserCardDo buildUserCard(OpenCardDto openCardInfo, Long userAccountId) {
		UserCardDo userCard = new UserCardDo();
		userCard.setAccountId(userAccountId);
		userCard.setVersion(1);
		userCard.setLast(1);
		// 如果是电子卡则生成电子卡号,实体卡则使用卡片上的卡号
		userCard.setCardNo(openCardInfo.getCardNo());
		userCard.setCategory(CardCategory.PARK.getCode());
		userCard.setType(CardType.MASTER.getCode());
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
