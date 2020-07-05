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
<form action="controller.do?command=login" method="post">
	<table border="1">
		<tr>
			<td>아이디</td>
			<td><input type="text" name="myid"/></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="text" name="mypw"/></td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="submit" value="로그인"/>
				<input type="button" value="회원가입" onclick=""/>
			</td>
		</tr>
	</table>
</form>
</body>
</html>