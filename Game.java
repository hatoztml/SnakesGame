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
	public GRect yýlan;
	public GOval yem;
	public GLabel Score;
	private RandomGenerator rgen = RandomGenerator.getInstance(); 
	public void run(){
		setSize(Width,Height);
		setBackground(Color.BLUE);
		//Oyun ekranýn özelliklerini ayarlýcaksýn
		addKeyListeners();
		yemle();
		setup();
	}
	public void setup(){
		
		yýlan=new GRect(180, 240, snakeWidth,snakeHeight );
		yýlan.setFilled(true);
		add(yýlan);
		while(true){
			
			CarpýsmaKontrol();
			
			if(direction.equals("left"))
				
				yýlan.setLocation(yýlan.getLocation().getX()-1, yýlan.getLocation().getY());
			
			else if(direction.equals("down"))
			
				yýlan.setLocation(yýlan.getLocation().getX(),yýlan.getLocation().getY()+1);
			
			else if(direction.equals("right"))
				
				yýlan.setLocation(yýlan.getLocation().getX()+1, yýlan.getLocation().getY());
			
			else if(direction.equals("up"))
				
				yýlan.setLocation(yýlan.getLocation().getX(),yýlan.getLocation().getY()-1 );
			
			else if(direction.equals("SPACE"))
				
				yýlan.setLocation(yýlan.getLocation().getX(),yýlan.getLocation().getY() );
			
			
			
			if(yýlan.getX()>=Width||yýlan.getX()<=0){
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
			if(yýlan.getY()>=Height||yýlan.getY()<=0)
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
	
	private void CarpýsmaKontrol() {
		GObject collider = getCollidingObject();
		if(collider != null) {
				remove(yem);
				remove(Score);
				score+=+1;
				yemle();
			}
		}
	
	/*
	private void yýlanýuzat() {
		// TODO Auto-generated method stub
		GObject collider;
		GPoint collidingPoint1 = new GPoint(yýlan.getX(), yýlan.getY());
		collider = getElementAt(collidingPoint1.getX(), collidingPoint1.getY());
		yýlan=new GRect(collider.getWidth(),collider.getHeight(),collider.getX()*snakeWidth,collider.getY());
		
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
