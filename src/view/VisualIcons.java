package src.view;

import javax.swing.ImageIcon;
import src.share.ICard;
import java.util.ArrayList;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

//singleton storing icons
public class VisualIcons {
	static VisualIcons me = null;
	private final int CARDS = 13;
	private ArrayList<ImageIcon> cards = new ArrayList<ImageIcon>();
	private ArrayList<ImageIcon> miniCards = new ArrayList<ImageIcon>();
	boolean useMiniatures = false;
	BufferedImage patternSelected= null;
	BufferedImage patternHint = null;
	final static String RESOURCES = "/src/resources";

	VisualIcons() {	
		ImageIcon back = new ImageIcon(new ImageIcon(this.getClass().getResource("/src/resources/back.png"))
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
	
	public static VisualIcons get() {
		if(me == null)
			me = new VisualIcons();	
		return me;
	}
	
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
	
	public void setUsingMiniatures(boolean value){
		this.useMiniatures = value;		
	}
	
	public boolean areIconsMinified(){
		return this.useMiniatures;	
	}	

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
