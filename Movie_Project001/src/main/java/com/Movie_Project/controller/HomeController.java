package com.Movie_Project.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.Movie_Project.service.EmploymentService;
import com.Movie_Project.service.MovieService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "Main";
	}
	
	@Autowired
	private MovieService mvsvc;
	
	@Autowired
	private EmploymentService epsvc;

	@RequestMapping(value = "/testinsert")
	public ModelAndView test001() throws Exception {
		System.out.println("/prject board test");
		int insertResult = epsvc.jobInsert1();
		return null;
	}
	@RequestMapping(value = "/testinsert2")
	public ModelAndView test002() throws Exception {
		System.out.println("/prject board test");
		int insertResult = epsvc.jobInsert2();
		return null;
	}
	

	@RequestMapping(value = "/projectBoard")
	public ModelAndView projectBoard() throws IOException {
		System.out.println("/prject board test");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("boardTest");
		return mav;
	}
	
	@RequestMapping(value = "/projectTest")
	public ModelAndView projectTest() throws IOException {
		System.out.println("/prject 기업 회원가입 폼 요청");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("projectTest");
		return mav;
	}
	
	@RequestMapping(value = "/find_WP")
	public ModelAndView find_WP() throws IOException {
		System.out.println("/project 회사 검색 페이지 팝업 요청");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/findWPpop");
		return mav;
	}
	
	@RequestMapping(value = "/getWPList")
	public @ResponseBody String getWPList(String WpName) {
		System.out.println("회사 검색 요청");
		System.out.println("입력한 회사이름 : "+ WpName);
		String WPList = mvsvc.getWPList(WpName);
		System.out.println(WPList);
		return WPList;
	}
	
	
	@RequestMapping(value = "/jobTest")
	public String jobTest() throws IOException {
		System.out.println("/jobTest 요청");
		int insertResult = mvsvc.jobTest();
		return "redirect:/";
	}
	
	
	@RequestMapping(value = "/addMovieList")
	public String addmovieList() throws IOException {
		System.out.println("/addMovieList 요청");
		int insertResult = mvsvc.addMovieList();
		return "redirect:/";
	}
	
	@RequestMapping(value = "/addthList")
	public String addthList() throws IOException {
		System.out.println("/addthList 요청");
		int insertResult = mvsvc.addthList();
		return "redirect:/";
	}
	
	@RequestMapping(value = "/addScheduleList")
	public String addScheduleList() throws Exception{
		System.out.println("상영스케쥴 추가 요청");
		int insertResult = mvsvc.addScheduleList();
		
		return "redirect:/";
	} 
	
	
	@RequestMapping(value = "/toHome")
	public ModelAndView toHome() throws IOException {
		System.out.println("/toHome 요청");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		return mav;
	}

	@RequestMapping(value = "/jobInsert")
	public ModelAndView jobInsert() throws IOException {
		System.out.println("/jobInsert 요청");
		int insertResult = mvsvc.jobInsert();
		System.out.println(insertResult);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		return mav;
	}
	
	
	@RequestMapping(value = "/jobInsert2")
	public ModelAndView jobInsert2() throws IOException {
		System.out.println("/jobInsert2 요청");
		int insertResult = mvsvc.jobInsert2();
		System.out.println(insertResult);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("home");
		return mav;
	}
	
	
	
	
	
	
	
}
