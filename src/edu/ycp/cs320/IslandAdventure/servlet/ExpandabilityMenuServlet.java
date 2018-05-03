package edu.ycp.cs320.IslandAdventure.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.IslandAdventure.controller.GameEngine;

public class ExpandabilityMenuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Fields needed
	GameEngine engine = null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Expandability Servlet: doGet");
		engine = new GameEngine();
		req.getRequestDispatcher("/_view/expandabilityMenu.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Login Servlet: doPost");
		
		// Navigating to new page by pressing button
		if (req.getParameter("newEnemy") != null) 
		{
			System.out.println("newEnemy has been clicked!");
			resp.sendRedirect(req.getContextPath() + "/newEnemy");
		}
		else if (req.getParameter("newArmor") != null)
		{
			System.out.println("newArmor has been clicked");
			resp.sendRedirect(req.getContextPath() + "/newArmor");
		}
		else if (req.getParameter("newWeapon") != null)
		{
			System.out.println("newWeapon has been clicked");
			resp.sendRedirect(req.getContextPath() + "/newWeapon");
		}
		else if (req.getParameter("return") != null)
		{
			System.out.println("return has been clicked");
			resp.sendRedirect(req.getContextPath() + "/index");
		}
	}
}
