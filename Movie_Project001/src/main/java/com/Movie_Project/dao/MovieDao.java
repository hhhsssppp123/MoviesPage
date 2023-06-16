package com.Movie_Project.dao;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.Movie_Project.dto.MovieDto;
import com.Movie_Project.dto.MovieStroyDto;
import com.Movie_Project.dto.ReservationDto;
import com.Movie_Project.dto.ScheduleDto;
import com.Movie_Project.dto.TheatersDto;
import com.Movie_Project.dto.cdto;
import com.Movie_Project.dto.epDto;

public interface MovieDao {

	
	
	@Select("SELECT MAX(MVCODE) FROM MOVIES ")
	String selectMaxMvCode();
	
	@Select("SELECT MAX(MSCODE) FROM MVSTORY ")
	String selectMaxMvCodeMvStory();
	
	@Select("SELECT MAX(CINUM) FROM CINFO ")
	String selectMaxCinum();
	
	@Select("SELECT MAX(EPNUM) FROM EMPLOYMENTS ")
	String selectMaxEpnum();

	@Select("SELECT * FROM MOVIES ORDER BY MVCODE")
	ArrayList<MovieDto> selectMvList();
	
	@Insert("INSERT INTO MOVIES(MVCODE, MVTITLE, MVDIR, MVACT, MVGENRE, MVINFO, MVDATE, MVPOS) "
			+ "VALUES(#{mvcode}, #{mvtitle},#{mvdir},#{mvact},#{mvgenre},#{mvinfo},#{mvdate},#{mvpos}) ")
	int insertMovie(MovieDto movie);

	@Select("SELECT MVTITLE FROM MOVIES WHERE MVTITLE = #{mvtitle} AND MVPOS = #{mvpos}")
	String checkMovies(@Param("mvtitle") String mvtitle, @Param("mvpos") String mvpos);
	
	@Select("SELECT MVTITLE FROM MOVIES WHERE MVTITLE = #{mvtitle} AND MVDIR = #{mvdir}")
	String checkMovies2(@Param("mvtitle") String mvtitle, @Param("mvdir") String mvdir);
	
	@Select("SELECT MSTITLE FROM MVSTORY WHERE MSTITLE = #{mvtitle} AND MSSTORY = #{mvStory}")
	String checkMvStory(@Param("mvtitle") String mvtitle, @Param("mvStory") String mvStory);

	@Select("SELECT CINAME FROM CINFO WHERE CINAME = #{baseMovieName}")
	String checkCom(String baseMovieName);

	@Select("SELECT EPNAME FROM EMPLOYMENTS WHERE EPNAME = #{epname} AND EPCINAME = #{epciname}")
	String checkEp(@Param("epname")String mvtitle6, @Param("epciname")String test);
	
	
	@Select("SELECT THCODE FROM THEATERS WHERE THCODE = #{cgvThcode}")
	String selectCheckTeater(String cgvThcode);

	@Insert("INSERT INTO THEATERS(THCODE, THNAME, THADDR, THTEL)"
			+ "VALUES(#{thcode}, #{thname}, #{thaddr}, #{thtel}) ")
	int insertTheater(TheatersDto theater);

	

	@Select("SELECT * FROM THEATERS ")
	ArrayList<TheatersDto> selectTheaterList();
	
	@Select("SELECT MVCODE FROM MOVIES WHERE MVTITLE = #{mvtitle}")
	String selectMvcode(String mvtitle);

	
	
	@Insert("INSERT INTO SCHEDULES(SCMVCODE, SCTHCODE, SCROOM, SCDATE) "
			  + "VALUES(#{scmvcode}, #{scthcode}, #{scroom}, TO_DATE(#{scdate},'YYYYMMDD HH24:MI')  ) ")
	int insertSchedule(ScheduleDto schedule);


	@Select("SELECT * "
		  + "FROM THEATERS "
		  + "WHERE THCODE IN ( SELECT SCTHCODE FROM SCHEDULES WHERE SCMVCODE = #{scmvcode} ) ")
	ArrayList<TheatersDto> selectReTheaterList(String scmvcode);

	
	@Select("SELECT  TO_CHAR(SCDATE,'YYYY-MM-DD') AS SCDATE "
			+ "FROM SCHEDULES "
			+ "GROUP BY TO_CHAR(SCDATE,'YYYY-MM-DD')"
			+ "ORDER BY SCDATE ")
	ArrayList<ScheduleDto> selectSchduleList();

	
	
	@Select("SELECT TO_CHAR(SCDATE, 'YYYY-MM-DD') AS SCDATE "
			+ " FROM SCHEDULES "
			+ " WHERE SCMVCODE = #{mvcode} AND SCTHCODE = #{thcode} "
			 
			+ " GROUP BY TO_CHAR(SCDATE, 'YYYY-MM-DD') "
			+ " ORDER BY SCDATE ")
	ArrayList<ScheduleDto> selectReDayList(@Param("mvcode")String mvcode, @Param("thcode") String thcode);

	
	
	
	@Select(" SELECT * "
			  + "FROM MOVIES "
			  + "WHERE MVCODE IN ( SELECT SCMVCODE FROM SCHEDULES WHERE SCTHCODE = #{thcode} ) ")
	ArrayList<MovieDto> selectReserveMovieList(String thcode);


	
	@Select(" SELECT * FROM SCHEDULES ")
	ArrayList<ScheduleDto> optionList();


	
	
	@Select("SELECT SCROOM, TO_CHAR(SCDATE,'HH24:MI') AS SCDATE "
			+ "FROM SCHEDULES "
			+ "WHERE SCMVCODE = #{mvcode} AND SCTHCODE = #{thcode} AND TO_CHAR(SCDATE,'YYYY-MM-DD') = #{scdate} "
			+ "ORDER BY SCROOM, SCDATE ")
	ArrayList<ScheduleDto> getTimeList(@Param("mvcode") String mvcode,@Param("thcode") String thcode,@Param("scdate") String scdate);

	
	
	@Select(" SELECT MAX(RECODE) FROM RESERVATION ")
	String selectMaxRecode();

	
	
	
	@Insert("INSERT INTO RESERVATION(RECODE, REMID, RETHCODE, REROOM, REDATE, REMVCODE, RECOUNT) "
			  + "VALUES(#{recode}, #{remid}, #{rethcode},#{reroom}, TO_DATE(#{redate},'YYYY-MM-DD HH24:MI'),#{remvcode},#{recount}  ) ")
	int reservation(ReservationDto reserve);

	
	
	@Delete("DELETE FROM RESERVATION WHERE RECODE = #{recode} ")
	int deleteReserveInfo(String recode);




	ArrayList<MovieDto> selectMovieList_Rank();
	
	
	int selectTotalRenumber();

	
	@Select("SELECT MV.*, NVL(REMV.RECOUNT,0) AS MVRECOUNT"
			+ "	FROM MOVIES MV"
			+ "	LEFT OUTER JOIN (SELECT REMVCODE, SUM(RECOUNT)AS RECOUNT"
			+ "	  FROM RESERVATION"
			+ "	  GROUP BY REMVCODE) REMV"
			+ "	ON MV.MVCODE = REMV.REMVCODE"
			+ "	WHERE MVCODE = #{MLcode}"
			+ "	ORDER BY NVL(REMV.RECOUNT,0) DESC, MV.MVDATE DESC")
	MovieDto viewMv(String MLcode);

	
	@Insert("INSERT INTO MVSTORY "
			+ " VALUES(#{mscode},#{mstitle},#{msstory} )")
	int insertMS(MovieStroyDto ms);
	
	
	
	@Select("SELECT * FROM MVSTORY WHERE MSCODE = #{MLcode}")
	MovieStroyDto viewMs(String MLcode);

	
	
	ArrayList<Map<String, String>> selectReview(@Param("mvcode")String mLcode,@Param("startRow") int startRow, @Param("endRow") int endRow);
	
	
	
	int selectReviewCount(@Param("mvcode") String mLcode);


	
	
	@Select("SELECT * FROM CINFO WHERE CINAME LIKE '%${wpName}%'  ")
	ArrayList<Map<String, String>> getWPList(String wpName);
	
	
	
	
	
	
	@Insert("INSERT INTO CINFO(CINUM, CINAME, CIIND, CITYPE, CIMONEY, CILEADER, CIMAJOR, CIHOMEPAGE,CIPEOPLE ,CIEST, CISALES,CIINSURANCE, CIWAGE, CIADDR, CISUB ) "
			+ "VALUES( #{cinum},#{ciname},#{ciind},#{citype},#{cimoney},"
			+ " #{cileader},#{cimajor},#{cihomepage},#{cipeople},#{ciest},#{cisales},#{ciinsurance},#{ciwage},#{ciaddr},#{cisub} )")
	int insertCinfo(cdto cinfo);
	
	
	
	
	@Insert("INSERT INTO EMPLOYMENTS(EPNUM, EPNAME, EPCINAME, EPEDU, EPCAREER, EPTREAT, EPTYPE, EPMONEY, EPAREA, EPTIME, EPSTATE, EPPOST, EPDEADLINE )"
			+ " VALUES(#{epnum},#{epname},#{epciname},#{epedu},#{epcareer},#{eptreat},#{eptype},#{epmoney},#{eparea},#{eptime},#{epstate}, #{eppost}, #{epdeadline} ) ")
	int insertEmployments(epDto epinfo);













}





