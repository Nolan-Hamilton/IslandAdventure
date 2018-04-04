package edu.ycp.cs320.IslandAdventure.controller;

import edu.ycp.cs320.IslandAdventure.model.Enemy;
import edu.ycp.cs320.IslandAdventure.model.Location;
import edu.ycp.cs320.IslandAdventure.model.Player;
import edu.ycp.cs320.IslandAdventure.persist.DatabaseProvider;
import edu.ycp.cs320.IslandAdventure.persist.DerbyDatabase;
import edu.ycp.cs320.IslandAdventure.persist.FakeDatabase;
import edu.ycp.cs320.IslandAdventure.persist.IDatabase;

import java.util.ArrayList;

import edu.ycp.cs320.IslandAdventure.model.*;

public class ActionController 
{
	private Player player;
	
	private InventoryController inventoryController;
	
	private Account account;
	
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
		String response = "";
		response += ">> " + action + "<br><br>"; // Add action command to response
		if (action.equals("Chop Wood") || action.equals("chop wood")) 
		{
			inventoryController.changeWoodAmount(10);
			player.changeTime(1);	// Takes 1 hour to chop wood
			player.modifyStamina(-15);	// Stamina is reduced by 15 when chopping wood
		}
		if (action.equals("Fish") || action.equals("fish")) 
		{
			inventoryController.changeFishAmount(10);
			player.changeTime(1);	// Takes 1 hour to chop wood
			player.modifyStamina(-15);	// Stamina is reduced by 15 when chopping wood
		}
		if (action.equals("Drop Wood") || action.equals("drop wood")){
			inventoryController.changeWoodAmount(-10);
			// Add item to location
		}
		if (action.equals("Move East") || action.equals("move east")) 
		{
			player.getLocation().setX(player.getLocation().getX()+1);
		}
		if (action.equals("Move West") || action.equals("move west")) 
		{
			player.getLocation().setX(player.getLocation().getX()-1);
		}
		if (action.equals("Move North") || action.equals("move north")) 
		{
			player.getLocation().setY(player.getLocation().getY()+1);
			
			EventController eventController = new EventController();
			Enemy enemy = eventController.createEnemy(player);
			FightController fightController = new FightController();
			response += fightController.Fight(player, enemy);
		}
		
		if (action.equals("Move South") || action.equals("move south")) 
		{
			player.getLocation().setY(player.getLocation().getY()-1);
		}

		// Displays the description of the current room as well as any items located in that room
		if (action.equals("Look") || action.equals("look")) {
			Location location = player.getLocation();
			// Display description
			response += account.getLocationByXYZ(location.getX(), location.getY(), location.getZ()).getDescription() + "<br>";
			// Display list of items.
			ArrayList<GameObject> objects = account.getObjectsByXYZ(location.getX(), location.getY(), location.getZ());
			if (objects.size() != 0) {
				response += "The following item(s) are also present: <br>";
				for (GameObject object : objects) {
					response += object.getName() + "<br>";
				}
			}
			response += "<br>";
		}
		response += " what next?";
		return response;
	}
}
