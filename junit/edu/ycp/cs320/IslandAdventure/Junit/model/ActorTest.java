package edu.ycp.cs320.IslandAdventure.Junit.model;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.IslandAdventure.model.Actor;
import edu.ycp.cs320.IslandAdventure.model.Location;

public class ActorTest 
{
	private Actor actor;
	
	@Before
	public void setUp()
	{
		Location location = new Location(1,2,3);
		actor = new Actor("Some Actor", "Some Description", 50, location);
	}
	
	// Name Tests
	@Test
	public void testGetName() 
	{
		assertTrue(actor.getName().equals("Some Actor"));
	}
	
	@Test
	public void testSetName() 
	{
		actor.setName("New Name");
		assertTrue(actor.getName().equals("New Name"));
	}
	
	// Description Tests
	@Test
	public void testGetDescription() 
	{
		assertTrue(actor.getDescription().equals("Some Description"));
	}
	
	@Test
	public void testSetDescription() {
		actor.setDescript("New Description");
		assertTrue(actor.getDescription().equals("New Description"));
	}
	
	// XYZ Tests
	@Test
	public void testGetX() 
	{
		assertTrue(actor.getX().equals(1));
	}
	
	@Test
	public void testSetX() {
		actor.setX(4);
		assertTrue(actor.getX().equals(4));
	}
	
	@Test
	public void testGetY() {
		assertTrue(actor.getY().equals(2));
	}
	
	@Test
	public void testSetY() {
		actor.setY(5);
		assertTrue(actor.getY().equals(5));
	}
	
	@Test
	public void testGetZ() {
		assertTrue(actor.getZ().equals(3));
	}
	
	@Test
	public void testSetZ() {
		actor.setZ(6);
		assertTrue(actor.getZ().equals(6));
	}
	
	// New Tests that are not inherited from GameObject
	@Test
	public void testGetHealth() {
		assertTrue(actor.getHealth().equals(50));
	}
	
	@Test
	public void testSetHealth() {
		actor.setHealth(75);
		assertTrue(actor.getHealth().equals(75));
	}
	
	@Test
	public void testChangeHealth() 
	{
		actor.setHealth(80);
		actor.changeHealth(-20);
		assertTrue(actor.getHealth().equals(60));
		actor.changeHealth(50);
		assertTrue(actor.getHealth().equals(110));
	}
}
