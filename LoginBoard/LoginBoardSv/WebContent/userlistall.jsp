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
	List<LoginBoardDto> list = (List<LoginBoardDto>)request.getAttribute("listall");
%>

	<h1 align="center">회원 조회(전체 유저)</h1>
	
	<table border="1">
		<col width="80"/>
		<col width="100"/>
		<col width="100"/>
		<col width="100"/>
		<col width="400"/>
		<col width="200"/>
		<col width="300"/>
		<col width="50"/>
		<col width="100"/>
		
		<tr>
			<th>번호</th>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이름</th>
			<th>주소</th>
			<th>전화번호</th>
			<th>이메일</th>
			<th>현재등급</th>
			<th>등급</th>
		</tr>
		<%
			if(list.size() == 0) {
		%>
				<tr>
				<td colspan="9" align="center">----가입 유저 없음-----</td>
				</tr>
		<% 
			} else {
				for(LoginBoardDto dto : list) {
		%>
			<tr>
				<td><%=dto.getMyno() %></td>
				<td><%=dto.getMyid() %></td>
				<td><%=dto.getMypw() %></td>
				<td><%=dto.getMyname() %></td>
				<td><%=dto.getMyaddr() %></td>
				<td><%=dto.getMyphone() %></td>
				<td><%=dto.getMyemail() %></td>
				<td><%=dto.getMyenabled() %></td>
				<td><%=dto.getMyrole() %></td>
			</tr>
		
		<%
				}
			}
		%>
		
	</table>
</body>
</html>