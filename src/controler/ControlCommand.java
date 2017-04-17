
/**
  * File:       RelayCommand.java
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
package src.controler;

import java.util.ArrayList;



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

    public String getCallable(){
        return this.callable;
    }

    public ArrayList<String> getArguments(){
        return this.arguments;
    }

    public int argumentCount(){
        return this.arguments != null ? this.arguments.size() : 0;
    }

    public boolean hasArguments(){
        return this.argumentCount() > 0;
    }
}