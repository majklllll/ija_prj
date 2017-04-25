package src.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import src.share.ICardDeck;
import src.share.ICardStack;
import src.controler.CommandMove;
import src.controler.CommandNext;
import src.controler.ICommand;
import src.share.ICard;

public class VisualCardStack extends VisualAbstractDeck {
	ICardStack stack;
		
	public void paint() {
		
		//adding cards in reverse direction to ensure right card overlay
		for(int i = stack.size() - 1; i >= 0; i--) {
			VisualCard card;
			ICard stackCard = stack.get(i);
			if(stackCard.isTurnedFaceUp()){
				card = new VisualCard( VisualCard.VisualCardColor.visualColor(stackCard.color()), stackCard.value(), x, y + 30 * i );
				
			}else{
				card = new VisualCard( VisualCard.VisualCardColor.BACK, 0, x, y + 30 * i );
				
			}
			
			board.add(card);
			
			//card selected - move to stack
			//no card selected yet - move from stack
			if(i == stack.size() - 1 ) {
				//for last card only
				//add event handler - select card to move
				card.addMouseListener(new MouseAdapter()  
				{  
				    public void mouseClicked(MouseEvent e)  
				    {  
						if(board.isMoveSourceSelected()){
							ICardDeck source = board.getSelectedMoveSource();
							
							ICommand command = new CommandMove(source, stack);
							board.getCommandBuilder().execute(command);
							
							board.setSelectedMoveSource(null);
							
						}else{
					    	board.setSelectedMoveSource(stack);							
						}				    	
				    }  
				}); 
			
			}

		}


		
		
	}
	public void setModel(ICardStack stackModel) {
		stack = stackModel;		
	}
}
