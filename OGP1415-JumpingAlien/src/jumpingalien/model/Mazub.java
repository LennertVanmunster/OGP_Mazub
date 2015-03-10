package jumpingalien.model;

import jumpingalien.util.Sprite;
import jumpingalien.util.Util;
import be.kuleuven.cs.som.annotate.*;


/**
 * @invar	The bottom-left pixel must always be inside the boundaries of the world.
 * 			|isValidLocation(getHorizontalLocation(),getVerticalLocation())
 * @invar	The absolute value of the velocity of Mazub must always be in the range 
 * 			from initialHorizontalVelocity to maximumHorizontalvelocity or be 
 * 			equal to zero m/s.
 * 			|canHaveAsHorizontalVelocity(getHorizontalVelocity())
 * @invar	
 * 
 * @version 1.0
 * @authors Pieter Van Damme and Lennert Vanmunster
 *
 */
public class Mazub {
	/**
	 *Initialize a new Mazub with given horizontal and vertical location,
	 *horizontal and vertical velocity, initial and maximum horizontal velocity,
	 *ducking state and a set of sprites. 
	 //geen idee of the documentatie hierboven voldoende is?
	 * 
	 * @param horizontalLocation
	 * 		  The horizontal location of Mazub.
	 * @param verticalLocation
	 * 		  The vertical location of Mazub.
	 * @param horizontalVelocity
	 * 		  The horizontal velocity of Mazub.
	 * @param verticalVelocity
	 * 		  The vertical velocity of Mazub.
	 * @param ducking
	 * 		  Parameter to tell if Mazub is ducking or not.
	 * @param initialHorizontalVelocity
	 * 		  The initial horizontal velocity of Mazub when he starts running.
	 * @param maximumHorizontalVelocityNotDucking
	 * 		  The maximum horizontal velocity of Mazub while he is not ducking.
	 * @param images
	 * 		  Sprites to display Mazub.
	 */
	@Raw
	public  Mazub(int horizontalLocation, int verticalLocation, double horizontalVelocity,
				double verticalVelocity, boolean ducking, double initialHorizontalVelocity,
				double maximumHorizontalVelocityNotDucking, Sprite... images){
		setHorizonalLocation(horizontalLocation);
		setVerticalLocation(verticalLocation);
		setHorizontalVelocity(horizontalVelocity);
		setVerticalVelocity(verticalVelocity);
		setDucking(ducking);
		setMaximumHorizontalVelocity(maximumHorizontalVelocityNotDucking);
		this.sprites=images;
		this.initialHorizontalVelocity = initialHorizontalVelocity;
		this.maximumHorizontalVelocityNotDucking = maximumHorizontalVelocityNotDucking;
	}
	
	public Mazub(int horizontalLocation, int verticalLocation, Sprite... images){
		this(horizontalLocation, verticalLocation, 0, 0, false, 1, 3, images);
	}
	
	/**
	 * Returns the horizontal location of this Mazub.
	 */
	@Basic 
	@Raw
	public int getHorizontalLocation(){
		return this.horizontalLocation;
	}
	

	public double getHorizontalLocationNotRounded(){
		return this.horizontalLocationNotRounded;
	}
	
	
	public void setHorizontalLocationNotRounded(double horizontalLocationNotRounded){
		this.horizontalLocationNotRounded=horizontalLocationNotRounded;
	}
	
	
	private double horizontalLocationNotRounded=0;
	
	
	/**
	 * Set the horizontal location of Mazub to the given location.
	 * 
	 * @param 	horizontalLocation
	 * 		  	The new horizontal location.
	 * @post  	This horizontal location is set as the new horizontal location.
	 * 		  	|new.getHorizontalLocation() = this.horizontalLocation
	 * @throws	IllegalArgumentException
	 * 			The given horizontal location is not valid.
	 * 			|!isValidHorizontalLocation(horizontalLocation)	
	 */
	public void setHorizonalLocation(int horizontalLocation)
			throws IllegalArgumentException {
		if(!isValidHorizontalLocation(horizontalLocation))
			throw new IllegalArgumentException();
		this.horizontalLocation = horizontalLocation;
	}
	
	/**
	 * Variable registering the horizontal location of this Mazub.
	 */
	private int horizontalLocation = 0;
	
	
	/**
	 * Check whether the given horizontal location is a valid location.
	 * 
	 * @param 	horizontalLocation
	 * 		  	The horizontal location.
	 * @return	True if and only if the horizontal location is an integer number
	 * 			in the range of 0 to maximumHorizontalLocation.
	 * 			|result == ((horizontalLocation >= 0 ) && (horizontalLocation <= maximumHorizontalLocation));
	 */
	@Basic
	public static boolean isValidHorizontalLocation(int horizontalLocation){
		return ((horizontalLocation >= 0 ) && (horizontalLocation <= getMaximumHorizontalLocation()));
	}
	
	
	/**
	 * Check whether the given vertical location is a valid location.
	 * 
	 * @param 	verticalLocation
	 * 		  	The vertical location.
	 * @return	True if and only if the vertical location is an integer number
	 * 			in the range of 0 to maximumVerticalLocation.
	 * 			|result == ((verticalLocation >= 0 ) && (verticalLocation <= maximumVerticalLocation));
	 */
	@Basic
	public static boolean isValidVerticalLocation(int verticalLocation){
		return ((verticalLocation >= 0 ) && (verticalLocation <= getMaximumVerticalLocation()));
	}
	
	
	/**
	 * Check whether the given location is a valid location.
	 * 
	 * @param	horizontalLocation
	 * 			The horizontal location.
	 * @param 	verticalLocation
	 * 		  	The vertical location.
	 * @return	True if and only if the horizontal and vertical location are valid locations.
	 * 			|result == (isValidHorizontalLocation(horizontalLocation) && isValidVerticalLocation(verticalLocation));
	 */
	public static boolean isValidLocation(int horizontalLocation, int verticalLocation){
		return (isValidHorizontalLocation(horizontalLocation) && isValidVerticalLocation(verticalLocation));
	}
	
	
	@Basic 
	@Raw
	/**
	 * Return the vertical location of this Mazub.
	 */
	public int getVerticalLocation() {
		return this.verticalLocation;
	}
	
	
	/**
	 * Set the vertical location of Mazub to the given location.
	 * 
	 * @param 	verticalLocation
	 * 		  	The new vertical location.
	 * @post  	This vertical location is set as the new vertical location.
	 * 		  	|new.getVerticalLocation() = verticalLocation
	 * @throws	IllegalArgumentException
	 * 			The given vertical location is not valid.
	 * 			|!isValidVerticalLocation(verticalLocation)	  
	 */
	@Basic @Raw
	public void setVerticalLocation(int verticalLocation) 
			throws IllegalArgumentException {
		if(!isValidVerticalLocation(verticalLocation))
			throw new IllegalArgumentException();
		this.verticalLocation = verticalLocation;
	}
	
	
	public double getVerticalLocationNotRounded(){
		return this.verticalLocationNotRounded;
	}
	
	
	public void setVerticalLocationNotRounded(double verticalLocationNotRounded){
		this.verticalLocationNotRounded = verticalLocationNotRounded;
	}
	
	/**
	 * Variable registering the vertical location of this Mazub.
	 */
	private int verticalLocation = 0;
	
	
	private double verticalLocationNotRounded = 0;
	
	
	/**
	 * Check whether the given horizontal velocity is a valid horizontal velocity.
	 * 
	 * @param 	horizontalVelocity
	 * 			The horizontal velocity.
	 * @return	True if and only if the absolute value of the velocity is in the range 
	 * 			from initialHorizontalVelocity to maximumHorizontalvelocity or is 
	 * 			equal to zero m/s.
	 * 			|result == (Util.fuzzyGreaterThanOrEqualTo(abs(horizontalVelocity),getInitialHorizontalVelocity()) 
				  && Util.fuzzyLessThanOrEqualTo(abs(horizontalVelocity), getMaximumHorizontalVelocity()))
				  || Util.fuzzyEquals(horizontalVelocity, 0);
	 */
	public boolean canHaveAsHorizontalVelocity(double horizontalVelocity){
		horizontalVelocity = Math.abs(horizontalVelocity);
		return Util.fuzzyGreaterThanOrEqualTo(horizontalVelocity,this.getInitialHorizontalVelocity()) 
				&& Util.fuzzyLessThanOrEqualTo(horizontalVelocity, getMaximumHorizontalVelocity())
				|| Util.fuzzyEquals(horizontalVelocity, 0);
	}
	
	
	/**
	 * Returns the horizontal velocity of this Mazub.
	 */
	@Basic 
	@Raw
	public double getHorizontalVelocity() {
		return this.horizontalVelocity;
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
	@Basic @Raw
	public void setHorizontalVelocity(double horizontalVelocity) 
		throws IllegalArgumentException{
		if(!canHaveAsHorizontalVelocity(horizontalVelocity))
			throw new IllegalArgumentException();
		this.horizontalVelocity = horizontalVelocity;
	}
	
	
	/**
	 * Variable registering the horizontal velocity of this Mazub.
	 */
	private double horizontalVelocity = 0;

	
	/**
	 * Check whether the given vertical velocity is a valid vertical velocity.
	 * 
	 * @param 	verticalVelocity
	 * 			The vertical velocity.
	 * @return	True if and only if the velocity is less than or equal to 8 m/s (the initial vertical velocity).
	 * 			|result ==  Util.fuzzyLessThanOrEqualTo(verticalVelocity, INITIAL_VERTICAL_VElOCITY)
				  
	 */
	public boolean canHaveAsVerticalVelocity(double verticalVelocity){
		return Util.fuzzyLessThanOrEqualTo(verticalVelocity, getInitialVerticalVelocity());
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
	 * Set the vertical velocity of Mazub to the given vertical velocity.
	 * 
	 * @param 	verticalVelocity
	 * 			The vertical velocity.
	 * @post	The given vertical velocity is set as the new vertical velocity
	 * 			of Mazub.
	 * 			|new.getVerticalVelocity() = verticalVelocity
	 * @throws	IllegalArgumentException
	 * 			The given vertical velocity is not valid.
	 * 			|!canHaveAsVerticalVelocity(verticalVelocity)
	 */
	public void setVerticalVelocity(double verticalVelocity) 
		throws IllegalArgumentException{
		if(!canHaveAsVerticalVelocity(verticalVelocity))
			throw new IllegalArgumentException();
		this.verticalVelocity = verticalVelocity;
	}
	
	
	/**
	 *  Variable registering the vertical velocity of this Mazub.
	 */
	private double verticalVelocity = 0;
	
	
	/**
	 * Returns the horizontal acceleration of this Mazub.
	 */
	@Basic 
	@Immutable
	public static double getHorizontalAcceleration(){
		return horizontalAcceleration;
	}
	
	
	/**
	 *  Variable registering the horizontal acceleration constant of all Mazubs.
	 */
	private static final double horizontalAcceleration = 0.9;
	
	
	/**
	 *  Variable registering the vertical acceleration constant of all Mazubs.
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
	 * Set the ducking state of this Mazub.
	 * 
	 * @param 	ducking
	 * 			A boolean which is True if Mazub is ducking
	 * 			and False if not.
	 * @post	The ducking state of this Mazub is changed to 
	 * 			the state expressed by the variable ducking.
	 * 			|new.isDucking() = ducking
	 */
	@Basic 
	@Raw
	public void setDucking(boolean ducking) {
		this.ducking = ducking;
	}
	
	
	/**
	 * Variable registering the ducking state of this Mazub.
	 */
	private boolean ducking = false;
	
	
	/**
	 * Returns the initial horizontal velocity of this Mazub.
	 */
	@Basic 
	@Raw
	public double getInitialHorizontalVelocity(){
		return this.initialHorizontalVelocity;		
	}
	
	
	/**
	 * Variable registering the initial horizontal velocity of Mazub.
	 */
	private final double initialHorizontalVelocity;
	
	
	/**
	 * Returns the initial vertical velocity of a Mazub.
	 */
	public double getInitialVerticalVelocity(){
		return INITIAL_VERTICAL_VElOCITY;
	}
	
	
	/**
	 *  Variable registering the initial vertical velocity of all Mazubs.
	 */
	private static final double INITIAL_VERTICAL_VElOCITY = 8;
	
	
	/**
	 * Returns the maximum horizontal velocity while not ducking of this Mazub.
	 */
	@Basic 
	@Raw
	public double getMaximumHorizontalVelocityNotDucking(){
		return this.maximumHorizontalVelocityNotDucking;
	}
	
	
	/**
	 * Variable registering the maximum horizontal velocity while not ducking of this Mazub.
	 */
	private final double maximumHorizontalVelocityNotDucking;
	
	
	/**
	 * Returns the maximum horizontal velocity of this Mazub.
	 */
	@Basic 
	@Raw
	public double getMaximumHorizontalVelocity(){
		return this.maximumHorizontalVelocity;
	}
	
	
	/**
	 * Set the maximum horizontal velocity of this Mazub to the given velocity.
	 * 
	 * @param 	maximumHorizontalVelocity
	 * 			The maximum horizontal velocity.
	 * @post	The absolute value of the given maximum horizontal velocity
	 * 			is set as the new maximum horizontal velocity of this Mazub.
	 * 			|new.getMaximumHorizontalVelocity() = abs(maximumHorizontalVelocity)
	 * @note	We demand that the maximum horizontal velocity is a positive
	 * 			number (including zero).
	 */
	@Basic 
	@Raw
	public void setMaximumHorizontalVelocity(double maximumHorizontalVelocity){
		this.maximumHorizontalVelocity = Math.abs(maximumHorizontalVelocity);
	}
	
	
	/**
	 * Variable registering the maximum horizontal velocity of this Mazub.
	 */
	private double maximumHorizontalVelocity;
	
	
	/**
	 * Returns the maximum horizontal location of all Mazubs.
	 */
	@Basic 
	@Immutable
	public static int getMaximumHorizontalLocation(){
		return maximumHorizontalLocation;
	}
	
	
	/**
	 * Variable registering the maximum horizontal location of all Mazubs.
	 */
	private final static int maximumHorizontalLocation = 1023;
	
	
	/**
	 * Returns the maximum vertical location of all Mazubs.
	 */
	@Basic
	@Immutable
	public static int getMaximumVerticalLocation(){
		return maximumVerticalLocation;
	}
	
	
	/**
	 * Variable registering the maximum vertical location of all Mazubs.
	 */
	private final static int maximumVerticalLocation = 767;
	
	
	/**
	 * Array registering images of Mazub.
	 */
	private Sprite[] images;
	
	
	/**
	 * Set the horizontal velocity of this Mazub to the initial horizontal velocity in the given direction.
	 * 
	 * @param 	direction
	 * 			The direction in which the Mazub will move.
	 * @pre		The given direction must be equal to -1 or 1. -1 denoting left and 1 denoting right.
	 * 			| (direction == -1) || (direction == 1)
	 * @post 	The new horizontal velocity of this Mazub is equal to direction*this.getInitialHorizontalVelocity()
	 * 			| new.getHorizontalVelocity() == direction*this.getInitialHorizontalVelocity()
	 */
	public void startMove(int direction){
		assert ((direction == -1) || (direction == 1)) :
			"Precondition: direction must be equal to -1 or 1";
		this.setHorizontalVelocity((direction)*this.getInitialHorizontalVelocity());
	}
	
	
	/**
	 * Set the horizontal velocity of this Mazub to zero.
	 * 
	 * @post	The new horizontal velocity of this Mazub is equal to 0.
	 * 			| new.getHorizontalVelocity() == 0
	 */
	public void endMove(){
		this.setHorizontalVelocity(0);
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
	public void advanceTime(double deltaTime)
		throws IllegalArgumentException {
		if(!isValidTimePeriod(deltaTime))
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
	public boolean isValidTimePeriod(double deltaTime){
		return (Util.fuzzyGreaterThanOrEqualTo(deltaTime,0) && (0.2 > deltaTime));
	}
	
	
	private void updateHorizontalLocation(double deltaTime) {
		this.setHorizontalLocationNotRounded(this.getHorizontalLocationNotRounded() + 
				100*(this.getHorizontalVelocity()*deltaTime + 
				(Math.signum(this.getHorizontalVelocity())*0.5*getHorizontalAcceleration()*Math.pow(deltaTime, 2))));
		horizontalLocation = (int) Math.floor(this.getHorizontalLocationNotRounded());
		try{
			this.setHorizonalLocation( horizontalLocation );
		} catch (IllegalArgumentException exc){
			this.setHorizontalVelocity(0);
			if(horizontalLocation < 0)
				this.setHorizonalLocation(0);
			else{
				this.setHorizonalLocation(getMaximumHorizontalLocation()-1);
			}
			
		}
	}
	
	
	private void updateVerticalLocation(double deltaTime) {
		if(isJumping())
			this.setVerticalLocationNotRounded(this.getVerticalLocationNotRounded() + 
				100*(getVerticalVelocity()*deltaTime + 0.5*VERTICAL_ACCELERATION*Math.pow(deltaTime,2)));
			verticalLocation = (int) Math.floor(this.getVerticalLocationNotRounded());
			try{
				this.setVerticalLocation(verticalLocation);
			} catch (IllegalArgumentException exc){
				this.setVerticalVelocity(0);
				if(verticalLocation < 0)
					this.setVerticalLocation(0);
				else{
					this.setVerticalLocation(getMaximumVerticalLocation()-1);
				}
			}
	}
	
	
	private void updateHorizontalVelocity(double deltaTime) {
		double newVelocity = getHorizontalVelocity() + (Math.signum(getHorizontalVelocity()))*getHorizontalAcceleration()*deltaTime;
		try{
			this.setHorizontalVelocity(newVelocity);
		} catch (IllegalArgumentException exc){
			this.setHorizontalVelocity(Math.signum(newVelocity)*getMaximumHorizontalVelocity());
		}
	}
	
	/**
	 * Check whether this Mazub is performing a jump.
	 * 
	 * @return	True if and only if the vertical velocity of Mazub is greater than zero (begin of the jump)
	 * 			or if the vertical location of Mazub is greater than zero.
	 * 			|(0 < getVerticalVelocity()) || (0 < getVerticalLocation())
	 */
	public boolean isJumping(){
		return (0 < getVerticalVelocity()) || (0 < getVerticalLocation());
	}
	
	
	private void updateVerticalVelocity(double deltaTime) {
		if(isJumping())
			this.setVerticalVelocity(getVerticalVelocity() + VERTICAL_ACCELERATION*deltaTime);
		else this.setVerticalVelocity(0);
	}
	
	
	public void startJump(){
		setVerticalVelocity(INITIAL_VERTICAL_VElOCITY);
	}
	
	
	
	public void endJump(){
		setVerticalVelocity(0);
	}
	
	
	public void startDuck(){
		this.setDucking(true);
		this.setMaximumHorizontalVelocity(1);
		
	}
	
	
	public void endDuck(){
		this.setDucking(false);
		this.setMaximumHorizontalVelocity(getMaximumHorizontalVelocityNotDucking());
	}
	
	
	private Sprite sprites[];
	
	public Sprite getCurrentSprite(){
		return sprites[1];
	}
	
	
	
	
	
}
