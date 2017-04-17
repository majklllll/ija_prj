
/**
  * File:       CardRepositoryModel.java
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
import src.share.ICard;
import src.share.ICardDeck;
import src.share.ICardRepository;
import java.util.ArrayList;
import java.util.Random;

public class CardRepositoryModel extends AbstractCardDeck implements ICardRepository{
    protected ArrayList<ICard> hiddenCards = new ArrayList<ICard>();
    private Random generator = new Random();

    public CardRepositoryModel(){
        this.maximalSize = AbstractCardDeck.StandardDeckSize;
    }
    
    public CardRepositoryModel(int maximalSize){
        this.maximalSize = maximalSize;
    }

    @Override
    protected boolean canAccessIndex(int index){
        return index >= 0 && index < this.size();   
    }

    @Override
    protected boolean canPutCard(ICard card){
        return false;
    }

    public void fill(ICardDeck deck, int count) throws IllegalArgumentException{
        if(deck.size() < count)
            throw new IllegalArgumentException("Fill: There is not enought cards in deck.");
        if(deck.size() + this.size() + this.sizeHidden() > this.capacity())
            throw new IllegalArgumentException("Fill: There is not enought space in repository.");
        for(;count > 0; count--)
            this.hiddenCards.add(deck.pop());
    }

    public boolean showNext(){
        if(this.sizeHidden() <= 0) 
            return false;
        ICard card = this.hiddenCards.remove(this.sizeHidden() - 1);
        card.turnFaceUp();
        return this.cards.add(card); // Turn up top hidden card.
    }

    public boolean hideTopCard(){
        if(this.size() <= 0)
            return false;
        ICard card = this.cards.remove(this.size() - 1);
        card.turnFaceDown();
        return this.hiddenCards.add(card);
    }

    public boolean turnOver(){
        if(isAnyHidden())
            return false;
        for(int index = this.size() - 1; index >= 0; index--){
            int cardNumber = index > 0 ? this.generator.nextInt(index) : 0; 
            this.hiddenCards.add(this.cards.remove(cardNumber));    // Turn over cards and mix them.
            this.hiddenCards.get(this.sizeHidden() - 1).turnFaceDown();
        }
        return true;
    } 

    public int sizeHidden(){
        return this.hiddenCards.size();
    }

    public boolean isAnyHidden(){
        return sizeHidden() > 0;
    }
}