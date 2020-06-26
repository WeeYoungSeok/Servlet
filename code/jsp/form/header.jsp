<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8");%>
<% response.setContentType("text/html; charset=UTF-8");%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
	header{
		background-color: #B4FFFF;
		height: 50px;
	}
	footer{
		background-color: #B4FFFF;
		height: 30px;
		text-align: center;
		line-height: 30px;
	}
	a{
		text-decoration: none;
	}
</style>
</head>
<body>

	<header>
		<a href="./boardlist.jsp">게시판 홈</a>
		<!-- 여기서는 form 폴더안에있는 boardlist.jsp를 찾자인데 -->
		<!-- 원래는  없다 -->
		<!-- 하지만 boardlist에서 이 파일을 인클루드하면 -->
		<!-- ./는 WebContent폴더로 바뀌게된다 -->
		<!-- 인클루드되면 ./의 위치도 바뀌게된다 -->
		<!-- ./는 현재위치를 의미 -->
	</header>
	
</body>
</html>