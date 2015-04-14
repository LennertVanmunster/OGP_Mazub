package jumpingalien.model;

import jumpingalien.util.Sprite;
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
	 * Return the current Sprite of this Mazub.
	 */
	public Sprite getCurrentSprite(){
		return this.getImages()[0];
	}
	
	/**
	 * Return a copy of the current image array of this Mazub.
	 */
	@Basic 
	@Raw
	public Sprite[] getImages(){
		return this.images.clone();
	}
	
	/**
	 * Return the number of images in the current image array of this Mazub.
	 */
	@Raw
	public int getNbImages(){
		return this.getImages().length;
	}
	
	/**
	 * Check whether the given number of images in the given image array is valid for all Mazubs.
	 * 
	 * @param 	nbImages
	 * 			The number of images to be checked.
	 * @return	The given number of images must be an even number and must be greater than or equal to 10;
	 * 			| result == (nbImages >= 10 && nbImages%2==0)
	 * 
	 */
	public abstract boolean isValidNbImages(int nbImages);
	
	/**
	 * Return the image in the image array of this Mazub at the given sprite index.
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
	 * 			smaller than or equal to the number of images in the current image array of this Mazub.
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
	 * Set the image array of this Mazub to the given image array.
	 * 
	 * @param 	images
	 * 			The new image array for this Mazub.
	 * @pre 	The length of the given image array must be a valid length.
	 * 			|isValidNbImages(images.length)
	 * @pre		The images in the given image array must all be valid images.
	 * 			| for i in 1..images.length:
	 * 			|	isValidImage(images[i])
	 * @post	The new image array of this Mazub is equal to copy of he given image array.
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
	 * Return the height of the currrent sprite of this Mazub.
	 */
	@Raw
	public int getHeight(){
		return this.getCurrentSprite().getHeight();
	}
	
	/**
	 * Return the width of the current sprite of this Mazub.
	 */
	@Raw
	public int getWidth(){
		return this.getCurrentSprite().getWidth();
	}

	/**
	 * Variable registering the array of images of this Mazub.
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
