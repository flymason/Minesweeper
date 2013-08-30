import java.awt.event.*;
import javax.swing.*;
import java.awt.*;


public class MineField extends JPanel implements MouseListener{

	
	
	private MineFieldModel field;
	private int numRows;
	private int numCol;
	private boolean gameOver;
	private boolean mouse1Pressed;
	private boolean mouse3Pressed;
	private SmilyButton smilyButton;
	
	public MineField(int numCol, int numRows, int numMines, SmilyButton sb)
	{
		this.numCol = numCol;
		this.numRows = numRows;
		smilyButton = sb;
		gameOver = false;
		
		setPreferredSize(new Dimension(numCol * 20, numRows * 20));
		setLayout(null);
		
		
		field = new MineFieldModel(numCol, numRows, numMines);
		
		Cell c;
		for(int x = 1; x < numCol+1; x++)
		{
			for(int y = 1; y < numRows+1; y++)
			{
				c = field.getCell(x, y);
				c.setPosition(x, y);
				c.addMouseListener(this);
				c.setLocation((x-1) * 20, (y-1) * 20);
				add(c);
			}
		}
	}
	
	
	
	public void mouseClicked(MouseEvent e) 
	{
		
	}

	public void mousePressed(MouseEvent e)
	{
		Cell source = (Cell) e.getComponent();
		if(gameOver)
		{
			return;
		}
		if(e.getModifiers() == InputEvent.BUTTON1_MASK)
		{
			//mouse1Pressed = true;
		}
		if(e.getModifiers() == InputEvent.BUTTON3_MASK)
		{
			//mouse3Pressed = true;
		}
		if(mouse1Pressed && mouse3Pressed)
		{
			//pressNeighbors(source, true);
		}
		
		else if(!source.isFlagged() && e.getModifiers() == InputEvent.BUTTON1_MASK)	//if cell is not flagged
		{
			Cell c = (Cell) e.getComponent();
			c.setPressed(true);
			if(!c.isClicked())
			{
				smilyButton.setMouthOpen(true);
			}
		}
	}

	public void mouseReleased(MouseEvent e)
	{
		Cell source = (Cell) e.getComponent();
		if(gameOver || source.isClicked())
		{
			return;
		}
		
		if(e.getModifiers() == InputEvent.BUTTON3_MASK)		//if it's button 3
		{
			mouse3Pressed = false;
			if(mouse1Pressed)
			{
				//pressNeighbors(source, false);
				
			}
			else
			{
			source.toggleFlag();
			}
		}
		if(e.getModifiers() == InputEvent.BUTTON1_MASK)
		{
			mouse1Pressed = false;
			smilyButton.setMouthOpen(false);
			//if(mouse3Pressed)
			{
				//pressNeighbors(source, false);
				
			}
			//else
			{
				if(source.isPressed())
				{
					if(!source.isFlagged())
					{
						source.setPressed(false);
						source.setClicked(true);
						Cell c;
						
						if(source.toString().equals("Mine"))
						{
							Mine mine = (Mine) source;
							mine.explode();
							gameOver = true;
							smilyButton.killSmiley();
							for(int x = 1; x < numCol+1; x++)
							{
								for(int y = 1; y< numRows+1; y++)
								{
									c = field.getCell(x, y);
									c.reveal();
								}
							}
						}
						else 
						{
							if(source.toString().equals("0"))
							{
								field.snake(source.getXPos(), source.getYPos());
							}
							gameOver = true;	//true until proven false
							for(int x = 1; x < numCol+1; x++)
							{
								for(int y = 1; y< numRows+1; y++)
								{
									c = field.getCell(x, y);
									if(!c.isClicked() && !c.toString().equals("Mine"))	//if everyone is not clicked and it's not a mine
									{
										gameOver = false;
									}
								}
							}
							if(gameOver)
							{
								smilyButton.putGlassesOn();
							}
						}
					}
				}
			}
		}
		repaint();
	}
	
	public void mouseEntered(MouseEvent e)
	{}
	public void mouseExited(MouseEvent e) {
		Cell source = (Cell) e.getComponent();
		if(gameOver || source.isPressed())
		{
			source.setPressed(false);
			smilyButton.setMouthOpen(false);
		}
		if(mouse1Pressed && mouse3Pressed)
		{
			pressNeighbors(source, false);
		}
	}
	public void pressNeighbors(Cell c, boolean p)
	{
		int x = c.getXPos();
		int y = c.getYPos();
		field.getCell(x+1, y+1).setPressed(p);
		field.getCell(x+1, y).setPressed(p);
		field.getCell(x+1, y-1).setPressed(p);
		field.getCell(x, y+1).setPressed(p);
		field.getCell(x, y-1).setPressed(p);
		field.getCell(x-1, y+1).setPressed(p);
		field.getCell(x-1, y).setPressed(p);
		field.getCell(x-1, y-1).setPressed(p);
		field.getCell(x, y).setPressed(p);
		repaint();
	}

	
	
	
	

}
