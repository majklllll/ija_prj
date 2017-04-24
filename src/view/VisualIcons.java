package src.view;

import javax.swing.ImageIcon;
import src.share.ICard;
import java.util.ArrayList;
import java.awt.Image;

//singleton storing icons
public class VisualIcons {
	static VisualIcons me = null;
	
	private ImageIcon back;
	private ImageIcon nothing;
	private ImageIcon arrows;
	
	private final int CARDS = 13;
	
	private ArrayList<ImageIcon> cards = new ArrayList<ImageIcon>();
	private ArrayList<ImageIcon> miniCards = new ArrayList<ImageIcon>();

	
	VisualIcons() {
		this.back = new ImageIcon(this.getClass().getResource("/src/resources/back.png"));
		this.nothing = new ImageIcon(this.getClass().getResource("/src/resources/nothing.png"));
		this.arrows = new ImageIcon(this.getClass().getResource("/src/resources/arrows.png"));
		
		cards.add(back);
		
		
		//add card images
		for(int i = 1; i <= CARDS; i++) {
		
			ImageIcon imageIcon = new ImageIcon(new ImageIcon(this.getClass().getResource("/src/resources/cards/c_" + i + ".png"))
					.getImage()
					.getScaledInstance(150, 200, Image.SCALE_DEFAULT));

			ImageIcon imageIconMini = new ImageIcon(new ImageIcon(this.getClass().getResource("/src/resources/cards/c_" + i + ".png"))
					.getImage()
					.getScaledInstance(75, 100, Image.SCALE_DEFAULT));
			
			cards.add(imageIcon);
			miniCards.add(imageIconMini);
		}
		for(int i = 1; i <= CARDS; i++) {
			ImageIcon imageIcon = new ImageIcon(new ImageIcon(this.getClass().getResource("/src/resources/cards/d_" + i + ".png"))
					.getImage()
					.getScaledInstance(150, 200, Image.SCALE_DEFAULT));

			ImageIcon imageIconMini = new ImageIcon(new ImageIcon(this.getClass().getResource("/src/resources/cards/d_" + i + ".png"))
					.getImage()
					.getScaledInstance(75, 100, Image.SCALE_DEFAULT));
			
			cards.add(imageIcon);
			miniCards.add(imageIconMini);
		}
		for(int i = 1; i <= CARDS; i++) {
			ImageIcon imageIcon = new ImageIcon(new ImageIcon(this.getClass().getResource("/src/resources/cards/h_" + i + ".png"))
					.getImage()
					.getScaledInstance(150, 200, Image.SCALE_DEFAULT));

			ImageIcon imageIconMini = new ImageIcon(new ImageIcon(this.getClass().getResource("/src/resources/cards/h_" + i + ".png"))
					.getImage()
					.getScaledInstance(75, 100, Image.SCALE_DEFAULT));
			
			cards.add(imageIcon);
			miniCards.add(imageIconMini);
		}
		for(int i = 1; i <= CARDS; i++) {
			ImageIcon imageIcon = new ImageIcon(new ImageIcon(this.getClass().getResource("/src/resources/cards/s_" + i + ".png"))
					.getImage()
					.getScaledInstance(150, 200, Image.SCALE_DEFAULT));

			ImageIcon imageIconMini = new ImageIcon(new ImageIcon(this.getClass().getResource("/src/resources/cards/s_" + i + ".png"))
					.getImage()
					.getScaledInstance(75, 100, Image.SCALE_DEFAULT));
			
			cards.add(imageIcon);
			miniCards.add(imageIconMini);
		}
		

		
	}
	
	public static VisualIcons get() {
		if(me == null)
			me = new VisualIcons();
		
		return me;
	}
	
	public ImageIcon getCardIcon(VisualCard.VisualCardColor type, int value) {
		
		switch(type) {
			case CLUBS:
				return cards.get(value);
			case DIAMONDS:
				return cards.get(CARDS + value);
			case HEARTS:
				return cards.get(2 * CARDS + value);
			case SPADES:
				return cards.get(3 * CARDS + value);
			case NONE:
				return nothing;
			case ARROWS:
				return arrows;
			case BACK:
				return back;
			default:
				return back;
		
		}
		
	}
	
}
