package brickBreaker;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.Timer;
public class Gameplay extends JPanel implements KeyListener,ActionListener {

	private boolean play=false;
	private int score=0;
	private int totalbricks=21;
	
	//speed on which ball will move with time delay 
	private Timer timer;
	private int delay=8;
	
	private int playerX=310;
	
	//position of slider at initial stage
	private int ballposX=120;
	private int ballposY=350;
	
	//direction of ball at initial stage
	private int ballXdir=-1;
	private int ballYdir=-2;
	
	private MapGenerator map;
	
	public Gameplay()
	{
		map=new MapGenerator(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer=new Timer(delay, this);
		timer.start();
		
	}
	public void paint(Graphics g)
	{
		//add background
		g.setColor(Color.black);
		g.fillRect(1,1,692,592);
		
		//drawing map
		map.draw((Graphics2D)g);
		
		//borders 
		g.setColor(Color.yellow);
		g.fillRect(0,0,3,592);
		g.fillRect(0,0,692,3);
		g.fillRect(691,0,3,592);
		//here no border for down side
		
		//score display
		g.setColor(Color.white);
		g.setFont(new Font("serif",Font.BOLD,25));
		g.drawString("Score:"+score,590,30);
		
		//paddle
		g.setColor(Color.green);
		g.fillRect(playerX,550,100,8);
		
		//ball
		g.setColor(Color.yellow);
		g.fillOval(ballposX,ballposY,20,20);
		// we added variable value as we want to change position according to movement
		
	    //if we ends the game
		if(totalbricks==0)
		{
			play=false;
			ballXdir=0;
			ballYdir=0;
			g.setColor(Color.RED);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("you won",190,300);
			
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("press enter to start",250,350);
		}
		
		//if ball is not touched by slider and games end
		if(ballposY>570){
			play=false;
			ballXdir=0;
			ballYdir=0;
			g.setColor(Color.RED);
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("Game-over,score:"+score,190,300);
			
			
			g.setFont(new Font("serif",Font.BOLD,20));
			g.drawString("press enter to start",250,350);
		}
		
		g.dispose();
	}
	
	
	public void actionPerformed(ActionEvent e) {
		timer.start();
		//movement of ball
		if(play)
		{
			//for detecting the intersection of ball and slider
			if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX,550,100,8)))
			{
				ballYdir=-ballYdir;
			}
			
		A:	for(int i=0;i<map.map.length;i++)
			{
				for(int j=0;j<map.map[0].length;j++){
				if(map.map[i][j]>0)	
				{
					int brickX=j*map.brickwidth+80;
					int brickY=i*map.brickheight+50;
					int brickwidth=map.brickwidth;
					int brickheight=map.brickheight;
					
					Rectangle rect=new Rectangle(brickX,brickY,brickwidth,brickheight);
					Rectangle ballrect=new Rectangle(ballposX,ballposY,20,20);
					Rectangle brickrect=rect;
					
					if(ballrect.intersects(brickrect))
					{
						map.setBrickValue(0,i,j);
						totalbricks--;
						score+=5;
						
						//for left and right intersection
						if(ballposX+19<=brickrect.x||ballposX+1>=brickrect.x+brickrect.width)
						{
							ballXdir=-ballXdir;
						}
						else
						{
							ballYdir=-ballYdir;
						}
						break A;
					}
				}
				}
			}
			//map.map.lenght first map is the declared variable, second one is intialized map in constructor 
			
			ballposX+=ballXdir;
			ballposY+=ballYdir;
			if(ballposX<0)//for left
			{
				ballXdir =-ballXdir;
			}
			if(ballposY<0)//for top
			{
				ballYdir =-ballYdir;
			}
			if(ballposX>670)//for right
			{
				ballXdir =-ballXdir;
			}
		}
		repaint(); //this inbuilt function will call the paint function again so that whenever the player's position is changed all the attributes are changed and we will get to know the occured change
		
	}
	public void keyTyped(KeyEvent e) {
		
		
	}
	public void keyReleased(KeyEvent e) {
		
		
	}
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
		{
			if(playerX>=600)
				{playerX=600;}
			else
				{moveRight();}
		}

	if(e.getKeyCode()==KeyEvent.VK_LEFT)
	{
		if(playerX<10)
			{playerX=10;}
		else
			{moveLeft();}
	}
	
	if(e.getKeyCode()==KeyEvent.VK_ENTER)
	{//for restart
		if(!play)
		{
			play=true;
			ballposX=120;
			ballposY=350;
			ballXdir=-1;
			ballYdir=-2;
			playerX=310;
			score=0;
			totalbricks=21;
			map=new MapGenerator(3,7);
			repaint();
		}
	}
}
 
	public void moveRight()
	{
		play =true; //as it was set to false earlier
		playerX+=20; // move 20 pixels more
	}
	
	public void moveLeft()
	{
		play =true; //as it was set to false earlier
		playerX-=20; // move 20 pixels back
	}
}
