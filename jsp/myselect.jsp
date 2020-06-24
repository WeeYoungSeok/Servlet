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
	// 다오한테 내가 받아온 번호 보내서 dto하나 받아옴
%>
	
	<h1>글 상세보기</h1>
		<table border="1">
			<tr>
				<td>이름</td>
				<td><%=dto.getMyname()%></td>
			</tr>
			
			<tr>
				<td>제목</td>
				<td><%=dto.getMytitle()%></td>
			</tr>
			
			<tr>
				<td>내용</td>
				<td><textarea cols="60" rows="10" readonly="readonly"><%=dto.getMycontent() %></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="right">
					<input type="button" value="취소" onclick=""/>
					<input type="button" value="수정" onclick="location.href='myupdate.jsp?myno=<%=dto.getMyno()%>'"/>
					<input type="button" value="삭제" onclick="location.href='mydelete.jsp?myno=<%=dto.getMyno()%>'"/>
				</td>
			</tr>
		</table>

</body>
</html>