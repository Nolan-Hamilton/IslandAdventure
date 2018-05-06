package edu.ycp.cs320.IslandAdventure.controller;

import edu.ycp.cs320.IslandAdventure.model.Location;

public class LocationController 
{
	private Location location;
	
	public LocationController(Location location){
		this.location = location;
	}
	
	public Location setStartingLocation()
	{
		Location location = new Location(2, 2, 0);
		return location;
	}
	
	public void setModel(Location model){
		this.location = model;
	}
	
	public Location getLocation(){
		return location;
	}
	
	public void setX(int x){
		location.setX(x);
	}
}
