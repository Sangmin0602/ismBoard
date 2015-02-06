<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글쓰기</title>
<jsp:include page="/WEB-INF/jsp/include/common-js.jsp"></jsp:include>
<script type="text/javascript">
var ctxpath = ctxpath || '<%= pageContext.getServletContext().getContextPath() %>';

function processWriting ( ) {
	
	var title = "ddd";
	var cotent = "글을 넣습니다.";
	
	$.post(ctxpath + '/write.json' , $('#frm').serialize(), 
		function(response){ 
		if ( response.success) {
			document.location.href = ctxpath + response.nextUrl;
		} else {
			alert ( 'Writing 실패 : ' + response.cause);
		}
	});
}
$(document).ready ( function(){
	
	$("#btn").click ( processWriting );
	
});
</script>
</head>
<body>
<form id="frm">
<div> 제목 : <input type="text" name="title" id="title"></div>
<div> 내용 : <input type="text" name="content" id="title"></div>
<div> <input type="button" id="btn" value="가입하기"></div>
</form>
</body>
</html>