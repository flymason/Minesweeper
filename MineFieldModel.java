import java.util.*;
import java.awt.*;

public class MineFieldModel {

	private Cell[][] model;
	private Stack<Cell> stack;
	
	public MineFieldModel(int xSize, int ySize, int numMines)
	{
		model = new Cell[xSize+2][ySize+2];
		stack = new Stack<Cell>();
		
		int minesLeft = numMines;
		
		Random rand = new Random();
		
		while(minesLeft > 0)		//place mines
		{
			int randX = (rand.nextInt(Integer.MAX_VALUE)) % xSize;
			int randY = (rand.nextInt(Integer.MAX_VALUE)) % ySize;
					
			if(model[randX+1][randY+1] == null)
				{
					model[randX+1][randY+1]= new Mine();
					minesLeft--;
				}
		}
//		for(int i = 0; i < xSize+2; i++)
//		{
//			model[0][i] 		= new NumCell(0);
//			model[xSize+1][i] 	= new NumCell(0);
//			model[i][0] 		= new NumCell(0);
//			model[i][xSize+1] 	= new NumCell(0);
//		}
		
		for(int x = 1; x < xSize+1; x++)	//place numbers and blanks
		{
			for(int y = 1; y < ySize+1; y++)
			{
				if(model[x][y] == null)
				{
					int neighborMines = 0;
					Cell neighbor;
					
					Point[] addresses = new Point[8];
					addresses[0] = new Point(x-1, y-1);
					addresses[1] = new Point(x, y-1);
					addresses[2] = new Point(x+1, y-1);
					addresses[3] = new Point(x+1, y);
					addresses[4] = new Point(x+1, y+1);
					addresses[5] = new Point(x, y+1);
					addresses[6] = new Point(x-1, y+1);
					addresses[7] = new Point(x-1, y);
					for(int p = 0; p < 8; p++)
					{
						try
						{
							neighbor = model[(int)addresses[p].getX()][(int)addresses[p].getY()];
							if(neighbor != null && neighbor.toString().equals("Mine"))
							{
								neighborMines++;
							}
						} catch(ArrayIndexOutOfBoundsException e) {}
					}
					
					model[x][y] = new NumCell(neighborMines);
				}
			}
		}
	}
	
	public Cell getCell(int x, int y)
	{
		return model[x][y];
	}

	public void snake(int x, int y)
	{
		//push cell
		Cell c = model[x][y];
		stack.push(c);
		c.setClicked(false);
		System.out.print(c.toString());
		
		while(!stack.isEmpty())
		{
			if(c == null || c.isClicked())
			{
				c = stack.pop();
				continue;
			}
			if(c.toString().equals("0") && !c.isClicked())	//if it's an empty cell and not clicked
			{
				x = c.getXPos();
				y = c.getYPos();
				stack.push(model[x+1][y]);
				stack.push(model[x+1][y+1]);
				stack.push(model[x][y+1]);
				stack.push(model[x-1][y+1]);
				stack.push(model[x-1][y]);
				stack.push(model[x-1][y-1]);
				stack.push(model[x][y-1]);
				stack.push(model[x+1][y-1]);
				
			}
			if(c.isFlagged() == false)
			{
			c.setClicked(true);
			}
			c = stack.pop();
//			if(c != null)
//			{
//				x = c.getXPos();
//				y = c.getYPos();
//			}
		}
	}
}






















