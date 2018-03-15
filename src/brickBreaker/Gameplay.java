package brickBreaker;
import java.awt.Graphics;
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
	private int totalbocks=21;
	
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
	
	public Gameplay()
	{
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
		
		//borders 
		g.setColor(Color.yellow);
		g.fillRect(0,0,3,592);
		g.fillRect(0,0,692,3);
		g.fillRect(691,0,3,592);
		//here no border for down side
		
		//paddle
		g.setColor(Color.green);
		g.fillRect(playerX,550,100,8);
		
		//ball
		g.setColor(Color.yellow);
		g.fillOval(ballposX,ballposY,20,20);
		// we added variable value as we want to change position according to movement
		
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
