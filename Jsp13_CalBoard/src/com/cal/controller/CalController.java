package com.cal.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cal.dao.CalDao;
import com.cal.dto.CalDto;
import com.cal.util.Utils;

// web.xml에서 맵핑하는거를 습관들이자 
// 어노테이션으로 하는거 말구!
@WebServlet("/cal.do")
public class CalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CalController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("[" + command + "]");
		CalDao dao = new CalDao();
		
		
		if (command.equals("calendar")) {
			response.sendRedirect("calendar.jsp");
			
		} else if (command.equals("insertcal")) {
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String date = request.getParameter("date");
			String hour = request.getParameter("hour");
			String min = request.getParameter("min");
			String mdate = year + Utils.isTwo(month)
								+ Utils.isTwo(date)
								+ Utils.isTwo(hour)
								+ Utils.isTwo(min);
			
			// 12자리로 나오기 위해 다 더해줌 
			// 하지만 월 일 시 분은 9이하의 수는 앞에 0이 붙어야함
			
			
			String id = request.getParameter("id");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			int res = dao.insertCalBoard(
					new CalDto(0, id, title, content, mdate, null));
			
			
			if (res > 0) {
				response.sendRedirect("cal.do?command=calendar");
			} else {
				request.setAttribute("msg", "일정 추가 실패");
				dispatch(request,response,"error.jsp");
			}
		} else if (command.equals("list")) {
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String date = request.getParameter("date");
			
			String yyyyMMdd = year + Utils.isTwo(month) + Utils.isTwo(date);
			
			List<CalDto> list = dao.getCalList("kh",yyyyMMdd);
			
			request.setAttribute("list", list);
			dispatch(request,response,"calendarlist.jsp");
		} else if(command.equals("detail")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			CalDto dto = dao.selectOne(seq);
			
			request.setAttribute("dto", dto);
			
			dispatch(request, response, "caldetail.jsp");
			
		} else if(command.equals("update")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			
			CalDto dto = dao.selectOne(seq);
			
			request.setAttribute("dto", dto);
			
			dispatch(request, response, "calupdate.jsp");
		} else if(command.equals("updateres")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			String year = request.getParameter("year");
			String month = request.getParameter("month");
			String date = request.getParameter("date");
			String hour = request.getParameter("hour");
			String min = request.getParameter("min");
			String mdate = year + Utils.isTwo(month)
								+ Utils.isTwo(date)
								+ Utils.isTwo(hour)
								+ Utils.isTwo(min);
			
			CalDto dto = new CalDto(seq,null,title,content,mdate,null);
			int res = dao.update(dto);
			
			if (res > 0) {
				response.sendRedirect("cal.do?command=calendar");
			} else {
				response.sendRedirect("cal.do?command=update&seq="+seq);
			}
		} else if(command.equals("delete")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			String mdate = request.getParameter("day");
			String year = mdate.substring(0,4);
			String month = mdate.substring(4,6);
			String date = mdate.substring(6,8);
			
			
			
			int res = dao.delete(seq);
			
			if(res > 0) {
				response.sendRedirect("cal.do?command=list&year="+year+"&month="+month+"&date="+date);
			} else {
				response.sendRedirect("cal.do?command=detail&seq="+seq);
			}
		} else if (command.equals("muldel")) {
			String[] seq = request.getParameterValues("chk");
			
			String mdate = request.getParameter("day");
			String year = mdate.substring(0,4);
			String month = mdate.substring(4,6);
			String date = mdate.substring(6,8);
			
			int res = dao.multiDelete(seq);
			
			
			if(res > 0) {
				response.sendRedirect("cal.do?command=list&year="+year+"&month="+month+"&date="+date);
			} else {
				response.sendRedirect("cal.do?command=list&year="+year+"&month="+month+"&date="+date);
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void dispatch(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}

}
