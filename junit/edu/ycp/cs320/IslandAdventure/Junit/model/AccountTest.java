package edu.ycp.cs320.IslandAdventure.Junit.model;

/* These are the jUnit tests for account class. */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.IslandAdventure.model.*;

public class AccountTest {
	private Account account;
	private Player	player;
	private Location[][][] map;
	private Location location;
	private Inventory inventory;
	
	@Before
	public void setUp(){
		location = new Location(10,10,10);
		player = new Player(0, 0, 0, 0, inventory, location, null);
		map = new Location[25][25][25];
		account = new Account("MyName", "SomePassword", player, map);
	}
	
	// Name Tests
	@Test
	public void testGetUsername() {
		assertTrue(account.getUsername().equals("MyName"));
	}
	
	@Test
	public void testSetUsername() {
		account.setUsername("MyNewName");
		assertTrue(account.getUsername().equals("MyNewName"));
	}
	
	// Password Tests
	@Test
	public void testGetPassword() {
		assertTrue(account.getPassword().equals("SomePassword"));
	}
	
	@Test
	public void testSetDescription() {
		account.setPassword("NewPassword");
		assertTrue(account.getPassword().equals("NewPassword"));
	}
	
	// Player tests
	@Test
	public void testGetPlayer() {
		assertTrue(account.getPlayer().equals(player));
		assertTrue(account.getPlayer() == player);
	}
	
	@Test
	public void testSetPlayer() {
		Player player2 = new Player(0, 0, 0, 0, inventory, location, null);
		account.setPlayer(player2);
		assertTrue(account.getPlayer().equals(player2));
		assertTrue(account.getPlayer() == player2);
	}
	
	// Map tests
	@Test
	public void testGetMap() {
		assertTrue(account.getMap().equals(map));
		assertTrue(account.getMap() == map);
		assertTrue(account.getMap().length == map.length);
	}
	
	@Test
	public void testSetMap() {
		Location[][][] map2 = new Location[15][15][15];
		account.setMap(map2);
		assertTrue(account.getMap().equals(map2));
		assertTrue(account.getMap() == map2);
		assertTrue(account.getMap().length == map2.length);
	}
	
}