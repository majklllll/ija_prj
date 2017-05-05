package src.view;

import src.share.ICard;
import src.share.ICardDeck;

public abstract class VisualAbstractDeck {
	protected int x,y;
	protected VisualBoard board;
	protected String name;

	public abstract void paint();

	public void setXY(int newX, int newY) {
		x = newX;
		y = newY;
	}

	public void setPanel(VisualBoard newBoard) {
		board = newBoard;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}
}
