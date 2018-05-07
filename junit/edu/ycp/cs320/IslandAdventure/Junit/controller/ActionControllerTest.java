package edu.ycp.cs320.IslandAdventure.Junit.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.IslandAdventure.controller.ActionController;
import edu.ycp.cs320.IslandAdventure.controller.PlayerController;
import edu.ycp.cs320.IslandAdventure.model.Account;
import edu.ycp.cs320.IslandAdventure.model.Item;
import edu.ycp.cs320.IslandAdventure.model.Player;

public class ActionControllerTest 
{
	private PlayerController playerController = new PlayerController();
	private Player player;
	private Account account = new Account("Username", "Password", player);
	private ActionController actionController;
	
	@Before
	public void setUp()
	{
		this.player = playerController.createNewPlayer();
		actionController = new ActionController(player, account);
		account.initialize();
		account.setPlayer(player);
	}
	
	@Test
	public void testInterpretAction() 
	{
		actionController.interpretAction("Chop Wood");	//Adds wood based on player inventory and xp.
		actionController.interpretAction("Chop Wood");
		assertTrue(player.getInventory().getItemCountFromString("Wood") == 2);
		
		actionController.interpretAction("Move North");
		assertTrue(player.getLocation().getY() == 2);
		
		player.setHealth(90);
		actionController.interpretAction("Eat FIsh");
		assertTrue(player.getHealth() == 90);	// Player health doesn't increase if no fish in inventory
		
		Item Fish = new Item("Fish", "Fish", player.getLocation(), 0);
		player.getInventory().addItem(Fish, 5);
		actionController.interpretAction("Eat FIsh");
		actionController.interpretAction("Eat FIsh");
		assertTrue(player.getHealth() == 100);	// Player should be healed but health doesn't exceed 100
	}
}
