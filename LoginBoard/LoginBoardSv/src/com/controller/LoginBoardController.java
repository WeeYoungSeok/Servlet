package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mvc.model.biz.LoginBoardBiz;
import com.mvc.model.biz.LoginBoardBizImpl;
import com.mvc.model.dto.LoginBoardDto;

@WebServlet("/controller.do")
public class LoginBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginBoardController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		HttpSession session = request.getSession();

	
		
		String command = request.getParameter("command");
		System.out.println("[" + command + "]");
		
		LoginBoardBiz biz = new LoginBoardBizImpl();
		if(command.equals("login")) {
			
			String myid = request.getParameter("myid");
			String mypw = request.getParameter("mypw");
			
			LoginBoardDto dto = biz.login(myid, mypw);
			
			if(dto != null) {
				
				session.setAttribute("dto", dto);
				
				if(dto.getMyrole().equals("ADMIN")) {
					jsResponse("로그인 성공","adminmain.jsp",response);
				} else if(dto.getMyrole().equals("USER")) {
					jsResponse("로그인 성공","usermain.jsp",response);
				}
			}else {
				jsResponse("로그인 실패","index.jsp",response);
			}
			
		} else if(command.equals("userlisten")) {
			
			List<LoginBoardDto> list = biz.selectEnabled();
			request.setAttribute("listen", list);
			dispatch("userlisten.jsp",request,response);
			
		} else if(command.equals("userlistall")) {
			List<LoginBoardDto> list = biz.selectList();
			request.setAttribute("listall", list);
			dispatch("userlistall.jsp",request,response);
		} else if(command.equals("logout")) {
			session.invalidate();
			jsResponse("로그아웃 되었습니다","index.jsp",response);
		} else if(command.equals("userdetail")) {
			
		}
	}
	
	public void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatch = 
				request.getRequestDispatcher(url);
		
		dispatch.forward(request, response);
	}
	
	public void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String s = "<script type='text/javascript'>" +
				   "alert('" + msg + "');" + 
				   "location.href='" + url + "';" +
				   "</script>";
		
		PrintWriter out = response.getWriter();
		out.append(s);
		
		
	}

}
