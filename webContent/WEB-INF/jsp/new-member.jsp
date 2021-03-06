<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
<jsp:include page="/WEB-INF/jsp/include/common-js.jsp"></jsp:include>
<script type="text/javascript">
var ctxpath = "/SimpleBoard";

function sendJoinReq ( ) {
	var userid = $("#userid").val().trim();
	var pass = $("#password").val().trim();
	
	if ( userid.length == 0 ) {
		alert ( "아이디 제대로 입력해라");
		return;
	}
	
	if ( pass.length == 0 ) {
		alert ("패스워드가 엉망이다");
		return;
	}
	
	$.post(ctxpath + '/join.json' , $('#frm').serialize(), 
		/*
		 response = {"user":{"email":"dddd@naver.com","userid":"ddddd","password":"12345"},"success":true};
		*/
		function(response){ 
		if ( response.success) {
			alert ( '회원 가입 성공' + response.user.userid );
			document.location.href = response.href;
		} else {
			alert ( '회원 가입 실패 : ' + response.cause);
		}
	});
}
$(document).ready ( function(){
	
	$("#btn").click ( sendJoinReq );
	
});
</script>
</head>
<body>
<form id="frm">
<div> 아이디 : <input type="text" name="userid" id="userid"></div>
<div> 이메일 : <input type="text" name="email" id="email"></div>
<div> 비밀번호 : <input type="password" name="password" id="password"></div>
<div> <input type="button" id="btn" value="가입하기"></div>
</form>
</body>
</html>