package com.my.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.score.Score;

@WebServlet("/mycontroller.do")
public class MYController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MYController() {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// doGet(request, response);
		// 두겟 두포스트에 둘다써주면 새로고침 안되고 계속 돌면서 화면안나옴 가장 먼저 의심
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String command = request.getParameter("command");
		System.out.println("[" + command + "]");

		if (command.equals("basic")) {
			response.sendRedirect("basic-arithmetic.jsp");
		} else if(command.equals("eltest")) {
	         Score sc = new Score("홍길동",100, 70, 85);
	         request.setAttribute("score", sc);
	         
	         RequestDispatcher dispatch = request.getRequestDispatcher("eltest.jsp");
	         dispatch.forward(request,response);
	      } else if(command.equals("jstltest")) {
	    	  List<Score> res = new ArrayList<Score>();
	    	  
	    	  for(int i = 10; i < 50; i += 10) {
	    		  Score sc = new Score("이름"+i, 50+i,50+i,50+i);
	    		  res.add(sc);
	    	  }
	    	  
	    	  request.setAttribute("list", res);
	    	  RequestDispatcher dispatch =
	    			  request.getRequestDispatcher("jstltest.jsp");
	    	  dispatch.forward(request, response);
	      } else if(command.equals("usebeantest")) {
	    	  response.sendRedirect("usebeantest.jsp");
	      }
		
	}

}
