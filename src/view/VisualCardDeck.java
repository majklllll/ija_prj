package src.view;

import java.util.ArrayList;

import src.share.ICardDeck;
import src.share.ICardStack;
import src.share.ICard;

public class VisualCardDeck extends VisualAbstractDeck  {
	
	ICardDeck deck;
	private VisualCard card;
			
	public void paint() {
		
		ICard stackCard = deck.top();
		if(stackCard != null){
			VisualCard cardVis = new VisualCard(VisualCard.VisualCardColor.visualColor(stackCard.color()), stackCard.value(), x, y );
			
			this.card = cardVis;
			board.add(card);
			
		}else{
			VisualCard cardVis = new VisualCard(VisualCard.VisualCardColor.NONE, 0, x, y );
			
			this.card = cardVis;
			board.add(card);
		}

			
			
		
		
	}
	public void setModel(ICardDeck stackModel) {
		deck = stackModel;		
	}
}
