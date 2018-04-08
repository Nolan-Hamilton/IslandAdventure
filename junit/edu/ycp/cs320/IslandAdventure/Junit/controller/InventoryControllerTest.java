package edu.ycp.cs320.IslandAdventure.Junit.controller;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.IslandAdventure.controller.InventoryController;
import edu.ycp.cs320.IslandAdventure.model.Inventory;
import edu.ycp.cs320.IslandAdventure.model.Item;

public class InventoryControllerTest 
{
	private Inventory inventory;
	private InventoryController inventoryController; 
	
	@Before
	public void setUp()
	{
		Map<Item, Integer> inventoryMap = new HashMap<Item, Integer>();
		inventory = new Inventory(inventoryMap);
		inventoryController = new InventoryController(inventory);
	}
		
	@Test
	public void testCreateNewInventory() 
	{
		assertTrue(inventoryController.createNewInventory().getInventoryMap().equals(inventory.getInventoryMap()));
	}
}
