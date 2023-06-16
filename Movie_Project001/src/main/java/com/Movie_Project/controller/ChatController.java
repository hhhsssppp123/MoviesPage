package com.Movie_Project.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ChatController {
	
	@Autowired
	private HttpSession session;
	
	
	
	
	@RequestMapping(value = "/chatPage")
	public ModelAndView chatPage() {
		ModelAndView mav = new ModelAndView();
		String loginId = (String) session.getAttribute("loginId");
		
		if(loginId == null) {
			mav.setViewName("redirect:/");
		}else {
			
			mav.setViewName("chat/chatPage2");
		}
		return mav;
	}
	
	
	
	
	
}
