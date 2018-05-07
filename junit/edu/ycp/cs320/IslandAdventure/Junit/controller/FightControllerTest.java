package edu.ycp.cs320.IslandAdventure.Junit.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.IslandAdventure.controller.FightController;
import edu.ycp.cs320.IslandAdventure.controller.PlayerController;
import edu.ycp.cs320.IslandAdventure.model.Account;
import edu.ycp.cs320.IslandAdventure.model.Armor;
import edu.ycp.cs320.IslandAdventure.model.Enemy;
import edu.ycp.cs320.IslandAdventure.model.Player;
import edu.ycp.cs320.IslandAdventure.model.Weapon;

public class FightControllerTest {

	private PlayerController playerController = new PlayerController();
	private Player player;
	private Account account = new Account("Username", "Password", player);
	private FightController fightController;

	@Before
	public void setUp()
	{
		this.player = playerController.createNewPlayer();
		fightController = new FightController();
		account.initialize();
		account.setPlayer(player);
	}
	
	@Test
	public void testFight() 
	{
		Enemy bug = new Enemy("Bug", "A little bug", 10, player.getLocation(), 2);
		Enemy gorilla = new Enemy("Gorilla", "A giant enraged gorilla", 60, player.getLocation(), 50);
		
		fightController.Fight(account, bug);
		assertTrue(player.getHealth() > 0);
		assertTrue(player.getSkills().getCombatXP() == 1);
		player.setHealth(100);
		
		Armor steelArmor = new Armor("Steel Armor", "Steel Armor", player.getLocation(), 20);
		Weapon steelSword = new Weapon("Steel Sword", "Steel Sword", player.getLocation(), 20);
		
		String result = fightController.Fight(account, gorilla);
		assertTrue(result.equals("You fought a Gorilla and died!"));	//Player should die with no equipment
		player.setHealth(100);
		gorilla.setHealth(60);
		
		player.getInventory().addItem(steelArmor, 1);
		player.getInventory().addItem(steelSword, 1);
		
		player.equipArmor(steelArmor);
		player.equipWeapon(steelSword);
		
		fightController.Fight(account, gorilla);
		assertTrue(player.getHealth() > 0);	//Player should win with equipment
		assertTrue(player.getSkills().getCombatXP() == 31);	// 1 xp originally + (60*50)/100 = 1+30 = 31
	}

}
