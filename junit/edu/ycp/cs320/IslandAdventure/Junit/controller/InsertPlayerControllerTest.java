package edu.ycp.cs320.IslandAdventure.Junit.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.IslandAdventure.controller.GetPlayerController;
import edu.ycp.cs320.IslandAdventure.controller.InsertPlayerController;
import edu.ycp.cs320.IslandAdventure.persist.DatabaseProvider;
import edu.ycp.cs320.IslandAdventure.persist.DerbyDatabase;
import edu.ycp.cs320.IslandAdventure.persist.IDatabase;

public class InsertPlayerControllerTest 
{
	private IDatabase db = null;
	private InsertPlayerController insertPlayerController;
	
	@Before
	public void setUp()
	{
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		this.db = DatabaseProvider.getInstance();
		insertPlayerController = new InsertPlayerController();
	}
	
	@Test
	public void testInsertPlayer() 
	{
		Integer playerID = insertPlayerController.insertPlayer("Bob", 75, 50, 50, 10, 9, 9, 0);
		GetPlayerController getPlayer = new GetPlayerController();
		assertTrue(getPlayer.getPlayer("Bob").getScore() == 75);
	}

}
