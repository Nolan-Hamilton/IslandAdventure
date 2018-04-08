package edu.ycp.cs320.IslandAdventure.model;

/* This is the Room class from with the map will be made up of.
 * This class represents the actual rooms on the map
 * 
 * */

public class Room {
	private Location location;
	private String description;
	boolean visible;
	boolean goNorth;
	boolean goEast;
	boolean goSouth;
	boolean goWest;
	boolean goUp;
	boolean goDown;

	
	public Room(Location loc, String descript, boolean vis, boolean gN, boolean gE,
			boolean gS, boolean gW, boolean gU, boolean gD){
		this.location = loc;
		this.description = descript;
		this.visible = vis;
		this.goNorth = gN;
		this.goEast = gE;
		this.goSouth = gS;
		this.goWest = gW;
		this.goUp = gU;
		this.goDown = gD;
	}
	
	public Location getLocation() {
		return this.location;
	}
	
	public void setLocation(int a, int b, int c)
	{
		this.location.setX(a);
		this.location.setY(b);
		this.location.setZ(c);
	}
	
	public void setLocation(Location loc)
	{
		this.location = loc;
	}
	
	public void setDescription(String descript) {
		this.description = descript;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setVisible(boolean vis) {
		this.visible = vis;
	}
	
	public boolean getVisible() {
		return this.visible;
	}
	
	public boolean getGoNorth() {
		return this.goNorth;
	}
	
	public void setGoNorth(boolean bool) {
		this.goNorth = bool;
	}
	
	public boolean getGoEast() {
		return this.goEast;
	}
	
	public void setGoEast(boolean bool) {
		this.goEast = bool;
	}

	public boolean getGoSouth() {
		return this.goSouth;
	}
	
	public void setGoSouth(boolean bool) {
		this.goSouth = bool;
	}

	public boolean getGoWest() {
		return this.goWest;
	}
	
	public void setGoWest(boolean bool) {
		this.goWest = bool;
	}

	public boolean getGoUp() {
		return this.goUp;
	}
	
	public void setGoUp(boolean bool) {
		this.goUp = bool;
	}

	public boolean getGoDown() {
		return this.goDown;
	}
	
	public void setGoDown(boolean bool) {
		this.goDown = bool;
	}
	
}