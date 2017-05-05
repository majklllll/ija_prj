
/**
  * File:       CardStackModel.java
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

// Dependecies
import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import src.share.ICard;
import src.share.ICardDeck;
import src.share.ICardStack;

/**
 * Class representing stack/working pack of cards. 
 */
public class CardStackModel extends AbstractCardDeck implements ICardStack{
    
    public CardStackModel(){
        this.maximalSize = AbstractCardDeck.StandardDeckSize;
    }

    public CardStackModel(int maximalSize){
        this.maximalSize = maximalSize;
    }

    /** {@inheritDoc} */
    @Override
    protected boolean canPutCard(ICard card){
        if(card == null)
            return false;
        if(this.isEmpty())
            return card.value() == ICard.ValueConvertor.King;
        return !this.top().similarColorTo(card) && card.value() == this.top().value() - 1;
    }

    /** {@inheritDoc} */
    @Override
    protected boolean canAccessIndex(int index){
        return index >= 0 && index < this.size();
    }
 
    /**
     * Isert whole deck at the top of this deck.
     * @param deck dekc to be inserted.
     * @return true when deck was inserted.
     */
    public boolean put(ICardDeck deck){
        if((this.size() + deck.size() > this.capacity()) || !this.canPutCard(deck.get(0)))
            return false;

        for(int index = 0; index < deck.size(); index++)    // Copy cards
            this.put(deck.get(index));
        return true;
    }

    /**
     * Remove multiple cards from deck srting with given card.
     * @param card from which should be deck taken.
     * @return deck of card begining with specified card.
    */
    public ICardDeck pop(ICard c){
        return this.takeFrom(c);
    }

    /**
     * Take cards above specified card and create new deck from them.
     * @param card from which should be deck taken.
     * @return deck of card begining with specified card.
     */
    public ICardDeck takeFrom(ICard card){
        CardStackModel stack = new CardStackModel(this.size());
        int indexOfMatch = this.cards.indexOf(card); 
        while(this.size() > indexOfMatch)
            stack.cards.add(this.cards.remove(indexOfMatch));
        return stack;
    }

    /**
     * Fill CardStack with some cards taken from another deck. Used to initilize stack.
     * @param deck dekc from which should be cards taken.
     * @param count count of inserted cards.
     */
    public void fill(ICardDeck deck, int count) throws IllegalArgumentException{
        if(count > deck.size())
            throw new IllegalArgumentException("Fill: Deck does not contain enough cards.");
        if(count > this.capacity() - this.size())
            throw new IllegalArgumentException("Fill: There is not enoght space in stack.");
        for(;count > 0; count--)
            this.cards.add(deck.pop());
    }

    /**
     * Get card with specified value.
     * @param value value of card to be matched.
     * @return copy of card from deck with given value.
     */
    public ICard getByValue(int value){
        for(int index = this.size() - 1; index >= 0; index--)
            if(this.cards.get(index).value() == value)
                return this.cards.get(index).clone();
        return null;
    }

    /**
     * Get card with specified color.
     * @param color color of card to be matched.
     * @return copy of card from deck with given color.
     */
    public ICard getByColor(ICard.Color color){
        for(int index = this.size() - 1; index >= 0; index--)
            if(this.cards.get(index).color() == color)
                return this.cards.get(index).clone();
        return null;
    }
}