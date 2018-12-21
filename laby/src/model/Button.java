package model;
/**
 * The Button is an element which appears in the labyrinth and allows to open a path in it.
 * @author Martial Duverneix
 *
 */
public class Button extends Element {
	/**
	 * the state of the button.
	 * If the state is true, the button is on.
	 * If the state is false, the button is off.
	 */
	private boolean state;
	/**
	 * Allocates a new Button object with a chosen position and with the off mode.
	 * @param _x the x coordinate we want for the button.
	 * @param _y the y coordinate we want for the button.
	 */
	public Button(int _x, int _y) {
		super(_x,_y);
		state=false;
	}
	/**
	 * Allocates a new Button with a chosen position and state.
	 * @param _x the x coordinate we want for the button.
	 * @param _y the y coordinate we want for the button.
	 * @param _state the state we want for the button.
	 */
	public Button(int _x, int _y, boolean _state) {
		super(_x, _y);
		state=_state;;

	}
	/**
	 * Gets the state.
	 * @return the state.
	 */
	public boolean getState() {
		return state;
	}
	/**
	 * Sets the state.
	 * @param state the state we want.
	 */
	public void setState(boolean state) {
		this.state = state;
	}
}
