package src.view;

import java.awt.Color;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import src.share.ICard;


public class VisualCard extends JLabel {
	private float opacity;
	ICard.Color type;
	int value;
	VisualCard(VisualCard.VisualCardColor cardType, int cardValue, int x, int y) {
		ImageIcon icon = VisualIcons.get().getCardIcon(cardType, cardValue);
		
		this.setIcon(icon);
		this.setHorizontalAlignment(SwingConstants.CENTER);
		this.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
		
		
	}
    public static enum VisualCardColor{
        CLUBS("C"),
        DIAMONDS("D"),
        HEARTS("H"),
        SPADES("S"),
        BACK("BACK"),
    	NONE("NONE"),
    	ARROWS("ARROW");
    	
    	private final String cardValue;
        private VisualCardColor(String value){
            this.cardValue = value;
        }
        
        public static VisualCard.VisualCardColor visualColor(ICard.Color color){
        	switch(color){
        		case CLUBS:
        			return CLUBS;
        		case DIAMONDS:
        			return DIAMONDS;
        		case HEARTS:
        			return HEARTS;
        		case SPADES:
        			return SPADES;
        	}
        	return CLUBS;
        }
    }
    
	public void setOpacity(float opacity) {
	    this.opacity = opacity;
	}

	public float getOpacity(){
	    return this.opacity;
	}
	
	public void setSelected(){
		//System.out.println("setting it");
		this.setOpacity(0.5f);	
		//super.repaint();
		//this.revalidate();
		
	}
	
	
	
}
