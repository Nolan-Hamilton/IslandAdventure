package edu.ycp.cs320.IslandAdventure.model;

/* This is the Location class which is used by all GameObjects and the Player class.
 * This class represents the coordinates of objects on the map.
 * 
 * */

public class Location 
{
	private int x;
	private int y;
	private int z;

	
	public Location(int a, int b, int c)
	{
		x = a;
		y = b;
		z = c;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public int getZ()
	{
		return z;
	}
	public void setX(int a)
	{
		this.x = a;
	}
	
	public void setY(int b)
	{
		this.y = b;
	}
	
	public void setZ(int c)
	{
		this.z = c;
	}
	public void setLocation(int a, int b, int c)
	{
		this.x = a;
		this.y = b;
		this.z = c;
	}
	
	public void setLocation(Location loc)
	{
		this.setX(loc.getX());
		this.setY(loc.getY());
		this.setZ(loc.getZ());
	}
	
	public boolean equals(Location loc)
	{
		if(this.getX() == loc.getX() && this.getY() == loc.getY() && this.getZ() == loc.getZ())
		{
			return true;
		}
		else
			return false;
	}
}
	
	
	

