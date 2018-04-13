package edu.ycp.cs320.IslandAdventure.MapView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import edu.ycp.cs320.IslandAdventure.model.*;

import javax.swing.JPanel;

public class MapPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private static final int WIDTH = 800;
	private static final int HEIGHT = 800;
	
	
	
	// TODO: add fields to store state
	int[][] map;
	int numRows;
	int numCols;
	int rowBound;
	int colBound;
	int floor;
	int roomWidth;
	int roomHeight;
	
	// constructor
	public MapPanel(Account account) {
		//Get Information about current map
		numRows = account.getMaxRowOfMap() + 1;
		numCols = account.getMaxColumnOfMap() + 1;
		rowBound = account.getMaxRowOfMap();
		colBound = account.getMaxColumnOfMap();
		floor = account.getPlayer().getLocation().getZ();
		
		roomWidth = WIDTH / numCols;
		roomHeight = HEIGHT / numRows;
		
		// Create an two dimensional array and set all elements to zero by default
		map = new int[numCols][numRows];
		for (int y = 0; y < numRows; y++){
			for (int x = 0; x < numCols; x++){
				map[x][y] = 0;	// This ensures non-existent rooms are set to zero
			}
		}
		// iterate through all of the existing rooms and the visible values of corresponding coordinates in the array
		// This avoid null pointer exceptions when trying to get visible value of non-existent room;
		for (Room room : account.getRooms()){
			// Only work with rooms on current floor
			if (room.getLocation().getZ() == floor){
				int x = room.getLocation().getX();
				int y = room.getLocation().getY();
				map[x][y] = room.getVisible() ? 1 : 0;
				// If the current room is where the player is located
				if (room.getLocation().equals(account.getPlayer().getLocation())){
					map[x][y] = 2;
				}
			}
		}
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.BLACK);
		
		repaint();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g); // paint background
		
		for (int y = 0; y < numRows; y ++){
			for (int x = 0; x < numCols; x++){
				if (map[x][y] == 1){
					g.setColor(Color.BLUE);	// Visited location is blue
					g.fillRect((x * roomWidth), (y * roomHeight), roomWidth, roomHeight);
				}else if (map[x][y] == 2){	
					g.setColor(Color.BLUE);	// Visited location is blue
					g.fillRect((x * roomWidth), (y * roomHeight), roomWidth, roomHeight);
					g.setColor(Color.RED);	// Player's location is red
					g.fillOval((x * roomWidth), (y * roomHeight), roomWidth, roomHeight);
				}else{
					g.setColor(Color.BLACK);// Non-Visited location is black
					g.fillRect((x * roomWidth), (y * roomHeight), roomWidth, roomHeight);
				}
				g.setColor(Color.GREEN);
				g.fillRect((x * roomWidth), 0, 1, HEIGHT);
			}
			g.setColor(Color.GREEN);
			g.fillRect(0, (y * roomHeight), WIDTH, 1);
		}
	}
}
