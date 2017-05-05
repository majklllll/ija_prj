
/**
  * File:       CommandNext.java
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

/**
 * Command to show next card in given repostiory.
 */
public class CommandNext implements ICommand{
    ICardRepository repository = null;

    public CommandNext(ICardRepository repository){
        this.repository = repository;
    }

    public void execute(){
        if(this.canExecute())
            repository.showNext();
    }

    public void unexecute(){
        repository.hideTopCard();
    }

    public boolean canExecute(){
        return this.repository.isAnyHidden();
    }
}