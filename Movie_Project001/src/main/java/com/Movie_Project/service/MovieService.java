package com.Movie_Project.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Movie_Project.dao.MovieDao;
import com.Movie_Project.dto.MovieDto;
import com.Movie_Project.dto.MovieStroyDto;
import com.Movie_Project.dto.ReservationDto;
import com.Movie_Project.dto.ScheduleDto;
import com.Movie_Project.dto.TheatersDto;
import com.Movie_Project.dto.cdto;
import com.Movie_Project.dto.epDto;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

@Service
public class MovieService {

	@Autowired
	private MovieDao mvdao;
	
	public int addMovieList() throws IOException {
				
		
		
				//1. CGV 영화 페이지 URL
				String cgvUrl = "http://www.cgv.co.kr/movies/?lt=1&ft=0";
				
				//2. Jsoup URL 접속
				Document doc = Jsoup.connect(cgvUrl).get();
				//System.out.println(doc);
				
				Elements charDiv = doc.select("#contents > div.wrap-movie-chart > div.sect-movie-chart");
				
				Elements movLi = charDiv.select("li");
				//div.box-image > a
				Elements mova = movLi.select("div.box-image > a");
				
				int insertResult = 0;
				for(int i = 0; i < movLi.size(); i++) {
					//5. 1~ 19위 영화의 상세페이지 URL 추출
					String detailUrl = "http://www.cgv.co.kr"+movLi.eq(i).select("div.box-image > a").eq(0).attr("href");
					System.out.println(detailUrl);
					Document detailDoc = Jsoup.connect(detailUrl).get();
					Elements baseMovie = detailDoc.select("#select_main > div.sect-base-movie");
					
					
					String mvtitle = baseMovie.select("div.box-contents > div.title > strong").eq(0).text();
					System.out.println("영화제목 : " + mvtitle);

					/*
					 * String mvdir =
					 * baseMovie.select("div.box-contents > div.spec > dl > dd:nth-child(2)").eq(0).
					 * text(); System.out.println("영화감독 : " + mvdir);
					 * 
					 * String mvact =
					 * baseMovie.select("div.box-contents > div.spec > dl > dd.on").eq(0).text().
					 * replace(" , ", ","); System.out.println("출연배우 : " + mvact);
					 * 
					 * String mvinfo =
					 * baseMovie.select("div.box-contents > div.spec > dl > dd.on").eq(1).text().
					 * replace(" ", ""); System.out.println("기본정보 : " + mvinfo);
					 * 
					 * String mvdate =
					 * baseMovie.select("div.box-contents > div.spec > dl > dd.on").eq(2).text().
					 * replace("(재개봉)", ""); System.out.println("개봉일 : " + mvdate);
					 * 
					 * String mvgenre =
					 * baseMovie.select("div.box-contents > div.spec > dl > dd.on").eq(0).next().
					 * text().replace("장르 :", "").trim(); System.out.println("장르 : "+mvgenre);
					 * 
					 * String mvpos = baseMovie.select("div.box-image > a").eq(0).attr("href");
					 * System.out.println("영화포스터 : "+ mvpos);
					 */
					
					Elements baseMvdetail = detailDoc.select("#menu > div.col-detail");
					String mvStroy =  baseMvdetail.select("div.sect-story-movie").text();
					System.out.println("영화줄거리 : "+mvStroy);
					
					
					
					MovieDto movie = new MovieDto();
					MovieStroyDto ms = new MovieStroyDto();
					//무비테이블 
//					movie.setMvtitle(mvtitle);
//					movie.setMvdir(mvdir);
//					movie.setMvact(mvact);
//					movie.setMvgenre(mvgenre);
//					movie.setMvinfo(mvinfo);
//					movie.setMvdate(mvdate);
//					movie.setMvpos(mvpos);
					//무비스토리 테이블
					ms.setMsstory(mvStroy);
					ms.setMstitle(mvtitle);
					
					
					//String maxMvcode = mvdao.selectMaxMvCode();
					String maxMvcode = mvdao.selectMaxMvCodeMvStory();
					System.out.println("영화코드 최대값 : " + maxMvcode);
					String mvcode = "MV";
					if(maxMvcode == null) {
						mvcode = mvcode + String.format("%03d", 1);
						System.out.println("처음 영화코드 : "+mvcode);
					}else {
						int mvcodeNum = Integer.parseInt(maxMvcode.replace("MV", "")) +1;
						mvcode =  mvcode + String.format("%03d", mvcodeNum);
					}
					System.out.println("영화코드 : "+mvcode);
					movie.setMvcode(mvcode);
					ms.setMscode(mvcode);
					
					//String mvCheck = mvdao.checkMovies(mvtitle, mvpos);
					//String mvCheck = mvdao.checkMovies2(mvtitle, mvdir);
					String mvCheck = mvdao.checkMvStory(mvtitle, mvStroy);
					if(mvCheck != null) {
						continue;
					}
					//insertResult += mvdao.insertMovie(movie);
					insertResult += mvdao.insertMS(ms);
					
					
					
					
					System.out.println();
					
				}
				
				System.out.println("등록된 영화 수 : " + insertResult);
		return 0;
		
		
	}

	public int addthList() throws IOException {
		/*
		var theaterJsonData = [{"AreaTheaterDetailList":[{"RegionCode":"01","TheaterCode":"0056","TheaterName":"CGV강남","TheaterName_ENG":null,"IsSelected":true},{"RegionCode":"01","TheaterCode":"0001","TheaterName":"CGV강변","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"01","TheaterCode":"0229","TheaterName":"CGV건대입구","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"01","TheaterCode":"0010","TheaterName":"CGV구로","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"01","TheaterCode":"0063","TheaterName":"CGV대학로","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"01","TheaterCode":"0252","TheaterName":"CGV동대문","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"01","TheaterCode":"0230","TheaterName":"CGV등촌","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"01","TheaterCode":"0009","TheaterName":"CGV명동","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"01","TheaterCode":"0105","TheaterName":"CGV명동역 씨네라이브러리","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"01","TheaterCode":"0057","TheaterName":"CGV미아","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"01","TheaterCode":"0288","TheaterName":"CGV방학","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"01","TheaterCode":"0030","TheaterName":"CGV불광","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"01","TheaterCode":"0046","TheaterName":"CGV상봉","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"01","TheaterCode":"0300","TheaterName":"CGV성신여대입구","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"01","TheaterCode":"0088","TheaterName":"CGV송파","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"01","TheaterCode":"0276","TheaterName":"CGV수유","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"01","TheaterCode":"0150","TheaterName":"CGV신촌아트레온","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"01","TheaterCode":"0040","TheaterName":"CGV압구정","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"01","TheaterCode":"0112","TheaterName":"CGV여의도","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"01","TheaterCode":"0292","TheaterName":"CGV연남","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"01","TheaterCode":"0059","TheaterName":"CGV영등포","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"01","TheaterCode":"0074","TheaterName":"CGV왕십리","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"01","TheaterCode":"0013","TheaterName":"CGV용산아이파크몰","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"01","TheaterCode":"0131","TheaterName":"CGV중계","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"01","TheaterCode":"0199","TheaterName":"CGV천호","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"01","TheaterCode":"0107","TheaterName":"CGV청담씨네시티","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"01","TheaterCode":"0223","TheaterName":"CGV피카디리1958","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"01","TheaterCode":"0164","TheaterName":"CGV하계","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"01","TheaterCode":"0191","TheaterName":"CGV홍대","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"01","TheaterCode":"P001","TheaterName":"CINE de CHEF 압구정","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"01","TheaterCode":"P013","TheaterName":"CINE de CHEF 용산아이파크몰","TheaterName_ENG":null,"IsSelected":false}],"RegionCode":"01","RegionName":"서울","RegionName_ENG":"Seoul","DisplayOrder":"1","IsSelected":true},{"AreaTheaterDetailList":[{"RegionCode":"02","TheaterCode":"0260","TheaterName":"CGV경기광주","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0255","TheaterName":"CGV고양행신","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0257","TheaterName":"CGV광교","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0266","TheaterName":"CGV광교상현","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0348","TheaterName":"CGV광명역","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0232","TheaterName":"CGV구리","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0344","TheaterName":"CGV기흥","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0278","TheaterName":"CGV김포","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0188","TheaterName":"CGV김포운양","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0298","TheaterName":"CGV김포한강","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0351","TheaterName":"CGV다산","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0124","TheaterName":"CGV동백","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0041","TheaterName":"CGV동수원","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0106","TheaterName":"CGV동탄","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0265","TheaterName":"CGV동탄역","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0233","TheaterName":"CGV동탄호수공원","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0226","TheaterName":"CGV배곧","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0155","TheaterName":"CGV범계","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0015","TheaterName":"CGV부천","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0194","TheaterName":"CGV부천역","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0287","TheaterName":"CGV부천옥길","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0049","TheaterName":"CGV북수원","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0242","TheaterName":"CGV산본","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0196","TheaterName":"CGV서현","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0304","TheaterName":"CGV성남모란","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0143","TheaterName":"CGV소풍","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0012","TheaterName":"CGV수원","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0274","TheaterName":"CGV스타필드시티위례","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0073","TheaterName":"CGV시흥","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0211","TheaterName":"CGV안산","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0279","TheaterName":"CGV안성","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0003","TheaterName":"CGV야탑","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0262","TheaterName":"CGV양주옥정","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0338","TheaterName":"CGV역곡","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0004","TheaterName":"CGV오리(임시휴업)","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0305","TheaterName":"CGV오산","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0307","TheaterName":"CGV오산중앙","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0271","TheaterName":"CGV용인","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0113","TheaterName":"CGV의정부","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0187","TheaterName":"CGV의정부태흥","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0205","TheaterName":"CGV이천","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0054","TheaterName":"CGV일산","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0320","TheaterName":"CGV정왕","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0055","TheaterName":"CGV죽전(리뉴얼중)","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0148","TheaterName":"CGV파주문산","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0310","TheaterName":"CGV파주야당","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0181","TheaterName":"CGV판교","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0195","TheaterName":"CGV평촌","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0052","TheaterName":"CGV평택","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0334","TheaterName":"CGV평택고덕","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0214","TheaterName":"CGV평택소사","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0309","TheaterName":"CGV포천","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0326","TheaterName":"CGV하남미사","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0301","TheaterName":"CGV화성봉담","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0145","TheaterName":"CGV화정","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"02","TheaterCode":"0342","TheaterName":"DRIVE IN 곤지암","TheaterName_ENG":null,"IsSelected":false}],"RegionCode":"02","RegionName":"경기","RegionName_ENG":"Gyeonggi","DisplayOrder":"2","IsSelected":false},{"AreaTheaterDetailList":[{"RegionCode":"202","TheaterCode":"0043","TheaterName":"CGV계양","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"202","TheaterCode":"0198","TheaterName":"CGV남주안","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"202","TheaterCode":"0021","TheaterName":"CGV부평","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"202","TheaterCode":"0325","TheaterName":"CGV송도타임스페이스","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"202","TheaterCode":"0247","TheaterName":"CGV연수역","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"202","TheaterCode":"0002","TheaterName":"CGV인천","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"202","TheaterCode":"0254","TheaterName":"CGV인천논현","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"202","TheaterCode":"0340","TheaterName":"CGV인천도화","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"202","TheaterCode":"0258","TheaterName":"CGV인천연수","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"202","TheaterCode":"0269","TheaterName":"CGV인천학익","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"202","TheaterCode":"0308","TheaterName":"CGV주안역","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"202","TheaterCode":"0235","TheaterName":"CGV청라","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"202","TheaterCode":"0339","TheaterName":"DRIVE IN 스퀘어원","TheaterName_ENG":null,"IsSelected":false}],"RegionCode":"202","RegionName":"인천","RegionName_ENG":"Incheon","DisplayOrder":"3","IsSelected":false},{"AreaTheaterDetailList":[{"RegionCode":"12","TheaterCode":"0139","TheaterName":"CGV강릉","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"12","TheaterCode":"0144","TheaterName":"CGV원주","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"12","TheaterCode":"0354","TheaterName":"CGV원통","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"12","TheaterCode":"0281","TheaterName":"CGV인제","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"12","TheaterCode":"0070","TheaterName":"CGV춘천","TheaterName_ENG":null,"IsSelected":false}],"RegionCode":"12","RegionName":"강원","RegionName_ENG":"Kangwon","DisplayOrder":"4","IsSelected":false},{"AreaTheaterDetailList":[{"RegionCode":"205","TheaterCode":"0261","TheaterName":"CGV논산","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"205","TheaterCode":"0207","TheaterName":"CGV당진","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"03","TheaterCode":"0007","TheaterName":"CGV대전","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"03","TheaterCode":"0286","TheaterName":"CGV대전가수원","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"03","TheaterCode":"0154","TheaterName":"CGV대전가오","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"03","TheaterCode":"0202","TheaterName":"CGV대전탄방","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"03","TheaterCode":"0127","TheaterName":"CGV대전터미널","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"205","TheaterCode":"0275","TheaterName":"CGV보령","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"205","TheaterCode":"0091","TheaterName":"CGV서산","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"205","TheaterCode":"0219","TheaterName":"CGV세종","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"03","TheaterCode":"0206","TheaterName":"CGV유성노은","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"205","TheaterCode":"0331","TheaterName":"CGV제천","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"205","TheaterCode":"0044","TheaterName":"CGV천안","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"205","TheaterCode":"0332","TheaterName":"CGV천안시청","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"205","TheaterCode":"0293","TheaterName":"CGV천안터미널","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"205","TheaterCode":"0110","TheaterName":"CGV천안펜타포트","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"205","TheaterCode":"0228","TheaterName":"CGV청주(서문)","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"205","TheaterCode":"0297","TheaterName":"CGV청주성안길","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"205","TheaterCode":"0282","TheaterName":"CGV청주율량","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"205","TheaterCode":"0142","TheaterName":"CGV청주지웰시티","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"205","TheaterCode":"0319","TheaterName":"CGV청주터미널","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"205","TheaterCode":"0284","TheaterName":"CGV충북혁신","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"205","TheaterCode":"0328","TheaterName":"CGV충주교현","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"205","TheaterCode":"0217","TheaterName":"CGV홍성","TheaterName_ENG":null,"IsSelected":false}],"RegionCode":"03,205","RegionName":"대전/충청","RegionName_ENG":"Daejeon/Chungcheong","DisplayOrder":"5","IsSelected":false},{"AreaTheaterDetailList":[{"RegionCode":"11","TheaterCode":"0157","TheaterName":"CGV대구수성","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"11","TheaterCode":"0108","TheaterName":"CGV대구스타디움","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"11","TheaterCode":"0185","TheaterName":"CGV대구아카데미","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"11","TheaterCode":"0343","TheaterName":"CGV대구연경","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"11","TheaterCode":"0216","TheaterName":"CGV대구월성","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"11","TheaterCode":"0147","TheaterName":"CGV대구한일","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"11","TheaterCode":"0109","TheaterName":"CGV대구현대","TheaterName_ENG":null,"IsSelected":false}],"RegionCode":"11","RegionName":"대구","RegionName_ENG":"Daegu","DisplayOrder":"6","IsSelected":false},{"AreaTheaterDetailList":[{"RegionCode":"05","TheaterCode":"0061","TheaterName":"CGV대연","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"05","TheaterCode":"0042","TheaterName":"CGV동래","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"05","TheaterCode":"0337","TheaterName":"CGV부산명지","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"05","TheaterCode":"0005","TheaterName":"CGV서면","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"05","TheaterCode":"0285","TheaterName":"CGV서면삼정타워","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"05","TheaterCode":"0303","TheaterName":"CGV서면상상마당","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"05","TheaterCode":"0089","TheaterName":"CGV센텀시티","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"05","TheaterCode":"0160","TheaterName":"CGV아시아드","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"207","TheaterCode":"0335","TheaterName":"CGV울산동구","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"207","TheaterCode":"0128","TheaterName":"CGV울산삼산","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"207","TheaterCode":"0264","TheaterName":"CGV울산신천","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"207","TheaterCode":"0246","TheaterName":"CGV울산진장","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"05","TheaterCode":"0306","TheaterName":"CGV정관","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"05","TheaterCode":"0245","TheaterName":"CGV하단아트몰링","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"05","TheaterCode":"0318","TheaterName":"CGV해운대","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"05","TheaterCode":"0159","TheaterName":"CGV화명","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"05","TheaterCode":"P004","TheaterName":"CINE de CHEF 센텀","TheaterName_ENG":null,"IsSelected":false}],"RegionCode":"05,207","RegionName":"부산/울산","RegionName_ENG":"Busan/Ulsan","DisplayOrder":"7","IsSelected":false},{"AreaTheaterDetailList":[{"RegionCode":"204","TheaterCode":"0263","TheaterName":"CGV거제","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"204","TheaterCode":"0330","TheaterName":"CGV경산","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"204","TheaterCode":"0323","TheaterName":"CGV고성","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"204","TheaterCode":"0053","TheaterName":"CGV구미","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"204","TheaterCode":"0240","TheaterName":"CGV김천율곡","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"204","TheaterCode":"0028","TheaterName":"CGV김해","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"204","TheaterCode":"0311","TheaterName":"CGV김해율하","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"204","TheaterCode":"0239","TheaterName":"CGV김해장유","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"204","TheaterCode":"0033","TheaterName":"CGV마산","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"204","TheaterCode":"0097","TheaterName":"CGV북포항","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"204","TheaterCode":"0272","TheaterName":"CGV안동","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"204","TheaterCode":"0234","TheaterName":"CGV양산삼호","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"204","TheaterCode":"0324","TheaterName":"CGV진주혁신","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"204","TheaterCode":"0023","TheaterName":"CGV창원","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"204","TheaterCode":"0079","TheaterName":"CGV창원더시티","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"204","TheaterCode":"0283","TheaterName":"CGV창원상남","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"204","TheaterCode":"0156","TheaterName":"CGV통영","TheaterName_ENG":null,"IsSelected":false}],"RegionCode":"204","RegionName":"경상","RegionName_ENG":"Gyeongsang","DisplayOrder":"8","IsSelected":false},{"AreaTheaterDetailList":[{"RegionCode":"04","TheaterCode":"0220","TheaterName":"CGV광양","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"04","TheaterCode":"0221","TheaterName":"CGV광양 엘에프스퀘어","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"206","TheaterCode":"0295","TheaterName":"CGV광주금남로","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"206","TheaterCode":"0193","TheaterName":"CGV광주상무","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"206","TheaterCode":"0210","TheaterName":"CGV광주용봉","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"206","TheaterCode":"0218","TheaterName":"CGV광주첨단","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"206","TheaterCode":"0244","TheaterName":"CGV광주충장로","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"206","TheaterCode":"0090","TheaterName":"CGV광주터미널","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"206","TheaterCode":"0215","TheaterName":"CGV광주하남","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"04","TheaterCode":"0277","TheaterName":"CGV군산","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"04","TheaterCode":"0237","TheaterName":"CGV나주","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"04","TheaterCode":"0289","TheaterName":"CGV목포","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"04","TheaterCode":"0280","TheaterName":"CGV목포평화광장","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"04","TheaterCode":"0225","TheaterName":"CGV서전주","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"04","TheaterCode":"0114","TheaterName":"CGV순천","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"04","TheaterCode":"0268","TheaterName":"CGV순천신대","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"04","TheaterCode":"0315","TheaterName":"CGV여수웅천","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"04","TheaterCode":"0020","TheaterName":"CGV익산","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"04","TheaterCode":"0213","TheaterName":"CGV전주고사","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"04","TheaterCode":"0336","TheaterName":"CGV전주에코시티","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"04","TheaterCode":"0179","TheaterName":"CGV전주효자","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"04","TheaterCode":"0186","TheaterName":"CGV정읍","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"06","TheaterCode":"0302","TheaterName":"CGV제주","TheaterName_ENG":null,"IsSelected":false},{"RegionCode":"06","TheaterCode":"0259","TheaterName":"CGV제주노형","TheaterName_ENG":null,"IsSelected":false}],"RegionCode":"206,04,06","RegionName":"광주/전라/제주","RegionName_ENG":"Gwangju/Jeonlla/Jeju","DisplayOrder":"9","IsSelected":false}];
	        
		
		
		*/
		
		String theaterList = "[{\"AreaTheaterDetailList\":[{\"RegionCode\":\"01\",\"TheaterCode\":\"0056\",\"TheaterName\":\"CGV강남\",\"TheaterName_ENG\":null,\"IsSelected\":true},{\"RegionCode\":\"01\",\"TheaterCode\":\"0001\",\"TheaterName\":\"CGV강변\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"01\",\"TheaterCode\":\"0229\",\"TheaterName\":\"CGV건대입구\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"01\",\"TheaterCode\":\"0010\",\"TheaterName\":\"CGV구로\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"01\",\"TheaterCode\":\"0063\",\"TheaterName\":\"CGV대학로\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"01\",\"TheaterCode\":\"0252\",\"TheaterName\":\"CGV동대문\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"01\",\"TheaterCode\":\"0230\",\"TheaterName\":\"CGV등촌\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"01\",\"TheaterCode\":\"0009\",\"TheaterName\":\"CGV명동\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"01\",\"TheaterCode\":\"0105\",\"TheaterName\":\"CGV명동역 씨네라이브러리\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"01\",\"TheaterCode\":\"0057\",\"TheaterName\":\"CGV미아\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"01\",\"TheaterCode\":\"0288\",\"TheaterName\":\"CGV방학\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"01\",\"TheaterCode\":\"0030\",\"TheaterName\":\"CGV불광\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"01\",\"TheaterCode\":\"0046\",\"TheaterName\":\"CGV상봉\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"01\",\"TheaterCode\":\"0300\",\"TheaterName\":\"CGV성신여대입구\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"01\",\"TheaterCode\":\"0088\",\"TheaterName\":\"CGV송파\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"01\",\"TheaterCode\":\"0276\",\"TheaterName\":\"CGV수유\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"01\",\"TheaterCode\":\"0150\",\"TheaterName\":\"CGV신촌아트레온\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"01\",\"TheaterCode\":\"0040\",\"TheaterName\":\"CGV압구정\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"01\",\"TheaterCode\":\"0112\",\"TheaterName\":\"CGV여의도\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"01\",\"TheaterCode\":\"0292\",\"TheaterName\":\"CGV연남\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"01\",\"TheaterCode\":\"0059\",\"TheaterName\":\"CGV영등포\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"01\",\"TheaterCode\":\"0074\",\"TheaterName\":\"CGV왕십리\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"01\",\"TheaterCode\":\"0013\",\"TheaterName\":\"CGV용산아이파크몰\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"01\",\"TheaterCode\":\"0131\",\"TheaterName\":\"CGV중계\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"01\",\"TheaterCode\":\"0199\",\"TheaterName\":\"CGV천호\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"01\",\"TheaterCode\":\"0107\",\"TheaterName\":\"CGV청담씨네시티\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"01\",\"TheaterCode\":\"0223\",\"TheaterName\":\"CGV피카디리1958\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"01\",\"TheaterCode\":\"0164\",\"TheaterName\":\"CGV하계\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"01\",\"TheaterCode\":\"0191\",\"TheaterName\":\"CGV홍대\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"01\",\"TheaterCode\":\"P001\",\"TheaterName\":\"CINE de CHEF 압구정\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"01\",\"TheaterCode\":\"P013\",\"TheaterName\":\"CINE de CHEF 용산아이파크몰\",\"TheaterName_ENG\":null,\"IsSelected\":false}],\"RegionCode\":\"01\",\"RegionName\":\"서울\",\"RegionName_ENG\":\"Seoul\",\"DisplayOrder\":\"1\",\"IsSelected\":true},{\"AreaTheaterDetailList\":[{\"RegionCode\":\"02\",\"TheaterCode\":\"0260\",\"TheaterName\":\"CGV경기광주\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0255\",\"TheaterName\":\"CGV고양행신\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0257\",\"TheaterName\":\"CGV광교\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0266\",\"TheaterName\":\"CGV광교상현\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0348\",\"TheaterName\":\"CGV광명역\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0232\",\"TheaterName\":\"CGV구리\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0344\",\"TheaterName\":\"CGV기흥\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0278\",\"TheaterName\":\"CGV김포\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0188\",\"TheaterName\":\"CGV김포운양\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0298\",\"TheaterName\":\"CGV김포한강\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0351\",\"TheaterName\":\"CGV다산\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0124\",\"TheaterName\":\"CGV동백\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0041\",\"TheaterName\":\"CGV동수원\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0106\",\"TheaterName\":\"CGV동탄\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0265\",\"TheaterName\":\"CGV동탄역\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0233\",\"TheaterName\":\"CGV동탄호수공원\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0226\",\"TheaterName\":\"CGV배곧\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0155\",\"TheaterName\":\"CGV범계\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0015\",\"TheaterName\":\"CGV부천\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0194\",\"TheaterName\":\"CGV부천역\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0287\",\"TheaterName\":\"CGV부천옥길\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0049\",\"TheaterName\":\"CGV북수원\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0242\",\"TheaterName\":\"CGV산본\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0196\",\"TheaterName\":\"CGV서현\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0304\",\"TheaterName\":\"CGV성남모란\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0143\",\"TheaterName\":\"CGV소풍\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0012\",\"TheaterName\":\"CGV수원\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0274\",\"TheaterName\":\"CGV스타필드시티위례\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0073\",\"TheaterName\":\"CGV시흥\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0211\",\"TheaterName\":\"CGV안산\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0279\",\"TheaterName\":\"CGV안성\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0003\",\"TheaterName\":\"CGV야탑\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0262\",\"TheaterName\":\"CGV양주옥정\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0338\",\"TheaterName\":\"CGV역곡\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0004\",\"TheaterName\":\"CGV오리(임시휴업)\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0305\",\"TheaterName\":\"CGV오산\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0307\",\"TheaterName\":\"CGV오산중앙\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0271\",\"TheaterName\":\"CGV용인\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0113\",\"TheaterName\":\"CGV의정부\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0187\",\"TheaterName\":\"CGV의정부태흥\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0205\",\"TheaterName\":\"CGV이천\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0054\",\"TheaterName\":\"CGV일산\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0320\",\"TheaterName\":\"CGV정왕\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0055\",\"TheaterName\":\"CGV죽전(리뉴얼중)\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0148\",\"TheaterName\":\"CGV파주문산\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0310\",\"TheaterName\":\"CGV파주야당\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0181\",\"TheaterName\":\"CGV판교\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0195\",\"TheaterName\":\"CGV평촌\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0052\",\"TheaterName\":\"CGV평택\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0334\",\"TheaterName\":\"CGV평택고덕\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0214\",\"TheaterName\":\"CGV평택소사\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0309\",\"TheaterName\":\"CGV포천\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0326\",\"TheaterName\":\"CGV하남미사\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0301\",\"TheaterName\":\"CGV화성봉담\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0145\",\"TheaterName\":\"CGV화정\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"02\",\"TheaterCode\":\"0342\",\"TheaterName\":\"DRIVE IN 곤지암\",\"TheaterName_ENG\":null,\"IsSelected\":false}],\"RegionCode\":\"02\",\"RegionName\":\"경기\",\"RegionName_ENG\":\"Gyeonggi\",\"DisplayOrder\":\"2\",\"IsSelected\":false},{\"AreaTheaterDetailList\":[{\"RegionCode\":\"202\",\"TheaterCode\":\"0043\",\"TheaterName\":\"CGV계양\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"202\",\"TheaterCode\":\"0198\",\"TheaterName\":\"CGV남주안\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"202\",\"TheaterCode\":\"0021\",\"TheaterName\":\"CGV부평\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"202\",\"TheaterCode\":\"0325\",\"TheaterName\":\"CGV송도타임스페이스\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"202\",\"TheaterCode\":\"0247\",\"TheaterName\":\"CGV연수역\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"202\",\"TheaterCode\":\"0002\",\"TheaterName\":\"CGV인천\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"202\",\"TheaterCode\":\"0254\",\"TheaterName\":\"CGV인천논현\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"202\",\"TheaterCode\":\"0340\",\"TheaterName\":\"CGV인천도화\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"202\",\"TheaterCode\":\"0258\",\"TheaterName\":\"CGV인천연수\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"202\",\"TheaterCode\":\"0269\",\"TheaterName\":\"CGV인천학익\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"202\",\"TheaterCode\":\"0308\",\"TheaterName\":\"CGV주안역\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"202\",\"TheaterCode\":\"0235\",\"TheaterName\":\"CGV청라\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"202\",\"TheaterCode\":\"0339\",\"TheaterName\":\"DRIVE IN 스퀘어원\",\"TheaterName_ENG\":null,\"IsSelected\":false}],\"RegionCode\":\"202\",\"RegionName\":\"인천\",\"RegionName_ENG\":\"Incheon\",\"DisplayOrder\":\"3\",\"IsSelected\":false},{\"AreaTheaterDetailList\":[{\"RegionCode\":\"12\",\"TheaterCode\":\"0139\",\"TheaterName\":\"CGV강릉\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"12\",\"TheaterCode\":\"0144\",\"TheaterName\":\"CGV원주\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"12\",\"TheaterCode\":\"0354\",\"TheaterName\":\"CGV원통\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"12\",\"TheaterCode\":\"0281\",\"TheaterName\":\"CGV인제\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"12\",\"TheaterCode\":\"0070\",\"TheaterName\":\"CGV춘천\",\"TheaterName_ENG\":null,\"IsSelected\":false}],\"RegionCode\":\"12\",\"RegionName\":\"강원\",\"RegionName_ENG\":\"Kangwon\",\"DisplayOrder\":\"4\",\"IsSelected\":false},{\"AreaTheaterDetailList\":[{\"RegionCode\":\"205\",\"TheaterCode\":\"0261\",\"TheaterName\":\"CGV논산\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"205\",\"TheaterCode\":\"0207\",\"TheaterName\":\"CGV당진\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"03\",\"TheaterCode\":\"0007\",\"TheaterName\":\"CGV대전\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"03\",\"TheaterCode\":\"0286\",\"TheaterName\":\"CGV대전가수원\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"03\",\"TheaterCode\":\"0154\",\"TheaterName\":\"CGV대전가오\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"03\",\"TheaterCode\":\"0202\",\"TheaterName\":\"CGV대전탄방\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"03\",\"TheaterCode\":\"0127\",\"TheaterName\":\"CGV대전터미널\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"205\",\"TheaterCode\":\"0275\",\"TheaterName\":\"CGV보령\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"205\",\"TheaterCode\":\"0091\",\"TheaterName\":\"CGV서산\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"205\",\"TheaterCode\":\"0219\",\"TheaterName\":\"CGV세종\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"03\",\"TheaterCode\":\"0206\",\"TheaterName\":\"CGV유성노은\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"205\",\"TheaterCode\":\"0331\",\"TheaterName\":\"CGV제천\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"205\",\"TheaterCode\":\"0044\",\"TheaterName\":\"CGV천안\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"205\",\"TheaterCode\":\"0332\",\"TheaterName\":\"CGV천안시청\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"205\",\"TheaterCode\":\"0293\",\"TheaterName\":\"CGV천안터미널\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"205\",\"TheaterCode\":\"0110\",\"TheaterName\":\"CGV천안펜타포트\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"205\",\"TheaterCode\":\"0228\",\"TheaterName\":\"CGV청주(서문)\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"205\",\"TheaterCode\":\"0297\",\"TheaterName\":\"CGV청주성안길\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"205\",\"TheaterCode\":\"0282\",\"TheaterName\":\"CGV청주율량\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"205\",\"TheaterCode\":\"0142\",\"TheaterName\":\"CGV청주지웰시티\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"205\",\"TheaterCode\":\"0319\",\"TheaterName\":\"CGV청주터미널\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"205\",\"TheaterCode\":\"0284\",\"TheaterName\":\"CGV충북혁신\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"205\",\"TheaterCode\":\"0328\",\"TheaterName\":\"CGV충주교현\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"205\",\"TheaterCode\":\"0217\",\"TheaterName\":\"CGV홍성\",\"TheaterName_ENG\":null,\"IsSelected\":false}],\"RegionCode\":\"03,205\",\"RegionName\":\"대전/충청\",\"RegionName_ENG\":\"Daejeon/Chungcheong\",\"DisplayOrder\":\"5\",\"IsSelected\":false},{\"AreaTheaterDetailList\":[{\"RegionCode\":\"11\",\"TheaterCode\":\"0157\",\"TheaterName\":\"CGV대구수성\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"11\",\"TheaterCode\":\"0108\",\"TheaterName\":\"CGV대구스타디움\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"11\",\"TheaterCode\":\"0185\",\"TheaterName\":\"CGV대구아카데미\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"11\",\"TheaterCode\":\"0343\",\"TheaterName\":\"CGV대구연경\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"11\",\"TheaterCode\":\"0216\",\"TheaterName\":\"CGV대구월성\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"11\",\"TheaterCode\":\"0147\",\"TheaterName\":\"CGV대구한일\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"11\",\"TheaterCode\":\"0109\",\"TheaterName\":\"CGV대구현대\",\"TheaterName_ENG\":null,\"IsSelected\":false}],\"RegionCode\":\"11\",\"RegionName\":\"대구\",\"RegionName_ENG\":\"Daegu\",\"DisplayOrder\":\"6\",\"IsSelected\":false},{\"AreaTheaterDetailList\":[{\"RegionCode\":\"05\",\"TheaterCode\":\"0061\",\"TheaterName\":\"CGV대연\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"05\",\"TheaterCode\":\"0042\",\"TheaterName\":\"CGV동래\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"05\",\"TheaterCode\":\"0337\",\"TheaterName\":\"CGV부산명지\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"05\",\"TheaterCode\":\"0005\",\"TheaterName\":\"CGV서면\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"05\",\"TheaterCode\":\"0285\",\"TheaterName\":\"CGV서면삼정타워\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"05\",\"TheaterCode\":\"0303\",\"TheaterName\":\"CGV서면상상마당\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"05\",\"TheaterCode\":\"0089\",\"TheaterName\":\"CGV센텀시티\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"05\",\"TheaterCode\":\"0160\",\"TheaterName\":\"CGV아시아드\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"207\",\"TheaterCode\":\"0335\",\"TheaterName\":\"CGV울산동구\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"207\",\"TheaterCode\":\"0128\",\"TheaterName\":\"CGV울산삼산\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"207\",\"TheaterCode\":\"0264\",\"TheaterName\":\"CGV울산신천\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"207\",\"TheaterCode\":\"0246\",\"TheaterName\":\"CGV울산진장\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"05\",\"TheaterCode\":\"0306\",\"TheaterName\":\"CGV정관\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"05\",\"TheaterCode\":\"0245\",\"TheaterName\":\"CGV하단아트몰링\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"05\",\"TheaterCode\":\"0318\",\"TheaterName\":\"CGV해운대\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"05\",\"TheaterCode\":\"0159\",\"TheaterName\":\"CGV화명\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"05\",\"TheaterCode\":\"P004\",\"TheaterName\":\"CINE de CHEF 센텀\",\"TheaterName_ENG\":null,\"IsSelected\":false}],\"RegionCode\":\"05,207\",\"RegionName\":\"부산/울산\",\"RegionName_ENG\":\"Busan/Ulsan\",\"DisplayOrder\":\"7\",\"IsSelected\":false},{\"AreaTheaterDetailList\":[{\"RegionCode\":\"204\",\"TheaterCode\":\"0263\",\"TheaterName\":\"CGV거제\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"204\",\"TheaterCode\":\"0330\",\"TheaterName\":\"CGV경산\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"204\",\"TheaterCode\":\"0323\",\"TheaterName\":\"CGV고성\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"204\",\"TheaterCode\":\"0053\",\"TheaterName\":\"CGV구미\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"204\",\"TheaterCode\":\"0240\",\"TheaterName\":\"CGV김천율곡\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"204\",\"TheaterCode\":\"0028\",\"TheaterName\":\"CGV김해\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"204\",\"TheaterCode\":\"0311\",\"TheaterName\":\"CGV김해율하\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"204\",\"TheaterCode\":\"0239\",\"TheaterName\":\"CGV김해장유\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"204\",\"TheaterCode\":\"0033\",\"TheaterName\":\"CGV마산\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"204\",\"TheaterCode\":\"0097\",\"TheaterName\":\"CGV북포항\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"204\",\"TheaterCode\":\"0272\",\"TheaterName\":\"CGV안동\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"204\",\"TheaterCode\":\"0234\",\"TheaterName\":\"CGV양산삼호\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"204\",\"TheaterCode\":\"0324\",\"TheaterName\":\"CGV진주혁신\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"204\",\"TheaterCode\":\"0023\",\"TheaterName\":\"CGV창원\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"204\",\"TheaterCode\":\"0079\",\"TheaterName\":\"CGV창원더시티\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"204\",\"TheaterCode\":\"0283\",\"TheaterName\":\"CGV창원상남\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"204\",\"TheaterCode\":\"0156\",\"TheaterName\":\"CGV통영\",\"TheaterName_ENG\":null,\"IsSelected\":false}],\"RegionCode\":\"204\",\"RegionName\":\"경상\",\"RegionName_ENG\":\"Gyeongsang\",\"DisplayOrder\":\"8\",\"IsSelected\":false},{\"AreaTheaterDetailList\":[{\"RegionCode\":\"04\",\"TheaterCode\":\"0220\",\"TheaterName\":\"CGV광양\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"04\",\"TheaterCode\":\"0221\",\"TheaterName\":\"CGV광양 엘에프스퀘어\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"206\",\"TheaterCode\":\"0295\",\"TheaterName\":\"CGV광주금남로\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"206\",\"TheaterCode\":\"0193\",\"TheaterName\":\"CGV광주상무\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"206\",\"TheaterCode\":\"0210\",\"TheaterName\":\"CGV광주용봉\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"206\",\"TheaterCode\":\"0218\",\"TheaterName\":\"CGV광주첨단\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"206\",\"TheaterCode\":\"0244\",\"TheaterName\":\"CGV광주충장로\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"206\",\"TheaterCode\":\"0090\",\"TheaterName\":\"CGV광주터미널\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"206\",\"TheaterCode\":\"0215\",\"TheaterName\":\"CGV광주하남\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"04\",\"TheaterCode\":\"0277\",\"TheaterName\":\"CGV군산\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"04\",\"TheaterCode\":\"0237\",\"TheaterName\":\"CGV나주\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"04\",\"TheaterCode\":\"0289\",\"TheaterName\":\"CGV목포\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"04\",\"TheaterCode\":\"0280\",\"TheaterName\":\"CGV목포평화광장\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"04\",\"TheaterCode\":\"0225\",\"TheaterName\":\"CGV서전주\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"04\",\"TheaterCode\":\"0114\",\"TheaterName\":\"CGV순천\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"04\",\"TheaterCode\":\"0268\",\"TheaterName\":\"CGV순천신대\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"04\",\"TheaterCode\":\"0315\",\"TheaterName\":\"CGV여수웅천\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"04\",\"TheaterCode\":\"0020\",\"TheaterName\":\"CGV익산\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"04\",\"TheaterCode\":\"0213\",\"TheaterName\":\"CGV전주고사\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"04\",\"TheaterCode\":\"0336\",\"TheaterName\":\"CGV전주에코시티\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"04\",\"TheaterCode\":\"0179\",\"TheaterName\":\"CGV전주효자\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"04\",\"TheaterCode\":\"0186\",\"TheaterName\":\"CGV정읍\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"06\",\"TheaterCode\":\"0302\",\"TheaterName\":\"CGV제주\",\"TheaterName_ENG\":null,\"IsSelected\":false},{\"RegionCode\":\"06\",\"TheaterCode\":\"0259\",\"TheaterName\":\"CGV제주노형\",\"TheaterName_ENG\":null,\"IsSelected\":false}],\"RegionCode\":\"206,04,06\",\"RegionName\":\"광주/전라/제주\",\"RegionName_ENG\":\"Gwangju/Jeonlla/Jeju\",\"DisplayOrder\":\"9\",\"IsSelected\":false}]";
		
		JsonElement je = JsonParser.parseString(theaterList);
		JsonArray incheonCgvList = je.getAsJsonArray().get(2).getAsJsonObject().get("AreaTheaterDetailList").getAsJsonArray();
		int insertResult = 0;
		for(JsonElement cgv: incheonCgvList) {
			String cgvThcode = cgv.getAsJsonObject().get("TheaterCode").getAsString();
			String cgvThName = cgv.getAsJsonObject().get("TheaterName").getAsString();
			String thCheck = mvdao.selectCheckTeater("T"+cgvThcode);
			if(thCheck != null) {
				continue;
			}
			String detailUrl = "http://www.cgv.co.kr/theaters/?areacode=202&theaterCode="+cgvThcode+"&date=";
			Document detailDoc = Jsoup.connect(detailUrl).get();
			detailDoc.select("#contents > div.wrap-theater > div.sect-theater > div > div.box-contents > div.theater-info > strong > a").remove();
			String cgvThAddr =detailDoc.select("#contents > div.wrap-theater > div.sect-theater > div > div.box-contents > div.theater-info > strong").text();
			String cgvThtel = detailDoc.select("#contents > div.wrap-theater > div.sect-theater > div > div.box-contents > div.theater-info > span.txt-info > em:nth-child(1)").text();
			TheatersDto theater = new TheatersDto();
			theater.setThaddr(cgvThAddr);
			theater.setThcode("T"+cgvThcode);
			theater.setThname(cgvThName);
			theater.setThtel(cgvThtel);
			insertResult += mvdao.insertTheater(theater);
		}
		System.out.println("등록된 극장 수 : "+insertResult);
		return insertResult;	
		
	}
	
	
	public int addScheduleList() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\stool\\chromeDriver\\chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		WebDriver driver = new ChromeDriver(options);

		//http://www.cgv.co.kr/theaters/?areacode=202&theaterCode=0021&date=20221216
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyyMMdd");
		Date today = new Date();
		
		ArrayList<TheatersDto> thList = mvdao.selectTheaterList();
		
		for(TheatersDto theater : thList) {
			String thcode = theater.getThcode().replace("T", "");
			String date = simpleFormat.format(today); // 20221216
			int insertResult = 0;
			for(int i = 0; i < 5; i++) {
				Date afterDate = simpleFormat.parse(date);
				cal.setTime(afterDate);
				cal.add(Calendar.DATE, 1);
				date = simpleFormat.format(cal.getTime()); //20221217
				String schedulePageUrl 
					= "http://www.cgv.co.kr/reserve/show-times/?areacode=202&theaterCode="+thcode+"&date="+date;
				
				driver.get(schedulePageUrl);
				driver.switchTo().frame( driver.findElement(By.id( "ifrm_movie_time_table" )) );
				
				List<WebElement> allScheduleList 
				 = driver.findElements(By.cssSelector("body > div > div.sect-showtimes > ul > li"));
				for(WebElement scheduleElement : allScheduleList) {
					String mvtitle = "";
					try {
						mvtitle = scheduleElement.findElement(By.cssSelector("div > div.info-movie > a > strong")).getText();
					} catch (Exception e) {
						continue;
					}
					
					String scmvcode = mvdao.selectMvcode(mvtitle);
					if(scmvcode == null) {
						continue;
					}
					List<WebElement> typeHall 
					= scheduleElement.findElements(By.cssSelector("div > div.type-hall"));
					
					for(WebElement hallSchedule:typeHall) {
						String scroom = hallSchedule.findElement(By.cssSelector("div.info-hall > ul > li:nth-child(2)")).getText().replaceAll(" .*층", "");
						
						List<WebElement> timeTableList = hallSchedule.findElements(By.cssSelector("div.info-timetable > ul > li"));
						
						for(WebElement timeTable : timeTableList) {
							ScheduleDto schedule = new ScheduleDto();
							String sctime = timeTable.findElement(By.cssSelector("em")).getText();
							schedule.setScmvcode(scmvcode);
							schedule.setScthcode("T"+thcode);
							schedule.setScroom(scroom);
							schedule.setScdate(date+" "+sctime);
							System.out.println(schedule);
							try {
								insertResult = mvdao.insertSchedule(schedule);
							} catch (Exception e) {
								System.out.println("예외");
								e.printStackTrace();
							}
						}
						
						System.out.println();
					}
					System.out.println();
				}
			}
		}
		driver.quit();
		return 0;
	}

	public ArrayList<TheatersDto> getTheaterList() {
		System.out.println("MovieService 극장 목록 조회");
		ArrayList<TheatersDto> thList = mvdao.selectTheaterList();
		return thList;
	}

	public String getReTheaterList(String scmvcode) {
		System.out.println("MovieService 예매가능한 극장 목록 조회");
		ArrayList<TheatersDto> thList = mvdao.selectReTheaterList(scmvcode);
		Gson gson = new Gson();
		String json = gson.toJson(thList);
		
		return new Gson().toJson(thList);
	}
	
	public String getReserveMovieList(String thcode) {
		System.out.println("MovieService 예매가능한 영화 목록 조회");
		ArrayList<MovieDto> mvList = mvdao.selectReserveMovieList(thcode);
		Gson gson = new Gson();
		String json = gson.toJson(mvList);
		System.out.println(json);
		return new Gson().toJson(mvList);
	}

	public ArrayList<ScheduleDto> getScheduleList() {
		System.out.println("MovieService 스케쥴 목록 조회");
		ArrayList<ScheduleDto> scList = mvdao.selectSchduleList();
		
		return scList;
		
	}

	public String getReDayList(String mvcode, String thcode) {
		System.out.println("MovieService 예매가능한 시간 목록 조회");
		ArrayList<ScheduleDto> dayList = mvdao.selectReDayList(mvcode, thcode);
		System.out.println();
		return new Gson().toJson(dayList);
	}

	public ArrayList<ScheduleDto> optionList() {
		
		System.out.println("MovieService 상세 시간 목록 조회");
		ArrayList<ScheduleDto> optionList = mvdao.optionList();
		
		return optionList;
		
	}

	public String getTimeList(String mvcode, String thcode, String scdate) {
		System.out.println("MovieService 예매가능한 상세시간 목록 조회");
		ArrayList<ScheduleDto> dayList = mvdao.getTimeList(mvcode, thcode, scdate);
		System.out.println();
		return new Gson().toJson(dayList);
	}

	public String getWPList(String WpName) {
		System.out.println("Service getWPList 요청");
		ArrayList<Map<String, String>> WPList = mvdao.getWPList(WpName);
		System.out.println();
		return new Gson().toJson(WPList);
	}
	
	

	public String selectMaxRecode() {
		String maxRecode = mvdao.selectMaxRecode();
		
		
		
		return maxRecode;
	}

	public int reservation(ReservationDto reserve) {
		
		System.out.println("MovieService 예매자정보 insert");
		System.out.println(reserve);
		int insertRe = mvdao.reservation(reserve);
		
		return insertRe;
	}

	public int deleteReserveInfo(String recode) {
		int deleteReserve = mvdao.deleteReserveInfo(recode);
		return deleteReserve;
	}

	
	
	public ArrayList<MovieDto> selectMovieList_Rank() {
			ArrayList<MovieDto> movieList =mvdao.selectMovieList_Rank(); 
			
			int totalReCount = mvdao.selectTotalRenumber();
			
			for(MovieDto mvinfo: movieList) {
				int recount = Integer.parseInt(mvinfo.getMvrecount());
				double rerate = ((double)recount/totalReCount)*100;
				mvinfo.setMvrecount((Math.round(rerate*10)/10.0)+"");
			}
			System.out.println(movieList);
			
			
			
			
			
			return movieList;
		}

	public MovieDto viewMv(String MLcode) {
		
		MovieDto mvinfo = mvdao.viewMv(MLcode);
		int totalReCount = mvdao.selectTotalRenumber();
		int recount = Integer.parseInt(mvinfo.getMvrecount());
		double rerate = ((double)recount/totalReCount)*100;
		mvinfo.setMvrecount((Math.round(rerate*10)/10.0)+"");
		
		
		return mvinfo;
	}

	
	public MovieStroyDto viewMs(String MLcode) {
		MovieStroyDto ms = mvdao.viewMs(MLcode);
		
		return ms;
	}

	public int jobTest() throws IOException {

		//1. CGV 영화 페이지 URL
		String jobUrl = "https://www.jobkorea.co.kr/recruit/joblist?menucode=local&localorder=1";
		
		//2. Jsoup URL 접속
		Document doc = Jsoup.connect(jobUrl).get();
		//System.out.println(doc);
		
		Elements charDiv = doc.select("#dev-gi-list > div > div.tplList.tplJobList > table > tbody");
		System.out.println("charDIv" + charDiv);
		Elements movLi = charDiv.select("tr");
		System.out.println("movLi"+movLi);
		
		int insertResult = 0;
		System.out.println(movLi.size());
		for(int i = 19; i < movLi.size(); i++) {
			//5. 잡코리아 url
			String detailUrl = "https://www.jobkorea.co.kr/"+movLi.eq(i).select("td.tplTit > div > strong > a").eq(0).attr("href");
			//System.out.println(detailUrl);
			Document detailDoc = Jsoup.connect(detailUrl).get();
			Elements baseMovie = detailDoc.select("#container > section > div.readSumWrap.clear > article");
			String mvtitle = baseMovie.select("div.sumTit > h3").text();
			System.out.println("================================");
			Elements mvtitle4 = baseMovie.select("div.sumTit > h3");
			Elements mvtitle5 =mvtitle4.select("div").remove();
			String mvtitle6 = mvtitle4.text();
			
			String test = movLi.eq(i).select("td.tplCo > a").text();
			String test1 = baseMovie.select("div.tbRow.clear > div:nth-child(1) > dl > dd:nth-child(2)").text();
			String test2 = baseMovie.select("div.tbRow.clear > div:nth-child(1) > dl > dd:nth-child(4)").text();
			String test3 = baseMovie.select("div.tbRow.clear > div:nth-child(2) > dl > dd:nth-child(2) > ul > li > strong").text();
			String test4 = baseMovie.select("div.tbRow.clear > div:nth-child(2) > dl > dd:nth-child(4)").text();
			String test6 = baseMovie.select("div.tbRow.clear > div:nth-child(2) > dl > dd:nth-child(8)").text();
			String test7 = baseMovie.select("div.tbRow.clear > div:nth-child(2) > dl > dd:nth-child(6)").text();
			
			Elements loLi = baseMovie.select("div.tbRow.clear > div:nth-child(2) > dl > dd:nth-child(6) > a");
			
			
			
			
			System.out.println("회사명 : " +i+"번 "+ test);
			System.out.println("공고명 : " +i+"번 "+ mvtitle6);
			System.out.println("경력 : " +i+"번 "+ test1);
			System.out.println("학력 : " +i+"번 "+ test2);
			System.out.println("고용형태 : " +i+"번 "+ test3);
			System.out.println("급여 : " +i+"번 "+ test4);
			//System.out.println("지역 : " +i+"번 "+ test5);
			System.out.println("시간 : " +i+"번 "+ test6);
			System.out.println("지역테스트 : " +i+"번 "+ test7);
			
			//회사 상세정보 
			String cUrl = "https://www.jobkorea.co.kr/"+baseMovie.select("div.tbRow.clear > div.tbCol.tbCoInfo > div.coBtn > a:nth-child(1)").attr("href");
			System.out.println(cUrl);
			Document cDoc = Jsoup.connect(cUrl).get();
			System.out.println(cDoc);
			//#container > section > div.readSumWrap.clear > article > div.tbRow.clear > div.tbCol.tbCoInfo > div.coBtn > a:nth-child(1)
			Elements baseCom = cDoc.select("#company-body > div.company-body-infomation.is-fixed > div.company-infomation-row.basic-infomation > div > table");
			System.out.println(baseCom);
			
			
			
			//#company-body > div.company-body-infomation > div.company-infomation-row.basic-infomation > div > table > tbody > tr:nth-child(1) > td:nth-child(2) > div > div
				
			//산업
			String c1 = baseCom.select("tbody > tr:nth-child(1) > td:nth-child(2) > div > div").text();
			//tr:nth-child(1) > td:nth-child(2) > div > div
			//사원수
			String c2 = baseCom.select("tbody > tr:nth-child(1) > td:nth-child(4) > div > div > div.value").text();
			// tr:nth-child(1) > td:nth-child(4) > div > div > div.value
			//기업구분
			String c3 = baseCom.select("tbody > tr:nth-child(2) > td:nth-child(2) > div > div").text();
			//tr:nth-child(2) > td:nth-child(2) > div > div
			//설립일
			String c4 = baseCom.select("tbody > tr:nth-child(2) > td:nth-child(4) > div > div > div.value").text();
			// tr:nth-child(2) > td:nth-child(4) > div > div > div.value
			//자본금
			String c5 = baseCom.select("tbody > tr:nth-child(3) > td:nth-child(2) > div > div > div.value").text();
			// tr:nth-child(3) > td:nth-child(2) > div > div > div.value
			//매출액
			String c6 = baseCom.select("tbody > tr:nth-child(3) > td:nth-child(4) > div > div > div.value").text();
			// tr:nth-child(3) > td:nth-child(4) > div > div > div.value
			//대표자
			String c7 = baseCom.select("tbody > tr:nth-child(4) > td:nth-child(2) > div > div").text();
			// tr:nth-child(4) > td:nth-child(2) > div > div
			//대졸초임
			String c8 = baseCom.select("tbody > tr:nth-child(4) > td:nth-child(4) > div > div > div > div").text();
			// tr:nth-child(4) > td:nth-child(4) > div > div > div > div
			//주요사업
			String c9 = baseCom.select("tbody > tr:nth-child(5) > td:nth-child(2) > div > div").text();
			// tr:nth-child(5) > td:nth-child(2) > div > div
			//4대보험
			String c10 = baseCom.select("tbody > tr:nth-child(5) > td:nth-child(4) > div > div").text();
			// tr:nth-child(5) > td:nth-child(4) > div > div
			//홈페이지
			String c11 = baseCom.select("tbody > tr:nth-child(6) > td:nth-child(2) > div > div > a").text();
			// tr:nth-child(6) > td:nth-child(2) > div > div > a
			//주소
			String c12 = baseCom.select("tbody > tr:nth-child(6) > td:nth-child(4) > div").text();
			// tr:nth-child(6) > td:nth-child(4) > div
			//계열사
			String c13 = baseCom.select("tbody > tr.field.full-width > td > div > div").text();
			// tr.field.full-width > td > div > div
			
			//#company-body > div.company-body-infomation > div.company-infomation-row.basic-infomation > div > table > tbody > tr:nth-child(1) > td:nth-child(2) > div > div
			
			System.out.println("산업 : " + c1);
			System.out.println("사원수  : " + c2);
			System.out.println("기업구분  : " +c3);
			System.out.println("설립일  : " + c4);
			System.out.println("자본금  : " + c5);
			System.out.println("매출액  : " + c6);
			System.out.println("대표자  : " + c7);
			System.out.println("대졸초임  : " + c8);
			System.out.println("주요사업  : " + c9);
			System.out.println("4대보험  : " + c10);
			System.out.println("홈페이지  : " + c11);
			System.out.println("주소  : " + c12);
			System.out.println("계열사  : " +c13);
			
			
			System.out.println();
			
		}
		
		System.out.println("등록된 공고 수 : " + insertResult);
		return 0;

		
		
		
		
		
		
	}
	
	

	public int jobInsert() throws IOException {
		//1. 잡코리아 채용정보 페이지 URL 
				//페이지 URL 내부 SELECTOR로 다음 리스트 접속이 불가능하여 URL을 변경해주며 만드는것으로 대체함
				//String jobUrl = "https://www.jobkorea.co.kr/recruit/joblist?menucode=local&localorder=1";
				String jobUrl = "https://www.jobkorea.co.kr/recruit/joblist?menucode=cotype2&cotype=4,5";
				
				//2. Jsoup URL 접속
				Document doc = Jsoup.connect(jobUrl).get();
				//System.out.println(doc);
				
				Elements charDiv = doc.select("#dev-gi-list > div > div.tplList.tplJobList > table > tbody");
				Elements movLi = charDiv.select("tr");
				int insertResult = 0;
				System.out.println(movLi.size());
				/*
				 * 잡코리아 내부 서버에서 과도한 접속으로 인해 차단당함
				 * 9회씩 반복문을 돌리면 오류 발생 X
				 * 오류 발생시 => 크롤링 진행이 안되고 x값이 컬럼에 insert된다.
				 * 			  잡코리아 홈페이지 접속후 인증코드 입력후 재시도 반복
				 */
				for(int i = 17; i < 20; i++) {
					//5. 잡코리아 기업 상세정보 페이지 url 주소
					String detailUrl = "https://www.jobkorea.co.kr/"+movLi.eq(i).select("td.tplCo > a").eq(0).attr("href");
					//System.out.println(detailUrl);
					Document detailDoc = Jsoup.connect(detailUrl).get();
					Elements baseMovie = detailDoc.select("#container > section > div.readSumWrap.clear > article");
					System.out.println("================================");
					//회사 상세정보 
					String baseMovieName = movLi.eq(i).select("td.tplCo > a").text();
					System.out.println(detailUrl);
					Document cDoc = Jsoup.connect(detailUrl).get();
					Elements baseCom = cDoc.select("#company-body > div.company-body-infomation");
					
					System.out.println("tbody > tr사이즈 : "+baseCom.select("tbody > tr").size());
					System.out.println("tbody : "+baseCom.select("tbody").size());
					//System.out.println("tbody > tr : "+baseCom.select("tbody > tr"));
					
					//잡코리아 상세 페이지 내부 selector에 차이가 있어 순서가 바뀌는 경우가 발생
					
					//산업
					String c1 = baseCom.select("tbody > tr:nth-child(1) > td:nth-child(2) > div > div").text();
					//tr:nth-child(1) > td:nth-child(2) > div > div
					//사원수
					String c2 = baseCom.select("tbody > tr:nth-child(1) > td:nth-child(4) > div > div > div.value").text();
					// tr:nth-child(1) > td:nth-child(4) > div > div > div.value
					//기업구분
					String c3 = baseCom.select("tbody > tr:nth-child(2) > td:nth-child(2) > div > div").text();
					//tr:nth-child(2) > td:nth-child(2) > div > div
					//설립일
					String c4 = baseCom.select("tbody > tr:nth-child(2) > td:nth-child(4) > div > div > div.value").text();
					// tr:nth-child(2) > td:nth-child(4) > div > div > div.value
					//자본금
					//tbody > tr:nth-child(3) > td:nth-child(2) > div > div
					String c5 = baseCom.select("tbody > tr:nth-child(3) > td:nth-child(2) > div > div").text();
					// tr:nth-child(3) > td:nth-child(2) > div > div > div.value
					//매출액
					//String c6 = baseCom.select("tbody > tr:nth-child(3) > td:nth-child(4) > div > div").text();
					
					String c6 = baseCom.select("tbody > tr:nth-child(3) > td:nth-child(4) > div > div").text();
					
					// tr:nth-child(3) > td:nth-child(4) > div > div > div.value
					//대표자
					String c7 = baseCom.select("tbody > tr:nth-child(4) > td:nth-child(2) > div > div").text();
					// tr:nth-child(4) > td:nth-child(2) > div > div
					//대졸초임
					String c8 = baseCom.select("tbody > tr:nth-child(4) > td:nth-child(4) > div > div > div > div").text();
					  try{
				            Integer number = Integer.valueOf(c8);
				            System.out.println(number);
				        }
				        catch (NumberFormatException ex){
				           c8 = baseCom.select("tbody > tr:nth-child(4) > td:nth-child(4) > div > div > div > div").text();
				        }
					
				
						
					
					
					//주요사업
					String c9 = baseCom.select("tbody > tr:nth-child(5) > td:nth-child(2) > div > div").text();
					//4대보험
					String c10 = baseCom.select("tbody > tr:nth-child(5) > td:nth-child(4) > div > div").text();
					// tr:nth-child(5) > td:nth-child(4) > div > div
					if(c9.contains("보험")) {
						c10 = c9;
						c9 = baseCom.select("tbody > tr:nth-child(4) > td:nth-child(4) > div > div").text();
					}
					
					// tr:nth-child(5) > td:nth-child(2) > div > div
					//홈페이지
					String c11 = baseCom.select("tbody > tr:nth-child(6) > td:nth-child(2) > div > div > a").text();
					// tr:nth-child(6) > td:nth-child(2) > div > div > a
					//주소
					//String c12 = baseCom.select("tbody > tr:nth-child(6) > td:nth-child(4) > div").text();
					String c12 = baseCom.select("tbody > tr:nth-child(6) > td:nth-child(4) > div > div.value").text();
					// tr:nth-child(6) > td:nth-child(4) > div
					// tbody > tr:nth-child(6) > td > div > div.value
					//계열사
					String c13 = baseCom.select("tbody > tr.field.full-width > td > div > div").text();
					// tr.field.full-width > td > div > div
					
					//잡코리아 상세 페이지 내부 selector에 차이가 있어 순서가 바뀌는 경우가 발생
					cdto cinfo = new cdto();
					System.out.println("순서 : " + i);
					
					System.out.println("기업명 : " + baseMovieName);
					System.out.println("산업 : " + c1);
					System.out.println("사원수  : " + c2);
					System.out.println("기업구분  : " +c3);
					System.out.println("설립일  : " + c4);
					System.out.println("자본금  : " + c5);
					System.out.println("매출액  : " + c6);
					System.out.println("대표자  : " + c7);
					System.out.println("대졸초임  : " + c8);
					System.out.println("주요사업  : " + c9);
					System.out.println("4대보험  : " + c10);
					System.out.println("홈페이지  : " + c11);
					System.out.println("주소  : " + c12);
					System.out.println("계열사  : " +c13);
					
					if(c13.equals("")) {
						System.out.println(c13 + "= ");
					}
					
					
					
					
					
					
					
					String maxCicode = mvdao.selectMaxCinum();
					System.out.println("회사코드 최대값 : " + maxCicode);
					String cicode = "CI";
					if(maxCicode == null) {
						cicode = cicode + String.format("%03d", 1);
						System.out.println("처음 회사코드 : "+cicode);
					}else {
						int cicodeNum = Integer.parseInt(maxCicode.replace("CI", "")) +1;
						cicode =  cicode + String.format("%03d", cicodeNum);
					}
					System.out.println("회사코드 : "+cicode);
					
					String mvCheck = mvdao.checkCom(baseMovieName);
					if(mvCheck != null) {
						continue;
					}
					
					
					
					
					
					//insertResult += mvdao.insertMovie(movie);
					if(baseMovieName.equals("")) {
						baseMovieName = "x";
					}
					if(c1.equals("")) {
						c1 = "x";
					}
					if(c2.equals("")) {
						c2 = "x";
					}
					if(c3.equals("")) {
						c3 = "x";
					}
					if(c4.equals("")) {
						c4 = "x";
					}
					if(c5.equals("")) {
						c5 = "x";
					}
					if(c6.equals("")) {
						c6 = "x";
					}
					if(c7.equals("")) {
						c7 = "x";
					}
					if(c8.equals("")) {
						c8 = "x";
					}
					if(c9.equals("")) {
						c9 = "x";
					}
					if(c10.equals("")) {
						c10 = "x";
					}
					if(c11.equals("")) {
						c11 = "x";
					}
					if(c12.equals("")) {
						c12 = "x";
					}
					if(c13.equals("")) {
						c13 = "x";
					}
					//회사테이블 
					cinfo.setCinum(cicode);
					cinfo.setCiname(baseMovieName);
					cinfo.setCiind(c1);
					cinfo.setCipeople(c2);
					cinfo.setCitype(c3);
					cinfo.setCiest(c4);
					cinfo.setCimoney(c5);
					cinfo.setCisales(c6);
					cinfo.setCileader(c7);
					cinfo.setCiwage(c8);
					cinfo.setCimajor(c9);
					cinfo.setCiinsurance(c10);
					cinfo.setCihomepage(c11);
					cinfo.setCiaddr(c12);
					cinfo.setCisub(c13);
					
					System.out.println(cinfo);
					
					insertResult += mvdao.insertCinfo(cinfo);
					
					
					
					System.out.println();
					
					/*
					//매출액 
					//for문 반복 후 년도-순이익 순으로 합치기 
					Elements c14 = baseCom.select("div.company-infomation-row.financial-analysis > div > div > div:nth-child(2) > div.chart-bar-wrap > div > table > tbody > tr");
					
					System.out.println("매출액 년도 몇개인가 "+c14.size());
					//String detailUrl = "https://www.jobkorea.co.kr/"+movLi.eq(i).select("td.tplCo > a").eq(0).attr("href");
					String p1= "";
					String p2= "";
					String p3= "";
					
					for(int j =0; j < c14.size(); j++) {
					p1 += c14.eq(j).select(".label").text()+ ":";
					p1 += c14.eq(j).select(".value").text()+ "/";
					}
					//영업이익
					Elements c15 = baseCom.select("div.company-infomation-row.financial-analysis > div > div > div:nth-child(3) > div.chart-bar-wrap > div > table > tbody > tr");
					System.out.println("당기순이익 년도"+c15.size());
					for(int j =0; j < c15.size(); j++) {
					p2 += c15.eq(j).select(".label").text()+ ":";
					p2 += c15.eq(j).select(".value").text()+ "/";
					}
					//당기순이익
					Elements c16 = baseCom.select("div.company-infomation-row.financial-analysis > div > div > div:nth-child(4) > div.chart-bar-wrap > div > table > tbody > tr");
					for(int j =0; j < c16.size(); j++) {
						p3 += c16.eq(j).select(".label").text()+ ":";
						p3 += c16.eq(j).select(".value").text()+ "/";
					}
					//잡코리아 상세 페이지 내부 selector에 차이가 있어 순서가 바뀌는 경우가 발생
					System.out.println("매출액 " + p1);
					System.out.println("영업이익 :"+ p2);
					System.out.println("당기순이익 :"+ p3);
					
					/*
					String p1 = c14.select("tr:nth-child(1) > .label").text();
					String p2 = c14.select("tr:nth-child(1) > .value").text();
					System.out.println("당기순이익 :"+c14);
					System.out.println("p1 "+p1);
					System.out.println("p2 "+p2);
					//매출액
					Elements c15 = baseCom.select("div.company-infomation-row.financial-analysis > div > div > div:nth-child(1) > div.chart-bar-wrap > div > table > tbody > tr");
					String p3 = c15.select("tr:nth-child(1) > .label").text();
					String p4 = c15.select("tr:nth-child(1) > .value").text();
					System.out.println("매출액 :"+c15);
					System.out.println("p3 "+p3);
					System.out.println("p4 "+p4);
					//영업이익
					Elements c16 = baseCom.select("div.company-infomation-row.financial-analysis > div > div > div:nth-child(2) > div.chart-bar-wrap > div > table > tbody > tr");
					String p5 = c16.select("tr:nth-child(1) > .label").text();
					String p6 = c16.select("tr:nth-child(1) > .value").text();
					System.out.println("영업이익 :"+c16);
					System.out.println("p5 "+p5);
					System.out.println("p6 "+p6);
					*/
					
					
					
					
				}
				
				System.out.println("등록된 회사 수 : " + insertResult);
				
				
				return insertResult;

			}
		
	
	public int jobInsert2() throws IOException {
		//1. 잡코리아 채용정보 페이지 URL
		String jobUrl = "https://www.jobkorea.co.kr/recruit/joblist?menucode=local&localorder=1";
		
		//2. Jsoup URL 접속
		Document doc = Jsoup.connect(jobUrl).get();
		
		Elements charDiv = doc.select("#dev-gi-list > div > div.tplList.tplJobList > table > tbody");
		Elements movLi = charDiv.select("tr");
		
		int insertResult = 0;
		System.out.println(movLi.size());
		/*
		 * 잡코리아 내부 서버에서 과도한 접속으로 인해 차단당함
		 * 3회씩 반복문을 돌리면 오류 발생 X
		 * 오류 발생시 => 크롤링 진행이 안되고 x값이 컬럼에 insert된다.
		 * 			  잡코리아 홈페이지 접속후 인증코드 입력후 재시도 반복
		 */
		for(int i = 30; i < 40; i++) {
			//5. 잡코리아 채용정보에서 공고 선택시 url 주소
			String detailUrl = "https://www.jobkorea.co.kr/"+movLi.eq(i).select("td.tplTit > div > strong > a").eq(0).attr("href");
			Document detailDoc = Jsoup.connect(detailUrl).get();
			
			Elements baseMovie = detailDoc.select("#container > section > div.readSumWrap.clear > article");
			String mvtitle = baseMovie.select("div.sumTit > h3").text();
			System.out.println("================================");
			Elements mvtitle4 = baseMovie.select("div.sumTit > h3");
			Elements mvtitle5 =mvtitle4.select("div").remove();
			
			//공고명
			String mvtitle6 = mvtitle4.text();
			//회사명
			String test = movLi.eq(i).select("td.tplCo > a").text();
			//경력
			String test1 = baseMovie.select("div.tbRow.clear > div:nth-child(1) > dl > dd:nth-child(2)").text();
			//학력
			String test2 = baseMovie.select("div.tbRow.clear > div:nth-child(1) > dl > dd:nth-child(4)").text();
			//고용형태
			String test3 = baseMovie.select("div.tbRow.clear > div:nth-child(2) > dl > dd:nth-child(2)").text();
			//급여
			String test4 = baseMovie.select("div.tbRow.clear > div:nth-child(2) > dl > dd:nth-child(4)").text();
			//시간
			String test6 = baseMovie.select("div.tbRow.clear > div:nth-child(2) > dl > dd:nth-child(8)").text();
			//지역
			String test7 = baseMovie.select("div.tbRow.clear > div:nth-child(2) > dl > dd:nth-child(6)").text();
			//우대
			//#dlPref > dd > span
			String test8 = baseMovie.select("#dlPref > dd > span").text();
			
			//시작일
			String start = detailDoc.select("#tab02 > div > article.artReadPeriod > div > dl.date > dd:nth-child(2) > span").text();
			//마감일
			String end = detailDoc.select("#tab02 > div > article.artReadPeriod > div > dl.date > dd:nth-child(4) > span").text();
			
			Elements loLi = baseMovie.select("div.tbRow.clear > div:nth-child(2) > dl > dd:nth-child(6) > a");
			
			
			
			
			System.out.println("loLi size: " + loLi.size());
			
			//공고 dto
			 epDto epinfo = new epDto();
			
			//잡코리아 상세 페이지 내부 selector에 차이가 있어 순서가 바뀌는 경우가 발생 
			System.out.println(detailUrl);
			System.out.println("회사명 : " +i+"번 "+ test);
			System.out.println("공고명 : " +i+"번 "+ mvtitle6);
			System.out.println("경력 : " +i+"번 "+ test1);
			System.out.println("학력 : " +i+"번 "+ test2);
			System.out.println("고용형태 : " +i+"번 "+ test3);
			System.out.println("급여 : " +i+"번 "+ test4);
			System.out.println("시간 : " +i+"번 "+ test6);
			System.out.println("지역 : " +i+"번 "+ test7);
			System.out.println("우대 : " +i+"번 "+ test8);
			System.out.println("시작일 : " +i+"번 "+ start);
			System.out.println("마감일 : " +i+"번 "+ end);
			
			
			
			String maxEpcode = mvdao.selectMaxEpnum();
			System.out.println("공고코드 최대값 : " + maxEpcode);
			String epcode = "EP";
			if(maxEpcode == null) {
				epcode = epcode + String.format("%03d", 1);
				System.out.println("처음 공고 : "+epcode);
			}else {
				int cicodeNum = Integer.parseInt(maxEpcode.replace("EP", "")) +1;
				epcode =  epcode + String.format("%03d", cicodeNum);
			}
			System.out.println("공고코드 : "+epcode);
			
			
			String mvCheck = mvdao.checkEp(mvtitle6, test);
			if(mvCheck != null) {
				continue;
			}
			
			
			
			epinfo.setEpnum(epcode);
			epinfo.setEpname(mvtitle6);
			epinfo.setEpciname(test);
			epinfo.setEpcareer(test1);
			epinfo.setEpedu(test2);
			epinfo.setEptype(test3);
			epinfo.setEpmoney(test4);
			epinfo.setEptime(test6);
			epinfo.setEparea(test7);
			epinfo.setEptreat(test8);
			epinfo.setEppost(start);
			epinfo.setEpdeadline(end);
			epinfo.setEpstate("Y");
			
			System.out.println();
			insertResult += mvdao.insertEmployments(epinfo);
			
		}
		
		System.out.println("등록된 공고 수 : " + insertResult);
		return insertResult;
	}
	
	
	
	
}
