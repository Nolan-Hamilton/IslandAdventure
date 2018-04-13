package edu.ycp.cs320.IslandAdventure.model;

import java.util.ArrayList;


public class Account 
{
	// Fields
	private String username;
	private String password;
	private Player player;
	private ArrayList<GameObject> objectList;
	private ArrayList<Room> mapRooms;
	private ArrayList<Item> itemList;
	
	//Constructors

	
	public Account(String username, String password, Player player){
		this.username = username;
		this.password = password;
		this.player = player;
		objectList = new ArrayList<GameObject>();
		mapRooms = new ArrayList<Room>();
		itemList = new ArrayList<Item>();
	}
	
	
	// Methods
	
	public void initialize(){
		// Set a knife at 10,10,0 and a hammer at 10,9,0.
		for (int z = 0; z < 3; z++) {
			for (int y = 0; y < 15; y++) {
				for (int x = 0; x < 15; x++) {
					String descript = "Description: Coordinates of this room are " + x + ", " + y + ", " + z;
					Location local = new Location(x,y,z);
					Room room = new Room(local, descript, false, true, true, true, true, true, true);
					mapRooms.add(room);
				}
			}
		}
		Location location = new Location(10,10,0);
		Item knife = new Item("Knife", "A small kife with a dull single sided blade.", location, 10);
		itemList.add(knife);
		Location location2 = new Location(10,9,0);
		Item hammer = new Item("Hammer", "A rock tied to a stick.", location2, 10);
		itemList.add(hammer);
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public Player getPlayer(){
		return this.player;
	}
	
	public void setPlayer(Player player){
		this.player = player;
	}
	
	public ArrayList<GameObject> getObjectList() {
		return this.objectList;
	}
	
	public void setObjectList(ArrayList<GameObject> list){
		this.objectList = list;
	}
	
	public ArrayList<Room> getRooms() {
		return this.mapRooms;
	}
	
	public void setRoomsList(ArrayList<Room> list) {
		this.mapRooms = list;
	}

	public ArrayList<Item> getItemList() {
		return this.itemList;
	}
	
	public void setItemList(ArrayList<Item> list){
		this.itemList = list;
	}
	
	public Room getRoomByXYZ(int x, int y, int z) {
		Room found = null;
		Location target = new Location(x,y,z);
		for (Room room : mapRooms){
			if (room.getLocation().equals(target)) {
				found = room;
			}
		}
		return found;
	}
	
	public ArrayList<GameObject> getObjectsByXYZ(int x, int y, int z){ //This can be changed to use location comparison
		ArrayList<GameObject> results = new ArrayList<GameObject>();
		for (GameObject object : objectList) {
			if (object.getX() == x && object.getY() == y && object.getZ() == z) {
				results.add(object);
			}
		}
		return results;
	}
	
	// Needs to be Tested!
	public int getMaxRowOfMap(){
		int rows = 0;
		for (Room room: mapRooms){
			if (room.getLocation().getY() > rows){
				rows = room.getLocation().getY();
			}
		}
		return rows;
	}
	
	public int getMaxColumnOfMap(){
		int columns = 0;
		for (Room room: mapRooms){
			if (room.getLocation().getY() > columns){
				columns = room.getLocation().getY();
			}
		}
		return columns;
	}
	
	public ArrayList<Item> getItemsByXYZ(int x, int y, int z){
		ArrayList<Item> results = new ArrayList<Item>();
		for (Item item : itemList) {
			if (item.getX() == x && item.getY() == y && item.getZ() == z) {
				results.add(item);
			}
		}
		return results;
	}
}
