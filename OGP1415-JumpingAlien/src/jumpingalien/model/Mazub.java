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
	 * @param 	initialHorizontalVelocity
	 * 		  	The initial horizontal velocity for this new Mazub when not ducking.
	 * @param 	maximumHorizontalVelocity
	 * 		  	The maximum horizontal velocity for this new Mazub when not ducking.
	 * @param 	ducking
	 * 		  	The ducking state for this new Mazub.
	 * @param 	images
	 * 		  	Array of sprites to display Mazub for this new Mazub.
	 * @effect	This new Mazub is initialized as a game object with the given horizontal location, vertical location, horizontal velocity, vertical velocity,
	 * 			initial horizontal velocity, initial vertical velocity, maximum horizontal velocity, hitpoints, images, the constant initial vertical velocity, the constant horizontal acceleration,
	 * 			and the constant maximum number of hitpoints.
	 * 			| super(horizontalLocation, verticalLocation, horizontalVelocity, verticalVelocity, initialHorizontalVelocity, maximumHorizontalVelocity, INITIAL_VERTICAL_VELOCITY, HORIZONTAL_ACCELERATION, hitPoints, MAX_HIT_POINTS, images)
	 * @post	The ducking state of this new Mazub is equal to the 
	 * 			given ducking state.
	 * 			|new.isDucking == ducking
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
				double verticalVelocity, double initialHorizontalVelocity,
				double maximumHorizontalVelocity, boolean ducking, int hitPoints, Sprite... images)
		throws IllegalArgumentException, IllegalLocationException {
		super(horizontalLocation, verticalLocation, horizontalVelocity, verticalVelocity, initialHorizontalVelocity, maximumHorizontalVelocity, INITIAL_VERTICAL_VELOCITY, HORIZONTAL_ACCELERATION, hitPoints, MAX_HIT_POINTS, images);
		setDucking(ducking);
		if(isDucking())
			startDuck();
		if (Util.fuzzyGreaterThanOrEqualTo(horizontalVelocity, 0))
			setDirection(Direction.RIGHT);
		else
			setDirection(Direction.LEFT);
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
		this(horizontalLocation, verticalLocation, 0, 0, 1, 3, false, 100, images);
	}
	
	
	/**
	 * Return the initial horizontal velocity of this Mazub.
	 * 
	 * @return	If the ducking state of this Mazub is true, the initial horizontal velocity of this Mazub is equal to 1.
	 * 			Otherwise the initial horizontal velocity of this Mazub is equal to its initial horizontal velocity defined in its superclass.
	 * 			|if (this.isDucking())
	 * 			|	result==1
	 * 			|else
	 * 			| 	result==super.getInitialHorizontalVelocity()
	 */
	@Raw
	public double getInitialHorizontalVelocity(){
		if (this.isDucking())
			return 1;
		else 
			return super.getInitialHorizontalVelocity();
	}
	
	
	/**
	 * Return the maximum horizontal velocity of this Mazub.
	 * 
	 * @return	If the ducking state of this Mazub is true, the maximum horizontal velocity of this Mazub is equal to 1.
	 * 			Otherwise the maximum horizontal velocity of this Mazub is equal to its maximum horizontal velocity defined in its superclass.
	 * 			|if (this.isDucking())
	 * 			|	result==1
	 * 			|else
	 * 			| 	result==super.getMaximumHorizontalVelocity()
	 */
	@Raw
	public double getMaximumHorizontalVelocity(){
		if (this.isDucking())
			return 1;
		else 
			return super.getMaximumHorizontalVelocity();
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
	 * Check whether the given initial horizontal velocity is a possible initial horizontal velocity for any Mazub.
	 * 
	 * @param 	initialHorizontalVelocity
	 * 			The initial horizontal velocity to check.
	 * @return	True if the given initial horizontal velocity is greater than or equal to 1.
	 * 			result == Util.fuzzyGreaterThanOrEqualTo(initialHorizontalVelocity, 1)
	 */
	public boolean isPossibleInitialHorizontalVelocity(double initialHorizontalVelocity){
		return Util.fuzzyGreaterThanOrEqualTo(initialHorizontalVelocity, 1);
	}
	
	/**
	 *  Constant registering the initial vertical velocity of all Mazubs.
	 */
	private static final double INITIAL_VERTICAL_VELOCITY = 8;
	

	
	
	
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
			return super.getHorizontalAcceleration();
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
			return super.getVerticalAcceleration();
		else
			return 0;
	}
	
	private static final double HORIZONTAL_ACCELERATION=0.9;
	
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
			return !this.getWorld().areaCoincidesWithTerrain(this.getEffectiveHorizontalLocation(), 
					this.getEffectiveVerticalLocation()+1, this.getImageAt(0).getWidth()-1, 
					this.getImageAt(0).getHeight())[1];
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
				if (Math.abs(newHorizontalVelocity)<Math.abs(super.getInitialHorizontalVelocity()))
					this.setHorizontalVelocity(this.getDirection().getNumberForCalculations()*this.getInitialHorizontalVelocity());
				else
					this.setHorizontalVelocity(this.getDirection().getNumberForCalculations()*super.getMaximumHorizontalVelocity());
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
			int [] overlap = this.checkLeftRightTopBottomSideOverlap();
			if(overlap[0]==1){
				collisionReaction(overlap[1],overlap[2]);
					
			}		
			if(this.canEndDuck){
				this.endDuck();
			}
		}
		checkWaterContact(deltaTime);
		checkMagmaContact(deltaTime);	
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

	
	protected void collisionReaction(int index1, int index2) {
		GameObject gameObject = this.getWorld().getGameObjectAtIndex(index1);
		if(gameObject instanceof Plant){
			if(this.getHitPoints() != this.getMaxHitPoints()){
				gameObject.setHitPoints(0);
				gameObject.unsetWorld();
				this.addHitPoints(50);
			}
		}
		else if(gameObject instanceof Shark){
			gameObject.removeHitPoints(50);
			if(index2 == 0){
				this.removeHitPoints(50);
			}
		}
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
	public void checkWaterContact(double deltaTime){
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
	 * 			from Mazub. The first hitpoints are immediately removed at first contact.
	 * 			|
	 */
	@Override
	public void checkMagmaContact(double deltaTime){
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
	

	private final static int MAX_HIT_POINTS=500;
	
	
	public boolean isTerminated() {
		return this.isTerminated;
	}

	public void terminate() {
		this.getWorld().terminate();
		this.isTerminated=true;
	}
	
	private boolean isTerminated=false;
}
