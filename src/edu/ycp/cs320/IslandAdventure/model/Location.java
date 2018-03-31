package edu.ycp.cs320.IslandAdventure.model;

public class Location {
	
	private int x;
	private int y;
	private int z;
	private String description;

	
	public Location(int a, int b, int c)
	{
		x = a;
		y = b;
		z = c;
	}
	
	public Location(int a, int b, int c, String description){
		this.x = a;
		this.y = b;
		this.z = c;
		this.description = description;
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
	
	public void setDescription(String descript) {
		this.description = descript;
	}
	
	public String getDescription() {
		return this.description;
	}
}
	
	/*
	 * put this in a move class for player
	 * 
	private boolean visible;
	private boolean possibleMove;
	 * 
	 * 	possibleMove = true; //move these to a move class 
		visible = false;
		
		
			public void setPossibleMove(boolean pos)
	{
		this.possibleMove = pos;
	}
	
	public boolean getPossibleMove()
	{
		return possibleMove;
	}
	
	public void setVisable(boolean vis)
	{
		this.visible = vis;
	}

	public boolean getVisable()
	{
		return visible;
	}
	
		public void testPossibleMoves()
	{
		assertTrue(loc.getPossibleMove());
		loc.setPossibleMove(false);
		assertFalse(loc.getPossibleMove());
	}

	public void testVisable()
	{
		assertFalse(loc.getVisable());
		loc.setVisible(true);
		assertTrue(loc.getVisible());
	}

		
	 */
	
	
	

