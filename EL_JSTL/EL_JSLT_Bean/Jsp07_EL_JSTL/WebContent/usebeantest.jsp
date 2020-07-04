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
	<!-- javaBean : getter/setter를 가지는 데이터 표현 객체 -->
	<h1>useBean</h1>
	
	<!-- 다바에서 bean beans를 보면 클래스나 인스턴스를 
		 지칭할 확률이 진짜 높음 -->
		 
	<jsp:useBean id="sc" class="com.my.score.Score" scope="session"></jsp:useBean>
	<!-- Score sc = new Score(); 와 같은말 -->
	<!-- 스코프에 세션이라고 써놓으면 
		 세션 스코프 객체에 sc라는 이름으로 만들어진 객체가 없다면
		 새로 객체를 만들어줌 -->
	
	
	
	<jsp:setProperty property="name" name="sc" value="홍길동"/>
	<!-- sc.setName("홍길동"); -->
	<jsp:setProperty property="kor" name="sc" value="100"/>
	<jsp:setProperty property="eng" name="sc" value="55"/>
	<jsp:setProperty property="math" name="sc" value="40"/>

	
	<jsp:getProperty property="name" name="sc"/>
	<!-- sc.getName(); -->	 
	<jsp:getProperty property="kor" name="sc"/>	 
	<jsp:getProperty property="eng" name="sc"/>	 
	<jsp:getProperty property="math" name="sc"/>
	<jsp:getProperty property="sum" name="sc"/>
	<jsp:getProperty property="avg" name="sc"/>
	<jsp:getProperty property="grade" name="sc"/>	 
	
	<button onclick="location.href='res.jsp'">res...</button>
	
</body>
</html>