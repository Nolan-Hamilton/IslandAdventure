package edu.ycp.cs320.IslandAdventure.persist;

import java.util.Scanner;

import edu.ycp.cs320.IslandAdventure.model.Player;

public class InsertNewPlayer 
{
	public static void main(String[] args, Player player) throws Exception 
	{
		Scanner keyboard = new Scanner(System.in);

		// Create the default IDatabase instance
		InitDatabase.init(keyboard);
		
		System.out.print("Player Username: ");
		String username = keyboard.nextLine();
		 
		// get the DB instance and execute transaction
		IDatabase db = DatabaseProvider.getInstance();
//		Integer author_id = db.retrieveAccount(username)

		// check if anything was returned and output the list
//		if (author_id == null) 
//		{
//			db.addAuthorWithAuthorName(firstName, lastName);
//			author_id = db.findAuthorIDByAuthorName(firstName, lastName);
//		}
		int author_id = 0;
		
		db.addPlayer(author_id, player.getScore(), player.getHealth(), player.getStamina(), player.getTime(),
				player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
	}
}
