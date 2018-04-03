package edu.ycp.cs320.IslandAdventure.Junit.model;

/* These are the jUnit tests for account class. */

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.IslandAdventure.model.*;

public class AccountTest {
	private Account account;
	private Player	player;
	private Location[][][] map;
	private Location location;
	private Inventory inventory;
	private ArrayList<GameObject> objects;
	Item knife;
	Item hammer;
	ArrayList<Location> locationList;
	Location location2;
	
	
	@Before
	public void setUp(){
		location = new Location(10,10,10);
		player = new Player(0, 0, 0, 0, inventory, location, null, null, null);
		map = new Location[25][25][25];
		account = new Account("MyName", "SomePassword", player, map);
		objects = new ArrayList<GameObject>();
		knife = new Item(null, null, null, null, null, location, null);
		hammer = new Item(null, null, null, null, null, location, null);
		objects.add(knife);
		account.getObjectList().add(hammer);
		locationList = new ArrayList<Location>();
		location2 = new Location(5,5,5);
		locationList.add(location2);
		account.getLocations().add(location);
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
		Player player2 = new Player(0, 0, 0, 0, inventory, location, null, null, null);
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
	
	@Test
	public void testGetObjectList() {
		assertTrue(account.getObjectList().get(0).equals(hammer));
	}
	
	@Test
	public void testSetObjectList() {
		account.setObjectList(objects);
		assertTrue(account.getObjectList().get(0).equals(knife));
	}
	
	@Test
	public void testGetLocations() {
		assertTrue(account.getLocations().get(0).equals(location));
	}
	
	
	
	@Test
	public void testSetLocationsList() {
		account.setLocationsList(locationList);
		assertTrue(account.getLocations().get(0).equals(location2));
	}
	
	@Test
	public void testGetLocationByXYZ() {
		assertTrue(account.getLocationByXYZ(10, 10, 10).equals(location));
	}
	
	@Test
	public void testGetObjectsByXYZ() {
		//knife.setLocation(location);
		account.getObjectList().get(0).setX(10);
		account.getObjectList().get(0).setY(10);
		account.getObjectList().get(0).setZ(10);
		//assertTrue(account.getObjectList().size() == 2);
		assertTrue(account.getObjectsByXYZ(10,10,10).get(0).equals(account.getObjectList().get(0)));
	}
	
}