package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.File;

/** 
 * Abstract class representing any element that can be represented
 * graphically at a single position. Provides methods to print the
 * element on the screen.
 *
 * @author 			Teiki Pepin
 */	

public abstract class ElementView {
	
	/**
	 * ImageView representing the sprite to display for this element.
	 */
	private ImageView sprite;
	
	/**
	 * Double representing the width of the element.
	 */
	private double width;
	
	/**
	 * Double representing the height of the element.
	 */
	private double height;

	
	/** 
	 * Abstract method that should be properly implemented
	 * by all inheriting classes. It will be used at 
	 * initialization to build the graphical representation of
	 * this element.
	 * 
	 * @return			a File pointing to an image file representing the element
	 * @see 			#ElementView()
	 */
	
	protected abstract File getImageFile();

	
	/** 
	 * Constructor for all implementing classes. Uses an image file
	 * that should be provided by the implementing class.
	 *
	 * @see 			#getImageFile()
	 */
	
	protected ElementView() {
		Image image = new Image(getImageFile().toURI().toString());
		sprite = new ImageView(image);		
		
		width = image.getWidth();
		height = image.getHeight();
	}	

	
	/** 
	 * Adds the sprite of this element to the specified pane at the given
	 * coordinates.
	 *
	 * @param	pane	the JavaFX pane in which to draw this element
	 * @param	x		the x coordinate in the graph at which to draw this element
	 * @param	y		the y coordinate in the graph at which to draw this element
	 * @see 			javafx.scene.layout.Pane
	 */
	
    public void drawSprite(Pane pane, int x, int y) {		
    	pane.getChildren().add(this.sprite);
    	updatePosition(x, y);
    }

    
	/** 
	 * Moves the sprite of this element to the given coordinates.
	 *
	 * @param	x		the x coordinate in the graph at which to move this sprite
	 * @param	y		the y coordinate in the graph at which to move this sprite
	 */
	
    public void updatePosition(int x, int y) {
    	sprite.setX(getDisplayPositionFromXCoord(x));
    	sprite.setY(getDisplayPositionFromYCoord(y));
    }
    
    
	/** 
	 * Gives the graphical display coordinate (in number of pixels from origin)
	 * on the horizontal axis from a vertex's coordinate in the graph.
	 *
	 * @param	x		the x coordinate in the graph
	 * @return			the x coordinate in the display
	 * @see				LabyrinthView
	 */
    
    private double getDisplayPositionFromXCoord(int x) {
    	double displayPosition;
    	
    	// We set the starting location to the west-most wall
    	displayPosition = LabyrinthView.WALL;
    	
    	// We move the location eastwards by the number of cells needed
    	displayPosition += x * (LabyrinthView.WALL + LabyrinthView.CELL);
    	
    	// We scale the location by the display scaling factor
    	displayPosition *= LabyrinthView.SPAN;
    	
    	// We center the location within the current cell depending on this object's width
    	displayPosition += (LabyrinthView.SPAN * LabyrinthView.CELL - width)/2;
    	
    	return displayPosition;
    }
    
    
	/** 
	 * Gives the graphical display coordinate (in number of pixels from origin)
	 * on the vertical axis from a vertex's coordinate in the graph.
	 *
	 * @param	y		the y coordinate in the graph
	 * @return			the y coordinate in the display
	 * @see				LabyrinthView
	 */
    
    private double getDisplayPositionFromYCoord(int y) {
    	double displayPosition;

    	// We set the starting location to the north-most wall
    	displayPosition = LabyrinthView.WALL;

    	// We move the location southwards by the number of cells needed
    	displayPosition += y * (LabyrinthView.WALL + LabyrinthView.CELL);

    	// We scale the location by the display scaling factor
    	displayPosition *= LabyrinthView.SPAN;

    	// We center the location within the current cell depending on this object's width
    	displayPosition += (LabyrinthView.SPAN * LabyrinthView.CELL - height)/2;
    	
    	return displayPosition;
    }
    

	/** 
	 * Getter that returns an ImageView corresponding to the graphical representation 
	 * of this element.
	 *
	 * @return			the ImageView representing the sprite of this element
	 */
    
	protected ImageView getSprite() {
		return sprite;
	}

	
	/** 
	 * This method updates the stored graphical representation of this
	 * object by reading from a file stored on disk.
	 *
	 * @param	newSpritePath	the File from which the sprite can be read
	 */
    
	protected void updateSprite(File newSpritePath) {
    	sprite.setImage(new Image(newSpritePath.toURI().toString()));
    }
}


