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
	int seq = Integer.parseInt(request.getParameter("seq"));
	String title = request.getParameter("title");
	String content = request.getParameter("content");
	// String writer = request.getParameter("writer");
	// 우리가 writer는 굳이 받아올 필요없음 어차피 update에서 writer를 수정안하니깐
	// 즉 update.jsp에서도 writer를 name이런거 다안써줘도됨
	
	MDBoardDao dao = new MDBoardDao();
	int res = dao.update(new MDBoardDto(seq,null,title,content,null));
	
	if(res > 0){
%>
	<script type="text/javascript">
		alert("수정 성공");
		location.href="boardselect.jsp?seq=<%=seq%>";
	</script>
<%
	} else {
%>
	<script type="text/javascript">
		alert("수정 실패");
		location.href="boardupdate.jsp?seq=<%=seq%>";
	</script>

<%
	}
%>
</body>
</html>