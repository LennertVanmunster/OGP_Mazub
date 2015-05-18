package jumpingalien.part3.tests;

import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import jumpingalien.model.Buzam;
import jumpingalien.model.Orientation;
import jumpingalien.model.IllegalLocationException;
import jumpingalien.model.GameObject;
import jumpingalien.model.Mazub;
import jumpingalien.model.World;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.statements.Break;
import jumpingalien.programs.statements.StartJump;
import jumpingalien.programs.statements.Statement;
import jumpingalien.programs.statements.Wait;
import jumpingalien.programs.types.Type;
import jumpingalien.util.Sprite;
import jumpingalien.util.Util;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



public class GameObjectTest {

	private static GameObject staticTestGameObject;
	private static GameObject movingGameObject;
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
		staticTestGameObject = new Mazub(0, 499, spriteArrayForSize(3, 3));
		staticTestGameObject.setWorld(staticTestWorld);
		staticTestWorld.setMazub((Mazub) staticTestGameObject);
		movingGameObject = new Mazub(0, 0, 2, 0, 1, 3, false, spriteArrayForSize(2, 2));
	}

	private GameObject testGameObject;
	private GameObject testGameObject2;
	private GameObject notJumpingTestGameObject;
	private GameObject jumpingTestGameObject;
	private GameObject notDuckingTestGameObject;
	private World testWorld;
	
	@Before
	public void setUp() throws Exception {
		testWorld = new World(500, 3, 3, 1000, 1000, 2, 2);
		testWorld.setTileValueAtTilePosition( 0, 0, FEATURE_SOLID);
		testWorld.setTileValueAtTilePosition( 1, 1, FEATURE_SOLID);
		testGameObject = new Mazub(0, 499, spriteArrayForSize(3, 3));
		testGameObject.setWorld(testWorld);
		testWorld.setMazub((Mazub) testGameObject);	
		testGameObject2 = new Buzam(0, 499, spriteArrayForSize(3, 3));
		testGameObject2.setWorld(testWorld);
		testWorld.setBuzam((Buzam) testGameObject2);	
		jumpingTestGameObject = new Mazub(25, 500, 1, 3, 1, 3, false, spriteArrayForSize(2, 2));
		jumpingTestGameObject.setWorld(testWorld);
		testWorld.setMazub((Mazub) jumpingTestGameObject);
		notJumpingTestGameObject = new Mazub(50, 499, 0, 0, 1, 3, false, spriteArrayForSize(2, 2));
		notJumpingTestGameObject.setWorld(testWorld);
		testWorld.setMazub((Mazub) notJumpingTestGameObject);
		notDuckingTestGameObject = new Mazub(100, 499, 0, 0, 1, 3, false, spriteArrayForSize(2, 2));
		notDuckingTestGameObject.setWorld(testWorld);
		testWorld.setMazub((Mazub) notDuckingTestGameObject);
	}
	

	@Test
	public void testCanHaveAsHorizontalLocation_TrueCase() {
		assertTrue(staticTestGameObject.canHaveAsHorizontalLocation(1));
	}
	
	@Test
	public void testCanHaveAsHorizontalLocation_FalseCase_NegativeLocation() {
		assertFalse(staticTestGameObject.canHaveAsHorizontalLocation(-1));
	}

	@Test
	public void testCanHaveAsHorizontalLocation_FalseCase_UnpassableTerrain() {
		assertFalse(staticTestGameObject.canHaveAsHorizontalLocation(501));
	} 
	
	@Test
	public void testCanHaveAsHorizontalLocation_FalseCase_MAX_VALUE() {
		assertFalse(staticTestGameObject.canHaveAsHorizontalLocation(Integer.MAX_VALUE));
	}
	
	@Test
	public void testCanHaveAsHorizontalLocation_FalseCase_MIN_VALUE() {
		assertFalse(staticTestGameObject.canHaveAsHorizontalLocation(Integer.MIN_VALUE));
	}
	
	@Test
	public void testcanHaveAsVerticalLocation_TrueCase() {
		assertTrue(staticTestGameObject.canHaveAsVerticalLocation(499));
	}

	@Test
	public void testcanHaveAsVerticalLocation_FalseCase_NegativeLocation() {
		assertFalse(staticTestGameObject.canHaveAsVerticalLocation(-1));
	}
	
	@Test
	public void  testcanHaveAsVerticalLocation_FalseCase_UnpassableTerrain() {
		assertFalse(staticTestGameObject.canHaveAsVerticalLocation(498));
	}
	
	@Test
	public void testcanHaveAsVerticalLocation_FalseCase_MAX_VALUE() {
		assertFalse(staticTestGameObject.canHaveAsVerticalLocation(Integer.MAX_VALUE));
	}
	
	@Test
	public void testcanHaveAsVerticalLocation_FalseCase_MIN_VALUE() {
		assertFalse(staticTestGameObject.canHaveAsVerticalLocation(Integer.MIN_VALUE));
	}
	
	@Test
	public void testCanHaveAsLocation_TrueCase() {
		assertTrue(staticTestGameObject.canHaveAsLocation(300,700));
	}

	@Test
	public void testCanHaveAsLocation_FalseCase_NegativeLocation() {
		assertFalse(staticTestGameObject.canHaveAsLocation(-1,-1));
	}
	
	@Test
	public void testCanHaveAsLocation_FalseCase_PartiallyNegativeLocation() {
		assertFalse(staticTestGameObject.canHaveAsLocation(1,-1));
	}
	
	@Test
	public void testCanHaveAsLocation_FalseCase_UnpassableTerrain() {
		assertFalse(staticTestGameObject.canHaveAsLocation(350,400));
	}
	
	@Test
	public void testSetHorizontalLocation_LegalCase() {
		testGameObject.setHorizontalLocation(10);
		assertEquals(testGameObject.getEffectiveHorizontalLocation(),10);
		
	}

	@Test (expected = IllegalLocationException.class)
	public void testSetHorizontalLocation_IllegalCase() throws Exception{
		testGameObject.setHorizontalLocation(-10);
	}
	
	@Test
	public void testSetVerticalLocation_LegalCase() {
		testGameObject.setVerticalLocation(500);
		assertEquals(testGameObject.getEffectiveVerticalLocation(),500);
	}

	@Test (expected = IllegalLocationException.class)
	public void testSetVerticalLocation_IllegalCase() throws Exception{
		testGameObject.setVerticalLocation(-20);
	}
	
	@Test
	public void testSetHorizontalVelocity_TrueCase_PositiveValue() {
		testGameObject.setHorizontalVelocity(1);
		assertTrue(Util.fuzzyEquals(testGameObject.getHorizontalVelocity(),1));
	}
	
	@Test
	public void testSetHorizontalVelocity_TrueCase_NegativeValue() {
		testGameObject.setHorizontalVelocity(-1);
		assertTrue(Util.fuzzyEquals(testGameObject.getHorizontalVelocity(),-1));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetHorizontalVelocity_FalseCase() throws IllegalArgumentException{
		testGameObject.setHorizontalVelocity(4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSetHorizontalVelocity_FalseCase2() throws IllegalArgumentException{
		testGameObject.setHorizontalVelocity(0.5);
	}
	
	@Test
	public void testSetVerticalVelocity_TrueCase_PositiveValue_InitialVelocity() {
		testGameObject.setVerticalVelocity(1);
		assertTrue(Util.fuzzyEquals(testGameObject.getVerticalVelocity(),1));
	}
	
	@Test
	public void testSetVerticalVelocity_TrueCase_NegativeValue() {
		testGameObject.setVerticalVelocity(-5);
		assertTrue(Util.fuzzyEquals(testGameObject.getVerticalVelocity(),-5));
	}

	@Test (expected = IllegalArgumentException.class)
	public void testSetVerticalVelocity_FalseCase() throws IllegalArgumentException {
		//8 is the initial vertical velocity
		testGameObject.setVerticalVelocity(8+1);
	}
	
	@Test
	public void testCanHaveAsVerticalVelocity_TrueCase() {
		//8 is the initial vertical velocity
		assertTrue(staticTestGameObject.canHaveAsVerticalVelocity(8-5));
	}

	@Test
	public void testCanHaveAsVerticalVelocity_TrueCase_NegativeInfinity() {
		assertTrue(staticTestGameObject.canHaveAsVerticalVelocity(Double.NEGATIVE_INFINITY));
	}
	
	@Test
	public void testCanHaveAsVerticalVelocity_FalseCase() {
		//8 is the initial vertical velocity
		assertFalse(staticTestGameObject.canHaveAsVerticalVelocity(8 + 0.1));
	}
	
	@Test
	public void testCanHaveAsInitialHorizontalVelocity_TrueCases() {
		assertTrue(staticTestGameObject.canHaveAsInitialHorizontalVelocity(1));
		assertTrue(staticTestGameObject.canHaveAsInitialHorizontalVelocity(1.5));
		assertTrue(staticTestGameObject.canHaveAsInitialHorizontalVelocity(2.99));
	}

	@Test
	public void testCanHaveAsInitialHorizontalVelocity_FalseCases() {
		assertFalse(staticTestGameObject.canHaveAsInitialHorizontalVelocity(0.99));
		assertFalse(staticTestGameObject.canHaveAsInitialHorizontalVelocity(-1.5));
		assertFalse(staticTestGameObject.canHaveAsInitialHorizontalVelocity(3.01));
	}
	
	@Test
	public void testCanHaveAsMaximumHorizontalVelocity_TrueCases() {
		assertTrue(staticTestGameObject.canHaveAsMaximumHorizontalVelocity(2));
		assertTrue(staticTestGameObject.canHaveAsMaximumHorizontalVelocity(2.1));
		assertTrue(staticTestGameObject.canHaveAsMaximumHorizontalVelocity(5));
		assertTrue(staticTestGameObject.canHaveAsMaximumHorizontalVelocity(Double.POSITIVE_INFINITY));
	}
	
	@Test
	public void testCanHaveAsMaximumHorizontalVelocity_FalseCases() {
		assertFalse(staticTestGameObject.canHaveAsMaximumHorizontalVelocity(0));
		assertFalse(staticTestGameObject.canHaveAsMaximumHorizontalVelocity(-1.6));
		assertFalse(staticTestGameObject.canHaveAsMaximumHorizontalVelocity(Double.NEGATIVE_INFINITY));
	}

	@Test
	public void getHorizontalAccelerationForUpdate_TrueCase(){
		testGameObject.setHorizontalVelocity(3);
		assertTrue(Util.fuzzyEquals(staticTestGameObject.getHorizontalAccelerationForUpdate(), 0));
		assertTrue(Util.fuzzyEquals(testGameObject.getHorizontalAccelerationForUpdate(), 0));
		assertTrue(Util.fuzzyEquals(movingGameObject.getHorizontalAccelerationForUpdate(), 0.9));
	}
	
	@Test
	public void setHorizontalAcceleration_TrueCase(){
		testGameObject.setHorizontalAcceleration(3);
		assertTrue(Util.fuzzyEquals(testGameObject.getHorizontalAcceleration(), 3));
	}

	@Test
	public void setHorizontalAcceleration_FalseCase(){
		testGameObject.setHorizontalAcceleration(0);
		assertFalse(Util.fuzzyEquals(testGameObject.getHorizontalAcceleration(), 0));
	}

	/**
	 * Test for a nominal method!
	 */
	@Test
	public void testSetDirection_TrueCase() {
		testGameObject.setDirection(Orientation.LEFT);
		assertEquals(testGameObject.getDirection(),Orientation.LEFT);
	}
	
	/**
	 * Test for a nominal method!
	 */
	@Test
	public void testSetDirection_FalseCase() {
		testGameObject.setDirection(Orientation.RIGHT);
		assertNotEquals(testGameObject.getDirection(),Orientation.LEFT);
	}
	
	@Test
	public void testIsValidDirection_TrueCases() {
		assertTrue(Mazub.isValidDirection(Orientation.RIGHT));
		assertTrue(Mazub.isValidDirection(Orientation.LEFT));
	}
	
	@Test
	public void testIsDucking_TrueCase() {
		testGameObject.setDucking(true);
		assertTrue(testGameObject.isDucking());
	}

	@Test
	public void testIsDucking_FalseCase() {
		assertFalse(staticTestGameObject.isDucking());
	}
	
	@Test
	public void testSetDucking() {
		testGameObject.setDucking(true);
		assertTrue(testGameObject.isDucking());
	}
	
	@Test
	public void testIsJumping1() {
		jumpingTestGameObject.advanceTime(0.19);
		assertTrue(jumpingTestGameObject.isJumping());
	}

	@Test
	public void testIsJumping() {
		testGameObject.advanceTime(0.19);
		assertFalse(testGameObject.isJumping());
	}

	@Test
	public void setJumping_TrueCase(){
		testGameObject.setJumping(true);
		assertTrue(testGameObject.isJumping());
		testGameObject.setJumping(false);
		assertFalse(testGameObject.isJumping());
	}
	
	@Test
	public void testIsMovingHorizontally_TrueCase(){
		assertTrue(movingGameObject.isMovingHorizontally());
		
	}
	
	@Test
	public void testIsMovingHorizontally_FalseCase(){
		assertFalse(staticTestGameObject.isMovingHorizontally());
	}
	
	@Test
	public void testIsValidDeltaTime_TrueCase() {
		assertTrue(GameObject.isValidDeltaTime(0.1));
	}

	@Test
	public void testIsValidDeltaTime_TrueCase_0() {
		assertTrue(GameObject.isValidDeltaTime(0));
	}
	
	@Test
	public void testIsValidDeltaTime_FalseCase_Negative() {
		assertFalse(GameObject.isValidDeltaTime(-0.15));
	}
	
	@Test
	public void testIsValidDeltaTime_FalseCase_GreaterThanMax() {
		assertFalse(GameObject.isValidDeltaTime(0.21));
	}
	
	@Test
	public void testIsValidDeltaTime_FalseCase_NaN() {
		assertFalse(GameObject.isValidDeltaTime(Double.NaN));
	}
	
	@Test
	public void testIsValidDeltaTime_FalseCase_Infinity() {
		assertFalse(GameObject.isValidDeltaTime(Double.NEGATIVE_INFINITY));
		assertFalse(GameObject.isValidDeltaTime(Double.POSITIVE_INFINITY));
	}
	
	@Test
	public void testSetContact_TrueCase(){
		testGameObject.setContact(true);
		assertTrue(testGameObject.getContact());
		testGameObject.setContact(false);
		assertFalse(testGameObject.getContact());
	}
	
	@Test
	public void testSetTimeSinceStartWaterContact_TrueCase(){
		testGameObject.setTimeSinceStartWaterContact(0);
		assertTrue(Util.fuzzyEquals(testGameObject.getTimeSinceStartWaterContact(),0));
		testGameObject.setTimeSinceStartWaterContact(0.3);
		assertTrue(Util.fuzzyEquals(testGameObject.getTimeSinceStartWaterContact(),0.3));
		testGameObject.setTimeSinceStartWaterContact(23);
		assertTrue(Util.fuzzyEquals(testGameObject.getTimeSinceStartWaterContact(),23));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetTimeSinceStartWaterContact_FalseCase_NegativeNumber() throws Exception{
		testGameObject.setTimeSinceStartWaterContact(-2.1);
	}
	
	@Test
	public void testSetTimeSinceStartMagmaContact_TrueCase(){
		testGameObject.setTimeSinceStartMagmaContact(0);
		assertTrue(Util.fuzzyEquals(testGameObject.getTimeSinceStartMagmaContact(),0));
		testGameObject.setTimeSinceStartMagmaContact(0.2);
		assertTrue(Util.fuzzyEquals(testGameObject.getTimeSinceStartMagmaContact(),0.2));
		testGameObject.setTimeSinceStartMagmaContact(13);
		assertTrue(Util.fuzzyEquals(testGameObject.getTimeSinceStartMagmaContact(),13));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetTimeSinceStartMagmaContact_FalseCase_NegativeNumber() throws Exception{
		testGameObject.setTimeSinceStartMagmaContact(-0.1);
	}
	
	@Test
	public void testSetTimeSinceStartAction_TrueCase(){
		testGameObject.setTimeSinceStartAction(0);
		assertTrue(Util.fuzzyEquals(testGameObject.getTimeSinceStartAction(),0));
		testGameObject.setTimeSinceStartAction(0.5);
		assertTrue(Util.fuzzyEquals(testGameObject.getTimeSinceStartAction(),0.5));
		testGameObject.setTimeSinceStartAction(16);
		assertTrue(Util.fuzzyEquals(testGameObject.getTimeSinceStartAction(),16));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetTimeSinceStartAction_FalseCase_NegativeNumber() throws Exception{
		testGameObject.setTimeSinceStartAction(-0.3);
	}
	
	@Test
	public void testIsValidTimeSinceStartAction_TrueCase(){
		assertTrue(GameObject.isValidTimeSinceAction(0));
		assertTrue(GameObject.isValidTimeSinceAction(1.1));
		assertTrue(GameObject.isValidTimeSinceAction(30));
	}
	
	@Test
	public void testIsValidTimeSinceStartAction_FalseCase(){
		assertFalse(GameObject.isValidTimeSinceAction(-0.1));
		assertFalse(GameObject.isValidTimeSinceAction(-1.1));
		assertFalse(GameObject.isValidTimeSinceAction(-30));
	}
	
	@Test
	public void testCanHaveAsSpriteIndex_TrueCases(){
		assertTrue(staticTestGameObject.canHaveAsSpriteIndex(0)); //min
		assertTrue(staticTestGameObject.canHaveAsSpriteIndex(30)); //max
		assertTrue(staticTestGameObject.canHaveAsSpriteIndex(15));
	}
	
	@Test
	public void testCanHaveAsSpriteIndex_FalseCases_NegativeIndex(){
		assertFalse(staticTestGameObject.canHaveAsSpriteIndex(-1));
		assertFalse(staticTestGameObject.canHaveAsSpriteIndex(-30));
		assertFalse(staticTestGameObject.canHaveAsSpriteIndex(-15));
	}
	
	@Test
	public void testCanHaveAsSpriteIndex_FalseCases_GreaterThanMax(){
		assertFalse(staticTestGameObject.canHaveAsSpriteIndex(31));
		assertFalse(staticTestGameObject.canHaveAsSpriteIndex(32));
	}
	
	@Test
	public void testIsValidImage_TrueCase(){
		assertTrue(GameObject.isValidImage(staticTestGameObject.getCurrentSprite()));
	}
	
	@Test
	public void testIsValidImage_FalseCase(){
		assertFalse(GameObject.isValidImage(null));
	}
	
	@Test
	public void testSetImages() {
		Sprite images [] = spriteArrayForSize(5, 10,16);
		testGameObject.setImages(images);
		assertArrayEquals(testGameObject.getImages(),images);
		assertEquals(testGameObject.getNbImages(),16);
		assertEquals(testGameObject.getWidth(),5);
		assertEquals(testGameObject.getHeight(),10);
	}
	
	@Test
	public void testIsValidMaxHitPoints_TrueCase() {
		assertTrue(GameObject.isValidMaxHitPoints(0));
		assertTrue(GameObject.isValidMaxHitPoints(10));
		assertTrue(GameObject.isValidMaxHitPoints(500));
		assertTrue(GameObject.isValidMaxHitPoints(1000));
	}
	
	@Test
	public void testIsValidMaxHitPoints_FalseCase() {
		assertFalse(GameObject.isValidMaxHitPoints(-10));
		assertFalse(GameObject.isValidMaxHitPoints(-500));
		assertFalse(GameObject.isValidMaxHitPoints(-1000));
	}

	@Test
	public void testCanHaveAsHitPoints_TrueCase() {
		assertTrue(staticTestGameObject.canHaveAsHitPoints(0));
		assertTrue(staticTestGameObject.canHaveAsHitPoints(10));
		assertTrue(staticTestGameObject.canHaveAsHitPoints(500));
	}

	@Test
	public void testCanHaveAsHitPoints_FalseCase() {
		assertFalse(staticTestGameObject.canHaveAsHitPoints(-2));
		assertFalse(staticTestGameObject.canHaveAsHitPoints(-50));
		assertFalse(staticTestGameObject.canHaveAsHitPoints(501));
	}

	@Test
	public void testRemoveHitPoints_TrueCase(){
		testGameObject.setHitPoints(250);
		testGameObject.removeHitPoints(50);
		assertEquals(testGameObject.getHitPoints(), 200);
		testGameObject.removeHitPoints(0);
		assertEquals(testGameObject.getHitPoints(), 200);
		testGameObject.removeHitPoints(300);
		assertEquals(testGameObject.getHitPoints(), 0);
	}
	
	@Test
	public void testRemoveHitPoints_FalseCase(){
		testGameObject.setHitPoints(250);
		testGameObject.removeHitPoints(-50);
		assertEquals(testGameObject.getHitPoints(), 250);
	}
	
	@Test
	public void testAddHitPoints_TrueCase(){
		testGameObject.setHitPoints(250);
		testGameObject.addHitPoints(50);
		assertEquals(testGameObject.getHitPoints(), 300);
		testGameObject.addHitPoints(0);
		assertEquals(testGameObject.getHitPoints(), 300);
		testGameObject.addHitPoints(300);
		assertEquals(testGameObject.getHitPoints(), 500);
	}
	
	@Test
	public void testAddHitPoints_FalseCase(){
		testGameObject.setHitPoints(250);
		testGameObject.addHitPoints(-50);
		assertEquals(testGameObject.getHitPoints(), 250);
	}
	
	@Test
	public void testSetHitPoints_TrueCase(){
		testGameObject.setHitPoints(250);
		assertEquals(testGameObject.getHitPoints(), 250);
		testGameObject.setHitPoints(0);
		assertEquals(testGameObject.getHitPoints(), 0);
		testGameObject.setHitPoints(500);
		assertEquals(testGameObject.getHitPoints(), 500);
	}
	
	@Test
	public void testSetHitPoints_FalseCase(){
		testGameObject.setHitPoints(-250);
		assertEquals(testGameObject.getHitPoints(), 0);
		testGameObject.setHitPoints(510);
		assertEquals(testGameObject.getHitPoints(), 500);
	}

	@Test
	public void testSetWorld_TrueCase(){
		testGameObject.setWorld(staticTestWorld);
		assertEquals(testGameObject.getWorld(), staticTestWorld);
	}
	
	@Test
	public void testSetWorld_nullCase(){
		testGameObject.setWorld(null);
	}
	
	@Test
	public void testHasProperWorld_TrueCase(){
		assertTrue(testGameObject.hasProperWorld());
	}
	
	@Test
	public void testHasProperWorld_FalseCase(){
		testGameObject.setWorld(staticTestWorld);
		assertFalse(testGameObject.hasProperWorld());
	}
	
	@Test
	public void testBlock_TrueCase(){
		jumpingTestGameObject.setHitPoints(0);
		jumpingTestGameObject.block();
		assertTrue(Util.fuzzyEquals(jumpingTestGameObject.getHorizontalVelocity(), 0));
		assertTrue(Util.fuzzyEquals(jumpingTestGameObject.getVerticalVelocity(), 0));
	}
	
	@Test (expected = IllegalStateException.class)
	public void testBlock_FalseCase() throws Exception {
		jumpingTestGameObject.setHitPoints(100);
		jumpingTestGameObject.block();
	}
	
	@Test
	public void testSetTimeSinceDead_TrueCase(){
		testGameObject.setTimeSinceDead(0.);
		assertTrue(Util.fuzzyEquals(testGameObject.getTimeSinceDead(),0));
		testGameObject.setTimeSinceDead(4.5);
		assertTrue(Util.fuzzyEquals(testGameObject.getTimeSinceDead(),4.5));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testSetTimeSinceDead_FalseCase() throws Exception{
		testGameObject.setTimeSinceDead(-0.1);
	}

	@Test
	public void testIsTerminated_TrueCase(){
		testGameObject.terminate();
		assertTrue(testGameObject.isTerminated());
	}
	
	@Test
	public void testIsTerminated_FalseCase(){
		assertFalse(testGameObject.isTerminated());
	}
	
	@Test
	public void testTerminate_TrueCase(){
		testGameObject.terminate();
		assertTrue(testGameObject.isTerminated());
	}
	
	//New methods made for part 3:
	
	@Test
	public void testgetGameObjectAtPixelPosition_TrueCase(){
		testGameObject.setHorizontalLocation(100);
		testGameObject.setVerticalLocation(499);
		Object object = testGameObject.getGameObjectAtPixelPosition(100, 499);
		assertTrue( object != null && object instanceof Mazub );
		object = testGameObject.getGameObjectAtPixelPosition(101, 500);
		assertTrue( object != null && object instanceof Mazub );
		object = testGameObject.getGameObjectAtPixelPosition(99, 502);
		assertTrue( object == null );
	}

	@Test
	public void testSetProgram_TrueCase(){
		Map<String,Type> globalVariables = new HashMap<String, Type>();
		Statement statement =  new StartJump(null);
		Program program = new Program(statement, globalVariables);
		assertTrue(testGameObject2.getProgram() == null);
		testGameObject2.setProgram(program);
		assertTrue(testGameObject2.getProgram() != null);
	}
	
	
}
