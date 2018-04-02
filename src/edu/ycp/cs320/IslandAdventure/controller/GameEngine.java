package edu.ycp.cs320.IslandAdventure.controller;

import edu.ycp.cs320.IslandAdventure.persist.DatabaseProvider;
import edu.ycp.cs320.IslandAdventure.persist.DerbyDatabase;
import edu.ycp.cs320.IslandAdventure.persist.FakeDatabase;
import edu.ycp.cs320.IslandAdventure.persist.IDatabase;
import edu.ycp.cs320.IslandAdventure.model.*;

public class GameEngine 
{
	
	//if action go to action controller
	
	//return resulting string to index servlet
	
	private IDatabase db    = null;

	public GameEngine() {
		
		// creating DB instance here
		DatabaseProvider.setInstance(new DerbyDatabase());
		db = DatabaseProvider.getInstance();		
	}
	
	public boolean insertNewAccountIntoDatabase(Account account) {
		// Check to see if username is already taken
		Account found = null;
		found = db.retrieveAccount(account.getUsername());
		if (found != null) {
			System.out.println("Failed to insert account for <" + account.getUsername() + "> because username is already taken");
			
			return false;
		}
		// Create new account
		Integer account_id = db.insertAccountIntoAccountTable(account);
		if (account_id > 0)
		{
			System.out.println("New book (ID: " + account_id + ") successfully added to accounts table: <" + account.getUsername() + ">");
			// Create Player using account_id
			db.addPlayer(account.getUsername(), account.getPlayer().getScore(), account.getPlayer().getHealth(), account.getPlayer().getStamina(), 
					account.getPlayer().getTime(), account.getPlayer().getLocation().getX(), account.getPlayer().getLocation().getY(), 
					account.getPlayer().getLocation().getZ());
			// Create Objects using account_id
			// Create Locations using accountID
			return true;
		}
		else
		{
			System.out.println("Failed to insert new book (ID: " + account_id + ") into Books table: <" + account.getUsername() + ">");
			
			return false;
		}
	}
}
