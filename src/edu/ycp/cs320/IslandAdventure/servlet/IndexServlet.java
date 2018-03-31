
package edu.ycp.cs320.IslandAdventure.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.IslandAdventure.controller.ActionController;
import edu.ycp.cs320.IslandAdventure.controller.InventoryController;
import edu.ycp.cs320.IslandAdventure.model.*;
import edu.ycp.cs320.IslandAdventure.controller.PlayerController;
import edu.ycp.cs320.IslandAdventure.controller.*;
import edu.ycp.cs320.IslandAdventure.persist.*;

public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// Here are the model and controllers so that they do not get recreated with each POST
	private FakeDatabase fakeData = null;
	private Account account = null;
	Player player = null;
	PlayerController playerController = null;
	Inventory inventoryModel = null;
	Location locationModel = null;
	InventoryController inventoryController = null;
	LocationController locationController = null;
	ActionController controller = null;
	String action = "";
	String response = "";
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		
		System.out.println("Index Servlet: doGet");

		req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		
		System.out.println("Index Servlet: doPost");

		// Create a fake database if it is not already created
		if (fakeData == null || account == null){
			fakeData = new FakeDatabase();
			account = new Account(null, null, null, null);
			account.setUsername("Temp User");
			account.setPassword("Temp Pass");
			//player = new Player(0, 0, 0, 0, null, null, null);
			playerController = new PlayerController();
			player = playerController.createNewPlayer();
			Location[][][] map = new Location[25][25][25];
			account.setPlayer(player);
			account.setMap(map);
			account.initialize();
			fakeData.getAccountList().add(account); //Add account to arraylist of Accounts
			controller = new ActionController(player, account);
			
			inventoryController = new InventoryController(player.getInventory());
			locationController = new LocationController(player.getLocation());
			System.out.println(account.getUsername() + "'s account is now created");
			System.out.println("Player X location: " + account.getPlayer().getLocation().getX());
		}
		//inventoryController.setModel(inventoryModel);
		//locationController.setModel(locationModel);
		
		/*// This keeps getting recreated
		// create Inventory model - model does not persist between requests
		// must recreate it each time a Post comes in
		String action = "";
		PlayerController playerController = new PlayerController();
		Player player = playerController.createNewPlayer();

		Inventory inventoryModel = new Inventory(player.getInventory().getInventoryMap());
		InventoryController inventoryController = new InventoryController(player.getInventory());
		ActionController controller = new ActionController(player);

		// assign model reference to controller so that controller can access
		// model
		inventoryController.setModel(inventoryModel);
		
		*/

		// Initialize variables in the Inventory model		
		req.setAttribute("inventory", inventoryModel);
		
		req.setAttribute("action", req.getParameter("action"));
		
		action = req.getParameter("action");
		

		//req.setAttribute("lastAction", action);
		
		response = controller.interpretAction(action);
		//System.out.println("Player X position: " + player.getLocation().getX());
		
		req.setAttribute("response", response);
		req.setAttribute("score", player.getScore());
		req.setAttribute("health", player.getHealth());
		req.setAttribute("stamina", player.getStamina());
		req.setAttribute("time", player.getTime());
		
		req.setAttribute("woodCount", player.getInventory().getWoodCount());
		
		req.setAttribute("locationX", player.getLocation().getX());
		req.setAttribute("locationY", player.getLocation().getY());
		req.setAttribute("locationZ", player.getLocation().getZ());
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
	}
}
