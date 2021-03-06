package edu.ycp.cs320.IslandAdventure.persist;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.IslandAdventure.model.Account;
import edu.ycp.cs320.IslandAdventure.model.Player;
import edu.ycp.cs320.IslandAdventure.model.Room;

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
			Integer x, Integer y, Integer z, int account_id, Integer woodCuttingXP, Integer fishingXP, Integer combatXP, Integer craftingXP)
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
	public Player getPlayer(int playerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAccountIdFromDatabase(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updatePlayerInDatabase(int account_id, Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean insertRoomsIntoDatabase(int account_id, String username, ArrayList<Room> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateMapInDatabase(int account_id, Account account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Room> loadMapFromDatabase(int account_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean insertItemIntoDatabase(Integer player_id, Integer inventoryItem, String name,
			String description, Integer uses, Integer amount, Integer x, Integer y, Integer z, Integer damage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account updateItemList(Integer account_id, Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean moveItemInventory(Integer account_id, Integer inventoryItem, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateItemsInDatabase(int account_id, Account account) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Account getItemList(Integer account_id, Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean updateItemAmount(Integer account_id, String name, Integer amount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean insertEnemyIntoDatabase(Integer account_id, String name, String description, Integer health,
			Integer damage, Integer x, Integer y, Integer z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account updateEnemyList(Integer account_id, Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean removeEnemy(Integer account_id, String name, String description, Integer x, Integer y, Integer z) 
	{		
		// TODO Auto-generated method stub
		return null;
	}

	public Boolean updateItemLocation(int account_id, String name, int x, int y, int z) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void loadInitialData(int account_id, String username) {
		// TODO Auto-generated method stub
		
	}


}
