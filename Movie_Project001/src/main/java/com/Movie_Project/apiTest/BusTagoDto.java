package com.Movie_Project.apiTest;

public class BusTagoDto {

	private String nodeid; 				//정류소ID
	private String nodenm; 				//정류소명
	private String routeid;				//노선ID
	private String routeno;				//노선번호
	private String routetp;				//노선유형
	private String arrprevstationcnt;   //남은 정류장 수
	private String vehicletp;			//차량유형
	private String arrtime;				//도착예상시간[초]
	
	public String getNodeid() {
		return nodeid;
	}
	public void setNodeid(String nodeid) {
		this.nodeid = nodeid;
	}
	public String getNodenm() {
		return nodenm;
	}
	public void setNodenm(String nodenm) {
		this.nodenm = nodenm;
	}
	public String getRouteid() {
		return routeid;
	}
	public void setRouteid(String routeid) {
		this.routeid = routeid;
	}
	public String getRouteno() {
		return routeno;
	}
	public void setRouteno(String routeno) {
		this.routeno = routeno;
	}
	public String getRoutetp() {
		return routetp;
	}
	public void setRoutetp(String routetp) {
		this.routetp = routetp;
	}
	public String getArrprevstationcnt() {
		return arrprevstationcnt;
	}
	public void setArrprevstationcnt(String arrprevstationcnt) {
		this.arrprevstationcnt = arrprevstationcnt;
	}
	public String getVehicletp() {
		return vehicletp;
	}
	public void setVehicletp(String vehicletp) {
		this.vehicletp = vehicletp;
	}
	public String getArrtime() {
		return arrtime;
	}
	public void setArrtime(String arrtime) {
		this.arrtime = arrtime;
	}
	
	
	
}
/*
정류소ID	nodeid	30	필수	DJB8001793	정류소ID
정류소명	nodenm	30	필수	북대전농협	정류소명
노선ID	routeid	30	필수	DJB30300002	노선ID
노선번호	routeno	30	필수	5	노선번호
노선유형	routetp	10	필수	마을버스	노선유형
도착예정버스 남은 정류장 수	arrprevstationcnt	3	필수	15	도착예정버스 남은 정류장 수
도착예정버스 차량유형	vehicletp	10	필수	저상버스	도착예정버스 차량유형
도착예정버스 도착예상시간	arrtime	5	필수	816	도착예정버스 도착예상시간[초]
*/