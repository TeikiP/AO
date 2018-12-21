package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class ViewRectangle {
	Rectangle2D.Double rect;

	public ViewRectangle(double x, double y, double width, double height) {
		rect = new Rectangle2D.Double(x, y, width, height);
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.MAGENTA);
				
		((Graphics2D)g).fill(rect);
		
		g.setColor(Color.BLACK);		
	}
}
