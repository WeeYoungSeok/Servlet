<%@page import="java.util.Calendar"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<%
	int year = Integer.parseInt(request.getParameter("year"));
	int month = Integer.parseInt(request.getParameter("month"));
	int date = Integer.parseInt(request.getParameter("date"));
	int lastDay = Integer.parseInt(request.getParameter("lastday"));

	Calendar cal = Calendar.getInstance();
	int hour = cal.get(Calendar.HOUR_OF_DAY);
	int min = cal.get(Calendar.MINUTE);
%>
<body>

	<h1>일정 작성하기</h1>
	
	<form action="cal.do" method="post">
		<input type="hidden" name="command" value="insertcal"/>
		
		<table border="1">
			<tr>
				<th>ID</th>
				<td><input type="text" name="id" value="kh" readonly/></td>
				<!-- 로그인된 아이디가 value에 들어오게 하자  -->
			</tr>
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
            <td><input type="text" name="title"/></td>
         </tr>
         <tr>
            <th>내용</th>
            <td>
               <textarea rows="10" cols="60" name="content"></textarea>
            </td>
         </tr>
         <tr>
         	<td colspan="2" align="right">
         		<input type="submit" value="일정작성"/>
         		<input type="button" value="돌아가기" onclick=""/>
         	</td>
         </tr>
		</table>
	</form>

</body>
</html>