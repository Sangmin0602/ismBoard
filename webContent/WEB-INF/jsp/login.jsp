<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인 하기</title>
<jsp:include page="/WEB-INF/jsp/include/common-js.jsp"></jsp:include>
<script type="text/javascript">
var ctxpath = ctxpath || "<%= pageContext.getServletContext().getContextPath()%>";
function processLogin () {
	var id = $('#user').val();
	var pass = $('#password').val();
	
	$.post(ctxpath +"/login.json",$('#frm').serialize(),function(response){
		if(response.success) {
			document.location.href = response.nextUrl; //"/Simpleboard"
		} else {
			$('#error-panel').html('login fails : ' + response.ecode );
		}
	})
}
( function() {
	$(document).ready ( function() {
		$('#btnLogin').click ( processLogin);
	});
})();
</script>
</head>
<body>

<!-- <form name="frm" id="frm">
	<input type="text" name="user" value=""/>
	<input type="password" name="password" />
	<input type="submit" value="로그인"/>
</form> -->

<div id="error-panel"></div>
<form name="frm" id="frm">
<%  
    String qs = request.getQueryString();
    if ( qs != null) { 
        qs = qs.substring("target=".length());
%>
    <input type="hidden" name="target" value="<%=qs %>"/>
<%}%>
	<input type="text" name="user" value="" id="user"/>
	<input type="password" name="password" id="password" />
	<input type="button" value="로그인" id="btnLogin"/>
</form>
</body>
</html>