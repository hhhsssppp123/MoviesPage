<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
<a href ="${pageContext.request.contextPath }/jobTest">잡코리아 테스트 </a>
<a href ="${pageContext.request.contextPath }/addMovieList">CGV 영화 정보 추가</a>
<a href ="${pageContext.request.contextPath }/addthList">CGV 극장 정보 추가</a>
<a href ="${pageContext.request.contextPath }/addScheduleList">CGV 영화 시간 정보 추가</a>
<a href ="${pageContext.request.contextPath }/MainPage">메인페이지로</a>
<a href ="${pageContext.request.contextPath }/adminMain">관리자페이지</a>
<a href ="${pageContext.request.contextPath }/chatPage">채팅페이지</a>
<a href ="${pageContext.request.contextPath }/testTalbe">스플릿테스트</a>
<a href ="${pageContext.request.contextPath }/testUpdate">스플릿테스트업데이트</a>
<a href ="${pageContext.request.contextPath }/projectTest">지도</a>
<a href ="${pageContext.request.contextPath }/projectBoard">게시판 양식</a>
<a href ="${pageContext.request.contextPath }/testinsert2">insert 회사 상세정보</a>
<a href ="${pageContext.request.contextPath }/testinsert">insert 공고 정보</a>


</body>
</html>
