package com.Movie_Project.dto;

public class ReviewsDto {
	private String rvrecode;
	private String rvmid;
	private String rvcomment;
	private String rvrecommend;
	private String rvdate;
	private String rvmvtitle;
	
	
	public String getRvmvtitle() {
		return rvmvtitle;
	}
	public void setRvmvtitle(String rvmvtitle) {
		this.rvmvtitle = rvmvtitle;
	}
	public String getRvrecode() {
		return rvrecode;
	}
	public void setRvrecode(String rvrecode) {
		this.rvrecode = rvrecode;
	}
	public String getRvmid() {
		return rvmid;
	}
	public void setRvmid(String rvmid) {
		this.rvmid = rvmid;
	}
	public String getRvcomment() {
		return rvcomment;
	}
	public void setRvcomment(String rvcomment) {
		this.rvcomment = rvcomment;
	}
	public String getRvrecommend() {
		return rvrecommend;
	}
	public void setRvrecommend(String rvrecommend) {
		this.rvrecommend = rvrecommend;
	}
	public String getRvdate() {
		return rvdate;
	}
	public void setRvdate(String rvdate) {
		this.rvdate = rvdate;
	}
	@Override
	public String toString() {
		return "ReviewsDto [rvrecode=" + rvrecode + ", rvmid=" + rvmid + ", rvcomment=" + rvcomment + ", rvrecommend="
				+ rvrecommend + ", rvdate=" + rvdate + "]";
	}
	
	
	
	
	
	
}
