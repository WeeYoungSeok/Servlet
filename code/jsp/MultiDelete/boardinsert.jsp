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
	<h1>글 작성</h1>
	
	<form action="boardinsertres.jsp" method="post">
		<table border="1">
			<tr>
				<td>이름</td>
				<td><input type="text" name="writer"/></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><input type="text" name="title"/></td>
			</tr>
			<tr>
				<td>내용</td>
				<td><textarea cols="50" rows="6" name="content"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="button" value="취소" onclick="location.href='boardlist.jsp'"/>
					<input type="submit" value="완료"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>