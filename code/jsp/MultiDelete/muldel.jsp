<%@page import="com.muldel.dao.MDBoardDao"%>
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
	// name="chk" 인 value값 여러개를 한번에 받아준다.
	// values임
	// 파일?chk=1&chk=2&chk=3... 이렇게 넘어온걸 받아줌
	String[] seq = request.getParameterValues("chk");
	//multiDelete에 넘겨줄것임 
	
	// 유효성 검사
	if (seq == null || seq.length == 0) {
		// 체크한거 하나도 없이 삭제 버튼 눌렀을때
	
%>
	<script type="text/javascript">
		alert("하나 이상 체크해 주세요");
		location.href="boardlist.jsp"
	</script>
<%
	} else {	// 체크된게 있다면
	MDBoardDao dao = new MDBoardDao();
	int res = dao.multiDelete(seq);	// chk의 벨류를 넘겨주고
	if(res > 0) {	// 삭제가 잘 성공했다면
	
%>
	<script type="text/javascript">
		alert("선택된 글들을 삭제 성공했습니다.");
		location.href="boardlist.jsp";
	</script>
<%
	} else {	// 삭제가 잘 성공하지 않았다면
%>
	<script type="text/javascript">
		alert("선택된 글들을 삭제 실패했습니다.")
		location.href="boardlist.jsp";
	</script>
<%
	}
}
%>
</body>
</html>