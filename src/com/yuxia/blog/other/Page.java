package com.yuxia.blog.other;

public class Page {
	private int pageNow = 1;//当前页数
	private int pageSize; // 每页显示记录的条数
	private int totalCount; // 总的记录条数
	private int totalPageCount; // 总的页数
	
	public Page(){
		
	}
	public Page(int pageNow, int pageSize, int totalCount) {
		super();
		this.pageNow = pageNow;
		this.pageSize = pageSize;
		this.totalCount = totalCount;
	}
	public int getPageNow() {
		return pageNow;
	}
	public void setPageNow(int pageNow) {
		this.pageNow = pageNow;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	/**
	 * 总页数=总记录数/总页数
	 */
	public int getTotalPageCount() {
		int count=totalCount/pageSize;
		return totalCount%pageSize==0?count:count+1;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	
}
