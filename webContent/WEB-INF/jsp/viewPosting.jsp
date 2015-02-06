<%@page import="ism.web.board.model.PostingVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
	requestScope 안에 posting 애트리뷰트가 들어있습니다. 타입은 PostingVO 입니다.
	
 -->
<div>제목 : <input type="text" value="${ posting.title}"/></div>
<div>작성자 : <input type="text" value="${ posting.writer.nickName}"/></div>
<!-- 
	EL tag
	.xxxx.yy.zz
	
	getXxxx().getYy().getZz();
	
	Object value = request.getAttribute("posting");
	
	Class cls = value.getClass();
	Method getWriterMethod = cls.getMethod("getWriter");
	Object writer = getWriterMethod.invoke( value );
	
	Class wCls = writer.getClass();
	
	Object nickName = wCls.getMethod ( "getNickName").invoke ( writer)
	nickName.toString(*
 -->
<div>내용 : <input type="text" value="${ posting.content }"/></div>
</body>
</html>