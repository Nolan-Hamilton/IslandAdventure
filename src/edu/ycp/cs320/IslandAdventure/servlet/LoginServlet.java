package edu.ycp.cs320.IslandAdventure.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.IslandAdventure.controller.GameEngine;
import edu.ycp.cs320.IslandAdventure.model.Account;
import edu.ycp.cs320.IslandAdventure.persist.FakeDatabase;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Fields needed
	GameEngine engine = null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Login Servlet: doGet");
		engine = new GameEngine();
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Login Servlet: doPost");
		
		// Navigating to new page by pressing button
		if (req.getParameter("justPlay") != null) {
			System.out.println("justPlay has been clicked!");
			req.getSession().setAttribute("username", null);
			//req.getRequestDispatcher("/_view/index.jsp").forward(req, resp); //Go to this page
			resp.sendRedirect(req.getContextPath() + "/index");
		}else{
			// if create new account was selected, then go to account page
			if (req.getParameter("newAcc") != null){
				System.out.println("newAcc has been clicked");
				//req.getRequestDispatcher("/_view/newAccount.jsp").forward(req, resp); //Go to this page
				resp.sendRedirect(req.getContextPath() + "/newAccount");
			}
		}
		
		String errorMessage = null;
		//FakeDatabase fakeData = new FakeDatabase();
		Account account = new Account(null, null, null, null);
		
		try{
			//Get input from JSP
			String user = (req.getParameter("user").toString());
			System.out.println(user);
			String pass = (req.getParameter("pass").toString());
			System.out.println(pass);
			// Put username and pw into account
			account.setUsername(user);
			account.setPassword(pass);
			System.out.println("1");
			//Verify that account with username and pw
			Boolean found = engine.verifyAccountInDatabase(account);
			if (found == true){
				//If account is found, then the password is valid
				System.out.println("2");
				req.getSession().setAttribute("username", account.getUsername());
				resp.sendRedirect(req.getContextPath() + "/index");
			}else{
				errorMessage = "Invalid username and/or password.";
			}
			
			
		} catch(Exception e) {
			errorMessage = e.toString();
		}
		
		req.setAttribute("errorMessage", errorMessage); //In case of error message
		req.setAttribute("user", req.getParameter("user"));
		req.setAttribute("pass", req.getParameter("pass"));
		
		// If there is an error message, print it.
		if (errorMessage != null){
			System.out.println("Error: " + errorMessage);
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp); //Go to this page
		}
	}
}
