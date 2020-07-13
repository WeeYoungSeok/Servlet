<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<h1 style="color:red;"><%=request.getAttribute("msg") %></h1>
	
	<p>
		사용에 불편을 드려서 죄송합니다.<br/>
		문의전화 : 010-0000-0000
	</p>
	<a href="cal.do?command=calendar">처음으로....</a>
</body>
</html>