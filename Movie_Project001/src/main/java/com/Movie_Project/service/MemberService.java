package com.Movie_Project.service;


import java.io.File;  
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Movie_Project.dao.MemberDao;
import com.Movie_Project.dao.MovieDao;
import com.Movie_Project.dto.MemberDto;
import com.Movie_Project.dto.MovieDto;
import com.Movie_Project.dto.QnADto;
import com.Movie_Project.dto.myInfoDto;



@Service
public class MemberService {

	@Autowired
	private MovieDao mvdao;
	
	@Autowired
	private MemberDao mdao;
	
	@Autowired
	private ServletContext context;
	
	public String memberIdCheck(String inputId) {
		String checkResult = "OK";
		//아이디 확인 - SELECT
		String memberId = mdao.selectMemberInfo(inputId);
		System.out.println("memberId : " + memberId);
		if(memberId != null) {
			checkResult = "NO";
		}
		return checkResult;
	}

	public int memberJoin(MemberDto member) throws IllegalStateException, IOException {
		System.out.println("MemberService memberJoin() 호출");
		
		MultipartFile mfile = member.getMfile();
		String mprofile = "";
		if(mfile.isEmpty()) {
			// 프로필 이미지 파일을 업로드 하지 않았을 경우
			System.out.println("첨부파일 없음");
		} else {
			//파일을 업로드 했을 경우
			System.out.println("첨부파일 있음");
			UUID uuid = UUID.randomUUID();
			mprofile = uuid.toString() + "_" + mfile.getOriginalFilename();
			// 파일 저장
			// 1. 파일을 저장할 경로
			String savePath = context.getRealPath("resources\\memberProfile");
			System.out.println(savePath);
			// 2. 파일저장기능 호출
			File file = new File(savePath,mprofile);
			mfile.transferTo(file);
		}
		System.out.println("mprofile : " + mprofile);
		member.setMprofile(mprofile);
		int insertResult = 0;
		try {
			insertResult = mdao.insertMember(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return insertResult;
	}

	
	
	public ArrayList<MovieDto> selectMvList() {
		// TODO Auto-generated method stub
		
		ArrayList<MovieDto> movieList = new ArrayList<MovieDto>();
		movieList = mvdao.selectMvList();
		return movieList;
	}
	
	//MemberService - 로그인 기능
		public MemberDto memberLogin(String inputMid, String inputMpw) {
			System.out.println("MemberService memberLogin() 호출");
			MemberDto loginInfo = mdao.selectMemberLogin(inputMid, inputMpw);
			System.out.println(loginInfo);
			return loginInfo;
		}

		
		
		public ArrayList<Map<String,String>> selectMemberInfo(String loginId) {
			System.out.println("SERVICE 예매내역 조회");
			ArrayList<Map<String, String>> reserveList = mdao.selectReserveList(loginId);
			System.out.println(reserveList);
			return reserveList;
		}

		
		
		public ArrayList<QnADto> selectMyQnA(String loginId) {
			ArrayList<QnADto> myQnAList = mdao.selectMyQnA(loginId);
			return myQnAList;
		}

		public ArrayList<Map<String, String>> selectMemberList() {
			// TODO Auto-generated method stub
			ArrayList< Map<String, String> > memberLIst = mdao.selectMemberList();
			return memberLIst;
		}

		public int changeMstate(String mid, String mstate) {
			int updateResult =  mdao.changeMstate(mid, mstate);
			return updateResult;
		}

		public Map<String, String> getDetailMemberInfo(String mid) {
		System.out.println("ADMIN SERVICE 회원 상세 정보 조회");
		Map<String, String> memInfo = mdao.selectDetailMemberInfo(mid);
		System.out.println(memInfo);
		return memInfo;
	}
		
		
		
	
	
	
	
}
