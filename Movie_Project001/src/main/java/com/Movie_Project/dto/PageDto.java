package com.Movie_Project.dto;

public class PageDto {
	private String mvcode;
	private int reviewPage;
	private int startPageNum;
	private int endPageNum;
	private int maxPageNum;
	
	public String getMvcode() {
		return mvcode;
	}
	public void setMvcode(String mvcode) {
		this.mvcode = mvcode;
	}
	public int getReviewPage() {
		return reviewPage;
	}
	public void setReviewPage(int reviewPage) {
		this.reviewPage = reviewPage;
	}
	public int getStartPageNum() {
		return startPageNum;
	}
	public void setStartPageNum(int startPageNum) {
		this.startPageNum = startPageNum;
	}
	public int getEndPageNum() {
		return endPageNum;
	}
	public void setEndPageNum(int endPageNum) {
		this.endPageNum = endPageNum;
	}
	public int getMaxPageNum() {
		return maxPageNum;
	}
	public void setMaxPageNum(int maxPageNum) {
		this.maxPageNum = maxPageNum;
	}
	
	
	
}
