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
        <section class="py-5 bg-light" style="margin-left: 30px; margin-right: 30px">
            <div class="container px-10 px-lg-10 mt-10" id="section1">
                <h2 class="fw-bolder mb-4">TheaterList.jsp<h5 class="card-title">${MLcode }</h5></h2>
                    
                    <%-- <div class="col-lg-3 col-md-6">
			            <div class="card-body">
			            <h5 class="card-title">영화</h5>
			           	 	<div class="list-group reserveArea" id="mvListArea">
					            <c:forEach items="${mvList }" var="movie">
					     		<button type="button" class="list-group-item" id="${movie.mvcode }" onclick="selectMovie(this, '${movie.mvcode }','${movie.mvpos }')">${movie.mvtitle } </button> 
					            </c:forEach>
			            	</div>
			            </div>
			        </div>
 --%>                    
			            <div class="card-body">
			            <h2 class="card-title" style="text-align: center">극장목록</h2>
			           	 	<div id="thListArea">
			           	 	<ul>
					            <c:forEach items="${thList }" var="movie">
					            <li style="display: inline-block;">
			     				<button type="button" id='${movie.thcode}' class="list-group-item" onclick="selectth(this, '${movie.thcode }')">${movie.thname } </button>
			     				</li> 
					            </c:forEach>
					        </ul>
			            	</div>
			            </div>
         	
         	
         			 <%-- 
         			 
         			
			         
			        
			        <div class="col-lg-3 col-md-6">
			            <div class="card-body">
			            <h5 class="card-title">상세시간</h5>
			           	 	<div class="list-group reserveArea" id="timeListArea">
					            <p>시간 날짜를 선택해주세요</p>
					            <c:forEach items="${optionList }" var="sctime">
			     				<button type="button" class="list-group-item" onclick="selectTime(this, '${sctime.scroom }')">${sctime.scroom } </button> 
					            </c:forEach>
			            	</div>
			            </div>
			        </div>    --%>
                    
                </div>
           <div class="container px-10 px-lg-10 mt-10" id="sectionqq">
    		<h2 class="fw-bolder mb-4">예매정보</h2>
			            <div class="card-body">
			           	 	<div id="dayListArea" style="text-align: center">
			           	 	<ul>
					            <c:forEach items="${scList }" var="movie">
					             <li style="display: inline-block;">
			     				<button type="button" class="list-group-item" onclick="selectDay(this, '${movie.scdate }')">${movie.scdate } </button> 
					           	</li> 
					            </c:forEach>
					        </ul>    
			            	</div>
			            </div>
			
		 	           
     		 </div>
      
            <div>
          				 <ul>
						
					            <c:forEach items="${thList }" var="th">
					            <li style="display: inline-block;" >
								
			     				</li> 
					            </c:forEach>
					     </ul>
            </div>
            <div id ="testArea"></div>
            
            
            
            <div>
					            <c:forEach items="${mvList }" var="mv">
					            <div class="row" style="margin-bottom: 40px" >
									<div class="col-3" >
											 <img class="card-img-top" src="${mv.MVPOS}" alt="..." />
								             상영영화 : ${mv.MVTITLE}
									</div>     
						            <div class="col-8" id = '${mv.MVCODE}'>
										
									
								
			     					</div>
								</div>	
					            </c:forEach>
            </div>
            
            
        </section>
        	
        	<section class="py-5 bg-light">
        	<div class="container px-100 px-lg-100 mt-100 " top: 50% left: 50%>
        	  <div class="col-12">
				<div class="card">
					<div class="card-body p-3">
						<div class="row">
							<div class="col-4">
									 <img id= "CheckMvPoster" alt="" src="" style="max-height: 160px;">
									<h4 id="CheckMv"></h4>		            	
							</div>    
							<div class="col-6" >
									<h4>극장 <span id="CheckTh"></span></h4>
									<h4>일시 <span id="CheckScDate"></span> <span id="CheckScTime"></span></h4>
									<h4>상영관 <span id="CheckScRoom"></span></h4>		            	
							</div>  
							
							
		
							          
							
						</div>	
					</div>	
					</div>
					</div>
					</div>	
			</section>
        	
        <!--  <section class="py-5 bg-light">
            <div class="container px-100 px-lg-100 mt-100 " top: 50% left: 50%>
        	 <div class="col-lg-3 col-md-6">
			            <div class="card-body d_none" id="refi">
			            <h5 class="card-title">예매확인</h5>
			           	 	<div>
					            <p>예매하시겠습니까?</p>
					            <button onclick="disSection2()">예매</button>
			            	</div>
			            </div>
			        </div>  
           </div>
        </section> -->
        
      
        
        
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
  
    </body>
   
   
   
   <script type="text/javascript">
    var selectedMvcode = "";
    var selectedThcode = "";
    var selectedDate = "";
    var selectedTime = "";
    var selectedRoom = "";
    var loginId = '${sessionScope.loginId }'
   	let memberLoginUrl = new URL('http://localhost:8080/controller/memberLoginForm');
	
    
    $(document).ready(function(){
    var currentUrl = window.location.href;
    var MLcode = '${MLcode}';
    console.log("movieList에게 전달받은 영화코드 :  " + MLcode);
    console.log("선택한 영화코드 :  " + selectedMvcode);
    console.log("로그인 아이디 :  " + loginId);
    console.log("현재 페이지 :  " + currentUrl);
    var checkTcode = '${checkTcode}';
    
    	console.log("선택한 극장 :  " + checkTcode);
       	
    	if(checkTcode.length >= 0){
    		selectedThcode = checkTcode;	
    	$("#"+checkTcode).addClass("selectM");
    	
   		 }
	});
    
    
    
    var section1 = document.getElementById('section1');
	 var section1_ClassList = section1.classList;
	 
  	 var sectionqq = document.getElementById('sectionqq');
	 var sectionqq_ClassList = sectionqq.classList;
    	
    
    
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
    
    
    
  /*   function selectth(selBtn,thcode){
    	selectedThcode = "";
    	selectedDate = "";
    	console.log("영화선택 selectth()호출")
    	console.log("선택한 극장 :  " + thcode);
    	$("#thListArea > button").removeClass("selectM");
    	$("#timeListArea > button").removeClass("selectM");
    	$(selBtn).addClass("selectM");
    	selectedThcode = thcode;
    	
    	console.log(selBtn);
    	console.log(selBtn.text);
    	console.log(selBtn.button);
    	
    	$("#CheckTh").text( $(selBtn).text() );
		if(selectedMvcode.length == 0){
    	getReserveMovieList(thcode);
		}else{
			// 선택한 옇와를 선택한 극장에서 예매 가능한 날짜 목록 출력 함수 호출
		     	getReserveDayList(selectedMvcode, selectedThcode); 
			
		}
    } */
    function selectth(selBtn,thcode){
    	selectedDate = "";
    	console.log("영화선택 selectth()호출")
    	console.log("선택한 극장 :  " + thcode);
    	$("#thListArea> ul > li > button").removeClass("selectM");
    	$(selBtn).addClass("selectM");
    	selectedThcode = thcode;
    	$("#CheckTh").text( $(selBtn).text() );
    	location.href="${pageContext.request.contextPath }/theaterRequesttcode?tcode="+thcode;
    	
			// 선택한 옇와를 선택한 극장에서 예매 가능한 날짜 목록 출력 함수 호출
		     	/* getReserveDayList(selectedMvcode, selectedThcode); */
    	/* $.ajax({
			type: "get",
			url: "${pageContext.request.contextPath }/theaterRequesttcode",
			data : {"tcode" : thcode},
			dataType : "json",
			success:function(theaterList){
				console.log(theaterList);
				var output ="";
				if(theaterList.length <= 0){
					alert("예매가능한 극장이 없습니다.");
				}else{
					for(var thinfo of theaterList){
						output += '<button type="button" class="list-group-item" onclick="selectth(this,'+ "'"+thinfo.thcode+"'" +')">'+thinfo.thname+'</button> ';
						 output += '<button type="button" class="list-group-item" onclick="selectth('+ "'"+thinfo.thcode+"'" +',this)">'+thinfo.thname+'</button> '; 
					}
				}
				$("#testArea").html(output);
				
			}
		}); */    	
		     	
		     	
    }
    
    
    
    
   /*  function selectDay(selBtn,scdate){
    	selectedDate = "";
    	console.log("영화선택 selectDay()호출")
    	console.log("선택한 날짜 :  " + scdate);
    	$("#dayListArea > button").removeClass("selectM");
    	$("#timeListArea > button").removeClass("selectM");
    	$(selBtn).addClass("selectM");
    	selectedDate = scdate;
    	getReserveTimeList(selectedThcode, scdate);
    		

    	
		
    	$("#CheckScDate").text( scdate );
		
		//선택한 시간출력 해제
		$("#CheckScTime").text( "" );	

		//선택한 상영관출력 해제
		$("#CheckScRoom").text( "" );	
    	
    	
    } */
    
    function selectDay(selBtn,scdate){
    	selectedDate = "";
    	console.log("영화선택 selectDay()호출")
    	console.log("선택한 날짜 :  " + scdate);
    	 $("#dayListArea >ul >li>button").removeClass("selectM");
    	/* $("#timeListArea > button").removeClass("selectM"); */ 
    	$(selBtn).addClass("selectM");
    	selectedDate = scdate;
    	console.log(selectedDate);
    	console.log("selectedThcode : "+selectedThcode);
    		getReserveTimeList(selectedThcode, scdate);
    	

    	
		
    	$("#CheckScDate").text( scdate );
		
		//선택한 시간출력 해제
		//$("#CheckScTime").text( "" );	

		//선택한 상영관출력 해제
		//$("#CheckScRoom").text( "" );	
    	
    	
    }
    
    function selectTime(selBtn,selroom,seltime){
    	selectedRoom = "";
    	selectedTime = "";
    	console.log("영화선택 selecttime()호출")
    	console.log("선택한 룸 :  " + selroom);
    	console.log("선택한 시간 :  " + seltime);
    	$("#timeListArea > button").removeClass("selectM");
    	$(selBtn).addClass("selectM");
    	selectedRoom = selroom;
    	selectedTime = seltime;
    	$("#CheckScTime").text(seltime);
    	$("#CheckScRoom").text(selroom);
    	
    	
    	
    	$("#inputMvTime").val(selectedDate + " "+ seltime );
    	$("#inputRoom").val(selroom);
    	
    	if(selectedRoom != 0 && selectedTime != 0){
    		displayRe();
    	}
	
    	
    	
    }
    
    function getReserveTheaterList(mvcode){
    	console.log("예매 가능한 극장 목록 조회")
		$.ajax({
			type: "get",
			url: "${pageContext.request.contextPath }/getReTheaterList",
			data : {"mvcode" : mvcode},
			dataType : "json",
			success:function(theaterList){
				console.log(theaterList);
				var output ="";
				if(theaterList.length <= 0){
					alert("예매가능한 극장이 없습니다.");
				}else{
					for(var thinfo of theaterList){
						output += '<button type="button" class="list-group-item" onclick="selectth(this,'+ "'"+thinfo.thcode+"'" +')">'+thinfo.thname+'</button> ';
						/* output += '<button type="button" class="list-group-item" onclick="selectth('+ "'"+thinfo.thcode+"'" +',this)">'+thinfo.thname+'</button> '; */
					}
				}
				$("#thListArea").html(output);
				
			}
		});
    }
    
    function getReserveMovieList(thcode){
    	console.log("예매 가능한 영화 목록 조회")
		$.ajax({
			type: "get",
			url: "${pageContext.request.contextPath }/getReserveMovieList",
			data : {"thcode" : thcode},
			dataType : "json",
			success:function(mvList){
				console.log(mvList);
				var output ="";
				if(mvList.length <= 0){
					alert("예매가능한 영화가 없습니다.");
				}else{
					for(var mvinfo of mvList){
						output += '<button type="button" class="list-group-item" onclick="selectMovie(this,'+ "'"+mvinfo.mvcode+"'" +')">'+mvinfo.mvtitle+'</button> ';
						/* output += '<button type="button" class="list-group-item" onclick="selectth('+ "'"+thinfo.thcode+"'" +',this)">'+thinfo.thname+'</button> '; */
				
					}
				}
				$("#mvListArea").html(output);
			}
		});
    }
    
    
    
    
    function getReserveDayList(mvcode, thcode){
    	console.log("예매 가능한 시간 목록 조회")
		$.ajax({
			type: "get",
			url: "${pageContext.request.contextPath }/getReDayList",
			data : {"mvcode" : mvcode , "thcode" : thcode},
			dataType : "json",
			success:function(dayList){
				console.log(dayList);
				var output ="";
				if(dayList.length <= 0){
					alert("예매가능한 시간이 없습니다.");
				}else{
					for(var dayinfo of dayList){
						output += '<button type="button" class="list-group-item" onclick="selectDay(this,'+ "'"+dayinfo.scdate+"'" +')">'+dayinfo.scdate+'</button> ';
						/* output += '<button type="button" class="list-group-item" onclick="selectth('+ "'"+thinfo.thcode+"'" +',this)">'+thinfo.thname+'</button> '; */
					}
				}
				$("#dayListArea").html(output);
			}
		});
    }
    
    
    
    function getReserveTimeList(thcode, scdate){
    	console.log("예매 가능한 상세시간 목록 조회")
		$.ajax({
			type: "get",
			url: "${pageContext.request.contextPath }/getTimeList2",
			data : {"thcode" : thcode, "scdate" : scdate},
			dataType : "json",
			success:function(timeList){
				console.log(timeList);
				var output ="";
				var reid ="";
				if(timeList.length <= 0){
					alert("예매가능한 시간이 없습니다.");
				}else{
					var nowScMvcode = "";
					var nowScRoom = "";
					var reset = "";
					 for(var timeinfo of timeList){
			    	  $("#"+timeinfo.scmvcode).html(reset); 
					} 
					for(var timeinfo of timeList){
						/* 틀린부분시작 */
									/* output += '<h1>'+timeinfo.scroom+'</h1>'; */
			 						/* output += timeinfo.scdate; */ 
/* 				 							if(nowScRoom != timeinfo.scroom) */
				 						output ="";
				 						if(nowScRoom != timeinfo.scroom){
				 							nowScRoom = timeinfo.scroom;
												console.log(timeinfo.scroom);
												output += '<h1>'+timeinfo.scroom+'</h1>';
						 						/* output += '<button type="button"  onclick="selectTime(this,'+ "'"+timeinfo.scroom+"'"+","+ "'"+timeinfo.scdate+"'" +')">'+timeinfo.scdate+'</button> '; */
										}else if(nowScRoom == timeinfo.scroom&& nowScMvcode !=timeinfo.scmvcode){
											output += '<h1>'+timeinfo.scroom+'</h1>';
											console.log("상영관일치 영화 불일치");
										}
				 						
									console.log("일치합니다");
									console.log(timeinfo.scmvcode);
									
						 				output += '<button type="button"  onclick="selectTime(this,'+ "'"+timeinfo.scroom+"'"+","+ "'"+timeinfo.scdate+"'" +')">'+timeinfo.scdate+'</button> ';
									nowScMvcode = timeinfo.scmvcode;
			 						/* output += timeinfo.scdate; */ 
									 
						/* 틀린부분끝 */			
									
								  /* $("#"+timeinfo.scmvcode).append("<button>"+timeinfo.scmvcode+"</button>"); */
									console.log("#"+timeinfo.scmvcode)
								   $("#"+timeinfo.scmvcode).append(output);
									/*  $("#"+timeinfo.scmvcode).html(output); */ 
					}
				}
/* 					 $("#testArea").html(output); */	 
			}
		});
    }
    
    
    
    
    
    </script>
    
    
    
</html>
