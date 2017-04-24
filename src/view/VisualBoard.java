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
import javax.swing.JOptionPane;

public class VisualBoard extends JPanel implements ISupportRepaint {
	private IGameBoard boardModel;
	private CommandBuilder commander;
	
	int panelPosX = 0, panelPosY = 0; 
	int panelWidth = 1280, panelHeight = 700;	
	
	VisualCardPack picker;

	
	ArrayList<VisualCardDeck> decks = new ArrayList<VisualCardDeck>();
	ArrayList<VisualCardStack> stacks = new ArrayList<VisualCardStack>();
	
	
	VisualBoard(IGameBoard bModel) {
		this.setLayout(null);
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		boardModel = bModel;
		
		
        commander = new CommandBuilder(bModel);
        bModel.registerObserver((ISupportRepaint)this);
        this.paintAll();
		
	}
	
	public void paintAll() {

		
		//initialize cards
		
		int cardSpace = panelWidth / 8;
		
		
		VisualCardPack packPicker = new VisualCardPack();
		packPicker.setModel(boardModel.getRepository());
		packPicker.setXY(cardSpace * (1), (panelHeight / 10)  );
		packPicker.setPanel(this);
		packPicker.paint();
		picker = packPicker;	
	
		
		
		for(int i = 0; i < 7; i++) {
			VisualCardStack stack = new VisualCardStack();
			stack.setModel(boardModel.getStack(i));
			stack.setXY(cardSpace * (i+1), (panelHeight / 2 - 60)  );
			stack.setPanel(this);
			stack.paint();
			stacks.add(stack);
			
		}

		
		for(int i = 0; i < 4; i++) {
			
			VisualCardDeck deck = new VisualCardDeck();
			deck.setModel(boardModel.getDeck(i));
			deck.setXY(cardSpace * (i+4), (panelHeight / 10)  );
			deck.setPanel(this);
			deck.paint();
			decks.add(deck);
		}
		
		//initialize buttons
		JButton btnUndo = new JButton("Undo");
		btnUndo.setBounds(0, 5, 100, 40);
		this.add(btnUndo);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds(150, 5, 100, 40);
		this.add(btnSave);
		
		JButton btnLoad= new JButton("Load");
		btnLoad.setBounds(300, 5, 100, 40);
		this.add(btnLoad);		
		
		JButton btnClose= new JButton("Close");
		btnClose.setBounds(450, 5, 100, 40);
		this.add(btnClose);		
		

		/*
		 * 
		 *  //add event handler - turn pack
			card.addMouseListener(new MouseAdapter()  
			{  
			    public void mouseClicked(MouseEvent e)  
			    {  
			    	ICommand command = new CommandRenew(pack);
			    	board.getCommandBuilder().execute(command);
			    }  
			}); 
		 */
		
		
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
		        //your actions
		    }
		});
		//add event handlers
		btnClose.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        //your actions
		    }
		});
		
		
		/*JButton btnNewGame = new JButton("New game");
		btnNewGame.setBounds(800, 5, 100, 50);
		this.add(btnNewGame);
		
		//add event handlers
		btnNewGame.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        //your actions
		    }
		});*/
		
		

	}
	
	public void repaint() {
		System.out.println("zadost o repaint");
		this.removeAll();
		if(this.boardModel != null)
			this.paintAll();
		
		super.repaint();
		this.revalidate();
		
	}
	
	
	public CommandBuilder getCommandBuilder(){
		return commander;
		
	}
	
	
	/*public void paintCardRepo() {
		
		
	}
	public void paintCardRepoSec() {
		
		
	}
	public void paintStack(int stackNumber) {
		
		
	}
	public void paintDeck(int deckNumber) {
		
		
	}	*/
	
}
