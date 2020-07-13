<%@page import="com.cal.util.Utils"%>
<%@page import="com.cal.dto.CalDto"%>
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
	CalDto dto = (CalDto)request.getAttribute("dto");
	Utils utils = new Utils();
	utils.setToDates(dto.getMdate());
%>
	<h1>일정 상세보기</h1>
	
	<table border="1">
		<tr>
			<th>일정</th>
			<td><%=utils.getToDates() %></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><%=dto.getTitle() %></td>
		</tr>
		<tr>
			<th>내용<th>
			<textarea rows="10" cols="60" name="content" readonly><%=dto.getContent() %></textarea>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="button" value="목록" onclick=""/>
				<input type="button" value="수정" onclick="location.href='cal.do?command=update&seq=<%=dto.getSeq()%>&day=<%=dto.getMdate()%>'"/>
				<input type="button" value="삭제" onclick="location.href='cal.do?command=delete&seq=<%=dto.getSeq()%>&day=<%=dto.getMdate()%>'"/>
  			</td>
		</tr>
	</table>
</body>
</html>