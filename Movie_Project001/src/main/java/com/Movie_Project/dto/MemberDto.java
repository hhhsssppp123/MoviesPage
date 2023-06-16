package com.Movie_Project.dto;

import org.springframework.web.multipart.MultipartFile;

public class MemberDto {
	private String mid;
	private String mpw;
	private String mname;
	private String mbirth;
	private String maddr;
	private String memail;
	private String mprofile;
	private String mstate;
	
	
	
	private MultipartFile mfile;



	public String getMid() {
		return mid;
	}



	public void setMid(String mid) {
		this.mid = mid;
	}



	public String getMpw() {
		return mpw;
	}



	public void setMpw(String mpw) {
		this.mpw = mpw;
	}



	public String getMname() {
		return mname;
	}



	public void setMname(String mname) {
		this.mname = mname;
	}



	public String getMbirth() {
		return mbirth;
	}



	public void setMbirth(String mbirth) {
		this.mbirth = mbirth;
	}



	public String getMaddr() {
		return maddr;
	}



	public void setMaddr(String maddr) {
		this.maddr = maddr;
	}



	public String getMemail() {
		return memail;
	}



	public void setMemail(String memail) {
		this.memail = memail;
	}



	public String getMprofile() {
		return mprofile;
	}



	public void setMprofile(String mprofile) {
		this.mprofile = mprofile;
	}



	public String getMstate() {
		return mstate;
	}



	public void setMstate(String mstate) {
		this.mstate = mstate;
	}



	public MultipartFile getMfile() {
		return mfile;
	}



	public void setMfile(MultipartFile mfile) {
		this.mfile = mfile;
	}



	@Override
	public String toString() {
		return "MemberDto [mid=" + mid + ", mpw=" + mpw + ", mname=" + mname + ", mbirth=" + mbirth + ", maddr=" + maddr
				+ ", memail=" + memail + ", mprofile=" + mprofile + ", mstate=" + mstate + ", mfile=" + mfile + "]";
	}
	
	
	
	
	
	
	
	
	
}
