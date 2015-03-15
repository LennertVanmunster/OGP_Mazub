package jumpingalien.part1.tests;

import static org.junit.Assert.*;
import jumpingalien.model.Mazub;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static jumpingalien.tests.util.TestUtils.*;

public class MazubTest {

	private static Mazub staticTestMazub;
	private static Mazub movingMazub;
	private static Mazub duckingMazub;
	private static Mazub notDuckingMazub;
	private static Mazub jumpingMazub1;
	private static Mazub jumpingMazub2;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		staticTestMazub = new Mazub(0, 0, 0, 0, 2, 3, false,spriteArrayForSize(2, 2));
		movingMazub = new Mazub(0, 0, 2, 0, 1, 3, false, spriteArrayForSize(2, 2));
		duckingMazub = new Mazub(0, 0, 0, 0, 1, 3, true, spriteArrayForSize(2, 2));
		notDuckingMazub = new Mazub(0, 0, 0, 0, 1, 3, false, spriteArrayForSize(2, 2));
		jumpingMazub1 = new Mazub(0, 0, 0, 8, 1, 3, false, spriteArrayForSize(2, 2));
		jumpingMazub2 = new Mazub(0, 40, 0, 3, 1, 3, false, spriteArrayForSize(2, 2));
	}

	private Mazub testMazub;
	private Mazub notJumpingTestMazub;
	private Mazub JumpingTestMazub;
	private Mazub notDuckingTestMazub;
	private Mazub duckingTestMazub;
	
	@Before
	public void setUp() throws Exception {
		testMazub = new Mazub(0, 0, 0, 0, 1, 3, false, spriteArrayForSize(2, 2));
		notJumpingTestMazub = new Mazub(0, 0, 0, 0, 1, 3, false, spriteArrayForSize(2, 2));
		JumpingTestMazub = new Mazub(0, 40, 0, 3, 1, 3, false, spriteArrayForSize(2, 2));
		notDuckingTestMazub = new Mazub(0, 0, 0, 0, 1, 3, false, spriteArrayForSize(2, 2));
		duckingTestMazub = new Mazub(0, 0, 0, 0, 1, 3, true, spriteArrayForSize(2, 2));
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
		testMazub.setVerticalLocation(11);
		assertEquals(testMazub.getVerticalLocation(),11);
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
		assertFalse(Mazub.isValidHorizontalLocation(Double.NaN));
	}
	
	@Test
	public void testIsValidHorizontalLocation_FalseCase_MAX_VALUE() {
		assertFalse(Mazub.isValidHorizontalLocation(Integer.MAX_VALUE));
	}
	
	@Test
	public void testIsValidHorizontalLocation_FalseCase_MIN_VALUE() {
		assertFalse(Mazub.isValidHorizontalLocation(Integer.MIN_VALUE));
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
		assertFalse(Mazub.isValidVerticalLocation(Double.NaN));
	}
	
	@Test
	public void testIsValidVerticalLocation_FalseCase_MAX_VALUE() {
		assertFalse(Mazub.isValidVerticalLocation(Integer.MAX_VALUE));
	}
	
	@Test
	public void testIsValidVerticalLocation_FalseCase_MIN_VALUE() {
		assertFalse(Mazub.isValidVerticalLocation(Integer.MIN_VALUE));
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
		assertFalse(Mazub.isValidLocation(Double.NaN,Double.NaN));
	}
	
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
		assertFalse(staticTestMazub.canHaveAsMaximumHorizontalVelocity(1.99));
		assertFalse(staticTestMazub.canHaveAsMaximumHorizontalVelocity(0));
		assertFalse(staticTestMazub.canHaveAsMaximumHorizontalVelocity(-1.6));
		assertFalse(staticTestMazub.canHaveAsMaximumHorizontalVelocity(Double.NEGATIVE_INFINITY));
	}

	@Test
	public void testMatchesMaximumHorizontalVelocityInitialHorizontalVelocity_TrueCase(){
		assertTrue(Mazub.matchesMaximumHorizontalVelocityInitialHorizontalVelocity(1, 1));
		assertTrue(Mazub.matchesMaximumHorizontalVelocityInitialHorizontalVelocity(1.1, 1));
		assertTrue(Mazub.matchesMaximumHorizontalVelocityInitialHorizontalVelocity(19.3, 2));
		assertTrue(Mazub.matchesMaximumHorizontalVelocityInitialHorizontalVelocity(Double.POSITIVE_INFINITY
				, 2));
		assertTrue(Mazub.matchesMaximumHorizontalVelocityInitialHorizontalVelocity(Double.POSITIVE_INFINITY
				, Double.POSITIVE_INFINITY));
	}
	
	@Test
	public void testMatchesMaximumHorizontalVelocityInitialHorizontalVelocity_FalseCase(){
		assertFalse(Mazub.matchesMaximumHorizontalVelocityInitialHorizontalVelocity(0.99, 1));
		assertFalse(Mazub.matchesMaximumHorizontalVelocityInitialHorizontalVelocity(-1.4, 2));
		assertFalse(Mazub.matchesMaximumHorizontalVelocityInitialHorizontalVelocity(Double.NEGATIVE_INFINITY
				, 2));
	}
	
	@Test
	public void testIsPossibleInitialHorizontalVelocity_TrueCase(){
		assertTrue(Mazub.isPossibleInitialHorizontalVelocity(1));
		assertTrue(Mazub.isPossibleInitialHorizontalVelocity(1.1));
		assertTrue(Mazub.isPossibleInitialHorizontalVelocity(14));
	}
	

	@Test
	public void testIsPossibleInitialHorizontalVelocity_TrueCase_PositiveInfinity(){
		assertTrue(Mazub.isPossibleInitialHorizontalVelocity(Double.POSITIVE_INFINITY));
	}
	

	@Test
	public void testIsPossibleInitialHorizontalVelocity_FalseCase(){
		assertFalse(Mazub.isPossibleInitialHorizontalVelocity(0));
		assertFalse(Mazub.isPossibleInitialHorizontalVelocity(0.99));
		assertFalse(Mazub.isPossibleInitialHorizontalVelocity(-1));
	}
	
	@Test
	public void testIsPossibleInitialHorizontalVelocity_FalseCase_NegativeInfinity(){
		assertFalse(Mazub.isPossibleInitialHorizontalVelocity(Double.NEGATIVE_INFINITY));
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
		assertFalse(staticTestMazub.canHaveAsHorizontalVelocity(
				staticTestMazub.getMaximumHorizontalVelocity() + 0.1));
	}
	
	@Test
	public void testCanHaveAsHorizontalVelocity_FalseCase_NaN() {
		assertFalse(staticTestMazub.canHaveAsHorizontalVelocity(Double.NaN));
	}
	
	@Test
	public void testIsValidVerticalVelocity_TrueCase() {
		assertTrue(Mazub.isValidVerticalVelocity(Mazub.getInitialVerticalVelocity()-5));
	}

	@Test
	public void testIsValidVerticalVelocity_TrueCase_NegativeInfinity() {
		assertTrue(Mazub.isValidVerticalVelocity(Double.NEGATIVE_INFINITY));
	}
	
	@Test
	public void testIsValidVerticalVelocity_FalseCase() {
		assertFalse(Mazub.isValidVerticalVelocity(Mazub.getInitialVerticalVelocity() + 0.1));
	}
	
	@Test
	public void testIsValidVerticalVelocity_FalseCase_NaN() {
		assertFalse(Mazub.isValidVerticalVelocity(Double.NaN));
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
	public void testIsValidDirection_TrueCases() {
		assertTrue(Mazub.isValidDirection(1));
		assertTrue(Mazub.isValidDirection(-1));
	}

	@Test
	public void testIsValidDirection_FalseCases() {
		assertFalse(Mazub.isValidDirection(2));
		assertFalse(Mazub.isValidDirection(-3));
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
	
	@Test
	public void testStartMove() {
		testMazub.startMove(1);
		assertTrue(Util.fuzzyEquals(testMazub.getHorizontalVelocity(), 
				testMazub.getInitialHorizontalVelocity()));
		assertTrue(Util.fuzzyEquals(testMazub.getTimeSinceStartMove(),0));
		assertTrue(Util.fuzzyEquals(testMazub.getTimeSinceEndMove(),0));
		
	}
	
	@Test
	public void testEndMove() {
		testMazub.startMove(1);
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
		assertTrue(Util.fuzzyEquals(notJumpingTestMazub.getVerticalVelocity(),
				Mazub.getInitialVerticalVelocity()));
	}

	@Test
	public void testEndJump() {
		JumpingTestMazub.endJump();
		assertTrue(JumpingTestMazub.isJumping());
		assertTrue(Util.fuzzyEquals(notJumpingTestMazub.getVerticalVelocity(),0));
	}	

	@Test
	public void testStartDuck() {
		notDuckingTestMazub.startDuck();
		assertTrue(notDuckingTestMazub.isDucking());
		assertTrue(Util.fuzzyEquals(notDuckingTestMazub.getMaximumHorizontalVelocity(),1));
	}

	@Test
	public void testEndDuck() {
		duckingTestMazub.endDuck();
		assertFalse(duckingTestMazub.isDucking());
		assertTrue(Util.fuzzyEquals(duckingTestMazub.getMaximumHorizontalVelocity(),
				duckingTestMazub.getMaximumHorizontalVelocityNotDucking()));
	}
	
	@Test
	public void testAdvanceTime_NotMoving() {
		testMazub = new Mazub(0, 0, 0, 0, 1, 3, false, spriteArrayForSize(2, 2));
		testMazub.advanceTime(0.15);
		assertEquals(testMazub.getHorizontalLocation(),0);
		assertEquals(testMazub.getVerticalLocation(),0);
		assertTrue(Util.fuzzyEquals(testMazub.getHorizontalVelocity(),0));
		assertTrue(Util.fuzzyEquals(testMazub.getVerticalVelocity(),0));
		
	}

	@Test
	public void testAdvanceTime_Moving() {
		testMazub = new Mazub(0, 0, 1, 1, 1, 3, false, spriteArrayForSize(2, 2));
		testMazub.advanceTime(0.15);
		assertEquals(testMazub.getHorizontalLocation(),16);
		assertEquals(testMazub.getVerticalLocation(),3);
		assertTrue(Util.fuzzyEquals(testMazub.getHorizontalVelocity(),1.135));
		assertTrue(Util.fuzzyEquals(testMazub.getVerticalVelocity(),-0.5));		
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testAdvanceTime_Exception() throws Exception{
		testMazub = new Mazub(0, 0, 0, 0, 1, 3, false, spriteArrayForSize(2, 2));
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
	public void testIsValidNbImages_TrueCases(){
		assertTrue(Mazub.isValidNbImages(10)); //min
		assertTrue(Mazub.isValidNbImages(16));
	}
	
	@Test
	public void testIsValidNbImages_FalseCase_TooFew(){
		assertFalse(Mazub.isValidNbImages(6));
		assertFalse(Mazub.isValidNbImages(0));
	}
	
	@Test
	public void testIsValidNbImages_FalseCase_Negative(){
		assertFalse(Mazub.isValidNbImages(-8));
		assertFalse(Mazub.isValidNbImages(-10));
		assertFalse(Mazub.isValidNbImages(-11));
	}
	
	@Test
	public void testIsValidNbImages_FalseCase_Odd(){
		assertFalse(Mazub.isValidNbImages(7));
		assertFalse(Mazub.isValidNbImages(11));
		assertFalse(Mazub.isValidNbImages(25));
	}
	
	@Test
	public void testIsValidSpriteIndex_TrueCases(){
		assertTrue(staticTestMazub.isValidSpriteIndex(0)); //min
		assertTrue(staticTestMazub.isValidSpriteIndex(30)); //max
		assertTrue(staticTestMazub.isValidSpriteIndex(15));
	}
	
	@Test
	public void testIsValidSpriteIndex_FalseCases_NegativeIndex(){
		assertFalse(staticTestMazub.isValidSpriteIndex(-1));
		assertFalse(staticTestMazub.isValidSpriteIndex(-30));
		assertFalse(staticTestMazub.isValidSpriteIndex(-15));
	}
	
	@Test
	public void testIsValidSpriteIndex_FalseCases_GreaterThanMax(){
		assertFalse(staticTestMazub.isValidSpriteIndex(31));
		assertFalse(staticTestMazub.isValidSpriteIndex(32));
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

}
