package jumpingalien.model;

import jumpingalien.util.Sprite;
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
		setHorizontalLocation(horizontalLocation);
		setVerticalLocation(verticalLocation);
		this.setImages(images);
	}
	
	@Override
	public boolean isValidNbImages(int nbImages){
		return nbImages == 2;
	}
	
	
}
