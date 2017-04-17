
/**
  * File:       ICardDeck.java
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
package src.share;

// Dependencies
import java.io.Serializable;

public interface ICardDeck extends Serializable{

    public boolean put(ICard card);

    public ICard pop();

    public ICard top();

    public ICard get();

    public ICard get(int index);

    public boolean contains(ICard card);

    public boolean containsValue(int value);

    public boolean isEmpty();

    public int size();

    public void emplace(ICard card);

    public boolean canPut(ICard card);

    public void setName(String name);

    public String getName();
}