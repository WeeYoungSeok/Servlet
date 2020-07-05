<%@page import="com.mvc.model.dto.LoginBoardDto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
response.setHeader("Pragma", "no-cache");			// http 1.0
response.setHeader("Cache-control", "no-store");	// http 1.1
response.setHeader("expires", "0");					// proxy server
// 서버가 가지고있던 캐시를 저장안하겠다라고 설정한 내용이다.
// 캐시 : 잠깐 여기에 저장하겠다.
// 우리가 클릭했던 링크들 이런게 캐시에 저장되어있다.

// 데이터가 변경되었을때 이전 내용을 화면에 보여주는 이유!
// 서버에서 다시 응답하는게 아니라 캐시에 저장된 내용을 가져오기때문에
// 우리가 로그아웃을 누르고 뒤로가기를 눌러도 로그인이 되어있는 이유이다!
%>   
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">

	a{
		text-decoration: none;
		color: orangered;
	}
</style>
</head>
<body>
	<%
		LoginBoardDto dto = (LoginBoardDto)session.getAttribute("dto");
	
	if (dto == null ) {
		pageContext.forward("index.jsp");
		// 우리가 가져온 dto값이 null이면 index.jsp로 가라
		// 우리가 로그아웃을하고 로그인페이지로 와서 다시 뒤로가기를 누르면 500에러가 떠버림
		// 그 500에러 방지
	}
	%>
	
	
	<h1><%=dto.getMyname() %>님 환영합니다!</h1>
	
	<span>등급 : <%=dto.getMyrole() %></span> <br/>
	<a href="controller.do?command=logout">로그아웃</a> <br/>
	<a href="controller.do?command=userdetail&myno=<%=dto.getMyno()%>">내 정보 보기</a>
	
</body>
</html>