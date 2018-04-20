package edu.ycp.cs320.IslandAdventure.controller;

import java.util.HashMap;
import java.util.Map;

import edu.ycp.cs320.IslandAdventure.model.Account;
import edu.ycp.cs320.IslandAdventure.model.Inventory;
import edu.ycp.cs320.IslandAdventure.model.Item;


public class InventoryController 
{
	private GameEngine gameEngine = new GameEngine();
	private Inventory inventory;
	private Account account;
	private Integer account_id;
	Item wood = new Item("Wood", "Wood", null, 0);
	Item fish = new Item("Fish", "Fish", null, 0);
	
	public InventoryController(Inventory inventory, Account account, Integer account_id) 
	{
		this.inventory = inventory;
		this.account = account;
		this.account_id = account_id;
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
		wood.setLocation(account.getPlayer().getLocation());
		inventory.addItem(wood, amount);
		gameEngine.insertNewItemIntoDatabase(account, account_id, wood, amount);
	}
	public void changeFishAmount(int amount)
	{
		fish.setLocation(account.getPlayer().getLocation());
		inventory.addItem(fish, amount);
		gameEngine.insertNewItemIntoDatabase(account, account_id, fish, amount);
	}
}
