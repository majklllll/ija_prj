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
			
			//card selected - move to stack
			//no card selected yet - move from stack
			if(i == stack.size() - 1 ) {
				//for last card only
				//add event handler - select card to move
				card.addMouseListener(new MouseAdapter()  
				{  
				    public void mouseReleased(MouseEvent e)  
				    {  
						if(board.isMoveSourceSelected()){
							if(board.getMultiMoveCard() == null){
								//move one card 
								ICardDeck source = board.getSelectedMoveSource();
								
								ICommand command = new CommandMove(source, stack);
								board.getCommandBuilder().execute(command);
								
								board.setSelectedMoveSource(null);
								
							}else{
								//move many cards
								
								ICardStack source = (ICardStack)board.getSelectedMoveSource();
								
								ICommand command = new CommandMoveMulti(source, stack, board.getMultiMoveCard());
								board.getCommandBuilder().execute(command);
								
								board.setSelectedMoveSource(null);								
								board.setMultiMoveCard(null);
							}

							
						}else{
					    	board.setSelectedMoveSource(stack);							
						}				    	
				    }  
				}); 
			
			}else{
				//for all turned up cards (except the last one)
				//
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
