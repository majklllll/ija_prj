
/**
  * File:       ICardStack.java
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

public interface ICardStack extends ICardDeck, ISupportFill{

    public boolean put(ICardDeck deck);

    public ICardDeck pop(ICard card);

    public ICard getByValue(int value);

    public ICard getByColor(ICard.Color color);
}