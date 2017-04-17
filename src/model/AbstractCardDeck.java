
/**
  * File:       AbstractCardDeck.java
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

// Dependencies
import java.util.ArrayList;
import src.share.ICardDeck;
import src.share.ICard; 

public abstract class AbstractCardDeck extends Object implements ICardDeck{
    protected String objectName              = "";
    protected int maximalSize                = 0;
    protected ArrayList<ICard> cards         = new ArrayList<ICard>();  
    public static final int StandardDeckSize = 52;

    protected abstract boolean canPutCard(ICard card);
    protected abstract boolean canAccessIndex(int index);

    public boolean canPut(ICard card){
        return this.canPutCard(card) && this.size() < this.maximalSize;
    }
    
    public boolean put(ICard card){
        if(!canPut(card))
           return false;
        return this.cards.add(card);
    }

    public ICard pop(){
        return this.isEmpty() ? null : this.cards.remove(this.size() - 1);
    }

    public ICard top(){
        return this.isEmpty() ? null : this.cards.get(this.size() - 1);
    }

    public ICard get(){
        return this.top() != null ? this.top().clone() : null;
    }

    public ICard get(int index){
        return canAccessIndex(index) ? this.cards.get(index).clone() : null;
    }

    public boolean contains(ICard card){
        for(ICard c : this.cards)
            if(c.equals(card)) return true;
        return false;
    }

    public boolean containsValue(int value){
        for(ICard c : this.cards)
            if(c.value() == value) return true;
        return false;
    }

    public boolean isEmpty(){
        return this.size() == 0;
    }

    public int size(){
        return this.cards.size();
    }

    public int capacity(){
        return this.maximalSize;
    }

    public void emplace(ICard card){
        this.cards.add(card);
    }

    public void setName(String name){
        this.objectName = name;
    }

    public String getName(){
        return this.objectName;
    }
}