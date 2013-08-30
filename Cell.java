
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;

abstract public class Cell extends JPanel{

	protected boolean pressed;
	protected boolean clicked;
	protected boolean reveal;
	protected boolean flagged;
	private int xPos;
	private int yPos;
	
	public Cell()
	{		
		setSize(new Dimension(20, 20));
		setOpaque(false);
		pressed = false;
		clicked = false;
		reveal = false;
		flagged = false;
		
		repaint();
	}	
	
	public void paintComponent(Graphics g)
	{
		if(clicked || pressed)
		{
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(0, 0, 20, 20);
			g.setColor(Color.GRAY);
			g.drawRect(0, 0, 20, 20);
		}	
		else	//if not pressed
		{
			g.setColor(Color.LIGHT_GRAY);
			g.fillRect(0, 0, 20, 20);
			
			g.setColor(Color.WHITE);
			int[] x = {0, 20, 18, 0};
			int[] y = {0, 0, 2, 2};
			g.fillPolygon(x, y, 4);
			
			int[] x2 = {0, 0, 2, 2};
			int[] y2 = {0, 20, 18, 0};
			g.fillPolygon(x2, y2, 4);
			
			g.setColor(Color.GRAY);
			int[] x3 = {18, 20, 20, 18};
			int[] y3 = {2, 0, 20, 20};
			g.fillPolygon(x3, y3, 4);
			
			int[] x4 = {0, 2, 20, 20};
			int[] y4 = {20, 18, 18, 20};
			g.fillPolygon(x4, y4, 4);
			
			if(flagged)
			{
				g.setColor(Color.RED);
				int[] x5 = {12, 12, 6};
				int[] y5 = {4, 12, 9};
				g.fillPolygon(x5, y5, 3);
				g.setColor(Color.BLACK);
				int[] x6 = {4, 11, 16};
				int[] y6 = {16, 12, 16};
				g.fillPolygon(x6, y6, 3);
			}
		}
	}
	
	
	public void reveal()
	{
		if(toString().equals("Mine") && flagged)	//if it's a correctly flagged mine, do nothing
		{}
		else if(toString().equals("Mine") || flagged)	//if it is a mine or is an incorrectly flagged cell
		{
			clicked = true;
			reveal = true;
		}

		
		repaint();
	}
	public void setPressed(boolean p)
	{
		if(!flagged)
		{
		pressed = p;
		repaint();
		}
	}
	public void setClicked(boolean c)
	{
		clicked = c;
		repaint();
	}
	public boolean isPressed()
	{
		return pressed;
	}
	public boolean isClicked()
	{
		return clicked;
	}
	public void setPosition(int x, int y)
	{
		xPos = x+1;
		yPos = y+1;
	}
	public int getXPos()
	{
		return xPos-1;
	}
	public int getYPos()
	{
		return yPos-1;
	}
	public void toggleFlag()
	{
		if(flagged == true)
			flagged = false;
		else
			flagged = true;
		
		repaint();
	}
	public boolean isFlagged()
	{
		return flagged;
	}

}

















