
/**
  * File:       CardDeckModel.java
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
package src.model;

// Dependencies
import src.share.ICard;
import src.share.ICardDeck;

/**
 * Class representing deck/target pack of cards. 
 */
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

    /** {@inheritDoc} */
    @Override
    protected boolean canAccessIndex(int index){
        return index == this.size() - 1;
    }

    /** {@inheritDoc} */
    @Override
    protected boolean canPutCard(ICard card){
        if(card == null)
            return false;
        if(this.isEmpty())
            return card.value() == ICard.ValueConvertor.Ace && card.color() == expectedColor;
        return this.top().value() + 1 == card.value() && this.top().color() == card.color();
    }

    /**
     * Get color of deck, only cards with same color can be inserted.
     * @return color of deck.
     */
    public ICard.Color getExpectedColor(){
        return this.expectedColor;
    }
}