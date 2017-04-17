
/**
  * File:       CommandTranslator.java
  * Author:     Jan Hrstka
  * Login:      xhrstk02
  * University: BUT (Brno University of Technology)
  * Faculty:    FIT (Faculty of Information Technology)
  * Course:     IJA (Java Programming Language)
  * Project:    Solitaire Klondike Game
  * Proj. Num:  4
  * Version:    1
  * Date:       13.04.2017
  * System:     GNU/Linux, x86_64, Ubuntu 16.04 LTS
  */
package src.cmdl;

// Dependencies
import java.util.Arrays;
import src.model.BoardModel;
import src.share.IGameBoard;
import src.share.ICardStack;
import src.share.ICardDeck;
import src.share.ICard;
import src.controler.*;
import java.util.Arrays;
import java.util.ArrayList;

public class CommandTranslator{
    IGameBoard model = null;
    public static final char deckTypeCharacter = 'd';
    public static final char stackTypeCharacter = 's'; 
    public static final char repositoryTypeCharacter = 'r';
    public static final String deckTypeCharacterSet = "dsr";  

    public CommandTranslator(IGameBoard model){
        this.model = model;
    }

    public ICommand translate(String command) throws IllegalArgumentException{
        ArrayList<String> parsedCommand = new ArrayList<String>(Arrays.asList(command.split("\\s+")));
        if(parsedCommand.size() <= 0)
            throw new IllegalArgumentException("No command specified.");
        command = parsedCommand.remove(0);

        // Analyze command
        switch(command){
            case "move":
            case "mv":
            case "m":
                if(parsedCommand.size() == 2)
                    return translateMove(parsedCommand);
                else if(parsedCommand.size() == 3)
                    return translateMoveMulti(parsedCommand);
                else throw new IllegalArgumentException("Invalid argument count of 'move' command.");
          
            case "next":
            case "n":
                this.expectedArgumentCount(parsedCommand, 0);
                return new CommandNext(model.getRepository());

            case "renew":
            case "rn":
                this.expectedArgumentCount(parsedCommand, 0);
                return new CommandRenew(model.getRepository());

            case "undo":
            case "u":
                return new ControlCommand("undo");

            case "redo":
            case "rd":
            case "r":
                return new ControlCommand("redo");

            case "quit":
            case "q":
                return new ControlCommand("quit");

            case "help":
                this.expectedArgumentCount(parsedCommand, 0);
                this.printHelp();
                return new ControlCommand("none");

            case "hint":
            case "h":
                this.expectedArgumentCount(parsedCommand, 0);
                return new ControlCommand("hint");

            case "save":
                this.expectedArgumentCount(parsedCommand, 1);
                return new ControlCommand("save", new ArrayList<String>(){{add(parsedCommand.get(0));}});

            case "load":
                this.expectedArgumentCount(parsedCommand, 1);
                return new ControlCommand("load", new ArrayList<String>(){{add(parsedCommand.get(0));}});

            default:
                throw new IllegalArgumentException("Ivalid command specified. See 'help' for more informations.");
        }
    }

    private ICommand translateMove(ArrayList<String> arguments){
        if(arguments.get(0).isEmpty() || arguments.get(1).isEmpty())
            throw new IllegalArgumentException("Invalid deck specification.");
        ICardDeck deck1 = this.selectDeck(this.getDeckType(arguments.get(0)), this.getDeckNumber(arguments.get(0)));
        ICardDeck deck2 = this.selectDeck(this.getDeckType(arguments.get(1)), this.getDeckNumber(arguments.get(1)));
        return new CommandMove(deck1, deck2);
    }

    private ICommand translateMoveMulti(ArrayList<String> arguments){
        if(arguments.get(0).isEmpty() || arguments.get(1).isEmpty() || arguments.get(2).isEmpty())
            throw new IllegalArgumentException("Invalid deck specification.");
        ICardStack stack1 = this.selectStack(this.getDeckType(arguments.get(0)), this.getDeckNumber(arguments.get(0)));
        ICardStack stack2 = this.selectStack(this.getDeckType(arguments.get(1)), this.getDeckNumber(arguments.get(1)));

        // Get card value and create command.
        int cardValue = ICard.ValueConvertor.toInt(arguments.get(2));
        ICard card = stack1.getByValue(cardValue);
        if(card == null)
            throw new IllegalArgumentException("Card with value " + cardValue + " is not present in stack '" + arguments.get(0) + "'.");
        return new CommandMoveMulti(stack1, stack2, card);
    }

    private ICardDeck selectDeck(char deckType, int index){
        if(deckType == CommandTranslator.repositoryTypeCharacter) 
            return this.model.getRepository();
        if(deckType == CommandTranslator.deckTypeCharacter && index >= 1 && index <= model.deckCount())
            return this.model.getDeck(index - 1);
        if(deckType == CommandTranslator.stackTypeCharacter && index >= 1 && index <= model.stackCount())
            return this.model.getStack(index - 1);
        throw new IllegalArgumentException("Specified deck '" + deckType + index + "' does not exist.");
    } 

    private ICardStack selectStack(char deckType, int index){
        if(deckType == CommandTranslator.stackTypeCharacter && index >= 1 && index <= model.stackCount())
            return this.model.getStack(index - 1);
        if(deckType == CommandTranslator.deckTypeCharacter || deckType == CommandTranslator.repositoryTypeCharacter)
            throw new IllegalArgumentException("Only stack can be used within this context.");
        throw new IllegalArgumentException("Specified stack '" + deckType + index + "' does not exist.");
    }

    private int getDeckNumber(String stringDeck){
        String numeric = stringDeck.substring(1);
        try{
            return Integer.parseInt(numeric);
        }
        catch(NumberFormatException e){
            throw new IllegalArgumentException("Invalid deck number given.");
        }
    }

    private char getDeckType(String stringDeck){
        if(CommandTranslator.deckTypeCharacterSet.indexOf(stringDeck.charAt(0)) == -1)
            throw new IllegalArgumentException("Invalid deck type given.");
        return stringDeck.charAt(0);
    }

    private void expectedArgumentCount(ArrayList<String> arguments, int expected){
        if(arguments.size() != expected)
            throw new IllegalArgumentException("Invalid argument count of command. Expecting " + expected + ".");
    }

    private void printHelp(){
        System.out.println("Available commands:");
        System.out.println("\tmove SOURCE DESTINATION");
        System.out.println("\t\tMoves single card from SOURCE to DESTINATION.");
        System.out.println("\t\tSOURCE is any deck - d|r|s");
        System.out.println("\t\tDESTINATION is any target deck or stack - d|s");
        System.out.println("\t\tAlterantives: mv|m");
        System.out.println("\tmove SOURCE DESTINATION CARD");
        System.out.println("\t\tMoves group of cards begining with CARD from SOURCE to DESTINATION.");
        System.out.println("\t\tSOURCE is any deck - d|r|s");
        System.out.println("\t\tDESTINATION is any target deck or stack - d|s");
        System.out.println("\t\tCARD is value of card in SOURCE, from which is pack taken.");
        System.out.println("\t\tAlterantives: mv|m");
        System.out.println("\tnext");
        System.out.println("\t\tShow next card in card repository.");
        System.out.println("\t\tAlterantives: n");
        System.out.println("\trenew");
        System.out.println("\t\tTurn over repository.");
        System.out.println("\t\tAlterantives: rn");
        System.out.println("\tundo");
        System.out.println("\t\tUndo last move.");
        System.out.println("\t\tAlterantives: u");
        System.out.println("\tredo");
        System.out.println("\t\tRedo last move.");
        System.out.println("\t\tAlterantives: rd|r");
        System.out.println("\tquit");
        System.out.println("\t\tExit the game.");
        System.out.println("\t\tAlterantives: q");
        System.out.println("\tsave FILENAME");
        System.out.println("\t\tSaves game under specified name.");
        System.out.println("\t\tFILENAME is name of saved game without extension.");
        System.out.println("\tload FILENAME");
        System.out.println("\t\tLoads game from file with given name.");
        System.out.println("\t\tFILENAME is name of saved game without extension.");
        System.out.println("\thint");
        System.out.println("\t\tShow hints for current state of game.");
        System.out.println("\t\tAlternatives: h");
        System.out.println("\thelp");
        System.out.println("\t\tDisplays help.");
    }
}