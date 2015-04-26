package jumpingalien.model;

import jumpingalien.util.Sprite;
import jumpingalien.util.Util;
import be.kuleuven.cs.som.annotate.*;

import java.lang.Math;
import java.util.Random;

/**
 * A class of slimes which extends the class of game objects. A slime is a non player character which is hostile to Mazub and belongs to a school of slimes.
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
	 * @param 	images
	 * 			An array of sprites for this new slime.
	 * @param	school
	 * 			The school of slimes to which this new slime belongs.
	 * @effect	This new slime is initialized as a game object with the given horizontal location, vertical location, a horizontal velocity of zero, a vertical velocity of zero,
	 * 			an initial horizontal velocity when not ducking of zero, a maximum horizontal velocity when not ducking equal to the maximum horizontal velocity constant, 
	 * 			an initial vertical velocity of zero, the constant horizontal acceleration
	 * 			for all slimes, a false ducking state, a number of hit points of 100, a maximum number of hit points of 100 and an image array containing its sprites.
	 * 			| super(horizontalLocation, verticalLocation, 0, 0, 0, maximumHorizontalVelocity, 0, horizontalAcceleration, false, 100, 100, images)
	 * @effect	A binary relationship is established between this slime and the given school.
	 * 			| school.addAsSlime(this)
	 * 			| this.setSchool(school)
	 */
	@Raw
	public Slime(int horizontalLocation, int verticalLocation, Sprite[] images, School school)
		throws IllegalArgumentException, IllegalLocationException {
		super(horizontalLocation, verticalLocation, 0, 0, initialHorizontalVelocityAtSpawn, maximumHorizontalVelocityAtSpawn, 0, horizontalAccelerationAtSpawn, false, HIT_POINTS, HIT_POINTS, images);
		school.addAsSlime(this);
		this.setSchool(school);
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
	public boolean canHaveAsHorizontalVelocity(double horizontalVelocity){
		horizontalVelocity = Math.abs(horizontalVelocity);
		return Util.fuzzyGreaterThanOrEqualTo(horizontalVelocity,this.getInitialHorizontalVelocityForUpdate()) 
				&& Util.fuzzyLessThanOrEqualTo(horizontalVelocity, this.getMaximumHorizontalVelocityForUpdate())
				|| Util.fuzzyEquals(horizontalVelocity, 0);
	}
	
	/**
	 * @return	The given initial horizontal velocity is equal to zero.
	 * 			| result == (intialHorizontalVelocity==0)
	 */
	@Override
	public boolean isPossibleInitialHorizontalVelocity(double initialHorizontalVelocity) {
		return initialHorizontalVelocity==initialHorizontalVelocityAtSpawn && initialHorizontalVelocityAtSpawn>=0;
	}
	
	public boolean isPossibleMaximumHorizontalVelocity(double maximumHorizontalVelocity){
		return maximumHorizontalVelocity==maximumHorizontalVelocityAtSpawn && maximumHorizontalVelocityAtSpawn>0;
	}
	
	/**
	 * Return the initial horizontal velocity of this game object.
	 * 
	 * @return	If the ducking state of this game object is true, the initial horizontal velocity of this game object is equal to the ducking velocity constant.
	 * 			Otherwise the initial horizontal velocity of this game object is equal to its initial horizontal velocity when not ducking.
	 * 			|if (this.isDucking())
	 * 			|	result==DUCKING_VELOCITY
	 * 			|else
	 * 			| 	result==this.getInitialHorizontalVelocity()
	 */
	@Raw
	public double getInitialHorizontalVelocityForUpdate(){
		return this.getInitialHorizontalVelocity();
	}
	
	/**
	 * Return the maximum horizontal velocity of this game object.
	 * 
	 * @return	If the ducking state of this game object is true, the maximum horizontal velocity of this game object is equal to the ducking velocity constant.
	 * 			Otherwise the maximum horizontal velocity of this game object is equal to its maximum horizontal velocity when not ducking.
	 * 			|if (this.isDucking())
	 * 			|	result==DUCKING_VELOCITY
	 * 			|else
	 * 			| 	result==this.getMaximumHorizontalVelocity()
	 */
	@Raw
	public double getMaximumHorizontalVelocityForUpdate(){
		return this.getMaximumHorizontalVelocity();
	}
	
	/**
	 * 
	 */
	private static final double initialHorizontalVelocityAtSpawn=0;
	
	/**
	 * Constant registering the maximum horizontal velocity when not ducking of this Mazub.
	 */
	private static final double maximumHorizontalVelocityAtSpawn = 2.5;

	/**
	 * Returns the vertical acceleration of this slime.
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
	public boolean canHaveAsVerticalAcceleration(double verticalAcceleration){
		return verticalAcceleration==0 || Util.fuzzyEquals(verticalAcceleration, VERTICAL_ACCELERATION);
	}
	
	/**
	 * Check whether the given horizontal acceleration is a valid horizontal acceleration.
	 * @param	horizontalAcceleration
	 * 			The horizontal acceleration to be checked.
	 * @return	The given horizontal acceleration is equal to zero or the horizontal acceleration of this game object.
	 * 			| result == (horizontalAcceleration==0 || Util.fuzzyEquals(horizontalAcceleration,this.horizontalAcceleration))
	 */
	public boolean canHaveAsHorizontalAcceleration(double horizontalAcceleration){
		return horizontalAcceleration==horizontalAccelerationAtSpawn && horizontalAcceleration!=0;
	}
	
	
	/**
	 *  Constant registering the horizontal acceleration of all slimes.
	 */
	private static final double horizontalAccelerationAtSpawn = 0.7;
	
	/**
	 * Check whether this slime can have the given ducking state as its ducking state.
	 * @return 	The given ducking state is false.
	 * 			|result== (ducking==false)
	 */
	public boolean canHaveAsDuckingState(boolean ducking){
		return !ducking;
	}
	
	/**
	 * Start a new action for this slime.
	 * @effect 	The current action duration of this slime is set to a random action duration between the minimum and maximum action duration,
	 * 			the direction of this slime is set to a random direction, the slime's horizontal moving state is set to true and the time since
	 * 			the start of the action of this slime is set to 0.
	 * 			| Random r = new Random()
	 * 			| this.setCurrentActionDuration(MINIMUM_ACTION_DURATION+(MAXIMUM_ACTION_DURATION-MINIMUM_ACTION_DURATION)*r.nextDouble())
	 * 			| this.setDirection(r.nextBoolean() ? Direction.LEFT : Direction.RIGHT)
	 * 			| this.setMovingHorizontally(true)
	 * 			| this.setTimeSinceStartAction(0)
	 */
	private void startNewAction(){
		Random r = new Random();
		this.setCurrentActionDuration(MINIMUM_ACTION_DURATION+(MAXIMUM_ACTION_DURATION-MINIMUM_ACTION_DURATION)*r.nextDouble());
		this.setDirection(r.nextBoolean() ? Direction.LEFT : Direction.RIGHT);
		this.setMovingHorizontally(true);
		this.setTimeSinceStartAction(0);
		this.setHorizontalVelocity(this.getInitialHorizontalVelocityForUpdate()*this.getDirection().getNumberForCalculations());
	}
	
	/**
	 * Set the current action duration of this slime to the given duration. This is how long the current action of this slime lasts.
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
	 * Constant registering the minimum action duration.
	 */
	private static final double MINIMUM_ACTION_DURATION=2;
	
	/**
	 * Constant registering the maximum action duration.
	 */
	private static final double MAXIMUM_ACTION_DURATION=6;
	
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
			int []overlap = checkAllowedLeftRightTopBottomSideOverlap();
			collisionHandler(overlap,oldHorizontalLocation,oldVerticalLocation);
		}
		this.setTimeSinceStartAction(this.getTimeSinceStartAction()+deltaTime);
		this.checkWaterContact(deltaTime);
		this.checkMagmaContact(deltaTime);
		this.calculateNewJumpingState();
	}
	
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
	public void checkMagmaContact(double deltaTime){
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
	
	public void removeHitPointsSchool(int hitPoints){
		this.removeHitPoints(hitPoints);
		for(Slime slime: this.getSchool().getAllSlimes()){
			if(slime!=this){
				slime.removeHitPoints(1);
			}
		}
	}
	
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
		if(hitPointsToBeAdded>=0){
			this.addHitPoints(hitPointsToBeAdded);
		}
		else{
			this.removeHitPoints(Math.abs(hitPointsToBeAdded));
		}
		this.setSchool(newSchool);
		school.addAsSlime(this);
	}
	
	/**
	 * Check whether the given number of images in the given image array is valid for all slimes.
	 * 
	 * @param 	nbImages
	 * 			The number of images to be checked.
	 * @return	The given number of images must be equal to 2.
	 * 			| result == (nbImages==2)
	 * 
	 */
	@Override
	public boolean canHaveAsNbImages(int nbImages){
		return nbImages==2;
	}	
	
	/**
	 * Terminate slime.
	 * @effect	If this slime is not already terminated, it is terminated as a game object, effectively breaking the relationship between this slime and its world.
	 * 			This slime is also removed from its school and its school is set to null
	 * 			|if (!this.isTerminated())
	 * 			|	super.terminate()
	 * 			| 	this.setSchool(null)
	 */
	public void terminate() {
		if (!this.isTerminated()){
			super.terminate();
			this.getSchool().removeAsSlime(this);
			this.setSchool(null);
		}
	}
	
	/**
	 * Set the given school as the school of this slime.
	 * @param 	school
	 * 			The school to be set.
	 * @post	| if(this.canHaveAsSchool(school))
	 * 			| 		new.getSchool()==this
	 */
	public void setSchool(School school){
		if(this.canHaveAsSchool(school)){
			this.school=school;
		}
	}
	
	/**
	 * Return the school to which this slime belongs.
	 */
	public School getSchool(){
		return this.school;
	}
	
	/**
	 * Check whether this slime can have the given school as its school.
	 * @param 	school
	 * @return	result == (school==null || school.canHaveAsGameObject(this))
	 */
	public boolean canHaveAsSchool(School school){
		return  ((this.isTerminated() && school==null) || (!this.isTerminated() && school.canHaveAsSlime(this)));
	}
	
	
	/**
	 * Check whether this slime has a school.
	 * @return 	This slime can have its school as its world and its school has this slime as one of its slimes.
	 * 			| this.canHaveAsSchool(this.getSchool()) && this.getSchool().hasAsSlime(this)
	 */
	public boolean hasProperSchool(){
		return this.canHaveAsSchool(this.getSchool()) && this.getSchool().hasAsSlime(this);
	}
	
	/**
	 * Variable registering the school to which this slime belongs.
	 */
	private School school;

	
	private final static int HIT_POINTS=100;

	
}


