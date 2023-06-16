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
        <section class="py-5 bg-light" >
            <div class="container px-10 px-lg-10 mt-10" id="section1">
                <h2 class="fw-bolder mb-4">Ticket.jsp<h5 class="card-title">${MLcode }</h5></h2>
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                    
                    <div class="col-lg-3 col-md-6">
			            <div class="card-body">
			            <h5 class="card-title">영화</h5>
			           	 	<div class="list-group reserveArea" id="mvListArea">
					            <c:forEach items="${mvList }" var="movie">
					     					<button type="button" class="list-group-item" id="${movie.mvcode }" onclick="selectMovie(this, '${movie.mvcode }','${movie.mvpos }')">${movie.mvtitle } </button> 
					     					<%--  <c:if test="${MLcode == movie.mvcode}">
					     							<button id="MLc" type="button" class="list-group-item" onclick="selectMovie(this, '${movie.mvcode }','${movie.mvpos }')">${movie.mvtitle } </button>
					     					</c:if> 
						           	<c:choose>
							            <c:when test="${MLcode ==null}"> 
					     					<button type="button" class="list-group-item" onclick="selectMovie(this, '${movie.mvcode }','${movie.mvpos }')">${movie.mvtitle } </button> 
							            </c:when>
							            <c:otherwise>
							            	<c:choose>
							            		<c:when test="${MLcode == movie.mvcode}">
							            			<button id="MLc" type="button" class="list-group-item" onclick="selectMovie(this, '${movie.mvcode }','${movie.mvpos }')">${movie.mvtitle } </button>
							            		</c:when>
							            	</c:choose>
							            			<button type="button" class="list-group-item" onclick="selectMovie(this, '${movie.mvcode }','${movie.mvpos }')">${movie.mvtitle } </button>
							            
							            
							            
							         	</c:otherwise>
						         	</c:choose>  --%>
					            </c:forEach>
			            	</div>
			            </div>
			        </div>
                    
                    <div class="col-lg-3 col-md-6">
			            <div class="card-body">
			            <h5 class="card-title">극장</h5>
			           	 	<div class="list-group reserveArea" id="thListArea">
					            <c:forEach items="${thList }" var="movie">
			     				<button type="button" class="list-group-item" onclick="selectth(this, '${movie.thcode }')">${movie.thname } </button> 
					            </c:forEach>
			            	</div>
			            </div>
			        </div>
         	
         	
         			 <div class="col-lg-3 col-md-6">
			            <div class="card-body">
			            <h5 class="card-title">시간</h5>
			           	 	<div class="list-group reserveArea" id="dayListArea">
					            <c:forEach items="${scList }" var="movie">
			     				<button type="button" class="list-group-item" onclick="selectDay(this, '${movie.scdate }')">${movie.scdate } </button> 
					            </c:forEach>
			            	</div>
			            </div>
			      	  </div>
         			
         			
			         
			        
			        <div class="col-lg-3 col-md-6">
			            <div class="card-body">
			            <h5 class="card-title">상세시간</h5>
			           	 	<div class="list-group reserveArea" id="timeListArea">
					            <p>시간 날짜를 선택해주세요</p>
					            
					            <%-- <c:forEach items="${optionList }" var="sctime">
			     				<button type="button" class="list-group-item" onclick="selectTime(this, '${sctime.scroom }')">${sctime.scroom } </button> 
					            </c:forEach> --%>
			            	</div>
			            </div>
			        </div>   
                    
                    
                </div>
            </div>
                  <div class="container px-10 px-lg-10 mt-10 d_none" id="sectionqq">
                <h2 class="fw-bolder mb-4">예매정보</h2>
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center"  >
                     <div class="col-lg-6 col-md-6">
                   		
		  
		   <div class="col-12">
		    <label for="inputEmail4" class="form-label">상영시간</label>
		    <input type="text" readonly="readonly" class="form-control" id="inputMvTime" value="">
		  </div>
		  <div class="col-12">
		    <label for="inputAddress" class="form-label">상영관</label>
		    <input type="text" readonly="readonly" class="form-control" id="inputRoom" value="">
		  </div>
		  <div class="col-md-6">
		    <label for="inputCity" class="form-label">예약자 아이디</label>
		    <input type="text"  readonly="readonly" class="form-control" id="inputIdcode" value="${sessionScope.loginId}">
		  </div>
		  <div class="col-md-6">
		    <label for="inputCity" class="form-label">예매코드</label>
		    <input type="text"  readonly="readonly" class="form-control" id="inputRecode" value="">
		  </div>
		  
		  <div class="col-md-6">
		    <label for="inputZip" class="form-label">예매인원</label>
							           <input type="number" id="scount">
		  </div>
                   		
                	</div>
                    
		 	            <div class="card-body">
			            <h5 class="card-title">예매확인</h5>
			           	 	<div>
					            <p>예매하시겠습니까?</p>
					            <label for="inputZip" class="form-label">예매인원</label>
					            <button onclick="reservation2()">예매1</button>
			            	</div>
			            </div> 
                	
            	</div>
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
							
							
		
							          
							<div class="col-2 d_none" id="refi">
							<h5 class="card-title">예매확인</h5>
						            <p>예매하시겠습니까?</p>
						            <button onclick="disSection2()">예매</button>
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
    </body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
  
   
   
   
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
    console.log("현재 페이지 :  " + typeof currentUrl);
   	
    
    if(MLcode.length >= null){
    	console.log("선택한 영화코드 :  " + selectedMvcode);
       	$("#"+MLcode).click();
       	$("#"+MLcode).focus();
    }
	});
    

    
    var section1 = document.getElementById('section1');
	 var section1_ClassList = section1.classList;
	 
  	 var sectionqq = document.getElementById('sectionqq');
	 var sectionqq_ClassList = sectionqq.classList;
    	
    
   function displayRe(){
	console.log("displayRe 호출");
 	console.log("선택한 영화코드 :  " + selectedMvcode);
	console.log("선택한 극장 :  " + selectedThcode);
	console.log("선택한 날짜 :  " + selectedDate);
	console.log("선택한 룸 :  " + selectedRoom);
   	console.log("선택한 시간 :  " + selectedTime);
    var reObj = document.getElementById('refi');
    var reObj_ClassList = reObj.classList;
    
    if(selectedMvcode != 0&&selectedThcode != 0&&selectedDate != 0&&selectedTime != 0&&selectedRoom != 0){
    	if(reObj_ClassList.contains('d_none')){
        	reObj_ClassList.remove('d_none'); 
        }	
    }else{
    	reObj_ClassList.add('d_none');
    }
   }
   
   
   function  reservation(){
		var scount_Obj = document.getElementById('scount');
		var scount = scount_Obj.value;
		console.log("진짜 예매 호출");
		 $.ajax({
				type: "get",
				url: "${pageContext.request.contextPath }/reservationreal",
				data : {"mvcode" : selectedMvcode,
					"thcode" : selectedThcode,
					"sdate" : selectedDate,
					"stime" : selectedTime,
					"scount" : scount,
					"sroom" : selectedRoom},
					async:false,
					success:function(insertReuslt){
						if(insertReuslt<0){
							console.log("예매성공");
						}
					}
					
		    });
	   };
	   
	   
	   function  reservation2(){
			var scount_Obj = document.getElementById('scount');
			var scount = scount_Obj.value;
			 $.ajax({
					type: "get",
					url: "${pageContext.request.contextPath }/reservation",
					data : {"mvcode" : selectedMvcode,
						"thcode" : selectedThcode,
						"sdate" : selectedDate,
						"stime" : selectedTime,
						"scount" : scount,
						"sroom" : selectedRoom},
						async:false,
						success:function(popUpUrl){
							console.log("popUpUrl" + popUpUrl)
							window.open(popUpUrl,"payPopUp","width=400,height=800,top=10,left=100");
				}
			    });
		    
		   };
	   
	   
	   
	   function pay_Result(payResult){
			console.log(payResult);	   
		   switch(payResult){
		   case 'Approval':
			   reservation();
			   alert("예매완료 되었습니다.");
			   location.href="${pageContext.request.contextPath }/MainPage";
			   alert("예매완료 되었습니다.");
			   break;
		   case 'Cancel':
			   alert("예매처리중 결제가 취소되었습니다.");
			   break;
		   
		   case 'Fail':
			   alert("예매처리중 결제가 실패했습니다.");
			   break;
		   
		   }
		   
		   
	   }
	    
    
     function  disSection2(){
	console.log("reservation 호출");
	if(loginId.length <= 0){
		alert("로그인후 이용 가능합니다.!");
				console.log("로그인페이지팝업띄우기");
				window.open(memberLoginUrl,"payPopUp","width=400,height=800,top=10,left=100");
				
	}else{
		 sectionqq_ClassList.remove('d_none'); 
			section1_ClassList.add('d_none'); 
		    $.ajax({
				type: "get",
				url: "${pageContext.request.contextPath }/reservationPage",
				data : {"mvcode" : selectedMvcode,
					"thcode" : selectedThcode,
					"sdate" : selectedDate,
					"stime" : selectedTime,
					"sroom" : selectedRoom},
					async:false,
					success:function(recode){
					if(recode.length <= 0){
						console.log("recode" + recode)
						alert("필수정보를 입력해주세요.");
					}else{
						$("#inputRecode").val(recode);
					}
			}
		    });
	}
	
   	
   	
  	/* location.href="${pageContext.request.contextPath }/reservationPage?mvcode="+selectedMvcode+"&thcode="+selectedThcode+"&sdate="+selectedDate+"&stime="+selectedTime+"&sroom="+selectedRoom; */ 
    
	   
   }
    
    
    
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
    
    
    function selectMovie(selBtn,mvcode,mvpos){
    	selectedMvcode="";
    	selectedDate = "";
    	console.log("영화선택 selectMovie()호출")
    	console.log("선택한 영화코드 :  " + mvcode);
    	
    	$("#mvListArea > button").removeClass("selectM");
    	$("#timeListArea > button").removeClass("selectM");
    	$(selBtn).addClass("selectM");
    	selectedMvcode = mvcode;
    	
    	/* $("#CheckMv").val(selBtn.text); */
    	$("#CheckMvPoster").attr("src", mvpos);
    	
    	var selMvTitle = $(selBtn).text();
    	console.log(selMvTitle);
		$("#CheckMv").text( selMvTitle );
		
    	if(selectedThcode == 0){
    	getReserveTheaterList(mvcode);
    	}else{
			// 선택한 옇와를 선택한 극장에서 예매 가능한 날짜 목록 출력 함수 호출
		     	getReserveDayList(selectedMvcode, selectedThcode); 
    	}
    	
    	
    }
    
    function selectth(selBtn,thcode){
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
		
		
    	
    }
    function selectDay(selBtn,scdate){
    	selectedDate = "";
    	console.log("영화선택 selectDay()호출")
    	console.log("선택한 날짜 :  " + scdate);
    	$("#dayListArea > button").removeClass("selectM");
    	$("#timeListArea > button").removeClass("selectM");
    	$(selBtn).addClass("selectM");
    	selectedDate = scdate;
    	if(selectedMvcode != 0&&selectedThcode != 0){
    		getReserveTimeList(selectedMvcode, selectedThcode, scdate);
    		
    	}

    	
		
    	$("#CheckScDate").text( scdate );
		
		//선택한 시간출력 해제
		$("#CheckScTime").text( "" );	

		//선택한 상영관출력 해제
		$("#CheckScRoom").text( "" );	
    	
    	
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
    
    
    
    function getReserveTimeList(mvcode, thcode, scdate){
    	console.log("예매 가능한 상세시간 목록 조회")
		$.ajax({
			type: "get",
			url: "${pageContext.request.contextPath }/getTimeList",
			data : {"mvcode" : mvcode , "thcode" : thcode, "scdate" : scdate},
			dataType : "json",
			success:function(timeList){
				console.log(timeList);
				var output ="";
				if(timeList.length <= 0){
					alert("예매가능한 시간이 없습니다.");
				}else{
					
					var nowScRoom = "";
					for(var timeinfo of timeList){
						var i = 0;
						if(nowScRoom != timeinfo.scroom){
							console.log(timeinfo.scroom);
							nowScRoom = timeinfo.scroom;
							output += '<table><tr>'+ timeinfo.scroom+'</tr>';
 							output += '<button type="button" class="list-group-item" onclick="selectTime(this,'+ "'"+timeinfo.scroom+"'"+","+ "'"+timeinfo.scdate+"'" +')">'+timeinfo.scdate+'</button> '; 
						}else{
/*  						output += '<button type="button" class="list-group-item" onclick="selectTime(this,'+ "'"+timeinfo.scdate+"'" +')">'+timeinfo.scdate+'</button> '; */ 
 						output += '<button type="button" class="list-group-item" onclick="selectTime(this,'+ "'"+timeinfo.scroom+"'"+"," + "'"+timeinfo.scdate+"'" +')">'+timeinfo.scdate+'</button> '; 
						}
						
						
						output += '</table>';
						i++;
							
							
						
						/* output += '<button type="button" class="list-group-item" onclick="selectth('+ "'"+thinfo.thcode+"'" +',this)">'+thinfo.thname+'</button> '; */
					}
				}
				$("#timeListArea").html(output);
			}
		});
    }
    
    
    
    
    </script>
    
    
</html>
