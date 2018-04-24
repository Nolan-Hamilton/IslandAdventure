package edu.ycp.cs320.IslandAdventure.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import edu.ycp.cs320.IslandAdventure.model.Account;
import edu.ycp.cs320.IslandAdventure.model.Enemy;
import edu.ycp.cs320.IslandAdventure.model.Location;
import edu.ycp.cs320.IslandAdventure.model.Player;

public class EventController 
{
	public EventController() 
	{
	}
	Random rand = new Random();
	
	FightController fightController = new FightController();
	
	public Enemy createEnemy(Player player)
	{	
		int  n = rand.nextInt(50) + 1;	//random # from 1-50
		
		if (n > 0)
		{
			Enemy spider = new Enemy("Giant Spider", "A Deadly Spider as Large as a Human", 
					100, player.getLocation(), 20);
			return spider;
		}
		else
		{
			return null;
		}
	}
	public String sleepEvent(Player player, int n) 
	{
		String sleepEvent = "";

		if (n > 10 && n < 20)
		{
			player.setStamina(100);
			player.changeTime(8);	//Player sleeps 8 hours
			sleepEvent = "You could have slept better but you feel slightly rejuvenated.";
		}
		else if (n >= 20)
		{
			sleepEvent += "You awake to a creature ready to attack!";
			Enemy enemy = createEnemy(player);
			sleepEvent += fightController.Fight(player, enemy);
		}
		else
		{
			player.setStamina(100);
			player.setHealth(100);
			player.changeTime(8);	//Player sleeps 8 hours
			sleepEvent = "You got a good night of sleep.";
		}
		return sleepEvent;
	}
	public String moveEvent(Account account)
	{
		String moveEvent = "";
		Player player = account.getPlayer();
		Location location = player.getLocation();
		ArrayList<Enemy> enemyList = account.getEnemiesByXYZ(location.getX(), location.getY(), location.getZ());
		if (!enemyList.isEmpty())
		{
			Enemy enemy = enemyList.get(0);	// Get first enemy if enemyList isn't empty
			moveEvent += fightController.Fight(player, enemy);
		}
		return moveEvent;
	}
}
