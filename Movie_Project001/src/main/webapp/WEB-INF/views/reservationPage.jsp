<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Shop Item - Start Bootstrap Template</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath }/resources/assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="${pageContext.request.contextPath }/resources/css/styles.css" rel="stylesheet" />
        <style type="text/css">
	     .d_none{
	            display: none;
	        }
	        
	      .reserveArea{
	      	height:350px;
	      	overflow: scroll;
	      }  
	      
	      .selObj{
	      	background-color: blue;
	      	color: white;
	      }
	      
	     .selectM{
	     background-color: gray;
	     	color: white;
	     }
	      
	    </style>
    </head>
    <body>
         <%@ include file="/WEB-INF/views/includes/top.jsp" %> 
        
        
        <!-- Product section-->
        <section class="py-5 bg-light">
            <div class="container px-10 px-lg-10 mt-10">
                <h2 class="fw-bolder mb-4">예매정보</h2>
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center"  >
                     <div class="col-lg-6 col-md-6">
                   		
  
   <div class="col-12">
    <label for="inputEmail4" class="form-label">상영시간</label>
    <input type="text" readonly="readonly" class="form-control" id="inputEmail3" value="  ${reservationInfo.redate}">
  </div>
  <div class="col-12">
    <label for="inputAddress" class="form-label">상영관</label>
    <input type="text" readonly="readonly" class="form-control" id="inputAddress" value=" ${reservationInfo.reroom} ">
  </div>
  <div class="col-md-6">
    <label for="inputCity" class="form-label">예약자 아이디</label>
    <input type="text"  readonly="readonly" class="form-control" id="inputCity" value="${reservationInfo.remid}">
  </div>
  <div class="col-md-6">
    <label for="inputCity" class="form-label">예매코드</label>
    <input type="text"  readonly="readonly" class="form-control" id="inputCity" value="${reservationInfo.recode}">
  </div>
  
  <div class="col-md-6">
    <label for="inputZip" class="form-label">예매인원</label>
					           <input type="number" id="scount">
  </div>
                   		
                	</div>
                    
                    <span>
			            <div class="card-body" id="refi">
			            <h5 class="card-title">예매확인</h5>
			           	 	<div>
					            <p>예매하시겠습니까?</p>
					            <label for="inputZip" class="form-label">예매인원</label>
					           <!--  <button onclick="reservation()">예매1</button> -->
 					            <button onclick="reservation2()">예매2</button> 
			            	</div>
			            </div>
                	</span>
                	
            	</div>
            	</div>
      
          </section>
         
       <!-- Related items section-->
         <%@ include file="/WEB-INF/views/includes/bottom.jsp" %> 
        <!-- Footer-->
        
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2022</p></div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="${pageContext.request.contextPath }/resources/js/scripts.js"></script>
    </body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
   
    <script type="text/javascript">
	
    
  
    
 
    
    function  reservation2(){
	console.log("reservation 호출");
	console.log("선택한 극장 :  " + '${reservationInfo.reroom}');
	var scount_Obj = document.getElementById('scount');
	var scount = scount_Obj.value;
	
	console.log("선택한 인원수vvvvvv :  " + scount);
	console.log("선택한 극장 :  " + '${reservationInfo.reroom}');
   	
	
	location.href="${pageContext.request.contextPath }/reservation?mvcode="+'${reservationInfo.remvcode}'+"&thcode="+'${reservationInfo.rethcode}'+"&sdate="+'${reservationInfo.redate}'+"&stime="+
			'${stime}'+"&sroom="+'${reservationInfo.reroom}'+"&scount="+scount;
    
   };
    
    
    
    function InfoDisplay(){
    	
    	 var infoObj = document.getElementById('infoul');
    	 var infoObj_ClassList = infoObj.classList;
         if(infoObj_ClassList.contains('d_none')){
        	 infoObj_ClassList.remove('d_none'); 
         }else{
        	 infoObj_ClassList.add('d_none'); 
         }
         
         return null;
    };
    
    
  
    
    </script>
    
    
</html>
