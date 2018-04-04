package edu.ycp.cs320.IslandAdventure.model;

import java.util.ArrayList;


public class Account 
{
	// Fields
	private String username;
	private String password;
	private Player player;
	private Location map[][][];
	private ArrayList<GameObject> objectList;
	private ArrayList<Location> mapLocations;
	
	//Constructors

	
	public Account(String username, String password, Player player, Location[][][] map){
		this.username = username;
		this.password = password;
		this.player = player;
		this.map = map;
		objectList = new ArrayList<GameObject>();
		mapLocations = new ArrayList<Location>();
	}
	
	
	// Methods
	
	public void initialize(){
		// Set a knife at 10,10,0 and a hammer at 10,9,0.
		for (int z = 0; z < 25; z++) {
			for (int y = 0; y < 25; y++) {
				for (int x = 0; x < 25; x++) {
					String descript = "Description: Coordinates of this room are " + x + ", " + y + ", " + z;
					Location local = new Location(x,y,z, descript);
					mapLocations.add(local);
				}
			}
		}
		Location location = new Location(10,10,0, "This is the starting room.");
		Item knife = new Item("Knife", "A small kife with a dull single sided blade.", location, 10);
		objectList.add(knife);
		Location location2 = new Location(10,9,0, "This is the secondary room.");
		Item hammer = new Item("Hammer", "A rock tied to a stick.", location2, 10);
		objectList.add(hammer);
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
	
	public Location[][][] getMap(){
		return this.map;
	}
	
	public void setMap(Location[][][] map){
		this.map = map;
	}
	
	public ArrayList<GameObject> getObjectList() {
		return this.objectList;
	}
	
	public void setObjectList(ArrayList<GameObject> list){
		this.objectList = list;
	}
	
	public ArrayList<Location> getLocations() {
		return this.mapLocations;
	}
	
	public void setLocationsList(ArrayList<Location> list) {
		this.mapLocations = list;
	}
	
	public Location getLocationByXYZ(int x, int y, int z) {
		Location found = null;
		for (Location location : mapLocations){
			if (location.getX() == x && location.getY() == y && location.getZ() == z) {
				found = location;
			}
		}
		return found;
	}
	
	public ArrayList<GameObject> getObjectsByXYZ(int x, int y, int z){
		ArrayList<GameObject> results = new ArrayList<GameObject>();
		for (GameObject object : objectList) {
			if (object.getX() == x && object.getY() == y && object.getZ() == z) {
				results.add(object);
			}
		}
		return results;
	}
}
