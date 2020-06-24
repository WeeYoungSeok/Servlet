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
	// 넘버포맷익셉션 널 어쩌구 오류 많이 나니깐 update에서 myno꼭 받아오자.
	// 넘버포맷익셉션은 null이 결국 update문으로 들어가서 null로 받아서 안되는거임!!
	int myno = Integer.parseInt(request.getParameter("myno"));
	
	String mytitle = request.getParameter("mytitle");
	String mycontent = request.getParameter("mycontent");
	
	MyDto dto = new MyDto(myno,mytitle,mycontent);
	MyDao dao = new MyDao();
	int res = dao.update(dto);
	
	if(res > 0){
%>
	<script type="text/javascript">
		alert("수정 성공");
		location.href="mylist.jsp?myno=<%=dto.getMyno()%>";
	</script>

<%
	} else {
%>
	<script type="text/javascript">
		alert("수정 실패");
		location.href="myupdate.jsp?myno=<%=dto.getMyno()%>";
	</script>

<%
	}
%>
</body>
</html>