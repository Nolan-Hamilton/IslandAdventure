package edu.ycp.cs320.IslandAdventure.Junit.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.IslandAdventure.controller.SkillsController;
import edu.ycp.cs320.IslandAdventure.model.Skills;

public class SkillsControllerTest 
{
	private Skills skills;
	private SkillsController skillsController = new SkillsController();
	
	@Before
	public void setUp()
	{
		skills = new Skills(0, 0, 0, 0);
	}
	
	@Test
	public void testCreateNewSkills() 
	{
		assertTrue(skillsController.createNewSkills().getCombatXP() == skills.getCombatXP());
		assertTrue(skillsController.createNewSkills().getFishingXP() == skills.getFishingXP());
		assertTrue(skillsController.createNewSkills().getWoodCuttingXP() == skills.getWoodCuttingXP());
		assertTrue(skillsController.createNewSkills().getCraftingXP() == skills.getCraftingXP());
	}
}
