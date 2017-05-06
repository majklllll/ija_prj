
/**
  * File:       ICardHint.java
  * @author     Jan Hrstka
  * Login:      xhrstk02
  * University: BUT (Brno University of Technology)
  * Faculty:    FIT (Faculty of Information Technology)
  * Course:     IJA (Java Programming Language)
  * Project:    Solitaire Klondike Game
  * Proj. Num:  4
  * Version:    1
  * Date:       14.04.2017
  * System:     GNU/Linux, x86_64, Ubuntu 16.04 LTS
  */
package src.share;

// Dependecies
import java.util.ArrayList;

/**
 * Interface for game board hints of card movements.
 */
public interface ICardHint{
     
    /**
     * Get card of this hint.
     * @return hinted card.
     */
    public ICard getCard();
    
    /**
     * Get card decks of this hint.
     * @return list of decks.
     */    
    public ArrayList<ICardDeck> getCardDecks();
      
    /**
     * Get stack of cards of this hint.
     * @return list of stacks.
     */
    public ArrayList<ICardStack> getCardStacks();
      
    /**
     * Get source deck of this hint.
     * @return source of hint.
     */
    public ICardDeck getSourceDeck();
     
    /**
     * Is this hint empty?
     * @return if true.
     */
    public boolean isEmpty();
     
    /**
     * Has this hint card stack?
     * @return if true.
     */
    public boolean hasCardStack();
    
    /**
     * Has this hint card deck?
     * @return if true.
     */
    public boolean hasCardDeck();
    
    /**
     * Has this hint source deck?
     * @return if true.
     */
    public boolean hasSourceDeck();
     
    /**
     * Get name of this hint.
     * @return string representation of hint.
     */
    public String toString();
    
    /**
     * Add stack to hints.
     * @param stack stack of cards
     * @return if added.
     */
    public boolean add(ICardStack stack);
    
    /**
     * Add deck to hints.
     * @param deck  deck to add to this hint
     * @return if added.
     */
    public boolean add(ICardDeck deck);
}