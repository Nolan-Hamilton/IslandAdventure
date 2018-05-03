package edu.ycp.cs320.IslandAdventure.MapView;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import edu.ycp.cs320.IslandAdventure.model.*;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

// You won't need to modify this class.
public class MapFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public MapFrame() {
		//Empty constructor
	}

	public void displayMap(Account account) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MapFrame frame = new MapFrame();
				frame.add(new MapPanel(account)); // This is where the other file goes
				frame.pack();
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.addKeyListener(new KeyAdapter() { 
					@Override
					public void keyPressed(KeyEvent e){
						if (e.getKeyCode() == KeyEvent.VK_ENTER){
							frame.dispose();
						}
					}
				});
				frame.setTitle("Island Adventure Map (PRESS ENTER TO EXIT)");
				frame.getContentPane().setBackground(Color.BLACK);
				frame.setVisible(true);
				frame.toFront();
			}
		});
	}
}
