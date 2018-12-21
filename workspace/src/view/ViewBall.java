package view;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

public class ViewBall{

	// La balle
	Ellipse2D.Double ellipse;

	public ViewBall(){
		ellipse = new Ellipse2D.Double();
	}

	public void paint(Graphics g) {
		g.setColor(Color.RED);
				
		((Graphics2D)g).fill(ellipse);
		
		g.setColor(Color.BLACK);
	}

	// repositionne la balle selon ses coordonnees
	public void setXY(double x, double y, double width, double height, double scale, int margin) {
		ellipse.setFrame(x * scale + margin, 
				y * scale + margin, 
				width * scale, 
				height * scale);
	}

}
