public class Card {
    /** The card's rank.  Example: <b>King</b> */
    private String rank;
    
    /** The card's suit.  Example: <b>Spades</b> */
    private String suit;
    
    /** The card's short rank.  Example: <b>K</b> */  
    private String shortRank;
    
    /** The card's short suit.  Example: <b>S</b> */  
    private String shortSuit;
    
    /** The card's value.  Example: <b>13</b> */  
    private int value;
    
    /**
     * The card's no-parameter constructor.
     * <p>
     * This will build what is esentially a blank card.  A blank card is a
     * "Blank of Blanks" with short rank and short suits as " " and value 0.
     * <p>
     * This is handy for testing and can also be used as an upside-down card, 
     * or as an empty space.  By settings its value as 0, we can avoid
     * accidentally tripping a false straight because in our default deck, 
     * the lowest value for a "real" card is 2.
     */
    public Card()
    {
        this("Blanks", "Blank", 0, " ", " ");
    }
    
     /**
     * The card's default constructor including long names and values only.
     * <p>
     * This constructor does not include the short name or the short suit.  This isn't
     * really used anymore since PokerSquares requires short name and short suit to work.
     * However, this is included as an exercise to make you write three construtors
     * 
     * @param r The card's rank
     * @param s The card's suit
     * @param v The card's value
     */
    public Card(String r, String s, int v)
    {
        this(r, s, v, " ", " ");       
    }
     
    /**
     * The card's default constructor including long names, values, and short names.
     * <p>
     * This constructor does not include the short name or the short suit.  This isn't
     * really used anymore since PokerSquares requires short name and short suit to work.
     * However, this is included as an exercise to make you write three construtors
     * 
     * @param r The card's rank
     * @param s The card's suit
     * @param v The card's value
     * @param sr The card's short rank
     * @param ss The card's short suit.
     */
    public Card (String r, String s, int v, String sr, String ss)
    {
        suit = s;
        rank = r;
        value = v;
        shortSuit = ss;
        shortRank = sr;
    }
    
    /**
     * Provides a short description of the rank. For a default deck of cards, 
     * this is a character in the following subset: <b>AKQJT98765432</b>
     * 
     * @return the short version of a card's rank.
     */
    public String getShortRank()
    {
        return shortRank;
    }

    /**
     * Provides a short description of the card's suit. For a default deck of cards, 
     * this is a character in the following subset: <b>HDSC</b>
     * 
     * @return the short version of a card's suit.
     */    
    public String getShortSuit()
    {
        return shortSuit;
    }
    
    /**
     * Provides a short description of the card's rank + suit.  For example,
     * in a default deck of cards, Jack of Clubs is represented as <b>JC</b>
     * 
     * @return the short version of the card's rank and suit.
     */
    public String getShortString()
    {
        return getShortRank()+getShortSuit();
    }
    
    /**
     * Provides a long description of the card.  This includes the long name,
     * as well as, parenthetically, its short name and relative value.
     * For example, in a default deck of cards, the Jack of Clubs is represented as:
     * <b>Jack of Clubs (JC, 11)</b>
     * 
     * @return the long description of the card
     */
    public String toString()
    {
        String ret = "";
        ret += getRank() + 
        " of " + getSuit() + 
        " (" + getShortString() + ", " + getValue() + ")";
        return ret;
    }

    /**
     * Provides the name of a card's rank.
     * Examples includes <b>Jack</b> and <b>Seven</b>
     * 
     * @return the name of the card's rank.
     */
    public String getRank() {return rank;}

     /**
     * Provides the name of a card's suit.
     * Examples includes <b>Hearts</b> and <b>Spades</b>
     * 
     * @return the name of the card's suit.
     */
    public String getSuit() {return suit;}

    /**
     * Provides the value of a card.
     * Default values will be in the range of 2-14 (for an Ace)
     * while Blank cards will have a value of 0.
     * 
     * @return the value of a card.
     */
    public int getValue() {return value;}
    
}