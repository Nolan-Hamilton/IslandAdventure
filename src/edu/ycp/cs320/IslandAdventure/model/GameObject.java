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
	private Integer x, y, z;		// Coordinates for Location
	private Location location;
	
	// Constructors
	public GameObject() {			// Empty Constructor
		
	}
	
	public GameObject(String name, String description, Integer x, Integer y, Integer z, Location location){
		this.name = name;			// Complete Constructor
		this.description = description;
		this.x = x;
		this.y = y;
		this.z = z;
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
		return this.x;
	}
	
	public void setX(Integer x) {
		this.x = x;
	}
	
	public Integer getY() {
		return this.y;
	}
	
	public void setY(Integer y) {
		this.y = y;
	}
	
	public Integer getZ() {
		return this.z;
	}
	
	public void setZ(Integer z) {
		this.z = z;
	}
	
	public Location getLocation(){
		return this.location;
	}
	
	public void setLocation(Location location){
		this.location = location;
	}
}