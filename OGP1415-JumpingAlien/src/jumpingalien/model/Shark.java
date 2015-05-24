package jumpingalien.model;

import java.util.List;
import java.util.Random;

import jumpingalien.programs.program.Program;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;
import be.kuleuven.cs.som.annotate.*;

/**
 * A class of sharks which extends the class of game objects. A shark is a non player character which is hostile to Mazub and is primarily found in water tiles.
 * @invar	Each shark can have its current action duration as its action duration.
 * 			|isValidActionDuration(getActionDuration())
 * @invar	Each shark can have its number of movements since its last jump as its number of movements since its last jump.
 * 			|isValidNbMovements(getNbMovementsSinceJump())
 * @invar	The random movement chance of all shark is a valid chance.
 * 			|isValidChance(getRandomMovementChance)
 * @invar	Each shark can have its current random diving multiplier as its random diving multiplier.
 * 			|isValidRandomDivingMultiplier(getRandomDivingMultiplier()) 
 * @version	2.0
 * @authors Pieter Van Damme and Lennert Vanmunster
 *
 */
public class Shark extends GameObject{
	
	/**
	 * Initialize a new shark as with given horizontal and vertical location, an array of sprites and all of its parameter constants. 
	 * @param 	horizontalLocation
	 * 			The horizontal location for this new shark.
	 * @param 	verticalLocation
	 * 			The vertical location for this new shark.
	 * @param 	program
	 * 			The program for this new shark.
	 * @param 	images
	 * 			An array of sprites.
	 * @effect 	This new shark is initialized as a game object with the given horizontal location, vertical location, a horizontal velocity of zero, a vertical velocity of zero,
	 * 			the initial horizontal velocity at spawn of sharks, the maximum horizontal velocity at spawn of sharks, 
	 * 			the initial vertical velocity for all sharks, the horizontal acceleration at spawn for sharks, a false ducking state, 
	 * 			a number of hit points equal to the shark hit point constant, a maximum number of hit points equal to the shark hit point constant, a program and an image array containing its sprites.
	 * 			| super(horizontalLocation, verticalLocation, 0, 0, initialHorizontalVelocityAtSpawn, maximumHorizontalVelocityAtSpawn, initialVerticalVelocity, horizontalAccelerationAtSpawn, false, HIT_POINTS, HIT_POINTS, program, images)
	 */
	@Raw
	public Shark(int horizontalLocation, int verticalLocation, Program program, Sprite... images){
		super(horizontalLocation, verticalLocation, 0, 0, initialHorizontalVelocityAtSpawn, maximumHorizontalVelocityAtSpawn, initialVerticalVelocity, horizontalAccelerationAtSpawn, false, HIT_POINTS, HIT_POINTS, program, images);
	}
	
	/**
	 * Initialize a new shark as with given horizontal and vertical location, an array of sprites and all of its parameter constants. 
	 * @param 	horizontalLocation
	 * 			The horizontal location for this new shark.
	 * @param 	verticalLocation
	 * 			The vertical location for this new shark.
	 * @param 	images
	 * 			An array of sprites.
	 * @effect 	This new shark is initialized as a game object with the given horizontal location, vertical location, a horizontal velocity of zero, a vertical velocity of zero,
	 * 			the initial horizontal velocity at spawn of sharks, the maximum horizontal velocity at spawn of sharks, 
	 * 			the initial vertical velocity for all sharks, the horizontal acceleration at spawn for sharks, a false ducking state, 
	 * 			a number of hit points equal to the shark hit point constant, a maximum number of hit points equal to the shark hit point constant, a program equal to null and an image array containing its sprites.
	 * 			| super(horizontalLocation, verticalLocation, 0, 0, initialHorizontalVelocityAtSpawn, maximumHorizontalVelocityAtSpawn, initialVerticalVelocity, horizontalAccelerationAtSpawn, false, HIT_POINTS, HIT_POINTS, null, images)
	 */
	@Raw
	public Shark(int horizontalLocation, int verticalLocation, Sprite... images){
		super(horizontalLocation, verticalLocation, 0, 0, initialHorizontalVelocityAtSpawn, maximumHorizontalVelocityAtSpawn, initialVerticalVelocity, horizontalAccelerationAtSpawn, false, HIT_POINTS, HIT_POINTS, null, images);
	}
	
	
	
	
	/**
	 * Check whether this shark can have the given horizontal velocity as its horizontal velocity.
	 * @param 	horizontalVelocity
	 * @return	True if the absolute value of the given horizontal velocity is equal to zero or greater than or equal to the initial horizontal velocity of this shark
	 * 			and less than or equal to the maximum horizontal velocity of this game object.
	 * 			| result== Util.fuzzyGreaterThanOrEqualTo(horizontalVelocity,this.getInitialHorizontalVelocityForUpdate()) 
	 *			&& Util.fuzzyLessThanOrEqualTo(horizontalVelocity, this.getMaximumHorizontalVelocityForUpdate())
	 *			|| Util.fuzzyEquals(horizontalVelocity, 0)
	 */
	@Raw
	@Override
	public boolean canHaveAsHorizontalVelocity(double horizontalVelocity){
		horizontalVelocity = Math.abs(horizontalVelocity);
		return Util.fuzzyGreaterThanOrEqualTo(horizontalVelocity,getInitialHorizontalVelocityForUpdate()) 
				&& Util.fuzzyLessThanOrEqualTo(horizontalVelocity, getMaximumHorizontalVelocityForUpdate())
				|| Util.fuzzyEquals(horizontalVelocity, 0);
	}
	
	/**
	 * Check whether the given initial horizontal velocity is a possible initial horizontal velocity for any shark.
	 * @return	True if the given initial horizontal velocity is equal to the initial horizontal velocity at spawn for sharks, denoting its
	 * 			immutability, and the given velocity is greater than or equal to zero.
	 * 			|result == (Util.fuzzyGreaterThanOrEqualTo(initialHorizontalVelocity, 0) && initialHorizontalVelocity==initialHorizontalVelocityAtSpawn)
	 */
	@Raw
	@Override
	public boolean isPossibleInitialHorizontalVelocity(double initialHorizontalVelocity){
		return initialHorizontalVelocity==getInitialHorizontalVelocityAtSpawn() && Util.fuzzyGreaterThanOrEqualTo(getInitialHorizontalVelocityAtSpawn(), 0);
	}
	
	/**
	 * Check whether the given maximum horizontal velocity is a possible maximum horizontal velocity for any shark.
	 * @return	True if the given maximum horizontal velocity is equal to the maximum horizontal velocity at spawn for sharks, denoting its
	 * 			immutability, and the given velocity is greater than or equal to zero.
	 * 			result == (maximumHorizontalVelocityAtSpawn>0 && maximumHorizontalVelocity==maximumHorizontalVelocityAtSpawn)
	 */
	@Raw
	@Override
	public boolean isPossibleMaximumHorizontalVelocity(double maximumHorizontalVelocity){
		return maximumHorizontalVelocity==getMaximumHorizontalVelocityAtSpawn() && getMaximumHorizontalVelocityAtSpawn()>0;
	}
	
	/**
	 * Return the current initial horizontal velocity of this shark for use in update calculations.
	 * 
	 * @return	The sharks regular initial horizontal velocity.
	 * 			| 	result==this.getInitialHorizontalVelocity()
	 */
	@Raw
	@Override
	public double getInitialHorizontalVelocityForUpdate(){
		return getInitialHorizontalVelocity();
	}
	
	/**
	 * Return the maximum horizontal velocity of this shark for use in update calculations.
	 * 
	 * @return	The regular maximum horizontal velocity.
	 * 			| 	result==this.getMaximumHorizontalVelocity()
	 */
	@Raw
	@Override
	public double getMaximumHorizontalVelocityForUpdate(){
		return getMaximumHorizontalVelocity();
	}
	
	/**
	 * Return the maximum horizontal velocity of all sharks at spawn.
	 */
	@Basic
	@Raw
	@Immutable
	public static double getMaximumHorizontalVelocityAtSpawn(){
		return maximumHorizontalVelocityAtSpawn;
	}
	
	/**
	 * Return the initial horizontal velocity of all sharks at spawn.
	 */
	@Basic
	@Raw
	@Immutable
	public static double getInitialHorizontalVelocityAtSpawn(){
		return initialHorizontalVelocityAtSpawn;
	}
	
	/**
	 * Variable storing the maximum horizontal velocity of all sharks at spawn.
	 */
	private static final double maximumHorizontalVelocityAtSpawn=4;
	
	/**
	 * Variable storing the maximum horizontal velocity of all sharks at spawn.
	 */
	private static final double initialHorizontalVelocityAtSpawn=0;
	
	/**
	 *  Variable registering the initial vertical velocity of all sharks.
	 */
	private static final double initialVerticalVelocity = 2;
	
	
	/**
	 * Return the current vertical acceleration of this shark.
	 * @return	If this shark is currently only in contact with water, the vertical acceleration is zero.
	 * 			Otherwise the vertical acceleration is equal to the gravitational constant.
	 * 			|if(this.checkWaterAndNoAirContact())
	 * @return	
	 * 			|if(checkWaterAndNoAirContact() && this.isJumping())
	 */
	@Override
	public double getVerticalAcceleration(){
		if(this.isJumping()){
			return VERTICAL_ACCELERATION;
		}
		else if(checkWaterAndNoAirContact() && !this.isJumping()){
			return getMaximumFloatingVerticalAcceleration() * this.getRandomDivingMultiplier();
		}
		else{
			return 0;
		}
	}
	
	/**
	 * Check whether this shark can have the given vertical acceleration as its vertical acceleration.
	 * @return	The absolute value of the given vertical acceleration is less than the maximum floating vertical acceleration or the given vertical acceleration
	 * 			is equal to the gravitational constant.
	 * 			|	Util.fuzzyLessThanOrEqualTo(Math.abs(verticalAcceleration),getMaximumFloatingVerticalAcceleration()) || Util.fuzzyEquals(verticalAcceleration,VERTICAL_ACCELERATION)
	 */
	@Raw
	@Override
	public boolean canHaveAsVerticalAcceleration(double verticalAcceleration){
		return Util.fuzzyLessThanOrEqualTo(Math.abs(verticalAcceleration),getMaximumFloatingVerticalAcceleration()) || Util.fuzzyEquals(verticalAcceleration,VERTICAL_ACCELERATION);
	}
	
	/**
	 * Check whether the given horizontal acceleration is a valid horizontal acceleration for any shark.
	 * @param	horizontalAcceleration
	 * 			The horizontal acceleration to be checked.
	 * @return	The given horizontal acceleration is not equal to zero and equal to the horizontal acceleration of sharks at spawn, denoting its immutability.
	 * 			| result == (horizontalAcceleration==0 && horizontalAcceleration==horizontalAccelerationAtSpawn)
	 */
	@Raw
	@Override
	public boolean canHaveAsHorizontalAcceleration(double horizontalAcceleration){
		return horizontalAcceleration==horizontalAccelerationAtSpawn && horizontalAccelerationAtSpawn!=0;
	}
	
	/**
	 * Return the horizontal acceleration of all sharks at spawn.
	 */
	@Basic
	@Raw
	@Immutable
	public static double getHorizontalAccelerationAtSpawn(){
		return horizontalAccelerationAtSpawn;
	}
	
	/**
	 * Return the maximum floating vertical acceleration of all sharks.
	 */
	@Basic
	@Raw
	@Immutable
	public static double getMaximumFloatingVerticalAcceleration(){
		return maximumFloatingVerticalAcceleration;
	}
	
	/**
	 * Variable registering the horizontal acceleration of all sharks at spawn.
	 */
	private static final double horizontalAccelerationAtSpawn=1.5;
	
	/**
	 * Variable registering the maximum vertical acceleration while floating of all sharks.
	 */
	private static final double maximumFloatingVerticalAcceleration=0.2;
	
	/**
	 * Check whether this shark can have the given ducking state as its ducking state.
	 * @return 	The given ducking state is false.
	 * 			|result== (ducking==false)
	 */
	@Raw
	@Override
	public boolean canHaveAsDuckingState(boolean ducking){
		return !ducking;
	}
	
	/**
	 * Check whether the given number of images is a valid number of images
	 * for the class shark.
	 * 
	 * @param	nbImages
	 * 			The given number of images.
	 * @return	The given number must be equal to two.
	 * 			|result == nbImages == 2; 
	 */
	@Raw
	@Override
	public boolean canHaveAsNbImages(int nbImages){
		return nbImages == 2;
	}
	
	
	
	/**
	 * Method to start a new action of this shark. This action can be either a random vertical movement or a jump.
	 * @post	The new current action duration of this shark is equal to a random double between the minimum action duration and the maximum action duration.
	 * 			|new.getCurrentActionDuration() == RandomDouble(getMinimumActionDuration()..getMaximumActionDuration)
	 * @post	The new direction of this shark is equal to a random direction. 
	 * 			The new horizontal velocity of this shark is equal to its current initial horizontal velocity in this direction. 
	 * 			|new.getDirection()==RandomDirection()
	 * 			|new.getHorizontalVelocity==new.getDirection().getNumberForCalculations()*this.getInitialHorizontalVelocityForUpdate()
	 * @post	The new time since the start of an action is equal to zero.
	 * 			|new.getTimeSinceStartAction()==0
	 * @post	If the current number of movements since this shark's last jump is greater than or equal to 4 and a randomly generated double
	 * 			between 0 and 1 is greater than the random movement chance of sharks and this shark is currently not already jumping, this shark starts jumping. This consists of setting 
	 * 			the vertical velocity of this shark to the initial vertical velocity of sharks, setting its jumping state to true an setting 
	 * 			the number of movements since this shark last jumped to zero.
	 * 			|if(this.getNbMovementsSinceLastJump()>=4 && RandomDouble(0,1)>getRandomMovementChance()
	 * 			|	new.isJumping==true
	 * 			|	new.getVerticalVelocity()==getInitialVerticalVelocity()
	 * 			|	new.getTimeSinceLastJump()==0
	 * 			Otherwise the shark starts a random diving action. The new number of movements since the last jump of this shark is equal to the
	 * 			old number of movements since the last jump of this shark incremented by 1, the new jumping state of this shark is false and the new
	 * 			random diving multiplier is equal to a random double between -1 and 1.
	 * 			|else:
	 * 			|	new.isJumping()==false
	 * 			|	new.getRandomDivingMultiplier()==RandomDouble(-1,1)
	 * 			|	new.getNbMovementsSinceLastJump()==this.getNbMovementsSinceLastJump()+1
	 */
	private void startNewAction(){
		Random r = new Random();
		this.setCurrentActionDuration(getMinimumActionDuration()+(getMaximumActionDuration()-getMinimumActionDuration())*r.nextDouble());
		this.setDirection(r.nextBoolean() ? Orientation.LEFT : Orientation.RIGHT);
		this.startMove(getDirection());
		this.setTimeSinceStartAction(0);
		if(this.getNbMovementsSinceLastJump() >= 4 && (r.nextDouble()>getRandomMovementChance())){
			this.startJump();
		}
		else{
			this.setNbMovementsSinceLastJump(this.getNbMovementsSinceLastJump() + 1);
			this.setRandomDivingMultiplier(2.0 *r.nextDouble() - 1.0);
			this.setJumping(false);
		}
	}
	
	/**
	 * Start the horizontal movement of this shark in the given direction.
	 * 
	 * @param 	direction
	 * 			The desired direction in which this shark will move.
	 * @pre		The given direction must be a valid direction.
	 * 			|isValidDirection(direction)
	 * @effect	Set the horizontal velocity in the given direction.
	 * 			|setHorizontalVelocity(this.getInitialHorizontalVelocityForUpdate()*direction.getNumberForCalculations())
	 * @effect	Set the direction in the given direction.	
	 * 			|setDirection(direction);
	 * @effect	Set isMovingHorizontally to true.
	 * 			|setMovingHorizontally(true);
	 * @note	The precondition is asserted in setDirection()
	 */
	@Raw
	@Override
	public void startMove(Orientation direction){
		setHorizontalVelocity(this.getInitialHorizontalVelocityForUpdate()*direction.getNumberForCalculations());
		setDirection(direction);
		setMovingHorizontally(true);
	}
	
	/**
	 * Stop movement of this shark in the given direction.
	 * 
	 * @param	direction
	 * 			The given direction.
	 * @effect	If the given direction is equal to the direction of this shark
	 * 			then set the horizontal velocity to zero and isMovingHorizontally to false.
	 * 			|if(direction==this.getDirection()){
	 * 			|	then setHorizontalVelocity(0)
	 * 			|		 setMovingHorizontally(false)
	 */
	@Raw
	@Override
	public void endMove(Orientation direction){
		if(direction==this.getDirection()){
			setHorizontalVelocity(0);
			setMovingHorizontally(false);
		}
	}
	
	/**
	 * Make this shark jump if this shark isn't jumping already.
	 * 
	 * @effect	If this shark isn't jumping, the vertical velocity of this shark is set to the initial vertical velocity and its jumping state is set to true.
	 * 			The number of movements since the last jump is set to zero.
	 * 			| if (!isJumping())
	 * 			|	setJumping(true)
	 * 			| 	setVerticalVelocity(getInitialverticalVelocity())
	 * 			|	this.setNbMovementsSinceLastJump(0);
	 * @note	The method setVerticalVelocity will never throw an exception in 
	 * 			its current implementation because this.getInitialVerticalVelocity() always returns a valid vertical velocity.
	 * 			There is no need to add a try catch statement.
	 */
	@Raw
	@Override
	public void startJump(){
		if (!isJumping()){
			setJumping(true);
			setVerticalVelocity(getInitialVerticalVelocity());
			this.setNbMovementsSinceLastJump(0);
		}
	}
	
	/**
	 * End the jump of this shark.
	 * 
	 * @effect	This shark's vertical velocity is set to zero and the jumping state is set to false.
	 * 			| setJumping(false)
	 * 			| setVerticalVelocity(0)
	 * @note	The method setVerticalVelocity will never throw an exception in 
	 * 			its current implementation because 0 is a valid vertical velocity.
	 * 			There is no need to add a try catch statement.
	 */
	@Raw
	@Override
	public void endJump(){
		setJumping(false);
		setVerticalVelocity(0);
	}
	
	/**
	 * Set the current action duration of this shark to the given duration. This is how long the current action of this shark lasts.
	 * @param 	duration
	 * 			The action duration to be set.
	 * @pre		The given duration must be a valid action duration.
	 * 			| isValidActionDuration(duration)
	 * @post	| new.getCurrentActionDuration()==duration
	 */
	@Raw
	public void setCurrentActionDuration(double duration) {
		assert (isValidActionDuration(duration)):
			"The given duration is not a valid action duration!";
		this.currentActionDuration=duration;
	}
	
	/**
	 * Return the number of movements since this sharks last jump.
	 */
	@Basic
	@Raw
	public int getNbMovementsSinceLastJump(){
		return this.nbMovementsSinceLastJump;
	}
	
	/**
	 * Set the number of movements since this sharks last jump. If the given number is not a valid number of movements
	 * the number of movements is set to zero.
	 * @param 	nbMovementsSinceLastJump
	 * 			The number of movements to be set.
	 * @post	If the given number is not a valid number of movements, the new number of movements of this shark is equal to zero.
	 * 			Otherwise it is equal to the given number.
	 * 			|if(!isValidNbMovementsSinceLastJump())
	 * 			|	new.getNbMovementsSinceLastJump()==0
	 * 			|else
	 * 			|	new.getNbMovementsSinceLastJump()==nbMovementsSinceLastJump
	 */
	@Raw
	public void setNbMovementsSinceLastJump(int nbMovementsSinceLastJump){
		if(!isValidNbMovements(nbMovementsSinceLastJump)){
			this.nbMovementsSinceLastJump = 0;
		}
		else{
			this.nbMovementsSinceLastJump = nbMovementsSinceLastJump;
		}
	}
	
	/**
	 * Check whether the given number of movements is a valid number of movements for all sharks.
	 * @param 	nbMovements
	 * 			The number of movements to be checked
	 * @return	The given number must be greater than or equal to zero.
	 * 			| result== (nbMovements>=0)
	 */
	@Raw
	public static boolean isValidNbMovements(int nbMovements){
		return nbMovements>=0;
	}
	
	/**
	 * Return the random movements chance of all sharks.
	 */
	@Basic
	@Raw
	@Immutable
	public static double getRandomMovementChance(){
		return randomMovementChance;
	}
	
	/**
	 * Check whether the given chance is a valid chance.
	 * @param chance
	 * 			The chance to be checked.
	 * @return	The chance must be greater than or equal to zero and less than or equal to 1.
	 * 			|chance>=0 && Util.fuzzyLessThanOrEqualTo(chance, 1)
	 */
	@Raw
	public static boolean isValidChance(double chance){
		return chance>=0 && Util.fuzzyLessThanOrEqualTo(chance, 1);
	}
	
	/**
	 * Return the current action duration of this shark.
	 */
	@Basic
	@Raw
	public double getCurrentActionDuration(){
		return currentActionDuration;
	}
	
	/**
	 * Return the minimum action duration for all sharks.
	 */
	@Basic
	@Raw
	@Immutable
	public static double getMinimumActionDuration(){
		return minimumActionDuration;
	}
	
	/**
	 * Return the maximum action duration for all sharks.
	 */
	@Basic
	@Raw
	@Immutable
	public static double getMaximumActionDuration(){
		return maximumActionDuration;
	}
	
	/**
	 * Check whether the given action duration is a valid action duration for all sharks.
	 * @param 	actionDuration
	 * 			The duration to be checked.
	 * @return	True if the given duration is greater than or equal to the minimum action duration of sharks
	 * 			and less than or equal to the maximum action duration of sharks.
	 * 			| result == (Util.fuzzyGreaterThanOrEqualTo(actionDuration, getMinimumActionDuration())
				|			&& Util.fuzzyLessThanOrEqualTo(actionDuration, getMaximumActionDuration()))
	 */
	@Raw
	public static boolean isValidActionDuration(double actionDuration){
		return Util.fuzzyGreaterThanOrEqualTo(actionDuration, getMinimumActionDuration())
				&& Util.fuzzyLessThanOrEqualTo(actionDuration, getMaximumActionDuration());
	}
	
	
	/**
	 * Return the current random diving multiplier of this shark.
	 */
	@Basic
	@Raw
	public double getRandomDivingMultiplier() {
		return randomDivingMultiplier;
	}
	
	/**
	 * Set the random diving multiplier of this shark to the given random diving multiplier. If the given multiplier is not a valid
	 * random diving multiplier the random diving multiplier is set to zero.
	 * @param 	randomDivingMultiplier
	 * 			The diving multiplier to be set.
	 * @post	If the given random diving multiplier is not valid the new random diving multiplier is equal to zero.
	 * 			Otherwise it is equal to the given multiplier.
	 * 			|if(!isValidRandomDivingMultiplier(randomDivingMultiplier):
	 *			|	new.getRandomDivingMultiplier == 0
	 *			|else: 
	 *			|	new.getRandomDivingMultiplier == randomDivingMultiplier		
	 */
	@Raw
	public void setRandomDivingMultiplier(double randomDivingMultiplier) {
		if(!isValidRandomDivingMultiplier(randomDivingMultiplier)){
			this.randomDivingMultiplier = 0;
		}
		else{
			this.randomDivingMultiplier = randomDivingMultiplier;
		}
	}
	
	/**
	 * Check whether the given random diving multiplier is a valid random diving multiplier for any shark.
	 * @param 	randomDivingMultiplier
	 * 			| The random diving multiplier to be checked.
	 * @return	The given multiplier must be greater than or equal to -1 and less than or equal to 1.
	 * 			|(Util.fuzzyGreaterThanOrEqualTo(randomDivingMultiplier,-1) 
	 *			| && Util.fuzzyLessThanOrEqualTo(randomDivingMultiplier, 1))
	 */
	@Raw
	public static boolean isValidRandomDivingMultiplier(double randomDivingMultiplier){
		return (randomDivingMultiplier>=-1 
				&& randomDivingMultiplier<=1);
	}

	/**
	 * Variable registering the random diving multiplier, this number is used to calculate the random diving vertical acceleration of a shark when 
	 * the shark is not jumping.
	 */
	private double randomDivingMultiplier;

	/**
	 * Variable registering the current action duration of this shark.
	 */
	private double currentActionDuration=0;

	/**
	 * Variable registering the minimum action duration of all sharks.
	 */
	private static final double minimumActionDuration=1;
	
	/**
	 * Variable registering the maximum action duration of all sharks.
	 */
	private static final double maximumActionDuration=4;
	
	/**
	 * Variable registering the number of movements since this sharks last jump.
	 */
	private int nbMovementsSinceLastJump = 2; 
	
	/**
	 * Variable registering the chance if the next action will be a random movement or a jump.
	 */
	private static final double randomMovementChance=0.5;
	
	
	
	/**
	 * Update the location and velocity of this shark.
	 * 
	 * @param 	deltaTime
	 * 			The period of time that is used to update this shark.
	 * @effect	The horizontal and vertical location are updated
	 * 			and the horizontal and vertical velocity are updated. 
	 * 			|updateVelocities(deltaTimeForPixel)
	 *			|updateLocations(deltaTimeForPixel, oldHorizontalLocation, oldVerticalLocation)
	 *			The jumping state is updated.
	 *			|updateJumping()
	 *			The collison with other objects is checked.
	 *			|collisionHandler(overlap,oldHorizontalLocation,oldVerticalLocation)
	 * @effect	|Contact with magma and water is checked.
	 *			|checkWaterContact(deltaTime);
	 *			|checkMagmaContact(deltaTime);	
	 * @post	|new.getTimeSinceStartAction == this.getTimeSinceStartAction()+deltaTime
	 * @throws	IllegalArgumentException 
	 * 			The given time period is not valid a valid time period.
	 * 			|!isValidTimePeriod(deltaTime)
	 */
	@Raw
	@Override
	public void advanceTime(double deltaTime)
		throws IllegalArgumentException {
		if(!isValidDeltaTime(deltaTime))
			throw new IllegalArgumentException();
		double deltaTimeForPixel=0;
		double sumDeltaTimeForPixel=0;
		double oldHorizontalLocation=this.getHorizontalLocation();
		double oldVerticalLocation=this.getVerticalLocation();
		if(this.getProgram()!=null){
			this.getProgram().execute(deltaTime);
		}
		else{
			if(this.getTimeSinceStartAction()<=this.getCurrentActionDuration()){
				this.setTimeSinceStartAction(this.getTimeSinceStartAction()+deltaTime);
			}
			else{
				this.startNewAction();
			}
		}
		while (sumDeltaTimeForPixel<deltaTime){
			oldHorizontalLocation = this.getHorizontalLocation();
			oldVerticalLocation = this.getVerticalLocation();
			deltaTimeForPixel= getDeltaTimeForPixel(deltaTime);
			sumDeltaTimeForPixel+=deltaTimeForPixel;
			this.updateVelocities(deltaTimeForPixel);
			this.updateLocations(deltaTimeForPixel, oldHorizontalLocation, oldVerticalLocation);
			this.updateJumping();
			int []overlap = checkLeftRightTopBottomSideOverlap();
			collisionHandler(overlap,oldHorizontalLocation,oldVerticalLocation);
		}
		checkAirContact(deltaTime);
		checkMagmaContact(deltaTime);
	}
	
	/**
	 * Update the velocities of this shark.
	 * 
	 * @param 	deltaTime
	 * 			A given period of time used in the calculations.
	 */
	protected void updateVelocities(double deltaTime){
		double newVerticalVelocity = this.getVerticalVelocity() + getVerticalAcceleration()*deltaTime;
		double newHorizontalVelocity = this.getHorizontalVelocity() + this.getDirection().getNumberForCalculations()*getHorizontalAcceleration()*deltaTime;
		this.setHorizontalVelocity(newHorizontalVelocity);
		this.setVerticalVelocity(newVerticalVelocity);
	}
	
	/**
	 * Update the locations of this shark.
	 * 
	 * @param 	deltaTime
	 * 			A given period of time used in the calculations.
	 * @param 	oldHorizontalLocation
	 * 			The old horizontal location.
	 * @param 	oldVerticalLocation
	 * 			The old vertical location.
	 */
	protected void updateLocations(double deltaTime, double oldHorizontalLocation, double oldVerticalLocation){
		double newHorizontalLocation = this.getHorizontalLocation() + 
				100*(this.getHorizontalVelocity()*deltaTime + 
				this.getDirection().getNumberForCalculations()*0.5*getHorizontalAcceleration()*Math.pow(deltaTime, 2));
		double newVerticalLocation = this.getVerticalLocation() + 100*(getVerticalVelocity()*deltaTime + 0.5*getVerticalAcceleration()*Math.pow(deltaTime,2));
		try{
			this.setHorizontalLocation(newHorizontalLocation);
		} catch(IllegalLocationException exc){
			this.setHorizontalLocation((int) oldHorizontalLocation);
		}
		try{
			this.setVerticalLocation(newVerticalLocation);
		} catch(IllegalLocationException exc){
			this.setVerticalLocation(oldVerticalLocation);
			this.setVerticalVelocity(0);
		}
	}
	
	/**
	 * Update the jumping state of this shark.
	 * 
	 * @post	If this shark is currently jumping and fully submerged in water and falling downwards. The new jumping state of this shark is false
	 * 			and its new vertical velocity is zero.
	 * 			|if(this.isJumping() && this.checkWaterAndNoAirContact() && this.getVerticalVelocity()<0):
	 *			|	new.isJumping() == false
	 * 			|   new.getVerticalVelocity == 0
	 * 			Otherwise if this shark is cannot have the location beneath it as its location and is not fully submerged in water the new jumping
	 * 			state of this shark is false and its new vertical velocity is zero.
	 * 			|else if(!this.canHaveAsLocation(this.getHorizontalLocation(), this.getVerticalLocation()-1) && !this.checkWaterAndNoAirContact())
	 *			|	new.isJumping()=false
	 *			|	new.getVerticalVelocity==0
	 *			Otherwise is this shark can have the location beneath it as its location and is not fully submerged in water the new jumping state of this shark is true.
	 *			|new.isJumping==true
	 */
	private void updateJumping(){
		if(this.checkWaterAndNoAirContact() && this.getVerticalVelocity()<0 && this.isJumping()){
			this.endJump();
		}
		else if(!this.canHaveAsLocation(this.getHorizontalLocation(), this.getVerticalLocation()-1) && !this.checkWaterAndNoAirContact()){
			this.endJump();
		}
		else if(this.canHaveAsLocation(this.getHorizontalLocation(), this.getVerticalLocation()-1) && !this.checkWaterAndNoAirContact()){
			this.setJumping(true);
		}
	}
	
	/**
	 * Execute the right actions after a collision with another game object.
	 * 
	 * @param 	index1
	 * 			The index registering the position of the other 
	 * 			game object in list of all game objects of this world.
	 * @param 	index2
	 * 			The index registering whether the bottom perimeter was 
	 * 			overlapping during the contact with the other game object.
	 * @param 	index3
	 * 			The index registering whether the top perimeter was
	 * 			overlapping during the contact with the other game object.
	 * @post	|if(gameObject instanceof Slime)
	 * 			|	gameObject.removeHitPoints(50);
	 *			|	this.removeHitPoints(50);
	 *			|else if(gameObject instanceof Mazub){
	 *			|	if(!((Mazub) gameObject).isUntouchable())
	 *			|		then this.removeHitPoints(50)
	 *			|		if(index3 == 0)
	 *			|			then gameObject.removeHitPoints(50)
	 *			|			gameObject).setTimeSinceLastHitpointsLoss(0)
	 */
	protected void collisionReaction(int index1, int index2, int index3) {
		GameObject gameObject = this.getWorld().getGameObjectAtIndex(index1);
		if(gameObject instanceof Slime){
			((Slime) gameObject).removeHitPointsSchool(50);
			this.removeHitPoints(50);
		}
		else if(gameObject instanceof Mazub){
			if(!((Mazub) gameObject).isUntouchable()){
				this.removeHitPoints(50);
				if(index3 == 0){
					gameObject.removeHitPoints(50);
					((Mazub) gameObject).setTimeSinceLastHitpointsLoss(0);
				}
			}
		}
		else if(gameObject instanceof Buzam){
			if(!gameObject.isUntouchable()){
				this.removeHitPoints(50);
				if(index3 == 0){
					gameObject.removeHitPoints(50);
					gameObject.setTimeSinceLastHitpointsLoss(0);
				}
			}
		}
	}
	
	/**
	 * Check whether the given left, right, top or bottom perimeter overlaps with another game object.
	 * 
	 * @return	The method iterates over all the game objects that can overlap with the given
	 * 			game object. That are the game objects that overlap with one of the tile with
	 * 			which this game object overlaps. If another game object is found that overlaps 
	 * 			with one of the given sides then an array with four elements is returned.
	 * 			This array can be interpreted by the method collisionHandler.
	 * 			|overlap = {0,0,0,0}
	 * 			|for each game object in gameGameObjectAtTile of this game object
	 * 			|	if(gameObject != this && gameObject != null && world.canHaveAsGameObject(gameObject))
	 * 			|		overlap = checkLeftOrRightSideOverlap(gameObject,perimeters...);
	 *			|		if(overlap [0] == 1)
	 *			|		then the array "overlap" is returned
	 *			|		or 
	 *			|		overlap = checkTopOrBottomSideOverlap(gameObject,perimeters...);
	 *			|		if(overlap [0] == 1)
	 *			|		then the array "overlap" is returned
	 *			|result == overlap
	 */
	protected int [] checkLeftRightTopBottomSideOverlap(){
		int [] overlap = {0,0,0,0};
		World world = this.getWorld();
		List<GameObject> gameObjects = getGameObjectsAtTiles(world.getTilePositionsIn(getEffectiveHorizontalLocation(), getEffectiveVerticalLocation(), getEffectiveHorizontalLocation()+getWidth(), getEffectiveVerticalLocation()+getHeight()));
		for(int index = 0; index < gameObjects.size(); index++){
			GameObject gameObject = gameObjects.get(index);
			if(gameObject != this && gameObject != null && world.canHaveAsGameObject(gameObject)){
				int [][] leftPerimeterOther = gameObject.getLeftPerimeter(gameObject.getEffectiveHorizontalLocation(), gameObject.getEffectiveVerticalLocation(), gameObject.getHeight());
				int [][] rightPerimeterOther = gameObject.getRightPerimeter(gameObject.getEffectiveHorizontalLocation(), gameObject.getEffectiveVerticalLocation(), gameObject.getWidth(), gameObject.getHeight());
				overlap = checkLeftOrRightSideOverlap(gameObject, this.getLeftPerimeter(getEffectiveHorizontalLocation(), getEffectiveVerticalLocation(), getHeight()), 
						 this.getRightPerimeter(getEffectiveHorizontalLocation(), getEffectiveVerticalLocation(), getWidth(), getHeight()), leftPerimeterOther, rightPerimeterOther);
				if(overlap [0] == 1){
					return overlap;
				}
				int [][] topPerimeterOther = gameObject.getTopPerimeter(gameObject.getEffectiveHorizontalLocation(), gameObject.getEffectiveVerticalLocation(), gameObject.getWidth(), gameObject.getHeight());
				int [][] bottomPerimeterOther = gameObject.getBottomPerimeter(gameObject.getEffectiveHorizontalLocation(), gameObject.getEffectiveVerticalLocation(), gameObject.getWidth());
				overlap = checkTopOrBottomSideOverlap(gameObject, this.getTopPerimeter(getEffectiveHorizontalLocation(), getEffectiveVerticalLocation(), getWidth(), getHeight()), 
						this.getBottomPerimeter(getEffectiveHorizontalLocation(), getEffectiveVerticalLocation(), getWidth()), topPerimeterOther, bottomPerimeterOther);
				if(overlap [0] == 1){
					return overlap;
				}
			}
		}
		return overlap;
	}
		
	
	/**
	 * Check whether the shark makes contact with water and no contact with air
	 * 
	 * @return 	If the shark is only located within water tiles and no air tiles, return true.
	 * 			|boolean [] contactTiles = (this.getWorld().areaCoincidesWithTerrain(this.getEffectiveHorizontalLocation(), 
				|this.getEffectiveVerticalLocation()+1, this.getWidth()-1, this.getHeight()-2)).clone()
	 * 			|if(contactTiles[2] == true && contactTiles[0] == false)
	 * 			|	result == true
	 * 			|else result == false
	 */
	protected boolean checkWaterAndNoAirContact(){
		boolean [] contactTiles = (this.getWorld().areaCoincidesWithTerrain(this.getEffectiveHorizontalLocation(), 
				this.getEffectiveVerticalLocation()+1, this.getWidth()-1, this.getHeight()-2)).clone();
		if(contactTiles[2] == true && contactTiles[0] == false){
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * Check whether the shark makes contact with air and take the corresponding actions.
	 * 
	 * @param 	deltaTime
	 * 			The given time period.
	 * @post	Every 0.2 seconds while being contacted with air, six hitpoints are removed
	 * 			from this shark. The first 0.2 seconds no hitpoints are removed.
	 * 			|if(contactTiles[0] == true)
	 * 			|	then this.setTimeSinceStartAirContact(time + deltaTime)
	 * 			|	if(Util.fuzzyGreaterThanOrEqualTo(this.getTimeSinceStartAirContact(), 0.2)){
	 *			|		then this.removeHitPoints(6);
	 *			|		then this.setTimeSinceStartAirContact(0);
	 *			|else this.setTimeSinceStartAirContact(0);
	 */
	protected void checkAirContact(double deltaTime){
		boolean [] contactTiles = (this.getWorld().areaCoincidesWithTerrain(this.getEffectiveHorizontalLocation(), 
				this.getEffectiveVerticalLocation()+1, this.getWidth()-1, this.getHeight()-2)).clone();
		if(contactTiles[0] == true){
			double time = getTimeSinceStartAirContact();
			this.setTimeSinceStartAirContact(time + deltaTime);
			if(Util.fuzzyGreaterThanOrEqualTo(this.getTimeSinceStartAirContact(), 0.2)){
				this.removeHitPoints(6);
				this.setTimeSinceStartAirContact(0);
			}
		}
		else
			this.setTimeSinceStartAirContact(0);
	}
	
	/**
	 *Check whether the shark makes contact with magma and take the corresponding actions. 
	 * @param 	deltaTime
	 * 			The given time period.
	 * @post	Every 0.2 seconds while being contacted with magma, fifty hitpoints are removed
	 * 			from shark. The first hitpoints are immediately removed at first contact.
	 * 			|if(contactTiles[3] == true)
	 *			|	then this.setTimeSinceStartMagmaContact(time + deltaTime);
	 *			|	if(time == 0)
	 *			|		then this.removeHitPoints(50);
	 *			|	else if(Util.fuzzyGreaterThanOrEqualTo(this.getTimeSinceStartMagmaContact(), 0.2))
	 *			|		then this.setTimeSinceStartMagmaContact(0);
	 *			|else this.setTimeSinceStartMagmaContact(0);
	 *	
	 */
	@Override
	protected void checkMagmaContact(double deltaTime){
		boolean [] contactTiles = (this.getWorld().areaCoincidesWithTerrain(this.getEffectiveHorizontalLocation(), 
				this.getEffectiveVerticalLocation()+1, this.getWidth()-1, this.getHeight()-2)).clone();
		if(contactTiles[3] == true){
			double time = getTimeSinceStartMagmaContact();
			this.setTimeSinceStartMagmaContact(time + deltaTime);
			if(time == 0)
				this.removeHitPoints(50);
			else if(Util.fuzzyGreaterThanOrEqualTo(this.getTimeSinceStartMagmaContact(), 0.2)){
				this.setTimeSinceStartMagmaContact(0);
			}
		}
		else{
			this.setTimeSinceStartMagmaContact(0);
		}
	}
	
	/**
	 * Return the time since the start of the air contact of this shark.
	 */
	@Basic
	@Raw
	public double getTimeSinceStartAirContact(){
		return this.timeSinceAirContact;
	}
	
	/**
	 * Set the time since the start of the air contact of this game object.
	 * 
	 * @param	time
	 * 			The time to be set.
	 * @post	The new time since start air contact is equal to the given time.
	 * 			|new.getTimeSinceStartAirContact()==time
	 * @throws	IllegalArgumentException
	 * 			The given time is not a valid action time.
	 * 			|!isValidTimeSinceAction(time)
	 * 			
	 */
	@Raw
	public void setTimeSinceStartAirContact(double time)
	throws IllegalArgumentException{
		if(!isValidTimeSinceAction(time))
			throw new IllegalArgumentException();
		this.timeSinceAirContact = time;
			
	}
	
	/**
	 * Variable registering the time since this shark started making contact with air.
	 */
	private double timeSinceAirContact = 0;
	
	/**
	 * Constant registering the maximum number of hit points of a shark.
	 */
	private final static int HIT_POINTS=100;

	@Override
	protected void checkWaterContact(double deltaTime) {
		//Not needed!
	}
}
