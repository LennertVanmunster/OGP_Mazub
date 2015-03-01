import be.kuleuven.cs.som.annotate.Raw;
import be.kuleuven.cs.som.annotate.Basic;
import be.kuleuven.cs.som.annotate.Annotate;

/**
 * 
 * @author
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
	 */
	@Raw
	public  Mazub(int horizontalLocation, int verticalLocation, double horizontalVelocity,
				double verticalVelocity, double horizontalAcceleration, double verticalAcceleration,
				boolean isDucking, double initalHorizontalVelocity, double maximumHorizontalVelocity ){
		
	}
	
	private int horizontalLocation;
	
	private int verticalLocation;
	
	private double horizontalVelocity;
	
	private double verticalVelocity;
	
	private static final double horizontalAcceleration = 0.9;
	
	private static final double verticalAcceleration = -10;
	
	private boolean isDucking = false;
	
	private final double initalHorizontalVelocity;
	
	private final double maximumHorizontalVelocity;
	
	public void startMove(boolean direction){			//0=links, 1=rechts
		
	}
	
	public void endMove(){
		
	}
	
	public void advanceTime(double deltaT){
		
	}
	
	public void startJump(){
		
	}
	
	public void endJump(){
		
	}
	
	public void startDuck(){
		this.setIsDucking(true);
	}
	
	public void endDuck(){
		this.setIsDucking(false);
	}
	
	public Sprite getCurrentSprite(){
		
	}
	
	
	
	
	
	
}
