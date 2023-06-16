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
        <title>MainPage</title>
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
                       
                         <h1 class="display-5 fw-bolder" align="center" >로그인</h1>
                       		 <p class="lead">
                                    <form class="user" action="${pageContext.request.contextPath }/memberLogin"
                                          method="post" >
                                        <div class="form-group">
                                            <input type="text" name="inputMid" class="form-control form-control-user"
                                                placeholder="아이디">
                                        </div>
                                        <div class="form-group">
                                            <input type="password" name="inputMpw" class="form-control form-control-user"
                                                placeholder="비밀번호">
                                        </div>
                                        <button type="submit" class="btn btn-primary btn-user btn-block">
                                            로그인
                                        </button>
                                    </form>
                        
                        
                        </p>
                        <div class="d-flex">
                            <input class="form-control text-center me-3" id="inputQuantity" type="num" value="1" style="max-width: 3rem" />
                            <button class="btn btn-outline-dark flex-shrink-0" type="button">
                                <i class="bi-cart-fill me-1"></i>
                                Add to cart
                            </button>
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
         
       
    }
   	
    function joinIdCheck(idVal){
		console.log("입력한 아이디 : " + idVal);
		if(idVal.length == 0){
			/* $("#idCheckMsg").text('아이디를 입력 해주세요!').css("color","red"); */
	        document.getElementById('idCheckMsg').style.color = 'red';
	        document.getElementById('idCheckMsg').innerText = '아이디를 입력 해주세요!';
		} else {
				$.ajax( { 
				type : "get",
				url : "${pageContext.request.contextPath }/memberIdCheck",
				data : { "inputId" : idVal },
				success : function(checkResult){
					console.log("checkResult : " + checkResult);
					if(checkResult == 'OK'){
						/* 아이디 사용 가능 */
						$("#idCheckMsg").text('사용가능한 아이디!').css("color","green"); 
						
					} else {
						/* 중복된 아이디 */
						$("#idCheckMsg").text('중복된 아이디!').css("color","red");
					}
				}
			} );
			
		}
	}
    
    
    function joinFormCheck(joinForm){
		/* 아이디, 비밀번호, 이름 */
		console.log("회원가입 폼 체크");
		
		/* 아이디 입력값 확인 */
	 	var inputmid = joinForm.mid;
		console.log(inputmid.value);
		if(inputmid.value.length == 0){
			alert('아이디를 입력해 주세요!');
			inputmid.focus();
			return false;
		}
		/* 비밀번호 입력값 확인 */
	 	var inputmpw = joinForm.mpw;
		if(inputmpw.value.length == 0){
			alert('비밀번호를 입력해 주세요!');
			inputmpw.focus();
			return false;
		}			
		/* 이름 입력값 확인 */
	 	var inputmname = joinForm.mname;
		if(inputmname.value.length == 0){
			alert('이름을 입력해 주세요!');
			inputmname.focus();
			return false;
		}			

	}
    
    function selectDomain(selVal){
		document.getElementById('eDomain').value = selVal;
	}
    
    </script>
    
    
</html>
