package jumpingalien.model;


import jumpingalien.programs.program.Program;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;
import be.kuleuven.cs.som.annotate.*;

import java.lang.Math;
import java.util.List;

/**
 * A class of Buzams, rectangular player-controlled objects in the game jumping alien.
 * @invar	The time since Buzam last started moving must be a valid time for each Buzam.
 * 			| isValidTimeSinceAction(getTimeSinceStartMove())
 * @invar	The time since Buzam last ended moving must be a valid time for each Buzam.
 * 			| isValidTimeSinceAction(getTimeSinceEndMove())
 * @version 1.0
 * @authors Pieter Van Damme and Lennert Vanmunster
 *
 */
public class Buzam extends GameObject {
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
	 * @param 	images
	 * 		  	Array of sprites to display Buzam for this new Buzam.
	 * @effect	This new Buzam is initialized as a game object with the given horizontal location, vertical location, horizontal velocity, vertical velocity,
	 * 			initial horizontal velocity when not ducking, maximum horizontal velocity when not ducking, the initial vertical velocity for all Buzams, the horizontal acceleration
	 * 			for all Buzams, ducking state, the initial number of hit points for all Buzams, the constant maximum number of hit points for all Buzams, the given ducking state, and an image array containing its sprites.
	 * 			| super(horizontalLocation, verticalLocation, horizontalVelocity, verticalVelocity, initialHorizontalVelocityNotDucking, maximumHorizontalVelocityNotDucking, INITIAL_VERTICAL_VELOCITY, horizontalAcceleration, ducking, hitPoints, MAX_HIT_POINTS, images)
	 * @effect	If the given ducking state of the new Buzam is true the Buzam
	 * 			starts ducking.
	 * 			|if(isDucking())
	 *			|	startDuck()
	 */
	@Raw
	public Buzam(int horizontalLocation, int verticalLocation, double horizontalVelocity,
				double verticalVelocity, double initialHorizontalVelocityNotDucking,
				double maximumHorizontalVelocityNotDucking, boolean ducking,Program program, Sprite... images)
		throws IllegalArgumentException, IllegalLocationException {
		super(horizontalLocation, verticalLocation, horizontalVelocity, verticalVelocity, initialHorizontalVelocityNotDucking, 
				maximumHorizontalVelocityNotDucking, initialVerticalVelocity, horizontalAccelerationAtStartGame, ducking, initialHitPoints, MAX_HIT_POINTS, program, images);
		if(isDucking())
			startDuck();
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
	 * Check whether this Buzam can have the given horizontal velocity as its horizontal velocity.
	 * @return	True if the absolute value of the given horizontal velocity is equal to zero or greater than or equal to the current initial horizontal velocity of this Buzam
	 * 			and less than or equal to the current maximum horizontal velocity of this Buzam.
	 * 			| result== Util.fuzzyGreaterThanOrEqualTo(horizontalVelocity,this.getInitialHorizontalVelocityForUpdate()) 
	 *			&& Util.fuzzyLessThanOrEqualTo(horizontalVelocity, this.getMaximumHorizontalVelocityForUpdate())
	 *			|| Util.fuzzyEquals(horizontalVelocity, 0)
	 */
	@Override
	public boolean canHaveAsHorizontalVelocity(double horizontalVelocity){
		horizontalVelocity = Math.abs(horizontalVelocity);
		return Util.fuzzyGreaterThanOrEqualTo(horizontalVelocity,getInitialHorizontalVelocityForUpdate()) 
				&& Util.fuzzyLessThanOrEqualTo(horizontalVelocity, getMaximumHorizontalVelocityForUpdate())
				|| Util.fuzzyEquals(horizontalVelocity, 0);
	}
	
	/**
	 * Check whether the given initial horizontal velocity is a possible initial horizontal velocity for any Buzam.
	 * 
	 * @param 	initialHorizontalVelocity
	 * 			The initial horizontal velocity to check.
	 * @return	True if the given initial horizontal velocity is greater than or equal to the ducking velocity for all Buzams.
	 * 			result == Util.fuzzyGreaterThanOrEqualTo(initialHorizontalVelocity, getDuckingVelocity())
	 */
	public boolean isPossibleInitialHorizontalVelocity(double initialHorizontalVelocity){
		return Util.fuzzyGreaterThanOrEqualTo(initialHorizontalVelocity, getDuckingVelocity());
	}
	
	public boolean isPossibleMaximumHorizontalVelocity(double maximumHorizontalVelocity){
		return maximumHorizontalVelocity>0;
	}
	
	/**
	 * Return the ducking velocity for all Buzams.
	 */
	public static double getDuckingVelocity(){
		return duckingVelocity;
	}
	
	/**
	 * Return the current initial horizontal velocity of this Buzam to be used in updates of this Buzam.
	 * 
	 * @return	If the ducking state of this Buzam is true, this method returns the ducking velocity of all Buzams.
	 * 			Otherwise it returns this Buzam's regular initial horizontal velocity.
	 * 			|if (this.isDucking())
	 * 			|	result==getDuckingVelocity()
	 * 			|else
	 * 			| 	result==this.getInitialHorizontalVelocity()
	 */
	@Raw
	public double getInitialHorizontalVelocityForUpdate(){
		if (isDucking())
			return getDuckingVelocity();
		else 
			return this.getInitialHorizontalVelocity();
	}
	
	/**
	 * Return the current maximum horizontal velocity of this Buzam to be used in updates of this Buzam.
	 * 
	 * @return	If the ducking state of this Buzam is true, this method returns the ducking velocity of all Buzams.
	 * 			Otherwise it returns this Buzam's regular maximum horizontal velocity.
	 * 			|if (this.isDucking())
	 * 			|	result==getDuckingVelocity()
	 * 			|else
	 * 			| 	result==this.getMaximumHorizontalVelocity()
	 */
	@Raw
	public double getMaximumHorizontalVelocityForUpdate(){
		if (isDucking())
			return getDuckingVelocity();
		else 
			return getMaximumHorizontalVelocity();
	}
	
	/**
	 *  Variable registering the initial vertical velocity of all Buzams.
	 */
	private static final double initialVerticalVelocity = 8;
	
	/**
	 * Variable registering the ducking velocity of all Buzams.
	 */
	private final static double duckingVelocity=1;
	
	
	
	/**
	 * Returns the vertical acceleration of this Buzam.
	 * 
	 * @return	If this Buzam is jumping than the vertical acceleration is equal
	 * 			to the gravitational acceleration constant.
	 * 			Otherwise the vertical acceleration is equal to zero.
	 * 			|if(this.isJumping())
	 *			|	result == VERTICAL_ACCELERATION
	 *			|else
	 *			| 	result == 0
	 */
	@Raw
	public double getVerticalAcceleration(){
		if(isJumping())
			return VERTICAL_ACCELERATION;
		else
			return 0;
	}
	
	/**
	 * Check whether the given horizontal acceleration is a valid horizontal acceleration for any Buzam.
	 * @param	horizontalAcceleration
	 * 			The horizontal acceleration to be checked.
	 * @return	The given horizontal acceleration is not equal to zero.
	 * 			| result == (horizontalAcceleration!=0)
	 */
	public boolean canHaveAsHorizontalAcceleration(double horizontalAcceleration){
		return horizontalAcceleration!=0;
	}
	
	/**
	 * Check whether this Buzam can have the given vertical acceleration as its vertical acceleration.
	 * @return	The given vertical acceleration is equal to zero or equal to the vertical acceleration constant.
	 * 			|	result== verticalAcceleration==0 || Util.fuzzyEquals(verticalAcceleration, VERTICAL_ACCELERATION)
	 */
	public boolean canHaveAsVerticalAcceleration(double verticalAcceleration){
		return verticalAcceleration==0 || Util.fuzzyEquals(verticalAcceleration, VERTICAL_ACCELERATION);
	}
	
	/**
	 * A variable registering the horizontal acceleration of all Buzams at the start of the game.
	 * This variable is solely used to initialize the regular horizontal acceleration of each Buzam.
	 */
	private static final double horizontalAccelerationAtStartGame=0.9;
	
	
	/**
	 * Start the horizontal movement of this Buzam in the given direction.
	 * 
	 * @param 	direction
	 * 			The desired direction in which this Buzam will move.
	 * @pre		The given direction must be a valid direction.
	 * 			|isValidDirection(direction)
	 * @post	If this Buzam's horizontal moving state is false.
	 * 			|if(!this.isMovingHorizontally()):
	 * 				The new horizontal moving state is true.
	 * 			|	new.isMovingHorizontally()==true
	 * 				The new horizontal velocity of this Buzam is equal to the given direction times its current initial horizontal velocity.
	 * 			|	new.getHorizontalVelocity() == direction*this.getInitialHorizontalVelocityForUpdate()
	 *				The new time since this Buzam has last ended moving is 0.
	 * 			|	new.getTimeSinceEndMove()==0
	 * 				The new time since this Buzam has started moving is 0.
	 * 			| 	new.getTimeSinceStartMove()==0
	 * 		 		The new direction of this Buzam is equal to the given direction.
	 * 			| 	this.getDirection()==direction
	 * @note	The precondition is asserted in setDirection()
	 */
	@Raw
	@Override
	public void startMove(Orientation direction){
		
		if (isMovingHorizontally()==false){
			setMovingHorizontally(true);
			setDirection(direction);
			setHorizontalVelocity((direction.getNumberForCalculations())*this.getInitialHorizontalVelocityForUpdate());
			setTimeSinceStartMove(0);
			setTimeSinceEndMove(0);
		}
	}
	
	
	/**
	 * End the horizontal movement of this Buzam.
	 * @post	The new horizontal moving state of this Buzam is false.
	 * 			| new.getMovingHorizontally()
	 * @post	The new horizontal velocity of this Buzam is equal to 0.
	 * 			| new.getHorizontalVelocity()= 0
	 * @post	The new time since this Buzam has last ended moving is 0.
	 * 			| new.getTimeSinceEndMove()==0
	 * @post	The new time since this Buzam has started moving is 0.
	 * 			| new.getTimeSinceStartMove()==0
	 */
	@Raw
	public void endMove(){
		setMovingHorizontally(false);
		setHorizontalVelocity(0);
		setTimeSinceEndMove(0);
		setTimeSinceStartMove(0);
	}
	
	/**
	 * Stop movement of this Buzam in the given direction.
	 * 
	 * @param	direction
	 * 			The given direction.
	 * @effect	If the given direction is equal to the direction of this buzam
	 * 			then endMove()
	 * 			|if(direction==this.getDirection()){
	 * 			|then endMove();
	 */
	@Override
	public void endMove(Orientation direction){
		if(direction==this.getDirection()){
			endMove();
		}
	}
	
	/**
	 * Make this Buzam jump if this Buzam isn't jumping already.
	 * 
	 * @effect	If this Buzam isn't jumping, the vertical velocity of this Buzam is set to the initial vertical velocity and its jumping state is set to true.
	 * 			| if (!isJumping())
	 * 			| 	this.setVerticalVelocity(getInitialverticalVelocity())
	 * 			|	this.setJumping(true)
	 * @note	The method setVerticalVelocity will never throw an exception in 
	 * 			its current implementation because this.getInitialVerticalVelocity() always returns a valid vertical velocity.
	 * 			There is no need to add a try catch statement.
	 */
	@Raw
	@Override
	public void startJump(){
		if (!isJumping()){
			setJumping(true);
			setVerticalVelocity(getInitialVerticalVelocity());
		}
	}
	
	
	/**
	 * End the jump of this Buzam if this Buzam is traveling upwards.
	 * 
	 * @effect	If this Buzam is traveling upwards, then this Buzam's vertical velocity is set to zero.
	 * 			| if(this.getVerticalVelocity()>0)
	 * 			|	this.setVerticalVelocity(0)
	 * @note	The method setVerticalVelocity will never throw an exception in 
	 * 			its current implementation because 0 is a valid vertical velocity.
	 * 			There is no need to add a try catch statement.
	 */
	@Raw
	public void endJump(){
		if (getVerticalVelocity()>0){
			setVerticalVelocity(0);
		}
	}
	
	/**
	 * Check whether this Buzam can have the given ducking state as its ducking state.
	 * @return 	True.
	 * 			|result==true
	 */
	@Override
	public boolean canHaveAsDuckingState(boolean ducking){
		return true;
	}
	
	/**
	 * Make this Buzam duck.
	 * 
	 * @effect	The ducking state of this Buzam is set to true and canEndDuck is set to false.
	 * 			| this.setDucking(true)
	 * 			| this.setCanEndDuck(true)
	 */
	public void startDuck(){
		setDucking(true);
		setWantsEndDuck(false);
	}
	
	/**
	 * End this Buzam's duck.
	 * 
	 * @effect	The ducking state of this Buzam is set to false.
	 * 			| this.setDucking(false)
	 * @throws	IllegalStateException
	 * 			Buzam is currently not able to stand up.
	 * 			| !this.canStandUp()
	 */
	public void endDuck(){
		try{
			setDucking(false);
		} catch (IllegalStateException exc){
			setWantsEndDuck(true);
		}	
	}
	
	
	/**
	 * Return the time since this Buzam has last ended moving.
	 */
	@Basic
	@Raw
	public double getTimeSinceEndMove(){
		return this.timeSinceEndMove;
	}
	
	
	
	/**
	 * Set the time since this Buzam has last ended moving.
	 * 
	 * @param 	timeSinceEndMove
	 * 			The time since this Buzam has last ended moving to be set.
	 * @post	The new time since this Buzam last ended moving is equal to the given time since Buzam last ended moving.
	 * 			|new.getTimeSinceEndMove()==timeSinceEndMove
	 * @throws	IllegalArgumentException
	 * 			The given time since Buzam last ended moving is not a valid action time.
	 * 			|!isValidTimeSinceAction(timeSinceEndMove)
	 * @note	We consider this method a part of advanceTime() and implement it defensively.
	 */
	@Raw
	protected void setTimeSinceEndMove(double timeSinceEndMove) throws IllegalArgumentException{
		if(!isValidTimeSinceAction(timeSinceEndMove))
			throw new IllegalArgumentException();
		this.timeSinceEndMove=timeSinceEndMove;
	}
	
	/**
	 * Return the time since this Buzam has last started moving.
	 */
	@Basic
	@Raw
	public double getTimeSinceStartMove(){
		return this.timeSinceStartMove;
	}
	
	/**
	 * Set the time since this Buzam has last started moving.
	 * @param 	timeSinceStartMove
	 * 			The time since this Buzam has last started moving to be set.
	 * @post	The new time since this Buzam last started moving is equal to the given time since Buzam last started moving.
	 * 			|new.getTimeSinceStartMove()==timeSinceStartMove
	 * @throws	IllegalArgumentException
	 * 			The given time since game object last started moving is not a valid action.
	 * 			|!isValidTimeSinceMove(timeSinceStartMove)
	 * @note	We consider this method a part of advanceTime() and implement it defensively.
	 */
	@Raw
	protected void setTimeSinceStartMove(double timeSinceStartMove) throws IllegalArgumentException{
		if(!isValidTimeSinceAction(timeSinceStartMove))
			throw new IllegalArgumentException();
		this.timeSinceStartMove=timeSinceStartMove;
	}
	
	/**
	 * Variable registering the time since this game object last started moving.
	 */
	private double timeSinceStartMove=0;
	
	/**
	 * Variable registering the time since this game object has last ended moving.
	 */
	private double timeSinceEndMove=Integer.MAX_VALUE;
	
	
	/**
	 * Update the location and velocity of this Buzam. Does nothing because Buzam can
	 * only be controlled by a program.
	 */
	@Raw
	@Override
	public void advanceTime(double deltaTime)
		throws IllegalArgumentException {
		if(!isValidDeltaTime(deltaTime))
			throw new IllegalArgumentException("Not a valid time period!");
		if(this.getProgram()!=null){
			this.getProgram().execute(deltaTime);
		}
		double deltaTimeForPixel=0;
		double sumDeltaTimeForPixel=0;
		double oldHorizontalLocation=getHorizontalLocation();
		double oldVerticalLocation=getVerticalLocation();
		this.setTimeSinceLastHitpointsLoss(deltaTime + getTimeSinceLastHitpointsLoss());
		if(!isMovingHorizontally()){
			setTimeSinceEndMove(getTimeSinceEndMove()+deltaTime);
		}
		else{
			setTimeSinceStartMove(getTimeSinceStartMove()+deltaTime);
		}
		while (sumDeltaTimeForPixel<deltaTime){
			calculateNewJumpingState();
			oldVerticalLocation = getVerticalLocation();
			oldHorizontalLocation = getHorizontalLocation();
			deltaTimeForPixel= getDeltaTimeForPixel(deltaTime);
			if(!(deltaTimeForPixel+sumDeltaTimeForPixel<deltaTime)){
				deltaTimeForPixel = deltaTime - sumDeltaTimeForPixel + 1E-10;
			}
			sumDeltaTimeForPixel+=deltaTimeForPixel;
			this.updateVelocities(deltaTimeForPixel);
			this.updateLocations(deltaTimeForPixel, oldHorizontalLocation, oldVerticalLocation);
			int []overlap = checkAllowedLeftRightTopBottomSideOverlap();
			collisionHandler(overlap,oldHorizontalLocation,oldVerticalLocation);
			calculateNewJumpingState();
			if(wantsEndDuck()){
				endDuck();
			}
		}
		checkWaterContact(deltaTime);
		checkMagmaContact(deltaTime);	
		calculateNewJumpingState();
	}
	
	/**
	 * Update the velocities of this Buzam.
	 * 
	 * @param 	deltaTime
	 * 			A given period of time used in the calculations.
	 */
	private void updateVelocities(double deltaTime){
		double newVerticalVelocity = getVerticalVelocity() + getVerticalAcceleration()*deltaTime;
		double newHorizontalVelocity = getHorizontalVelocity() + getDirection().getNumberForCalculations()*getHorizontalAccelerationForUpdate()*deltaTime;
		try{
			setHorizontalVelocity(newHorizontalVelocity);
		} catch(IllegalArgumentException exc){
			if (Math.abs(newHorizontalVelocity)<Math.abs(getInitialHorizontalVelocityForUpdate()))
				setHorizontalVelocity(getDirection().getNumberForCalculations()*getInitialHorizontalVelocityForUpdate());
			else
				setHorizontalVelocity(getDirection().getNumberForCalculations()*getMaximumHorizontalVelocityForUpdate());
		}
		try{
			setVerticalVelocity(newVerticalVelocity);
		} catch (IllegalArgumentException exc){
			setVerticalVelocity(0);
		}
	}

	/**
	 * Update the locations of this Buzam.
	 * 
	 * @param 	deltaTime
	 * 			A given period of time used in the calculations.
	 * @param 	oldHorizontalLocation
	 * 			The old horizontal location.
	 * @param 	oldVerticalLocation
	 * 			The old vertical location.
	 */
	private void updateLocations(double deltaTime, double oldHorizontalLocation, double oldVerticalLocation){
		double newHorizontalLocation = this.getHorizontalLocation() + 
				100*(this.getHorizontalVelocity()*deltaTime + 
				this.getDirection().getNumberForCalculations()*0.5*getHorizontalAccelerationForUpdate()*Math.pow(deltaTime, 2));
		double newVerticalLocation = this.getVerticalLocation() + 100*(getVerticalVelocity()*deltaTime + 0.5*getVerticalAcceleration()*Math.pow(deltaTime,2));
		try{
			setHorizontalLocation(newHorizontalLocation);
		} catch(IllegalLocationException exc){
			if(newHorizontalLocation>getWorld().getWorldWidth()){
				getWorld().setGameOver(true);
				this.terminate();
				return;
			}
			else{
				setHorizontalLocation((int) oldHorizontalLocation);
			}
		}
		try{
			setVerticalLocation(newVerticalLocation);
		} catch(IllegalLocationException exc){
			if(newVerticalLocation>getWorld().getWorldHeight()){
				getWorld().setGameOver(true);
				this.terminate();
				return;
			}
			else{
				setVerticalLocation(oldVerticalLocation);
				setVerticalVelocity(0);
			}
		}
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
	 * @effect	|if(gameObject instanceof Plant)
	 * 			|	if(this.getHitPoints() != this.getMaxHitPoints() && gameObject.getHitPoints() != 0)
	 * 			|	then gameObject.setHitPoints(0)
	 *			|		 this.addHitPoints(50)
	 *			|else if(gameObject instanceof Shark && gameObject.getHitPoints() != 0)
	 *			|	if(!isUntouchable())
	 *			|		then gameObject.removeHitPoints(50)
	 *			|		if(index2 == 0)
	 *			|			then this.removeHitPoints(50)
	 *			|			this.setTimeSinceLastHitpointsLoss(0)
	 *			|else if(gameObject instanceof Slime && gameObject.getHitPoints() != 0)
	 *			|	if(!isUntouchable())
	 *			|		gameObject.removeHitPoints(50)
	 *			|		if(index2 == 0)
	 *			|			this.removeHitPoints(50)
	 *			|			this.setTimeSinceLastHitpointsLoss(0)
	 */
	@Override
	protected void collisionReaction(int index1, int index2, int index3) {
		GameObject gameObject = this.getWorld().getGameObjectAtIndex(index1);
		if(gameObject instanceof Plant){
			if(this.getHitPoints() != this.getMaxHitPoints() && gameObject.getHitPoints() != 0){
				gameObject.setHitPoints(0);
				this.addHitPoints(50);
			}
		}
		else if(gameObject instanceof Shark && gameObject.getHitPoints() != 0){
			if(!isUntouchable()){
				gameObject.removeHitPoints(50);
				if(index2 == 0){
					this.removeHitPoints(50);
					this.setTimeSinceLastHitpointsLoss(0);
				}
			}
		}
		else if(gameObject instanceof Slime && gameObject.getHitPoints() != 0){
			if(!isUntouchable()){
				((Slime) gameObject).removeHitPointsSchool(50);
				if(index2 == 0){
					this.removeHitPoints(50);
					this.setTimeSinceLastHitpointsLoss(0);
				}
			}
		}
		else if(gameObject instanceof Mazub && gameObject.getHitPoints() != 0){
			if(!isUntouchable() && !gameObject.isUntouchable()){
				if(index3 == 0){
					((Mazub) gameObject).removeHitPoints(50);
					((Mazub) gameObject).setTimeSinceLastHitpointsLoss(0);
				}
				if(index2 == 0){
					this.removeHitPoints(50);
					this.setTimeSinceLastHitpointsLoss(0);
				}
			}
		}
	}
	
	/**
	 * Check whether the left, right, top or bottom of this Buzam overlaps with another game object.
	 * 
	 * @return	The method iterates over all the game objects that can overlap with 
	 * 			this Buzam. That are the game objects that overlap with one of the tile with
	 * 			which this Buzam overlaps. If another game object is found that overlaps 
	 * 			with one of the given sides then an array is returned with at the first position
	 * 			number one defining that there is overlap, at the second position the index 
	 * 			of the game object in the world, at the third position a number registering whether 
	 * 			the bottom perimeter was overlapping during the contact with the other game object
	 * 			and an index registering whether the top perimeter was
	 * 			overlapping during the contact with the other game object.
	 * 			During the iteration is checked whether Buzam currently does not have his maximum number
	 * 			of hitpoints and if the other game object is not a plant.
	 * 			|overlap = {0,0,0,0}
	 * 			|for each game object in gameGameObjectAtTile of this game object
	 * 			|	if( this.getHitPoints() != this.getMaxHitPoints() || !(gameObject instanceof Plant))
	 * 			|		if(gameObject != this && gameObject != null && world.canHaveAsGameObject(gameObject))
	 * 			|			overlap = checkLeftOrRightSideOverlap(gameObject,...);
	 *			|			if(overlap [0] == 1)
	 *			|			then the array "overlap" is returned
	 *			|			or 
	 *			|			overlap = checkTopOrBottomSideOverlap(gameObject,...);
	 *			|			if(overlap [0] == 1)
	 *			|			then the array "overlap" is returned
	 *			|return overlap
	 */
	@Override
	protected int [] checkLeftRightTopBottomSideOverlap(int [][] leftPerimeter1, int [][] rightPerimeter1, int [][] topPerimeter1, int [][] bottomPerimeter1){
		int [] overlap = {0,0,0,0};
		World world = this.getWorld();
		List<GameObject> gameObjects = getGameObjectsAtTiles(world.getTilePositionsIn(getEffectiveHorizontalLocation(), getEffectiveVerticalLocation(), getEffectiveHorizontalLocation()+getWidth(), getEffectiveVerticalLocation()+getHeight()));
		for(int index = 0; index < gameObjects.size(); index++){
			GameObject gameObject = gameObjects.get(index);
			if( this.getHitPoints() != this.getMaxHitPoints() || !(gameObject instanceof Plant)){
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
		}
		return overlap;
		}
	
	
	/**
	 * Check whether the game object makes contact with water and take the corresponding actions.
	 * 
	 * @param 	deltaTime
	 * 			The given time period.
	 * @post	Every 0.2 seconds while being contacted with water, two hitpoints are removed
	 * 			from Buzam. The first 0.2 seconds no hitpoints are removed.
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
				this.removeHitPoints(2);
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
	 * 			from Buzam. The first hitpoints are immediately removed at first contact.
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
				this.removeHitPoints(50);
			else if(Util.fuzzyGreaterThanOrEqualTo(this.getTimeSinceStartMagmaContact(), 0.2)){
				this.setTimeSinceStartMagmaContact(0);
			}
		}
		else{
			this.setTimeSinceStartMagmaContact(0);
		}
	}
	
	/**
	 * Return the current Sprite of this Buzam.
	 */
	@Override
	public Sprite getCurrentSprite(){
		int newSpriteIndex=Integer.MAX_VALUE;
		if (!this.isMovingHorizontally() && !this.isDucking() && this.getTimeSinceEndMove()>1)
			newSpriteIndex=0;
		else if (!this.isMovingHorizontally() && this.isDucking() && this.getTimeSinceEndMove()>1)
			newSpriteIndex=1;
		else if (!this.isMovingHorizontally() && !this.isDucking() && this.getTimeSinceEndMove()<=1 && this.getDirection()==Orientation.RIGHT)
			newSpriteIndex=2;
		else if (!this.isMovingHorizontally() && !this.isDucking() && this.getTimeSinceEndMove()<=1 && this.getDirection()==Orientation.LEFT)
			newSpriteIndex=3;
		else if (this.isMovingHorizontally() && this.getDirection()==Orientation.RIGHT && this.isJumping() && !this.isDucking())
			newSpriteIndex=4;
		else if (this.isMovingHorizontally() && this.getDirection()==Orientation.LEFT && this.isJumping() && !this.isDucking())
			newSpriteIndex=5;
		else if ((this.isDucking() && this.getDirection()==Orientation.RIGHT && this.getTimeSinceEndMove()<=1) || 
				(this.isDucking() && this.getDirection()==Orientation.RIGHT && this.getTimeSinceEndMove()>1 && this.isMovingHorizontally()))
			newSpriteIndex=6;
		else if ((this.isDucking() && this.getDirection()==Orientation.LEFT && this.getTimeSinceEndMove()<=1) ||
				(this.isDucking() && this.getDirection()==Orientation.LEFT && this.getTimeSinceEndMove()>1 && this.isMovingHorizontally()))
			newSpriteIndex=7;
		else if(!this.isJumping() && !this.isDucking()){
			int i = (int) Math.floor(this.getTimeSinceStartMove()/0.075);
			int m=(this.getNbImages()-8)/2;
			if (this.getDirection()==Orientation.RIGHT){
			newSpriteIndex=i%m+8;
			}
			else{
				newSpriteIndex=i%m+8+m;
			}
		}
		return this.getImageAt(newSpriteIndex);
	}
	
	/**
	 * Check whether the given number of images in the given image array is valid for all Buzams.
	 * 
	 * @param 	nbImages
	 * 			The number of images to be checked.
	 * @return	The given number of images must be an even number and must be greater than or equal to 10;
	 * 			| result == (nbImages >= 10 && nbImages%2==0)
	 * 
	 */
	@Override
	public boolean canHaveAsNbImages(int nbImages){
		return nbImages>=10 && nbImages%2==0;
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
