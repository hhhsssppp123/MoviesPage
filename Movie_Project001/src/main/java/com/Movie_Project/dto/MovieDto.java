package com.Movie_Project.dto;

public class MovieDto {
	
	private String mvcode;
	private String mvtitle;
	private String mvdir;
	private String mvact;
	private String mvgenre;
	private String mvinfo;
	private String mvdate;
	private String mvpos;
	private String mvrecount;
	
	
	
	public String getMvrecount() {
		return mvrecount;
	}
	public void setMvrecount(String mvrecount) {
		this.mvrecount = mvrecount;
	}
	public String getMvcode() {
		return mvcode;
	}
	public void setMvcode(String mvcode) {
		this.mvcode = mvcode;
	}
	public String getMvtitle() {
		return mvtitle;
	}
	public void setMvtitle(String mvtitle) {
		this.mvtitle = mvtitle;
	}
	public String getMvdir() {
		return mvdir;
	}
	public void setMvdir(String mvdir) {
		this.mvdir = mvdir;
	}
	public String getMvact() {
		return mvact;
	}
	public void setMvact(String mvact) {
		this.mvact = mvact;
	}
	public String getMvgenre() {
		return mvgenre;
	}
	public void setMvgenre(String mvgenre) {
		this.mvgenre = mvgenre;
	}
	public String getMvinfo() {
		return mvinfo;
	}
	public void setMvinfo(String mvinfo) {
		this.mvinfo = mvinfo;
	}
	public String getMvdate() {
		return mvdate;
	}
	public void setMvdate(String mvdate) {
		this.mvdate = mvdate;
	}
	public String getMvpos() {
		return mvpos;
	}
	public void setMvpos(String mvpos) {
		this.mvpos = mvpos;
	}
	@Override
	public String toString() {
		return "MovieDto [mvcode=" + mvcode + ", mvtitle=" + mvtitle + ", mvdir=" + mvdir + ", mvact=" + mvact
				+ ", mvgenre=" + mvgenre + ", mvinfo=" + mvinfo + ", mvdate=" + mvdate + ", mvpos=" + mvpos + "]";
	}
	
	
		
}
