<%@page import="com.muldel.dto.MDBoardDto"%>
<%@page import="java.util.List"%>
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
<script type="text/javascript" src="https://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	function allChk(bool){
		var chks = document.getElementsByName("chk");
		
		for(var i = 0; i < chks.length; i++) {
			chks[i].checked = bool;
		}
	}
	
	$(function(){
		$("#muldelform").submit(function(){
			if($("#muldelform input:checked").length == 0){
				alert("하나 이상 체크 하셔야 됩니다.");
				return false;
				// return false로 인해서
				// 선택글 삭제하면 애초에 submit이벤트가 발생안하게됨
				// muldel쪽을 들려서 다시 돌아오는게 아님 이거는
			}
		});
		
		$("input[name=chk]").click(function(){
			if($("input[name=chk]").length != $("input[name=chk]:checked").length){
				$("input[name=all]").prop("checked",false);
			}
		});
		
	});
	
	
	
</script>
</head>
<body>

<%
	MDBoardDao dao = new MDBoardDao();
	List<MDBoardDto> list = dao.selectList();
%>

	<%@ include file="./form/header.jsp" %>
	<!-- 해당페이지에 file을 추가해줄거야 내 폴더밑에 폼밑에 헤더라는 파일을 추가해줄거야 -->
	<!-- include는 포함한다. -->
	<!-- 파일 전체가 추가됨 개발자도구 켜보자 meta title style header 들어옴 -->
	<!-- 헤더에 추가한 스타일도 다 저장됨 -->
	
	<h1>글 목록</h1>

	<form action="./muldel.jsp" method="post" id="muldelform">
		<table border="1">
      <col width="30">
      <col width="50">
      <col width="100">
      <col width="300">
      <col width="100">
         <tr>
            <td><input type="checkbox" name="all" onclick="allChk(this.checked);"></td>
            <th>번   호</th>
            <th>작성자</th>
            <th>제   목</th>
            <th>작성일</th>
         </tr>
<%
		if(list.size() == 0) {	// 로우가 하나도 없다면 list의 값이 하나도 없다면
%>
		<tr>
			<td colspan="5">-----글이 존재하지 않습니다-----</td>
		</tr>
<%
		} else {
			for(MDBoardDto dto : list) {
%>
		<tr>
			<td align="center"><input type="checkbox" name="chk" value="<%=dto.getSeq() %>" ></td>
			<!-- checkbox가 form으로 전달되면 chk=1&chk=2 이렇게 들어옴 name=value -->
			<td align="center"><%=dto.getSeq() %></td>
			<td align="center"><%=dto.getWriter() %></td>
			<td align="center"><a href="boardselect.jsp?seq=<%=dto.getSeq()%>"><%=dto.getTitle() %></a></td>
			<td align="center"><%=dto.getRegdate() %></td>
		</tr>
<%
			}
		}
%>

		<tr>
			<td colspan="5" align="right">
				<input type="button" value="글작성" onclick="location.href='boardinsert.jsp'"/>
				<input type="submit" value="선택글 삭제"/>
			</td>
		</tr>
      </table>
	</form>
	
	<%@ include file="./form/footer.jsp" %>
</body>
</html>