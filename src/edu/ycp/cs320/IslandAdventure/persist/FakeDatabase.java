package edu.ycp.cs320.IslandAdventure.persist;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ycp.cs320.IslandAdventure.model.Account;
import edu.ycp.cs320.IslandAdventure.model.Inventory;
import edu.ycp.cs320.IslandAdventure.model.Player;
import edu.ycp.cs320.IslandAdventure.model.Location;
import edu.ycp.cs320.IslandAdventure.model.GameObject;

public class FakeDatabase implements IDatabase {
	
	private List<Account> accounts;
	
	public FakeDatabase() {
		accounts = new ArrayList<Account>();
		
		// Add initial data (not yet implemented
		//readInitialData();
		
		System.out.println(accounts.size() + " accounts");
	}
	
	@Override
	public Account retrieveAccount(String username) {
		Account result = new Account(null, null, null, null);
		// Look for account and load its data
		for (Account account : accounts) {
			if (account.getUsername().equals(username)) {
				result = account;
			}
		}
		return result;
	}
	
	public List<Account> getAccountList(){
		return this.accounts;
	}
	
	/*
	public void readInitialData() {
		try {
			authorList.addAll(InitialData.getAuthors());
			bookList.addAll(InitialData.getBooks());
		} catch (IOException e) {
			throw new IllegalStateException("Couldn't read initial data", e);
		}
	}
	
	@Override
	public List<Pair<Author, Book>> findAuthorAndBookByTitle(String title) {
		List<Pair<Author, Book>> result = new ArrayList<Pair<Author,Book>>();
		for (Book book : bookList) {
			if (book.getTitle().equals(title)) {
				Author author = findAuthorByAuthorId(book.getAuthorId());
				result.add(new Pair<Author, Book>(author, book));
			}
		}
		return result;
	}
	
	// Implementation of findAuthorAndBookByAuthorLastName for Task #1 BooksByAuthorLastNameQuery
	@Override
	public List<Pair<Author, Book>> findAuthorAndBookByAuthorLastName(String lastName) {
		List<Pair<Author, Book>> result = new ArrayList<Pair<Author,Book>>();
		for (Author author : authorList){
			// Iterate through authorList to find authors with lastName
			if (author.getLastname().equals(lastName)){
				// Whenever author lastName is found, get authorId and use to search books
				for (Book book : bookList){
					// Compare author IDs
					if (book.getAuthorId() == author.getAuthorId()){
						// if matching ID is found, add that books to result with associated author
						result.add(new Pair<Author, Book>(author, book));
					}
				}
			}
		}
		return result;
	}
	
	// Implementation of findAuthorAndBookByAuthorLastName for Task #1 BooksByAuthorLastNameQuery
	@Override
	public List<Pair<Author, Book>> insertNewBook(String lastName, String firstName, String title, String isbn, int published) {
		boolean found = false;
		int authorID = 0;
		for (Author author : authorList){
			// Iterate through authorList to find authors with lastName and firstName
			if ((author.getLastname().equals(lastName)) && (author.getFirstname().equals(firstName))){
				// Whenever author author is found, set found to true and get the authorID
				found = true;
				authorID = author.getAuthorId();
			}
		}
		if (found == true) {
			Book newBook = new Book();			// Create new book
			newBook.setAuthorId(authorID);		// Set attributes of new book
			newBook.setTitle(title);
			newBook.setIsbn(isbn);
			newBook.setPublished(published);
			bookList.add(newBook);				// Add new book to bookList
			System.out.println("New Book Added");
			
			// Find book in fake database
			List<Pair<Author, Book>> result = findAuthorAndBookByTitle(title);
			return result;
		}else {
			System.out.println("New Author");
			//First add new Author
			int maxID = 0;
			for (Author author : authorList){	// Loops through the authors and find the highest authorId
				if (author.getAuthorId() > maxID) {
					maxID = author.getAuthorId();
				}
			}
			Author newAuthor = new Author();	// Create New author
			int newID = maxID + 1;				// Set next authorID which has not been used yet.
			newAuthor.setAuthorId(newID);		// Set new author attributes
			newAuthor.setLastname(lastName);
			newAuthor.setFirstname(firstName);
			authorList.add(newAuthor);			// Add author to authorList
			
			// Now Add book
			Book newBook = new Book();			// Create new book
			newBook.setAuthorId(newID);		// Set attributes of new book (Use ID of new author)
			newBook.setTitle(title);
			newBook.setIsbn(isbn);
			newBook.setPublished(published);
			bookList.add(newBook);				// Add new book to bookList
			System.out.println("New Book Added");
			
			// Search for the book in the fake database
			List<Pair<Author, Book>> result = findAuthorAndBookByTitle(title);
			return result;
		}
	}

	private Author findAuthorByAuthorId(int authorId) {
		for (Author author : authorList) {
			if (author.getAuthorId() == authorId) {
				return author;
			}
		}
		return null;
	}
	*/
}
