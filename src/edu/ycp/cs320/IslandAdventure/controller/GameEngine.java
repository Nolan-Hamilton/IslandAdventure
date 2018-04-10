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
			System.out.println("New account (ID: " + account_id + ") successfully added to accounts table: <" + account.getUsername() + ">");
			
			// Create Player using account_id
			int player_id = db.addPlayer(account.getUsername(), account.getPlayer().getScore(), account.getPlayer().getHealth(), account.getPlayer().getStamina(), 
					account.getPlayer().getTime(), account.getPlayer().getLocation().getX(), account.getPlayer().getLocation().getY(), 
					account.getPlayer().getLocation().getZ(), account_id);
			
			System.out.println("New Player added, Player_id: <" + player_id + ">");
			// Create Objects using account_id
			// Create Locations using accountID
			return true;
		}
		else
		{
			System.out.println("Failed to insert new account (ID: " + account_id + ") into accounts table: <" + account.getUsername() + ">");
			
			return false;
		}
	}
	
	public boolean verifyAccountInDatabase(Account account) {
		Account found = null;
		found = db.retrieveAccount(account.getUsername());
		if (found != null){
			if (found.getUsername().equals(account.getUsername()) && found.getPassword().equals(account.getPassword())) {
				System.out.println("Account varified for " + account.getUsername());
				return true;
			}else{
				System.out.println("Invalid username and/or password.");
				return false;
			}
		}else{
			System.out.println("Account not found for " + account.getUsername());
			return false;
		}
	}
	
	// Method for getting account_id
	public Integer getAccountID(String username){
		Integer account_id = null;
		
		account_id = db.getAccountIdFromDatabase(username);
		System.out.println("GameEngine >> Account_id # <" + account_id + "> found from <" + username + ">");
		
		return account_id;
	}
	
	// Method for saving player information to database
	public boolean updatePlayerInDatabase(int account_id, Player player) {
		return db.updatePlayerInDatabase(account_id, player);
	}
	
	// Method to load a player when re-entering the game
	public Player loadPlayer(int account_id){
		return db.getPlayer(account_id);
	}
}
