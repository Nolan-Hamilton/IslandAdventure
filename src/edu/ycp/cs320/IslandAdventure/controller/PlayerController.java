package edu.ycp.cs320.IslandAdventure.controller;

import edu.ycp.cs320.IslandAdventure.model.Account;
import edu.ycp.cs320.IslandAdventure.model.Inventory;
import edu.ycp.cs320.IslandAdventure.model.Location;
import edu.ycp.cs320.IslandAdventure.model.Player;
import edu.ycp.cs320.IslandAdventure.model.Skills;
import edu.ycp.cs320.IslandAdventure.model.*;

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
			player.getLocation().setX(2); // Set player location to original location
			player.getLocation().setY(2);
			player.getLocation().setZ(0);
		}
		Location shrine = new Location(6,7,2);
		for (Item item : player.getInventory().getInventoryMap().keySet()) {
			if (item.getName().equals("Island Treasure") && player.getLocation().equals(shrine)) {
				status += "You found the Treasure! You win!<br>";
				player.addScore(500);
				player.getLocation().setX(2); // Set player location to original location
				player.getLocation().setY(2);
				player.getLocation().setZ(0);
			}
		}
		return status;
	}
}
