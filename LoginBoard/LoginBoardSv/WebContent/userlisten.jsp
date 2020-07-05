<%@page import="com.mvc.model.dto.LoginBoardDto"%>
<%@page import="java.util.List"%>
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
	List<LoginBoardDto> list = (List<LoginBoardDto>)request.getAttribute("listen");
%>
	<h1>회원 조회(가입 유저)</h1>
	
	<table border="1">
		<col width="50">
		<col width="100">
		<col width="100">
		<col width="500">
		<col width="50">
		<col width="100">
		
		<tr>
			<th>번    호</th>
			<th>아 이 디</th>
			<th>이    름</th>
			<th>이 메 일</th>
			<th>현재등급</th>
			<th>등급변경</th>
		</tr>
		
		<%
			if(list.size() == 0) {
		%>
			<tr>
				<td colspan="6">-----회원 없음-----</td>
			<tr>
				
		<%
			} else {
				for(LoginBoardDto dto : list) {
		%>
			<tr>
				<td><%=dto.getMyno() %></td>
				<td><%=dto.getMyid() %></td>
				<td><%=dto.getMyname() %></td>
				<td><%=dto.getMyemail() %></td>
				<td><%=dto.getMyrole() %></td>
				<td align="center"><input type="button" value="등급변경" onclick=""/></td>
			</tr>
		<% 
				}
			}
		%>
		
		<tr>
			<td colspan="6" align="right">
				<input type="button" value="메인" onclick="history.back();"/>
			</td>
		</tr>
	</table>
</body>
</html>