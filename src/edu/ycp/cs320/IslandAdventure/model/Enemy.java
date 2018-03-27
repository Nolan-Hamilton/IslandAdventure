package edu.ycp.cs320.IslandAdventure.model;

public class Enemy extends Actor
{
	// FIELDS (Fields that are not found in GameObject or Actor)
	Integer damage;
	
	public Enemy(String name, String description, Integer x, Integer y, Integer z, Integer health,Location location, Integer damage) 
	{
		super(name, description, x, y, z, health, location);
		this.damage = damage;
	}

	// METHODS
	public Integer getDamage()
	{
		return damage;
	}
	
	public void setDamage(Integer damage)
	{
		this.damage = damage;
	}
}
