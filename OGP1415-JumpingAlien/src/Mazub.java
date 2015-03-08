import jumpingalien.util.Sprite;
import jumpingalien.util.Util;
import be.kuleuven.cs.som.annotate.*;


/**
 * @invar	The bottom-left pixel must always be inside the boundaries of the world.
 * 			|isValidLocation(getHorizontalLocation(),getVerticalLocation())
 * @invar	The absolute value of the velocity of Mazub must always be in the range 
 * 			from initialHorizontalVelocity to maximumHorizontalvelocity or be 
 * 			equal to zero m/s.
 * 			|canHaveAsHorizontalVelocity(getHorizontalVelocity())
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
	 * @param ducking
	 * 		  Parameter to tell if Mazub is ducking or not.
	 * @param initialHorizontalVelocity
	 * 		  The initial horizontal velocity of Mazub when he starts running.
	 * @param maximumHorizontalVelocityNotDucking
	 * 		  The maximum horizontal velocity of Mazub while he is not ducking.
	 * @param images
	 * 		  Sprites to display Mazub.
	 */
	@Raw
	public  Mazub(int horizontalLocation, int verticalLocation, double horizontalVelocity,
				double verticalVelocity, boolean ducking, double initialHorizontalVelocity,
				double maximumHorizontalVelocityNotDucking, Sprite... images){
		setHorizonalLocation(horizontalLocation);
		setVerticalLocation(verticalLocation);
		setHorizontalVelocity(horizontalVelocity);
		setVerticalVelocity(verticalVelocity);
		setDucking(ducking);
		this.initialHorizontalVelocity = initialHorizontalVelocity;
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
	
	public double getHorizontalLocationNotRounded(){
		return this.horizontalLocationNotRounded;
	}
	
	public void setHorizontalLocationNotRounded(double horizontalLocationNotRounded){
		this.horizontalLocationNotRounded=horizontalLocationNotRounded;
	}
	
	private double horizontalLocationNotRounded=0;
	
	/**
	 * Set the horizontal location of Mazub to the given location.
	 * 
	 * @param 	horizontalLocation
	 * 		  	The new horizontal location.
	 * @post  	This horizontal location is set as the new horizontal location.
	 * 		  	|new.getHorizontalLocation() = this.horizontalLocation
	 * @throws	IllegalArgumentException
	 * 			The given horizontal location is not valid.
	 * 			|!isValidHorizontalLocation(horizontalLocation)	
	 */
	public void setHorizonalLocation(int horizontalLocation)
			throws IllegalArgumentException {
		if(!isValidHorizontalLocation(horizontalLocation))
			throw new IllegalArgumentException();
		this.horizontalLocation = horizontalLocation;
	}
	
	private int horizontalLocation = 0;
	
	/**
	 * Check whether the given horizontal location is a valid location.
	 * 
	 * @param 	horizontalLocation
	 * 		  	The horizontal location.
	 * @return	True if and only if the horizontal location is an integer number
	 * 			in the range of 0 to maximumHorizontalLocation.
	 * 			|result == ((horizontalLocation >= 0 ) && (horizontalLocation <= maximumHorizontalLocation));
	 */
	@Basic
	public static boolean isValidHorizontalLocation(int horizontalLocation){
		return ((horizontalLocation >= 0 ) && (horizontalLocation <= getMaximumHorizontalLocation()));
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
	@Basic
	public static boolean isValidVerticalLocation(int verticalLocation){
		return ((verticalLocation >= 0 ) && (verticalLocation <= getMaximumVerticalLocation()));
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
	public static boolean isValidLocation(int horizontalLocation, int verticalLocation){
		return (isValidHorizontalLocation(horizontalLocation) && isValidVerticalLocation(verticalLocation));
	}
	
	
	@Basic 
	@Raw
	/**
	 * Return the vertical location of this Mazub.
	 */
	public int getVerticalLocation() {
		return this.verticalLocation;
	}
	
	/**
	 * Set the vertical location of Mazub to the given location.
	 * 
	 * @param 	verticalLocation
	 * 		  	The new vertical location.
	 * @post  	This vertical location is set as the new vertical location.
	 * 		  	|new.getVerticalLocation() = verticalLocation
	 * @throws	IllegalArgumentException
	 * 			The given vertical location is not valid.
	 * 			|!isValidVerticalLocation(verticalLocation)	  
	 */
	@Basic @Raw
	public void setVerticalLocation(int verticalLocation) 
			throws IllegalArgumentException {
		if(!isValidVerticalLocation(verticalLocation))
			throw new IllegalArgumentException();
		this.verticalLocation = verticalLocation;
	}
	
	public double getVerticalLocationNotRounded(){
		return this.verticalLocationNotRounded;
	}
	
	public void setVerticalLocationNotRounded(double verticalLocationNotRounded){
		this.verticalLocationNotRounded = verticalLocationNotRounded;
	}
	
	private int verticalLocation = 0;
	
	private double verticalLocationNotRounded = 0;
	
	
	/**
	 * Check whether the given horizontal velocity is a valid horizontal velocity.
	 * 
	 * @param 	horizontalVelocity
	 * 			The horizontal velocity.
	 * @return	True if and only if the absolute value of the velocity is in the range 
	 * 			from initialHorizontalVelocity to maximumHorizontalvelocity or is 
	 * 			equal to zero m/s.
	 * 			|result == (Util.fuzzyGreaterThanOrEqualTo(abs(horizontalVelocity),getInitialHorizontalVelocity()) 
				  && Util.fuzzyLessThanOrEqualTo(abs(horizontalVelocity), getMaximumHorizontalVelocity()))
				  || Util.fuzzyEquals(horizontalVelocity, 0);
	 */
	public boolean canHaveAsHorizontalVelocity(double horizontalVelocity){
		horizontalVelocity = Math.abs(horizontalVelocity);
		return (Util.fuzzyGreaterThanOrEqualTo(horizontalVelocity,this.getInitialHorizontalVelocity()) 
				&& Util.fuzzyLessThanOrEqualTo(horizontalVelocity, this.getMaximumHorizontalVelocity()))
				|| Util.fuzzyEquals(horizontalVelocity, 0);
	}
	
	
	/**
	 * Returns the horizontal velocity of this Mazub.
	 */
	@Basic 
	@Raw
	public double getHorizontalVelocity() {
		return this.horizontalVelocity;
	}
	
	
	/**
	 * Set the horizontal velocity of Mazub to the given horizontal velocity.
	 * 
	 * @param 	horizontalVelocity
	 * 			The horizontal velocity.
	 * @post	The given horizontal velocity is set as the new horizontal velocity
	 * 			of Mazub.
	 * 			|new.getHorizontalVelocity() = horizontalVelocity
	 * @throws	IllegalArgumentException
	 * 			The given horizontal velocity is not valid.
	 * 			|!canHaveAsHorizontalVelocity(horizontalVelocity)
	 */
	@Basic @Raw
	public void setHorizontalVelocity(double horizontalVelocity) 
		throws IllegalArgumentException{
		if(!canHaveAsHorizontalVelocity(horizontalVelocity))
			throw new IllegalArgumentException();
		this.horizontalVelocity = horizontalVelocity;
	}
	
	private double horizontalVelocity = 0;

	
	@Basic @Raw
	public double getVerticalVelocity() {
		return this.verticalVelocity;
	}
	
	public void setVerticalVelocity(double verticalVelocity) {
		this.verticalVelocity = verticalVelocity;
	}
	
	private double verticalVelocity = 0;
	
	@Basic @Immutable
	public static double getHorizontalAcceleration(){
		return horizontalAcceleration;
	}
	
	private static final double horizontalAcceleration = 0.9;
	
	public static final double VERTICAL_ACCELERATION = -10;
	
	@Basic @Raw
	public boolean isDucking() {
		return this.ducking;
	}
	
	@Basic @Raw
	public void setDucking(boolean ducking) {
		this.ducking = ducking;
	}
	
	private boolean ducking = false;
	
	@Basic @Raw
	public double getInitialHorizontalVelocity(){
		return this.initialHorizontalVelocity;		
	}
	
	private final double initialHorizontalVelocity;
	
	private static final double INITIAL_VERTICAL_VElOCITY = 8;
	
	@Basic @Raw
	public double getMaximumHorizontalVelocity(){
		return this.maximumHorizontalVelocity;
	}
	
	@Basic @Raw
	public void setMaximumHorizontalVelocity(double maximumHorizontalVelocity){
		this.maximumHorizontalVelocity = maximumHorizontalVelocity;
	}
	
	private double maximumHorizontalVelocity=getMaximumHorizontalVelocityNotDucking();
	
	@Basic @Raw
	public double getMaximumHorizontalVelocityNotDucking(){
		return this.maximumHorizontalVelocityNotDucking;
	}
	
	private final double maximumHorizontalVelocityNotDucking;
	
	@Basic @Immutable
	public static int getMaximumHorizontalLocation(){
		return maximumHorizontalLocation;
	}
	
	private final static int maximumHorizontalLocation = 1023;
	
	@Basic @Immutable
	public static int getMaximumVerticalLocation(){
		return maximumVerticalLocation;
	}
	
	private final static int maximumVerticalLocation = 767;
	
	private Sprite[] images;
	
	/**
	 * 
	 * @param 	direction
	 * 			The direction in which the Mazub will move.
	 * @pre		The given direction must be equal to -1 or 1. -1 denoting left and 1 denoting right.
	 * 			| (direction == -1) || (direction == 1)
	 * @post 	The new horizontal velocity of this Mazub is equal to direction*this.getInitialHorizontalVelocity()
	 * 			| new.getHorizontalVelocity() == direction*this.getInitialHorizontalVelocity()
	 */
	public void startMove(int direction){
		assert ((direction == -1) || (direction == 1)) :
			"Precondition: direction must be equal to -1 or 1";
		this.setHorizontalVelocity(direction*this.getInitialHorizontalVelocity());
	}
	
	/**
	 * @post	The new horizontal velocity of this Mazub is equal to 0.
	 * 			| new.getHorizontalVelocity() == 0
	 */
	public void endMove(){
		this.setHorizontalVelocity(0);
	}
	
	public void advanceTime(double deltaTime){
		updateHorizontalLocation(deltaTime);
		updateVerticalLocation(deltaTime);
		updateHorizontalVelocity(deltaTime);
		updateVerticalVelocity(deltaTime);
	}

	private void updateHorizontalLocation(double deltaTime) {
		this.setHorizontalLocationNotRounded(this.getHorizontalLocationNotRounded() + 
				100*(this.getHorizontalVelocity()*deltaTime + 
				Math.signum(this.getHorizontalVelocity())*0.5*getHorizontalAcceleration()*Math.pow(deltaTime, 2)));
		this.setHorizonalLocation( (int) Math.floor(this.getHorizontalLocationNotRounded()));
	}
	
	private void updateVerticalLocation(double deltaTime) {
		int notOnGround = (Util.fuzzyEquals(getVerticalVelocity(), 0)) ? 0 : 1;
		this.setVerticalLocationNotRounded(this.getVerticalLocationNotRounded() + 
				100*(getVerticalVelocity()*deltaTime + notOnGround* 0.5*VERTICAL_ACCELERATION*Math.pow(deltaTime,2)));
		this.setVerticalLocation( (int) Math.floor(this.getVerticalLocationNotRounded()));
	}
	
	private void updateHorizontalVelocity(double deltaTime) {
		double newVelocity = getHorizontalVelocity() + 100*Math.signum(getHorizontalVelocity())*getHorizontalAcceleration()*deltaTime;
		if (Math.abs(newVelocity) > this.getMaximumHorizontalVelocity()){
			this.setHorizontalVelocity(Math.signum(newVelocity)*this.getMaximumHorizontalVelocity());
		}
		else{
			this.setHorizontalVelocity(newVelocity);
		}
	}
	
	private void updateVerticalVelocity(double deltaTime) {
		int notOnGround = (Util.fuzzyEquals(getVerticalVelocity(), 0)) ? 0 : 1;
		this.setVerticalVelocity(getVerticalVelocity() + 100*notOnGround*VERTICAL_ACCELERATION*deltaTime);
	}
	
	public void startJump(){
		setVerticalVelocity(INITIAL_VERTICAL_VElOCITY);
	}
	
	public void endJump(){
		setVerticalVelocity(0);
	}
	
	public void startDuck(){
		this.setDucking(true);
		this.setMaximumHorizontalVelocity(1);
		
	}
	
	public void endDuck(){
		this.setDucking(false);
		this.setMaximumHorizontalVelocity(getMaximumHorizontalVelocityNotDucking());
	}
	
	public Sprite getCurrentSprite(int i){
		return this.images[i];
	}
	
	
	
	
	
	
	
}
