package edu.ycp.cs320.IslandAdventure.persist;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.IslandAdventure.model.Account;
import edu.ycp.cs320.IslandAdventure.model.Player;
import edu.ycp.cs320.IslandAdventure.model.Room;
import edu.ycp.cs320.IslandAdventure.model.Inventory;
import edu.ycp.cs320.IslandAdventure.model.Item;
import edu.ycp.cs320.IslandAdventure.model.Location;
import edu.ycp.cs320.IslandAdventure.model.GameObject;

public interface IDatabase {
	// Retrieve an account with data if given a username
	public Account retrieveAccount(String username);

	public Integer addPlayer(String user, Integer score, Integer health, Integer stamina,
			Integer time, Integer x, Integer y, Integer z, int account_id, 
			Integer woodCuttingXP, Integer fishingXP, Integer combatXP, Integer craftingXP);
	
	public Player getPlayer(String user);

	public Player getPlayer(int playerID);

	public Integer insertAccountIntoAccountTable(Account account);

	public Integer getAccountIdFromDatabase(String username);

	public Boolean updatePlayerInDatabase(int account_id, Player player);

	public Boolean insertRoomsIntoDatabase(int account_id, String username, ArrayList<Room> list);

	public boolean updateMapInDatabase(int account_id, Account account);

	public ArrayList<Room> loadMapFromDatabase(int account_id);
	
	public Boolean insertItemIntoDatabase(Integer account_id, Integer inventoryItem, String name, String description, 
			Integer uses, Integer amount, Integer x, Integer y, Integer z, Integer damage);

	public Account getItemList(Integer account_id, Account account);

	public boolean updateItemsInDatabase(int account_id, Account account);
	
	public Account updateItemList(Integer account_id, Account account);

	public Boolean moveItemInventory(Integer account_id, Integer inventoryItem, String name);
	
	public Boolean updateItemAmount(Integer account_id, String name, Integer amount);
	
	public Boolean insertEnemyIntoDatabase(Integer account_id, String name, String description, 
			Integer health, Integer damage, Integer x, Integer y, Integer z);
	
	public Account updateEnemyList(Integer account_id, Account account);

	public Boolean removeEnemy(Integer account_id, String name, String description, Integer x, Integer y, Integer z);
	
	public Boolean updateItemLocation(int account_id, String name, int x, int y, int z);

	public void loadInitialData(int account_id, String username);
}
