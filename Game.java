import javax.swing.*;
import javax.swing.border.Border;

import java.awt.event.*;
import java.awt.*;

public class Game extends JFrame implements ActionListener, MouseListener{

	
	private MineField mineField;
	private JPanel gameArea;
	private JPanel smilyBar;
	public SmilyButton smilyButton;
	private int numRows;
	private int numCol;
	private int numMines;
	private boolean mousePressed;
	
	public Game()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Minesweeper");
		setLayout(new BorderLayout());
		mousePressed = false;
		numRows = 16;
		numCol = 16;
		numMines = 40;
		
		smilyButton = new SmilyButton();
		smilyButton.addMouseListener(this);
		
		mineField = new MineField(numCol, numRows, numMines, smilyButton);
		gameArea = new JPanel();
		smilyBar = new JPanel();
		
		//smilyButton.setAlignmentX(CENTER_ALIGNMENT);
		
		add(mkTopMenu(), BorderLayout.NORTH);
		
		smilyBar.add(Box.createHorizontalGlue());
		smilyBar.add(smilyButton);
		smilyBar.setBorder(BorderFactory.createRaisedBevelBorder());
		gameArea.setLayout(new BorderLayout());
		gameArea.add(smilyBar, BorderLayout.NORTH);
		gameArea.add(mineField, BorderLayout.CENTER);
		
		add(gameArea, BorderLayout.CENTER);
		pack();
		
		Dimension		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((screenSize.width - getWidth()) / 2, (screenSize.height - getHeight()) / 2 );
		setVisible(true);
	}
	
	public JPanel mkTopMenu()
	{
		JPanel		panel = new JPanel(new BorderLayout());
		
		JMenu		gameMenu = new JMenu("Game");
		JMenu		sizeMenu = new JMenu("Size");
		
		JMenuItem newGame = new JMenuItem("New Game");			//File items
		newGame.addActionListener(this);
		newGame.setMnemonic('N');
		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(this);
		exit.setMnemonic('x');
		
		JMenuItem beg = new JMenuItem("Beginner");			//File items
		beg.addActionListener(this);
		beg.setMnemonic('B');
		JMenuItem inter = new JMenuItem("Intermediate");			//File items
		inter.addActionListener(this);
		inter.setMnemonic('I');
		JMenuItem exp = new JMenuItem("Expert");			//File items
		exp.addActionListener(this);
		exp.setMnemonic('E');
		
		gameMenu.add(newGame);
		gameMenu.add(exit);
		
		sizeMenu.add(beg);
		sizeMenu.add(inter);
		sizeMenu.add(exp);
	
		JMenuBar	mBar = new JMenuBar();
		mBar.add(gameMenu);
		mBar.add(sizeMenu);
		
		panel.add(mBar, BorderLayout.NORTH);
		return panel;
	}
	
	public void actionPerformed(ActionEvent e)		// JButtons
	{
		String command = e.getActionCommand();

		

		if (command.equals("New Game"))
		{
			startNewGame();
		}
		else if(command.equals("Beginner"))
		{
			numRows = 9;
			numCol = 9;
			numMines = 10;
			startNewGame();
		}
		else if(command.equals("Intermediate"))
		{
			numRows = 16;
			numCol = 16;
			numMines = 40;
			startNewGame();
		}
		else if(command.equals("Expert"))
		{
			numRows = 30;
			numCol = 16;
			numMines = 99;
			startNewGame();
		}
		else if(command.equals("Exit"))
		{
			System.exit(0);
		}
			
	}
	
	public void startNewGame()
	{
		gameArea.remove(mineField);
		mineField = new MineField(numRows, numCol, numMines, smilyButton);
		gameArea.add(mineField, BorderLayout.CENTER);
		smilyButton.reset();
		pack();
		repaint();
		setVisible(true);
	}
	
	public void mouseClicked(MouseEvent e) 
	{
		
	}

	public void mousePressed(MouseEvent e)
	{
		smilyButton.setPressed(true);
		mousePressed = true;
		repaint();
	}

	public void mouseReleased(MouseEvent e)
	{
		if (smilyButton.isPressed())
		{
			startNewGame();
		}
		mousePressed = false;
		smilyButton.setPressed(false);
	}
	public void mouseEntered(MouseEvent e){
		if(mousePressed)
		{
			smilyButton.setPressed(true);
		}
	}
	public void mouseExited(MouseEvent e) {
			smilyButton.setPressed(false);
	} 
	
	public static void main(String[] args)
	{
		new Game();
	}
	
}
