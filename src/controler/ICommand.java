
/**
  * File:       ICommand.java
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

// Depenencies
import java.io.Serializable;

/**
 * Interface for command design pattern.
 */
public interface ICommand extends Serializable{

    /** 
     * Perform action specified by command. 
     * */
    public void execute();

    /** 
     * Undo changes caused by application of command. 
     * */
    public void unexecute();

    /**
     * Check wheever can be command executed.
     * @return true when its possible to execute command.
     */
    public boolean canExecute();
}