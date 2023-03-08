import java.util.ArrayList;

public class PokerHand extends Deck
{ 
    /** the ranks of hands, from High Card, to Straight Flush */
    private String[] handRanks;
        
    /** the relative numeric rank of the hand, for comparison */
    private int rank;
    
    /** the scores of the hands under the English Poker Soliatire system */
    private int[] handValues;

    /**
     * Class default constructor populating the names of Poker Hands
     */    
    public PokerHand()
    {
        deck = new ArrayList<Card>();
        handRanks = new String[] {"High Card", "One Pair", "Two Pair", "Three of a Kind",
            "Straight", "Flush", "Full House", "Four of a Kind", "Straight Flush"};
        handValues = new int[] {0,1,3,6,12,5,10,16,30};
    }
    
    /**
     * Class default constructor creating a hand from a specific set of cards.
     */ 
    public PokerHand(Card... cards)
    {
        this();
        for (Card card: cards)
            deck.add(card);
    }
       
    /**
     * Class constructor creating a hand by dealing five cards off the top of another Deck.
     * 
     * @param otherDeck A deck of cards
     */ 
    public PokerHand(Deck otherDeck)
    {
        this();
        for (int i = 0; i < 5; i++)
            deck.add(otherDeck.removeCard(0));
    }
    
    /**
     * Returns a numerical ranking of the pokerhand, where 0 represents High Card,
     * and 8 represents a Straight Flush.  The hands are numbered this way so that the number can
     * also be used for array indexes for various purposes
     * 
     * @return a number from 0-8 indicating the relative strength of the poker hand.
     * @see getRankString
     */

    public int getRankInt()
    {
        if (isStraightFlush()) return 8;
        if (isFourOfAKind())   return 7;
        if (isFullHouse())     return 6;
        if (isFlush())         return 5;
        if (isStraight())      return 4;
        if (isThreeOfAKind())  return 3;
        if (isTwoPair())       return 2;
        if (isOnePair())       return 1;
        return 0;
    }

    /**
     * Returns a scoring value for the hand under the English scoring system.
     * 
     * @return a number from 0-30 indicating the solitaire value of the poker hand.
     * @see getRankInt
     */
    public int getHandValue()
    {
        return handValues[getRankInt()];
    }

    /**
     * Returns a textual representation of the name of the poker hand.
     *  
     * @return a String indicating the name of the poker hand.
     * @see getRankInt
     */
    public String getRankString()
    {
        return handRanks[getRankInt()];
    }

    /**
     * @return a String representation of the hand's cards and its rank.
     */
    public String toString()
    {
        return super.toString() + getRankString();
    }

    /**
     * Provides a String representation of the summary of a hand.
     * <p>
     * The summary includes the short names of each card, the rank
     * of the hand, and the scoring value of the hand.
     * <p>
     * Sample Output:
     * <pre>9S KC KD KS 3H - Three of a Kind (6)</pre>
     * 
     * @return the String representation of a hand's summary
     */   
    public String getShortOutput()
    {
        String str = "";
        str += getShortString() + "- ";
        str += getRankString() + " (" + getHandValue() + ")";
       return str;
    }
    
    /** 
     * This is a helper method that assists with determining hand rank.
     * <p>
     * If you compare every pair of cards in a five-card hand, and count the number of pairs that match,
     * there are ten comparisons.  For "One Pair" there will be one match, for "Two Pair" there will be two,
     * for "Three of a kind" there will be three, for "Full House" there will be 5, and for "Four of a Kind"
     * there will be six.  So finding out what type of hand you have, other than straights or flushes,
     * is just a matter of counting the matches.
     * <p>
     * To allow for unknown cards, or empty spaces in a poker square,
     * cards with a value of 0 will not match with each other.
     * 
     * @return the number of cards with the same rank in the deck.
     */
    private int getNumMatches()
    {
        int count = 0;
        for (int i = 0; i < deck.size()-1; i++)
            for (int j = i+1; j < deck.size(); j++){
                if (deck.get(i).getValue() == deck.get(j).getValue())
                  if (deck.get(i).getValue() != 0)
                    count++;
            }      
        return count;
    }

    /**
     * Determines whether the poker hand contains One Pair.
     * <p>
     * A hand contains One Pair if the number of times cards' ranks
     * match is 1. For the sample hand below, the first two cards match, 
     * so there is one pair.
     * <pre>AH AS KC QD 5H</pre>
     * 
     * @return true if the hand contains one pair, false otherwise.
     */
    public boolean isOnePair()
    {
        return getNumMatches() == 1;
    }

    /**
     * Determines whether the poker hand contains Two Pairs
     * <p>
     * A hand contains Two Pairs if the number of times cards' ranks
     * match is 2. For the sample hand below, the first and second card match,
     * and the third and four cards match, so there are two pairs.
     * 
     * <pre>AH AS QC QD 5H</pre>
     * 
     * @return true if the hand contains two pairs, false otherwise.
     */
    public boolean isTwoPair()
    {
        return getNumMatches() == 2;
    }

    /**
     * Determines whether the poker hand contains Three of a Kind
     * <p>
     * A hand contains Three of a Kind if the number of times cards' ranks
     * match is 3. For the sample hand below, the first and second cards match, 
     * the first and third cards match, and the second and third cards match.
     * So there is three of a kind.
     * 
     * <pre>AH AS AC QD 5H</pre>
     * 
     * @return true if the hand contains three of a kind, false otherwise.
     */
    public boolean isThreeOfAKind()
    {
        return getNumMatches() == 3;
    }

    /**
     * Determines whether the poker hand contains a Full House
     * <p>
     * A hand contains a Full House if the number of times cards' ranks
     * match is 4. For the sample hand below, the first and second cards match, 
     * the first and third cards match, the second and third cards match,
     * and the fourth and fifth cards match.  So there is one pair.
     * 
     * <pre>AH AS AC 5D 5H</pre>
     * 
     * @return true if the hand contains a full house, false otherwise.
     */
    public boolean isFullHouse()
    {
        return getNumMatches() == 4;
    }

    /**
     * Determines whether the poker hand contains Four of a Kind
     * <p>
     * A hand contains Four of a Kind if the number of times cards' ranks
     * match is 6. For the sample hand below: the first and second cards match, 
     * the first and third cards match, the first and fourth cards match,
     * the second and third cards match, the second and fourth cards match,
     * and the third and fourth cards match.  So there is four of a kind.
     * 
     * <pre>AH AS KC QD 5H</pre>
     * 
     * @return true if the hand contains four of a kind, false otherwise.
     */
    public boolean isFourOfAKind()
    {
        return getNumMatches() == 6;
    }

    /**
     * Determines whether the poker hand contains a Straight Flush
     * 
     * A hand that is a straight flush is both a straight and a flush.
     * So I simple check the state of methods that solve for those conditions.
     * 
     * @return true if the hand contains a straight flush, false otherwise.
     */
    public boolean isStraightFlush()
    {
        //System.out.println(isSingleSuited() + " " + isConsecutive());
        return (isStraight() && isFlush());
    }

    /**
     * Determines whether the poker hand contains a Straight
     * <br>
     * A hand is a straight if the five cards are in consecutive order.
     * <br>
     * This method creates a version of copy of the hand that is sorted by value.
     * It then determines if each card is one value larger than the card before it.
     * 
     * @return true if the hand contains a straight, false otherwise.
     */    
    public boolean isStraight()
    {
        ArrayList<Card> sortedHand = sortByValue();
        for (int i = 1; i < sortedHand.size(); i++)
            if (sortedHand.get(i-1).getValue() + 1 != sortedHand.get(i).getValue())
                return false;
        return true;  
    }

    /**
     * Determines whether the poker hand contains a Flush
     * <br>
     * A hand is a flush if all five cards are the same suit.
     * <br>
     * This traverses the hand and determines whether each
     * card has the same suit or not.  A hand of blanks is not a flush.
     * 
     * @return true if the hand contains a flush, false otherwise.
     */
    public boolean isFlush()
    {
        for (Card card: deck)
          //System.out.print(card.getSuit());
          if (card.getValue() == 0)
             return false;        
        for (int i = 1; i < deck.size(); i++)
            if (!(deck.get(i).getSuit().equals(deck.get(0).getSuit())))
                return false;
        return true;
    }

    /**
     * Creates a sorted version of the poker hand
     * <p>
     * This is a helper method to help the isStraight method.
     * It creates a copy of the original deck into a new ArrayList.
     * Then the copy is sorted and returned.  That makes it easier for
     * isStraight to function because it can receive a copy of the sorted
     * hand and then work with it.
     * <p>
     * @return the cards in the hand, sorted by rank.
     */
    private ArrayList<Card> sortByValue()
    {
        ArrayList<Card> sorted = new ArrayList<Card>(deck);
        Card key;
        int i, j;
        for (i = 1; i < sorted.size(); i++)
        {
            key = sorted.get(i);
            j = i - 1;
            while (j >= 0 && sorted.get(j).getValue() > key.getValue())
            {
                sorted.set(j+1, sorted.get(j));
                j = j - 1;
            }
            sorted.set(j + 1, key);
        }
        return sorted;
    }
}
