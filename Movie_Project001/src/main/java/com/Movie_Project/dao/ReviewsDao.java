package com.Movie_Project.dao;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.Movie_Project.dto.ReviewsDto;

public interface ReviewsDao {

	
	@Insert("INSERT INTO REVIEWS "
			+ " VALUES(#{rvrecode},#{rvmid},#{rvcomment},#{rvrecommend},#{rvdate},#{rvmvtitle} )")
	int insertReview(ReviewsDto review);
	
	
	
	@Select("SELECT * FROM REVIEWS WHERE RVMID = #{loginId}")
	ArrayList<ReviewsDto> selectMyReview(String loginId);


	


	
	

	
	
	
	
	
}
