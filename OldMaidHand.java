//********************************************************************
//  OldMaidHand.java     
//
//  @Heidi Shimek
//********************************************************************



import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;

public class OldMaidHand extends CardStack
{
	private String name;
	private CardStack cards;
	Scanner scan = new Scanner(System.in);

	public OldMaidHand(String name)
	{
		super();
		this.name = name;
	}

	public void removePairs()
	{
		int i = 0;
		int j = 0;
		int k = 0;
		while (i < super.getSize())
		{
			j = i + 1;
			while (j < super.getSize())
			{
				if (super.getCard(i).getFace() == super.getCard(j).getFace())
				{
					super.removeCard(super.getCard(j));
					k++;
					break;
				}
				j++;
			}
			if (k == 1)
			{
				super.removeCard(super.getCard(i));
				k = 0;
			}
			else
			{
				i++;
			}
		}
	}

	public void display()
	{
		System.out.println(name + "'s Hand: " + super.toString());
		System.out.println();
	}

	public void addHand(Card card)
	{
		this.cards.addCard(card);
	}

	public Card drawCard()
	{
		Random random = new Random();
		System.out.println("Choose a card from " + this.getName() + "'s hand");
   		System.out.println(name + "'s Hand: " + super.toStringWithIndex());
    	int i;
    	i = scan.nextInt();
   	 	while (i < 1 || i > super.getSize())
   	 	{
      		System.out.println("Invalid! Choose a card: ");
     		i=scan.nextInt();
    	}
		Card c = super.getCard(i-1);
		super.removeCard(c);
		return c;
	}

	public void shuffle()
	{
		Random random = new Random();
		for (int j = 0; j < super.getSize(); j++)
		{
			swap(j, random.nextInt(super.getSize()));
		}
	}

	public String getName()
	{
		return name;
	}

	public String toString()
	{
		return this.cards.toString();
	}
}