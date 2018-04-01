package edu.ycp.cs320.IslandAdventure.controller;

import java.util.Random;

import edu.ycp.cs320.IslandAdventure.model.Enemy;
import edu.ycp.cs320.IslandAdventure.model.Player;

public class EventController 
{
	public EventController() 
	{
	}
	Random rand = new Random();

	int  n = rand.nextInt(50) + 1;	//random # from 1-50
	
	public Enemy createEnemy(Player player)
	{	
		if (n > 0)
		{
			Enemy spider = new Enemy("Giant Spider", "A Deadly Spider as Large as a Human", 
					player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ(),
					100, player.getLocation(), 20);
			return spider;
		}
		else
		{
			return null;
		}
	}
}
