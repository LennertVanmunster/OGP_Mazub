package jumpingalien.model;

import jumpingalien.util.Sprite;
import jumpingalien.util.Util;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of sharks
 * 
 * @version	1.0
 * @authors Pieter Van Damme and Lennert Vanmunster
 *
 */
public class Shark extends GameObject{

	@Raw
	public Shark(int horizontalLocation, int verticalLocation, double initialHorizontalVelocity,
			double maximumHorizontalVelocity,  Sprite... images)
	throws IllegalArgumentException{
		this.setHorizontalLocation(horizontalLocation);
		this.setVerticalLocation(verticalLocation);
		this.setImages(images);
		if(!isPossibleInitialHorizontalVelocity(initialHorizontalVelocity))
			throw new IllegalArgumentException("Not a valid initial horizontal velocity!");
		this.initialHorizontalVelocity = initialHorizontalVelocity;
		if(!canHaveAsMaximumHorizontalVelocity(maximumHorizontalVelocity))
			throw new IllegalArgumentException("Not a valid maximum horizontal velocity!");
		this.maximumHorizontalVelocity=maximumHorizontalVelocity;
		this.setHorizontalVelocity(horizontalVelocity);
		this.setVerticalVelocity(verticalVelocity);
		this.setMaxHitPoints(MAX_HIT_POINTS);
		this.setHitPoints(hitPoints);
	}
	

	@Raw
	public Shark(int horizontalLocation, int verticalLocation,  Sprite... images){
		this(horizontalLocation, verticalLocation, 0, 4, images);
	}
	
	
	/**
	 * Return the initial horizontal velocity of this shark.
	 * 
	 * @return	The initial horizontal velocity of this shark.
	 * 			| result==this.initialHorizontalVelocity
	 */
	@Raw
	public double getInitialHorizontalVelocity(){
			return this.initialHorizontalVelocity;
	}
	
	/**
	 * Return the maximum horizontal velocity when not ducking of this shark.
	 * 
	 * @return	The maximum horizontal velocity of this shark.
	 * 			| 	result==this.maximumHorizontalVelocity
	 */
	@Raw
	public double getMaximumHorizontalVelocity(){
			return this.maximumHorizontalVelocity;
	}
	
	/**
	 *  Check whether the given maximum horizontal velocity is a valid maximum horizontal velocity.
	 *  
	 * @param 	maximumHorizontalVelocity
	 * 			The maximum horizontal velocity to be checked.
	 * @return	True if and only if the given maximum horizontal velocity matches with the initial horizontal
	 * 			velocity of this shark.
	 * 			|result =  matchesMaximumHorizontalVelocityInitialHorizontalVelocity(maximumHorizontalVelocity, this.getInitialHorizontalVelocity());
	 */
	@Raw
	public boolean canHaveAsMaximumHorizontalVelocity(double maximumHorizontalVelocity){
		return matchesMaximumHorizontalVelocityInitialHorizontalVelocity(maximumHorizontalVelocity, this.getInitialHorizontalVelocity());
	}
	
	/**
	 * Check whether the given maximum horizontal velocity matches with the given initial horizontal velocity of a shark.
	 * 
	 * @param 	maximumHorizontalVelocity
	 * 			The maximum horizontal velocity to check.
	 * @param 	initialHorizontalVelocity
	 * 			The initial horizontal velocity to check.
	 * @return	True if the given maximum horizontal velocity is greater than or equal to the given initial horizontal velocity.
	 * 			| result == Util.fuzzyGreaterThanOrEqualTo(maximumHorizontalVelocity,initialHorizontalVelocity)
	 */
	public static boolean matchesMaximumHorizontalVelocityInitialHorizontalVelocity(
			double maximumHorizontalVelocity, double initialHorizontalVelocity){
		return Util.fuzzyGreaterThanOrEqualTo(maximumHorizontalVelocity,initialHorizontalVelocity);
	}
	
	/**
	 * Check whether the given initial horizontal velocity is a possible initial horizontal velocity for any shark.
	 * 
	 * @param 	initialHorizontalVelocity
	 * 			The initial horizontal velocity to check.
	 * @return	True if the given initial horizontal velocity is greater than or equal to 0.
	 * 			result == Util.fuzzyGreaterThanOrEqualTo(initialHorizontalVelocity, 0)
	 */
	public static boolean isPossibleInitialHorizontalVelocity(double initialHorizontalVelocity){
		return Util.fuzzyGreaterThanOrEqualTo(initialHorizontalVelocity, 0);
	}
	
	/**
	 * Check whether the given horizontal velocity is a valid horizontal velocity.
	 * 
	 * @param 	horizontalVelocity
	 * 			The horizontal velocity to be checked.
	 * @return	True if and only if either the absolute value of the given horizontal velocity is greater than or equal
	 * 			to the initial horizontal velocity of this shark and is less than or equal to the maximum horizontal
	 * 			velocity of this shark or the given horizontal velocity is equal to zero.
	 * 			|result == (Util.fuzzyGreaterThanOrEqualTo(abs(horizontalVelocity),this.getInitialHorizontalVelocity()) 
				|  && Util.fuzzyLessThanOrEqualTo(abs(horizontalVelocity), this.getMaximumHorizontalVelocity()))
				|  || Util.fuzzyEquals(horizontalVelocity, 0);
	 */
	@Raw
	@Override
	public boolean canHaveAsHorizontalVelocity(double horizontalVelocity){
		horizontalVelocity = Math.abs(horizontalVelocity);
		return Util.fuzzyGreaterThanOrEqualTo(horizontalVelocity,this.getInitialHorizontalVelocity()) 
				&& Util.fuzzyLessThanOrEqualTo(horizontalVelocity, this.getMaximumHorizontalVelocity())
				|| Util.fuzzyEquals(horizontalVelocity, 0);
	}
	
	
	
	/**
	 * Variable registering the initial horizontal velocity of this shark.
	 */
	private final double initialHorizontalVelocity;
	
	/**
	 * Variable registering the maximum horizontal velocity of this shark.
	 */
	private final double maximumHorizontalVelocity;
	
	/**
	 * Check whether the given vertical velocity is a valid vertical velocity
	 * for the class of sharks.
	 * 
	 * @param	verticalVelocity
	 * 			The given vertical velocity
	 * @return	|result == Util.fuzzyLessThanOrEqualTo(verticalVelocity, INITIAL_VERTICAL_VELOCITY)
	 */
	@Override
	public boolean isValidVerticalVelocity(double verticalVelocity){
		return Util.fuzzyLessThanOrEqualTo(verticalVelocity, INITIAL_VERTICAL_VELOCITY);
	}
	
	/**
	 *  Constant registering the initial vertical velocity of all sharks.
	 */
	private static final double INITIAL_VERTICAL_VELOCITY = 2;
	
	/**
	 * Check whether the given number of images is a valid number of images
	 * for the class shark.
	 * 
	 * @param	nbImages
	 * 			The given number of images.
	 * @return	|result == nbImages == 2; 
	 */
	@Override
	public boolean isValidNbImages(int nbImages){
		return nbImages == 2;
	}
	
	@Raw
	@Override
	public void advanceTime(double deltaTime)
		throws IllegalArgumentException {
		if(!isValidDeltaTime(deltaTime))
			throw new IllegalArgumentException();
	}
	
	/**
	 * Variable registering the maximum number of hit points of a shark.
	 */
	private final static int MAX_HIT_POINTS=100;
}
