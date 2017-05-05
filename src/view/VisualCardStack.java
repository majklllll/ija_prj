/**
 * This file contains class VisualCardStack
 * @author      Jan Hrstka, Michal Peška
 */

package src.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import src.share.ICardDeck;
import src.share.ICardStack;
import src.controler.CommandMove;
import src.controler.CommandNext;
import src.controler.CommandMoveMulti;
import src.controler.ICommand;
import src.share.ICard;


/**
*   This class represents one card stack on board
*/
public class VisualCardStack extends VisualAbstractDeck {
	ICardStack stack;
	VisualCard topCard;
	
	/**
	 * Paints this stack
	 */		
	public void paint() {
		int distance = VisualIcons.get().areIconsMinified() ? 15 : 30;
		
		//adding cards in reverse direction to ensure right card overlay
		for(int i = stack.size() - 1; i >= 0; i--){
			ICard stackCard = stack.get(i);
			VisualCard card = stackCard.isTurnedFaceUp() ? 
				new VisualCard( VisualCard.VisualCardColor.visualColor(stackCard.color()), stackCard.value(), x, y + distance * i ) :
				new VisualCard( VisualCard.VisualCardColor.BACK, 0, x, y + distance * i );
			board.add(card);
			
			// Card selected - move to stack
			// No card selected yet - move from stack
			if(i == stack.size() - 1 ) {
				// For last card only
				// Add event handler - select card to move
				card.addMouseListener(new MouseAdapter()  
				{  
				    public void mouseReleased(MouseEvent e)  
				    {  
				    	moveStackHere(card);
				    }  
				});
				this.topCard = card;
			}
			else{
				// For all turned up cards (except the last one)
				if(stackCard.isTurnedFaceUp())
					card.addMouseListener(new MouseAdapter()  
					{  
					    public void mouseReleased(MouseEvent e)  
					    {  
					    	board.setSelectedMoveSource(stack, card);
						    board.setMultiMoveCard(stackCard);
					    }  
					}); 				
			}
		}
		
		//in a case if there are no cards left, add a blank one
		if(stack.size() == 0){		
			VisualCard card = new VisualCard(VisualCard.VisualCardColor.NONE, 0, x, y );
			board.add(card);
			card.addMouseListener(new MouseAdapter()  
			{  
			    public void mouseReleased(MouseEvent e)  
			    {  
			    	if(board.isMoveSourceSelected()){
			    		moveStackHere(card);
			    	}
			    }  
			}); 
			this.topCard = card;
		}
	}
	
	/**
	 * Moves stack
	 */	
	public void moveStackHere(VisualCard card){
		if(board.isMoveSourceSelected()){
			ICommand command = null;
			if(board.getMultiMoveCard() == null){
				// Move one card.
				ICardDeck source = board.getSelectedMoveSource();
				command = new CommandMove(source, stack);
				board.unselectedMoveSource();
			}
			else{
				// Move many cards.	
				ICardStack source = (ICardStack)board.getSelectedMoveSource();
				command = new CommandMoveMulti(source, stack, board.getMultiMoveCard());
				board.unselectedMoveSource();							
			}

			// Execute command xor set this as a source.
			if(command.canExecute())
				 board.getCommandBuilder().execute(command);
			else board.setSelectedMoveSource(stack, card);
		}
		else board.setSelectedMoveSource(stack, card);		
	}
	
	
	/**
	 * Sets model reference of this stack
	 * @param  stackModel model of stack.
	 */
	public void setModel(ICardStack stackModel) {
		stack = stackModel;		
	}
	
	/**
	 * Gets card from top of this stack
	 * @return card model reference.
	 */
	public VisualCard top(){
		return this.topCard;
	}
}
