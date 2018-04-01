package edu.ycp.cs320.IslandAdventure.controller;

import edu.ycp.cs320.IslandAdventure.model.Enemy;
import edu.ycp.cs320.IslandAdventure.model.Location;
import edu.ycp.cs320.IslandAdventure.model.Player;

public class ActionController 
{
	private Player player;
	
	private InventoryController inventoryController;
	
	public ActionController(Player player) 
	{
		this.player = player;
		inventoryController = new InventoryController(player.getInventory());
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
		if (action.equals("Drop Wood") || action.equals("drop wood")){
			inventoryController.changeWoodAmount(-10);
		}
		if (action.equals("Move East") || action.equals("move east")) 
		{
			Location location = player.getLocation();
			int x = location.getX() + 1;
			player.getLocation().setX(x);
		}
		if (action.equals("Move West") || action.equals("move west")) 
		{
			Location location = player.getLocation();
			int x = location.getX() - 1;
			player.getLocation().setX(x);
		}
		if (action.equals("Move North") || action.equals("move north")) 
		{
			Location location = player.getLocation();
			int y = location.getY() + 1;
			player.getLocation().setY(y);
			EventController eventController = new EventController();
			Enemy enemy = eventController.createEnemy(player);
			FightController fightController = new FightController();
			response += fightController.Fight(player, enemy);
		}
		if (action.equals("Move South") || action.equals("move south")) 
		{
			Location location = player.getLocation();
//			int y = location.getY() - 1;
			player.getLocation().setY(location.getY()-1);
		}


		response += " what next?";
		return response;
	}
}
