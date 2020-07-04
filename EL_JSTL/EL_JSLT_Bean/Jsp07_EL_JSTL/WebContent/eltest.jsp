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
	<h1>Expression Language</h1>
	
	<table border="1">
		<tr>
			<th colspan="2">${score.name }님의 점수</th>
		</tr>
		
		<tr>
			<th>국어</th>
			<td>${score.kor}</td>
			<!-- score.kor은 score.getKor()을 호출해줌 
			필드값이아니라 게터메소드 호출
			근데 대신 get대문자나머지소문자 이렇게하고
			.대문자를소문자로바꾸고 나머지소문자 써야 호출됨
			앞에 score는 controller에서 받아온 score임 -->
		</tr>
		<tr>
			<th>영어</th>
			<td>${score.eng}</td>
		</tr>
		<tr>
			<th>수학</th>
			<td>${score.math }</td>
		</tr>
		<tr>
			<th>총점</th>
			<td>${score.sum }</td>
		</tr>
		<tr>
			<th>평균</th>
			<td>${score.avg }</td>
		</tr>
		<tr>
			<th>등급</th>
			<td>${score.grade }</td>
		</tr>
	</table>

</body>
</html>