package edu.ycp.cs320.IslandAdventure.Junit.model;


import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

import edu.ycp.cs320.IslandAdventure.model.Location;
import edu.ycp.cs320.IslandAdventure.model.*;




public class RoomTest {
	
	Location loc;
	Room room;
	
	@Before
	public void setUp() {
		 loc = new Location(1,2,3);
		 room = new Room(loc, "This is the description.", false, false, false, false, false, false, false);
	}
	
	@Test
	public void testGetLocation() {
		assertTrue(room.getLocation().equals(loc));
	}
	
	@Test
	public void testSetLocation()
	{
		Location locEx = new Location(2,2,2);
		room.setLocation(2,2,2);
		assertTrue(room.getLocation().equals(locEx));
		
	}
	
	@Test
	public void testSetLocationWithLocation() {
		
		Location loc1 = new Location(3,2,1);
		room.setLocation(loc1);
		assertTrue(room.getLocation().equals(loc1));
		
	}

	@Test
	public void testGetDescription() {
		assertTrue(room.getDescription().equals("This is the description."));
	}
	
	@Test
	public void testSetDescription() {
		room.setDescription("This is the new Description.");
		assertTrue(room.getDescription().equals("This is the new Description."));
	}
	
	@Test
	public void testGetVisible() {
		assertTrue(room.getVisible() == false);
	}
	
	@Test
	public void testSetVisible() {
		room.setVisible(true);
		assertTrue(room.getVisible() == true);
	}
	
	@Test
	public void testGetGoNorth() {
		assertTrue(room.getGoNorth() == false);
	}
	
	@Test
	public void testSetGoNorth() {
		room.setGoNorth(true);
		assertTrue(room.getGoNorth() == true);
	}
	
	@Test
	public void testGetGoEast() {
		assertTrue(room.getGoEast() == false);
	}
	
	@Test
	public void testSetGoEast() {
		room.setGoEast(true);
		assertTrue(room.getGoEast() == true);
	}
	
	@Test
	public void testGetGoSouth() {
		assertTrue(room.getGoSouth() == false);
	}
	
	@Test
	public void testSetGoSouth() {
		room.setGoSouth(true);
		assertTrue(room.getGoSouth() == true);
	}
	
	@Test
	public void testGetGoWest() {
		assertTrue(room.getGoWest() == false);
	}
	
	@Test
	public void testSetGoWest() {
		room.setGoWest(true);
		assertTrue(room.getGoWest() == true);
	}
	
	@Test
	public void testGetGoUp() {
		assertTrue(room.getGoUp() == false);
	}
	
	@Test
	public void testSetGoUp() {
		room.setGoUp(true);
		assertTrue(room.getGoUp() == true);
	}
	
	@Test
	public void testGetGoDown() {
		assertTrue(room.getGoDown() == false);
	}
	
	@Test
	public void TestSetGoDown() {
		room.setGoDown(true);
		assertTrue(room.getGoDown() == true);
	}
}
