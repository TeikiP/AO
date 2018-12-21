package view;

import java.awt.Color;
import java.awt.Graphics;

import model.Rectangle;

public class ViewJokari {
	private ViewBall viewBall;
	private ViewRectangle viewRectangle;
	
	public ViewJokari(double RectX, double RectY, double RectWidth, double RectHeight) {
		viewBall = new ViewBall();
		viewRectangle = new ViewRectangle(RectX, RectY, RectWidth, RectHeight);
	}

	public void paint(Graphics g) {
		viewBall.paint(g);
		viewRectangle.paint(g);
		
		g.setColor(Color.BLACK);
		/*g.drawLine(
				(int)(xmin * scale) + margin,
				(int)(ymax * scale) + margin,
				(int)(xmax * scale) + margin,
				(int)(ymax * scale) + margin);*/
	}

	// repositionne la balle selon ses coordonnees
	public void setXY(double x, double y, double width, double height, double scale, int margin) {
		viewBall.setXY(x, y, width, height, scale, margin);
	}
}
