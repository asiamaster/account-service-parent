package com.dili.account.type;

/**
 * @description： 
 *          卡片状态 
 * @author ：WangBo
 * @time ：2020年4月27日下午1:45:02
 */
public enum CardRepositoryStatus
{
	USED("在用",1),
	ACTIVE("激活",2),
	VOID("作废",3);
	
	private String name;
    private int code;
    
	private CardRepositoryStatus(String name, int code)
	{
		this.name = name;
		this.code = code;
	}
	
	public static CardRepositoryStatus getCardRepositoryStatus(int code)
    {
        for (CardRepositoryStatus repositoryStatus : CardRepositoryStatus.values()) {
            if (repositoryStatus.getCode() == code) {
                return repositoryStatus;
            }
        }
        return null;
    }

    public static String getName(int code)
    {
        for (CardRepositoryStatus repositoryStatus : CardRepositoryStatus.values()) {
            if (repositoryStatus.getCode() == code) {
                return repositoryStatus.name;
            }
        }
        return null;
    }

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/**
	 * @return the code
	 */
	public int getCode()
	{
		return code;
	}
	
	/**
	 * @param code the code to set
	 */
	public void setCode(int code)
	{
		this.code = code;
	}
}
