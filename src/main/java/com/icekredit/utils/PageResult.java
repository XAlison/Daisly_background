package com.icekredit.utils;

import java.util.List;

public class PageResult<T> {
	private Integer allRow=0;
	private Integer currentPage =1;
	private Integer size =5;
	private Integer pageTotal;
    private List<T> dataList;
	public PageResult(){}
	public PageResult(Integer size){
		this.size=size;
	}
	public Integer getAllRow() {
		return allRow;
	}
	public void setAllRow(Integer allRow) {
		this.pageTotal = (allRow+size-1)/size;
		this.allRow = allRow;
	}
	public Integer getCurrentPage() {
		if(currentPage < 1)
			return 1;
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		if(currentPage != null)
			this.currentPage = currentPage;
	}
	public void setStrCurrentPage(String str) {
		try{
			this.currentPage = Integer.parseInt(str);
		}catch(Exception e){
			this.currentPage =1;
		};
		if(this.currentPage<1)
			this.currentPage=1;
	}
	public Integer getStart(){
		return  this.getSize() * (this.getCurrentPage() - 1);
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public Integer getTotalPage() {
		return pageTotal;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setTotalPage(Integer pageTotal) {
		this.pageTotal = pageTotal;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}


}
