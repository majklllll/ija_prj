
/**
  * File:       CommandBuilder.java
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
import src.share.ICardRepository;
import src.share.ICardStack;
import src.share.IGameBoard;
import src.share.ICardDeck;
import src.model.BoardModel;
import src.share.ICard;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class CommandBuilder{
    public static final String saveFileExtension = ".stack";
    ArrayList<ICommand> undoStack = new ArrayList<ICommand>();
    ArrayList<ICommand> redoStack = new ArrayList<ICommand>();
    IGameBoard model = null;

    public CommandBuilder(IGameBoard model){
        this.model = model;
    }

    private void push(ArrayList<ICommand> stack, ICommand command){
        stack.add(command);
    }

    private ICommand pop(ArrayList<ICommand> stack){
        return stack.remove(stack.size() - 1);
    }

    public boolean execute(ICommand command){
        if(command.getClass() == ControlCommand.class)
            return this.executeCallAble((ControlCommand)command);
        else if(command.canExecute()){
            command.execute();
            this.push(undoStack, command);
            this.model.update();
            return true;
        }
        return false;
    }

    private boolean executeCallAble(ControlCommand command){
        Boolean methodResult = null;
        java.lang.reflect.Method method;
        try{
            if(command.hasArguments()){
                method = this.getClass().getMethod(command.getCallable(), ArrayList.class);
                methodResult = (Boolean)method.invoke(this, command.getArguments());
            }
            else {
                method = this.getClass().getMethod(command.getCallable());
                methodResult = (Boolean)method.invoke(this);
            }
            this.model.update();
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return methodResult.booleanValue();
    }

    public boolean undo(){
        if(canUndo()){
            ICommand command = this.pop(undoStack);
            command.unexecute();
            this.push(redoStack, command);
            return true;
        }
        return false;
    }

    public boolean redo(){
        if(canRedo()){
            ICommand command = this.pop(redoStack);
            command.execute();
            this.push(undoStack, command);
            return true;
        } 
        return false;
    }

    public boolean quit(){
        System.exit(0);
        return true;
    }

    public boolean canExecute(ICommand command){
        return command.canExecute();
    }

    public boolean canUndo(){
        return undoStack.size() > 0;
    }

    public boolean canRedo(){
        return redoStack.size() > 0;
    }

    // Concreate commands.
    public boolean move(ICardDeck source, ICardDeck destination){
        return this.execute(new CommandMove(source, destination));
    }

    public boolean moveMulti(ICardStack source, ICardStack destination, ICard card){
        return this.execute(new CommandMoveMulti(source, destination, card));
    }

    public boolean next(ICardRepository repository){
        return this.execute(new CommandNext(repository));
    }

    public boolean renew(ICardRepository repository){
        return this.execute(new CommandRenew(repository));
    }

    public boolean none(){
        return true;
    }

    public boolean hint(){
        this.model.createHints();
        return true;
    }

    public boolean save(ArrayList<String> fileNameList){
        if(!fileNameList.isEmpty())
            return this.model.save(fileNameList.get(0) + BoardModel.saveFileExtension) 
                && this.save(fileNameList.get(0) + CommandBuilder.saveFileExtension);
        return false;
    }

    public boolean load(ArrayList<String> fileNameList){
        if(fileNameList.isEmpty())
            return false; 

        ArrayList<ICommand> currentUndoStack = this.undoStack;
        ArrayList<ICommand> currentRedoStack = this.redoStack;
        if(this.load(fileNameList.get(0) + CommandBuilder.saveFileExtension)){
            if(!this.model.load(fileNameList.get(0) + BoardModel.saveFileExtension)){
                this.undoStack = currentUndoStack;  // Restore stacks when board is not loaded.
                this.redoStack = currentRedoStack;
                return false;
            }
            return true;
        }
        return false;
    }

    public boolean save(String fileName){
        FileOutputStream fos    = null;
        ObjectOutputStream oos  = null;
        try{
            fos = new FileOutputStream(fileName);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(this.undoStack);
            oos.writeObject(this.redoStack);
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

    public boolean load(String fileName){
        ObjectInputStream ois     = null;
        FileInputStream fis       = null;
        ArrayList<ICommand> undos = null;
        ArrayList<ICommand> redos = null;
        try{
            fis = new FileInputStream(fileName);
            ois = new ObjectInputStream(fis);
            undos = (ArrayList<ICommand>) ois.readObject();
            redos = (ArrayList<ICommand>) ois.readObject();
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
        if(undos != null && redos != null){
            this.undoStack = undos;
            this.redoStack = redos;
            return true;
        }
        return false;
    }
}