package jumpingalien.part3.tests;


import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import jumpingalien.model.Orientation;
import jumpingalien.model.Buzam;
import jumpingalien.model.World;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.statements.StartJump;
import jumpingalien.programs.statements.Statement;
import jumpingalien.programs.types.Type;
import jumpingalien.util.Util;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



public class BuzamTest {

	private static Program program;
	private static Buzam staticTestBuzam;
	private static World staticTestWorld;
	public static final int FEATURE_AIR = 0;
	public static final int FEATURE_SOLID = 1;
	public static final int FEATURE_WATER = 2;
	public static final int FEATURE_MAGMA = 3;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Map<String,Type<?>> globalVariables = new HashMap<String, Type<?>>();
		Statement statement =  new StartJump(null);
		program = new Program(statement, globalVariables);
		staticTestWorld = new World(500, 3, 3, 1000, 1000, 2, 2);
		staticTestWorld.setTileValueAtTilePosition( 0, 0, FEATURE_SOLID);
		staticTestWorld.setTileValueAtTilePosition( 1, 1, FEATURE_SOLID);
		staticTestBuzam = new Buzam(0, 499, spriteArrayForSize(3, 3));
		staticTestBuzam.setWorld(staticTestWorld);
		staticTestWorld.setBuzam(staticTestBuzam);
	}

	private Buzam testBuzam;
	private Buzam notJumpingTestBuzam;
	private Buzam jumpingTestBuzam;
	private Buzam notDuckingTestBuzam;
	private World testWorld;
	
	@Before
	public void setUp() throws Exception {
		testWorld = new World(500, 3, 3, 1000, 1000, 2, 2);
		testWorld.setTileValueAtTilePosition( 0, 0, FEATURE_SOLID);
		testWorld.setTileValueAtTilePosition( 1, 1, FEATURE_SOLID);
		testBuzam = new Buzam(0, 499, spriteArrayForSize(3, 3));
		testBuzam.setWorld(testWorld);
		testWorld.setBuzam(testBuzam);		
		jumpingTestBuzam = new Buzam(25, 500, 0, 3, 1, 3, false, program, spriteArrayForSize(2, 2));
		jumpingTestBuzam.setWorld(testWorld);
		testWorld.setBuzam(jumpingTestBuzam);
		notJumpingTestBuzam = new Buzam(50, 499, 0, 0, 1, 3, false, program, spriteArrayForSize(2, 2));
		notJumpingTestBuzam.setWorld(testWorld);
		testWorld.setBuzam(notJumpingTestBuzam);
		notDuckingTestBuzam = new Buzam(100, 499, 0, 0, 1, 3, false, program, spriteArrayForSize(2, 2));
		notDuckingTestBuzam.setWorld(testWorld);
		testWorld.setBuzam(notDuckingTestBuzam);
	}
	
	@Test
	public void testCanHaveAsHorizontalVelocity_TrueCase() {
		assertTrue(staticTestBuzam.canHaveAsHorizontalVelocity(2));
	}

	@Test
	public void testCanHaveAsHorizontalVelocity_TrivialCase() {
		assertTrue(staticTestBuzam.canHaveAsHorizontalVelocity(0));
	}
	@Test
	public void testCanHaveAsHorizontalVelocity_FalseCase() {
		assertFalse(staticTestBuzam.canHaveAsHorizontalVelocity(0.1));
	}
	
	@Test
	public void testCanHaveAsHorizontalVelocity_FalseCase2() {
		//3 is the maximum horizontal velocity
		assertFalse(staticTestBuzam.canHaveAsHorizontalVelocity(3 + 0.1));
	}
	
	@Test
	public void testCanHaveAsHorizontalVelocity_FalseCase_NaN() {
		assertFalse(staticTestBuzam.canHaveAsHorizontalVelocity(Double.NaN));
	}
	
	@Test
	public void testIsPossibleInitialHorizontalVelocity_TrueCase(){
		assertTrue(staticTestBuzam.isPossibleInitialHorizontalVelocity(1));
		assertTrue(staticTestBuzam.isPossibleInitialHorizontalVelocity(1.1));
		assertTrue(staticTestBuzam.isPossibleInitialHorizontalVelocity(14));
	}
	

	@Test
	public void testIsPossibleInitialHorizontalVelocity_TrueCase_PositiveInfinity(){
		assertTrue(staticTestBuzam.isPossibleInitialHorizontalVelocity(Double.POSITIVE_INFINITY));
	}
	

	@Test
	public void testIsPossibleInitialHorizontalVelocity_FalseCase(){
		assertFalse(staticTestBuzam.isPossibleInitialHorizontalVelocity(0));
		assertFalse(staticTestBuzam.isPossibleInitialHorizontalVelocity(0.99));
		assertFalse(staticTestBuzam.isPossibleInitialHorizontalVelocity(-1));
	}
	
	@Test
	public void testIsPossibleInitialHorizontalVelocity_FalseCase_NegativeInfinity(){
		assertFalse(staticTestBuzam.isPossibleInitialHorizontalVelocity(Double.NEGATIVE_INFINITY));
	}
	
	@Test
	public void testIsPossibleMaximumHorizontalVelocity_TrueCase(){
		assertTrue(staticTestBuzam.isPossibleMaximumHorizontalVelocity(0.1));
		assertTrue(staticTestBuzam.isPossibleMaximumHorizontalVelocity(5));
		assertTrue(staticTestBuzam.isPossibleMaximumHorizontalVelocity(Double.POSITIVE_INFINITY));
	}

	@Test
	public void testIsPossibleMaximumHorizontalVelocity_FalseCase(){
		assertFalse(staticTestBuzam.isPossibleMaximumHorizontalVelocity(-0.2));
		assertFalse(staticTestBuzam.isPossibleMaximumHorizontalVelocity(0));
		assertFalse(staticTestBuzam.isPossibleMaximumHorizontalVelocity(-10.4));
		assertFalse(staticTestBuzam.isPossibleMaximumHorizontalVelocity(Double.NEGATIVE_INFINITY));
	}
	
	@Test
	public void testCanHaveAsHorizontalAcceleration_TrueCase(){
		assertTrue(staticTestBuzam.canHaveAsHorizontalAcceleration(0.1));
		assertTrue(staticTestBuzam.canHaveAsHorizontalAcceleration(12.3));
		assertTrue(staticTestBuzam.canHaveAsHorizontalAcceleration(Double.POSITIVE_INFINITY));
		assertTrue(staticTestBuzam.canHaveAsHorizontalAcceleration(-5));
		assertTrue(staticTestBuzam.canHaveAsHorizontalAcceleration(Double.NEGATIVE_INFINITY));
	}
	
	@Test
	public void testCanHaveAsHorizontalAcceleration_FalseCase(){
		assertFalse(staticTestBuzam.canHaveAsHorizontalAcceleration(0));	
	}
	
	@Test
	public void testCanHaveAsVerticalAcceleration_TrueCase(){
		assertTrue(staticTestBuzam.canHaveAsVerticalAcceleration(0));	
		assertTrue(staticTestBuzam.canHaveAsVerticalAcceleration(-10));	
	}
	
	@Test
	public void testCanHaveAsVerticalAcceleration_FalseCase(){
		assertFalse(staticTestBuzam.canHaveAsVerticalAcceleration(0.1));	
		assertFalse(staticTestBuzam.canHaveAsVerticalAcceleration(-9.9));
		assertFalse(staticTestBuzam.canHaveAsVerticalAcceleration(5));	

	}
	
	@Test
	public void testStartMove() {
		testBuzam.startMove(Orientation.RIGHT);
		//1 is the initial horizontal velocity
		assertTrue(Util.fuzzyEquals(testBuzam.getHorizontalVelocity(),1));
		assertTrue(Util.fuzzyEquals(testBuzam.getTimeSinceStartMove(),0));
		assertTrue(Util.fuzzyEquals(testBuzam.getTimeSinceEndMove(),0));
		
	}
	
	@Test
	public void testEndMove() {
		testBuzam.startMove(Orientation.RIGHT);
		testBuzam.advanceTime(0.19);
		testBuzam.endMove();
		assertTrue(Util.fuzzyEquals(testBuzam.getHorizontalVelocity(),0));
		assertTrue(Util.fuzzyEquals(testBuzam.getTimeSinceStartMove(),0));
		assertTrue(Util.fuzzyEquals(testBuzam.getTimeSinceEndMove(),0));
	}
	
	@Test
	public void testStartJump() {
		notJumpingTestBuzam.startJump();
		assertTrue(notJumpingTestBuzam.isJumping());
		assertTrue(Util.fuzzyEquals(notJumpingTestBuzam.getVerticalVelocity(),8));
	}

	@Test
	public void testEndJump() {
		jumpingTestBuzam.endJump();
		assertFalse(jumpingTestBuzam.isJumping());
		assertTrue(Util.fuzzyEquals(notJumpingTestBuzam.getVerticalVelocity(),0));
	}	

	@Test
	public void testCanHaveAsDuckingState(){
		assertTrue(staticTestBuzam.canHaveAsDuckingState(true));
		assertTrue(staticTestBuzam.canHaveAsDuckingState(false));
	}
	
	@Test
	public void testStartDuck() {
		notDuckingTestBuzam.startDuck();
		assertTrue(notDuckingTestBuzam.isDucking());
		notDuckingTestBuzam.startMove(Orientation.RIGHT);
		assertTrue(Util.fuzzyEquals(notDuckingTestBuzam.getHorizontalVelocity(),notDuckingTestBuzam.getInitialHorizontalVelocityForUpdate()));
	}

	@Test
	public void testEndDuck() {
		testBuzam.startDuck();
		assertTrue(testBuzam.isDucking());
		testBuzam.endDuck();
		assertFalse(testBuzam.isDucking());
	}
	
	@Test
	public void testAdvanceTime_NotMoving() {
		testBuzam.setHorizontalVelocity(0);
		testBuzam.setVerticalVelocity(0);
		testBuzam.advanceTime(0.15);
		assertEquals(testBuzam.getEffectiveHorizontalLocation(),0);
		assertEquals(testBuzam.getEffectiveVerticalLocation(),499);
		assertTrue(Util.fuzzyEquals(testBuzam.getHorizontalVelocity(),0));
		assertTrue(Util.fuzzyEquals(testBuzam.getVerticalVelocity(),0));
		
	}

	@Test
	public void testAdvanceTime_Moving() {
		testWorld = new World(500, 3, 3, 1000, 1000, 2, 2);
		testWorld.setTileValueAtTilePosition( 0, 0, FEATURE_SOLID);
		testBuzam = new Buzam(0, 499, 1, 1, 1, 3, false, null, spriteArrayForSize(2, 2));
		testBuzam.setWorld(testWorld);
		testWorld.setBuzam(testBuzam);
		testBuzam.advanceTime(0.1500);
		assertEquals(testBuzam.getEffectiveHorizontalLocation(),16);
		assertEquals(testBuzam.getEffectiveVerticalLocation(),502);
		assertTrue(Util.fuzzyEquals(testBuzam.getHorizontalVelocity(),1.135));
		assertTrue(Util.fuzzyEquals(testBuzam.getVerticalVelocity(),-0.5));		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAdvanceTime_Exception() throws Exception{
		testBuzam.advanceTime(0.21);
	}
	
	@Test
	public void testIsUntouchable_trueCase(){
		testBuzam.removeHitPoints(1);
		testBuzam.setTimeSinceLastHitpointsLoss(0);
		assertTrue(testBuzam.isUntouchable());
		testBuzam.advanceTime(0.19);
		assertTrue(testBuzam.isUntouchable());
	}
	
	@Test
	public void testIsUntouchable_falseCase(){
		testBuzam.setTimeSinceLastHitpointsLoss(0.7);
		assertFalse(testBuzam.isUntouchable());
		testBuzam.advanceTime(0.19);
		assertFalse(testBuzam.isUntouchable());
	}

	@Test
	public void setTimeSinceLastHitpointsLoss_TrueCases(){
		testBuzam.setTimeSinceLastHitpointsLoss(2.1);
		assertTrue(Util.fuzzyEquals(testBuzam.getTimeSinceLastHitpointsLoss(),2.1));
		testBuzam.setTimeSinceLastHitpointsLoss(0);
		assertTrue(Util.fuzzyEquals(testBuzam.getTimeSinceLastHitpointsLoss(),0));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void setTimeSinceLastHitpointsLoss_FalseCases() throws Exception{
		testBuzam.setTimeSinceLastHitpointsLoss(-0.1);
		testBuzam.setTimeSinceLastHitpointsLoss(-0.6);
		testBuzam.setTimeSinceLastHitpointsLoss(-10);
	}
	
	@Test
	public void testcanHaveAsNbImages_TrueCases(){
		assertTrue(staticTestBuzam.canHaveAsNbImages(10)); //min
		assertTrue(staticTestBuzam.canHaveAsNbImages(16));
	}
	
	@Test
	public void testcanHaveAsNbImages_FalseCase_TooFew(){
		assertFalse(staticTestBuzam.canHaveAsNbImages(6));
		assertFalse(staticTestBuzam.canHaveAsNbImages(0));
	}
	
	@Test
	public void testcanHaveAsNbImages_FalseCase_Negative(){
		assertFalse(staticTestBuzam.canHaveAsNbImages(-8));
		assertFalse(staticTestBuzam.canHaveAsNbImages(-10));
		assertFalse(staticTestBuzam.canHaveAsNbImages(-11));
	}
	
	@Test
	public void testcanHaveAsNbImages_FalseCase_Odd(){
		assertFalse(staticTestBuzam.canHaveAsNbImages(7));
		assertFalse(staticTestBuzam.canHaveAsNbImages(11));
		assertFalse(staticTestBuzam.canHaveAsNbImages(25));
	}
	
	
	
}