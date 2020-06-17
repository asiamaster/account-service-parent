package com.dili.account.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dili.account.service.ICardStorageService;

/**
 * @description： 卡片入库service实现
 *
 * @author ：WangBo
 * @time ：2020年4月22日下午5:53:40
 */
@Service("cardStorageService")
public class CardStorageServiceImpl{

	private static final Logger LOG = LoggerFactory.getLogger(CardStorageServiceImpl.class);

//	@Resource
//	private IUserCardRepositoryDao userCardRepositoryDao;
//
//	@Override
//	public Page<UserCardRepositoryEntity> listPage(CardRepoQueryParam queryParam) {
//		Page<UserCardRepositoryEntity> page = new Page<UserCardRepositoryEntity>();
//		page.setPageNumber(queryParam.getPageNumber());
//		page.setPageSize(queryParam.getPageSize());
//		Long listCount = userCardRepositoryDao.selectForPageCount(queryParam);
//		if (listCount > 0) {
//			List<UserCardRepositoryEntity> listData = userCardRepositoryDao.selectForPage(queryParam);
//			page.setData(listData);
//			page.setTotal(listCount);
//		}
//		return page;
//	}
//
//	@Override
//	public void addCard(CardAddRepositoryDto addInfo) {
//		UserCardRepositoryEntity repository = userCardRepositoryDao.getByCardNo(addInfo.getCardNo());
//		if (repository != null) {
//			LOG.error(DUPLICATION_ERRMSG, addInfo.getCardNo());
//			throw new AppException(DUPLICATION_ERRMSG);
//		}
//		UserCardRepositoryEntity cardInfo = new UserCardRepositoryEntity();
//		cardInfo.setCardNo(addInfo.getCardNo());
//		cardInfo.setCategory(addInfo.getCategory());
//		cardInfo.setCreaterName(addInfo.getCreaterName());
//		cardInfo.setDeviceId(addInfo.getDeviceId());
//		cardInfo.setInstitutionCode(addInfo.getInstitutionCode());
//		cardInfo.setMakerVersion(addInfo.getMakerVersion());
//		cardInfo.setMarketId(addInfo.getMarketId());
//		cardInfo.setRemark(addInfo.getRemark());
//		cardInfo.setStatus(CardRepositoryStatus.ACTIVE.getCode());
//		cardInfo.setVerifyCode(addInfo.getVerifyCode());
//		LocalDateTime now = LocalDateTime.now();
//		cardInfo.setCreatedTime(now);
//		cardInfo.setModifiedTime(now);
//		// 入库
//		userCardRepositoryDao.save(cardInfo);
//	}
//
//	@Override
//	public UserCardRepositoryEntity activateCard(String cardNo) {
//		UserCardRepositoryEntity repository = checkCardStatus(cardNo);
//		// 该卡已在激活状态
//		if (repository.getStatus() == CardRepositoryStatus.ACTIVE.getCode()) {
//			LOG.error(NOT_IN_USE_ERRMSG, cardNo);
//			throw new AppException(NOT_IN_USE_ERRMSG);
//		}
//		// 更新为激活
//		UserCardRepositoryEntity updateParam = new UserCardRepositoryEntity();
//		updateParam.setCardNo(cardNo);
//		updateParam.setStatus(CardRepositoryStatus.ACTIVE.getCode());
//		updateParam.setModifiedTime(LocalDateTime.now());
//		userCardRepositoryDao.updateByCardNo(updateParam);
//
//		repository.setStatus(updateParam.getStatus());
//		return repository;
//	}
//
//	@Override
//	public UserCardRepositoryEntity inUse(String cardNo) {
//		UserCardRepositoryEntity repository = checkCardStatus(cardNo);
//		// 该卡已在使用状态
//		if (repository.getStatus() == CardRepositoryStatus.USED.getCode()) {
//			LOG.error(IN_USE_ERRMSG, cardNo);
//			throw new AppException(IN_USE_ERRMSG);
//		}
//		// 更新为使用中
//		UserCardRepositoryEntity updateParam = new UserCardRepositoryEntity();
//		updateParam.setCardNo(cardNo);
//		updateParam.setStatus(CardRepositoryStatus.USED.getCode());
//		updateParam.setModifiedTime(LocalDateTime.now());
//		userCardRepositoryDao.updateByCardNo(updateParam);
//
//		repository.setStatus(updateParam.getStatus());
//		return repository;
//	}
//
//	@Override
//	public void voidCard(String cardNo, String remark) {
//		checkCardStatus(cardNo);
//		// 作废
//		UserCardRepositoryEntity updateParam = new UserCardRepositoryEntity();
//		updateParam.setCardNo(cardNo);
//		updateParam.setStatus(CardRepositoryStatus.VOID.getCode());
//		updateParam.setModifiedTime(LocalDateTime.now());
//		updateParam.setRemark(remark);
//		userCardRepositoryDao.updateByCardNo(updateParam);
//	}
//
//	/**
//	 * 检查卡状态并返回该卡号数据 <br>
//	 * 如果不存在或者卡已作废则抛出异常
//	 */
//	private UserCardRepositoryEntity checkCardStatus(String cardNo) {
//		UserCardRepositoryEntity repository = userCardRepositoryDao.getByCardNo(cardNo);
//		// 判断该卡是否存在
//		if (repository == null) {
//			LOG.error(NONEXISTENT_ERRMSG, cardNo);
//			throw new AppException(NONEXISTENT_ERRMSG);
//		}
//		// 是否作废
//		if (repository.getStatus() == CardRepositoryStatus.VOID.getCode()) {
//			LOG.error(VOID_ERRMSG, cardNo);
//			throw new AppException(VOID_ERRMSG);
//		}
//		return repository;
//	}

	private static final String NONEXISTENT_ERRMSG = "该卡{}未入库!";
	private static final String IN_USE_ERRMSG = "该卡{}已在使用中!";
	private static final String NOT_IN_USE_ERRMSG = "该卡{}未被使用，操作失败!";
	private static final String VOID_ERRMSG = "该卡{}已作废，操作失败!";
	private static final String DUPLICATION_ERRMSG = "卡号{}重复,入库失败!";
}
