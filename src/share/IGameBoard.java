
/**
  * File:       IGameBoard.java
  * @author     Jan Hrstka
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
 * Interface for game boards.
 */
public interface IGameBoard{
         
    /**
     * Register new observer.
     * @param observer observer to be notified about changes.
     */
    public void registerObserver(ISupportRepaint observer);
          
    /**
     * Get card repository.
     * @return card repository.
     */
    public ICardRepository getRepository();
          
    /**
     * Get deck at given position.
     * @param index position of deck to be retrieved.
     * @return deck at given position.
     */
    public ICardDeck getDeck(int index);
           
    /**
     * Get stack at given position.
     * @param index position of stack to be retrieved.
     * @return stack at given position.
     */
    public ICardStack getStack(int index);
           
    /**
     * Get count of decks in board.
     * @return count of decks in board.
     */
    public int deckCount();
          
    /**
     * Get count of stacks in board.
     * @return count of stacks in board.
     */
    public int stackCount();
          
    /**
     * Notify all observers about changes.
     */
    public void update();
          
    /**
     * Check whenever is any nint availavle.
     * @return true when there is at least one hint.
     */
    public boolean hintAvailable();
        
    /**
     * Get hints for all cards.
     * @return list of hints.
     */
    public ArrayList<ICardHint> getHints();
      
    /**
     * Create hints for all cards.
     */
    public void createHints();
      
    /**
     * Create card hint for give card.
     * @param card card for which should be hint constructed.
     * @return hint for given card.
     */
    public ICardHint hintForCard(ICard card);
      
    /**
     * Save state of board into given file.
     * @param fileName name of file into which shoud be board saved.
     * @return true when board was saved successfuly.
     */
    public boolean save(String fileName);
     
    /**
     * Load state of board from given file.
     * @param fileName name of file from which shoud be board loaded.
     * @return true when board was loaded successfuly.
     */
    public boolean load(String fileName);
    
    /**
     * Check if game has ended.
     * @return true when game is over.
     */
    public boolean isGameOver();
}