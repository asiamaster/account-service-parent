package com.dili.account.type;

/**
 * @description：
 *          卡片状态
 * @author ：WangBo
 * @time ：2020年4月27日下午1:45:02
 */
public enum CardStorageState
{
	USED("在用",1),
	ACTIVE("激活",2),
	VOID("作废",3);

	private String name;
    private int code;

    CardStorageState(String name, int code)
	{
		this.name = name;
		this.code = code;
	}

	public static CardStorageState getCardRepositoryStatus(int code)
    {
        for (CardStorageState repositoryStatus : CardStorageState.values()) {
            if (repositoryStatus.getCode() == code) {
                return repositoryStatus;
            }
        }
        return null;
    }

    public static String getName(int code)
    {
        for (CardStorageState repositoryStatus : CardStorageState.values()) {
            if (repositoryStatus.getCode() == code) {
                return repositoryStatus.name;
            }
        }
        return null;
    }

	public String getName()
	{
		return name;
	}

	public int getCode()
	{
		return code;
	}

}
