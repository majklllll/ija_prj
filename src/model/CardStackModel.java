
/**
  * File:       CardStackModel.java
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
package src.model;

// Dependecies
import java.lang.IllegalArgumentException;
import java.util.ArrayList;
import src.share.ICard;
import src.share.ICardDeck;
import src.share.ICardStack;

public class CardStackModel extends AbstractCardDeck implements ICardStack{
    
    public CardStackModel(){
        this.maximalSize = AbstractCardDeck.StandardDeckSize;
    }

    public CardStackModel(int maximalSize){
        this.maximalSize = maximalSize;
    }

    @Override
    protected boolean canPutCard(ICard card){
        if(card == null)
            return false;
        if(this.isEmpty())
            return card.value() == ICard.ValueConvertor.King;
        return !this.top().similarColorTo(card) && card.value() == this.top().value() - 1;
    }

    @Override
    protected boolean canAccessIndex(int index){
        return index >= 0 && index < this.size();
    }

    public boolean put(ICardDeck deck){
        if((this.size() + deck.size() > this.capacity()) || !this.canPutCard(deck.get(0)))
            return false;

        for(int index = 0; index < deck.size(); index++)    // Copy cards
            this.put(deck.get(index));
        return true;
    }

    public ICardDeck pop(ICard c){
        return this.takeFrom(c);
    }

    // Take cards above specified card and create new deck from them.
    public ICardDeck takeFrom(ICard card){
        CardStackModel stack = new CardStackModel(this.size());
        int indexOfMatch = this.cards.indexOf(card); 
        while(this.size() > indexOfMatch)
            stack.cards.add(this.cards.remove(indexOfMatch));
        return stack;
    }

    // Fill CardStack with some cards taken from another deck. Used to initilize stack.
    public void fill(ICardDeck deck, int count) throws IllegalArgumentException{
        if(count > deck.size())
            throw new IllegalArgumentException("Fill: Deck does not contain enough cards.");
        if(count > this.capacity() - this.size())
            throw new IllegalArgumentException("Fill: There is not enoght space in stack.");
        for(;count > 0; count--)
            this.cards.add(deck.pop());
    }

    public ICard getByValue(int value){
        for(int index = this.size() - 1; index >= 0; index--)
            if(this.cards.get(index).value() == value)
                return this.cards.get(index).clone();
        return null;
    }

    public ICard getByColor(ICard.Color color){
        for(int index = this.size() - 1; index >= 0; index--)
            if(this.cards.get(index).color() == color)
                return this.cards.get(index).clone();
        return null;
    }
}