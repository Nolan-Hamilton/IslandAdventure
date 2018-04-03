package edu.ycp.cs320.IslandAdventure.model;

/* This is the class that will serve as the superclass for all objects in the Island Adventure game.
 * All other object classes will inherit from this class. 
 * Created originally by Nolan Hamilton. 
 * 
 * 
 * Changes:
 * 3/4/18 - Nolan-Hamilton: Initial creation of class.
 * 3/5/18 - Nolan-Hamilton: Comments added.
 * */

public class GameObject {
	
	// Fields
	private String name; 			// Name which object is refered to.
	private String description;		// Description of Object for user.
	private Location location;
	
	public GameObject(String name, String description, Location location){
		this.name = name;			// Complete Constructor
		this.description = description;
		this.location = location;
	}
	
	// Methods
	
	// Name methods (get and set)
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	// Description methods (get and set)
	public String getDescription() {
		return this.description;
	}
	
	public void setDescript(String description) {
		this.description = description;
	}
	
	// XYZ methods (get and set)
	public Integer getX() {
		return location.getX();
	}
	
	public void setX(Integer x) {
		location.setX(x);
	}
	
	public Integer getY() {
		return location.getY();
	}
	
	public void setY(Integer y) {
		location.setY(y);
	}
	
	public Integer getZ() {
		return location.getZ();
	}
	
	public void setZ(Integer z) {
		location.setZ(z);
	}
	
	public Location getLocation(){
		return this.location;
	}
	
	public void setLocation(Location location){
		this.location = location;
	}
}