package model; 

/**
 * The abstract class Character is a superclass of all classes that can move on the labyrinth.
 * @author Martial Duverneix
 *
 */
public abstract class Character extends Element {
	/**
	 * Sets the position of the character on the x and y chosen.
	 * @param _x the x coordinate we want for the player.
	 * @param _y the y coordinate we want for the player.
	 */
	public Character(int _x, int _y) {
		super(_x,_y);
	}
	/**
	 * Looks if this position has a wall. Returns true if there is a wall and false if there is not.
	 * @param nx the x coordinate where we want to test if there will be a wall.
	 * @param ny the y coordinate where we want to test if there will be a wall.
	 * @return if there is a wall at this position.
	 */
	public boolean collisionWall(int nx, int ny) {
		if(Labyrinth.getInstance().areVerticesConnected(this.getX(), this.getY(), nx, ny)) {
			return false;
		}
		else
			return true;
	}
	/**
	 * Moves the character to a new position if there is no wall there.
	 * @param _x the x coordinate where we want to go.
	 * @param _y the y coordinate where we want to go.
	 */
	public void moves(int _x, int _y) {
		if(!collisionWall(_x, _y)){
			this.setX(_x);
			this.setY(_y);
		}
	}

	
	
}
