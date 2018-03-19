package edu.ycp.cs320.IslandAdventure.persist;

import java.util.List;

import edu.ycp.cs320.IslandAdventure.model.Account;
import edu.ycp.cs320.IslandAdventure.model.Player;
import edu.ycp.cs320.IslandAdventure.model.Inventory;
import edu.ycp.cs320.IslandAdventure.model.Location;
import edu.ycp.cs320.IslandAdventure.model.GameObject;

public interface IDatabase {
	// Retrieve an account with data if given a username
	public Account retrieveAccount(String username);
	
}
