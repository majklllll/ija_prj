
/**
  * File:       ICardHint.java
  * Author:     Jan Hrstka
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

// Dependecies
import java.util.ArrayList;

public interface ICardHint{

    public ICard getCard();
    
    public ArrayList<ICardDeck> getCardDecks();

    public ArrayList<ICardStack> getCardStacks();

    public ICardDeck getSourceDeck();

    public boolean isEmpty();

    public boolean hasCardStack();

    public boolean hasCardDeck();

    public boolean hasSourceDeck();

    public String toString();

    public boolean add(ICardStack stack);

    public boolean add(ICardDeck deck);
}