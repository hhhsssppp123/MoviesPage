package com.Movie_Project.dto;

public class myInfoDto {
	
	private String mvtitle;
	private String thname;
	private String redate;
	private int recount;
	private String mvpos;
	private String reroom;
	private String recode;
	
	
	
	public String getRecode() {
		return recode;
	}
	public void setRecode(String recode) {
		this.recode = recode;
	}
	public String getMvpos() {
		return mvpos;
	}
	public void setMvpos(String mvpos) {
		this.mvpos = mvpos;
	}
	public String getReroom() {
		return reroom;
	}
	public void setReroom(String reroom) {
		this.reroom = reroom;
	}
	public String getMvtitle() {
		return mvtitle;
	}
	public void setMvtitle(String mvtitle) {
		this.mvtitle = mvtitle;
	}
	public String getThname() {
		return thname;
	}
	public void setThname(String thname) {
		this.thname = thname;
	}
	public String getRedate() {
		return redate;
	}
	public void setRedate(String redate) {
		this.redate = redate;
	}
	public int getRecount() {
		return recount;
	}
	public void setRecount(int recount) {
		this.recount = recount;
	}
	@Override
	public String toString() {
		return "myInfoDto [mvtitle=" + mvtitle + ", thname=" + thname + ", redate=" + redate + ", recount=" + recount
				+ ", mvpos=" + mvpos + ", reroom=" + reroom + ", recode=" + recode + "]";
	}
	
	
	
	
	
}
