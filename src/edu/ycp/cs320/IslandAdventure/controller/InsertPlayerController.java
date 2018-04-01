package edu.ycp.cs320.IslandAdventure.controller;

import edu.ycp.cs320.IslandAdventure.persist.DatabaseProvider;
import edu.ycp.cs320.IslandAdventure.persist.DerbyDatabase;
import edu.ycp.cs320.IslandAdventure.persist.IDatabase;

public class InsertPlayerController 
{
	private IDatabase db = null;

	public InsertPlayerController() 
	{
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
	}

	public Integer insertPlayer(String user, Integer score, Integer health, Integer stamina, Integer time,
			Integer x, Integer y, Integer z) 
	{
		// insert new player into DB
		Integer player_id = db.addPlayer(user, score, health, stamina, time, x, y, z);

		// check if the insertion succeeded
		if (player_id > 0)
		{
			System.out.println("New player (ID: " + player_id + ") successfully added to Players table");
			
			return player_id;
		}
		else
		{
			System.out.println("Failed to insert new player (ID: " + player_id + ") into Players table");
			
			return null;
		}
	}
}
