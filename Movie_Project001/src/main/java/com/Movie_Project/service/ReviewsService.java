package com.Movie_Project.service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Movie_Project.dao.MovieDao;
import com.Movie_Project.dao.ReviewsDao;
import com.Movie_Project.dto.PageDto;
import com.Movie_Project.dto.ReviewsDto;
import com.Movie_Project.dto.ScheduleDto;
import com.google.gson.Gson;



@Service
public class ReviewsService {

	@Autowired
	private ReviewsDao rvdao;
	
	@Autowired
	private MovieDao mvdao;
	
	
	
	public int insertReview(ReviewsDto review) {
		int insertResult =rvdao.insertReview(review);
		return insertResult;
	}


	public ArrayList<ReviewsDto> selectMyReview(String loginId) {
		ArrayList<ReviewsDto> myreview = rvdao.selectMyReview(loginId);
		return myreview;
	}
	

	public ArrayList<Map<String, String>> selectReview(String mLcode, int reviewPage, int reviewPageLimit, int reviewPageNumber) {
		System.out.println("service 관람평 조회 ");
		
		
		int startRow = (reviewPage -1)*reviewPageLimit + 1;
		int endRow = reviewPage * reviewPageLimit;
		ArrayList<Map<String, String>> reviewList = mvdao.selectReview(mLcode, reviewPage, endRow);
	
		return reviewList;
	}


	public int selectReviewCount(String mLcode, int reviewPage) {
		
		int rvcount = mvdao.selectReviewCount(mLcode);
		return rvcount;
	}




	public PageDto getReviewPageInfo(String mLcode, int reviewPage, int reviewPageLimit, int reviewPageNumber) {
		System.out.println("SERVICE 관람평 페이지 정보 조회");
		//1. 해당 영화에 작성된 관람평 전체 개수
		int reviewCount = mvdao.selectReviewCount(mLcode);
		
		//2. 페이지 번호 최대값
		int maxPageNum = (int)Math.ceil( (double)reviewCount / reviewPageLimit);
		//3. 시작 페이지 번호
		int startPageNum = ((int)Math.ceil( (double)reviewPage / reviewPageNumber)-1)*reviewPageNumber + 1;
		//4. 끝 페이지 번호
		int endPageNum = startPageNum + reviewPageNumber -1;
		if(endPageNum > maxPageNum) {
			endPageNum = maxPageNum;
		}
		
		
		PageDto pageInfo = new PageDto();
		pageInfo.setStartPageNum(startPageNum);
		pageInfo.setMaxPageNum(maxPageNum);
		pageInfo.setEndPageNum(endPageNum);
		pageInfo.setReviewPage(reviewPage);
		
		
		return pageInfo;
	}


	public String getReviewPageInfo2(String mvcode, int pageNum, int reviewPageLimit, int reviewPageNumber) {
		
		System.out.println("SERVICE 관람평 페이지 정보 조회");
		//1. 해당 영화에 작성된 관람평 전체 개수
		int reviewCount = mvdao.selectReviewCount(mvcode);
		
		//2. 페이지 번호 최대값
		int maxPageNum = (int)Math.ceil( (double)reviewCount / reviewPageLimit);
		//3. 시작 페이지 번호
		int startPageNum = ((int)Math.ceil( (double)pageNum / reviewPageNumber)-1)*reviewPageNumber + 1;
		//4. 끝 페이지 번호
		int endPageNum = startPageNum + reviewPageNumber -1;
		if(endPageNum > maxPageNum) {
			endPageNum = maxPageNum;
		}
		
		
		PageDto pageInfo = new PageDto();
		pageInfo.setMvcode(mvcode);
		pageInfo.setStartPageNum(startPageNum);
		pageInfo.setMaxPageNum(maxPageNum);
		pageInfo.setEndPageNum(endPageNum);
		pageInfo.setReviewPage(pageNum);
		
		
		
		return new Gson().toJson(pageInfo);
	}

	
	
	
}
