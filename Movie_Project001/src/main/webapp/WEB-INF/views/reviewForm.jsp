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
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/star.css">
<style type="text/css">
.d_none {
	display: none;
}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/includes/top.jsp"%>
	<c:set var="nowDate" value="<%=new Date()%>"></c:set>

	<section class="py-5 bg-light">
		<div style="padding: 10px;">
			<h1>예매내역</h1>
			<div class="row" style="padding: 10px; margin: 20px;" >
				
				<div class="col-2 d-flex align-items-center" >
					<button type="button" class="btn btn-danger btn-lg " onclick="">추천버튼</button>
				</div>
				<div class="col-7" style="text-align: center">
					<h1>${mvtitle }</h1>
					<div class="small mb-1">예매코드: ${recode }</div>
				</div>
				<div class="col-2" style="text-align: center">
					<button type="button" class="btn btn-danger btn-lg" onclick="">별로버튼</button>
				</div>
				
				<div class="row" style="text-align: center">
					
					<form name="myform" id="myform" action="" onclick="saveRecommend(this.rating.value)">
					    <fieldset>
					        <input type="radio" name="rating" value="5" id="rate1"><label for="rate1">⭐</label>
					        <input type="radio" name="rating" value="4" id="rate2"><label for="rate2">⭐</label>
					        <input type="radio" name="rating" value="3" id="rate3"><label for="rate3">⭐</label>
					        <input type="radio" name="rating" value="2" id="rate4"><label for="rate4">⭐</label>
					        <input type="radio" name="rating" value="1" id="rate5"><label for="rate5">⭐</label>
					    </fieldset>
					</form>
					
				</div>
				
			</div>
		
		<div class="row" style="margin: 20px;">
			<div class="mb-3 col-12">
				<label for="staticEmail" class="col-sm-2 col-form-label">작성자</label>
					<input type="text" readonly class="form-control-plaintext"
						id="staticEmail" value="${sessionScope.loginId}">
			</div>
		
			
			<div class="mb-3 col-12">
				<label for="rvcomment" class="form-label">관람평</label>
				<textarea class="form-control" id="rvcomment" rows="10"></textarea>
			</div>
		<div class="mb-3 col-12">
		<button type="button" class="btn btn-primary" onclick="makereview('${recode}')">작성하기</button>
		</div>
		
		</div>
		</div>
	</section>
	





	<!-- Related items section-->
	<!-- Footer-->
	<footer class="py-5 bg-dark  ">
		<div class="container fixed-bottom">
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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>

<script type="text/javascript">


	var rvrecommend = 0;
	var mvtitle = '${mvtitle}';

	$(document).ready(function() {
		var checkMsg = '${reMsg}';
		if (checkMsg.length > 0) {
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
	function saveRecommend(grade){
		console.log("saveRecommend 호출");
		console.log(grade);
		rvrecommend = grade;
	}
	
	
	function makereview(recode){
		var rvcomment = $("#rvcomment").val();
		
		console.log("영화제목 :"+mvtitle);
		console.log("예매번호 :"+recode);
		console.log("관람평 :"+rvcomment);
		console.log("별점 :"+rvrecommend);
		if(rvrecommend <= 0){
			alert("별점을 선택해주세요!");
		}else{
			console.log("chec");
		 	location.href="${pageContext.request.contextPath }/insertReview?rvcomment="+rvcomment+"&rvrecommend="+rvrecommend+"&rvrecode="+recode+"&mvtitle="+mvtitle;  
		}
		 
		
	}
	
	
</script>


</html>
