package com.dili.account.type;

/**
 * 各市场代号与(CRM系统)对应
 */
public enum MarketEnum {

	HRB("8888", "hd", "哈尔滨地利"),
	SY("8890", "sy", "沈阳地利"),
	CC("8892", "cc", "长春地利"),
	GY("8891", "gy", "贵阳地利"),
	QQHR("8894", "qqhe", "齐齐哈尔地利"),
	MDJ("8893", "mdj", "牡丹江地利"),
	SG("8895", "sg", "寿光地利"),
	CD("8896", "cd", "四川聚和"),
	DLK("8897", "dlk", "哈尔滨达利凯农副产品有限公司"),
	HZ("8898", "hz", "杭州水产批发市场");

	/**
	 * 园区系统市场编号
	 */
	private String marketId;

	/**
	 * CRM系统编码
	 */
	private String crmCode;

	/**
	 * 名称
	 */
	private String name;

	private MarketEnum(String marketId, String code, String name) {
		this.marketId = marketId;
		this.crmCode = code;
		this.name = name;
	}

	public String getMarketId() {
		return marketId;
	}

	public String getName() {
		return name;
	}

	public static String getCRMCode(String marketId) {
		for (MarketEnum marketEnum : values()) {
			if (marketEnum.getMarketId().equalsIgnoreCase(marketId)) {
				return marketEnum.crmCode;
			}
		}
		return null;
	}

	/**
	 * CRM系统编码
	 */
	public String getCrmCode() {
		return crmCode;
	}

	/**
	 * CRM系统编码
	 */
	public void setCrmCode(String crmCode) {
		this.crmCode = crmCode;
	}

	/**
	 * 园区系统市场编号
	 */
	public void setMarketId(String marketId) {
		this.marketId = marketId;
	}

	/**
	 * 名称
	 */
	public void setName(String name) {
		this.name = name;
	}

}