package src.view;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;

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

import javax.swing.JOptionPane;

public class VisualBoard extends JPanel implements ISupportRepaint {
	private IGameBoard boardModel;
	private CommandBuilder commander;
	
	private VisualCardPack picker;

	private ArrayList<VisualCardDeck> decks = new ArrayList<VisualCardDeck>();
	private ArrayList<VisualCardStack> stacks = new ArrayList<VisualCardStack>();
	
	private ICardDeck selectedSource;
	private ICard selectedMultiMoveCard;
	
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
		btnSave.setBounds(150, 5, 100, 25);
		this.add(btnSave);
		
		JButton btnLoad= new JButton("Load");
		btnLoad.setBounds(300, 5, 100, 25);
		this.add(btnLoad);		
		
		JButton btnClose= new JButton("Close");
		btnClose.setBounds(450, 5, 100, 25);
		this.add(btnClose);	
		
		
		//initialize cards
		int basicValue = this.getHeight();
		
		System.out.println(basicValue / 20);
		
		
		int cardSpace = (int)(basicValue / 4.4);
		
		//add card picker and repository pack
		VisualCardPack packPicker = new VisualCardPack();
		packPicker.setModel(boardModel.getRepository());
		packPicker.setXY(cardSpace * (1), /*(basicValue / 20)*/ 35  );
		packPicker.setPanel(this);
		packPicker.paint();
		picker = packPicker;	
	
		
		//add stack
		for(int i = 0; i < 7; i++) {
			VisualCardStack stack = new VisualCardStack();
			stack.setModel(boardModel.getStack(i));
			stack.setXY(cardSpace * (i+1), (int)(basicValue / 2.4 )  );
			stack.setPanel(this);
			stack.paint();
			stacks.add(stack);
			
		}

		//add 4 decks for used cards
		for(int i = 0; i < 4; i++) {
			
			VisualCardDeck deck = new VisualCardDeck();
			deck.setModel(boardModel.getDeck(i));
			deck.setXY(cardSpace * (i+4), /*(basicValue / 20)*/ 35  );
			deck.setPanel(this);
			deck.paint();
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
		        String fileName = JOptionPane.showInputDialog(null, "Enter file name:", "Dialog for file name", JOptionPane.WARNING_MESSAGE);
		        if(fileName.length() > 0){
		        	ICommand command = new ControlCommand("save", new ArrayList<String>(){{add(fileName);}});
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
		        //your actions
		    	closeThisBoard();
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
		        //your actions
		    	closeThisBoard();
		    }
		});
	}
	
	
	public void repaint() {
		removeListeners();	
		
		
		//System.out.println("Prekresleni boardu ");
		this.removeAll();
		
		//check if game is over
		if(this.boardModel != null){
			if(this.boardModel.isGameOver()){
				paintGameOver();
			}else{
				//repaint board
				this.paintAll();
			}
		}
		//repaint GUI
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
	
	public void setSelectedMoveSource(ICardDeck deck){
		this.selectedSource = deck;		
			
	}
	public ICardDeck getSelectedMoveSource(){
		return this.selectedSource;		
	}
	
	public boolean isMoveSourceSelected() {
		return (this.selectedSource != null);				
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
		//load list of files in directory "saved"
		final String LOAD_DIR = "saved";
		ArrayList<String> filesString = new ArrayList<String>();

		File[] listOfFiles = new File(LOAD_DIR).listFiles(new FilenameFilter() { 
	            public boolean accept(File dir, String filename)
	            {
	            	return filename.endsWith(".board"); 
	            }
			} );
		//If this pathname does not denote a directory, then listFiles() returns null. 

		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        filesString.add(file.getName().replaceAll("\\.board$", ""));
		    }
		}		
		
		//convert arraylist to array
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

		//If a string was returned, execute load command
		if ((fileToLoad != null) && (fileToLoad.length() > 0)) {
			//try to load file
			ICommand command = new ControlCommand("load", new ArrayList<String>(){{add(LOAD_DIR + "/" + fileToLoad);}});
			this.getCommandBuilder().execute(command);
		}
		//System.out.println(possibilities);
		
	}
	
	
}
