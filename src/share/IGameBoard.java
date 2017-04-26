
/**
  * File:       IGameBoard.java
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

// Dependencies
import java.util.ArrayList;

public interface IGameBoard{

    public void registerObserver(ISupportRepaint observer);

    public ICardRepository getRepository();

    public ICardDeck getDeck(int index);

    public ICardStack getStack(int index);

    public int deckCount();

    public int stackCount();

    public void update();

    public boolean hintAvailable();

    public ArrayList<ICardHint> getHints();

    public void createHints();

    public boolean save(String fileName);

    public boolean load(String fileName);

    public boolean isGameOver();
}