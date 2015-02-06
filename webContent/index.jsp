<%@page import="ism.web.board.model.UserVO"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
</head>
<body>
<%
	UserVO loginUser = (UserVO) session.getAttribute ("user");
	if ( loginUser != null ) {
%>
	환영합니다. <%= loginUser.getNickName() %> 님
<%
	}
%>

<h2>잘 나오냐?????</h2>
</body>
</html>
