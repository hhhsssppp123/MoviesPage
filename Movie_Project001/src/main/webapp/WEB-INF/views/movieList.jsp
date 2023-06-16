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
	        
	        
	        #btn-back-to-top {
			position: fixed;
			bottom: 20px;
			right: 20px;
			display: none;
			}
	    </style>
    </head>
    <body>
         <%@ include file="/WEB-INF/views/includes/top.jsp" %> 
        
      <!-- Back to top button -->
<button type="button" class="btn btn-info btn-floating btn-lg" id="btn-back-to-top">
  <i class="fas fa-arrow-up">상단으로</i>
</button>

<!-- Explanation -->
  
        
        
        
        <!-- Product section-->
              <!-- Related items section-->
        <section class="py-5 bg-light">
            <div class="container px-4 px-lg-5 mt-5">
                <h2 class="fw-bolder mb-4">인기차트</h2>
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                   
                    <c:forEach items="${mvList }" var="movie">
                    <div class="col mb-5"  >
                        <div class="card" style="height: 750px;">
                                 	
                            <div class="card-body p-1">
                                <div class="text-center">
                                <img class="card-img-top" src="${movie.mvpos}" alt="..."  />
                                           
                                           <p>
                                           영화제목: ${movie.mvtitle }
                                           </p>
                                           <p>
                                           영화감독: ${movie.mvdir }
                                           </p>
                                           <p>
                                           영화배우: ${movie.mvact }
                                           </p>
                                           <p>
                                           장르: ${movie.mvgenre }
                                           </p>
                                           <p>
                                           개봉일자:  ${movie.mvdate }
                                           </p>
                                            <p>
                                           예매율:  ${movie.mvrecount }
                                           </p>
                                </div>
                            </div>
					            	
                            
                            
                            
                            
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" style="background-color: red" onclick="callView('${movie.mvcode }' )">상세정보</a></div>
                                <div class="text-center">
                                <button class="btn btn-outline-dark mt-auto" onclick="callTicket('${movie.mvcode }' )">예매하기2</button>
                                </div>
                                 
                            </div>
                        </div>
                    </div>
                    </c:forEach>
                </div>
            </div>
          </section>
            
            
            
        
      
        <!-- Footer-->
        <footer class="py-5 bg-dark">
            <div class="container"><p class="m-0 text-center text-white">Copyright &copy; Your Website 2022</p></div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="${pageContext.request.contextPath }/resources/js/scripts.js"></script>
    </body>
    
    <script type="text/javascript">
    function InfoDisplay(){
    	
    	 var infoObj = document.getElementById('infoul');
    	 var infoObj_ClassList = infoObj.classList;
         if(infoObj_ClassList.contains('d_none')){
        	 infoObj_ClassList.remove('d_none'); 
         }else{
        	 infoObj_ClassList.add('d_none'); 
         }
         
         return null;
    }
    
    function callTicket(mvcode){
    	console.log("선택한 영화코드  :" + mvcode );
    	location.href="${pageContext.request.contextPath }/ticketPage?MLcode="+mvcode;
    	
    }
    
    function callView(mvcode){
    	console.log("선택한 영화코드  :" + mvcode );
    	location.href="${pageContext.request.contextPath }/viewMvPage?MLcode="+mvcode;
    	
    }
    let mybutton = document.getElementById("btn-back-to-top");

		 // When the user scrolls down 20px from the top of the document, show the button
		 window.onscroll = function () {
		 scrollFunction();
		 };
		
		 function scrollFunction() {
		 if (
		 document.body.scrollTop > 20 ||
		 document.documentElement.scrollTop > 20
		 ) {
		 mybutton.style.display = "block";
		 } else {
		 mybutton.style.display = "none";
		 }
		 }
		 // When the user clicks on the button, scroll to the top of the document
		 mybutton.addEventListener("click", backToTop);
		
		 function backToTop() {
		 document.body.scrollTop = 0;
		 document.documentElement.scrollTop = 0;
		 }
    
    </script>
    
    
</html>
