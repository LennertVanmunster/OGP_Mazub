package jumpingalien.part3.tests;


import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.*;
import jumpingalien.model.Orientation;
import jumpingalien.model.Mazub;
import jumpingalien.model.World;
import jumpingalien.util.Util;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



public class MazubTest {

	private static Mazub staticTestMazub;
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
	public void testIsPossibleMaximumHorizontalVelocity_TrueCase(){
		assertTrue(staticTestMazub.isPossibleMaximumHorizontalVelocity(0.1));
		assertTrue(staticTestMazub.isPossibleMaximumHorizontalVelocity(5));
		assertTrue(staticTestMazub.isPossibleMaximumHorizontalVelocity(Double.POSITIVE_INFINITY));
	}

	@Test
	public void testIsPossibleMaximumHorizontalVelocity_FalseCase(){
		assertFalse(staticTestMazub.isPossibleMaximumHorizontalVelocity(-0.2));
		assertFalse(staticTestMazub.isPossibleMaximumHorizontalVelocity(0));
		assertFalse(staticTestMazub.isPossibleMaximumHorizontalVelocity(-10.4));
		assertFalse(staticTestMazub.isPossibleMaximumHorizontalVelocity(Double.NEGATIVE_INFINITY));
	}
	
	@Test
	public void testCanHaveAsHorizontalAcceleration_TrueCase(){
		assertTrue(staticTestMazub.canHaveAsHorizontalAcceleration(0.1));
		assertTrue(staticTestMazub.canHaveAsHorizontalAcceleration(12.3));
		assertTrue(staticTestMazub.canHaveAsHorizontalAcceleration(Double.POSITIVE_INFINITY));
		assertTrue(staticTestMazub.canHaveAsHorizontalAcceleration(-5));
		assertTrue(staticTestMazub.canHaveAsHorizontalAcceleration(Double.NEGATIVE_INFINITY));
	}
	
	@Test
	public void testCanHaveAsHorizontalAcceleration_FalseCase(){
		assertFalse(staticTestMazub.canHaveAsHorizontalAcceleration(0));	
	}
	
	@Test
	public void testCanHaveAsVerticalAcceleration_TrueCase(){
		assertTrue(staticTestMazub.canHaveAsVerticalAcceleration(0));	
		assertTrue(staticTestMazub.canHaveAsVerticalAcceleration(-10));	
	}
	
	@Test
	public void testCanHaveAsVerticalAcceleration_FalseCase(){
		assertFalse(staticTestMazub.canHaveAsVerticalAcceleration(0.1));	
		assertFalse(staticTestMazub.canHaveAsVerticalAcceleration(-9.9));
		assertFalse(staticTestMazub.canHaveAsVerticalAcceleration(5));	

	}
	
	@Test
	public void testStartMove() {
		testMazub.startMove(Orientation.RIGHT);
		//1 is the initial horizontal velocity
		assertTrue(Util.fuzzyEquals(testMazub.getHorizontalVelocity(),1));
		assertTrue(Util.fuzzyEquals(testMazub.getTimeSinceStartMove(),0));
		assertTrue(Util.fuzzyEquals(testMazub.getTimeSinceEndMove(),0));
		
	}
	
	@Test
	public void testEndMove() {
		testMazub.startMove(Orientation.RIGHT);
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
	public void testCanHaveAsDuckingState(){
		assertTrue(staticTestMazub.canHaveAsDuckingState(true));
		assertTrue(staticTestMazub.canHaveAsDuckingState(false));
	}
	
	@Test
	public void testStartDuck() {
		notDuckingTestMazub.startDuck();
		assertTrue(notDuckingTestMazub.isDucking());
		notDuckingTestMazub.startMove(Orientation.RIGHT);
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
	
	
	
}