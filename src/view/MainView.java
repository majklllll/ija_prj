package src.view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import java.util.ArrayList;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.view.VisualBoard;
import src.controler.CommandBuilder;
import src.controler.ICommand;
import src.model.BoardModel;


public class MainView extends JFrame{
	public static final int BOARD_LIMIT           = 4;
	protected ArrayList<VisualBoard> boards       = new ArrayList<VisualBoard>(); 

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
		getContentPane().setLayout(new GridLayout(1, 1, 0, 0));
		
		

		
	}
	
	/* Create new board and add it */
	public void addBoard() {
		if(boards.size() < BOARD_LIMIT) {
			if(boards.size() == 1) {
				//change layout to grid 2x2
				getContentPane().setLayout(new GridLayout(2, 2, 0, 0));
			}
			
			
			
			BoardModel boardModel = new BoardModel();
			VisualBoard board = new VisualBoard(boardModel);
			getContentPane().add(board);
			boards.add(board);
			
			//System.out.println(getContentPane().getSize());
			
		}	
		
	}

	public void removeBoard(int number) {
		if(boards.size() == 2) {
			//change grid to 1x1
			getContentPane().setLayout(new GridLayout(1, 1, 0, 0));
		}
		
		
		boards.remove(number);
	}
	
	public VisualBoard getBoard(int number) {
		return boards.get(number);
		
	}
	

	
}



