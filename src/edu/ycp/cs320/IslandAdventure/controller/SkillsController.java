package edu.ycp.cs320.IslandAdventure.controller;

import edu.ycp.cs320.IslandAdventure.model.Skills;

public class SkillsController 
{
	public Skills createNewSkills()
	{
		Skills skills = new Skills(0, 0, 0, 0);
		return skills;
	}
}
