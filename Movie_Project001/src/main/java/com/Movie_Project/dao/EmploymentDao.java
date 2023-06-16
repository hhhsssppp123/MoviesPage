package com.Movie_Project.dao;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.Movie_Project.dto.CinfoDto;
import com.Movie_Project.dto.EmploymentDto;



public interface EmploymentDao {

	@Select("SELECT MAX(EPNUM) FROM EMPLOYMENT ")
	String selectMaxEpnum();
	
	@Select("SELECT EPNAME FROM EMPLOYMENT WHERE EPNAME = #{epname} AND EPCINAME = #{epciname}")
	String checkEp(@Param("epname")String epname, @Param("epciname")String epciname);

	@Insert("INSERT INTO EMPLOYMENT(EPNUM, EPNAME, EPCINAME, EPEDU, EPCAREER, EPTREAT, EPTYPE, EPMONEY, EPAREA, EPTIME, EPSTATE, EPPOST, EPDEADLINE , EPESSTATE)"
			+ " VALUES(#{epnum},#{epname},#{epciname},#{epedu},#{epcareer},#{eptreat},#{eptype},#{epmoney},#{eparea},#{eptime},#{epstate}, #{eppost}, #{epdeadline}, #{epesstate} ) ")
	int insertEmployments(EmploymentDto epinfo);

	@Select("SELECT * FROM CINFO WHERE CINAME LIKE '%${ciname}%'  ")
	ArrayList<Map<String, String>> getWPList(String ciname);
	
	@Select("SELECT CINAME FROM CINFO WHERE CINAME = #{baseMovieName}")
	String checkCom(String baseCName);

	@Insert("INSERT INTO CINFO(CINUM, CINAME, CIIND, CITYPE, CIMONEY, CILEADER, CIMAJOR, CIHOMEPAGE,CIPEOPLE ,CIEST, CISALES,CIINSURANCE, CIWAGE, CIADDR ) "
			+ "VALUES( #{cinum},#{ciname},#{ciind},#{citype},#{cimoney},"
			+ " #{cileader},#{cimajor},#{cihomepage},#{cipeople},#{ciest},#{cisales},#{ciinsurance},#{ciwage},#{ciaddr} )")
	int insertCinfo(CinfoDto cinfo);

	@Update("UPDATE CINFO SET CIADDR = #{ciaddr} WHERE CINUM = #{cinum} ")
	int updateCiAddr(@Param("cinum")String cinum,@Param("ciaddr")String ciaddr);
	
	@Select("SELECT MAX(CINUM) FROM CINFO ")
	String selectMaxCinum();

	
}
