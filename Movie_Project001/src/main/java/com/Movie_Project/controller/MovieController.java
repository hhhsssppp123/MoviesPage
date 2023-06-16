package com.Movie_Project.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Movie_Project.apiTest.ApiService;
import com.Movie_Project.dto.MovieDto;
import com.Movie_Project.dto.MovieStroyDto;
import com.Movie_Project.dto.PageDto;
import com.Movie_Project.dto.ReservationDto;
import com.Movie_Project.dto.ScheduleDto;
import com.Movie_Project.dto.TheatersDto;
import com.Movie_Project.service.MemberService;
import com.Movie_Project.service.MovieService;
import com.Movie_Project.service.ReviewsService;

@Controller
public class MovieController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private MemberService msvc; 
	
	@Autowired
	private MovieService mvsvc;
	
	@Autowired
	private ApiService asvc;
	
	@Autowired
	private ReviewsService rsvc;
	

	@RequestMapping(value = "/reviewForm")
	public ModelAndView reviewForm(String recode, String mvtitle) {
		System.out.println("/reviewForm 요청");
		System.out.println("관람평쓸 예매코드 " + recode);
		System.out.println("관람평쓸 영화제목 " + mvtitle);
		ModelAndView mav = new ModelAndView();
		mav.addObject("recode", recode);
		mav.addObject("mvtitle", mvtitle);
		mav.setViewName("reviewForm");
		
		return mav;
	}
	
	
	
	@RequestMapping(value = "/cancelReservaion")
	public ModelAndView cancelReservaion(String cancelRecode, RedirectAttributes ra) {
		System.out.println("/cancelReservaion 요청");
		System.out.println("취소할 예매코드 " + cancelRecode);
		ModelAndView mav = new ModelAndView();
		int deleteResult = mvsvc.deleteReserveInfo(cancelRecode);
		System.out.println("deleteResult: "+deleteResult);
		if(deleteResult > 0) {
			ra.addFlashAttribute("reMsg", "예매취소성공!! ");
		}else {
			ra.addFlashAttribute("reMsg", "예매취소에실패하였습니다!! ");
			
		}
		
		mav.setViewName("redirect:/myInfo");
		
		return mav;
	}
	
	
	
	@RequestMapping(value = "/getRePageList")
	public @ResponseBody String getRePageList(String mvcode, int pageNum ) {
		System.out.println("/viewMvPage 요청");
		System.out.println("요청받은 MLcode : " + mvcode);
		System.out.println("관람평 페이지 번호 : " + pageNum);

		ModelAndView mav = new ModelAndView();
		
		int reviewPageLimit = 2; // 한페이지에 보여줄 관람평의 개수 
		int reviewPageNumber = 10; // 한페이지 번호의 개수
		
		
		//관람평 페이지 정보 조회
		String pageList = rsvc.getReviewPageInfo2(mvcode, pageNum,reviewPageLimit,reviewPageNumber);
		System.out.println("pageList: "+ pageList);
		
		
		
		
		return pageList;
	}
	
	
	
	
	
	
	
	@RequestMapping(value = "/viewMvPage")
	public ModelAndView viewMvPage(String MLcode, @RequestParam(value = "reviewPage", defaultValue ="1")int reviewPage ) {
		System.out.println("/viewMvPage 요청");
		System.out.println("요청받은 MLcode : " + MLcode);
		System.out.println("관람평 페이지 번호 : " + reviewPage);

		ModelAndView mav = new ModelAndView();
		MovieDto movie = new MovieDto();
		movie = mvsvc.viewMv(MLcode);
		MovieStroyDto ms = new MovieStroyDto();
		ms = mvsvc.viewMs(MLcode);
		mav.addObject("movie", movie);
		String ms_2 = ms.getMsstory().replace(".", ". <br>");
		ms.setMsstory(ms_2);
		
		int reviewPageLimit = 2; // 한페이지에 보여줄 관람평의 개수 
		int reviewPageNumber = 10; // 한페이지 번호의 개수
		
		ArrayList<Map<String, String>> reviewList = rsvc.selectReview(MLcode, reviewPage, reviewPageLimit, reviewPageNumber);
		System.out.println(reviewList);
		
		//관람평 페이지 정보 조회
		PageDto pageInfo = rsvc.getReviewPageInfo(MLcode, reviewPage,reviewPageLimit,reviewPageNumber);
		mav.addObject("pageInfo", pageInfo);
		
		
		int rvcount = rsvc.selectReviewCount(MLcode, reviewPage);
		System.out.println(rvcount);
		
		mav.addObject("rvcount", rvcount);
		mav.addObject("reviewList", reviewList);
		mav.addObject("ms", ms);
		mav.setViewName("viewMv");
		
		
		return mav;
	}
	
	
	
	
	
	@RequestMapping(value = "/ticketPage")
	public ModelAndView ticketPage(String MLcode) {
		System.out.println("/ticketPage 요청");
		ModelAndView mav = new ModelAndView();
		ArrayList<MovieDto> movieList = new ArrayList<MovieDto>();
		movieList = msvc.selectMvList();
		ArrayList<TheatersDto> thList = mvsvc.getTheaterList();
		ArrayList<ScheduleDto> scList = mvsvc.getScheduleList();
		ArrayList<ScheduleDto> optionList = mvsvc.optionList();
		mav.addObject("thList", thList);
		mav.addObject("mvList", movieList);
		mav.addObject("scList", scList);
		mav.addObject("optionList", optionList);
		mav.setViewName("Ticket");
		
		System.out.println("요청받은 MLcode : " + MLcode);
		mav.addObject("MLcode", MLcode);
		
		return mav;
	}
	
	
	@RequestMapping(value = "/getReTheaterList")
	public @ResponseBody String getReTheaterList(String mvcode) {
		System.out.println("ajax_예매가능한 극장 목록 조회 요청");
		String thList = mvsvc.getReTheaterList(mvcode);
		System.out.println(thList);
		return thList;
	}
	
	
	@RequestMapping(value = "/getReTheaterList2")
	public  ModelAndView getReTheaterList2(String mvcode) {
		ModelAndView mav = new ModelAndView();
		System.out.println("무비차트에서 서비스로청");
		System.out.println("요청받은 무비코드 : " + mvcode);
		String thList = mvsvc.getReTheaterList(mvcode);
		System.out.println(thList);
		mav.addObject("thList", thList);
		mav.setViewName("Ticket");
		return mav;
	}

	
	@RequestMapping(value = "/getReserveMovieList")
	public @ResponseBody String getReserveMovieList(String thcode) {
		System.out.println("ajax_예매가능한 극장 목록 조회 요청");
		String mvList = mvsvc.getReserveMovieList(thcode);
		System.out.println(mvList);
		return mvList;
	}
	
	
	@RequestMapping(value = "/getReDayList")
	public @ResponseBody String getReDayList(String mvcode, String thcode) {
		System.out.println("ajax_예매가능한 시간 목록 조회 요청");
		String dayList = mvsvc.getReDayList(mvcode, thcode);
		System.out.println(dayList);
		return dayList;
	}
	
	@RequestMapping(value = "/getTimeList")
	public @ResponseBody String getTimeList(String mvcode, String thcode, String scdate) {
		System.out.println("ajax_예매가능한 상세 시간 목록 조회 요청");
		System.out.println("mvcode : "+ mvcode);
		System.out.println("thcode : "+ thcode);
		System.out.println("scdate : "+ scdate);
		String timeList = mvsvc.getTimeList(mvcode, thcode, scdate);
		System.out.println(timeList);
		return timeList;
	}
	
	
	@RequestMapping(value = "/reservation")
	public @ResponseBody String reservation(String mvcode,String thcode, String sdate, String stime, String sroom, int scount) throws Exception {
		
		String recode = maxRecode();
		ReservationDto reserve = new ReservationDto();
		reserve.setRecode(recode);
		reserve.setRemid((String)session.getAttribute("loginId"));
		reserve.setRethcode(thcode);
		reserve.setReroom(sroom);
		//sdate 시간 날짜 합치기
		reserve.setRedate(sdate+" "+stime);
		reserve.setRemvcode(mvcode);
		reserve.setRecount(scount);
		int insertRe = mvsvc.reservation(reserve);
		System.out.println("넘겨줄 reserve"+reserve);
		String nextPcUrl = reserveMovie_PayReady(reserve);
		
		//System.out.println("insertRe: " + insertRe);
		return nextPcUrl;
		
	}
	
	@RequestMapping(value = "/reservationreal")
	public @ResponseBody int reservationreal(String mvcode,String thcode, String sdate, String stime, String sroom, int scount) throws Exception {
		System.out.println("reservationreal호출함");
		String recode = maxRecode();
		ReservationDto reserve = new ReservationDto();
		reserve.setRecode(recode);
		reserve.setRemid((String)session.getAttribute("loginId"));
		reserve.setRethcode(thcode);
		reserve.setReroom(sroom);
		//sdate 시간 날짜 합치기
		reserve.setRedate(sdate+" "+stime);
		reserve.setRemvcode(mvcode);
		reserve.setRecount(scount);
		int insertRe = mvsvc.reservation(reserve);
		//System.out.println("insertRe: " + insertRe);
		return insertRe;
		
	}
	
	
	@RequestMapping(value = "/reserveMovie_PayReady")
	public @ResponseBody String reserveMovie_PayReady(ReservationDto reserve) throws Exception {
		System.out.println("ajax_영화예매_결제준비 요청");
		//결제 QR코드 페이지 URL
		String nextPcUrl = asvc.kakaoPay_ready(reserve, session);
		System.out.println(nextPcUrl);
		return nextPcUrl;
	}
	
	
	
	@RequestMapping(value = "/reserveMovie_PayApproval")
	public ModelAndView reserveMovie_PayApproval(String pg_token, String recode) throws Exception {
		
		ModelAndView mav = new ModelAndView();
		System.out.println("_approval");
		System.out.println("pg_token : " + pg_token);
		String tid = (String)session.getAttribute("payTid");
		System.out.println("tid : " + tid);
		System.out.println(recode);
		String payResult = asvc.kakaoPay_approval(tid,pg_token,recode,session);
		
		mav.addObject("reserveResult", payResult);
		mav.setViewName("Ticket_PayResult");
		return mav;
	}
	
	@RequestMapping(value = "/reserveMovie_PayCancel")
	public ModelAndView reserveMovie_PayCancel(String recode) throws Exception {
		System.out.println("ajax_영화예매_결제취소");
		ModelAndView mav = new ModelAndView();
		int deleteReserve = mvsvc.deleteReserveInfo(recode);
		mav.addObject("reserveResult", "Cancel");
		mav.setViewName("Ticket_PayResult");
		return mav;
	}		
	
	@RequestMapping(value = "/reserveMovie_PayFail")
	public ModelAndView reserveMovie_PayFail(String recode) throws Exception {
		System.out.println("ajax_영화예매_결제실패");
		ModelAndView mav = new ModelAndView();
		int deleteReserve = mvsvc.deleteReserveInfo(recode);
		mav.addObject("reserveResult", "Fail");
		mav.setViewName("Ticket_PayResult");
		return mav;
	}	
	
	
	
	public String maxRecode() {
		String maxRecode = mvsvc.selectMaxRecode();
		System.out.println("예매번호 최대값 : " + maxRecode);
		String recode = "RE";
		if(maxRecode == null) {
			recode = recode + String.format("%03d", 1);
			System.out.println("처음 영화코드 : "+recode);
		}else {
			int mvcodeNum = Integer.parseInt(maxRecode.replace("RE", "")) +1;
			recode =  recode + String.format("%03d", mvcodeNum);
		}
		System.out.println("예매코드 : "+recode);
		return recode;
	}
	
	
	@RequestMapping(value = "/reservationPage")
	public @ResponseBody String reservationPage(String mvcode,String thcode, String sdate, String stime, String sroom) {
		System.out.println("예매할영화코드 : "+ mvcode);
		System.out.println("예매할극장코드: "+ thcode);
		System.out.println("예매할날짜: "+ sdate);
		System.out.println("예매할시간: "+ stime);
		System.out.println("예매할상영관이름: "+ sroom);
		String recode = maxRecode();
		return recode;
		}
		
	
	
	
	@RequestMapping(value = "/movieList")
	public ModelAndView movieList() {
		System.out.println("/movieList 요청");
		ModelAndView mav = new ModelAndView();
		ArrayList<MovieDto> movieList =mvsvc.selectMovieList_Rank(); 
		mav.addObject("mvList", movieList);
		mav.setViewName("movieList");
		return mav;
	}
	
	
	
}
