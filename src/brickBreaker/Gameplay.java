package brickBreaker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import java.util.Timer;
public class Gameplay extends JPanel implements KeyListener,ActionListener {

	private boolean play=false;
	private int score=0;
	private int totalbocks=21;
	//speed on which ball will move with time delay 
	private Timer time;
	private int delay=8;
	//position of slider at initial stage
	private int ballposX=120;
	private int ballposY=350;
	//diretion of ball at intial stage
	private int ballXdir=-1;
	private int vallYdir=-2;
	
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
