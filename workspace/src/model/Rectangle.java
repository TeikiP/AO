package model;

public class Rectangle {
	private double width;
	private double height;
	
	private double x;
	private double y;
	
	public Rectangle() {
		width = .5;
		height = width / 3;
		
		x = (Panel.getXMIN()+Panel.getXMAX())/2;
		y = Panel.getYMIN()+height/2;
	}
}
