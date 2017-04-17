
/**
  * File:       ICard.java
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

// Package name
package src.share;

// Required:
import java.io.Serializable;
import java.lang.Comparable;
import java.lang.IllegalArgumentException;
import java.util.Arrays;
import java.util.List;

public interface ICard extends Serializable{

    /**
     * Enumeration representing color of card.
     */
    public static enum Color implements Serializable, Comparable<ICard.Color>{
        CLUBS("C"),
        DIAMONDS("D"),
        HEARTS("H"),
        SPADES("S");

        private final String cardValue;
        private static final String[] hashableColorList = {"None", "C", "D", "H", "S"};

        private Color(String value){
            this.cardValue = value;
        }

        public String toString(){
            return this.cardValue;
        }

        public boolean isRed(){
            return this == ICard.Color.DIAMONDS || this == ICard.Color.HEARTS; 
        }

        public boolean isBlack(){
            return this == ICard.Color.CLUBS || this == ICard.Color.SPADES;
        }

        public boolean similarColorTo(ICard.Color c){
            return (this.isRed() && c.isRed()) || (this.isBlack() && c.isBlack());
        }

        public static String[] toHashList(){
            return hashableColorList;
        }
    }   

    /**
     * Simple integer/string and string/integer value convertor.
     */
    public static final class ValueConvertor{
        private ValueConvertor(){}
        private static final String[] values = {"None", "A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        public static final int MinimalIntValue = 1;
        public static final int MaximalIntValue = 13;
        public static final int Ace = 1;
        public static final int Jang = 11;
        public static final int Queen = 12;
        public static final int King = 13;
       
        public static int toInt(String value){
            int result = Arrays.asList(values).indexOf(value);
            if(result == -1)
                throw new IllegalArgumentException("No matching card value to " + value + ".");
            return result;
        }

        public static String toString(int value){
            if(value > ICard.ValueConvertor.MaximalIntValue || value < ICard.ValueConvertor.MinimalIntValue)
                throw new IllegalArgumentException("Invalid value of card " + value + ".");
            return values[value];
        }
    }

    // ICard interface itself.  
    public ICard.Color color();

    public int value();

    public String toString();

    public ICard clone();

    public int compareValue(ICard c);

    public boolean similarColorTo(ICard c);

    public boolean isTurnedFaceUp();

    public boolean turnFaceUp();

    public boolean turnFaceDown();
}