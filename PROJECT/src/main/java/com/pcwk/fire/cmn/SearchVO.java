package com.pcwk.fire.cmn;

public class SearchVO extends DTO {
	
	private int pageSize;		// 10, 20, 30, 50, 100
	private int pageNum; 		// 1, 2, 3, 4, ... 10
	private String searchDiv; 	// 검색 구분
	private String searcWord;	// 검색어
	
	public SearchVO() {
		
	}

	public SearchVO(int pageSize, int pageNum, String searchDiv, String searcWord) {
		super();
		this.pageSize = pageSize;
		this.pageNum = pageNum;
		this.searchDiv = searchDiv;
		this.searcWord = searcWord;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getSearchDiv() {
		return searchDiv;
	}

	public void setSearchDiv(String searchDiv) {
		this.searchDiv = searchDiv;
	}

	public String getSearcWord() {
		return searcWord;
	}

	public void setSearcWord(String searcWord) {
		this.searcWord = searcWord;
	}

	@Override
	public String toString() {
		return "SearchVO [pageSize=" + pageSize + ", pageNum=" + pageNum + ", searchDiv=" + searchDiv + ", searcWord="
				+ searcWord + ", toString()=" + super.toString() + "]";
	}
	
}
