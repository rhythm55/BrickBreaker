package brickBreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {
	//it will contain all bricks in form of array
	public int map[][];
	public int brickwidth;
	public int brickheight;
	public MapGenerator(int row,int col)
	{
		map=new int[row][col];
		for(int i=0;i<map.length;i++)
		{
			for(int j=0;j<map[0].length;j++)
			{
				map[i][j]=1;
				//the purpose of giving 1 to every brick is to know that weather its intersected with ball or not if it is then value will be 0
				// if value will of a brick is 0 it will not displayed
			}
		}
		
		brickwidth=540/col;
		brickheight=150/row;
	}
	
	public void draw(Graphics2D g)
	{
		for(int i=0;i<map.length;i++)
		{
			for(int j=0;j<map[0].length;j++)
			{
				if(map[i][j]>0)
				{
					g.setColor(Color.white);
					g.fillRect(j*brickwidth+80,i*brickheight+50,brickwidth,brickheight);
					
					//for separating each brick with black line boundary
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.black);
					g.drawRect(j*brickwidth+80,i*brickheight+50,brickwidth,brickheight);
				}
			}
		
	    }
	
    }
	public void setBrickValue(int value,int row,int col)
	{
		map[row][col]=value;
	}
}	
