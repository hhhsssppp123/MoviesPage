package com.Movie_Project.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.Movie_Project.dao.MemberDao;
import com.Movie_Project.dto.MovieDto;
import com.Movie_Project.dto.ScheduleDto;
import com.Movie_Project.dto.TestDto;
import com.Movie_Project.dto.TheatersDto;
import com.Movie_Project.service.MemberService;
import com.Movie_Project.service.MovieService;
import com.Movie_Project.service.ReviewsService;


@Controller
public class AdminController {

	@Autowired
	private HttpSession session;
	
	@Autowired
	private MemberService msvc; 
	
	@Autowired
	private MovieService mvsvc;
	
	@Autowired
	private ReviewsService rvsvc;
	
	
	@RequestMapping(value = "/adminMain")
	public ModelAndView adminMain() {
		ModelAndView mav = new ModelAndView();
		String loginId = (String) session.getAttribute("loginId");
		mav.setViewName("admin/adminMain");
		return mav;
	}
	
	
	@RequestMapping(value = "/adminTable")
	public ModelAndView adminTable() {
		ModelAndView mav = new ModelAndView();
		ArrayList<MovieDto> movieList = msvc.selectMvList();
		ArrayList<TheatersDto> thList = mvsvc.getTheaterList();
		ArrayList<ScheduleDto> scList = mvsvc.getScheduleList();
		String loginId = (String) session.getAttribute("loginId");
		ArrayList< Map<String, String> > memberLIst = msvc.selectMemberList();
		System.out.println(memberLIst);
		
		
		mav.addObject("memberList", memberLIst);
		mav.setViewName("admin/table");
		return mav;
	}
	
	@RequestMapping(value = "/changeMstate")
	public @ResponseBody int changeMstate(String mid, String mstate) {
		System.out.println("상태 변경할 아이디"+mid);
		System.out.println("현제 상태"+mstate);
		int updateResult =  msvc.changeMstate(mid,mstate);
		
		return updateResult;
	}
	
	
	@RequestMapping(value = "/adminDatailMemberView")
	public ModelAndView adminDatailMemberView(String mid) {
		System.out.println("관리자 회원 상세 페이지 요청");
		ModelAndView mav = new ModelAndView();
		Map<String, String> detailMemberInfo = msvc.getDetailMemberInfo(mid);
		
		mav.addObject("member", detailMemberInfo);
		mav.setViewName("admin/AdminDetailMemberView");
		return mav;
	}
	
	@Autowired
	private MemberDao mdao;
	
	@RequestMapping(value = "/testTalbe")
	public ModelAndView testTalbe() {
		System.out.println("스플릿테스트 호출");
		ModelAndView mav = new ModelAndView();
		
		TestDto t = mdao.selectTestTable();
		String[] result = t.getT1().split(" ");
		
		System.out.println("t = "+t);
		System.out.println("result = "+result);
		System.out.println(Arrays.toString(result));
		
		
		return null;
	}
	
	
	@RequestMapping(value = "/testUpdate")
	public ModelAndView testUpdate() {
		System.out.println("스플릿테스트 호출");
		ModelAndView mav = new ModelAndView();
		TestDto t = mdao.selectTestTable();
		//int updateResult = mdao.updateTestTable();
		
		System.out.println("t = "+t);
		System.out.println(t);
		return null;
	}
	
	
	
	
	
	
}
