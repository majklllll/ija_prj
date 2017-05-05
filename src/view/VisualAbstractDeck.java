/**
 * This file contains class VisualAbstractDeck
 * @author      Jan Hrstka, Michal Peška
 */

package src.view;

import src.share.ICard;
import src.share.ICardDeck;

/**
*   This class defines common deck properties and methods
*/
public abstract class VisualAbstractDeck {
	protected int x,y;
	protected VisualBoard board;
	protected String name;
	
	/**
	 * Abstract paint method
	 */
	public abstract void paint();
	
	/**
	 * Method sets coordinates of this deck
	 * @param  newX coordinate X.
	 * @param  newY coordinate Y.
	 */
	public void setXY(int newX, int newY) {
		x = newX;
		y = newY;
	}
	
	/**
	 * Method sets reference to board
	 * @param  newBoard reference to board.
	 */
	public void setPanel(VisualBoard newBoard) {
		board = newBoard;
	}
	
	/**
	 * Sets name of this deck
	 * @param  name name for deck.
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/**
	 * Method returns name of this deck
	 * @return Deck name.
	 */
	public String getName(){
		return this.name;
	}
}
