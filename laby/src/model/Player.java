package model;


/**
 * The player is a character that moves around in the labyrinth.
 * @author Martial Duverneix
 *
 */
public class Player extends Character {
	/**
	 * The instance of the Player with the position 0 on the x coordinate and 0 on the y coordinate.
	 */
	private static Player instance = new Player(0,0);
	/**
	 * Gets the instance of the Player.
	 * @return the instance of the Player.
	 */
	public static Player getInstance() {
		return instance;
	}
	/**
	 * Allocates the Player object.
	 * @param _x the x coordinate we want for the player.
	 * @param _y the y coordinate we want for the player.
	 */
	private Player(int _x, int _y) {
		super(_x,_y);
	}
}
