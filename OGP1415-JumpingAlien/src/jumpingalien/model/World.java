package jumpingalien.model;

import be.kuleuven.cs.som.annotate.*;

import java.util.*;



/**
 * A class which represent a game world.
 * 
 * @invar	Each game world can have its height as is height.
 * 			|canHaveAsWorldHeight(getWorldHeight())
 * @invar	Each game world can have its width as its width.
 * 			|canHaveAsWorldWidth(getWorldWidth())
 * @invar	A game world must contain at least one player character Mazub and
 * 			no more than 100 other game objects.
 * 			|isValidAmountOfGameObjects(getAmountOfMazub(),getAmountOfOtherGameObjects())
 * @invar	The visible window may not be bigger than the game world for each game world.
 * 			|canHaveAsVisibleWindow()
 * @invar	If possible the window shall always be positioned so that there are 
 * 			at least 200 pixels between all pixels occupied by Mazub.
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
	 * @throws	IllegalArgumentException
	 * 			|!isValidTileSize(tileSize)
	 * @throws	IllegalArgumentException
	 * 			|!isValidNbTiles(nbTilesX)
	 * @throws	IllegalArgumentException
	 * 			|!isValidNbTiles(nbTilesY)
	 * @throws	IllegalArgumentException
	 * 			|!canHaveAsWindowWidth(visibleWindowWidth)
	 * @throws	IllegalArgumentException
	 * 			|!canHaveAsWindowHeight(visibleWindowHeight)
	 * @throws	IllegalArgumentException
	 * 			|
	 */
	@Raw
	public World(int tileSize, int nbTilesX, int nbTilesY, int visibleWindowWidth, int visibleWindowHeight,
			int targetTileX, int targetTileY, Mazub alien) throws IllegalArgumentException{
		if(!isValidTileSize(tileSize))
			throw new IllegalArgumentException("Not a valid tile size!");
		this.tileSize = tileSize;
		if(!isValidNbTiles(nbTilesX))
			throw new IllegalArgumentException("Not a valid number of tiles!");
		this.nbTilesX = nbTilesX;
		if(!isValidNbTiles(nbTilesY))
			throw new IllegalArgumentException("Not a valid number of tiles!");
		this.nbTilesY = nbTilesY;
		createTiles(getNbTilesX(),getNbTilesY());
		this.worldSizeInPixels = new int [2];
		setWorldSizeInPixels();
		if(!canHaveAsWindowWidth(visibleWindowWidth))
			throw new IllegalArgumentException("Not a window width!");
		this.visibleWindowWidth = visibleWindowWidth;
		if(!canHaveAsWindowHeight(visibleWindowHeight))
			throw new IllegalArgumentException("Not a window height!");
		this.visibleWindowHeight = visibleWindowHeight;
		//To be worked out:
		this.setVisibleWindow(0, 0, visibleWindowWidth, visibleWindowHeight);
		this.targetTile = new int [2];
		setTargetTile(targetTileX,targetTileY);
		this.setMazub(alien);
		
	}
	
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
	 * Check whether the given tile size is a valid tile size.
	 * 
	 * @param 	tileSize
	 * 			The size of the given tile to check.
	 * @return	| result == tilseSize > 0
	 */
	@Raw
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
	@Raw
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
	 * Returns the number of tiles of this World.
	 * 
	 * @return | result ==  this.getNbTilesX() * this.getNbTilesY()
	 */
	@Raw
	@Immutable
	public int getNbTiles(){
		return this.getNbTilesX() * this.getNbTilesY();
	}
	
	/**
	 * Returns the tiles of this World.
	 */
	@Basic
	public int [][] getTiles(){
		return this.tiles.clone();
	}
	
	/**
	 * Return the tile at the given position.
	 * @param 	horizontalPosition
	 * 			The horizontal position of the tile.
	 * @param 	verticalPosition
	 * 			The vertical position of the tile.
	 * @return	|this.getTiles()[horizontalPosition][verticalPosition]
	 */
	public int getTileValueAtTilePosition(int horizontalPosition,int verticalPosition) 
			throws IllegalArgumentException{
		if(!canHaveAsTilePosition(horizontalPosition, verticalPosition))
			throw new IllegalArgumentException("Not a valid tile position");
		return this.getTiles()[horizontalPosition][verticalPosition];
	}
	
	/**
	 * Set the tile at the given position of tile to the new given value.
	 * @param 	tile
	 * 			The given value of the tile.
	 * @param 	horizontalPosition
	 * 			The horizontal position of the tile.
	 * @param 	verticalPosition
	 * 			The vertical position of the tile.
	 * @post	|new.getTileAt(horizontalPosition, verticalPosition) == tile
	 * @throws 	IllegalArgumentException
	 * 			|!isValidTile(tile)
	 * @throws	IllegalArgumentException
	 * 			|!canHaveAsTilePosition(horizontalPosition, verticalPosition)
	 */
	public void setTileAt(int tile, int horizontalPosition,int verticalPosition) 
			throws IllegalArgumentException{
		if(!isValidTileValue(tile))
			throw new IllegalArgumentException("Not a valid tile!");
		if(!canHaveAsTilePosition(horizontalPosition, verticalPosition))
			throw new IllegalArgumentException("Not a valid tile position!");
		this.tiles [horizontalPosition][verticalPosition] = tile;
	}
	
	/**
	 * Create a matrix full of zero's with given dimensions 
	 * representing the tiles of the World.
	 * @param 	nbTilesX
	 * 			The number of tiles in the horizontal direction.
	 * @param 	nbTilesY
	 * 			The number of tiles in the vertical direction.
	 * @post	|new.getTiles == new int [nbTilesX] [nbTilesY]
	 * @throws	IllegalArgumentException
	 * 			|!isValidNbTiles(nbTilesX) || !isValidNbTiles(nbTilesY)
	 */
	@Raw
	private void createTiles(int nbTilesX, int nbTilesY) throws IllegalArgumentException{
		if(!isValidNbTiles(nbTilesX) || !isValidNbTiles(nbTilesY))
			throw new IllegalArgumentException("Not a valid number of tiles");
		this.tiles = new int [nbTilesX] [nbTilesY]; 
	}
	
	/**
	 * Check whether the tile matrix can have the given tile position as 
	 * its position.
	 * @param 	horizontalPosition
	 * 			The horizontal position of the tile.
	 * @param 	verticalPosition
	 * 			The vertical position of the tile.
	 * @return	|(0 <= horizontalPosition &&  horizontalPosition < this.getNbTilesX())
				|	&& (0 <=  verticalPosition &&  verticalPosition < this.getNbTilesY())
	 */
	@Raw
	public boolean canHaveAsTilePosition(int horizontalPosition,int verticalPosition){
		return (0 <= horizontalPosition &&  horizontalPosition < this.getNbTilesX())
				&& (0 <=  verticalPosition &&  verticalPosition < this.getNbTilesY());
	}
	
	/**
	 * Check whether the given tile is a valid tile.
	 * 
	 * @param 	tile
	 * 			The value of the tile.
	 * @return	| result == (0 <= tile && tile <= 4)
	 */
	@Raw
	public boolean isValidTileValue(int tile){
		return (0 <= tile && tile <= 4);
	}
	
	/**
	 * A matrix registering the values of all tiles of this World.
	 */
	private int [][] tiles;
	
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
	 * Return the current visible window pixel coordinates in the order
	 * left, bottom, right, top.
	 */
	@Basic
	public int [] getVisibleWindow(){
		return this.visibleWindow.clone();
	}
	
	/**
	 * Set the current visible window pixel coordinates in the order
	 * left, bottom, right, top.
	 * 
	 * @param 	left
	 * 			The location of the left border.
	 * @param 	bottom
	 * 			The location of the bottom border.
	 * @param 	right
	 * 			The location of the right border.
	 * @param 	top
	 * 			The location of the top border.
	 * @throws 	IllegalArgumentException
	 * 			|!canHaveAsPixelLocation(left, bottom) || !canHaveAsPixelLocation(right, top)
	 */
	@Raw
	private void setVisibleWindow(int left, int bottom, int right, int top)
		throws IllegalArgumentException {
		if(!canHaveAsPixelLocation(left, bottom) || !canHaveAsPixelLocation(right, top))
			throw new IllegalArgumentException("Not a valid pixel location!");
		int [] window = {left, bottom, right, top};
		this.visibleWindow = window;
	}
	
	/**
	 * Check whether the given pixel location can be a pixel location.
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
	 * Check whether the display window can have the given window width as its
	 * window width
	 * 
	 * @param 	windowWidth
	 * 			The given window width
	 * @return	| result == windowWidth <= this.getWorldWidth()
	 */
	@Raw
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
	@Raw
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
	
	/**
	 * An array registering the pixel coordinates of the visible window, in the order
	 * left, bottom, right, top.
	 */
	private int [] visibleWindow;
	
	/**
	 * Returns the size of the given game world, in number of pixels.
	 * 
	 * @return |result == this.worldSizeInPixels.clone()
	 */
	@Basic
	@Immutable
	public int [] getWorldSizeInPixels(){
		return this.worldSizeInPixels.clone();
	}
	
	/**
	 * Returns the width of the given World.
	 */
	@Immutable
	public int getWorldWidth(){
		return getWorldSizeInPixels() [0];
	}
	
	/**
	 *Returns the height of the given World.
	 */
	@Immutable
	public int getWorldHeight(){
		return getWorldSizeInPixels() [1];
	}
	
	/**
	 * Set horizontal and vertical size in pixels of this World.
	 * 
	 * @post	|this.worldSizeInPixels [0] = this.getTileSize() * this.getNbTilesX();
	 *			|this.worldSizeInPixels [1] = this.getTileSize() * this.getNbTilesY();
	 */
	@Raw
	private void setWorldSizeInPixels(){
		this.worldSizeInPixels [0] = this.getTileSize() * this.getNbTilesX();
		this.worldSizeInPixels [1] = this.getTileSize() * this.getNbTilesY();
	}
	
	/**
	 * Variable registering the world size of the game world, in pixels, as an array of two
	 * elements: width (X) and height (Y), in that order.
	 */
	private final int [] worldSizeInPixels;
	
	/**
	 * Returns the location of the target tile.
	 */
	@Basic
	@Immutable
	public int [] getTargetTile(){
		return this.targetTile.clone();
	}
	
	/**
	 * Returns the horizontal location of the target tile.
	 * 
	 * @return	| this.getTargetTile() [0];
	 */
	@Immutable
	public int getTargetTileX(){
		return this.getTargetTile() [0];
	}
	
	/**
	 * Returns the vertical location of the target tile.
	 * 
	 * @return	| this.getTargetTile() [1];
	 */
	@Immutable
	public int getTargetTileY(){
		return getTargetTile() [1];
	}
	
	/**
	 * Set horizontal and vertical location of the target tile of this World.
	 * 
	 * @post	|this.targetTile [0] = targetTileX;
	 *			|this.targetTile [1] = targetTileY;
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
	 * Return the bottom left pixel of the tile with the given position.
	 * @param 	tileX
	 * 			The horizontal position of the tile.
	 * @param 	tileY
	 * 			The vertical position of the tile.
	 * @return	| result ==  { length * tileX, length * tileY}
	 */
	public int [] getBottomLeftPixelOfTile(int tileX, int tileY)
		throws IllegalArgumentException{
		if(!canHaveAsTilePosition(tileX, tileY))
			throw new IllegalArgumentException("Not a valid tile position!");
		int length = getTileSize();
		int [] pixelLocation = { length * tileX, length * tileY};
		return  pixelLocation;
	}
	
	/**
	 * Return the tile at the given pixel position as an array of 2 elements with
	 * the horizontal and vertical position in that order.
	 * 
	 * @param 	pixelX
	 * 			The horizontal location of the pixel.
	 * @param 	pixelY
	 * 			The vertical location of the pixel.
	 * @return	|result == { pixelX/length, pixelY/length}
	 * @throws 	IllegalArgumentException
	 * 			|!canHaveAsPixelLocation(pixelX, pixelY)
	 */
	public int [] getTileAtPixelPosition(int pixelX, int pixelY)
		throws IllegalArgumentException{
		if(!canHaveAsPixelLocation(pixelX, pixelY))
			throw new IllegalArgumentException("Not a valid pixel position!");
		int length = getTileSize(); 
		int [] tile = { pixelX/length, pixelY/length};
		return tile;
	}
	
	/**
	 * Returns the tile positions of all tiles within the given rectangular
	 * region.
	 * 
	 * @param 	pixelLeft
	 *           The x-coordinate of the left side of the rectangular region.
	 * @param 	pixelBottom
	 *           The y-coordinate of the bottom side of the rectangular region.
	 * @param 	pixelRight
	 *           The x-coordinate of the right side of the rectangular region.
	 * @param 	pixelTop
	 *           The y-coordinate of the top side of the rectangular region.
	 * 
	 * @return An array of tile positions, where each position (x_T, y_T) is
	 *         represented as an array of 2 elements, containing the horizontal
	 *         (x_T) and vertical (y_T) coordinate of a tile in that order.
	 *         The returned array is ordered from left to right,
	 *         bottom to top: all positions of the bottom row (ordered from
	 *         small to large x_T) precede the positions of the row above that.
	 *
	 * @throws	IllegalArgumentException
	 * 			|(!canHaveAsPixelLocation(pixelLeft, pixelBottom) 
				| || !canHaveAsPixelLocation(pixelRight, pixelTop))	
	 * 
	 */
	public int [][] getTilePositionsIn(int pixelLeft, int pixelBottom, 
			int pixelRight, int pixelTop) 
		throws IllegalArgumentException{
		if(!canHaveAsPixelLocation(pixelLeft, pixelBottom) 
				|| !canHaveAsPixelLocation(pixelRight, pixelTop))
			throw new IllegalArgumentException("Not a valid pixel location!");
		int [] startTile = getTileAtPixelPosition(pixelLeft, pixelBottom);
		int [] stopTile = getTileAtPixelPosition(pixelRight, pixelTop);
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
	 * Returns the geological feature of the tile at the given pixel position.
	 * 
	 * @param 	pixelX
	 * 			The horizontal location of the pixel.
	 * @param 	pixelY
	 * 			The vertical location of the pixel.
	 * @return	|result == getTileValueAtTilePosition(tile[0], tile[1])
	 * @throws 	IllegalArgumentException
	 * 			|!canHaveAsPixelLocation(pixelX, pixelY)
	 */
	public int getGeologicalFeatureAtPixel(int pixelX, int pixelY) throws IllegalArgumentException {
		if(!canHaveAsPixelLocation(pixelX, pixelY))
			throw new IllegalArgumentException("Not a valid pixel position!");
		int tile [] = getTileAtPixelPosition(pixelX, pixelY);
		return getTileValueAtTilePosition(tile[0], tile[1]);
	}
	
	/**
	 * Set the geological feature of the given tile to the given value.
	 * 
	 * @param 	tileX
	 * 			The horizontal location of the tile.
	 * @param 	tileY
	 * 			The vertical location of the tile.
	 * @param 	tileType
	 * 			The value of the given tile.
	 * @post	|this.tiles[tileX][tileY] = tileType;
	 */
	public void setGeologicalFeatureAtTile(int tileX, int tileY, int tileType){
		if(!canHaveAsTilePosition(tileX, tileY))
			throw new IllegalArgumentException("Not a valid tile position!");
		if(!isValidTileValue(tileType))
			throw new IllegalArgumentException("Not a valid tile value!");
		if(!getGameHasStarted())
			this.tiles[tileX][tileY] = tileType;
	}
	
	/**
	 * Variable registering the number of geological features in this world.
	 */
	private final static int NB_OF_GEOLOGICAL_FEATURES = 4;
	
	/**
	 * Check with which types of geological features this area overlaps.
	 * It returns an array with true or false at the position of the corresponding 
	 * geological feature. So if the area overlaps with geological feature one,
	 * the boolean at position one will be true.
	 * @param 	horizontalLocation
	 * 			The horizontal location of the area.
	 * @param 	verticalLocation
	 * 			The vertical location of the area.
	 * @param 	areaWidth
	 * 			The width of the area.
	 * @param 	areaHeight
	 * 			The height of the area.
	 * @return
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
	
	public int[][] areaOverlapsWithTiles(int horizontalLocation, int verticalLocation, int areaWidth, int areaHeight) throws IllegalArgumentException{
		if(!this.canHaveAsPixelLocation(horizontalLocation, verticalLocation) 
				|| !this.canHaveAsPixelLocation(horizontalLocation + areaWidth, 
						verticalLocation + areaHeight))
			throw new IllegalArgumentException();
		int coincidingTiles[][]= this.getTilePositionsIn(horizontalLocation, verticalLocation, 
				horizontalLocation+areaWidth, verticalLocation+areaHeight);
		return coincidingTiles.clone();
	}
	
	/**
	 *Returns whether the game in this world has already started or not.
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
	 * Set the status of gameOver.
	 * @param 	isGameOver
	 * 			The given status of the game in this world.
	 */
	private void setGameOver(boolean isGameOver){
		this.gameOver = isGameOver;
	}
	
	/**
	 * Variable registering whether the game in this world is over or not.
	 */
	private boolean gameOver = false;
	
	/**
	 * Returns whether the game in this world was won or not.
	 * The game has been won if the a player has reached the finish.
	 * The function returns false if the game has not ended yet
	 * or if the player did not win after the game ended.
	 */
	@Basic
	public boolean getDidPlayerWin(){
			return this.didPlayerWin;
	}
	
	/**
	 * Set the result of an ended game.
	 * @param 	didPlayerWin
	 * 			The result of the game.
	 */
	public void setDidPlayerWin(boolean didPlayerWin){
		if(this.getGameOver())
			this.didPlayerWin = didPlayerWin;	
		else
			this.didPlayerWin = false;
	}
	
	/**
	 * Variable registering whether the game of the given world was won by a player.
	 */
	private boolean didPlayerWin = false;
	
	public void advanceTime(double deltaTime){
		for(int index = 0; index < getNbOfGameObjects(); index++){
			GameObject gameObject = getGameObjectAtIndex(index);
			if(canHaveAsGameObject(gameObject))
				gameObject.advanceTime(deltaTime);
			this.updateVisibleWindow();
			this.checkGameOver();
			if(gameObject != null && gameObject.getHitPoints()<= 0)
				gameObject.unsetWorld();
		}
	}
	
	private void updateVisibleWindow(){
		int horizontalWindowPosition=this.getVisibleWindow()[0];
		int verticalWindowPosition=this.getVisibleWindow()[1];
		if (this.getMazub().getEffectiveHorizontalLocation()<=200){
			horizontalWindowPosition=0;
		}
		else if (this.getMazub().getEffectiveHorizontalLocation()+this.getMazub().getWidth()>=this.getWorldWidth()-200){
			horizontalWindowPosition=this.getWorldWidth()-this.getVisibleWindowWidth()-1;
		}
		else if (this.getMazub().getEffectiveHorizontalLocation()<=this.getVisibleWindow()[0]+200){
			horizontalWindowPosition=this.getMazub().getEffectiveHorizontalLocation()-200;
		}
		else if(this.getMazub().getEffectiveHorizontalLocation()+this.getMazub().getWidth()>=this.getVisibleWindow()[2]-200){
			horizontalWindowPosition=this.getMazub().getEffectiveHorizontalLocation()+this.getMazub().getWidth()+200-this.getVisibleWindowWidth();
		}
		if (this.getMazub().getEffectiveVerticalLocation()<=200){
			verticalWindowPosition=0;
		}
		else if (this.getMazub().getEffectiveVerticalLocation()+this.getMazub().getHeight()>=this.getWorldHeight()-200){
			verticalWindowPosition=this.getWorldHeight()-this.getVisibleWindowHeight()-1;
		}
		else if (this.getMazub().getEffectiveVerticalLocation()<=this.getVisibleWindow()[1]+200){
			verticalWindowPosition=this.getMazub().getEffectiveVerticalLocation()-200;
		}
		else if(this.getMazub().getEffectiveVerticalLocation()+this.getMazub().getHeight()>=this.getVisibleWindow()[3]-200){
			verticalWindowPosition=this.getMazub().getEffectiveVerticalLocation()+this.getMazub().getHeight()+200-this.getVisibleWindowHeight();
		}
		this.setVisibleWindow(horizontalWindowPosition, verticalWindowPosition, horizontalWindowPosition+this.getVisibleWindowWidth(), verticalWindowPosition+this.getVisibleWindowHeight());
	}
	
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
	 * Set the Mazub of this world.
	 * @param 	alien
	 * 			The given alien of the class Mazub.
	 * @post	|new.getMazub == Mazub
	 * 
	 */
	public void setMazub(Mazub alien){
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
	public int getNbOfGameObjects(){
		return gameObjects.size();
	}

	/**
	 * Add a game object to this world.
	 * 
	 * @param  	gameObject
	 * 			The given game object.
	 * @post	|new.getGameObjectAtIndex(getNbGameObjects - 1) == gameObject
	 * @throws 	IllegalArgumentException
	 * 			|!canHaveAsGameObject(gameObject)
	 */
	public void addAsGameObject(GameObject gameObject) throws IllegalArgumentException {
		if(!canHaveAsGameObject(gameObject)|| gameObject.getWorld()!=this){
			throw new IllegalArgumentException("Not a valid GameObject!");
		}
		gameObjects.add(gameObject);
	}
	
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
	 * 			The given game object.
	 * @return	|if(getNbOfGameObjects() > 100)
	 *			|	result == false;
	 *			|else if(gameObject==null){
	 *			|	result == false;
	 *			|else{
	 *			|	result == gameObject.canHaveAsLocation(gameObject.getHorizontalLocation()
	 *			|	,gameObject.getVerticalLocation());
	 *	
	 */
	public boolean canHaveAsGameObject(GameObject gameObject){
		if (this.isTerminated())
			return gameObject==null;
		else if(getNbOfGameObjects() > 100){
			return false;
		}
		else{
			return gameObject!=null && !gameObject.isTerminated() && gameObject.canHaveAsLocation(gameObject.getHorizontalLocation(),gameObject.getVerticalLocation());
		}
	}
	
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
	 * @return	|gameObjects.contains(gameObject);
	 */
	@Raw
	public boolean hasAsGameObject(GameObject gameObject) {
		return gameObjects.contains(gameObject);
	}
	
//	public boolean hasProperGameObjects(){
//		return (this.canHaveAsGameObject(this.getGameObject()) && this.getGameObject()==null) || this.getGameObject().getWorld()==this;
//	}
	
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
	 */
	private List<GameObject> gameObjects = new ArrayList<GameObject>();

	/**
	 * Check whether the Mazub of this world is terminated.
	 * 
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
	 * Terminate the Mazub of this world.
	 * 
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
	 * Variable registering if the Mazub of this world is terminated.
	 */
	private boolean isTerminated=false;
	
	/**
	 * Remove the given game object from this world.
	 * 
	 * @param 	gameObject
	 * 			The given game object.
	 * @post	|new.contains(gameObject) == false
	 */
	public void removeGameObject(GameObject gameObject){
		if(gameObject != null && hasAsGameObject(gameObject))
			gameObjects.remove(gameObject);
			gameObject.setWorld(null);
	}
	
	/**
	 * Returns a list containing the plants of this world.
	 * 
	 * @return	|List<Plant> plants = new ArrayList<Plant>();
	 *			|for(int index = 0; index < getNbOfGameObjects(); index++)
	 *			|	GameObject gameObject = getGameObjectAtIndex(index);
	 *			|	if(gameObject instanceof Plant)
	 *			|		plants.add((Plant) gameObject);
	 *			|result == plants
	 *		
	 */
	public List<Plant> getPlant(){
		List<Plant> plants = new ArrayList<Plant>();
		for(int index = 0; index < getNbOfGameObjects(); index++){
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
	 * @return	|List<Slime> slimes = new ArrayList<Slime>();
	 *			|for(int index = 0; index < getNbOfGameObjects(); index++){
	 *			|	GameObject gameObject = getGameObjectAtIndex(index);
	 *			|	if(gameObject instanceof Slime){
	 *			|		slimes.add((Slime) gameObject);
	 *			|return slimes;
	 *		
	 */
	public List<Slime> getSlimes(){
		List<Slime> slimes = new ArrayList<Slime>();
		for(int index = 0; index < getNbOfGameObjects(); index++){
			GameObject gameObject = getGameObjectAtIndex(index);
			if(gameObject instanceof Slime){
				slimes.add((Slime) gameObject);
			}
		}
		return slimes;		
	}
	
	/**
	 * Returns a list containing the slimes of this world.
	 * 
	 * @return	|List<Slime> slimes = new ArrayList<Slime>();
	 *			|for(int index = 0; index < getNbOfGameObjects(); index++){
	 *			|	GameObject gameObject = getGameObjectAtIndex(index);
	 *			|	if(gameObject instanceof Slime){
	 *			|		slimes.add((Slime) gameObject);
	 *			|return slimes;
	 *		
	 */
	public List<Shark> getSharks(){
		List<Shark> sharks = new ArrayList<Shark>();
		for(int index = 0; index < getNbOfGameObjects(); index++){
			GameObject gameObject = getGameObjectAtIndex(index);
			if(gameObject instanceof Shark){
				sharks.add((Shark) gameObject);
			}
		}
		return sharks;		
	}
}
