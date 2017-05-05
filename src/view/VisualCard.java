/**
 * This file contains class VisualCard
 * @author      Jan Hrstka, Michal Peška
 */

package src.view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.io.Serializable;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;

import src.model.CardModel;
import src.share.ICard;


/**
*   This class represents one card on board
*/
public class VisualCard extends JLabel {
	private float opacity;
	private static final float OpacityPattern = 0.5f;
	private ICard.Color color;
	private int value;
	private boolean isSelected = false;
	private boolean isHintTarget = false;
	
	/**
	 * Constructor for visual card setting it
	 * @param  cardType type of card.
	 * @param  cardValue value of card.
	 * @param  x coord. X.
	 * @param  y coord. Y.
	 */
  	VisualCard(VisualCard.VisualCardColor cardType, int cardValue, int x, int y){
        ImageIcon icon = VisualIcons.get().getCardIcon(cardType, cardValue);	
        this.color = VisualCardColor.toColor(cardType);
        this.value = cardValue;
        this.setIcon(icon);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        this.setBounds(x, y, icon.getIconWidth(), icon.getIconHeight());
  	}
	
	/**
	 * Enumerator for visual colors and other necessary types
	 */
    public static enum VisualCardColor{
        CLUBS("C"),
        DIAMONDS("D"),
        HEARTS("H"),
        SPADES("S"),
        BACK("BACK"),
    	NONE("NONE"),
    	ARROWS("ARROW");
    	
    	private final String cardValue;
         	
    	/**
    	 * Sets visual card color
    	 */
        private VisualCardColor(String value){
            this.cardValue = value;
        }
	
    	/**
    	 * Converts card color to special type of color for visual cards
    	 * @param  color model color to convert.
    	 * @return visual color type.
    	 */        
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
	
    	/**
    	 * Converts visual color to type of color for model cards
    	 * @param  color visual color to convert.
    	 * @return model type color.
    	 */
    		public static ICard.Color toColor(VisualCard.VisualCardColor color){
    			switch(color){
    				case CLUBS:
    					return ICard.Color.CLUBS;
    				case DIAMONDS:
    					return ICard.Color.DIAMONDS;
    				case HEARTS:
    					return ICard.Color.HEARTS;
    				case SPADES:
    					return ICard.Color.SPADES;
    				default:
    					return ICard.Color.SPADES;
    			}
    		}
    }
	
	/**
	 * Special card color modificators
	 */
	public static enum VisualCardPattern{
		NONE("NONE"),
		SELECTED("SELECTED"),
		HINT_TARGET("HINT TARGET");

		private final String cardValue;

		private VisualCardPattern(String value){
			this.cardValue = value;
		}
	}
	
  	/**
  	 * Paints this component
  	 */
  	@Override
    public void paintComponent(final Graphics g) {
    		super.paintComponent(g);
    		if(isSelected || isHintTarget){
    			Graphics2D g2 = (Graphics2D)g;
    			BufferedImage im = isSelected ? VisualIcons.get().getPattern(VisualCard.VisualCardPattern.SELECTED) :
    											VisualIcons.get().getPattern(VisualCard.VisualCardPattern.HINT_TARGET);
    			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, VisualCard.OpacityPattern));
    			g2.drawRenderedImage((RenderedImage)im, AffineTransform.getTranslateInstance(0, 0));
    			g2.dispose();
    		}
    }
	
	/**
	 * Sets opacity of this card
	 * @param  opacity opacity of this element.
	 */    
	public void setOpacity(float opacity) {
	    this.opacity = opacity;
	}
	
	/**
	 * Returns opacity of this card
	 * @return opacity value.
	 */
	public float getOpacity(){
	    return this.opacity;
	}
	
	/**
	 * Set if this card is selected
	 * @param  isSelected is this selected.
	 */	
	public void setSelected(boolean isSelected){
		this.isSelected = isSelected;
		repaint();
	}
	
	/**
	 * Sets hint target
	 * @param  isHintTarget is this hint target?.
	 */
	public void setHintTarget(boolean isHintTarget){
		this.isHintTarget = isHintTarget;
		repaint();
	}
	
	/**
	 * Returns representation of this card
	 * @return card model reference.
	 */
	public ICard toCardModel(){
		return new CardModel(this.color, this.value);
	}
}
