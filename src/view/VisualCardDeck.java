/**
 * This file contains class VisualCardDeck
 * @author      Jan Hrstka, Michal Peška
 */

package src.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import src.share.ICardDeck;
import src.share.ICardStack;
import src.controler.CommandMove;
import src.controler.ICommand;
import src.share.ICard;


/**
*   This class represents one of four card decks on board
*/
public class VisualCardDeck extends VisualAbstractDeck {
	ICardDeck deck;
	private VisualCard card;
	
	/**
	 * Paints this deck
	 */			
	public void paint() {
		ICard stackCard = deck.top();
		VisualCard card = this.card = stackCard != null ?
			new VisualCard(VisualCard.VisualCardColor.visualColor(stackCard.color()), stackCard.value(), x, y ) :
			new VisualCard(VisualCard.VisualCardColor.NONE, 0, x, y );
		board.add(this.card);	
			
		card.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseReleased(MouseEvent e)  
		    {  
				if(board.isMoveSourceSelected()){
					ICardDeck source = board.getSelectedMoveSource();	
					ICommand command = new CommandMove(source, deck);
					if(command.canExecute()){
						board.getCommandBuilder().execute(command);
						board.unselectedMoveSource();
					}
					else if(!deck.isEmpty()) 
						board.setSelectedMoveSource(deck, card);	
				}		
				else if(!deck.isEmpty()) 
					board.setSelectedMoveSource(deck, card);	    	
		    }
		}); 		
	}
	
	/**
	 * Sets model reference for this deck
	 * @param  stackModel model of stack.
	 */
	public void setModel(ICardDeck stackModel) {
		deck = stackModel;		
	}
	
	/**
	 * Gets card from top
	 * @return  card on top.
	 */
	public VisualCard top(){
		return this.card;
	}
}
