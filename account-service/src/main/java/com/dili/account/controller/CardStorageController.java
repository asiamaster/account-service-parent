package com.dili.account.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dili.account.dto.CardAddStarogeDto;
import com.dili.account.dto.CardRepoQueryParam;
import com.dili.account.entity.CardStorageDo;
import com.dili.account.service.ICardStorageService;
import com.dili.account.util.AssertUtils;
import com.dili.ss.domain.BaseOutput;
import com.dili.ss.domain.PageOutput;

/**
 * @description： 
 *          卡片仓库服务，入库、报废
 * @author ：WangBo
 * @time ：2020年6月19日下午5:36:46
 */
@RestController
@RequestMapping(value = "api/account/storage")
public class CardStorageController {

	@Resource
	private ICardStorageService cardStorageService;

	/**
	 * 卡片仓库列表查询，带分页
	 */
	@PostMapping("list")
	public PageOutput<List<CardStorageDo>> list(@RequestBody CardRepoQueryParam param) {
		return cardStorageService.listPage(param);
	}
	
	/**
	 * 卡片入库
	 */
	@PostMapping("add")
	public BaseOutput<?> addCard(@RequestBody CardAddStarogeDto addCardInfo) {
		AssertUtils.notEmpty(addCardInfo.getCardNo(), "卡号不能为空!");
		AssertUtils.notEmpty(addCardInfo.getCreator(), "入库操作人员不能为空!");
//		AssertUtils.notEmpty(addCardInfo.getInstitutionCode(), "卡片内置所属机构不能为空!");
		AssertUtils.notNull(addCardInfo.getType(), "卡片类型不能为空!");
		cardStorageService.addCard(addCardInfo);
		return BaseOutput.success();
	}

	/**
	 * 卡片作废
	 */
	@PostMapping("void")
	public BaseOutput<?> voidCard(@RequestBody CardAddStarogeDto voidCardInfo) {
		AssertUtils.notEmpty(voidCardInfo.getCardNo(), "卡号不能为空!");
		cardStorageService.voidCard(voidCardInfo.getCardNo(), voidCardInfo.getNotes());
		return BaseOutput.success();
	}
}
