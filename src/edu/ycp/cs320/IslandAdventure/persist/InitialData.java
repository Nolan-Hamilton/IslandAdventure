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
			}
			return roomList;
		} finally {
			readRooms.close();
		}
	}
	/*
	public static List<Book> getBooks() throws IOException {
		List<Book> bookList = new ArrayList<Book>();
		ReadCSV readBooks = new ReadCSV("books.csv");
		try {
			// auto-generated primary key for books table
			Integer bookId = 1;
			while (true) {
				List<String> tuple = readBooks.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Book book = new Book();
				book.setBookId(bookId++);
				book.setAuthorId(Integer.parseInt(i.next()));
				book.setTitle(i.next());
				book.setIsbn(i.next());
				book.setPublished(Integer.parseInt(i.next()));
				bookList.add(book);
			}
			return bookList;
		} finally {
			readBooks.close();
		}
	}*/
}
