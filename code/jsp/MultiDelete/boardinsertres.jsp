<%@page import="com.muldel.dto.MDBoardDto"%>
<%@page import="com.muldel.dao.MDBoardDao"%>
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

<%
	String writer = request.getParameter("writer");
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	
	MDBoardDao dao = new MDBoardDao();
	
	
	int res = dao.insert(new MDBoardDto(0,writer,title,content,null));
	
	if(res>0){
	
%>
	<script type="text/javascript">
		alert("추가 성공");
		location.href="boardlist.jsp";
	</script>
<%
	} else {
%>
	<script type="text/javascript">
		alert("추가 실패");
		location.href="boardinsert.jsp";
	</script>
<%
	}
%>
</body>
</html>