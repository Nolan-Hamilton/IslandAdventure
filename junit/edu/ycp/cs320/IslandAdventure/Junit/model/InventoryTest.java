package edu.ycp.cs320.IslandAdventure.Junit.model;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.IslandAdventure.model.Inventory;
import edu.ycp.cs320.IslandAdventure.model.Item;

public class InventoryTest 
{
	private Inventory inventory;
	private Map<Item, Integer> inventoryMap = new HashMap<Item, Integer>();
	
	private Item steel = new Item("Steel", "Steel", null, 0);
	private Item wood = new Item("Wood", "Wood", null, 0);
	private Item axe = new Item("Axe", "Axe", null, 0);
	
	@Before
	public void setUp()
	{
		inventoryMap.put(steel, 7);
		inventory = new Inventory(inventoryMap);
	}
	
	@Test
	public void testAddItem() 
	{
		inventory.addItem(axe, 1);
		assertTrue(inventoryMap.containsKey(axe));
		
		inventory.addItem(wood, 20);
		assertTrue(inventory.getItemCountFromString("Wood") == 20);
		
		inventory.addItem(wood, 15);
		assertTrue(inventory.getItemCountFromString("Wood") == 35);
	}

	@Test
	public void testGetItemCount()
	{
		assertTrue(inventory.getItemCountFromString("Steel") == 7);
	}
	
	@Test
	public void testGetInventoryMap()
	{
		assertTrue(inventory.getInventoryMap().equals(inventoryMap));
	}
}
