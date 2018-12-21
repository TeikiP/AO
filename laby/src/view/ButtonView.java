package view;

import java.io.File;

/** 
 * Sub-specification for ElementView meant to represent a button. 
 * Its graphical representation can be either an open button or a 
 * closed button. Uses all functions made by its parent class 
 * but provides an image of a button, either open or closed.
 * Also provides methods to switch between the open and closed states.
 *
 * @author 			Teiki Pepin
 * @see 			view.ElementView
 */	

public class ButtonView extends ElementView {
	/**
	 * String containing the pathname to the closed button image file.
	 */
	String closeButtonPath;
	
	/**
	 * String containing the pathname to the opened button image file.
	 */
	String openButtonPath;
	
	
	/** 
	 * Returns a File object that points to an image file representing
	 * a closed button. The File is meant to be used elsewhere in order
	 * to generate a proper sprite that can be printed on the screen.
	 * 
	 * This also sets the pathnames for the closed button and the 
	 * open button files so that they can be used later on. <p>
	 * 
	 * If the selected file doesn't exist, an empty File will be returned.
	 *
	 * @return 			a File object that contains an image of a closed button
	 * @see 			view.ElementView
	 * @see 			java.io.File
	 */
	
	@Override
	protected File getImageFile() {
		closeButtonPath = "assets/button_close.png";
		openButtonPath = "assets/button_open.png";
		
		return new File(closeButtonPath);
	}


	/** 
	 * Updates the sprite of this object to one of an open button. It uses 
	 * the field openButtonPath defined elsewhere to generate the File 
	 * that is provided to its parent's updating method. <p>
	 * 
	 * If the selected file doesn't exist, an empty File will be provided.
	 *
	 * @see 			view.ElementView#updateSprite(File)
	 * @see 			java.io.File
	 */
	
	public void openButton() {
		super.updateSprite(new File(openButtonPath));
	}


	/** 
	 * Updates the sprite of this object to one of a closed button. It uses 
	 * the field closeButtonPath defined elsewhere to generate the File 
	 * that is provided to its parent's updating method. <p>
	 * 
	 * If the selected file doesn't exist, an empty File will be provided.
	 *
	 * @see 			view.ElementView#updateSprite(File)
	 * @see 			java.io.File
	 */
	
	public void closeButton() {
		super.updateSprite(new File(closeButtonPath));
	}

}
