/**
 * This file contains class MainView
 * @author      Jan Hrstka, Michal Peška
 */

package src.view;

import java.awt.EventQueue;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.ArrayList;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import src.view.VisualBoard;
import src.model.BoardModel;

/**
*   This class represents GUI window 
*/
public class MainView extends JFrame{
	public static final int BOARD_LIMIT           = 4;
	protected ArrayList<VisualBoard> boards       = new ArrayList<VisualBoard>(); 
	private JPanel bottomPanel;
	
	private GridLayout layoutFull;
	private GridLayout layout4Tiles;
	
	
	/**
	 * Launch the application.
	 * @param  args arguments.
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
		setBounds(100, 100, 1280, 750);
		getContentPane().setLayout(null);
		
		initLayouts();
		
		JPanel top = new JPanel(layoutFull);
		top.setBounds(0, 0, 1280, 25);
		
		JPanel bottom = new JPanel(layoutFull);
		bottom.setBounds(0, 25, 1280, 660);
		
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
	
	/**
	 * Creates new board and add it to window
	 */
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
	
	/**
	 * Removes existing board from window
	 * @param  board board.
	 */
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
	
	/**
	 * Method returns board of specific number
	 * @param  number number of board.
	 * @return visual board.
	 */	
	public VisualBoard getBoard(int number) {
		return boards.get(number);
		
	}
	
	/**
	 * Method force window to repaint
	 */	
	public void forceRepaint(){
		this.repaint();
		this.revalidate();	
	}
	
	/**
	 * Initializes default layouts used in whole app
	 */	
	private void initLayouts() {
		layoutFull = new GridLayout(1, 1, 0, 0);
		layout4Tiles = new GridLayout(2, 2, 0, 0);
	}
	
	/**
	 * Method changes layout of whole window
	 * @param  layout layout to set.
	 */	
	public void changeLayout(GridLayout layout){
		this.bottomPanel.setLayout(layout);
		
		//inform icon provider about this change
		VisualIcons.get().setUsingMiniatures((layout == this.layout4Tiles));
	}
}