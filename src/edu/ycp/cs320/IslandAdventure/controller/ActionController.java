package edu.ycp.cs320.IslandAdventure.controller;

import edu.ycp.cs320.IslandAdventure.persist.DatabaseProvider;
import edu.ycp.cs320.IslandAdventure.persist.DerbyDatabase;
import edu.ycp.cs320.IslandAdventure.persist.FakeDatabase;
import edu.ycp.cs320.IslandAdventure.persist.IDatabase;
import edu.ycp.cs320.IslandAdventure.MapView.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import edu.ycp.cs320.IslandAdventure.model.*;

public class ActionController 
{
	private Player player;
	
	private InventoryController inventoryController;
	
	private Account account;
	
	private EventController eventController = new EventController();
	
	private FightController fightController = new FightController();
	
	private PlayerController playerController = new PlayerController();
	
	private GameEngine gameEngine = new GameEngine();
	
	// Database implementation is borrowed from Library Example Project By Prof. Hake
	private IDatabase db    = null;
	
	public ActionController(Player player, Account account) 
	{
		this.player = player;
		this.account = account;
		inventoryController = new InventoryController(player.getInventory(), account, gameEngine.getAccountID(account.getUsername()));
		// creating DB instance here
		//DatabaseProvider.setInstance(new DerbyDatabase());
		//db = DatabaseProvider.getInstance();
	}
	
	public String interpretAction(String action)
	{
		Location location = player.getLocation();
		String response = "";
		response += ">> " + action + "<br><br>"; // Add action command to response
		
		if (action.contains("move") || action.contains("Move"))
		{
			if (action.contains("East") || action.contains("east")) 
			{
				if (account.getRoomByXYZ(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()).getGoEast() == true){
					location.setX(player.getLocation().getX()+1);
				}else{
					response += "You cannot go that way! <br><br>";
				}
			}
			else if (action.contains("West") || action.contains("west")) 
			{
				if (account.getRoomByXYZ(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()).getGoWest() == true){
					location.setX(player.getLocation().getX()-1);
				}else{
					response += "You cannot go that way! <br><br>";
				}
			}
			else if (action.contains("North") || action.contains("north")) 
			{
				
				if (account.getRoomByXYZ(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()).getGoSouth() == true){
					location.setY(player.getLocation().getY()+1);
				}else{
					response += "You cannot go that way! <br><br>";
				}
			}
			else if (action.contains("South") || action.contains("south")) 
			{
				if (account.getRoomByXYZ(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()).getGoNorth() == true){
					location.setY(player.getLocation().getY()-1);
				}else{
					response += "You cannot go that way! <br><br>";
				}
			}
			// Print description and items
			// Display description
			response += account.getRoomByXYZ(location.getX(), location.getY(), location.getZ()).getDescription() + "<br>";
			// Display Items
			ArrayList<Item> items = account.getItemsByXYZ(location.getX(), location.getY(), location.getZ());
			if (items.size() != 0) 
			{
				response += "The following item(s) are also present: <br>";
				for (Item item : items) 
				{
					response += item.getName() + "<br>";
				}
			}
			response += "<br>";
		}
		
		else if (action.equals("Chop Wood") || action.equals("chop wood")) 
		{
			inventoryController.changeWoodAmount(10);
			player.changeTime(1);	// Takes 1 hour to chop wood
			player.getSkills().addWoodCuttingXP(5);	//Gains 5 WC XP
			player.modifyStamina(-15);	// Stamina is reduced by 15 when chopping wood
		}
		
		else if (action.equals("Fish") || action.equals("fish")) 
		{
			inventoryController.changeFishAmount(10);
			player.changeTime(1);	// Takes 1 hour to chop wood
			player.getSkills().addFishingXP(5);	//Gains 5 Fishing XP
			player.modifyStamina(-15);	// Stamina is reduced by 15 when chopping wood
		}
		
		else if (action.equals("Drop Wood") || action.equals("drop wood")){
			inventoryController.changeWoodAmount(-10);
			// Add item to location
		}
		
		else if(action.contains("sleep"))
		{
			Random rand = new Random();
			int  n;
			if (player.getLocation().getX() == 10 && player.getLocation().getY() == 10) //Player is home
			{
				n = rand.nextInt(20) + 1;	//random # from 1-20
			}
			else
			{
				n = rand.nextInt(50) + 1;	//random # from 1-50
			}
			
			response += eventController.sleepEvent(player, n) + "<br>";
		}
		
		//Display Map
		else if (action.equals("display map") || action.equals("Display Map")){
			MapFrame map = new MapFrame();
			map.displayMap(account);
		}

		// Displays the description of the current room as well as any items located in that room
		else if (action.equals("Look") || action.equals("look")) 
		{
			// Display description
			response += account.getRoomByXYZ(location.getX(), location.getY(), location.getZ()).getDescription() + "<br>";
			// Display list of items.
			ArrayList<Item> items = account.getItemsByXYZ(location.getX(), location.getY(), location.getZ());
			if (items.size() != 0) 
			{
				response += "The following item(s) are also present: <br>";
				for (Item item : items) 
				{
					response += item.getName() + "<br>";
				}
			}
			response += "<br>";
		}
		
		else if (action.contains("drop") || action.contains("Drop")){
			int chop = action.indexOf(" ") + 1;			//This finds the index of the first letter of the item
			String itemName = action.substring(chop).toLowerCase();	//returns the name of the item and throws out the 'take '
			boolean found = false;
			for (Item key: player.getInventory().getInventoryMap().keySet()){
				if (itemName.equals(key.getName().toLowerCase())){
					Item item = new Item(key.getName(), key.getDescription(), new Location(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()), key.getUses());
					account.getItemList().add(item);
					player.getInventory().addItem(key, -1);
					int account_id = gameEngine.getAccountID(account.getUsername());
					gameEngine.moveItemInventory(account_id, 0, item.getName());
					found = true;
					response += itemName + " has been removed from your inventory. <br>";
					if (player.getInventory().getInventoryMap().get(key) == 0){
						player.getInventory().getInventoryMap().remove(key); // Remove from list otherwise Hammer = 0, Hammer = 1
					}
					break;
				}
			}
			if (found == false){
				response += "This item was not found in your inventory. <br>";
			}
		}
		
		else if (action.contains("take") || action.contains("Take")){
			ArrayList<Item> items = account.getItemsByXYZ(location.getX(), location.getY(), location.getZ());
			int chop = action.indexOf(" ") + 1;			//This finds the index of the first letter of the item
			String itemName = action.substring(chop).toLowerCase();	//returns the name of the item and throws out the 'take '
			if (items.size() != 0) 
			{
				boolean stringFound = false;
				for (Item item : items) 
				{
					if (item.getName().toLowerCase().equals(itemName)){
						response += "You acquired the following item: <br>" + item.getName() + "<br>";
						player.getInventory().addItem(item, 1);
						int account_id = gameEngine.getAccountID(account.getUsername());
						gameEngine.moveItemInventory(account_id, 1, item.getName());
						//Remove the Item from the objectList //////////////////////////////////////////
						int index = account.getObjectIndexByNameAndXYZ(item.getName(), item.getX(), item.getY(), item.getZ()); //This finds index of item in ObjectList
						account.getItemList().remove(index);			//This removes item from ObjectList using index.
						///////////////////////////////////////////////////////////////////////////////
						stringFound = true;
						break;	// Only one item is taken at a time
					}
				}
				if (stringFound == false){		// This is if item is misspelled or not here
					response += "This item could not be found. <br> ";
				}
			}else{
				response += "There is nothing here to take. <br>";
			}
		}
		/*//This is no longer used but is here for reference or to revert back to
		else if (action.equals("pick up")) // Picks up all items in the room
		{
			ArrayList<Item> items = account.getItemsByXYZ(location.getX(), location.getY(), location.getZ());
			if (items.size() != 0) 
			{
				response += "You acquired the following item(s): <br>";
				for (Item item : items) 
				{
					response += item.getName() + "<br>";
					player.getInventory().addItem(item, 1);
				}
			}
		}
		*/
		else if (action.contains("equip"))
		{
			Set<Item> keyset = player.getInventory().getInventoryMap().keySet();
			Iterator<Item> iterator = keyset.iterator();
			
			while(iterator.hasNext())
			{
				Item item = (Item) iterator.next();
				if (action.contains(item.getName()))
				{
					player.equipWeapon(item);
					response += "You equipped a " + item.getName() + "!";
				}
			}
		}else{
			// If the command is not enderstandable
			response += "I do not understand what you are saying... <br>";
		}
		
		playerController.checkPlayerState(player);	//Checks if player health and stamina > 0 
		response += "<br>";
		return response;
	}
}
