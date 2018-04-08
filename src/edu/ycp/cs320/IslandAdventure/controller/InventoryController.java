package edu.ycp.cs320.IslandAdventure.controller;

import java.util.HashMap;
import java.util.Map;

import edu.ycp.cs320.IslandAdventure.model.Inventory;
import edu.ycp.cs320.IslandAdventure.model.Item;


public class InventoryController 
{
	private Inventory inventory;
	Item wood = new Item("Wood", "Wood", null, 0);
	Item fish = new Item("Fish", "Fish", null, 0);
	
	public InventoryController(Inventory inventory) 
	{
		this.inventory = inventory;
	}
	
	public Inventory createNewInventory()
	{
		Map<Item, Integer> inventoryMap = new HashMap<Item, Integer>();
		Inventory inventory = new Inventory(inventoryMap);
		return inventory;
	}
	
	public void setModel(Inventory model) 
	{
		this.inventory = model;
	}
	
	public void changeWoodAmount(int amount)
	{
		inventory.addItem(wood, amount);
	}
	public void changeFishAmount(int amount)
	{
		inventory.addItem(fish, amount);
	}
}
