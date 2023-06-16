<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="ko-KR">    
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
  
<style type="text/css">
button.check {
	background-color: blue;
	text-align: center;
	color: white;
}

.d-none {
	display: none;
}
</style>
  
         
</head>


<body>
		
		<!-- 
		<div class="joinType">
		<button type="button" class="selectType check" onclick="selectTypeBtn(this)" value="p">개인회원</button>
		<button type="button" class="selectType" onclick="selectTypeBtn(this)" value="c">기업회원</button>
		</div>
		<div class="joinContext"> -->
		
		<!-- 일반회원 양식 -->
		<%-- <div class="person">
			<form action="${pageContext.request.contextPath }/joinMember" method="post">
				<div>
					<label>아이디</label><br>
					<input type="text" id="mid" name="mid">
					<button type="button" onclick="checkSameId()">중복체크</button>
					<br>
					<span id="idCheckResult"></span>
				</div>
				<div>
					<label>비밀번호</label><br>
					<input type="text" name="mpw" maxlength="16">
				</div>
				<div>
					<label>이름</label><br>
					<input type="text" name="mname">
				</div>
				<div class="input-group">
									<input type="text" id="postcode" placeholder="우편번호" disabled="disabled" class="form-control">
									<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기" class="btn btn-secondary">
								</div>
								<div class="input-group mb-3">
									<input type="text" id="address" placeholder="주소" disabled="disabled" class="form-control">
									<input type="text" id="detailAddress" placeholder="상세주소" disabled="disabled" onchange="createAddr()" class="form-control">
									<input type="text" id="extraAddress" placeholder="참고항목" disabled="disabled" class="form-control">
									<input type="hidden" id="maddr" name="maddr">
								</div>
				<div>
					<label>생년월일</label><br>
					<input type="date" name="mbirth">
				</div>
				<div>
					<label>이메일</label><br>
					<input type="hidden" id="memail" name="memail">
					<input type="text" id="emailId" onchange="createEmail()">
					@
					<input type="text" id="domain" onchange="createEmail()">
					<select onchange="domainSelect(this)">
						<option value="">직접입력</option>
						<option value="daum.com">다음</option>
						<option value="naver.com">네이버</option>
						<option value="gmail.com">구글</option>
						<option value="kakao.com">카카오</option>
					</select>
					<button type="button" id="mailCheckBtn">본인인증</button>
					<br>
					<input type="text" id="inputCode" disabled="disabled" maxlength="6" placeholder="인증번호 6자리 입력">
					<input type="hidden" id="emailCode" disabled="disabled" maxlength="6">
					<br>
					<span id="mailCheckResult"></span>
				</div>
				<button type="submit">회원가입</button>
			</form>
		</div> --%>

		<!-- 기업회원 양식 -->
		
		
		
		<div class="company">회원가입 양식2
		<div class="row g-3">
			<div class="col-md-6">
	          <label for="inputWP" class="form-label">회사명</label>
	          <input type="text" class="form-control" id="inputWP">
	          <input type="button" onclick="findWP()" value="회사검색">
	        </div>
	        <div class="col-md-6">
	          <label for="inputLN" class="form-label">사업자 등록번호</label>
	          <input type="password" class="form-control" id="inputLN">
	        </div>
	        <div class="col-12">
	          <label for="inputCN" class="form-label">대표자명</label>
	          <input type="text" class="form-control" id="inputCN">
	        </div>
        </div>
        
 		<form class="row g-3">
 		  <div class="input-group">
									<input type="text" id="postcode" placeholder="우편번호" disabled="disabled" class="form-control">
									<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기" class="btn btn-secondary">
								</div>
								<div class="input-group mb-3">
									<input type="text" id="ciaddr" placeholder="주소" disabled="disabled" class="form-control">
									<input type="text" id="cidetailAddress" placeholder="상세주소" disabled="disabled" onchange="createAddr()" class="form-control">
									<input type="text" id="ciextraAddress" placeholder="참고항목" disabled="disabled" class="form-control">
									<input type="hidden" id="ciaddr" name="ciaddr">
								</div>

        <div class="col-md-6">
          <label class="form-label">기업형태</label>
          <select class="form-select" aria-label="Default select example">
            <option selected>Open this select menu</option>
            <option value="1">대기업</option>
            <option value="2">중소기업</option>
            <option value="3">...</option>
          </select>
        </div>
        <div class="col-md-6">
          <label for="inputId" class="form-label">아이디</label>
          <input type="text" class="form-control" id="inputId" placeholder="아이디를 입력해주세요.">
        </div>
        <div class="col-md-6">
          <label for="inputPw" class="form-label">비밀번호</label>
          <input type="post" class="form-control" id="inputPw">
        </div>
        <div class="col-md-6">
            <label for="inputName" class="form-label">이름</label>
            <input type="text" class="form-control" id="inputName">
        </div>
        <div class="col-md-6">
            <label for="inputEmail" class="form-label">이메일</label>
            <input type="text" class="form-control" id="inputEmail">
        </div>

        <div class="col-md-6">
            <label for="inputPN" class="form-label">휴대폰 번호</label>
            <input type="text" class="form-control" id="inputPN">
        </div>
          
    
      </form>
      <!-- 지도테스트 -->
		<p style="margin-top:-12px">
		    <em class="link">
		        <a href="javascript:void(0);" onclick="window.open('http://fiy.daum.net/fiy/map/CsGeneral.daum', '_blank', 'width=981, height=650')">
		            혹시 주소 결과가 잘못 나오는 경우에는 여기에 제보해주세요.
		        </a>
		    </em>
		</p>
		<div id="map" style="width:350px;height:350px;"></div>
		<!-- 지도테스트끝 -->

	</div>
	
	</div>
		<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
      
      	<script src="//code.jquery.com/jquery-3.3.1.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    	
<!--     	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=f77dd6f20b5b24ff05e65852a37ccc78&libraries=services"></script> -->
    	
<script>


	/* 	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		    mapOption = {
		        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
		        level: 3 // 지도의 확대 레벨
		    };  
		
		// 지도를 생성합니다    
		var map = new kakao.maps.Map(mapContainer, mapOption); 
		
		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new kakao.maps.services.Geocoder();
 */
	
	


// 주소로 좌표를 검색합니다
//geocoder.addressSearch('제주특별자치도 제주시 첨단로 242', function(result, status) {
	 
</script>
    	
    	
    <!-- 
      <script>
          new daum.Postcode({
              oncomplete: function(data) {
                  // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분입니다.
                  // 예제를 참고하여 다양한 활용법을 확인해 보세요.
              }
          }).open();
      </script>
      -->
      <script type="text/javascript">
      
      <!-- adress -->
  		// 전체 주소 설정
  		function createAddr() {
  			var addr = $('#address').val();
  			var detailAddr = $('#detailAddress').val();
  			var totalAddr = addr + " " + detailAddr;
  			$('#maddr').attr('value', totalAddr);
  		}

  		// 우편번호 검색 
  		function execDaumPostcode() {
  			new daum.Postcode(
  					{
  						oncomplete : function(data) {
  							// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

  							// 각 주소의 노출 규칙에 따라 주소를 조합한다.
  							// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
  							var addr = ''; // 주소 변수
  							var extraAddr = ''; // 참고항목 변수

  							//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
  							if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
  								addr = data.roadAddress;
  							} else { // 사용자가 지번 주소를 선택했을 경우(J)
  								addr = data.jibunAddress;
  							}

  							// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
  							if (data.userSelectedType === 'R') {
  								// 법정동명이 있을 경우 추가한다. (법정리는 제외)
  								// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
  								if (data.bname !== ''
  										&& /[동|로|가]$/g.test(data.bname)) {
  									extraAddr += data.bname;
  								}
  								// 건물명이 있고, 공동주택일 경우 추가한다.
  								if (data.buildingName !== ''
  										&& data.apartment === 'Y') {
  									extraAddr += (extraAddr !== '' ? ', '
  											+ data.buildingName
  											: data.buildingName);
  								}
  								// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
  								if (extraAddr !== '') {
  									extraAddr = ' (' + extraAddr + ')';
  								}
  								// 조합된 참고항목을 해당 필드에 넣는다.
  								document.getElementById("extraAddress").value = extraAddr;

  							} else {
  								document.getElementById("extraAddress").value = '';
  							}

  							// 우편번호와 주소 정보를 해당 필드에 넣는다.
  							document.getElementById('postcode').value = data.zonecode;
  							document.getElementById("address").value = addr;
  							document.getElementById("inputCiaddr").value = addr;
  							
  							// 커서를 상세주소 필드로 이동한다.
  							$('#detailAddress').attr('disabled',false);
  							document.getElementById("detailAddress").focus();
  						}
  					}).open();
  		}
  	</script>
      
      
      
      <script>
        //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
       
        
       
        
        //sample4_roadAddress.oninput = function() {
        window.call = function() {
            console.log('call함수 호출')
        	var loval1 = sample4_roadAddress.value;
            console.log(loval1);
            geocoder.addressSearch(loval1, function(result, status) {
        		console.log('addressSearch 호출()');
        	    // 정상적으로 검색이 완료됐으면 
        	    console.log(loval1);
        	     if (status === kakao.maps.services.Status.OK) {

        	        var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

        	        // 결과값으로 받은 위치를 마커로 표시합니다
        	        var marker = new kakao.maps.Marker({
        	            map: map,
        	            position: coords
        	        });

        	        // 인포윈도우로 장소에 대한 설명을 표시합니다
        	        var infowindow = new kakao.maps.InfoWindow({
        	            content: '<div style="width:150px;text-align:center;padding:6px 0;">우리회사</div>'
        	        });
        	        infowindow.open(map, marker);

        	        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        	        map.setCenter(coords);
        	    } 
        	    console.log('정상 검색 x');
        	}); 
            
         };
        
        
        
    </script> 
        
	<script type="text/javascript">       
       
	
	
	function findWP(){
        	console.log("find_WP 호출")
    		window.open("${pageContext.request.contextPath }/find_WP","WPPopUp","width=600,height=800,top=10,left=100");
        }
	
	function TopenV(){
		/* document.getElementById('inputWP').value
		document.getElementById('inputLN').value 
		document.getElementById('inputCN').value */ 		
		console.log("TopenV 호출 ");
		console.log(document.getElementById('inputWP').value);
		console.log(document.getElementById('inputLN').value);
		console.log(document.getElementById('inputCN').value);
		
		
	}
	
   </script>   
   <script type="text/javascript">
		function selectTypeBtn(btnObj) {
			$('.selectType').removeClass('check');
			$(btnObj).addClass('check');
			var btnVal = btnObj.value;
			if (btnVal == 'p') {
				$('.person').removeClass("d-none");
				$('.company').addClass('d-none');
			} else {
				$('.person').addClass('d-none');
				$('.company').removeClass('d-none');
			}
		}

		
		function checkSameId() {
			console.log('중복체크 버튼 클릭');
			var sampleId = "admin";
			var inputId = $('#mid').val();
			if (inputId == sampleId) {
				$('#idCheckResult').text('중복된 아이디');
				$('#idCheckResult').css('color', 'red');
			} else if (inputId.length < 4) {
				$('#idCheckResult').text('');
			} else {
				$('#idCheckResult').text('사용가능한 아이디');
				$('#idCheckResult').css('color', 'green');
			}
		}

		function domainSelect(obj) {
			document.getElementById('domain').value = obj.value;
			createEmail();
		}

		function createEmail() {
			var totalEmail = $('#emailId').val() + '@' + $('#domain').val();
			$('#memail').attr('value', totalEmail);
		}
	</script>


	<!-- email -->
	<script type="text/javascript">
		// 인증코드 전송
		$('#mailCheckBtn').click(function() {
			var email =  $('#memail').val();
			console.log(email);
			$.ajax({
				type: 'get',
				url: '${pageContext.request.contextPath }/mailCheck?email='+email,
				success: function(data){
					console.log(data);
					$('#emailCode').attr('value',data);
					alert('인증번호가 발송되었습니다.');
					$('#inputCode').attr('disabled',false);
					$('#inputCode').focus();
				}
			});
		});
		
		// 이메일 인증 번호 확인
		$('#inputCode').change(function() {
			var inputVal = $('#inputCode').val();
			var correctCode = $('#emailCode').val();
			console.log(inputVal.length);
			if (inputVal == correctCode) {
				$('#mailCheckResult').text('인증성공!')
				$('#mailCheckResult').css('color', 'green');
			} else if (inputVal.length < 6) {
				$('#mailCheckResult').text('');
			} else {
				$('#mailCheckResult').text('인증실패!');
				$('#mailCheckResult').css('color', 'red');
			}
		});
	</script>

</body>
</html>