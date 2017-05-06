
/**
  * File:       ISupportRepaint.java
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
package src.share;

/**
 * Interface required by observable class.
 * Each observer has to implement these funtions.
 */
public interface ISupportRepaint{
          
    /**
     * State of observable class changed, repaint observer.
     */
    public void repaint();
}