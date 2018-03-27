package edu.ycp.cs320.IslandAdventure.Junit.model;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.IslandAdventure.model.Enemy;

public class EnemyTest 
{
	private Enemy enemy;
	
	@Before
	public void setUp()
	{
		enemy = new Enemy("Giant Spider", "Spider but larger", 1,2,3, 50, 10);
	}
	
	// Name Tests
	@Test
	public void testGetName() 
	{
		assertTrue(enemy.getName().equals("Giant Spider"));
	}
	
	@Test
	public void testSetName() 
	{
		enemy.setName("Wolf");
		assertTrue(enemy.getName().equals("Wolf"));
	}
	
	// Description Tests
	@Test
	public void testGetDescription() 
	{
		assertTrue(enemy.getDescription().equals("Spider but larger"));
	}
	
	@Test
	public void testSetDescription() {
		enemy.setDescript("Larger Dog");
		assertTrue(enemy.getDescription().equals("Larger Dog"));
	}
	
	// XYZ Tests
	@Test
	public void testGetX() 
	{
		assertTrue(enemy.getX().equals(1));
	}
	
	@Test
	public void testSetX() 
	{
		enemy.setX(4);
		assertTrue(enemy.getX().equals(4));
	}
	
	@Test
	public void testGetY() 
	{
		assertTrue(enemy.getY().equals(2));
	}
	
	@Test
	public void testSetY() 
	{
		enemy.setY(5);
		assertTrue(enemy.getY().equals(5));
	}
	
	@Test
	public void testGetZ() 
	{
		assertTrue(enemy.getZ().equals(3));
	}
	
	@Test
	public void testSetZ() 
	{
		enemy.setZ(6);
		assertTrue(enemy.getZ().equals(6));
	}
	
	// New Tests that are not inherited from GameObject
	@Test
	public void testGetDamage() 
	{
		assertTrue(enemy.getDamage().equals(10));
	}
	
	@Test
	public void testSetHealth() 
	{
		enemy.setHealth(20);
		assertTrue(enemy.getHealth().equals(20));
	}
	
	@Test
	public void testGetHealth() 
	{
		assertTrue(enemy.getHealth().equals(50));
	}
	
	@Test
	public void testChangeHealth() 
	{
		enemy.setHealth(80);
		enemy.changeHealth(-20);
		assertTrue(enemy.getHealth().equals(60));
		enemy.changeHealth(50);
		assertTrue(enemy.getHealth().equals(110));
	}
}
