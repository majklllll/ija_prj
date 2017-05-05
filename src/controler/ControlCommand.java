
/**
  * File:       RelayCommand.java
  * @author     Jan Hrstka
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
package src.controler;

import java.util.ArrayList;


/**
 * Command containing full name and arguments of method to be called usign reflection.
 */
public class ControlCommand implements ICommand{
    private String callable = null;
    private ArrayList<String> arguments = null;

    public ControlCommand(String callable){
        this.callable = callable;
    }

    public ControlCommand(String callable, ArrayList<String> arguments){
        this.callable = callable;
        this.arguments = arguments;
    }

    public void execute(){}

    public void unexecute(){}

    public boolean canExecute(){
        return true;
    }

    /**
     * Get callable/method to be called while command exection.
     * @return string containng callable method.
     */
    public String getCallable(){
        return this.callable;
    }

    /**
     * Get argumetns of called method.
     * @return list of method arguments.
     */
    public ArrayList<String> getArguments(){
        return this.arguments;
    }

    /**
     * Get count of method arguments.
     * @return count of method arguments.
     */
    public int argumentCount(){
        return this.arguments != null ? this.arguments.size() : 0;
    }

    /**
     * Check if method related with control command has any argument.
     * @return true when method related with command has at least one argumetn. 
     */
    public boolean hasArguments(){
        return this.argumentCount() > 0;
    }
}