package edu.ycp.cs320.IslandAdventure.Junit.model;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.IslandAdventure.model.Inventory;

public class InventoryTest 
{
	private Inventory inventory;
	private Map<String, Integer> inventoryMap = new HashMap<String, Integer>();
	
	@Before
	public void setUp()
	{
		inventoryMap.put("Steel", 7);
		inventory = new Inventory(inventoryMap);
	}
	
	@Test
	public void testAddItem() 
	{
		inventory.addItem("axe", 1);
		assertTrue(inventoryMap.containsKey("axe"));
		
		inventory.addItem("Wood", 20);
		assertTrue(inventory.getItemCount("Wood") == 20);
		
		inventory.addItem("Wood", 15);
		assertTrue(inventory.getItemCount("Wood") == 35);
	}

	@Test
	public void testGetItemCount()
	{
		assertTrue(inventory.getItemCount("Steel") == 7);
	}
	
	@Test
	public void testGetInventoryMap()
	{
		assertTrue(inventory.getInventoryMap().equals(inventoryMap));
	}
}
