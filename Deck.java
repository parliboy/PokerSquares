import java.util.ArrayList;

public class Deck { 
    /** The deck of cards */
    protected ArrayList<Card> deck;

    /** The default card ranks by name */
    static final private String[] rk = {"Ace", "Two", "Three", "Four", "Five", "Six",
            "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"} ;
    
    /** The default card suits by name */
    static final private String[] st = {"Hearts", "Diamonds", "Spades", "Clubs"};
    
    /** The relative values of the cards, from 2 to 14 */
    static final private int[] vl = {14, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    
    /** The short ranks of the cards, represented as a single letter */  
    static final private String[] rkShort = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};
    
    /** The short suits of the cards, represented as a single letter */
    static final private String[] stShort = {"H", "D", "S", "C"};
    //private String[] stShort = {"♥", "♦", "♠", "♣"};
    
    /** 
     * No-parameter constructor for the deck
     * <p>
     * It instantiates the deck and then populates it.  This involves
     * two loops that traverse the length of the ranks and the suits.
     * It also involves invoking the five-parameter version of the card constructor.
     * <p>
     * Actaully, mine just uses the <code>this</code> keyword and hands the arrays
     * off to the five-parameter constructor, because I'm lazy.  This is why the arrays
     * at the top all
     */
    public Deck() {
        this(rk,st,vl,rkShort,stShort);
    }
    
    /**
     * Three parameter constructor for the deck.
     * <p>
     * It instantiates the deck and then populates it.  This involves
     * two loops that traverse the length of the ranks and the suits.
     * It also involves invoking the three-parameter version of the card constructor.
     * <p>
     * This constructor exists in case you wish to use card definitions other than
     * the ones provided at the top of this class.
     */
    public Deck(String[] ranks, String[] suits, int[] values) {
        deck = new ArrayList<Card>();
        for (int i = 0; i < ranks.length; i++)
            for (String suit : suits)
                deck.add(new Card(ranks[i], suit, values[i]));
    }
    
    /** 
     * Five-parameter constructor for the deck
     * <p>
     * It instantiates the deck and then populates it.  This involves
     * two loops that traverse the length of the ranks and the suits.
     * It also involves invoking the five-parameter version of the card constructor.
     * <p>
     * This constructor exists in case you wish to use card definitions other than
     * the ones provided at the top of this class.
     */
    public Deck(String[] ranks, String[] suits, int[] values, String[] shortRanks, String[] shortSuits) {
        deck = new ArrayList<Card>();
        for (int i = 0; i < ranks.length; i++)
            for (int j = 0; j < suits.length; j++)
                deck.add(new Card(ranks[i], suits[j], values[i], shortRanks[i], shortSuits[j]));
    }
    
    /**
     * Adds a card to the bottom of the deck.
     * 
     * @param card the card to be added
     */
    public void addCard(Card card)
    {
      deck.add(card);    
    }
    
    /**
     * Return a card at a given position in the deck.  If the card does not exist,
     * an empty card is returned instead.
     * 
     * @param i the position of the card you want to check.
     * @return the card at a given position, 
     *         or an empty card if that position doesn't exist.
     */
    public Card getCard(int i)
    {
        return (i < deck.size() ? deck.get(i) : new Card());
    }
    
     /**
     * Removes and returns a card at a given position in the deck.
     * If the card does not exist, no card is removed, 
     * and an empty card is returned instead.
     * 
     * @param i the position of the card you want to check.
     * @return the now-removed card at a given position, 
     *         or an empty card if that position doesn't exist.
     */
    public Card removeCard(int i)
    {
        return (i < deck.size() ? deck.remove(i) : new Card());
    }

    /**
     * Returns the number of cards in the deck
     * <p>
     * Useful in case other classes need to know the size of the deck.
     * 
     * @return number of cards in the deck
     */
    public int getSize()
    {
        return deck.size();   
    }

    /**
     * This is a standard shuffle routine
     * 
     * It works by creating a new empty deck, randomly moving cards
     * from the original deck to the new deck, and then pointing
     * the memory reference of the old deck to the new deck.
     */  
    public void shuffle()
    {
        ArrayList<Card> newDeck = new ArrayList<Card>();
        while (deck.size() > 0)
            newDeck.add(deck.remove((int)(Math.random() * deck.size())));
        deck = newDeck;
    }

    /**
     * Provides a list of all the cards in the deck.
     * Works by invoking the toString methods of each card.
     * 
     * @return A list of all the cards
     */
    public String toString()
    {            
        String ret = "";
        for (Card card: deck)
            ret += card + "\n";
        return ret;
    }

     /**
     * Provides a list of all the cards in the deck.
     * Works by invoking the getShortString methods of each card.
     * 
     * @return A list of the short names of all of the cards
     */  
    public String getShortString()
    {
        String ret = "";
        for (Card card: deck)
            ret += card.getShortString() + " ";
        return ret;
    }
}