package edu.ycp.cs320.IslandAdventure.Junit.model;

/* These are the jUnit tests for Armor class. */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.IslandAdventure.model.*;

public class ArmorTest {
	private Armor armor;
	private Location location = new Location(1,2,3);
	
	@Before
	public void setUp(){
		armor = new Armor("Wood Armor", "Weak armor made from wood", location, 20);
	}
	
	// Name Tests
	@Test
	public void testGetName() 
	{
		assertTrue(armor.getName().equals("Wood Armor"));
	}
	
	@Test
	public void testSetName() 
	{
		armor.setName("Steel Armor");
		assertTrue(armor.getName().equals("Steel Armor"));
	}
	
	// Description Tests
	@Test
	public void testGetDescription() 
	{
		assertTrue(armor.getDescription().equals("Weak armor made from wood"));
	}
	
	@Test
	public void testSetDescription() {
		armor.setDescript("Weak armor made from steel");
		assertTrue(armor.getDescription().equals("Weak armor made from steel"));
	}
	
	// XYZ Tests
	@Test
	public void testGetX() 
	{
		assertTrue(armor.getX().equals(1));
	}
	
	@Test
	public void testSetX() 
	{
		armor.setX(4);
		assertTrue(armor.getX().equals(4));
	}
	
	@Test
	public void testGetY() 
	{
		assertTrue(armor.getY().equals(2));
	}
	
	@Test
	public void testSetY() 
	{
		armor.setY(5);
		assertTrue(armor.getY().equals(5));
	}
	
	@Test
	public void testGetZ() 
	{
		assertTrue(armor.getZ().equals(3));
	}
	
	@Test
	public void testSetZ() 
	{
		armor.setZ(6);
		assertTrue(armor.getZ().equals(6));
	}
	
	@Test
	public void testGetLocation()
	{
		assertTrue(armor.getLocation().equals(location));
	}
	
	@Test
	public void testSetLocation()
	{
		Location location2 = new Location(2,2,2);
		armor.setLocation(location2);
		assertTrue(armor.getLocation().equals(location2));
	}
	
	// New Tests that are not inherited from GameObject
	@Test
	public void testGetArmorAmount() 
	{
		assertTrue(armor.getArmorAmount().equals(20));
	}
	
	@Test
	public void testSetArmorAmount() 
	{
		armor.setArmorAmount(50);
		assertTrue(armor.getArmorAmount().equals(50));
	}
}