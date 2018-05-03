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
		int armor = 0;
		int damage = 0;
		int enemyOriginalHealth = enemy.getHealth();

		while (player.getHealth() > 0 && enemy.getHealth() > 0 )
		{
			if (player.getArmor() != null)
			{
				armor = player.getArmor().getArmorAmount();
			}
			if (player.getWeapon() != null)
			{
				damage = player.getWeapon().getDamage();
			}
			enemy.changeHealth(-((player.getSkills().getCombatXP()/100)+damage+10));
			
			playerHealthLost += (enemy.getDamage() - ((player.getSkills().getCombatXP()/100)+armor));
			if (playerHealthLost <= 0)	//Player always loses at least one health point
			{
				playerHealthLost -= (enemy.getDamage() - ((player.getSkills().getCombatXP()/100)+armor));
				playerHealthLost += 1;
			}
			player.modifyHealth(-playerHealthLost);
		}
		if (player.getHealth() > 0)	// Player won
		{
			combatXPGained += (enemyOriginalHealth*enemy.getDamage())/100; // Stronger enemies give more xp
			if (combatXPGained < 1)	// Player still gets some xp even for fighting weak enemies
			{
				combatXPGained = 1;
			}
			player.getSkills().addCombatXP(combatXPGained);
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
