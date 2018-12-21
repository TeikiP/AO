package model;

public class Panel {
	
	// Taille de la fenêtre en pixels
	 private static int WIDTH = 500;
	 private static int HEIGHT = 500;
		
	// Marges en pixels
	 private static int MARGIN = 32;
	
	// Echelle: 40 pixels = 1m
	 private static double SCALE = 40;
	
	// coordonnees du plan en mètres
	 private static double XMIN = 0;
	 private static double YMIN = 0;
	 private static double XMAX = 10;
	 private static double YMAX = 10;
	 
	public static int getWIDTH() {
		return WIDTH;
	}
	public static int getHEIGHT() {
		return HEIGHT;
	}
	public static int getMARGIN() {
		return MARGIN;
	}
	public static double getSCALE() {
		return SCALE;
	}
	public static double getXMIN() {
		return XMIN;
	}
	public static double getYMIN() {
		return YMIN;
	}
	public static double getXMAX() {
		return XMAX;
	}
	public static double getYMAX() {
		return YMAX;
	}

	
		
}
