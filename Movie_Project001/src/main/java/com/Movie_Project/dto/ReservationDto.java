package com.Movie_Project.dto;

public class ReservationDto {
	private String recode;
	private String remid;
	private String rethcode;
	private String reroom;
	private String redate;
	private String remvcode;
	private int recount;
	
	
	
	public String getRecode() {
		return recode;
	}
	public void setRecode(String recode) {
		this.recode = recode;
	}
	public String getRemid() {
		return remid;
	}
	public void setRemid(String remid) {
		this.remid = remid;
	}
	public String getRethcode() {
		return rethcode;
	}
	public void setRethcode(String rethcode) {
		this.rethcode = rethcode;
	}
	public String getReroom() {
		return reroom;
	}
	public void setReroom(String reroom) {
		this.reroom = reroom;
	}
	public String getRedate() {
		return redate;
	}
	public void setRedate(String redate) {
		this.redate = redate;
	}
	public String getRemvcode() {
		return remvcode;
	}
	public void setRemvcode(String remvcode) {
		this.remvcode = remvcode;
	}
	public int getRecount() {
		return recount;
	}
	public void setRecount(int recount) {
		this.recount = recount;
	}
	@Override
	public String toString() {
		return "ReservationDto [recode=" + recode + ", remid=" + remid + ", rethcode=" + rethcode + ", reroom=" + reroom
				+ ", redate=" + redate + ", remvcode=" + remvcode + ", recount=" + recount + "]";
	}
	
	
	
	
}
