import java.util.*;

public class Game
{
    public static void main(String[] args)
    {
        String[] rk = {"Ace", "Two", "Three", "Four", "Five", "Six",
                "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"} ;
        String[] st = {"Hearts", "Diamonds", "Spades", "Clubs"};
        int[] vl = {14, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        String[] rkShort = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
        String[] stShort = {"H", "D", "S", "C"};

        Scanner scan = new Scanner(System.in);
        int xPos;
        int yPos;
        String loc;
        Card next;
        //String[] stShort = {"♥", "♦", "♠", "♣"};

        //Deck deck52 = new Deck(rk, st, vl, rkShort, stShort);
        //System.out.println(deck52);

        //shuffle the deck()
        // deck52.shuffle();

        PokerSquare square = new PokerSquare();
        Deck deck = new Deck();
        deck.shuffle();
        System.out.println(square.toString());
        square.buildHands();
        System.out.println(square.getSummary());

        for (int i = 0; i < 25; i++)
        {
            next = deck.removeCard(0);
            System.out.println("The next card is: -> " + next);
            while (true)
            {   
                System.out.print("Enter the coordinate for the next card -> ");
                loc = scan.nextLine();
                if (loc.length() >= 2)
                {  
                  xPos = Character.toUpperCase(loc.charAt(0))-65;
                  yPos = loc.charAt(1)-49;
                  if (xPos >= 0 && xPos <= 5 && yPos >= 0 && yPos <= 5)
                    if (square.getCard(xPos,yPos).getSuit().contains("Blank"))
                      break;
                }
                System.out.print("Invalid coordinate. ");
            }
            square.setCard(xPos, yPos, next);
            System.out.print('\u000C');
            System.out.println(square.toString());
            square.buildHands();
            System.out.println(square.getSummary());
        }
        System.out.println("\nGAME OVER!");
    }

       
    //         System.out.print(""+'\u000C'+i);

    //PokerHand hand = new PokerHand(deck52);
    //System.out.println("*HAND* size: " + hand.getSize());
    //System.out.println(hand);

    //deck52.sortBySuit();
    //System.out.println("*DECK* size: " + deck52.getSize());
    //System.out.println(deck52);

}