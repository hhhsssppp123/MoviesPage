package com.Movie_Project.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.Movie_Project.apiTest.ApiService;
import com.Movie_Project.dto.MovieDto;
import com.Movie_Project.dto.ScheduleDto;
import com.Movie_Project.dto.TheatersDto;
import com.Movie_Project.service.MemberService;
import com.Movie_Project.service.MovieService;
import com.Movie_Project.service.ReviewsService;
import com.Movie_Project.service.TheaterService;
import com.google.gson.Gson;

@Controller
public class TheaterController {
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

	@Autowired
	private TheaterService tsvc;
	
	@RequestMapping(value = "/theaterListPage")
	public ModelAndView theaterList(String tcode, String scdate) {
		ModelAndView mav = new ModelAndView();
		System.out.println("/theaterList 요청");
		
		if(tcode ==null) {
			tcode = "T0002";
		}
		System.out.println(tcode);
		DateFormat DFormat = DateFormat.getDateInstance();
		
		Date rvdate = new Date();
		String nowDay = DFormat.format(rvdate);
		String dbDay =nowDay.replace(". ", "-");
		String dbDay2=dbDay.replace(".", "");
		String dbDay3=dbDay2.replace("-", "-0");
		System.out.println("db에 저장할 시간 : " +dbDay3);
		
		
		//System.out.println(retheaterList);
		//mav.addObject("retheaterList", retheaterList);
		
		ArrayList<Map<String, String>> movieList = tsvc.selectreMovieList(tcode, dbDay3);
		
		System.out.println(movieList);
		
		/*
		 * ArrayList<MovieDto> movieList = new ArrayList<MovieDto>(); movieList =
		 * msvc.selectMvList();
		 */
		
		ArrayList<TheatersDto> thList = mvsvc.getTheaterList();
		ArrayList<ScheduleDto> scList = mvsvc.getScheduleList();
		mav.addObject("thList", thList);
		mav.addObject("scList", scList);
		mav.addObject("mvList", movieList);
		mav.setViewName("TheaterList");
		return mav;
	}
	
	@RequestMapping(value = "/getTimeList2")
	public @ResponseBody String getTimeList2(String thcode, String scdate) {
		System.out.println("ajax_예매가능한 상세 시간 목록 조회 요청");
		System.out.println("thcode : "+ thcode);
		System.out.println("scdate : "+ scdate);
		String timeList = tsvc.getTimeList2( thcode, scdate);
		System.out.println(timeList);
		return timeList;
	}
	@RequestMapping(value = "/theaterRequesttcode")
	public ModelAndView theaterRequesttcode(String tcode) {
		ModelAndView mav = new ModelAndView();
		System.out.println("theaterRequesttcode 요청");
		System.out.println("thcode : "+ tcode);
		ArrayList<Map<String, String>> movieList = tsvc.selectreMovieList2(tcode);
		System.out.println("theaterRequesttcode : "+movieList);

		ArrayList<TheatersDto> thList = mvsvc.getTheaterList();
		ArrayList<ScheduleDto> scList = mvsvc.getScheduleList();
		mav.addObject("mvList", movieList);
		mav.addObject("thList", thList);
		mav.addObject("scList", scList);
		mav.addObject("checkTcode", tcode);
		
		mav.setViewName("TheaterList");
//		Gson gson = new Gson();
//		String json = gson.toJson(movieList);
		return mav;
	}
	
	
}
