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
	
	//Constructors

	
	public Account(String username, String password, Player player, Location[][][] map){
		this.username = username;
		this.password = password;
		this.player = player;
		this.map = map;
		objectList = new ArrayList<GameObject>();
	}
	
	
	// Methods
	
	public void initialize(){
		// Set a knife at 10,10,0 and a hammer at 10,9,0.
		Location location = new Location(10,10,0, "This is the starting room.");
		Item knife = new Item("Knife", "A small kife with a dull single sided blade.", 10,10,0, location, 10);
		objectList.add(knife);
		Location location2 = new Location(10,9,0, "This is the secondary room.");
		Item hammer = new Item("Hammer", "A rock tied to a stick.", 10,9,0, location2, 10);
		objectList.add(hammer);
		map[10][10][0].setLocation(location);
		map[10][9][0].setLocation(location2);
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
}
