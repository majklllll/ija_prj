
/**
  * File:       ICard.java
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

// Package name
package src.share;

// Required:
import java.io.Serializable;
import java.lang.Comparable;
import java.lang.IllegalArgumentException;
import java.util.Arrays;
import java.util.List;

/**
 * Interface for single card.
 */
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

        /**
         * Convert card color to string representation.
         * @return string representing color.
         */
        public String toString(){
            return this.cardValue;
        }

        /**
         * Check if card color is DIAMON or HEART.
         * @return true when color is HEART or DIAMOND. 
         */
        public boolean isRed(){
            return this == ICard.Color.DIAMONDS || this == ICard.Color.HEARTS; 
        }

         /**
         * Check if card color is SPADES or CLUBS.
         * @return true when color is SPADES or CLUBS. 
         */
        public boolean isBlack(){
            return this == ICard.Color.CLUBS || this == ICard.Color.SPADES;
        }

        /**
         * Check if color is similar (red of black) to given color.
         * @param color color to be compared with.
         * @return true when both colors are red ot both are black.
         */
        public boolean similarColorTo(ICard.Color c){
            return (this.isRed() && c.isRed()) || (this.isBlack() && c.isBlack());
        }

        /**
         * Convert enumeration to hashable list.
         * @return hashable list containing all values of enumeration.
         */
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
    /**
     * Get color of card.
     * @return color of card.
     */
    public ICard.Color color();
     
    /**
     * Get value of card.
     * @return value of card.
     */
    public int value();
     
    /**
     * Convert this card to string.
     * @return string represent. of this card.
     */
    public String toString();
     
    /**
     * Copy this card.
     * @return copy of card.
     */
    public ICard clone();
     
    /**
     * Compare values of cards
     * @param c card withi value to check.
     * @return true if values are similar.
     */
    public int compareValue(ICard c);
     
    /**
     * Have these cards similar color?
     * @param c card with color to check.
     * @return true if colors are similar.
     */
    public boolean similarColorTo(ICard c);
     
    /**
     * Is face of card turned up.
     * @return true if card is turned up.
     */
    public boolean isTurnedFaceUp();
     
    /**
     * Turn face of card up.
     * @return true if action was performed successfuly.
     */
    public boolean turnFaceUp();
    
    /**
     * Turn face of card down.
     * @return true if action was performed successfuly.
     */
    public boolean turnFaceDown();
}