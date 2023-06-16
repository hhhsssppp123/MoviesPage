package com.Movie_Project.service;

import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Movie_Project.dao.EmploymentDao;
import com.Movie_Project.dto.CinfoDto;
import com.Movie_Project.dto.EmploymentDto;
import com.google.gson.Gson;


@Service
public class EmploymentService {

	@Autowired
	private EmploymentDao epdao;
	
	public int jobInsert1() throws IOException {
		//1. 잡코리아 채용정보 페이지 URL
		//String jobUrl = "https://www.jobkorea.co.kr/recruit/joblist?menucode=local&localorder=1";
		
		
		//2. Jsoup URL 접속
		//Document doc = Jsoup.connect(jobUrl).get();
		// 채용정보
		//Elements charDiv = doc.select("#dev-gi-list > div > div.tplList.tplJobList > table > tbody");
		
		// 채용정보
		//Elements JobLi = charDiv.select("tr");
		
		//2 잡코리아 공채정보 페이지 URL
		String jobUrl = "https://www.jobkorea.co.kr/starter/";
		Document doc = Jsoup.connect(jobUrl).get();
		
		// 공채정보
		Elements charDiv = doc.select("#devStarterForm > div.filterListArea");
		Elements JobLi = charDiv.select("ul> li");
		//System.out.println(JobLi);
		
		
		int insertResult = 0;
		System.out.println(JobLi.size());
		
		
		
		//#devStarterForm > div.filterListArea > ul
		/*
		 * 잡코리아 내부 서버에서 과도한 접속으로 인해 차단당함
		 * 3회씩 반복문을 돌리면 오류 발생 X
		 * 오류 발생시 => 크롤링 진행이 안되고 x값이 컬럼에 insert된다.
		 * 			  잡코리아 홈페이지 접속후 인증코드 입력후 재시도 반복
		 */
		for(int i = 30; i < 50 ; i++) {
			//5. 잡코리아 채용정보에서 공고 선택시 url 주소
			//채용정보
			//String detailUrl = "https://www.jobkorea.co.kr/"+JobLi.eq(i).select("td.tplTit > div > strong > a").eq(0).attr("href");
			
			//공채정보
			String detailUrl = JobLi.eq(i).select(" div.info > div.tit > a").eq(0).attr("href");
			//System.out.println(detailUrl);
			
			String detailUrlCheck = JobLi.eq(i).select("div.side").text();
			
			
			if(!detailUrl.contains("https:")) {
				detailUrl = "https://www.jobkorea.co.kr/"+detailUrl;
			}
			//System.out.println(detailUrl);
			if(!detailUrlCheck.contains("자소서")) {
				continue;
			}
			
			//System.out.println(i+" 번 :"+detailUrl);
			
			
			Document detailDoc = Jsoup.connect(detailUrl).get();
			
			Elements baseMovie = detailDoc.select("#container > section > div.readSumWrap.clear > article");
			System.out.println("================================");
			Elements Exciname = baseMovie.select("div.sumTit > h3");
			Elements removerole =Exciname.select("div").remove();
			
			//공고명
			String epname = Exciname.text();
			//회사명 (채용공고)
			//String epciname = JobLi.eq(i).select("td.tplCo > a").text();
			//회사명 (공채)
			String epciname = JobLi.eq(i).select("div.co > div.coTit > a").text();
			//경력
			String epcareer = baseMovie.select("div.tbRow.clear > div:nth-child(1) > dl > dd:nth-child(2)").text();
			//학력
			String epedu = baseMovie.select("div.tbRow.clear > div:nth-child(1) > dl > dd:nth-child(4)").text();
			//고용형태
			String eptype = baseMovie.select("div.tbRow.clear > div:nth-child(2) > dl > dd:nth-child(2)").text();
			//급여
			String epmoney = baseMovie.select("div.tbRow.clear > div:nth-child(2) > dl > dd:nth-child(4)").text();
			//시간
			String eptime = baseMovie.select("div.tbRow.clear > div:nth-child(2) > dl > dd:nth-child(8)").text();
			//지역
			String eparea = baseMovie.select("div.tbRow.clear > div:nth-child(2) > dl > dd:nth-child(6)").text();
			//우대
			//#dlPref > dd > span
			String eptreat = baseMovie.select("#dlPref > dd > span").text();
			
			//시작일
			String eppost = detailDoc.select("#tab02 > div > article.artReadPeriod > div > dl.date > dd:nth-child(2) > span").text();
			//마감일
			String epdeadline = detailDoc.select("#tab02 > div > article.artReadPeriod > div > dl.date > dd:nth-child(4) > span").text();
			
			
			//공고 dto
			 EmploymentDto epinfo = new EmploymentDto();
			
			//잡코리아 상세 페이지 내부 selector에 차이가 있어 순서가 바뀌는 경우가 발생 
			System.out.println(detailUrl);
			System.out.println("회사명 : " +i+"번 "+ epciname);
			System.out.println("공고명 : " +i+"번 "+ epname);
			System.out.println("경력 : " +i+"번 "+ epcareer);
			System.out.println("학력 : " +i+"번 "+ epedu);
			System.out.println("고용형태 : " +i+"번 "+ eptype);
			System.out.println("급여 : " +i+"번 "+ epmoney);
			System.out.println("시간 : " +i+"번 "+ eptime);
			System.out.println("지역 : " +i+"번 "+ eparea);
			System.out.println("우대 : " +i+"번 "+ eptreat);
			System.out.println("시작일 : " +i+"번 "+ eppost);
			System.out.println("마감일 : " +i+"번 "+ epdeadline);
			
			
			
			String maxEpcode = epdao.selectMaxEpnum();
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
			
			
			String epCheck = epdao.checkEp(epname, epciname);
			if(epCheck != null) {
				continue;
			}
			

			DateFormat DFormat = DateFormat.getDateInstance();
			Date rvdate = new Date();
			//System.out.println("현재시간 :"+rvdate);
			String nowDay = DFormat.format(rvdate);
			String dbDay =nowDay.replace(". ", "-");
			
			
			
			
			if(epname.equals("")) {
				epname = "-";
			}if(epciname.equals("")) {
				epciname = "-";
			}if(epcareer.equals("")) {
				epcareer = "-";
			}if(epedu.equals("")) {
				epedu = "-";
			}if(eptype.equals("")) {
				eptype = "-";
			}if(epmoney.equals("")) {
				epmoney = "-";
			}if(eptime.equals("")) {
				eptime ="-";
			}if(eparea.equals("")) {
				eparea ="-";
			}if(eptreat.equals("")) {
				eptreat = "-";
			}if(eppost.equals("")) {
				eppost = nowDay.replace(". ", "-");;
			}
			
			
			
			
			
			
			
			
			epinfo.setEpnum(epcode);
			epinfo.setEpname(epname);
			epinfo.setEpciname(epciname);
			epinfo.setEpcareer(epcareer);
			epinfo.setEpedu(epedu);
			epinfo.setEptype(eptype);
			epinfo.setEpmoney(epmoney);
			epinfo.setEptime(eptime);
			epinfo.setEparea(eparea);
			epinfo.setEptreat(eptreat);
			epinfo.setEppost(eppost);
			epinfo.setEpdeadline(epdeadline);
			epinfo.setEpstate("Y");
			epinfo.setEpesstate("Y");
			
			System.out.println();
			insertResult += epdao.insertEmployments(epinfo);
			
		}
		
		System.out.println("등록된 공고 수 : " + insertResult);
		return insertResult;
	}

	
	public int jobInsert2() throws Exception {
		//1. 잡코리아 채용정보 페이지 URL 
	//	String jobUrl = "https://www.jobkorea.co.kr/recruit/joblist?menucode=local&localorder=1";
		//2. Jsoup URL 접속
	//	Elements charDiv = doc.select("#dev-gi-list > div > div.tplList.tplJobList > table > tbody");
	//	Elements JobLi = charDiv.select("tr");
		
		
		
		
		
		//2 잡코리아 공채정보 페이지 URL
		String jobUrl = "https://www.jobkorea.co.kr/starter/";
		Document doc = Jsoup.connect(jobUrl).get();
		
		// 공채정보
		Elements charDiv = doc.select("#devStarterForm > div.filterListArea");
		Elements JobLi = charDiv.select("ul> li");
		System.out.println(JobLi);
		
		
		
		int insertResult = 0;
		System.out.println(JobLi.size());
		
		for(int i = 1; i < 5; i++) {
			//5. 잡코리아 기업 상세정보 페이지 url 주소
			//String detailUrl = "https://www.jobkorea.co.kr/"+JobLi.eq(i).select("td.tplCo > a").eq(0).attr("href");
			
			String detailUrl = JobLi.eq(i).select("div.co > div.coTit > a.coLink").eq(0).attr("href");
			
			String detailUrlCheck = JobLi.eq(i).select("div.side").text();
			
			
			if(!detailUrl.contains("https:")) {
				detailUrl = "https://www.jobkorea.co.kr/"+JobLi.eq(i).select("div.co > div.coTit > a.coLink").eq(0).attr("href");
			}
			//System.out.println(detailUrl);
			if(!detailUrlCheck.contains("자소서")) {
				continue;
			}
			Document detailDoc = Jsoup.connect(detailUrl).get();
			Elements baseC = detailDoc.select("#container > section > div.readSumWrap.clear > article");
			System.out.println("================================");
			//회사 상세정보 
			String baseCName = JobLi.eq(i).select("td.tplCo > a").text();
			System.out.println(detailUrl);
			Document cDoc = Jsoup.connect(detailUrl).get();
			Elements baseCom = cDoc.select("#company-body > div.company-body-infomation");
			System.out.println("tbody > tr사이즈 : "+baseCom.select("tbody > tr").size());
			System.out.println("tbody : "+baseCom.select("tbody").size());
			//잡코리아 상세 페이지 내부 selector에 차이가 있어 순서가 바뀌는 경우가 발생
			//산업
			String ciind = baseCom.select("tbody > tr:nth-child(1) > td:nth-child(2) > div > div").text();
			//사원수
			String cipeople = baseCom.select("tbody > tr:nth-child(1) > td:nth-child(4) > div > div > div.value").text();
			//기업구분
			String citype = baseCom.select("tbody > tr:nth-child(2) > td:nth-child(2) > div > div").text();
			//설립일
			String ciest = baseCom.select("tbody > tr:nth-child(2) > td:nth-child(4) > div > div > div.value").text();
			//자본금
			String cimoney = baseCom.select("tbody > tr:nth-child(3) > td:nth-child(2) > div > div").text();
			//매출액
			String cisales = baseCom.select("tbody > tr:nth-child(3) > td:nth-child(4) > div > div").text();
			//대표자
			String cileader = baseCom.select("tbody > tr:nth-child(4) > td:nth-child(2) > div > div").text();
			//대졸초임
			String ciwage = baseCom.select("tbody > tr:nth-child(4) > td:nth-child(4) > div > div > div > div").text();
			  try{
		            Integer number = Integer.valueOf(ciwage);
		            System.out.println(number);
		        }
		        catch (NumberFormatException ex){
		           ciwage = baseCom.select("tbody > tr:nth-child(4) > td:nth-child(4) > div > div > div > div").text();
		        }
			//주요사업
			String cimajor = baseCom.select("tbody > tr:nth-child(5) > td:nth-child(2) > div > div").text();
			//4대보험
			String ciinsurance = baseCom.select("tbody > tr:nth-child(5) > td:nth-child(4) > div > div").text();
			if(cimajor.contains("보험")) {
				ciinsurance = cimajor;
				cimajor = baseCom.select("tbody > tr:nth-child(4) > td:nth-child(4) > div > div").text();
			}
			//홈페이지
			String cihomepage = baseCom.select("tbody > tr:nth-child(6) > td:nth-child(2) > div > div > a").text();
			//주소
			String ciaddr = baseCom.select("tbody > tr:nth-child(6) > td:nth-child(4) > div > div.value").text();
			//잡코리아 상세 페이지 내부 selector에 차이가 있어 순서가 바뀌는 경우가 발생
			CinfoDto cinfo = new CinfoDto();
			System.out.println("순서 : " + i);
			
			
			
			
			
			/*
			 * String maxEpcode = epdao.selectMaxEpnum(); System.out.println("공고코드 최대값 : " +
			 * maxEpcode); String epcode = "EP"; if(maxEpcode == null) { epcode = epcode +
			 * String.format("%03d", 1); System.out.println("처음 공고 : "+epcode); }else { int
			 * cicodeNum = Integer.parseInt(maxEpcode.replace("EP", "")) +1; epcode = epcode
			 * + String.format("%03d", cicodeNum); } System.out.println("공고코드 : "+epcode);
			 * 
			 * 
			 * String epCheck = epdao.checkEp(epname, epciname); if(epCheck != null) {
			 * continue; }
			 */
			
			
			String maxCicode = epdao.selectMaxCinum();
			System.out.println("회사코드 최대값 : " + maxCicode);
			String cicode = "CI";
			if(maxCicode == null) {
				cicode = cicode + String.format("%03d", 1);
				System.out.println("처음 회사코드 : "+cicode);
			}else {
				int mvcodeNum = Integer.parseInt(maxCicode.replace("CI", "")) +1;
				cicode =  cicode + String.format("%03d", mvcodeNum);
			}
			System.out.println("회사코드 : "+cicode);
			
			
			String mvCheck = epdao.checkCom(baseCName);
			if(mvCheck != null) {
				continue;}
			
			if(baseCName.equals("")) {
				baseCName = "-";
			}if(ciind.equals("")) {
				ciind = "-";
			}if(cipeople.equals("")) {
				cipeople = "-";
			}if(citype.equals("")) {
				citype = "-";
			}if(ciest.equals("")) {
				ciest = "-";
			}if(cimoney.equals("")) {
				cimoney = "-";
			}if(cisales.equals("")) {
				cisales = "-";
			}if(cileader.equals("")) {
				cileader = "-";
			}if(ciwage.equals("")) {
				ciwage = "-";
			}if(cimajor.equals("")) {
				cimajor = "-";
			}if(ciinsurance.equals("")) {
				ciinsurance = "-";
			}if(cihomepage.equals("")) {
				cihomepage = "-";
			}if(ciaddr.equals("")) {
				ciaddr = "-";
			}
			System.out.println("기업명 : " + baseCName);
			System.out.println("산업 : " + ciind);
			System.out.println("사원수  : " + cipeople);
			System.out.println("기업구분  : " +citype);
			System.out.println("설립일  : " + ciest);
			System.out.println("자본금  : " + cimoney);
			System.out.println("매출액  : " + cisales);
			System.out.println("대표자  : " + cileader);
			System.out.println("대졸초임  : " + ciwage);
			System.out.println("주요사업  : " + cimajor);
			System.out.println("4대보험  : " + ciinsurance);
			System.out.println("홈페이지  : " + cihomepage);
			System.out.println("주소  : " + ciaddr);
			//회사테이블 
			cinfo.setCinum(cicode);
			cinfo.setCiname(baseCName);
			cinfo.setCiind(ciind);
			cinfo.setCipeople(cipeople);
			cinfo.setCitype(citype);
			cinfo.setCiest(ciest);
			cinfo.setCimoney(cimoney);
			cinfo.setCisales(cisales);
			cinfo.setCileader(cileader);
			cinfo.setCiwage(ciwage);
			cinfo.setCimajor(cimajor);
			cinfo.setCiinsurance(ciinsurance);
			cinfo.setCihomepage(cihomepage);
			cinfo.setCiaddr(ciaddr);
			
			//insertResult += epdao.insertCinfo(cinfo);
			
			System.out.println();
			
			
		}
		
		System.out.println("등록된 회사 수 : " + insertResult);
		return 0;
	}
	
	
}
