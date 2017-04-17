
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

public interface ICardFactory{    
    public ICard createCard(ICard.Color color, int value);

    public ICardDeck createCardPack();

    public ICardDeck createTargetPack(ICard.Color color);

    public ICardRepository createSourcePack();

    public ICardRepository createSourcePack(int insertCards);

    public ICardStack createWorkingPack();

    public ICardStack createWorkingPack(int insertCards);

    public int availableCards();
}