
package edu.ycp.cs320.IslandAdventure.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.IslandAdventure.controller.ActionController;
import edu.ycp.cs320.IslandAdventure.controller.InventoryController;
import edu.ycp.cs320.IslandAdventure.model.*;

public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("Index Servlet: doGet");

		req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("Index Servlet: doPost");

		//Account account = (Account) req.getAttribute("account");
		//System.out.println("Servlet accessing: " + req.getParameter("account"));

		// create Inventory model - model does not persist between requests
		// must recreate it each time a Post comes in
		String action = "";
		Inventory inventoryModel = new Inventory(null);
		InventoryController inventoryController = new InventoryController(null);
		ActionController controller = new ActionController();

		// assign model reference to controller so that controller can access
		// model
		inventoryController.setModel(inventoryModel);

		// Initialize variables in the Inventory model
		req.setAttribute("inventory", inventoryModel);

		req.setAttribute("action", req.getParameter("action"));

		action = req.getParameter("action");

		req.setAttribute("lastAction", action);

		controller.interpretAction(action);

		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
	}

	/*
	 * public String getAction() { return action; }
	 */
}
