package edu.ycp.cs320.IslandAdventure.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Inventory 
{
	private Map<Item, Integer> inventory = new HashMap<Item, Integer>();
	
	public Inventory(Map<Item, Integer> inventory)
	{
		this.inventory = inventory;
	}
	
	public void addItem(Item item, int amount) 
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
	
	public Integer getItemCount(Item item)
	{
		if (inventory.containsKey(item))
		{
			return inventory.get(item);
		}
		else
		{
			return null;
		}
	}
	
	public Integer getItemCountFromString(String itemName)
	{
		Set<Item> keyset = inventory.keySet();
		Iterator<Item> iterator = keyset.iterator();
		while(iterator.hasNext())
		{
			Item item = iterator.next();
			if (itemName.equals(item.getName()))
			{
				return inventory.get(item);
			}
		}
		return null;
	}
	
	public Map<Item, Integer> getInventoryMap()
	{
		return inventory;
	}
}
