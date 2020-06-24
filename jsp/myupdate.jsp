<%@page import="com.my.model.dto.MyDto"%>
<%@page import="com.my.model.dao.MyDao"%>
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
		int myno = Integer.parseInt(request.getParameter("myno"));
	
		MyDao dao = new MyDao();
		MyDto dto = dao.selectOne(myno);
	%>
	
	<h1>글 수정</h1>
		<form action="myupdateres.jsp" method="post">
			<input type="hidden" name="myno" value="<%=dto.getMyno()%>"/>
		<table border="1">
			<tr>
				<td>이름</td>
				<td><input type="text" value="<%=dto.getMyname()%>" readonly="readonly"/></td>
			</tr>
			
			<tr>
				<td>제목</td>
				<td><input type="text" name="mytitle" value="<%=dto.getMytitle()%>"/></td>
			</tr>
			
			<tr>
				<td>내용</td>
				<td><textarea cols="60" rows="10" name="mycontent"><%=dto.getMycontent() %></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="button" value="취소" onclick="location.href='myselect.jsp?myno=<%=dto.getMyno()%>'"/>
					<input type="submit" value="수정"/>
				</td>
			</tr>
		</table>
		</form>

</body>
</html>