import jumpingalien.util.Sprite;
import be.kuleuven.cs.som.annotate.*;


/**
 * @invar	The bottom-left pixel must always be inside the boundaries of the world.
 * 			|isValidLocation(getHorizontalLocation(),getVerticalLocation())
 * @invar	The absolute value of the velocity of Mazub must always be in the range 
 * 			from initialHorizontalVelocity to maximumHorizontalvelocity or be 
 * 			equal to zero m/s.
 * 			|isValidHorizontalVelocity(getHorizontalVelocity())
 * @invar	
 * 
 * @version 1.0
 * @authors Pieter Van Damme and Lennert Vanmunster
 *
 */
public class Mazub {
	/**
	 *Initialize a new Mazub with given horizontal and vertical location,
	 *horizontal and vertical velocity, initial and maximum horizontal velocity,
	 *ducking state and a set of sprites. 
	 //geen idee of the documentatie hierboven voldoende is?
	 * 
	 * @param horizontalLocation
	 * 		  The horizontal location of Mazub.
	 * @param verticalLocation
	 * 		  The vertical location of Mazub.
	 * @param horizontalVelocity
	 * 		  The horizontal velocity of Mazub.
	 * @param verticalVelocity
	 * 		  The vertical velocity of Mazub.
	 * @param horizontalAcceleration
	 * 		  The horizontal acceleration of Mazub.
	 * @param verticalAcceleration
	 * 		  The vertical acceleration of Mazub.
	 * @param isDucking
	 * 		  Parameter to tell if Mazub is ducking or not.
	 * @param initialHorizontalVelocity
	 * 		  The initial horizontal velocity of Mazub.
	 * @param maximumHorizontalVelocity
	 * 		  The maximum horizontal velocity of Mazub.
	 * @param images
	 * 		  Sprites to dislplay Mazub.
	 */
	@Raw
	public  Mazub(int horizontalLocation, int verticalLocation, double horizontalVelocity,
				double verticalVelocity,
				boolean isDucking, double initialHorizontalVelocity, double initialVerticalVelocity,
				double maximumHorizontalVelocityNotDucking, Sprite... images){
		setHorizonalLocation(horizontalLocation);
		setVerticalLocation(verticalLocation);
		setHorizontalVelocity(horizontalVelocity);
		setVerticalVelocity(verticalVelocity);
		setDucking(isDucking);
		this.initialHorizontalVelocity = initialHorizontalVelocity;
		this.initialVerticalVelocity = initialVerticalVelocity;
		this.maximumHorizontalVelocityNotDucking = maximumHorizontalVelocityNotDucking;
	}
	
	/**
	 * Return the horizontal location of this Mazub.
	 */
	@Basic 
	@Raw
	public int getHorizontalLocation(){
		return this.horizontalLocation;
	}
	
	/**
	 * Set the horizontal location of Mazub to the given location.
	 * 
	 * @param horizontalLocation
	 * 		  The new horizontal location.
	 * @post  This horizontal location is set as the new horizontal location.
	 * 		  |new.getHorizontalLocation() = this.horizontalLocation
	 */
	public void setHorizonalLocation(int horizontalLocation)
			throws IllegalArgumentException {
		if(!isValidHorizontalLocation(horizontalLocation))
			throw new IllegalArgumentException();
		this.horizontalLocation = horizontalLocation;
	}
	
	private int horizontalLocation;
	
	/**
	 * Check whether the given horizontal location is a valid location.
	 * 
	 * @param 	horizontalLocation
	 * 		  	The horizontal location.
	 * @return	True if and only if the horizontal location is an integer number
	 * 			in the range of 0 to maximumHorizontalLocation.
	 * 			|result == ((horizontalLocation >= 0 ) && (horizontalLocation <= maximumHorizontalLocation));
	 */
	@Raw
	public boolean isValidHorizontalLocation(int horizontalLocation){
		return ((horizontalLocation >= 0 ) && (horizontalLocation <= maximumHorizontalLocation));
	}
	
	
	/**
	 * Check whether the given vertical location is a valid location.
	 * 
	 * @param 	verticalLocation
	 * 		  	The vertical location.
	 * @return	True if and only if the vertical location is an integer number
	 * 			in the range of 0 to maximumVerticalLocation.
	 * 			|result == ((verticalLocation >= 0 ) && (verticalLocation <= maximumVerticalLocation));
	 */
	@Raw
	public boolean isValidVerticalLocation(int verticalLocation){
		return ((verticalLocation >= 0 ) && (verticalLocation <= maximumVerticalLocation));
	}
	
	/**
	 * Check whether the given location is a valid location.
	 * 
	 * @param	horizontalLocation
	 * 			The horizontal location.
	 * @param 	verticalLocation
	 * 		  	The vertical location.
	 * @return	True if and only if the horizontal and vertical location are valid locations.
	 * 			|result == (isValidHorizontalLocation(horizontalLocation) && isValidVerticalLocation(verticalLocation));
	 */
	@Raw
	public boolean isValidLocation(int horizontalLocation, int verticalLocation){
		return (isValidHorizontalLocation(horizontalLocation) && isValidVerticalLocation(verticalLocation));
	}
	
	
	@Basic 
	@Raw
	/**
	 * Return the vertical location of this Mazub.
	 */
	public int getVerticalLocation() {
		return verticalLocation;
	}
	
	/**
	 * Set the vertical location of Mazub to the given location.
	 * 
	 * @param verticalLocation
	 * 		  The new vertical location.
	 * @post  This vertical location is set as the new vertical location.
	 * 		  |new.getVerticalLocation() = this.verticalLocation
	 */
	public void setVerticalLocation(int verticalLocation) 
			throws IllegalArgumentException {
		if(!isValidVerticalLocation(verticalLocation))
			throw new IllegalArgumentException();
		this.verticalLocation = verticalLocation;
	}
	
	private int verticalLocation;
	
	@Basic @Raw
	public double getHorizontalVelocity() {
		return horizontalVelocity;
	}
	public void setHorizontalVelocity(double horizontalVelocity) {
		this.horizontalVelocity = horizontalVelocity;
	}
	
	private double horizontalVelocity;
	
	@Basic @Raw
	public double getVerticalVelocity() {
		return verticalVelocity;
	}
	
	public void setVerticalVelocity(double verticalVelocity) {
		this.verticalVelocity = verticalVelocity;
	}
	
	private double verticalVelocity;
	
	@Basic @Raw
	public double getHorizontalAcceleration(){
		return horizontalAcceleration;
	}
	
	private static final double horizontalAcceleration = 0.9;
	
	public static final double VERTICAL_ACCELERATION = -10;
	
	public boolean isDucking() {
		return isDucking;
	}
	public void setDucking(boolean isDucking) {
		this.isDucking = isDucking;
	}
	
	private boolean isDucking;
	
	@Basic @Raw
	public double getInitialHorizontalVelocity(){
		return this.initialHorizontalVelocity;		
	}
	
	private final double initialHorizontalVelocity;
	
	@Basic @Raw
	public double getInitialVerticalVelocity(){
		return this.initialVerticalVelocity;
	}
	
	private final double initialVerticalVelocity;
	
	@Basic @Raw
	public double getMaximumHorizontalVelocity(){
		return this.maximumHorizontalVelocity;
	}
	
	public void setMaximumHorizontalVelocity(double maximumHorizontalVelocity){
		this.maximumHorizontalVelocity = maximumHorizontalVelocity;
	}
	
	private double maximumHorizontalVelocity;
	
	private final double maximumHorizontalVelocityNotDucking;
	
	private static int horizontalNumberOfPixels=1024;
	
	private static int maximumHorizontalLocation = horizontalNumberOfPixels - 1;
	
	private static int verticalNumberOfPixels=768;
	
	private static int maximumVerticalLocation = verticalNumberOfPixels - 1;
	
	private static double pixelLength = 0.01;
	
	private Sprite[] images;
	
	public void startMove(int direction){			//-1=links, 1=rechts
		this.setHorizontalVelocity(direction*getInitialHorizontalVelocity());
	}
	
	public void endMove(){
		this.setHorizontalVelocity(0);
	}
	
	public void advanceTime(double deltaT){
		this.setHorizonalLocation( (int) (getHorizontalLocation() + getHorizontalVelocity()*deltaT + 0.5*getHorizontalAcceleration()*deltaT*deltaT));
		this.setVerticalLocation( (int) (getVerticalLocation() + getVerticalVelocity()*deltaT + 0.5*VERTICAL_ACCELERATION*deltaT*deltaT));
		this.setHorizontalVelocity(getHorizontalVelocity() + getHorizontalAcceleration()*deltaT);
		this.setVerticalVelocity(getVerticalVelocity() + VERTICAL_ACCELERATION*deltaT);
	}
	
	public void startJump(){
		setVerticalVelocity(getInitialVerticalVelocity());
	}
	
	public void endJump(){
		if (getVerticalVelocity()>0){
			setVerticalVelocity(0);
		}	
	}
	
	public void startDuck(){
		this.setDucking(true);
		this.setMaximumHorizontalVelocity(1);
		
	}
	
	public void endDuck(){
		this.setDucking(false);
		this.setMaximumHorizontalVelocity(maximumHorizontalVelocityNotDucking);
	}
	
	public Sprite getCurrentSprite(int i){
		return this.images[i];
	}
	
	
	
	
	
	
	
}
