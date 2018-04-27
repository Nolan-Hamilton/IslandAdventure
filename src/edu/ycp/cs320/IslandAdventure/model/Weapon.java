package edu.ycp.cs320.IslandAdventure.model;

/* This is the Weapon class which inherits from Item class.
 * This class represents weapons in the game to aid in combat.
 * 
 * */

public class Weapon extends Item 
{
	// FIELDS (Fields that are not found in Item)
	private Integer damage;
	
	public Weapon(String name, String description, Location location, Integer damage)
	{
		super(name, description, location, 0);	// This passes arguments to superclass for construction.
		this.damage = damage;
	}
	
	// METHODS
	public Integer getDamage()
	{
		return this.damage;
	}
	
	public void setDamage(Integer damage)
	{
		this.damage = damage;
	}
}