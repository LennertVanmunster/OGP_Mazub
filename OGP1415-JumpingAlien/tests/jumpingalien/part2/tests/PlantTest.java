package jumpingalien.part2.tests;


import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.*;
import jumpingalien.model.Direction;
import jumpingalien.model.Mazub;
import jumpingalien.model.Plant;
import jumpingalien.model.World;
import jumpingalien.util.Util;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



public class PlantTest {

	private static Plant staticTestPlant;
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
		staticTestPlant = new Plant(100, 550, spriteArrayForSize(3, 3));
		staticTestPlant.setWorld(staticTestWorld);
		staticTestWorld.addAsGameObject(staticTestPlant);
	}

	private Plant testPlant;
	private World testWorld;
	private Mazub testMazub;
	
	@Before
	public void setUp() throws Exception {
		testWorld = new World(500, 3, 3, 1000, 1000, 2, 2);
		testWorld.setTileValueAtTilePosition( 0, 0, FEATURE_SOLID);
		testWorld.setTileValueAtTilePosition( 1, 1, FEATURE_SOLID);
		testPlant = new Plant(100, 550, spriteArrayForSize(3, 3));
		testPlant.setWorld(testWorld);
		testWorld.addAsGameObject(testPlant);
		//Used in advance time of plant
		testMazub = new Mazub(0, 499, spriteArrayForSize(3, 3));
		testMazub.setWorld(testWorld);
		testWorld.setMazub(testMazub);
	}
	
	@Test
	public void testIsValidVelocityConstant_TrueCase(){
		assertTrue(Plant.isValidVelocityConstant(0.1));
		assertTrue(Plant.isValidVelocityConstant(3));
		assertTrue(Plant.isValidVelocityConstant(12));
	}
	
	@Test
	public void testIsValidVelocityConstant_FalseCase(){
		assertFalse(Plant.isValidVelocityConstant(-0.1));
		assertFalse(Plant.isValidVelocityConstant(-3));
		assertFalse(Plant.isValidVelocityConstant(-12));
	}
	
	@Test
	public void testCanHaveAsHorizontalVelocity_TrueCase(){
		assertTrue(testPlant.canHaveAsHorizontalVelocity(0));
		assertTrue(testPlant.canHaveAsHorizontalVelocity(0.5));
		assertTrue(testPlant.canHaveAsHorizontalVelocity(-0.5));
	}
	
	@Test
	public void testCanHaveAsHorizontalVelocity_FalseCase(){
		assertFalse(testPlant.canHaveAsHorizontalVelocity(0.1));
		assertFalse(testPlant.canHaveAsHorizontalVelocity(0.4));
		assertFalse(testPlant.canHaveAsHorizontalVelocity(-0.6));
	}

	@Test
	public void testIsPossibleInitialHorizontalVelocity_TrueCase(){
		assertTrue(testPlant.isPossibleInitialHorizontalVelocity(0.5));
	}
	
	@Test
	public void testIsPossibleInitialHorizontalVelocity_FalseCase(){
		assertFalse(testPlant.isPossibleInitialHorizontalVelocity(0.4));
		assertFalse(testPlant.isPossibleInitialHorizontalVelocity(-0.5));

	}
	
	@Test
	public void testIsPossibleMaximumHorizontalVelocity_TrueCase(){
		assertTrue(testPlant.isPossibleMaximumHorizontalVelocity(0.5));
	}
	
	@Test
	public void testIsPossibleMaximumHorizontalVelocity_FalseCase(){
		assertFalse(testPlant.isPossibleMaximumHorizontalVelocity(0));
		assertFalse(testPlant.isPossibleMaximumHorizontalVelocity(0.6));
		assertFalse(testPlant.isPossibleMaximumHorizontalVelocity(-0.5));
	}
	
	@Test
	public void testCanHaveAsDuckingState_TrueCase(){
		assertTrue(testPlant.canHaveAsDuckingState(false));
	}
	
	@Test
	public void testCanHaveAsDuckingState_FalseCase(){
		assertFalse(testPlant.canHaveAsDuckingState(true));
	}
	
	@Test
	public void testCanHaveAsVerticalAcceleration_TrueCase(){
		assertTrue(testPlant.canHaveAsVerticalAcceleration(0));
	}
	
	@Test
	public void testCanHaveAsVerticalAcceleration_FalseCase(){
		assertFalse(testPlant.canHaveAsVerticalAcceleration(0.1));
		assertFalse(testPlant.canHaveAsVerticalAcceleration(-0.5));
	}
	
	@Test
	public void testCanHaveAsNbImages_TrueCase(){
		assertTrue(testPlant.canHaveAsNbImages(2));
	}
	
	@Test
	public void testCanHaveAsNbImages_FalseCase(){
		assertFalse(testPlant.canHaveAsNbImages(3));
		assertFalse(testPlant.canHaveAsNbImages(0));
		assertFalse(testPlant.canHaveAsNbImages(-5));
	}

	@Test
	public void testAdvanceTime_Moving() {
		testPlant.advanceTime(0.15);
		assertEquals(testPlant.getEffectiveHorizontalLocation(),108);
		assertEquals(testPlant.getEffectiveVerticalLocation(),550);
		assertTrue(Util.fuzzyEquals(testPlant.getHorizontalVelocity(),0.5));
		assertTrue(Util.fuzzyEquals(testPlant.getVerticalVelocity(),0));		
	}
	
}
