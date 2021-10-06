//********************************************************************
//  Deck.java     
//
//  @Heidi Shimek
//********************************************************************

import java.util.*;

public class Deck extends CardStack
{
	private int index = 0;

	public Deck()
	{
		super();
		for (int i = Card.CLUBS; i <= Card.SPADES; i++)
		{
			for (int j = Card.ACE; j <= Card.KING; j++)
			{
				super.addCard(new Card(j, i));
				index++;
			}
		}

		System.out.println("The card removed: " + super.randomDeal());
		
		System.out.println("Shuffling!");
		this.shuffle();
	}

	public void shuffle()
	{
		Random i = new Random();
		for (int j = 0; j < super.getSize(); j++)
		{
			swap(j, i.nextInt(super.getSize()));
		}
	}


}