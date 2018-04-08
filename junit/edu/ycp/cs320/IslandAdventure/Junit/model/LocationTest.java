package edu.ycp.cs320.IslandAdventure.Junit.model;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import edu.ycp.cs320.IslandAdventure.model.Location;




public class LocationTest {
	
	Location loc;
	
	@Before
	public void setUp() {
		 loc = new Location(1,2,3);
	}
	
	@Test
	public void testGetX()
	{
		assertTrue(loc.getX() == 1);
	}

	@Test
	public void testGetY()
	{
		assertTrue(loc.getY() == 2);
	}
	
	@Test
	public void testGetZ()
	{
		assertTrue(loc.getZ() == 3);
	}
	
	@Test
	public void testSetLocation()
	{
		Location loc1 = new Location(3,2,1);
		loc1.setLocation(1,2,3);
		assertTrue(loc.equals(loc1));
		
	}
	
	@Test
	public void testSetLocationWithLocation() {
		
		Location loc1 = new Location(3,2,1);
		Location loc2 = new Location(1,2,3);
		loc1.setLocation(loc2);
		assertTrue(loc.equals(loc1));
		
	}
}
