
/**
  * File:       ICardRepository.java
  * @author     Jan Hrstka
  * Login:      xhrstk02
  * University: BUT (Brno University of Technology)
  * Faculty:    FIT (Faculty of Information Technology)
  * Course:     IJA (Java Programming Language)
  * Project:    Solitaire Klondike Game
  * Proj. Num:  4
  * Version:    1
  * Date:       12.04.2017
  * System:     GNU/Linux, x86_64, Ubuntu 16.04 LTS
  */
package src.share;

/**
 * Interface for card repository.
 */
public interface ICardRepository extends ICardDeck, ISupportFill{
     
    /**
     * Show next card in repository.
     * @return if successful.
     */
    public boolean showNext();
    
    /**
     * Action hiding top card.
     * @return if successful.
     */
    public boolean hideTopCard();
     
    /**
     * Action turning this repository over.
     * @return if successful.
     */
    public boolean turnOver();
     
    /**
     * Get count of hidden cards.
     * @return count of hidden cards.
     */
    public int sizeHidden();
     
    /**
     * Is any card hidden?
     * @return if is true.
     */
    public boolean isAnyHidden();
}