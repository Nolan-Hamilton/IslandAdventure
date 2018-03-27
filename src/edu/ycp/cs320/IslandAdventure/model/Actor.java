package edu.ycp.cs320.IslandAdventure.model;

public class Actor extends GameObject
{
	// FIELDS (Fields that are not found in GameObject)
	Integer health;
		
	// CONSTRUCTOR
	public Actor(String name, String description, Integer x, Integer y, Integer z, Integer health, Location location)
	{
		super(name, description,x, y, z, location);	// This passes arguments to superclass for construction.
		this.health = health;
	}
	
	// METHODS
	public Integer getHealth()
	{
		return health;
	}
	
	public void setHealth(Integer health)
	{
		this.health = health;
	}
	
	// This method is called when actor takes damage or recovers health
	public void changeHealth(int healthChange)
	{
		health = health + healthChange;	// Decrease # of uses by 1.
	}
}
