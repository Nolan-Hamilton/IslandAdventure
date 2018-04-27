package edu.ycp.cs320.IslandAdventure.Junit.model;

/* These are the jUnit tests for Weapon class. */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.IslandAdventure.model.*;

public class WeaponTest {
	private Weapon weapon;
	private Location location = new Location(1,2,3);
	
	@Before
	public void setUp(){
		weapon = new Weapon("Wood Weapon", "Weak weapon made from wood", location, 20);
	}
	
	// Name Tests
	@Test
	public void testGetName() 
	{
		assertTrue(weapon.getName().equals("Wood Weapon"));
	}
	
	@Test
	public void testSetName() 
	{
		weapon.setName("Steel Weapon");
		assertTrue(weapon.getName().equals("Steel Weapon"));
	}
	
	// Description Tests
	@Test
	public void testGetDescription() 
	{
		assertTrue(weapon.getDescription().equals("Weak weapon made from wood"));
	}
	
	@Test
	public void testSetDescription() {
		weapon.setDescript("Weak weapon made from steel");
		assertTrue(weapon.getDescription().equals("Weak weapon made from steel"));
	}
	
	// XYZ Tests
	@Test
	public void testGetX() 
	{
		assertTrue(weapon.getX().equals(1));
	}
	
	@Test
	public void testSetX() 
	{
		weapon.setX(4);
		assertTrue(weapon.getX().equals(4));
	}
	
	@Test
	public void testGetY() 
	{
		assertTrue(weapon.getY().equals(2));
	}
	
	@Test
	public void testSetY() 
	{
		weapon.setY(5);
		assertTrue(weapon.getY().equals(5));
	}
	
	@Test
	public void testGetZ() 
	{
		assertTrue(weapon.getZ().equals(3));
	}
	
	@Test
	public void testSetZ() 
	{
		weapon.setZ(6);
		assertTrue(weapon.getZ().equals(6));
	}
	
	@Test
	public void testGetLocation()
	{
		assertTrue(weapon.getLocation().equals(location));
	}
	
	@Test
	public void testSetLocation()
	{
		Location location2 = new Location(2,2,2);
		weapon.setLocation(location2);
		assertTrue(weapon.getLocation().equals(location2));
	}
	
	// New Tests that are not inherited from GameObject
	@Test
	public void testGetWeaponAmount() 
	{
		assertTrue(weapon.getDamage().equals(20));
	}
	
	@Test
	public void testSetWeaponAmount() 
	{
		weapon.setDamage(50);
		assertTrue(weapon.getDamage().equals(50));
	}
}