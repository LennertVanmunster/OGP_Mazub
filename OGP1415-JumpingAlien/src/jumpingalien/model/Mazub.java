package jumpingalien.model;


import jumpingalien.util.Sprite;
import jumpingalien.util.Util;
import be.kuleuven.cs.som.annotate.*;
import java.lang.Math;

/**
 * A class of Mazubs, rectangular player-controlled objects in the game jumping alien,
 * involving a horizontal location, vertical location, maximum horizontal location, maximum vertical location,
 * horizontal velocity, vertical velocity, initial horizontal velocity, maximum horizontal velocity, 
 * initial vertical velocity, horizontal acceleration, vertical acceleration and an array of sprites.
 * 
 * @invar	The bottom-left pixel of each Mazub must have a valid location in the game world.
 * 			|isValidLocation(getHorizontalLocation(),getVerticalLocation())
 * @invar	Each Mazub can have its horizontal velocity as its horizontal velocity.
 * 			|canHaveAsHorizontalVelocity(getHorizontalVelocity())
 * @invar	The initial horizontal velocity of each Mazub must a valid initial horizontal velocity.
 * 			|canHaveAsInitialHorizontalVelocity(getInitialHorizontalVelocity())
 * @invar	Each Mazub can have its maximum horizontal velocity as its maximum horizontal velocity.
 * 			|canHaveAsMaximumHorizontalVelocity(getMaximumHorizontalVelocity())
 * @invar	The vertical velocity of each Mazub must be a valid vertical velocity.
 * 			|isValidVerticalVelocity(getVerticalVelocity())
 * @invar	The direction of each Mazub must be a valid direction.
 * 			|isValidDirection(getDirection())
 * @invar	The length of the image array of each Mazub must be a valid length.
 * 			|isValidNbImages(getNbImages())
 * @invar	Each image in the image array of each Mazub must be a valid image.
 * 			| for each i in 1..getNbImages():
 * 			| 	isValidImage(getImageAt(I))
 * @invar	The time since Mazub last started moving must be a valid time for each Mazub.
 * 			| isValidTimeSinceMove(getTimeSinceStartMove())
 * @invar	The time since Mazub last ended moving must be a valid time for each Mazub.
 * 			| isValidTimeSinceMove(getTimeSinceEndMove())
 * @version 2.0
 * @authors Pieter Van Damme and Lennert Vanmunster
 *
 */
public class Mazub extends GameObject {
	/**
	 * Initialize a new Mazub with given horizontal and vertical location,
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
	 * @post	The horizontal location of this new Mazub is equal to the 
	 * 			given horizontal location.
	 * 			|new.getHorizontalLocation() == horizontalLocation
	 * @post	The vertical location of this new Mazub is equal to the 
	 * 			given vertical location.
	 * 			|new.getVerticalLocation() == verticalLocation
	 * @post	The initial horizontal velocity while Mazub is not ducking
	 * 			of this new Mazub is equal to the given initial horizontal velocity.
	 * 			|new.getInitialHorizontalVelocityNotDucking() == initialHorizontalVelocityNotDucking
	 * @post	The maximum horizontal velocity while Mazub is not ducking of this new Mazub is equal to the 
	 * 			given maximum horizontal velocity.
	 * 			|new.getMaximumHorizontalVelocityNotDucking() == maximumHorizontalVelocityNotDucking
	 * @post	The horizontal velocity of this new Mazub is equal to the 
	 * 			given horizontal velocity.
	 * 			|new.getHorizontalVelocity() == horizontalVelocity
	 * @post	The vertical velocity of this new Mazub is equal to the 
	 * 			given vertical velocity.
	 * 			|new.getVerticalVelocity() = verticalVelocity
	 * @post	If the given horizontal velocity is greater than or equal to 0 the direction of this new Mazub is RIGHT.
	 * 			Otherwise the direction of this new Mazub is LEFT.
	 * 			|if (Util.fuzzyGreaterThanOrEqualTo(horizontalVelocity, 0))
	 * 			|	new.getDirection() == Direction.RIGHT
	 * 			|else
	 * 			|	new.getDirection() == Direction.LEFT
	 * @post	The ducking state of this new Mazub is equal to the 
	 * 			given ducking state.
	 * 			|new.isDucking == ducking
	 * @post	The images of this new Mazub are equal to the 
	 * 			given images.
	 * 			|new.getImages() == images
	 * @effect	If the given ducking state of the new Mazub is true the Mazub
	 * 			starts ducking.
	 * 			|if(isDucking())
	 *			|	startDuck()
	 * @throws	IllegalLocationException
	 * 			Not a valid horizontal location
	 * 			|!canHaveAsHorizontalLocation(horizontalLocation)	
	 * @throws	IllegalLocationException
	 * 			Not a valid vertical location
	 * 			|!canHaveAsVericalLocation(verticalLocation)	
	 * @throws	IllegalArgumentException
	 * 			The given initial horizontal velocity is not valid for any Mazub or it does 
	 * 			not match with the given maximum horizontal velocity.
	 * 			|!isPossibleInitialHorizontalVelocity(initialHorizontalVelocityNotDucking)	
	 * @throws	IllegalArgumentException
	 * 			The given maximum horizontal velocity is not valid for any Mazub or it does
	 * 			not match with the given initial horizontal velocity.
	 * 			|!canHaveAsMaximumHorizontalVelocity(maximumHorizontalVelocityNotDucking)	
	 * @throws	IllegalArgumentException
	 * 			Not a valid horizontal velocity.
	 * 			|!canHaveAsHorizontalVelocity(horizontalVelocity)
	 * @throws	IllegalArgumentException
	 * 			Not a valid vertical velocity.
	 * 			|!isValidVerticalVelocity(verticalVelocity)	
	 */
	@Raw
	public Mazub(int horizontalLocation, int verticalLocation, double horizontalVelocity,
				double verticalVelocity, double initialHorizontalVelocityNotDucking,
				double maximumHorizontalVelocityNotDucking, boolean ducking, int hitPoints, World world, Sprite... images)
		throws IllegalArgumentException, IllegalLocationException {
		this.setWorld(world);
		setHorizontalLocation(horizontalLocation);
		setVerticalLocation(verticalLocation);
		setDucking(ducking);
		if(!isPossibleInitialHorizontalVelocity(initialHorizontalVelocityNotDucking))
			throw new IllegalArgumentException("Not a valid initial horizontal velocity!");
		this.initialHorizontalVelocityNotDucking = initialHorizontalVelocityNotDucking;
		if(!canHaveAsMaximumHorizontalVelocity(maximumHorizontalVelocityNotDucking))
			throw new IllegalArgumentException("Not a valid maximum horizontal velocity!");
		this.maximumHorizontalVelocityNotDucking=maximumHorizontalVelocityNotDucking;
		setHorizontalVelocity(horizontalVelocity);
		setVerticalVelocity(verticalVelocity);
		if(isDucking())
			startDuck();
		if (Util.fuzzyGreaterThanOrEqualTo(horizontalVelocity, 0))
			setDirection(Direction.RIGHT);
		else
			setDirection(Direction.LEFT);
		this.setHitPoints(hitPoints);
		this.setImages(images);
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
	 * @throws IllegalLocationException 
	 * @throws IllegalArgumentException 
	 * @effect	This new Mazub is initialized with the given horizontal location as its horizontal location,
	 * 			the given vertical location as its vertical location, the given array of sprites as its sprites.
	 * 			This new Mazub's initial horizontal velocity is set to 0, its maximum horizontal velocity is set to 3
	 * 			and its ducking state is set to false.
	 * 			|this(horizontalLocation, verticalLocation, 0, 0, 1, 3, false, images)
	 */
	@Raw
	public Mazub(int horizontalLocation, int verticalLocation, Sprite... images){
		this(horizontalLocation, verticalLocation, 0, 0, 1, 3, false, 100, null, images);
	}
	
//	/**
//	 * Return the maximum horizontal location of all Mazubs.
//	 */
//	@Basic 
//	@Immutable
//	public static int getMaximumHorizontalLocation(){
//		return maximumHorizontalLocation;
//	}
//	
//	/**
//	 * Return the maximum vertical location of all Mazubs.
//	 */
//	@Basic
//	@Immutable
//	public static int getMaximumVerticalLocation(){
//		return maximumVerticalLocation;
//	}
	
	/**
	 * Return the effective horizontal location of this Mazub as an integer number.
	 */
	@Raw
	public int getEffectiveHorizontalLocation(){
		return (int) Math.floor(this.getHorizontalLocation());
	}
	
	/**
	 * Return the effective vertical location of this Mazub as an integer number.
	 */
	@Raw
	public int getEffectiveVerticalLocation(){
		return (int) Math.floor(this.getVerticalLocation());
	}
	
	
	/**
	 * Check whether the given horizontal location is a valid horizontal location.
	 * 
	 * @param 	horizontalLocation
	 * 		  	The horizontal location to be checked.
	 * @return	True if and only if the given horizontal location is greater than or equal to 0
	 * 			and smaller than the maximum horizontal location plus one.
	 * 			| result == ((horizontalLocation >= 0 ) && (horizontalLocation < maximumHorizontalLocation+1))
	 */
	@Raw
	public boolean canHaveAsHorizontalLocation(double horizontalLocation){
		if (this.getWorld()==null){
			return true;
		}
		else if ((horizontalLocation < 0 ) || (horizontalLocation >= this.getWorld().getWorldWidth()+1)){
			return false;
		}
		else{
			return !this.getWorld().areaCoincidesWithTerrain((int) horizontalLocation, this.getEffectiveVerticalLocation()+1, this.getWidth()-1, this.getHeight()-2);
		}
	}
	
	
	/**
	 * Check whether the given vertical location is a valid vertical location.
	 * 
	 * @param 	verticalLocation
	 * 		  	The vertical location to be checked.
	 * @return	True if and only if the given vertical location is greater than or equal to 0
	 * 			and smaller than the maximums vertical location plus one.
	 * 			|result == ((verticalLocation >= 0 ) && (verticalLocation < maximumVerticalLocation+1))
	 */
	@Raw
	public boolean canHaveAsVerticalLocation(double verticalLocation){
		if (this.getWorld()==null){
			return true;
		}
		else if (verticalLocation<0 || verticalLocation >= this.getWorld().getWorldHeight()+1){
			return false;
		}
		else{
			return !this.getWorld().areaCoincidesWithTerrain(this.getEffectiveHorizontalLocation(), (int) verticalLocation+1, this.getWidth()-1, this.getHeight()-2);
		}
	}
	
	/**
	 * Check whether the given location is a valid location.
	 * 
	 * @param	horizontalLocation
	 * 			The horizontal location to be checked.
	 * @param 	verticalLocation
	 * 		  	The vertical location to be checked.
	 * @return	True if and only if both the horizontal and vertical location are valid locations.
	 * 			|result == (canHaveAsHorizontalLocation(horizontalLocation) && canHaveAsVericalLocation(verticalLocation))
	 */
	public boolean canHaveAsLocation(double horizontalLocation, double verticalLocation){
		return this.canHaveAsHorizontalLocation(horizontalLocation) && this.canHaveAsVerticalLocation(verticalLocation);
	}
	
	/**
	 * Return the calculated horizontal location of this Mazub.
	 */
	@Basic
	@Raw
	public double getHorizontalLocation(){
		return this.horizontalLocation;
	}
	
	/**
	 * Return the calculated vertical location of this Mazub.
	 */
	@Basic
	@Raw
	public double getVerticalLocation(){
		return this.verticalLocation;
	}
	
	/**
	 * Set the calculated horizontal location of this Mazub to the given location.
	 * 
	 * @param 	horizontalLocation
	 * 		  	The new calculated horizontal location for this mazub.
	 * @post  	The new calculated horizontal location of this mazub is set to the given calculated horizontal location.
	 * 		  	| new.getHorizontalLocation() = this.horizontalLocation
	 * @throws	IllegalPositionException
	 * 			The given horizontal location is not valid.
	 * 			| !canHaveAsHorizontalLocation(horizontalLocation)
	 */
	@Raw
	protected void setHorizontalLocation(double horizontalLocation) throws IllegalLocationException{
		if(!canHaveAsHorizontalLocation(horizontalLocation))
			throw new IllegalLocationException(horizontalLocation, this.getVerticalLocation());
		this.horizontalLocation=horizontalLocation;
	}
	
	/**
	 * Set the calculated vertical location of Mazub to the given location.
	 * 
	 * @param 	verticalLocation
	 * 		  	The new calculated vertical location.
	 * @post  	The new calculated vertical location of this Mazub is set to the given vertical location.
	 * 		  	| new.getVerticalLocation() = verticalLocation
	 * @throws	IllegalLocationException
	 * 			The given vertical location is not valid.
	 * 			|!canHaveAsVericalLocation(verticalLocation)	  
	 */
	@Raw
	protected void setVerticalLocation(double verticalLocation) 
			throws IllegalLocationException {
		if(!canHaveAsVerticalLocation(verticalLocation))
			throw new IllegalLocationException(this.getHorizontalLocation(), this.verticalLocation);
		this.verticalLocation = verticalLocation;
	}
	
	public int[] getBorders(){
		return new int[] {this.getEffectiveHorizontalLocation(), this.getEffectiveVerticalLocation(), 
				this.getEffectiveHorizontalLocation()+this.getWidth(), this.getEffectiveVerticalLocation()+this.getHeight()};
	}
	
	/**
	 * Variable registering the calculated horizontal location of this Mazub.
	 */
	private double horizontalLocation = 0;
	
	/**
	 * Variable registering the calculated  vertical location of this Mazub.
	 */
	private double verticalLocation = 0;
	
//	/**
//	 * Variable registering the maximum horizontal location of all Mazubs.
//	 */
//	private final static int maximumHorizontalLocation = 1023;
//	
//	/**
//	 * Variable registering the maximum vertical location of all Mazubs.
//	 */
//	private final static int maximumVerticalLocation = 767;
	
	
	

	
	/**
	 * Return the initial horizontal velocity of this Mazub.
	 * 
	 * @return	If the ducking state of this Mazub is true, the initial horizontal velocity of this Mazub is equal to 1.
	 * 			Otherwise the initial horizontal velocity of this Mazub is equal to its initial horizontal velocity when not ducking.
	 * 			|if (this.isDucking())
	 * 			|	result==1
	 * 			|else
	 * 			| 	result==this.getInitialHorizontalVelocityNotDucking()
	 */
	@Raw
	public double getInitialHorizontalVelocity(){
		if (this.isDucking())
			return 1;
		else 
			return this.getInitialHorizontalVelocityNotDucking();
	}
	
	/**
	 * Return the initial horizontal velocity when not ducking of this Mazub.
	 */
	@Basic
	@Raw
	@Immutable
	public double getInitialHorizontalVelocityNotDucking(){
		return this.initialHorizontalVelocityNotDucking;
	}
	
	/**
	 * Return the initial vertical velocity of all Mazubs.
	 */
	@Basic
	@Immutable
	public static double getInitialVerticalVelocity(){
		return INITIAL_VERTICAL_VELOCITY;
	}
	
	/**
	 * Return the maximum horizontal velocity when not ducking of this Mazub.
	 * 
	 * @return	If the ducking state of this Mazub is true, the maximum horizontal velocity of this Mazub is equal to 1.
	 * 			Otherwise the maximum horizontal velocity of this Mazub is equal to its maximum horizontal velocity when not ducking.
	 * 			|if (this.isDucking())
	 * 			|	result==1
	 * 			|else
	 * 			| 	result==this.getMaximumHorizontalVelocityNotDucking()
	 */
	@Raw
	public double getMaximumHorizontalVelocity(){
		if (this.isDucking())
			return 1;
		else 
			return this.getMaximumHorizontalVelocityNotDucking();
	}
	
	/**
	 * Return the maximum horizontal velocity when not ducking of this Mazub.
	 */
	@Basic
	@Raw
	@Immutable
	public double getMaximumHorizontalVelocityNotDucking(){
		return this.maximumHorizontalVelocityNotDucking;
	}
	
	/**
	 * Check whether this Mazub is currently moving horizontally.
	 * 
	 * @return	True if the current horizontal velocity of this Mazub is not equal to zero.
	 * 			| result == this.getHorizontalVelocity()!=0
	 */
	@Raw
	public boolean isMovingHorizontally(){
		return this.isMovingHorizontally;
	}
	
	private boolean isMovingHorizontally=false;
	
	/**
	 * Set the horizontal velocity of Mazub to the given horizontal velocity.
	 * 
	 * @param 	horizontalVelocity
	 * 			The horizontal velocity.
	 * @post	The given horizontal velocity is set as the new horizontal velocity
	 * 			of Mazub.
	 * 			|new.getHorizontalVelocity() = horizontalVelocity
	 * @throws	IllegalArgumentException
	 * 			The given horizontal velocity is not a valid horizontal velocity.
	 * 			|!this.canHaveAsHorizontalVelocity(horizontalVelocity)
	 */
	@Raw
	public void setHorizontalVelocity(double horizontalVelocity) 
		throws IllegalArgumentException{
		if(!this.canHaveAsHorizontalVelocity(horizontalVelocity))
			throw new IllegalArgumentException("Not a valid horizontal velocity!");
		this.horizontalVelocity = horizontalVelocity;
	}
	
	/**
	 * Set the vertical velocity of Mazub to the given vertical velocity.
	 * 
	 * @param 	verticalVelocity
	 * 			The vertical velocity to be set.
	 * @post	The given vertical velocity is set as the new vertical velocity of this Mazub.
	 * 			|new.getVerticalVelocity() = verticalVelocity
	 * @throws	IllegalArgumentException
	 * 			The given vertical velocity is not a valid vertical velocity.
	 * 			|!isValidVerticalVelocity(verticalVelocity)
	 */ 
	@Raw
	public void setVerticalVelocity(double verticalVelocity) 
		throws IllegalArgumentException{
		if(!isValidVerticalVelocity(verticalVelocity))
			throw new IllegalArgumentException("Not a valid vertical velocity!");
		this.verticalVelocity = verticalVelocity;
	}
	
	

	/**
	 * Check whether the given initial horizontal velocity is a valid horizontal velocity.
	 * 
	 * @param 	initialHorizontalVelocity
	 * 			The initial horizontal velocity to be checked.
	 * @return	True if and only if the given initial horizontal velocity is a possible initial horizontal 
	 * 			velocity for any Mazub and it matches with the maximum horizontal velocity of this Mazub.
	 * 			|result ==  isPossibleInitialHorizontalVelocity(initialHorizontalVelocity) && 
				matchesMaximumHorizontalVelocityInitialHorizontalVelocity(this.getMaximumHorizontalVelocity(), initialHorizontalVelocity)
	 */
	@Raw
	public boolean canHaveAsInitialHorizontalVelocity(double initialHorizontalVelocity){
		return isPossibleInitialHorizontalVelocity(initialHorizontalVelocity) && 
				matchesMaximumHorizontalVelocityInitialHorizontalVelocity(this.getMaximumHorizontalVelocity(), initialHorizontalVelocity);
	}
	
	
	/**
	 *  Check whether the given maximum horizontal velocity is a valid maximum horizontal velocity.
	 *  
	 * @param 	maximumHorizontalVelocity
	 * 			The maximum horizontal velocity to be checked.
	 * @return	True if and only if the given maximum horizontal velocity matches with the initial horizontal
	 * 			velocity of this Mazub.
	 * 			|result =  matchesMaximumHorizontalVelocityInitialHorizontalVelocity(maximumHorizontalVelocity, this.getInitialHorizontalVelocity());
	 */
	@Raw
	public boolean canHaveAsMaximumHorizontalVelocity(double maximumHorizontalVelocity){
		return matchesMaximumHorizontalVelocityInitialHorizontalVelocity(maximumHorizontalVelocity, this.getInitialHorizontalVelocity());
	}
	
	/**
	 * Check whether the given maximum horizontal velocity matches with the given initial horizontal velocity of a Mazub.
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
	 * Check whether the given initial horizontal velocity is a possible initial horizontal velocity for any Mazub.
	 * 
	 * @param 	initialHorizontalVelocity
	 * 			The initial horizontal velocity to check.
	 * @return	True if the given initial horizontal velocity is greater than or equal to 1.
	 * 			result == Util.fuzzyGreaterThanOrEqualTo(initialHorizontalVelocity, 1)
	 */
	public static boolean isPossibleInitialHorizontalVelocity(double initialHorizontalVelocity){
		return Util.fuzzyGreaterThanOrEqualTo(initialHorizontalVelocity, 1);
	}
	
	
	/**
	 * Check whether the given horizontal velocity is a valid horizontal velocity.
	 * 
	 * @param 	horizontalVelocity
	 * 			The horizontal velocity to be checked.
	 * @return	True if and only if either the absolute value of the given horizontal velocity is greater than or equal
	 * 			to the initial horizontal velocity of this Mazub and is less than or equal to the maximum horizontal
	 * 			velocity of this Mazub or the given horizontal velocity is equal to zero.
	 * 			|result == (Util.fuzzyGreaterThanOrEqualTo(abs(horizontalVelocity),this.getInitialHorizontalVelocity()) 
				|  && Util.fuzzyLessThanOrEqualTo(abs(horizontalVelocity), this.getMaximumHorizontalVelocity()))
				|  || Util.fuzzyEquals(horizontalVelocity, 0);
	 */
	@Raw
	public boolean canHaveAsHorizontalVelocity(double horizontalVelocity){
		horizontalVelocity = Math.abs(horizontalVelocity);
		return Util.fuzzyGreaterThanOrEqualTo(horizontalVelocity,this.getInitialHorizontalVelocity()) 
				&& Util.fuzzyLessThanOrEqualTo(horizontalVelocity, this.getMaximumHorizontalVelocity())
				|| Util.fuzzyEquals(horizontalVelocity, 0);
	}
	
	/**
	 * Check whether the given vertical velocity is a valid vertical velocity.
	 * 
	 * @param 	verticalVelocity
	 * 			The vertical velocity to be checked.
	 * @return	True if and only if the given vertical velocity is less than or equal to 8 m/s (the initial vertical velocity constant).
	 * 			| result ==  Util.fuzzyLessThanOrEqualTo(verticalVelocity, getInitialVerticalVelocity())
	 */
	@Override
	public boolean isValidVerticalVelocity(double verticalVelocity){
		return Util.fuzzyLessThanOrEqualTo(verticalVelocity, getInitialVerticalVelocity());
	}
	
	/**
	 *  Constant registering the initial vertical velocity of all Mazubs.
	 */
	private static final double INITIAL_VERTICAL_VELOCITY = 8;
	
	/**
	 * Variable registering the initial horizontal velocity when not ducking of this Mazub.
	 */
	private final double initialHorizontalVelocityNotDucking;
	
	/**
	 * Variable registering the maximum horizontal velocity when not ducking of this Mazub.
	 */
	private final double maximumHorizontalVelocityNotDucking;
	
	
	/**
	 * Returns the horizontal acceleration of this Mazub.
	 * 
	 * @return	If this Mazub is not moving horizontally or the absolute value of this Mazub's horizontal 
	 * 			velocity is greater than its maximum horizontal velocity, the horizontal acceleration of this Mazub is equal to zero.
	 * 			Otherwise the horizontal acceleration of this Mazub is equal to horizontalAcceleration.
	 * 			| if (!this.isMovingHorizontally() || Util.fuzzyEquals(Math.abs(this.getHorizontalVelocity()), this.getMaximumHorizontalVelocity()))
	 * 			| 	result==0
	 * 			| else
	 * 			| 	result==horizontalAcceleration
	 */
	@Raw
	@Override
	public double getHorizontalAcceleration(){
		if (!this.isMovingHorizontally() || 
				Util.fuzzyEquals(Math.abs(this.getHorizontalVelocity()), this.getMaximumHorizontalVelocity())){
			return 0;
		}
		else{
			return horizontalAcceleration;
		}
	}
	
	/**
	 * Returns the vertical acceleration of this Mazub.
	 * 
	 * @return	If this Mazub is jumping than the vertical acceleration is equal
	 * 			to the gravitational acceleration constant.
	 * 			Otherwise the vertical acceleration is equal to zero.
	 * 			|if(this.isJumping())
	 *			|	result == VERTICAL_ACCELERATION
	 *			|else
	 *			| 	result == 0
	 */
	@Raw
	@Override
	public double getVerticalAcceleration(){
		if(this.isJumping())
			return VERTICAL_ACCELERATION;
		else
			return 0;
	}
	
	/**
	 *  Variable registering the horizontal acceleration of all Mazubs.
	 */
	private static final double horizontalAcceleration = 0.9;
	
	/**
	 *  Constant registering the vertical acceleration of all Mazubs.
	 */
	public static final double VERTICAL_ACCELERATION = -10;
	
	
	/**
	 * Check whether the given Mazub is ducking or not.
	 * 
	 * @return	True if and only if the Mazub is ducking.
	 * 			| result == this.ducking
	 */
	@Basic 
	@Raw
	public boolean isDucking() {
		return this.ducking;
	}
	
	/**
	 * Set the ducking state of this Mazub to the given ducking state.
	 * 
	 * @param 	ducking
	 * 			The new ducking state for this Mazub.
	 * @post	The new ducking state of this Mazub is equal to 
	 * 			the state expressed by the variable ducking.
	 * 			| new.isDucking() = ducking
	 */
	@Raw
	public void setDucking(boolean ducking) throws IllegalStateException{
		if(this.isDucking() && !this.canStandUp() && ducking==false){
			throw new IllegalStateException();
		}
		this.ducking = ducking;
	}
	
	
	/**
	 * Variable registering the ducking state of this Mazub.
	 */
	private boolean ducking = false;
	
	/**
	 * Check whether this Mazub is performing a jump.
	 * 
	 * @return	True if and only if either the vertical location or the vertical velocity of this mazub is greater than zero.
	 * 			|(0 < getVerticalVelocity()) || (0 < getVerticalLocation())
	 */
	public boolean isJumping(){
		return this.isJumping;
	}
	
	private boolean isJumping=false;
	
	
	/**
	 * Start the horizontal movement of this Mazub in the given direction.
	 * 
	 * @param 	direction
	 * 			The desired direction in which this Mazub will move.
	 * @post 	The new horizontal velocity of this Mazub is equal to the given direction times its initial horizontal velocity.
	 * 			| new.getHorizontalVelocity() == direction*this.getInitialHorizontalVelocity()
	 * @effect 	The direction of this mazub is set the given direction.
	 * 			| this.setDirection(direction)
	 * @post	The new time since this Mazub has last ended moving is 0.
	 * 			| new.getTimeSinceEndMove()==0
	 * @post	The new time since this Mazub has started moving is 0.
	 * 			| new.getTimeSinceStartMove()==0
	 * @note	setDirection has a precondition for direction.
	 */
	@Raw
	public void startMove(Direction direction){
		if (this.isMovingHorizontally()==false){
			this.isMovingHorizontally=true;
			this.setDirection(direction);
			this.setHorizontalVelocity((direction.getNumberForCalculations())*this.getInitialHorizontalVelocity());
			this.setTimeSinceStartMove(0);
			this.setTimeSinceEndMove(0);
		}
	}
	
	
	/**
	 * End the horizontal movement of this Mazub.
	 * 
	 * @post	The new horizontal velocity of this Mazub is equal to 0.
	 * 			| new.getHorizontalVelocity()= 0
	 * @post	The new time since this Mazub has last ended moving is 0.
	 * 			| new.getTimeSinceEndMove()==0
	 * @post	The new time since this Mazub has started moving is 0.
	 * 			| new.getTimeSinceStartMove()==0
	 */
	@Raw
	public void endMove(){
		this.isMovingHorizontally=false;
		this.setHorizontalVelocity(0);
		this.setTimeSinceEndMove(0);
		this.setTimeSinceStartMove(0);
	}
	
	/**
	 * Make this mazub jump if this Mazub isn't jumping already.
	 * 
	 * @effect	If this mazub isn't jumping, the vertical velocity of this mazub is set to the initial vertical velocity.
	 * 			| if (!isJumping())
	 * 			| this.setVerticalVelocity(INITIAL_VERTICAL_VELOCITY)
	 * @note	The method setVerticalVelocity will never throw an exception in 
	 * 			its current implementation because INITIAL_VERTICAL_VELOCITY is a valid vertical velocity.
	 * 			There is no need to add a try catch statement.
	 */
	@Raw
	public void startJump(){
		if (!isJumping()){
			this.isJumping=true;
			this.setVerticalVelocity(getInitialVerticalVelocity());
		}
	}
	
	
	/**
	 * End the jump of this mazub if this Mazub is travelling upwards.
	 * 
	 * @effect	If this mazub is travelling upwards, then this mazub's vertical velocity is set to zero.
	 * 			| if(this.getVerticalVelocity()>0)
	 * 			|	this.setVerticalVelocity(0)
	 * @note	The method setVerticalVelocity will never throw an exception in 
	 * 			its current implementation because 0 is a valid vertical velocity.
	 * 			There is no need to add a try catch statement.
	 */
	@Raw
	public void endJump(){
		if (this.getVerticalVelocity()>0){
			this.setVerticalVelocity(0);
		}
	}
	
	/**
	 * Make this Mazub duck.
	 * 
	 * @effect	The ducking state of this Mazub is set to true.
	 * 			| this.setDucking(true)
	 */
	public void startDuck(){
		this.setDucking(true);
		this.canEndDuck=false;
	}
	
	/**
	 * End this Mazub's duck.
	 * 
	 * @effect	The ducking state of this Mazub is set to false.
	 * 			| this.setDucking(false)
	 */
	public void endDuck(){
		try{
			this.setDucking(false);
		} catch (IllegalStateException exc){
			this.canEndDuck=true;
		}	
	}
	
	private boolean canEndDuck=false;
	
	public boolean canStandUp(){
		if(!this.isDucking()){
			return true;
		}
		else{
			return !this.getWorld().areaCoincidesWithTerrain(this.getEffectiveHorizontalLocation(), this.getEffectiveVerticalLocation()+1, this.getImageAt(0).getWidth()-1, this.getImageAt(0).getHeight());
		}
	}
	
	/**
	 * Update the location and velocity of this Mazub.
	 * 
	 * @param 	deltaTime
	 * 			The period of time that is used to update this Mazub.
	 * @effect	The horizontal and vertical location are updated
	 * 			and the horizontal and vertical velocity are updated.
	 * 			|updateHorizontalLocation(deltaTime)
	 *			|updateVerticalLocation(deltaTime);
	 *			|updateHorizontalVelocity(deltaTime);
	 *			|updateVerticalVelocity(deltaTime);
	 * @throws	IllegalArgumentException 
	 * 			The given time period is not valid a valid time period.
	 * 			|!isValidTimePeriod(deltaTime)
	 */
	@Raw
	@Override
	public void advanceTime(double deltaTime)
		throws IllegalArgumentException {
		if(!isValidDeltaTime(deltaTime))
			throw new IllegalArgumentException();
		double deltaTimeForPixel=0;
		double sumDeltaTimeForPixel=0;
		double newHorizontalLocation=this.getHorizontalLocation();
		double newVerticalLocation=this.getVerticalLocation();
		double newHorizontalVelocity=this.getHorizontalVelocity();
		double newVerticalVelocity=this.getVerticalVelocity();
		double oldHorizontalLocation=this.getHorizontalLocation();
		double oldVerticalLocation=this.getVerticalLocation();
		if(!this.isMovingHorizontally()){
			this.setTimeSinceEndMove(this.getTimeSinceEndMove()+deltaTime);
		}
		else{
			this.setTimeSinceStartMove(this.getTimeSinceStartMove()+deltaTime);
		}
		while (sumDeltaTimeForPixel<deltaTime){
			deltaTimeForPixel= getDeltaTimeForPixel(deltaTime);
			newVerticalVelocity = this.getVerticalVelocity() + getVerticalAcceleration()*deltaTimeForPixel;
			newHorizontalVelocity = this.getHorizontalVelocity() + this.getDirection().getNumberForCalculations()*getHorizontalAcceleration()*deltaTimeForPixel;
			newHorizontalLocation = this.getHorizontalLocation() + 
					100*(this.getHorizontalVelocity()*deltaTimeForPixel + 
					this.getDirection().getNumberForCalculations()*0.5*getHorizontalAcceleration()*Math.pow(deltaTimeForPixel, 2));
			newVerticalLocation = this.getVerticalLocation() + 100*(getVerticalVelocity()*deltaTimeForPixel + 0.5*getVerticalAcceleration()*Math.pow(deltaTimeForPixel,2));
			sumDeltaTimeForPixel+=deltaTimeForPixel;
			try{
				this.setHorizontalVelocity(newHorizontalVelocity);
			} catch(IllegalArgumentException exc){
				if (Math.abs(newHorizontalVelocity)<Math.abs(this.getInitialHorizontalVelocityNotDucking()))
					this.setHorizontalVelocity(this.getDirection().getNumberForCalculations()*this.getInitialHorizontalVelocityNotDucking());
				else
					this.setHorizontalVelocity(this.getDirection().getNumberForCalculations()*this.getMaximumHorizontalVelocity());
			}
			try{
				this.setVerticalVelocity(newVerticalVelocity);
			} catch (IllegalArgumentException exc){
				this.setVerticalVelocity(0);
			}
			try{
				this.setHorizontalLocation(newHorizontalLocation);
				oldHorizontalLocation=newHorizontalLocation;
			} catch(IllegalLocationException exc){
				this.setHorizontalLocation((int) oldHorizontalLocation);
				this.setHorizontalVelocity(0);
			}
			try{
				this.setVerticalLocation(newVerticalLocation);
				oldVerticalLocation=newVerticalLocation;
			} catch(IllegalLocationException exc){
				this.setVerticalLocation(oldVerticalLocation);
				this.setVerticalVelocity(0);
			}
			if(this.canEndDuck){
				this.endDuck();
			}
		}
		this.calculateNewJumpingState();
	}
	
	private void calculateNewJumpingState() {
		this.isJumping=true;
		int coincidingTiles[][]= this.getWorld().getTilePositionsIn(this.getEffectiveHorizontalLocation()+1, this.getEffectiveVerticalLocation(), 
				this.getEffectiveHorizontalLocation()+this.getWidth()-1, this.getEffectiveVerticalLocation()+this.getHeight());
		for(int tiles=0; tiles<Math.sqrt(coincidingTiles.length); tiles++){
			if(this.getWorld().getTileValueAtTilePosition(coincidingTiles[tiles][0],coincidingTiles[tiles][1])==1){
				this.isJumping=false;
			}
		}
	}

	
	/**
	 * Check whether the given deltaTime is a valid time period.
	 * 
	 * @param 	deltaTime
	 * 			The period of time to be checked.
	 * @return	True if and only if the time period is smaller
	 * 			than 0.2s and greater than or equal to zero.
	 * 			| result == Util.fuzzyGreaterThanOrEqualTo(deltaTime,0) && deltaTime < 0.2
	 */
	public static boolean isValidDeltaTime(double deltaTime){
		return Util.fuzzyGreaterThanOrEqualTo(deltaTime,0) && deltaTime < 0.2;
	}
	
	
	
	/**
	 * Return the current Sprite of this Mazub.
	 */
	@Override
	public Sprite getCurrentSprite(){
		int newSpriteIndex=Integer.MAX_VALUE;
		if (!this.isMovingHorizontally() && !this.isDucking() && this.getTimeSinceEndMove()>1)
			newSpriteIndex=0;
		else if (!this.isMovingHorizontally() && this.isDucking() && this.getTimeSinceEndMove()>1)
			newSpriteIndex=1;
		else if (!this.isMovingHorizontally() && !this.isDucking() && this.getTimeSinceEndMove()<=1 && this.getDirection()==Direction.RIGHT)
			newSpriteIndex=2;
		else if (!this.isMovingHorizontally() && !this.isDucking() && this.getTimeSinceEndMove()<=1 && this.getDirection()==Direction.LEFT)
			newSpriteIndex=3;
		else if (this.isMovingHorizontally() && this.getDirection()==Direction.RIGHT && this.isJumping() && !this.isDucking())
			newSpriteIndex=4;
		else if (this.isMovingHorizontally() && this.getDirection()==Direction.LEFT && this.isJumping() && !this.isDucking())
			newSpriteIndex=5;
		else if ((this.isDucking() && this.getDirection()==Direction.RIGHT && this.getTimeSinceEndMove()<=1) || 
				(this.isDucking() && this.getDirection()==Direction.RIGHT && this.getTimeSinceEndMove()>1 && this.isMovingHorizontally()))
			newSpriteIndex=6;
		else if ((this.isDucking() && this.getDirection()==Direction.LEFT && this.getTimeSinceEndMove()<=1) ||
				(this.isDucking() && this.getDirection()==Direction.LEFT && this.getTimeSinceEndMove()>1 && this.isMovingHorizontally()))
			newSpriteIndex=7;
		else if(!this.isJumping() && !this.isDucking()){
			int i = (int) Math.floor(this.getTimeSinceStartMove()/0.075);
			int m=(this.getNbImages()-8)/2;
			if (this.getDirection()==Direction.RIGHT){
			newSpriteIndex=i%m+8;
			}
			else{
				newSpriteIndex=i%m+8+m;
			}
		}
		return this.getImageAt(newSpriteIndex);
	}
	
	/**
	 * Return a copy of the current image array of this Mazub.
	 */
	@Basic 
	@Raw
	public Sprite[] getImages(){
		return this.images.clone();
	}
	
	/**
	 * Return the number of images in the current image array of this Mazub.
	 */
	@Raw
	public int getNbImages(){
		return this.getImages().length;
	}
	
	/**
	 * Check whether the given number of images in the given image array is valid for all Mazubs.
	 * 
	 * @param 	nbImages
	 * 			The number of images to be checked.
	 * @return	The given number of images must be an even number and must be greater than or equal to 10;
	 * 			| result == (nbImages >= 10 && nbImages%2==0)
	 * 
	 */
	@Override
	public boolean isValidNbImages(int nbImages){
		return nbImages>=10 && nbImages%2==0;
	}
	
	/**
	 * Return the image in the image array of this Mazub at the given sprite index.
	 * 
	 * @pre		The given sprite index must be a valid sprite index.
	 * 			| isValidSpriteIndex(spriteIndex)
	 */
	@Basic
	@Raw
	public Sprite getImageAt(int spriteIndex) throws IllegalArgumentException{
		assert isValidSpriteIndex(spriteIndex):
			"The given sprite index is not a valid sprite index!";
		return this.getImages()[spriteIndex];
	}
	
	
	/**
	 * Check whether to given sprite index is a valid sprite index.
	 * 
	 * @param 	spriteIndex
	 * 			The sprite index to be checked.
	 * @return	True if the given sprite index is greater than or equal to zero and 
	 * 			smaller than or equal to the number of images in the current image array of this Mazub.
	 * 			| result == (spriteIndex>=0 && spriteIndex<=this.getNbImages())
	 */
	@Raw
	@Override
	public boolean isValidSpriteIndex(int spriteIndex){
		return spriteIndex>=0 && spriteIndex<=this.getNbImages();
	}
	
	/**
	 * Check whether the given image is a valid image.
	 * 
	 * @param 	image
	 *			The image to be checked.
	 *@return	image!=null
	 */
	public static boolean isValidImage(Sprite image){
		return image!=null;
	}
	
	/**
	 * Set the image array of this Mazub to the given image array.
	 * 
	 * @param 	images
	 * 			The new image array for this Mazub.
	 * @pre 	The length of the given image array must be a valid length.
	 * 			|isValidNbImages(images.length)
	 * @pre		The images in the given image array must all be valid images.
	 * 			| for i in 1..images.length:
	 * 			|	isValidImage(images[i])
	 * @post	The new image array of this Mazub is equal to copy of he given image array.
	 * 			| this.images==images.clone()
	 */
	@Raw
	@Override
	public void setImages(Sprite[] images){
		assert isValidNbImages(images.length):
			"Not a valid number of images in the given image array!";
		for (Sprite image:images){
			assert isValidImage(image):
				"The given image array contains at least one image that isn't valid!";		
		}
		this.images=images.clone();
	}
	
	
	/**
	 * Return the height of the currrent sprite of this Mazub.
	 */
	@Raw
	public int getHeight(){
		return this.getCurrentSprite().getHeight();
	}
	
	/**
	 * Return the width of the current sprite of this Mazub.
	 */
	@Raw
	public int getWidth(){
		return this.getCurrentSprite().getWidth();
	}

	/**
	 * Variable registering the array of images of this Mazub.
	 */
	private Sprite images[];
	
	
	/**
	 * Return the current number of hit-points of this Mazub.
	 */
	@Basic
	@Raw
	public int getHitPoints() {
		return this.hitPoints;
	}

	
	/**
	 * Set the hit-points of this Mazub to the given hit-points.
	 * 
	 * @param 	hitPoints
	 * 		  	The new number of hit-points for this Mazub.
	 * @post	If the given number of hit-points is greater than or equal to the maximum number of hit-points, 
	 * 			the new number of hit-points of this Mazub is set to the maximum number of hit-points.
	 * 		  	| if (hitPoints>=MAX_HIT_POINTS)
	 *			|		new.hitPoints == MAX_HIT_POINTS
	 * @post	If the given number of hit-points is negative or equal to zero, 
	 * 			the new number of hit-points of this Mazub is set to 0.
	 * 		  	| if (hitPoints<=0)
	 *			|		new.hitPoints == 0
	 * @post	If the given number of hit-points is in the pre-established range of hit-points for a Mazub,
	 * 			the new number of hit-points of this Mazub is set to the given number of hit-points.
	 * 			| if (hitPoints>0 && hitPoints<500){
	 * 			|		new.hitPoints==hitPoints		
	 */
	@Basic
	@Raw
	public void setHitPoints(int hitPoints) {
		if (hitPoints>=MAX_HIT_POINTS){
			this.hitPoints = MAX_HIT_POINTS;
		}
		else if (hitPoints<=0){
			this.hitPoints = 0;
		}
		else{
			this.hitPoints= hitPoints;
		}
	}
	
	/**
	 * Variable registering the number of hit-points of this Mazub. 
	 */
	private int hitPoints=100;
	
	private final static int MAX_HIT_POINTS=500;
	
	public void setWorld(World world) throws IllegalArgumentException{
		if (world==null || world.canHaveAsGameObject(this)){
			this.world=world;
		}
		else{
			throw new IllegalArgumentException("Not a valid world!");
		}
	}
	
	public World getWorld(){
		return this.world;
	}
	
	private World world;
	
	public boolean isTerminated() {
		return this.isTerminated;
	}

	public void terminate() {
		this.getWorld().terminate();
		this.isTerminated=true;
	}
	
	private boolean isTerminated=false;
}
