
/**
  * File:       ICardDeck.java
  * Author:     Jan Hrstka
  * Login:      xhrstk02
  * University: BUT (Brno University of Technology)
  * Faculty:    FIT (Faculty of Information Technology)
  * Course:     IJA (Java Programming Language)
  * Project:    Solitaire Klondike Game
  * Proj. Num:  4
  * Version:    1
  * Date:       11.04.2017
  * System:     GNU/Linux, x86_64, Ubuntu 16.04 LTS
  */
package src.share;

// Dependencies
import java.io.Serializable;

/**
 * Interface for deck of cards.
 */
public interface ICardDeck extends Serializable{
    
    /**
     * Insert card.
     * @param card card to insert
     * @return if successful.
     */
    public boolean put(ICard card);
    
    /**
     * Get card from deck and remove it.
     * @return card from deck.
     */
    public ICard pop();
    
    /**
     * Get card from top of deck
     * @return card from top.
     */
    public ICard top();
    
    /**
     * Get this card.
     * @return this card.
     */
    public ICard get();
    
    /**
     * Get card by its index.
     * @param index index of card
     * @return card on index.
     */
    public ICard get(int index);
    
    /**
     * Does this deck contain this card?
     * @param card card to check
     * @return if successful.
     */
    public boolean contains(ICard card);
     
    /**
     * Does it contain this value?.
     * @param value value to check
     * @return if it does.
     */
    public boolean containsValue(int value);
    
    /**
     * Is deck empty?
     * @return if is empty.
     */
    public boolean isEmpty();
    
    /**
     * Get size of deck.
     * @return size of deck.
     */
    public int size();
    
    /**
     * Emplace card here.
     * @param card card to emplace here.
     */
    public void emplace(ICard card);
    
    /**
     * Can card be putted?
     * @param card card to put here
     * @return if successful.
     */
    public boolean canPut(ICard card);
    
    /**
     * Set deck name.
     * @param name  name of deck.
     */
    public void setName(String name);
    
    /**
     * Get deck name.
     * @return name of deck.
     */
    public String getName();
}