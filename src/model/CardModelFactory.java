
/**
  * File:       CardModelFactory.java
  * @author     Jan Hrstka
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

/**
 * Class for creating cards, decks and stacks.
 */
public class CardModelFactory implements ICardFactory{    
    private CardPackModel pack = CardPackModel.createStandardPack();
    private static final int DefaultMixing = 200;

    public CardModelFactory(){
        pack.mix(CardModelFactory.DefaultMixing);
    }

    public CardModelFactory(int mixing){
        pack.mix(mixing);
    }

    /**
     * Create card with specified color nad value.
     * @return crated card.
     */
    public ICard createCard(ICard.Color color, int value){
        return new CardModel(color, value);
    }

    /**
     * Create standard card pack.
     * @return standard card pack.
     */
    public ICardDeck createCardPack(){
        return CardPackModel.createStandardPack();
    }

    /**
     * Create target deck with given color.
     * @param color color of taget deck.
     * @return target deck with given color.
     */
    public ICardDeck createTargetPack(ICard.Color color){
        return new CardDeckModel(color);
    }

    /**
     * Create card repository.
     * @return card repository.
     */
    public ICardRepository createSourcePack(){
        return new CardRepositoryModel();
    }

    /**
     * Create source pack - card repository.
     * @return card repository.
     */
    public ICardRepository createSourcePack(int insertCards){
        CardRepositoryModel repository = new CardRepositoryModel();
        repository.fill(this.pack, insertCards);
        return repository;
    }

    /**
     * Create working pack.
     * @return empty working pack.
     */
    public ICardStack createWorkingPack(){
        return new CardStackModel();
    }

    /**
     * Create sworking pack filled with cards.
     * @param insertCards count of cards to be inserted into pack.
     * @return working pack filled with cards.
     */
    public ICardStack createWorkingPack(int insertCards){
        ICardStack stack = new CardStackModel();
        stack.fill(this.pack, insertCards);
        return stack;
    }

    /**
     * Get count of card available in factory.
     * @return count of card remaining in factory.
     */
    public int availableCards(){
        return this.pack.size();
    }
}