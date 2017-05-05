
/**
  * File:       CommandMove.java
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
package src.controler;

// Dependecies
import src.share.ICardDeck;

/**
 * Command to move single card from deck to another.
 */
public class CommandMove implements ICommand{
    ICardDeck source      = null;
    ICardDeck destination = null;
    boolean wasTurnedUp   = false;

    public CommandMove(ICardDeck source, ICardDeck destination){
        this.source = source;
        this.destination = destination;
    }

    public void execute(){
        if(this.canExecute()){
            wasTurnedUp = false;
            this.destination.put(source.pop());
            if(!this.source.isEmpty()){
                this.wasTurnedUp = this.source.top().isTurnedFaceUp();
                this.source.top().turnFaceUp();
            }
        }
    }

    public void unexecute(){
        if(!this.wasTurnedUp)
            this.source.top().turnFaceDown();
        this.source.emplace(this.destination.pop());
    }

    public boolean canExecute(){
        if(!destination.canPut(source.top()))
            return false;
        return true;
    }
}