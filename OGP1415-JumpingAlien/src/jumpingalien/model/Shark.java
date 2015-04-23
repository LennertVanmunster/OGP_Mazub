package jumpingalien.model;

import jumpingalien.util.Sprite;
import jumpingalien.util.Util;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of sharks
 * 
 * @version	1.0
 * @authors Pieter Van Damme and Lennert Vanmunster
 *
 */
public class Shark extends GameObject{

	@Raw
	public Shark(int horizontalLocation, int verticalLocation, double initialHorizontalVelocity,
			double maximumHorizontalVelocity,  Sprite... images)
	throws IllegalArgumentException{
		super(horizontalLocation, verticalLocation, 0, 0, initialHorizontalVelocity, maximumHorizontalVelocity, INITIAL_VERTICAL_VELOCITY, HORIZONTAL_ACCELERATION, 100, MAX_HIT_POINTS, images);
	}
	

	@Raw
	public Shark(int horizontalLocation, int verticalLocation,  Sprite... images){
		this(horizontalLocation, verticalLocation, 0, 4, images);
	}
	
	
	
	/**
	 * Check whether the given initial horizontal velocity is a possible initial horizontal velocity for any shark.
	 * 
	 * @param 	initialHorizontalVelocity
	 * 			The initial horizontal velocity to check.
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
	 * Returns the vertical acceleration of this game object.
	 * 
	 */
	@Raw
	public double getVerticalAcceleration(){
		if(checkWaterAndNoAirContact())
			return 0;
		else
			return super.getVerticalAcceleration();
	}
	
	private static final double HORIZONTAL_ACCELERATION=1.5;
	
	/**
	 * Check whether the given number of images is a valid number of images
	 * for the class shark.
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
				if (Math.abs(newHorizontalVelocity)<Math.abs(this.getInitialHorizontalVelocity()))
					this.setHorizontalVelocity(this.getDirection().getNumberForCalculations()*this.getInitialHorizontalVelocity());
				else
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
				collisionReaction(overlap[1]);
					
			}		
			}
		checkMagmaContact(deltaTime);	
//		this.calculateNewJumpingState();
		if(this.getHitPoints() <= 0)
			this.unsetWorld();
	}
	
	/**
	 * Check whether the game object makes contact with water and no contact with air
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
	
	protected void collisionReaction(int index) {
		GameObject gameObject = this.getWorld().getGameObjectAtIndex(index);
		if(gameObject instanceof Mazub){
			//assert 1==2;
			if(this.getHitPoints() != this.getMaxHitPoints()){
				this.removeHitPoints(50);
			}
		}
	}

	/**
	 * Variable registering the maximum number of hit points of a shark.
	 */
	private final static int MAX_HIT_POINTS=100;
}
