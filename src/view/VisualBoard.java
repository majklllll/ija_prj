package src.view;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import src.model.BoardModel;
import src.controler.CommandBuilder;
import src.controler.CommandRenew;
import src.controler.ControlCommand;
import src.controler.ICommand;
import src.share.*;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FilenameFilter;

public class VisualBoard extends JPanel implements ISupportRepaint {
	private IGameBoard boardModel;
	private CommandBuilder commander;
	private VisualCardPack picker;
	private ArrayList<VisualCardDeck> decks = new ArrayList<VisualCardDeck>();
	private ArrayList<VisualCardStack> stacks = new ArrayList<VisualCardStack>();
	private ArrayList<VisualCard> hintTargets = new ArrayList<VisualCard>();
	private ICardDeck selectedSource = null;
	private ICard selectedMultiMoveCard = null;
	private VisualCard selectedSourceCard = null;
	private boolean hintRequested = false;
	
	VisualBoard(IGameBoard bModel) {
		this.setLayout(null);
		//this.setBorder(new EmptyBorder(5, 5, 5, 5));
		boardModel = bModel;	
        commander = new CommandBuilder(bModel);
        bModel.registerObserver((ISupportRepaint)this);
        this.paintAll();
	}
	
	public void paintAll() {
		//initialize buttons
		JButton btnUndo = new JButton("Undo");
		btnUndo.setBounds(0, 5, 100, 25);
		this.add(btnUndo);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(110, 5, 100, 25);
		this.add(btnSave);
		
		JButton btnLoad= new JButton("Load");
		btnLoad.setBounds(220, 5, 100, 25);
		this.add(btnLoad);		
		
		JButton btnClose= new JButton("Close");
		btnClose.setBounds(330, 5, 100, 25);
		this.add(btnClose);	

		JButton btnHint= new JButton("Hint:Off");
		btnHint.setBounds(440, 5, 100, 25);
		this.add(btnHint);		
		
		//initialize cards
		int basicValue = this.getHeight();
		int cardSpace = (int)(basicValue / 4.4);
		
		//add card picker and repository pack
		VisualCardPack packPicker = new VisualCardPack();
		packPicker.setModel(boardModel.getRepository());
		packPicker.setXY(cardSpace * (1), /*(basicValue / 20)*/ 35  );
		packPicker.setPanel(this);
		packPicker.paint();
		packPicker.setName("r1");
		picker = packPicker;	
		
		//add stack
		for(int i = 0; i < 7; i++) {
			VisualCardStack stack = new VisualCardStack();
			stack.setModel(boardModel.getStack(i));
			stack.setXY(cardSpace * (i+1), (int)(basicValue / 2.4 )  );
			stack.setPanel(this);
			stack.paint();
			stack.setName("s" + (i + 1));
			stacks.add(stack);
		}

		//add 4 decks for used cards
		for(int i = 0; i < 4; i++) {	
			VisualCardDeck deck = new VisualCardDeck();
			deck.setModel(boardModel.getDeck(i));
			deck.setXY(cardSpace * (i+4), /*(basicValue / 20)*/ 35  );
			deck.setPanel(this);
			deck.paint();
			deck.setName("d" + (i + 1));
			decks.add(deck);
		}
		
		//add event handlers
		btnUndo.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	ICommand command = new ControlCommand("undo");
		    	getCommandBuilder().execute(command);
		    }
		});

		//add event handlers
		btnSave.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
				final String SAVE_DIRECTORY = "saves";
		        String fileName = JOptionPane.showInputDialog(null, "Enter file name:", "Dialog for file name", JOptionPane.WARNING_MESSAGE);
		        if(fileName != null && fileName.length() > 0){
		        	ICommand command = new ControlCommand("save", new ArrayList<String>(){{add(SAVE_DIRECTORY +  "/" + fileName);}});
		        	getCommandBuilder().execute(command);
		        }
		    }
		});

		//add event handlers
		btnLoad.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        loadFile();
		    }
		});

		//add event handlers
		btnClose.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	closeThisBoard();
		    }
		});

		btnHint.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(hintRequested){
					hintRequested = false;
					clearHints();
					btnHint.setText("Hint:Off");
				}
				else{
					hintRequested = true;
					btnHint.setText("Hint:On");
					setSelectedMoveSource(selectedSource, selectedSourceCard);
				}
			}
		});
		

	}
	public void paintGameOver(){
		JButton btnClose= new JButton("Game Over - You have won");
		btnClose.setBounds(250, 30, 200, 80);
		this.add(btnClose);	
		
		//add event handlers
		btnClose.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	closeThisBoard();
		    }
		});
	}	
	
	public void repaint() {
		removeListeners();		
		this.removeAll();
		
		// Check if game is over
		if(this.boardModel != null){
			if(this.boardModel.isGameOver()){
				paintGameOver();
			}else{
				// Repaint board
				this.paintAll();
			}
		}

		// Repaint GUI
		super.repaint();
		this.revalidate();
	}
	
	public void removeListeners(){	
		Component[] components = this.getComponents();
		for(Component component: components){
			
			//System.out.println(
			MouseListener[] listeners = component.getMouseListeners();
			for(MouseListener listener : listeners){
				this.removeMouseListener(listener);
				
			}	
		}
	}
	
	public CommandBuilder getCommandBuilder(){
		return commander;	
	}
	
	public void unselectedMoveSource(){
		this.setSelectedMoveSource(null, null);		
	}

	public void setSelectedMoveSource(ICardDeck deck, VisualCard sourceCard){
		if(this.selectedSourceCard != null)
			this.selectedSourceCard.setSelected(false);	
		this.selectedSource = deck;		
		this.selectedSourceCard = sourceCard;
		this.setMultiMoveCard(null);

		if(sourceCard != null){
			sourceCard.setSelected(true);
			if(this.hintRequested)
				this.createHints();
		}
	}
	private void createHints(){
		ICardHint hint = this.boardModel.hintForCard(this.selectedSourceCard.toCardModel());
		this.clearHints();
		for(ICardDeck deck : hint.getCardDecks()){
			for(VisualCardDeck visualCardDeck : this.decks){
				if(deck.getName().equals(visualCardDeck.getName())){
					VisualCard target = visualCardDeck.top();
					target.setHintTarget(true);
					this.hintTargets.add(target);
				}
			}
		}

		for(ICardStack stack : hint.getCardStacks()){
			for(VisualCardStack visualCardStack : this.stacks){
				if(stack.getName().equals(visualCardStack.getName())){
					VisualCard target = visualCardStack.top();
					target.setHintTarget(true);
					this.hintTargets.add(target);
				}
			}
		}
	}

	private void clearHints(){
		while(!this.hintTargets.isEmpty()){
			this.hintTargets.remove(0).setHintTarget(false);
		}
	}

	public ICardDeck getSelectedMoveSource(){
		return this.selectedSource;		
	}
	
	public boolean isMoveSourceSelected() {
		return this.selectedSource != null;				
	}
	
	public ICard getMultiMoveCard(){
		return this.selectedMultiMoveCard;		
	}
	
	public void setMultiMoveCard(ICard card) {
		this.selectedMultiMoveCard = card;				
	}
	
	public void closeThisBoard(){
		MainView window = (MainView)this.getTopLevelAncestor();
		window.removeBoard(this);
	}
	
	public void loadFile(){
		// Load list of files in directory "saved"
		// If this pathname does not denote a directory, then listFiles() returns null.
		final String LOAD_DIR = "saves";
		ArrayList<String> filesString = new ArrayList<String>();
		File[] listOfFiles = new File(LOAD_DIR).listFiles(new FilenameFilter() { 
	            public boolean accept(File dir, String filename)
	            {
	            	return filename.endsWith(".board"); 
	            }
			} );

		// Inform user when there is not any saved game.
		if(listOfFiles == null || listOfFiles.length <= 0){
			JOptionPane.showMessageDialog(null, "No saved game found.", "There are no saved games.", JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        filesString.add(file.getName().replaceAll("\\.board$", ""));
		    }
		}		
		
		// Convert arraylist to array
		Object[] fileNamesArray = filesString.toArray(new Object[filesString.size()]);
		String fileToLoad = (String)JOptionPane.showInputDialog(
		                    this,
		                    "Select file to load,\n" +
		                    "Here are your choices:",
		                    "Load dialog",
		                    JOptionPane.PLAIN_MESSAGE,
		                    null,
		                    fileNamesArray,
		                    "loadDialog");

		// If a string was returned, execute load command
		if (fileToLoad != null && fileToLoad.length() > 0) {
			ICommand command = new ControlCommand("load", new ArrayList<String>(){{add(LOAD_DIR + "/" + fileToLoad);}});
			this.getCommandBuilder().execute(command);
		}		
	}
}
