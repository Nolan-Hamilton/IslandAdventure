package edu.ycp.cs320.IslandAdventure.model;

public class Skills 
{
	int woodCuttingXP;
	int fishingXP;
	int combatXP;
	int craftingXP;
	
	public Skills(int woodCuttingXP, int fishingXP, int combatXP, int craftingXP)
	{
		this.woodCuttingXP = woodCuttingXP;
		this.fishingXP = fishingXP;
		this.combatXP = combatXP;
		this.craftingXP = craftingXP;
	}
	
	public int getWoodCuttingXP()
	{
		return woodCuttingXP;
	}
	public void addWoodCuttingXP(int XP)
	{
		woodCuttingXP = woodCuttingXP + XP;
	}
	public void setWoodCuttingXP(int XP)
	{
		woodCuttingXP = XP;
	}
	
	public int getFishingXP()
	{
		return fishingXP;
	}
	public void addFishingXP(int XP)
	{
		fishingXP = fishingXP + XP;
	}
	public void setFishingXP(int XP)
	{
		fishingXP = XP;
	}
	
	public int getCombatXP()
	{
		return combatXP;
	}
	public void addCombatXP(int XP)
	{
		combatXP = combatXP + XP;
	}
	public void setCombatXP(int XP)
	{
		combatXP = XP;
	}
	
	public int getCraftingXP()
	{
		return craftingXP;
	}
	public void addCraftingXP(int XP)
	{
		craftingXP = craftingXP + XP;
	}
	public void setCraftingXP(int XP)
	{
		craftingXP = XP;
	}
}
