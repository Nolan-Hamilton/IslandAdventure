package edu.ycp.cs320.IslandAdventure.Junit.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.IslandAdventure.model.Inventory;
import edu.ycp.cs320.IslandAdventure.model.Item;
import edu.ycp.cs320.IslandAdventure.model.Location;
import edu.ycp.cs320.IslandAdventure.model.Player;
import edu.ycp.cs320.IslandAdventure.model.Skills;

public class PlayerTest 
{
	private Player player;
	
	private Inventory inventory;
	private Location location;
	private Skills skills;
	
	@Before
	public void setUp()
	{
		location = new Location(10, 10, 0);
		Map<String, Integer> inventoryMap = new HashMap<String, Integer>();
		inventory = new Inventory(inventoryMap);
		skills = new Skills(0,10,100,35);
		player = new Player(5, 50, 50, 10, inventory, location, skills, null, null);
	}
	
	@Test
	public void testGetScore() 
	{
		assertTrue(player.getScore() == 5);
	}
	
	@Test
	public void testAddScore()
	{
		player.addScore(5);
		assertTrue(player.getScore() == 10);
	}
	
	@Test
	public void testGetHealth() 
	{
		assertTrue(player.getHealth() == 50);
	}
	
	@Test
	public void testModifyHealth()
	{
		player.modifyHealth(-20);
		assertTrue(player.getHealth() == 30);
	}
	
	@Test
	public void testGetStamina() 
	{
		assertTrue(player.getStamina() == 50);
	}
	
	@Test
	public void testModifyStamina()
	{
		player.modifyStamina(50);
		assertTrue(player.getStamina() == 100);
	}
	
	@Test
	public void testGetTime() 
	{
		assertTrue(player.getTime() == 10);
	}
	
	@Test
	public void testChangeTime()
	{
		player.changeTime(4);
		assertTrue(player.getTime() == 14);
	}
	
	@Test
	public void testHasItem()
	{
		inventory.addItem("Wood", 5);
		assertTrue(player.hasItem("Wood"));
		assertFalse(player.hasItem("Stone"));
	}
	
	@Test
	public void testGetItemCount()
	{
		inventory.addItem("Steel", 42);
		assertTrue(player.getItemCount("Steel") == 42);
	}
	
	@Test
	public void testGetInventory()
	{
		assertTrue(player.getInventory().equals(inventory));
	}
	
	@Test
	public void testGetLocation()
	{
		assertTrue(player.getLocation().equals(location));
	}
	
	@Test
	public void testChangeLocation()
	{
		Location location2 = new Location(12, 16, 0);
		player.changeLocation(location2);
		assertTrue(player.getLocation().equals(location2));
	}
	
	@Test
	public void testGetSkills()
	{
		assertTrue(player.getSkills().equals(skills));
	}
	
	@Test
	public void testEquipArmor() 
	{
		Item armor1 = new Item("Wood Armor", "Weak Armor", location, null);
		Item armor2 = new Item("Steel Armor", "Strong Armor", location, null);
		player.equipArmor(armor1);
		assertTrue(player.getArmor() == null); // No such item in inventory
		
		player.getInventory().addItem(armor1.getName(), 1);
		player.getInventory().addItem(armor2.getName(), 1);
		
		player.equipArmor(armor1);
		assertTrue(player.getArmor().equals(armor1));
		
		player.equipArmor(armor2);
		assertTrue(player.getArmor().equals(armor1)); // Needs to unequip current armor first
		
		player.unequipArmor();
		
		player.equipArmor(armor2);
		assertTrue(player.getArmor().equals(armor2));
	}
	
	@Test
	public void testEquipWeapon() 
	{
		Item weapon1 = new Item("Wood Sword", "Weak Sword", location, null);
		Item weapon2 = new Item("Steel Sword", "Strong Sword", location, null);
		player.equipWeapon(weapon1);
		assertTrue(player.getWeapon() == null); // No such item in inventory
		
		player.getInventory().addItem(weapon1.getName(), 1);
		player.getInventory().addItem(weapon2.getName(), 1);
		
		player.equipWeapon(weapon1);
		assertTrue(player.getWeapon().equals(weapon1));
		
		player.equipWeapon(weapon2);
		assertTrue(player.getWeapon().equals(weapon1)); // Needs to unequip current weapon first
		
		player.unequipWeapon();
		
		player.equipWeapon(weapon2);
		assertTrue(player.getWeapon().equals(weapon2));
	}
}
