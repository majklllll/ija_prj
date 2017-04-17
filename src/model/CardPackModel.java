
/**
  * File:       CardPackModel.java
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
import java.util.Collections;
import java.util.Random;

public class CardPackModel extends AbstractCardDeck{
    private Random generator = new Random();

    public CardPackModel(){
        this.maximalSize = AbstractCardDeck.StandardDeckSize;
    }
    
    public CardPackModel(int maximalSize){
        this.maximalSize = maximalSize;
    }

    @Override
    protected boolean canAccessIndex(int index){
        return index >= 0 && index < this.size();
    }

    @Override
    protected boolean canPutCard(ICard card){
        return !this.contains(card);
    }

    /**
     *  Create deck of size 52 and fill it with cards. 
     */
    public static CardPackModel createStandardPack(){
        CardPackModel packModel = new CardPackModel();
        for(ICard.Color c : ICard.Color.values())
            for(int val = ICard.ValueConvertor.MinimalIntValue; val <= ICard.ValueConvertor.MaximalIntValue; val++)
                packModel.put(new CardModel(c, val));
        return packModel;
    }

    /**
     * Mix cards in pack. Parameters specify count of swaps of any card in collection.
     */
    public void mix(int swaps){
        for(int swap = 0; swap < swaps; swap++){
            int cardNumber1 = this.generator.nextInt(this.size() - 1);
            int cardNumber2 = this.generator.nextInt(this.size() - 1);
            Collections.swap(this.cards, cardNumber1, cardNumber2);
        }
    } 
}