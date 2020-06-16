package com.dili.account.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 卡片仓库服务，入库、报废
 * 
 * @author ：WangBo
 * @time ：2020年4月28日下午4:05:36
 */
@RestController
@RequestMapping(value = "card/repository")
public class CardRepositoryController {
//
//	@Resource
//	private ICardRepositoryService cardRepositoryService;
//
//	/**
//	 * 卡片仓库列表查询，带分页
//	 */
//	@PostMapping("list")
//	public Message<Page<UserCardRepositoryEntity>> list(@RequestBody CardRepoQueryParam param) {
//		Page<UserCardRepositoryEntity> listPage = cardRepositoryService.listPage(param);
//		return Message.success(listPage);
//	}
//	
//	/**
//	 * 卡片入库
//	 */
//	@PostMapping("add")
//	public Message<?> addCard(@RequestBody CardAddRepositoryDto addCardInfo) {
//		AssertUtils.notEmpty(addCardInfo.getCardNo(), "卡号不能为空!");
//		AssertUtils.notEmpty(addCardInfo.getCreaterName(), "入库操作人员不能为空!");
//		AssertUtils.notEmpty(addCardInfo.getInstitutionCode(), "卡片内置所属机构不能为空!");
//		AssertUtils.notNull(addCardInfo.getCategory(), "卡片类型不能为空!");
//		cardRepositoryService.addCard(addCardInfo);
//		return Message.success();
//	}
//
//	/**
//	 * 卡片作废
//	 */
//	@PostMapping("void")
//	public Message<?> voidCard(@RequestBody CardAddRepositoryDto voidCardInfo) {
//		AssertUtils.notEmpty(voidCardInfo.getCardNo(), "卡号不能为空!");
//		cardRepositoryService.voidCard(voidCardInfo.getCardNo(), voidCardInfo.getRemark());
//		return Message.success();
//	}
}
