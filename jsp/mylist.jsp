<%@page import="com.my.model.dto.MyDto"%>
<%@page import="java.util.List"%>
<%@page import="com.my.model.dao.MyDao"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
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
	MyDao dao = new MyDao();
	List<MyDto> list = dao.selectList();
%>
	<h1>글 목록</h1>
	
	<table border="1">
		<col width="50px"/>
		<col width="100px"/>
		<col width="300px"/>
		<col width="100px"/>
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>제목</th>
			<th>날짜</th>
		</tr>
		
<%
	for(int i = 0; i < list.size(); i++) {
%>
		<tr>
			<!-- dto를 로우한줄에 담고 그걸 list로 관리함! -->
			<td><%=list.get(i).getMyno()%></td>
			<td><%=list.get(i).getMyname()%></td>
			<td><a href="myselect.jsp?myno=<%=list.get(i).getMyno()%>"><%=list.get(i).getMytitle() %></a></td>
			<td><%=list.get(i).getMydate()%></td>
		</tr>
<%
	}
%>

		<tr>
			<td colspan="4" align="right">
				<input type="button" value="글 작성" onclick="location.href='myinsert.jsp'"/>
			</td>
 		</tr>
	</table>

</body>
</html>