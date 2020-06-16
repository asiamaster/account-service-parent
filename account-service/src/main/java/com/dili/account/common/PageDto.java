package com.dili.account.common;

/**
 * @description： 
 *          分页使用基本参数
 * @author ：WangBo
 * @time ：2020年5月7日下午3:45:54
 */
public class PageDto {
	/** 当前第几页 */
	private int pageNumber;
	/** 每页多少条数据 */
	private int pageSize;
	/** 查询起始索引，通过计算得出*/
	private int startIndex;

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getStartIndex() {
		return (pageNumber * pageSize) - pageSize;
	}

}
