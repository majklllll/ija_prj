package src.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import src.share.ICardDeck;
import src.share.ICardStack;
import src.controler.CommandMove;
import src.controler.ICommand;
import src.share.ICard;

public class VisualCardDeck extends VisualAbstractDeck {
	ICardDeck deck;
	private VisualCard card;
			
	public void paint() {
		ICard stackCard = deck.top();
		this.card = stackCard != null ?
			new VisualCard(VisualCard.VisualCardColor.visualColor(stackCard.color()), stackCard.value(), x, y ) :
			new VisualCard(VisualCard.VisualCardColor.NONE, 0, x, y );
		board.add(this.card);	
			
		this.card.addMouseListener(new MouseAdapter()  
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
				}		
				else{
					board.setSelectedMoveSource(deck, card);
				}		    	
		    }  
		}); 		
	}

	public void setModel(ICardDeck stackModel) {
		deck = stackModel;		
	}

	public VisualCard top(){
		return this.card;
	}
}
