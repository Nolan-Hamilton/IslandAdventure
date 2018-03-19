package edu.ycp.cs320.IslandAdventure.Junit.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.IslandAdventure.model.Skills;

public class SkillsTest 
{
	private Skills skills;
	
	@Before
	public void setUp()
	{
		skills = new Skills(0, 5, 10, 15);
	}
	
	@Test
	public void testGetWoodCuttingXP() 
	{
		assertTrue(skills.getWoodCuttingXP() == 0);
	}
	
	@Test
	public void testAddWoodCuttingXP()
	{
		skills.addWoodCuttingXP(17);
		assertTrue(skills.getWoodCuttingXP() == 17);
	}
	
	@Test
	public void testGetFishingXP() 
	{
		assertTrue(skills.getFishingXP() == 5);
	}
	
	@Test
	public void testAddFishingXP()
	{
		skills.addFishingXP(35);
		assertTrue(skills.getFishingXP() == 40);
	}
	
	@Test
	public void testGetCombatXP() 
	{
		assertTrue(skills.getCombatXP() == 10);
	}
	
	@Test
	public void testAddCombatXP()
	{
		skills.addCombatXP(10);
		assertTrue(skills.getCombatXP() == 20);
	}
	
	@Test
	public void testGetCraftingXP() 
	{
		assertTrue(skills.getCraftingXP() == 15);
	}
	
	@Test
	public void testAddCraftingXP()
	{
		skills.addCraftingXP(1);
		assertTrue(skills.getCraftingXP() == 16);
	}

}
