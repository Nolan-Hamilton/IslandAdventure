package edu.ycp.cs320.IslandAdventure.controller;

import edu.ycp.cs320.IslandAdventure.model.Player;
import edu.ycp.cs320.IslandAdventure.persist.DatabaseProvider;
import edu.ycp.cs320.IslandAdventure.persist.DerbyDatabase;
import edu.ycp.cs320.IslandAdventure.persist.IDatabase;

public class GetPlayerController 
{
	private IDatabase db = null;

	public GetPlayerController() 
	{
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
	}

	public Player getPlayer(String user) 
	{
		// insert new player into DB
		Player player = db.getPlayer(user);
		return player;
	}
}
