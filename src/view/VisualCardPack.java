package src.view;

import java.awt.event.*;
import src.share.ICardRepository;
import src.share.ICard;

import src.controler.*;

public class VisualCardPack extends VisualAbstractDeck {
	
	private ICardRepository pack;
	private VisualCard cardPicker;
	private VisualCard cardDefer;
	
			
	public void paint() {
		
		if(pack.isAnyHidden()) {
			//hidden cards - cardback
			
			VisualCard card = new VisualCard(VisualCard.VisualCardColor.BACK, 0, 0, y  );
			board.add(card);
			cardPicker = card;	
			
			//add event handler - next card
			card.addMouseListener(new MouseAdapter()  
			{  
			    public void mouseReleased(MouseEvent e)  
			    {  
			    	//show next card
			    	ICommand command = new CommandNext(pack);
			    	board.getCommandBuilder().execute(command);
			    }  
			}); 
			
			
		}else{
			//no hidden card - arrows
			VisualCard card = new VisualCard(VisualCard.VisualCardColor.ARROWS, 0, 0, y  );
			board.add(card);
			cardPicker = card;
			
			//add event handler - turn pack
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
        	//there is a card
        	ICard modelCard = pack.top();
        	VisualCard card = new VisualCard(VisualCard.VisualCardColor.visualColor(modelCard.color()), modelCard.value(), x, y  );
			board.add(card);
			cardDefer = card; 
			
			//add event handler - select card to move
			card.addMouseListener(new MouseAdapter()  
			{  
			    public void mouseReleased(MouseEvent e)  
			    {  
			    	board.setSelectedMoveSource(pack);
			    	//VisualCard origin = (VisualCard) e.getSource();
			    	//origin.setSelected();
			    	//board.repaint();
			    }  
			}); 
			
        }else{
        	//there is no card
			VisualCard card = new VisualCard(VisualCard.VisualCardColor.NONE, 0, x, y  );
			board.add(card);
			cardDefer = card;        	
        	
        }

		
	}
	public void setModel(ICardRepository stackModel) {
		pack = stackModel;		
	}

}
