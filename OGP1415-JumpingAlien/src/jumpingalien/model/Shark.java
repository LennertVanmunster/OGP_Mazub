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
	 * Initialize a new shark with given horizontal and vertical location,
	 * initial and maximum horizontal velocity and an array of sprites. 
	 * @param 	horizontalLocation
	 * 			The horizontal location for this new shark.
	 * @param 	verticalLocation
	 * 			The vertical location for this new shark.
	 * @param 	initialHorizontalVelocity
	 * 			The initial horizontal velocity for this new shark.
	 * @param 	maximumHorizontalVelocity
	 * 			The maximum horizontal velocity for this new shark.
	 * @param 	images
	 * 			An array of sprites.
	 * @effect 	This new shark is initialized as a game object with the given horizontal location, vertical location, a horizontal velocity of zero, a vertical velocity of zero,
	 * 			the given initial horizontal velocity, the given maximum horizontal velocity, 
	 * 			the initial vertical velocity for all sharks, the horizontal acceleration for all sharks, a false ducking state, 
	 * 			a number of hit points of 100, the maximum number of hit points for all sharks and an image array containing its sprites.
	 * 			| super(horizontalLocation, verticalLocation, 0, 0, initialHorizontalVelocity, maximumHorizontalVelocity, INITIAL_VERTICAL_VELOCITY, HORIZONTAL_ACCELERATION, false, 100, MAX_HIT_POINTS, images)
	 */
	@Raw
	public Shark(int horizontalLocation, int verticalLocation, double initialHorizontalVelocity,
			double maximumHorizontalVelocity,  Sprite... images){
		super(horizontalLocation, verticalLocation, 0.1, 0, initialHorizontalVelocity, maximumHorizontalVelocity, INITIAL_VERTICAL_VELOCITY, HORIZONTAL_ACCELERATION, false, 100, MAX_HIT_POINTS, images);
	}
	
	/**
	 * Initialize a new shark with the given horizontal and vertical location, an initial horizontal velocity of zero,
	 * a maximum horizontal velocity of 4 and an array of sprites.
	 * @param 	horizontalLocation
	 * 			The horizontal location for this new shark.
	 * @param 	verticalLocation
	 * 			The vertical location for this new shark.
	 * @param 	images
	 * 			An array of sprites.
	 * @effect	This new shark is initialized with the given horizontal and vertical location, an initial horizontal velocity of zero,
	 *			 a maximum horizontal velocity of 4 and the given array of sprites.
	 */
	@Raw
	public Shark(int horizontalLocation, int verticalLocation,  Sprite... images){
		this(horizontalLocation, verticalLocation, 0, 4, images);
	}
	
	/**
	 * Check whether this shark can have the given horizontal velocity as its horizontal velocity.
	 * @param 	horizontalVelocity
	 * @return	True if the absolute value of the given horizontal velocity is equal to zero or greater than or equal to the initial horizontal velocity of this shark
	 * 			and less than or equal to the maximum horizontal velocity of this game object.
	 * 			| result== Util.fuzzyGreaterThanOrEqualTo(horizontalVelocity,this.getInitialHorizontalVelocity()) 
	 *			&& Util.fuzzyLessThanOrEqualTo(horizontalVelocity, this.getMaximumHorizontalVelocity())
	 *			|| Util.fuzzyEquals(horizontalVelocity, 0)
	 */
	public boolean canHaveAsHorizontalVelocity(double horizontalVelocity){
		horizontalVelocity = Math.abs(horizontalVelocity);
		return Util.fuzzyGreaterThanOrEqualTo(horizontalVelocity,this.getInitialHorizontalVelocity()) 
				&& Util.fuzzyLessThanOrEqualTo(horizontalVelocity, this.getMaximumHorizontalVelocity())
				|| Util.fuzzyEquals(horizontalVelocity, 0);
	}
	
	/**
	 * Check whether the given initial horizontal velocity is a possible initial horizontal velocity for any shark.
	 * @return	True if the given initial horizontal velocity is greater than or equal to 0.
	 * 			result == Util.fuzzyGreaterThanOrEqualTo(initialHorizontalVelocity, 0)
	 */
	public boolean isPossibleInitialHorizontalVelocity(double initialHorizontalVelocity){
		return Util.fuzzyGreaterThanOrEqualTo(initialHorizontalVelocity, 0);
	}
	
	/**
	 *  Constant registering the initial vertical velocity of all sharks.
	 */
	private static final double INITIAL_VERTICAL_VELOCITY = 2;
	
	/**
	 * Returns the vertical acceleration of this shark.
	 * @return	If this shark is currently only in contact with water, the vertical acceleration is zero.
	 * 			Otherwise the vertical acceleration is equal to the gravitational constant.
	 * 			|if(this.checkWaterAndNoAirContact())
	 *			|	result == 0
	 *			|else
	 *			| 	result == VERTICAL_ACCELERATION
	 */
	@Raw
	public double getVerticalAcceleration(){
		if(checkWaterAndNoAirContact() )
			return 0;
		else
			return VERTICAL_ACCELERATION;
	}
	
	/**
	 * Check whether this shark can have the given vertical acceleration as its vertical acceleration.
	 * @return	The absolute value of the given vertical acceleration is less than the maximum floating vertical acceleration or the given vertical acceleration
	 * 			is equal to the gravitational constant.
	 * 			|	Util.fuzzyLessThanOrEqualTo(Math.abs(verticalAcceleration),MAXIMUM_FLOATING_VERTICAL_ACCELERATION) || Util.fuzzyEquals(verticalAcceleration,VERTICAL_ACCELERATION)
	 */
	public boolean canHaveAsVerticalAcceleration(double verticalAcceleration){
		return Util.fuzzyLessThanOrEqualTo(Math.abs(verticalAcceleration),MAXIMUM_FLOATING_VERTICAL_ACCELERATION) || Util.fuzzyEquals(verticalAcceleration,VERTICAL_ACCELERATION);
	}
	
	/**
	 * Constant registering the horizontal acceleration of all sharks.
	 */
	private static final double HORIZONTAL_ACCELERATION=1.5;
	
	/**
	 * Constant registering the maximum vertical acceleration while floating of all sharks.
	 */
	private static final double MAXIMUM_FLOATING_VERTICAL_ACCELERATION=0.2;
	
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
	 * @return	|result == nbImages == 2; 
	 */
	@Override
	public boolean canHaveAsNbImages(int nbImages){
		return nbImages == 2;
	}
	
	/**
	 * Return the time since the start of the air contact of this shark.
	 * The time is reset when the shark loses hit  points or no longer makes contact.
	 */
	public double getTimeSinceStartAirContact(){
		return this.timeSinceAirContact;
	}
	
	/**
	 * Set the time since the start of the air contact of this game object.
	 * 
	 * @param	time
	 * 			The given time.
	 * @throws	IllegalArgumentException
	 * 			|!isValidTimeSinceMove(time)
	 * 			
	 */
	public void setTimeSinceStartAirContact(double time)
	throws IllegalArgumentException{
		if(!isValidTimeSinceAction(time))
			throw new IllegalArgumentException();
		this.timeSinceAirContact = time;
			
	}
	
	/**
	 * Variable registering the time since this game object is making contact with water.
	 */
	private double timeSinceAirContact = 0;
	
	private void startNewAction(){
		this.lastJump += 1;
		Random r = new Random();
		this.setHorizontalVelocity(0);
		this.setCurrentActionDuration(MINIMUM_ACTION_DURATION+(MAXIMUM_ACTION_DURATION-MINIMUM_ACTION_DURATION)*r.nextDouble());
		this.setDirection(r.nextBoolean() ? Direction.LEFT : Direction.RIGHT);
		this.setTimeSinceStartAction(0);
		if(lastJump >= 4){
				this.setVerticalVelocity(INITIAL_VERTICAL_VELOCITY);
				this.lastJump = 0;
		}
	}
	
	/**
	 * Set the current action duration of this slime to the given duration. This is how long the current action of this shark lasts.
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
	private static final double MINIMUM_ACTION_DURATION=1;
	
	/**
	 * Constant registering the maximum action duration.
	 */
	private static final double MAXIMUM_ACTION_DURATION=4;
	
	private int lastJump = 2; 
	
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
			int [][] overlappingGameObjects = this.checkLeftRightTopBottomSideOverlap();
			for(int [] overlap : overlappingGameObjects)
				if(overlap[0]==1)
					collisionReaction(overlap[1],overlap[2]);
		}
		this.setTimeSinceStartAction(this.getTimeSinceStartAction()+deltaTime);
		checkAirContact(deltaTime);
		checkMagmaContact(deltaTime);
		if(this.getHitPoints() <= 0)
			this.unsetWorld();
	}
	
	protected void collisionReaction(int index1, int index2) {
		GameObject gameObject = this.getWorld().getGameObjectAtIndex(index1);
		if(gameObject instanceof Shark){
			this.setHorizontalVelocity(0);
			gameObject.setHorizontalVelocity(0);
			if(this.getVerticalVelocity() > 0 && this.getVerticalLocation() - gameObject.getVerticalLocation() < 0)
				this.setVerticalVelocity(-this.getVerticalVelocity());
			else if(this.getVerticalVelocity() < 0 && this.getVerticalLocation() - gameObject.getVerticalLocation() > 0)
				try{
					this.setVerticalVelocity(-this.getVerticalVelocity());
				}catch (IllegalArgumentException exc){
					this.setVerticalVelocity(2);
				}
			if (this.getDirection()==Direction.LEFT && this.getHorizontalLocation() - gameObject.getHorizontalLocation() > 0){
				this.setDirection(Direction.RIGHT);
			}
			else if (this.getDirection()==Direction.RIGHT && this.getHorizontalLocation() - gameObject.getHorizontalLocation() < 0){
				this.setDirection(Direction.LEFT);
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
		else
			return false;
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
	 * Variable registering the maximum number of hit points of a shark.
	 */
	private final static int MAX_HIT_POINTS=100;
}
