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
	<!-- session이라서 유지된다
		 id가 sc라서 sc.name으로 메서드 불러옴 -->
	<h1>${sc.name }님 환영합니다!</h1>
	
	<jsp:useBean id="sc" class="com.my.score.Score" scope="session"></jsp:useBean>
	
	<jsp:getProperty property="name" name="sc"/>
	<jsp:getProperty property="kor" name="sc"/>
	<jsp:getProperty property="eng" name="sc"/>
	<jsp:getProperty property="math" name="sc"/>
</body>
</html>