package com.Movie_Project.dto;


public class TestDto {

	private String tid;
	private String t1;
	
	
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getT1() {
		return t1;
	}
	public void setT1(String t1) {
		this.t1 = t1;
	}
	@Override
	public String toString() {
		return "TestDto [tid=" + tid + ", t1=" + t1 + "]";
	}
	
	
	
}
