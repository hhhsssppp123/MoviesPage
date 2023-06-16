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
	    </style>
    </head>
    <body>
         <%@ include file="/WEB-INF/views/includes/top.jsp" %> 
        
        
        <!-- Product section-->
        <section class="py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="row gx-4 gx-lg-5 align-items-center">
                    <div class="col-md-6" >  
                    <img class="card-img-top" style="height: 500px;" src="${movie.mvpos}" alt="..." />
                    </div>
                    <div class="col-md-6">
                        <h1 class="display-5 fw-bolder">${movie.mvtitle}</h1>
                        <div class="small mb-1">예매율: ${movie.mvrecount} % <br> 장르: ${movie.mvgenre}</div>
                         <p class="lead">영화감독 : ${movie.mvdir}</p>
                        <p class="lead">배우 : ${movie.mvact}</p>
                        <p class="lead">기본 : ${movie.mvinfo}</p>
                        <div class="d-flex">
                            <input class="form-control text-center me-3" id="inputQuantity" type="num" value="1" style="max-width: 3rem" />
                            <button class="btn btn-outline-dark flex-shrink-0" type="button">
                                <i class="bi-cart-fill me-1"></i>
                                Add to cart
                            </button>
                        </div>
                       
                        
                        
                    </div>
                     <br><br><br>
                    <div class="col-7 text-break fw-semibold" style="margin-top: 30px">
                        	${ms.msstory}
					</div>
                </div>
            </div>
        </section>
        
        
      <section class="py-5 bg-light">
			<div style="padding: 10px; margin-left:50px; margin-right:50px;">
			<h1>게시판상단</h1>
			</div>	
				  <div class="row" style="padding: 10px; margin-left:200px; margin-right:200px outline-color: black;">
				  
				    <!-- <div class="row" style="margin-bottom: 10px;" > -->
 					<c:forEach items="${reviewList}" var="review"> 	
				    <div class="col-6" style="padding: 10px; margin-top: 10px; margin-bottom: 10px;">
				    <div class="row">
				    <div class="col-6">
				     <img class="img-profile rounded-circle" width="50px" height="50px"
		                                    src="${pageContext.request.contextPath }/resources/memberProfile/비회원이미지.jpg">
				    </div>
				    <div class="col-6">
				   				 <div class="small mb-1">작성자 : ${review.RVMID}</div>
				   				 <div class="small mb-1">평점 : ⭐${review.RVRECOMMEND}</div>
				    </div>
				    </div>
					<div class="small mb-1" style="margin-top: 50px;"> ${review.RVCOMMENT} </div>
				   
				   
				    </div>
				  
				  </c:forEach>	
				  
				  
				   				   
				   
				  <!-- </div> -->
				</div>
				<div style="text-align: center" id= pageListArea>
				   <c:forEach begin="${pageInfo.startPageNum }" end="${pageInfo.endPageNum }" var="pageNum" step="1">
				   		<c:choose>
					   		<c:when test="${pageNum == pageInfo.reviewPage }">
				   			<div id="whenTag">
					   			${pageNum}
					   		</div>
					   		</c:when>
					   		<c:otherwise>
					   		<div id="otherTag">
				  			 <a href="${pageContext.request.contextPath}/viewMvPage?MLcode=${movie.mvcode}&reviewPage=${pageNum}">${pageNum}</a> 
				  			<button onclick="ajaxPageList('${movie.mvcode}','${pageNum}')">${pageNum}</button>
				  			 </div>
					   		</c:otherwise>
				   		</c:choose>
				   
				   
				   
				   
				   </c:forEach>
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
         <script src="${pageContext.request.contextPath }/resources/js/scripts.js"></script>
          <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
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
    
    
   function ajaxPageList(mvcode, pageNum){
    	console.log("mvcode : "+ mvcode);
    	console.log("pageNum : "+ pageNum);
		$.ajax({
			type: "get",
			url: "${pageContext.request.contextPath }/getRePageList",
			data : {"mvcode" : mvcode , "pageNum" : pageNum},
			dataType : "json",
			success:function(pageList){
				console.log(pageList);
				var output1 ="";
				var output2 ="";
				if(pageList.length <= 0){
					alert("관람평이 없습니다..");
				}
				 else{
				output1+=  "'"+pageList.reviewPage+"'" ;		
				output2 += ' <button onclick="ajaxPageList('+ "'"+pageList.mvcode+"'" +','+ "'"+pageList.reviewPage+"'" +')">'+ "'"+pageList.reviewPage+"'" +'</button>';
				/* output += '<button type="button" class="list-group-item" onclick="selectth('+ "'"+thinfo.thcode+"'" +',this)">'+thinfo.thname+'</button> '; */ 
				}
				
				$("#whenTag").html(output1);
				$("#otherTag").html(output2);
			
			}
		});
    }
    		
    
    
    
    
    </script>
    
    
</html>
