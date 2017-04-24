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
		/*System.out.println(cardPicker);
		
		if(cardPicker != null)
			board.remove(cardPicker);
		
		if(cardDefer != null)
			board.remove(cardDefer);*/
		
		
		if(pack.isAnyHidden()) {
			//hidden cards - cardback
			
			VisualCard card = new VisualCard(VisualCard.VisualCardColor.BACK, 0, 0, y  );
			board.add(card);
			cardPicker = card;	
			
			//add event handler - next card
			card.addMouseListener(new MouseAdapter()  
			{  
			    public void mouseClicked(MouseEvent e)  
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
			    public void mouseClicked(MouseEvent e)  
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
