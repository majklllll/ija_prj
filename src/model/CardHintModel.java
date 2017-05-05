
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
package src.model;

// Dependencies
import java.util.ArrayList;
import src.share.ICardHint;
import src.share.ICardDeck;
import src.share.ICardStack;
import src.share.ICard;

/**
 * Class representing hint bind to card.
 */
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

    /**
     * Get reference to card for which is hint created.
     * @return card for which is hint created.
     */
    public ICard getCard(){
        return this.card;
    }
    
    /**
     * Get list of decks, where can ba card placed.
     * @return arraylist of deck, where can be card placed.
     */
    public ArrayList<ICardDeck> getCardDecks(){
        return this.decks;
    }

    /**
     * Get list of stacks, where can ba card placed.
     * @return arraylist of stacks, where can be card placed.
     */
    public ArrayList<ICardStack> getCardStacks(){
        return this.stacks;
    }

    /**
     * Get deck from which is card taken.
     * @return deck from which is card taken,
     */
    public ICardDeck getSourceDeck(){
        return this.sourceDeck;
    }

    /**
     * Check whenever is hint empty - no possible mover were found.
     * @return true when deck is empty.
     */
    public boolean isEmpty(){
        return !this.hasCardDeck() && !this.hasCardStack(); 
    }

    /**
     * Check if card can be placed at leats into one stack.
     * @return true when some suitable stakcs were found.
     */
    public boolean hasCardStack(){
        return !this.stacks.isEmpty();
    }

    /**
     * Check if card can be placed at leats into one deck.
     * @return true when some suitable decks were found.
     */
    public boolean hasCardDeck(){
        return !this.decks.isEmpty();
    }

    /**
     * Check if source deck is specified.
     * @return true when source deck is specified.
     */
    public boolean hasSourceDeck(){
        return this.sourceDeck != null;
    }

    /**
     * Convert card hint into simple string form in order of printing.
     * @return string representation of card hint.
     */
    public String toString(){
        ArrayList<String> targets = new ArrayList<String>();
        for(ICardDeck deck : this.decks)
            targets.add(deck.getName());
        for(ICardStack stack : this.stacks)
            targets.add(stack.getName());
        return this.card.toString() + " -> " + String.join(" | ", targets);
    }

    /**
     * Add next card stack as a target where can be card placed.
     * @param stack is target where can be card placed.
     */
    public boolean add(ICardStack stack){
        return this.stacks.add(stack);
    }

    /**
     * Add next card deck as a target where can be card placed.
     * @param deck is target where can be card placed.
     */
    public boolean add(ICardDeck deck){
        return this.decks.add(deck);
    }
}