package edu.ycp.cs320.IslandAdventure.model;

public class Player 
{
	private int score;
	private int health;
	private int stamina;
	private int time;
	private Inventory inventory;
	private Location location;
	private Skills skills;
	
	public Player(int score, int health, int stamina, int time, Inventory inventory, Location location, Skills skills)
	{
		this.score = score;
		this.health = health;
		this.stamina = stamina;
		this.time = time;
		this.inventory = inventory;
		this.location = location;
		this.skills = skills;
	}
	
	public int getScore()
	{
		return score;
	}
	public void addScore(int scoreToAdd)
	{
		score = score + scoreToAdd;	// Returns current score plus score to add
	}
	
	public int getHealth()
	{
		return health;
	}
	public void modifyHealth(int healthChange)
	{
		health = health + healthChange;	// Health change can be positive or negative
	}
	
	public int getStamina()
	{
		return stamina;
	}
	public void modifyStamina(int staminaChange)
	{
		stamina = stamina + staminaChange;	// Stamina change can be positive or negative
	}
	
	public int getTime()
	{
		return time;
	}
	public void changeTime(int timeChange)
	{
		time = time + timeChange;	// Time can be from 0 to 24. Each activity takes a # of hours
	}
	
	public Boolean hasItem(String item)
	{
		Integer count = inventory.getItemCount(item);
		if (count != null)
		{
			if (count > 0)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	public int getItemCount(String item)
	{
		return inventory.getItemCount(item);
	}
	public Inventory getInventory()
	{
		return inventory;
	}

	public Location getLocation()
	{
		return location;
	}
	public void changeLocation(Location location)
	{
		this.location = location;
	}
	
	public Skills getSkills()
	{
		return skills;
	}
}
