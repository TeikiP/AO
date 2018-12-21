package model;
/**
 * The Enemy is an element which appears in the labyrinth and tries to kill the player.
 * @author Martial Duverneix
 *
 */
public class Enemy extends Character {
	/**
	 * Allocates a new Enemy object with a chosen position.
	 * @param _x the x coordinate we want for the enemy.
	 * @param _y the y coordinate we want for the enemy.
	 */
	public Enemy(int _x, int _y) {
		super(_x,_y);
	}
}
