package com.Movie_Project.dao;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.Movie_Project.dto.MemberDto;
import com.Movie_Project.dto.MovieDto;
import com.Movie_Project.dto.QnADto;
import com.Movie_Project.dto.TestDto;
import com.Movie_Project.dto.myInfoDto;

public interface MemberDao {

		@Select("SELECT MID "
			  + "FROM MEMBERS "
			  + "WHERE MID = #{inputId}")
		String selectMemberInfo(String inputId);

	
		@Insert("INSERT INTO MEMBERS(MID, MPW, MNAME, MBIRTH, MADDR, MEMAIL, MPROFILE, MSTATE) "
				   + "VALUES(#{mid}, #{mpw}, #{mname}, TO_DATE(#{mbirth},'YYYY-MM-DD'), "
				          + "#{maddr}, #{memail}, #{mprofile}, '0' )")
		int insertMember(MemberDto member);


		@Select("SELECT MID, MPROFILE, MSTATE "
		  + "FROM MEMBERS "
		  + "WHERE MID = #{mid} AND MPW = #{mpw}")
		MemberDto selectMemberLogin(@Param("mid") String inputMid, @Param("mpw")String inputMpw);
		
		
		@Select("SELECT RE.RECODE, MV.MVCODE, MV.MVTITLE, MV.MVPOS,TH.THNAME, "
				+ "			       TO_CHAR(RE.REDATE,'YYYY-MM-DD HH24:MI') AS REDATE, RE.REROOM, "
				+ "			       RE.RECOUNT,RV.RVRECODE "
				+ "			FROM RESERVATION RE "
				+ "			INNER JOIN MOVIES MV ON RE.REMVCODE = MV.MVCODE"
				+ "			INNER JOIN THEATERS TH ON RE.RETHCODE = TH.THCODE"
				+ "			LEFT OUTER JOIN REVIEWS RV ON RE.RECODE = RV.RVRECODE"
				+ "			WHERE RE.REMID = #{remid}"
				+ "			ORDER BY REDATE DESC")
		ArrayList<myInfoDto> selectMyInfo(String loginId);

		
		
		ArrayList<Map<String, String>> selectReserveList(@Param("remid") String loginId);

		
		@Select("SELECT * "
				+ " FROM MYQNA"
				+ " WHERE QID = #{loginId}")
		ArrayList<QnADto> selectMyQnA(String loginId);


		
		
		ArrayList<Map<String, String>> selectMemberList();


		int changeMstate(@Param("mid") String mid, @Param("mstate") String mstate);


		Map<String, String> selectDetailMemberInfo(String mid);


		
		@Select("SELECT * FROM TEST_TABLE22")
		TestDto selectTestTable();

		
		
		@Update("UPDATE T1")
		int updateTestTable();
		
	
	
}
