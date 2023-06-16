package com.Movie_Project.service;


import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Movie_Project.dao.MemberDao;
import com.Movie_Project.dao.MovieDao;
import com.Movie_Project.dao.TheaterDao;
import com.Movie_Project.dto.ScheduleDto;
import com.google.gson.Gson;



@Service
public class TheaterService {

	@Autowired
	private MovieDao mvdao;
	
	@Autowired
	private MemberDao mdao;
	
	@Autowired
	private ServletContext context;
	
	@Autowired
	private TheaterDao tdao;
		
		


		public ArrayList<Map<String, String>> selectreMovieList(String tcode, String dbDay2) {
			System.out.println("SERVICE 극장목록페이지로 줄 영화 리스트 조회");
			ArrayList<Map<String, String>> movieList = tdao.selectreMovieList(tcode, dbDay2);
			return movieList;
		}
		
		
		public ArrayList<Map<String, String>> selectreMovieList2(String tcode) {
			System.out.println("SERVICE 극장목록페이지로 줄 영화 리스트 조회");
			ArrayList<Map<String, String>> movieList = tdao.selectreMovieList2(tcode);
			return movieList;
		}


		public String getTimeList2(String thcode, String scdate) {
			System.out.println("MovieService 예매가능한 상세시간 목록 조회");
			ArrayList<ScheduleDto> dayList = tdao.getTimeList2(thcode, scdate);
			System.out.println();
			return new Gson().toJson(dayList);
			
		}
		
		
	
	
	
	
}
