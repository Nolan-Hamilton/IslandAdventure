package edu.ycp.cs320.IslandAdventure.persist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.IslandAdventure.model.Account;
import edu.ycp.cs320.IslandAdventure.model.Armor;
import edu.ycp.cs320.IslandAdventure.model.Enemy;
import edu.ycp.cs320.IslandAdventure.model.Inventory;
import edu.ycp.cs320.IslandAdventure.model.Item;
import edu.ycp.cs320.IslandAdventure.model.Location;
import edu.ycp.cs320.IslandAdventure.model.Player;
import edu.ycp.cs320.IslandAdventure.model.Room;
import edu.ycp.cs320.IslandAdventure.model.Skills;
import edu.ycp.cs320.IslandAdventure.model.Weapon;

// This code is heavily based off of DerbyDatabase.java from CS320_Lab06 by Prof. Hake.
public class DerbyDatabase implements IDatabase {
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load Derby driver");
		}
	}
	
	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;
	
	@Override
	public Account retrieveAccount(String username) {
		return executeTransaction(new Transaction<Account>() {
			@Override
			public Account execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				PreparedStatement stmt5 = null;
				PreparedStatement stmt6 = null;				
				
				ResultSet resultSet1 = null;
				ResultSet resultSet2 = null;
				ResultSet resultSet5 = null;				
				
				// for saving the account
				Account account = null;

				// **try to retrieve author_id (if it exists) from DB, for Author's full name, passed into query
				// Try to retrieve existing account to see if one is already made
				try {
					stmt1 = conn.prepareStatement(
							"select accounts.username, accounts.password from accounts "
							+ "  where accounts.username = ? "
					);
					stmt1.setString(1, username);
					//stmt1.setString(2, account.getPassword());
					//stmt1.setInt(3, playerID);
					//stmt1.setInt(4, mapID);
					
					// execute the update
					resultSet1 = stmt1.executeQuery(); 
					
					// get the precise schema of the tuples returned as the result of the query
					ResultSetMetaData resultSchema = stmt1.getMetaData();
					
					// iterate through the returned tuples, printing each one
					// count # of rows returned
					int rowsReturned = 0;
					
					ArrayList<String> resultList = new ArrayList<String>();
					
					// collect data
					while (resultSet1.next()) {
						for (int i = 1; i <= resultSchema.getColumnCount(); i++) {
							String obj = resultSet1.getObject(i).toString();
							resultList.add(obj);
							//System.out.println("account_id: " + account_id);
							if (i > 1) {
								//System.out.print(",");
							}
							//System.out.print(obj2.toString());
						}
						//System.out.println();
						
						// count # of rows returned
						rowsReturned++;	
					}
					
					// indicate if the query returned nothing
					if (rowsReturned == 0) {
						System.out.println("No rows returned that matched the query");
					}else{
						account = new Account(null, null, null);
						account.setUsername(resultList.get(0));
						account.setPassword(resultList.get(1));
						System.out.println("Username retrieved: " + account.getUsername());
						System.out.println("Password retrieved: " + account.getPassword());
					}
					/////////////////////////////////////////////////////////////////////////////////////////////////
				} finally {
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);					
					DBUtil.closeQuietly(resultSet2);
					DBUtil.closeQuietly(stmt3);					
					DBUtil.closeQuietly(stmt4);
					DBUtil.closeQuietly(resultSet5);
					DBUtil.closeQuietly(stmt5);
					DBUtil.closeQuietly(stmt6);
				}
				return account;
			}
		});
	}
	
	@Override
	public Integer insertAccountIntoAccountTable(Account account) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				PreparedStatement stmt5 = null;
				PreparedStatement stmt6 = null;				
				
				ResultSet resultSet1 = null;
				ResultSet resultSet2 = null;
				ResultSet resultSet5 = null;				
				
				// for saving author ID and book ID
				Integer account_id = -1;

				// **try to retrieve author_id (if it exists) from DB, for Author's full name, passed into query
				// Try to retrieve existing account to see if one is already made
				try {
					stmt1 = conn.prepareStatement(
							"INSERT INTO accounts (username, password) "
							+ "  VALUES (?, ?) "
					);
					stmt1.setString(1, account.getUsername());
					stmt1.setString(2, account.getPassword());
					//stmt1.setInt(3, playerID);
					//stmt1.setInt(4, mapID);
					
					// execute the update
					stmt1.executeUpdate(); // IF MANIPULATING DATABASE, MUST USE EXECUTE UPDATE!!!!!!!!!!!!!
					account_id = 1;
					
					stmt2 = conn.prepareStatement(
							"select accounts.account_id from accounts" +
							"  where accounts.username = ? "
					);
					stmt2.setString(1, account.getUsername());
					
					// execute the update
					resultSet2 = stmt2.executeQuery();
					
					
					// get the precise schema of the tuples returned as the result of the query
					ResultSetMetaData resultSchema2 = stmt2.getMetaData();

					// iterate through the returned tuples, printing each one
					// count # of rows returned
					int rowsReturned = 0;
					
					// THis should only iterate once since only account_id is being retrieved.
					while (resultSet2.next()) {
						for (int i = 1; i <= resultSchema2.getColumnCount(); i++) {
							Integer obj2 = (Integer) resultSet2.getObject(i);
							account_id = obj2;
							//System.out.println("account_id: " + account_id);
							if (i > 1) {
								System.out.print(",");
							}
							//System.out.print(obj2.toString());
						}
						System.out.println();
						
						// count # of rows returned
						rowsReturned++;
						System.out.println("New account #<" + account_id + "> inserted in accounts table");	
					}
					
					// indicate if the query returned nothing
					if (rowsReturned == 0) {
						System.out.println("No rows returned that matched the query");
						System.out.println("account #<" + account_id + "> not found");
					}
					
					/////////////////////////////////////////////////////////////////////////////////////////////////
				} finally {
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);					
					DBUtil.closeQuietly(resultSet2);
					DBUtil.closeQuietly(stmt3);					
					DBUtil.closeQuietly(stmt4);
					DBUtil.closeQuietly(resultSet5);
					DBUtil.closeQuietly(stmt5);
					DBUtil.closeQuietly(stmt6);
				}
				return account_id;
			}
		});
	}

	@Override
	public Integer addPlayer(String user, Integer score, Integer health, 
			Integer stamina, Integer time, Integer x, Integer y, Integer z, int account_id, 
			Integer woodCuttingXP, Integer fishingXP, Integer combatXP, Integer craftingXP) 
	{
		return executeTransaction(new Transaction<Integer>() 
		{
			@Override
			public Integer execute(Connection conn) throws SQLException 
			{
				PreparedStatement insertPlayer   = null;
				ResultSet resultSet = null;
				PreparedStatement stmt   = null;

				Integer player_id = -1;
				try 
				{
					insertPlayer = conn.prepareStatement("insert into players (username, score, health, stamina,"
							+ " time, x, y, z, account_id, woodcutting, fishing, combat, crafting) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
					{
						insertPlayer.setString(1, user);
						insertPlayer.setInt(2, score);
						insertPlayer.setInt(3, health);
						insertPlayer.setInt(4, stamina);
						insertPlayer.setInt(5, time);
						insertPlayer.setInt(6, x);
						insertPlayer.setInt(7, y);
						insertPlayer.setInt(8, z);
						insertPlayer.setInt(9, account_id);
						insertPlayer.setInt(10, woodCuttingXP);
						insertPlayer.setInt(11, fishingXP);
						insertPlayer.setInt(12, combatXP);
						insertPlayer.setInt(13, craftingXP);
					}
					insertPlayer.executeUpdate();
					
					System.out.println("New player <" + user + "> inserted in Players table");						
					
					// try to retrieve player_id for new Player - DB auto-generates player_id
					stmt = conn.prepareStatement(
							"select players.player_id from players " +
							"  where players.username = ? "
					);
					stmt.setString(1, user);
					
					// execute the query							
					resultSet = stmt.executeQuery();
					
					// get the result - there had better be one							
					if (resultSet.next())
					{
						player_id = resultSet.getInt(1);
						System.out.println("New player <" + user + "> ID: " + player_id);						
					}
					else
					{
						System.out.println("Problem! New player should have been added");
					}
				} 
				finally 
				{
					DBUtil.closeQuietly(insertPlayer);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
				return player_id;
			}
		});
	}
	
	@Override
	public Boolean insertItemIntoDatabase(Integer account_id, Integer inventoryItem, String name, 
			String description, Integer uses, Integer amount, Integer x, Integer y, Integer z, Integer damage) {
		return executeTransaction(new Transaction<Boolean>() 
		{
			@Override
			public Boolean execute(Connection conn) throws SQLException 
			{
				PreparedStatement checkItem   = null;
				PreparedStatement insertItem   = null;
				PreparedStatement updateItem   = null;

				ResultSet resultSet = null;
				Boolean itemAdded = false;
				try 
				{
					// Check if item already exists
					checkItem = conn.prepareStatement(
							"select items.item_id, items.amount from items" +
							" WHERE items.name = ? AND items.account_id = ? AND items.inventoryItem = ?"
					);
					checkItem.setString(1, name);
					checkItem.setInt(2, account_id);
					checkItem.setInt(3, 1);
					resultSet = checkItem.executeQuery();
					
					if (resultSet.next()) // If item exists only change amount
					{
						int item_id = resultSet.getInt(1);
						int itemAmount = resultSet.getInt(2) + amount;		
						
						updateItem = conn.prepareStatement(
								"UPDATE items" +
									" SET items.amount = ?" +
									" where items.item_id = ?"
						);
						updateItem.setInt(1, itemAmount);
						updateItem.setInt(2, item_id);
						
						// execute the update
						updateItem.executeUpdate();
						
						itemAdded = true;
					}
					else
					{
						insertItem = conn.prepareStatement("insert into items (account_id, inventoryItem, name, description, uses,"
								+ " amount, x, y, z, damage) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
						{
							insertItem.setInt(1, account_id);
							insertItem.setInt(2, inventoryItem);
							insertItem.setString(3, name);
							insertItem.setString(4, description);
							insertItem.setInt(5, uses);
							insertItem.setInt(6, amount);
							insertItem.setInt(7, x);
							insertItem.setInt(8, y);
							insertItem.setInt(9, z);
							insertItem.setInt(10, damage);
						}
						insertItem.executeUpdate();
						
						System.out.println("New item for account number <" + account_id + "> inserted in items table");
						itemAdded = true;
					}
				} 
				finally 
				{
					DBUtil.closeQuietly(checkItem);
					DBUtil.closeQuietly(insertItem);
					DBUtil.closeQuietly(updateItem);
					DBUtil.closeQuietly(resultSet);
				}
				return itemAdded;
			}
		});
	}
	
	@Override
	public Boolean moveItemInventory(Integer account_id, Integer inventoryItem, String name) {
		return executeTransaction(new Transaction<Boolean>() 
		{
			@Override
			public Boolean execute(Connection conn) throws SQLException 
			{
				PreparedStatement updateItem = null;
				boolean bool = false;
				try 
				{
					updateItem = conn.prepareStatement(
							"UPDATE items" +
							" SET items.inventoryItem = ?" +
							" WHERE items.name = ? AND items.account_id = ?"
					);
					updateItem.setInt(1, inventoryItem);
					updateItem.setString(2, name);
					updateItem.setInt(3, account_id);
					
					updateItem.executeUpdate();
					
					bool = true;
				} 
				finally 
				{
					DBUtil.closeQuietly(updateItem);
				}
				return bool;
			}
		});
	}
	
	@Override
	public Boolean updateItemLocation(int account_id, String name, int x, int y, int z) {
		return executeTransaction(new Transaction<Boolean>() 
		{
			@Override
			public Boolean execute(Connection conn) throws SQLException 
			{
				PreparedStatement updateItem = null;
				boolean bool = false;
				try 
				{
					updateItem = conn.prepareStatement(
							"UPDATE items" +
							" SET items.x = ?," +
							" 	items.y = ?," +
							" 	items.z = ?" +
							" WHERE items.name = ? AND items.account_id = ?"
					);
					updateItem.setInt(1, x);
					updateItem.setInt(2, y);
					updateItem.setInt(3, z);
					updateItem.setString(4, name);
					updateItem.setInt(5, account_id);
					
					updateItem.executeUpdate();
					
					bool = true;
				} 
				finally 
				{
					DBUtil.closeQuietly(updateItem);
				}
				return bool;
			}
		});
	}
	
	@Override
	public Boolean updateItemAmount(Integer account_id, String name, Integer amount) {
		return executeTransaction(new Transaction<Boolean>() 
		{
			@Override
			public Boolean execute(Connection conn) throws SQLException 
			{
				PreparedStatement updateItem = null;
				boolean bool = false;
				try 
				{
					updateItem = conn.prepareStatement(
							"UPDATE items" +
							" SET items.amount = ?" +
							" WHERE items.name = ? AND items.account_id = ?"
					);
					updateItem.setInt(1, amount);
					updateItem.setString(2, name);
					updateItem.setInt(3, account_id);
					
					updateItem.executeUpdate();
					
					bool = true;
				} 
				finally 
				{
					DBUtil.closeQuietly(updateItem);
				}
				return bool;
			}
		});
	}
	
	//This loads the list of items from database into account model
	@Override
	public Account updateItemList(Integer account_id, Account account) 
	{
		return executeTransaction(new Transaction<Account>() 
		{
			@Override
			public Account execute(Connection conn) throws SQLException 
			{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
	
				try {
					// Retrieve all attributes from items tables
					stmt = conn.prepareStatement(
							"select items.inventoryItem, items.name, items.description, items.uses, items.amount, "
							+ "items.x, items.y, items.z, items.damage from items" +
							" where items.account_id = ? "
					);
					stmt.setInt(1, account_id);
					
					resultSet = stmt.executeQuery();

					Boolean found = false;
					
					while (resultSet.next()) 
					{
						found = true;
				
						// retrieve attributes from resultSet starting with index 1
						loadItem(account, resultSet, 1);
					}

					// check if the items were found
					if (!found) 
					{
						System.out.println("No items found in the items table for account.");
					}
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
				return account;
			}
		});
	}
	
	@Override
	public Player getPlayer(String user) 
	{
		return executeTransaction(new Transaction<Player>() 
		{
			@Override
			public Player execute(Connection conn) throws SQLException 
			{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				Location location = new Location(10,10,0);
				Skills skills = new Skills(0,0,0,0);
				Player player = new Player(0, 0, 0, 0, null, location, skills, null, null);
				try {
					// Retrieve all attributes from players tables
					stmt = conn.prepareStatement(
							"select score, health, stamina, time, x, y, z, woodcutting, fishing, combat, crafting from players" +
							" where players.username = ? "
					);
					stmt.setString(1, user);
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;

					while (resultSet.next()) 
					{
						found = true;
				
						// retrieve attributes from resultSet starting with index 1
						loadPlayer(player, resultSet, 1);
					}

					// check if the player was found
					if (!found) 
					{
						System.out.println("<" + user + "> was not found in the players table");
					}
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
				return player;
			}
		});
	}
	
	@Override
	public Integer getAccountIdFromDatabase(String username) {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;			
				ResultSet resultSet1 = null;				
				
				// for saving author ID and book ID
				Integer account_id = -1;

				// **try to retrieve author_id (if it exists) from DB, for Author's full name, passed into query
				// Try to retrieve existing account to see if one is already made
				try {
					stmt1 = conn.prepareStatement(
							"select accounts.account_id from accounts" +
							"  where accounts.username = ? "
					);
					stmt1.setString(1, username);
					
					// execute the update
					resultSet1 = stmt1.executeQuery();
					
					// get the precise schema of the tuples returned as the result of the query
					ResultSetMetaData resultSchema1 = stmt1.getMetaData();

					// iterate through the returned tuples, printing each one
					// count # of rows returned
					int rowsReturned = 0;
					
					// THis should only iterate once since only account_id is being retrieved.
					while (resultSet1.next()) {
						for (int i = 1; i <= resultSchema1.getColumnCount(); i++) {
							Integer obj = (Integer) resultSet1.getObject(i);
							account_id = obj;
							//System.out.println("account_id: " + account_id);
							if (i > 1) {
								System.out.print(",");
							}
							//System.out.print(obj2.toString());
						}
						System.out.println();
						// count # of rows returned
						rowsReturned++;
						System.out.println("DerbyDatabase >> Account ID#<" + account_id + "> has ben retireved for username: <" + username + ">");	
					}
					// indicate if the query returned nothing
					if (rowsReturned == 0) {
						System.out.println("No rows returned that matched the query");
						System.out.println("account #<" + account_id + "> not found");
					}
				} finally {
					DBUtil.closeQuietly(resultSet1);
					DBUtil.closeQuietly(stmt1);
				}
				return account_id;
			}
		});
	}
	
	//Update Account information
	@Override
	public Boolean updatePlayerInDatabase(int account_id, Player player) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;				
				
				ResultSet resultSet2 = null;				
				
				// for saving author ID and book ID
				Boolean bool = new Boolean(false);
				int stamina = -1;

				//Modify the values of the attributes of the players table
				try {
					stmt1 = conn.prepareStatement(
							"UPDATE players" +
								" SET players.score = ?," +
									" players.health = ?," +
									" players.stamina = ?," +
									" players.time = ?," +
									" players.x = ?," +
									" players.y = ?," +
									" players.z = ?," +
									" players.woodcutting = ?," +
									" players.fishing = ?," +
									" players.combat = ?," +
									" players.crafting = ?" +
								" WHERE players.account_id = ? "
					);
					stmt1.setInt(1, player.getScore());
					stmt1.setInt(2, player.getHealth());
					stmt1.setInt(3, player.getStamina());
					stmt1.setInt(4, player.getTime());
					stmt1.setInt(5, player.getLocation().getX());
					stmt1.setInt(6, player.getLocation().getY());
					stmt1.setInt(7, player.getLocation().getZ());
					stmt1.setInt(8, player.getSkills().getWoodCuttingXP());
					stmt1.setInt(9, player.getSkills().getFishingXP());
					stmt1.setInt(10, player.getSkills().getCombatXP());
					stmt1.setInt(11, player.getSkills().getCraftingXP());
					stmt1.setInt(12, account_id);
					
					// execute the update
					stmt1.executeUpdate(); // IF MANIPULATING DATABASE, MUST USE EXECUTE UPDATE!!!!!!!!!!!!!
					
					stmt2 = conn.prepareStatement(
							"select players.stamina from players " +
							"  where players.account_id = ? "
					);
					stmt2.setInt(1, account_id);
					
					// execute the update
					resultSet2 = stmt2.executeQuery();
					
					// get the precise schema of the tuples returned as the result of the query
					ResultSetMetaData resultSchema2 = stmt2.getMetaData();

					// iterate through the returned tuples, printing each one
					// count # of rows returned
					int rowsReturned = 0;
					
					// THis should only iterate once since only score is being retrieved.
					while (resultSet2.next()) {
						for (int i = 1; i <= resultSchema2.getColumnCount(); i++) {
							Integer obj2 = (Integer) resultSet2.getObject(i);
							stamina = obj2;
							//System.out.println("account_id: " + account_id);
							if (i > 1) {
								System.out.print(",");
							}
							//System.out.print(obj2.toString());
						}
						System.out.println();
						// count # of rows returned
						rowsReturned++;
						System.out.println("DerbyDatabase >> stamina retireved: <" + stamina + "> from account_id: <" + account_id + ">");	
					}
					// indicate if the query returned nothing
					if (rowsReturned == 0) {
						System.out.println("No rows returned that matched the query");
					}else{
						bool = true;
					}
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);					
					DBUtil.closeQuietly(resultSet2);
				}
				return bool;
			}
		});
	}
	
	@Override
	public Player getPlayer(int account_id) {
		return executeTransaction(new Transaction<Player>() {
			@Override
			public Player execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				Location location = new Location(10,10,0);
				Skills skills = new Skills(0,0,0,0);
				Player player = new Player(0, 0, 0, 0, null, location, skills, null, null);
				try {
					// Retrieve all attributes from players tables
					stmt = conn.prepareStatement(
							"select players.score, players.health, players.stamina, " + 
									" players.time, players.x, players.y, players.z, "
									+ "woodcutting, fishing, combat, crafting from players" +
							" where players.account_id = ? "
					);
					stmt.setInt(1, account_id);
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;

					while (resultSet.next()) 
					{
						found = true;
				
						// retrieve attributes from resultSet starting with index 1
						loadPlayer(player, resultSet, 1);
					}

					// check if the player was found
					if (!found) 
					{
						System.out.println("DerbyDatabase >> account_id: <" + account_id + "> was not found in the players table");
					}else{
						System.out.println("DerbyDatabase >> loaded health of account_id: <" + account_id + "> is <" + player.getHealth() + ">");
					}
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
				return player;
			}
		});
	}
	
	@Override
	public Boolean insertRoomsIntoDatabase(int account_id, String username, ArrayList<Room> list) {
		// TODO Auto-generated method stub
		return executeTransaction(new Transaction<Boolean>() 
		{
			@Override
			public Boolean execute(Connection conn) throws SQLException 
			{
				PreparedStatement insertRooms   = null;

				Boolean done = new Boolean(false);
				try 
				{
					for (Room room : list){
							
						insertRooms = conn.prepareStatement("insert into rooms (account_id, username, x, y, z, longDescript, visible,"
								+ " go_north, go_east, go_south, go_west, go_up, go_down, shortDescript)"
								+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
						
						insertRooms.setInt(1, account_id);
						insertRooms.setString(2, username);
						insertRooms.setInt(3, room.getLocation().getX());
						insertRooms.setInt(4, room.getLocation().getY());
						insertRooms.setInt(5, room.getLocation().getZ());
						insertRooms.setString(6, room.getLongDescription());
						insertRooms.setInt(7, (room.getVisible()) ? 1 : 0); // Convert from boolean to integer
						insertRooms.setInt(8, (room.getGoNorth()) ? 1 : 0);
						insertRooms.setInt(9, (room.getGoEast()) ? 1 : 0);
						insertRooms.setInt(10, (room.getGoSouth()) ? 1 : 0);
						insertRooms.setInt(11, (room.getGoWest()) ? 1 : 0);
						insertRooms.setInt(12, (room.getGoUp()) ? 1 : 0);
						insertRooms.setInt(13, (room.getGoDown()) ? 1 : 0);
						insertRooms.setString(14, room.getShortDescription());
						
						insertRooms.executeUpdate();						
					}
					done = true;
					System.out.println("DerbyDatabase >> Rooms inserted into database for account_id: <" + account_id + ">, Username: <" + username + ">");
				} 
				finally 
				{
					DBUtil.closeQuietly(insertRooms);
				}
				return done;
			}
		});
	}
	
	@Override
	public boolean updateMapInDatabase(int account_id, Account account) {
		return executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;				
				
				ResultSet resultSet2 = null;				
				
				// for saving author ID and book ID
				Boolean bool = new Boolean(false);

				//Modify the values of the attributes of the players table
				try {
					for (Room room : account.getRooms()){
					
						stmt1 = conn.prepareStatement(
								"UPDATE rooms" +
									" SET rooms.visible = ?," +
										" rooms.go_north = ?," +
										" rooms.go_east = ?," +
										" rooms.go_south = ?," +
										" rooms.go_west = ?," +
										" rooms.go_up = ?," +
										" rooms.go_down = ?" +
									" WHERE rooms.account_id = ? AND rooms.x = ? AND rooms.y = ? AND rooms.z = ? "
						);
						stmt1.setInt(1, room.getVisible() ? 1 : 0);
						stmt1.setInt(2, room.getGoNorth() ? 1 : 0);
						stmt1.setInt(3, room.getGoEast() ? 1 : 0);
						stmt1.setInt(4, room.getGoSouth() ? 1 : 0);
						stmt1.setInt(5, room.getGoWest() ? 1 : 0);
						stmt1.setInt(6, room.getGoUp() ? 1 : 0);
						stmt1.setInt(7, room.getGoDown() ? 1 : 0);
						stmt1.setInt(8, account_id);
						stmt1.setInt(9, room.getLocation().getX());
						stmt1.setInt(10, room.getLocation().getY());
						stmt1.setInt(11, room.getLocation().getZ());
						
						// execute the update
						stmt1.executeUpdate(); // IF MANIPULATING DATABASE, MUST USE EXECUTE UPDATE!!!!!!!!!!!!!
					
					}
					
					stmt2 = conn.prepareStatement(
							"select rooms.visible from rooms " +
							"  where rooms.account_id = ? AND rooms.x = ? AND rooms.y = ? AND rooms.z = ? "
					);
					stmt2.setInt(1, account_id);
					stmt2.setInt(2, account.getPlayer().getLocation().getX());
					stmt2.setInt(3, account.getPlayer().getLocation().getY());
					stmt2.setInt(4, account.getPlayer().getLocation().getZ());
					
					// execute the update
					resultSet2 = stmt2.executeQuery();
					
					// get the precise schema of the tuples returned as the result of the query
					ResultSetMetaData resultSchema2 = stmt2.getMetaData();

					// iterate through the returned tuples, printing each one
					// count # of rows returned
					int rowsReturned = 0;
					
					// THis should only iterate once since only visible is being retrieved.
					while (resultSet2.next()) {
						for (int i = 1; i <= resultSchema2.getColumnCount(); i++) {
							Integer obj = (Integer) resultSet2.getObject(i);
							bool = (obj == 1) ? true : false; // Convert integer to boolean
							//System.out.println("account_id: " + account_id);
							if (i > 1) {
								System.out.print(",");
							}
							//System.out.print(obj2.toString());
						}
						System.out.println();
						// count # of rows returned
						rowsReturned++;
						System.out.println("DerbyDatabase >> visible retireved for current room: <" + bool.toString() + "> for account_id: <" + account_id + ">");	
					}
					// indicate if the query returned nothing
					if (rowsReturned == 0) {
						System.out.println("DerbyDatabase >> No rows returned that matched the query. Visible retrieved for current room: <" + bool.toString() + ">");
					}
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);					
					DBUtil.closeQuietly(resultSet2);
				}
				return bool;
			}
		});
	}
	
	@Override
	public ArrayList<Room> loadMapFromDatabase(int account_id) {
		return executeTransaction(new Transaction<ArrayList<Room>>() {
			@Override
			public ArrayList<Room> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				ArrayList<Room> map = new ArrayList<Room>();
				
				try {
					
					// Retrieve all attributes from players tables
					stmt = conn.prepareStatement(
							"select rooms.x, rooms.y, rooms.z," + 
									" rooms.longDescript, rooms.visible," +
									" rooms.go_north, rooms.go_east, rooms.go_south," +
									" rooms.go_west, rooms.go_up, rooms.go_down, rooms.shortDescript" +
							" from rooms where rooms.account_id = ? "
					);
					stmt.setInt(1, account_id);
					
					resultSet = stmt.executeQuery();
					
					ResultSetMetaData resultSchema = stmt.getMetaData();
					
					boolean bool = false;
					
					while (resultSet.next()) 
					{
						// new room must be created every time or else all are set to 14,14,2
						Room room = new Room(null, null, null, false, false, false, false, false, false, false);
						bool = true;
						// retrieve attributes from resultSet starting with index 1
						loadRoom(room, resultSet, 1);
						map.add(room);
					}

					// check if the map was found
					if (!bool) 
					{
						System.out.println("DerbyDatabase >> account_id: <" + account_id + "> was not found in the map table");
					}else{
						System.out.println("DerbyDatabase >> loaded map of account_id: <" + account_id + ">");
					}
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
				return map;
			}
		});
	}
	
	@Override
	public Account updateEnemyList(Integer account_id, Account account) 
	{
		return executeTransaction(new Transaction<Account>() 
		{
			@Override
			public Account execute(Connection conn) throws SQLException 
			{
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
	
				try {
					// Retrieve all attributes from items tables
					stmt = conn.prepareStatement(
							"select enemies.name, enemies.description, enemies.health, enemies.damage, "
							+ "enemies.x, enemies.y, enemies.z from enemies" +
							" where enemies.account_id = ? "
					);
					stmt.setInt(1, account_id);
					
					resultSet = stmt.executeQuery();

					Boolean found = false;
					
					account.getEnemyList().clear();	//Empties current enemyList to update with new list
					
					while (resultSet.next()) 
					{
						found = true;
				
						// retrieve attributes from resultSet starting with index 1
						loadEnemy(account, resultSet, 1);
					}

					// check if the items were found
					if (!found) 
					{
						System.out.println("No enemy found in the enemies table.");
					}
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
				return account;
			}
		});
	}
	
	@Override
	public Boolean insertEnemyIntoDatabase(Integer account_id, String name, String description, 
			Integer health, Integer damage, Integer x, Integer y, Integer z) {
		return executeTransaction(new Transaction<Boolean>() 
		{
			@Override
			public Boolean execute(Connection conn) throws SQLException 
			{
				PreparedStatement insertEnemy   = null;

				Boolean enemyAdded = false;
				try 
				{
					insertEnemy = conn.prepareStatement("insert into enemies (account_id, name, description, health,"
							+ " damage, x, y, z) values (?, ?, ?, ?, ?, ?, ?, ?)");
					{
						insertEnemy.setInt(1, account_id);
						insertEnemy.setString(2, name);
						insertEnemy.setString(3, description);
						insertEnemy.setInt(4, health);
						insertEnemy.setInt(5, damage);
						insertEnemy.setInt(6, x);
						insertEnemy.setInt(7, y);
						insertEnemy.setInt(8, z);
					}
					insertEnemy.executeUpdate();
						
					System.out.println("New enemy for account number <" + account_id + "> inserted in enemies table");
					enemyAdded = true;
				} 
				finally 
				{
					DBUtil.closeQuietly(insertEnemy);
				}
				return enemyAdded;
			}
		});
	}
	
	@Override
	public Boolean removeEnemy(Integer account_id, String name, String description, 
			Integer x, Integer y, Integer z) {
		return executeTransaction(new Transaction<Boolean>() 
		{
			@Override
			public Boolean execute(Connection conn) throws SQLException 
			{
				PreparedStatement removeEnemy   = null;

				Boolean enemyRemoved = false;
				try 
				{
					removeEnemy = conn.prepareStatement("DELETE FROM enemies " + 
							"WHERE enemies.account_id = ? and enemies.name = ? and enemies.description = ? and "
							+ "enemies.x = ? and enemies.y = ? and enemies.z = ?");
					{
						removeEnemy.setInt(1, account_id);
						removeEnemy.setString(2, name);
						removeEnemy.setString(3, description);
						removeEnemy.setInt(4, x);
						removeEnemy.setInt(5, y);
						removeEnemy.setInt(6, z);
					}
					removeEnemy.executeUpdate();
						
					System.out.println("Enemy <" + name + "> deleted from enemies table");
					enemyRemoved = true;
				} 
				finally 
				{
					DBUtil.closeQuietly(removeEnemy);
				}
				return enemyRemoved;
			}
		});
	}
	
	public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
		try {
			return doExecuteTransaction(txn);
		} catch (SQLException e) {
			throw new PersistenceException("Transaction failed", e);
		}
	}
	
	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
		Connection conn = connect();
		
		try {
			int numAttempts = 0;
			boolean success = false;
			ResultType result = null;
			
			while (!success && numAttempts < MAX_ATTEMPTS) {
				try {
					result = txn.execute(conn);
					conn.commit();
					success = true;
				} catch (SQLException e) {
					if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
						// Deadlock: retry (unless max retry count has been reached)
						numAttempts++;
					} else {
						// Some other kind of SQLException
						throw e;
					}
				}
			}
			
			if (!success) {
				throw new SQLException("Transaction failed (too many retries)");
			}
			
			// Success!
			return result;
		} finally {
			DBUtil.closeQuietly(conn);
		}
	}

	private Connection connect() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:derby:test.db;create=true");
		
		// Set autocommit to false to allow execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
		
		return conn;
	}
	
	private void loadPlayer(Player player, ResultSet resultSet, int index) throws SQLException 
	{
		player.setScore(resultSet.getInt(index++));
		player.setHealth(resultSet.getInt(index++));
		player.setStamina(resultSet.getInt(index++));
		player.setTime(resultSet.getInt(index++));
		player.setX(resultSet.getInt(index++));
		player.setY(resultSet.getInt(index++));
		player.setZ(resultSet.getInt(index++));
		player.getSkills().setWoodCuttingXP(resultSet.getInt(index++));
		player.getSkills().setFishingXP(resultSet.getInt(index++));
		player.getSkills().setCombatXP(resultSet.getInt(index++));
		player.getSkills().setCraftingXP(resultSet.getInt(index++));
	}
	
	private void loadItem(Account account, ResultSet resultSet, int index) throws SQLException
	{
		Inventory inventory = account.getPlayer().getInventory();	// Get players inventory
		
		Integer inventoryItem = resultSet.getInt(index++);
		String name = resultSet.getString(index++);
		String description = resultSet.getString(index++);
		Integer uses = resultSet.getInt(index++);
		Integer amount = resultSet.getInt(index++);
		Integer x = resultSet.getInt(index++);
		Integer y = resultSet.getInt(index++);
		Integer z = resultSet.getInt(index++);
		Integer damage = resultSet.getInt(index++);
		
		Location location = new Location(x, y, z);
		if (damage > 0)
		{
			Weapon weapon = new Weapon(name, description, location, damage);
			if (inventoryItem > 0)
			{
				inventory.addItem(weapon, amount);
				if (inventoryItem == 2)
				{
					account.getPlayer().setWeapon(weapon);
				}
			}
			else
			{
				account.getItemList().add(weapon); // Else add to account itemList
			}
		}
		else if (damage < 0)
		{
			Armor armor = new Armor(name, description, location, -damage);
			if (inventoryItem > 0)
			{
				inventory.addItem(armor, amount);
				if (inventoryItem == 2)
				{
					account.getPlayer().setArmor(armor);
				}
			}
			else
			{
				account.getItemList().add(armor); // Else add to account itemList
			}
		}
		else
		{
			Item item = new Item(name, description, location, uses);
			if (inventoryItem > 0)
			{
				inventory.addItem(item, amount);
			}
			else
			{
				account.getItemList().add(item); // Else add to account itemList
			}
		}
	}
	
	private void loadEnemy(Account account, ResultSet resultSet, int index) throws SQLException
	{
		Inventory inventory = account.getPlayer().getInventory();	// Get players inventory

		String name = resultSet.getString(index++);
		String description = resultSet.getString(index++);
		Integer health = resultSet.getInt(index++);
		Integer damage = resultSet.getInt(index++);
		Integer x = resultSet.getInt(index++);
		Integer y = resultSet.getInt(index++);
		Integer z = resultSet.getInt(index++);
		
		Location location = new Location(x, y, z);
		Enemy enemy = new Enemy(name, description, health, location, damage);
		
		account.getEnemyList().add(enemy);
	}
	
	private void loadAccount(Account account, ResultSet resultSet, int index) throws SQLException 
	{
		//account.setAccount_id(resultSet.getInt(index++));
		account.setUsername(resultSet.getString(index++));
		account.setPassword(resultSet.getString(index++));
	}
	
	private void loadRoom(Room room, ResultSet resultSet, int index) throws SQLException {
		Location loc = new Location(0,0,0);
		loc.setX(resultSet.getInt(index++));
		loc.setY(resultSet.getInt(index++));
		loc.setZ(resultSet.getInt(index++));
		room.setLocation(loc);
		room.setLongDescription(resultSet.getString(index++));
		room.setVisible(resultSet.getInt(index++) == 1 ? true : false);
		room.setGoNorth(resultSet.getInt(index++) == 1 ? true : false);
		room.setGoEast(resultSet.getInt(index++) == 1 ? true : false);
		room.setGoSouth(resultSet.getInt(index++) == 1 ? true : false);
		room.setGoWest(resultSet.getInt(index++) == 1 ? true : false);
		room.setGoUp(resultSet.getInt(index++) == 1 ? true : false);
		room.setGoDown(resultSet.getInt(index++) == 1 ? true : false);
		room.setShortDescription(resultSet.getString(index++));
	}
	
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;
				PreparedStatement stmt4 = null;
				PreparedStatement stmt5 = null;
				
				try {
					stmt1 = conn.prepareStatement(
						"create table players (" +
						"	player_id integer primary key " +
						"		generated always as identity (start with 1, increment by 1), " +
						"	username varchar(40)," +
						"	score integer," +
						"	health integer," +
						"	stamina integer," +
						"	time integer," +
						"	x integer," +
						"	y integer," +
						"	z integer," +
						"	account_id integer," +
						"	woodcutting integer," +
						"	fishing integer," +
						"	combat integer," +
						"	crafting integer" +
						")"
					);	
					stmt1.executeUpdate();
					
					System.out.println("Players table created.");
					
					stmt2 = conn.prepareStatement(
						"create table accounts (" +
						"	account_id integer primary key " +
						"		generated always as identity (start with 1, increment by 1), " +									
						"	username varchar(40)," +
						"	password varchar(40)" +
						")"
					);	
					stmt2.executeUpdate();
						
					System.out.println("Accounts table created.");
					
					// Create Room tables
					stmt3 = conn.prepareStatement(
						"create table rooms (" +
						"	room_id integer primary key " +
						"		generated always as identity (start with 1, increment by 1), " +
						"	account_id integer," +
						"	username varchar(40)," +
						"	x integer," +
						"	y integer," +
						"	z integer," +
						"	longDescript varchar(250)," +
						"	visible integer," +
						"	go_north integer," +
						"	go_east integer," +
						"	go_south integer," +
						"	go_west integer," +
						"	go_up integer," +
						"	go_down integer," +
						"	shortDescript varchar(150)" +
						")"
					);
					stmt3.executeUpdate();
					System.out.println("Rooms table created.");
					
					// Create items table
					stmt4 = conn.prepareStatement(
							"create table items (" +
							"	item_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	account_id integer," +
							"	inventoryItem integer," +
							"	name varchar(40)," +
							"	description varchar(150)," +
							"	uses integer," +
							"	amount integer," +
							"	x integer," +
							"	y integer," +
							"	z integer," +
							"	damage integer" +
							")"
						);	
					stmt4.executeUpdate();
					System.out.println("Items table created.");
						
					// Create enemies table
					stmt5 = conn.prepareStatement(
							"create table enemies (" +
							"	enemy_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	account_id integer," +
							"	name varchar(40)," +
							"	description varchar(150)," +
							"	health integer," +
							"	damage integer," +
							"	x integer," +
							"	y integer," +
							"	z integer" +
							")"
						);	
					stmt5.executeUpdate();
					System.out.println("Enemies table created.");
						
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
					DBUtil.closeQuietly(stmt3);
					DBUtil.closeQuietly(stmt4);
					DBUtil.closeQuietly(stmt5);
				}
			}
		});
	}
	
	public void loadInitialData(int account_id, String username) {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				//List<Player> playerList;
				List<Room> roomList;
				List<Item> itemList;
				List<Enemy> enemyList;
				try {
					//playerList = InitialData.getAuthors();
					roomList = InitialData.getRooms();
					itemList = InitialData.getItems();
					enemyList = InitialData.getEnemies();
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertRoom = null;
				PreparedStatement insertItem = null;
				PreparedStatement insertEnemy = null;

				try {
					// populate rooms table
					insertRoom = conn.prepareStatement("insert into rooms (account_id, username, x, y, z, longDescript, visible,"
							+ " go_north, go_east, go_south, go_west, go_up, go_down, shortDescript)"
							+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
					for (Room room : roomList) {
//						insertAuthor.setInt(1, author.getAuthorId());	// auto-generated primary key, don't insert this
						insertRoom.setInt(1, account_id);
						insertRoom.setString(2, username);
						insertRoom.setInt(3, room.getLocation().getX());
						insertRoom.setInt(4, room.getLocation().getY());
						insertRoom.setInt(5, room.getLocation().getZ());
						insertRoom.setString(6, room.getLongDescription());
						insertRoom.setInt(7, room.getVisible() ? 1 : 0);
						insertRoom.setInt(8, room.getGoNorth() ? 1 : 0);
						insertRoom.setInt(9, room.getGoEast() ? 1 : 0);
						insertRoom.setInt(10, room.getGoSouth() ? 1 : 0);
						insertRoom.setInt(11, room.getGoWest() ? 1 : 0);
						insertRoom.setInt(12, room.getGoUp() ? 1 : 0);
						insertRoom.setInt(13, room.getGoDown() ? 1 : 0);
						insertRoom.setString(14, room.getShortDescription());
						insertRoom.addBatch();
					}
					insertRoom.executeBatch();
					
					// populate items table
					
					insertItem = conn.prepareStatement("insert into items (account_id, inventoryItem, name, description, uses, amount, x, y, z, damage)"
							+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
					for (Item item : itemList) {
//						insertBook.setInt(1, book.getBookId());		// auto-generated primary key, don't insert this
						insertItem.setInt(1, account_id);
						insertItem.setInt(2, 0);
						insertItem.setString(3, item.getName());
						insertItem.setString(4, item.getDescription());
						insertItem.setInt(5, item.getUses());
						insertItem.setInt(6, 1);
						insertItem.setInt(7, item.getLocation().getX());
						insertItem.setInt(8, item.getLocation().getY());
						insertItem.setInt(9, item.getLocation().getZ());
						if (item.getName().toLowerCase().contains("sword")) {
							insertItem.setInt(10, 25);
						}
						else if (item.getName().toLowerCase().contains("knife")) {
							insertItem.setInt(10, 20);
						}
						else if (item.getName().toLowerCase().contains("armor")) {
							insertItem.setInt(10, -20);
						}
						else {
							insertItem.setInt(10, 0); // Not a weapon.
						}
						insertItem.addBatch();
					}
					insertItem.executeBatch();
					
					// populate enemies table
					
					insertEnemy = conn.prepareStatement("insert into enemies (account_id, name, description, health, damage, x, y, z)"
							+ " values (?, ?, ?, ?, ?, ?, ?, ?)");
					for (Enemy enemy : enemyList) {
//						insertBook.setInt(1, book.getBookId());		// auto-generated primary key, don't insert this
						insertEnemy.setInt(1, account_id);
						insertEnemy.setString(2, enemy.getName());
						insertEnemy.setString(3, enemy.getDescription());
						insertEnemy.setInt(4, enemy.getHealth());
						insertEnemy.setInt(5, enemy.getDamage());
						insertEnemy.setInt(6, enemy.getLocation().getX());
						insertEnemy.setInt(7, enemy.getLocation().getY());
						insertEnemy.setInt(8, enemy.getLocation().getZ());
						System.out.println("DerbyDatabase >> Enemy location: " + enemy.getLocation().getX() + "," + enemy.getLocation().getY());
						insertEnemy.addBatch();
					}
					insertItem.executeBatch();
					
					return true;
				} finally {
					DBUtil.closeQuietly(insertItem);
					DBUtil.closeQuietly(insertRoom);
					DBUtil.closeQuietly(insertEnemy);
				}
			}
		});
	}
	
	// The main method creates the database tables and loads the initial data.
	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		DerbyDatabase db = new DerbyDatabase();
		db.createTables();
		
		//System.out.println("Loading initial data...");
//		db.loadInitialData();
		
		System.out.println("Success!");
	}

	@Override
	public Account getItemList(Integer account_id, Account account) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateItemsInDatabase(int account_id, Account account) {
		// TODO Auto-generated method stub
		return false;
	}
}