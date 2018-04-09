package edu.ycp.cs320.IslandAdventure.persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ycp.cs320.IslandAdventure.model.Account;
import edu.ycp.cs320.IslandAdventure.model.Inventory;
import edu.ycp.cs320.IslandAdventure.model.Player;
import edu.ycp.cs320.IslandAdventure.model.Location;
import edu.ycp.cs320.IslandAdventure.model.GameObject;

public class FakeDatabase implements IDatabase 
{
	private List<Account> accounts;
	
	public FakeDatabase() 
	{
		accounts = new ArrayList<Account>();
		
		// Add initial data (not yet implemented
		//readInitialData();
		
		//System.out.println(accounts.size() + " accounts");
	}
	
	@Override
	public Account retrieveAccount(String username) 
	{
		Account result = new Account(null, null, null);
		// Look for account and load its data
		for (Account account : accounts) 
		{
			if (account.getUsername().equals(username)) 
			{
				result = account;
			}
		}
		return result;
	}
	
	@Override
	public Integer addPlayer(String user, Integer score, Integer health, Integer stamina, Integer time,
			Integer x, Integer y, Integer z, int account_id)
	{
		throw new UnsupportedOperationException();
	}
	
	@Override
	public Player getPlayer(String user)
	{
		throw new UnsupportedOperationException();
	}
	
	public List<Account> getAccountList()
	{
		return this.accounts;
	}

	@Override
	public Integer insertAccountIntoAccountTable(Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Player getPlayer(Integer playerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAccountIdFromDatabase(String username) {
		// TODO Auto-generated method stub
		return null;
	}
}
