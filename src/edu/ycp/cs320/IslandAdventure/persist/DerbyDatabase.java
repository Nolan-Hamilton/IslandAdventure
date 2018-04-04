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
import edu.ycp.cs320.IslandAdventure.model.Player;

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
	public Integer addPlayer(String user, Integer score, Integer health, Integer stamina, Integer time, Integer x, Integer y, Integer z) 
	{
		return executeTransaction(new Transaction<Integer>() 
		{
			@Override
			public Integer execute(Connection conn) throws SQLException 
			{
				PreparedStatement insertPlayer   = null;
				ResultSet resultSet = null;
				PreparedStatement stmt   = null;

				try 
				{
					insertPlayer = conn.prepareStatement("insert into players (user, score, health, stamina,"
							+ " time, x, y, z) values (?, ?, ?, ?, ?, ?, ?, ?)");
					{
						insertPlayer.setString(1, user);
						insertPlayer.setString(2, score.toString());
						insertPlayer.setString(3, health.toString());
						insertPlayer.setString(4, stamina.toString());
						insertPlayer.setString(5, time.toString());
						insertPlayer.setString(6, x.toString());
						insertPlayer.setString(7, y.toString());
						insertPlayer.setString(8, z.toString());
					}
					insertPlayer.executeUpdate();
					
					System.out.println("New player <" + user + "> inserted in Players table");						
					
					// try to retrieve player_id for new Player - DB auto-generates player_id
					stmt = conn.prepareStatement(
							"select player_id from players " +
							"  where user = ? "
					);
					stmt.setString(1, user);
					
					// execute the query							
					resultSet = stmt.executeQuery();
					
					Integer player_id = -1;
					
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
					return player_id;
				} 
				finally 
				{
					DBUtil.closeQuietly(insertPlayer);
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
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
				
				try {
					// Retrieve all attributes from both Books and Authors tables
					stmt = conn.prepareStatement(
							"select player.* " +
							" where players.user = ? "
					);
					stmt.setString(1, user);
					
					Player player = new Player(0, 0, 0, 0, null, null, null, null, null);
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) 
					{
						found = true;
				
						// retrieve attributes from resultSet starting with index 1
						loadPlayer(player, resultSet, 1);
					}
					
					// check if the title was found
					if (!found) 
					{
						System.out.println("<" + user + "> was not found in the players table");
					}
					
					return player;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
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
		//player.setPlayer_id(resultSet.getInt(index++));
	}
	
	private void loadAccount(Account account, ResultSet resultSet, int index) throws SQLException 
	{
		//account.setAccount_id(resultSet.getInt(index++));
		account.setUsername(resultSet.getString(index++));
		account.setPassword(resultSet.getString(index++));
	}
	
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				
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
						"	account_id integer" +
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
					
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});
	}
	/*
	public void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				List<Player> playerList;
				
				try {
					playerList = InitialData.getAuthors();
					bookList = InitialData.getBooks();
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertAuthor = null;
				PreparedStatement insertBook   = null;

				try {
					// populate authors table (do authors first, since author_id is foreign key in books table)
					insertAuthor = conn.prepareStatement("insert into authors (lastname, firstname) values (?, ?)");
					for (Author author : authorList) {
//						insertAuthor.setInt(1, author.getAuthorId());	// auto-generated primary key, don't insert this
						insertAuthor.setString(1, author.getLastname());
						insertAuthor.setString(2, author.getFirstname());
						insertAuthor.addBatch();
					}
					insertAuthor.executeBatch();
					
					// populate books table (do this after authors table,
					// since author_id must exist in authors table before inserting book)
					insertBook = conn.prepareStatement("insert into books (author_id, title, isbn, published) values (?, ?, ?, ?)");
					for (Book book : bookList) {
//						insertBook.setInt(1, book.getBookId());		// auto-generated primary key, don't insert this
						insertBook.setInt(1, book.getAuthorId());
						insertBook.setString(2, book.getTitle());
						insertBook.setString(3, book.getIsbn());
						insertBook.setInt(4,  book.getPublished());
						insertBook.addBatch();
					}
					insertBook.executeBatch();
					
					return true;
				} finally {
					DBUtil.closeQuietly(insertBook);
					DBUtil.closeQuietly(insertAuthor);
				}
			}
		});
	}
	*/
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
	public Player getPlayer(Integer playerID) {
		// TODO Auto-generated method stub
		return null;
	}


}