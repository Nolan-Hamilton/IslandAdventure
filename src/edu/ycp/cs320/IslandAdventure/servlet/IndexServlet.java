
package edu.ycp.cs320.IslandAdventure.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ycp.cs320.IslandAdventure.model.*;
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
	GameEngine engine = null;
	int account_id = -1;
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException 
	{
		
		System.out.println("Index Servlet: doGet");
		
		if (req.getSession().getAttribute("username") != null) {
			
			fakeData = new FakeDatabase();
			account = new Account(null, null, null);
			account.setUsername(req.getSession().getAttribute("username").toString());
			account.setPassword(req.getSession().getAttribute("password").toString());
			//player = new Player(0, 0, 0, 0, null, null, null);
			playerController = new PlayerController();
			player = playerController.createNewPlayer();
			account.setPlayer(player);
			account.initialize();
			fakeData.getAccountList().add(account); //Add account to arraylist of Accounts
			controller = new ActionController(player, account);
			engine = new GameEngine();
			account_id = engine.getAccountID(account.getUsername());
			
			/*
			// Load the player data from database (This is how to make player persist
			Player playerLoader = engine.loadPlayer(account_id);
			player.setScore(playerLoader.getScore());
			player.setHealth(playerLoader.getHealth());
			player.setStamina(playerLoader.getStamina());
			player.setTime(playerLoader.getTime());
			player.setX(playerLoader.getLocation().getX());
			player.setY(playerLoader.getLocation().getY());
			player.setZ(playerLoader.getLocation().getZ());
			//account.setPlayer(player);
			*/
			 
			
			inventoryController = new InventoryController(player.getInventory());
			locationController = new LocationController(player.getLocation());
			System.out.println(account.getUsername() + "'s account is now created");
			req.setAttribute("user", account.getUsername());
		}
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
			account = new Account(null, null, null);
			account.setUsername("Temp User");
			account.setPassword("Temp Pass");
			//player = new Player(0, 0, 0, 0, null, null, null);
			playerController = new PlayerController();
			player = playerController.createNewPlayer();
			Location[][][] map = new Location[25][25][25];
			account.setPlayer(player);
			account.initialize();
			fakeData.getAccountList().add(account); //Add account to arraylist of Accounts
			controller = new ActionController(player, account);
			
			inventoryController = new InventoryController(player.getInventory());
			locationController = new LocationController(player.getLocation());
			System.out.println(account.getUsername() + "'s account is now created");
			System.out.println("Player X location: " + account.getPlayer().getLocation().getX());
		}

		// Initialize variables in the Inventory model		
		req.setAttribute("inventory", player.getInventory());
		
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
		
		if (player.getArmor() != null)
		{
			req.setAttribute("armor", player.getArmor().getName());
		}
		if (player.getWeapon() != null)
		{
			req.setAttribute("weapon", player.getWeapon().getName());
		}
		
		req.setAttribute("map", player.getInventory().getInventoryMap());
		req.setAttribute("", player.getInventory().getInventoryMap());
		
		req.setAttribute("locationX", player.getLocation().getX());
		req.setAttribute("locationY", player.getLocation().getY());
		req.setAttribute("locationZ", player.getLocation().getZ());
		
		req.setAttribute("user", account.getUsername());
		
		// Update the Database with changes
		
		engine.updatePlayerInDatabase(account_id, player);
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
	}
}
