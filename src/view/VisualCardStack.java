package src.view;

import java.util.ArrayList;

import src.share.ICardDeck;
import src.share.ICardStack;
import src.share.ICard;

public class VisualCardStack extends VisualAbstractDeck {
	ICardStack stack;
	private ArrayList<VisualCard> cards = new ArrayList<VisualCard>();
			
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
			cards.add(card);
			
			
		}
		for(int i = 0; i < stack.size();i++) System.out.println(stack.get(i).value());
		System.out.println();
		
	}
	public void setModel(ICardStack stackModel) {
		stack = stackModel;		
	}
}
