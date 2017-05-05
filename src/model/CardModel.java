
/**
  * File:       CardModel.java
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

// Required:
import src.share.ICard;
import java.lang.Object;
import java.util.Arrays;
import java.util.List;

/**
 * Class representing single card.
 */
public class CardModel extends Object implements ICard{
    
    // Properties
    protected ICard.Color color;
    protected int value;
    protected boolean isTurnedUp;   

    public CardModel(ICard.Color color, int value){
        this.color = color;
        this.value = value;
        this.isTurnedUp = false;
    }

    protected CardModel(ICard.Color color, int value, boolean isTurnedUp){
        this.color = color;
        this.value = value;
        this.isTurnedUp = isTurnedUp;
    }

    public CardModel(ICard.Color color, String value){
        this.color = color;
        this.value = ICard.ValueConvertor.toInt(value);
        this.isTurnedUp = false;
    }

    // Compare 2 objects, cards has to be of same color and value to mark them as a equals.
    @Override
    public boolean equals(Object other){
        if(other == null || !(other instanceof CardModel)) 
            return false;
        CardModel anotherCard = (CardModel)other;
        return anotherCard.color.equals(this.color) && anotherCard.value() == this.value;
    }

    // Override hashcode because of operator == and remove method of set.
    // We want to compare cards by theirs values, not by OID.
    @Override
    public int hashCode(){
        return Arrays.asList(ICard.Color.toHashList()).indexOf(this.color.toString()) * this.value;
    }

    /**
     * Create copy of card.
     * @return copy of card.
     */
    public ICard clone(){
        return new CardModel(this.color, this.value, this.isTurnedUp);
    }

    /**
     * Get color of card.
     * @return color of card.
     */
    public ICard.Color color(){
        return this.color;
    }

    /**
     * Get value of card.
     * @return value of card.
     */
    public int value(){
        return this.value;
    }

    /**
     * Conveert card into simple string form in order of printing.
     * @return string representation of card.
     */
    public String toString(){
        return ICard.ValueConvertor.toString(this.value) + "(" + this.color.toString() + ")";
    }

    /**
     * Check whenever has card similar (red or black) color to given card.
     * @param card card ot be compared with.
     * @return true when both cards are red or both are black.
     */
    public boolean similarColorTo(ICard card){
        return card == null ? false : this.color.similarColorTo(card.color());
    }

    /**
     * Get difference betweet values of cards.
     * @return difference between value of given card and current card.
     */
    public int compareValue(ICard card){
        return card == null ? Integer.MAX_VALUE : this.value - card.value();
    }

    /**
     * Check whenever is card turned by face up.
     * @return true when card is turned face up.
     */
    public boolean isTurnedFaceUp(){
        return this.isTurnedUp;
    }

    /**
     * Turns card face up.
     * @return true if card was turned.
     */
    public boolean turnFaceUp(){
        if(this.isTurnedUp) return false;
        return this.isTurnedUp = true;
    }

    /**
     * Turns card face down.
     * @return true if card was turned.
     */
    public boolean turnFaceDown(){
        if(!this.isTurnedUp) return false;
        this.isTurnedUp = false;
        return true;
    } 
}