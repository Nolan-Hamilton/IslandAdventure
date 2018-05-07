package edu.ycp.cs320.IslandAdventure.controller;

import edu.ycp.cs320.IslandAdventure.MapView.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import edu.ycp.cs320.IslandAdventure.model.*;

public class ActionController 
{
	private Player player;
	
	private InventoryController inventoryController;
	
	private Account account;
	
	private EventController eventController = new EventController();
	
	private PlayerController playerController = new PlayerController();
	
	private GameEngine gameEngine = new GameEngine();
	
	
	public ActionController(Player player, Account account) 
	{
		this.player = player;
		this.account = account;
		inventoryController = new InventoryController(player.getInventory(), account, gameEngine.getAccountID(account.getUsername()));
	}
	
	public String interpretAction(String action)
	{
		Location location = player.getLocation();
		String response = "";
		if (action.equals("")){
			return response += ""; // Do nothing if action command is empty
		}
		response += ">> " + action + "<br><br>"; // Add action command to response
		action = action.toLowerCase();	// turns action to lower-case so commands aren't case sensitive
		
		Location riddle1 = new Location(7,6,1);
		Location riddle2 = new Location(5,8,1);
		
		if ((action.equals("a candle") || action.equals("candle")) && player.getLocation().equals(riddle1))
		{
			response += "A blinding light appears in front of you. It appears to be a mystical candle. "
					+ "You can now see enough to move farther in the cave!<br><br>";
			account.getRoomByXYZ(7,6,1).setGoEast(true);	//Allows player to progress through cave
			return response;
		}
		
		else if ((action.equals("a map") || action.equals("map")) && player.getLocation().equals(riddle2))
		{
			response += "The cave trembles as you turn around to find a map. The map shows the "
					+ "path forward!<br><br>";
			account.getRoomByXYZ(5,8,1).setGoEast(true);	//Allows player to progress through cave
			return response;
		}
		
		if ((action.equals("a map") || action.equals("map")) && player.getLocation().equals(riddle2))
		{
			response += "The cave trembles as you turn around to find a map. The map shows the "
					+ "path forward!<br><br>";
			return response;
		}
		if (action.contains("move"))
		{
			if (player.getLocation().getZ() != 0 && !(action.contains("up")) && !(action.contains("down"))){
				System.out.println("ActionController >> Underground Movement!");
				boolean lightSource = false;
				for (Item item : player.getInventory().getInventoryMap().keySet()){
					if (item.getName().toLowerCase().equals("lantern") || item.getName().toLowerCase().equals("wood torch")){
						lightSource = true;
						System.out.println("ActionConroller >> LightSource = " + lightSource);
					}
				} // If player does not possess light source, cannot move underground
				if (lightSource == false){
					response += "You cannot move in this darkness without a light source. <br><br>";
					return response;
				}
			}
			if (action.toLowerCase().contains("east")) 
			{
				if (account.getRoomByXYZ(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()).getGoEast() == true){
					location.setX(player.getLocation().getX()+1);
				}else{
					response += "You cannot go that way! <br><br>";
				}
			}
			else if (action.contains("west")) 
			{
				if (account.getRoomByXYZ(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()).getGoWest() == true){
					location.setX(player.getLocation().getX()-1);
				}else{
					response += "You cannot go that way! <br><br>";
				}
			}
			else if (action.contains("north")) 
			{
				
				if (account.getRoomByXYZ(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()).getGoNorth() == true){
					location.setY(player.getLocation().getY()+1);
				}else{
					response += "You cannot go that way! <br><br>";
				}
			}
			else if (action.contains("south")) 
			{
				if (account.getRoomByXYZ(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()).getGoSouth() == true){
					location.setY(player.getLocation().getY()-1);
				}else{
					response += "You cannot go that way! <br><br>";
				}
			}
			else if (action.contains("down")) 
			{
				if (account.getRoomByXYZ(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()).getGoDown() == true){
					location.setZ(player.getLocation().getZ()+1);
				}else{
					response += "You cannot go that way! <br><br>";
				}
			}
			else if (action.contains("up")) 
			{
				if (account.getRoomByXYZ(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()).getGoUp() == true){
					location.setZ(player.getLocation().getZ()-1);
				}else{
					response += "You cannot go that way! <br><br>";
				}
			}
			// Print description and items
			// if new room, display long description
			if (account.getRoomByXYZ(location.getX(), location.getY(), location.getZ()).getVisible() == false) {
				response += account.getRoomByXYZ(location.getX(), location.getY(), location.getZ()).getLongDescription() + "<br>";
			}else {
				response += account.getRoomByXYZ(location.getX(), location.getY(), location.getZ()).getShortDescription() + "<br>";
			}
			
			// Display Items
			ArrayList<Item> items = account.getItemsByXYZ(location.getX(), location.getY(), location.getZ());
			if (items.size() != 0) 
			{
				response += "The following item(s) are also present: <br>";
				for (Item item : items) 
				{
					response += item.getName() + "<br>";
				}
			}
			response += eventController.moveEvent(account);
			response += "<br>";
		}
		
		else if (action.equals("chop wood")) 
		{
			int axeMultiplier = 1;
			if (player.getInventory().getItemCountFromString("wood axe") > 0)
			{
				axeMultiplier = 2; // Player gets bonus for having a wood axe
			}
			// Below is formula for calculating wood gathered using skills and axe
			int amountChopped = axeMultiplier * ((player.getSkills().getWoodCuttingXP()/100) + 1);
			inventoryController.changeWoodAmount(amountChopped);
			player.changeTime(1);	// Takes 1 hour to chop wood
			player.getSkills().addWoodCuttingXP(5);	//Gains 5 WC XP
			player.modifyStamina(-15);	// Stamina is reduced by 15 when chopping wood
		}
		
		else if (action.equals("fish")) 
		{
			boolean fishingRod = false;
			for (Item item : player.getInventory().getInventoryMap().keySet()){
				if (item.getName().toLowerCase().contains("fishing rod")){
					fishingRod = true;
					System.out.println("ActionConroller >> FishingRod = " + fishingRod);
				}
			}
			if (fishingRod == false){
				response += "You cannot fish without a fishing rod. Craft one. <br><br>";
				return response;
			}
			if ((player.getLocation().getX() == 0 || player.getLocation().getX() == 14 || player.getLocation().getY() == 0 || player.getLocation().getY() == 14) && player.getLocation().getZ() == 0){
				int amountFished = (player.getSkills().getFishingXP()/100 + 3);
				inventoryController.changeFishAmount(amountFished);
				player.changeTime(1);	// Takes 1 hour to chop wood
				player.getSkills().addFishingXP(5);	//Gains 5 Fishing XP
				player.modifyStamina(-15);	// Stamina is reduced by 15 when chopping wood
			}else{
				response += "You must me on the shoreline to fish in the ocean! <br><br>";
				return response;
			}
		}
		
		else if(action.contains("sleep"))
		{
			Random rand = new Random();
			int  n;
			if (player.getLocation().getX() == 10 && player.getLocation().getY() == 10) //Player is home
			{
				n = rand.nextInt(20) + 1;	//random # from 1-20
			}
			else	// Higher random numbers cause higher chance of bad effects from sleeping
			{
				n = rand.nextInt(50) + 1;	//random # from 1-50
			}
			
			response += eventController.sleepEvent(account, n) + "<br>";
		}
		
		else if (action.equals("eat fish")) 
		{
			if (player.getInventory().getItemCountFromString("Fish") > 0)
			{
				inventoryController.changeFishAmount(-1);
				player.modifyHealth(10);	// Health is increased by 10 for each fish eaten
				if (player.getHealth() > 100)
				{
					player.setHealth(100);	// Can't exceed 100 health
				}
				response += "You eat a fish and gain health.";
			}
			else
			{
				response += "You don't have any fish to eat!";
			}
		}
		
		//Display Map
		else if (action.equals("display map")){
			MapFrame map = new MapFrame();
			map.displayMap(account);
		}

		// Displays the description of the current room as well as any items located in that room
		else if (action.equals("look")) 
		{
			// Display description
			if (account.getRoomByXYZ(location.getX(), location.getY(), location.getZ()).getVisible() == false) {
				response += account.getRoomByXYZ(location.getX(), location.getY(), location.getZ()).getLongDescription() + "<br>";
			}else {
				response += account.getRoomByXYZ(location.getX(), location.getY(), location.getZ()).getShortDescription() + "<br>";
			}
			// Display list of items.
			ArrayList<Item> items = account.getItemsByXYZ(location.getX(), location.getY(), location.getZ());
			if (items.size() != 0) 
			{
				response += "The following item(s) are also present: <br>";
				for (Item item : items) 
				{
					response += item.getName() + "<br>";
				}
			}
			response += "<br>";
		}
		
		else if (action.contains("drop")){
			int chop = action.indexOf(" ") + 1;			//This finds the index of the first letter of the item
			String itemName = action.substring(chop).toLowerCase();	//returns the name of the item and throws out the 'take '
			boolean found = false;
			for (Item key: player.getInventory().getInventoryMap().keySet()){
				if (itemName.equals(key.getName().toLowerCase())){
					Item item = new Item(key.getName(), key.getDescription(), new Location(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()), key.getUses());
					account.getItemList().add(item);
					player.getInventory().addItem(key, -1);
					int account_id = gameEngine.getAccountID(account.getUsername());
					gameEngine.moveItemInventory(account_id, 0, item.getName());
					//Set Item location in inventory
					gameEngine.updateItemLocation(account_id, item.getName(), item.getX(), item.getY(), item.getZ());
					found = true;
					response += itemName + " has been removed from your inventory. <br>";
					if (player.getInventory().getInventoryMap().get(key) == 0){
						player.getInventory().getInventoryMap().remove(key); // Remove from list otherwise Hammer = 0, Hammer = 1
					}
					break;
				}
			}
			if (found == false){
				response += "This item was not found in your inventory. <br>";
			}
		}
		
		else if (action.contains("take")){
			ArrayList<Item> items = account.getItemsByXYZ(location.getX(), location.getY(), location.getZ());
			int chop = action.indexOf(" ") + 1;			//This finds the index of the first letter of the item
			String itemName = action.substring(chop).toLowerCase();	//returns the name of the item and throws out the 'take '
			if (items.size() != 0) 
			{
				boolean stringFound = false;
				for (Item item : items) 
				{
					if (item.getName().toLowerCase().equals(itemName)){
						response += "You acquired the following item: <br>" + item.getName() + "<br>";
						
						player.getInventory().addItem(item, 1);
						int account_id = gameEngine.getAccountID(account.getUsername());
						gameEngine.moveItemInventory(account_id, 1, item.getName());
						//Remove the Item from the objectList //////////////////////////////////////////
						int index = account.getObjectIndexByNameAndXYZ(item.getName(), item.getX(), item.getY(), item.getZ()); //This finds index of item in ObjectList
						account.getItemList().remove(index);			//This removes item from ObjectList using index.
						///////////////////////////////////////////////////////////////////////////////
						stringFound = true;
						break;	// Only one item is taken at a time
					}
				}
				if (stringFound == false){		// This is if item is misspelled or not here
					response += "This item could not be found. <br> ";
				}
			}else{
				response += "There is nothing here to take. <br>";
			}
		}
		
		else if (action.contains("craft"))
		{
			if (action.contains("wood"))
			{
				if (action.contains("sword"))
				{
					response += craftItem(player, "wood sword", "Wood", 5, 0, 5, 10); // Need 5 wood and 0 XP to craft wood sword
				}
				if (action.contains("axe"))
				{
					response += craftItem(player, "wood axe", "Wood", 5, 0, 5, 0); // Need 5 wood and 0 XP to craft wood axe
				}
				if (action.contains("rod"))
				{
					response += craftItem(player, "wood fishing rod", "Wood", 5, 0, 5, 0); // Need 5 wood and 0 XP to craft wood rod
				}
				if (action.contains("torch")){
					response += craftItem(player, "wood torch", "Wood", 5, 10, 5, 0); // Need 5 wood and 10 XP to craft a wood torch
				}
				if (action.contains("armor"))
				{
					response += craftItem(player, "wood armor", "Wood", 5, 0, 5, -10); // Need 5 wood and 0 XP to craft wood armor
				}
			}
			else
			{
				response += "Hint: type craft, material to use, and object. ex. (craft wood sword)";
			}
		}
	
		else if (action.contains("equip"))
		{
			Set<Item> keyset = player.getInventory().getInventoryMap().keySet();
			Iterator<Item> iterator = keyset.iterator();
			
			while(iterator.hasNext())
			{
				Item item = (Item) iterator.next();
				int account_id = gameEngine.getAccountID(account.getUsername());
				if (action.contains(item.getName()) && (item instanceof Weapon || item instanceof Armor))
				{
					if (item instanceof Weapon)
					{
						Weapon weapon = (Weapon) item;
						// Player must have enough combat xp to equip powerful items
						if ((weapon.getDamage() <= 20) || (player.getSkills().getCombatXP() > (weapon.getDamage()*10)))
						{
							player.equipWeapon(weapon);
							response += "You equipped a " + item.getName() + "!";
							gameEngine.moveItemInventory(account_id, 2, weapon.getName());
						}
						else
						{
							response += "You need more combat experience to equip that!";
						}
					}
					else
					{
						Armor armor = (Armor) item;
						if ((armor.getArmorAmount() <= 20) || (player.getSkills().getCombatXP() > (armor.getArmorAmount()*10)))
						{
							player.equipArmor(armor);
							response += "You equipped a " + item.getName() + "!";
							gameEngine.moveItemInventory(account_id, 2, armor.getName());
						}
						else
						{
							response += "You need more combat experience to equip that!";
						}
					}
				}
			}
		}else{
			// If the command is not understandable
			response += "I do not understand what you are saying... <br>";
		}
		
		response += playerController.checkPlayerState(account);	//Checks if player health and stamina > 0 
		response += "<br>";
		return response;
	}
	
	public String craftItem(Player player, String itemName, String itemRequired, 
			Integer amountRequired, Integer craftingXPRequired, Integer craftingXPGained, Integer damage)
	{
		String response = "";
		int count = player.getInventory().getItemCountFromString(itemRequired);
		int craftingXP = player.getSkills().getCraftingXP();
		Inventory inventory = player.getInventory();
		if (count >= amountRequired && craftingXP >= craftingXPRequired)
		{
			response += "You crafted a " + itemName + "! <br>";
			if (damage > 0)
			{
				Weapon weapon = new Weapon(itemName, itemName, player.getLocation(), damage);
				inventory.addItem(weapon, 1);
			}
			else if (damage < 0)
			{
				Armor armor = new Armor(itemName, itemName, player.getLocation(), -damage);
				inventory.addItem(armor, 1);
			}
			else
			{
				Item item = new Item(itemName, itemName, player.getLocation(), 0);
				inventory.addItem(item, 1);
			}
			Item itemForDatabase = new Item(itemName, itemName, player.getLocation(), 0);
			int account_id = gameEngine.getAccountID(account.getUsername());
			gameEngine.insertNewItemIntoDatabase(account, account_id, itemForDatabase, 1, damage);
			player.getSkills().addCraftingXP(craftingXPGained);
			// Below removes items used for crafting from inventory;
			Item itemToRemove = null;
			Set<Item> keySet = inventory.getInventoryMap().keySet();
			Iterator<Item> iterator = keySet.iterator();
			while (iterator.hasNext())
			{
				Item itemCheck = iterator.next();
				if (itemCheck.getName().equals(itemRequired))
				{
					itemToRemove = itemCheck;
				}
			}
			inventory.addItem(itemToRemove, -amountRequired);
			gameEngine.updateItemAmount(account_id, itemToRemove.getName(), inventory.getItemCount(itemToRemove));
		}
		else if (count < amountRequired)
		{
			response += "You need more " + itemRequired + " to craft that. <br>";
		}
		else
		{
			response += "You need more crafting XP to craft that. <br>";
		}
		return response;
	}
}
