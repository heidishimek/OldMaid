//********************************************************************
//  OldMaid.java
//
//  @Heidi Shimek
//********************************************************************


import java.util.*;

public class OldMaid
{
	public static void main(String[] args)
	{
		 boolean play = true;

		 Scanner scan = new Scanner(System.in);

		 Deck deck;

		 ArrayList<OldMaidPlayer> cards = new ArrayList<OldMaidPlayer>();

		 ArrayList<CardStack> players = new ArrayList<CardStack>();

		 OldMaid game = new OldMaid();
		 game.getPlayers(scan, players, cards);

		 while (play)
		 {	
		 	ArrayList<CardStack> playersWon = new ArrayList<>();
		 	deck = new Deck();
		 	game.showHand(deck, players);

		 	System.out.println();

		 	System.out.println("Removed Pairs: ");
		 	for (int i = 0; i < players.size(); i++)
		 	{
		 		((OldMaidHand) players.get(i)).removePairs();
		 		((OldMaidHand) players.get(i)).display();
		 	}

		 	System.out.println();

		 	System.out.println("Remaining on each hand: ");
		 	for (int i = 0; i < players.size(); i++)
		 	{
		 		((OldMaidHand) players.get(i)).display();
		 		System.out.println();
		 	}

		 	Random r = new Random();
		 	int i = r.nextInt(players.size());
		 	System.out.println(((OldMaidHand) players.get(i)).getName() + 
		 		" goes first!");
		 	System.out.println();
		 	int index = 0;
		 	Card c;

		 	while (players.size() != 1)
		 	{
		 		if (i >= (players.size()))
		 		{
		 			i = 0;
		 		}

		 		if (i == 0)
		 		{
        			System.out.println(((OldMaidHand) players.get(i)).getName() + 
		 				" is drawing card from "+((OldMaidHand) players.get(players.size() - 1)).getName()+"\'s cards" );

		 			c = ((OldMaidHand) players.get(players.size() - 1)).drawCard();

		 			if (players.get(players.size() - 1).getSize() == 0)
		 			{
		 				index++;
		 				game.update(players.get(0), cards, index);
		 				playersWon.add(players.get(0));
		 				players.remove(players.get(0));
		 			}
		 			players.get(0).addCard(c);
					
		 			((OldMaidHand) players.get(0)).removePairs();

		 			if (players.get(0).getSize() != 0)
		 			{
		 				((OldMaidHand) players.get(0)).shuffle();
		 			}

		 			if (players.get(0).getSize() == 0)
		 			{
		 				index++;
		 				game.update(players.get(0), cards, index);
		 				playersWon.add(players.get(0));
		 				players.remove(players.get(0));
		 			}
		 			else
		 			{
		 				i++;
		 			}
		 		}

		 		else
		 		{
           			System.out.println(((OldMaidHand) players.get(i)).getName() + 
		 				" is drawing card from "+((OldMaidHand) players.get(i - 1)).getName()+"\'s cards" );
		 			c = ((OldMaidHand) players.get(i - 1)).drawCard();

		 			if (players.get(i - 1).getSize() == 0)
		 			{
		 				index++;
		 				game.update(players.get(i - 1), cards, index);
		 				playersWon.add(players.get(i - 1));
		 				players.remove(players.get(i - 1));
		 			}
		 			if (i >= players.size())
		 			{
		 				i = players.size() - 1;
		 			}
		 			players.get(i).addCard(c);

		 			System.out.println("Remove pairs from " + ((OldMaidHand) players.get(i)).getName() + "'s hand");
		 			((OldMaidHand) players.get(i)).removePairs();

		 			if (players.get(i).getSize() != 0)
		 			{
		 				((OldMaidHand) players.get(i)).shuffle();
		 			} 

		 			if (players.get(i).getSize() == 0)
		 			{
		 				index++;
		 				game.update(players.get(i), cards, index);
		 				playersWon.add(players.get(i));
		 				players.remove(players.get(i));
					}
		 			else
		 			{
		 				i++;
					}
		 		}
		 	}

		 		game.update(players.get(0), cards);

		 		System.out.print("Scores: ");
		 		for (int x = 0; x < cards.size(); x++)
		 		{
		 			System.out.println(cards.get(x));
		 		}

		 		System.out.println("Play again? y or n");
		 		String playAgain = scan.nextLine();
		 		if (playAgain.equals("y"))
		 		{
		 			for (int a = 0; a < playersWon.size(); a++)
		 			{
		 				players.add(playersWon.get(a));
		 			}
		 		}
		 		else
		 		{
		 			play = false;
		 		}
		 	}
		 }

		 public void getPlayers(Scanner scan, ArrayList<CardStack> players, ArrayList<OldMaidPlayer> cards)
		 {
		 	boolean play = true;
		 	int thePlayers = 0;
		 	while (play)
		 	{
		 		System.out.println("OLD MAID");
		 		System.out.println("----------------------");
		 		System.out.println();
		 		System.out.println("The deck is shuffled and one card is removed. Each player will be dealt cards and the pairs will be removed from each hand.");
		 		System.out.println("You will pick a card from the designated player and pairs will be removed if there was a pair.");
		 		System.out.println("The game will continue until one player is left with the OLD MAID!");
		 		System.out.println();
		 		System.out.println("-----------------------");
		 		System.out.println("Enter the number of players (3 - 5): ");
		 		thePlayers = scan.nextInt();
		 		String i = scan.nextLine();
		 		if (thePlayers < 3 || thePlayers > 5)
		 		{
		 			System.out.print("Error! Enter number of players between 3 and 5.");
		 			continue;
		 		}
		 		else
		 		{
		 			break;
		 		}
		 	}

		 	String name;
		 	for (int i = 0; i < thePlayers; i++)
		 	{
		 		System.out.println("Enter name of player " + (i+1) + ":");
		 		name = scan.nextLine();
		 		players.add(new OldMaidHand(name));
		 		cards.add(new OldMaidPlayer(name));
		 	}
		 }

		 public void showHand(Deck deck, ArrayList<CardStack> players)
		 {
		 	int i = 0;
		 	while (deck.getSize() != 0)
		 	{
		 		players.get(i).addCard(deck.deal());
		 		i++;

		 		if (i == players.size())
		 		{
		 			i = 0;
		 		}
		 	}
		 	for (int j = 0; j < players.size(); j++)
		 	{
		 		((OldMaidHand) players.get(j)).display();
		 	}
		 }

		 public void update(CardStack players, ArrayList<OldMaidPlayer> cards)
		 {
		 	for (int i = 0; i < cards.size(); i++)
		 	{
		 		if (((OldMaidHand) players).getName() == cards.get(i).getName())
		 		{
		 			cards.get(i).lost();
		 		}
		 	}
		 }

		 public void update(CardStack players, ArrayList<OldMaidPlayer> cards, int index)
		 {
		 	for (int i = 0; i < cards.size(); i++)
		 	{
		 		if (((OldMaidHand) players).getName() == cards.get(i).getName() && index == 1)
		 		{
		 			cards.get(i).won();
		 			cards.get(i).setPoints(3);
		 		}
		 		else if (((OldMaidHand) players).getName() == cards.get(i).getName() && index < cards.size())
		 		{
		 			cards.get(i).setPoints(1);
		 		}
		 	}
		 }
}