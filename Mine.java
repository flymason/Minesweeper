import java.awt.*;

public class Mine extends Cell{

	private boolean exploded;
	public Mine()
	{
		exploded = false;
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if (exploded)
		{
			g.setColor(Color.RED);
			g.fillRect(1, 1, 18, 18);
		}
		if(reveal && !flagged)
		{
			g.setColor(Color.BLACK);
			g.fillOval(5, 5, 10, 10);
			g.drawLine(10, 4, 10, 17);
			g.drawLine(4, 10, 17, 10);
			g.drawLine(5, 5, 6, 6);	//top left
			g.drawLine(15, 6, 14, 7);	//top right
			g.drawLine(14, 14, 15, 15);	//bottom right
			g.drawLine(6, 15, 7, 14);	//bottom left
			
			g.setColor(Color.WHITE);
			g.fillOval(7, 7, 3, 3);
		}
	}
	public String toString()
	{
		return "Mine";
	}
	public void explode()
	{
		exploded = true;
	}
}
	










