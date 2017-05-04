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

public class VisualCardStack extends VisualAbstractDeck {
	ICardStack stack;
		
	public void paint() {
		int distance = VisualIcons.get().areIconsMinified() ? 15 : 30;
		
		//adding cards in reverse direction to ensure right card overlay
		for(int i = stack.size() - 1; i >= 0; i--) {
			VisualCard card;
			ICard stackCard = stack.get(i);
			if(stackCard.isTurnedFaceUp()){
				card = new VisualCard( VisualCard.VisualCardColor.visualColor(stackCard.color()), stackCard.value(), x, y + distance * i );
				
			}else{
				card = new VisualCard( VisualCard.VisualCardColor.BACK, 0, x, y + distance * i );
				
			}
			
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
						if(board.isMoveSourceSelected()){
							ICommand command = null;
							if(board.getMultiMoveCard() == null){
								// Move one card.
								ICardDeck source = board.getSelectedMoveSource();
								command = new CommandMove(source, stack);
								board.setSelectedMoveSource(null);
							}
							else{
								// Move many cards.	
								ICardStack source = (ICardStack)board.getSelectedMoveSource();
								command = new CommandMoveMulti(source, stack, board.getMultiMoveCard());
								board.setSelectedMoveSource(null);								
								board.setMultiMoveCard(null);
							}

							// Execute command xor set this as a source.
							if(command.canExecute())
								 board.getCommandBuilder().execute(command);
							else board.setSelectedMoveSource(stack);
						}
						else board.setSelectedMoveSource(stack);										    	
				    }  
				}); 
			
			}
			else{
				// For all turned up cards (except the last one)
				if(stackCard.isTurnedFaceUp())
					card.addMouseListener(new MouseAdapter()  
					{  
					    public void mouseReleased(MouseEvent e)  
					    {  
					    	board.setSelectedMoveSource(stack);
						    board.setMultiMoveCard(stackCard);	 			    	
					    }  
					}); 				
			}
		}
	}

	public void setModel(ICardStack stackModel) {
		stack = stackModel;		
	}
}
