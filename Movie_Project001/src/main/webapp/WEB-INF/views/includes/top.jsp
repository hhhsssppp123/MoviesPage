 <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
 
 <style>

#listUl1>li:hover{
	     background-color: burlywood;
	     
}
	        


</style>
 
 
 <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="MainPage">Movie_Project</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4" id="listUl1">
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="MainPage">Home</a></li>
                        <li class="nav-item"><a class="nav-link" href="#!">About</a></li>
                        
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">영화</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath }/movieList">영화</a></li>
                                <li><hr class="dropdown-divider" /></li>
                                <li><a class="dropdown-item" href="#!">무비차트</a></li>
                            </ul>
                        </li>
                       
                       <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">극장</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath }/theaterListPage">극장</a></li>
                                <li><hr class="dropdown-divider" /></li>
                                <li><a class="dropdown-item" href="#!">CGV 극장</a></li>
                                <li><a class="dropdown-item" href="#!">특별관</a></li>
                            </ul>
                        </li>
                        
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">예매</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath }/ticketPage">예매</a></li>
                                <li><hr class="dropdown-divider" /></li>
                                <li><a class="dropdown-item" href="#!">상영스케줄</a></li>
                                <li><a class="dropdown-item" href="#!">New Arrivals</a></li>
                            </ul>
                        </li>
                        
                       <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">스토어</a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="#!">스토어</a></li>
                                <li><hr class="dropdown-divider" /></li>
                                <li><a class="dropdown-item" href="#!">영화예매권</a></li>
                                <li><a class="dropdown-item" href="#!">음식</a></li>
                            </ul>
                        </li>
                    </ul>
                      
                       <c:choose>
                        	<c:when test="${sessionScope.loginId == null }"> <%-- 로그인이 되어 있지 않을 경우 --%>
	                        <li>
	                                <img class="img-profile rounded-circle" width="50px" height="50px"
	                                    src="${pageContext.request.contextPath }/resources/memberProfile/비회원이미지.jpg">
	                        		 <span class="badge bg-dark text-white ms-1 rounded-pill">비회원</span>
	                        		 ${sessionScope.loginId}
	                        </li>                        	
                        	</c:when>
                        	<c:otherwise> <%-- 로그인이 되어 있을 경우 --%>
 							
	                        <li>
	                        		<span class="badge bg-dark text-white ms-1 rounded-pill"> ${sessionScope.loginId}</span>
										                        
	                                <%-- <span class="mr-2 d-none d-lg-inline text-gray-600 small">
	                               	${sessionScope.loginId}
	                                </span> --%>
	                                <c:choose>
	                                	<c:when test="${sessionScope.loginProfile == null}">  <%-- 프로필이 등록되어 있지 않을 경우 --%>
		                                <img class="img-profile rounded-circle" width="50px" height="50px"
		                                    src="${pageContext.request.contextPath }/resources/memberProfile/비회원이미지.jpg">
	                                	</c:when>
	                                	<c:otherwise>    <%-- 프로필이 등록 되어 있을 경우 --%>
			                                <img class="img-profile rounded-circle" width="50px" height="50px"
			                                    src="${pageContext.request.contextPath }/resources/memberProfile/${sessionScope.loginProfile}">
	                                	</c:otherwise>
	                                </c:choose>
	                        </li>
                        	</c:otherwise>
                        </c:choose>
                     
                     
		                     
                    <div class="d-flex">
                        <button class="btn btn-outline" onclick="InfoDisplay()">
                             <i class="bi-cart-fill me-1">INFO</i> 
                            <ul class="d_none" id="infoul">
                             <c:choose>
                            <c:when test="${sessionScope.loginId == null }"> 
                            <li><a class="dropdown-item " href="${pageContext.request.contextPath }/memberLoginForm">로그인</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath }/MemberJoinForm">회원가입</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath }/toHome">home으로이동하기</a></li>
                            </c:when>
                            <c:otherwise> 
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath }/myInfo">내정보</a></li>
                            <li><a class="dropdown-item" href="javascript:logout()">로그아웃</a></li>
                            <li><a class="dropdown-item" href="${pageContext.request.contextPath }/toHome">home으로이동하기</a></li>
                            </c:otherwise>
                            </c:choose>
                            </ul>
                            <span class="badge bg-dark text-white ms-1 rounded-pill"></span>
                        </button>
                    </div> 
                </div>
            </div>
            <script type="text/javascript">
            var currentUrl = window.location.href;
            function logout(){
            	location.href= "${pageContext.request.contextPath }/logOut?currentUrl="+currentUrl;
            }
            </script>
            
        </nav>
  
        
			
        
