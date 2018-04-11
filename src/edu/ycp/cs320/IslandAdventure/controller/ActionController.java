package edu.ycp.cs320.IslandAdventure.controller;

import edu.ycp.cs320.IslandAdventure.persist.DatabaseProvider;
import edu.ycp.cs320.IslandAdventure.persist.DerbyDatabase;
import edu.ycp.cs320.IslandAdventure.persist.FakeDatabase;
import edu.ycp.cs320.IslandAdventure.persist.IDatabase;

import java.util.ArrayList;
import java.util.Iterator;
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
	
	// Database implementation is borrowed from Library Example Project By Prof. Hake
	private IDatabase db    = null;
	
	public ActionController(Player player, Account account) 
	{
		this.player = player;
		inventoryController = new InventoryController(player.getInventory());
		this.account = account;
		
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
				location.setX(player.getLocation().getX()+1);
			}
			else if (action.contains("West") || action.contains("west")) 
			{
				location.setX(player.getLocation().getX()-1);
			}
			else if (action.contains("North") || action.contains("north")) 
			{
				location.setY(player.getLocation().getY()+1);
			}
			else if (action.contains("South") || action.contains("south")) 
			{
				location.setY(player.getLocation().getY()-1);
			}
		}
		
		else if (action.equals("Chop Wood") || action.equals("chop wood")) 
		{
			inventoryController.changeWoodAmount(10);
			player.changeTime(1);	// Takes 1 hour to chop wood
			player.modifyStamina(-15);	// Stamina is reduced by 15 when chopping wood
		}
		
		else if (action.equals("Fish") || action.equals("fish")) 
		{
			inventoryController.changeFishAmount(10);
			player.changeTime(1);	// Takes 1 hour to chop wood
			player.modifyStamina(-15);	// Stamina is reduced by 15 when chopping wood
		}
		
		else if (action.equals("Drop Wood") || action.equals("drop wood")){
			inventoryController.changeWoodAmount(-10);
			// Add item to location
		}
		
		else if(action.contains("sleep"))
		{
			if (player.getLocation().getX() == 10 && player.getLocation().getY() == 10) //Player is home
			{
				player.setStamina(100);
				player.setHealth(100);
				player.changeTime(8);	//Player sleeps 8 hours
			}
			else
			{
				player.setStamina(100);
				player.changeTime(8);	//Player sleeps 8 hours
				Enemy enemy = eventController.createEnemy(player);
				response += fightController.Fight(player, enemy);
			}
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
		}
		
		playerController.checkPlayerState(player);	//Checks if player health and stamina > 0 
		response += " what next?";
		return response;
	}
}
