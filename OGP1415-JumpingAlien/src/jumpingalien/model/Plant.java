package jumpingalien.model;

import jumpingalien.util.Sprite;
import jumpingalien.util.Util;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of Plants. A plant is a non player object which is friendly to Mazub and restores Mazub's hit points upon contact.
 * 
 * @version	1.0
 * @authors Pieter Van Damme and Lennert Vanmunster
 *
 *
 */
public class Plant extends GameObject {
	
	/**
	 * Initialize a new plant with given horizontal and vertical location and the given array of sprites. 
	 * @param 	horizontalLocation
	 * 			The horizontal location for this new plant.
	 * @param 	verticalLocation
	 * 			The vertical location for this new plant.
	 * @param 	images
	 * 			An array of sprites.
	 * @effect	This new plant is initialized as a game object with the given horizontal location, vertical location, a horizontal velocity of equal to the velocity constant of all plants, 
	 * 			a vertical velocity of zero, an initial horizontal velocity equal to the velocity constant of all plants, a maximum horizontal velocity equal to the velocity constant of all plants, 
	 * 			an initial vertical velocity of zero, a horizontal acceleration of zero, a false ducking state, 
	 * 			a number of hit points of 1, the maximum number of hit points for all plants and an image array containing its sprites.
	 */
	@Raw
	public Plant(int horizontalLocation, int verticalLocation, Sprite... images)
	throws IllegalArgumentException{
		super(horizontalLocation, verticalLocation, VELOCITYCONSTANT, 0, VELOCITYCONSTANT, VELOCITYCONSTANT, 0, 0, false, 1, MAX_HIT_POINTS, images);
	}
	
	/**
	 * Check whether this plant can have the given horizontal velocity as its horizontal velocity.
	 * @param 	horizontalVelocity
	 * @return	True if the absolute value of the given horizontal velocity is equal to the velocity constant of all plants.
	 * 			| result== Util.fuzzyEquals(Math.abs(horizontalVelocity), VELOCITYCONSTANT)
	 */
	public boolean canHaveAsHorizontalVelocity(double horizontalVelocity){
		return Util.fuzzyEquals(Math.abs(horizontalVelocity), VELOCITYCONSTANT);
	}
	
	/**
	 * @return	The given initial horizontal velocity is equal to the velocity constant of all plants.
	 * 			| result == (intialHorizontalVelocity==VELOCITYCONSTANT)
	 */
	@Override
	public boolean isPossibleInitialHorizontalVelocity(double initialHorizontalVelocity) {
		return initialHorizontalVelocity==VELOCITYCONSTANT;
	}
	
	/**
	 * Check whether this plant can have the given ducking state as its ducking state.
	 * @return 	The given ducking state is false.
	 * 			|result== (ducking==false)
	 */
	public boolean canHaveAsDuckingState(boolean ducking){
		return !ducking;
	}
	
	/**
	 * Returns the vertical acceleration of this game object.
	 * 
	 * @return	Zero.
	 *			| result == 0
	 */
	@Raw
	public double getVerticalAcceleration(){
			return 0;
	}
	
	/**
	 * Check whether this plant can have the given vertical acceleration as its vertical acceleration.
	 * @return	The given vertical acceleration is equal to zero.
	 * 			|	result== verticalAcceleration==0
	 */
	public boolean canHaveAsVerticalAcceleration(double verticalAcceleration){
		return verticalAcceleration==0;
	}
	
	
	/**
	 * Variable registering the velocity constant for the horizontal velocity of all plants.
	 */
	private static final double VELOCITYCONSTANT = 0.5;
	
	/**
	 * Check whether the given number of images is a valid number of images
	 * for the class Plant.
	 * 
	 * @param	nbImages
	 * 			The given number of images.
	 * @return	|result == (nbImages == 2); 
	 */
	@Override
	public boolean canHaveAsNbImages(int nbImages){
		return nbImages == 2;
	}
	
	/**
	 * Update the location and velocity of this Plant.
	 * 
	 * @param 	deltaTime
	 * 			The period of time that is used to update this Plant.
	 * @effect	The horizontal location is updated
	 * 			and the horizontal velocity is updated.
	 * 			|updateHorizontalLocation(deltaTime)
	 *			|updateHorizontalVelocity();
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
		if(Util.fuzzyLessThanOrEqualTo(getTimeSinceStartAction(), 0.5))
			this.setTimeSinceStartAction(this.getTimeSinceStartAction() + deltaTime);
		else{
			this.updateHorizontalVelocity();
		}
		double deltaTimeForPixel=0;
		double sumDeltaTimeForPixel=0;
		while (sumDeltaTimeForPixel<deltaTime){
			deltaTimeForPixel= getDeltaTimeForPixel(deltaTime);
			updateHorizontalLocation(deltaTimeForPixel);
			sumDeltaTimeForPixel+=deltaTimeForPixel;
		}	
	}
	
	/**
	 * Update the horizontal location of this plant.
	 * 
	 * @param 	deltaTimeForPixel
	 * 			The period of time that is used to update this Plant.
	 * 
	 */
	private void updateHorizontalLocation(double deltaTimeForPixel){
		double oldHorizontalLocation = this.getHorizontalLocation();
		double newHorizontalLocation = this.getHorizontalLocation() + 
				100*(this.getHorizontalVelocity()*deltaTimeForPixel + 
				this.getDirection().getNumberForCalculations()*0.5*getHorizontalAcceleration()*Math.pow(deltaTimeForPixel, 2));
		try{
			this.setHorizontalLocation(newHorizontalLocation);
		} catch(IllegalLocationException exc){
			this.updateHorizontalVelocity();
			this.setHorizontalLocation((int) oldHorizontalLocation);
		}
	}
	
	/**
	 * Update the horizontal velocity of this plant.
	 * 
	 * @effect	|setTimeSinceStartMove(0);
	 * @effect	|setNewDirection();
	 * @effect	|setHorizontalVelocity(this.getDirection().getNumberForCalculations() * VELOCITYCONSTANT);
	 */
	private void updateHorizontalVelocity(){
		this.setTimeSinceStartAction(0);
		this.setNewDirection();
		this.setHorizontalVelocity(this.getDirection().getNumberForCalculations() * VELOCITYCONSTANT);
	}
	
	/**
	 * Set the new direction for this plant.
	 * 
	 * @post	|if(this.getDirection() == Direction.LEFT)
	 *			|	new.getDirection == Direction.RIGHT	
	 *			|else if(this.getDirection() == Direction.RIGHT)
	 *			|	new.getDirection == Direction.LEFT;
	 */
	private void setNewDirection(){
		if(this.getDirection() == Direction.LEFT)
			this.setDirection(Direction.RIGHT);
		else if(this.getDirection() == Direction.RIGHT)
			this.setDirection(Direction.LEFT);
	}
	
	/**
	 * Variable registering the maximum number of hitpoints for plants.
	 */
	private final static int MAX_HIT_POINTS=1;

	@Override
	protected void collisionReaction(int index) {
		GameObject gameObject = this.getWorld().getGameObjectAtIndex(index);
		if(gameObject instanceof Mazub){
			if(gameObject.getHitPoints() != gameObject.getMaxHitPoints()){
				this.setHitPoints(0);
				this.unsetWorld();
				gameObject.addHitPoints(50);
			}
		}
	}
	
	@Override
	public void checkWaterContact(double deltaTime) {
		
	}

	@Override
	public void checkMagmaContact(double deltaTime) {
		
	}

	
	

	
}
