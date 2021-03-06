
/**
  * File:       ISupportFill.java
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
package src.share;

/**
 * Interface indicating support for filling deck with cards.
 */
public interface ISupportFill{
        
    /**
     * Fill deck with cards from another deck.
     * @param deck deck from which should be cards taken.
     * @param count count of inserted cards.
     */
    public void fill(ICardDeck deck, int count);
}