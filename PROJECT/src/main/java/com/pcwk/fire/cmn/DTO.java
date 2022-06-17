package com.pcwk.fire.cmn;

public class DTO {
	// 글 번호
	private int num;
	// 총 글수
	private int totalCnt;
	
	public DTO() {
		
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getTotalCnt() {
		return totalCnt;
	}

	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}

	@Override
	public String toString() {
		return "DTO [num=" + num + ", totalCnt=" + totalCnt + "]";
	}
	
}
