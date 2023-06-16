<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.Date"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회사검색</title>
</head>
<body>
	<div id="serchArea1">
		 <input type="text" id="serachArea1_2" name="Wval" placeholder="회사명을 입력해주세요">
         <input type="button" onclick="findWP1_2()" value="찾기"><br>
         
		
			
	</div>
	<div id="serchedListArea">
	
				<form action=""></form>
				 <input type="text" id="serachedList_WP" placeholder="회사명" readonly="readonly"><br>
				 <input type="text" id="serachedList_C" placeholder="대표명" readonly="readonly"><br>
				 <input type="text" id="serachedList_Addr" placeholder="회사주소" readonly="readonly"><br>
				 
	</div>






</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>	
	
<script type="text/javascript">
	function findWP1_2(){
		var w_Obj= document.getElementById('serachArea1_2');
		var WpName = w_Obj.value;
		console.log("findWP1_2()호출");
		console.log("WpName : " + WpName);
		$.ajax({
			type: "get",
			url: "${pageContext.request.contextPath }/getWPList",
			data : {"WpName" : WpName},
			dataType : "json",
			success:function(WpList){
				console.log(WpList);
				var output ="";
				if(WpList.length <= 0){
					alert("검색결과가 없습니다.정확한 이름인지 다시 한번 확인해 주세요.");
				}else{
					//회사명, 대표이름, 주소
					for(var i = 0; i < WpList.length; i++){
						//회사명, 대표이름, 주소 받아와서 목록 ouput에 저장하기
						//output += '<button type="button" class="list-group-item" onclick="selectMovie(this,'+ "'"+mvinfo.mvcode+"'" +')">'+mvinfo.mvtitle+'</button> ';
						/* output += '<button type="button" class="list-group-item" onclick="selectth('+ "'"+thinfo.thcode+"'" +',this)">'+thinfo.thname+'</button> '; */
						output += '<textarea readonly="readonly"class="retext mb-2 border-0 font-weight-bold text-gray-800 w-100">'+WpList[i].CINUM+'</textarea>';
						output += '<textarea readonly="readonly"class="retext mb-2 border-0 font-weight-bold text-gray-800 w-100">'+WpList[i].CINAME+'</textarea>';
						output += '<textarea readonly="readonly"class="retext mb-2 border-0 font-weight-bold text-gray-800 w-100">'+WpList[i].CILEADER+'</textarea>';
						output += '<textarea readonly="readonly"class="retext mb-2 border-0 font-weight-bold text-gray-800 w-100">'+WpList[i].CIADDR+'</textarea>';
						output += '<textarea readonly="readonly"class="retext mb-2 border-0 font-weight-bold text-gray-800 w-100">'+WpList[i].CITYPE+'</textarea>';
						output += '<button type="button" onclick="dd('+ "'"+WpList[i].CINUM+"'"+",'"+WpList[i].CINAME+"'" +",'"+WpList[i].CILEADER+"'"+",'"+WpList[i].CIADDR+"'" +",'"+WpList[i].CITYPE+"'"+',this)">';
						output += '선택';
						output += '</button>';
						output += '<br>';
						//onclick="replyLike('+ "'"+reList[i].renum+"'" +',this)">';
					}
					
				}
				$("#serchedListArea").html(output);
			}
		});
	}
	
	
	function dd(cinum,ciname,cileader,ciaddr,cB){
		console.log("dd()호출")
		//session값으로 부모 페이지로 보내기
		console.log(cinum + ciname+cileader + ciaddr);
		window.opener.document.getElementById('inputWP').value = ciname;
		window.opener.document.getElementById('inputLN').value = cinum;
		window.opener.document.getElementById('inputCN').value = cileader;
		window.opener.document.getElementById('address').value = ciaddr;
		

		//inputWP
		//inputLN
		//inputCN
		//opener.call();
		window.open('about:blank','_self').self.close();
		
	}
</script>

</html>