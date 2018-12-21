package controler;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.*;
import view.*;

public class Animation implements ActionListener {

	// Quelques constantes
	private static double GRAVITATION = 9.80665; // acceleration de la gravitation

	private ViewFrame viewFrame;
	private ViewJokari viewJokari;
	private Panel panel;
	// Objet permettant de provoquer une animation à temps réguliers
	private AnimationTimer timer;
	private Jokari jokari;


	public Animation(String title, Jokari jokari){
		this.jokari = jokari;
		
		panel = new Panel();
		viewJokari = new ViewJokari(jokari.getRectangle()//TODO get coords here);
		
		viewFrame = new ViewFrame(title, Panel.getWIDTH(), Panel.getHEIGHT(),
				Panel.getXMIN(), Panel.getYMIN(), Panel.getXMAX(), Panel.getYMAX(),
				Panel.getSCALE(), Panel.getMARGIN(), viewJokari);
		timer = new AnimationTimer(this);
		timer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {		
		//for (int i=0;i<listBalls.size();i++) {
			/*listBalls.get(i).step();
			
			if(collision(listBalls.get(0),listBalls.get(1))) {
				//Balle 1
				listBalls.get(0).setVx0(listBalls.get(1).getVx());
				listBalls.get(0).setVy0(listBalls.get(1).getVy());
				listBalls.get(0).setX0(listBalls.get(0).getX());
				listBalls.get(0).setY0(listBalls.get(0).getY());
				listBalls.get(0).setT(AnimationTimer.getMSSTEP());
				
				//Balle 2
				listBalls.get(1).setVx0(listBalls.get(0).getVx());
				listBalls.get(1).setVy0(listBalls.get(0).getVy());
				listBalls.get(1).setX0(listBalls.get(1).getX());
				listBalls.get(1).setY0(listBalls.get(1).getY());
				listBalls.get(1).setT(AnimationTimer.getMSSTEP());
			}*/
			/*
			viewBalls.get(i).setXY(listBalls.get(i).getColor(), listBalls.get(i).getX(), listBalls.get(i).getY(), 
					Ball.getWIDTH(), Ball.getHEIGHT(), 
					Panel.getSCALE(), Panel.getMARGIN());
		}
		*/
		viewFrame.panel.repaint();
		Toolkit.getDefaultToolkit().sync();
		
	}
	
	public boolean collision(Ball ball1, Ball ball2) {
		double distance = Math.pow((ball1.getX() - ball2.getX()) * (ball1.getX() - ball2.getX()) + (ball1.getY() - ball2.getY()) * (ball1.getY() - ball2.getY()), 0.5);
		
		if (distance <= Ball.getWIDTH())
			return true;
		
		return false;
	}

	public static double getGRAVITATION() {
		return GRAVITATION;
	}

	public ViewFrame getViewFrame() {
		return viewFrame;
	}

	public ArrayList<ViewBall> getViewBall() {
		return viewBalls;
	}

	public Panel getPanel() {
		return panel;
	}

	public AnimationTimer getTimer() {
		return timer;
	}
	
	
	
}
