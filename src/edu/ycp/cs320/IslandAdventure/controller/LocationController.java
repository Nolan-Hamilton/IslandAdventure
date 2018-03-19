package edu.ycp.cs320.IslandAdventure.controller;

import edu.ycp.cs320.IslandAdventure.model.Location;

public class LocationController 
{
	public Location setStartingLocation()
	{
		Location location = new Location(10, 10, 0);
		return location;
	}
}
