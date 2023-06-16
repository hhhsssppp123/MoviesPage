/**
	
 * $(document).ready(function(){
    let currentUrl = window.location.href;
    var MLcode = '${MLcode}';
    console.log("movieList에게 전달받은 영화코드 :  " + MLcode);
    console.log("선택한 영화코드 :  " + selectedMvcode);
    console.log("로그인 아이디 :  " + loginId);
    console.log("현재 페이지 :  " + currentUrl);
    if(MLcode.length >= null){
    	console.log("선택한 영화코드 :  " + selectedMvcode);
       	$("#"+MLcode).click();
       	$("#"+MLcode).focus();
    }
	});
	
	function logOut(){
		location.href="${pageContext.request.contextPath }/logOut?currentUrl="+currentUrl;
	}
	
 */
 