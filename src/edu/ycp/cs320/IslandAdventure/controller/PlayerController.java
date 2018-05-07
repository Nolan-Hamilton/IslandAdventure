package edu.ycp.cs320.IslandAdventure.controller;

import edu.ycp.cs320.IslandAdventure.model.Account;
import edu.ycp.cs320.IslandAdventure.model.Inventory;
import edu.ycp.cs320.IslandAdventure.model.Location;
import edu.ycp.cs320.IslandAdventure.model.Player;
import edu.ycp.cs320.IslandAdventure.model.Skills;

public class PlayerController 
{
	InventoryController inventoryController = new InventoryController(null, null, null);
	LocationController locationController = new LocationController(null);
	SkillsController skillsController = new SkillsController();
	GameEngine gameEngine = new GameEngine();
	
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
	
	public String checkPlayerState(Account account)
	{
		String status = "";
		Player player = account.getPlayer();
		if (player.getStamina() <= 0)	//Player starts losing health until they sleep
		{
			player.modifyHealth(-10);
			status += "You are exhausted. You need to sleep or you will take damage! <br>";
		}
		if (player.getHealth() <= 0)
		{
			System.out.println("Player died");
			//Clears materials from inventory
			player.getInventory().getInventoryMap().clear();
			gameEngine.updateItemAmount(gameEngine.getAccountID(account.getUsername()), "Wood", 0);
			gameEngine.updateItemAmount(gameEngine.getAccountID(account.getUsername()), "Fish", 0);
			status += "You fell unconscious and lost your materials. <br>";
			player.setHealth(50);
			player.getLocation().setX(10); // Set player location to original location
			player.getLocation().setY(10);
			player.getLocation().setZ(0);
		}
		return status;
	}
}
