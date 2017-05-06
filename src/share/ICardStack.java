
/**
  * File:       ICardStack.java
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

/**
 * Interface for stack of cards.
 */
public interface ICardStack extends ICardDeck, ISupportFill{
      
    /**
     * Inserts deck to stack.
     * @param deck deck of cards
     * @return if successful.
     */
    public boolean put(ICardDeck deck);
     
    /**
     * Get part of card deck from top until card is met.
     * @param card card to look for
     * @return part of deck.
     */
    public ICardDeck pop(ICard card);
    
    /**
     * Get card by value.
     * @param value value of card
     * @return card with that value.
     */
    public ICard getByValue(int value);
    
    /**
     * Get card by color.
     * @param color color of card
     * @return card of that color.
     */
    public ICard getByColor(ICard.Color color);
}