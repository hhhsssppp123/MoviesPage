<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    
    
 <!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Dashboard</title>
		<!-- 원본 스타일 -->	
	    <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath }/resources/assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="${pageContext.request.contextPath }/resources/css/styles.css" rel="stylesheet" />
		<!-- 원본 스타일 끝 -->
	
	
	
	
    <!-- Custom fonts for this template-->
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.1/css/jquery.dataTables.css">
    
    <link href="${pageContext.request.contextPath }/resources/admin/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="${pageContext.request.contextPath }/resources/admin/css/sb-admin-2.css" rel="stylesheet">


		<style type="text/css">
	     .d_none{
	            display: none;
	        }
	    </style>
</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

                <!-- sidebar -->
          <%@ include file="/WEB-INF/views/includes/sidebar.jsp" %> 

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <!-- topbar -->
				 <%@ include file="/WEB-INF/views/includes/top.jsp" %> 
				 
                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <h1 class="h3 mb-2 text-gray-800">Tables</h1>
                    <p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below.
                        For more information about DataTables, please visit the <a target="_blank"
                            href="https://datatables.net">official DataTables documentation</a>.</p>

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">DataTables Example</h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered display" id="table_id" width="100%" cellspacing="0">
								    <thead>
								        <tr>
								            <th>아이디</th>
								            <th>비밀번호</th>
								            <th>이름</th>
								            <th>생년월일</th>
								            <th>주소</th>
								            <th>이메일</th>
								            <th>예매횟수</th>
								            <th>상태</th>
								        </tr>
								    </thead>
								    <tbody>
								        <c:forEach items="${memberList }" var="mem">
								        <tr>
								           
								           <td><button class="btn btn-primary w-100"  onclick="memberDetailView('${mem.MID}')">${mem.MID}</button></td>
								            <td>${mem.MPW }</td>
								            <td>${mem.MNAME }</td>
								            <td>${mem.MBIRTH}</td>
								            <td>${mem.MADDR}</td>
								            <td>${mem.MEMAIL}</td>
								            
								            <td>
								            <c:choose>
								            <c:when test="${mem.RECOUNT == null}">
								            	0
								            </c:when>
								            <c:otherwise>
								            	${mem.RECOUNT}
								            </c:otherwise>
								            </c:choose>
								            </td>
								            
								            
								            <td>
								            <c:choose>
								            <c:when test="${mem.MSTATE == 0}">
								            	<button class="btn-outline-success" onclick="disc(this, '${mem.MID }', '${mem.MSTATE }')">사용중</button>
								            </c:when>
								            <c:otherwise>
								            	<button class="btn-danger" onclick="disc(this, '${mem.MID }', '${mem.MSTATE }')">이용불가</button>
								            </c:otherwise>
								            </c:choose>
								            </td>
								        </tr>
								          </c:forEach>
								    </tbody>
                                  
                                  </table>
                            </div>
                        </div>
                    </div>

                </div>
                <!-- /.container-fluid -->

           

            </div>
            <!-- End of Main Content -->

            <!-- Footer -->
            <footer class="sticky-footer bg-white">
                <div class="container my-auto">
                    <div class="copyright text-center my-auto">
                        <span>Copyright &copy; Your Website 2021</span>
                    </div>
                </div>
            </footer>
            <!-- End of Footer -->

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="login.html">Logout</a>
                </div>
            </div>
        </div>
    </div>
	
	
	<!-- 원본 스크립트 파일 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
     <script src="${pageContext.request.contextPath }/resources/js/scripts.js"></script>
    <!-- Bootstrap core JavaScript-->
    <script src="${pageContext.request.contextPath }/resources/admin/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath }/resources/admin/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="${pageContext.request.contextPath }/resources/admin/vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="${pageContext.request.contextPath }/resources/admin/js/sb-admin-2.min.js"></script>

    <!-- Page level plugins -->
    <script src="${pageContext.request.contextPath }/resources/admin/vendor/chart.js/Chart.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="${pageContext.request.contextPath }/resources/admin/js/demo/chart-area-demo.js"></script>
    <script src="${pageContext.request.contextPath }/resources/admin/js/demo/chart-pie-demo.js"></script>
    
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
	
	
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.js"></script>
</body>
	<script type="text/javascript">
	$(document).ready( function () {
	    $('#table_id').DataTable();
	} );
	
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
	    
	    function disc(selBtn, mid, mstate){
	    	console.log("selBtn ="+selBtn);
	    	console.log("mid ="+mid);
	    	console.log("mstate ="+mstate);
	    	if(mstate == 0){
		    	$(selBtn).removeClass("btn-outline-success");
		    	$(selBtn).addClass("btn-danger");
		    	$(selBtn).html("이용불가");
		    	}else{
		    		$(selBtn).removeClass("btn-danger");
			    	$(selBtn).addClass("btn-outline-success");
			    	$(selBtn).html("사용중");
			    	/* mstate = 0; */
		    	}
	    	/* mstate = 1; */
	    	$.ajax({
				type: "get",
				url: "${pageContext.request.contextPath }/changeMstate",
				data : {"mid" : mid, "mstate": mstate},
				dataType : "json",
				success:function(change){
					console.log("결과 1이면 성공 0 이면 실패 " + change);
					console.log("성공!")
			    	
					
				}
			});
	    	
			
	    	
	    	
	    }
	    
	    function memberDetailView(mid){
	    	console.log("조회할 회원아이디 : " + mid)
			var url = '${pageContext.request.contextPath }/adminDatailMemberView?mid='+mid
			window.open(url,'detailMemberView',"width=750, height=450, top=100, left=500");
		}
	    
	    function onc(selBtn, mid){
	    	console.log("selBtn ="+selBtn)
	    	console.log("mid ="+mid)
	    	$(selBtn).removeClass("btn-danger");
	    	$(selBtn).addClass("btn-outline-success");
	    	$(selBtn).html("사용중");	
	    }
	    
	    
	    
	</script>
	
</html>