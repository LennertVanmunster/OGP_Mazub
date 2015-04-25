package jumpingalien.model;

import be.kuleuven.cs.som.annotate.*;

import java.util.*;



/**
 * A class which represent a game world.
 * 
 * @invar	Each world can have its tile size as its tile size.
 * 			|canHaveAsTileSize(getTileSize())
 * @invar	Each world can have its horizontal number of tiles as its horizontal number of tiles.
 * 			|canHaveAsNbTilesX(getNbTilesX())
 * @invar	Each world can have its vertical number of tiles as its vertical number of tiles.
 * 			|canHaveAsTilesY(getNbTilesY())
 * @invar	Each game world can have its visible window width as its visible window width.
 * 			|canHaveAsVisibleWindowDimension(getVisibleWindowWidth())
 * @invar	Each game world can have its visible window height as its visible window height.
 * 			|canHaveAsVisibleWindowDimension(getVisibleWindowHeight())
 * @invar	Each game object can have its visible window horizontal location as its visible window horizontal location.
 * 			|canHaveAsVisibleWindowLocation(getHorizontalVisibleWindowLocation())
 * @invar	Each game object can have its visible window vertical location as its visible window vertical location.
 * 			|canHaveAsVisibleWindowLocation(getVerticalVisibleWindowLocation())
 * @invar	If possible the window shall always be positioned so that there are 
 * 			at least 200 pixels between all pixels occupied by Mazub.
 * @invar	A game world must contain at least one player character Mazub and
 * 			no more than 100 other game objects.
 * 			|isValidAmountOfGameObjects(getAmountOfMazub(),getAmountOfOtherGameObjects())
 * @invar	Each world must have proper game objects.
 * 			| hasProperGameObjects()
 *
 * @version	1.0
 * @author 	Pieter Van Damme and Lennert Vanmunster
 *
 */
public class World {
	
	/**
	 * Initialize a new game world with given a tile size, given number of tiles in the 
	 * horizontal and vertical direction, given width and height of the visible window and 
	 * a given location of the target tile (X,Y).
	 * 
	 * @param 	tileSize
	 * 			The size of a tile (in pixels) for this new World.
	 * @param 	nbTilesX
	 * 			The number of tiles in the horizontal direction for this new World.
	 * @param 	nbTilesY
	 * 			The number of tiles in the horizontal direction for this new World.
	 * @param 	visibleWindowWidth
	 * 			The width of the visible window.
	 * @param 	visibleWindowHeight
	 * 			The height of the visible window.
	 * @param 	targetTileX
	 * 			The horizontal location of the target tile.
	 * @param 	targetTileY
	 * 			The vertical location of the target tile.
	 * @param	alien
	 * 			The Mazub belonging to this world.
	 * @post	|new.getTileSize()==tileSize
	 * @post	|new.getNbTilesX()==nbTilesX
	 * @post	|new.getNbTilesY()==nbTilesY
	 * @post	|new.getVisibleWindowWidth()==visibleWindowWidth
	 * @post	|new.getVisibleWindowHeight()==visibleWindowHeight
	 * @post	|new.getTargetTileX==targetTileX
	 * @post	|new.getTargetTileY==targetTileY
	 * @post	|new.getMazub()==alien
	 * @effect	|createTiles(nbTilesX,nbTilesY)
	 * @throws	IllegalArgumentException
	 * 			|!isPossibleTileSize(tileSize) || !matchesTileSizeNbTilesWindowDimensionWindowLocation(tileSize, nbTilesX, visibleWindowWidth, getInitialVisibleWindowLocation()[0])
	 * 			| || !matchesTileSizeNbTilesWindowDimensionWindowLocation(tileSize, nbTilesY, visibleWindowHeight, getInitialVisibleWindowLocation()[1])
	 * @throws	IllegalArgumentException
	 * 			|!isPossibleNbTiles(nbTilesX) || !matchesTileSizeNbTilesWindowDimensionWindowLocation(tileSize, nbTilesX, visibleWindowWidth, getInitialVisibleWindowLocation()[0])
	 * @throws	IllegalArgumentException
	 * 			|!isPossibleNbTiles(nbTilesY) || !matchesTileSizeNbTilesWindowDimensionWindowLocation(tileSize, nbTilesY, visibleWindowHeight, getInitialVisibleWindowLocation()[1])
	 * @throws	IllegalArgumentException
	 * 			|!isPossibleWindowDimension(visibleWindowWidth) || !matchesTileSizeNbTilesWindowDimensionWindowLocation(tileSize, nbTilesX, visibleWindowWidth, getInitialVisibleWindowLocation()[0])
	 * @throws	IllegalArgumentException
	 * 			|!isPossibleWindowDimension(visibleWindowHeight) || !matchesTileSizeNbTilesWindowDimensionWindowLocation(tileSize, nbTilesY, visibleWindowHeight, getInitialVisibleWindowLocation()[1])
	 * @throws	IllegalArgumentException
	 * 			|!isPossibleWindowLocation(0) || !matchesTileSizeNbTilesWindowDimensionWindowLocation(tileSize, nbTilesX, visibleWindowWidth, getInitialVisibleWindowLocation()[0])
	 * @throws	IllegalArgumentException			
	 * 			|!isPossibleWindowLocation(0) || !matchesTileSizeNbTilesWindowDimensionWindowLocation(tileSize, nbTilesY, visibleWindowHeight, getInitialVisibleWindowLocation()[1])
	 * @throws	IllegalArgumentException
	 * 			|!canHaveAsMazub(alien)
	 */
	@Raw
	public World(int tileSize, int nbTilesX, int nbTilesY, int visibleWindowWidth, int visibleWindowHeight,
			int targetTileX, int targetTileY, Mazub alien) throws IllegalArgumentException{
		if(!isPossibleTileSize(tileSize))
			throw new IllegalArgumentException("Not a valid tile size!");
		this.tileSize = tileSize;
		if(!isPossibleNbTiles(nbTilesX))
			throw new IllegalArgumentException("Not a valid number of tiles!");
		this.nbTilesX = nbTilesX;
		if(!isPossibleNbTiles(nbTilesY))
			throw new IllegalArgumentException("Not a valid number of tiles!");
		this.nbTilesY = nbTilesY;
		if(!isPossibleVisibleWindowDimension(visibleWindowWidth))
			throw new IllegalArgumentException("Not a window width!");
		this.visibleWindowWidth = visibleWindowWidth;
		if(!isPossibleVisibleWindowDimension(visibleWindowHeight))
			throw new IllegalArgumentException("Not a window height!");
		this.visibleWindowHeight = visibleWindowHeight;
		//To be worked out:
		this.setVisibleWindowLocation(getInitialVisibleWindowLocation()[0],getInitialVisibleWindowLocation()[1]);
		createTiles(getNbTilesX(),getNbTilesY());
		this.targetTile = new int [2];
		setTargetTile(targetTileX,targetTileY);
		this.setMazub(alien);
		
	}
	
	/**
	 * Create a game world without a mazub.
	 * @param 	tileSize
	 * 			The size of a tile (in pixels) for this new World.
	 * @param 	nbTilesX
	 * 			The number of tiles in the horizontal direction for this new World.
	 * @param 	nbTilesY
	 * 			The number of tiles in the horizontal direction for this new World.
	 * @param 	visibleWindowWidth
	 * 			The width of the visible window.
	 * @param 	visibleWindowHeight
	 * 			The height of the visible window.
	 * @param 	targetTileX
	 * 			The horizontal location of the target tile.
	 * @param 	targetTileY
	 * 			The vertical location of the target tile.
	 * @effect	|this(tileSize, nbTilesX, nbTilesY, visibleWindowWidth, visibleWindowHeight, targetTileX, targetTileY, null)
	 */
	public World(int tileSize, int nbTilesX, int nbTilesY, int visibleWindowWidth, int visibleWindowHeight,
			int targetTileX, int targetTileY){
		this(tileSize, nbTilesX, nbTilesY, visibleWindowWidth, visibleWindowHeight, targetTileX, targetTileY, null);
	}
	
	/**
	 * Return the tile size of this World.
	 */
	@Basic
	@Raw
	@Immutable
	public int getTileSize(){
		return this.tileSize;
	}
	
	/**
	 * Return the number of tiles in the horizontal direction.
	 */
	@Basic
	@Raw
	@Immutable
	public int getNbTilesX(){
		return this.nbTilesX;
	}
	
	/**
	 * Return the number of tiles in the vertical direction.
	 */
	@Basic
	@Raw
	@Immutable
	public int getNbTilesY(){
		return this.nbTilesY;
	}
	
	/**
	 * Return the visible window width
	 */
	@Basic
	@Immutable
	public int getVisibleWindowWidth(){
		return this.visibleWindowWidth;
	}
	
	/**
	 * Return the visible window height
	 */
	@Basic
	@Immutable
	public int getVisibleWindowHeight(){
		return this.visibleWindowHeight;
	}
	
	/**
	 * Return the initial visible window pixel location as an array consisting of the horizontal and vertical components.
	 */
	@Immutable
	public static int[] getInitialVisibleWindowLocation(){
		return initialVisibleWindowLocation;
	}
	
	/**
	 * Return the current visible window pixel location as an array consisting of the horizontal and vertical components.
	 */
	@Basic
	public int [] getVisibleWindowLocation(){
		return this.visibleWindowLocation.clone();
	}
	
	/**
	 * Set the current visible window pixel location to the given horizontal and vertical location.
	 * 
	 * @param 	horizontalLocation
	 * 			The location of the left border of the visible window.
	 * @param 	verticalLocation
	 * 			The location of the bottom border of the visible window.
	 * @post	|new.getVisibleWindowLocation()=={horizontalLocation,verticalLocation}
	 * @throws 	IllegalArgumentException
	 * 			|!canHaveAsHorizontalVisibleWindowLocation(horizontalLocation) || !canHaveAsVerticalVisibleWindowLocation(verticalLocation)
	 */
	@Raw
	private void setVisibleWindowLocation(int horizontalLocation, int verticalLocation)
		throws IllegalArgumentException {
		if(!canHaveAsHorizontalVisibleWindowLocation(horizontalLocation) || !canHaveAsVerticalVisibleWindowLocation(verticalLocation))
			throw new IllegalArgumentException("Not a valid pixel location!");
		int [] window = {horizontalLocation, verticalLocation};
		this.visibleWindowLocation = window;
	}
	
	/**
	 * Check whether the given tile size is a possible tile size for any world.
	 * 
	 * @param 	tileSize
	 * 			The size of the given tile to check.
	 * @return	| result == tilseSize > 0
	 */
	@Raw
	public static boolean isPossibleTileSize(int tileSize){
		return tileSize > 0;
	}
	
	/**
	 * Check whether the given number of tiles is a possible number of tiles for any world.
	 * 
	 * @param 	nbTiles
	 * 			The number of tiles to check.
	 * @return	| result == nbTiles > 0
	 */
	@Raw
	public static boolean isPossibleNbTiles(int nbTiles){
		return nbTiles > 0;
	}
	
	/**
	 * Check whether the given window dimension is a possible window dimension for any world.
	 * 
	 * @param 	windowDimension
	 * 			The window dimension to check.
	 * @return	| result == windowDimension > 0
	 */
	public static boolean isPossibleVisibleWindowDimension(int windowDimension){
		return windowDimension>0;
	}
	
	/**
	 * Check whether the given window location is a possible window location for any world.
	 * 
	 * @param 	windowLocation
	 * 			The window location to check.
	 * @return	| result == windowLocation >= 0
	 */
	public static boolean isPossibleWindowLocation(int windowLocation){
		return windowLocation>=0;
	}
	
	/**
	 * Check whether the given tile size, number of tiles, window dimension and window location match with each other.
	 * @param 	tileSize
	 * @param 	nbTiles
	 * @param	windowDimension
	 * @param 	windowLocation
	 * @return	|tileSize * nbTiles >= windowDimension + windowLocation
	 */
	public static boolean matchesTileSizeNbTilesWindowDimensionWindowLocation(int tileSize, int nbTiles, int windowDimension, int windowLocation){
		return tileSize * nbTiles >= windowDimension + windowLocation;	
	}
	
	/**
	 * Check whether this world can have the given tile size as its tile size.
	 * @param 	tileSize
	 * @return	|result==isPossibleTileSize(tileSize) && matchesTileSizeNbTilesWindowDimensionWindowLocation(tileSize, this.getNbTiles(), this.getVisibleWindowWidth(), this.getVisibleWindowLocation()[0])
	 *			|	&& matchesTileSizeNbTilesWindowDimensionWindowLocation(tileSize, this.getNbTiles(), this.getVisibleWindowHeight(),this.getVisibleWindowLocation()[1])
	 */
	public boolean canHaveAsTileSize(int tileSize){
		return isPossibleTileSize(tileSize) && matchesTileSizeNbTilesWindowDimensionWindowLocation(tileSize, this.getNbTiles(), this.getVisibleWindowWidth(), this.getVisibleWindowLocation()[0])
				&& matchesTileSizeNbTilesWindowDimensionWindowLocation(tileSize, this.getNbTiles(), this.getVisibleWindowHeight(),this.getVisibleWindowLocation()[1]);
	}
	
	/**
	 * Check whether this world can have the given number of tiles in the horizontal direction as its horizontal number of tiles.
	 * @param 	nbTilesX
	 * @return	|result==isPossibleNbTiles(nbTilesX) && matchesTileSizeNbTilesWindowDimensionWindowLocation(this.getTileSize(), nbTilesX, this.getVisibleWindowWidth(), this.getVisibleWindowLocation()[0])
	 */
	public boolean canHaveAsNbTilesX(int nbTilesX){
		return isPossibleNbTiles(nbTilesX) && matchesTileSizeNbTilesWindowDimensionWindowLocation(this.getTileSize(), nbTilesX, this.getVisibleWindowWidth(), this.getVisibleWindowLocation()[0]);
	}
	
	/**
	 * Check whether this world can have the given number of tiles in the vertical direction as its vertical number of tiles.
	 * @param 	nbTilesY
	 * @return	|result==isPossibleNbTiles(nbTilesY) && matchesTileSizeNbTilesWindowDimensionWindowLocation(this.getTileSize(), nbTilesY, this.getVisibleWindowHeight(), this.getVisibleWindowLocation()[1])
	 */
	public boolean canHaveAsNbTilesY(int nbTilesY){
		return isPossibleNbTiles(nbTilesY) && matchesTileSizeNbTilesWindowDimensionWindowLocation(this.getTileSize(), nbTilesY, this.getVisibleWindowHeight(), this.getVisibleWindowLocation()[1]);
	}
	
	/**
	 * Check whether this world can have the given visible window width as its visible window width.
	 * @param 	visibleWindowWidth
	 * @return	|result==isPossibleVisibleWindowDimension(visibleWindowWidth) && 
	 *			|	matchesTileSizeNbTilesWindowDimensionWindowLocation(this.getTileSize(), this.getNbTilesX(), visibleWindowWidth, this.getVisibleWindowLocation()[0])
	 */
	public boolean canHaveAsVisibleWindowWidth(int visibleWindowWidth){
		return isPossibleVisibleWindowDimension(visibleWindowWidth) && 
				matchesTileSizeNbTilesWindowDimensionWindowLocation(this.getTileSize(), this.getNbTilesX(), visibleWindowWidth, this.getVisibleWindowLocation()[0]);
	}
	
	/**
	 * Check whether this world can have the given visible window height as its visible window height.
	 * @param 	visibleWindowHeight
	 * @return	|result==isPossibleVisibleWindowDimension(visibleWindowHeight) && 
	 *			|	matchesTileSizeNbTilesWindowDimensionWindowLocation(this.getTileSize(), this.getNbTilesY(), visibleWindowHeight, this.getVisibleWindowLocation()[1])
	 */
	public boolean canHaveAsVisibleWindowHeight(int visibleWindowHeight){
		return isPossibleVisibleWindowDimension(visibleWindowHeight) && 
				matchesTileSizeNbTilesWindowDimensionWindowLocation(this.getTileSize(), this.getNbTilesY(), visibleWindowHeight, this.getVisibleWindowLocation()[1]);
	}
	
	/**
	 * Check whether this world can have the given horizontal visible window location as its horizontal visible window location.
	 * @param 	horizontalVisibleWindowLocation
	 * @return	|result==isPossibleWindowLocation(horizontalVisibleWindowLocation) &&
	 *			|	matchesTileSizeNbTilesWindowDimensionWindowLocation(this.getTileSize(), this.getNbTilesX(), this.getVisibleWindowWidth(), horizontalVisibleWindowLocation)
	 */
	public boolean canHaveAsHorizontalVisibleWindowLocation(int horizontalVisibleWindowLocation){
		return isPossibleWindowLocation(horizontalVisibleWindowLocation) &&
				matchesTileSizeNbTilesWindowDimensionWindowLocation(this.getTileSize(), this.getNbTilesX(), this.getVisibleWindowWidth(), horizontalVisibleWindowLocation);
	}
	
	/**
	 * Check whether this world can have the given vertical visible window location as its vertical visible window location.
	 * @param 	verticalVisibleWindowLocation
	 * @return	|result==isPossibleWindowLocation(verticalVisibleWindowLocation)
	 *			|	&& matchesTileSizeNbTilesWindowDimensionWindowLocation(this.getTileSize(), this.getNbTilesY(), this.getVisibleWindowHeight(), verticalVisibleWindowLocation)
	 */
	public boolean canHaveAsVerticalVisibleWindowLocation(int verticalVisibleWindowLocation){
		return isPossibleWindowLocation(verticalVisibleWindowLocation)
				&& matchesTileSizeNbTilesWindowDimensionWindowLocation(this.getTileSize(), this.getNbTilesY(), this.getVisibleWindowHeight(), verticalVisibleWindowLocation);
	}
	
	/**
	 * Check whether the given Mazub matches with the given visible window parameters.
	 * @param 	mazub
	 * @param 	horizontalVisibleWindowLocation
	 * @param 	verticalVisibleWindowLocation
	 * @param 	visibleWindowWidth
	 * @param 	visibleWindowHeight
	 * @return	False if this world can not have the given visible window as its visible window.
	 * 			Otherwise true if Mazub is null.
	 * 			Otherwise true if Mazub is entirely located within the visible window and the distance between any border of Mazub and the border of the visible
	 * 			window is not smaller than 200 unless the distance between one of Mazub's borders and on of the borlders of the game world is less than 200.
	 * 			| if (!this.canHaveAsHorizontalVisibleWindowLocation(horizontalVisibleWindowLocation) || !this.canHaveAsVerticalVisibleWindowLocation(verticalVisibleWindowLocation)
	 *			|	|| !this.canHaveAsVisibleWindowWidth(visibleWindowWidth) || !this.canHaveAsVisibleWindowHeight(visibleWindowHeight))
	 *			|	result==false
	 *			| else if (mazub==null)
	 *			| 	result==true
	 *			| else if ((mazub.getEffectiveHorizontalLocation()<horizontalVisibleWindowLocation) || (mazub.getEffectiveHorizontalLocation()+mazub.getWidth()>horizontalVisibleWindowLocation+visibleWindowWidth) 
	 *			|	|| (mazub.getEffectiveVerticalLocation()<verticalVisibleWindowLocation) || (mazub.getEffectiveVerticalLocation()+mazub.getHeight()>verticalVisibleWindowLocation+visibleWindowHeight))
	 *			|		result==false
	 *			| else if ((mazub.getEffectiveHorizontalLocation()<200 && horizontalVisibleWindowLocation!=0) || 
	 *			|	(mazub.getEffectiveHorizontalLocation()+mazub.getWidth()>horizontalVisibleWindowLocation+visibleWindowWidth-200 && horizontalVisibleWindowLocation+visibleWindowWidth!=this.getWorldWidth())
	 *			|	|| (mazub.getEffectiveVerticalLocation()<200 && verticalVisibleWindowLocation!=0) ||
	 *			|	(mazub.getEffectiveVerticalLocation()+mazub.getHeight()>verticalVisibleWindowLocation+visibleWindowHeight-200 && verticalVisibleWindowLocation+visibleWindowHeight!=this.getWorldHeight()))
	 *			|	result==false;
	 *			|else
	 *			|	result==true;
	 */
	public boolean matchesMazubVisibleWindow(Mazub mazub, int horizontalVisibleWindowLocation, int verticalVisibleWindowLocation, int visibleWindowWidth, int visibleWindowHeight){
		if (!this.canHaveAsHorizontalVisibleWindowLocation(horizontalVisibleWindowLocation) || !this.canHaveAsVerticalVisibleWindowLocation(verticalVisibleWindowLocation)
				|| !this.canHaveAsVisibleWindowWidth(visibleWindowWidth) || !this.canHaveAsVisibleWindowHeight(visibleWindowHeight)){
			return false;
		}
		if (mazub==null){
			return true;
		}
		else if ((mazub.getEffectiveHorizontalLocation()<horizontalVisibleWindowLocation) || (mazub.getEffectiveHorizontalLocation()+mazub.getWidth()>horizontalVisibleWindowLocation+visibleWindowWidth) 
					|| (mazub.getEffectiveVerticalLocation()<verticalVisibleWindowLocation) || (mazub.getEffectiveVerticalLocation()+mazub.getHeight()>verticalVisibleWindowLocation+visibleWindowHeight)){
				return false;
		}
		else if ((mazub.getEffectiveHorizontalLocation()<200 && horizontalVisibleWindowLocation!=0) || 
				(mazub.getEffectiveHorizontalLocation()+mazub.getWidth()>horizontalVisibleWindowLocation+visibleWindowWidth-200 && horizontalVisibleWindowLocation+visibleWindowWidth!=this.getWorldWidth())
				|| (mazub.getEffectiveVerticalLocation()<200 && verticalVisibleWindowLocation!=0) ||
				(mazub.getEffectiveVerticalLocation()+mazub.getHeight()>verticalVisibleWindowLocation+visibleWindowHeight-200 && verticalVisibleWindowLocation+visibleWindowHeight!=this.getWorldHeight())){
			return false;
		}
		else{
			return true;
		}
				
	}
	
	/**
	 * Variable registering the tileSize of this World.
	 */
	private final int tileSize;
	
	/**
	 * Variable registering the number of tiles in the horizontal direction.
	 */
	private final int nbTilesX;
	
	/**
	 * Variable registering the number of tiles in the vertical direction.
	 */
	private final int nbTilesY;
	
	/**
	 * Variable registering the visible window width in pixels.
	 */
	private final int visibleWindowWidth;
	
	/**
	 * Variable registering the visible window height in pixels.
	 */
	private final int visibleWindowHeight;
	
	/**
	 * Variable registering the initial visible window location when creating a new world for all worlds.
	 */
	private static final int[] initialVisibleWindowLocation={0,0};	
	/**
	 * An array registering the current pixel coordinates of the bottom left pixel of the visible window.
	 */
	private int [] visibleWindowLocation;

	/**
	 * Returns the total number of tiles of this World.
	 * 
	 * @return | result ==  this.getNbTilesX() * this.getNbTilesY()
	 */
	@Raw
	@Immutable
	public int getNbTiles(){
		return this.getNbTilesX() * this.getNbTilesY();
	}
	
	/**
	 * Returns the tile values of this World as matrix. The first index of the matrix denotes the horizontal
	 * tile location of the tile and the second index the vertical tile location. The value at that position in the matrix is the 
	 * tile value at that tile position in the world. 
	 */
	@Basic
	public int [][] getTiles(){
		return this.tiles.clone();
	}
	
	/**
	 * Return the tile at the given tile position.
	 * @param 	horizontalTilePosition
	 * 			The horizontal tile position of the tile.
	 * @param 	verticalPosition
	 * 			The vertical tile position of the tile.
	 * @return	|this.getTiles()[horizontalPosition][verticalPosition]
	 */
	public int getTileValueAtTilePosition(int horizontalTilePosition,int verticalTilePosition) 
			throws IllegalArgumentException{
		if(!canHaveAsTilePosition(horizontalTilePosition, verticalTilePosition))
			throw new IllegalArgumentException("Not a valid tile position");
		return this.getTiles()[horizontalTilePosition][verticalTilePosition];
	}
	
	/**
	 * Set the tile value at the given tile position to the given value.
	 * @param 	tile
	 * 			The given value of the tile.
	 * @param 	horizontalPosition
	 * 			The horizontal tile position of the tile.
	 * @param 	verticalPosition
	 * 			The vertical tile position of the tile.
	 * @post	|new.getTileValueAtTilePosition(horizontalTilePosition, verticalTilePosition) == tile
	 * @throws 	IllegalArgumentException
	 * 			|!isValidTileValue(tile)
	 * @throws	IllegalArgumentException
	 * 			|!canHaveAsTilePosition(horizontalPosition, verticalPosition)
	 */
	public void setTileValueAtTilePosition(int horizontalTilePosition,int verticalTilePosition, int tile)
			throws IllegalArgumentException{
		if(!isValidTileValue(tile))
			throw new IllegalArgumentException("Not a valid tile!");
		if(!canHaveAsTilePosition(horizontalTilePosition, verticalTilePosition))
			throw new IllegalArgumentException("Not a valid tile position!");
		this.tiles [horizontalTilePosition][verticalTilePosition] = tile;
	}
	
	/**
	 * Create a matrix full of zero's with given dimensions 
	 * representing the tiles of the World.
	 * @param 	nbTilesX
	 * 			The number of tiles in the horizontal direction.
	 * @param 	nbTilesY
	 * 			The number of tiles in the vertical direction.
	 * @post	|new.getTiles() == new int [nbTilesX] [nbTilesY]
	 * @throws	IllegalArgumentException
	 * 			|!this.canHaveAsNbTiles(nbTilesX) || !this.canHaveAsNbTiles(nbTilesY)
	 */
	@Raw
	private void createTiles(int nbTilesX, int nbTilesY) throws IllegalArgumentException{
		if(!this.canHaveAsNbTilesX(nbTilesX) || !this.canHaveAsNbTilesY(nbTilesY))
			throw new IllegalArgumentException("Not a valid number of tiles");
		this.tiles = new int [nbTilesX] [nbTilesY]; 
	}
	
	/**
	 * Check whether the tile matrix can have the given tile position as 
	 * its tile position.
	 * @param 	horizontalTilePosition
	 * 			The horizontal tile position of the tile.
	 * @param 	verticalTilePosition
	 * 			The vertical tile position of the tile.
	 * @return	|(0 <= horizontalTilePosition &&  horizontalTilePosition < this.getNbTilesX())
				|	&& (0 <=  verticalTilePosition &&  verticalTilePosition < this.getNbTilesY())
	 */
	@Raw
	public boolean canHaveAsTilePosition(int horizontalTilePosition,int verticalTilePosition){
		return (0 <= horizontalTilePosition &&  horizontalTilePosition < this.getNbTilesX())
				&& (0 <=  verticalTilePosition &&  verticalTilePosition < this.getNbTilesY());
	}
	
	/**
	 * Check whether the given tile is a valid tile value.
	 * @param 	tile
	 * 			The value of the tile to be checked.
	 * @return	| result == (0 <= tileValue && tileValue <= NB_OF_GEOLOGICAL_FEATURES)
	 */
	@Raw
	public boolean isValidTileValue(int tileValue){
		return (0 <= tileValue && tileValue <= NB_OF_GEOLOGICAL_FEATURES);
	}
	
	/**
	 * A matrix registering the values of all tiles of this World. The first index of the matrix denotes the horizontal
	 * tile position and the second index denotes the vertical tile position. The value at a certain matrix position equals the
	 * tile value at a certain tile position for each position.
	 */
	private int [][] tiles;
	
	
	
	/**
	 * Check whether the given pixel location can be a pixel location in this world.
	 * @param 	pixelX
	 * 			The horizontal location of the pixel.
	 * @param 	pixelY
	 * 			The vertical location of the pixel.
	 * @return	|(0 <= pixelX && pixelX <= getWorldWidth())
	 *			|	&& (0 <= pixelY && pixelY <= getWorldHeight())
	 */
	@Raw
	public boolean canHaveAsPixelLocation(int pixelX, int pixelY){
		return (0 <= pixelX && pixelX < this.getWorldWidth())
				&& (0 <= pixelY && pixelY < this.getWorldHeight());
	}
	
	
	/**
	 * Return the size of the given game world as an array consisting of the horizontal and vertical number of pixels.
	 * @return |result == {this.getTileSize() * this.getNbTilesX(),this.getTileSize() * this.getNbTilesY()}
	 */
	@Basic
	@Immutable
	public int [] getWorldSizeInPixels(){
		int[] worldSizeInPixels={this.getTileSize() * this.getNbTilesX(),this.getTileSize() * this.getNbTilesY()};
		return worldSizeInPixels;
	}
	
	/**
	 * Return the width of this world in pixels.
	 */
	@Immutable
	public int getWorldWidth(){
		return getWorldSizeInPixels() [0];
	}
	
	/**
	 *Returns the height of this world in pixels.
	 */
	@Immutable
	public int getWorldHeight(){
		return getWorldSizeInPixels() [1];
	}
	
	/**
	 * Return the tile location of the target tile as an array consisting of the horizontal tile position of the target tile
	 * and the vertical tile position of the target tile.
	 */
	@Basic
	@Immutable
	public int [] getTargetTile(){
		return this.targetTile.clone();
	}
	
	/**
	 * Return the horizontal tile position of the target tile.
	 * 
	 * @return	| this.getTargetTile() [0];
	 */
	@Immutable
	public int getTargetTileX(){
		return this.getTargetTile() [0];
	}
	
	/**
	 * Return the vertical tile position of the target tile.
	 * 
	 * @return	| this.getTargetTile() [1];
	 */
	@Immutable
	public int getTargetTileY(){
		return getTargetTile() [1];
	}
	
	/**
	 * Set horizontal and vertical tile position of the target tile of this world to the given horizontal and vertical tile positions.
	 * @param	targetTileX
	 * 			A horizontal tile position of the target tile.
	 * @param	targetTileY
	 * 			A vertical tile location of the target tile.
	 * @post	|new.getTargetTileX() == targetTileX
	 *			|tnew.getTargetTileY() == targetTileY
	 * @throws	IllegalArgumentException
	 * 			|!canHaveAsTilePosition(targetTileX, targetTileY)
	 */
	@Raw
	private void setTargetTile(int targetTileX, int targetTileY)
		throws IllegalArgumentException{
		if(!canHaveAsTilePosition(targetTileX, targetTileY))
			throw new IllegalArgumentException("Not a valid position for the target tile");
		this.targetTile [0] = targetTileX;
		this.targetTile [1] = targetTileY;
	}

	/**
	 * Array of two integers registering the location of the target tile (X,Y).
	 */
	private final int[] targetTile;
	
	/**
	 * Return the bottom left pixel location of the tile with the given tile position.
	 * @param 	tileX
	 * 			The horizontal tile position of the tile.
	 * @param 	tileY
	 * 			The vertical tile position of the tile.
	 * @return	| result ==  { this.getTileSize() * tileX, this.getTileSize() * tileY}
	 */
	public int [] getBottomLeftPixelOfTile(int tileX, int tileY)
		throws IllegalArgumentException{
		if(!canHaveAsTilePosition(tileX, tileY))
			throw new IllegalArgumentException("Not a valid tile position!");
		int [] pixelLocation = { this.getTileSize() * tileX, this.getTileSize() * tileY};
		return  pixelLocation;
	}
	
	/**
	 * Return the tile position at the given pixel location as an array of 2 elements with
	 * the horizontal and vertical tile position in that order.
	 * 
	 * @param 	pixelX
	 * 			The horizontal location of the pixel.
	 * @param 	pixelY
	 * 			The vertical location of the pixel.
	 * @return	|result == { pixelX/this.getTileSize(), pixelY/this.getTileSize()}
	 * @throws 	IllegalArgumentException
	 * 			|!canHaveAsPixelLocation(pixelX, pixelY)
	 */
	public int [] getTilePositionAtPixelLocation(int pixelX, int pixelY)
		throws IllegalArgumentException{
		if(!canHaveAsPixelLocation(pixelX, pixelY))
			throw new IllegalArgumentException("Not a valid pixel position!");
		int [] tile = { pixelX/this.getTileSize(), pixelY/this.getTileSize()};
		return tile;
	}
	
	/**
	 * Return the tile positions of all tiles within the given rectangular
	 * region of the world.
	 * 
	 * @param 	pixelLeft
	 *           The horizontal location of the left side of the rectangular region.
	 * @param 	pixelBottom
	 *           The vertical location of the bottom side of the rectangular region.
	 * @param 	pixelRight
	 *           The horizontal location of the right side of the rectangular region.
	 * @param 	pixelTop
	 *           The vertical location of the top side of the rectangular region.
	 * 
	 * @return An array of tile positions, where each position (x_T, y_T) is
	 *         represented as an array of 2 elements, containing the horizontal
	 *         (x_T) and vertical (y_T) coordinate of a tile in that order.
	 *         The returned array is ordered from left to right,
	 *         bottom to top: all positions of the bottom row (ordered from
	 *         small to large x_T) precede the positions of the row above that.
	 *         | int[] startTile=this.getTileAtPixelPosition(pixelLeft, pixelBottom)
	 *         | int[] stopTile=int [] stopTile = this.getTileAtPixelPosition(pixelRight, pixelTop)
	 *         | int nbRows=stopTile[1] - startTile[1] + 1
	 *         | int int nbColumns = stopTile[0] - startTile[0] + 1
	 *         | int[][] tilePositions= new int[nbRow*nbColumns][2]
	 *         | for (row=0..nbRows)
	 *         |	for (column=0..nbColumns)
	 *         |		tilePositions [row*nbColumns + column][0] = startTile[0]+column
	 *		   |		tilePositions [row*nbColumns + column][1] = startTile[1]+row
	 *		   | result==tilePositions
	 *
	 * @throws	IllegalArgumentException
	 * 			|(!canHaveAsPixelLocation(pixelLeft, pixelBottom) 
	 *			| || !canHaveAsPixelLocation(pixelRight, pixelTop))	
	 * 
	 */
	public int [][] getTilePositionsIn(int pixelLeft, int pixelBottom, 
			int pixelRight, int pixelTop) 
		throws IllegalArgumentException{
		if(!this.canHaveAsPixelLocation(pixelLeft, pixelBottom) 
				|| !this.canHaveAsPixelLocation(pixelRight, pixelTop))
			throw new IllegalArgumentException("Not a valid pixel location!");
		int [] startTile = this.getTilePositionAtPixelLocation(pixelLeft, pixelBottom);
		int [] stopTile = this.getTilePositionAtPixelLocation(pixelRight, pixelTop);
		int nbRows = stopTile[1] - startTile[1] + 1;
		int nbColumns = stopTile[0] - startTile[0] + 1;
		int [][] tilePositions = new int [nbRows * nbColumns][2];
		for (int row = 0; row < nbRows; row++ ){
			for(int column = 0; column < nbColumns; column++){
				tilePositions [row*nbColumns + column][0] = startTile[0]+column;
				tilePositions [row*nbColumns + column][1] = startTile[1]+row;
				
			}
		}
		return tilePositions;
	}
	
	/**
	 * Returns the tile value of the tile at the given pixel location.
	 * 
	 * @param 	pixelX
	 * 			The horizontal location of the pixel.
	 * @param 	pixelY
	 * 			The vertical location of the pixel.
	 * @return	|result == this.getTileValueAtTilePosition(this.getTileAtPixelPosition(pixelX,pixelY)[0],this.getTileAtPixelPosition(pixelX,pixelY)[1])
	 * @throws 	IllegalArgumentException
	 * 			|!this.canHaveAsPixelLocation(pixelX, pixelY)
	 */
	public int getTileValueAtPixelLocation(int pixelX, int pixelY) throws IllegalArgumentException {
		if(!canHaveAsPixelLocation(pixelX, pixelY))
			throw new IllegalArgumentException("Not a valid pixel position!");
		int tile [] = getTilePositionAtPixelLocation(pixelX, pixelY);
		return getTileValueAtTilePosition(tile[0], tile[1]);
	}
	
	/**
	 * Variable registering the number of geological features in this world, we also refer to this as the tile value of a tile.
	 */
	private final static int NB_OF_GEOLOGICAL_FEATURES = 4;
	
	/**
	 * Check with which types of geological features this pixel area overlaps.
	 * It returns an array with a boolean at the position of the corresponding 
	 * geological feature. So if the area overlaps with geological feature one,
	 * the boolean at position one will be true.
	 * @param 	horizontalLocation
	 * 			The horizontal pixel location of the area.
	 * @param 	verticalLocation
	 * 			The vertical pixel location of the area.
	 * @param 	areaWidth
	 * 			The width of the area in pixels.
	 * @param 	areaHeight
	 * 			The height of the area in pixels.
	 * @return	| boolean [] coincidesWithTerrain= {false,false,false,false}
	 * 			| for (tiles=0..this.getTilePositionsIn(horizontalLocation, verticalLocation, 
	 *			|					horizontalLocation+areaWidth, verticalLocation+areaHeight).length)
	 *			|		for(index=0..NB_OF_GEOLOGICAL_FEATURES)
	 *			|			coincidesWithTerrain[index]=true;
	 *			| result==coincidesWithTerrain
	 * @throws 	IllegalArgumentException
	 * 			|!this.canHaveAsPixelLocation(horizontalLocation, verticalLocation) 
				|	|| !this.canHaveAsPixelLocation(horizontalLocation + areaWidth, 
				|		verticalLocation + areaHeight)
	 */
	public boolean [] areaCoincidesWithTerrain(int horizontalLocation, int verticalLocation, int areaWidth, int areaHeight) throws IllegalArgumentException{
		if(!this.canHaveAsPixelLocation(horizontalLocation, verticalLocation) 
				|| !this.canHaveAsPixelLocation(horizontalLocation + areaWidth, 
						verticalLocation + areaHeight))
			throw new IllegalArgumentException();
		boolean [] coincidesWithTerrain= {false,false,false,false};
		int coincidingTiles[][]= this.getTilePositionsIn(horizontalLocation, verticalLocation, 
				horizontalLocation+areaWidth, verticalLocation+areaHeight);
		for(int tiles=0; tiles<coincidingTiles.length; tiles++){
			for(int index = 0; index < NB_OF_GEOLOGICAL_FEATURES; index++)
				if(this.getTileValueAtTilePosition(coincidingTiles[tiles][0],coincidingTiles[tiles][1])==index){
					coincidesWithTerrain [index] = true;
			}
		}
		return coincidesWithTerrain;
	}
	
	/**
	 * Return whether the game in this world has already started or not.
	 */
	@Basic
	public boolean getGameHasStarted(){
		return this.gameHasStarted;
	}
	
	/**
	 * Set the start status of this world.
	 * 
	 * @param 	start
	 * 			The given start status of this world.
	 * @post	|new.getGameHasStarted()==start
	 */
	public void setGameHasStarted(boolean start){
		this.gameHasStarted = start;
	}
	
	/**
	 * Variable registering whether the game in this world has already started or not.
	 */
	private boolean gameHasStarted = false;
	
	/**
	 * Returns whether the current game is over or not.
	 */
	@Basic
	public boolean getGameOver(){
		return this.gameOver;
	}
	
	/**
	 * Set the current game over status..
	 * @param 	isGameOver
	 * 			The given status of the game in this world.
	 * @post	|new.getGameOver()==isGameOver
	 */
	void setGameOver(boolean isGameOver){
		this.gameOver = isGameOver;
	}
	
	/**
	 * Variable registering whether the game in this world is over or not.
	 */
	private boolean gameOver = false;
	
	/**
	 * Return whether the game in this world was won or not.
	 * The game has been won if the player has reached the finish before game over.
	 */
	@Basic
	public boolean getDidPlayerWin(){
			return this.didPlayerWin;
	}
	
	/**
	 * Set the player win status to the given status if the game is over.
	 * @param 	didPlayerWin
	 * 			The result of the game.
	 * @post	| if(this.getGameOver())
	 * 			|	new.getDidPlayerWin()==didPlayerWin
	 * 			| else
	 * 			|	result==false
	 */
	public void setDidPlayerWin(boolean didPlayerWin){
		if(this.getGameOver())
			this.didPlayerWin = didPlayerWin;	
		else
			this.didPlayerWin = false;
	}
	
	/**
	 * Variable registering whether the game of the given world was won by the player.
	 */
	private boolean didPlayerWin = false;
	
	/**
	 * Advance the time of this world and all its game objects by the given time duration.
	 * @param deltaTime
	 */
	public void advanceTime(double deltaTime){
		for(int index = 0; index < this.getNbGameObjects(); index++){
			GameObject gameObject = getGameObjectAtIndex(index);
			if(canHaveAsGameObject(gameObject))
				gameObject.advanceTime(deltaTime);
			if(gameObject!=null && gameObject.getHitPoints()<= 0 && index!=0)
				gameObject.terminate();
		}
		this.checkGameOver();
		this.updateVisibleWindow();
	}
	
	/**
	 * Update the visible window location of this game world depending on the current size and location of its Mazub.
	 * @post	|if (this.getMazub().getEffectiveHorizontalLocation()<=200)
	 *			|	new.getVisibleWindowLocation()[0]==0;
	 *			|else if (this.getMazub().getEffectiveHorizontalLocation()+this.getMazub().getWidth()>=this.getWorldWidth()-200)
	 *			|	new.getVisibleWindowLocation()[0]==this.getWorldWidth()-this.getVisibleWindowWidth()-1
	 *			|else if (this.getMazub().getEffectiveHorizontalLocation()<=this.getVisibleWindowLocation()[0]+200)
	 *			|	new.getVisibleWindowLocation()[0]==this.getMazub().getEffectiveHorizontalLocation()-200
	 *			|else if (this.getMazub().getEffectiveHorizontalLocation()+this.getMazub().getWidth()>=this.getVisibleWindowLocation()[0]+this.getVisibleWindowWidth()-200)
	 *			|	new.getVisibleWindowLocation()[0]==this.getMazub().getEffectiveHorizontalLocation()+this.getMazub().getWidth()+200-this.getVisibleWindowWidth()
	 *			|if(this.getMazub().getEffectiveVerticalLocation()<=200)
	 *			|	new.getVisibleWindowLocation[1]==0
	 *			|else if (this.getMazub().getEffectiveVerticalLocation()+this.getMazub().getHeight()>=this.getWorldHeight()-200)
	 *			|	new.getVisibleWindowLocation[1]==this.getWorldHeight()-this.getVisibleWindowHeight()-1
	 *			|else if (this.getMazub().getEffectiveVerticalLocation()<=this.getVisibleWindowLocation()[1]+200)
	 *			|	new.getVisibleWindowLocation[1]==this.getMazub().getEffectiveVerticalLocation()-200
	 *			|else if (this.getMazub().getEffectiveVerticalLocation()+this.getMazub().getHeight()>=this.getVisibleWindowLocation()[1]+this.getVisibleWindowHeight()-200)
	 *			|	new.getVisibleWindowLocation[1]==this.getMazub().getEffectiveVerticalLocation()+this.getMazub().getHeight()+200-this.getVisibleWindowHeight()
	 */
	private void updateVisibleWindow(){
		int horizontalWindowPosition=this.getVisibleWindowLocation()[0];
		int verticalWindowPosition=this.getVisibleWindowLocation()[1];
		if (this.getMazub().getEffectiveHorizontalLocation()<=200){
			horizontalWindowPosition=0;
		}
		else if (this.getMazub().getEffectiveHorizontalLocation()+this.getMazub().getWidth()>=this.getWorldWidth()-200){
			horizontalWindowPosition=this.getWorldWidth()-this.getVisibleWindowWidth()-1;
		}
		else if (this.getMazub().getEffectiveHorizontalLocation()<=this.getVisibleWindowLocation()[0]+200){
			horizontalWindowPosition=this.getMazub().getEffectiveHorizontalLocation()-200;
		}
		else if(this.getMazub().getEffectiveHorizontalLocation()+this.getMazub().getWidth()>=this.getVisibleWindowLocation()[0]+this.getVisibleWindowWidth()-200){
			horizontalWindowPosition=this.getMazub().getEffectiveHorizontalLocation()+this.getMazub().getWidth()+200-this.getVisibleWindowWidth();
		}
		if (this.getMazub().getEffectiveVerticalLocation()<=200){
			verticalWindowPosition=0;
		}
		else if (this.getMazub().getEffectiveVerticalLocation()+this.getMazub().getHeight()>=this.getWorldHeight()-200){
			verticalWindowPosition=this.getWorldHeight()-this.getVisibleWindowHeight()-1;
		}
		else if (this.getMazub().getEffectiveVerticalLocation()<=this.getVisibleWindowLocation()[1]+200){
			verticalWindowPosition=this.getMazub().getEffectiveVerticalLocation()-200;
		}
		else if(this.getMazub().getEffectiveVerticalLocation()+this.getMazub().getHeight()>=this.getVisibleWindowLocation()[1]+this.getVisibleWindowHeight()-200){
			verticalWindowPosition=this.getMazub().getEffectiveVerticalLocation()+this.getMazub().getHeight()+200-this.getVisibleWindowHeight();
		}
		this.setVisibleWindowLocation(horizontalWindowPosition, verticalWindowPosition);
	}
	
	/**
	 * Update the game over state of this world depending on the current state of its Mazub.
	 * @post	|if(this.getMazub().getHitPoints()<=0)
	 * 			|	this.setGameOver(true);
	 * 			|else
	 * 			|	int coincidingTiles[][]= this.getTilePositionsIn(this.getMazub().getEffectiveHorizontalLocation(), this.getMazub().getEffectiveVerticalLocation(), 
	 *			|							this.getMazub().getEffectiveHorizontalLocation()+this.getMazub().getWidth(), this.getMazub().getEffectiveVerticalLocation()+this.getMazub().getHeight())
	 * 			|	for(tiles=0..coincidingTiles.length)
	 * 			|		if(coincidingTiles[tiles][0] == this.getTargetTileX() && coincidingTiles[tiles][1]==this.getTargetTileY())
	 *			|			new.getGameOver()==true
	 *			|			new.getDidPlayerWin()==true
	 *			|			break
	 */
	private void checkGameOver(){
		if(this.getMazub().getHitPoints()<=0){
			this.setGameOver(true);
		}
		else{
			int coincidingTiles[][]= this.getTilePositionsIn(this.getMazub().getEffectiveHorizontalLocation(), this.getMazub().getEffectiveVerticalLocation(), 
					this.getMazub().getEffectiveHorizontalLocation()+this.getMazub().getWidth(), this.getMazub().getEffectiveVerticalLocation()+this.getMazub().getHeight());
			for(int tiles=0; tiles<coincidingTiles.length; tiles++){
				if(coincidingTiles[tiles][0] == this.getTargetTileX() && coincidingTiles[tiles][1]==this.getTargetTileY()){
					this.setGameOver(true);
					this.setDidPlayerWin(true);
					break;
				}
			}
		}
	}
	

	/**
	 * Check whether this world can have the given Mazub as its Mazub.
	 * 
	 * @param 	mazub
	 * 			The Mazub to be checked.
	 * @return	|if(!this.getGameHasStarted())
	 * 			|	if(mazub==null)
	 * 			|		result==true
	 * 			|	else
	 * 			|		result==mazub.canHaveAsLocation(mazub.getHorizontalLocation(),mazub.getVerticalLocation())
	 *			|		&& this.matchesMazubVisibleWindow(mazub, this.getVisibleWindowLocation()[0], this.getVisibleWindowLocation()[1],
	 *			|				this.getVisibleWindowWidth(),this.getVisibleWindowHeight())
	 * 			|else
	 * 			|	if(mazub==null)
	 * 			|		result==false
	 * 			|	if(mazub.isTerminate())
	 * 			|		result==(mazub==null)
	 * 			|	else
	 * 			|		result==mazub.canHaveAsLocation(mazub.getHorizontalLocation(),mazub.getVerticalLocation())
	 *			|		&& this.matchesMazubVisibleWindow(mazub, this.getVisibleWindowLocation()[0], this.getVisibleWindowLocation()[1],
	 *			|				this.getVisibleWindowWidth(),this.getVisibleWindowHeight())
	 *	
	 */
	public boolean canHaveAsMazub(Mazub mazub){
		if (!this.getGameHasStarted()){
			if(mazub==null){
				return true;
			}
			else{
				return mazub.canHaveAsLocation(mazub.getHorizontalLocation(),mazub.getVerticalLocation())
						&& this.matchesMazubVisibleWindow(mazub, this.getVisibleWindowLocation()[0], this.getVisibleWindowLocation()[1],
						this.getVisibleWindowWidth(),this.getVisibleWindowHeight());
			}
		}
		else{
			if(mazub==null){
				return false;
			}
			if(mazub.isTerminated()){
				return mazub==null;
			}
			else{
				return mazub.canHaveAsLocation(mazub.getHorizontalLocation(),mazub.getVerticalLocation())
						&& this.matchesMazubVisibleWindow(mazub, this.getVisibleWindowLocation()[0], this.getVisibleWindowLocation()[1],
								this.getVisibleWindowWidth(),this.getVisibleWindowHeight());
			}
		}
	}
	
	/**
	 * Set the Mazub of this world.
	 * @param 	alien
	 * 			The given alien of the class Mazub.
	 * @post	new.getMazub == Mazub
	 * @throws	IllegalArgumentException
	 * 			!(this.canHaveAsMazub(alien) && alien.getWorld()==this)
	 */
	public void setMazub(Mazub alien) throws IllegalArgumentException{
		if (!(this.canHaveAsMazub(alien))){
			throw new IllegalArgumentException("Not a valid Mazub!");
		}
		if(alien != null && alien.getWorld()!=this){
			throw new IllegalArgumentException("Not a valid Mazub!");
		}
		gameObjects.add(0,alien);
	}
	
	/**
	 * Return the Mazub of this world.
	 */
	@Basic
	public Mazub getMazub(){
		return (Mazub) gameObjects.get(0);
	}
	
	/**
	 * Return the number of game objects in this world.
	 * 
	 * @return	|gameObjects.size()
	 */
	@Raw
	public int getNbGameObjects(){
		return gameObjects.size();
	}
	
	/**
	 * Check whether the given number of game objects is a valid number of game objects.
	 * @param	nbGameObjects
	 * @return	result==(nbGameObjects<=100)
	 */
	public boolean isValidNbGameObjects(int nbGameObjects){
		return nbGameObjects<=100;
	}

	/**
	 * Add a game object to this world.
	 * @param  	gameObject
	 * 			The given game object.
	 * @post	|if(gameObject instanceof Mazub)
	 * 			|	new.getMazub()==gameObject
	 * 			|else
	 * 			|	new.getGameObjectAtIndex(getNbGameObjects - 1) == gameObject
	 * @throws 	IllegalArgumentException
	 * 			|!canHaveAsGameObject(gameObject)
	 */
	public void addAsGameObject(GameObject gameObject) throws IllegalArgumentException {
		if(gameObject instanceof Mazub){
			this.setMazub((Mazub) gameObject);
			return;
		}
		if(!canHaveAsGameObject(gameObject)|| gameObject.getWorld()!=this){
			throw new IllegalArgumentException("Not a valid GameObject!");
		}
		gameObjects.add(gameObject);
	}
	
	/**
	 * Remove the given game object from the list of game objects and set the world of the given game object to null.
	 * @param gameObject
	 * @post	|if(this.hasAsGameObject(gameObject)
	 * 			|	new.hasAsGameObject(gameObject)==false
	 * 			|	(new gameObject).getWorld()==null
	 */
	public void removeAsGameObject(GameObject gameObject){
		if(this.hasAsGameObject(gameObject)){
			this.gameObjects.remove(gameObject);
			gameObject.setWorld(null);
		}
	}
	
	/**
	 * Check whether this world can have the given game object as its game object.
	 * 
	 * @param 	gameObject
	 * 			The game object to be checked.
	 * @return	|if(gameObject==null)
	 * 			|	result==false
	 * 			|else if(!this.getGameHasStarted())
	 * 			|	result==gameObject.canHaveAsLocation(gameObject.getHorizontalLocation(),gameObject.getVerticalLocation())
	 *			|	&& isValidNbGameObjects(this.getNbGameObjects())
	 * 			|else
	 * 			|	if(gameObject.isTerminate())
	 * 			|		result==(gameObject==null)
	 * 			|	else
	 * 			|	result==gameObject.canHaveAsLocation(gameObject.getHorizontalLocation(),gameObject.getVerticalLocation())
	 *			|	&& isValidNbGameObjects(this.getNbGameObjects())
	 *	
	 */
	public boolean canHaveAsGameObject(GameObject gameObject){
		if(gameObject==null){
			return false;
		}
		else if (!this.getGameHasStarted()){
			return gameObject.canHaveAsLocation(gameObject.getHorizontalLocation(),gameObject.getVerticalLocation())
					&& isValidNbGameObjects(this.getNbGameObjects());
			
		}
		else{
			if(gameObject.isTerminated()){
				return gameObject==null;
			}
			else{
				return gameObject.canHaveAsLocation(gameObject.getHorizontalLocation(),gameObject.getVerticalLocation())
						&& isValidNbGameObjects(this.getNbGameObjects());
			}
		}
	}
	
	/**
	 * Check whether this world has proper game objects associated with it, this includes a proper Mazub.
	 * @return	|for(gamObject in gameObjects)
	 * 			|	if(!this.canHavaAsGameOject(gameObject))
	 * 			|		result==false
	 * 			|	if(gameObject.getWorld()!=this)
	 * 			|		result==false
	 * 			|	else
	 * 			|		result==true
	 */
	public boolean hasProperGameObjects(){
		for (GameObject gameObject : gameObjects) {
            if (!canHaveAsGameObject(gameObject))
                return false;
            if (gameObject.getWorld() != this)
                return false;
        }
        return true;
	}
	
	/**
	 * Check whether this world contains the given game object.
	 * @param 	gameObject
	 * 			The given game object.
	 * @return	|result==gameObjects.contains(gameObject);
	 */
	@Raw
	public boolean hasAsGameObject(GameObject gameObject) {
		return gameObjects.contains(gameObject);
	}
	
	/**
	 * Return the index of the given game object in the list of game objects.
	 * 
	 * @param 	gameObject
	 * 			The given game object.
	 * @return	| result == gameObjects.indexOf(gameObject)
	 */
	@Raw
	public int getIndexOfGameObject(GameObject gameObject) {
		return gameObjects.indexOf(gameObject);
	}
	
	/**
	 * Get the game object of this world at the given index.
	 * @param 	index
	 * 			The index of the game object in the list of game objects.
	 * @return	|result == gameObjects.get(index)
	 */
	public GameObject getGameObjectAtIndex(int index){
		return gameObjects.get(index);
	}
	
	
	/**
	 * List registering the game objects of this world.
	 * @invar	
	 */
	private List<GameObject> gameObjects = new ArrayList<GameObject>();

	/**
	 * Check whether the Mazub of this world is terminated.
	 * @return	|result == isTerminated
	 */
	public boolean isTerminated() {
		return isTerminated;
	}
	
	/**
	 * Check whether this world has a Mazub.
	 * 
	 * @return | result == this.getMazub()!=null;
	 */
	public boolean hasMazub(){
		return this.getMazub()!=null;
	}

	/**
	 * Terminate the this world.
	 * @effect	|if(!this.isTerminated)
	 * 			|	for(gameObject in this.gameObjects)
	 * 			|		gameObject.setWorld(null)
	 * @post		|new.getNbGameObjects()==0
	 * 			|new.isTerminated()==true
	 */
	public void terminate() {
		if(!this.isTerminated()){
			for(GameObject gameObject: this.gameObjects){
				gameObject.setWorld(null);
			}
			this.gameObjects.clear();
			this.isTerminated=true;
		}
	}

	
	/**
	 * Variable registering this world is terminated.
	 */
	private boolean isTerminated=false;
	
	/**
	 * Remove the given game object from this world.
	 * @param 	gameObject
	 * 			The game object to be removed.
	 * @effect	|if(gameObject != null && this.hasAsGameObject(gameObject)
	 * 			|	gameObject.setWorld(null)
	 * @post	|if(gameObject != null && this.hasAsGameObject(gameObject)
	 * 			|	new.contains(gameObject) == false
	 */
	public void removeGameObject(GameObject gameObject){
		if(gameObject != null && hasAsGameObject(gameObject))
			gameObjects.remove(gameObject);
			gameObject.setWorld(null);
	}
	
	/**
	 * Returns an array list containing the plants of this world.
	 * 
	 * @return	|List<Plant> plants = new ArrayList<Plant>()
	 *			|for(int index = 0..this.getNbGameObjects())
	 *			|	GameObject gameObject = getGameObjectAtIndex(index)
	 *			|	if(gameObject instanceof Plant)
	 *			|		plants.add((Plant) gameObject)
	 *			|result == plants
	 *		
	 */
	public List<Plant> getPlants(){
		List<Plant> plants = new ArrayList<Plant>();
		for(int index = 0; index < getNbGameObjects(); index++){
			GameObject gameObject = getGameObjectAtIndex(index);
			if(gameObject instanceof Plant){
				plants.add((Plant) gameObject);
			}
		}
		return plants;		
	}
	
	/**
	 * Returns a list containing the slimes of this world.
	 * 
	 * @return	|List<Slime> slimes = new ArrayList<Slime>()
	 *			|for(index=0..getNbGameObjects())
	 *			|	GameObject gameObject = getGameObjectAtIndex(index)
	 *			|	if(gameObject instanceof Slime)
	 *			|		slimes.add((Slime) gameObject)
	 *			|result==slimes
	 *		
	 */
	public List<Slime> getSlimes(){
		List<Slime> slimes = new ArrayList<Slime>();
		for(int index = 0; index < getNbGameObjects(); index++){
			GameObject gameObject = getGameObjectAtIndex(index);
			if(gameObject instanceof Slime){
				slimes.add((Slime) gameObject);
			}
		}
		return slimes;		
	}
	
	/**
	 * Returns a list containing the sharks of this world.
	 * 
	 * @return	|List<Shark> sharks = new ArrayList<Shark>()
	 *			|for(index = 0..this.getNbGameObjects())
	 *			|	GameObject gameObject = getGameObjectAtIndex(index)
	 *			|	if(gameObject instanceof Shark)
	 *			|		sharks.add((Slime) gameObject)
	 *			|result==sharks
	 *		
	 */
	public List<Shark> getSharks(){
		List<Shark> sharks = new ArrayList<Shark>();
		for(int index = 0; index < getNbGameObjects(); index++){
			GameObject gameObject = getGameObjectAtIndex(index);
			if(gameObject instanceof Shark){
				sharks.add((Shark) gameObject);
			}
		}
		return sharks;		
	}
}
