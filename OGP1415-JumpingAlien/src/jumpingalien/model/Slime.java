package jumpingalien.model;

import jumpingalien.programs.program.Program;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;
import be.kuleuven.cs.som.annotate.*;

import java.lang.Math;
import java.util.List;
import java.util.Random;

/**
 * A class of slimes which extends the class of game objects. A slime is a non player character which is hostile to Mazub and belongs to a school of slimes.
 * @invar	Each slime can have its current action duration as its action duration.
 * 			|isValidActionDuration(getCurrentActionDuration())
 * @invar	Each slime has a proper school.
 * 			| hasProperSchool()
 * @version 2.0
 * @authors Pieter Van Damme and Lennert Vanmunster
 *
 */
public class Slime extends GameObject {
	
	/**
	 * Initialize a new Slime with given horizontal and vertical location,
	 * ducking state, school and an array of sprites. 
	 * @param	horizontalLocation
	 * 			The horizontal location for this new slime.
	 * @param	verticalLocation
	 * 			The vertical location for this new slime.
	 * @param	program
	 * 			The program for this new slime.
	 * @param 	images
	 * 			An array of sprites for this new slime.
	 * @param	school
	 * 			The school of slimes to which this new slime belongs.
	 * @effect	This new slime is initialized as a game object with the given horizontal location, vertical location, a horizontal velocity of zero, a vertical velocity of zero,
	 * 			an initial horizontal velocity when not ducking of zero, a maximum horizontal velocity when not ducking equal to the maximum horizontal velocity constant, 
	 * 			an initial vertical velocity of zero, the constant horizontal acceleration
	 * 			for all slimes, a false ducking state, a number of hit points of 100, a maximum number of hit points of 100, a program and an image array containing its sprites.
	 * 			| super(horizontalLocation, verticalLocation, 0, 0, initialHorizontalVelocityAtSpawn, maximumHorizontalVelocityAtSpawn, 0, horizontalAccelerationAtSpawn, false, HIT_POINTS, HIT_POINTS, program, images)
	 * @effect	A binary relationship is established between this slime and the given school.
	 * 			| school.addAsSlime(this)
	 */
	@Raw
	public Slime(int horizontalLocation, int verticalLocation, Sprite[] images, School school, Program program)
		throws IllegalArgumentException, IllegalLocationException {
		super(horizontalLocation, verticalLocation, 0, 0, initialHorizontalVelocityAtSpawn, maximumHorizontalVelocityAtSpawn, 0, horizontalAccelerationAtSpawn, false, HIT_POINTS, HIT_POINTS, program, images);
		school.addAsSlime(this);
	}
	
	/**
	 * Initialize a new Slime with given horizontal and vertical location,
	 * ducking state, school and an array of sprites. 
	 * @param	horizontalLocation
	 * 			The horizontal location for this new slime.
	 * @param	verticalLocation
	 * 			The vertical location for this new slime.
	 * @param 	images
	 * 			An array of sprites for this new slime.
	 * @param	school
	 * 			The school of slimes to which this new slime belongs.
	 * @effect	This new slime is initialized as a game object with the given horizontal location, vertical location, a horizontal velocity of zero, a vertical velocity of zero,
	 * 			an initial horizontal velocity when not ducking of zero, a maximum horizontal velocity when not ducking equal to the maximum horizontal velocity constant, 
	 * 			an initial vertical velocity of zero, the constant horizontal acceleration
	 * 			for all slimes, a false ducking state, a number of hit points of 100, a maximum number of hit points of 100, a program equal to null and an image array containing its sprites.
	 * 			| 		super(horizontalLocation, verticalLocation, 0, 0, initialHorizontalVelocityAtSpawn, maximumHorizontalVelocityAtSpawn, 0, horizontalAccelerationAtSpawn, false, HIT_POINTS, HIT_POINTS, null, images);
	 * @effect	A binary relationship is established between this slime and the given school.
	 * 			| school.addAsSlime(this)
	 */
	@Raw
	public Slime(int horizontalLocation, int verticalLocation, Sprite[] images, School school)
		throws IllegalArgumentException, IllegalLocationException {
		super(horizontalLocation, verticalLocation, 0, 0, initialHorizontalVelocityAtSpawn, maximumHorizontalVelocityAtSpawn, 0, horizontalAccelerationAtSpawn, false, HIT_POINTS, HIT_POINTS, null, images);
		school.addAsSlime(this);
	}
	
	
	/**
	 * Check whether this slime can have the given horizontal velocity as its horizontal velocity.
	 * @param 	horizontalVelocity
	 * @return	True if the absolute value of the given horizontal velocity is equal to zero or greater than or equal to the initial horizontal velocity of this slime
	 * 			and less than or equal to the maximum horizontal velocity of this game object.
	 * 			| result== Util.fuzzyGreaterThanOrEqualTo(horizontalVelocity,this.getInitialHorizontalVelocityForUpdate()) 
	 *			&& Util.fuzzyLessThanOrEqualTo(horizontalVelocity, this.getMaximumHorizontalVelocityForUpdate())
	 *			|| Util.fuzzyEquals(horizontalVelocity, 0)
	 */
	@Raw
	@Override
	public boolean canHaveAsHorizontalVelocity(double horizontalVelocity){
		horizontalVelocity = Math.abs(horizontalVelocity);
		return Util.fuzzyGreaterThanOrEqualTo(horizontalVelocity,this.getInitialHorizontalVelocityForUpdate()) 
				&& Util.fuzzyLessThanOrEqualTo(horizontalVelocity, this.getMaximumHorizontalVelocityForUpdate())
				|| Util.fuzzyEquals(horizontalVelocity, 0);
	}
	
	/**
	 * Check whether the given initial horizontal velocity is a possible initial horizontal velocity for slimes.
	 * @return	The given initial horizontal velocity is equal to the initial horizontal velocity of all slimes at spawn and is greater than or equal to zero.
	 * 			| result == (intialHorizontalVelocity>=0 && initialHorizontalVelocity==getInitialHorizontalVelocityAtSpawn())
	 */
	@Raw
	@Override
	public boolean isPossibleInitialHorizontalVelocity(double initialHorizontalVelocity) {
		return initialHorizontalVelocity==getInitialHorizontalVelocityAtSpawn() && getInitialHorizontalVelocityAtSpawn()>=0;
	}
	
	/**
	 * Check whether the given maximum horizontal velocity is a possible maximum horizontal velocity for slimes.
	 * @return	The given maximum horizontal velocity is equal to the maximum horizontal velocity of all slimes at spawn and is greater than to zero.
	 * 			| result == (maximumHorizontalVelocity>0 && maximumHorizontalVelocity==getMaximumHorizontalVelocityAtSpawn())
	 */
	@Raw
	@Override
	public boolean isPossibleMaximumHorizontalVelocity(double maximumHorizontalVelocity){
		return maximumHorizontalVelocity==getMaximumHorizontalVelocityAtSpawn() && getMaximumHorizontalVelocityAtSpawn()>0;
	}
	
	/**
	 * Return the initial horizontal velocity of this game object for used in update calculations.
	 * 
	 * @return	The regular initial horizontal velocity of slimes.
	 * 			| result==this.getInitialHorizontalVelocity()
	 */
	@Raw
	@Immutable
	public double getInitialHorizontalVelocityForUpdate(){
		return this.getInitialHorizontalVelocity();
	}
	
	/**
	 * Return the maximum horizontal velocity of this game object.
	 * 
	 * @return	The regulear maximum horizontal velocity of slimes.
	 * 			| 	result==this.getMaximumHorizontalVelocity()
	 */
	@Raw
	@Immutable
	public double getMaximumHorizontalVelocityForUpdate(){
		return this.getMaximumHorizontalVelocity();
	}
	
	/**
	 * Return the maximum horizontal velocity of slimes at spawn.
	 */
	@Basic
	@Raw
	@Immutable
	public static double getMaximumHorizontalVelocityAtSpawn(){
		return maximumHorizontalVelocityAtSpawn;
	}
	

	/**
	 * Return the initial horizontal velocity of slimes at spawn.
	 */
	@Basic
	@Raw
	@Immutable
	public static double getInitialHorizontalVelocityAtSpawn(){
		return initialHorizontalVelocityAtSpawn;
	}
	
	/**
	 * Variable registering the initial horizontal velocity of all slimes at spawn.
	 */
	private static final double initialHorizontalVelocityAtSpawn=0;
	
	/**
	 * Variable registering the maximum horizontal velocity of all slimes at spawn.
	 */
	private static final double maximumHorizontalVelocityAtSpawn = 2.5;

	/**
	 * Returns the current vertical acceleration of this slime.
	 * 
	 * @return	If this slime is jumping than the vertical acceleration is equal
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
	 * Check whether this slime can have the given vertical acceleration as its vertical acceleration.
	 * @return	The given vertical acceleration is equal to zero or equal to the vertical acceleration constant.
	 * 			|	result== verticalAcceleration==0 || Util.fuzzyEquals(verticalAcceleration, VERTICAL_ACCELERATION)
	 */
	@Raw
	public boolean canHaveAsVerticalAcceleration(double verticalAcceleration){
		return verticalAcceleration==0 || Util.fuzzyEquals(verticalAcceleration, VERTICAL_ACCELERATION);
	}
	
	/**
	 * Check whether the given horizontal acceleration is a valid horizontal acceleration.
	 * @param	horizontalAcceleration
	 * 			The horizontal acceleration to be checked.
	 * @return	The given horizontal acceleration is not equal to zero and equal to the horizontal acceleration at spawn of all slimes.
	 * 			| result == (horizontalAcceleration!=0 && horizontalAcceleration==getHorizontalAccelerationAtSpawn())
	 */
	@Raw
	@Override
	public boolean canHaveAsHorizontalAcceleration(double horizontalAcceleration){
		return horizontalAcceleration==getHorizontalAccelerationAtSpawn() && horizontalAcceleration!=0;
	}
	
	/**
	 * Return the horizontal acceleration of all slimes at spawn.
	 */
	public static double getHorizontalAccelerationAtSpawn(){
		return horizontalAccelerationAtSpawn;
	}
	
	
	/**
	 *  Variable registering the horizontal acceleration of all slimes at spawn.
	 */
	private static final double horizontalAccelerationAtSpawn = 0.7;
	
	/**
	 * Check whether this slime can have the given ducking state as its ducking state.
	 * @return 	The given ducking state is false.
	 * 			|result== (ducking==false)
	 */
	@Override
	@Raw
	public boolean canHaveAsDuckingState(boolean ducking){
		return !ducking;
	}
	
	/**
	 * Start a new action for this slime.
	 * @effect 	The current action duration of this slime is set to a random action duration between the minimum and maximum action duration,
	 * 			the direction of this slime is set to a random direction, the slime's horizontal moving state is set to true, the time since
	 * 			the start of the action of this slime is set to 0 and the horizontal velocity of this slime is set to the initial horizontal velocity of slimes
	 * 			in the calculated random direction.
	 * 			| Random r = new Random()
	 * 			| this.setCurrentActionDuration(getMinimumActionDuration()+(getMaximumActionDuration()-getMinimumActionDuration())*r.nextDouble())
	 * 			| this.setDirection(r.nextBoolean() ? Direction.LEFT : Direction.RIGHT)
	 * 			| this.setMovingHorizontally(true)
	 * 			| this.setTimeSinceStartAction(0)
	 * 			| this.setHorizontalVelocity(this.getInitialHorizontalVelocityForUpdate()*new.getDirection().getNumberForCalculations())
	 */
	private void startNewAction(){
		Random r = new Random();
		this.setCurrentActionDuration(getMinimumActionDuration()+(getMaximumActionDuration()-getMinimumActionDuration())*r.nextDouble());
		this.setDirection(r.nextBoolean() ? Orientation.LEFT : Orientation.RIGHT);
		this.setTimeSinceStartAction(0);
		this.startMove(getDirection());
	}
	
	/**
	 * Start the horizontal movement of this slime in the given direction.
	 * 
	 * @param 	direction
	 * 			The desired direction in which this slime will move.
	 * @pre		The given direction must be a valid direction.
	 * 			|isValidDirection(direction)
	 * @effect	Set the horizontal velocity in the given direction.
	 * 			|setHorizontalVelocity(this.getInitialHorizontalVelocityForUpdate()*direction.getNumberForCalculations())
	 * @effect	Set the direction in the given direction.	
	 * 			|setDirection(direction);
	 * @effect	Set isMovingHorizontally to true.
	 * 			|setMovingHorizontally(true);
	 * @note	The precondition is asserted in setDirection()
	 */
	@Override
	public void startMove(Orientation direction){
		setHorizontalVelocity(this.getInitialHorizontalVelocityForUpdate()*direction.getNumberForCalculations());
		setDirection(direction);
		setMovingHorizontally(true);
	}
	
	
	/**
	 * Stop movement of this slime in the given direction.
	 * 
	 * @param	direction
	 * 			The given direction.
	 * @effect	If the given direction is equal to the direction of this slime
	 * 			then set the horizontal velocity to zero and isMovingHorizontally to false.
	 * 			|if(direction==this.getDirection()){
	 * 			|	then setHorizontalVelocity(0)
	 * 			|		 setMovingHorizontally(false)
	 */
	@Override
	public void endMove(Orientation direction){
		if(direction==this.getDirection()){
			setHorizontalVelocity(0);
			setMovingHorizontally(false);
		}
	}
	
	/**
	 * Make this slime jump if this slime isn't jumping already.
	 * 
	 * @effect	If this slime isn't jumping, the vertical velocity of this slime is set 
	 * 			to the initial vertical velocity and its jumping state is set to true.
	 * 			| if (!isJumping())
	 * 			|	setJumping(true)
	 * 			| 	setVerticalVelocity(getInitialverticalVelocity())
	 * @note	The method setVerticalVelocity will never throw an exception in 
	 * 			its current implementation because this.getInitialVerticalVelocity() always returns a valid vertical velocity.
	 * 			There is no need to add a try catch statement.
	 */
	@Override
	public void startJump(){
		if (!isJumping()){
			setJumping(true);
			setVerticalVelocity(getInitialVerticalVelocity());
		}
	}
	
	/**
	 * End the jump of this slime if the slime was jumping.
	 * 
	 * @effect	This slime's vertical velocity is set to zero.
	 * 			| if (getVerticalVelocity()>0)
	 * 			| then setVerticalVelocity(0)
	 * @note	The method setVerticalVelocity will never throw an exception in 
	 * 			its current implementation because 0 is a valid vertical velocity.
	 * 			There is no need to add a try catch statement.
	 */
	@Override
	public void endJump(){
		if (getVerticalVelocity()>0){
			setVerticalVelocity(0);
		}
	}
	
	/**
	 * Check whether the given action duration is a valid action duration for all slimes.
	 * @param 	actionDuration
	 * 			The duration to be checked.
	 * @return	True if the given duration is greater than or equal to the minimum action duration of sharks
	 * 			and less than or equal to the maximum action duration of sharks.
	 * 			| result == (Util.fuzzyGreaterThanOrEqualTo(actionDuration, getMinimumActionDuration())
				|			&& Util.fuzzyLessThanOrEqualTo(actionDuration, getMaximumActionDuration()))
	 */
	@Raw
	public static boolean isValidActionDuration(double actionDuration){
		return Util.fuzzyGreaterThanOrEqualTo(actionDuration, getMinimumActionDuration())
				&& Util.fuzzyLessThanOrEqualTo(actionDuration, getMaximumActionDuration());
	}
	
	/**
	 * Return the minimum action duration of all slimes.
	 */
	@Basic
	@Raw
	@Immutable
	public static double getMinimumActionDuration(){
		return minimumActionDuration;
	}
	
	/**
	 * Return the maximum action duration of all slimes.
	 */
	@Basic
	@Raw
	@Immutable
	public static double getMaximumActionDuration(){
		return maximumActionDuration;
	}
	
	/**
	 * Set the current action duration of this slime to the given duration. This is how long the current action of this slime lasts.
	 * @param 	duration
	 * 			The duration to be set in seconds.
	 * @pre		The given duration must be a valid action duration.
	 * 			| isValidActionDuration(duration)
	 * @post	| new.getCurrentActionDuration()==duration
	 */
	@Raw
	public void setCurrentActionDuration(double duration) {
		assert (isValidActionDuration(duration)):
			"The given duration is not a valid action duration!";
		this.currentActionDuration=duration;
	}
	
	/**
	 * Return the current action duration of this slime.
	 */
	@Basic
	@Raw
	public double getCurrentActionDuration(){
		return currentActionDuration;
	}

	/**
	 * Variable registering the current action duration of this slime.
	 */
	private double currentActionDuration=0;

	/**
	 * Variable registering the minimum action duration of all slimes.
	 */
	private static final double minimumActionDuration=2;
	
	/**
	 * Variable registering the maximum action duration of all slimes.
	 */
	private static final double maximumActionDuration=6;
	
	/**
	 * Update the location and velocity of this slime.
	 * 
	 * @param 	deltaTime
	 * 			The period of time that is used to update this slime.
	 * @effect	The horizontal and vertical location are updated
	 * 			and the horizontal and vertical velocity are updated.
	 * 			|updateHorizontalLocation(deltaTime)
	 *			|updateVerticalLocation(deltaTime);
	 *			|updateHorizontalVelocity(deltaTime);
	 *			|updateVerticalVelocity(deltaTime);
	 * @effect	|Contact with magma and water is checked.
	 *			|checkWaterContact(deltaTime);
	 *			|checkMagmaContact(deltaTime);		
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
		if(this.getProgram()!=null){
			this.getProgram().execute(deltaTime);
		}
		else{
			if(this.getTimeSinceStartAction()<=this.getCurrentActionDuration()){
				this.setTimeSinceStartAction(this.getTimeSinceStartAction()+deltaTime);
			}
			else{
				this.startNewAction();
			}
		}
		double deltaTimeForPixel=0;
		double sumDeltaTimeForPixel=0;
		double newHorizontalLocation=this.getHorizontalLocation();
		double newVerticalLocation=this.getVerticalLocation();
		double newHorizontalVelocity=this.getHorizontalVelocity();
		double newVerticalVelocity=this.getVerticalVelocity();
		double oldHorizontalLocation=this.getHorizontalLocation();
		double oldVerticalLocation=this.getVerticalLocation();
		while (sumDeltaTimeForPixel<deltaTime){
			oldHorizontalLocation = this.getHorizontalLocation();
			oldVerticalLocation = this.getVerticalLocation();
			deltaTimeForPixel= getDeltaTimeForPixel(deltaTime);
			newVerticalVelocity = this.getVerticalVelocity() + getVerticalAcceleration()*deltaTimeForPixel;
			newHorizontalVelocity = this.getHorizontalVelocity() + this.getDirection().getNumberForCalculations()*getHorizontalAccelerationForUpdate()*deltaTimeForPixel;
			newHorizontalLocation = this.getHorizontalLocation() + 
					100*(this.getHorizontalVelocity()*deltaTimeForPixel + 
					this.getDirection().getNumberForCalculations()*0.5*getHorizontalAccelerationForUpdate()*Math.pow(deltaTimeForPixel, 2));
			newVerticalLocation = this.getVerticalLocation() + 100*(getVerticalVelocity()*deltaTimeForPixel + 0.5*getVerticalAcceleration()*Math.pow(deltaTimeForPixel,2));
			sumDeltaTimeForPixel+=deltaTimeForPixel;
			try{
				this.setHorizontalVelocity(newHorizontalVelocity);
			} catch(IllegalArgumentException exc){
					this.setHorizontalVelocity(this.getDirection().getNumberForCalculations()*this.getMaximumHorizontalVelocityForUpdate());
			}
			try{
				this.setVerticalVelocity(newVerticalVelocity);
			} catch (IllegalArgumentException exc){
				this.setVerticalVelocity(0);
			}
			try{
				this.setHorizontalLocation(newHorizontalLocation);
			} catch(IllegalLocationException exc){
				this.setHorizontalLocation((int) oldHorizontalLocation);
			}
			try{
				this.setVerticalLocation(newVerticalLocation);
			} catch(IllegalLocationException exc){
				this.setVerticalLocation(oldVerticalLocation);
				this.setVerticalVelocity(0);
			}
			int []overlap = checkLeftRightTopBottomSideOverlap();
			collisionHandler(overlap,oldHorizontalLocation,oldVerticalLocation);
		}
		
		this.checkWaterContact(deltaTime);
		this.checkMagmaContact(deltaTime);
		this.calculateNewJumpingState();
	}
	
	/**
	 * Execute the right actions after a collision with another game object.
	 * 
	 * @param 	index1
	 * 			The index registering the position of the other 
	 * 			game object in list of all game objects of this world.
	 * @param 	index2
	 * 			The index registering whether the bottom perimeter was 
	 * 			overlapping during the contact with the other game object.
	 * @param 	index3
	 * 			The index registering whether the top perimeter was
	 * 			overlapping during the contact with the other game object.
	 * @effect	|GameObject gameObject = this.getWorld().getGameObjectAtIndex(index1)
	 * 			|if(gameObject instanceof Shark)
	 * 			|	gameObject.removeHitPoints(50)
	 * 			|	this.removeHitPoints(50)
	 *			|else if(gameObject instanceof Mazub)
	 *			|	if(!((Mazub) gameObject).isUntouchable())
	 *			|		this.removeHitPoints(50)
	 *			|		if(index3 == 0)
	 *			|			gameObject.removeHitPoints(50)
	 *			|			((Mazub) gameObject).setTimeSinceLastHitpointsLoss(0)
	 *			|else if(gameObject instanceof Slime)
	 *			|	Slime otherSlime= (Slime) gameObject
	 *			|	if (this.getSchool().getNbSlimes()>otherSlime.getSchool().getNbSlimes())
	 *			|		otherSlime.joinSchool(this.getSchool())
	 *			|	else if(this.getSchool().getNbSlimes()<otherSlime.getSchool().getNbSlimes())
	 *			|		this.joinSchool(otherSlime.getSchool())
	 */
	protected void collisionReaction(int index1, int index2, int index3) {
		GameObject gameObject = this.getWorld().getGameObjectAtIndex(index1);
		if(gameObject instanceof Shark){
			gameObject.removeHitPoints(50);
			this.removeHitPointsSchool(50);
		}
		else if(gameObject instanceof Mazub){
			if(!((Mazub) gameObject).isUntouchable()){
				this.removeHitPointsSchool(50);
				if(index3 == 0){
					gameObject.removeHitPoints(50);
					((Mazub) gameObject).setTimeSinceLastHitpointsLoss(0);
				}
			}
		}
		else if(gameObject instanceof Slime){
			Slime otherSlime= (Slime) gameObject;
			if (this.getSchool().getNbSlimes()>otherSlime.getSchool().getNbSlimes()){
				otherSlime.joinSchool(this.getSchool());
			}
			else if(this.getSchool().getNbSlimes()<otherSlime.getSchool().getNbSlimes()){
				this.joinSchool(otherSlime.getSchool());
			}
		}
		else if(gameObject instanceof Buzam){
			if( !gameObject.isUntouchable()){
				this.removeHitPointsSchool(50);
				if(index3 == 0){
					gameObject.removeHitPoints(50);
					gameObject.setTimeSinceLastHitpointsLoss(0);
				}
			}
		}
	}
	
	/**
	 * Check whether the given left, right, top or bottom perimeter overlaps with another game object.
	 * 
	 * @return	The method iterates over all the game objects that can overlap with the given
	 * 			game object. That are the game objects that overlap with one of the tile with
	 * 			which this game object overlaps. If another game object is found that overlaps 
	 * 			with one of the given sides then an array with four elements is returned.
	 * 			This array can be interpreted by the method collisionHandler.
	 * 			|overlap = {0,0,0,0}
	 * 			|for each game object in gameGameObjectAtTile of this game object
	 * 			|	if(gameObject != this && gameObject != null && world.canHaveAsGameObject(gameObject))
	 * 			|		overlap = checkLeftOrRightSideOverlap(gameObject,perimeters...);
	 *			|		if(overlap [0] == 1)
	 *			|		then the array "overlap" is returned
	 *			|		or 
	 *			|		overlap = checkTopOrBottomSideOverlap(gameObject,perimeters...);
	 *			|		if(overlap [0] == 1)
	 *			|		then the array "overlap" is returned
	 *			|result == overlap
	 */
	protected int [] checkLeftRightTopBottomSideOverlap(){
		int [] overlap = {0,0,0,0};
		World world = this.getWorld();
		List<GameObject> gameObjects = getGameObjectsAtTiles(world.getTilePositionsIn(getEffectiveHorizontalLocation(), getEffectiveVerticalLocation(), getEffectiveHorizontalLocation()+getWidth(), getEffectiveVerticalLocation()+getHeight()));
		for(int index = 0; index < gameObjects.size(); index++){
			GameObject gameObject = gameObjects.get(index);
			if(gameObject != this && gameObject != null && world.canHaveAsGameObject(gameObject)){
				int [][] leftPerimeterOther = gameObject.getLeftPerimeter(gameObject.getEffectiveHorizontalLocation(), gameObject.getEffectiveVerticalLocation(), gameObject.getHeight());
				int [][] rightPerimeterOther = gameObject.getRightPerimeter(gameObject.getEffectiveHorizontalLocation(), gameObject.getEffectiveVerticalLocation(), gameObject.getWidth(), gameObject.getHeight());
				overlap = checkLeftOrRightSideOverlap(gameObject, this.getLeftPerimeter(getEffectiveHorizontalLocation(), getEffectiveVerticalLocation(), getHeight()), 
						 this.getRightPerimeter(getEffectiveHorizontalLocation(), getEffectiveVerticalLocation(), getWidth(), getHeight()), leftPerimeterOther, rightPerimeterOther);
				if(overlap [0] == 1){
					return overlap;
				}
				int [][] topPerimeterOther = gameObject.getTopPerimeter(gameObject.getEffectiveHorizontalLocation(), gameObject.getEffectiveVerticalLocation(), gameObject.getWidth(), gameObject.getHeight());
				int [][] bottomPerimeterOther = gameObject.getBottomPerimeter(gameObject.getEffectiveHorizontalLocation(), gameObject.getEffectiveVerticalLocation(), gameObject.getWidth());
				overlap = checkTopOrBottomSideOverlap(gameObject, this.getTopPerimeter(getEffectiveHorizontalLocation(), getEffectiveVerticalLocation(), getWidth(), getHeight()), 
						this.getBottomPerimeter(getEffectiveHorizontalLocation(), getEffectiveVerticalLocation(), getWidth()), topPerimeterOther, bottomPerimeterOther);
				if(overlap [0] == 1){
					return overlap;
				}
			}
		}
		return overlap;
	}
	
	
	/**
	 * Check whether the game object makes contact with water and take the corresponding actions.
	 * 
	 * @param 	deltaTime
	 * 			The given time period.
	 * @post	Every 0.2 seconds while being contacted with water, two hitpoints are removed
	 * 			from Mazub. The first 0.2 seconds no hitpoints are removed.
	 * 			|
	 */
	@Override
	protected void checkWaterContact(double deltaTime){
		boolean [] contactTiles = (this.getWorld().areaCoincidesWithTerrain(this.getEffectiveHorizontalLocation(), 
				this.getEffectiveVerticalLocation()+1, this.getWidth()-1, this.getHeight()-2)).clone();
		if(contactTiles[2] == true){
			double time = getTimeSinceStartWaterContact();
			this.setTimeSinceStartWaterContact(time + deltaTime);
			if(Util.fuzzyGreaterThanOrEqualTo(this.getTimeSinceStartWaterContact(), 0.2)){
				this.removeHitPointsSchool(2);
				this.setTimeSinceStartWaterContact(0);
			}
		}
		else
			this.setTimeSinceStartWaterContact(0);
	}
	
	/**
	 *Check whether the game object makes contact with magma and take the corresponding actions. 
	 * @param 	deltaTime
	 * 			The given time period.
	 * @post	Every 0.2 seconds while being contacted with magma, fifty hitpoints are removed
	 * 			from Mazub. The first hitpoints are immediately removed at first contact.
	 * 			|
	 */
	@Override
	protected void checkMagmaContact(double deltaTime){
		boolean [] contactTiles = (this.getWorld().areaCoincidesWithTerrain(this.getEffectiveHorizontalLocation(), 
				this.getEffectiveVerticalLocation()+1, this.getWidth()-1, this.getHeight()-2)).clone();
		if(contactTiles[3] == true){
			double time = getTimeSinceStartMagmaContact();
			this.setTimeSinceStartMagmaContact(time + deltaTime);
			if(time == 0)
				this.removeHitPointsSchool(50);
			else if(Util.fuzzyGreaterThanOrEqualTo(this.getTimeSinceStartMagmaContact(), 0.2)){
				this.setTimeSinceStartMagmaContact(0);
			}
		}
		else{
			this.setTimeSinceStartMagmaContact(0);
		}
	}
	
	/**
	 * Remove the given number of hit points from this slime and 1 hit point from all the slimes in its school.
	 * @param 	hitPoints
	 * 			The number of hit points to be removed from this slime.
	 * @effect	All other slimes in the school of this slime lose 1 hit point.
	 * 			| for(slimes in this.getSchool.getAllSlimes())
	 * 			|	if(slime!=this)
	 * 			|		this.removeHitPoints(1)
	 * @effect	This slime loses the given number of hit points.
	 * 			| super.removeHitPoints(hitPoints)
	 */
	public void removeHitPointsSchool(int hitPoints){
		this.removeHitPoints(hitPoints);
		for(Slime slime: this.getSchool().getAllSlimes()){
			if (slime != this){
				slime.removeHitPoints(1);
			}
		}
	}
	
	/**
	 * Make this slime migrate from its current school to the given school. 
	 * This includes an exchange of hit points between the slimes of the current school of this slime and the given school.
	 * @param 	newSchool
	 * 			The new school to be set for this slime.
	 * @effect	One hit point is added to all slimes in the school of this slime excluding this slime.
	 * 			|for(slime in this.getSchool.getAllSlimes())
	 * 			|	if(slime!=this)
	 * 			|		this.addHitPoints(1)
	 * @effect	One hit point is removed from all slimes in the given school.
	 * 			|for(slime in newSchool.getAllSlimes())
	 * 			|	this.removeHitPoints(1)
	 * @effect	The number of hit points to added to this slime is calculated via: the number of slimes in the given school minus the number of slimes in this school plus one.
	 * 			If that number is positive than that number is added to the hit points of this slime.
	 *			Otherwise the absolute value of that number is removed from the hit points of this slime. 	
	 * 			| int hitPointsToBeAdded=newSchool.getNbSlimes()-this.getSchool().getNbSlimes()+1
	 * 			|if (hitPointsToBeAdded>0)
	 * 			|	this.addHitPoints(newSchool.getNbSlimes())
	 * 			|else
	 * 			|	this.removeHitPoints(Math.abs(hitPointsToBeAdded))
	 * @post	This new school of this slime is equal to the given school.
	 * 			|new.getSchool()==newSchool
	 * @post	The new school of this slime has this slime as one of its slimes.
	 *  		|(new newSchool).hasAsSlime(this)
	 * @post	The current school of this slime doesn't have this slime as one of its slimes.
	 * 			| !this.getSchool().hasAsSlime(new this)
	 * 
	 */
	public void joinSchool(School newSchool){
		for(Slime slime: this.getSchool().getAllSlimes()){
			if(slime!=this){
				slime.addHitPoints(1);
			}
		}
		for(Slime slime: newSchool.getAllSlimes()){
			slime.removeHitPoints(1);
		}
		int hitPointsToBeAdded=newSchool.getNbSlimes()-this.getSchool().getNbSlimes()+1;
		if(hitPointsToBeAdded>0){
			this.addHitPoints(hitPointsToBeAdded);
		}
		else{
			this.removeHitPoints(Math.abs(hitPointsToBeAdded));
		}
		this.getSchool().removeAsSlime(this);
		newSchool.addAsSlime(this);
	}
	
	/**
	 * Constant registering the number of hit points of all slimes.
	 */
	public final static int HIT_POINTS=100;
	
	/**
	 * Check whether the given number of images in the given image array is valid for all slimes.
	 * 
	 * @param 	nbImages
	 * 			The number of images to be checked.
	 * @return	The given number of images must be equal to 2.
	 * 			| result == (nbImages==2)
	 * 
	 */
	@Raw
	@Override
	public boolean canHaveAsNbImages(int nbImages){
		return nbImages==2;
	}	
	
	/**
	 * Terminate slime.
	 * @effect	If this slime is not already terminated, it is terminated as a game object, effectively breaking the relationship between this slime and its world.
	 * 			This slime is also removed from its school.
	 * 			|if (!this.isTerminated())
	 * 			|	super.terminate()
	 * 			| 	this.getSchool.removeAsSlime(this)
	 */
	public void terminate() {
		if (!this.isTerminated()){
			super.terminate();
			this.getSchool().removeAsSlime(this);
		}
	}
	
	/**
	 * Set the given school as the school of this slime.
	 * @param 	school
	 * 			The school to be set.
	 * @post	| new.getSchool()==this
	 * @throws	IllegalArgumentException
	 * 			The given school is not effective and the current school of this slime is effective and the current school of this slime
	 * 			references this slime as one of its slimes.
	 * 			|school==null && this.getSchool()!=null && (this.getSchool().hasAsSlime(this))
	 * @throws	IllegalArgumentException
	 * 			This slime can not have the given school as its school.
	 * 			|!this.canHaveAsSchool(school)
	 */
	public void setSchool(School school) throws IllegalArgumentException{
		if (school==null && this.getSchool()!=null && (this.getSchool().hasAsSlime(this))){
			throw new IllegalArgumentException();
		}
		if(!this.canHaveAsSchool(school)){
			throw new IllegalArgumentException();
		}
		this.school=school;
	}
	
	/**
	 * Return the school to which this slime belongs.
	 */
	@Basic
	@Raw
	public School getSchool(){
		return this.school;
	}
	
	/**
	 * Check whether this slime can have the given school as its school.
	 * @param 	school
	 * @return	result == (school==null || school.canHaveAsGameObject(this))
	 */
	@Raw
	public boolean canHaveAsSchool(School school){
		return  (school==null || school.canHaveAsSlime(this));
	}
	
	
	/**
	 * Check whether this slime has a school.
	 * @return 	This slime can have its school as its world and its school has this slime as one of its slimes.
	 * 			| this.canHaveAsSchool(this.getSchool()) && this.getSchool().hasAsSlime(this)
	 */
	@Raw
	public boolean hasProperSchool(){
		return this.canHaveAsSchool(this.getSchool()) && this.getSchool().hasAsSlime(this);
	}
	
	/**
	 * Variable registering the school to which this slime belongs.
	 */
	private School school;

	

	
}


