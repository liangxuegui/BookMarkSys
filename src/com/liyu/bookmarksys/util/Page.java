package com.liyu.bookmarksys.util;


public class Page {
	private int pageNum;
	private int pageSize;
	private int totalPage;
	private int totalRowNum;
	private int currentPage;
	public Page(){}
	public Page(int pageSize,int totalRowNum){
		this.pageSize = pageSize;
		this.totalRowNum = totalRowNum;
		this.totalPage = (totalRowNum-1)/pageSize;
	}
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalRowNum() {
		return totalRowNum;
	}
	public void setTotalRowNum(int totalRowNum) {
		this.totalRowNum = totalRowNum;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
}
