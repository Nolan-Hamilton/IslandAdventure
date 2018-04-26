package edu.ycp.cs320.IslandAdventure.Junit.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.IslandAdventure.controller.EventController;
import edu.ycp.cs320.IslandAdventure.controller.PlayerController;
import edu.ycp.cs320.IslandAdventure.model.Account;
import edu.ycp.cs320.IslandAdventure.model.Player;

public class EventControllerTest 
{
	private PlayerController playerController = new PlayerController();
	private Player player;
	private Account account = new Account("Username", "Password", player);
	private EventController eventController;
	
	
	@Before
	public void setUp()
	{
		this.player = playerController.createNewPlayer();
		eventController = new EventController();
		account.initialize();
		account.setPlayer(player);
	}
	
	@Test
	public void testSleepEvent() 
	{
		eventController.sleepEvent(account, 5);
		assertTrue(player.getStamina() == 100);
		assertTrue(player.getHealth() == 100);
	}

}
