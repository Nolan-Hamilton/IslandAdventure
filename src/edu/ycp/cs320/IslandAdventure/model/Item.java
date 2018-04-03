package edu.ycp.cs320.IslandAdventure.model;

/* This is the Item class which inherits from GameObject class.
 * This class represents objects in the game that can be picked up and placed in inventory.
 * 
 * */

public class Item extends GameObject {
	// FIELDS (Fields that are not found in GameObject)
	Integer uses;
	
	public Item(String name, String description, Integer x, Integer y, Integer z, Location location, Integer uses){
		super(name, description,x, y, z, location);	// This passes arguments to superclass for construction.
		this.uses = uses;
	}
	
	// METHODS
	public Integer getUses(){
		return this.uses;
	}
	
	public void setUses(Integer uses){
		this.uses = uses;
	}
	
	// This method is called when an item is used.
	public void use(){
		this.uses--;	// Decrease # of uses by 1.
	}
	
}