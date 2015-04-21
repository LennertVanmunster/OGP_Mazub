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
		setHorizontalLocation(horizontalLocation);
		setVerticalLocation(verticalLocation);
		this.setImages(images);
		this.setSchool(school);
	}
	
	
	/**
	 * Return the effective horizontal location of this Slime as an integer number.
	 */
	@Raw
	public int getEffectiveHorizontalLocation(){
		return (int) Math.floor(this.getHorizontalLocation());
	}
	
	/**
	 * Return the effective vertical location of this Slime as an integer number.
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
	 * @return	True if and only if the given horizontal location is greater than or equal to 0
	 * 			and smaller than the maximum horizontal location plus one.
	 * 			| result == ((horizontalLocation >= 0 ) && (horizontalLocation < maximumHorizontalLocation+1))
	 */
	@Raw
	public boolean canHaveAsHorizontalLocation(double horizontalLocation){
		if (this.getWorld()==null){
			return true;
		}
		else if ((horizontalLocation < 0 ) || (horizontalLocation >= this.getWorld().getWorldWidth()+1)){
			return false;
		}
		else{
			boolean bottomOverlap=false;
			int [][] bottomPerimeter = this.getBottomPerimeterOfGameObject((int) horizontalLocation, this.getEffectiveVerticalLocation(), this.getWidth());
			for (int X=bottomPerimeter[0][0]; X<bottomPerimeter.length;X++){
				if (this.getWorld().getGeologicalFeatureAtPixel(X, bottomPerimeter[0][1])==1){
					bottomOverlap=true;
				}
			}
			return (!this.getWorld().areaCoincidesWithTerrain((int) horizontalLocation, 
					this.getEffectiveVerticalLocation()+1, this.getWidth()-1, this.getHeight()-2)[1])
					&& bottomOverlap==true;
			
		}
	}
	
	
	/**
	 * Check whether the given vertical location is a valid vertical location.
	 * 
	 * @param 	verticalLocation
	 * 		  	The vertical location to be checked.
	 * @return	True if and only if the given vertical location is greater than or equal to 0
	 * 			and smaller than the maximums vertical location plus one.
	 * 			|result == ((verticalLocation >= 0 ) && (verticalLocation < maximumVerticalLocation+1))
	 */
	@Raw
	public boolean canHaveAsVerticalLocation(double verticalLocation){
		if (this.getWorld()==null){
			return true;
		}
		else if (verticalLocation<0 || verticalLocation >= this.getWorld().getWorldHeight()+1){
			return false;
		}
		else{
			boolean bottomOverlap=false;
			int [][] bottomPerimeter = this.getBottomPerimeterOfGameObject(this.getEffectiveHorizontalLocation(), (int) verticalLocation, this.getWidth());
			for (int X=bottomPerimeter[0][0]; X<bottomPerimeter.length;X++){
				if (this.getWorld().getGeologicalFeatureAtPixel(X, bottomPerimeter[0][1])==1){
					bottomOverlap=true;
				}
			}
			return !this.getWorld().areaCoincidesWithTerrain(this.getEffectiveHorizontalLocation(), 
					(int) verticalLocation+1, this.getWidth()-1, this.getHeight()-2)[1] && bottomOverlap==true;
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
	 * Return the calculated horizontal location of this Mazub.
	 */
	@Basic
	@Raw
	public double getHorizontalLocation(){
		return this.horizontalLocation;
	}
	
	/**
	 * Return the calculated vertical location of this Mazub.
	 */
	@Basic
	@Raw
	public double getVerticalLocation(){
		return this.verticalLocation;
	}
	
	/**
	 * Set the calculated horizontal location of this Mazub to the given location.
	 * 
	 * @param 	horizontalLocation
	 * 		  	The new calculated horizontal location for this mazub.
	 * @post  	The new calculated horizontal location of this mazub is set to the given calculated horizontal location.
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
	 * Set the calculated vertical location of Mazub to the given location.
	 * 
	 * @param 	verticalLocation
	 * 		  	The new calculated vertical location.
	 * @post  	The new calculated vertical location of this Mazub is set to the given vertical location.
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
	 * Variable registering the calculated horizontal location of this Mazub.
	 */
	private double horizontalLocation = 0;
	
	/**
	 * Variable registering the calculated  vertical location of this Mazub.
	 */
	private double verticalLocation = 0;
	
	/**
	 * Return the maximum horizontal velocity when not ducking of this Mazub.
	 * 
	 * @return	If the ducking state of this Mazub is true, the maximum horizontal velocity of this Mazub is equal to 1.
	 * 			Otherwise the maximum horizontal velocity of this Mazub is equal to its maximum horizontal velocity when not ducking.
	 * 			|if (this.isDucking())
	 * 			|	result==1
	 * 			|else
	 * 			| 	result==this.getMaximumHorizontalVelocityNotDucking()
	 */
	@Raw
	public static double getMaximumHorizontalVelocity(){
			return MAXIMUM_HORIZONTAL_VELOCITY;
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
	 * Set the horizontal velocity of Mazub to the given horizontal velocity.
	 * 
	 * @param 	horizontalVelocity
	 * 			The horizontal velocity.
	 * @post	The given horizontal velocity is set as the new horizontal velocity
	 * 			of Mazub.
	 * 			|new.getHorizontalVelocity() = horizontalVelocity
	 * @throws	IllegalArgumentException
	 * 			The given horizontal velocity is not a valid horizontal velocity.
	 * 			|!this.canHaveAsHorizontalVelocity(horizontalVelocity)
	 */
	@Raw
	public void setHorizontalVelocity(double horizontalVelocity) 
		throws IllegalArgumentException{
		if(!canHaveAsHorizontalVelocity(horizontalVelocity))
			throw new IllegalArgumentException("Not a valid horizontal velocity!");
		this.horizontalVelocity = horizontalVelocity;
	}
	
	
	/**
	 * Check whether the given horizontal velocity is a valid horizontal velocity.
	 * 
	 * @param 	horizontalVelocity
	 * 			The horizontal velocity to be checked.
	 * @return	True if and only if either the absolute value of the given horizontal velocity is greater than or equal
	 * 			to the initial horizontal velocity of this Mazub and is less than or equal to the maximum horizontal
	 * 			velocity of this Mazub or the given horizontal velocity is equal to zero.
	 * 			|result == (Util.fuzzyGreaterThanOrEqualTo(abs(horizontalVelocity),this.getInitialHorizontalVelocity()) 
				|  && Util.fuzzyLessThanOrEqualTo(abs(horizontalVelocity), this.getMaximumHorizontalVelocity()))
				|  || Util.fuzzyEquals(horizontalVelocity, 0);
	 */
	@Raw
	public boolean canHaveAsHorizontalVelocity(double horizontalVelocity){
		horizontalVelocity = Math.abs(horizontalVelocity);
		return Util.fuzzyGreaterThanOrEqualTo(horizontalVelocity,0) 
				&& Util.fuzzyLessThanOrEqualTo(horizontalVelocity, getMaximumHorizontalVelocity());
	}
	
	@Override
	public double getVerticalVelocity(){
		return 0;
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
		if (!this.isMovingHorizontally() || 
				Util.fuzzyEquals(Math.abs(this.getHorizontalVelocity()), getMaximumHorizontalVelocity())){
			return 0;
		}
		else{
			return HORIZONTAL_ACCELERATION;
		}
	}
	
	public double getVerticalAcceleration(){
		return 0;
	}

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
	

	private double timeSinceStartAction = 0;
	
	public double getTimeSinceStartAction() {
		return this.timeSinceStartAction;
	}


	public void setTimeSinceStartAction(double timeSinceStartAction) {
		this.timeSinceStartAction = timeSinceStartAction;
	}

	private double currentActionDuration=0;
	
	
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
		double newHorizontalVelocity=this.getHorizontalVelocity();
		double oldHorizontalLocation=this.getHorizontalLocation();
		if(this.getTimeSinceStartAction()>=this.getCurrentActionDuration()){
			this.startNewAction();
		}
		while (sumDeltaTimeForPixel<deltaTime){
			deltaTimeForPixel= getDeltaTimeForPixel(deltaTime);
			newHorizontalVelocity = this.getHorizontalVelocity() + this.getDirection().getNumberForCalculations()*getHorizontalAcceleration()*deltaTimeForPixel;
			newHorizontalLocation = this.getHorizontalLocation() + 
					100*(this.getHorizontalVelocity()*deltaTimeForPixel + 
					this.getDirection().getNumberForCalculations()*0.5*getHorizontalAcceleration()*Math.pow(deltaTimeForPixel, 2));
			sumDeltaTimeForPixel+=deltaTimeForPixel;
			try{
				this.setHorizontalVelocity(newHorizontalVelocity);
			} catch(IllegalArgumentException exc){
				this.setHorizontalVelocity(this.getDirection().getNumberForCalculations()*getMaximumHorizontalVelocity());
			}
			try{
				this.setHorizontalLocation(newHorizontalLocation);
				oldHorizontalLocation=newHorizontalLocation;
			} catch(IllegalLocationException exc){
				this.setHorizontalLocation((int) oldHorizontalLocation);
				this.setHorizontalVelocity(-this.getHorizontalVelocity());
			}	
		}
		checkWaterContact(deltaTime);
		checkMagmaContact(deltaTime);
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
		if (this.getDirection()==Direction.LEFT)
			newSpriteIndex=0;
		else{
			newSpriteIndex=1;
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
}


