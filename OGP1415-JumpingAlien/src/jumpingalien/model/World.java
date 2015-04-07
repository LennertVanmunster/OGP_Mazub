package jumpingalien.model;

import be.kuleuven.cs.som.annotate.*;

/**
 * A class which represent a game world.
 * 
 * @invar	The width (X) of the game world should be a integer multiple
 * 			of the length of a tile.
 * 			|canHaveAsWorldHeight(getWorldHeight())
 * @invar	The height (Y) of the game world should be a integer multiple
 * 			of the length of a tile.
 * 			|canHaveAsWorldHeight(getWorldHeight())
 * @invar	A game world must contain at least one player character Mazub and
 * 			no more than 100 other game objects.
 * 			|isValidAmountOfGameObjects(getAmountOfMazub(),getAmountOfOtherGameObjects())
 * @invar	The visible window may not be bigger than the game world.
 * 			|canHaveAsVisibleWindow()
 * @invar	If possible the window shall always be positioned so that there are 
 * 			at least 200 pixels between alle pixels occupied by Mazub.
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
	 */
	@Raw
	public World(int tileSize, int nbTilesX, int nbTilesY, int visibleWindowWidth, int visibleWindowHeight,
			int targetTileX, int targetTileY) throws IllegalArgumentException{
		if(!isValidTileSize(tileSize))
			throw new IllegalArgumentException("Not a valid tile size!");
		this.tileSize = tileSize;
		if(!isValidNbTiles(nbTilesX))
			throw new IllegalArgumentException("Not a valid number of tiles!");
		this.nbTilesX = nbTilesX;
		if(!isValidNbTiles(nbTilesY))
			throw new IllegalArgumentException("Not a valid number of tiles!");
		this.nbTilesY = nbTilesY;
		createTiles();
		if(!canHaveAsWindowWidth(visibleWindowWidth))
			throw new IllegalArgumentException("Not a window width!");
		this.visibleWindowWidth = visibleWindowWidth;
		if(!canHaveAsWindowHeight(visibleWindowHeight))
			throw new IllegalArgumentException("Not a window height!");
		this.visibleWindowHeight = visibleWindowHeight;
		
		
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
	 * Check whether the given tile size is a valid tile size.
	 * 
	 * @param 	tileSize
	 * 			The size of the given tile to check.
	 * @return	| result == tilseSize > 0
	 */
	public boolean isValidTileSize(int tileSize){
		return tileSize > 0;
	}
	
	/**
	 * Check whether the given number of tiles is a valid number of tiles.
	 * 
	 * @param 	nbTiles
	 * 			The number of tiles to check.
	 * @return	| result == nbTiles > 0
	 */
	public boolean isValidNbTiles(int nbTiles){
		return nbTiles > 0;
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
	 * Return the visible window width
	 */
	public int getVisibleWindowWidth(){
		return this.visibleWindowWidth;
	}
	
	/**
	 * Return the visible window height
	 */
	public int getVisibleWindowHeight(){
		return this.visibleWindowHeight;
	}
	
	/**
	 * Check whether the display window can have the given window width as its
	 * window width
	 * 
	 * @param 	windowWidth
	 * 			The given window width
	 * @return	| result == windowWidth <= this.getWorldWidth()
	 */
	public boolean canHaveAsWindowWidth(int windowWidth){
		return windowWidth <= this.getWorldWidth();
		
	}
	
	/**
	 * Check whether the display window can have the given window height as its
	 * window height
	 * 
	 * @param 	windowHeight
	 * 			The given window height
	 * @return	| result == windowHeight <= this.getWorldHeight()
	 */
	public boolean canHaveAsWindowHeight(int windowHeight){
		return windowHeight <= this.getWorldHeight();
		
	}
	
	/**
	 * Variable registering the visible window width.
	 */
	private final int visibleWindowWidth;
	
	/**
	 * Variable registering the visible window height.
	 */
	private final int visibleWindowHeight;
}
