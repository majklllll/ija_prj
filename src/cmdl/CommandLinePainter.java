
/**
  * File:       CommandLinePainter.java
  * Author:     Jan Hrstka
  * Login:      xhrstk02
  * University: BUT (Brno University of Technology)
  * Faculty:    FIT (Faculty of Information Technology)
  * Course:     IJA (Java Programming Language)
  * Project:    Solitaire Klondike Game
  * Proj. Num:  4
  * Version:    1.4
  * Date:       15.04.2017
  * System:     GNU/Linux, x86_64, Ubuntu 16.04 LTS
  */
package src.cmdl;

// Dependencies
import src.share.ICardRepository;
import src.share.IGameBoard;
import src.share.ICardStack;
import src.share.ICardDeck;
import src.share.ICardHint;
import src.model.CardDeckModel;
import src.model.CardModel;
import src.share.ICard;
import src.share.ISupportRepaint;
import java.util.ArrayList;

public class CommandLinePainter implements ISupportRepaint{
    private IGameBoard model = null;
    private ICard dummyHiddenCard = new CardModel(ICard.Color.DIAMONDS, 5); 
    public static final String blackOnWhite = "\u001B[30;47m";
    public static final String redOnWhite = "\u001B[31;47m";
    public static final String whiteOnBlue = "\u001B[37;44m";
    public static final String defaultColors = "\u001B[39;49m";   
    public static final String Diamond = "\u2666";
    public static final String Heart = "\u2665";
    public static final String Spade = "\u2660";
    public static final String Club = "\u2663";
    public static final String ArrowRight = "\u2192";

    public CommandLinePainter(IGameBoard model){
        this.model = model;
    }

    public void repaint(){
        // Check if game is finnished.
        if(this.model.isGameOver()){
            this.print("Game over: you won.\n");
            return;
        }

        // Print leading line with description.
        this.print("   r1\t                d1     d2     d3     d4 \n");

        // Print repository
        this.print(" ");
        ICardRepository repository = this.model.getRepository();
        if(repository.isAnyHidden())
            this.print(dummyHiddenCard);
        else this.print("   X   ");
        if(!repository.isEmpty())
            this.print(repository.top());
        else this.print("   X   ");
        this.print("       ");

        // Print decks
        for(int index = 0; index < this.model.deckCount(); index++){
            ICardDeck deck = this.model.getDeck(index);
            if(!deck.isEmpty())
                this.print(deck.top());
            else if(deck.getClass() == CardDeckModel.class){
                CardDeckModel deckModel = (CardDeckModel)deck;
                this.printEmptyDeck(deckModel.getExpectedColor(), null, true);
            }
            else this.print(" - ");
        }
        System.out.println();

        // Print stacks
        this.print("\n    s1     s2     s3     s4     s5     s6     s7\n");
        boolean stop = false;
        for(int cardIndex = 0; !stop; cardIndex++){
            this.print(" ");
            stop = true;
            for(int stackIndex = 0; stackIndex < this.model.stackCount(); stackIndex++){
                ICard card = this.model.getStack(stackIndex).get(cardIndex);            
                if(card != null){
                    this.print(card);
                    stop = false;
                }
                else this.print("       ");
            }
            System.out.println();
        }

        // Print hints
        if(this.model.hintAvailable())
            this.print(this.model.getHints());
    }

    private void print(String text){
        System.out.print(text);
    }

    private void print(ICard card){
        this.print(" ");
        if(!card.isTurnedFaceUp())
            this.printWhiteOnBlue("  ?  ");
        else if(card.color().isRed())
            this.printRedOnWhite(card.color() == ICard.Color.DIAMONDS ? 
                " " + CommandLinePainter.Diamond + " " + ICard.ValueConvertor.toString(card.value()) + " " : 
                " " + CommandLinePainter.Heart + " " + ICard.ValueConvertor.toString(card.value()) + " ");
        else if(card.color().isBlack())
            this.printBlackOnWhite(card.color() == ICard.Color.SPADES ? 
                " " + CommandLinePainter.Spade + " " + ICard.ValueConvertor.toString(card.value()) + " " : 
                " " + CommandLinePainter.Club + " " + ICard.ValueConvertor.toString(card.value()) + " "); 
        this.print((card.value() == 10 && card.isTurnedFaceUp() ? "" : " "));
    }

    private void print(ArrayList<ICardHint> hints){
        for(ICardHint hint : hints){
            // Print card and source deck.
            this.print(hint.getCard());
            this.print(" ");
            if(hint.hasSourceDeck()){
                this.print("(");
                this.printEmptyDeck(hint.getCard().color(), hint.getSourceDeck().getName(), false);
                this.print(")");
            }
            this.print(" " + CommandLinePainter.ArrowRight + " ");
            
            // Print decks
            for(ICardDeck deck : hint.getCardDecks()){
                if(deck.getClass() == CardDeckModel.class){
                    CardDeckModel model = (CardDeckModel)deck;
                    this.printEmptyDeck(model.getExpectedColor(), model.getName(), true);
                }
                else this.print(" " + deck.getName() + " ");
            }

            // Print stacks.
            for(ICardStack stack : hint.getCardStacks()){
                if(stack.isEmpty())
                    this.printWhiteOnBlue(" " + stack.getName() + " ");
                else this.printEmptyDeck(stack.top().color(), stack.getName(), true);
            }
            System.out.println();
        }
    }

    private void printEmptyDeck(ICard.Color color, String deckName, boolean separateWithSpace){
        if(separateWithSpace)
            this.print(" ");
        if(color == ICard.Color.DIAMONDS)
            this.printRedOnWhite(" " + CommandLinePainter.Diamond + " " + (deckName != null ? deckName : "") + " ");
        else if(color == ICard.Color.HEARTS)
            this.printRedOnWhite(" " + CommandLinePainter.Heart + " " + (deckName != null ? deckName : "") + " ");
        else if(color == ICard.Color.SPADES)
            this.printBlackOnWhite(" " + CommandLinePainter.Spade + " " + (deckName != null ? deckName : "") + " ");
        else if(color == ICard.Color.CLUBS)
            this.printBlackOnWhite(" " + CommandLinePainter.Club + " " + (deckName != null ? deckName : "") + " ");
        if(separateWithSpace)
            this.print("  ");
    }

    private void printWhiteOnBlue(String text){
        this.print(CommandLinePainter.whiteOnBlue + text + CommandLinePainter.defaultColors);
    }

    private void printRedOnWhite(String text){
        this.print(CommandLinePainter.redOnWhite + text + CommandLinePainter.defaultColors);
    }

    private void printBlackOnWhite(String text){
        this.print(CommandLinePainter.blackOnWhite + text + CommandLinePainter.defaultColors);
    }
}