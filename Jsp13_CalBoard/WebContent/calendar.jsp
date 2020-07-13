<%@page import="com.cal.util.Utils"%>
<%@page import="java.util.Calendar"%>
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
	#calendar{
		border-collapse: collapse;
		border: 1px solid dodgerblue;
	}
	#calendar th{
		width: 80px;
		border: 1px solid dodgerblue;
	}
	#calendar td{
		width: 80px;
		height: 80px;
		border: 1px solid dodgerblue;
		text-align: left;
		vertical-align: top;
		position: relative;
	}
	a{
		text-decoration: none;
	}
</style>
</head>
<body>
<%
	Calendar cal = Calendar.getInstance();

	int year = cal.get(Calendar.YEAR);
	int month = cal.get(Calendar.MONTH) + 1;

	
	String paramYear = request.getParameter("year");
	String paramMonth = request.getParameter("month");
	
	
	if (paramYear != null) {
		year = Integer.parseInt(paramYear);
	}
	if (paramMonth != null) {
		month = Integer.parseInt(paramMonth);
	}

	
	if (month > 12) {
		year++;
		month = 1;
	}
	if (month < 1) {
		year--;
		month = 12;
	}
	
	// 현재년도, 현재월, 1일 셋팅
	cal.set(year, month-1, 1);
	// 이걸로 2020년 7월 1일로 셋팅
	
	// 1일의 요일
	int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
	// 위에서 셋팅을 해주면 여기서 1일의 요일이 나옴
	
	
	// 마지막 일
	int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	// 2020년 7월의 마지막일
	
	
	// 달력!
	
%>

	<table id="calendar">
		<caption>
			<a href="calendar.jsp?year=<%=year-1%>&month=<%=month%>">◀◀</a>
			<a href="calendar.jsp?year=<%=year%>&month=<%=month-1%>">◁</a>
			
			<span class="y"><%=year %></span> 년
			<span class="m"><%=month %></span> 월
			
			<a href="calendar.jsp?year=<%=year%>&month=<%=month+1%>">▷</a>
			<a href="calendar.jsp?year=<%=year+1%>&month=<%=month%>">▶▶</a>
		</caption>
		<tr>
			<th>일</th><th>월</th><th>화</th><th>수</th><th>목</th><th>금</th><th>토</th>
		</tr>
		
		<tr>
			<%
			// 공백
				for(int i = 0; i < dayOfWeek-1; i++) {
					// 7월의 수요일이 자바내부에서 4임
					// 3칸을 띄워주어야하기위해서 -1을 해줌
					out.println("<td>&nbsp;</td>");
				}
			
			for(int i = 1; i <= lastDay; i++) {
				
				%>
					<td>
						<a href="cal.do?command=list&year=<%=year%>&month=<%=month%>&date=<%=i%>&lastday=<%=lastDay%>" style="color:<%=Utils.fontColor(i, dayOfWeek) %>"><%=i %></a>
						<a href="insertcalboard.jsp?year=<%=year%>&month=<%=month%>&date=<%=i%>&lastday=<%=lastDay%>">
							<img alt="일정추가" src="./a.jpg" style="width: 10px; height: 10px;"/>
						</a>
					</td>
				<%
				if((dayOfWeek-1 + i) % 7 == 0) {
					// 요일의 숫자 -1하고 i를 더해주면 딱 7일 떨어지는 순간이
					// 토요일임 그때 tr로 줄바꿈
					out.print("<tr></tr>");
				}
			}
			
			// 뒤쪽 공백
			
			for(int i = 0; i < (7-(dayOfWeek-1 + lastDay)%7)%7; i++) {
				out.print("<td>&nbsp;</td>");
			}
			// 고민해보기
			%>
		</tr>
		
	</table>

</body>
</html>