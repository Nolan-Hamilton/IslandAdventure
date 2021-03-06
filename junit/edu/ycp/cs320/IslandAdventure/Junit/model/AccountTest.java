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
	private Location location;
	private Inventory inventory;
	private ArrayList<GameObject> objects;
	Item knife;
	Item hammer;
	ArrayList<Room> roomList;
	Location location2;
	
	
	@Before
	public void setUp(){
		location = new Location(10,10,10);
		player = new Player(0, 0, 0, 0, inventory, location, null, null, null);
		account = new Account("MyName", "SomePassword", player);
		objects = new ArrayList<GameObject>();
		knife = new Item("knife", null, location, null);
		hammer = new Item("hammer", null, location, null);
		objects.add(knife);
		account.getObjectList().add(hammer);
		roomList = new ArrayList<Room>();
		location2 = new Location(5,5,5);
		Room room2 = new Room(location2, "Descript2", "shortDescript2", false, false, false, false, false, false, false);
		roomList.add(room2);
		Room room = new Room(location, "Descript", "shortDescript", false, false, false, false, false, false, false);
		account.getRooms().add(room);
		
		account.getItemList().add(hammer);
		account.getItemList().add(knife);
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
	public void testSetPassword() {
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
	public void testGetRooms() {
		assertTrue(account.getRooms().get(0).getLocation().equals(location));
	}
	
	
	
	@Test
	public void testSetRoomsList() {
		account.setRoomsList(roomList);
		assertTrue(account.getRooms().get(0).getLocation().equals(location2));
	}
	
	@Test
	public void testGetRoombyXYZ() {
		assertTrue(account.getRoomByXYZ(10, 10, 10).getLocation().equals(location));
	}
	
	@Test
	public void testGetMaxRowOfMap(){
		int max = account.getMaxRowOfMap();
		assertTrue(max == 10);
	}
	
	@Test
	public void testGetMaxColumnOfMap(){
		int max = account.getMaxColumnOfMap();
		assertTrue(max == 10);
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
	
	@Test
	public void testGetItemIndexByNameAndXYZ(){
		assertEquals(0, account.getObjectIndexByNameAndXYZ("hammer", 10, 10, 10));
		assertTrue(account.getObjectIndexByNameAndXYZ("Hammer", 10, 10, 10) == 0);
		assertEquals(1, account.getObjectIndexByNameAndXYZ("knife", 10, 10, 10));
	}
	
}