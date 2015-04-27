package jumpingalien.part2.tests;


import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.*;
import jumpingalien.model.Direction;
import jumpingalien.model.IllegalLocationException;
import jumpingalien.model.Mazub;
import jumpingalien.model.World;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



public class MazubTest {

	private static Mazub staticTestMazub;
	private static Mazub movingMazub;
	private static World staticTestWorld;
	public static final int FEATURE_AIR = 0;
	public static final int FEATURE_SOLID = 1;
	public static final int FEATURE_WATER = 2;
	public static final int FEATURE_MAGMA = 3;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		staticTestWorld = new World(500, 3, 3, 1000, 1000, 2, 2);
		staticTestWorld.setTileValueAtTilePosition( 0, 0, FEATURE_SOLID);
		staticTestWorld.setTileValueAtTilePosition( 1, 1, FEATURE_SOLID);
		staticTestMazub = new Mazub(0, 499, spriteArrayForSize(3, 3));
		staticTestMazub.setWorld(staticTestWorld);
		staticTestWorld.setMazub(staticTestMazub);
		movingMazub = new Mazub(0, 0, 2, 0, 1, 3, false, spriteArrayForSize(2, 2));
	}

	private Mazub testMazub;
	private Mazub notJumpingTestMazub;
	private Mazub jumpingTestMazub;
	private Mazub notDuckingTestMazub;
	private World testWorld;
	
	@Before
	public void setUp() throws Exception {
		testWorld = new World(500, 3, 3, 1000, 1000, 2, 2);
		testWorld.setTileValueAtTilePosition( 0, 0, FEATURE_SOLID);
		testWorld.setTileValueAtTilePosition( 1, 1, FEATURE_SOLID);
		testMazub = new Mazub(0, 499, spriteArrayForSize(3, 3));
		testMazub.setWorld(testWorld);
		testWorld.setMazub(testMazub);		
		jumpingTestMazub = new Mazub(25, 500, 0, 3, 1, 3, false, spriteArrayForSize(2, 2));
		jumpingTestMazub.setWorld(testWorld);
		testWorld.setMazub(jumpingTestMazub);
		notJumpingTestMazub = new Mazub(50, 499, 0, 0, 1, 3, false, spriteArrayForSize(2, 2));
		notJumpingTestMazub.setWorld(testWorld);
		testWorld.setMazub(notJumpingTestMazub);
		notDuckingTestMazub = new Mazub(100, 499, 0, 0, 1, 3, false, spriteArrayForSize(2, 2));
		notDuckingTestMazub.setWorld(testWorld);
		testWorld.setMazub(notDuckingTestMazub);
	}

	@Test
	public void testSetHorizontalLocation_LegalCase() {
		testMazub.setHorizontalLocation(10);
		assertEquals(testMazub.getEffectiveHorizontalLocation(),10);
		
	}

	@Test (expected = IllegalLocationException.class)
	public void testSetHorizontalLocation_IllegalCase() throws Exception{
		testMazub.setHorizontalLocation(-10);
	}
	
	@Test
	public void testSetVerticalLocation_LegalCase() {
		testMazub.setVerticalLocation(500);
		assertEquals(testMazub.getEffectiveVerticalLocation(),500);
	}

	@Test (expected = IllegalLocationException.class)
	public void testSetVerticalLocation_IllegalCase() throws Exception{
		testMazub.setVerticalLocation(-20);
	}
	
	@Test
	public void testCanHaveAsHorizontalLocation_TrueCase() {
		assertTrue(staticTestMazub.canHaveAsHorizontalLocation(1));
	}
	
	@Test
	public void testCanHaveAsHorizontalLocation_FalseCase_NegativeLocation() {
		assertFalse(staticTestMazub.canHaveAsHorizontalLocation(-1));
	}

	@Test
	public void testCanHaveAsHorizontalLocation_FalseCase_UnpassableTerrain() {
		assertFalse(staticTestMazub.canHaveAsHorizontalLocation(501));
	} 
	
//	@Test
//	public void testCanHaveAsHorizontalLocation_FalseCase_NaN() {
//		assertFalse(staticTestMazub.canHaveAsHorizontalLocation(Double.NaN));
//	}
//	
	@Test
	public void testCanHaveAsHorizontalLocation_FalseCase_MAX_VALUE() {
		assertFalse(staticTestMazub.canHaveAsHorizontalLocation(Integer.MAX_VALUE));
	}
	
	@Test
	public void testCanHaveAsHorizontalLocation_FalseCase_MIN_VALUE() {
		assertFalse(staticTestMazub.canHaveAsHorizontalLocation(Integer.MIN_VALUE));
	}
	
	@Test
	public void testcanHaveAsVerticalLocation_TrueCase() {
		assertTrue(staticTestMazub.canHaveAsVerticalLocation(499));
	}

	@Test
	public void testcanHaveAsVerticalLocation_FalseCase_NegativeLocation() {
		assertFalse(staticTestMazub.canHaveAsVerticalLocation(-1));
	}
	
	@Test
	public void  testcanHaveAsVerticalLocation_FalseCase_UnpassableTerrain() {
		assertFalse(staticTestMazub.canHaveAsVerticalLocation(498));
	}
	
//	@Test
//	public void testIsValidVerticalLocation_FalseCase_NaN() {
//		assertFalse(Mazub.isValidVerticalLocation(Double.NaN));
//	}
	
	@Test
	public void testcanHaveAsVerticalLocation_FalseCase_MAX_VALUE() {
		assertFalse(staticTestMazub.canHaveAsVerticalLocation(Integer.MAX_VALUE));
	}
	
	@Test
	public void testcanHaveAsVerticalLocation_FalseCase_MIN_VALUE() {
		assertFalse(staticTestMazub.canHaveAsVerticalLocation(Integer.MIN_VALUE));
	}
	
	@Test
	public void testCanHaveAsLocation_TrueCase() {
		assertTrue(staticTestMazub.canHaveAsLocation(300,700));
	}

	@Test
	public void testCanHaveAsLocation_FalseCase_NegativeLocation() {
		assertFalse(staticTestMazub.canHaveAsLocation(-1,-1));
	}
	
	@Test
	public void testCanHaveAsLocation_FalseCase_PartiallyNegativeLocation() {
		assertFalse(staticTestMazub.canHaveAsLocation(1,-1));
	}
	
	@Test
	public void testCanHaveAsLocation_FalseCase_UnpassableTerrain() {
		assertFalse(staticTestMazub.canHaveAsLocation(350,400));
	}
	
//	@Test
//	public void testCanHaveAsLocation_FalseCase_NaN() {
//		assertFalse(staticTestMazub.canHaveAsLocation(Double.NaN,Double.NaN));
//	}
	
	@Test
	public void testIsMovingHorizontally_TrueCase(){
		assertTrue(movingMazub.isMovingHorizontally());
		
	}
	
	@Test
	public void testIsMovingHorizontally_FalseCase(){
		assertFalse(staticTestMazub.isMovingHorizontally());
	}
	
	@Test
	public void testSetHorizontalVelocity_TrueCase_PositiveValue() {
		testMazub.setHorizontalVelocity(1);
		assertTrue(Util.fuzzyEquals(testMazub.getHorizontalVelocity(),1));
	}
	
	@Test
	public void testSetHorizontalVelocity_TrueCase_NegativeValue() {
		testMazub.setHorizontalVelocity(-1);
		assertTrue(Util.fuzzyEquals(testMazub.getHorizontalVelocity(),-1));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetHorizontalVelocity_FalseCase() throws IllegalArgumentException{
		testMazub.setHorizontalVelocity(4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetHorizontalVelocity_FalseCase2() throws IllegalArgumentException{
		testMazub.setHorizontalVelocity(0.5);
	}
	
	@Test
	public void testSetVerticalVelocity_TrueCase_PositiveValue_InitialVelocity() {
		testMazub.setVerticalVelocity(1);
		assertTrue(Util.fuzzyEquals(testMazub.getVerticalVelocity(),1));
	}
	
	@Test
	public void testSetVerticalVelocity_TrueCase_NegativeValue() {
		testMazub.setVerticalVelocity(-5);
		assertTrue(Util.fuzzyEquals(testMazub.getVerticalVelocity(),-5));
	}

	@Test (expected = IllegalArgumentException.class)
	public void testSetVerticalVelocity_FalseCase() throws IllegalArgumentException {
		//8 is the initial vertical velocity
		testMazub.setVerticalVelocity(8+1);
	}
		
	@Test
	public void testCanHaveAsInitialHorizontalVelocity_TrueCases() {
		assertTrue(staticTestMazub.canHaveAsInitialHorizontalVelocity(1));
		assertTrue(staticTestMazub.canHaveAsInitialHorizontalVelocity(1.5));
		assertTrue(staticTestMazub.canHaveAsInitialHorizontalVelocity(2.99));
	}

	@Test
	public void testCanHaveAsInitialHorizontalVelocity_FalseCases() {
		assertFalse(staticTestMazub.canHaveAsInitialHorizontalVelocity(0.99));
		assertFalse(staticTestMazub.canHaveAsInitialHorizontalVelocity(-1.5));
		assertFalse(staticTestMazub.canHaveAsInitialHorizontalVelocity(3.01));
	}
	
	@Test
	public void testCanHaveAsMaximumHorizontalVelocity_TrueCases() {
		assertTrue(staticTestMazub.canHaveAsMaximumHorizontalVelocity(2));
		assertTrue(staticTestMazub.canHaveAsMaximumHorizontalVelocity(2.1));
		assertTrue(staticTestMazub.canHaveAsMaximumHorizontalVelocity(5));
		assertTrue(staticTestMazub.canHaveAsMaximumHorizontalVelocity(Double.POSITIVE_INFINITY));
	}
	
	@Test
	public void testCanHaveAsMaximumHorizontalVelocity_FalseCases() {
		assertFalse(staticTestMazub.canHaveAsMaximumHorizontalVelocity(0));
		assertFalse(staticTestMazub.canHaveAsMaximumHorizontalVelocity(-1.6));
		assertFalse(staticTestMazub.canHaveAsMaximumHorizontalVelocity(Double.NEGATIVE_INFINITY));
	}

	
	@Test
	public void testIsPossibleInitialHorizontalVelocity_TrueCase(){
		assertTrue(staticTestMazub.isPossibleInitialHorizontalVelocity(1));
		assertTrue(staticTestMazub.isPossibleInitialHorizontalVelocity(1.1));
		assertTrue(staticTestMazub.isPossibleInitialHorizontalVelocity(14));
	}
	

	@Test
	public void testIsPossibleInitialHorizontalVelocity_TrueCase_PositiveInfinity(){
		assertTrue(staticTestMazub.isPossibleInitialHorizontalVelocity(Double.POSITIVE_INFINITY));
	}
	

	@Test
	public void testIsPossibleInitialHorizontalVelocity_FalseCase(){
		assertFalse(staticTestMazub.isPossibleInitialHorizontalVelocity(0));
		assertFalse(staticTestMazub.isPossibleInitialHorizontalVelocity(0.99));
		assertFalse(staticTestMazub.isPossibleInitialHorizontalVelocity(-1));
	}
	
	@Test
	public void testIsPossibleInitialHorizontalVelocity_FalseCase_NegativeInfinity(){
		assertFalse(staticTestMazub.isPossibleInitialHorizontalVelocity(Double.NEGATIVE_INFINITY));
	}
	
	@Test
	public void testCanHaveAsHorizontalVelocity_TrueCase() {
		assertTrue(staticTestMazub.canHaveAsHorizontalVelocity(2));
	}

	@Test
	public void testCanHaveAsHorizontalVelocity_TrivialCase() {
		assertTrue(staticTestMazub.canHaveAsHorizontalVelocity(0));
	}
	@Test
	public void testCanHaveAsHorizontalVelocity_FalseCase() {
		assertFalse(staticTestMazub.canHaveAsHorizontalVelocity(0.1));
	}
	
	@Test
	public void testCanHaveAsHorizontalVelocity_FalseCase2() {
		//3 is the maximum horizontal velocity
		assertFalse(staticTestMazub.canHaveAsHorizontalVelocity(3 + 0.1));
	}
	
	@Test
	public void testCanHaveAsHorizontalVelocity_FalseCase_NaN() {
		assertFalse(staticTestMazub.canHaveAsHorizontalVelocity(Double.NaN));
	}
	
	@Test
	public void testCanHaveAsVerticalVelocity_TrueCase() {
		//8 is the initial vertical velocity
		assertTrue(staticTestMazub.canHaveAsVerticalVelocity(8-5));
	}

	@Test
	public void testCanHaveAsVerticalVelocity_TrueCase_NegativeInfinity() {
		assertTrue(staticTestMazub.canHaveAsVerticalVelocity(Double.NEGATIVE_INFINITY));
	}
	
	@Test
	public void testCanHaveAsVerticalVelocity_FalseCase() {
		//8 is the initial vertical velocity
		assertFalse(staticTestMazub.canHaveAsVerticalVelocity(8 + 0.1));
	}
	
	@Test
	public void testCanHaveAsVerticalVelocity_FalseCase_NaN() {
		assertFalse(staticTestMazub.canHaveAsVerticalVelocity(Double.NaN));
	}
	
	/**
	 * Test voor nominale methode!
	 */
	@Test
	public void testSetDirection_TrueCase() {
		testMazub.setDirection(Direction.LEFT);
		assertEquals(testMazub.getDirection(),Direction.LEFT);
	}
	
	/**
	 * Test voor nominale methode!
	 */
	@Test
	public void testSetDirection_FalseCase() {
		testMazub.setDirection(Direction.RIGHT);
		assertNotEquals(testMazub.getDirection(),Direction.LEFT);
	}
	
	@Test
	public void testIsValidDirection_TrueCases() {
		assertTrue(Mazub.isValidDirection(Direction.RIGHT));
		assertTrue(Mazub.isValidDirection(Direction.LEFT));
	}
	
	@Test
	public void testCanHaveAsDuckingState(){
		assertTrue(staticTestMazub.canHaveAsDuckingState(true));
		assertTrue(staticTestMazub.canHaveAsDuckingState(false));
	}
	
	@Test
	public void testSetDucking() {
		testMazub.setDucking(true);
		assertTrue(testMazub.isDucking());
	}
	
	@Test
	public void testIsDucking_TrueCase() {
		testMazub.setDucking(true);
		assertTrue(testMazub.isDucking());
	}

	@Test
	public void testIsDucking_FalseCase() {
		assertFalse(staticTestMazub.isDucking());
	}
	
	@Test
	public void testIsJumping1() {
		jumpingTestMazub.advanceTime(0.19);
		assertTrue(jumpingTestMazub.isJumping());
	}

	
	@Test
	public void testIsJumping() {
		staticTestMazub.advanceTime(0.19);
		assertFalse(staticTestMazub.isJumping());
	}
	
	@Test
	public void testStartMove() {
		testMazub.startMove(Direction.RIGHT);
		//1 is the initial horizontal velocity
		assertTrue(Util.fuzzyEquals(testMazub.getHorizontalVelocity(),1));
		assertTrue(Util.fuzzyEquals(testMazub.getTimeSinceStartMove(),0));
		assertTrue(Util.fuzzyEquals(testMazub.getTimeSinceEndMove(),0));
		
	}
	
	@Test
	public void testEndMove() {
		testMazub.startMove(Direction.RIGHT);
		testMazub.advanceTime(0.19);
		testMazub.endMove();
		assertTrue(Util.fuzzyEquals(testMazub.getHorizontalVelocity(),0));
		assertTrue(Util.fuzzyEquals(testMazub.getTimeSinceStartMove(),0));
		assertTrue(Util.fuzzyEquals(testMazub.getTimeSinceEndMove(),0));
	}
	
	@Test
	public void testStartJump() {
		notJumpingTestMazub.startJump();
		assertTrue(notJumpingTestMazub.isJumping());
		assertTrue(Util.fuzzyEquals(notJumpingTestMazub.getVerticalVelocity(),8));
	}

	@Test
	public void testEndJump() {
		jumpingTestMazub.endJump();
		assertFalse(jumpingTestMazub.isJumping());
		assertTrue(Util.fuzzyEquals(notJumpingTestMazub.getVerticalVelocity(),0));
	}	

	
	@Test
	public void testStartDuck() {
		notDuckingTestMazub.startDuck();
		assertTrue(notDuckingTestMazub.isDucking());
		notDuckingTestMazub.startMove(Direction.RIGHT);
		assertTrue(Util.fuzzyEquals(notDuckingTestMazub.getHorizontalVelocity(),notDuckingTestMazub.getInitialHorizontalVelocityForUpdate()));
	}

	@Test
	public void testEndDuck() {
		testMazub.startDuck();
		assertTrue(testMazub.isDucking());
		testMazub.endDuck();
		assertFalse(testMazub.isDucking());
	}
	
	@Test
	public void testAdvanceTime_NotMoving() {
		testMazub.setHorizontalVelocity(0);
		testMazub.setVerticalVelocity(0);
		testMazub.advanceTime(0.15);
		assertEquals(testMazub.getEffectiveHorizontalLocation(),0);
		assertEquals(testMazub.getEffectiveVerticalLocation(),499);
		assertTrue(Util.fuzzyEquals(testMazub.getHorizontalVelocity(),0));
		assertTrue(Util.fuzzyEquals(testMazub.getVerticalVelocity(),0));
		
	}

	@Test
	public void testAdvanceTime_Moving() {
		testWorld = new World(500, 3, 3, 1000, 1000, 2, 2);
		testWorld.setTileValueAtTilePosition( 0, 0, FEATURE_SOLID);
		testMazub = new Mazub(0, 499, 1, 1, 1, 3, false, spriteArrayForSize(2, 2));
		testMazub.setWorld(testWorld);
		testWorld.setMazub(testMazub);
		testMazub.advanceTime(0.15);
		assertEquals(testMazub.getEffectiveHorizontalLocation(),16);
		assertEquals(testMazub.getEffectiveVerticalLocation(),502);
		assertTrue(Util.fuzzyEquals(testMazub.getHorizontalVelocity(),1.135));
		assertTrue(Util.fuzzyEquals(testMazub.getVerticalVelocity(),-0.5));		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAdvanceTime_Exception() throws Exception{
		testMazub.advanceTime(0.21);
	}
	
	@Test
	public void testIsValidDeltaTime_TrueCase() {
		assertTrue(Mazub.isValidDeltaTime(0.1));
	}

	@Test
	public void testIsValidDeltaTime_TrueCase_0() {
		assertTrue(Mazub.isValidDeltaTime(0));
	}
	
	@Test
	public void testIsValidDeltaTime_FalseCase_Negative() {
		assertFalse(Mazub.isValidDeltaTime(-0.15));
	}
	
	@Test
	public void testIsValidDeltaTime_FalseCase_GreaterThanMax() {
		assertFalse(Mazub.isValidDeltaTime(0.21));
	}
	
	@Test
	public void testIsValidDeltaTime_FalseCase_NaN() {
		assertFalse(Mazub.isValidDeltaTime(Double.NaN));
	}
	
	@Test
	public void testIsValidDeltaTime_FalseCase_Infinity() {
		assertFalse(Mazub.isValidDeltaTime(Double.NEGATIVE_INFINITY));
		assertFalse(Mazub.isValidDeltaTime(Double.POSITIVE_INFINITY));
	}
	
	@Test
	public void testcanHaveAsNbImages_TrueCases(){
		assertTrue(staticTestMazub.canHaveAsNbImages(10)); //min
		assertTrue(staticTestMazub.canHaveAsNbImages(16));
	}
	
	@Test
	public void testcanHaveAsNbImages_FalseCase_TooFew(){
		assertFalse(staticTestMazub.canHaveAsNbImages(6));
		assertFalse(staticTestMazub.canHaveAsNbImages(0));
	}
	
	@Test
	public void testcanHaveAsNbImages_FalseCase_Negative(){
		assertFalse(staticTestMazub.canHaveAsNbImages(-8));
		assertFalse(staticTestMazub.canHaveAsNbImages(-10));
		assertFalse(staticTestMazub.canHaveAsNbImages(-11));
	}
	
	@Test
	public void testcanHaveAsNbImages_FalseCase_Odd(){
		assertFalse(staticTestMazub.canHaveAsNbImages(7));
		assertFalse(staticTestMazub.canHaveAsNbImages(11));
		assertFalse(staticTestMazub.canHaveAsNbImages(25));
	}
	
	@Test
	public void testCanHaveAsSpriteIndex_TrueCases(){
		assertTrue(staticTestMazub.canHaveAsSpriteIndex(0)); //min
		assertTrue(staticTestMazub.canHaveAsSpriteIndex(30)); //max
		assertTrue(staticTestMazub.canHaveAsSpriteIndex(15));
	}
	
	@Test
	public void testCanHaveAsSpriteIndex_FalseCases_NegativeIndex(){
		assertFalse(staticTestMazub.canHaveAsSpriteIndex(-1));
		assertFalse(staticTestMazub.canHaveAsSpriteIndex(-30));
		assertFalse(staticTestMazub.canHaveAsSpriteIndex(-15));
	}
	
	@Test
	public void testCanHaveAsSpriteIndex_FalseCases_GreaterThanMax(){
		assertFalse(staticTestMazub.canHaveAsSpriteIndex(31));
		assertFalse(staticTestMazub.canHaveAsSpriteIndex(32));
	}
	
	@Test
	public void testIsValidImage_TrueCase(){
		assertTrue(Mazub.isValidImage(staticTestMazub.getCurrentSprite()));
	}
	
	@Test
	public void testIsValidImage_FalseCase(){
		assertFalse(Mazub.isValidImage(null));
	}
	
	@Test
	public void testSetImages() {
		Sprite images [] = spriteArrayForSize(5, 10,16);
		testMazub.setImages(images);
		assertArrayEquals(testMazub.getImages(),images);
		assertEquals(testMazub.getNbImages(),16);
		assertEquals(testMazub.getWidth(),5);
		assertEquals(testMazub.getHeight(),10);
	}
	
	@Test
	public void testIsUntouchable_trueCase(){
		testMazub.removeHitPoints(1);
		testMazub.setTimeSinceLastHitpointsLoss(0);
		assertTrue(testMazub.isUntouchable());
		testMazub.advanceTime(0.19);
		assertTrue(testMazub.isUntouchable());
	}
	
	@Test
	public void testIsUntouchable_falseCase(){
		testMazub.setTimeSinceLastHitpointsLoss(0.7);
		assertFalse(testMazub.isUntouchable());
		testMazub.advanceTime(0.19);
		assertFalse(testMazub.isUntouchable());
	}

	@Test
	public void setTimeSinceLastHitpointsLoss_TrueCases(){
		testMazub.setTimeSinceLastHitpointsLoss(2.1);
		assertTrue(Util.fuzzyEquals(testMazub.getTimeSinceLastHitpointsLoss(),2.1));
		testMazub.setTimeSinceLastHitpointsLoss(0);
		assertTrue(Util.fuzzyEquals(testMazub.getTimeSinceLastHitpointsLoss(),0));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setTimeSinceLastHitpointsLoss_FalseCases() throws Exception{
		testMazub.setTimeSinceLastHitpointsLoss(-0.1);
		testMazub.setTimeSinceLastHitpointsLoss(-0.6);
		testMazub.setTimeSinceLastHitpointsLoss(-10);
	}
}