package edu.ycp.cs320.IslandAdventure.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.IslandAdventure.controller.*;
import edu.ycp.cs320.IslandAdventure.model.*;
import edu.ycp.cs320.IslandAdventure.persist.*;


public class NewAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Fields needed
	GameEngine engine = null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("New Account Servlet: doGet");
		engine = new GameEngine();
		req.getRequestDispatcher("/_view/newAccount.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("NewAccountServlet doPost");
		
		// Navigating to new page by pressing button
		if (req.getParameter("back2Login") != null){
			System.out.println("back2Login has been clicked");
			//req.getRequestDispatcher("/_view/login.jsp").forward(req, resp); //Go to this page
			resp.sendRedirect(req.getContextPath() + "/login");
		}
		
		// Create new account with null values
		String errorMessage = null;
		FakeDatabase fakeData = new FakeDatabase();
		Account account = new Account(null, null, null);
		
		try {
			String user = (req.getParameter("user").toString());
			String pass = (req.getParameter("pass").toString());
			String pass2 = (req.getParameter("pass2").toString());
			
			//System.out.println("User: " + user + pass + pass2);
			//System.out.println("user: " + user != null);
			//System.out.println("pass: " + pass.equals(pass2));
			
			if (!(user.equals("")) && !(pass.equals("")) && !(pass2.equals("")) && (pass.equals(pass2))) {
				//System.out.println("Success!");
				account.setUsername(user);
				account.setPassword(pass);
				Player player = new Player(0, 0, 0, 0, null, null, null, null, null);
				Location playerLocation = new Location(10,10,10);
				player.changeLocation(playerLocation);
				account.setPlayer(player);
				fakeData.getAccountList().add(account); //Add account to arraylist of Accounts
				
				Boolean newAcc = engine.insertNewAccountIntoDatabase(account); // account data is added to database
				if (newAcc == true){
					req.setAttribute("accountsList", fakeData); //Set attribute for fakeAccount
					req.setAttribute("account", account);	// Passes the account to the next servlet
					req.setAttribute("user", req.getParameter("user")); //Set Attributes
					req.setAttribute("pass", req.getParameter("pass"));
					req.getSession().setAttribute("username", req.getParameter("user"));
					req.getSession().setAttribute("password", req.getParameter("pass"));
					//req.getRequestDispatcher("/_view/index.jsp").forward(req, resp); //Go to this pag
					resp.sendRedirect(req.getContextPath() + "/index");
					//req.getRequestDispatcher("/index").forward(req, resp);
					System.out.println(account.getUsername() + " is now playing");
				}else{
					errorMessage = "Username already taken. Please choose a different username.";
				}
				
			}else{
				if (pass != pass2){
					errorMessage = "Please make sure your password is entered correctly in both boxes!";
				}
				if ((user.equals("")) || (pass.equals("")) || (pass2.equals(""))) {
					errorMessage = "Pease do not leave anything blank!";
				}
			}
			
			
		} catch (Exception e) {
			errorMessage = e.toString();
		}
		
		

		req.setAttribute("errorMessage", errorMessage); //In case of error message
		req.setAttribute("user", req.getParameter("user"));
		req.setAttribute("pass", req.getParameter("pass"));
		req.setAttribute("pass2", req.getParameter("pass2")); //These are supposed to keep the inputs from disappearing.
		
		// If there is an error message, show it and stay on the same page.
		if (errorMessage != null){
			System.out.println("Error: " + errorMessage);
			req.getRequestDispatcher("/_view/newAccount.jsp").forward(req, resp); //Go to this page
		}
	}
}
