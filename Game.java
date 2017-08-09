import java.awt.Color;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import acm.program.*;
import acm.util.RandomGenerator;
import acm.graphics.*;
public class Game extends GraphicsProgram {

	private static final int Width=500;
	private static final int Height=500;
	public static int score=0;
	public double snakeWidth=10;
	public double snakeHeight=10;
	public String direction="N";
	public String direction1="HATA";
	public GRect y�lan;
	public GOval yem;
	public GLabel Score;
	private RandomGenerator rgen = RandomGenerator.getInstance(); 
	public void run(){
		setSize(Width,Height);
		setBackground(Color.BLUE);
		//Oyun ekran�n �zelliklerini ayarl�caks�n
		addKeyListeners();
		yemle();
		setup();
	}
	public void setup(){
		
		y�lan=new GRect(180, 240, snakeWidth,snakeHeight );
		y�lan.setFilled(true);
		add(y�lan);
		while(true){
			
			Carp�smaKontrol();
			
			if(direction.equals("left"))
				
				y�lan.setLocation(y�lan.getLocation().getX()-1, y�lan.getLocation().getY());
			
			else if(direction.equals("down"))
			
				y�lan.setLocation(y�lan.getLocation().getX(),y�lan.getLocation().getY()+1);
			
			else if(direction.equals("right"))
				
				y�lan.setLocation(y�lan.getLocation().getX()+1, y�lan.getLocation().getY());
			
			else if(direction.equals("up"))
				
				y�lan.setLocation(y�lan.getLocation().getX(),y�lan.getLocation().getY()-1 );
			
			else if(direction.equals("SPACE"))
				
				y�lan.setLocation(y�lan.getLocation().getX(),y�lan.getLocation().getY() );
			
			
			
			if(y�lan.getX()>=Width||y�lan.getX()<=0){
				GLabel mesaj=new GLabel("G A M E   O V E R  - - - > "+score);
				mesaj.setLocation(100, 100);
				mesaj.setColor(Color.RED);
				add(mesaj);
				if(score>=0){
					Score=new GLabel("Score : "+ score);
					Score.setLocation(0, 0);
					
					Score.setColor(Color.RED);
					add(Score);
					
					}
				break;
				
				}
			if(y�lan.getY()>=Height||y�lan.getY()<=0)
			{
				GLabel mesaj=new GLabel("G A M E   O V E R  - - - > "+score);
				mesaj.setLocation(100, 100);
				mesaj.setColor(Color.RED);
				add(mesaj);
				break;
				
				}
			try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
			
		}
		
		
	}
	
	private void Carp�smaKontrol() {
		GObject collider = getCollidingObject();
		if(collider != null) {
				remove(yem);
				remove(Score);
				score+=+1;
				yemle();
			}
		}
	
	/*
	private void y�lan�uzat() {
		// TODO Auto-generated method stub
		GObject collider;
		GPoint collidingPoint1 = new GPoint(y�lan.getX(), y�lan.getY());
		collider = getElementAt(collidingPoint1.getX(), collidingPoint1.getY());
		y�lan=new GRect(collider.getWidth(),collider.getHeight(),collider.getX()*snakeWidth,collider.getY());
		
		}
	*/
	private GObject getCollidingObject() {
		GPoint collidingPoint1 = new GPoint(yem.getX(), yem.getY());
		GPoint collidingPoint2 = new GPoint(yem.getX() + 4* Width, yem.getY());
		GPoint collidingPoint3 = new GPoint(yem.getX(), yem.getY() + 4* Height);
		GPoint collidingPoint4 = new GPoint(yem.getX() + 4* Width, yem.getY() +4*  Height);
		GObject collider = null;
		if(getElementAt(collidingPoint1.getX(), collidingPoint1.getY()) != null) {
			collider = getElementAt(collidingPoint1.getX(), collidingPoint1.getY());
		} else {
			if(getElementAt(collidingPoint2.getX(), collidingPoint2.getY()) != null) {
				collider = getElementAt(collidingPoint2.getX(), collidingPoint2.getY());
			} else {
				if(getElementAt(collidingPoint3.getX(), collidingPoint3.getY()) != null) {
					collider = getElementAt(collidingPoint3.getX(), collidingPoint3.getY());
				} else {
					if(getElementAt(collidingPoint4.getX(), collidingPoint4.getY()) != null) {
						collider = getElementAt(collidingPoint1.getX(), collidingPoint1.getY());
					}
				}
			}
		}
		return collider;
	}
	public void yemle(){
		double x=rgen.nextDouble(1,48)*10;
		double y=rgen.nextDouble(1,48)*10;
		yem = new GOval(rgen.nextDouble(10,(Width / snakeWidth) - 2) * snakeWidth, rgen.nextDouble(10,(Height / snakeHeight) - 2) * snakeHeight,snakeHeight,snakeHeight);
		yem.setFilled(true);
		yem.setFillColor(Color.ORANGE);
		add(yem);
		Score=new GLabel("SCORE  - - - > "+ score);
		Score.setLocation(10, 10);
		Score.setColor(Color.YELLOW);
		add(Score);
	}

	public void keyPressed(KeyEvent e){
		switch(e.getKeyChar()){
		case KeyEvent.VK_A:
			if(direction!="right")
			direction="left"; break;
		case KeyEvent.VK_S:
			if(direction!="up")
			direction="down"; break;
		case KeyEvent.VK_D:
			if(direction!="left")
			direction="right"; break;
		case KeyEvent.VK_W:
			if(direction!="down")
			direction="up"; break;
		case KeyEvent.VK_SPACE:
			direction="SPACE";break;
		
		}
	}
	
}
