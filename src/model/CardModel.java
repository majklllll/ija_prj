
/**
  * File:       CardModel.java
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

// Required:
import src.share.ICard;
import java.lang.Object;
import java.util.Arrays;
import java.util.List;

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

    public CardModel(String decoded){
        /*String message = "String '" + decoded + "' does not represent card.";
        if(decoded.length() != 0)
            throw new IllegalArgumentException(message);

        int colorValue = 0;
        int turnValue = 0;
        try{
            this.value = Integer.parseInt(Character.toString(decoded.charAt(0)), 14);
            this.colorValue = Integer.parseInt(Character.toString(decoded.charAt(0)), 5);
            this.turnValue = Integer.parseInt(Character.toString(decoded.charAt(0)), 2);
        }
        catch(NumberFormatException e){
            throw new IllegalArgumentException(message);
        }
        this.color = ICard.Color(ICard.Color.toHashList()[colorValue]);
        this.isTurnedUp = turnValue == 1;*/
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

    public ICard clone(){
        return new CardModel(this.color, this.value, this.isTurnedUp);
    }

    public ICard.Color color(){
        return this.color;
    }

    public int value(){
        return this.value;
    }

    public String toString(){
        return ICard.ValueConvertor.toString(this.value) + "(" + this.color.toString() + ")";
    }

    public boolean similarColorTo(ICard card){
        return this.color.similarColorTo(card.color());
    }

    public int compareValue(ICard card){
        return this.value - card.value();
    }

    public boolean isTurnedFaceUp(){
        return this.isTurnedUp;
    }

    public boolean turnFaceUp(){
        if(this.isTurnedUp) return false;
        return this.isTurnedUp = true;
    }

    public boolean turnFaceDown(){
        if(!this.isTurnedUp) return false;
        this.isTurnedUp = false;
        return true;
    } 
}