package jumpingalien.model;

import jumpingalien.util.Sprite;
import jumpingalien.util.Util;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of Plants.
 * 
 * @version	1.0
 * @authors Pieter Van Damme and Lennert Vanmunster
 *
 *
 */
public class Plant extends GameObject {
	
	@Raw
	public Plant(int horizontalLocation, int verticalLocation, Sprite... images)
	throws IllegalArgumentException{
		this.setHorizontalLocation(horizontalLocation);
		this.setVerticalLocation(verticalLocation);
		this.setImages(images);
		this.setHorizontalVelocity(0);
		this.setVerticalVelocity(0);
		this.setMaxHitPoints(MAX_HIT_POINTS);
		this.setHitPoints(1);
	}
	
	/**
	 * Check whether the given vertical velocity is a valid vertical velocity
	 * for the class of plants.
	 * 
	 * @param	verticalVelocity
	 * 			The given vertical velocity
	 * @return	|Util.fuzzyEquals(verticalVelocity, 0)
	 */
	@Override
	public boolean isValidVerticalVelocity(double verticalVelocity){
		return Util.fuzzyEquals(verticalVelocity, 0);
	}
	
	/**
	 * Check whether the given horizontal velocity is a valid horizontal velocity
	 * for the class of plants.
	 * 
	 * @param	horizontalVelocity
	 * 			The given horizontal velocity
	 * @return	|result == Util.fuzzyEquals(Math.abs(horizontalVelocity), VELOCITYCONSTANT)
				|	||Util.fuzzyEquals(Math.abs(horizontalVelocity), 0);
	 * 
	 */
	@Override
	public boolean canHaveAsHorizontalVelocity(double horizontalVelocity) {
		return Util.fuzzyEquals(Math.abs(horizontalVelocity), VELOCITYCONSTANT)
				||Util.fuzzyEquals(Math.abs(horizontalVelocity), 0);
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
	 * @return	|result == nbImages == 2; 
	 */
	@Override
	public boolean isValidNbImages(int nbImages){
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
		if(Util.fuzzyLessThanOrEqualTo(getTimeSinceStartMove(), 0.5))
			this.setTimeSinceStartMove(this.getTimeSinceStartMove() + deltaTime);
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
		this.setTimeSinceStartMove(0);
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
