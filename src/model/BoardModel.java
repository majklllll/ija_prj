
/**
  * File:       BoardModel.java
  * @author     Jan Hrstka
  * Login:      xhrstk02
  * University: BUT (Brno University of Technology)
  * Faculty:    FIT (Faculty of Information Technology)
  * Course:     IJA (Java Programming Language)
  * Project:    Solitaire Klondike Game
  * Proj. Num:  4
  * Version:    1
  * Date:       26.04.2017
  * System:     GNU/Linux, x86_64, Ubuntu 16.04 LTS
  */
package src.model;

// Dependecies
import src.share.ICard;
import src.share.ICardDeck;
import src.share.ICardFactory;
import src.share.ICardHint;
import src.share.ICardRepository;
import src.share.ICardStack;
import src.share.ISupportRepaint;
import src.share.IGameBoard;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Base class representing playing board.
 * Contaings decks and stack with cards.
 */
public class BoardModel implements IGameBoard{
    private boolean gameOver                          = false;
    private ICardFactory factory                      = new CardModelFactory();
    protected ArrayList<ISupportRepaint> observers    = new ArrayList<ISupportRepaint>();
    protected ArrayList<ICardStack> stacks            = new ArrayList<ICardStack>();
    protected ArrayList<ICardDeck> decks              = new ArrayList<ICardDeck>();
    protected ArrayList<ICardHint> cardHints          = null;
    protected ICardRepository repository              = null;
    public static final int defaultStackCount         = 7;
    public static final int defaultDeckCount          = 4;
    public static final String saveFileExtension      = ".board";

    public BoardModel(){
        for(int stackNumber = 0; stackNumber < BoardModel.defaultStackCount; stackNumber++){
            this.stacks.add(factory.createWorkingPack(stackNumber + 1));
            this.stacks.get(stackNumber).top().turnFaceUp();
            this.stacks.get(stackNumber).setName("s" + (stackNumber + 1));
        }
        this.decks.add(factory.createTargetPack(ICard.Color.HEARTS));
        this.decks.add(factory.createTargetPack(ICard.Color.SPADES));
        this.decks.add(factory.createTargetPack(ICard.Color.CLUBS));
        this.decks.add(factory.createTargetPack(ICard.Color.DIAMONDS));
        this.repository = factory.createSourcePack(factory.availableCards());
        repository.setName("r1");
        for(int index = 1; index <= defaultDeckCount; index++)
            this.decks.get(index - 1).setName("d" + index);
    }

    /**
     * Register new observer.
     * @param observer observer to be notified about changes.
     */
    public void registerObserver(ISupportRepaint observer){
        observers.add(observer);
    }

    /**
     * Notify all observers about changes.
     */
    public void update(){
        this.gameOver = this.checkGameOver();
        for(ISupportRepaint observer : this.observers)
            observer.repaint();
    }

    /**
     * Get card repository.
     * @return card repository.
     */
    public ICardRepository getRepository(){
        return this.repository;
    }

    /**
     * Get deck at given position.
     * @param index position of deck to be retrieved.
     * @return deck at given position.
     */
    public ICardDeck getDeck(int index){
        if(index < 0 || index > this.deckCount())
            return null;
        return this.decks.get(index);
    }

    /**
     * Get stack at given position.
     * @param index position of stack to be retrieved.
     * @return stack at given position.
     */
    public ICardStack getStack(int index){
        if(index < 0 || index > this.stackCount())
            return null;
        return this.stacks.get(index);
    }

    /**
     * Get count of decks in board.
     * @return count of decks in board.
     */
    public int deckCount(){
        return this.decks.size();
    }

    /**
     * Get count of stacks in board.
     * @return count of stacks in board.
     */
    public int stackCount(){
        return this.stacks.size();
    }

    /**
     * Check whenever is any nint availavle.
     * @return true when there is at least one hint.
     */
    public boolean hintAvailable(){
        return this.cardHints == null ? false : this.cardHints.size() > 0;
    }

    /**
     * Get hints for all cards.
     * @return list of hints.
     */
    public ArrayList<ICardHint> getHints(){
        ArrayList<ICardHint> hints = this.cardHints;
        this.cardHints = null;
        return hints;
    }

    /**
     * Create hints for all cards.
     */
    public void createHints(){
        this.cardHints = new ArrayList<ICardHint>();
        
        // Get hints for repository.
        ICardHint hint = this.hintForCard(this.repository.top(), this.repository, true);
        if(hint != null && !hint.isEmpty())
            this.cardHints.add(hint);

        // Get hint for each deck.
        for(ICardDeck deck : this.decks)
            if(!deck.isEmpty()){
                hint = this.hintForCard(deck.top(), deck, true);
                if(!hint.isEmpty())
                    this.cardHints.add(hint);
            }

        // Get hints for each visible card of each stack.
        for(ICardStack stack : this.stacks)
            for(int index = stack.size() - 1; index >= 0; index--){
                ICard card = stack.get(index);
                if(card == null || !card.isTurnedFaceUp())
                    break;
                hint = this.hintForCard(card, stack, card.equals(stack.top()));
                if(!hint.isEmpty())
                    this.cardHints.add(hint);
            }
    }

    /**
     * Create card hint for give card.
     * @param card card for which should be hint constructed.
     * @return hint for given card.
     */
    public ICardHint hintForCard(ICard card){
        return this.hintForCard(card, null, true);
    }

    /**
     * Create card hint for give card.
     * @param card card for which should be hint constructed.
     * @param sourceDeck is deck in which is card placed.
     * @param includeDecks specify whenever include decks.
     * @return hint for given card.
     */
    private ICardHint hintForCard(ICard card, ICardDeck sourceDeck, boolean includeDecks){
        if(card == null)
            return null;
        
        ICardHint hint = new CardHintModel(card.clone(), sourceDeck);
        if(includeDecks)
            for(ICardDeck deck : this.decks)
                if(deck.canPut(card))
                    hint.add(deck);

        for(ICardStack stack : this.stacks)
            if(stack.canPut(card))
                hint.add(stack);
        return hint;
    }

    /**
     * Save state of board into given file.
     * @param fileName name of file into which shoud be board saved.
     * @return true when board was saved successfuly.
     */
    public boolean save(String fileName){
        FileOutputStream fos    = null;
        ObjectOutputStream oos  = null;
        try{
            fos = new FileOutputStream(fileName);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(this.decks);
            oos.writeObject(this.stacks);
            oos.writeObject(this.repository);
            fos.close();
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try{
                if(oos != null)
                    oos.close();
            } catch(IOException e) { /* Ignore invalid close operation*/ }
        }
        return true;
    }

    /**
     * Load state of board from given file.
     * @param fileName name of file from which shoud be board loaded.
     * @return true when board was loaded successfuly.
     */
    public boolean load(String fileName){
        ObjectInputStream ois        = null;
        FileInputStream fis          = null;
        ArrayList<ICardDeck> decks   = null;
        ArrayList<ICardStack> stacks = null;
        ICardRepository repository   = null;
        try{
            fis = new FileInputStream(fileName);
            ois = new ObjectInputStream(fis);
            decks  = (ArrayList<ICardDeck>)  ois.readObject();
            stacks = (ArrayList<ICardStack>) ois.readObject();
            repository = (ICardRepository)   ois.readObject();
            fis.close();
        } catch(Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try{
                if(ois != null)
                    ois.close();
            } catch(IOException e) { /* Ignore invalid close operation*/ }
        }

        // Overwrite existing data with new one if they are complete.
        if(decks != null && stacks != null && repository != null){
            this.decks = decks;
            this.stacks = stacks;
            this.repository = repository;
            return true;
        }
        return false;
    }

    /**
     * Check if game has ended.
     * @return true when game is over.
     */
    public boolean checkGameOver(){
        for(ICardDeck deck : this.decks)
            if(deck.isEmpty() || deck.top().value() != ICard.ValueConvertor.King)
                return false;
        return true;
    }

    /**
     * Check if game has ended.
     * @return true when game is over.
     */
    public boolean isGameOver(){
        return this.gameOver;
    }
}