package jumpingalien.part3.tests;


import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.*;
import jumpingalien.model.Shark;
import jumpingalien.model.World;
import jumpingalien.util.Util;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



public class SharkTest {

	private static Shark staticTestShark;
	private static World staticTestWorld;
	public static final int FEATURE_AIR = 0;
	public static final int FEATURE_SOLID = 1;
	public static final int FEATURE_WATER = 2;
	public static final int FEATURE_MAGMA = 3;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		staticTestWorld = new World(500, 3, 3, 1000, 1000, 2, 2);
		staticTestWorld.setTileValueAtTilePosition( 0, 0, FEATURE_WATER);
		staticTestShark = new Shark(0, 499, spriteArrayForSize(3, 3));
		staticTestWorld.addAsGameObject(staticTestShark);
	}

	private Shark testShark;
	private World testWorld;
	
	@Before
	public void setUp() throws Exception {
		testWorld = new World(500, 3, 3, 1000, 1000, 2, 2);
		testWorld.setTileValueAtTilePosition( 0, 0, FEATURE_WATER);
		testShark = new Shark(100, 300, spriteArrayForSize(3, 3));
		testWorld.addAsGameObject(testShark);		
	}
	
	@Test
	public void testCanHaveAsHorizontalVelocity_TrueCase(){
		assertTrue(testShark.canHaveAsHorizontalVelocity(0));
		assertTrue(testShark.canHaveAsHorizontalVelocity(0.5));
		assertTrue(testShark.canHaveAsHorizontalVelocity(4));
	}
	
	@Test
	public void testCanHaveAsHorizontalVelocity_FalseCase(){
		assertFalse(testShark.canHaveAsHorizontalVelocity(-4.1));
		assertFalse(testShark.canHaveAsHorizontalVelocity(5.1));
		assertFalse(testShark.canHaveAsHorizontalVelocity(10));
	}
	
	@Test
	public void testIsPossibleInitialHorizontalVelocity_TrueCase(){
		assertTrue(testShark.isPossibleInitialHorizontalVelocity(0));
	}
	
	@Test
	public void testIsPossibleInitialHorizontalVelocity_FalseCase(){
		assertFalse(testShark.isPossibleInitialHorizontalVelocity(0.4));
		assertFalse(testShark.isPossibleInitialHorizontalVelocity(-0.5));
	}
	
	@Test
	public void testIsPossibleMaximumHorizontalVelocity_TrueCase(){
		assertTrue(testShark.isPossibleMaximumHorizontalVelocity(4));
	}
	
	@Test
	public void testIsPossibleMaximumHorizontalVelocity_FalseCase(){
		assertFalse(testShark.isPossibleMaximumHorizontalVelocity(0));
		assertFalse(testShark.isPossibleMaximumHorizontalVelocity(4.1));
		assertFalse(testShark.isPossibleMaximumHorizontalVelocity(-4.5));
	}
	
	@Test
	public void testCanHaveAsVerticalAcceleration_TrueCase(){
		assertTrue(testShark.canHaveAsVerticalAcceleration(0));
		assertTrue(testShark.canHaveAsVerticalAcceleration(0.2));
		assertTrue(testShark.canHaveAsVerticalAcceleration(-0.2));
		assertTrue(testShark.canHaveAsVerticalAcceleration(-10));
		assertTrue(testShark.canHaveAsVerticalAcceleration(0.1));
	}
	
	@Test
	public void testCanHaveAsVerticalAcceleration_FalseCase(){
		assertFalse(testShark.canHaveAsVerticalAcceleration(0.3));
		assertFalse(testShark.canHaveAsVerticalAcceleration(-9));
	}
	
	@Test
	public void testCanHaveAsHorizontalAcceleration_TrueCase(){
		assertTrue(testShark.canHaveAsHorizontalAcceleration(1.5));
	}
	
	@Test
	public void testCanHaveAsHorizontalAcceleration_FalseCase(){
		assertFalse(testShark.canHaveAsHorizontalAcceleration(1.4));
		assertFalse(testShark.canHaveAsHorizontalAcceleration(-1.5));
		assertFalse(testShark.canHaveAsHorizontalAcceleration(0));
	}
	
	@Test
	public void testCanHaveAsDuckingState_TrueCase(){
		assertTrue(testShark.canHaveAsDuckingState(false));
	}
	
	@Test
	public void testCanHaveAsDuckingState_FalseCase(){
		assertFalse(testShark.canHaveAsDuckingState(true));
	}
	
	@Test
	public void testCanHaveAsNbImages_TrueCase(){
		assertTrue(testShark.canHaveAsNbImages(2));
	}
	
	@Test
	public void testCanHaveAsNbImages_FalseCase(){
		assertFalse(testShark.canHaveAsNbImages(3));
		assertFalse(testShark.canHaveAsNbImages(0));
		assertFalse(testShark.canHaveAsNbImages(-5));
	}
	
	//Nominal method
	@Test
	public void testCurrentActionDuration(){
		testShark.setCurrentActionDuration(0);
		assertTrue(Util.fuzzyEquals(testShark.getCurrentActionDuration(),0));
		testShark.setCurrentActionDuration(1);
		assertTrue(Util.fuzzyEquals(testShark.getCurrentActionDuration(),1));
	}
	
	@Test
	public void testSetNbMovementsSinceLastJump_TrueCase(){
		testShark.setNbMovementsSinceLastJump(0);
		assertTrue(Util.fuzzyEquals(testShark.getNbMovementsSinceLastJump(),0));
		testShark.setNbMovementsSinceLastJump(5);
		assertTrue(Util.fuzzyEquals(testShark.getNbMovementsSinceLastJump(),5));
		testShark.setNbMovementsSinceLastJump(500);
		assertTrue(Util.fuzzyEquals(testShark.getNbMovementsSinceLastJump(),500));
	}
	
	@Test
	public void testSetNbMovementsSinceLastJump_FalseCase(){
		testShark.setNbMovementsSinceLastJump(-1);
		assertTrue(Util.fuzzyEquals(testShark.getNbMovementsSinceLastJump(),0));
	}
	
	@Test
	public void testIsValidNbMovementsSinceLastJump_TrueCase(){
		assertTrue(Shark.isValidNbMovements(0));
		assertTrue(Shark.isValidNbMovements(10));
		assertTrue(Shark.isValidNbMovements(5));
	}
	
	@Test
	public void testIsValidNbMovementsSinceLastJump_FalseCase(){
		assertFalse(Shark.isValidNbMovements(-130));
		assertFalse(Shark.isValidNbMovements(-10));
		assertFalse(Shark.isValidNbMovements(-5));
	}
	
	@Test
	public void testIsValidChance_TrueCase(){
		assertTrue(Shark.isValidChance(0));
		assertTrue(Shark.isValidChance(.5));
		assertTrue(Shark.isValidChance(1));
	}
	
	@Test
	public void testIsValidChance_FalseCase(){
		assertFalse(Shark.isValidChance(-0.1));
		assertFalse(Shark.isValidChance(-.5));
		assertFalse(Shark.isValidChance(-1));
	}
	
	@Test
	public void testIsValidActionDuration_TrueCase(){
		assertTrue(Shark.isValidActionDuration(1));
		assertTrue(Shark.isValidActionDuration(4));
		assertTrue(Shark.isValidActionDuration(2.2));
	}
	
	@Test
	public void testIsValidActionDuration_FalseCase(){
		assertFalse(Shark.isValidActionDuration(0.9));
		assertFalse(Shark.isValidActionDuration(4.1));
		assertFalse(Shark.isValidActionDuration(-2.2));
	}
	
	@Test
	public void testSetRandomDivingMultiplier_TrueCase(){
		testShark.setRandomDivingMultiplier(0.25);
		assertTrue(Util.fuzzyEquals(testShark.getRandomDivingMultiplier(), 0.25));
	}
	
	@Test
	public void testSetRandomDivingMultiplier_FalseCase(){
		testShark.setRandomDivingMultiplier(1.25);
		assertTrue(Util.fuzzyEquals(testShark.getRandomDivingMultiplier(), 0));
	}
	
	@Test
	public void testIsValidRandomDivingMultiplier_TrueCase(){
		assertTrue(Shark.isValidRandomDivingMultiplier(1));
		assertTrue(Shark.isValidRandomDivingMultiplier(-1));
		assertTrue(Shark.isValidRandomDivingMultiplier(0));
		assertTrue(Shark.isValidRandomDivingMultiplier(0.25));
		assertTrue(Shark.isValidRandomDivingMultiplier(-0.46));
	}
	
	@Test
	public void testIsValidRandomDivingMultiplier_FalseCase(){
		assertFalse(Shark.isValidRandomDivingMultiplier(1.1));
		assertFalse(Shark.isValidRandomDivingMultiplier(-1.1));
		assertFalse(Shark.isValidRandomDivingMultiplier(25));
		assertFalse(Shark.isValidRandomDivingMultiplier(-46));
	}
	
	@Test
	public void testAdvanceTime_Moving() {
		testShark.advanceTime(0.15);
		assertTrue(Util.fuzzyLessThanOrEqualTo(testShark.getEffectiveHorizontalLocation(), 103) 
				|| Util.fuzzyGreaterThanOrEqualTo(testShark.getEffectiveHorizontalLocation(), 97));
		assertTrue(Util.fuzzyLessThanOrEqualTo(testShark.getEffectiveVerticalLocation(), 331) 
				|| Util.fuzzyGreaterThanOrEqualTo(testShark.getEffectiveVerticalLocation(), 268));
		assertTrue(Util.fuzzyLessThanOrEqualTo(testShark.getHorizontalVelocity(), 0.225) 
				|| Util.fuzzyGreaterThanOrEqualTo(testShark.getHorizontalVelocity(), -0.225));
		assertTrue(Util.fuzzyLessThanOrEqualTo(testShark.getVerticalVelocity(), 2) 
				|| Util.fuzzyGreaterThanOrEqualTo(testShark.getVerticalVelocity(), -2));		
	}
	
	@Test
	public void testSetTimeSinceAirContact_TrueCase(){
		testShark.setTimeSinceStartAirContact(0);
		assertTrue(Util.fuzzyEquals(testShark.getTimeSinceStartAirContact(), 0));
		testShark.setTimeSinceStartAirContact(4);
		assertTrue(Util.fuzzyEquals(testShark.getTimeSinceStartAirContact(), 4));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetTimeSinceAirContact_FalseCase() throws Exception{
		testShark.setTimeSinceStartAirContact(-0.1);
		testShark.setTimeSinceStartAirContact(-2.4);
	}
	
}
