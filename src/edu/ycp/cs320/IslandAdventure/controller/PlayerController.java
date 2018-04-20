package edu.ycp.cs320.IslandAdventure.controller;

import edu.ycp.cs320.IslandAdventure.model.Inventory;
import edu.ycp.cs320.IslandAdventure.model.Location;
import edu.ycp.cs320.IslandAdventure.model.Player;
import edu.ycp.cs320.IslandAdventure.model.Skills;

public class PlayerController 
{
	InventoryController inventoryController = new InventoryController(null, null, null);
	LocationController locationController = new LocationController(null);
	SkillsController skillsController = new SkillsController();
	
	public PlayerController() 
	{
	}
	
	public Player createNewPlayer()
	{
		Inventory inventory = inventoryController.createNewInventory();
		Location location = locationController.setStartingLocation();
		Skills skills = skillsController.createNewSkills();
		Player player = new Player(0, 100, 100, 6, inventory, location, skills, null, null);
		return player;
	}
	
	public void checkPlayerState(Player player)
	{
		if (player.getStamina() <= 0)	//Player starts losing health until they sleep
		{
			player.modifyHealth(-10);
		}
		if (player.getHealth() <= 0)
		{
			System.out.println("Player died");
			player.getInventory().getInventoryMap().clear();	//Clears items from inventory
		}
	}
}
