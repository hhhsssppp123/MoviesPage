package com.Movie_Project.dto;

public class MovieStroyDto {
	private String mscode;
	private String mstitle;
	private String msstory;
	
	
	public String getMstitle() {
		return mstitle;
	}
	public void setMstitle(String mstitle) {
		this.mstitle = mstitle;
	}
	public String getMscode() {
		return mscode;
	}
	public void setMscode(String mscode) {
		this.mscode = mscode;
	}
	public String getMsstory() {
		return msstory;
	}
	public void setMsstory(String msstory) {
		this.msstory = msstory;
	}
	@Override
	public String toString() {
		return "MovieStroyDto [mscode=" + mscode + ", mstitle=" + mstitle + ", msstory=" + msstory + "]";
	}
	
	
	
	
	
}
