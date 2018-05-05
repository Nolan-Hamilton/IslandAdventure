package edu.ycp.cs320.IslandAdventure.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.IslandAdventure.controller.*;
import edu.ycp.cs320.IslandAdventure.model.*;


public class NewEnemyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Fields needed
	GameEngine engine = null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("New Enemy Servlet: doGet");
		engine = new GameEngine();
		req.getRequestDispatcher("/_view/newEnemy.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("NewEnemyServlet doPost");
		
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
			String health = (req.getParameter("health").toString());
			String damage = (req.getParameter("damage").toString());
			String locationX = (req.getParameter("x").toString());
			String locationY = (req.getParameter("y").toString());
			String locationZ = (req.getParameter("z").toString());
			
			if (!(name.equals("")) && !(description.equals("")) && !(locationX.equals("")) && !(locationY.equals("")) && !(locationZ.equals(""))) {
				//System.out.println("Success!");
				Location location = new Location(Integer.parseInt(locationX), Integer.parseInt(locationY), Integer.parseInt(locationZ));
				Enemy enemyToAdd = new Enemy(name, description, Integer.parseInt(health), location, Integer.parseInt(damage));
				// Add enemy to players database
				String username = (String) req.getSession().getAttribute("username");
				Integer account_id = engine.getAccountID(username);
				Account account = (Account) req.getSession().getAttribute("account");
				engine.insertEnemyIntoDatabase(account, account_id, enemyToAdd);
				engine.updateEnemiesList(account_id, account);
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
			req.getRequestDispatcher("/_view/newEnemy.jsp").forward(req, resp); //Go to this page
		}
		else
		{
			//req.getRequestDispatcher("/_view/index.jsp").forward(req, resp); //Go to this page
			resp.sendRedirect(req.getContextPath() + "/index"); //Go back to index page should be doGet()
		}
	}
}
