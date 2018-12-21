package view;
import java.awt.Rectangle;

import javax.swing.JFrame;


public class ViewFrame extends JFrame {

	public ViewPanel panel;

	public ViewFrame(String string, int width, int height, double xmin, double ymin, double xmax, double ymax, 
			double scale, int margin, ViewJokari viewJokari) {
		super(string);
		panel = new ViewPanel(width, height, xmin, ymin, xmax, ymax, scale, margin, viewJokari);
		panel.setBounds(new Rectangle(0, width, 0, height));
		panel.setLayout(null);
		this.add(panel);	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width, height);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}


}
