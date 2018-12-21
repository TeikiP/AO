package view;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


/** 
 * This class is used to draw the walls composing the labyrinth as 
 * well as the frame surrounding it.
 * 
 * There is only a single labyrinth to be displayed so this class follows a
 * singleton design pattern.
 *
 *
 * @author		Teiki Pepin, Ali Cherifi
 */	

public class LabyrinthView {

    static Scene scene = null;

    private static int width;
    private static int height;
    
    static final int SPAN = 4;
    static final int WALL = 2;
    static final int CELL = 9;
    public static final Paint WALL_COLOR = Color.BURLYWOOD;
    private static LabyrinthView labyrinthView = new LabyrinthView();

	/**
	 * Empty private constructor.
	 * Not to be explicitly used since this is meant as a singleton class.
	 */
    
    private LabyrinthView() {
    }


	/**
	 * Gets the current instance of the ScoreView.
	 * 
	 * @return the instance of the ScoreView.
	 */
	
    public static LabyrinthView getInstance() {
        return labyrinthView;
    }

    /**
     * Sets the EventHanGetter for the frame's width.dler for the scene.
     * 
     * @param 	eventHandler	the eventHandler responsible for updating the scene
     */
    
    public void setOnAction(EventHandler<KeyEvent> eventHandler){
        scene.setOnKeyPressed(eventHandler);

    }

    
	/**
	 * This method draws the frame around the labyrinth.
	 * 
	 * @param	stage	the JavaFX Stage on which to add the pane
	 * @param	pane	the JavaFX Pane on which to display the wall
	 * @param	nbrX	the horizontal length of the labyrinth
	 * @param	nbrY	the vertical length of the labyrinth
	 */
    
    public static void drawFrame(Stage stage, Pane pane, int nbrX, int nbrY) {
        width = ((WALL + CELL) * nbrX + WALL) * SPAN;
        height = ((WALL + CELL) * nbrY + WALL) * SPAN;
        scene = new Scene(pane, width, height);

        Rectangle square;
        stage.setScene(scene);

        square = new Rectangle(0, 0,
                SPAN * (nbrX * (CELL + WALL) + WALL),
                WALL * SPAN);
        square.setFill(WALL_COLOR);
        pane.getChildren().add(square);

        square = new Rectangle(0, SPAN * (nbrY * (CELL + WALL)),
                SPAN * (nbrX * (CELL + WALL) + WALL),
                WALL * SPAN);
        square.setFill(WALL_COLOR);
        pane.getChildren().add(square);

        square = new Rectangle(0, 0,
                WALL * SPAN,
                SPAN * (nbrY * (CELL + WALL) + WALL));
        square.setFill(WALL_COLOR);
        pane.getChildren().add(square);

        square = new Rectangle(SPAN * (nbrX * (CELL + WALL)), 0,
                WALL * SPAN,
                SPAN * (nbrY * (CELL + WALL) + WALL));
        square.setFill(WALL_COLOR);
        pane.getChildren().add(square);

        for (int x = 0; x < nbrX - 1; ++x) {
            int offsetX = ((WALL + CELL) + (WALL + CELL) * x) * SPAN;
            for (int y = 0; y < nbrY - 1; ++y) {
                int offsetY = ((WALL + CELL) + (WALL + CELL) * y) * SPAN;
                square = new Rectangle(offsetX, offsetY, WALL * SPAN, WALL * SPAN);
                square.setFill(WALL_COLOR);
                pane.getChildren().add(square);

            }
        }

    }
    

	/**
	 * This method draws a single wall between the specified coordinates.
	 * 
	 * @param	pane	the JavaFX Pane on which to display the wall
	 * @param	xs		the source's horizontal axis coordinate
	 * @param	xt		the source's vertical axis coordinate
	 * @param	ys		the target's horizontal axis coordinate
	 * @param	yt		the target's vertical axis coordinate
	 * @param	color	the JavaFX color in which the wall is to be painted
	 */
    
    public static void drawWall(Pane pane, int xs, int ys, int xt, int yt, Paint color) {
        int x = 0, y = 0, xspan = 0, yspan = 0;
        if (ys == yt) {
            x = ((WALL + CELL) + (WALL + CELL)*((int) (xs + xt) / 2))*SPAN;
            y = (WALL + ys*(WALL + CELL))*SPAN;
            xspan = WALL*SPAN;
            yspan = CELL*SPAN;
            Rectangle square = new Rectangle(x, y, xspan, yspan);
            square.setFill(color);
            pane.getChildren().add(square);
        } else if (xs == xt) {
            x = (WALL + xs*(WALL + CELL))*SPAN;
            y = ((WALL + CELL) + (WALL + CELL)*((int) (ys + yt) / 2))*SPAN;
            xspan = CELL*SPAN;
            yspan = WALL*SPAN;
            Rectangle square = new Rectangle(x, y, xspan, yspan);
            square.setFill(color);
            pane.getChildren().add(square);
        }
    }

    
	/**
	 * Getter for the frame's width.
	 * 
	 * @return 		the width of the frame
	 */
    
    public static int getWidth() {
    	return width;
    }

    
	/**
	 * Getter for the frame's height.
	 * 
	 * @return 		the height of the frame
	 */
    
    public static int getHeight() {
    	return height;
    }
} 
