
/**
  * File:       ICardFactory.java
  * Author:     Jan Hrstka
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
 * Interface for card factory.
 */
public interface ICardFactory{  
    
    /**
     * Creates card of specific color and value.
     * @param color color of this card
     * @param value value of card
     * @return created card.
     */  
    public ICard createCard(ICard.Color color, int value);
     
    /**
     * Creates card pack.
     * @return deck of cards.
     */
    public ICardDeck createCardPack();
     
    /**
     * Creates pack for specific color.
     * @param color color of deck
     * @return deck for cards.
     */
    public ICardDeck createTargetPack(ICard.Color color);
     
    /**
     * Creates repository of cards.
     * @return repository of cards.
     */
    public ICardRepository createSourcePack();
    
    /**
     * Creates repository of cards.
     * @param insertCards count of cards
     * @return repository of cards.
     */
    public ICardRepository createSourcePack(int insertCards);
    
    /**
     * Creates full working pack of cards.
     * @return stack of cards.
     */
    public ICardStack createWorkingPack();
    
    /**
     * Creates working pack of cards.
     * @param insertCards count of cards
     * @return stack of cards.
     */
    public ICardStack createWorkingPack(int insertCards);
    
    /**
     * Get count of available cards.
     * @return count of cards.
     */
    public int availableCards();
}