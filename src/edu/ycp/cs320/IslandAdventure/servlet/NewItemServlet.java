package edu.ycp.cs320.IslandAdventure.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.IslandAdventure.controller.*;
import edu.ycp.cs320.IslandAdventure.model.*;
import edu.ycp.cs320.IslandAdventure.persist.*;


public class NewItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Fields needed
	GameEngine engine = null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("New Item Servlet: doGet");
		engine = new GameEngine();
		req.getRequestDispatcher("/_view/newItem.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("NewItemServlet doPost");
		
		// Navigating to new page by pressing button
		if (req.getParameter("return") != null)
		{
			System.out.println("return has been clicked");
			//req.getRequestDispatcher("/_view/login.jsp").forward(req, resp); //Go to this page
			resp.sendRedirect(req.getContextPath() + "/index");
		}
		
		String errorMessage = null;
		
		try {
			String name = (req.getParameter("name").toString());
			String description = (req.getParameter("description").toString());
			String locationX = (req.getParameter("x").toString());
			String locationY = (req.getParameter("y").toString());
			String locationZ = (req.getParameter("z").toString());
			
			if (!(name.equals("")) && !(description.equals("")) && !(locationX.equals("")) && !(locationY.equals("")) && !(locationZ.equals(""))) {
				//System.out.println("Success!");
				/*
				req.getSession().setAttribute("newItemName", name);
				req.getSession().setAttribute("newItemDescript", description);
				req.getSession().setAttribute("newItemX", locationX);
				req.getSession().setAttribute("newItemY", locationY);
				req.getSession().setAttribute("newItemZ", locationZ);
				req.getSession().setAttribute("newItem", true);
				System.out.println("NewItemServlet >> newItem == " + req.getSession().getAttribute("newItem"));
				req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
				*/
				
				Location location = new Location(Integer.parseInt(locationX), Integer.parseInt(locationY), Integer.parseInt(locationZ));
				Item itemToAdd = new Item(name, description, location, 0);
				// Add item to players database
				GameEngine gameEngine = new GameEngine();
				String username = (String) req.getSession().getAttribute("username");
				Integer account_id = gameEngine.getAccountID(username);
				Account account = (Account) req.getSession().getAttribute("account");
				System.out.println(username);
				System.out.println(account_id);
				gameEngine.insertNewItemIntoDatabase(account, account_id, itemToAdd, 1);
				engine.updateItemList(account, account_id);
			}
			else
			{
				errorMessage = "Please do not leave anything blank!";
			}
			
			
		} catch (Exception e) {
			errorMessage = e.toString();
		}
		
		req.setAttribute("errorMessage", errorMessage); //In case of error message
		
		// If there is an error message, show it and stay on the same page.
		if (errorMessage != null){
			System.out.println("Error: " + errorMessage);
			req.getRequestDispatcher("/_view/newItem.jsp").forward(req, resp); //Go to this page
		}
		else
		{
			req.getRequestDispatcher("/_view/index.jsp").forward(req, resp); //Go to this page
		}
	}
}
