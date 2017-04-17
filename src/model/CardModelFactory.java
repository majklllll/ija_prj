
/**
  * File:       CardModelFactory.java
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
package src.model;

// Dependencies
import src.share.ICardFactory;
import src.share.ICardStack;
import src.share.ICardDeck;
import src.share.ICardRepository;
import src.share.ICard;

public class CardModelFactory implements ICardFactory{    
    private CardPackModel pack = CardPackModel.createStandardPack();
    private static final int DefaultMixing = 200;

    public CardModelFactory(){
        pack.mix(CardModelFactory.DefaultMixing);
    }

    public CardModelFactory(int mixing){
        pack.mix(mixing);
    }

    public ICard createCard(ICard.Color color, int value){
        return new CardModel(color, value);
    }

    public ICardDeck createCardPack(){
        return CardPackModel.createStandardPack();
    }

    public ICardDeck createTargetPack(ICard.Color color){
        return new CardDeckModel(color);
    }

    public ICardRepository createSourcePack(){
        return new CardRepositoryModel();
    }

    public ICardRepository createSourcePack(int insertCards){
        CardRepositoryModel repository = new CardRepositoryModel();
        repository.fill(this.pack, insertCards);
        return repository;
    }

    public ICardStack createWorkingPack(){
        return new CardStackModel();
    }

    public ICardStack createWorkingPack(int insertCards){
        ICardStack stack = new CardStackModel();
        stack.fill(this.pack, insertCards);
        return stack;
    }

    public int availableCards(){
        return this.pack.size();
    }
}