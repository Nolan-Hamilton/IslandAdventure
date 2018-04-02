package edu.ycp.cs320.IslandAdventure.persist;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
		// TODO Auto-generated method stub
		return null;
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
							"insert into accounts (username, password) " +
							"  values(?, ?) "
					);
					stmt1.setString(1, account.getUsername());
					stmt1.setString(2, account.getPassword());
					//stmt1.setInt(3, playerID);
					//stmt1.setInt(4, mapID);
					
					// execute the update
					stmt1.executeQuery();
					
					stmt2 = conn.prepareStatement(
							"select account.acount_id from accounts" +
							"  where account.username = ? "
					);
					stmt2.setString(1, account.getUsername());
					
					// execute the update
					resultSet2 = stmt2.executeQuery();
					
					if (resultSet2.next())
					{
						account_id = resultSet2.getInt(1);
						System.out.println("New account #<" + account_id + "> inserted in accounts table");			
					}
					else
					{
						System.out.println("account #<" + account_id + "> not found");
					}
					
					

					/*
					// if Author was found then save author_id					
					if (resultSet1.next())
					{
						author_id = resultSet1.getInt(1);
						System.out.println("Author <" + lastName + ", " + firstName + "> found with ID: " + author_id);						
					}
					else
					{
						System.out.println("Author <" + lastName + ", " + firstName + "> not found");
				
						// if the Author is new, insert new Author into Authors table
						if (author_id <= 0) {
							// prepare SQL insert statement to add Author to Authors table
							stmt2 = conn.prepareStatement(
									"insert into authors (lastname, firstname) " +
									"  values(?, ?) "
							);
							stmt2.setString(1, lastName);
							stmt2.setString(2, firstName);
							
							// execute the update
							stmt2.executeUpdate();
							
							System.out.println("New author <" + lastName + ", " + firstName + "> inserted in Authors table");						
						
							// try to retrieve author_id for new Author - DB auto-generates author_id
							stmt3 = conn.prepareStatement(
									"select author_id from authors " +
									"  where lastname = ? and firstname = ? "
							);
							stmt3.setString(1, lastName);
							stmt3.setString(2, firstName);
							
							// execute the query							
							resultSet3 = stmt3.executeQuery();
							
							// get the result - there had better be one							
							if (resultSet3.next())
							{
								author_id = resultSet3.getInt(1);
								System.out.println("New author <" + lastName + ", " + firstName + "> ID: " + author_id);						
							}
							else	// really should throw an exception here - the new author should have been inserted, but we didn't find them
							{
								System.out.println("New author <" + lastName + ", " + firstName + "> not found in Authors table (ID: " + author_id);
							}
						}
					}
					
					// now insert new Book into Books table
					// prepare SQL insert statement to add new Book to Books table
					stmt4 = conn.prepareStatement(
							"insert into books (title, isbn, published) " +
							"  values(?, ?, ?) "
					);
					stmt4.setString(1, title);
					stmt4.setString(2, isbn);
					stmt4.setInt(3, published);
					
					// execute the update
					stmt4.executeUpdate();
					
					System.out.println("New book <" + title + "> inserted into Books table");					

					// now retrieve book_id for new Book, so that we can set up BookAuthor entry
					// and return the book_id, which the DB auto-generates
					// prepare SQL statement to retrieve book_id for new Book
					stmt5 = conn.prepareStatement(
							"select book_id from books " +
							"  where title = ? and isbn = ? and published = ? "
									
					);
					stmt5.setString(1, title);
					stmt5.setString(2, isbn);
					stmt5.setInt(3, published);

					// execute the query
					resultSet5 = stmt5.executeQuery();
					
					// get the result - there had better be one
					if (resultSet5.next())
					{
						book_id = resultSet5.getInt(1);
						System.out.println("New book <" + title + "> ID: " + book_id);						
					}
					else	// really should throw an exception here - the new book should have been inserted, but we didn't find it
					{
						System.out.println("New book <" + title + "> not found in Books table (ID: " + book_id);
					}
					
					// now that we have all the information, insert entry into BookAuthors table
					// which is the junction table for Books and Authors
					// prepare SQL insert statement to add new Book to Books table
					stmt6 = conn.prepareStatement(
							"insert into bookAuthors (book_id, author_id) " +
							"  values(?, ?) "
					);
					stmt6.setInt(1, book_id);
					stmt6.setInt(2, author_id);
					
					// execute the update
					stmt6.executeUpdate();
					
					System.out.println("New entry for book ID <" + book_id + "> and author ID <" + author_id + "> inserted into BookAuthors junction table");						
					
					System.out.println("New book <" + title + "> inserted into Books table");					
					
					return book_id;
					*/
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
					
					Player player = new Player(0, 0, 0, 0, null, null, null);
					
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
						"	user string," +
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