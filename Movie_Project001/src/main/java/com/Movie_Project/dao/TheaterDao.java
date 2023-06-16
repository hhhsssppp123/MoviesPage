package com.Movie_Project.dao;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.Movie_Project.dto.ReviewsDto;
import com.Movie_Project.dto.ScheduleDto;

public interface TheaterDao {

	

	
	@Select("SELECT SCMVCODE, SCROOM, TO_CHAR(SCDATE,'HH24:MI') AS SCDATE  "
			+ "FROM SCHEDULES "
			+ "WHERE SCTHCODE = #{thcode} AND TO_CHAR(SCDATE,'YYYY-MM-DD') = #{scdate} "
			+ "ORDER BY SCMVCODE,SCROOM, SCDATE ")
	ArrayList<ScheduleDto> getTimeList2(@Param("thcode") String thcode,@Param("scdate") String scdate);


	ArrayList<Map<String, String>> selectreMovieList(@Param("tcode") String thcode,@Param("scdate") String scdate);

	
	ArrayList<Map<String, String>> selectreMovieList2(@Param("tcode") String thcode);
	
	
	
	
	

	
	
	
	
	
}
