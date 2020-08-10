package com.dili.account.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dili.account.dto.BatchActivateCardDto;
import com.dili.account.dto.BatchCardAddStorageDto;
import com.dili.account.dto.CardAddStorageDto;
import com.dili.account.dto.CardRepoQueryParam;
import com.dili.account.entity.CardStorageDo;
import com.dili.account.service.ICardStorageService;
import com.dili.account.util.AssertUtils;
import com.dili.ss.domain.BaseOutput;
import com.dili.ss.domain.PageOutput;

/**
 * @description： 卡片仓库服务，入库、报废
 * 
 * @author ：WangBo
 * @time ：2020年6月19日下午5:36:46
 */
@RestController
@RequestMapping(value = "api/account/cardStorage")
public class CardStorageController {

	@Resource
	private ICardStorageService cardStorageService;

	/**
	 * 卡片仓库列表查询，带分页
	 */
	@PostMapping("pageList")
	public PageOutput<List<CardStorageDo>> pageList(@RequestBody CardRepoQueryParam param) {
		return cardStorageService.listPage(param);
	}

	/**
	 * 根据卡号查询卡片在仓库中的状态
	 */
	@PostMapping("getCardStorageByCardNo")
	public BaseOutput<CardStorageDo> getCardStorageByCardNo(@RequestBody CardRepoQueryParam param) {
		AssertUtils.notEmpty(param.getCardNo(), "卡号不能为空!");
		return BaseOutput.successData(cardStorageService.getByCardNo(param.getCardNo()));
	}

	/**
	 * 卡片入库
	 */
	@PostMapping("add")
	public BaseOutput<?> addCard(@RequestBody CardAddStorageDto addCardInfo) {
		AssertUtils.notEmpty(addCardInfo.getCardNo(), "卡号不能为空!");
		AssertUtils.notEmpty(addCardInfo.getCreator(), "入库操作人员不能为空!");
		AssertUtils.notNull(addCardInfo.getFirmId(), "卡片所属市场不能为空!");
		AssertUtils.notNull(addCardInfo.getType(), "卡片类型不能为空!");
		cardStorageService.addCard(addCardInfo);
		return BaseOutput.success();
	}

	/**
	 * 卡片批量激活，只需要cardNos
	 */
	@PostMapping("batchActivate")
	public BaseOutput<?> batchActivate(@RequestBody BatchActivateCardDto dto) {
		AssertUtils.notNull(dto.getCardNos(), "参数校验失败：卡号缺失!");
		cardStorageService.batchActivate(dto.getCardNos());
		return BaseOutput.success();
	}

	/**
	 * 卡片批量入库
	 */
	@PostMapping("batchAdd")
	public BaseOutput<?> barchAddCard(@RequestBody BatchCardAddStorageDto batchInfo) {
		AssertUtils.notEmpty(batchInfo.getCreator(), "入库操作人员不能为空!");
		AssertUtils.notNull(batchInfo.getFirmId(), "卡片所属市场不能为空!");
		AssertUtils.notNull(batchInfo.getStartCardNo(), "起始卡号不能为空!");
		AssertUtils.notNull(batchInfo.getEndCardNo(), "截止卡号不能为空!");
		cardStorageService.batchAddCard(batchInfo);
		return BaseOutput.success();
	}

	/**
	 * 卡片作废
	 */
	@PostMapping("void")
	public BaseOutput<?> voidCard(@RequestBody CardAddStorageDto voidCardInfo) {
		AssertUtils.notEmpty(voidCardInfo.getCardNo(), "卡号不能为空!");
		cardStorageService.voidCard(voidCardInfo.getCardNo(), voidCardInfo.getNotes());
		return BaseOutput.success();
	}
	
	/**
	 * 卡片由使用中转为激活状态
	 */
	@PostMapping("activateCardByInUse")
	public BaseOutput<?> activateCardByInUse(@RequestBody CardAddStorageDto cardInfo) {
		AssertUtils.notEmpty(cardInfo.getCardNo(), "卡号不能为空!");
		cardStorageService.activateCard(cardInfo.getCardNo());
		return BaseOutput.success();
	}
}
