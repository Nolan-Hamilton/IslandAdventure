package edu.ycp.cs320.IslandAdventure.model;

/* This is the Armor class which inherits from Item class.
 * This class represents armor in the game to aid in combat.
 * 
 * */

public class Armor extends Item 
{
	// FIELDS (Fields that are not found in Item)
	private Integer armor;
	
	public Armor(String name, String description, Location location, Integer armor)
	{
		super(name, description, location, 0);	// This passes arguments to superclass for construction.
		this.armor = armor;
	}
	
	// METHODS
	public Integer getArmorAmount()
	{
		return this.armor;
	}
	
	public void setArmorAmount(Integer armor)
	{
		this.armor = armor;
	}
}