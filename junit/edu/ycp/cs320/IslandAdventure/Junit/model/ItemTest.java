package edu.ycp.cs320.IslandAdventure.Junit.model;

/* These are the jUnit tests for Item class. */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.IslandAdventure.model.*;

public class ItemTest {
	private Item item;
	private Location location = new Location(1,2,3);
	
	@Before
	public void setUp(){
		item = new Item("Some Item", "Some Description", location, 10);
	}
	
	// Name Tests
	@Test
	public void testGetName() {
		assertTrue(item.getName().equals("Some Item"));
	}
	
	@Test
	public void testSetName() {
		item.setName("New Item");
		assertTrue(item.getName().equals("New Item"));
	}
	
	// Description Tests
	@Test
	public void testGetDescription() {
		assertTrue(item.getDescription().equals("Some Description"));
	}
	
	@Test
	public void testSetDescription() {
		item.setDescript("New Description");
		assertTrue(item.getDescription().equals("New Description"));
	}
	
	// XYZ Tests
	@Test
	public void testGetX() {
		assertTrue(item.getX().equals(1));
	}
	
	@Test
	public void testSetX() {
		item.setX(4);
		assertTrue(item.getX().equals(4));
	}
	
	@Test
	public void testGetY() {
		assertTrue(item.getY().equals(2));
	}
	
	@Test
	public void testSetY() {
		item.setY(5);
		assertTrue(item.getY().equals(5));
	}
	
	@Test
	public void testGetZ() {
		assertTrue(item.getZ().equals(3));
	}
	
	@Test
	public void testSetZ() {
		item.setZ(6);
		assertTrue(item.getZ().equals(6));
	}
	
	@Test
	public void testGetLocation(){
		assertTrue(item.getLocation().equals(location));
	}
	
	@Test
	public void testSetLocation(){
		Location location2 = new Location(2,2,2);
		item.setLocation(location2);
		assertTrue(item.getLocation().equals(location2));
	}
	
	// New Tests that are not inherited from GameObject
	@Test
	public void testGetUses() {
		assertTrue(item.getUses().equals(10));
	}
	
	@Test
	public void testSetUses() {
		item.setUses(5);
		assertTrue(item.getUses().equals(5));
	}
	
	@Test
	public void testUse() {
		item.setUses(8);
		item.use();
		assertTrue(item.getUses().equals(7));
		item.use();
		assertTrue(item.getUses().equals(6));
	}
	
	@Test
	public void testGetInventoryItem(){
		assertEquals(item.getInventoryItem(), false);
	}
	
	@Test
	public void testSetInventoryItem(){
		item.setInventoryItem(true);
		assertEquals(item.getInventoryItem(), true);
	}
}