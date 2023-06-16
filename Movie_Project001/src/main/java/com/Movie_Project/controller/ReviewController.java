package com.Movie_Project.controller;

import java.text.DateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Movie_Project.apiTest.ApiService;
import com.Movie_Project.dto.ReviewsDto;
import com.Movie_Project.service.MemberService;
import com.Movie_Project.service.MovieService;
import com.Movie_Project.service.ReviewsService;



@Controller
public class ReviewController {
	
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
		
		@RequestMapping(value = "/insertReview")
		public ModelAndView insertReview(String rvcomment, String rvrecommend, String rvrecode, String mvtitle, RedirectAttributes ra) {
			System.out.println("/insertReview 요청");
			System.out.println("관람평쓸 예매코드 :" + rvrecode);
			System.out.println("관람평 :" + rvcomment);
			System.out.println("관람평쓸 영화제목 :" + rvrecommend);
			ModelAndView mav = new ModelAndView();
			DateFormat DFormat = DateFormat.getDateInstance();
			ReviewsDto review = new ReviewsDto();
			String loginId = (String)session.getAttribute("loginId");
			review.setRvmid(loginId);
			review.setRvcomment(rvcomment);
			review.setRvmvtitle(mvtitle);
			review.setRvrecode(rvrecode);
			review.setRvrecommend(rvrecommend);
			Date rvdate = new Date();
			System.out.println("현재시간 :"+rvdate);
			String nowDay = DFormat.format(rvdate);
			String dbDay =nowDay.replace(". ", "-");
			String dbDay2=dbDay.replace(".", "");
			System.out.println("db에 저장할 시간 : " +dbDay2);
			review.setRvdate(dbDay2);
			System.out.println(review);
			
			int insertResult =rsvc.insertReview(review);
			if(insertResult > 0) {
				ra.addFlashAttribute("reMsg", "작성완료");
				mav.setViewName("redirect:/myInfo");
			}else {
				ra.addFlashAttribute("reMsg", "작성중 오류가 발생했습니다 ");
				mav.setViewName("redirect:/reviewForm");
				
			}
			
			return mav;
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
