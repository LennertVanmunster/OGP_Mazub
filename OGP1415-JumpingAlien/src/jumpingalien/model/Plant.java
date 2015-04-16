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
	}
	
	public boolean isValidVerticalVelocity(double verticalVelocity){
		return Util.fuzzyEquals(verticalVelocity, 0);
	}
	
	public boolean canHaveAsHorizontalVelocity(double horizontalVelocity) {
		return Util.fuzzyEquals(Math.abs(horizontalVelocity), VELOCITYCONSTANT)
				||Util.fuzzyEquals(Math.abs(horizontalVelocity), 0);
	}
	
	private static final double VELOCITYCONSTANT = 0.5;
	
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
	 *			|updateHorizontalVelocity(deltaTime);
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
			this.setTimeSinceStartMove(0);
			this.setNewDirection();
			this.setHorizontalVelocity(this.getDirection().getNumberForCalculations() * VELOCITYCONSTANT);
		}
		double deltaTimeForPixel=0;
		double sumDeltaTimeForPixel=0;
		while (sumDeltaTimeForPixel<deltaTime){
			deltaTimeForPixel= getDeltaTimeForPixel(deltaTime);
			updateHorizontalLocation(deltaTimeForPixel);
			sumDeltaTimeForPixel+=deltaTimeForPixel;
		}	
	}
	
	private void updateHorizontalLocation(double deltaTimeForPixel){
		double oldHorizontalLocation = this.getHorizontalLocation();
		double newHorizontalLocation = this.getHorizontalLocation() + 
				100*(this.getHorizontalVelocity()*deltaTimeForPixel + 
				this.getDirection().getNumberForCalculations()*0.5*getHorizontalAcceleration()*Math.pow(deltaTimeForPixel, 2));
		try{
			this.setHorizontalLocation(newHorizontalLocation);
			oldHorizontalLocation=newHorizontalLocation;
		} catch(IllegalLocationException exc){
			this.setHorizontalLocation((int) oldHorizontalLocation);
			this.setHorizontalVelocity(0);
		}
	}
	
	private void setNewDirection(){
		if(this.getDirection() == Direction.LEFT)
			this.setDirection(Direction.RIGHT);
		else if(this.getDirection() == Direction.RIGHT)
			this.setDirection(Direction.LEFT);
	}
	
	
	
}
