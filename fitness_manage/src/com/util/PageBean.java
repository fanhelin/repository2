package com.util;

import java.util.List;

public class PageBean<T> {
	private int total;
	private List<T> rows;
	private int pageSize=10;
	private int pageNumber=1;
	
	public PageBean(int pageSize,int pageNumber){
		this.pageNumber=pageNumber;
		this.pageSize=pageSize;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
}
