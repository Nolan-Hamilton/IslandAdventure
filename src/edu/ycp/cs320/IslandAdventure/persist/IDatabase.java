package edu.ycp.cs320.IslandAdventure.persist;

import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.IslandAdventure.model.Account;
import edu.ycp.cs320.IslandAdventure.model.Player;
import edu.ycp.cs320.IslandAdventure.model.Room;
import edu.ycp.cs320.IslandAdventure.model.Inventory;
import edu.ycp.cs320.IslandAdventure.model.Location;
import edu.ycp.cs320.IslandAdventure.model.GameObject;

public interface IDatabase {
	// Retrieve an account with data if given a username
	public Account retrieveAccount(String username);

	public Integer addPlayer(String user, Integer score, Integer health, Integer stamina,
			Integer time, Integer x, Integer y, Integer z, int account_id);
	
	public Player getPlayer(String user);

	public Player getPlayer(int playerID);

	public Integer insertAccountIntoAccountTable(Account account);

	public Integer getAccountIdFromDatabase(String username);

	public Boolean updatePlayerInDatabase(int account_id, Player player);

	public Boolean insertRoomsIntoDatabase(int account_id, String username, ArrayList<Room> list);

	public boolean updateMapInDatabase(int account_id, Account account);
}
