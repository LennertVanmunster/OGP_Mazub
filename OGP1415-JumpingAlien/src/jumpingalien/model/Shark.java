package jumpingalien.model;

import java.util.Random;

import jumpingalien.util.Sprite;
import jumpingalien.util.Util;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of sharks which extends the class of game objects. A shark is a non player character which is hostile to Mazub and is primarily found in water tiles.
 * 
 * @version	1.0
 * @authors Pieter Van Damme and Lennert Vanmunster
 *
 */
public class Shark extends GameObject{
	
	/**
	 * Initialize a new shark as with given horizontal and vertical location, an array of sprites and all of its parameter constants. 
	 * @param 	horizontalLocation
	 * 			The horizontal location for this new shark.
	 * @param 	verticalLocation
	 * 			The vertical location for this new shark.
	 * @param 	images
	 * 			An array of sprites.
	 * @effect 	This new shark is initialized as a game object with the given horizontal location, vertical location, a horizontal velocity of zero, a vertical velocity of zero,
	 * 			the initial horizontal velocity at spawn of sharks, the maximum horizontal velocity at spawn of sharks, 
	 * 			the initial vertical velocity for all sharks, the horizontal acceleration at spawn for sharks, a false ducking state, 
	 * 			a number of hit points equal to the shark hit point constant, a maximum number of hit points equal to the shark hit point constant and an image array containing its sprites.
	 * 			| super(horizontalLocation, verticalLocation, 0, 0, initialHorizontalVelocityAtSpawn, maximumHorizontalVelocityAtSpawn, initialVerticalVelocity, horizontalAccelerationAtSpawn, false, 100, MAX_HIT_POINTS, images)
	 */
	@Raw
	public Shark(int horizontalLocation, int verticalLocation, Sprite... images){
		super(horizontalLocation, verticalLocation, 0, 0, initialHorizontalVelocityAtSpawn, maximumHorizontalVelocityAtSpawn, initialVerticalVelocity, horizontalAccelerationAtSpawn, false, HIT_POINTS, HIT_POINTS, images);
	}
	
	
	/**
	 * Check whether this shark can have the given horizontal velocity as its horizontal velocity.
	 * @param 	horizontalVelocity
	 * @return	True if the absolute value of the given horizontal velocity is equal to zero or greater than or equal to the initial horizontal velocity of this shark
	 * 			and less than or equal to the maximum horizontal velocity of this game object.
	 * 			| result== Util.fuzzyGreaterThanOrEqualTo(horizontalVelocity,this.getInitialHorizontalVelocityForUpdate()) 
	 *			&& Util.fuzzyLessThanOrEqualTo(horizontalVelocity, this.getMaximumHorizontalVelocityForUpdate())
	 *			|| Util.fuzzyEquals(horizontalVelocity, 0)
	 */
	public boolean canHaveAsHorizontalVelocity(double horizontalVelocity){
		horizontalVelocity = Math.abs(horizontalVelocity);
		return Util.fuzzyGreaterThanOrEqualTo(horizontalVelocity,getInitialHorizontalVelocityForUpdate()) 
				&& Util.fuzzyLessThanOrEqualTo(horizontalVelocity, getMaximumHorizontalVelocityForUpdate())
				|| Util.fuzzyEquals(horizontalVelocity, 0);
	}
	
	/**
	 * Check whether the given initial horizontal velocity is a possible initial horizontal velocity for any shark.
	 * @return	True if the given initial horizontal velocity is equal to the initial horizontal velocity at spawn for sharks, denoting its
	 * 			immutability, and the given velocity is greater than or equal to zero.
	 * 			result == (Util.fuzzyGreaterThanOrEqualTo(initialHorizontalVelocity, 0) && initialHorizontalVelocity==initialHorizontalVelocityAtSpawn)
	 */
	public boolean isPossibleInitialHorizontalVelocity(double initialHorizontalVelocity){
		return initialHorizontalVelocity==initialHorizontalVelocityAtSpawn && Util.fuzzyGreaterThanOrEqualTo(initialHorizontalVelocityAtSpawn, 0);
	}
	
	/**
	 * Check whether the given maximum horizontal velocity is a possible maximum horizontal velocity for any shark.
	 * @return	True if the given maximum horizontal velocity is equal to the maximum horizontal velocity at spawn for sharks, denoting its
	 * 			immutability, and the given velocity is greater than or equal to zero.
	 * 			result == (maximumHorizontalVelocityAtSpawn>0 && maximumHorizontalVelocity==maximumHorizontalVelocityAtSpawn)
	 */
	public boolean isPossibleMaximumHorizontalVelocity(double maximumHorizontalVelocity){
		return maximumHorizontalVelocity==maximumHorizontalVelocityAtSpawn && maximumHorizontalVelocityAtSpawn>0;
	}
	
	/**
	 * Return the current initial horizontal velocity of this shark for use in update calculations.
	 * 
	 * @return	The sharks regular initial horizontal velocity.
	 * 			| 	result==this.getInitialHorizontalVelocity()
	 */
	@Raw
	public double getInitialHorizontalVelocityForUpdate(){
		return getInitialHorizontalVelocity();
	}
	
	/**
	 * Return the maximum horizontal velocity of this shark for use in update calculations.
	 * 
	 * @return	The regular maximum horizontal velocity.
	 * 			| 	result==this.getMaximumHorizontalVelocity()
	 */
	@Raw
	public double getMaximumHorizontalVelocityForUpdate(){
		return getMaximumHorizontalVelocity();
	}
	
	/**
	 * Variable storing the maximum horizontal velocity of all sharks at spawn.
	 */
	private static final double maximumHorizontalVelocityAtSpawn=4;
	
	/**
	 * Variable storing the maximum horizontal velocity of all sharks at spawn.
	 */
	private static final double initialHorizontalVelocityAtSpawn=0;
	
	/**
	 *  Variable registering the initial vertical velocity of all sharks.
	 */
	private static final double initialVerticalVelocity = 2;
	
	/**
	 * Return the current vertical acceleration of this shark.
	 * @return	If this shark is currently only in contact with water, the vertical acceleration is zero.
	 * 			Otherwise the vertical acceleration is equal to the gravitational constant.
	 * 			|if(this.checkWaterAndNoAirContact())
	 *			|	result == 0
	 *			|else
	 *			| 	result == VERTICAL_ACCELERATION
	 */
	@Raw
	public double getVerticalAcceleration(){
		if(checkWaterAndNoAirContact() && this.getLastJump() >= 4){
			return 0;
		}
		else if(checkWaterAndNoAirContact() && this.getLastJump() < 4){
			return maximumFloatingVerticalAcceleration * this.getRandomDivingConstant();
		}
		else
			return VERTICAL_ACCELERATION;
	}
	
	/**
	 * Check whether this shark can have the given vertical acceleration as its vertical acceleration.
	 * @return	The absolute value of the given vertical acceleration is less than the maximum floating vertical acceleration or the given vertical acceleration
	 * 			is equal to the gravitational constant.
	 * 			|	Util.fuzzyLessThanOrEqualTo(Math.abs(verticalAcceleration),maximumFloatingVerticalAcceleration) || Util.fuzzyEquals(verticalAcceleration,VERTICAL_ACCELERATION)
	 */
	public boolean canHaveAsVerticalAcceleration(double verticalAcceleration){
		return Util.fuzzyLessThanOrEqualTo(Math.abs(verticalAcceleration),maximumFloatingVerticalAcceleration) || Util.fuzzyEquals(verticalAcceleration,VERTICAL_ACCELERATION);
	}
	
	/**
	 * Check whether the given horizontal acceleration is a valid horizontal acceleration for any shark.
	 * @param	horizontalAcceleration
	 * 			The horizontal acceleration to be checked.
	 * @return	The given horizontal acceleration is not equal to zero and equal to the horizontal acceleration of sharks at spawn, denoting its immutability.
	 * 			| result == (horizontalAcceleration==0 && horizontalAcceleration==horizontalAccelerationAtSpawn)
	 */
	public boolean canHaveAsHorizontalAcceleration(double horizontalAcceleration){
		return horizontalAcceleration==horizontalAccelerationAtSpawn && horizontalAccelerationAtSpawn!=0;
	}
	
	/**
	 * Variable registering the horizontal acceleration of all sharks at spawn.
	 */
	private static final double horizontalAccelerationAtSpawn=1.5;
	
	/**
	 * Variable registering the maximum vertical acceleration while floating of all sharks.
	 */
	private static final double maximumFloatingVerticalAcceleration=0.2;
	
	/**
	 * Check whether this shark can have the given ducking state as its ducking state.
	 * @return 	The given ducking state is false.
	 * 			|result== (ducking==false)
	 */
	public boolean canHaveAsDuckingState(boolean ducking){
		return !ducking;
	}
	
	/**
	 * Check whether the given number of images is a valid number of images
	 * for the class shark.
	 * 
	 * @param	nbImages
	 * 			The given number of images.
	 * @return	The given number must be equal to two.
	 * 			|result == nbImages == 2; 
	 */
	@Override
	public boolean canHaveAsNbImages(int nbImages){
		return nbImages == 2;
	}
	
	/**
	 * Return the time since the start of the air contact of this shark.
	 */
	public double getTimeSinceStartAirContact(){
		return this.timeSinceAirContact;
	}
	
	/**
	 * Set the time since the start of the air contact of this game object.
	 * 
	 * @param	time
	 * 			The time to be set.
	 * @post	The new time since start air contact is equal to the given time.
	 * 			|new.getTimeSinceStartAirContact()==time
	 * @throws	IllegalArgumentException
	 * 			The given time is not a valid action time.
	 * 			|!isValidTimeSinceAction(time)
	 * 			
	 */
	public void setTimeSinceStartAirContact(double time)
	throws IllegalArgumentException{
		if(!isValidTimeSinceAction(time))
			throw new IllegalArgumentException();
		this.timeSinceAirContact = time;
			
	}
	
	/**
	 * Variable registering the time since this shark started making contact with air.
	 */
	private double timeSinceAirContact = 0;
	
	private void startNewAction(){
		this.setLastJump(this.getLastJump() + 1);
		Random r = new Random();
		this.setCurrentActionDuration(minimumActionDuration+(maximumActionDuration-minimumActionDuration)*r.nextDouble());
		this.setDirection(r.nextBoolean() ? Direction.LEFT : Direction.RIGHT);
		this.setHorizontalVelocity(this.getInitialHorizontalVelocityForUpdate()*this.getDirection().getNumberForCalculations());
		this.setMovingHorizontally(true);
		this.setTimeSinceStartAction(0);
		this.setRandomDivingConstant((2.0 *r.nextDouble() - 1.0));
		if(this.getLastJump() >= 4){
				this.setVerticalVelocity(getInitialVerticalVelocity());
				this.setLastJump(0);
		}
	}
	
	/**
	 * Set the current action duration of this shark to the given duration. This is how long the current action of this shark lasts.
	 * @param 	duration
	 * 			The duration to be set in seconds.
	 * @post	| new.getCurrentActionDuration()==duration
	 */
	private void setCurrentActionDuration(double duration) {
		this.currentActionDuration=duration;
	}
	
	/**
	 * Return the current action duration.
	 */
	public double getCurrentActionDuration(){
		return currentActionDuration;
	}

	/**
	 * Variable registering the current action duration.
	 */
	private double currentActionDuration=0;

	/**
	 * Variable registering the minimum action duration of all sharks.
	 */
	private static final double minimumActionDuration=1;
	
	/**
	 * Variable registering the maximum action duration of all sharks.
	 */
	private static final double maximumActionDuration=4;
	
	public int getLastJump(){
		return this.lastJump;
	}
	
	public void setLastJump(int lastJump){
		if(lastJump < 0){
			this.lastJump = 0;
		}
		else{
			this.lastJump = lastJump;
		}
	}
	
	private int lastJump = 2; 
	
	public double getRandomDivingConstant() {
		return randomDivingConstant;
	}
	
	public void setRandomDivingConstant(double randomDivingConstant) {	
		this.randomDivingConstant = randomDivingConstant;
	}

	private double randomDivingConstant;
	
	/**
	 * Update the location and velocity of this shark.
	 * 
	 * @param 	deltaTime
	 * 			The period of time that is used to update this shark.
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
		if(this.getTimeSinceStartAction()>=this.getCurrentActionDuration()){
			this.startNewAction();
		}
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
			if(this.isJumping()){
				if(this.checkWaterAndNoAirContact()){
					this.setJumping(false);
					this.setVerticalVelocity(0);
				}
			}
			int []overlap = checkAllowedLeftRightTopBottomSideOverlap();
			collisionHandler(overlap,oldHorizontalLocation,oldVerticalLocation);
		}
		this.setTimeSinceStartAction(this.getTimeSinceStartAction()+deltaTime);
		checkAirContact(deltaTime);
		checkMagmaContact(deltaTime);
	}
	
	protected void collisionReaction(int index1, int index2, int index3) {
		GameObject gameObject = this.getWorld().getGameObjectAtIndex(index1);
		if(gameObject instanceof Slime){
			gameObject.removeHitPoints(50);
			this.removeHitPoints(50);
		}
		else if(gameObject instanceof Mazub){
			if(!((Mazub) gameObject).isUntouchable()){
				this.removeHitPoints(50);
				if(index3 == 0){
					gameObject.removeHitPoints(50);
					((Mazub) gameObject).setTimeSinceLastHitpointsLoss(0);
				}
			}
		}
	}
		
	
	/**
	 * Check whether the shark makes contact with water and no contact with air
	 * 
	 * @return 	|if(contactTiles[2] == true && contactTiles[0] == false)
	 * 			|then result == true
	 * 			|else result == false
	 */
	public boolean checkWaterAndNoAirContact(){
		boolean [] contactTiles = (this.getWorld().areaCoincidesWithTerrain(this.getEffectiveHorizontalLocation(), 
				this.getEffectiveVerticalLocation()+1, this.getWidth()-1, this.getHeight()-2)).clone();
		if(contactTiles[2] == true && contactTiles[0] == false){
			return true;
		}
		else{
			this.setJumping(true);
			return false;
		}
	}

	/**
	 * Check whether the shark makes contact with air and take the corresponding actions.
	 * 
	 * @param 	deltaTime
	 * 			The given time period.
	 * @post	Every 0.2 seconds while being contacted with air, six hitpoints are removed
	 * 			from Mazub. The first 0.2 seconds no hitpoints are removed.
	 * 			|
	 */
	public void checkAirContact(double deltaTime){
		boolean [] contactTiles = (this.getWorld().areaCoincidesWithTerrain(this.getEffectiveHorizontalLocation(), 
				this.getEffectiveVerticalLocation()+1, this.getWidth()-1, this.getHeight()-2)).clone();
		if(contactTiles[0] == true){
			double time = getTimeSinceStartAirContact();
			this.setTimeSinceStartAirContact(time + deltaTime);
			if(Util.fuzzyGreaterThanOrEqualTo(this.getTimeSinceStartAirContact(), 0.2)){
				this.removeHitPoints(6);
				this.setTimeSinceStartAirContact(0);
			}
		}
		else
			this.setTimeSinceStartAirContact(0);
	}
	
	/**
	 *Check whether the shark makes contact with magma and take the corresponding actions. 
	 * @param 	deltaTime
	 * 			The given time period.
	 * @post	Every 0.2 seconds while being contacted with magma, fifty hitpoints are removed
	 * 			from shark. The first hitpoints are immediately removed at first contact.
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
	 * Constant registering the maximum number of hit points of a shark.
	 */
	private final static int HIT_POINTS=100;
}
