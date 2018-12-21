package view;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/** 
 * Class in charge of displaying the score. Only one score
 * display is needed at a time so this class follows a singleton 
 * design pattern.
 *
 * @author 			Teiki Pepin
 */	

public class ScoreView {

	/**
	 * Current instance of this singleton class.
	 */
    private static ScoreView instance = null;
    
	/**
	 * JavaFX Label used to display the score.
	 */
    private static Label score;

	/**
	 * Private constructor. Sets a JavaFX Label that will be used to display
	 * the score. Not to be explicitly used since this is meant as a singleton class.
	 */
    
    private ScoreView() {	
    	score = new Label();
    }


	/**
	 * Gets the current instance of the ScoreView.
	 * 
	 * @return the instance of the ScoreView.
	 */
	
    public static ScoreView getInstance() {
        if (instance == null)
        	instance = new ScoreView();
        
        return instance;
    }

	/** 
	 * Adds the score label, initially set to zero, to the display 
	 * specified by the given Pane. Sets its position to the top-right corner.
	 *
	 * @param	pane	the JavaFX pane in which to draw the score
	 * @see 			javafx.scene.layout.Pane
	 */
	
    public void createScoreView(Pane pane) {
	    updateScore(0);

	    score.relocate(LabyrinthView.getWidth()-30, 0);
        pane.getChildren().add(score);
    }
    

	/** 
	 * Updates the graphic display of the score.
	 *
	 * @param	score	the new score to update to
	 */
	
    public void updateScore(int score) {
    	ScoreView.score.setText(Integer.toString(score));
    }
    
    
}
