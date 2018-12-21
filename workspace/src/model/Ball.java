package model;

import controler.Animation;

import java.awt.Color;
import java.util.*;

public class Ball {

	// Taille de la balle
	private static double WIDTH = 0.35; // 35 cm
	private static double HEIGHT = WIDTH;
	private static double RADIUS = WIDTH/2;
	
	// Masse de la balle
	private static double MASS = 1.0;
	
	private Color color;
	
	// données physiques
	private double x; // position
	private double y;
	private double vx; // vitesse
	private double vy;
	private double ax, ay; // acceleration
	private double x0, y0; // position initiale
	private double vx0; // vitesse initiale
	private double vy0;
	private double fx, fy; // force
	private double t; // temps relatif

	public Ball() {
		color = new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
				
		fx = 0; // force
		fy = MASS * Animation.getGRAVITATION();
		
		ax = fx / MASS; // acceleration initiale
		ay = fy / MASS; 
		
		vx0 = vx = (new Random()).nextInt(5)+1; // vitesse initiale 1 m/s
		vy0 = vy = 0;
		if ((new Random()).nextInt(2) == 0)
			vx0 = vx = -vx;
		
		x0 = x = (new Random()).nextDouble() * Panel.getXMAX();
		y0 = y = (Panel.getYMIN() + Panel.getYMAX())/2;
		
		t = 0;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public void step() {
		vx = vx0 + ax * t;
		vy = vy0 + ay * t;
		double t2 = t*t;
		x = x0 + vx0 * t + (ax * t2) / 2;
		y = y0 + vy0 * t + (ay * t2) / 2;
		
		if(x >= Panel.getXMAX() - WIDTH || x <= Panel.getXMIN()){
			vx0 = -vx;
			vy0 = vy ;
			x0 = x;
			y0 = y;
			t = 0;
		}
		
		if(y >= Panel.getYMAX() - HEIGHT || y <= Panel.getYMIN()){
			vx0 = vx;
			vy0 = -vy;
			x0 = x;
			y0 = y;
			t = 0;
		}
		
		t += AnimationTimer.getMSSTEP();
	}
	
	public static double getWIDTH() {
		return WIDTH;
	}

	public static double getHEIGHT() {
		return HEIGHT;
	}

	public static double getRADIUS() {
		return RADIUS;
	}

	public static double getMASS() {
		return MASS;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getVx() {
		return vx;
	}

	public double getVy() {
		return vy;
	}

	public double getAx() {
		return ax;
	}

	public double getAy() {
		return ay;
	}

	public double getX0() {
		return x0;
	}

	public double getY0() {
		return y0;
	}

	public double getVx0() {
		return vx0;
	}

	public double getVy0() {
		return vy0;
	}

	public double getFx() {
		return fx;
	}

	public double getFy() {
		return fy;
	}

	public double getT() {
		return t;
	}

	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	public void setAx(double ax) {
		this.ax = ax;
	}

	public void setAy(double ay) {
		this.ay = ay;
	}

	public void setX0(double x0) {
		this.x0 = x0;
	}

	public void setY0(double y0) {
		this.y0 = y0;
	}

	public void setVx0(double vx0) {
		this.vx0 = vx0;
	}

	public void setVy0(double vy0) {
		this.vy0 = vy0;
	}

	public void setT(double t) {
		this.t = t;
	}

	
	
}
