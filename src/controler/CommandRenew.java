
/**
  * File:       CommandRenew.java
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
import src.share.ICardRepository;
import src.share.ICard;
import java.util.ArrayList;

/**
 * Command to mix and turn over given repository.
 */
public class CommandRenew implements ICommand{
    ICardRepository repository = null;
    ArrayList<ICard> cardsBefore = null;

    public CommandRenew(ICardRepository repository){
        this.repository = repository;
    }

    public void execute(){
        if(this.canExecute()){
            cardsBefore = new ArrayList<ICard>();
            for(int index = 0; index < repository.size(); index++)
                cardsBefore.add(repository.get(index));
            repository.turnOver();
        }       
    }

    public void unexecute(){
        while(repository.isAnyHidden())
            repository.showNext();
        while(repository.size() > 0)
            repository.pop();
        while(cardsBefore.size() > 0)
            repository.emplace(cardsBefore.remove(0));
    }

    public boolean canExecute(){
        return !this.repository.isAnyHidden();
    }
}