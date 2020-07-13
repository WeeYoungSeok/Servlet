<%@page import="java.util.Calendar"%>
<%@page import="com.cal.dto.CalDto"%>
<%@page import="com.cal.util.Utils"%>
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
	CalDto dto = (CalDto)request.getAttribute("dto");
	int year = Integer.parseInt(dto.getMdate().substring(0,4));
	int month = Integer.parseInt(dto.getMdate().substring(4,6));
	int date = Integer.parseInt(dto.getMdate().substring(6,8));
	
	
	int hour = Integer.parseInt(dto.getMdate().substring(8,10));
	int min = Integer.parseInt(dto.getMdate().substring(10,12));
	Calendar cal = Calendar.getInstance();
	cal.set(year, month-1, 1);
	
	int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	
%>
	<h1>일정 수정하기</h1>
	<form action="cal.do?command=updateres" method="post">
	<input type="hidden" value="<%=dto.getSeq() %>" name="seq"/>
	<table border="1">
		<tr>
			<th>일정</th>
			<td>
					<select name="year">
<%
						for (int i = year - 5; i <= year + 5; i++ ) {
%>
						<!-- 우리가 일정을 현재년도를 기준으로 -5년부터 +5년까지 선택하게 해주고
							 삼항연산자를 이용해 우리가 처음에 일정 년도를 선택할때
							 현재 년도로 잡혀있어야하기때문에 해줌 -->
						<option value="<%=i%>"<%=(year == i)?"selected" : "" %>><%=i %></option>
<%
						}
%>
					</select>년
					
					<select name="month">
					
					<%
						for(int i = 1; i < 13; i++) {
					%>
						<option value="<%=i %>" <%=(month==i)?"selected":"" %>><%=i %></option>
					<%
						}
					%>
					</select>월
					
					<select name="date">
					<%
						for(int i = 1; i <= lastDay; i++) {
					%>
						<option value="<%=i %>" <%=(date==i)?"selected":"" %>><%=i %></option>
					<%
						}
					%>
					</select>일
					
					<select name="hour">
					<%
						for(int i = 0; i <= 23; i++) {
					%>
						<option value="<%=i %>" <%=(hour==i)?"selected":"" %>><%=i %></option>
					<%
						}
					%>
					</select>시
					
					<select name="min">
					<%
						for(int i = 0; i <= 59; i++) {
					%>
						<option value="<%=i %>" <%=(min==i)?"selected":"" %>><%=i %></option>
					<%
						}
					%>
					</select>분
				</td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" value="<%=dto.getTitle() %>"/></td>
		</tr>
		<tr>
			<th>내용<th>
			<textarea rows="10" cols="60" name="content"><%=dto.getContent() %></textarea>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="button" value="취소" onclick="location.href='cal.do?command=detail&seq=<%=dto.getSeq()%>'"/>
				<input type="submit" value="완료"/>
  			</td>
		</tr>
	</table>
	</form>
</body>
</html>