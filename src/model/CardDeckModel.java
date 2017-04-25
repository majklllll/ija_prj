
/**
  * File:       CardDeckModel.java
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
package src.model;

// Dependencies
import src.share.ICard;
import src.share.ICardDeck;

public class CardDeckModel extends AbstractCardDeck{
    private ICard.Color expectedColor;

    public CardDeckModel(ICard.Color color){
        this.expectedColor = color;
        this.maximalSize = AbstractCardDeck.StandardDeckSize;
    }
    
    public CardDeckModel(ICard.Color color, int maximalSize){
        this.expectedColor = color;
        this.maximalSize = maximalSize;
    }

    @Override
    protected boolean canAccessIndex(int index){
        return index == this.size() - 1;
    }

    @Override
    protected boolean canPutCard(ICard card){
        if(this.isEmpty())
            return card.value() == ICard.ValueConvertor.Ace && card.color() == expectedColor;
        return this.top().value() + 1 == card.value() && this.top().color() == card.color();
    }

    public ICard.Color getExpectedColor(){
        return this.expectedColor;
    }
}