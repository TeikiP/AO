package model;

import java.util.concurrent.ThreadLocalRandom;

/**
 * The Door is an element which appears in the labyrinth and allows to create a new labyrinth to continue to play after you passed it.
 * @author Martial Duverneix
 *
 */
public class Door extends Element{
	/**
	 * the instance of the door.
	 */
	private static Door door;
	
	/**
	 * Gets the instance of the door.
	 * @param x the x coordinate we want for the door if it's not already created.
	 * @param y the y coordinate we want for the door if it's not already created.
	 * @return the instance.
	 */
	public static Door getInstance(int x, int y){
		if (door == null)
			door = new Door(x, y);
		return door;
	}
	/**
	 * Allocates a new Door object with a chosen position.
	 * @param _x the x coordinate we want for the door.
	 * @param _y the y coordinate we want for the door.
	 */
	private Door(int _x, int _y) {
		super(_x,_y);
	}
}
