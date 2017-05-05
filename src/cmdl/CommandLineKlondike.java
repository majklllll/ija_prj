
/**
  * File:       CommandLineKlondike.java
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
package src.cmdl;

import src.model.BoardModel;
import src.controler.CommandBuilder;
import src.controler.ICommand;
import src.share.*;

/**
 * Command line version of Klondike Solitaire.
 */
public class CommandLineKlondike{

    public static void main(String[] args){
        String command;
        BoardModel board = new BoardModel();
        CommandBuilder commander = new CommandBuilder(board);
        CommandLinePainter painter = new CommandLinePainter(board);
        CommandTranslator translator = new CommandTranslator(board);
        board.registerObserver((ISupportRepaint)painter);
        painter.repaint();

        while(true){
            command = System.console().readLine().trim();
            try{
                ICommand preparedCommand = translator.translate(command);
                if(!commander.execute(preparedCommand))
                    System.out.println("Can not perform this action.");
            }
            catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }
    }
}