package jumpingalien.model;

import jumpingalien.util.Sprite;
import jumpingalien.util.Util;
import be.kuleuven.cs.som.annotate.*;

import java.lang.Math;
import java.util.Random;

/**
 * 
 * @invar	
 * @version 2.0
 * @authors Pieter Van Damme and Lennert Vanmunster
 *
 */
public class Slime extends GameObject {
	/**
	 * Initialize a new Slime with given horizontal and vertical location,
	 * ducking state and an array of sprites. 
	 * 
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
	public Slime(int horizontalLocation, int verticalLocation, Sprite[] images, School school)
		throws IllegalArgumentException, IllegalLocationException {
		super(horizontalLocation, verticalLocation, 0, 0, 0, MAXIMUM_HORIZONTAL_VELOCITY, 0, HORIZONTAL_ACCELERATION, 100, 100, images);
		school.addAsSlime(this);
	}
	
	
	/**
	 * Constant registering the maximum horizontal velocity when not ducking of this Mazub.
	 */
	private static final double MAXIMUM_HORIZONTAL_VELOCITY = 2.5;
	
	
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
		if (Util.fuzzyEquals(Math.abs(this.getHorizontalVelocity()), getMaximumHorizontalVelocity())){
			return 0;
		}
		else{
			return super.getHorizontalAcceleration();
		}
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
	 *  Constant registering the horizontal acceleration of all Mazubs.
	 */
	private static final double HORIZONTAL_ACCELERATION = 0.7;
	
	private void startNewAction(){
		Random r = new Random();
		this.setCurrentActionDuration(MINIMUM_ACTION_DURATION+(MAXIMUM_ACTION_DURATION-MINIMUM_ACTION_DURATION)*r.nextDouble());
		this.setDirection(r.nextBoolean() ? Direction.LEFT : Direction.RIGHT);
		this.setTimeSinceStartAction(0);
	}
	
	private void setCurrentActionDuration(double duration) {
		this.currentActionDuration=duration;
	}
	
	public double getCurrentActionDuration(){
		return currentActionDuration;
	}
	
	public double getTimeSinceStartAction() {
		return this.timeSinceStartAction;
	}


	public void setTimeSinceStartAction(double timeSinceStartAction) {
		this.timeSinceStartAction = timeSinceStartAction;
	}

	private double currentActionDuration=0;
	
	private double timeSinceStartAction = 0;
	
	private static final double MINIMUM_ACTION_DURATION=2;
	
	private static final double MAXIMUM_ACTION_DURATION=6;
	
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
		if(this.getTimeSinceStartAction()>=this.getCurrentActionDuration()){
			this.startNewAction();
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
				if (this.getDirection()==Direction.LEFT){
					this.setDirection(Direction.RIGHT);
				}
				else{
					this.setDirection(Direction.LEFT);
				}
			}
			try{
				this.setVerticalLocation(newVerticalLocation);
				oldVerticalLocation=newVerticalLocation;
			} catch(IllegalLocationException exc){
				this.setVerticalLocation(oldVerticalLocation);
				this.setVerticalVelocity(0);
			}
		}
		this.checkWaterContact(deltaTime);
		this.checkMagmaContact(deltaTime);
		this.calculateNewJumpingState();
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
		return nbImages==2;
	}	
	
	public boolean isTerminated() {
		return this.isTerminated;
	}

	public void terminate() {
		this.getWorld().removeAsGameObject(this);
		this.setWorld(null);
		this.getSchool().removeAsSlime(this);
		this.isTerminated=true;
	}
	
	private boolean isTerminated=false;

	public boolean isValidVerticalVelocity(double verticalVelocity) {
		return true;
	}
	
	public void setSchool(School school){
		if(this.canHaveAsSchool(school)){
			this.school=school;
		}
	}
	
	public School getSchool(){
		return this.school;
	}
	
	public boolean canHaveAsSchool(School school){
		return (school==null || school.canHaveAsSlime(this));
	}
	
	private School school;

	@Override
	protected void collisionReaction(int index) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public boolean isPossibleInitialHorizontalVelocity(double initialHorizontalVelocity) {
		return initialHorizontalVelocity==0;
	}
}


