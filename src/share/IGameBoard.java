
/**
  * File:       IGameBoard.java
  * Author:     Jan Hrstka
  * Login:      xhrstk02
  * University: BUT (Brno University of Technology)
  * Faculty:    FIT (Faculty of Information Technology)
  * Course:     IJA (Java Programming Language)
  * Project:    Solitaire Klondike Game
  * Proj. Num:  4
  * Version:    1
  * Date:       12.04.2017
  * System:     GNU/Linux, x86_64, Ubuntu 16.04 LTS
  */
package src.share;

// Dependencies
import java.util.ArrayList;

/**
 * Interface for game board.
 */
public interface IGameBoard{
         
    /**
     * Get color of card.
     * @return color of card.
     */
    public void registerObserver(ISupportRepaint observer);
          
    /**
     * Get color of card.
     * @return color of card.
     */
    public ICardRepository getRepository();
          
    /**
     * Get color of card.
     * @return color of card.
     */
    public ICardDeck getDeck(int index);
           
    /**
     * Get color of card.
     * @return color of card.
     */
    public ICardStack getStack(int index);
           
    /**
     * Get color of card.
     * @return color of card.
     */
    public int deckCount();
          
    /**
     * Get color of card.
     * @return color of card.
     */
    public int stackCount();
          
    /**
     * Get color of card.
     * @return color of card.
     */
    public void update();
          
    /**
     * Get color of card.
     * @return color of card.
     */
    public boolean hintAvailable();
        
    /**
     * Get color of card.
     * @return color of card.
     */
    public ArrayList<ICardHint> getHints();
      
    /**
     * Get color of card.
     * @return color of card.
     */
    public void createHints();
      
    /**
     * Get color of card.
     * @return color of card.
     */
    public ICardHint hintForCard(ICard card);
      
    /**
     * Get color of card.
     * @return color of card.
     */
    public boolean save(String fileName);
     
    /**
     * Get color of card.
     * @return color of card.
     */
    public boolean load(String fileName);
    
    /**
     * Get color of card.
     * @return color of card.
     */
    public boolean isGameOver();
}