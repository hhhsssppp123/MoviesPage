package com.Movie_Project.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Movie_Project.dto.MemberDto;
import com.Movie_Project.dto.MovieDto;
import com.Movie_Project.dto.QnADto;
import com.Movie_Project.dto.ScheduleDto;
import com.Movie_Project.dto.TheatersDto;
import com.Movie_Project.service.MemberService;
import com.Movie_Project.service.MovieService;
import com.Movie_Project.service.ReviewsService;

@Controller
public class MemberController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private MemberService msvc; 
	
	@Autowired
	private MovieService mvsvc;
	
	@Autowired
	private ReviewsService rvsvc;
	
	
	@RequestMapping(value = "/myInfo")
	public ModelAndView myInfo() {
		System.out.println("/myInfo �슂泥�");
		ModelAndView mav = new ModelAndView();
		String loginId = (String) session.getAttribute("loginId");
		ArrayList< Map<String, String> > reserveList = msvc.selectMemberInfo(loginId);
		System.out.println(reserveList);
		//ArrayList<ReviewsDto> myreview = rvsvc.selectMyReview(loginId);
		//System.out.println(myreview);
		mav.addObject("myinfo", reserveList);
		ArrayList<QnADto> myQnA= msvc.selectMyQnA(loginId);
		mav.addObject("myQnAList", myQnA);
		System.out.println(reserveList);
		//mav.addObject("myreview", myreview);
		
		mav.setViewName("member/myInfo");
		return mav;
	}
	
	@RequestMapping(value = "/writeQnAForm")
	public ModelAndView writeQnAForm() {
		String loginId = (String) session.getAttribute("loginId");
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("member/writeQnA");
		return mav;
	}
	
	
	@RequestMapping(value = "/MainPage")
	public ModelAndView MainPage(boolean check) {
		System.out.println("/MainPage �슂泥�");
		ModelAndView mav = new ModelAndView();
		ArrayList<MovieDto> movieList = msvc.selectMvList();
		ArrayList<TheatersDto> thList = mvsvc.getTheaterList();
		ArrayList<ScheduleDto> scList = mvsvc.getScheduleList();
		mav.addObject("thList", thList);
		mav.addObject("mvList", movieList);
		mav.addObject("scList", scList);
		
		mav.setViewName("Main");
		return mav;
	}
	
	public ModelAndView insertBottom() {
	ModelAndView mav = new ModelAndView();
	boolean check = true;
	ArrayList<MovieDto> movieList = msvc.selectMvList();
	ArrayList<TheatersDto> thList = mvsvc.getTheaterList();
	ArrayList<ScheduleDto> scList = mvsvc.getScheduleList();
	mav.addObject("thList", thList);
	mav.addObject("mvList", movieList);
	mav.addObject("scList", scList);
	System.out.println(movieList);
	System.out.println(thList);
	System.out.println(scList);
	mav.setViewName("redirect:/MainPage?check="+check);
	return mav;
	}
	
	@RequestMapping(value = "/logOut")
	public ModelAndView logOut(String currentUrl) {
		System.out.println("/logOut �슂泥�");
		ModelAndView mav = new ModelAndView();
		System.out.println(currentUrl);
		session.removeAttribute("loginId");
		System.out.println("돌아갈 주소 "+currentUrl );
		mav.setViewName("redirect:"+currentUrl);
		return mav;
	}
	
	
	@RequestMapping(value = "/MemberJoinForm")
	public ModelAndView MemberJoinForm() {
		System.out.println("/MemberJoinForm �슂泥�");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/MemberJoinForm");
		return mav;
	}
	
	@RequestMapping(value = "/memberIdCheck")
	public @ResponseBody String memberIdCheck(String inputId) {
		System.out.println("�븘�씠�뵒 以묐났 泥댄겕 �솗�씤 �슂泥�");
		System.out.println("�엯�젰�븳 �븘�씠�뵒 : " + inputId);
		//1. �븘�씠�뵒 以묐났 �솗�씤 湲곕뒫 �샇異�
		String idCheckResult = msvc.memberIdCheck(inputId);
		//2. 以묐났�솗�씤 寃곌낵媛� 由ы꽩
		return idCheckResult;
	}
	
	@RequestMapping(value = "/memberJoin")
	public ModelAndView memberJoin(MemberDto member) throws IllegalStateException, IOException {
		System.out.println("�쉶�썝媛��엯 �슂泥�");
		ModelAndView mav = new ModelAndView();
		//1. �쉶�썝�젙蹂� �뙆�씪硫뷀꽣 �솗�씤
		System.out.println(member);
		//2. �쉶�썝媛��엯 �꽌鍮꾩뒪 �샇異�
		int insertResult = msvc.memberJoin(member);
		if(insertResult > 0) {
			System.out.println("�쉶�썝媛��엯 �꽦怨�");
			mav.setViewName("redirect:/");
		} else {
			System.out.println("�쉶�썝媛��엯 �떎�뙣");
			mav.setViewName("redirect:/MemberJoinForm");
		}
		return mav;
	}
	
	@RequestMapping(value = "/memberLoginForm")
	public ModelAndView memberLoginForm() {
		System.out.println("濡쒓렇�씤 �럹�씠吏� �씠�룞�슂泥�");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("member/MemberLoginForm");
		return mav;
	}
	
	@RequestMapping(value = "/memberLogin")
	public ModelAndView memberLogin(String inputMid, String inputMpw, RedirectAttributes ra) {
		ModelAndView mav = new ModelAndView();
		String admin = "admin";
		//1. 濡쒓렇�씤 �럹�씠吏��뿉�꽌 �쟾�넚�븳 �븘�씠�뵒, 鍮꾨�踰덊샇 �솗�씤
		System.out.println("�엯�젰�븳 �븘�씠�뵒 : " + inputMid);
		System.out.println("�엯�젰�븳 鍮꾨�踰덊샇 : " + inputMpw);
		//2. �엯�젰�븳 �븘�씠�뵒, 鍮꾨�踰덊샇濡� �쉶�썝�젙蹂� 議고쉶( �븘�씠�뵒(mid), �봽濡쒗븘(mprofile) )
		MemberDto loginInfo = msvc.memberLogin(inputMid, inputMpw);
		if(loginInfo == null) { // 議고쉶�릺�뒗 �쉶�썝�젙蹂닿� �뾾�쓣 寃쎌슦
			// 濡쒓렇�씤 �럹�씠吏� �씠�룞
			mav.setViewName("redirect:/memberLoginForm");
		} else {
			if( loginInfo.getMid().equals("admin") ) {
				System.out.println("입력아이디 : admin");
				session.setAttribute("loginId", loginInfo.getMid());
				session.setAttribute("loginProfile", loginInfo.getMprofile());
				mav.setViewName("redirect:/adminMain");
			}else {
				if(loginInfo.getMstate().equals("1")) {
					System.out.println("이용중지계정");
					ra.addFlashAttribute("redirectMsg", "이용중지된 계정입니다. 관리자아게 문의해주세요 ");
					mav.setViewName("rediect:/");
				}else {
					session.setAttribute("loginId", loginInfo.getMid());
					session.setAttribute("loginProfile", loginInfo.getMprofile());
					System.out.println(session.getAttribute("loginId"));
					System.out.println(session.getAttribute("loginProfile"));
					//2. 硫붿씤�럹�씠吏� �씠�룞
					mav.setViewName("redirect:/MainPage");
				}
			}
		}
		return mav;
	}	
	
	
}
