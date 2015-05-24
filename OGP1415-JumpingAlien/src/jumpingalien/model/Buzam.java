package jumpingalien.model;


import jumpingalien.programs.program.Program;
import jumpingalien.util.Sprite;
import be.kuleuven.cs.som.annotate.*;


/**
 * A class of Buzams, the nemesis of Mazub in the game jumping alien.
 * @version 1.0
 * @authors Pieter Van Damme and Lennert Vanmunster
 *
 */
public class Buzam extends Alien {
	/**
	 * Initialize a new Buzam as a game object with given horizontal and vertical location,
	 * horizontal and vertical velocity, initial and maximum horizontal velocity,
	 * ducking state and an array of sprites. 
	 * 
	 * @param 	horizontalLocation
	 * 		  	The horizontal location for this new Buzam.
	 * @param 	verticalLocation
	 * 		  	The vertical location for this new Buzam.
	 * @param 	horizontalVelocity
	 * 		  	The horizontal velocity for this new Buzam.
	 * @param 	verticalVelocity
	 * 		  	The vertical velocity for this new Buzam.
	 * @param 	initialHorizontalVelocityNotDucking
	 * 		  	The initial horizontal velocity for this new Buzam when not ducking.
	 * @param 	maximumHorizontalVelocityNotDucking
	 * 		  	The maximum horizontal velocity for this new Buzam when not ducking.
	 * @param 	ducking
	 * 		  	The ducking state for this new Buzam.
	 * @param 	program
	 * 			The program for this new Buzam.
	 * @param 	images
	 * 		  	Array of sprites to display Buzam for this new Buzam.
	 * @effect	This new Buzam is initialized as an alien with the given horizontal location, vertical location, horizontal velocity, vertical velocity,
	 * 			initial horizontal velocity when not ducking, maximum horizontal velocity when not ducking, ducking state, the initial number of hit points for all Buzams, 
	 * 			the constant maximum number of hit points for all Buzams, the given ducking state, the given program and an image array containing its sprites.
	 * 			| super(horizontalLocation, verticalLocation, horizontalVelocity, verticalVelocity, initialHorizontalVelocityNotDucking, 
	 *			maximumHorizontalVelocityNotDucking, ducking, initialHitPoints, MAX_HIT_POINTS, program, images)
	 */
	@Raw
	public Buzam(int horizontalLocation, int verticalLocation, double horizontalVelocity,
				double verticalVelocity, double initialHorizontalVelocityNotDucking,
				double maximumHorizontalVelocityNotDucking, boolean ducking, Program program, Sprite... images)
		throws IllegalArgumentException, IllegalLocationException {
		super(horizontalLocation, verticalLocation, horizontalVelocity, verticalVelocity, initialHorizontalVelocityNotDucking, 
				maximumHorizontalVelocityNotDucking, ducking, initialHitPoints, MAX_HIT_POINTS, program, images);
	}
	
	/**
	 * Initialize this new Buzam to a Buzam with a horizontal and vertical velocity of 0m/s, 
	 * an initial horizontal velocity of 1m/s, a maximum horizontal velocity of 3m/s and a false ducking state
	 * with the given horizontal location, vertical location, program and array of sprites.
	 * 
	 * @param 	horizontalLocation
	 * 			The horizontal location for this new Buzam.
	 * @param 	verticalLocation
	 * 			The vertical location for this new Buzam.
	 * @param	program
	 * 			The program for this new Buzam.
	 * @param	images
	 * 			An array of sprites for this new Buzam.
	 * @effect	This new Buzam is initialized with the given horizontal location as its horizontal location,
	 * 			the given vertical location as its vertical location, the given array of sprites as its sprites.
	 * 			This new Buzam's initial horizontal velocity is set to 0, its maximum horizontal velocity is set to 3
	 * 			and its ducking state is set to false.
	 * 			|this(horizontalLocation, verticalLocation, 0, 0, 1, 3, false, program, images)
	 */
	@Raw
	public Buzam(int horizontalLocation, int verticalLocation, Program program, Sprite... images){
		this(horizontalLocation, verticalLocation, 0, 0, 1, 3, false, program, images);
	}
	
	/**
	 * Initialize this new Buzam to a Buzam with a horizontal and vertical velocity of 0m/s, 
	 * an initial horizontal velocity of 1m/s, a maximum horizontal velocity of 3m/s and a false ducking state
	 * with the given horizontal location, vertical location and array of sprites.
	 * 
	 * @param 	horizontalLocation
	 * 			The horizontal location for this new Buzam.
	 * @param 	verticalLocation
	 * 			The vertical location for this new Buzam.
	 * @param	images
	 * 			An array of sprites for this new Buzam.
	 * @effect	This new Buzam is initialized with the given horizontal location as its horizontal location,
	 * 			the given vertical location as its vertical location, the given array of sprites as its sprites.
	 * 			This new Buzam's initial horizontal velocity is set to 0, its maximum horizontal velocity is set to 3,
	 * 			its ducking state is set to false and its program is set to null.
	 * 			|this(horizontalLocation, verticalLocation, 0, 0, 1, 3, false, null, images)
	 */
	@Raw
	public Buzam(int horizontalLocation, int verticalLocation, Sprite... images){
		this(horizontalLocation, verticalLocation, 0, 0, 1, 3, false, null, images);
	}
	
	/**
	 * Constant registering the maximum number of hit points for any Buzam.
	 */
	private final static int MAX_HIT_POINTS=500;
	
	/**
	 * Variable registering the initial number of hit points for any Buzam.
	 */
	private final static int initialHitPoints=500;

}
