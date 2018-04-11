package edu.ycp.cs320.IslandAdventure.Junit.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.IslandAdventure.controller.InventoryController;
import edu.ycp.cs320.IslandAdventure.controller.LocationController;
import edu.ycp.cs320.IslandAdventure.controller.PlayerController;
import edu.ycp.cs320.IslandAdventure.controller.SkillsController;
import edu.ycp.cs320.IslandAdventure.model.Inventory;
import edu.ycp.cs320.IslandAdventure.model.Item;
import edu.ycp.cs320.IslandAdventure.model.Location;
import edu.ycp.cs320.IslandAdventure.model.Player;
import edu.ycp.cs320.IslandAdventure.model.Skills;

public class PlayerControllerTest 
{
	InventoryController inventoryController = new InventoryController(null);
	LocationController locationController = new LocationController(null);
	SkillsController skillsController = new SkillsController();
	
	private Player player;
	private PlayerController playerController = new PlayerController();
	
	@Before
	public void setUp()
	{
		Inventory inventory = inventoryController.createNewInventory();
		Location location = locationController.setStartingLocation();
		Skills skills = skillsController.createNewSkills();
		player = new Player(0, 100, 100, 6, inventory, location, skills, null, null);
	}
	
	@Test
	public void testCreateNewPlayer() 
	{
		assertTrue(playerController.createNewPlayer().getClass().equals(player.getClass()));
	}
	
	@Test
	public void testCheckPlayerState() 
	{
		Item item = new Item("Item", "Item", player.getLocation(), 0);
		player.getInventory().addItem(item, 10);
		assertTrue(player.getInventory().getItemCount(item) == 10);
		player.setHealth(-10);
		playerController.checkPlayerState(player);
		assertTrue(player.getInventory().getItemCount(item) == 0);
	}

}
