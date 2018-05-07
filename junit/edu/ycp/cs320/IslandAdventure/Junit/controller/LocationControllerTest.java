package edu.ycp.cs320.IslandAdventure.Junit.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.IslandAdventure.controller.LocationController;
import edu.ycp.cs320.IslandAdventure.model.Location;

public class LocationControllerTest 
{
	private Location location;
	private LocationController locationController;
	
	@Before
	public void setUp()
	{
		location = new Location(2,2,0);
		locationController = new LocationController(location);
	}
	
	@Test
	public void testSetStartingLocation() 
	{
		assertTrue(locationController.setStartingLocation().equals(location));
	}
}
