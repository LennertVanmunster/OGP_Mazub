package jumpingalien.model;

import java.util.List;

import jumpingalien.programs.program.Program;
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
	public Plant(int horizontalLocation, int verticalLocation, Program program, Sprite... images) throws IllegalArgumentException{
		super(horizontalLocation, verticalLocation, velocityConstant, 0, velocityConstant, velocityConstant, 0, 0, false, HIT_POINTS, HIT_POINTS, program, images);
	}
	
	public Plant(int horizontalLocation, int verticalLocation, Sprite... images) throws IllegalArgumentException{
		super(horizontalLocation, verticalLocation, velocityConstant, 0, velocityConstant, velocityConstant, 0, 0, false, HIT_POINTS, HIT_POINTS, null, images);
	}
	
	
	/**
	 * Return the velocity constant of plants.
	 */
	@Basic
	@Raw
	@Immutable
	public static double getVelocityConstant(){
		return velocityConstant;
	}
	
	/**
	 * Check if the given velocity constant is a valid velocity constant for all plants.
	 * @param 	velocityConstant
	 * 			The velocity to be checked.
	 * @return	The given velocity is greater than zero.
	 * 			|result==(velocityConstant>0)
	 */
	@Raw
	public static boolean isValidVelocityConstant(double velocityConstant){
		return velocityConstant>0;
	}
	
	/**
	 * Check whether this plant can have the given horizontal velocity as its horizontal velocity.
	 * @param 	horizontalVelocity
	 * @return	True if the absolute value of the given horizontal velocity is equal to the velocity constant of all plants or it is equal to zero.
	 * 			| result== Util.fuzzyEquals(Math.abs(horizontalVelocity), getVelocityConstant()) || Util.fuzzyEquals(horizontalVelocity, 0
	 */
	@Raw
	@Override
	public boolean canHaveAsHorizontalVelocity(double horizontalVelocity){
		return Util.fuzzyEquals(Math.abs(horizontalVelocity), getVelocityConstant())
				|| Util.fuzzyEquals(horizontalVelocity, 0);
	}
	
	/**
	 * Check whether the given initial horizontal velocity is a possible initial horizontal velocity for plants.
	 * @return	The given initial horizontal velocity is equal to the velocity constant all plants and the velocity constant is valid.
	 * 			| result == (initialHorizontalVelocity==getVelocityConstant() && isValidVelocityConstant(getVelocityConstant()))
	 */
	@Raw
	@Override
	public boolean isPossibleInitialHorizontalVelocity(double initialHorizontalVelocity) {
		return initialHorizontalVelocity==getVelocityConstant() && isValidVelocityConstant(getVelocityConstant());
	}
	
	
	/**
	 * Check whether the given maximum horizontal velocity is a possible maximum horizontal velocity for plants.
	 * @return	The given maximum horizontal velocity is equal to the velocity constant all plants and the velocity constant is valid.
	 * 			| result == (maximumHorizontalVelocity==getVelocityConstant() && isValidVelocityConstant(getVelocityConstant()))
	 */
	@Raw
	@Override
	public boolean isPossibleMaximumHorizontalVelocity(double maximumHorizontalVelocity) {
		return maximumHorizontalVelocity==getVelocityConstant() && isValidVelocityConstant(getVelocityConstant());
	}
	
	/**
	 * Return the initial horizontal velocity of this plant for use in update calculations.
	 * 
	 * @return	The regular initial horizontal velocity of plants.
	 * 			| result==this.getInitialHorizontalVelocity()
	 */
	@Raw
	@Override
	public double getInitialHorizontalVelocityForUpdate(){
		return this.getInitialHorizontalVelocity();
	}
	
	/**
	 * Return the maximum horizontal velocity of this plant for use in update calculations.
	 * 
	 * @return	The regular maximum horizontal velocity of plants.
	 * 			| result==this.getMaximumHorizontalVelocity()
	 */
	@Raw
	@Override
	public double getMaximumHorizontalVelocityForUpdate(){
		return this.getMaximumHorizontalVelocity();
	}
	
	/**
	 * Check whether this plant can have the given ducking state as its ducking state.
	 * @return 	The given ducking state is false.
	 * 			|result== (ducking==false)
	 */
	@Raw
	@Override
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
	@Immutable
	@Override
	public double getVerticalAcceleration(){
			return 0;
	}
	
	/**
	 * Check whether this plant can have the given vertical acceleration as its vertical acceleration.
	 * @return	The given vertical acceleration is equal to zero.
	 * 			|	result== verticalAcceleration==0
	 */
	@Raw
	@Override
	public boolean canHaveAsVerticalAcceleration(double verticalAcceleration){
		return verticalAcceleration==0;
	}
	
	/**
	 * Check whether the given horizontal acceleration is a valid horizontal acceleration for plants.
	 * @param	horizontalAcceleration
	 * 			The horizontal acceleration to be checked.
	 * @return	The given horizontal acceleration is equal to zero or the horizontal acceleration of this game object.
	 * 			| result == (horizontalAcceleration==0 || Util.fuzzyEquals(horizontalAcceleration,this.horizontalAcceleration))
	 */
	@Raw
	@Override
	public boolean canHaveAsHorizontalAcceleration(double horizontalAcceleration){
		return horizontalAcceleration==0;
	}
	
	
	/**
	 * Variable registering the velocity constant for the horizontal velocity of all plants.
	 */
	private static final double velocityConstant = 0.5;
	
	/**
	 * Check whether the given number of images is a valid number of images
	 * for the class Plant.
	 * 
	 * @param	nbImages
	 * 			The given number of images.
	 * @return	|result == (nbImages == 2); 
	 */
	@Raw
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
		if(this.getProgram()!=null){
			this.getProgram().execute(deltaTime);
		}
		else{
			if(Util.fuzzyLessThanOrEqualTo(getTimeSinceStartAction(), 0.5))
				this.setTimeSinceStartAction(this.getTimeSinceStartAction() + deltaTime);
			else{
				this.startNewAction();
			}
		}
		double deltaTimeForPixel=0;
		double sumDeltaTimeForPixel=0;
		Mazub mazub = this.getWorld().getMazub();
		while (sumDeltaTimeForPixel<deltaTime){
			deltaTimeForPixel= getDeltaTimeForPixel(deltaTime);
			updateHorizontalLocation(deltaTimeForPixel);
			sumDeltaTimeForPixel+=deltaTimeForPixel;
		}
		
		if(mazub.getHitPoints() != mazub.getMaxHitPoints() && this.getHitPoints() != 0){
			int [] overlap = this.checkAllowedLeftRightTopBottomSideOverlap();
			if(overlap[0]==1){
				collisionReaction(overlap[1],overlap[2], overlap[3]);	
			}
		}
	}
	
	/**
	 * Execute the right actions after a collision with another game object.
	 * 
	 * @param 	index1
	 * 			The index registering the position of the other 
	 * 			game object in list of all game objects of this world.
	 * @param 	index2
	 * 			The index registering whether the bottom perimeter was 
	 * 			overlapping during the contact with the other game object.
	 * @param 	index3
	 * 			The index registering whether the top perimeter was
	 * 			overlapping during the contact with the other game object.
	 * @effect	|GameObject gameObject = this.getWorld().getGameObjectAtIndex(index1)
	 * 			|if(gameObject instanceof Mazub)
	 * 			|	if(gameObject.getHitPoints() != gameObject.getMaxHitPoints() && this.getHitPoints() != 0)
	 * 			|		this.setHitPoints(0)
	 * 			|		gameObject.addHitPoints(50)
	 */
	protected void collisionReaction(int index1, int index2, int index3) {
		GameObject gameObject = this.getWorld().getGameObjectAtIndex(index1);
		if(gameObject instanceof Mazub){
			if(gameObject.getHitPoints() != gameObject.getMaxHitPoints() && this.getHitPoints() != 0){
				this.setHitPoints(0);
				gameObject.addHitPoints(50);
			}
		}
		else if(gameObject instanceof Buzam){
			if(gameObject.getHitPoints() != gameObject.getMaxHitPoints() && this.getHitPoints() != 0){
				this.setHitPoints(0);
				gameObject.addHitPoints(50);
			}
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
				this.getDirection().getNumberForCalculations()*0.5*getHorizontalAccelerationForUpdate()*Math.pow(deltaTimeForPixel, 2));
		try{
			this.setHorizontalLocation(newHorizontalLocation);
		} catch(IllegalLocationException exc){
			this.startNewAction();
			this.setHorizontalLocation((int) oldHorizontalLocation);
		}
	}
	
	/**
	 * Start a new action for this plant. This solely consists of updating the horizontal velocity of this plant.
	 * 
	 * @effect	The time since the start of the action of this plant is set to zero.
	 * 			|setTimeSinceStartaction(0);
	 * @effect	The new direction of this plant is set.
	 * 			|setNewDirection();
	 * @effect	The new horizontal velocity of this plant is set to the opposite of its current horizontal velocity.
	 * 			|setHorizontalVelocity(this.getDirection().getNumberForCalculations() * getVelocityConstant());
	 */
	private void startNewAction(){
		this.setTimeSinceStartAction(0);
		this.setNewDirection();
		startMove(getDirection());
	}
	
	public void startMove(Orientation direction){
		setHorizontalVelocity(direction.getNumberForCalculations()*getVelocityConstant());
		setMovingHorizontally(true);
	}
	
	public void endMove(Orientation direction){
		if(direction==this.getDirection()){
			setHorizontalVelocity(0);
			setMovingHorizontally(false);
		}
	}
	
	public void startJump(){
		
	}
	
	public void endJump(){
		
	}
	
	/**
	 * Set the new direction for this plant.
	 * 
	 * @post	The new direction of this plant is equal to the opposite of its current direction.
	 * 			|if(this.getDirection() == Direction.LEFT)
	 *			|	new.getDirection == Direction.RIGHT	
	 *			|else if(this.getDirection() == Direction.RIGHT)
	 *			|	new.getDirection == Direction.LEFT;
	 */
	private void setNewDirection(){
		if(this.getDirection() == Orientation.LEFT)
			this.setDirection(Orientation.RIGHT);
		else if(this.getDirection() == Orientation.RIGHT)
			this.setDirection(Orientation.LEFT);
	}
	
	
	
	/**
	 * Constant registering the number of hit points for all plants.
	 */
	private final static int HIT_POINTS=1;

	
	@Override
	protected void checkWaterContact(double deltaTime) {
		
	}

	@Override
	protected void checkMagmaContact(double deltaTime) {
		
	}

	/**
	 * Check whether the left, right, top or bottom of this plant overlaps with another game object.
	 * 
	 * @return	The method iterates over all the game objects that can overlap with 
	 * 			this plant. That are the game objects that overlap with one of the tile with
	 * 			which this plan overlaps. If another game object is found that overlaps 
	 * 			with one of the given sides then an array is returned with at the first position
	 * 			number one defining that there is overlap, at the second position the index 
	 * 			of the game object in the world, at the third position a number registering whether 
	 * 			the bottom perimeter was overlapping during the contact with the other game object
	 * 			and an index registering whether the top perimeter was
	 * 			overlapping during the contact with the other game object.
	 * 			During the iteration is checked whether the game object at one of the overlapping
	 * 			tile is an instance of Mazub, because only in this case a plant can interact
	 * 			with the other game object.
	 * 			|overlap = {0,0,0,0}
	 * 			|for each game object in gameGameObjectAtTile of this game object
	 * 			|	if(gameObject instanceof Mazub)
	 * 			|		if(gameObject != this && gameObject != null && world.canHaveAsGameObject(gameObject))
	 * 			|			overlap = checkLeftOrRightSideOverlap(gameObject,...);
	 *			|			if(overlap [0] == 1)
	 *			|			then the array "overlap" is returned
	 *			|			or 
	 *			|			overlap = checkTopOrBottomSideOverlap(gameObject,...);
	 *			|			if(overlap [0] == 1)
	 *			|			then the array "overlap" is returned
	 *			|return overlap
	 */
	@Override
	protected int [] checkLeftRightTopBottomSideOverlap(int [][] leftPerimeter1, int [][] rightPerimeter1, int [][] topPerimeter1, int [][] bottomPerimeter1){
		int [] overlap = {0,0,0,0};
		World world = this.getWorld();
		List<GameObject> gameObjects = getGameObjectsAtTiles(world.getTilePositionsIn(getEffectiveHorizontalLocation(), getEffectiveVerticalLocation(), getEffectiveHorizontalLocation()+getWidth(), getEffectiveVerticalLocation()+getHeight()));
		for(int index = 0; index < gameObjects.size(); index++){
			GameObject gameObject = gameObjects.get(index);
			if(gameObject instanceof Mazub){
				if(gameObject != this && gameObject != null && world.canHaveAsGameObject(gameObject)){
					int [][] leftPerimeter2 = gameObject.getLeftPerimeterOfGameObject(gameObject.getEffectiveHorizontalLocation(), gameObject.getEffectiveVerticalLocation(), gameObject.getHeight());
					int [][] rightPerimeter2 = gameObject.getRightPerimeterOfGameObject(gameObject.getEffectiveHorizontalLocation(), gameObject.getEffectiveVerticalLocation(), gameObject.getWidth(), gameObject.getHeight());
					overlap = checkLeftOrRightSideOverlap(gameObject, leftPerimeter1, rightPerimeter1, leftPerimeter2, rightPerimeter2);
					if(overlap [0] == 1){
						return overlap;
					}
					int [][] topPerimeter2 = gameObject.getTopPerimeterOfGameObject(gameObject.getEffectiveHorizontalLocation(), gameObject.getEffectiveVerticalLocation(), gameObject.getWidth(), gameObject.getHeight());
					int [][] bottomPerimeter2 = gameObject.getBottomPerimeterOfGameObject(gameObject.getEffectiveHorizontalLocation(), gameObject.getEffectiveVerticalLocation(), gameObject.getWidth());
					overlap = checkTopOrBottomSideOverlap(gameObject, topPerimeter1, bottomPerimeter1, topPerimeter2, bottomPerimeter2);
					if(overlap [0] == 1){
						return overlap;
					}
				}
			}
		}
		return overlap;
		}
	

	
}
