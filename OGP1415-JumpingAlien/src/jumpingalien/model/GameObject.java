package jumpingalien.model;

import jumpingalien.util.Sprite;
import jumpingalien.util.Util;
import be.kuleuven.cs.som.annotate.*;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A class of GameObjects.
 * 
 * @invar	Each game object can have its location as its location in its game world.
 * 			|canHaveAsLocation(getHorizontalLocation(),getVerticalLocation())
 * @invar	Each game object can have its horizontal velocity as its horizontal velocity.
 * 			|canHaveAsHorizontalVelocity(getHorizontalVelocity())
 * @invar	Each game object can have its vertical velocity as its vertical velocity.
 * 			|canHaveAsVerticalVelocity(getVerticalVelocity())
 * @invar	Each game object can have its initial horizontal velocity as its initial horizontal velocity.
 * 			|canHaveAsInitialHorizontalVelocity(getInitialHorizontalVelocity())
 * @invar	Each game object can have its maximum horizontal velocity as its maximum horizontal velocity.
 * 			|canHaveAsMaximumHorizontalVelocity(getMaximumHorizontalVelocity())
 * @invar	The initial vertical velocity of each game object must be a valid vertical velocity.
 * 			|isValidVerticalVelocity(getVerticalVelocity)
 * @invar	Each game object can have its horizontal acceleration as its horizontal acceleration.
 * 			|canHaveAsHorizontalAcceleration(getHorizontalAcceleration())
 * @invar	Each game object can have its vertical acceleration as its vertical acceleration.
 * 			|canHaveAsVerticalAcceleration(verticalAcceleration())
 * @invar	The direction of each game object must be a valid direction.
 * 			|isValidDirection(getDirection())
 * @invar	Each game object can have its ducking state as its ducking state.
 * 			|canHaveAsDuckingState(this.isDucking())
 * @invar	Each game object can have the length of the image array as its image array length
 * 			|this.canHaveAsNbImages(getNbImages())
 * @invar	Each image in the image array of each game object must be a valid image.
 * 			| for each i in 1..getNbImages():
 * 			| 	isValidImage(getImageAt(I))
 * @invar	The time since this game object last started an action must be a valid time for each game object.
 * 			| isValidTimeSinceAction(getTimeSinceStartAction())
 * @invar	The time since this game object last started contacting water must be a valid time for each game object.
 * 			| isValidTimeSinceAction(getTimeSinceStartWaterContact())
 * @invar	The time since this game object last started contacting magma must be a valid time for each game object.
 * 			| isValidTimeSinceAction(getTimeSinceStartMagmaContact())
 * @invar	Each game object has a proper world to which it is attached.
 * 			| hasProperWorld()
 * @version	1.0
 * @authors Pieter Van Damme and Lennert Vanmunster
 *
 */
public abstract class GameObject {
	
	/**
	 * Creates a new game object with the given parameters.
	 * @param horizontalLocation
	 * 			The horizontal location of the new game object.
	 * @param verticalLocation
	 * 			The vertical location of the new game object.
	 * @param horizontalVelocity
	 * 			The horizontal velocity of the new game object.
	 * @param verticalVelocity
	 * 			The vertical velocity of the new game object.
	 * @param initialHorizontalVelocity
	 * 			The initial horizontal velocity of the new game object when the game object starts moving and is not ducking.
	 * @param maximumHorizontalVelocity
	 * 			The maximum horizontal velocity of the new game object when it is not ducking.
	 * @param initialVerticalVelocity
	 * 			The initial vertical velocity of the new game object when the game object jumps.
	 * @param horizontalAcceleration
	 * 			The horizontal acceleration of the new game object when the game object moves.
	 * @param hitPoints
	 * 			The hit points of the new game object.
	 * @param maxHitPoints
	 * 			The maximum number of hit points of the new game object.
	 * @param images
	 * 			An array of images.
	 * @post	The horizontal location of this new game object is equal to the 
	 * 			given horizontal location.
	 * 			|new.getHorizontalLocation() == horizontalLocation
	 * @post	The vertical location of this new game object is equal to the 
	 * 			given vertical location.
	 * 			|new.getVerticalLocation() == verticalLocation
	 * @post	The initial horizontal velocity when not ducking
	 * 			of this new game object is equal to the given initial horizontal velocity.
	 * 			|new.getInitialHorizontalVelocity() == initialHorizontalVelocity
	 * @post	The maximum horizontal velocity this new game object when not ducking is equal to the 
	 * 			given maximum horizontal velocity.
	 * 			|new.getMaximumHorizontalVelocity() == maximumHorizontalVelocity
	 * @post	The horizontal velocity of this new game object is equal to the 
	 * 			given horizontal velocity.
	 * 			|new.getHorizontalVelocity() == horizontalVelocity
	 * @post	The vertical velocity of this new game object is equal to the 
	 * 			given vertical velocity.
	 * 			|new.getVerticalVelocity() = verticalVelocity
	 * @post	The initial vertical velocity of this game object is equal to the absolute value of the given initial vertical velocity.
	 * 			|new.getInitialVerticalVelocity()==Math.abs(initialVerticalVelocity)
	 * @post	The horizontal acceleration of this game object is equal to the absolute value of the given horizontal acceleration.
	 * 			|new.horizontalAcceleration=horizontalAcceleration
	 * @post	If the given horizontal velocity is equal to zero, the horizontal moving state of this game object is set to true.
	 * 			Otherwise it is set to false.
	 * 			|if(horizontalVelocity==0)
     *			|	this.isMovingHorizontally()==false
	 *			|else
	 *			|this.getMovingHorizontally()==true;
	 * @post	If the given horizontal velocity is greater than or equal to 0 the direction of this new game object is RIGHT.
	 * 			Otherwise the direction of this new game object is LEFT.
	 * 			|if (Util.fuzzyGreaterThanOrEqualTo(horizontalVelocity, 0))
	 * 			|	new.getDirection() == Direction.RIGHT
	 * 			|else
	 * 			|	new.getDirection() == Direction.LEFT
	 * @post	The images of this new game object are equal to the 
	 * 			given images.
	 * 			|new.getImages() == images
	 * @post	The maximum number of hit points is equal to the given maximum number of hit points.
	 * 			| new.getMaxHitPoints(maxHitPoints);
	 * @effect	The given number of hit points is set as the number of hit points for this game object.
	 * 			| this.setHitPoints(hitPoints)
	 * @throws	IllegalLocationException
	 * 			Not a valid horizontal location.
	 * 			|!canHaveAsHorizontalLocation(horizontalLocation)	
	 * @throws	IllegalLocationException
	 * 			Not a valid vertical location.
	 * 			|!canHaveAsVericalLocation(verticalLocation)	
	 * @throws	IllegalArgumentException
	 * 			The given initial horizontal velocity is not valid for any game object or it does 
	 * 			not match with the given maximum horizontal velocity.
	 * 			|!isPossibleInitialHorizontalVelocity(initialHorizontalVelocity)	
	 * @throws	IllegalArgumentException
	 * 			The given maximum horizontal velocity is not valid for any game object or it does
	 * 			not match with the given initial horizontal velocity.
	 * 			|!canHaveAsMaximumHorizontalVelocity(maximumHorizontalVelocity)	
	 * @throws	IllegalArgumentException
	 * 			Not a valid horizontal velocity.
	 * 			|!canHaveAsHorizontalVelocity(horizontalVelocity)
	 * @throws	IllegalArgumentException
	 * 			Not a valid vertical velocity.
	 * 			|!isValidVerticalVelocity(verticalVelocity)	
	 * @throws	IllegalArgumentException
	 * 			Not a valid number of maximum hit points.
	 * 			|!isValidMaxHitPoints(maxHitPoints)
	 * @throws	IllegalArgumentException
	 * 			This game object cannot have the given ducking state as its ducking state.
	 * 			| !canHaveAsDuckingState(ducking)
	 */
	protected GameObject(int horizontalLocation, int verticalLocation, double horizontalVelocity,
				double verticalVelocity, double initialHorizontalVelocity, double maximumHorizontalVelocity, double initialVerticalVelocity, double horizontalAcceleration, boolean ducking, int hitPoints, int maxHitPoints, Sprite... images)
		throws IllegalArgumentException, IllegalLocationException {
			setHorizontalLocation(horizontalLocation);
			setVerticalLocation(verticalLocation);
			if(!isPossibleInitialHorizontalVelocity(initialHorizontalVelocity))
				throw new IllegalArgumentException("Not a valid initial horizontal velocity!");
			this.initialHorizontalVelocity = initialHorizontalVelocity;
			if(!canHaveAsMaximumHorizontalVelocity(maximumHorizontalVelocity))
				throw new IllegalArgumentException("Not a valid maximum horizontal velocity!");
			this.maximumHorizontalVelocity=maximumHorizontalVelocity;
			this.initialVerticalVelocity=Math.abs(initialVerticalVelocity);
			setHorizontalVelocity(horizontalVelocity);
			setVerticalVelocity(verticalVelocity);
			this.setDucking(ducking);
			this.setHorizontalAcceleration(horizontalAcceleration);
			if(horizontalVelocity==0){
				this.setMovingHorizontally(false);
			}
			else{
				this.setMovingHorizontally(true);
			}
			if (Util.fuzzyGreaterThanOrEqualTo(horizontalVelocity, 0))
				setDirection(Direction.RIGHT);
			else
				setDirection(Direction.LEFT);
			if(!isValidMaxHitPoints(maxHitPoints)){
				throw new IllegalArgumentException("Not a valid number of maximum hit points!");
			}
			this.maxHitPoints=maxHitPoints;
			this.setHitPoints(hitPoints);
			this.setImages(images);
	}
	
	
	/**
	 * Return the effective horizontal location of this game object as an integer number.
	 */
	@Raw
	public int getEffectiveHorizontalLocation(){
		return (int) Math.floor(this.getHorizontalLocation());
	}
	
	/**
	 * Return the effective vertical location of this game object as an integer number.
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
	 * @return	True if this game object has no world.
	 * 			| if(this.getWorld()==null)
	 * 			|	result==true
	 * 			Otherwise false if  the given horizontal location is less than 0
	 * 			or greater than or equal to the maximum horizontal location plus one.
	 * 			| result == !((horizontalLocation < 0 ) && (horizontalLocation >= maximumHorizontalLocation+1))
	 			Otherwise true if the hit box of this game object doesn't coincide with the terrain of its world.
	 * 			| result== !this.getWorld().areaCoincidesWithTerrain((int) horizontalLocation, this.getEffectiveVerticalLocation()+1, this.getWidth()-1, this.getHeight()-2)[1]
	 */
	@Raw
	public boolean canHaveAsHorizontalLocation(double horizontalLocation){
		if (this.getWorld()==null){
			return true;
		}	
		else if (horizontalLocation<0 || horizontalLocation >= this.getWorld().getWorldWidth()+1){
			return false;
		}
		else if(this.getWorld().areaCoincidesWithTerrain((int) horizontalLocation, this.getEffectiveVerticalLocation()+1, this.getWidth()-1, this.getHeight()-2)[1]){
			return false;
		}
//		else if(this != null && checkNotAllowedLeftRightTopBottomSideOverlap((int) horizontalLocation, this.getEffectiveVerticalLocation(), this.getWidth(), this.getHeight())){
//			return false;
//		}
		else{
			return true;
		}
	}
	
	
	/**
	 * Check whether the given vertical location is a valid vertical location.
	 * 
	 * @param 	verticalLocation
	 * 		  	The vertical location to be checked.
	 *  @return	True if this game object has no world.
	 * 			| if(this.getWorld()==null)
	 * 			|	result==true
	 * 			Otherwise false if  the given vertical location is less than 0
	 * 			or greater than or equal to the maximum vertical location plus one.
	 * 			| result == !((horizontalLocation < 0 ) && (horizontalLocation >= maximumHorizontalLocation+1))
	 			Otherwise true if the hit box of this game object doesn't coincide with the terrain of its world.
	 * 			| result== !this.getWorld().areaCoincidesWithTerrain((int) horizontalLocation, this.getEffectiveVerticalLocation()+1, this.getWidth()-1, this.getHeight()-2)[1]
	 */
	@Raw
	public boolean canHaveAsVerticalLocation(double verticalLocation){
		if(this.getWorld()==null){
			return true; 
		}
		else if (verticalLocation<0 || verticalLocation >= this.getWorld().getWorldHeight()+1){
			return false;
		}
		else if(this.getWorld().areaCoincidesWithTerrain(this.getEffectiveHorizontalLocation(), 
				(int) verticalLocation+1, this.getWidth()-1, this.getHeight()-2)[1]){
			return false;
		}
//		else if(this != null && checkNotAllowedLeftRightTopBottomSideOverlap(this.getEffectiveHorizontalLocation(), (int) verticalLocation, this.getWidth(), this.getHeight() )){
//			return false;
//		}
		else{
			return true;
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
	 * Return the calculated horizontal location of this game object.
	 */
	@Basic
	@Raw
	protected double getHorizontalLocation(){
		return this.horizontalLocation;
	}
	
	/**
	 * Return the calculated vertical location of this game object.
	 */
	@Basic
	@Raw
	protected double getVerticalLocation(){
		return this.verticalLocation;
	}
	
	/**
	 * Set the calculated horizontal location of this game object to the given location.
	 * 
	 * @param 	horizontalLocation
	 * 		  	The new calculated horizontal location for this game object.
	 * @post  	The new calculated horizontal location of this game object is set to the given calculated horizontal location.
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
	 * Set the calculated vertical location of game object to the given location.
	 * 
	 * @param 	verticalLocation
	 * 		  	The new calculated vertical location.
	 * @post  	The new calculated vertical location of this game object is set to the given vertical location.
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
	 * Variable registering the calculated horizontal location of this game object.
	 */
	private double horizontalLocation = 0;
	
	/**
	 * Variable registering the calculated  vertical location of this game object.
	 */
	private double verticalLocation = 0;
	
	/**
	 * Return the horizontal velocity of this game object.
	 */
	@Basic
	@Raw 
	public double getHorizontalVelocity() {
		return this.horizontalVelocity;
	}
	
	/**
	 * Return the vertical velocity of this game object.
	 */
	@Basic 
	@Raw
	public double getVerticalVelocity() {
		return this.verticalVelocity;
	}
	
	/**
	 * Return the maximum horizontal velocity of this game object.
	 */
	@Raw
	public abstract double getMaximumHorizontalVelocityForUpdate();
	
	/**
	 * Return the maximum horizontal velocity when not ducking of this game object.
	 */
	@Basic
	@Raw
	public double getMaximumHorizontalVelocity(){
		return this.maximumHorizontalVelocity;
	}
	
	/**
	 * Return the initial horizontal velocity when not ducking of this game object.
	 */
	@Basic
	@Raw
	public double getInitialHorizontalVelocity(){
		return this.initialHorizontalVelocity;
	}
	
	public void setInitialHorizontalVelocity(double initialHorizontalVelocity){
		if(!canHaveAsInitialHorizontalVelocity(initialHorizontalVelocity)){
			throw new IllegalArgumentException("This game object cannot have that initial horizontal velocity!");
		}
		this.initialHorizontalVelocity=initialHorizontalVelocity;
	}
	
	public void setMaximumHorizontalVelocity(double maximumHorizontalVelocity){
		if(!canHaveAsMaximumHorizontalVelocity(maximumHorizontalVelocity)){
			throw new IllegalArgumentException("This game object cannot have that maximum horizontal velocity!");
		}
		this.maximumHorizontalVelocity=maximumHorizontalVelocity;
	}
	
	/**
	 * Return the initial horizontal velocity of this game object.
	 */
	@Raw
	public abstract double getInitialHorizontalVelocityForUpdate();
	
	/**
	 * Return the initial vertical velocity of this game object
	 */
	@Raw
	public double getInitialVerticalVelocity(){
		return this.initialVerticalVelocity;
	}
	
	/**
	 * Set the horizontal velocity of game object to the given horizontal velocity.
	 * 
	 * @param 	horizontalVelocity
	 * 			The horizontal velocity.
	 * @post	The given horizontal velocity is set as the new horizontal velocity
	 * 			of game object.
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
	 * Set the vertical velocity of game object to the given vertical velocity.
	 * 
	 * @param 	verticalVelocity
	 * 			The vertical velocity to be set.
	 * @post	The given vertical velocity is set as the new vertical velocity of this game object.
	 * 			|new.getVerticalVelocity() = verticalVelocity
	 * @throws	IllegalArgumentException
	 * 			The given vertical velocity is not a valid vertical velocity.
	 * 			|!isValidVerticalVelocity(verticalVelocity)
	 */ 
	@Raw
	public void setVerticalVelocity(double verticalVelocity) 
		throws IllegalArgumentException{
		if(!canHaveAsVerticalVelocity(verticalVelocity))
			throw new IllegalArgumentException("Not a valid vertical velocity!");
		this.verticalVelocity = verticalVelocity;
	}
	
	/**
	 * Check whether the given initial vertical velocity is a valid initial vertical velocity.
	 * @param	initialVerticalVelocity
	 * 			The initial vertical velocity to be checked.
	 * @return	True if the given vertical velocity is greater than or equal to zero.
	 * 			| result == (initialVerticalVelocity>=0)
	 */
	public boolean isValidInitialVerticalVelocity(double initialVerticalVelocity){
		return initialVerticalVelocity>=0;
	}
	
	/**
	 * Check whether this game object can have the given horizontal velocity as its horizontal velocity.
	 * @param 	horizontalVelocity
	 */
	public abstract boolean canHaveAsHorizontalVelocity(double horizontalVelocity);
	
	/**
	 * Check whether this game object can have the given vertical velocity as its vertical velocity.
	 * 
	 * @param 	verticalVelocity
	 * 			The vertical velocity to be checked.
	 * @return	True if and only if the given vertical velocity is less than or equal to the initial vertical velocity constant of this game object.
	 * 			| result ==  Util.fuzzyLessThanOrEqualTo(verticalVelocity, getInitialVerticalVelocity())
	 */
	public boolean canHaveAsVerticalVelocity(double verticalVelocity){
		return Util.fuzzyLessThanOrEqualTo(verticalVelocity, getInitialVerticalVelocity());
	}
	
	/**
	 * Check whether this game object can have the given initial horizontal velocity as its initial horizontal velocity.
	 * 
	 * @param 	initialHorizontalVelocity
	 * 			The initial horizontal velocity to be checked.
	 * @return	True if and only if the given initial horizontal velocity is a possible initial horizontal 
	 * 			velocity for this game object and it matches with the maximum horizontal velocity of this game object.
	 * 			|result ==  this.isPossibleInitialHorizontalVelocity(initialHorizontalVelocity) && 
				matchesMaximumHorizontalVelocityInitialHorizontalVelocity(this.getMaximumHorizontalVelocity(), initialHorizontalVelocity)
	 */
	@Raw
	public boolean canHaveAsInitialHorizontalVelocity(double initialHorizontalVelocity){
		return this.isPossibleInitialHorizontalVelocity(initialHorizontalVelocity) && 
				matchesMaximumHorizontalVelocityInitialHorizontalVelocity(this.getMaximumHorizontalVelocity(), initialHorizontalVelocity);
	}
	
	
	/**
	 *  Check whether this game object can have the given maximum horizontal velocity as its maximum horizontal velocity.
	 *  
	 * @param 	maximumHorizontalVelocity
	 * 			The maximum horizontal velocity to be checked.
	 * @return	True if and only if the given maximum horizontal velocity matches with the initial horizontal
	 * 			velocity of this game object.
	 * 			|result =  matchesMaximumHorizontalVelocityInitialHorizontalVelocity(maximumHorizontalVelocity, this.getInitialHorizontalVelocity());
	 */
	@Raw
	public boolean canHaveAsMaximumHorizontalVelocity(double maximumHorizontalVelocity){
		return isPossibleMaximumHorizontalVelocity(maximumHorizontalVelocity)
				&& matchesMaximumHorizontalVelocityInitialHorizontalVelocity(maximumHorizontalVelocity, this.getInitialHorizontalVelocity());
	}
	
	/**
	 * Check whether the given maximum horizontal velocity matches with the given initial horizontal velocity of a game object.
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
	 * Check whether the given initial horizontal velocity is a possible initial horizontal velocity for this game object.
	 * @param 	initialHorizontalVelocity
	 * 			The initial horizontal velocity to check.
	 */
	public abstract boolean isPossibleInitialHorizontalVelocity(double initialHorizontalVelocity);

	public abstract boolean isPossibleMaximumHorizontalVelocity(double maximumHorizontalVelocity);
	
	/**
	 * Variable registering the horizontal velocity of this game object.
	 */
	private double horizontalVelocity = 0;

	/**
	 *  Variable registering the vertical velocity of this game object.
	 */
	private double verticalVelocity = 0;
	
	/**
	 * Variable registering the initial horizontal velocity of this game object when not ducking.
	 */
	private double initialHorizontalVelocity=0;
	
	/**
	 * Variable registering the maximum horizontal velocity of this game object.
	 */
	private double maximumHorizontalVelocity=0;
	
	/**
	 * Variable registering the initial vertical velocity of this game object.
	 */
	private final double initialVerticalVelocity;
	
	
	/**
	 * Check whether the given horizontal acceleration is a valid horizontal acceleration.
	 * @param	horizontalAcceleration
	 * 			The horizontal acceleration to be checked.
	 */
	public abstract boolean canHaveAsHorizontalAcceleration(double horizontalAcceleration);

	/**
	 * Returns the horizontal acceleration of this game object.
	 * 
	 * @return	If this game object is not moving horizontally or the absolute value of this game object's horizontal 
	 * 			velocity is equal to its maximum horizontal velocity, the horizontal acceleration of this game object is equal to zero.
	 * 			Otherwise the horizontal acceleration of this game object is equal to horizontalAcceleration.
	 * 			| if ((!this.isMovingHorizontally() && this.getInitialHorizontalVelocity()!=0) || Util.fuzzyEquals(Math.abs(this.getHorizontalVelocity()), this.getMaximumHorizontalVelocity()))
	 * 			| 	result==0
	 * 			| else
	 * 			| 	result==this.horizontalAcceleration
	 */
	@Raw
	public double getHorizontalAccelerationForUpdate(){
		if (!this.isMovingHorizontally() || 
				Util.fuzzyEquals(Math.abs(this.getHorizontalVelocity()), this.getMaximumHorizontalVelocityForUpdate())){
			return 0;
		}
		else{
			return this.getHorizontalAcceleration();
		}
	}
	
	public double getHorizontalAcceleration(){
		return this.horizontalAcceleration;
	}
	
	public void setHorizontalAcceleration(double horizontalAcceleration){
		if(canHaveAsHorizontalAcceleration(horizontalAcceleration)){
			this.horizontalAcceleration=horizontalAcceleration;
		}
	}
	
	/**
	 * Return the vertical acceleration of this game object.
	 */
	@Raw
	public abstract double getVerticalAcceleration();
	
	/**
	 * Check whether this game object can have the given vertical acceleration as its vertical acceleration.
	 * @param 	verticalAcceleration
	 * 			The vertical acceleration to be checked.
	 */
	public abstract boolean canHaveAsVerticalAcceleration(double verticalAcceleration);

	/**
	 *  Variable registering the horizontal acceleration of a game object.
	 */
	private double horizontalAcceleration;
	
	/**
	 *  Constant registering the gravitational acceleration.
	 */
	protected static final double VERTICAL_ACCELERATION = -10;
	
	/**
	 *  Return the direction of this game object.
	 */
	@Basic
	@Raw
	public Direction getDirection(){
		return this.direction;
	}
	
	/**
	 * Set the direction of this game object to the given direction.
	 * 
	 * @param 	direction
	 * 			The new direction for this game object.
	 * @pre		The given direction must be either LEFT or RIGHT.
	 * 			| (direction == Direction.LEFT) || (direction == Direction.RIGHT)
	 */
	@Raw
	public void setDirection(Direction direction){
		assert (direction==Direction.LEFT || direction==Direction.RIGHT);
		this.direction = direction;
	}
	
	/**
	 * Check whether the given direction is a valid direction.
	 * 
	 * @param 	direction
	 * 			The direction to be checked.
	 * @return	True if and only if the given direction is either LEFT or RIGHT.
	 * 			|result == ((direction == Direction.LEFT) || (direction == Direction.RIGHT))
	 */
	public static boolean isValidDirection(Direction direction){
		return ((direction == Direction.LEFT) || (direction == Direction.RIGHT));
	}
	
	/**
	 * Check whether the given game object is ducking or not.
	 * 
	 * @return	True if and only if the game object is ducking.
	 * 			| result == this.ducking
	 */
	@Basic 
	@Raw
	public boolean isDucking() {
		return this.ducking;
	}
	
	/**
	 * Check whether this game object can have the given ducking state as its ducking state.
	 * @param 	ducking
	 * 			The given ducking state.
	 */
	public abstract boolean canHaveAsDuckingState(boolean ducking);
	
	public void setWantsEndDuck(boolean wantsEndDuck){
		this.wantsEndDuck=wantsEndDuck;
	}
	
	public boolean wantsEndDuck(){
		return this.wantsEndDuck;
	}
	
	/**
	 * Set the ducking state of this game object to the given ducking state.
	 * 
	 * @param 	ducking
	 * 			The new ducking state for this game object.
	 * @post	The new ducking state of this game object is equal to 
	 * 			the state expressed by the variable ducking.
	 * 			| new.isDucking() = ducking
	 * @throws	IllegalArgumentException
	 * 			The game object can not have the given ducking state as its ducking state.
	 * 			| !canHaveAsDuckingState(ducking)
	 * @throws	IllegalStateException
	 * 			If this method wants to change the ducking state of this game object to true when
	 * 			this game object is currently ducking and unable to stand up.
	 * 			| this.isDucking() && !this.canStandUp() && ducking==false
	 */
	@Raw
	public void setDucking(boolean ducking) throws IllegalStateException, IllegalArgumentException{
		if(!this.canHaveAsDuckingState(ducking)){
			throw new IllegalArgumentException();
		}
		if(this.isDucking() && !this.canStandUp() && ducking==false){
			throw new IllegalStateException();
		}
		this.ducking = ducking;
	}
	
	/**
	 * Check whether the game object is currently able to stand up.
	 * @return	True if the game object is already standing up.
	 * 			|if (!this.isDucking())
	 * 			|	result==true
	 * 			Otherwise true if the sprite of the standing game object doesn't coincide with terrain at the current location.
	 * 			| else
	 * 			|	result==!this.getWorld().areaCoincidesWithTerrain(this.getEffectiveHorizontalLocation(), 
	 *			|		this.getEffectiveVerticalLocation()+1, this.getImageAt(0).getWidth()-1, 
	 *			|		this.getImageAt(0).getHeight())[1]
	 */
	public boolean canStandUp(){
		if(!this.isDucking()){
			return true;
		}
		else{
			return !this.getWorld().areaCoincidesWithTerrain(this.getEffectiveHorizontalLocation(), 
					this.getEffectiveVerticalLocation()+1, this.getImageAt(0).getWidth()-1, 
					this.getImageAt(0).getHeight())[1];
		}
	}
	
	/**
	 * Variable registering if the game object is currently ducking and wants to stand up but is unable to do so.
	 */
	private boolean wantsEndDuck=false;
	
	/**
	 * Variable registering the ducking state of this game object.
	 */
	private boolean ducking = false;
	
	/**
	 * Return the jumping state of this game object.
	 */
	public boolean isJumping(){
		return this.isJumping;
	}
	
	/**
	 * Set the jumping state of this game object.
	 * @param 	jumping
	 *			The new jumping state of this game object.
	 * @post	The new jumping state of this game object is equal to the given jumping state.
	 * 			| new.isJumping()==jumping
	 */
	public void setJumping(boolean jumping){
		this.isJumping=jumping;
	}
	
	/**
	 * Variable registering the jumping state of this game object.
	 */
	private boolean isJumping=false;
	
	/**
	 * Return the horizontal moving state of this game object.
	 */
	@Raw
	public boolean isMovingHorizontally(){
		return this.isMovingHorizontally;
	}
	
	/**
	 * Set the horizontal moving state of this game object to the given horizontal moving state.
	 * @param 	isMovingHorizontally
	 * 			A horizontal moving state.
	 * @post	The new horizontal moving state of this game object is equal to the given horizontal moving state.
	 * 			| new.isMovingHorizontally()==isMovingHorizontally
	 */
	public void setMovingHorizontally(boolean isMovingHorizontally){
		this.isMovingHorizontally=isMovingHorizontally;
	}
	
	/**
	 * Variable registering the current horizontal moving state of this game object.
	 */
	private boolean isMovingHorizontally=false;
	
	/**
	 * Check whether the given game object overlaps with the given tiles.
	 * 
	 * @param 	gameObject
	 * 			The given game object.
	 * @param 	tiles
	 * 			The given tiles.
	 * @return	Return true if and only if the game object overlaps with at least one of
	 * 			the given tiles.
	 * 			|overlap = false
	 * 			|coveredTiles = the tile overlap by the game object
	 * 			|for each tile in tiles
	 * 			|	for each coveredTile in coveredTiles
	 * 			|		if(Arrays.equals(tile, coveredTile))
	 * 			|		then overlap = true
	 * 
	 * @throws	IllegalArgumentException
	 * 			If the world of this game object can not have the given game object as its game object.
	 * 			|!world.canHaveAsGameObject(gameObject)
	 */
	protected boolean gameObjectOverlapsWithTiles(GameObject gameObject, int [][] tiles)
	throws IllegalArgumentException{
		World world = this.getWorld();
		if(!world.canHaveAsGameObject(gameObject))
			throw new IllegalArgumentException();
		boolean overlap = false;
		int [][] coveredTiles = world.getTilePositionsIn(gameObject.getEffectiveHorizontalLocation(), gameObject.getEffectiveVerticalLocation(), gameObject.getEffectiveHorizontalLocation()+gameObject.getWidth(), gameObject.getEffectiveVerticalLocation()+gameObject.getHeight());				
		outerloop:
		for(int [] tile : tiles)
			for(int [] coveredTile: coveredTiles)
				if(Arrays.equals(tile, coveredTile)){
					overlap = true;
					break outerloop;	
				}
		return overlap;
	}
	
	/**
	 * Return a list with the game objects that overlap with the given tiles.
	 *
	 * @param 	tiles
	 * 			The given tiles.
	 * @return 	The method iterates over all the game objects in the world except this game object. 
	 * 			If a game object overlaps with one of the given tiles, than it is added to the list which contains 
	 * 			all the overlapping game objects. At the end this list is returned.
	 * 			|for each game object of the world
	 * 			|	if(world.canHaveAsGameObject(gameObject) && gameObject != this)
	 * 			|    and if(gameObjectOverlapsWithTiles(gameObject, tiles))
	 * 			|	then the game object is added to the list of overlapping game objects
	 * 			|result == gameObjects
	 * 
	 */
	protected List<GameObject> getGameObjectsAtTiles(int [][] tiles){
		List<GameObject> gameObjects = new ArrayList<GameObject>();
		World world = this.getWorld();
		for(int index = 0; index < world.getNbGameObjects(); index++){
			GameObject gameObject = world.getGameObjectAtIndex(index);
			if(world.canHaveAsGameObject(gameObject) && gameObject != this){
				if(gameObjectOverlapsWithTiles(gameObject, tiles))	
					gameObjects.add(gameObject);
			}
		}
		return gameObjects;		
	}
	
	/**
	 * Return a matrix with at each row a coordinate of the top perimeter of this game object.
	 * 
	 * @param 	pixelX
	 * 			The given horizontal location.
	 * @param 	pixelY
	 * 			The given vertical location.
	 * @param 	width
	 * 			The given width of the game object.
	 * @param 	height
	 * 			The given height of the game object.
	 * @return	The method iterates of all (x...x + Width -1) and for each horizontal location 
	 * 			a list [X, Y] is added at the matrix with X equal to the current x of 
	 * 			the iteration plus pixelX and Y always equal to pixelY + height -1.
	 * 			The resulting matrix is returned.
	 * 			|for each X in 0...width -1
	 * 			|	topPerimeter [X][0] = pixelX + X;
	 *			|	topPerimeter [X][1] = Y;
	 *			|result == topPerimeter
	 * @throws 	IllegalArgumentException
	 * 			If the world can not have the given location as its location.
	 * 			|!world.canHaveAsPixelLocation(pixelX, pixelY)
	 */
	public int [][] getTopPerimeterOfGameObject(int pixelX, int pixelY,int width, int height)
			throws IllegalArgumentException{
				if(!world.canHaveAsPixelLocation(pixelX, pixelY))
					throw new IllegalArgumentException();
				int Y = pixelY + height - 1;
				int [][] topPerimeter = new int [width - 1][2];
				for (int X = 0; X < width - 1; X++ ){
					topPerimeter [X][0] = pixelX + X;
					topPerimeter [X][1] = Y;
				}
				return topPerimeter.clone();
			}
	
	/**
	 * Return a matrix with at each row a coordinate of the bottom perimeter of this game object.
	 * 
	 * @param 	pixelX
	 * 			The given horizontal location.
	 * @param 	pixelY
	 * 			The given vertical location.
	 * @param 	width
	 * 			The given width of the game object.
	 * @return	The method iterates of all (x...x + Width -1) and for each horizontal location 
	 * 			a list [X, Y] is added at the matrix with X equal to the current x of 
	 * 			the iteration plus pixelX and Y always equal to pixelY.
	 * 			The resulting matrix is returned.
	 * 			|for each Y in 0...width -1
	 * 			|	bottomPerimeter [X][0] = pixelX + X;
	 *			|	bottomPerimeter [X][1] = pixelY;
	 *			|result == bottomPerimeter
	 * @throws 	IllegalArgumentException
	 * 			If the world can not have the given location as its location.
	 * 			|!world.canHaveAsPixelLocation(pixelX, pixelY)
	 */
	public int [][] getBottomPerimeterOfGameObject(int pixelX, int pixelY,int width)
			throws IllegalArgumentException{
				if(!world.canHaveAsPixelLocation(pixelX, pixelY))
					throw new IllegalArgumentException();
				int [][] bottomPerimeter = new int [width - 1][2];
				for (int X = 0; X < width - 1; X++ ){
					bottomPerimeter [X][0] = pixelX + X;
					bottomPerimeter [X][1] = pixelY;
				}
				return bottomPerimeter.clone();
			}

	/**
	 * Return a matrix with at each row a coordinate of the left perimeter of this game object.
	 * 
	 * @param 	pixelX
	 * 			The given horizontal location.
	 * @param 	pixelY
	 * 			The given vertical location.
	 * @param 	height
	 * 			The given height of the game object.
	 * @return	The method iterates of all (y + 1...y + height - 2) and for each vertical location 
	 * 			a list [X, Y] is added at the matrix with X equal to pixelX
	 * 			 and Y equal to the given y of the iteration + pixelY + 1.
	 * 			The resulting matrix is returned.
	 * 			|for each Y in 0...height-2
	 * 			|	leftPerimeter [Y][0] = pixelX;
	 *			|	leftPerimeter [Y][1] = pixelY + Y + 1;
	 *			|result == leftPerimeter
	 * @throws 	IllegalArgumentException
	 * 			If the world can not have the given location as its location.
	 * 			|!world.canHaveAsPixelLocation(pixelX, pixelY)
	 */
	public int [][] getLeftPerimeterOfGameObject(int pixelX, int pixelY, int height)
	throws IllegalArgumentException{
		if(!world.canHaveAsPixelLocation(pixelX, pixelY))
			throw new IllegalArgumentException();
		int [][] leftPerimeter = new int [height-2][2];
		for (int Y = 0; Y < height-2; Y++ ){
			leftPerimeter [Y][0] = pixelX;
			leftPerimeter [Y][1] = pixelY + Y + 1;
		}
		return leftPerimeter.clone();
	}
	
	/**
	 * Return a matrix with at each row a coordinate of the right perimeter of this game object.
	 * 
	 * @param 	pixelX
	 * 			The given horizontal location.
	 * @param 	pixelY
	 * 			The given vertical location.
	 * @param 	width
	 * 			The given width of the game object.
	 * @param 	height
	 * 			The given height of the game object.
	 * @return	The method iterates of all (y + 1...y + height - 2) and for each vertical location 
	 * 			a list [X, Y] is added at the matrix with X equal to pixelX + width - 1
	 * 			 and Y equal to the given y of the iteration + pixelY + 1.
	 * 			The resulting matrix is returned.
	 * 			|for each Y in 0...height-2
	 * 			|	leftPerimeter [Y][0] = pixelX;
	 *			|	leftPerimeter [Y][1] = pixelY + Y + 1;
	 *			|result == rightPerimeter
	 * @throws 	IllegalArgumentException
	 * 			If the world can not have the given location as its location.
	 * 			|!world.canHaveAsPixelLocation(pixelX, pixelY)
	 */
	public int [][] getRightPerimeterOfGameObject(int pixelX, int pixelY, int width, int height)
			throws IllegalArgumentException{
				if(!world.canHaveAsPixelLocation(pixelX, pixelY))
					throw new IllegalArgumentException();
				int [][] rightPerimeter = new int [height-2][2];
				int X = pixelX + width - 1;
				for (int Y = 0; Y < height-2; Y++ ){
					rightPerimeter [Y][0] = X;
					rightPerimeter [Y][1] = pixelY + Y + 1;
				}
				return rightPerimeter.clone();
			}
	

	public int [] checkAllowedLeftRightTopBottomSideOverlap(){
		int [] overlappingGameObjects = this.checkLeftRightTopBottomSideOverlap(this.getLeftPerimeterOfGameObject(getEffectiveHorizontalLocation(), getEffectiveVerticalLocation(), getHeight()),
				 this.getRightPerimeterOfGameObject(getEffectiveHorizontalLocation(), getEffectiveVerticalLocation(), getWidth(), getHeight()),
				 this.getTopPerimeterOfGameObject(getEffectiveHorizontalLocation(), getEffectiveVerticalLocation(), getWidth(), getHeight()),
				 this.getBottomPerimeterOfGameObject(getEffectiveHorizontalLocation(), getEffectiveVerticalLocation(), getWidth()));
		return overlappingGameObjects.clone();
	}
	
	
	public boolean checkNotAllowedLeftRightTopBottomSideOverlap(int pixelX1, int pixelY1, int width1, int height1){
		World world = this.getWorld();
		boolean overlap = false;
		if(world.getGameHasStarted()){
			List<GameObject> gameObjects = getGameObjectsAtTiles(world.getTilePositionsIn(getEffectiveHorizontalLocation(), getEffectiveVerticalLocation(), getEffectiveHorizontalLocation()+getWidth(), getEffectiveVerticalLocation()+getHeight()));
			for(int index = 0; index < gameObjects.size(); index++){
				GameObject gameObject = gameObjects.get(index);
				if(gameObject != this && gameObject != null && world.canHaveAsGameObject(gameObject)){
					int pixelX2 = gameObject.getEffectiveHorizontalLocation();
					int pixelY2 = gameObject.getEffectiveVerticalLocation();
					int width2 = gameObject.getWidth();
					int height2 = gameObject.getHeight();
					if(!(pixelX1 + 1 + (width1 - 3) < pixelX2 || pixelX2 + 1 + (width2 -3) < pixelX1 || pixelY1 + 1 + (height1 - 3) < pixelY2 || pixelY2 + 1 + (height2 - 3) < pixelY1)){
						return true;
					}
				}
			}
		}
		return overlap;
	}
	
	/**
	 * Check whether the left, right, top or bottom of this game object overlaps with another game object.
	 * 
	 * @return	The method iterates over all the game objects that can overlap with the given
	 * 			game object. That are the game objects that overlap with one of the tile with
	 * 			which this game object overlaps. If another game object is found that overlaps 
	 * 			with one of the given sides then an array is returned with at the first position
	 * 			number one defining that there is overlap and at the second position the index 
	 * 			of the game object in the world.
	 * 			|overlap = {0,0}
	 * 			|for each game object in gameGameObjectAtTile of this game object
	 * 			|	if(gameObject != this && world.canHaveAsGameObject(gameObject))
	 * 			|		overlap = checkLeftOrRightSideOverlap(gameObject);
	 *			|		if(overlap [0] == 1)
	 *			|		then the array "overlap" is returned
	 *			|		or 
	 *			|		overlap = checkTopOrBottomSideOverlap(gameObject);
	 *			|		if(overlap [0] == 1)
	 *			|		then the array "overlap" is returned
	 */
	public int [] checkLeftRightTopBottomSideOverlap(int [][] leftPerimeter1, int [][] rightPerimeter1, int [][] topPerimeter1, int [][] bottomPerimeter1){
		int [] overlap = {0,0,0,0};
		World world = this.getWorld();
		List<GameObject> gameObjects = getGameObjectsAtTiles(world.getTilePositionsIn(getEffectiveHorizontalLocation(), getEffectiveVerticalLocation(), getEffectiveHorizontalLocation()+getWidth(), getEffectiveVerticalLocation()+getHeight()));
		for(int index = 0; index < gameObjects.size(); index++){
			GameObject gameObject = gameObjects.get(index);
			if(gameObject != this && gameObject != null && world.canHaveAsGameObject(gameObject)){
				int [][] leftPerimeter2 = gameObject.getLeftPerimeterOfGameObject(gameObject.getEffectiveHorizontalLocation(), gameObject.getEffectiveVerticalLocation(), gameObject.getHeight());
				int [][] rightPerimeter2 = gameObject.getRightPerimeterOfGameObject(gameObject.getEffectiveHorizontalLocation(), gameObject.getEffectiveVerticalLocation(), gameObject.getWidth(), gameObject.getHeight());
				overlap = checkLeftOrRightSideOverlap(gameObject, leftPerimeter1, rightPerimeter1, leftPerimeter2, rightPerimeter2);
				if(overlap [0] == 1){
					return overlap;
				}
				int [][] topPerimeter2 = gameObject.getTopPerimeterOfGameObject(gameObject.getEffectiveHorizontalLocation(), gameObject.getEffectiveVerticalLocation(), gameObject.getWidth(), gameObject.getHeight());
				int [][] bottomPerimeter2 = gameObject.getBottomPerimeterOfGameObject(gameObject.getEffectiveHorizontalLocation(), gameObject.getEffectiveVerticalLocation(), gameObject.getWidth());
				overlap = checkTopOrBottomSideOverlap(gameObject, topPerimeter1, bottomPerimeter1, topPerimeter2, bottomPerimeter2);
				if(overlap [0] == 1){
					return overlap;
				}
			}
		}
		return overlap;
		}
	
	/**
	 * Check whether the top or bottom side of this game object overlaps with the
	 * top or bottom side of the given game object.
	 * 
	 * @return	The method iterates over the positions of the top and bottom perimeter of this game object
	 * 			and the top and bottom perimeter of the given game object. 
	 * 			If one of the top positions is equal another other bottom position or the other way around
	 * 			then an array is returned with at the first position the number one defining that 
	 * 			there is overlap and at the second position the index of the game object in the world.
	 * 			|overlap = {0,0}
	 * 			|for position1 in top perimeter and bottom perimeter
	 * 			|	for position 2 in top perimeter and bottom perimeter
	 * 			|		if(position1top == position2bottom || position1bottom == position2top)
	 * 			|		then
	 *			|		overlap [0] = 1 ;
	 *			|		overlap [1] = world.getIndexOfGameObject(gameObject);
	 *			|result == overlap 
	 */
	public int [] checkTopOrBottomSideOverlap(GameObject gameObject, int [][] topPerimeter1, int [][] bottomPerimeter1, int [][] topPerimeter2, int [][] bottomPerimeter2){
		int [] overlap = {0,0,0,0};
		World world = this.getWorld();		
		outerloop:
		for (int Y = 0; Y < this.getWidth()-1-2; Y++ ){
			for (int Y2 = 0; Y2 < gameObject.getWidth()-1-2; Y2++ ){
				if(Arrays.equals(topPerimeter1[Y],bottomPerimeter2[Y2])){
					overlap [0] = 1 ;
					overlap [1] = world.getIndexOfGameObject(gameObject);
					overlap [3] = 1;
					break outerloop;
				}
				if(Arrays.equals(topPerimeter2[Y2],bottomPerimeter1[Y])){
					overlap [0] = 1 ;
					overlap [1] = world.getIndexOfGameObject(gameObject);
					overlap [2] = 1;
					break outerloop;
				}
			}
		}
		return overlap.clone();
	}
	
	/**
	 * Check whether the left or right side of this game object overlaps with the
	 * left or right side of the given game object.
	 * 
	 * @return	The method iterates over the positions of the left and right perimeter of this game object
	 * 			and the left and right perimeter of the given game object. 
	 * 			If one of the left positions is equal another right position or the other way around
	 * 			then an array is returned with at the first position the number one defining that 
	 * 			there is overlap and at the second position the index of the game object in the world.
	 * 			|overlap = {0,0}
	 * 			|for position1 in left perimeter and right perimeter
	 * 			|	for position 2 in left perimeter and right perimeter
	 * 			|		if(position1left == position2right || position1right == position2left)
	 * 			|		then
	 *			|		overlap [0] = 1 ;
	 *			|		overlap [1] = world.getIndexOfGameObject(gameObject);
	 *			|result == overlap 
	 */
	public int [] checkLeftOrRightSideOverlap(GameObject gameObject, int [][] leftPerimeter1, int [][] rightPerimeter1, int [][] leftPerimeter2, int [][] rightPerimeter2){
		int [] overlap = {0,0,0,0};
		World world = this.getWorld();
		outerloop:
		for (int Y = 0; Y < this.getHeight()-2-2; Y++ ){
			for (int Y2 = 0; Y2 < gameObject.getHeight()-2-2; Y2++ ){
				if(Arrays.equals(leftPerimeter1[Y],rightPerimeter2[Y2]) ||
						Arrays.equals(leftPerimeter2[Y2],rightPerimeter1[Y])){
					overlap [0] = 1 ;
					overlap [1] = world.getIndexOfGameObject(gameObject);
					break outerloop;
				}
			}
		}
		return overlap.clone();
	}
	
	/**
	 * Method the selects the appropriate response for this game object 
	 * after a collision with another game object.
	 * @param 	index
	 * 			The index of the other game object.
	 */
	protected abstract void collisionReaction(int index1, int index2, int index3);
	
	/**
	 * Variable registering the direction game object is facing.
	 */
	private Direction direction = Direction.LEFT;
	
	public double getDeltaTimeForPixel(double deltaTime){
		double time =  0.01/(Math.sqrt((Math.pow(this.getHorizontalVelocity(),2)+Math.pow(this.getVerticalVelocity(), 2)))+
				Math.sqrt((Math.pow(this.getHorizontalAcceleration(),2)+Math.pow(this.getVerticalAcceleration(), 2)))*deltaTime);
		if(isValidDeltaTime(time))
			return time;
		else
			return 0.03;
	}
	
	/**
	 * Update the location and velocity of this game object.
	 * 
	 * @param 	deltaTime
	 * 			The period of time that is used to update this GameObject.
	 */
	public abstract void advanceTime(double deltaTime);
	
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
	 * Set the new jumping state of this game object depending on its location.
	 * @effect	If the game object's bottom perimeter overlaps with a terrain tile, the jumping state is set to false.
	 * 			Otherwise the jumping state is set to true.
	 * 			| if(!this.canHaveAsLocation(this.getHorizontalLocation(), this.getVerticalLocation()-1))
	 *			| 	this.setJumping(false)
	 *			| else
	 *			| 	this.setJumping(true)
	 */
	protected void calculateNewJumpingState() {
		this.setJumping(true);
		if(!this.canHaveAsLocation(this.getHorizontalLocation(), this.getVerticalLocation()-1) || this.contact == true){
			this.setJumping(false);
		}
	}
	
	public boolean getContact(){
		return this.contact;
	}
	
	public void setContact(boolean contact){
		this.contact = contact;
	}
	
	
	protected boolean contact;
	
	boolean contact2;

	public void collisionHandler(int [] overlap, double oldHorizontalLocation, double oldVerticalLocation){
		GameObject gameObject = this.getWorld().getGameObjectAtIndex(overlap[1]);
		if(overlap[0]==1){
			collisionReaction(overlap[1],overlap[2],overlap[3]);
			if(!( gameObject instanceof Plant)){
				this.setContact(true);
				gameObject.setContact(true);
			}
		}
		else if(this.getContact() == true){
			if(checkNotAllowedLeftRightTopBottomSideOverlap(this.getEffectiveHorizontalLocation(), this.getEffectiveVerticalLocation(), this.getWidth(), this.getHeight())){
				if(checkNotAllowedLeftRightTopBottomSideOverlap((int) oldHorizontalLocation, this.getEffectiveVerticalLocation(), this.getWidth(), this.getHeight())){
					this.setVerticalLocation(oldVerticalLocation);
					if(this instanceof Mazub){
						this.setVerticalVelocity(0);
					}
				}
				if(checkNotAllowedLeftRightTopBottomSideOverlap(this.getEffectiveHorizontalLocation(), (int) oldVerticalLocation, this.getWidth(), this.getHeight())){
					this.setHorizontalLocation(oldHorizontalLocation);
				}
			}
			else{
				this.setContact(false);
				gameObject.setContact(false);
			}
		}
	}
	
	/**
	 * Return the time since the start of the water contact of this game object.
	 * The time is reset when the game object loses hit points or no longer makes contact.
	 */
	public double getTimeSinceStartWaterContact(){
		return this.timeSinceWaterContact;
	}
	
	/**
	 * Return the time since the start of the magma contact of this game object.
	 * The time is reset when the game object loses hit points or no longer makes contact.
	 */
	public double getTimeSinceStartMagmaContact(){
		return this.timeSinceMagmaContact;
	}
	
	/**
	 * Set the time since the start of the water contact of this game object.
	 * 
	 * @param	time
	 * 			The given time.
	 * @throws	IllegalArgumentException
	 * 			|!isValidTimeSinceMove(time)
	 * 			
	 */
	public void setTimeSinceStartWaterContact(double time)
	throws IllegalArgumentException{
		if(!isValidTimeSinceAction(time))
			throw new IllegalArgumentException();
		this.timeSinceWaterContact = time;
			
	}
	
	/**
	 * Set the time since the start of the magma contact of this game object.
	 * 
	 * @param	time
	 * 			The given time.
	 * @throws	IllegalArgumentException
	 * 			|!isValidTimeSinceMove(time)
	 * 			
	 */
	public void setTimeSinceStartMagmaContact(double time)
			throws IllegalArgumentException{
				if(!isValidTimeSinceAction(time))
					throw new IllegalArgumentException();
				this.timeSinceMagmaContact = time;		
	}
	
	/**
	 * Return the time since the start of the last action of this game object.
	 */
	public double getTimeSinceStartAction() {
		return this.timeSinceStartAction;
	}

	/**
	 * Set the time since the  start of the last action of this game object.
	 * @param 	timeSinceStartAction
	 * 			The time since the start of the last action to be set.
	 * @post	| new.timeSinceStartAction == timeSinceStartAction
	 */
	public void setTimeSinceStartAction(double timeSinceStartAction) {
		this.timeSinceStartAction = timeSinceStartAction;
	}
	
	/**
	 * Check whether the given time is a valid time period since game object's last action.
	 * @param 	time
	 * 			The time period to be checked.
	 * @return	True if the given time period is greater than or equal to zero.
	 * 			| result == Util.fuzzyGreaterThanOrEqualTo(time, 0)
	 */
	protected static boolean isValidTimeSinceAction(double time){
		return Util.fuzzyGreaterThanOrEqualTo(time, 0);
	}
	
	/**
	 * Variable registering the time since this game object is making contact with water.
	 */
	private double timeSinceWaterContact = 0;
	
	/**
	 * Variable registering the time since this game object is making contact with magma.
	 */
	private double timeSinceMagmaContact = 0;
	
	/**
	 * The time since the start of the last action of this game object, the type of action is dependent on the type of the game object.
	 */
	private double timeSinceStartAction = 0;
	
	/**
	 * Check whether the game object makes contact with water and take the corresponding actions.
	 */
	public abstract void checkWaterContact(double deltaTime);
	
	/**
	 *Check whether the game object makes contact with magma and take the corresponding actions. 
	 */
	public abstract void checkMagmaContact(double deltaTime);
	
	/**
	 * Return the current Sprite of this game object.
	 */
	public Sprite getCurrentSprite(){
		if(this.getDirection() == Direction.LEFT)
			return getImageAt(0);
		else 
			return getImageAt(1);
	}
	
	/**
	 * Return a copy of the current image array of this game object.
	 */
	@Basic 
	@Raw
	public Sprite[] getImages(){
		return this.images.clone();
	}
	
	/**
	 * Return the number of images in the current image array of this game object.
	 */
	@Raw
	public int getNbImages(){
		return this.getImages().length;
	}
	
	/**
	 * Check whether this game object can have the given number of images as the number of images in its image array.
	 */
	public abstract boolean canHaveAsNbImages(int nbImages);
	
	/**
	 * Return the image in the image array of this game object at the given sprite index.
	 * 
	 * @pre		The given sprite index must be a valid sprite index.
	 * 			| isValidSpriteIndex(spriteIndex)
	 */
	@Basic
	@Raw
	public Sprite getImageAt(int spriteIndex) throws IllegalArgumentException{
		assert canHaveAsSpriteIndex(spriteIndex):
			"The given sprite index is not a valid sprite index!";
		return this.getImages()[spriteIndex];
	}
	
	
	/**
	 * Check whether to given sprite index is a valid sprite index.
	 * 
	 * @param 	spriteIndex
	 * 			The sprite index to be checked.
	 * @return	True if the given sprite index is greater than or equal to zero and 
	 * 			smaller than or equal to the number of images in the current image array of this game object.
	 * 			| result == (spriteIndex>=0 && spriteIndex<=this.getNbImages())
	 */
	@Raw
	public boolean canHaveAsSpriteIndex(int spriteIndex){
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
	 * Set the image array of this game object to the given image array.
	 * 
	 * @param 	images
	 * 			The new image array for this game object.
	 * @pre 	The length of the given image array must be a valid length.
	 * 			|isValidNbImages(images.length)
	 * @pre		The images in the given image array must all be valid images.
	 * 			| for i in 1..images.length:
	 * 			|	isValidImage(images[i])
	 * @post	The new image array of this game object is equal to copy of he given image array.
	 * 			| this.images==images.clone()
	 */
	@Raw
	public void setImages(Sprite[] images){
		assert canHaveAsNbImages(images.length):
			"Not a valid number of images in the given image array!";
		for (Sprite image:images){
			assert isValidImage(image):
				"The given image array contains at least one image that isn't valid!";		
		}
		this.images=images.clone();
	}
	
	
	/**
	 * Return the height of the currrent sprite of this game object.
	 */
	@Raw
	public int getHeight(){
		return this.getCurrentSprite().getHeight();
	}
	
	/**
	 * Return the width of the current sprite of this game object.
	 */
	@Raw
	public int getWidth(){
		return this.getCurrentSprite().getWidth();
	}

	/**
	 * Variable registering the array of images of this game object.
	 */
	protected Sprite images[];
	
	/**
	 * Return the current number of hit-points of this game object.
	 */
	@Basic
	@Raw
	public int getHitPoints() {
		return this.hitPoints;
	}
	
	public boolean isValidMaxHitPoints(int maxHitPoints){
		return maxHitPoints>=0;
	}
	
	
	/**
	 * Return the current maximum number of hit-points of this game object.
	 */
	@Basic
	@Raw
	public int getMaxHitPoints() {
		return this.maxHitPoints;
	}
	
	/**
	 * Remove the given number of hit points from the current number of hit points of this game object.
	 * @param 	hitPoints
	 * 			The amount of hit points to be removed.
	 * @pre		The given number of hit points must be greater than zero.
	 * 			| hitPoints > 0
	 * @effect	| this.setHitPoints(this.getHitPoints() - hitPoints)
	 */
	public void removeHitPoints(int hitPoints){
		assert (hitPoints>0):
			"The given number of hit points to be removed must be positive!";
		int oldHitPoints = getHitPoints();
		this.setHitPoints(oldHitPoints - hitPoints);
	}
	
	/**
	 * Add the given number of hit points to the current number of hit points of this game object.
	 * @param 	hitPoints
	 * 			The amount of hit points to be added.
	 * @pre		The given number of hit points must be greater than zero.
	 * 			| hitPoints > 0
	 * @effect	| this.setHitPoints(this.getHitPoints() + hitPoints)
	 */
	public void addHitPoints(int hitPoints){
		assert (hitPoints>0):
			"The given number of hit points to be removed must be positive!";
		int oldHitPoints = getHitPoints();
		setHitPoints(oldHitPoints + hitPoints);
	}
	
	/**
	 * Set the hit-points of this game object to the given hit-points.
	 * 
	 * @param 	hitPoints
	 * 		  	The new number of hit-points for this game object.
	 * @post	If the given number of hit-points is greater than or equal to the maximum number of hit-points, 
	 * 			the new number of hit-points of this game object is set to the maximum number of hit-points.
	 * 		  	| if (hitPoints>=MAX_HIT_POINTS)
	 *			|		new.hitPoints == MAX_HIT_POINTS
	 * @post	If the given number of hit-points is negative or equal to zero, 
	 * 			the new number of hit-points of this game object is set to 0.
	 * 		  	| if (hitPoints<=0)
	 *			|		new.hitPoints == 0
	 * @post	If the given number of hit-points is in the pre-established range of hit-points for a game object,
	 * 			the new number of hit-points of this game object is set to the given number of hit-points.
	 * 			| if (hitPoints>0 && hitPoints<500){
	 * 			|		new.hitPoints==hitPoints		
	 */
	@Basic
	@Raw
	public void setHitPoints(int hitPoints) {
		if (hitPoints>=getMaxHitPoints()){
			this.hitPoints = getMaxHitPoints();
		}
		else if (hitPoints<=0){
			this.hitPoints = 0;
		}
		else{
			this.hitPoints= hitPoints;
		}
	}
	
	/**
	 * Variable registering the number of hit-points of this game object. 
	 */
	protected int hitPoints;
	
	/**
	 * Variable registering the maximum number of hit-points of this game object. 
	 */
	protected final int maxHitPoints;
	
	/**
	 * Return the world that is currently attached to this game object.
	 */
	public World getWorld(){
		return this.world;
	}
	
	/**
	 * Set a world for this game object.
	 * @param 	world
	 * 			The world for this game object.
	 * @post	| new.getWorld()==world
	 * @throws 	IllegalArgumentException
	 * 			| !canHaveAsWorld(world)
	 */
	public void setWorld(World world) throws IllegalArgumentException{
		if (this.canHaveAsWorld(world)){
			this.world=world;
		}
		else{
			throw new IllegalArgumentException("Not a valid world!");
		}
	}
	
	/**
	 * Variable registering the world that is currently attached to this game object.
	 */
	protected World world;
	
	/**
	 * Break the link between this game object and the world that is attached to it.
	 * @post	|if(this.getWorld()!=null)
	 * 			|	new.getWorld()==null
	 * 			|	this.getWorld().hasAsGameObject(this)==false
	 */
	protected void unsetWorld(){
		World world = this.getWorld();
		if(this.getWorld() != null)
			this.setWorld(null);
			world.removeGameObject(this);
	}
	
	/**
	 * Check whether this game object can have the given world as its world
	 * @param 	world
	 * @return	result == (world==null || world.canHaveAsGameObject(this))
	 */
	protected boolean canHaveAsWorld(World world){
		return (world==null || world.canHaveAsGameObject(this));
	}
	
	/**
	 * Check whether this game object has a proper world.
	 * @return 	This game object can have its world as its world and its world has this game object as one of its game objects.
	 * 			| this.canHaveAsWorld(this.getWorld()) && this.getWorld().hasAsGameObject(this)
	 */
	protected boolean hasProperWorld(){
		return this.canHaveAsWorld(this.getWorld()) && this.getWorld().hasAsGameObject(this);
	}
	
	/**
	 * Check whether this game object is terminated.
	 */
	public boolean isTerminated(){
		return isTerminated;
	}
	
	/**
	 * Terminate this game object.
	 * @post	If this game object is not already terminated its terminated state is set to true, its world is set to null and it is removed as a game object from its world.
	 * 			|if (!this.isTerminated()){
	 *			|	this.getWorld().removeAsGameObject(this);
	 *			|	this.setWorld(null);
	 *			|	this.isTerminated=true;
	 */
	public void terminate(){
		if (!this.isTerminated()){
			this.getWorld().removeAsGameObject(this);
			this.setWorld(null);
			this.isTerminated=true;
		}
	}
	
	/**
	 * Variable registering the terminated state of this game object.
	 */
	private boolean isTerminated=false;
}
