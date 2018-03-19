package edu.ycp.cs320.IslandAdventure.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Login Servlet: doGet");
		
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("LoginServlet: doPost");
		
		// Navigating to new page by pressing button
		if (req.getParameter("justPlay") != null) {
			System.out.println("justPlay has been clicked!");
			req.getRequestDispatcher("/_view/index.jsp").forward(req, resp); //Go to this page
		}else{
			// if create new account was selected, then go to account page
			if (req.getParameter("newAcc") != null){
				System.out.println("newAcc has been clicked");
				req.getRequestDispatcher("/_view/newAccount.jsp").forward(req, resp); //Go to this page
			}
		}
	}
}
