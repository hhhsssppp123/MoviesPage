<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.Date"%>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Shop Item - Start Bootstrap Template</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon"
	href="${pageContext.request.contextPath }/resources/assets/favicon.ico" />
<!-- Bootstrap icons-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link
	href="${pageContext.request.contextPath }/resources/css/styles.css"
	rel="stylesheet" />
<style type="text/css">
.d_none {
	display: none;
}
   .reserveArea{
	      	height:350px;
	      	overflow: scroll;
	      }  
	      
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/includes/top.jsp"%>
	<c:set var="nowDate" value="<%=new Date()%>"></c:set>

	<section class="py-5 bg-light">
	<div id="sectionArea">
			<h1>예매내역</h1>
			<div style="padding: 10px;"class="list-group reserveArea" >
			<c:forEach items="${myinfo }" var="reInfo">
				  <div class="row" style="padding: 10px; margin-left:100px; margin-right:100px;">
				    <div class="col-2">
				    <img class="card-img-top" src="${reInfo.MVPOS}" alt="..."  style="max-width: 120px;" />
				    </div>
				    <div class="col-7">
				    	<h1>${reInfo.MVTITLE}</h1>
								<div class="small mb-1">예매코드: ${reInfo.RECODE} </div>
								<div class="small mb-1">관람극장: ${reInfo.THNAME} ${reInfo.REROOM }</div>
								<fmt:parseDate value="${reInfo.REDATE}"
									pattern="yyyy-MM-dd HH:mm" var="reserveDate"></fmt:parseDate>
								<%-- <p class="lead">상영시간 : ${myinfo.redate}</p> --%>
								<p class="lead">상영시간 : ${ reInfo.REDATE}</p>
								<%-- <p class="lead">상영시간 : ${ reserveDate} :: 현재시간 : ${nowDate }</p> --%>
								<p class="lead">예매인원 : ${reInfo.RECOUNT} 명</p>
				    
				    </div>
				    
					 <div class="col-2" style="text-align: center">
								<c:choose>
									<c:when test="${nowDate < reserveDate}">
			                         <button type="button" class="btn btn-danger btn-lg" onclick="cancelReservaion('${reInfo.recode}')">예매취소</button>
			                         </c:when>
									<c:otherwise>
												 <c:choose>
								                         <c:when test="${reInfo.RVRECODE == null }">
								                        	 <button type="button" class="btn btn-danger btn-lg" onclick="writeReview('${reInfo.RECODE}','${reInfo.MVTITLE}')">관람평작성</button>
								                         </c:when>
								                         <c:otherwise>
								                         	 <button type="button" class="btn btn-outline-success btn-lg" onclick="viewMyReview('${reInfo.RVRECODE}')">내관람평보기</button>
								                         </c:otherwise>
						                         </c:choose>
			                        </c:otherwise>
								</c:choose>
					 </div> 
				  </div>
			</c:forEach>
				</div>	 
			<!-- myQnAList -->
			<h1>문의내역</h1>
			<div>
			<button type="button" class="btn btn-danger btn-lg" onclick = "writeQnA()">문의하기</button>

			<c:forEach items="${myQnAList }" var="myq">
				  <div class="row" style="padding: 10px; margin-left:100px; margin-right:100px;">
				  
				    <div class="col-8">
								<fmt:parseDate value="${myq.qdate}"
									pattern="yyyy-MM-dd HH:mm" var="reserveDate"></fmt:parseDate>
								<%-- <p class="lead">상영시간 : ${myinfo.redate}</p> --%>
								<h4 class="lead">문의내역</h4>
								<p class="lead">제목 ${myq.qtitle}</p>
								<p class="lead">작성일 : ${myq.qdate}</p>
								<%-- <p class="lead">상영시간 : ${ reserveDate} :: 현재시간 : ${nowDate }</p> --%>
				    </div>
				    
					 <div class="col-3" style="text-align: center">
						 <c:choose>
							      <c:when test="${myq.qstate == 0 }">
								            <button type="button" class="btn btn-danger btn-lg" onclick="writeReview('${reInfo.RECODE}','${reInfo.MVTITLE}')">수정하기</button>
						         </c:when>
								 <c:otherwise>
								          	 <button type="button" class="btn btn-outline-success btn-lg" onclick="viewMyReview('${reInfo.RVRECODE}')">답변보기</button>
							     </c:otherwise>
						                         </c:choose>
					 </div> 
				  </div>
			</c:forEach>
			 </div>
			<!--End of  myQnAList -->
			
			
			
		 
		</div>
	</section>




	<!-- Product section-->
	<%-- <section class="py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="row gx-4 gx-lg-5 align-items-center">
                    <div class="col-md-6" >
                     
                    <c:set var="nowDate" value="<%=new Date() %>"></c:set>
                     <c:forEach items="${myinfo }" var="myinfo">
                      <h1 class="display-5 fw-bolder">${myinfo.mvtitle}</h1>
                        <div class="small mb-1">극장정보: ${myinfo.thname} % </div>
                        <fmt:parseDate value="${myinfo.redate}" pattern="yyyy-MM-dd HH:mm" var="reserveDate"></fmt:parseDate>
                         <p class="lead">상영시간 : ${myinfo.redate}</p>
                         <p class="lead">상영시간 : ${ reserveDate}</p>
                         <p class="lead">상영시간 : ${ reserveDate} :: 현재시간 : ${nowDate }</p>
                         
                         <c:choose>
                         <c:when test="${nowDate < reserveDate} }">
                         예매취소버튼
                         </c:when>
                         <c:otherwise>
                         관람평 작성버튼
                         </c:otherwise>
                         </c:choose>
                         
                        <p class="lead">예매인원 : ${myinfo.recount} 명</p>
                        </c:forEach>
                      
                    </div>
                    <div class="col-md-6">
                     <c:forEach items="${myinfo }" var="myinfo">
                        <h1 class="display-5 fw-bolder">${myinfo.mvtitle}</h1>
                        <div class="small mb-1">예매율: ${myinfo.thname} % </div>
                         <p class="lead">영화감독 : ${myinfo.redate}</p>
                        <p class="lead">배우 : ${myinfo.recount}</p>
                       </c:forEach>
                        <div class="d-flex">
                            <input class="form-control text-center me-3" id="inputQuantity" type="num" value="1" style="max-width: 3rem" />
                            <button class="btn btn-outline-dark flex-shrink-0" type="button">
                                <i class="bi-cart-fill me-1"></i>
                                Add to cart
                            </button>
                        </div>
                        
                        
                    </div>
                </div>
            </div>
        </section> --%>




	<!-- Related items section-->
	<!-- Footer-->
	<footer class="py-5 bg-dark">
		<div class="container">
			<p class="m-0 text-center text-white">Copyright &copy; Your
				Website 2022</p>
		</div>
	</footer>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script
		src="${pageContext.request.contextPath }/resources/js/scripts.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/js/scripts.js"></script>
</body>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	


<script type="text/javascript">

$(document).ready(function(){
 var checkMsg = '${reMsg}';
 if(checkMsg.length >0){
	 alert(checkMsg);
 }
	});



	function InfoDisplay() {

		var infoObj = document.getElementById('infoul');
		var infoObj_ClassList = infoObj.classList;
		if (infoObj_ClassList.contains('d_none')) {
			infoObj_ClassList.remove('d_none');
		} else {
			infoObj_ClassList.add('d_none');
		}

		return null;
	}
	
	function cancelReservaion(selectRecode){
		console.log(selectRecode);
		location.href="${pageContext.request.contextPath }/cancelReservaion?cancelRecode="+selectRecode;
		
	}
	
	function viewMyReview(rvrecode){
		console.log("내리뷰보기호출");
		console.log("열람할 내 관람평 번호 : "+ rvrecode);
	}
	
	
	function writeReview(recode,mvtitle){
		console.log(recode);
		console.log(mvtitle);
		window.open('${pageContext.request.contextPath }/reviewForm?recode='+recode+"&mvtitle="+mvtitle,'reviewForm',"width=400,height=600,top=10,left=100");
		
	}
	
	function writeQnA(){
		console.log("qna요청");
		location.href="${pageContext.request.contextPath }/writeQnAForm";
		
	}
	
</script>


</html>
