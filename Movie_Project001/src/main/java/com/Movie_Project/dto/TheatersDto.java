package com.Movie_Project.dto;

public class TheatersDto {
	private String thcode;
	private String thname;
	private String thaddr;
	private String thtel;
	public String getThcode() {
		return thcode;
	}
	public void setThcode(String thcode) {
		this.thcode = thcode;
	}
	public String getThname() {
		return thname;
	}
	public void setThname(String thname) {
		this.thname = thname;
	}
	public String getThaddr() {
		return thaddr;
	}
	public void setThaddr(String thaddr) {
		this.thaddr = thaddr;
	}
	public String getThtel() {
		return thtel;
	}
	public void setThtel(String thtel) {
		this.thtel = thtel;
	}
	@Override
	public String toString() {
		return "TheatersDto [thcode=" + thcode + ", thname=" + thname + ", thaddr=" + thaddr + ", thtel=" + thtel + "]";
	}
	
	
	
	
}
