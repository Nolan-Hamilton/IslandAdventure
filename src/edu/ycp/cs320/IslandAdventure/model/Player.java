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
	public void setScore(int score)
	{
		this.score = score;
	}
	public void addScore(int scoreToAdd)
	{
		score = score + scoreToAdd;	// Returns current score plus score to add
	}
	
	public int getHealth()
	{
		return health;
	}
	public void setHealth(int health)
	{
		this.health = health;
	}
	public void modifyHealth(int healthChange)
	{
		health = health + healthChange;	// Health change can be positive or negative
	}
	
	public int getStamina()
	{
		return stamina;
	}
	public void setStamina(int stamina)
	{
		this.stamina = stamina;
	}
	public void modifyStamina(int staminaChange)
	{
		stamina = stamina + staminaChange;	// Stamina change can be positive or negative
	}
	
	public int getTime()
	{
		return time;
	}
	public void setTime(int time)
	{
		this.time = time;
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
	public void setX(int x)
	{
		location.setX(x);
	}
	public void setY(int y)
	{
		location.setX(y);
	}
	public void setZ(int z)
	{
		location.setX(z);
	}
	
	public Skills getSkills()
	{
		return skills;
	}
}
