package edu.ycp.cs320.IslandAdventure.controller;

import edu.ycp.cs320.IslandAdventure.model.Inventory;
import edu.ycp.cs320.IslandAdventure.model.Location;
import edu.ycp.cs320.IslandAdventure.model.Player;
import edu.ycp.cs320.IslandAdventure.model.Skills;

public class PlayerController 
{
	InventoryController inventoryController = new InventoryController(null);
	LocationController locationController = new LocationController();
	SkillsController skillsController = new SkillsController();
	public Player createNewPlayer()
	{
		Inventory inventory = inventoryController.createNewInventory();
		Location location = locationController.setStartingLocation();
		Skills skills = skillsController.createNewSkills();
		Player player = new Player(0, 100, 100, 6, inventory, location, skills);
		return player;
	}
}
