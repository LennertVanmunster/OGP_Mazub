package jumpingalien.model;

import jumpingalien.util.Sprite;
import jumpingalien.util.Util;
import be.kuleuven.cs.som.annotate.*;

import java.lang.Math;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
			return !this.getWorld().areaCoincidesWithTerrain((int) horizontalLocation, 
					this.getEffectiveVerticalLocation()+1, this.getWidth()-1, this.getHeight()-2)[1];
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
			return !this.getWorld().areaCoincidesWithTerrain(this.getEffectiveHorizontalLocation(), 
					(int) verticalLocation+1, this.getWidth()-1, this.getHeight()-2)[1];
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
	 * Variable registering the horizontal velocity of this game object.
	 */
	protected double horizontalVelocity = 0;

	/**
	 *  Variable registering the vertical velocity of this game object.
	 */
	protected double verticalVelocity = 0;
	

	/**
	 * Returns the horizontal acceleration of this game object.
	 * 
	 */
	@Raw
	public double getHorizontalAcceleration(){
			return horizontalAcceleration;
	}
	
	/**
	 * Returns the vertical acceleration of this game object.
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
	 * Check whether the given game object overlaps with the given tiles.
	 * 
	 * @param 	gameObject
	 * 			The given game object.
	 * @param 	tiles
	 * 			The given tiles.
	 * @return	Return true if and only if the game object overlaps with at least one of
	 * 			the given tiles.
	 * 			|overlap = false
	 * 			|coveredTiles = the tile overlap by the game object
	 * 			|for each tile in tiles
	 * 			|	for each coveredTile in coveredTiles
	 * 			|		if(Arrays.equals(tile, coveredTile))
	 * 			|		then overlap = true
	 * 
	 * @throws	IllegalArgument
	 * 			If the world of this game object can not have the given game object as its game object.
	 * 			|!world.canHaveAsGameObject(gameObject)
	 */
	protected boolean gameObjectOverlapsWithTiles(GameObject gameObject, int [][] tiles)
	throws IllegalArgumentException{
		World world = this.getWorld();
		if(!world.canHaveAsGameObject(gameObject))
			throw new IllegalArgumentException();
		boolean overlap = false;
		int [][] coveredTiles = world.areaOverlapsWithTiles(gameObject.getEffectiveHorizontalLocation(), gameObject.getEffectiveVerticalLocation(), gameObject.getWidth(), gameObject.getHeight());				
		outerloop:
		for(int [] tile : tiles)
			for(int [] coveredTile: coveredTiles)
				if(Arrays.equals(tile, coveredTile)){
					overlap = true;
					break outerloop;	
				}
		return overlap;
	}
	
	/**
	 * Return a list with the game objects that overlap with the given tiles.
	 *
	 * @param 	tiles
	 * 			The given tiles.
	 * @return 	The method iterates over all the game objects in the world except this game object. 
	 * 			If a game object overlaps with one of the given tiles, than it is added to the list which contains 
	 * 			all the overlapping game objects. At the end this list is returned.
	 * 			|for each game object of the world
	 * 			|	if(world.canHaveAsGameObject(gameObject) && gameObject != this)
	 * 			|    and if(gameObjectOverlapsWithTiles(gameObject, tiles))
	 * 			|	then the game object is added to the list of overlapping game objects
	 * 			|result == gameObjects
	 * 
	 */
	protected List<GameObject> getGameObjectsAtTiles(int [][] tiles){
		List<GameObject> gameObjects = new ArrayList<GameObject>();
		World world = this.getWorld();
		for(int index = 0; index < world.getNbOfGameObjects(); index++){
			GameObject gameObject = world.getGameObjectAtIndex(index);
			if(world.canHaveAsGameObject(gameObject) && gameObject != this){
				if(gameObjectOverlapsWithTiles(gameObject, tiles))	
					gameObjects.add(gameObject);
			}
		}
		return gameObjects;		
	}
	
	/**
	 * Return a matrix with at each row a coordinate of the top perimeter of this game object.
	 * 
	 * @param 	pixelX
	 * 			The given horizontal location.
	 * @param 	pixelY
	 * 			The given vertical location.
	 * @param 	width
	 * 			The given width of the game object.
	 * @param 	height
	 * 			The given height of the game object.
	 * @return	The method iterates of all (x...x + Width -1) and for each horizontal location 
	 * 			a list [X, Y] is added at the matrix with X equal to the current x of 
	 * 			the iteration plus pixelX and Y always equal to pixelY + height -1.
	 * 			The resulting matrix is returned.
	 * 			|for each X in 0...width -1
	 * 			|	topPerimeter [X][0] = pixelX + X;
	 *			|	topPerimeter [X][1] = Y;
	 *			|result == topPerimeter
	 * @throws 	IllegalArgumentException
	 * 			If the world can not have the given location as its location.
	 * 			|!world.canHaveAsPixelLocation(pixelX, pixelY)
	 */
	public int [][] getTopPerimeterOfGameObject(int pixelX, int pixelY,int width, int height)
			throws IllegalArgumentException{
				if(!world.canHaveAsPixelLocation(pixelX, pixelY))
					throw new IllegalArgumentException();
				int Y = pixelY + height - 1;
				int [][] topPerimeter = new int [width - 1][2];
				for (int X = 0; X < width - 1; X++ ){
					topPerimeter [X][0] = pixelX + X;
					topPerimeter [X][1] = Y;
				}
				return topPerimeter.clone();
			}
	
	/**
	 * Return a matrix with at each row a coordinate of the bottom perimeter of this game object.
	 * 
	 * @param 	pixelX
	 * 			The given horizontal location.
	 * @param 	pixelY
	 * 			The given vertical location.
	 * @param 	width
	 * 			The given width of the game object.
	 * @return	The method iterates of all (x...x + Width -1) and for each horizontal location 
	 * 			a list [X, Y] is added at the matrix with X equal to the current x of 
	 * 			the iteration plus pixelX and Y always equal to pixelY.
	 * 			The resulting matrix is returned.
	 * 			|for each Y in 0...width -1
	 * 			|	bottomPerimeter [X][0] = pixelX + X;
	 *			|	bottomPerimeter [X][1] = pixelY;
	 *			|result == bottomPerimeter
	 * @throws 	IllegalArgumentException
	 * 			If the world can not have the given location as its location.
	 * 			|!world.canHaveAsPixelLocation(pixelX, pixelY)
	 */
	public int [][] getBottomPerimeterOfGameObject(int pixelX, int pixelY,int width)
			throws IllegalArgumentException{
				if(!world.canHaveAsPixelLocation(pixelX, pixelY))
					throw new IllegalArgumentException();
				int [][] bottomPerimeter = new int [width - 1][2];
				for (int X = 0; X < width - 1; X++ ){
					bottomPerimeter [X][0] = pixelX + X;
					bottomPerimeter [X][1] = pixelY;
				}
				return bottomPerimeter.clone();
			}

	/**
	 * Return a matrix with at each row a coordinate of the left perimeter of this game object.
	 * 
	 * @param 	pixelX
	 * 			The given horizontal location.
	 * @param 	pixelY
	 * 			The given vertical location.
	 * @param 	height
	 * 			The given height of the game object.
	 * @return	The method iterates of all (y + 1...y + height - 2) and for each vertical location 
	 * 			a list [X, Y] is added at the matrix with X equal to pixelX
	 * 			 and Y equal to the given y of the iteration + pixelY + 1.
	 * 			The resulting matrix is returned.
	 * 			|for each Y in 0...height-2
	 * 			|	leftPerimeter [Y][0] = pixelX;
	 *			|	leftPerimeter [Y][1] = pixelY + Y + 1;
	 *			|result == leftPerimeter
	 * @throws 	IllegalArgumentException
	 * 			If the world can not have the given location as its location.
	 * 			|!world.canHaveAsPixelLocation(pixelX, pixelY)
	 */
	public int [][] getLeftPerimeterOfGameObject(int pixelX, int pixelY, int height)
	throws IllegalArgumentException{
		if(!world.canHaveAsPixelLocation(pixelX, pixelY))
			throw new IllegalArgumentException();
		int [][] leftPerimeter = new int [height-2][2];
		for (int Y = 0; Y < height-2; Y++ ){
			leftPerimeter [Y][0] = pixelX;
			leftPerimeter [Y][1] = pixelY + Y + 1;
		}
		return leftPerimeter.clone();
	}
	
	/**
	 * Return a matrix with at each row a coordinate of the right perimeter of this game object.
	 * 
	 * @param 	pixelX
	 * 			The given horizontal location.
	 * @param 	pixelY
	 * 			The given vertical location.
	 * @param 	width
	 * 			The given width of the game object.
	 * @param 	height
	 * 			The given height of the game object.
	 * @return	The method iterates of all (y + 1...y + height - 2) and for each vertical location 
	 * 			a list [X, Y] is added at the matrix with X equal to pixelX + width - 1
	 * 			 and Y equal to the given y of the iteration + pixelY + 1.
	 * 			The resulting matrix is returned.
	 * 			|for each Y in 0...height-2
	 * 			|	leftPerimeter [Y][0] = pixelX;
	 *			|	leftPerimeter [Y][1] = pixelY + Y + 1;
	 *			|result == rightPerimeter
	 * @throws 	IllegalArgumentException
	 * 			If the world can not have the given location as its location.
	 * 			|!world.canHaveAsPixelLocation(pixelX, pixelY)
	 */
	public int [][] getRightPerimeterOfGameObject(int pixelX, int pixelY, int width, int height)
			throws IllegalArgumentException{
				if(!world.canHaveAsPixelLocation(pixelX, pixelY))
					throw new IllegalArgumentException();
				int [][] rightPerimeter = new int [height-2][2];
				int X = pixelX + width - 1;
				for (int Y = 0; Y < height-2; Y++ ){
					rightPerimeter [Y][0] = X;
					rightPerimeter [Y][1] = pixelY + Y + 1;
				}
				return rightPerimeter.clone();
			}
	
	/**
	 * Check whether the left, right or top of this game object overlaps with another game object.
	 * 
	 * @return	The method iterates over all the game objects that can overlap with the given
	 * 			game object. That are the game objects that overlap with one of the tile with
	 * 			which this game object overlaps. If another game object is found that overlaps 
	 * 			with one of the given sides then an array is returned with at the first position
	 * 			number one defining that there is overlap and at the second position the index 
	 * 			of the game object in the world.
	 * 			|overlap = {0,0}
	 * 			|for each game object in gameGameObjectAtTile of this game object
	 * 			|	if(gameObject != this && world.canHaveAsGameObject(gameObject))
	 * 			|		overlap = checkLeftOrRightSideOverlap(gameObject);
	 *			|		if(overlap [0] == 1)
	 *			|		then the array "overlap" is returned
	 *			|		or 
	 *			|		overlap = checkTopSideOverlap(gameObject);
	 *			|		if(overlap [0] == 1)
	 *			|		then the array "overlap" is returned
	 */
	public int [] checkLeftRightTopSideOverlap(){
		int [] overlap = {0,0};
		World world = this.getWorld();
		List<GameObject> gameObjects = getGameObjectsAtTiles(world.areaOverlapsWithTiles(getEffectiveHorizontalLocation(), getEffectiveVerticalLocation(), getWidth(), getHeight()));
		for(int index = 0; index < gameObjects.size(); index++){
			GameObject gameObject = gameObjects.get(index);
			if(gameObject != this && world.canHaveAsGameObject(gameObject)){
				overlap = checkLeftOrRightSideOverlap(gameObject);
				if(overlap [0] == 1)
					break;
				overlap = checkTopSideOverlap(gameObject);
				if(overlap [0] == 1)
					break;
			}
		}
		return overlap;
		}
	
	/**
	 * Check whether the left, right, top or bottom of this game object overlaps with another game object.
	 * 
	 * @return	The method iterates over all the game objects that can overlap with the given
	 * 			game object. That are the game objects that overlap with one of the tile with
	 * 			which this game object overlaps. If another game object is found that overlaps 
	 * 			with one of the given sides then an array is returned with at the first position
	 * 			number one defining that there is overlap and at the second position the index 
	 * 			of the game object in the world.
	 * 			|overlap = {0,0}
	 * 			|for each game object in gameGameObjectAtTile of this game object
	 * 			|	if(gameObject != this && world.canHaveAsGameObject(gameObject))
	 * 			|		overlap = checkLeftOrRightSideOverlap(gameObject);
	 *			|		if(overlap [0] == 1)
	 *			|		then the array "overlap" is returned
	 *			|		or 
	 *			|		overlap = checkTopOrBottomSideOverlap(gameObject);
	 *			|		if(overlap [0] == 1)
	 *			|		then the array "overlap" is returned
	 */
	public int [] checkLeftRightTopBottomSideOverlap(){
		int [] overlap = {0,0};
		World world = this.getWorld();
		List<GameObject> gameObjects = getGameObjectsAtTiles(world.areaOverlapsWithTiles(getEffectiveHorizontalLocation(), getEffectiveVerticalLocation(), getWidth(), getHeight()));
		for(int index = 0; index < gameObjects.size(); index++){
			GameObject gameObject = gameObjects.get(index);
			if(gameObject != this && world.canHaveAsGameObject(gameObject)){
				overlap = checkLeftOrRightSideOverlap(gameObject);
				if(overlap [0] == 1)
					break;
				overlap = checkTopOrBottomSideOverlap(gameObject);
				if(overlap [0] == 1)
					break;
			}
		}
		return overlap;
		}
		
	/**
	 * Check whether the top side of this game object overlaps with the given game object.
	 * 
	 * @return	The method iterates over the positions of the top perimeter of this game object and the 
	 * 			bottom perimeter of the given game object. 
	 * 			If one of the positions is equal to each other then an array is returned with at the first position
	 * 			the number one defining that there is overlap and at the second position the index 
	 * 			of the game object in the world.
	 * 			|overlap = {0,0}
	 * 			|for position1 in top perimeter
	 * 			|	for position 2 in bottom perimeter
	 * 			|		if(position1 == position2)
	 * 			|		then
	 *			|		overlap [0] = 1 ;
	 *			|		overlap [1] = world.getIndexOfGameObject(gameObject);
	 *			|result == overlap 
	 */
	public int [] checkTopSideOverlap(GameObject gameObject){
		int [] overlap = {0,0};
		World world = this.getWorld();
		int [][] topPerimeter1 = this.getTopPerimeterOfGameObject(getEffectiveHorizontalLocation(), getEffectiveVerticalLocation(), getWidth(), getHeight());
		int [][] bottomPerimeter2 = gameObject.getBottomPerimeterOfGameObject(gameObject.getEffectiveHorizontalLocation(), gameObject.getEffectiveVerticalLocation(), gameObject.getWidth());
		outerloop:
		for (int Y = 0; Y < this.getWidth()-1; Y++ ){
			for (int Y2 = 0; Y2 < gameObject.getWidth()-1; Y2++ ){
				if(Arrays.equals(topPerimeter1[Y],bottomPerimeter2[Y2])){
					overlap [0] = 1 ;
					overlap [1] = world.getIndexOfGameObject(gameObject);
					break outerloop;
				}
			}
		}
		return overlap.clone();
	}
	
	/**
	 * Check whether the top or bottom side of this game object overlaps with the
	 * top or bottom side of the given game object.
	 * 
	 * @return	The method iterates over the positions of the top and bottom perimeter of this game object
	 * 			and the top and bottom perimeter of the given game object. 
	 * 			If one of the top positions is equal another other bottom position or the other way around
	 * 			then an array is returned with at the first position the number one defining that 
	 * 			there is overlap and at the second position the index of the game object in the world.
	 * 			|overlap = {0,0}
	 * 			|for position1 in top perimeter and bottom perimeter
	 * 			|	for position 2 in top perimeter and bottom perimeter
	 * 			|		if(position1top == position2bottom || position1bottom == position2top)
	 * 			|		then
	 *			|		overlap [0] = 1 ;
	 *			|		overlap [1] = world.getIndexOfGameObject(gameObject);
	 *			|result == overlap 
	 */
	public int [] checkTopOrBottomSideOverlap(GameObject gameObject){
		int [] overlap = {0,0};
		World world = this.getWorld();
		int [][] topPerimeter1 = this.getTopPerimeterOfGameObject(getEffectiveHorizontalLocation(), getEffectiveVerticalLocation(), getWidth(), getHeight());
		int [][] bottomPerimeter2 = gameObject.getBottomPerimeterOfGameObject(gameObject.getEffectiveHorizontalLocation(), gameObject.getEffectiveVerticalLocation(), gameObject.getWidth());
		int [][] bottomPerimeter1 = this.getBottomPerimeterOfGameObject(getEffectiveHorizontalLocation(), getEffectiveVerticalLocation(), getWidth());
		int [][] topPerimeter2 = gameObject.getTopPerimeterOfGameObject(gameObject.getEffectiveHorizontalLocation(), gameObject.getEffectiveVerticalLocation(), gameObject.getWidth(), gameObject.getHeight());
		outerloop:
		for (int Y = 0; Y < this.getWidth()-1; Y++ ){
			for (int Y2 = 0; Y2 < gameObject.getWidth()-1; Y2++ ){
				if(Arrays.equals(topPerimeter1[Y],bottomPerimeter2[Y2]) ||
						Arrays.equals(topPerimeter2[Y2],bottomPerimeter1[Y])){
					overlap [0] = 1 ;
					overlap [1] = world.getIndexOfGameObject(gameObject);
					break outerloop;
				}
			}
		}
		return overlap.clone();
	}
	
	/**
	 * Check whether the left or right side of this game object overlaps with the
	 * left or right side of the given game object.
	 * 
	 * @return	The method iterates over the positions of the left and right perimeter of this game object
	 * 			and the left and right perimeter of the given game object. 
	 * 			If one of the left positions is equal another right position or the other way around
	 * 			then an array is returned with at the first position the number one defining that 
	 * 			there is overlap and at the second position the index of the game object in the world.
	 * 			|overlap = {0,0}
	 * 			|for position1 in left perimeter and right perimeter
	 * 			|	for position 2 in left perimeter and right perimeter
	 * 			|		if(position1left == position2right || position1right == position2left)
	 * 			|		then
	 *			|		overlap [0] = 1 ;
	 *			|		overlap [1] = world.getIndexOfGameObject(gameObject);
	 *			|result == overlap 
	 */
	public int [] checkLeftOrRightSideOverlap(GameObject gameObject){
		int [] overlap = {0,0};
		World world = this.getWorld();
		int [][] leftPerimeter1 = this.getLeftPerimeterOfGameObject(getEffectiveHorizontalLocation(), getEffectiveVerticalLocation(), getHeight());
		int [][] leftPerimeter2 = gameObject.getLeftPerimeterOfGameObject(gameObject.getEffectiveHorizontalLocation(), gameObject.getEffectiveVerticalLocation(), gameObject.getHeight());
		int [][] rightPerimeter1 = this.getRightPerimeterOfGameObject(getEffectiveHorizontalLocation(), getEffectiveVerticalLocation(), getWidth(), getHeight());
		int [][] rightPerimeter2 = gameObject.getRightPerimeterOfGameObject(gameObject.getEffectiveHorizontalLocation(), gameObject.getEffectiveVerticalLocation(), gameObject.getWidth(), gameObject.getHeight());
		outerloop:
		for (int Y = 0; Y < this.getHeight()-2; Y++ ){
			for (int Y2 = 0; Y2 < gameObject.getHeight()-2; Y2++ ){
				if(Arrays.equals(leftPerimeter1[Y],rightPerimeter2[Y2]) ||
						Arrays.equals(leftPerimeter2[Y2],rightPerimeter1[Y])){
					overlap [0] = 1 ;
					overlap [1] = world.getIndexOfGameObject(gameObject);
					break outerloop;
				}
			}
		}
		return overlap.clone();
	}
	
	/**
	 * Method the selects the appropriate response for this game object 
	 * after a collision with another game object.
	 * @param 	index
	 * 			The index of the other game object.
	 */
	protected abstract void collisionReaction(int index);
	
	/**
	 * Variable registering the direction game object is facing.
	 */
	private Direction direction = Direction.LEFT;
	
	public double getDeltaTimeForPixel(double deltaTime){
		double time =  0.01/(Math.sqrt((Math.pow(this.getHorizontalVelocity(),2)+Math.pow(this.getVerticalVelocity(), 2)))+
				Math.sqrt((Math.pow(this.getHorizontalAcceleration(),2)+Math.pow(this.getVerticalAcceleration(), 2)))*deltaTime);
		if(isValidDeltaTime(time))
			return time;
		else
			return 0.03;
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
	 * Return the time since the start of the water contact of this game object.
	 * The time is reset when the game object loses hit  points or no longer makes contact.
	 */
	public double getTimeSinceStartWaterContact(){
		return this.timeSinceWaterContact;
	}
	
	/**
	 * Return the time since the start of the magma contact of this game object.
	 * The time is reset when the game object loses hit points or no longer makes contact.
	 */
	public double getTimeSinceStartMagmaContact(){
		return this.timeSinceMagmaContact;
	}
	
	/**
	 * Set the time since the start of the water contact of this game object.
	 * 
	 * @param	time
	 * 			The given time.
	 * @throws	IllegalArgumentException
	 * 			|!isValidTimeSinceMove(time)
	 * 			
	 */
	public void setTimeSinceStartWaterContact(double time)
	throws IllegalArgumentException{
		if(!isValidTimeSinceMove(time))
			throw new IllegalArgumentException();
		this.timeSinceWaterContact = time;
			
	}
	
	/**
	 * Set the time since the start of the magma contact of this game object.
	 * 
	 * @param	time
	 * 			The given time.
	 * @throws	IllegalArgumentException
	 * 			|!isValidTimeSinceMove(time)
	 * 			
	 */
	public void setTimeSinceStartMagmaContact(double time)
			throws IllegalArgumentException{
				if(!isValidTimeSinceMove(time))
					throw new IllegalArgumentException();
				this.timeSinceMagmaContact = time;		
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
	 * Variable registering the time since this game object is making contact with water.
	 */
	private double timeSinceWaterContact = 0;
	
	/**
	 * Variable registering the time since this game object is making contact with magma.
	 */
	private double timeSinceMagmaContact = 0;
	
	/**
	 * Variable registering the time since this game object last started moving.
	 */
	private double timeSinceStartMove=0;
	
	/**
	 * Variable registering the time since this game object has last ended moving.
	 */
	private double timeSinceEndMove=2;
	
	/**
	 * Check whether the game object makes contact with water and take the corresponding actions.
	 */
	public abstract void checkWaterContact(double deltaTime);
	
	/**
	 *Check whether the game object makes contact with magma and take the corresponding actions. 
	 */
	public abstract void checkMagmaContact(double deltaTime);
	
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
		assert canHaveAsSpriteIndex(spriteIndex):
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
	public boolean canHaveAsSpriteIndex(int spriteIndex){
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
	public void setImages(Sprite[] images){
		assert isValidNbImages(images.length):
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
	protected Sprite images[];
	
	/**
	 * Return the current number of hit-points of this game object.
	 */
	@Basic
	@Raw
	public int getHitPoints() {
		return this.hitPoints;
	}

	/**
	 * Return the current maximum number of hit-points of this game object.
	 */
	@Basic
	@Raw
	public int getMaxHitPoints() {
		return this.maxHitPoints;
	}
	
	/**
	 * Set the maximum number of hit-points of this game object.
	 */
	@Basic
	@Raw
	public void setMaxHitPoints(int hitPoints) {
		this.maxHitPoints = hitPoints;
	}
	
	public void removeHitPoints(int hitPoints){
		int oldHitPoints = getHitPoints();
		this.setHitPoints(oldHitPoints - hitPoints);
	}
	
	public void addHitPoints(int hitPoints){
		int oldHitPoints = getHitPoints();
		setHitPoints(oldHitPoints + hitPoints);
	}
	
	/**
	 * Set the hit-points of this game object to the given hit-points.
	 * 
	 * @param 	hitPoints
	 * 		  	The new number of hit-points for this game object.
	 * @post	If the given number of hit-points is greater than or equal to the maximum number of hit-points, 
	 * 			the new number of hit-points of this game object is set to the maximum number of hit-points.
	 * 		  	| if (hitPoints>=MAX_HIT_POINTS)
	 *			|		new.hitPoints == MAX_HIT_POINTS
	 * @post	If the given number of hit-points is negative or equal to zero, 
	 * 			the new number of hit-points of this game object is set to 0.
	 * 		  	| if (hitPoints<=0)
	 *			|		new.hitPoints == 0
	 * @post	If the given number of hit-points is in the pre-established range of hit-points for a game object,
	 * 			the new number of hit-points of this game object is set to the given number of hit-points.
	 * 			| if (hitPoints>0 && hitPoints<500){
	 * 			|		new.hitPoints==hitPoints		
	 */
	@Basic
	@Raw
	public void setHitPoints(int hitPoints) {
		if (hitPoints>=getMaxHitPoints()){
			this.hitPoints = getMaxHitPoints();
		}
		else if (hitPoints<=0){
			this.hitPoints = 0;
		}
		else{
			this.hitPoints= hitPoints;
		}
	}
	
	/**
	 * Variable registering the number of hit-points of this game object. 
	 */
	protected int hitPoints;
	
	/**
	 * Variable registering the maximum number of hit-points of this game object. 
	 */
	protected int maxHitPoints;
	
	public World getWorld(){
		return this.world;
	}
	
	/**
	 * Set a world for this game object.
	 * @param 	world
	 * 			The world for this game object.
	 * @throws 	IllegalArgumentException
	 * 			|
	 */
	public void setWorld(World world) throws IllegalArgumentException{
		if ( world == null || world.canHaveAsGameObject(this)){
			this.world=world;
		}
		else{
			throw new IllegalArgumentException("Not a valid world!");
		}
	}
	
	protected World world;
	
	protected void unsetWorld(){
		World world = getWorld();
		if(world != null)
			setWorld(null);
			world.removeGameObject(this);
	}
}
