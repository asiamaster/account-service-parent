package com.dili.account.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 账户信息查询
 * @author ：WangBo
 * @time ：2020年4月28日下午3:30:27
 */
@RestController
@RequestMapping(value = "user")
public class QueryAccountController {
//	private static final Logger log = LoggerFactory.getLogger(QueryAccountController.class);
//
//	@Resource
//	private IAccountQueryService accountQueryService;
//
//	/**
//	 * 查询唯一数据
//	 */
//	@PostMapping("queryOnlyAccount")
//	public Message<UserAccountCardDto> queryOnlyAccount(@RequestBody UserAccountCardQuery queryParam) {
//		log.info("queryOnlyAccount > " + JsonUtils.toJsonString(queryParam));
//		if(queryParam.getAccountId() == null && queryParam.getCardNo() == null) {
//			new AppException("请至少设置一个查询参数!");
//		}
//		return Message.success(accountQueryService.getOnly(queryParam.getCardNo(), queryParam.getAccountId()));
//	}
//
//	/**
//	 * 查询列表
//	 */
//	@PostMapping(value = "queryListAccount")
//	public Message<List<UserAccountCardDto>> queryListAccount(@RequestBody UserAccountCardQuery queryParam) {
//		log.info("queryListAccount > " + JsonUtils.toJsonString(queryParam));
//		checkParamIsNull(queryParam);
//		return Message.success(accountQueryService.listAccount(queryParam));
//	}
//
//	/**
//	 * 校验对象字段，所有字段都为空则抛出异常
//	 */
//	private void checkParamIsNull(Object o) {
//		try {
//			Field[] fields = o.getClass().getDeclaredFields();
//			for (Field f : fields) {
//				f.setAccessible(true);
//				if (f.get(o) != null) {
//					return;
//				}
//			}
//		} catch (Exception e) {
//			log.error("查询参数检查出错", e);
//		}
//		throw new AppException("请至少设置一个查询参数!");
//	}
}
