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
	}
	
	@Test
	public void testInterpretAction() 
	{
		actionController.interpretAction("Chop Wood");	//Adds 10 wood to player inventory.
		actionController.interpretAction("Chop Wood");
		assertTrue(player.getInventory().getItemCountFromString("Wood") == 20);
		
		actionController.interpretAction("Move North");
		assertTrue(player.getLocation().getY() == 11);
	}

}
