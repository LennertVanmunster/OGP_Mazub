package jumpingalien.model;

import jumpingalien.programs.program.Program;
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
 * @invar	Each game object can have its number of hit points as its number of hit points.
 * 			| canHaveAsHitPoints(getHitPoints())
 * @invar	Each game object has a proper world to which it is attached.
 * 			| hasProperWorld()
 * @invar	Each game object has a proper program to which it is attached.
 * 			| hasProperProgram()
 * @version	2.0
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
	 * 			The initial horizontal velocity of the new game object when the game object starts moving.
	 * @param maximumHorizontalVelocity
	 * 			The maximum horizontal velocity of the new game object.
	 * @param initialVerticalVelocity
	 * 			The initial vertical velocity of the new game object when the game object jumps.
	 * @param horizontalAcceleration
	 * 			The horizontal acceleration of the new game object when the game object moves.
	 * @param hitPoints
	 * 			The hit points of the new game object.
	 * @param maxHitPoints
	 * 			The maximum number of hit points of the new game object.
	 * @param program
	 * 			The program for the new game object
	 * @param images
	 * 			An array of images.
	 * @post	The horizontal location of this new game object is equal to the 
	 * 			given horizontal location.
	 * 			|new.getHorizontalLocation() == horizontalLocation
	 * @post	The vertical location of this new game object is equal to the 
	 * 			given vertical location.
	 * 			|new.getVerticalLocation() == verticalLocation
	 * @post	The initial horizontal velocity
	 * 			of this new game object is equal to the given initial horizontal velocity.
	 * 			|new.getInitialHorizontalVelocity() == initialHorizontalVelocity
	 * @post	The maximum horizontal velocity this new game object is equal to the 
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
	 * @effect	The given program is set as the program of this new game object.
	 * 			|this.setProgram(program)
	 * @effect	A binary relationship is established between this game object and the given program.
	 * 			|this.linkProgram(program)
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
	 * 			Not a valid program.
	 * 			|!canHaveAsProgram(program)
	 * @throws	IllegalArgumentException
	 * 			This game object cannot have the given ducking state as its ducking state.
	 * 			| !canHaveAsDuckingState(ducking)
	 */
	@Raw
	@Model
	protected GameObject(int horizontalLocation, int verticalLocation, double horizontalVelocity,
				double verticalVelocity, double initialHorizontalVelocity, double maximumHorizontalVelocity, double initialVerticalVelocity, double horizontalAcceleration, boolean ducking, int hitPoints, int maxHitPoints, Program program, Sprite... images )
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
				setDirection(Orientation.RIGHT);
			else
				setDirection(Orientation.LEFT);
			if(!isValidMaxHitPoints(maxHitPoints)){
				throw new IllegalArgumentException("Not a valid number of maximum hit points!");
			}
			this.maxHitPoints=maxHitPoints;
			this.setHitPoints(hitPoints);
			this.linkProgram(program);
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
	public double getHorizontalLocation(){
		return this.horizontalLocation;
	}
	
	/**
	 * Return the calculated vertical location of this game object.
	 */
	@Basic
	@Raw
	public double getVerticalLocation(){
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
	public void setHorizontalLocation(double horizontalLocation) throws IllegalLocationException{
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
	public void setVerticalLocation(double verticalLocation) 
			throws IllegalLocationException {
		if(!canHaveAsVerticalLocation(verticalLocation))
			throw new IllegalLocationException(this.getHorizontalLocation(), this.verticalLocation);
		this.verticalLocation = verticalLocation;
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
	 * Return the current maximum horizontal velocity of this game object for use in update calculations.
	 */
	@Raw
	protected abstract double getMaximumHorizontalVelocityForUpdate();
	
	/**
	 * Return the current initial horizontal velocity of this game object for use in update calculations.
	 */
	@Raw
	protected abstract double getInitialHorizontalVelocityForUpdate();
	
	/**
	 * Return the maximum horizontal velocity of this game object.
	 */
	@Basic
	@Raw
	public double getMaximumHorizontalVelocity(){
		return this.maximumHorizontalVelocity;
	}
	
	/**
	 * Return the initial horizontal velocity of this game object.
	 */
	@Basic
	@Raw
	public double getInitialHorizontalVelocity(){
		return this.initialHorizontalVelocity;
	}
	
	/**
	 * Set the initial horizontal velocity of this game object to the given initial horizontal velocity.
	 * @param 	initialHorizontalVelocity
	 * 			The initial horizontal velocity to be set.
	 * @post	The new initial horizontal velocity of this game object is equal to the given initial horizontal velocity.
	 * 			| new.getInitialHorizontalVelocity()==initialHorizontalVelocity
	 * @throws	IllegalArgumentException
	 * 			The given initial horizontal velocity is not a valid initial horizontal velocity.
	 * 			|(!canHaveAsInitialHorizontalVelocity(initialHorizontalVelocity))
	 */
	@Raw
	protected void setInitialHorizontalVelocity(double initialHorizontalVelocity){
		if(!canHaveAsInitialHorizontalVelocity(initialHorizontalVelocity)){
			throw new IllegalArgumentException("This game object cannot have that initial horizontal velocity!");
		}
		this.initialHorizontalVelocity=initialHorizontalVelocity;
	}
	
	/**
	 * Set the maximum horizontal velocity of this game object to the given maximum horizontal velocity.
	 * @param 	maximumHorizontalVelocity
	 * 			The maximum horizontal velocity to be set.
	 * @post	The new maximum horizontal velocity of this game object is equal to the given maximum horizontal velocity.
	 * 			| new.getMaximumHorizontalVelocity()==maximumHorizontalVelocity
	 * @throws	IllegalArgumentException
	 * 			The given maximum horizontal velocity is not a valid maximum horizontal velocity.
	 * 			|(!canHaveAsMaximumHorizontalVelocity(maximumHorizontalVelocity))
	 */
	@Raw
	protected void setMaximumHorizontalVelocity(double maximumHorizontalVelocity){
		if(!canHaveAsMaximumHorizontalVelocity(maximumHorizontalVelocity)){
			throw new IllegalArgumentException("This game object cannot have that maximum horizontal velocity!");
		}
		this.maximumHorizontalVelocity=maximumHorizontalVelocity;
	}
	
	/**
	 * Return the initial vertical velocity of this game object.
	 */
	@Basic
	@Immutable
	@Raw
	protected double getInitialVerticalVelocity(){
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
	@Raw
	protected boolean isValidInitialVerticalVelocity(double initialVerticalVelocity){
		return initialVerticalVelocity>=0;
	}
	
	/**
	 * Check whether this game object can have the given horizontal velocity as its horizontal velocity.
	 * @param 	horizontalVelocity
	 */
	@Raw
	public abstract boolean canHaveAsHorizontalVelocity(double horizontalVelocity);
	
	/**
	 * Check whether this game object can have the given vertical velocity as its vertical velocity.
	 * 
	 * @param 	verticalVelocity
	 * 			The vertical velocity to be checked.
	 * @return	True if and only if the given vertical velocity is less than or equal to the initial vertical velocity constant of this game object.
	 * 			| result ==  Util.fuzzyLessThanOrEqualTo(verticalVelocity, getInitialVerticalVelocity())
	 */
	@Raw
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
	protected static boolean matchesMaximumHorizontalVelocityInitialHorizontalVelocity(
			double maximumHorizontalVelocity, double initialHorizontalVelocity){
		return Util.fuzzyGreaterThanOrEqualTo(maximumHorizontalVelocity,initialHorizontalVelocity);
	}
	
	/**
	 * Check whether the given initial horizontal velocity is a possible initial horizontal velocity for this game object.
	 * @param 	initialHorizontalVelocity
	 * 			The initial horizontal velocity to check.
	 */
	protected abstract boolean isPossibleInitialHorizontalVelocity(double initialHorizontalVelocity);
	
	/**
	 * Check whether the given maximum horizontal velocity is a possible maximum horizontal velocity for this game object.
	 * @param 	maximumHorizontalVelocity
	 * 			The maximum horizontal velocity to check.
	 */
	protected abstract boolean isPossibleMaximumHorizontalVelocity(double maximumHorizontalVelocity);
	
	/**
	 * Variable registering the horizontal velocity of this game object.
	 */
	private double horizontalVelocity = 0;

	/**
	 *  Variable registering the vertical velocity of this game object.
	 */
	private double verticalVelocity = 0;
	
	/**
	 * Variable registering the initial horizontal velocity of this game object.
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
	@Raw
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
	
	/**
	 * Return the horizontal acceleration of this game object.
	 */
	@Basic
	@Raw
	public double getHorizontalAcceleration(){
		return this.horizontalAcceleration;
	}
	
	/**
	 * Set the horizontal acceleration of this game object to the given horizontal acceleration.
	 * @param 	horizontalAcceleration
	 * 			The horizontal acceleration to be set.
	 * @post	If this game object can have the given horizontal acceleration as its horizontal acceleration, 
	 * 			the new horizontal acceleration of this game object is equal to the given horizontal acceleration.
	 * 			|if(canHaveAsHorizontalAcceleration(horizontalAcceleration))
	 * 			|	new.getHorizontalAcceleration()==horizontalAcceleration
	 */
	@Raw
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
	@Raw
	public abstract boolean canHaveAsVerticalAcceleration(double verticalAcceleration);

	/**
	 * Variable registering the horizontal acceleration of a game object.
	 */
	private double horizontalAcceleration;
	
	/**
	 * Constant registering the gravitational acceleration.
	 */
	public static final double VERTICAL_ACCELERATION = -10;
	
	/**
	 *  Return the direction of this game object.
	 */
	@Basic
	@Raw
	public Orientation getDirection(){
		return this.direction;
	}
	
	/**
	 * Set the direction of this game object to the given direction.
	 * 
	 * @param 	direction
	 * 			The new direction for this game object.
	 * @pre		The given direction must be either LEFT or RIGHT.
	 * 			| (direction == Orientation.LEFT) || (direction == Orientation.RIGHT)
	 */
	@Raw
	public void setDirection(Orientation direction){
		assert (direction==Orientation.LEFT || direction==Orientation.RIGHT);
		this.direction = direction;
	}
	
	/**
	 * Check whether the given direction is a valid direction.
	 * 
	 * @param 	direction
	 * 			The direction to be checked.
	 * @return	True if and only if the given direction is either LEFT or RIGHT.
	 * 			|result == ((direction == Orientation.LEFT) || (direction == Orientation.RIGHT))
	 */
	public static boolean isValidDirection(Orientation direction){
		return ((direction == Orientation.LEFT) || (direction == Orientation.RIGHT));
	}
	
	/**
	 * Variable registering the direction game object is facing.
	 */
	private Orientation direction = Orientation.LEFT;
	
	/**
	 * Return the ducking state of this game object.
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
	@Raw
	public abstract boolean canHaveAsDuckingState(boolean ducking);
	
	/**
	 * Set the state of wanting to end duck of this game object to the given boolean.
	 * @param 	wantsEndDuck
	 * 			The  state of wanting to end duck to be set.
	 * @post	The new wanting to end duck state of this game object is equal to the given boolean.
	 * 			| new.wantsEndDuck()==true
	 */
	@Raw
	protected void setWantsEndDuck(boolean wantsEndDuck){
		this.wantsEndDuck=wantsEndDuck;
	}
	
	/**
	 * Return if this object currently wants to end its duck.
	 */
	@Basic
	@Raw
	protected boolean wantsEndDuck(){
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
	protected boolean canStandUp(){
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
	@Basic
	@Raw
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
	@Raw
	public void setJumping(boolean jumping){
		this.isJumping=jumping;
	}
	
	/**
	 * Make this game object jump.
	 */
	public abstract void startJump();
	
	/**
	 * End the jump of this game object.
	 */
	public abstract void endJump();
	
	/**
	 * Variable registering the jumping state of this game object.
	 */
	private boolean isJumping=false;
	
	/**
	 * Return the horizontal moving state of this game object.
	 */
	@Basic
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
	@Raw
	public void setMovingHorizontally(boolean isMovingHorizontally){
		this.isMovingHorizontally=isMovingHorizontally;
	}
	
	/**
	 * Variable registering the current horizontal moving state of this game object.
	 */
	private boolean isMovingHorizontally=false;
	
	/**
	 * Make this game object move in the given direction if it is not already moving.
	 * @param 	direction
	 * 			The desired movement direction.
	 */
	public abstract void startMove(Orientation direction);
	
	
	/**
	 * Make this game object end its movement in the given direction.
	 * @param 	direction
	 * 			The given direction.
	 */
	public abstract void endMove(Orientation direction);
	
	/**
	 * Check whether this game object overlaps with the given tiles.
	 * 
	 * @param 	tiles
	 * 			The given tiles.
	 * @return	Return true if and only if the game object overlaps with at least one of
	 * 			the given tiles.
	 * 			|overlap = false
	 * 			|coveredTiles = the tile overlapped by this game object
	 * 			|for each tile in tiles
	 * 			|	for each coveredTile in coveredTiles
	 * 			|		if(Arrays.equals(tile, coveredTile))
	 * 			|		then overlap = true
	 * 			|result==overlap
	 * @throws	IllegalArgumentException
	 * 			If the world of this game object can not have the given game object as its game object.
	 * 			|!world.canHaveAsGameObject(gameObject)
	 */
	protected boolean overlapsWithTiles(int [][] tiles)
	throws IllegalArgumentException{
		World world = this.getWorld();
		if(!world.canHaveAsGameObject(this)){
			throw new IllegalArgumentException();
		}
		boolean overlap = false;
		int [][] coveredTiles = world.getTilePositionsIn(this.getEffectiveHorizontalLocation(), this.getEffectiveVerticalLocation(), this.getEffectiveHorizontalLocation()+this.getWidth(), this.getEffectiveVerticalLocation()+this.getHeight());				
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
	 */
	protected List<GameObject> getGameObjectsAtTiles(int [][] tiles){
		List<GameObject> gameObjects = new ArrayList<GameObject>();
		World world = this.getWorld();
		for(int index = 0; index < world.getNbGameObjects(); index++){
			GameObject gameObject = world.getGameObjectAtIndex(index);
			if(world.canHaveAsGameObject(gameObject) && gameObject != this && gameObject != null){
				if(gameObject.overlapsWithTiles(tiles)){	
					gameObjects.add(gameObject);
				}
			}
		}
		return gameObjects;		
	}
	
	/**
	 * Return the game object at the given pixel position of the world of this game object.
	 * @param 	pixelX
	 * 			The horizontal pixel position.
	 * @param 	pixelY
	 * 			The vertical pixel position.
	 * @return	The game object that overlaps with the given pixel position. If there is no overlap at the given pixel position,
	 * 			this method returns null.
	 * 			|result==null
	 * 			|int [][] tile = {this.getWorld().getTilePositionAtPixelLocation(pixelX, pixelY)}
	 *			|List<GameObject> gameObjects = this.getGameObjectsAtTiles(tile)
	 *			|for each gameObject in gameObjects:
	 *			|	int pixelX2 = gameObject.getEffectiveHorizontalLocation()
	 *			|	int pixelY2 = gameObject.getEffectiveVerticalLocation()
	 *			|	int width2 = gameObject.getWidth()
	 *			|	int height2 = gameObject.getHeight()
	 *			|	if(!(pixelX < pixelX2 || pixelX2 + (width2 -1) < pixelX || pixelY < pixelY2 || pixelY2 + (height2 - 1) < pixelY)):
	 *			|		result == foundGameObject
	 */
	public GameObject getGameObjectAtPixelPosition(int pixelX, int pixelY){
		int [][] tile = {this.getWorld().getTilePositionAtPixelLocation(pixelX, pixelY)};
		List<GameObject> gameObjects = this.getGameObjectsAtTiles(tile);
		for(int index = 0; index < gameObjects.size(); index++){
			GameObject foundGameObject = gameObjects.get(index);
			int pixelX2 = foundGameObject.getEffectiveHorizontalLocation();
			int pixelY2 = foundGameObject.getEffectiveVerticalLocation();
			int width2 = foundGameObject.getWidth();
			int height2 = foundGameObject.getHeight();
			if(!(pixelX < pixelX2 || pixelX2 + (width2 -1) < pixelX || pixelY < pixelY2 || pixelY2 + (height2 - 1) < pixelY)){
				return foundGameObject;
			}
		}
		return null;
	}
	
	/**
	 * Return a matrix with at each row a pixel coordinate of the top perimeter of this game object.
	 * 
	 * @param 	pixelX
	 * 			The given horizontal location.
	 * @param 	pixelY
	 * 			The given vertical location.
	 * @param 	width
	 * 			The given width of the game object.
	 * @param 	height
	 * 			The given height of the game object.
	 * @return	The method iterates over all (x...x + Width -1) and for each horizontal location 
	 * 			a list [X, Y] is added at the matrix position with X equal to the current x of 
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
	protected int [][] getTopPerimeter(int pixelX, int pixelY,int width, int height)
			throws IllegalArgumentException{
				if(!world.canHaveAsPixelLocation(pixelX, pixelY))
					throw new IllegalArgumentException();
				int Y = pixelY + height - 1;
				int [][] topPerimeter = new int [width][2];
				for (int X = 0; X < width; X++ ){
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
	protected int [][] getBottomPerimeter(int pixelX, int pixelY,int width)
			throws IllegalArgumentException{
				if(!world.canHaveAsPixelLocation(pixelX, pixelY))
					throw new IllegalArgumentException();
				int [][] bottomPerimeter = new int [width][2];
				for (int X = 0; X < width ; X++ ){
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
	protected int [][] getLeftPerimeter(int pixelX, int pixelY, int height)
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
	protected int [][] getRightPerimeter(int pixelX, int pixelY, int width, int height)
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
	
	/**
	 * Check whether the overlap between the given area and this game object is not a correct overlap. 
	 * 
	 * @param 	pixelX1
	 * 			The horizontal location of the area.
	 * @param 	pixelY1
	 * 			The vertical location of the area.
	 * @param	width1
	 * 			The width of the area.
	 * @param 	height1
	 * 			The height of the area.
	 * @return	True if and only if for at least one  game object in the game world the following if statement
	 * 			yields true: 
	 * 			|if(!(pixelX1 + 1 + (width1 - 3) < pixelX2 || pixelX2 + 1 + (width2 -3) < pixelX1 
	 * 			|	|| pixelY1 + 1 + (height1 - 3) < pixelY2 || pixelY2 + 1 + (height2 - 3) < pixelY1))
     *			| then result == true
     *			|else result == false
	 */	
	protected boolean isNotAllowedLeftRightTopBottomSideOverlap(int pixelX1, int pixelY1, int width1, int height1){
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
	 * Check whether the given left, right, top or bottom perimeter overlaps with another game object.
	 */
	protected abstract int [] checkLeftRightTopBottomSideOverlap();
	
	/**
	 * Check whether the top or bottom side of this game object overlaps with the
	 * top or bottom side of the given game object.
	 * 
	 * @return	The method iterates over the positions of the top and bottom perimeter of this game object
	 * 			and the top and bottom perimeter of the given game object. 
	 * 			If one of the top positions of this game object is equal to another bottom position
	 * 			of the other game object then an array is returned with at the first position the 
	 * 			number one defining that there is overlap, at the second position the index of the 
	 * 			game object in the world and at the fourth  position the number one defining that 
	 * 			the other game object is standing on this game object.
	 * 			If one of the bottom positions of this game object is equal to another top position
	 * 			of the other game object then an array is returned with at the first position the 
	 * 			number one defining that there is overlap, at the second position the index of the 
	 * 			game object in the world and at the third position the number one defining that 
	 * 			the this game object is standing on the other game object.
	 * 			|overlap = {0,0,0,0}
	 * 			|for position1 in top perimeter and bottom perimeter
	 * 			|	for position 2 in top perimeter and bottom perimeter
	 * 			|		if(position1top == position2bottom)
	 * 			|		then
	 *			|		overlap [0] = 1 ;
	 *			|		overlap [1] = world.getIndexOfGameObject(gameObject);
	 *			|		overlap [3] = 1;
	 *			|		if(position2top == position1bottom)
	 * 			|		then
	 *			|		overlap [0] = 1 ;
	 *			|		overlap [1] = world.getIndexOfGameObject(gameObject);
	 *			|		overlap [2] = 1;
	 *			|result == overlap 
	 */
	protected int [] checkTopOrBottomSideOverlap(GameObject gameObject, int [][] topPerimeter1, int [][] bottomPerimeter1, int [][] topPerimeter2, int [][] bottomPerimeter2){
		int [] overlap = {0,0,0,0};
		World world = this.getWorld();		
		outerloop:
		for (int Y = 0; Y < this.getWidth(); Y++ ){
			for (int Y2 = 0; Y2 < gameObject.getWidth(); Y2++ ){
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
	 * 			|overlap = {0,0,0,0}
	 * 			|for position1 in left perimeter and right perimeter
	 * 			|	for position 2 in left perimeter and right perimeter
	 * 			|		if(position1left == position2right || position1right == position2left)
	 * 			|		then
	 *			|		overlap [0] = 1 ;
	 *			|		overlap [1] = world.getIndexOfGameObject(gameObject);
	 *			|result == overlap 
	 */
	protected int [] checkLeftOrRightSideOverlap(GameObject gameObject, int [][] leftPerimeter1, int [][] rightPerimeter1, int [][] leftPerimeter2, int [][] rightPerimeter2){
		int [] overlap = {0,0,0,0};
		World world = this.getWorld();
		outerloop:
		for (int Y = 0; Y < this.getHeight()-2; Y++ ){
			for (int Y2 = 0; Y2 < gameObject.getHeight()-2; Y2++ ){
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
	 * Calculate a new small time period based on the current velocity and acceleration. 
	 * So that the game object can only travel one pixel per time period.
	 * 
	 * @param  	deltaTime
	 * 			The given time period.
	 * @return	|if(isValidDeltaTime(time))
	 * 			|then result == 0.01/(Math.sqrt((Math.pow(this.getHorizontalVelocity(),2)+Math.pow(this.getVerticalVelocity(), 2)))+
	 *			|				Math.sqrt((Math.pow(this.getHorizontalAcceleration(),2)+Math.pow(this.getVerticalAcceleration(), 2)))*deltaTime)
	 *			|else result == 0.03 (not moving)
	 */
	protected double getDeltaTimeForPixel(double deltaTime){
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
		if((!this.canHaveAsLocation(this.getHorizontalLocation(), this.getVerticalLocation()-1) || this.contact == true) 
				&& Util.fuzzyEquals(this.getVerticalVelocity(), 0)){
			this.setJumping(false);
		}
	}
	
	/**
	 * Return whether this game object is currently overlapping with another game object.
	 */
	@Basic
	@Raw
	public boolean getContact(){
		return this.contact;
	}
	
	/**
	 * Set the contact state of this game object.
	 * 
	 * @param 	contact
	 * 			Boolean declaring contact or not.
	 * @post	|new.getContact() == contact
	 */
	@Raw
	public void setContact(boolean contact){
		this.contact = contact;
	}
	
	/**
	 * Boolean registering if this game object is currently overlapping with another game object.
	 */
	private boolean contact;
	
	/**
	 * Adapt the location and velocities of this game object due to contact with another game object.
	 * 
	 * @param 	overlap
	 * 			An array of four elements registering contact details (see checkTopOrBottomSideOverlap).
	 * @param 	oldHorizontalLocation
	 * 			The old horizontal location of this game object.
	 * @param 	oldVerticalLocation
	 * 			The old vertical location of this game object.
	 * @post	|if there is overlap (overlap [0] == 1)
	 * 			|	if this game object is not dead
	 * 			| 		then collisionReaction(overlap[1],overlap[2],overlap[3])
	 * 			|	if(!( gameObject instanceof Plant))
	 * 			|		then this.setContact(true);
	 *			|			gameObject.setContact(true)
	 *			|else (this.getContact() == true && !( gameObject instanceof Plant))
	 *			|	if the current contact is not allowed
	 *			| 		then check if the contact with the old horizontal location is not allowed
	 *			|		if true: this.setVerticalLocation(oldVerticalLocation)
	 *			|				if this method is used by a mazub: this.setVerticalVelocity(0)
	 *			|		then check if the contact with the old vertical location is not allowed
	 *			|		if true: this.setHorizontalLocation(oldHorizontalLocation);
	 *			|	else: (the game object is not any more overlapping)
	 *			|		this.setContact(false);
	 *			|		gameObject.setContact(false);
	 */
	protected void collisionHandler(int [] overlap, double oldHorizontalLocation, double oldVerticalLocation){
		GameObject gameObject = this.getWorld().getGameObjectAtIndex(overlap[1]);
		if(overlap[0]==1){
			if(Util.fuzzyEquals(this.getTimeSinceDead(), 0)){
				collisionReaction(overlap[1],overlap[2],overlap[3]);
			}
			if(!( gameObject instanceof Plant)){
				this.setContact(true);
				gameObject.setContact(true);
			}
		}
		else if(this.getContact() == true && !(gameObject instanceof Plant)){
			if(isNotAllowedLeftRightTopBottomSideOverlap(this.getEffectiveHorizontalLocation(), this.getEffectiveVerticalLocation(), this.getWidth(), this.getHeight())){
				if(isNotAllowedLeftRightTopBottomSideOverlap((int) oldHorizontalLocation, this.getEffectiveVerticalLocation(), this.getWidth(), this.getHeight())){
					this.setVerticalLocation(oldVerticalLocation);
					if(this instanceof Mazub){
						this.setVerticalVelocity(0);
					}
				}
				if(isNotAllowedLeftRightTopBottomSideOverlap(this.getEffectiveHorizontalLocation(), (int) oldVerticalLocation, this.getWidth(), this.getHeight())){
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
	 * Set the current velocities of this game object to zero 
	 * and keep blocking the movements of other game objects.
	 * 
	 * @post	|new.getHorizontalVelocity == 0
	 * 			|new.getVerticalVelocity == 0
	 * @effect	|if this game object is not a plant
	 * 			|then collisionHandler(overlap,this.getHorizontalLocation(),this.getVerticalLocation())
	 * @throws	IllegalStateException
	 * 			|!(this.getHitPoints() <= 0)
	 */
	public void block()
	throws IllegalStateException{
		if(!(this.getHitPoints() <= 0))
			throw new IllegalStateException();
		this.setHorizontalVelocity(0);
		this.setVerticalVelocity(0);
		if(!(this instanceof Plant)){
			int []overlap = checkLeftRightTopBottomSideOverlap();
			collisionHandler(overlap,this.getHorizontalLocation(),this.getVerticalLocation());
		}
	}
	
	/**
	 * Return the time since the start of the water contact of this game object.
	 * The time is reset when the game object loses hit points or no longer makes contact.
	 */
	@Basic
	@Raw
	public double getTimeSinceStartWaterContact(){
		return this.timeSinceWaterContact;
	}
	
	/**
	 * Return the time since the start of the magma contact of this game object.
	 * The time is reset when the game object loses hit points or no longer makes contact.
	 */
	@Basic
	@Raw
	public double getTimeSinceStartMagmaContact(){
		return this.timeSinceMagmaContact;
	}
	
	/**
	 * Set the time since the start of the water contact of this game object.
	 * 
	 * @param	time
	 * 			The given time.
	 * @throws	IllegalArgumentException
	 * 			|!isValidTimeSinceAction(time)
	 * 			
	 */
	@Raw
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
	 * 			|!isValidTimeSinceAction(time)
	 * 			
	 */
	@Raw
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
	 * @throws	|IllegalArgumentException
	 * 			|!isValidTimeSinceAction(time)
	 */
	public void setTimeSinceStartAction(double timeSinceStartAction) 
		throws IllegalArgumentException{
		if(!isValidTimeSinceAction(timeSinceStartAction))
			throw new IllegalArgumentException();
		this.timeSinceStartAction = timeSinceStartAction;
	}
	
	/**
	 * Check whether the given time is a valid time period since game object's last action.
	 * @param 	time
	 * 			The time period to be checked.
	 * @return	True if the given time period is greater than or equal to zero.
	 * 			| result == Util.fuzzyGreaterThanOrEqualTo(time, 0)
	 */
	public static boolean isValidTimeSinceAction(double time){
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
	protected abstract void checkWaterContact(double deltaTime);
	
	/**
	 *Check whether the game object makes contact with magma and take the corresponding actions. 
	 */
	protected abstract void checkMagmaContact(double deltaTime);
	
	/**
	 * Return the current Sprite of this game object.
	 */
	public Sprite getCurrentSprite(){
		if(this.getDirection() == Orientation.LEFT)
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
	
	/**
	 * Check whether the given number of maximum hit points is a valid number of maximum hit points.
	 * @param 	maxHitPoints
	 * 			The number of maximum hit points to be checked.
	 * @return	The given number of maximum hit points is greater than or equal to zero.
	 * 			|result==(maxHitPoints>=0)
	 */
	public static boolean isValidMaxHitPoints(int maxHitPoints){
		return maxHitPoints>=0;
	}
	
	/**
	 * Check whether this game object can have the given number of hit points as its hit points.
	 * @param 	hitPoints
	 * 			The hit points to be checked.
	 * @return	The maximum number of hit points of this game object is valid and the given number of hit points is less than or equal to its maximum number of hit points.
	 * 			| result==(isValidMaxHitPoints(this.getMaxHitPoints()) && hitPoints <= this.getMaxHitPoints())
	 */
	@Raw
	public boolean canHaveAsHitPoints(int hitPoints){
		return isValidMaxHitPoints(hitPoints) && hitPoints <= this.getMaxHitPoints();
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
	 * @post	The given number of hit points must be greater than or equal to zero.
	 * 			If the given number is smaller then nothing is changed.
	 * 			| if(hitPoints >= 0)
	 * 			|then this.setHitPoints(this.getHitPoints() - hitPoints)
	 */
	public void removeHitPoints(int hitPoints){
		int oldHitPoints = getHitPoints();
		if(hitPoints >= 0)
			this.setHitPoints(oldHitPoints - hitPoints);
	}
	
	/**
	 * Add the given number of hit points to the current number of hit points of this game object.
	 * @param 	hitPoints
	 * 			The amount of hit points to be added.
	 * @post	The given number of hit points must be greater than or equal zero.
	 * 			If the given number is smaller then nothing is changed.
	 * 			| if(hitPoints >= 0)
	 * 			|then this.setHitPoints(this.getHitPoints() + hitPoints)
	 */
	public void addHitPoints(int hitPoints){
		int oldHitPoints = getHitPoints();
		if(hitPoints >= 0)
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
	 * Check whether this game object can lose hitpoints when making contact 
	 * with enemy game objects.
	 * 
	 * @return	True if and only if timeSinceLastHitpointsLoss is smaller than 0.6.
	 * 			|result == this.getTimeSinceLastHitpointsLoss() < 0.6
	 */
	public boolean isUntouchable(){
		return getTimeSinceLastHitpointsLoss() < 0.6;
	}
	
	/**
	 * Return the time since gameObject last lost some hitpoints.
	 *
	 */ 
	@Basic
	public double getTimeSinceLastHitpointsLoss(){
		return this.timeSinceLastHitpointsLoss;
	}
	
	/**
	 * Set the time since game object last lost some hitpoints.
	 * 
	 * @param 	time
	 * 			The given time to set.
	 * @throws	IllegalArgumentException
	 * 			If the given time is smaller than zero.
	 * 			|!isValidTimeSinceAction(time)
	 */
	public void setTimeSinceLastHitpointsLoss(double time)
	throws IllegalArgumentException{
		if(!isValidTimeSinceAction(time))
			throw new IllegalArgumentException("Not a valid time!");
		this.timeSinceLastHitpointsLoss = time;
	}
	
	/**
	 * Variable registering the time since the game objects last hitpoints loss.
	 */
	private double timeSinceLastHitpointsLoss = 0.6;
	
	/**
	 * Variable registering the number of hit-points of this game object. 
	 */
	private int hitPoints;
	
	/**
	 * Variable registering the maximum number of hit-points of this game object. 
	 */
	private final int maxHitPoints;
	
	
	/**
	 * Set the program of this game object to the given program.
	 * @param 	program
	 * 			The program to be set.
	 * @throws 	IllegalArgumentException
	 * 			This game object can not have the given program as its program.
	 */
	public void setProgram(Program program) throws IllegalArgumentException{
		if(!canHaveAsProgram(program)){
			throw new IllegalArgumentException();
		}
		this.program=program;
	}
	
	/**
	 * Return the program of this game object.
	 */
	public Program getProgram(){
		return this.program;
	}
	
	/**
	 * Check whether this game object can have the given program as its program.
	 * @param 	program
	 * 			The program to be checked.
	 * @return	If the given program is null, the result is true.
	 * 			Otherwise return true if the program is not terminated and well formed.
	 * 			| if(program==null):
	 * 			|	result==true
	 * 			| else:
	 * 			|	result==(!program.isTerminated() && program.isWellFormed())
	 * 			
	 */
	public boolean canHaveAsProgram(Program program){
		if(program==null){
			return true;
		}
		else{
			return (!program.isTerminated() && program.isWellFormed());
		}
	}
	
	/**
	 * Check whether this game object has a proper program.
	 * @return	True if this game object can have its current program as its program and the current program of this game object
	 * 			is null or it references this game object.
	 * 			| result== canHaveAsProgram(getProgram()) && (getProgram()==null || getProgram().getGameObject()==this)
	 */
	public boolean hasProperProgram(){
		return canHaveAsProgram(getProgram()) && (getProgram()==null || getProgram().getGameObject()==this);
	}
	
	/**
	 * Variable registering the program of this game object.
	 */
	private Program program;
	
	/**
	 * Establish the binary relationship between this game object and the given program.
	 * @param 	program
	 * 			The program to be set.
	 * @effect	The given program is set as the program of this game object.
	 * 			| this.setProgram(program)
	 * @effect	If the given program is not null, the game object referenced by the given program is set to this game object.
	 * 			|if(program != null):
	 * 			|	program.setGameObject(this)
	 * @effect	If the old program of this game object is not null, the game object referenced by the old program is set to null.
	 * 			|if(this.getProgram()!=null):
	 *			|	this.getProgram().setGameObject(null)
	 * @throws 	IllegalArgumentException
	 * 			If this game object is terminated.
	 * 			| this.isTerminated()
	 * @throws	IllegalArgumentException
	 * 			If the given program is not null and the game object of the given program is not null and the program references another
	 * 			game object.
	 * 			| program != null && program.getGameObject()!=null && program.getGameObject() != this
	 */
	public void linkProgram(Program program) throws IllegalArgumentException{
		if(this.isTerminated()){
			throw new IllegalArgumentException();
		}
		if(program != null && program.getGameObject()!=null && program.getGameObject() != this){
			throw new IllegalArgumentException();
		}
		if(this.getProgram()!=null){
			this.getProgram().setGameObject(null);
		}
		this.setProgram(program);
		if(program!=null){
			program.setGameObject(this);
		}
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
	 * Return the world that is currently attached to this game object.
	 */
	public World getWorld(){
		return this.world;
	}
	
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
			world.removeAsGameObject(this);
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
	public boolean hasProperWorld(){
		return this.canHaveAsWorld(this.getWorld()) && this.getWorld().hasAsGameObject(this);
	}
	
	/**
	 * Variable registering the world that is currently attached to this game object.
	 */
	protected World world;
	
	
	
	/**
	 * Return the time since this game object died.
	 */
	@Basic
	public double getTimeSinceDead(){
		return this.timeSinceDead;
	}
	
	/**
	 * Set the time since this game object died.
	 * 
	 * @param 	time
	 * 			The given time period.
	 * @post	|new.getTimeSinceDead == time
	 * @throws	IllegalArgumentException
	 * 			|time < 0
	 */
	public void setTimeSinceDead(double time){
		if(time < 0)
			throw new IllegalArgumentException();
		this.timeSinceDead = time;
	}
	
	/**
	 * Variable registering the time since this game object died.
	 */
	protected double timeSinceDead = 0;
	
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
			this.isTerminated=true;
			this.getWorld().removeAsGameObject(this);
			this.setWorld(null);
			
		}
	}
	
	/**
	 * Variable registering the terminated state of this game object.
	 */
	private boolean isTerminated=false;
}
