
/**
  * File:       CommandMoveMulti.java
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
package src.controler;

// Dependecies
import src.share.ICardDeck;
import src.share.ICardStack;
import src.share.ICard;

public class CommandMoveMulti implements ICommand{
    ICardStack source      = null;
    ICardStack destination = null;
    ICard card             = null;
    boolean wasTurnedUp    = false;

    public CommandMoveMulti(ICardStack source, ICardStack destination, ICard card){
        this.source = source;
        this.destination = destination;
        this.card = card;
    }

    public void execute(){
        if(this.canExecute()){
            wasTurnedUp = false;
            this.destination.put(this.source.pop(card));
            if(!this.source.isEmpty()){
                this.wasTurnedUp = this.source.top().isTurnedFaceUp();
                this.source.top().turnFaceUp();
            }
        }
    }

    public void unexecute(){
        if(!this.wasTurnedUp)
            this.source.top().turnFaceDown();
        ICardDeck takenDeck = this.destination.pop(card);
        for(int index = 0; index < takenDeck.size(); index++){
            this.source.emplace(takenDeck.get(index));
        }
    }

    public boolean canExecute(){
        return this.destination.canPut(this.card) && this.source.contains(card);
    }
}