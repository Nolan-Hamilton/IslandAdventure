package edu.ycp.cs320.IslandAdventure.controller;

import java.util.HashMap;
import java.util.Map;

import edu.ycp.cs320.IslandAdventure.model.Inventory;


public class InventoryController 
{
	private Inventory inventory;
	
	public InventoryController(Inventory inventory) 
	{
		this.inventory = inventory;
	}
	
	public Inventory createNewInventory()
	{
		Map<String, Integer> inventoryMap = new HashMap<String, Integer>();
		Inventory inventory = new Inventory(inventoryMap);
		return inventory;
	}
	
	public void setModel(Inventory model) 
	{
		this.inventory = model;
	}
	
	public void changeWoodAmount(int amount)
	{
		inventory.addItem("Wood", amount);
	}
}
