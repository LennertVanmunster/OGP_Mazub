package jumpingalien.model;


import jumpingalien.util.Sprite;
import jumpingalien.util.Util;
import be.kuleuven.cs.som.annotate.*;

/**
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
 * @version 1.0
 * @authors Pieter Van Damme and Lennert Vanmunster
 *
 */
public class Mazub {
	/**
	 *Initialize a new Mazub with given horizontal and vertical location,
	 *horizontal and vertical velocity, initial and maximum horizontal velocity,
	 *ducking state and a set of sprites. 
	 * 
	 * @param 	horizontalLocation
	 * 		  	The horizontal location of Mazub.
	 * @param 	verticalLocation
	 * 		  	The vertical location of Mazub.
	 * @param 	horizontalVelocity
	 * 		  	The horizontal velocity of Mazub.
	 * @param 	verticalVelocity
	 * 		  	The vertical velocity of Mazub.
	 * @param 	ducking
	 * 		  	Parameter to tell if Mazub is ducking or not.
	 * @param 	initialHorizontalVelocity
	 * 		  	The initial horizontal velocity of Mazub when he starts running.
	 * @param 	maximumHorizontalVelocityNotDucking
	 * 		  	The maximum horizontal velocity of Mazub while he is not ducking.
	 * @param 	images
	 * 		  	Sprites to display Mazub.
	 * @post	The horizontal location of this new Mazub is equal to the 
	 * 			given horizontal location.
	 * 			|new.getHorizontalLocation() = horizontalLocation
	 * @post	The vertical location of this new Mazub is equal to the 
	 * 			given vertical location.
	 * 			|new.getVerticalLocation() = verticalLocation
	 * @post	The initial horizontal velocity of this new Mazub is equal to the 
	 * 			given initial horizontal velocity.
	 * 			|new.getInitialHorizontalVelocity = initialHorizontalVelocity
	 * @post	The maximum horizontal velocity (ducking and not ducking) of this new Mazub is equal to the 
	 * 			given maximum horizontal velocity.
	 * 			|new.getMaximumHorizontalVelocityNotDucking() = maximumHorizontalVelocityNotDucking
	 * 			|new.getMaximumHorizontalVelocity() = maximumHorizontalVelocityNotDucking
	 * @post	The horizontal velocity of this new Mazub is equal to the 
	 * 			given horizontal velocity.
	 * 			|new.getHorizontalVelocity() = horizontalVelocity
	 * @post	The vertical velocity of this new Mazub is equal to the 
	 * 			given vertical velocity.
	 * 			|new.getVerticalVelocity() = verticalVelocity
	 * @post	The direction of this new Mazub is equal to the 
	 * 			given direction.
	 * 			|new.getDirection() = direction
	 * @post	The ducking state of this new Mazub is equal to the 
	 * 			given ducking state.
	 * 			|new.isDucking = ducking
	 * @post	If the ducking state of the new Mazub is true the Mazub
	 * 			starts ducking.
	 * 			|if(isDucking())
	 *			|	startDuck()
	 * @post	The images of this new Mazub are equal to the 
	 * 			given images.
	 * 			|new.getImages() = images	
	 * @throws	IllegalArgumentException
	 * 			Not a valid horizontal location
	 * 			|!isValidHorizontalLocation(horizontalLocation)	
	 * @throws	IllegalArgumentException
	 * 			Not a valid vertical location
	 * 			|!isValidVerticalLocation(verticalLocation)	
	 * @throws	IllegalArgumentException
	 * 			Not a valid initial horizontal velocity
	 * 			|!canHaveAsInitialHorizontalVelocity(initialHorizontalVelocity)	
	 * @throws	IllegalArgumentException
	 * 			Not a valid maximum horizontal velocity!
	 * 			|!canHaveAsMaximumHorizontalVelocity(maximumHorizontalVelocityNotDucking)	
	 * @throws	IllegalArgumentException
	 * 			Not a valid horizontal velocity
	 * 			|!canHaveAsHorizontalVelocity(horizontalVelocity)
	 * @throws	IllegalArgumentException
	 * 			Not a valid vertical velocity
	 * 			|!isValidVerticalVelocity(verticalVelocity)
	 * @throws	IllegalArgumentException
	 * 			Not a valid direction
	 * 			|!isValidDirection(direction)	
	 */
	@Raw
	public Mazub(int horizontalLocation, int verticalLocation, double horizontalVelocity,
				double verticalVelocity, double initialHorizontalVelocity,
				double maximumHorizontalVelocityNotDucking, boolean ducking, int direction, Sprite... images)
		throws IllegalArgumentException {
		setHorizontalLocation(horizontalLocation);
		setVerticalLocation(verticalLocation);
		setHorizontalVelocity(horizontalVelocity);
		setVerticalVelocity(verticalVelocity);
		setDucking(ducking);
		if(!isPossibleInitialHorizontalVelocity(initialHorizontalVelocity))
			throw new IllegalArgumentException("Not a valid initial horizontal velocity!");
		this.initialHorizontalVelocity = initialHorizontalVelocity;
		setMaximumHorizontalVelocity(maximumHorizontalVelocityNotDucking);
		this.maximumHorizontalVelocityNotDucking = maximumHorizontalVelocityNotDucking;
		if(isDucking())
			startDuck();
		if(!isValidDirection(direction))
			throw new IllegalArgumentException("Not a valid direction!");
		setDirection(direction);
		this.setImages(images);
	}
	
	@Raw
	public Mazub(int horizontalLocation, int verticalLocation, Sprite... images){
		this(horizontalLocation, verticalLocation, 0, 0, 1, 3, false, 1, images);
	}
	
	/**
	 * Returns the maximum horizontal location of all Mazubs.
	 */
	@Basic 
	@Immutable
	public static int getMaximumHorizontalLocation(){
		return maximumHorizontalLocation;
	}
	
	/**
	 * Returns the maximum vertical location of all Mazubs.
	 */
	@Basic
	@Immutable
	public static int getMaximumVerticalLocation(){
		return maximumVerticalLocation;
	}
	
	/**
	 * Returns the horizontal location of this Mazub as an integer number.
	 */
	@Raw
	public int getHorizontalLocation(){
		return (int) Math.floor(this.getHorizontalLocationNotRounded());
	}
	
	/**
	 * Returns the vertical location of this Mazub as an integer number.
	 */
	@Raw
	public int getVerticalLocation(){
		return (int) Math.floor(this.getVerticalLocationNotRounded());
	}
	
	/**
	 * Set the horizontal location of this Mazub to the given location.
	 * 
	 * @param 	horizontalLocation
	 * 			The new horizontal location for this mazub.
	 * @effect	The new horizontal location of this mazub is set to the given horizontal location.
	 * 			| this.setHorizontalLocationNotRounded(horizontalLocation)
	 */
	@Raw
	public void setHorizontalLocation(int horizontalLocation){
		this.setHorizontalLocationNotRounded(horizontalLocation);
	}
	
	/**
	 * Set the vertical location of this Mazub to the given location.
	 * 
	 * @param 	verticalLocationNotRounded
	 * 		  	The new vertical location.
	 * @effect  The new vertical location of this mazub is set to the given vertical location.
	 * 		  	| this.setVerticalLocationNotRounded(verticalLocation)
	 */
	@Raw
	public void setVerticalLocation(int verticalLocation){
		this.setVerticalLocationNotRounded(verticalLocation);
	}
	
	/**
	 * Check whether the given horizontal location is a valid location.
	 * 
	 * @param 	horizontalLocation
	 * 		  	The horizontal location to be checked.
	 * @return	True if and only if the horizontal location is greater than or equal to 0
	 * 			and smaller than or equal to the maximum horizontal location.
	 * 			| result == ((horizontalLocation >= 0 ) && (horizontalLocation <= maximumHorizontalLocation));
	 */
	@Raw
	public static boolean isValidHorizontalLocation(double horizontalLocation){
		return ((horizontalLocation >= 0 ) && (horizontalLocation < getMaximumHorizontalLocation()+1));
	}
	
	
	/**
	 * Check whether the given vertical location is a valid location.
	 * 
	 * @param 	verticalLocation
	 * 		  	The vertical location.
	 * @return	True if and only if the vertical location is
	 * 			in the range of 0 to maximumVerticalLocation.
	 * 			|result == ((verticalLocation >= 0 ) && (verticalLocation <= maximumVerticalLocation));
	 */
	@Raw
	public static boolean isValidVerticalLocation(double verticalLocation){
		return ((verticalLocation >= 0 ) && (verticalLocation < getMaximumVerticalLocation()+1));
	}
	
	/**
	 * Check whether the given location is a valid location.
	 * 
	 * @param	horizontalLocation
	 * 			The horizontal location to be checked.
	 * @param 	verticalLocation
	 * 		  	The vertical location to be checked.
	 * @return	True if and only if the horizontal and vertical location are valid locations.
	 * 			|result == (isValidHorizontalLocation(horizontalLocation) && isValidVerticalLocation(verticalLocation));
	 */
	public static boolean isValidLocation(double horizontalLocation, double verticalLocation){
		return (isValidHorizontalLocation(horizontalLocation) && isValidVerticalLocation(verticalLocation));
	}
	
	/**
	 * Returns the calculated horizontal location of this Mazub.
	 */
	@Basic
	@Raw
	private double getHorizontalLocationNotRounded(){
		return this.horizontalLocationNotRounded;
	}
	
	/**
	 *Returns the vertical location of this Mazub.
	 */
	@Basic
	@Raw
	private double getVerticalLocationNotRounded(){
		return this.verticalLocationNotRounded;
	}
	
	/**
	 * Set the horizontal location of this Mazub to the given location.
	 * 
	 * @param 	horizontalLocationNotRounded
	 * 		  	The new calculated horizontal location for this mazub.
	 * @post  	The new horizontal location of this mazub is set to the given horizontal location.
	 * 		  	| new.getHorizontalLocationNotRounded() = this.horizontalLocationNotRounded
	 * @throws	IllegalArgumentException
	 * 			The given horizontal location is not valid.
	 * 			| !isValidHorizontalLocation(horizontalLocationNotRounded)
	 */
	@Raw
	private void setHorizontalLocationNotRounded(double horizontalLocationNotRounded) throws IllegalArgumentException{
		if(!isValidHorizontalLocation(horizontalLocationNotRounded))
			throw new IllegalArgumentException("Not a valid horizontal location!");
		this.horizontalLocationNotRounded=horizontalLocationNotRounded;
	}
	
	/**
	 * Set the calculated vertical location of Mazub to the given location.
	 * 
	 * @param 	verticalLocationNotRounded
	 * 		  	The new calculated vertical location.
	 * @post  	The new calculated vertical location of this mazub is set to the given vertical location.
	 * 		  	| new.getVerticalLocationNotRounded() = verticalLocationNotRounded
	 * @throws	IllegalArgumentException
	 * 			The given vertical location is not valid.
	 * 			|!isValidVerticalLocation(verticalLocationNotRounded)	  
	 */
	@Raw
	private void setVerticalLocationNotRounded(double verticalLocationNotRounded) 
			throws IllegalArgumentException {
		if(!isValidVerticalLocation(verticalLocationNotRounded))
			throw new IllegalArgumentException("Not a valid vertical location!");
		this.verticalLocationNotRounded = verticalLocationNotRounded;
	}
	
	/**
	 * Variable registering the horizontal location of this Mazub.
	 */
	private double horizontalLocationNotRounded = 0;
	
	/**
	 * Variable registering the vertical location of this Mazub.
	 */
	private double verticalLocationNotRounded = 0;
	
	/**
	 * Variable registering the maximum horizontal location of all Mazubs.
	 */
	private final static int maximumHorizontalLocation = 1023;
	
	/**
	 * Variable registering the maximum vertical location of all Mazubs.
	 */
	private final static int maximumVerticalLocation = 767;
	
	
	
	
	
	/**
	 * Returns the horizontal velocity of this Mazub.
	 */
	@Basic
	@Raw 
	public double getHorizontalVelocity() {
		return this.horizontalVelocity;
	}
	
	/**
	 * Returns the vertical velocity of this Mazub.
	 */
	@Basic 
	@Raw
	public double getVerticalVelocity() {
		return this.verticalVelocity;
	}
	
	/**
	 * Returns the initial horizontal velocity of this Mazub.
	 */
	@Basic 
	@Raw
	public double getInitialHorizontalVelocity(){
<<<<<<< HEAD
		if (this.isDucking())
			return 1;
		else 
			return this.initialHorizontalVelocity;	
		
=======
			return this.initialHorizontalVelocity;	
>>>>>>> origin/master
	}
	
	/**
	 * Returns the initial vertical velocity of all Mazubs.
	 */
	@Basic
	@Immutable
	public static double getInitialVerticalVelocity(){
		return INITIAL_VERTICAL_VElOCITY;
	}
	
	/**
	 * Returns the maximum horizontal velocity of this Mazub.
	 */
	@Basic 
	@Raw
	public double getMaximumHorizontalVelocity(){
		return this.maximumHorizontalVelocity;
	}
	
	/**
	 * Returns the maximum horizontal velocity while not ducking of this Mazub.
	 */
	@Basic 
	@Raw
	@Immutable
	public double getMaximumHorizontalVelocityNotDucking(){
		return this.maximumHorizontalVelocityNotDucking;
	}
	
	/**
	 * Check whether this Mazub is currently moving horizontally.
	 * @return	True if the current horizontal velocity of this Mazub is not equal to zero.
	 * 			| result == this.getHorizontalVelocity()!=0
	 */
	@Raw
	public boolean isMovingHorizontally(){
		return this.getHorizontalVelocity()!=0;
	}
	
	/**
	 * Set the horizontal velocity of Mazub to the given horizontal velocity.
	 * 
	 * @param 	horizontalVelocity
	 * 			The horizontal velocity.
	 * @post	The given horizontal velocity is set as the new horizontal velocity
	 * 			of Mazub.
	 * 			|new.getHorizontalVelocity() = horizontalVelocity
	 * @throws	IllegalArgumentException
	 * 			The given horizontal velocity is not valid.
	 * 			|!canHaveAsHorizontalVelocity(horizontalVelocity)
	 */
	@Raw
	public void setHorizontalVelocity(double horizontalVelocity) 
		throws IllegalArgumentException{
		if(!canHaveAsHorizontalVelocity(horizontalVelocity))
			throw new IllegalArgumentException("Not a valid horizontal velocity!");
		this.horizontalVelocity = horizontalVelocity;
	}
	
	/**
	 * Set the vertical velocity of Mazub to the given vertical velocity.
	 * 
	 * @param 	verticalVelocity
	 * 			The vertical velocity to be set.
	 * @post	The given vertical velocity is set as the new vertical velocity
	 * 			of this Mazub.
	 * 			|new.getVerticalVelocity() = verticalVelocity
	 * @throws	IllegalArgumentException
	 * 			The given vertical velocity is not valid.
	 * 			|!canHaveAsVerticalVelocity(verticalVelocity)
	 */ 
	@Raw
	public void setVerticalVelocity(double verticalVelocity) 
		throws IllegalArgumentException{
		if(!isValidVerticalVelocity(verticalVelocity))
			throw new IllegalArgumentException("Not a valid vertical velocity!");
		this.verticalVelocity = verticalVelocity;
	}
	
	/**
	 * Set the maximum horizontal velocity of this Mazub to the given velocity.
	 * 
	 * @param 	maximumHorizontalVelocity
	 * 			The maximum horizontal velocity to be set.
	 * @post	The given maximum horizontal velocity
	 * 			is set as the new maximum horizontal velocity of this Mazub.
	 * 			| new.getMaximumHorizontalVelocity() = maximumHorizontalVelocity
	 * @throws	IllegalArgumentException
	 * 			The given maximum horizontal velocity is not valid.
	 * 			| !canHaveAsMaximumHorizontalVelocity(maximumHorizontalVelocity)
	 */
	@Raw
	public void setMaximumHorizontalVelocity(double maximumHorizontalVelocity)
		throws IllegalArgumentException{
		if(!canHaveAsMaximumHorizontalVelocity(maximumHorizontalVelocity))
			throw new IllegalArgumentException();
		this.maximumHorizontalVelocity = maximumHorizontalVelocity;
	}
	

	/**
	 * Checks whether the given initial horizontal velocity is a valid horizontal velocity.
	 * 
	 * @param 	initialHorizontalVelocity
	 * 			The given initial horizontal velocity.
	 * @return	True if and only if the given initial horizontal velocity is 
	 * 			greater than or equal to 1.
	 * 			|result = Util.fuzzyGreaterThanOrEqualTo(initialHorizontalVelocity,1)
	 */
	@Raw
	public boolean canHaveAsInitialHorizontalVelocity(double initialHorizontalVelocity){
		return isPossibleInitialHorizontalVelocity(initialHorizontalVelocity) && 
				matchesMaximumHorizontalVelocityInitialHorizontalVelocity(this.getMaximumHorizontalVelocity(), initialHorizontalVelocity);
	}
	
	
	/**
	 *  Checks whether the given maximum horizontal velocity is a valid maximum horizontal velocity.
	 *  
	 * @param 	maximumHorizontalVelocity
	 * 			The given maximum horizontal velocity.
	 * @return	True if and only if the given maximum horizontal velocity is 
	 * 			greater than or equal to the initial horizontal velocity.
	 * 			|result = Util.fuzzyGreaterThanOrEqualTo(maximumHorizontalVelocity,getInitialHorizontalVelocity())
	 */
	@Raw
	public boolean canHaveAsMaximumHorizontalVelocity(double maximumHorizontalVelocity){
		return matchesMaximumHorizontalVelocityInitialHorizontalVelocity(maximumHorizontalVelocity, this.getInitialHorizontalVelocity());
	}
	
	/**
	 * Check whether the given maximum horizontal velocity matches with the given initial horizontal velocity of a Mazub.
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
	 * @return	True if and only if the absolute value of the given horizontal velocity is in the range 
	 * 			from initialHorizontalVelocity to maximumHorizontalVelocity or is 
	 * 			equal to 0 m/s.
	 * 			|result == (Util.fuzzyGreaterThanOrEqualTo(abs(horizontalVelocity),this.getInitialHorizontalVelocity()) 
				  && Util.fuzzyLessThanOrEqualTo(abs(horizontalVelocity), this.getMaximumHorizontalVelocity()))
				  || Util.fuzzyEquals(horizontalVelocity, 0);
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
	 * @return	True if and only if the given vertical velocity is less than or equal to 8 m/s (the initial vertical velocity).
	 * 			| result ==  Util.fuzzyLessThanOrEqualTo(verticalVelocity, getInitialVerticalVelocity())
	 */
	public static boolean isValidVerticalVelocity(double verticalVelocity){
		return Util.fuzzyLessThanOrEqualTo(verticalVelocity, getInitialVerticalVelocity());
	}
	
	
	/**
	 * Variable registering the horizontal velocity of this Mazub.
	 */
	private double horizontalVelocity = 0;

	/**
	 *  Variable registering the vertical velocity of this Mazub.
	 */
	private double verticalVelocity = 0;
	
	/**
	 *  Variable registering the initial vertical velocity of all Mazubs.
	 */
	private static final double INITIAL_VERTICAL_VElOCITY = 8;
	
	/**
	 * Variable registering the initial horizontal velocity of Mazub.
	 */
	private final double initialHorizontalVelocity;
	
	/**
	 * Variable registering the maximum horizontal velocity while not ducking of this Mazub.
	 */
	private final double maximumHorizontalVelocityNotDucking;
	
	/**
	 * Variable registering the maximum horizontal velocity of this Mazub.
	 */
	private double maximumHorizontalVelocity;
	
	
	/**
	 * Returns the horizontal acceleration of this Mazub.
	 * @return	If the horizontal velocity of this Mazub is equal to 0 than the horizontal acceleration is equal to zero.
	 * 			Otherwise the horizontal acceleration of this Mazub is equal to horizontalAcceleration.
	 * 			| if (this.getHorizontalVelocity()==0)
	 * 			| 	result==0
	 * 			| else
	 * 			| 	result==horizontalAcceleration
	 */
	@Raw
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
	 * @return	If this Mazub is jumping than the vertical acceleration is equal
	 * 			to the gravitational acceleration.
	 * 			Otherwise the vertical acceleration is equal to zero.
	 * 			|if(isJumping())
	 *			|	result == VERTICAL_ACCELERATION
	 *			|else
	 *			| 	result == 0
	 */
	@Raw
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
	 *  Variable registering the vertical acceleration constant of all Mazubs.
	 */
	public static final double VERTICAL_ACCELERATION = -10;
	
	
	/**
	 *  Return the direction of this Mazub.
	 */
	@Basic
	@Raw
	public int getDirection(){
		return this.direction;
	}
	
	/**
	 * Set the direction of Mazub to the given value.
	 * @param 	direction
	 * 			The desired direction of this Mazub.
	 * @pre		The given direction must be equal to 1 or -1, denoting respectively right and left.
	 * 			| (direction == 1) || (direction == -1)
	 */
	@Raw
	public void setDirection(int direction){
		assert (direction==1 || direction==-1);
		this.direction = direction;
	}
	
	/**
	 * Check whether the given direction is a valid direction.
	 * @param 	direction
	 * 			The given direction.
	 * @return	True if and only if the given direction equals 1 or -1
	 * 			|result = ((direction == -1) || (direction == 1))
	 */
	public static boolean isValidDirection(int direction){
		return ((direction == -1) || (direction == 1));
	}
	
	/**
	 * Variable registering the direction Mazub is facing. 1 means right, -1 means left.
	 */
	private int direction;
	
	
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
	 * Set the ducking state of this Mazub.
	 * 
	 * @param 	ducking
	 * 			The desired ducking state of this Mazub.
	 * @post	The ducking state of this Mazub is changed to 
	 * 			the state expressed by the variable ducking.
	 * 			| new.isDucking() = ducking
	 */
	@Raw
	public void setDucking(boolean ducking) {
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
	@Raw
	public boolean isJumping(){
		return this.getVerticalLocation()>0 || this.getVerticalVelocity()>0;
	}
	
	
	/**
	 * Set the horizontal velocity of this Mazub to the initial horizontal velocity in the given direction.
	 * 
	 * @param 	direction
	 * 			The desired direction in which the Mazub will move.
	 * @pre		The given direction must be equal to -1 or 1. -1 denoting left and 1 denoting right.
	 * 			| (direction == -1) || (direction == 1)
	 * @post 	The new horizontal velocity of this Mazub is equal to the given direction times this.getInitialHorizontalVelocity().
	 * 			| new.getHorizontalVelocity() == direction*this.getInitialHorizontalVelocity()
	 * @post 	The new direction for this mazub is equal to the given direction.
	 * 			| new.getDirection()==direction
	 * @post	The new time since this Mazub has last ended moving is 0.
	 * 			| new.getTimeSinceEndMove()==0
	 * @post	The new time since this Mazub has started moving is 0.
	 * 			| new.getTimeSinceStartMove()==0
	 */
	@Raw
	public void startMove(int direction){
		assert ((direction == -1) || (direction == 1)) :
			"Precondition: direction must be equal to -1 or 1";
		this.setDirection(direction);
		this.setHorizontalVelocity((direction)*this.getInitialHorizontalVelocity());
		this.setTimeSinceStartMove(0);
		this.setTimeSinceEndMove(0);
	}
	
	
	/**
	 * Set the horizontal velocity of this Mazub to zero.
	 * 
	 * @post	The new horizontal velocity of this Mazub is equal to 0.
	 * 			| new.getHorizontalVelocity() == 0
	 * @post	The new time since this Mazub has last ended moving is 0.
	 * 			| new.getTimeSinceEndMove()==0
	 * @post	The new time since this Mazub has started moving is 0.
	 * 			| new.getTimeSinceStartMove()==0
	 */
	@Raw
	public void endMove(){
		this.setHorizontalVelocity(0);
		this.setTimeSinceEndMove(0);
		this.setTimeSinceStartMove(0);
	}
	
	/**
	 * Make the mazub jump if it isn't jumping already.
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
			this.setVerticalVelocity(INITIAL_VERTICAL_VElOCITY);
		}
	}
	
	
	/**
	 * End the jump of this mazub if it is going upwards.
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
	 * Make mazub duck.
	 * 
	 * @post	The new ducking state of this mazub is true.
	 * 			| new.getDucking()
	 * @post	The new maximum horizontal velocity of this mazub is equal to 1m/s.
	 * 			| new.getMaximumHorizontalVelocity()==1
	 * @note	The invocation of this.setMaximumHorizontalVelocity(1) will not result in the throwing of an
	 * 			IllegalArgumentException because a Mazub can always have a maximum horizontal velocity of 1m/s
	 * 			when its ducking state is true. 
	 */
	public void startDuck(){
		this.setDucking(true);
		this.setMaximumHorizontalVelocity(1);
		
	}
	
	/**
	 * End mazub's duck.
	 * 
	 * @post	The new ducking state of this mazub is false.
	 * 			| !new.getDucking()
	 * @post	The new maximum horizontal velocity of this mazub is equal to
	 * 			the maximum horizontal velocity when this mazub isn't ducking.
	 * 			| new.getMaximumHorizontalVelocity==this.getMaximumHorizontalVelocityNotDucking()
	 * @note	The invocation of this.setMaximumHorizontalVelocity(this.getMaximumHorizontalVelocityNotDucking())
	 * 			will not result in the throwing of an exception because 
	 */
	public void endDuck(){
		this.setDucking(false);
		this.setMaximumHorizontalVelocity(this.getMaximumHorizontalVelocityNotDucking());
	}
	
	
	/**
	 * Update the location and velocity of this Mazub.
	 * 
	 * @param 	deltaTime
	 * 			A small period of time.
	 * @post	The horizontal and vertical location are updated
	 * 			and the horizontal and vertical velocity are updated.
	 * @throws	IllegalArgumentException 
	 * 			The given deltaTime is not valid
	 * 			|!isValidTimePeriod(deltaTime)
	 */
	@Raw
	public void advanceTime(double deltaTime)
		throws IllegalArgumentException {
		if(!isValidDeltaTime(deltaTime))
			throw new IllegalArgumentException();
		updateHorizontalLocation(deltaTime);
		updateVerticalLocation(deltaTime);
		updateHorizontalVelocity(deltaTime);
		updateVerticalVelocity(deltaTime);
	}

	
	/**
	 * Check whether the given deltaTime is a valid time period.
	 * 
	 * @param 	deltaTime
	 * 			A period of time.
	 * @return	True if and only if the time period is smaller
	 * 			than 0.2s and not less than zero.
	 */
	public static boolean isValidDeltaTime(double deltaTime){
		return Util.fuzzyGreaterThanOrEqualTo(deltaTime,0) && (deltaTime < 0.2);
	}
	
	/**
	 * Return the time since mazub has last moved.
	 * @note	We consider this method a part of advanceTime() and implement it defensively.
	 */
	@Basic
	@Raw
	public double getTimeSinceEndMove(){
		return this.timeSinceEndMove;
	}
	
	/**
	 * Set the time since mazub has last moved.
	 * 
	 * @param 	timeSinceEndMove
	 * 			The time since mazub's last move.
	 * @post	The new time since this mazub's last move is equal to the given time since this mazub's last move.
	 * 			|new.getTimeSinceEndMove()==timeSinceEndMove
	 * @throws	IllegalArgumentException
	 * 			The given time since Mazub's last move is not valid.
	 * 			|!isValidTimeSinceMove(timeSinceEndMove)
	 * @note	We consider this method a part of advanceTime() and implement it defensively.
	 */
	@Raw
	private void setTimeSinceEndMove(double timeSinceEndMove) throws IllegalArgumentException{
		if(!isValidTimeSinceMove(timeSinceEndMove))
			throw new IllegalArgumentException();
		this.timeSinceEndMove=timeSinceEndMove;
	}
	
	/**
	 * Return the time since mazub has last started moving.
	 * @note	We consider this method a part of advanceTime() and implement it defensively.
	 */
	@Basic
	@Raw
	public double getTimeSinceStartMove(){
		return this.timeSinceStartMove;
	}
	
	/**
	 * Set the time since mazub has last started moving.
	 * @param 	timeSinceStartMove
	 * 			The time since mazub last started moving.
	 * @post	The new time since this Mazub last started moving is equal to the given time since mazub last started moving.
	 * 			|new.getTimeSinceStartMove()==timeSinceStartMove
	 * @throws	IllegalArgumentException
	 * 			The given time since Mazub last started moving is not valid.
	 * 			|!isValidTimeSinceMove(timeSinceStartMove)
	 * @note	We consider this method a part of advanceTime() and implement it defensively.
	 */
	@Raw
	private void setTimeSinceStartMove(double timeSinceStartMove) throws IllegalArgumentException{
		if(!isValidTimeSinceMove(timeSinceStartMove))
			throw new IllegalArgumentException();
		this.timeSinceStartMove=timeSinceStartMove;
	}
	
	/**
	 * Chack whether the given time is a valid time period since Mazub's last action (either start move or end move).
	 * @param 	time
	 * 			The time period to be checked.
	 * @return	True if time is greater than or equal to zero.
	 * 			| result == Util.fuzzyGreaterThanOrEqualTo(time, 0)
	 */
	private static boolean isValidTimeSinceMove(double time){
		return Util.fuzzyGreaterThanOrEqualTo(time, 0);
	}
	
	/**
	 * Update the horizontal location of this Mazub.
	 * 
	 * @param 	deltaTime
	 * 			A period of time.
	 * @post 	If Mazub is moving horizontally, the new horizontal location is calculated and if 
	 * 			this newly calculated horizontal location is a valid horizontal location it is set 
	 * 			as the new horizontal location of this Mazub.
	 * 			|if(isMovingHorizontally())
	 * 			|	if(isValidHorizontalLocation(this.getHorizontalLocationNotRounded() + 
				|	100*(this.getHorizontalVelocity()*deltaTime + this.getDirection()*0.5*getHorizontalAcceleration()*Math.pow(deltaTime, 2))
	 * 			|		new.getHorizontalLocation() = this.getHorizontalLocationNotRounded() + 
				|		100*(this.getHorizontalVelocity()*deltaTime + this.getDirection()*0.5*getHorizontalAcceleration()*Math.pow(deltaTime, 2))
	 * @post	If Mazub is moving horizontally, the new horizontal location is calculated and if 
	 * 			this newly calculated horizontal location is a not valid horizontal location and is smaller than zero, 
	 * 			the new horizontal location is equal to zero.
	 * 			|if(isMovingHorizontally())
	 * 			|	if(this.getHorizontalLocationNotRounded() + 100*(this.getHorizontalVelocity()*deltaTime + 
	 * 			|	this.getDirection()*0.5*getHorizontalAcceleration()*Math.pow(deltaTime, 2)) < 0)
	 * 			|		new.getHorizontalLocation() = 0
	 * @post	If Mazub is moving horizontally, the new horizontal location is calculated and if 
	 * 			this newly calculated horizontal location is a not valid horizontal location and is not smaller than zero, 
	 * 			the new horizontal location is equal to the maximum horizontal location..
	 * 			|if(isMovingHorizontally())
	 * 			|	if(! isValidHorizontalLocation(this.getHorizontalLocationNotRounded() + 
				|	100*(this.getHorizontalVelocity()*deltaTime + this.getDirection()*0.5*getHorizontalAcceleration()*Math.pow(deltaTime, 2))
	 * 			|		if(this.getHorizontalLocationNotRounded() + 100*(this.getHorizontalVelocity()*deltaTime + 
	 * 			|		this.getDirection()*0.5*getHorizontalAcceleration()*Math.pow(deltaTime, 2)) >= 0)
	 * 			|		new.getHorizontalLocation() = getMaximumHorizontalLocation()
	 */
	private void updateHorizontalLocation(double deltaTime) {
		double newHorizontalLocation= this.getHorizontalLocationNotRounded();
		if (this.isMovingHorizontally()){
			try{
				newHorizontalLocation = this.getHorizontalLocationNotRounded() + 
						100*(this.getHorizontalVelocity()*deltaTime + 
						this.getDirection()*0.5*getHorizontalAcceleration()*Math.pow(deltaTime, 2));
				this.setHorizontalLocationNotRounded(newHorizontalLocation);
			} catch (IllegalArgumentException exc){
				this.setHorizontalVelocity(0);
				if(newHorizontalLocation < 0){
					this.setHorizontalLocationNotRounded(0);
				}
				else{
					this.setHorizontalLocationNotRounded(getMaximumHorizontalLocation());
				}
			
			}
		}
	}
	
	
	/**
	 * Update the vertical location of this Mazub.
	 * 
	 * @param 	deltaTime
	 * 			A period of time.
	 * @post 	If Mazub is jumping and the newly calculated vertical location is a valid vertical location it 
	 * 			is set as the new vertical location of this Mazub.
	 * 			|if(this.isJumping())
	 * 			|	if(isValidVerticalLocation(this.getVerticalLocationNotRounded()+
	 * 			|	100*(getVerticalVelocity()*deltaTime + 0.5*getVerticalAcceleration()*Math.pow(deltaTime,2)))
	 * 			|		new.getVerticalLocation() = this.getVerticalLocationNotRounded()+
	 * 			|		100*(getVerticalVelocity()*deltaTime + 0.5*getVerticalAcceleration()*Math.pow(deltaTime,2))
	 * @post	If Mazub is jumping and the newly calculated vertical location is a not valid vertical location and
	 * 			is smaller than 0, the new horizontal location of this Mazub is equal to zero.
	 * 			|if(this.isJumping())
	 * 			|	if(!isValidVerticalLocation(this.getVerticalLocationNotRounded()+
	 * 			|	100*(getVerticalVelocity()*deltaTime + 0.5*getVerticalAcceleration()*Math.pow(deltaTime,2))) && 
	 * 			|	this.getVerticalLocationNotRounded()+ 100*(getVerticalVelocity()*deltaTime + 
	 * 			|	0.5*getVerticalAcceleration()*Math.pow(deltaTime,2))) < 0)
	 * 			|		new.getVerticalLocation() = 0
	 * @post	If Mazub is jumping and the newly calculated vertical location is a not valid vertical location and
	 * 			is not smaller than 0, the new horizontal location of this Mazub is equal to the maximum vertical location.
	 * 			|if(this.isJumping())
	 * 			|	if(!isValidVerticalLocation(this.getVerticalLocationNotRounded()+
	 * 			|	100*(getVerticalVelocity()*deltaTime + 0.5*getVerticalAcceleration()*Math.pow(deltaTime,2))) && 
	 * 			|	! this.getVerticalLocationNotRounded()+ 100*(getVerticalVelocity()*deltaTime + 
	 * 			|	0.5*getVerticalAcceleration()*Math.pow(deltaTime,2))) < 0)
	 * 			|		new.getVerticalLocation() = getMaximumVerticalLocation()
	 */
	private void updateVerticalLocation(double deltaTime) {
		if(this.isJumping()){
			double newVerticalLocation = this.getVerticalLocationNotRounded() + 
					100*(getVerticalVelocity()*deltaTime + 0.5*getVerticalAcceleration()*Math.pow(deltaTime,2));
			try{
				this.setVerticalLocationNotRounded(newVerticalLocation);
			} catch (IllegalArgumentException exc){
				this.setVerticalVelocity(0);
				if(newVerticalLocation < 0)
					this.setVerticalLocationNotRounded(0);
				
				else
					this.setVerticalLocationNotRounded(getMaximumVerticalLocation());
				
			}
		}
	}
	
	
	/**
	 * Update the horizontal velocity of this Mazub.
	 * 
	 * @param 	deltaTime
	 * 			A period of time for which the update needs to place.
	 * @post	If this mazub is not moving horizontally then the time since mazub's 
	 * 			last move is incremented by deltaTime.
	 * 			| if (this.getHorizontalVelocity()==0)
	 * 			|	(new this).getTimeSinceEndMove()==this.getTimeSinceEndMove()+deltaTime
	 * @post	If this mazub is moving horizontally then the new horizontal velocity is calculated
	 * 			and the time since mazub last started moving is incremented by deltaTime.
	 * 			|newVelocity = this.getHorizontalVelocity() + this.getDirection()*getHorizontalAcceleration()*deltaTime
	 * 			|this.setTimeSinceStartMove(this.getTimeSinceStartMove()+deltaTime)
	 * 			If newVelocity is a valid horizontal velocity then the horizontal velocity
	 * 			of this Mazub is set to new velocity.
	 * 			|if(canHaveAsHorizontalVelocity(newVelocity))
	 * 			|	new.getHorizontalVelocity = this.getHorizontalVelocity() + this.getDirection()*getHorizontalAcceleration()*deltaTime
	 * 			If newVelocity is not a valid valid horizontal velocity then the horizontal velocity
	 * 			of this Mazub is set to the maximum horizontal velocity in the given direction.
	 * 			|if(!canHaveAsHorizontalVelocity(newVelocity))
	 * 			|	new.getHorizontalVelocity = this.getDirection()*this.getMaximumHorizontalVelocity()
	 * 			
	 */
	private void updateHorizontalVelocity(double deltaTime) {
		if(!this.isMovingHorizontally()){
			this.setTimeSinceEndMove(this.getTimeSinceEndMove()+deltaTime);
		}
		else{
			double newVelocity = this.getHorizontalVelocity() + this.getDirection()*getHorizontalAcceleration()*deltaTime;
			this.setTimeSinceStartMove(this.getTimeSinceStartMove()+deltaTime);
			if(canHaveAsHorizontalVelocity(newVelocity))
				this.setHorizontalVelocity(newVelocity);
			else{
				this.setHorizontalVelocity(this.getDirection()*this.getMaximumHorizontalVelocity());
			}
		}
	}
	
	
	/**
	 * Update the vertical velocity of this Mazub.
	 * 
	 * @param 	deltaTime
	 * 			A period of time.
	 * @post	If this Mazub is jumping then the new vertical velocity of this Mazub
	 * 			is calcutated and set at the calculated value.
	 * 			|new.getVerticalVelocity = getVerticalVelocity() + getVerticalAcceleration()*deltaTime
	 * @post	If this Mazub is not jumping then the vertical velocity
	 * 			is set to zero.
	 * 			|new.getVerticalVelocity = 0
	 */
	private void updateVerticalVelocity(double deltaTime) {
		if(this.isJumping()){
			this.setVerticalVelocity(getVerticalVelocity() + getVerticalAcceleration()*deltaTime);
		}
		else{
			this.setVerticalVelocity(0);
		}
	}
	
	/**
	 * Variable registering the time since mazub last started moving.
	 */
	private double timeSinceStartMove=0;
	
	/**
	 * Variable registering the time since mazub has last ended moving.
	 */
	private double timeSinceEndMove=2;
	
	
	
	/**
	 * Return the current Sprite of this Mazub.
	 */
	public Sprite getCurrentSprite(){
		int newSpriteIndex=Integer.MAX_VALUE;
		if (!this.isMovingHorizontally() && this.isDucking()==false && this.getTimeSinceEndMove()>1)
			newSpriteIndex=0;
		else if (!this.isMovingHorizontally() && this.isDucking()==true && this.getTimeSinceEndMove()>1)
			newSpriteIndex=1;
		else if (!this.isMovingHorizontally() && this.isDucking()==false && this.getTimeSinceEndMove()<=1 && this.getDirection()==1)
			newSpriteIndex=2;
		else if (!this.isMovingHorizontally() && this.isDucking()==false && this.getTimeSinceEndMove()<=1 && this.getDirection()==-1)
			newSpriteIndex=3;
		else if (this.getHorizontalVelocity()>0 && this.isJumping()==true && this.isDucking()==false)
			newSpriteIndex=4;
		else if (this.getHorizontalVelocity()<0 && this.isJumping()==true && this.isDucking()==false)
			newSpriteIndex=5;
		else if (this.isDucking()==true && this.getDirection()==1 && this.getTimeSinceEndMove()<=1)
			newSpriteIndex=6;
		else if (this.isDucking()==true && this.getDirection()==-1 && this.getTimeSinceEndMove()<=1)
			newSpriteIndex=7;
		else if(this.isJumping()==false && this.isDucking()==false){
			int i = (int) Math.floor(this.getTimeSinceStartMove()/0.075);
			int m=(this.getNbImages()-8)/2;
			if (this.getDirection()==1){
			newSpriteIndex=i%m+8;
			}
			else{
				newSpriteIndex=i%m+8+m;
			}
		}
		return this.getImageAt(newSpriteIndex);
	}
	
	/**
	 * Returns a copy of the current image array of this Mazub.
	 */
	@Basic 
	@Raw
	public Sprite[] getImages(){
		return this.images.clone();
	}
	
	/**
	 * Returns the number of images in the current image array of this Mazub.
	 */
	@Raw
	public int getNbImages(){
		return this.getImages().length;
	}
	
	/**
	 * Check whether the given number of images is valid for all Mazubs.
	 * @param 	nbImages
	 * 			The number of images to be checked.
	 * @return	The given number of images must be an even number and must be greater than or equal to 10;
	 * 			| result == nbImages >= 10 && nbImages%2==0
	 * 
	 */
	public static boolean isValidNbImages(int nbImages){
		return nbImages>=10 && nbImages%2==0;
	}
	
	/**
	 * Returns the image in the image array of this Mazub at the given sprite index.
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
	 * Checks whether to given sprite index is a valid sprite index.
	 * @param 	spriteIndex
	 * 			The sprite index to be checked.
	 * @return	True if the given sprite index is greater than or equal to zero and 
	 * 			smaller than or equal to the number of images in the current image array of this Mazub.
	 * 			| result == spriteIndex>=0 && spriteIndex<=this.getNbImages()
	 */
	@Raw
	public boolean isValidSpriteIndex(int spriteIndex){
		return spriteIndex>=0 && spriteIndex<=this.getNbImages();
	}
	
	/**
	 * Check whether the given image is a valid image.
	 * @param 	image
	 *			The image to be checked.
	 *@return	image!=null
	 */
	public static boolean isValidImage(Sprite image){
		return image!=null;
	}
	
	/**
	 * Sets the image array of this Mazub to the given images array.
	 * @param 	images
	 * 			An image array.
	 * @pre 	The length of the given image array must be a valid length.
	 * 			|isValidNbImages(images.length)
	 * @pre		The images in the given image array must all be valid images.
	 * 			| for i in 1..images.length:
	 * 			|	isValidImage(images[i])
	 * @post	The new image array of this Mazub is equal to copy of he given image array.
	 * 			| this.images==images.clone()
	 */
	@Raw
	public void setImages(Sprite... images){
		assert (isValidNbImages(images.length)):
			"Not a valid number of images in the given image array!";
		for (Sprite image:images){
			assert isValidImage(image):
				"The given image array contains at least one image that isn't valid!";		
		}
		this.images=images.clone();
	}
	
	
	/**
	 * Returns the height of the currrent sprite of this Mazub.
	 */
	@Raw
	public int getHeight(){
		return this.getCurrentSprite().getHeight();
	}
	
	/**
	 * Returns the width of the current sprite of this Mazub.
	 */
	@Raw
	public int getWidth(){
		return this.getCurrentSprite().getWidth();
	}
	
	/**
	 * Variable registering the array of images of this Mazub.
	 */
	private Sprite images[];
	
	
	
	
}
