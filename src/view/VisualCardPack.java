/**
 * This file contains class VisualCardPack
 * @author      Jan Hrstka, Michal Peška
 */

package src.view;

import java.awt.event.*;
import src.share.ICardRepository;
import src.share.ICard;

import src.controler.*;


/**
*   This class represents both card repositories (picker and defer deck)
*/
public class VisualCardPack extends VisualAbstractDeck {
	
	private ICardRepository pack;
	private VisualCard cardPicker;
	private VisualCard cardDefer;
	
	
	/**
	 * Paints picker and defer
	 */			
	public void paint() {
		if(pack.isAnyHidden()){
			// Hidden cards - cardback
			VisualCard card = new VisualCard(VisualCard.VisualCardColor.BACK, 0, 0, y  );
			board.add(card);
			cardPicker = card;	
			
			// Add event handler - next card
			card.addMouseListener(new MouseAdapter()  
			{  
			    public void mouseReleased(MouseEvent e)  
			    {  
			    	// Show next card
			    	ICommand command = new CommandNext(pack);
			    	board.getCommandBuilder().execute(command);
			    }  
			}); 
		}
		else{
			// No hidden card - arrows
			VisualCard card = new VisualCard(VisualCard.VisualCardColor.ARROWS, 0, 0, y  );
			board.add(card);
			cardPicker = card;
			
			// Add event handler - turn pack
			card.addMouseListener(new MouseAdapter()  
			{  
			    public void mouseReleased(MouseEvent e)  
			    {  
			    	ICommand command = new CommandRenew(pack);
			    	board.getCommandBuilder().execute(command);
			    }  
			}); 
		}
	
        if(!pack.isEmpty()) {
        	// There is a card
        	ICard modelCard = pack.top();
        	VisualCard card = new VisualCard(VisualCard.VisualCardColor.visualColor(modelCard.color()), modelCard.value(), x, y  );
			board.add(card);
			cardDefer = card; 
			
			// Add event handler - select card to move
			card.addMouseListener(new MouseAdapter()  
			{  
			    public void mouseReleased(MouseEvent e)  
			    {  
			    	board.setSelectedMoveSource(pack, card);
			    }  
			}); 
			
        }
		else{
        	// There is no card
			VisualCard card = new VisualCard(VisualCard.VisualCardColor.NONE, 0, x, y);
			board.add(card);
			cardDefer = card;        	
        }
	}
	
	/**
	 * Sets model reference for this repository
	 * @param  stackModel model of this stack.
	 */
	public void setModel(ICardRepository stackModel) {
		pack = stackModel;		
	}
}
