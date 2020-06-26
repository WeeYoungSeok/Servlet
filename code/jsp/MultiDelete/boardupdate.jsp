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
	
	MDBoardDao dao = new MDBoardDao();
	MDBoardDto dto = dao.selectOne(seq);
%>


	<h1>글 수정</h1>
	<form action="boardupdateres.jsp" method="post">
		<input type="hidden" name="seq" value="<%=dto.getSeq() %>"/>
	<table border="1">
		<tr>
			<td>이름</td>
			<td><input type="text" value="<%=dto.getWriter() %>" name="writer" readonly="readonly"/></td>
		</tr>
		<tr>
			<td>제목</td>
			<td><input type="text" value="<%=dto.getTitle() %>" name="title"/></td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea cols="50" rows="6" name="content"><%=dto.getContent() %></textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="button" value="취소" onclick="location.href='boardselect.jsp?seq=<%=dto.getSeq()%>'"/>
				<input type="submit" value="수정"/>
			</td>
		</tr>
	</table>
	</form>
</body>
</html>