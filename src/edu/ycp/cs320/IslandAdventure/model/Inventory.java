package edu.ycp.cs320.IslandAdventure.model;

import java.util.HashMap;
import java.util.Map;

public class Inventory 
{
	private Map<String, Integer> inventory = new HashMap<String, Integer>();
	
	public Inventory(Map<String, Integer> inventory)
	{
		this.inventory = inventory;
	}
	
	public void addItem(String item, int amount) 
	{
	    Integer count = inventory.get(item);
	    if (count == null) 
	    {
	        inventory.put(item, amount);
	    } 
	    else 
	    {
	        inventory.put(item, count + amount);
	    }
	}
	
	public Integer getItemCount(String item)
	{
		return inventory.get(item);
	}
	
	public Map<String, Integer> getInventoryMap()
	{
		return inventory;
	}
	
	public Integer getWoodCount()	// Temporary method for testing
	{
		return inventory.get("Wood");
	}
}
