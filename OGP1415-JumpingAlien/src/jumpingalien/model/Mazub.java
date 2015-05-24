package jumpingalien.model;


import jumpingalien.util.Sprite;
import be.kuleuven.cs.som.annotate.*;

/**
 * @version 2.0
 * @authors Pieter Van Damme and Lennert Vanmunster
 *
 */
public class Mazub extends Alien {
	/**
	 * Initialize a new Mazub as a game object with given horizontal and vertical location,
	 * horizontal and vertical velocity, initial and maximum horizontal velocity,
	 * ducking state and an array of sprites. 
	 * 
	 * @param 	horizontalLocation
	 * 		  	The horizontal location for this new Mazub.
	 * @param 	verticalLocation
	 * 		  	The vertical location for this new Mazub.
	 * @param 	horizontalVelocity
	 * 		  	The horizontal velocity for this new Mazub.
	 * @param 	verticalVelocity
	 * 		  	The vertical velocity for this new Mazub.
	 * @param 	initialHorizontalVelocityNotDucking
	 * 		  	The initial horizontal velocity for this new Mazub when not ducking.
	 * @param 	maximumHorizontalVelocityNotDucking
	 * 		  	The maximum horizontal velocity for this new Mazub when not ducking.
	 * @param 	ducking
	 * 		  	The ducking state for this new Mazub.
	 * @param 	images
	 * 		  	Array of sprites to display Mazub for this new Mazub.
	 * @effect	This new Mazub is initialized as a game object with the given horizontal location, vertical location, horizontal velocity, vertical velocity,
	 * 			initial horizontal velocity when not ducking, maximum horizontal velocity when not ducking, the initial vertical velocity for all Mazubs, the horizontal acceleration
	 * 			for all Mazubs, ducking state, the initial number of hit points for all Mazubs, the constant maximum number of hit points for all Mazubs, the given ducking state, and an image array containing its sprites.
	 * 			| super(horizontalLocation, verticalLocation, horizontalVelocity, verticalVelocity, initialHorizontalVelocityNotDucking, 
	 *			| maximumHorizontalVelocityNotDucking, ducking, initialHitPoints, MAX_HIT_POINTS, null, images)
	 */
	@Raw
	public Mazub(int horizontalLocation, int verticalLocation, double horizontalVelocity,
				double verticalVelocity, double initialHorizontalVelocityNotDucking,
				double maximumHorizontalVelocityNotDucking, boolean ducking, Sprite... images)
		throws IllegalArgumentException, IllegalLocationException {
		super(horizontalLocation, verticalLocation, horizontalVelocity, verticalVelocity, initialHorizontalVelocityNotDucking, 
				maximumHorizontalVelocityNotDucking, ducking, initialHitPoints, MAX_HIT_POINTS, null, images);
	}

	/**
	 * Initialize this new Mazub to a Mazub with a horizontal and vertical velocity of 0m/s, 
	 * an initial horizontal velocity of 1m/s, a maximum horizontal velocity of 3m/s and a false ducking state
	 * with the given horizontal location, vertical location and array of sprites.
	 * 
	 * @param 	horizontalLocation
	 * 			The horizontal location for this new Mazub.
	 * @param 	verticalLocation
	 * 			The vertical location for this new Mazub.
	 * @param	images
	 * 			An array of sprites for this new Mazub.
	 * @effect	This new Mazub is initialized with the given horizontal location as its horizontal location,
	 * 			the given vertical location as its vertical location, the given array of sprites as its sprites.
	 * 			This new Mazub's initial horizontal velocity is set to 0, its maximum horizontal velocity is set to 3
	 * 			and its ducking state is set to false.
	 * 			|this(horizontalLocation, verticalLocation, 0, 0, 1, 3, false, images)
	 */
	@Raw
	public Mazub(int horizontalLocation, int verticalLocation, Sprite... images){
		this(horizontalLocation, verticalLocation, 0, 0, 1, 3, false, images);
	}
	
	/**
	 * Constant registering the maximum number of hit points for any Mazub.
	 */
	private final static int MAX_HIT_POINTS=500;
	
	/**
	 * Variable registering the initial number of hit points for any Mazub.
	 */
	private final static int initialHitPoints=100;

}
