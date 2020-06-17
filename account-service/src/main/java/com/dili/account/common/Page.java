package com.dili.account.common;

import java.util.List;

/**
 * @description： 分页使用基本参数
 * 
 * @author ：WangBo
 * @time ：2020年6月16日下午5:55:08
 */
public class Page<T> {
	private Integer total;
	private List<T> rows;
	private List<?> footer;
	/** 当前第几页 */
	private int pageNumber;
	/** 每页多少条数据 */
	private int pageSize;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public List<?> getFooter() {
		return footer;
	}

	public void setFooter(List<?> footer) {
		this.footer = footer;
	}

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
