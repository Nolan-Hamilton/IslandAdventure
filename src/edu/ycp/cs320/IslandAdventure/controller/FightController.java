package edu.ycp.cs320.IslandAdventure.controller;

import edu.ycp.cs320.IslandAdventure.model.Account;
import edu.ycp.cs320.IslandAdventure.model.Enemy;
import edu.ycp.cs320.IslandAdventure.model.Player;

public class FightController 
{
	GameEngine gameEngine = new GameEngine();
	
	public FightController() 
	{
	}
	
	public String Fight(Account account, Enemy enemy)
	{
		Player player = account.getPlayer();
		String battleDescription;
		int playerHealthLost = 0;
		int combatXPGained = 0;
		
		while (player.getHealth() > 0 && enemy.getHealth() > 0 )
		{
			enemy.changeHealth(-30);
			player.modifyHealth(-10);
			playerHealthLost += 10;
		}
		if (player.getHealth() > 0)
		{
			player.getSkills().addCombatXP(10);
			combatXPGained += 10;
			battleDescription = "You fought a " + enemy.getName() + " and defeated it! You took " + 
					playerHealthLost + " damage and gained " + combatXPGained + " combat XP!";
			gameEngine.removeEnemy(gameEngine.getAccountID(account.getUsername()), enemy);
			account.getEnemyList().remove(enemy);
		}
		else
		{
			battleDescription = "You fought a " + enemy.getName() + " and died!";
		}
		
		return battleDescription;
	}
}
