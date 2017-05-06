package src.view;

//import java.awt.BorderLayout;
import java.awt.EventQueue;
//import java.awt.Color;

//import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

//import javafx.scene.paint.Color;

import java.util.ArrayList;

import java.awt.GridLayout;
//import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.view.VisualBoard;
//import src.controler.CommandBuilder;
//import src.controler.ICommand;
import src.model.BoardModel;


public class MainView extends JFrame{
	public static final int BOARD_LIMIT           = 4;
	protected ArrayList<VisualBoard> boards       = new ArrayList<VisualBoard>(); 
	private JPanel bottomPanel;
	private GridLayout layoutFull;
	private GridLayout layout4Tiles;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView frame = new MainView();
					frame.setVisible(true);
					frame.addBoard();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 700);
		getContentPane().setLayout(null);
		
		initLayouts();
		
		JPanel top = new JPanel(layoutFull);
		top.setBounds(0, 0, 1280, 25);
		
		JPanel bottom = new JPanel(layoutFull);
		bottom.setBounds(0, 25, 1500, 660);
		
		getContentPane().add(top);
		getContentPane().add(bottom);
		
		this.bottomPanel = bottom;
		
		JButton btnNewGame = new JButton("Add new game");
		top.add(btnNewGame);
		
		//add event handlers
		btnNewGame.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        //your actions
		    	addBoard();
		    }
		});	
	}
	
	/* Create new board and add it */
	public void addBoard() {
		if(boards.size() < BOARD_LIMIT) {
			if(boards.size() == 1) {
				//change layout to grid 2x2
				this.changeLayout(layout4Tiles);
				
			}
			
			BoardModel boardModel = new BoardModel();
			VisualBoard board = new VisualBoard(boardModel);
			this.bottomPanel.add(board);
			boards.add(board);
			this.forceRepaint();
		}	
	}

	public void removeBoard(VisualBoard board) {
		if(boards.size() == 2) {
			//change grid to 1x1
			this.changeLayout(layoutFull);
		}
		
		//remove from the list
		boards.remove(board);
		
		//remove from GUI
		board.removeAll();
		this.bottomPanel.remove(board);
		forceRepaint();
		
		System.out.println(boards.size());
	}
	
	public VisualBoard getBoard(int number) {
		return boards.get(number);
		
	}
	
	public void forceRepaint(){
		this.repaint();
		this.revalidate();	
	}
	
	private void initLayouts() {
		layoutFull = new GridLayout(1, 1, 0, 0);
		layout4Tiles = new GridLayout(2, 2, 0, 0);
	}
	
	public void changeLayout(GridLayout layout){
		this.bottomPanel.setLayout(layout);
		
		//inform icon provider about this change
		VisualIcons.get().setUsingMiniatures((layout == this.layout4Tiles));
	}
}