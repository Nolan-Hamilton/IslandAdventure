
package edu.ycp.cs320.IslandAdventure.servlet;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			playerController = new PlayerController();
			player = playerController.createNewPlayer();
			account.setPlayer(player);
			//account.initialize();
			fakeData.getAccountList().add(account); //Add account to arraylist of Accounts
			controller = new ActionController(player, account);
			engine = new GameEngine();
			account_id = engine.getAccountID(account.getUsername());
			
			if (req.getSession().getAttribute("existingPlayer").equals(true)){
				System.out.println("IndexServlet >> existingPlayer == true");
				
				// Load the player data from database (This is how to make player persist
				// Only execute this code player already exists.
				Player playerLoader = engine.loadPlayer(account_id);
				player.setScore(playerLoader.getScore());
				player.setHealth(playerLoader.getHealth());
				player.setStamina(playerLoader.getStamina());
				player.setTime(playerLoader.getTime());
				player.setX(playerLoader.getLocation().getX());
				player.setY(playerLoader.getLocation().getY());
				player.setZ(playerLoader.getLocation().getZ());
				player.getSkills().setWoodCuttingXP(playerLoader.getSkills().getWoodCuttingXP());
				player.getSkills().setFishingXP(playerLoader.getSkills().getFishingXP());
				player.getSkills().setCombatXP(playerLoader.getSkills().getCombatXP());
				player.getSkills().setCraftingXP(playerLoader.getSkills().getCraftingXP());
				//account.setPlayer(player);
				/*
				ArrayList<Room> rooms = engine.loadMap(account_id);
				for (Room room : rooms){
					System.out.println(room.getDescription());
				}
				*/
				
				account.setRoomsList(engine.loadMap(account_id));
			
				//System.out.println(account.getRoomByXYZ(11, 11, 0).getVisible());
				//System.out.println(account.getRooms().get(350).getVisible());
			}else{
				Iterator<Item> iterator = account.getItemList().iterator();
				while(iterator.hasNext())	// Puts initial items into database
				{
					Item item = iterator.next();

					if (item instanceof Weapon) {
						engine.insertNewItemIntoDatabase(account, account_id, item, 1, ((Weapon) item).getDamage());
					}
					else if (item instanceof Armor){
						engine.insertNewItemIntoDatabase(account, account_id, item, 1, -((Armor) item).getArmorAmount());
					}
					else
					{
						engine.insertNewItemIntoDatabase(account, account_id, item, 1, 0);
					}
				}
				account.getItemList().clear(); // Clears initial items after added to database so there aren't duplicates
				System.out.println("IndexServlet >> existingPlayer == false");
				
				Iterator<Enemy> iterator2 = account.getEnemyList().iterator();
				while(iterator2.hasNext())	// Puts initial items into database
				{
					Enemy enemy = iterator2.next();
					engine.insertEnemyIntoDatabase(account, account_id, enemy);
				}
				account.getEnemyList().clear(); // Clears initial items after added to database so there aren't duplicates
				
				System.out.println("IndexServlet >> existingPlayer == false");
			}
			
			engine.updateItemList(account, account_id);	// Loads items to account
			engine.updateEnemiesList(account_id, account); // Loads enemies to account
			account.setRoomsList(engine.loadMap(account_id));
			
			inventoryController = new InventoryController(player.getInventory(), account, account_id);
			locationController = new LocationController(player.getLocation());
			System.out.println(account.getUsername() + "'s account is now created");
			req.setAttribute("user", account.getUsername());
		}
		response = "Lets go on an adventure to find treasure!<br><br>";
		doPost(req, resp);	// displays initial data so not blank on startup
		//req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
		
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
			//account.initialize();
			fakeData.getAccountList().add(account); //Add account to arraylist of Accounts
			controller = new ActionController(player, account);
			
			inventoryController = new InventoryController(player.getInventory(), account, account_id);
			locationController = new LocationController(player.getLocation());
			System.out.println(account.getUsername() + "'s account is now created");
			System.out.println("Player X location: " + account.getPlayer().getLocation().getX());
		}

		req.getSession().setAttribute("account", account);
		
		if (req.getParameter("expandabilityMenu") != null) 
		{
			System.out.println("expandabilityMenu has been clicked!");
			resp.sendRedirect(req.getContextPath() + "/expandabilityMenu");
		}

		/*
		// If a new item has been created execute this block
		if (req.getSession().getAttribute("newItem").equals(true)){
			String name = req.getSession().getAttribute("newItemName").toString();
			String description = req.getSession().getAttribute("newItemDescript").toString();
			//Location location = new Location((int) req.getSession().getAttribute("newItemX"), (int) req.getSession().getAttribute("newItemY"), (int) req.getSession().getAttribute("newItemZ"));
			Item itemToAdd = new Item(name, description, player.getLocation(), 0);
			engine.insertNewItemIntoDatabase(account, account_id, itemToAdd, 1);
			req.getSession().setAttribute("newItem", false); // Reset to false so this it only executes this black after creating new item
			System.out.println("IndexServlet >> newItem == " + req.getSession().getAttribute("newItem"));
			
		}
		
		
		// Initialize variables in the Inventory model		
		req.setAttribute("inventory", player.getInventory());
		*/
		req.setAttribute("action", req.getParameter("action"));
		
		action = req.getParameter("action");
		
		// Check for directional button movement
		if (req.getParameter("north") != null){
			action = "move north";
		}else if (req.getParameter("east") != null){
			action = "move east";
		}else if (req.getParameter("south") != null){
			action = "move south";
		}else if (req.getParameter("west") != null){
			action = "move west";
		}

		//req.setAttribute("lastAction", action);
		if (action == null){
			action = "";	// This avoids a nullPointerException on initial doPost()
		}
		response += controller.interpretAction(action);
		//System.out.println("Player X position: " + player.getLocation().getX());
		
		// Initialize variables in the Inventory model		
		req.setAttribute("inventory", player.getInventory());
		
		req.setAttribute("response", response);
		req.setAttribute("score", player.getScore());
		req.setAttribute("health", player.getHealth());
		req.setAttribute("stamina", player.getStamina());
		req.setAttribute("time", player.getTime());
		
		req.setAttribute("woodCuttingXP", player.getSkills().getWoodCuttingXP());
		req.setAttribute("fishingXP", player.getSkills().getFishingXP());
		req.setAttribute("combatXP", player.getSkills().getCombatXP());
		req.setAttribute("craftingXP", player.getSkills().getCraftingXP());
		
		if (player.getArmor() != null)
		{
			req.setAttribute("armor", player.getArmor().getName());
			req.setAttribute("armorAmount", player.getArmor().getArmorAmount());
		}
		if (player.getWeapon() != null)
		{
			req.setAttribute("weapon", player.getWeapon().getName());
			req.setAttribute("weaponDamage", player.getWeapon().getDamage());
		}
		
		
		
		req.setAttribute("locationX", player.getLocation().getX());
		req.setAttribute("locationY", player.getLocation().getY());
		req.setAttribute("locationZ", player.getLocation().getZ());
		
		req.setAttribute("user", account.getUsername());
		
		// Set visible to true for current room since player is here
		System.out.println("IndexServlet >> Player location is : " + player.getLocation().getX() + "," + player.getLocation().getY());
		account.getRoomByXYZ(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()).setVisible(true);
		
		// Update the Database with changes (This does not occur if 'Just Play!' is clicked)
		if (req.getSession().getAttribute("username") != null) {
			engine.updatePlayerInDatabase(account_id, player);
			engine.updateMapInDatabase(account_id, account);
			/*
			if (action.toLowerCase().contains("take") || action.toLowerCase().contains("chop") || action.toLowerCase().contains("fish") || 
					action.toLowerCase().contains("drop") || action.toLowerCase().contains("craft")){
				player.getInventory().getInventoryMap().clear();
				engine.updateItemList(account, account_id);	// This reloads items to account to keep inventory from stacking (wood = 2, wood = 1).
			}
			*/
		}
		req.setAttribute("action", ""); // Empty the input box for next command
		
		req.setAttribute("map", null); // This resets the inventory to avoid repeated keys
		req.setAttribute("map", player.getInventory().getInventoryMap());
		req.setAttribute("", player.getInventory().getInventoryMap());
		
		// Forward to view to render the result HTML document
		req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
	}
}
