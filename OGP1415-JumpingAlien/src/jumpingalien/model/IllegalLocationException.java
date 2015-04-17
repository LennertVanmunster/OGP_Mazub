package jumpingalien.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class for signaling illegal Locations for game objects.
 * 
 * @version  1.0
 * @author   Lennert Vanmunster, Pieter Van Damme
 */
public class IllegalLocationException extends RuntimeException {

	
	/**
	 * Initialize this new illegal Location exception with given Location.
	 * 
	 * @param  	horizontalLocation
	 *         	The horizontal Location for this new illegal Location exception.
	 * @param	verticalLocation
	 * 			The vertical Location for this new illegal Location exception.
	 * @post  	The horizontal Location of this new illegal Location exception is equal
	 *        	 to the given horizontal Location.
	 *       	| new.gethorizontalLocation() == horizontalLocation
	 * @post  	The vertical Location of this new illegal Location exception is equal
	 *        	 to the given vertical Location.
	 *       	| new.getverticalLocation() == verticalLocation  		
	 */
	public IllegalLocationException(double horizontalLocation, double verticalLocation) {
		this.horizontalLocation = horizontalLocation;
		this.verticalLocation = verticalLocation;
	}

	/**
	 * Return the horizontal Location registered for this illegal location exception.
	 */
	@Basic @Immutable
	public double getHorizontalLocation() {
		return this.horizontalLocation;
	}
	
	/**
	 * Return the vertical Location registered for this illegal location exception.
	 */
	@Basic @Immutable
	public double getVerticalLocation() {
		return this.verticalLocation;
	}

	/**
	 * Variable registering the horizontal location involved in this illegal Location
	 * exception.
	 */
	private final double horizontalLocation;
	
	/**
	 * Variable registering the vertical location involved in this illegal Location
	 * exception.
	 */
	private final double verticalLocation;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2688533382970034569L;
}
