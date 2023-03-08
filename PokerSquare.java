import java.util.ArrayList;

public class PokerSquare
{ 
  /** A grid that will hold a PokerSquare */
  private Card[][] square;
  
  /** A one-dimensional array to hold the ten poker hands of the square */
  private PokerHand[] PokerHands;
  
  /** The score value of the square */
  private int score;
  

  public PokerSquare()
  {
    square = new Card[5][5];
     Deck pokerDeck = new Deck();
     score = 0;        
     PokerHands = new PokerHand[10];            
     pokerDeck.shuffle();    
     for (int i = 0; i < 5; i++)
       for (int j = 0; j < 5; j++)
         square[i][j] = new Card ("Blank", "Blanks", 0, " ", " ");
  }
  
   /**
   * The PokerSqure constructor instantiates the square, a standard deck of cards,
   * the PokerHands array, and the score variable.  It also shuffles the deck
   * and then deals the top 25 cards of the deck to build the 5x5 square in row-major order. 
   * 
   * @Exception NullPointerException if there are fewer than 25 cards in the deck.
   */
  public PokerSquare(Deck foo)
  {  
     square = new Card[5][5];
     Deck pokerDeck = new Deck();
     score = 0;        
     PokerHands = new PokerHand[10];            
     pokerDeck.shuffle();    
     for (int i = 0; i < 5; i++)
       for (int j = 0; j < 5; j++)
         square[i][j] = pokerDeck.removeCard(0);
  }
  
  /**
   * Constructs ten poker hands in the PokerHands array.
   * 
   * Hands 0-4 are generated from the rows of the square, while
   * hands 5-9 are generated from the columns of the square.
   */
  public void buildHands()
  {
      for (int i = 0; i < 5; i++)
      {
          PokerHands[i] = new PokerHand(square[i][0], square[i][1], square[i][2], square[i][3], square[i][4]);
          PokerHands[i+5] = new PokerHand(square[0][i], square[1][i], square[2][i], square[3][i], square[4][i]);
      } 
  }
  
  /**
   * Provides a summary of the game state
   * 
   * Two columns of data have space allocated.  The left column
   * contains the game state for the rows of the square, while the right
   * column contains the game state for the columns of the square.  The state
   * includes the hand rank and value for all ten hands, along with the score
   * for the entire square.
   * 
   * Sample output:
   * <p><pre>
   * ROWS                                  COLUMNS<br>
   * --------------                        --------------
   * 9D 6H 3D 8S KC - High Card (0)        9D JH 9S 4S TS - One Pair (1)      
   * JH 8H 2C 4H KH - High Card (0)        6H 8H 5C 8C AD - One Pair (1)       
   * 9S 5C 7C KD 7S - One Pair (1)         3D 2C 7C 3H 7H - Two Pair (3)      
   * 4S 8C 3H 4D JC - One Pair (1)         8S 4H KD 4D QC - One Pair (1)    
   * TS AD 7H QC 6D - High Card (0)        KC KH 7S JC 6D - One Pair (1)     
   *
   * Score: 9</pre>
   * 
   * @return a summary of the game state.
   */
  public String getSummary()
  {
      String str = "";
      str += "ROWS                                  COLUMNS\n";
      str += "--------------                        --------------\n";
      for (int i = 0; i < 5; i++)
      {
         str += String.format("%-38s",PokerHands[i].getShortOutput());
         str += String.format("%-38s",PokerHands[i+5].getShortOutput());
         str += "\n";
      }        
      str += "\nScore: " + getScore();
      return str;
  }
  
  public void setCard(int i, int j, Card card)
  {
    square[i][j] = card;
  }
  
  public Card getCard(int i, int j)
  {
   return square[i][j];
  }

  /**
   * Provides the names of each poker hand, separated by line.
   * The hands names of the rows are printed first, followed by
   * the names of the column hands.  Other than line returns, there
   * is no formatting applied.
   * <p>
   * Not used currently, but useful during testing.
   * 
   * @return the names of the hands in the squares
   */
  public String getHandNames()
  {
      String str = "";
      for (PokerHand hand : PokerHands)
        str += hand.getRankString() + "\n";
      return str;
  }      
  
  /**
   * Provides a String representation of the Poker Square.
   * <p>
   * A sample representation follows:
   * <p><pre>
   *     1    2    3     4    5    
   * A [AC] [4H] [JC] [4S] [AH] 
   * B [5C] [JD] [4D] [AS] [8C] 
   * C [7H] [9D] [5D] [2H] [QH] 
   * D [6S] [KD] [5S] [KS] [7D] 
   * E [5H] [AD] [TC] [TD] [2D] </pre>
   * 
   * @return a String representation of the square.
   */
  public String toString()
  {
      String ret = "";
      ret += "    1    2    3    4    5\n";   
      for (int i = 0; i < 5; i++)
      {
          ret += (char)(i+65) + " ";
          for (int j = 0; j < 5; j++)
              ret += "[" + square[i][j].getShortString() + "] ";
          ret += "\n";

      }
      return ret;
  }
  
  /**
   * Provides the current numeric score of the square.
   * <p>
   * It resets score to 0, traverses through each hand,
   * and adds that hand's value to the score.
   * <p>
   * Then it returns the score.
   * 
   * @return the square's score.
   */
  public int getScore()
  {
      score = 0;
      for (PokerHand hand : PokerHands)
      {
          score += hand.getHandValue();
      }
      return score;
  }
}
