package edu.ycp.cs320.IslandAdventure.persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ycp.cs320.IslandAdventure.model.Account;
import edu.ycp.cs320.IslandAdventure.model.Item;
import edu.ycp.cs320.IslandAdventure.model.*;
// This code is heavily based off of InitialData.java from CS320_Lab06 by Prof. Hake.
public class InitialData {
	public static List<Room> getRooms() throws IOException {
		List<Room> roomList = new ArrayList<Room>();
		ReadCSV readRooms = new ReadCSV("rooms.csv");
		try {
			// auto-generated primary key for authors table
			Integer authorId = 1;
			while (true) {
				List<String> tuple = readRooms.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Room room = new Room(null, null, null, false, false, false, false, false, false, false);
				Location location = new Location(0, 0, 0);
				//author.setAuthorId(authorId++);				
				// room ID, account_id, and username will be added later
				location.setX(Integer.parseInt(i.next()));
				location.setY(Integer.parseInt(i.next()));
				location.setZ(Integer.parseInt(i.next()));
				room.setLocation(location);
				room.setLongDescription(i.next());
				room.setVisible(Integer.parseInt(i.next()) == 1 ? true : false);
				room.setGoNorth(Integer.parseInt(i.next()) == 1 ? true : false);
				room.setGoEast(Integer.parseInt(i.next()) == 1 ? true : false);
				room.setGoSouth(Integer.parseInt(i.next()) == 1 ? true : false);
				room.setGoWest(Integer.parseInt(i.next()) == 1 ? true : false);
				room.setGoUp(Integer.parseInt(i.next()) == 1 ? true : false);
				room.setGoDown(Integer.parseInt(i.next()) == 1 ? true : false);
				room.setShortDescription(i.next());
				roomList.add(room);// Add the Room!
			}
			return roomList;
		} finally {
			readRooms.close();
		}
	}
	
	public static List<Item> getItems() throws IOException {
		List<Item> itemList = new ArrayList<Item>();
		ReadCSV readItems = new ReadCSV("items.csv");
		try {
			// auto-generated primary key for books table
			while (true) {
				List<String> tuple = readItems.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				int type = Integer.parseInt(i.next());
				if (type > 0) {
					Weapon weapon = new Weapon(null, null, null, type);
					weapon.setName(i.next());
					weapon.setDescript(i.next());
					weapon.setUses(Integer.parseInt(i.next()));
					weapon.setDamage(type);
					Location location = new Location(0,0,0);
					location.setX(Integer.parseInt(i.next()));
					location.setY(Integer.parseInt(i.next()));
					location.setZ(Integer.parseInt(i.next()));
					weapon.setLocation(location);
					itemList.add(weapon);
				}
				else if (type < 0) {
					Armor armor = new Armor(null, null, null, type);
					armor.setName(i.next());
					armor.setDescript(i.next());
					armor.setUses(Integer.parseInt(i.next()));
					armor.setArmorAmount(type);
					Location location = new Location(0,0,0);
					location.setX(Integer.parseInt(i.next()));
					location.setY(Integer.parseInt(i.next()));
					location.setZ(Integer.parseInt(i.next()));
					armor.setLocation(location);
					itemList.add(armor);
				}
				else {
					Item item = new Item(null, null, null, 1);
					item.setName(i.next());
					item.setDescript(i.next());
					item.setUses(Integer.parseInt(i.next()));
					Location location = new Location(0,0,0);
					location.setX(Integer.parseInt(i.next()));
					location.setY(Integer.parseInt(i.next()));
					location.setZ(Integer.parseInt(i.next()));
					item.setLocation(location);
					itemList.add(item);
				}
				
			}
			return itemList;
		} finally {
			readItems.close();
		}
	}
	
	public static List<Enemy> getEnemies() throws IOException {
		List<Enemy> enemyList = new ArrayList<Enemy>();
		ReadCSV readEnemies = new ReadCSV("enemies.csv");
		try {
			// auto-generated primary key for books table
			while (true) {
				List<String> tuple = readEnemies.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Enemy enemy = new Enemy(null, null, null, null, null);
				enemy.setName(i.next());
				enemy.setDescript(i.next());
				enemy.setHealth(Integer.parseInt(i.next()));
				enemy.setDamage(Integer.parseInt(i.next()));
				Location location = new Location(0,0,0);
				location.setX(Integer.parseInt(i.next()));
				location.setY(Integer.parseInt(i.next()));
				location.setZ(Integer.parseInt(i.next()));
				enemy.setLocation(location);
				System.out.println("InitialData >> Enemy Location: " + location.getX() + "," + location.getY()+","+ location.getZ());
				enemyList.add(enemy);
			}
			return enemyList;
		} finally {
			readEnemies.close();
		}
	}
}
