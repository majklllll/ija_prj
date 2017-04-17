
/**
  * File:       ICardHint.java
  * Author:     Jan Hrstka
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
package src.model;

// Dependencies
import java.util.ArrayList;
import src.share.ICardHint;
import src.share.ICardDeck;
import src.share.ICardStack;
import src.share.ICard;

public class CardHintModel implements ICardHint{
    private ArrayList<ICardDeck>  decks  = new ArrayList<ICardDeck>();
    private ArrayList<ICardStack> stacks = new ArrayList<ICardStack>();
    private ICard card = null;
    private ICardDeck sourceDeck = null;

    public CardHintModel(ICard card){
        this.card = card;
    }

    public CardHintModel(ICard card, ICardDeck sourceDeck){
        this.card = card;
        this.sourceDeck = sourceDeck;
    }

    public ICard getCard(){
        return this.card;
    }
    
    public ArrayList<ICardDeck> getCardDecks(){
        return this.decks;
    }

    public ArrayList<ICardStack> getCardStacks(){
        return this.stacks;
    }

    public ICardDeck getSourceDeck(){
        return this.sourceDeck;
    }

    public boolean isEmpty(){
        return !this.hasCardDeck() && !this.hasCardStack(); 
    }

    public boolean hasCardStack(){
        return !this.stacks.isEmpty();
    }

    public boolean hasCardDeck(){
        return !this.decks.isEmpty();
    }

    public boolean hasSourceDeck(){
        return this.sourceDeck != null;
    }

    public String toString(){
        ArrayList<String> targets = new ArrayList<String>();
        for(ICardDeck deck : this.decks)
            targets.add(deck.getName());
        for(ICardStack stack : this.stacks)
            targets.add(stack.getName());
        return this.card.toString() + " -> " + String.join(" | ", targets);
    }

    public boolean add(ICardStack stack){
        return this.stacks.add(stack);
    }

    public boolean add(ICardDeck deck){
        return this.decks.add(deck);
    }
}