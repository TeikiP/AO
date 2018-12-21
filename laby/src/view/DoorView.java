package view;

import java.io.File;

/** 
 * Sub-specification for ElementView meant to represent a door.
 * Uses all functions made by its parent class but uses
 * an image of a door as a graphical representation.
 *
 * @author 			Teiki Pepin
 * @see 			view.ElementView
 */	

public class DoorView extends ElementView {

	/** 
	 * Returns a File object that points to an image file representing
	 * an open door. The File is meant to be used elsewhere in order
	 * to generate a proper sprite that can be printed on the screen. <p>
	 * 
	 * If the file doesn't exist, an empty File will be returned.
	 *
	 * @return 			a File object that contains an image of an open door
	 * @see 			view.ElementView
	 * @see 			java.io.File
	 */	
	
	@Override
	protected File getImageFile() {
		return new File("assets/door_open.png");
	}
	
}
