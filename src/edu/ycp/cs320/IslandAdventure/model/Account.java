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
	private ArrayList<Enemy> enemyList;
	
	//Constructors

	
	public Account(String username, String password, Player player){
		this.username = username;
		this.password = password;
		this.player = player;
		objectList = new ArrayList<GameObject>();
		mapRooms = new ArrayList<Room>();
		itemList = new ArrayList<Item>();
		enemyList = new ArrayList<Enemy>();
	}
	
	
	// Methods
	
	public void initialize(){
		// Set a knife at 10,10,0 and a hammer at 10,9,0.
		for (int z = 0; z < 3; z++) {
			for (int y = 0; y < 15; y++) {
				for (int x = 0; x < 15; x++) {
					String descript1 = "Long Description: Coordinates of this room are " + x + ", " + y + ", " + z;
					String descript2 = "Short Description: Coordinates are " + x + ", " + y + ", " + z;
					Location local = new Location(x,y,z);
					boolean top = true, right = true, bottom = true, left = true;
					if (x == 14){ right = false;}
					if (x == 0){ left = false;}
					if (y == 14){ bottom = false;}	// If on bottom row, can't go south
					if (y == 0){ top = false;}
					Room room = new Room(local, descript1, descript2, false, top, right, bottom, left, false, false);
					mapRooms.add(room);
				}
			}
		}
		Location location = new Location(10,10,0);
		Weapon knife = new Weapon("Knife", "A small kife with a dull single sided blade.", location, 5);
		itemList.add(knife);
		Location location2 = new Location(10,9,0);
		Item hammer = new Item("Hammer", "A rock tied to a stick.", location2, 10);
		itemList.add(hammer);
		this.getRoomByXYZ(5, 5, 0).setGoDown(true);	// This is where the cave entrance is.
		this.getRoomByXYZ(5, 5, 1).setGoUp(true);	// This is where the cave exit is.
		this.getRoomByXYZ(9,9,1).setGoDown(true);	// This is where the dungeon entrance is.
		this.getRoomByXYZ(9,9,2).setGoUp(true);		// This is where the dungeon exit is.
		//Item lantern = new Item("Lantern", "For seeing in the dark", new Location(4,4,0), 10);
		//itemList.add(lantern); //This lantern is no longer needed now that you can craft a torch
		Location location3 = new Location(2,3,0);
		Enemy enemyBoss = new Enemy("Island King", "The king of the island", 500, location3, 100);
		enemyList.add(enemyBoss);
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
	
	public ArrayList<Enemy> getEnemyList() {
		return this.enemyList;
	}
	
	public void setEnemyList(ArrayList<Enemy> list){
		this.enemyList = list;
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
	
	public ArrayList<Enemy> getEnemiesByXYZ(int x, int y, int z){
		ArrayList<Enemy> results = new ArrayList<Enemy>();
		for (Enemy enemy : enemyList) {
			if (enemy.getX() == x && enemy.getY() == y && enemy.getZ() == z) {
				results.add(enemy);
			}
		}
		return results;
	}
	
	public int getObjectIndexByNameAndXYZ(String name, int x, int y, int z){
		int index = -1;
		for (Item item : itemList){
			//System.out.println(item.getName() + item.getX() + item.getY()+ item.getZ());
			//System.out.println(name + x + y + z);
			//System.out.println(item.getName().toLowerCase().equals(name.toLowerCase()));
			if (item.getName().toLowerCase().equals(name.toLowerCase()) && item.getX() == x && item.getY() == y && item.getZ() == z) {
				index = itemList.indexOf(item);
				//System.out.println(objectList.indexOf(item));
			}
		}
		return index;
	}
}
