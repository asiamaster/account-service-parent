package com.dili.account.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dili.account.dao.ICardStorageDao;
import com.dili.account.dto.CardAddStarogeDto;
import com.dili.account.dto.CardRepoQueryParam;
import com.dili.account.entity.CardStorageDo;
import com.dili.account.exception.AccountBizException;
import com.dili.account.service.ICardStorageService;
import com.dili.account.type.CardStorageState;
import com.dili.ss.domain.PageOutput;
import com.github.pagehelper.PageHelper;

/**
 * @description： 卡片入库service实现
 *
 * @author ：WangBo
 * @time ：2020年4月22日下午5:53:40
 */
@Service("cardStorageService")
public class CardStorageServiceImpl implements ICardStorageService {

	private static final Logger LOG = LoggerFactory.getLogger(CardStorageServiceImpl.class);

	@Resource
	private ICardStorageDao cardStorageDao;

	@Override
	public PageOutput<List<CardStorageDo>> listPage(CardRepoQueryParam queryParam) {
		PageHelper.startPage(queryParam.getPageNumber(), queryParam.getPageSize());
		List<CardStorageDo> selectList = cardStorageDao.selectList(queryParam);
		PageOutput<List<CardStorageDo>> success = PageOutput.success();
		success.setTotal(selectList.size());
		success.setPageNum(queryParam.getPageNumber());
		success.setPageSize(queryParam.getPageSize());
		return success.setData(selectList);
	}

	@Override
	public void addCard(CardAddStarogeDto addInfo) {
		CardStorageDo repository = cardStorageDao.getByCardNo(addInfo.getCardNo());
		if (repository != null) {
			LOG.error(DUPLICATION_ERRMSG, addInfo.getCardNo());
			throw new AccountBizException(DUPLICATION_ERRMSG);
		}
		CardStorageDo cardInfo = new CardStorageDo();
		cardInfo.setCardNo(addInfo.getCardNo());
		cardInfo.setType(addInfo.getType());
		cardInfo.setCreator(addInfo.getCreator());
		cardInfo.setDeviceId(addInfo.getDeviceId());
		cardInfo.setMakerVersion(addInfo.getMakerVersion());
		cardInfo.setFirmId(addInfo.getFirmId());
		cardInfo.setFirmName(addInfo.getFirmName());
		cardInfo.setNotes(addInfo.getNotes());
		cardInfo.setState(CardStorageState.ACTIVE.getCode());
		cardInfo.setCreatorId(addInfo.getCreatorId());
		cardInfo.setVerifyCode(addInfo.getVerifyCode());
		LocalDateTime now = LocalDateTime.now();
		cardInfo.setCreateTime(now);
		cardInfo.setModifyTime(now);
		// 入库
		cardStorageDao.save(cardInfo);
	}

	@Override
	public CardStorageDo activateCard(String cardNo) {
		CardStorageDo repository = checkCardState(cardNo);
		// 该卡已在激活状态
		if (repository.getState() == CardStorageState.ACTIVE.getCode()) {
			LOG.error(NOT_IN_USE_ERRMSG, cardNo);
			throw new AccountBizException(NOT_IN_USE_ERRMSG);
		}
		// 更新为激活
		CardStorageDo updateParam = new CardStorageDo();
		updateParam.setCardNo(cardNo);
		updateParam.setState(CardStorageState.ACTIVE.getCode());
		updateParam.setModifyTime(LocalDateTime.now());
		cardStorageDao.updateByCardNo(updateParam);

		repository.setState(updateParam.getState());
		return repository;
	}

	@Override
	public CardStorageDo inUse(String cardNo) {
		CardStorageDo repository = checkCardState(cardNo);
		// 该卡已在使用状态
		if (repository.getState() == CardStorageState.USED.getCode()) {
			LOG.error(IN_USE_ERRMSG, cardNo);
			throw new AccountBizException(IN_USE_ERRMSG);
		}
		// 更新为使用中
		CardStorageDo updateParam = new CardStorageDo();
		updateParam.setCardNo(cardNo);
		updateParam.setState(CardStorageState.USED.getCode());
		updateParam.setModifyTime(LocalDateTime.now());
		cardStorageDao.updateByCardNo(updateParam);

		repository.setState(updateParam.getState());
		return repository;
	}

	@Override
	public void voidCard(String cardNo, String remark) {
		checkCardState(cardNo);
		// 作废
		CardStorageDo updateParam = new CardStorageDo();
		updateParam.setCardNo(cardNo);
		updateParam.setState(CardStorageState.VOID.getCode());
		updateParam.setModifyTime(LocalDateTime.now());
		cardStorageDao.updateByCardNo(updateParam);
	}

	/**
	 * 检查卡状态并返回该卡号数据 <br>
	 * 如果不存在或者卡已作废则抛出异常
	 */
	private CardStorageDo checkCardState(String cardNo) {
		CardStorageDo repository = cardStorageDao.getByCardNo(cardNo);
		// 判断该卡是否存在
		if (repository == null) {
			LOG.error(NONEXISTENT_ERRMSG, cardNo);
			throw new AccountBizException(NONEXISTENT_ERRMSG);
		}
		// 是否作废
		if (repository.getState() == CardStorageState.VOID.getCode()) {
			LOG.error(VOID_ERRMSG, cardNo);
			throw new AccountBizException(VOID_ERRMSG);
		}
		return null;
	}

	private static final String NONEXISTENT_ERRMSG = "该卡{}未入库!";
	private static final String IN_USE_ERRMSG = "该卡{}已在使用中!";
	private static final String NOT_IN_USE_ERRMSG = "该卡{}未被使用，操作失败!";
	private static final String VOID_ERRMSG = "该卡{}已作废，操作失败!";
	private static final String DUPLICATION_ERRMSG = "卡号{}重复,入库失败!";
}
