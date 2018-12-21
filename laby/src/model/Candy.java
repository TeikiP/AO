package model; 
/**
 * The Candy is an element which appears in the labyrinth and can be taken to increase the score.
 * @author Martial Duverneix
 *
 */ 
public class Candy extends Element {
	/**
	 * the number of points we want to add to the score.
	 */
	private int numPoint;
	
	/**
	 * Allocates a new Candy object with 500 number of points and a chosen position.
	 * @param _x the x coordinate we want for the candy.
	 * @param _y the y coordinate we want for the candy.
	 */
	public Candy(int _x, int _y) { 
		super(_x,_y);
		numPoint=500; 
	}
	/**
	 * Allocates a new Candy object with a chosen position and a chosen number of points.
	 * @param _x the x coordinate we want for the candy.
	 * @param _y the y coordinate we want for the candy.
	 * @param _point the number of points we want the candy to give.
	 */
	public Candy(int _x, int _y, int _point) {
		super(_x, _y);
		numPoint=_point;
	}
	/**
	 * Gets the number of points.
	 * @return the number of points.
	 */
	public int getNumPoint() {
		return numPoint;
	}
	/**
	 * Sets a new number of points.
	 * @param numPoint the new number of points.
	 */
	public void setNumPoint(int numPoint) {
		this.numPoint = numPoint;
	}
	
}
