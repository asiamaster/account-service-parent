package com.dili.account.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import com.dili.account.dao.ICardStorageDao;
import com.dili.account.dto.BatchActivateCardDto;
import com.dili.account.dto.BatchCardAddStorageDto;
import com.dili.account.dto.CardAddStorageDto;
import com.dili.account.dto.CardRepoQueryParam;
import com.dili.account.entity.CardStorageDo;
import com.dili.account.exception.BizExceptionProxy;
import com.dili.account.service.ICardStorageService;
import com.dili.account.type.CardStorageState;
import com.dili.account.util.PageUtils;
import com.dili.ss.domain.PageOutput;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

/**
 * @description： 卡片入库service实现
 * 
 * @author ：WangBo
 * @time ：2020年6月19日下午5:16:13
 */
@Service("cardStorageService")
public class CardStorageServiceImpl implements ICardStorageService {

	private static final Logger LOG = LoggerFactory.getLogger(CardStorageServiceImpl.class);

	@Resource
	private ICardStorageDao cardStorageDao;

	@Override
	public PageOutput<List<CardStorageDo>> listPage(CardRepoQueryParam queryParam) {
		Page<CardStorageDo> page = PageHelper.startPage(queryParam.getPage(), queryParam.getRows());
		List<CardStorageDo> selectList = cardStorageDao.selectList(queryParam);
		return PageUtils.convert2PageOutput(page, selectList);
	}

	@Override
	public void addCard(CardAddStorageDto addInfo) {
		CardStorageDo repository = cardStorageDao.getByCardNo(addInfo.getCardNo());
		if (repository != null) {
			LOG.error(DUPLICATION_ERRMSG, addInfo.getCardNo());
			throw BizExceptionProxy.exception(DUPLICATION_ERRMSG);
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
		cardInfo.setState(CardStorageState.ACTIVATE.getCode());
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
		if (repository.getState() == CardStorageState.ACTIVATE.getCode()) {
			LOG.error(NOT_IN_USE_ERRMSG, cardNo);
			throw BizExceptionProxy.exception(NOT_IN_USE_ERRMSG);
		}
		// 更新为激活
		CardStorageDo updateParam = new CardStorageDo();
		updateParam.setCardNo(cardNo);
		updateParam.setState(CardStorageState.ACTIVATE.getCode());
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
			throw BizExceptionProxy.exception(IN_USE_ERRMSG);
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
			throw BizExceptionProxy.exception(NONEXISTENT_ERRMSG);
		}
		// 是否作废
		if (repository.getState() == CardStorageState.VOID.getCode()) {
			LOG.error(VOID_ERRMSG, cardNo);
			throw BizExceptionProxy.exception(VOID_ERRMSG);
		}
		return repository;
	}

	@Override
	public int updateByCardNo(CardStorageDo cardStorage) {
		checkCardState(cardStorage.getCardNo());
		// 修改状态
		CardStorageDo updateParam = new CardStorageDo();
		updateParam.setCardNo(cardStorage.getCardNo());
		updateParam.setState(cardStorage.getState());
		updateParam.setModifyTime(LocalDateTime.now());
		cardStorageDao.updateByCardNo(updateParam);
		return 0;
	}

	@Override
	public CardStorageDo getByCardNo(String cardNo) {
		if (StringUtils.isBlank(cardNo)) {
			return null;
		}
		CardStorageDo cardStorage = cardStorageDao.getByCardNo(cardNo);
		if (cardStorage == null) {
			LOG.error(NONEXISTENT_ERRMSG, cardNo);
			throw BizExceptionProxy.exception(NONEXISTENT_ERRMSG);
		}
		return cardStorageDao.getByCardNo(cardNo);
	}

	@Override
	public void batchAddCard(BatchCardAddStorageDto batchCardDto) {
		// 检查重复卡号
		CardRepoQueryParam queryParam = new CardRepoQueryParam();
		queryParam.setStartCardNo(batchCardDto.getStartCardNo());
		queryParam.setEndCardNo(batchCardDto.getEndCardNo());
		queryParam.setFirmId(batchCardDto.getFirmId());
		List<CardStorageDo> selectList = cardStorageDao.selectList(queryParam);
		if (!CollectionUtils.isEmpty(selectList)) {
			if (selectList.size() == 1) {
				throw BizExceptionProxy.exception("入库失败，包含重复卡号" + selectList.get(0).getCardNo());
			}
			Long existsCardNo1 = Long.parseLong(selectList.get(0).getCardNo());
			Long existsCardNo2 = Long.parseLong(selectList.get(selectList.size() - 1).getCardNo());
			String errMsg = String.format("入库失败，包含重复卡号%s~%s", existsCardNo1, existsCardNo2);
			if (existsCardNo1 > existsCardNo2) {
				errMsg = String.format("入库失败，包含重复卡号%s~%s", existsCardNo2, existsCardNo1);
			}
			throw BizExceptionProxy.exception(errMsg);
		}

		List<CardStorageDo> cardList = new ArrayList<CardStorageDo>();
		for (long i = batchCardDto.getStartCardNo(); i <= batchCardDto.getEndCardNo(); i++) {
			CardStorageDo saveInfo = new CardStorageDo();
			saveInfo.setCardNo(i + "");
			saveInfo.setCreateTime(LocalDateTime.now());
			saveInfo.setCreator(batchCardDto.getCreator());
			saveInfo.setCreatorId(batchCardDto.getCreatorId());
			saveInfo.setFirmId(batchCardDto.getFirmId());
			saveInfo.setFirmName(batchCardDto.getFirmName());
			saveInfo.setModifyTime(LocalDateTime.now());
			saveInfo.setNotes(batchCardDto.getNotes());
			saveInfo.setState(CardStorageState.UNACTIVATE.getCode());
			saveInfo.setType(batchCardDto.getCardType());
			cardList.add(saveInfo);
		}
		cardStorageDao.batchSave(cardList);
	}

	@Override
	public void batchActivate(List<String> cardNos) {
		BatchActivateCardDto activateDto = new BatchActivateCardDto();
		activateDto.setState(CardStorageState.ACTIVATE.getCode());
		activateDto.setModifyTime(LocalDateTime.now());
		activateDto.setCardNos(cardNos);
		// 批量激活
		int batchResult = cardStorageDao.batchActivate(activateDto);
		if (batchResult == 0 || batchResult != cardNos.size()) {
			LOG.warn("需要激活的的卡列表中包含非未激活状态的卡号！");
		}
	}

	private static final String NONEXISTENT_ERRMSG = "该卡{}未入库!";
	private static final String IN_USE_ERRMSG = "该卡{}已在使用中!";
	private static final String NOT_IN_USE_ERRMSG = "该卡{}未被使用，操作失败!";
	private static final String VOID_ERRMSG = "该卡{}已作废，操作失败!";
	private static final String DUPLICATION_ERRMSG = "卡号{}重复,入库失败!";
}
