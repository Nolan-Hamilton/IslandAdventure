package edu.ycp.cs320.IslandAdventure.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.IslandAdventure.controller.ActionController;
import edu.ycp.cs320.IslandAdventure.controller.InventoryController;
import edu.ycp.cs320.IslandAdventure.model.Inventory;
import edu.ycp.cs320.IslandAdventure.model.*;
import edu.ycp.cs320.IslandAdventure.controller.*;

public class NewAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("New Account Servlet: doGet");
		
		req.getRequestDispatcher("/_view/newAccount.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("NewAccountServlet doPost");
		
		// Navigating to new page by pressing button
		if (req.getParameter("back2Login") != null){
			System.out.println("back2Login has been clicked");
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp); //Go to this page
		}
		
		// Create new account with null values
		String errorMessage = null;
		Account account = new Account(null, null, null, null);
		
		try {
			String user = (req.getParameter("user").toString());
			String pass = (req.getParameter("pass").toString());
			String pass2 = (req.getParameter("pass2").toString());
			
			//System.out.println("User: " + user + pass + pass2);
			//System.out.println("user: " + user != null);
			//System.out.println("pass: " + pass.equals(pass2));
			
			if (!(user.equals("")) && !(pass.equals("")) && !(pass2.equals("")) && (pass.equals(pass2))) {
				//System.out.println("Sucess!");
				
				account.setUsername(user);
				account.setPassword(pass);
				Player player = new Player(0, 0, 0, 0, null, null);
				Location[][][] map = new Location[25][25][25];
				account.setPlayer(player);
				account.setMap(map);
				req.setAttribute("account", account);	// Passes the account to the next servlet
				req.getRequestDispatcher("/_view/index.jsp").forward(req, resp); //Go to this page
				//req.getRequestDispatcher("/index").forward(req, resp);
				System.out.println(account.getUsername() + " is now playing");
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
		
		
		/* THIS IS JUST COPIED FROM INDEXSERVLET
		// create Inventory model - model does not persist between requests
		// must recreate it each time a Post comes in 
		Inventory inventoryModel = new Inventory();
		
		InventoryController inventoryController = new InventoryController();
		
		// assign model reference to controller so that controller can access model
		inventoryController.setModel(inventoryModel);
		
		// Initialize variables in the Inventory model
		inventoryController.createGame();
		
		req.setAttribute("inventory", inventoryModel);
		
		req.setAttribute("action", req.getParameter("action"));
		
		String action = req.getParameter("action");
	
		req.setAttribute("lastAction", action);
		
		ActionController controller = new ActionController();
		controller.interpretAction(action);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/newAccount.jsp").forward(req, resp);
		*/
	}
}
