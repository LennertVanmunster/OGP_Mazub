package jumpingalien.model;

import jumpingalien.util.Sprite;
import jumpingalien.util.Util;
import be.kuleuven.cs.som.annotate.*;

import java.lang.Math;

/**
 * A class of GameObjects.
 * 
 * @version	1.0
 * @authors Pieter Van Damme and Lennert Vanmunster
 *
 */
public abstract class GameObject {

	
	
	/**
	 * Return the effective horizontal location of this game object as an integer number.
	 */
	@Raw
	public int getEffectiveHorizontalLocation(){
		return (int) Math.floor(this.getHorizontalLocation());
	}
	
	/**
	 * Return the effective vertical location of this game object as an integer number.
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
			return !this.getWorld().areaCoincidesWithTerrain((int) horizontalLocation, this.getEffectiveVerticalLocation()+1, this.getWidth()-1, this.getHeight()-2);
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
			return !this.getWorld().areaCoincidesWithTerrain(this.getEffectiveHorizontalLocation(), (int) verticalLocation+1, this.getWidth()-1, this.getHeight()-2);
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
	 * Return the calculated horizontal location of this game object.
	 */
	@Basic
	@Raw
	public double getHorizontalLocation(){
		return this.horizontalLocation;
	}
	
	/**
	 * Return the calculated vertical location of this game object.
	 */
	@Basic
	@Raw
	public double getVerticalLocation(){
		return this.verticalLocation;
	}
	
	/**
	 * Set the calculated horizontal location of this game object to the given location.
	 * 
	 * @param 	horizontalLocation
	 * 		  	The new calculated horizontal location for this game object.
	 * @post  	The new calculated horizontal location of this game object is set to the given calculated horizontal location.
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
	 * Set the calculated vertical location of game object to the given location.
	 * 
	 * @param 	verticalLocation
	 * 		  	The new calculated vertical location.
	 * @post  	The new calculated vertical location of this game object is set to the given vertical location.
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
	 * Variable registering the calculated horizontal location of this game object.
	 */
	private double horizontalLocation = 0;
	
	/**
	 * Variable registering the calculated  vertical location of this game object.
	 */
	private double verticalLocation = 0;
	
	/**
	 * Return the horizontal velocity of this game object.
	 */
	@Basic
	@Raw 
	public double getHorizontalVelocity() {
		return this.horizontalVelocity;
	}
	
	/**
	 * Return the vertical velocity of this game object.
	 */
	@Basic 
	@Raw
	public double getVerticalVelocity() {
		return this.verticalVelocity;
	}
	
	/**
	 * Set the horizontal velocity of game object to the given horizontal velocity.
	 * 
	 * @param 	horizontalVelocity
	 * 			The horizontal velocity.
	 * @post	The given horizontal velocity is set as the new horizontal velocity
	 * 			of game object.
	 * 			|new.getHorizontalVelocity() = horizontalVelocity
	 * @throws	IllegalArgumentException
	 * 			The given horizontal velocity is not a valid horizontal velocity.
	 * 			|!this.canHaveAsHorizontalVelocity(horizontalVelocity)
	 */
	@Raw
	public void setHorizontalVelocity(double horizontalVelocity) 
		throws IllegalArgumentException{
		if(!this.canHaveAsHorizontalVelocity(horizontalVelocity))
			throw new IllegalArgumentException("Not a valid horizontal velocity!");
		this.horizontalVelocity = horizontalVelocity;
	}
	
	/**
	 * Set the vertical velocity of game object to the given vertical velocity.
	 * 
	 * @param 	verticalVelocity
	 * 			The vertical velocity to be set.
	 * @post	The given vertical velocity is set as the new vertical velocity of this game object.
	 * 			|new.getVerticalVelocity() = verticalVelocity
	 * @throws	IllegalArgumentException
	 * 			The given vertical velocity is not a valid vertical velocity.
	 * 			|!isValidVerticalVelocity(verticalVelocity)
	 */ 
	@Raw
	public void setVerticalVelocity(double verticalVelocity) 
		throws IllegalArgumentException{
		if(!isValidVerticalVelocity(verticalVelocity))
			throw new IllegalArgumentException("Not a valid vertical velocity!");
		this.verticalVelocity = verticalVelocity;
	}
	
	/**
	 * Check whether the given horizontal velocity is a valid horizontal velocity.
	 * @param horizontalVelocity
	 * @return
	 */
	public abstract boolean canHaveAsHorizontalVelocity(double horizontalVelocity);
	
	/**
	 * Check whether the given vertical velocity is a valid vertical velocity.
	 * 
	 * @param 	verticalVelocity
	 * 			The vertical velocity to be checked.
	 * @return	True if and only if the given vertical velocity is less than or equal to 8 m/s (the initial vertical velocity constant).
	 * 			| result ==  Util.fuzzyLessThanOrEqualTo(verticalVelocity, getInitialVerticalVelocity())
	 */
	public abstract boolean isValidVerticalVelocity(double verticalVelocity);
	
	/**
	 * Variable registering the horizontal velocity of this Mazub.
	 */
	protected double horizontalVelocity = 0;

	/**
	 *  Variable registering the vertical velocity of this Mazub.
	 */
	protected double verticalVelocity = 0;
	

	/**
	 * Returns the horizontal acceleration of this Mazub.
	 * 
	 */
	@Raw
	public double getHorizontalAcceleration(){
			return horizontalAcceleration;
	}
	
	/**
	 * Returns the vertical acceleration of this Mazub.
	 * 
	 */
	@Raw
	public double getVerticalAcceleration(){
			return this.verticalAcceleration;
	}
	
	/**
	 *  Variable registering the horizontal acceleration of a game object.
	 */
	protected double horizontalAcceleration;
	
	/**
	 *  Constant registering the vertical acceleration of a game object.
	 */
	protected double verticalAcceleration = -10;
	
	/**
	 *  Return the direction of this game object.
	 */
	@Basic
	@Raw
	public Direction getDirection(){
		return this.direction;
	}
	
	/**
	 * Set the direction of this game object to the given value.
	 * 
	 * @param 	direction
	 * 			The new direction for this game object.
	 * @pre		The given direction must be either LEFT or RIGHT.
	 * 			| (direction == Direction.LEFT) || (direction == Direction.RIGHT)
	 */
	@Raw
	public void setDirection(Direction direction){
		assert (direction==Direction.LEFT || direction==Direction.RIGHT);
		this.direction = direction;
	}
	
	/**
	 * Check whether the given direction is a valid direction.
	 * 
	 * @param 	direction
	 * 			The direction to be checked.
	 * @return	True if and only if the given direction is either LEFT or RIGHT.
	 * 			|result == ((direction == Direction.LEFT) || (direction == Direction.RIGHT))
	 */
	public static boolean isValidDirection(Direction direction){
		return ((direction == Direction.LEFT) || (direction == Direction.RIGHT));
	}
	
	/**
	 * Variable registering the direction game object is facing.
	 */
	private Direction direction = Direction.LEFT;
	
	public double getDeltaTimeForPixel(double deltaTime){
		return 0.01/(Math.sqrt((Math.pow(this.getHorizontalVelocity(),2)+Math.pow(this.getVerticalVelocity(), 2)))+
			Math.sqrt((Math.pow(this.getHorizontalAcceleration(),2)+Math.pow(this.getVerticalAcceleration(), 2)))*deltaTime);
	}
	
	/**
	 * Update the location and velocity of this game object.
	 * 
	 * @param 	deltaTime
	 * 			The period of time that is used to update this GameObject.
	 */
	public abstract void advanceTime(double deltaTime);
	
	/**
	 * Check whether the given deltaTime is a valid time period.
	 * 
	 * @param 	deltaTime
	 * 			The period of time to be checked.
	 * @return	True if and only if the time period is smaller
	 * 			than 0.2s and greater than or equal to zero.
	 * 			| result == Util.fuzzyGreaterThanOrEqualTo(deltaTime,0) && deltaTime < 0.2
	 */
	public static boolean isValidDeltaTime(double deltaTime){
		return Util.fuzzyGreaterThanOrEqualTo(deltaTime,0) && deltaTime < 0.2;
	}
	
	/**
	 * Return the time since this game object has last ended moving.
	 * 
	 * @note	We consider this method a part of advanceTime() and implement it defensively.
	 */
	@Basic
	@Raw
	public double getTimeSinceEndMove(){
		return this.timeSinceEndMove;
	}
	
	/**
	 * Set the time since game object has last ended moving.
	 * 
	 * @param 	timeSinceEndMove
	 * 			The time since this game object has last ended moving to be set.
	 * @post	The new time since this game object last ended moving is equal to the given time since game object last ended moving.
	 * 			|new.getTimeSinceEndMove()==timeSinceEndMove
	 * @throws	IllegalArgumentException
	 * 			The given time since game object last ended moving is not valid.
	 * 			|!isValidTimeSinceMove(timeSinceEndMove)
	 * @note	We consider this method a part of advanceTime() and implement it defensively.
	 */
	@Raw
	protected void setTimeSinceEndMove(double timeSinceEndMove) throws IllegalArgumentException{
		if(!isValidTimeSinceMove(timeSinceEndMove))
			throw new IllegalArgumentException();
		this.timeSinceEndMove=timeSinceEndMove;
	}
	
	/**
	 * Return the time since game object has last started moving.
	 * @note	We consider this method a part of advanceTime() and implement it defensively.
	 */
	@Basic
	@Raw
	public double getTimeSinceStartMove(){
		return this.timeSinceStartMove;
	}
	
	/**
	 * Set the time since game object has last started moving.
	 * @param 	timeSinceStartMove
	 * 			The time since this game object has last started moving to be set.
	 * @post	The new time since this game object last started moving is equal to the given time since game object last started moving.
	 * 			|new.getTimeSinceStartMove()==timeSinceStartMove
	 * @throws	IllegalArgumentException
	 * 			The given time since game object last started moving is not valid.
	 * 			|!isValidTimeSinceMove(timeSinceStartMove)
	 * @note	We consider this method a part of advanceTime() and implement it defensively.
	 */
	@Raw
	protected void setTimeSinceStartMove(double timeSinceStartMove) throws IllegalArgumentException{
		if(!isValidTimeSinceMove(timeSinceStartMove))
			throw new IllegalArgumentException();
		this.timeSinceStartMove=timeSinceStartMove;
	}
	
	/**
	 * Check whether the given time is a valid time period since game object's last action (either start move or end move).
	 * @param 	time
	 * 			The time period to be checked.
	 * @return	True if the given time period is greater than or equal to zero.
	 * 			| result == Util.fuzzyGreaterThanOrEqualTo(time, 0)
	 */
	protected static boolean isValidTimeSinceMove(double time){
		return Util.fuzzyGreaterThanOrEqualTo(time, 0);
	}
	
	/**
	 * Variable registering the time since this game object last started moving.
	 */
	private double timeSinceStartMove=0;
	
	/**
	 * Variable registering the time since this game object has last ended moving.
	 */
	private double timeSinceEndMove=2;
	
	
	/**
	 * Return the current Sprite of this game object.
	 */
	public Sprite getCurrentSprite(){
		if(this.getDirection() == Direction.LEFT)
			return getImageAt(0);
		else 
			return getImageAt(1);
	}
	
	/**
	 * Return a copy of the current image array of this game object.
	 */
	@Basic 
	@Raw
	public Sprite[] getImages(){
		return this.images.clone();
	}
	
	/**
	 * Return the number of images in the current image array of this game object.
	 */
	@Raw
	public int getNbImages(){
		return this.getImages().length;
	}
	
	/**
	 * Check whether the given number of images in the given image array is valid for all game objects.
	 * 
	 * @param 	nbImages
	 * 			The number of images to be checked.
	 * @return	The given number of images must be an even number and must be greater than or equal to 10;
	 * 			| result == (nbImages >= 10 && nbImages%2==0)
	 * 
	 */
	public abstract boolean isValidNbImages(int nbImages);
	
	/**
	 * Return the image in the image array of this game object at the given sprite index.
	 * 
	 * @pre		The given sprite index must be a valid sprite index.
	 * 			| isValidSpriteIndex(spriteIndex)
	 */
	@Basic
	@Raw
	public Sprite getImageAt(int spriteIndex) throws IllegalArgumentException{
		assert isValidSpriteIndex(spriteIndex):
			"The given sprite index is not a valid sprite index!";
		return this.getImages()[spriteIndex];
	}
	
	
	/**
	 * Check whether to given sprite index is a valid sprite index.
	 * 
	 * @param 	spriteIndex
	 * 			The sprite index to be checked.
	 * @return	True if the given sprite index is greater than or equal to zero and 
	 * 			smaller than or equal to the number of images in the current image array of this game object.
	 * 			| result == (spriteIndex>=0 && spriteIndex<=this.getNbImages())
	 */
	@Raw
	public boolean isValidSpriteIndex(int spriteIndex){
		return spriteIndex>=0 && spriteIndex<=this.getNbImages();
	}
	
	/**
	 * Check whether the given image is a valid image.
	 * 
	 * @param 	image
	 *			The image to be checked.
	 *@return	image!=null
	 */
	public static boolean isValidImage(Sprite image){
		return image!=null;
	}
	
	/**
	 * Set the image array of this game object to the given image array.
	 * 
	 * @param 	images
	 * 			The new image array for this game object.
	 * @pre 	The length of the given image array must be a valid length.
	 * 			|isValidNbImages(images.length)
	 * @pre		The images in the given image array must all be valid images.
	 * 			| for i in 1..images.length:
	 * 			|	isValidImage(images[i])
	 * @post	The new image array of this game object is equal to copy of he given image array.
	 * 			| this.images==images.clone()
	 */
	@Raw
	public void setImages(Sprite... images){
		assert (isValidNbImages(images.length)):
			"Not a valid number of images in the given image array!";
		for (Sprite image:images){
			assert isValidImage(image):
				"The given image array contains at least one image that isn't valid!";		
		}
		this.images=images.clone();
	}
	
	
	/**
	 * Return the height of the currrent sprite of this game object.
	 */
	@Raw
	public int getHeight(){
		return this.getCurrentSprite().getHeight();
	}
	
	/**
	 * Return the width of the current sprite of this game object.
	 */
	@Raw
	public int getWidth(){
		return this.getCurrentSprite().getWidth();
	}

	/**
	 * Variable registering the array of images of this game object.
	 */
	private Sprite images[];
	
	public World getWorld(){
		return this.world;
	}
	
	public void setWorld(World world) throws IllegalArgumentException{
		if (world==null || world.canHaveAsGameObject(this)){
			this.world=world;
		}
		else{
			throw new IllegalArgumentException("Not a valid world!");
		}
	}
	
	private World world;
}
