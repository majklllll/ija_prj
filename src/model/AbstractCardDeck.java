
/**
  * File:       AbstractCardDeck.java
  * @author     Jan Hrstka
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
package src.model;

// Dependencies
import java.util.ArrayList;
import src.share.ICardDeck;
import src.share.ICard; 

/**
 * Base class representing deck/group of cards. 
 * Provides stacking cards with bound check.
 * 
 */
public abstract class AbstractCardDeck extends Object implements ICardDeck{
    protected String objectName              = "";
    protected int maximalSize                = 0;
    protected ArrayList<ICard> cards         = new ArrayList<ICard>();  
    public static final int StandardDeckSize = 52;

    /**
     * Specifies insertion rules and whenever can be card inserted into deck.
     * @param card ICard to be checked.
     * @return true when specified card is suitable, passed all insertion rules and can be inserted into stack.
     */
    protected abstract boolean canPutCard(ICard card);

    /**
     * Specifies what indexes (cards) can be accessed.
     * @param index Position of card in deck.
     * @return true when card at given position can be accessed using public interface. 
     */
    protected abstract boolean canAccessIndex(int index);

    /**
     * Check if specified card can be inserted into deck.
     * @param card ICard to be checked.
     * @return true when card can be inserted into stack.
     */
    public boolean canPut(ICard card){
        return this.canPutCard(card) && this.size() < this.maximalSize;
    }
    
    /**
     * Insert card into deck, before this operation checks iserting
     * rules specified by {@link canPutCard(ICard card)}.
     * @param card ICard to be inserted into deck.
     * @return true when card was inserted into deck.
     */
    public boolean put(ICard card){
        if(!canPut(card))
           return false;
        return this.cards.add(card);
    }

    /** 
     * Remove top card from deck and return it.
     * @return top card placed in deck.
    */
    public ICard pop(){
        return this.isEmpty() ? null : this.cards.remove(this.size() - 1);
    }

    /**
     * Get reference to top card of deck.
     * @return reference to top card of deck.
     */
    public ICard top(){
        return this.isEmpty() ? null : this.cards.get(this.size() - 1);
    }

    /**
     * Get copy of top card of deck.
     * @return copy of top card of deck.
     */
    public ICard get(){
        return this.top() != null ? this.top().clone() : null;
    }

    /**
     * Get copy of card at given position when this card is accessible.
     * Access to card is specified by {@link canAccessIndex(int index)}. 
     * @param index postion of card to be copied.
     * @return copy of card at given position. Returns null when card is not accessible.
     */
    public ICard get(int index){
        return canAccessIndex(index) ? this.cards.get(index).clone() : null;
    }

    /**
     * Check if deck contains given card.
     * @param card card to be checked.
     * @return true when deck contains given card.
     */
    public boolean contains(ICard card){
        for(ICard c : this.cards)
            if(c.equals(card)) return true;
        return false;
    }

    /**
     * Check if deck contains card with specified valie.
     * @return true when deck contains card with specified value.
    */
    public boolean containsValue(int value){
        for(ICard c : this.cards)
            if(c.value() == value) return true;
        return false;
    }

    /**
     * Check is deck is empty.
     * @return true when deck contains no card.
     */
    public boolean isEmpty(){
        return this.size() == 0;
    }

    /**
     * Get count of card in deck.
     * @return count of cards in deck.
     */
    public int size(){
        return this.cards.size();
    }

    /**
     * Get cpacity of deck.
     * @return maximal possible count of cards in deck.
     */
    public int capacity(){
        return this.maximalSize;
    }

    /**
     * Insert card into deck, which card is inserted does not matter.
     * Do not check iserting rules.
     */
    public void emplace(ICard card){
        this.cards.add(card);
    }

    /**
     * Asisgn user defined name to deck.
     * @param name name of deck to be assigned.
     */
    public void setName(String name){
        this.objectName = name;
    }

    /**
     * Get user defined name of deck.
     * @return user defined name of deck ad string.
     */
    public String getName(){
        return this.objectName;
    }
}