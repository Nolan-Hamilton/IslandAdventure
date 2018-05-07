package edu.ycp.cs320.IslandAdventure.controller;

import java.util.ArrayList;
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
		
		if (n < 30)
		{
			Enemy spider = new Enemy("Giant Spider", "A Deadly Spider as Large as a Human", 
					50, player.getLocation(), 15);
			return spider;
		}
		else if (n >= 30) 
		{
			Enemy crocodile = new Enemy("Crocodile", "An angry and hungry crocodile", 
					80, player.getLocation(), 10);
			return crocodile;
		}
		else
		{
			return null;
		}
	}
	public String sleepEvent(Account account, int n) 
	{
		Player player = account.getPlayer();
		
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
			sleepEvent += fightController.Fight(account, enemy);
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
		Location riddle1 = new Location(6,7,1);
		Location riddle2 = new Location(8,5,1);
		if (player.getLocation().equals(riddle1))
		{
			moveEvent += "You enter a room and a mystical darkness keeps you from moving further. "
					+ "You need an even stronger light source. There is an ominious riddle written "
					+ "on the stone in front of you. It says, "
					+ "\"My life is measured in hours. I serve by being devoured. "
					+ "Thin I am quick, Fat I am slow. Wind is my foe. What am I?\" ";
			return moveEvent;
		}
		else if (player.getLocation().equals(riddle2))
		{
			moveEvent += "The cave tunnels branch into dozens of paths. You suddenly feel completely lost. "
					+ "Panic sets in and you realize you can't progress farther without knowing the way."
					+ " You realize there is another riddle written just behind you. It says, "
					+ "\"I Have Cities, But no Houses. I Have Mountains, But no Trees. "
					+ "I Have Water, But no Fish. What Am I?\" ";
		}
		else if (!enemyList.isEmpty())
		{
			Enemy enemy = enemyList.get(0);	// Get first enemy if enemyList isn't empty
			moveEvent += fightController.Fight(account, enemy);
		}
		return moveEvent;
	}
}
