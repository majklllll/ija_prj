/**
 * This file contains class VisualIcons
 * @author      Jan Hrstka, Michal Peška
 */

package src.view;

import javax.swing.ImageIcon;
import src.share.ICard;
import java.util.ArrayList;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;


/**
*   This class is a singleton with icons' images
*/
public class VisualIcons {
	static VisualIcons me = null;
	private final int CARDS = 13;
	private ArrayList<ImageIcon> cards = new ArrayList<ImageIcon>();
	private ArrayList<ImageIcon> miniCards = new ArrayList<ImageIcon>();
	boolean useMiniatures = false;
	BufferedImage patternSelected= null;
	BufferedImage patternHint = null;
	final static String RESOURCES = "/lib/resources";
	
	/**
	 * Constructor loading all icons
	 */
	VisualIcons() {	
		ImageIcon back = new ImageIcon(new ImageIcon(this.getClass()
				.getResource(RESOURCES + "/back.png"))
				.getImage().getScaledInstance(150, 200, Image.SCALE_DEFAULT));
		ImageIcon backMini = new ImageIcon(back.getImage().getScaledInstance(75, 100, Image.SCALE_DEFAULT));
		cards.add(back);
		miniCards.add(backMini);
		
		//add card images
		for(int i = 1; i <= CARDS; i++) {
			ImageIcon imageIcon = new ImageIcon(new ImageIcon(this.getClass()
					.getResource(RESOURCES + "/cards/c_" + i + ".png"))
					.getImage()
					.getScaledInstance(150, 200, Image.SCALE_DEFAULT));

			ImageIcon imageIconMini = new ImageIcon(new ImageIcon(this.getClass()
					.getResource(RESOURCES + "/cards/c_" + i + ".png"))
					.getImage()
					.getScaledInstance(75, 100, Image.SCALE_DEFAULT));
			
			cards.add(imageIcon);
			miniCards.add(imageIconMini);
		}

		for(int i = 1; i <= CARDS; i++) {
			ImageIcon imageIcon = new ImageIcon(new ImageIcon(this.getClass()
					.getResource(RESOURCES + "/cards/d_" + i + ".png"))
					.getImage()
					.getScaledInstance(150, 200, Image.SCALE_DEFAULT));

			ImageIcon imageIconMini = new ImageIcon(new ImageIcon(this.getClass()
					.getResource(RESOURCES + "/cards/d_" + i + ".png"))
					.getImage()
					.getScaledInstance(75, 100, Image.SCALE_DEFAULT));
			
			cards.add(imageIcon);
			miniCards.add(imageIconMini);	
		}

		for(int i = 1; i <= CARDS; i++) {
			ImageIcon imageIcon = new ImageIcon(new ImageIcon(this.getClass()
					.getResource(RESOURCES + "/cards/h_" + i + ".png"))
					.getImage()
					.getScaledInstance(150, 200, Image.SCALE_DEFAULT));

			ImageIcon imageIconMini = new ImageIcon(new ImageIcon(this.getClass()
					.getResource(RESOURCES + "/cards/h_" + i + ".png"))
					.getImage()
					.getScaledInstance(75, 100, Image.SCALE_DEFAULT));
			
			cards.add(imageIcon);
			miniCards.add(imageIconMini);
		}

		for(int i = 1; i <= CARDS; i++) {
			ImageIcon imageIcon = new ImageIcon(new ImageIcon(this.getClass()
					.getResource(RESOURCES + "/cards/s_" + i + ".png"))
					.getImage()
					.getScaledInstance(150, 200, Image.SCALE_DEFAULT));

			ImageIcon imageIconMini = new ImageIcon(new ImageIcon(this.getClass()
					.getResource(RESOURCES + "/cards/s_" + i + ".png"))
					.getImage()
					.getScaledInstance(75, 100, Image.SCALE_DEFAULT));
			
			cards.add(imageIcon);
			miniCards.add(imageIconMini);
		}
		
		ImageIcon nothing = new ImageIcon(new ImageIcon(this.getClass()
				.getResource(RESOURCES + "/nothing.png"))
				.getImage()
				.getScaledInstance(150, 200, Image.SCALE_DEFAULT));
		ImageIcon nothingMini = new ImageIcon(nothing.getImage().getScaledInstance(75, 100, Image.SCALE_DEFAULT));
		
		ImageIcon arrows = new ImageIcon(new ImageIcon(this.getClass()
				.getResource(RESOURCES + "/arrows.png"))
				.getImage()
				.getScaledInstance(150, 200, Image.SCALE_DEFAULT));
		ImageIcon arrowsMini = new ImageIcon(arrows.getImage().getScaledInstance(75, 100, Image.SCALE_DEFAULT));
		
		cards.add(nothing);
		cards.add(arrows);
		miniCards.add(nothingMini);
		miniCards.add(arrowsMini);
		readPatterns();
	}
	
	/**
	 * Singleton getter
	 * @return singleton of this static class.
	 */	
	public static VisualIcons get() {
		if(me == null)
			me = new VisualIcons();	
		return me;
	}
	
	/**
	 * Returns icon by its type and color
	 * @param  type type of visual card.
	 * @param  value value of card.
	 * @return icon reference.
	 */	
	public ImageIcon getCardIcon(VisualCard.VisualCardColor type, int value) {
		ArrayList<ImageIcon> cardSource;
		if(this.useMiniatures){
			cardSource = miniCards;
		}else{
			cardSource = cards;
		}
		
		switch(type) {
			case CLUBS:
				return cardSource.get(value);
			case DIAMONDS:
				return cardSource.get(CARDS + value);
			case HEARTS:
				return cardSource.get(2 * CARDS + value);
			case SPADES:
				return cardSource.get(3 * CARDS + value);
			case NONE:
				return cardSource.get(4 * CARDS + 1);
			case ARROWS:
				return cardSource.get(4 * CARDS + 2);
			case BACK:
				return cardSource.get(0);
			default:
				return cardSource.get(0);
		}
	}
	
	/**
	 * Sets indicator of using miniatures
	 * @param  value are miniatures used.
	 */	
	public void setUsingMiniatures(boolean value){
		this.useMiniatures = value;		
	}
	
	/**
	 * Are icons minified?
	 */	
	public boolean areIconsMinified(){
		return this.useMiniatures;	
	}	
	
	/**
	 * Returns buffered image of specific type 
	 * @param  pattern pattern for special cards indicator.
	 * @return buffered image.
	 */
	public BufferedImage getPattern(VisualCard.VisualCardPattern pattern){
		switch(pattern){
			case HINT_TARGET:
				return this.patternHint;
			case SELECTED:
				return this.patternSelected;
			default:
				return new BufferedImage(150, 200, BufferedImage.TYPE_INT_ARGB);
		}
	}
	
	/**
	 * Reads special card backgrounds for hints and selected cards
	 */
	private void readPatterns(){
		try{
			patternSelected = ImageIO.read(new File("resources/selected.png"));
		} 
		catch(Exception e){
			patternSelected = new BufferedImage(150, 200, BufferedImage.TYPE_INT_ARGB);
		}

		try{
			patternHint = ImageIO.read(new File("resources/hasHint.png"));
		}
		catch(Exception e){
			patternHint = new BufferedImage(150, 200, BufferedImage.TYPE_INT_ARGB);
		}
	}
}
