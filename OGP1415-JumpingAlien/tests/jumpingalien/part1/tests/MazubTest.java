package jumpingalien.part1.tests;

import static org.junit.Assert.*;
import jumpingalien.model.Mazub;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static jumpingalien.tests.util.TestUtils.*;

public class MazubTest {

	private static Mazub staticTestMazub, duckingMazub, notDuckingMazub, jumpingMazub1, jumpingMazub2;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		staticTestMazub = new Mazub(0, 0, 0, 0, 1, 3, false, 1,spriteArrayForSize(2, 2));
		duckingMazub = new Mazub(0, 0, 0, 0, 1, 3, true, 1,spriteArrayForSize(2, 2));
		notDuckingMazub = new Mazub(0, 0, 0, 0, 1, 3, false, 1,spriteArrayForSize(2, 2));
		jumpingMazub1 = new Mazub(0, 0, 0, 8, 1, 3, false, 1,spriteArrayForSize(2, 2));
		jumpingMazub2 = new Mazub(0, 40, 0, 3, 1, 3, false, 1,spriteArrayForSize(2, 2));
	}

	private Mazub testMazub;
	
	@Before
	public void setUp() throws Exception {
		testMazub = new Mazub(0, 0,spriteArrayForSize(2, 2));
	}

	@After
	public void tearDown() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void testSetHorizontalLocation_LegalCase() {
		testMazub.setHorizontalLocation(10);
		assertEquals(testMazub.getHorizontalLocation(),10);
		
	}

	@Test (expected = IllegalArgumentException.class)
	public void testSetHorizontalLocation_IllegalCase() throws Exception{
		testMazub.setHorizontalLocation(-10);
	}
	
	@Test
	public void testSetVerticalLocation_LegalCase() {
		testMazub.setVerticalLocation(10);
		assertEquals(testMazub.getVerticalLocation(),10);
	}

	@Test (expected = IllegalArgumentException.class)
	public void testSetVerticalLocation_IllegalCase() throws Exception{
		testMazub.setVerticalLocation(-20);
	}
	
	@Test
	public void testIsValidHorizontalLocation_TrueCase() {
		assertTrue(Mazub.isValidHorizontalLocation(1));
	}
	
	@Test
	public void testIsValidHorizontalLocation_FalseCase_NegativeLocation() {
		assertFalse(Mazub.isValidHorizontalLocation(-1));
	}

	@Test
	public void testIsValidHorizontalLocation_FalseCase_GreaterThanMaximum() {
		assertFalse(Mazub.isValidHorizontalLocation(Mazub.getMaximumHorizontalLocation() + 1));
	} 
	
	@Test
	public void testIsValidHorizontalLocation_FalseCase_NaN() {
		assertFalse(Mazub.isValidHorizontalLocation(Float.NaN));
	}
	
	@Test
	public void testIsValidVerticalLocation_TrueCase() {
		assertTrue(Mazub.isValidVerticalLocation(1));
	}

	@Test
	public void testIsValidVerticalLocation_FalseCase_NegativeLocation() {
		assertFalse(Mazub.isValidVerticalLocation(-1));
	}
	
	@Test
	public void testIsValidVerticalLocation_FalseCase_GreaterThanMaximum() {
		assertFalse(Mazub.isValidVerticalLocation(Mazub.getMaximumVerticalLocation() + 1));
	}
	
	@Test
	public void testIsValidVerticalLocation_FalseCase_NaN() {
		assertFalse(Mazub.isValidVerticalLocation(Float.NaN));
	}
	
	@Test
	public void testIsValidLocation_TrueCase() {
		assertTrue(Mazub.isValidLocation(1,1));
	}

	@Test
	public void testIsValidLocation_FalseCase_NegativeLocation() {
		assertFalse(Mazub.isValidLocation(-1,-1));
	}
	
	@Test
	public void testIsValidLocation_FalseCase_PartiallyNegativeLocation() {
		assertFalse(Mazub.isValidLocation(1,-1));
	}
	
	@Test
	public void testIsValidLocation_FalseCase_GreaterThanMaximum() {
		assertFalse(Mazub.isValidLocation(Mazub.getMaximumHorizontalLocation() + 1,1));
	}
	
	@Test
	public void testIsValidLocation_FalseCase_NaN() {
		assertFalse(Mazub.isValidLocation(Float.NaN,Float.NaN));
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
		testMazub.setHorizontalVelocity(testMazub.getMaximumHorizontalVelocity()+1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetHorizontalVelocity_FalseCase2() throws IllegalArgumentException{
		testMazub.setHorizontalVelocity(0.5);
	}
	
	@Test
	public void testSetVerticalVelocity_TrueCase_PositiveValue() {
		testMazub.setVerticalVelocity(-5);
		assertTrue(Util.fuzzyEquals(testMazub.getVerticalVelocity(),-5));
	}
	
	@Test
	public void testSetVerticalVelocity_TrueCase_NegativeValue() {
		testMazub.setVerticalVelocity(Mazub.getInitialVerticalVelocity());
		assertTrue(Util.fuzzyEquals(testMazub.getVerticalVelocity(),Mazub.getInitialVerticalVelocity()));
	}

	@Test (expected = IllegalArgumentException.class)
	public void testSetVerticalVelocity_FalseCase() throws IllegalArgumentException {
		testMazub.setVerticalVelocity(Mazub.getInitialVerticalVelocity()+1);
	}
	
	@Test
	public void testSetMaximumHorizontalVelocity_TrueCase() {
		testMazub.setMaximumHorizontalVelocity(10);
		assertTrue(Util.fuzzyEquals(testMazub.getMaximumHorizontalVelocity(),10));
	}

	@Test (expected = IllegalArgumentException.class)
	public void testSetMaximumHorizontalVelocity_FalseCase() throws IllegalArgumentException {
		testMazub.setMaximumHorizontalVelocity(testMazub.getInitialHorizontalVelocity()-0.1);
	}
	
//	@Test
//	public boolean testCanHaveAsInitialHorizontalVelocity_TrueCase() {
//		assertTrue();
//	}

//	@Test
//	public void testIsValidMaximumHorizontalVelocity() {
//		fail("Not yet implemented");
//	}

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
		assertFalse(staticTestMazub.canHaveAsHorizontalVelocity(0.5));
	}
	
	@Test
	public void testCanHaveAsHorizontalVelocity_FalseCase2() {
		assertFalse(staticTestMazub.canHaveAsHorizontalVelocity(
				staticTestMazub.getMaximumHorizontalVelocity() + 1));
	}
	
	@Test
	public void testIsValidVerticalVelocity_TrueCase() {
		assertTrue(Mazub.isValidVerticalVelocity(Mazub.getInitialVerticalVelocity()-5));
	}

	@Test
	public void testIsValidVerticalVelocity_FalseCase() {
		assertFalse(Mazub.isValidVerticalVelocity(Mazub.getInitialVerticalVelocity() + 0.1));
	}
	
	/**
	 * Test voor nominale methode!
	 */
	@Test
	public void testSetDirection_TrueCase() {
		testMazub.setDirection(-1);
		assertEquals(testMazub.getDirection(),-1);
	}
	
	/**
	 * Test voor nominale methode!
	 */
	@Test
	public void testSetDirection_FalseCase() {
		testMazub.setDirection(-1);
		assertNotEquals(testMazub.getDirection(),1);
	}
	
	@Test
	public void testIsDucking_TrueCase() {
		assertTrue(duckingMazub.isDucking());
	}

	@Test
	public void testIsDucking_FalseCase() {
		assertFalse(notDuckingMazub.isDucking());
	}
	
	@Test
	public void testSetDucking() {
		testMazub.setDucking(true);
		assertTrue(testMazub.isDucking());
	}
	
	@Test
	public void testIsJumping1() {
		assertTrue(jumpingMazub1.isJumping());
	}

	@Test
	public void testIsJumping2() {
		assertTrue(jumpingMazub2.isJumping());
	}
	
	@Test
	public void testIsJumping() {
		assertFalse(staticTestMazub.isJumping());
	}
	
//	@Test
//	public void testStartMove() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testIsValidDirection() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testEndMove() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testAdvanceTime() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testIsValidDeltaTime() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testStartJump() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testEndJump() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testStartDuck() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testEndDuck() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSetCurrentSpriteIndex() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testSetImages() {
//		fail("Not yet implemented");
//	}
//

}
