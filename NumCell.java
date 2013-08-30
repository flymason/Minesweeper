import java.awt.*;

public class NumCell extends Cell
{
	private int value;
	
	public NumCell(int value)
	{
		this.value = value;
	}
	public String toString()
	{
		return value + "";
	}
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(clicked && !flagged)
		{
			switch(value)
			{
				case 1 :
					g.setColor(Color.BLUE);
					break;
				case 2 :
					g.setColor(Color.decode("#1E7514"));
					break;
				case 3 :
					g.setColor(Color.RED);
					break;
				case 4 :
					g.setColor(Color.decode("#14176E"));
					break;
				case 5 :
					g.setColor(Color.decode("#8A172C"));
					break;
				case 6 :
					g.setColor(Color.decode("#0CA0B0"));
					break;
				case 7 :
					g.setColor(Color.BLACK);
					break;
				case 8 :
					g.setColor(Color.BLACK);
					break;
			}
			
			if(value != 0)
			{
				Font	f = new Font("DialogInput", Font.BOLD, 18);
				g.setFont(f);
				g.drawString(value + "", 6, 16);
			}
		}
		if(reveal && flagged)	//if it's a falsely flagged mine
		{
			g.setColor(Color.BLACK);	//draw mine
			g.fillOval(5, 5, 10, 10);
			g.drawLine(10, 4, 10, 17);
			g.drawLine(4, 10, 17, 10);
			g.drawLine(5, 5, 6, 6);		//top left
			g.drawLine(15, 6, 14, 7);	//top right
			g.drawLine(14, 14, 15, 15);	//bottom right
			g.drawLine(6, 15, 7, 14);	//bottom left
			
			g.setColor(Color.WHITE);	//draw glint
			g.fillOval(7, 7, 3, 3);
			
			g.setColor(Color.RED);		//draw X
			g.drawLine(2, 2, 18, 18);
			g.drawLine(18, 2, 2, 18);
		}
			
		
	}
}
