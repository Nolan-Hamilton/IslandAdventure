package edu.ycp.cs320.IslandAdventure.controller;

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
	
	public void interpretAction(String action)
	{
		if (action.equals("Chop Wood") || action.equals("chop wood")) 
		{
			inventoryController.changeWoodAmount(10);
			player.changeTime(1);	// Takes 1 hour to chop wood
			player.modifyStamina(-15);	// Stamina is reduced by 15 when chopping wood
		}
		if (action.equals("Move East") || action.equals("move east")) 
		{
			Location location = player.getLocation();
				System.out.println("Location X: " + location.getX());
			int x = location.getX() + 1;
				System.out.println("X value: " + x);
			//player.getLocation().setX(x);
			location.setX(x);
			player.changeLocation(location);
			System.out.println("Location X is now: " + player.getLocation().getX());
		}
		if (action.equals("Move West") || action.equals("move east")) 
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
		}
		if (action.equals("Move South") || action.equals("move south")) 
		{
			Location location = player.getLocation();
//			int y = location.getY() - 1;
			player.getLocation().setY(location.getY()-1);
		}
	}
}
