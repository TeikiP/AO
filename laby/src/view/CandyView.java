package view;

import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

/** 
 * Sub-specification for ElementView meant to represent a random
 * candy. Uses all functions made by its parent class but uses
 * an image of a candy as a graphical representation.
 *
 * @author 			Teiki Pepin
 * @see 			view.ElementView
 */	

public class CandyView extends ElementView {	

	/** 
	 * Returns a File object that points to an image file representing
	 * a candy. The File is meant to be used elsewhere in order
	 * to generate a proper sprite that can be printed on the screen. <p>
	 * 
	 * The file is selected among four possible choices in a random manner.
	 * If the selected file doesn't exist, an empty File will be returned.
	 *
	 * @return 			a File object that contains an image of a candy selected randomly among possible choices
	 * @see 			view.ElementView
	 * @see 			java.io.File
	 * @see 			java.util.concurrent.ThreadLocalRandom#nextInt(int, int)
	 */	
	
	@Override
	protected File getImageFile() {
		File file;
		int type = ThreadLocalRandom.current().nextInt(0, 4);
		
		switch(type) {
			case 0:
				file = new File("assets/candy-1.png");
				break;
				
			case 1:
				file = new File("assets/candy-2.png");
				break;
				
			case 2:
				file = new File("assets/candy-3.png");
				break;
				
			case 3:
				file = new File("assets/candy-4.png");
				break;
				
			default:
				file = new File("assets/candy-1.png");
				break;				
		}
		
		return file;
	}

}
