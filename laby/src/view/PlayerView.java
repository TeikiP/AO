package view;

import java.io.File;

/** 
 * Sub-specification for ElementView meant to represent the player.
 * Uses all functions made by its parent class but uses
 * an image of the player as a graphical representation. <p>
 * 
 * There is only a single player so this class applies a
 * singleton design pattern.
 *
 * @author 			Teiki Pepin
 */	

public class PlayerView extends ElementView {

	/**
	 * Current instance of this singleton class.
	 */
	static PlayerView playerView = null;

	/**
	 * Empty private constructor.
	 * Not to be explicitly used since this is meant as a singleton class.
	 */
	
	private PlayerView() {
		
	}

	/**
	 * Gets the current instance of the PlayerView.
	 * 
	 * @return the instance of the PlayerView.
	 */
	
	public static PlayerView getInstance(){
		if(playerView == null)
			playerView = new PlayerView();
		return playerView;
	}


	/** 
	 * Returns a File object that points to an image file representing
	 * a player. The File is meant to be used elsewhere in order
	 * to generate a proper sprite that can be printed on the screen. <p>
	 * 
	 * If the file doesn't exist, an empty File will be returned.
	 *
	 * @return 			a File object that contains an image of a player
	 * @see 			view.ElementView
	 * @see 			java.io.File
	 */	
	
	@Override
	protected File getImageFile() {
		return new File("assets/player.png");
	}
	
}