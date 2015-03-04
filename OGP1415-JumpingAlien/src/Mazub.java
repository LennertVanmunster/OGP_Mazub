import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import jumpingalien.util.Sprite;
import be.kuleuven.cs.som.annotate.*;


/**
 * @invar	The bottom-left pixel must always be inside the boundaries of the world.
 * 			|isValidPosition(getVerticalLocation(),getHorizontalLocation())
 * 
 * @version 1.0
 * @authors Pieter Van Damme and Lennert Vanmunster
 *
 */
public class Mazub {
	/**
	 * 
	 * @param horizontalLocation
	 * @param verticalLocation
	 * @param horizontalVelocity
	 * @param verticalVelocity
	 * @param horizontalAcceleration
	 * @param verticalAcceleration
	 * @param isDucking
	 * @param initalHorizontalVelocity
	 * @param maximumHorizontalVelocity
	 * @param images
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
	
	@Basic @Raw
	public int getHorizontalLocation(){
		return this.horizontalLocation;
	}
	
	public void setHorizonalLocation(int horizontalLocation){
		this.horizontalLocation = horizontalLocation;
	}
	
	private int horizontalLocation;
	
	public boolean isValidPosition(int verticalPosition,int horizontalPosition){
		return (verticalPosition >= 0) && ((horizontalPosition >= 0 ) && (horizontalPosition <= 0));
	}
	
	@Basic @Raw
	public int getVerticalLocation() {
		return verticalLocation;
	}
	
	public void setVerticalLocation(int verticalLocation) {
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
	
	private static int verticalNumberOfPixels=768;
	
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
