package com.dili.account.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dili.account.dto.OpenCardDto;
import com.dili.account.dto.OpenCardResponseDto;
import com.dili.account.service.IOpenCardService;
import com.dili.account.type.AccountType;
import com.dili.account.type.CardType;
import com.dili.account.util.AssertUtils;
import com.dili.ss.domain.BaseOutput;

/**
 *	开卡服务          
 * @author ：WangBo
 * @time ：2020年4月28日下午3:36:22
 */
/**
 * @author mrkin
 *
 */
@RestController
@RequestMapping(value = "api/account")
public class OpenCardController {

	@Resource
	private IOpenCardService openCardService;

	/**
	 * 主卡开卡
	 * @throws InterruptedException 
	 */
	@PostMapping("openMasterCard")
	public BaseOutput<?> openMasterCard(@RequestBody OpenCardDto openCardInfo) {
		// 主要参数校验
		checkMasterParam(openCardInfo);
		OpenCardResponseDto response=null;
//		try {
			// 开卡
			response = openCardService.openMasterCard(openCardInfo);
//		} catch (Exception e) {
//			System.out.println("XXXXXXXXXXXXXXXXXXXX");
//			throw e;
//		}
		return BaseOutput.success("success").setData(response);
	}
//
//	/**
//	 * 副卡开卡
//	 */
//	@PostMapping("openSlaveCard")
//	public Message<OpenCardResponseDto> openSlaveCard(@RequestBody OpenCardDto openCardInfo) throws Exception {
//		// 主要参数校验
//		AssertUtils.notNull(openCardInfo.getParentAccountId(), "主卡信息不能为空!");
//		AssertUtils.notEmpty(openCardInfo.getMarketId(), "开卡市场编码不能为空!");
//		OpenCardResponseDto response = openCardService.openSlaveCard(openCardInfo);
//		return Message.success(response);
//	}
//
	/**
	 * 主卡参数校验
	 * @param openCardInfo
	 */
	private void checkMasterParam(OpenCardDto openCardInfo) {
		AssertUtils.notEmpty(openCardInfo.getName(), "开卡用户名不能为空!");
		AssertUtils.notEmpty(openCardInfo.getCredentialNo(), "开卡用户名证件号不能为空!");
		AssertUtils.notEmpty(openCardInfo.getMobile(), "开卡手机号不能为空!");
		AssertUtils.notNull(openCardInfo.getFirmId(), "开卡市场编码不能为空!");
		AssertUtils.notEmpty(openCardInfo.getLoginPwd(), "账户密码不能为空!");
	}
//	
//	public static void main(String[] args) throws Exception {
//		OpenCardDto openInfo = new OpenCardDto();
//		openInfo.setAccountType(AccountType.PERSONAL.getCode());
//		openInfo.setAddress("成都府人民东路88号");
//		openInfo.setAuthStatus(AuthStatus.AUTH.getCode());
//		openInfo.setBizUsageType(CardBizType.BUYER.getCode());
//		openInfo.setBusinessCategory("苹果香蕉");
//		openInfo.setCardNo("888888888888");
//		openInfo.setCategory(CardCategory.MASTER.getCode());
//		openInfo.setCreateSource(CreateSource.COUNTER.getCode());
//		openInfo.setCredentialNo("513002198810102287");
//		openInfo.setCredentialType(CredentialType.ID.getCode());
//		openInfo.setCrmCustormerId(123221L);
//		openInfo.setCustomerArea(CustomerAreaType.LOCAL.getCode());
//		openInfo.setCustormerType(AccountType.PERSONAL.getCode());
//		openInfo.setFundAccountId(550330L);
//		openInfo.setGender(Gender.MALE.getCode());
//		openInfo.setLegalCredentialType(CredentialType.ID.getCode());
//		openInfo.setLegalName("法人姓名");
//		openInfo.setLegalNo("5119192929929293");
//		openInfo.setLoginPwd("123456");
//		openInfo.setMarketId(MarketEnum.SY.getMarketId());
//		openInfo.setMobile("18888888888");
//		openInfo.setName("老李一");
//		openInfo.setParentAccountId(23422L);
//		openInfo.setSeinsweise(CardType.PHYSICAL_CARD.getCode());
//		openInfo.setTradePwd("123456");
//		openInfo.setUsePermission("10,12,12");
//		openInfo.setValidityDate(LocalDateTime.now());
////		OpenCardDto dto = new OpenCardDto();
////		Field[] fields = dto.getClass().getDeclaredFields();
////		for (Field f : fields) {
////			f.setAccessible(true);
////			if (f.getType().equals(String.class)) {
////				if (f.getName().contains("address")) {
////					f.set(dto, "四川省成都人民东路8号");
////				} else if (f.getName().contains("mobile")) {
////					f.set(dto, "18988888888");
////				} else if (f.getName().contains("name")) {
////					f.set(dto, "老李");
////				} else if (f.getName().contains("cardNo")) {
////					f.set(dto, "888888888888");
////				} else {
////					f.set(dto, "测试数据" + f.getName());
////				}
////			} else if (f.getType().equals(Long.class)) {
////				f.set(dto, 2L);
////			} else if (f.getType().equals(LocalDateTime.class)) {
////				f.set(dto, LocalDateTime.now());
////			} else {
////				f.set(dto, 1);
////			}
////		}
//		System.out.println(JsonUtils.toJsonString(openInfo));
//	}
}
