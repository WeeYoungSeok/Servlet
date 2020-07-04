<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--  -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>JSTL Page</h1>
	<!-- Jsp Standard Tag Library -->

	<table border="1">
		<tr>
			<th>이름</th>
			<th>국어</th>
			<th>영어</th>
			<th>수학</th>
			<th>총점</th>
			<th>평균</th>
			<th>등급</th>
		</tr>
		<c:forEach items="${list }" var="score">
			<tr>
				<td>
					<c:if test="${score.name eq '이름10' }">
						<c:out value="박진우"></c:out>
					</c:if> 
					<!-- 먼저 처음 list에 이름10이 if문을 검사하고나서
						 다음 반복문 choose문으로 들어감 ( 이때도 처음 td이면서 이름10 )
						 그러면 이름10이 choose문으로 들어가서 when을 다물어봄
						 결국 다아니여서 otherwise를 만나 누구지?가 출력이됨
						 이게 처음 list인 이름10이고 처음 td임 그래서 박진우 누구지?
						 라고 출력이 되는거임
						 그 다음 list에 이름20이들어오고 if문은 조건 거짓이어서 넘어가고
						 choose문으로 들어가는거임
						 한마디로 list의 이름이 들어오고 if문 검사하고 choose문 검사하는거임  -->
					<!-- when 안에 참이면 그 안에 있는 값 발동! -->
					<c:choose>
						<c:when test="${score.name eq '이름20' }">
							<c:out value="${score.name } 님!!"></c:out>
						</c:when>
						<c:when test="${score.name eq '이름30' }">
							<c:out value="${score.name }"></c:out>
						</c:when>
						<c:otherwise>
							<c:out value="누구지?"></c:out>
						</c:otherwise>
					</c:choose>
				</td>
				<td>${score.kor }</td>

				<td>${score.eng}<c:if test="${score.eng ge '70'}">
						<c:out value="멋져!!"></c:out>
					</c:if>
				</td>
				<td>${score.math }</td>
				<td>${score.sum }</td>
				<td>${score.avg }</td>

				<td>
					<!-- choose안에 when없으면 터짐 --> 
					<c:choose>
						<c:when test="${score.grade == 'A' || score.grade == 'B'}">
							<c:out value="pass"></c:out>
						</c:when>
						<c:otherwise>
							<c:out value="fail"></c:out>
						</c:otherwise>
					</c:choose>
				</td>

			</tr>
		</c:forEach>
	</table>

	<!-- 주석안에 jstl 태그쓰지말자 터짐
	그냥 태그를 쓰지말자 안에 쓰고싶으면 <%-- 여기에쓰자--%> -->
	
	<!-- eq : 같다
		 ne : 틀리다
		 empty : null -->
	<br/>
	<h1>구구단</h1>

	<c:forEach var="i" begin="2" end="9" step="1">		
				<b>${i }단</b><br>
		<c:forEach var="j" begin="1" end="9" step="1">
				${i}x${j}=${i*j}<br/>
		</c:forEach>
				<br/>
	</c:forEach>
			
	


</body>
</html>