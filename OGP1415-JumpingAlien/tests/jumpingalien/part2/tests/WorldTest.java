package jumpingalien.part2.tests;

import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.*;
import jumpingalien.model.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class WorldTest {
	
	private static School school1;
	private static School terminatedSchool;
	private static Slime jumpingSlime;
	private static World world1;
	private static World largeWorld;
	private static World startedWorld;
	private static World terminatedWorld;
	private static Mazub originMazub;
	private static Mazub topRightCornerMazub;
	
	
	@BeforeClass
	public static void setUpBeforeClass(){
		school1=new School();
		jumpingSlime=new Slime(0,10,spriteArrayForSize(2, 2),school1);
		jumpingSlime.setJumping(true);
		terminatedSchool=new School();
		terminatedSchool.terminate();
		world1= new World(50,10,10,500,500,9,9);
		largeWorld=new World(100,20,20,500,500,9,9);
		startedWorld=new World(50,10,10,500,500,9,9);
		startedWorld.setGameHasStarted(true);
		terminatedWorld= new World(50,10,10,500,500,9,9);
		terminatedWorld.terminate();
		originMazub= new Mazub(0,0,spriteArrayForSize(10, 10));
		topRightCornerMazub= new Mazub(490,490,spriteArrayForSize(10, 10));
		
	}
	
	private static World testWorld;
	private static School testSchool1;
	private static Slime slime1TestSchool1;
	private static School testSchool2;
	private static Slime slime1TestSchool2;
	private static World largeTestWorld;
	private static Mazub testMazub;
	private static World world9Schools;
	@Before
	public void setUp(){
		testWorld=new World(50,10,10,500,500,9,9);
		testSchool1=new School();
		slime1TestSchool1= new Slime(40,0,spriteArrayForSize(2,2),testSchool1);
		testSchool2=new School();
		slime1TestSchool2= new Slime(70,0,spriteArrayForSize(2,2),testSchool2);
		testMazub= new Mazub(0,0,spriteArrayForSize(10, 10));
		largeTestWorld=new World(100,20,20,500,500,9,9);
		world9Schools= new World(50,10,10,500,500,9,9);
		for(int i=0;i<9;i++){
			School newSchool= new School();
			Slime newSlime= new Slime(i*3,0, spriteArrayForSize(2, 2), newSchool);
			world9Schools.addAsGameObject(newSlime);
		}
	}
	
	@Test
	public void getNbTiles() {
		assertEquals(world1.getNbTiles(),100);
	}
	
	@Test
	public void isPossibleTileSize_trueCase(){
		assertTrue(World.isPossibleTileSize(10));
	}
	
	@Test
	public void isPossibleTileSize_falseCase(){
		assertFalse(World.isPossibleTileSize(-10));
		assertFalse(World.isPossibleTileSize(0));
	}
	
	@Test
	public void isPossibleNbTiles_trueCase(){
		assertTrue(World.isPossibleNbTiles(10));
	}
	
	@Test
	public void isPossibleNbTiles_falseCase(){
		assertFalse(World.isPossibleNbTiles(-10));
		assertFalse(World.isPossibleNbTiles(0));
	}
	
	@Test
	public void isPossibleVisibleWindowDimension_trueCase(){
		assertTrue(World.isPossibleVisibleWindowDimension(10));
	}
	
	@Test
	public void isPossibleVisibleWindowDimension_falseCase(){
		assertFalse(World.isPossibleVisibleWindowDimension(-10));
		assertFalse(World.isPossibleVisibleWindowDimension(0));
	}
	
	@Test
	public void isPossibleWindowLocation_trueCase(){
		assertTrue(World.isPossibleWindowLocation(10));
		assertTrue(World.isPossibleWindowLocation(0));
	}
	
	@Test
	public void isPossibleWindowLocation_falseCase(){
		assertFalse(World.isPossibleWindowLocation(-10));
	}
	
	@Test
	public void matchesTileSizeNbTilesWindowDimensionWindowLocation_trueCase(){
		assertTrue(World.matchesTileSizeNbTilesWindowDimensionWindowLocation(10,10,50,0));
	}
	
	@Test
	public void matchesTileSizeNbTilesWindowDimensionWindowLocation_falseCase(){
		assertFalse(World.matchesTileSizeNbTilesWindowDimensionWindowLocation(10, 10, 200, 0));
		assertFalse(World.matchesTileSizeNbTilesWindowDimensionWindowLocation(10, 10, 50, 200));
	}
	
	@Test
	public void canHaveAsTileSize_trueCase(){
		assertTrue(world1.canHaveAsTileSize(50));
	}
	
	public void canHaveAsTileSize_falseCase(){
		assertFalse(world1.canHaveAsTileSize(49));
		assertFalse(world1.canHaveAsTileSize(51));
		assertFalse(world1.canHaveAsTileSize(-10));
	}
	
	@Test
	public void canHaveAsNbTilesX_trueCase(){
		assertTrue(world1.canHaveAsNbTilesX(10));
		assertTrue(world1.canHaveAsNbTilesX(20));
		
	}
	
	@Test
	public void canHaveAsNbTilesX_falseCase(){
		assertFalse(world1.canHaveAsNbTilesX(9));
		assertFalse(world1.canHaveAsNbTilesX(-10));
	}
	
	@Test
	public void canHaveAsNbTilesY_trueCase(){
		assertTrue(world1.canHaveAsNbTilesY(10));
		assertTrue(world1.canHaveAsNbTilesY(20));
		
	}
	
	@Test
	public void canHaveAsNbTilesY_falseCase(){
		assertFalse(world1.canHaveAsNbTilesY(9));
		assertFalse(world1.canHaveAsNbTilesY(-10));
	}
	
	@Test
	public void canHaveAsVisibleWindowWidth_trueCase(){
		assertTrue(world1.canHaveAsVisibleWindowWidth(500));
		assertTrue(world1.canHaveAsVisibleWindowWidth(20));
		
	}
	
	@Test
	public void canHaveAsVisibleWindowWidth_falseCase(){
		assertFalse(world1.canHaveAsVisibleWindowWidth(600));
		assertFalse(world1.canHaveAsVisibleWindowWidth(-10));
	}
	
	@Test
	public void canHaveAsVisibleWindowHeight_trueCase(){
		assertTrue(world1.canHaveAsVisibleWindowHeight(500));
		assertTrue(world1.canHaveAsVisibleWindowHeight(20));
		
	}
	
	@Test
	public void canHaveAsVisibleWindowHeight_falseCase(){
		assertFalse(world1.canHaveAsVisibleWindowHeight(600));
		assertFalse(world1.canHaveAsVisibleWindowHeight(-10));
	}
	
	@Test
	public void canHaveAsHorizontalVisibleWindowLocation_trueCase(){
		assertTrue(world1.canHaveAsHorizontalVisibleWindowLocation(0));
		assertTrue(largeTestWorld.canHaveAsHorizontalVisibleWindowLocation(200));
		
	}
	
	@Test
	public void canHaveAsHorizontalVisibleWindowLocation_falseCase(){
		assertFalse(world1.canHaveAsHorizontalVisibleWindowLocation(1));
		assertFalse(world1.canHaveAsHorizontalVisibleWindowLocation(-10));
	}
	
	@Test
	public void canHaveAsVerticalVisibleWindowLocation_trueCase(){
		assertTrue(world1.canHaveAsVerticalVisibleWindowLocation(0));
		assertTrue(largeTestWorld.canHaveAsVerticalVisibleWindowLocation(200));
		
	}
	
	@Test
	public void canHaveAsVerticalVisibleWindowLocation_falseCase(){
		assertFalse(world1.canHaveAsVerticalVisibleWindowLocation(1));
		assertFalse(world1.canHaveAsVerticalVisibleWindowLocation(-10));
	}
	
	@Test
	public void matchesVisibleWindowMazub_trueCase(){
		assertTrue(world1.matchesMazubVisibleWindow(originMazub, 0, 0, 500, 500));
		assertTrue(world1.matchesMazubVisibleWindow(topRightCornerMazub, 0, 0, 500, 500));
		assertTrue(world1.matchesMazubVisibleWindow(null, 0, 0, 500, 500));
		largeTestWorld.setVisibleWindowLocation(500,500);
		testMazub.setHorizontalLocation(700);
		testMazub.setVerticalLocation(700);
		assertTrue(largeWorld.matchesMazubVisibleWindow(testMazub, 500, 500, 500, 500));
	}
	
	@Test 
	public void matchesVisibleWindowMazub_falseCase(){
		assertFalse(world1.matchesMazubVisibleWindow(originMazub, 0, 0, 5, 5));
		assertFalse(world1.matchesMazubVisibleWindow(topRightCornerMazub, 0, 0, 100, 100));
		largeTestWorld.setVisibleWindowLocation(500,500);
		testMazub.setHorizontalLocation(699);
		testMazub.setVerticalLocation(699);
		assertFalse(largeWorld.matchesMazubVisibleWindow(testMazub, 500, 500, 500, 500));
		testMazub.setHorizontalLocation(791);
		testMazub.setVerticalLocation(791);
		assertFalse(largeWorld.matchesMazubVisibleWindow(testMazub, 500, 500, 500, 500));
	}
	
	@Test
	public void setTileValueAtTilePosition_legalCase(){
		testWorld.setTileValueAtTilePosition(0, 0, 0);
		assertEquals(testWorld.getTileValueAtTilePosition(0, 0),0);
		testWorld.setTileValueAtTilePosition(0, 0, 3);
		assertEquals(testWorld.getTileValueAtTilePosition(0, 0),3);
		testWorld.setTileValueAtTilePosition(9, 9, 0);
		assertEquals(testWorld.getTileValueAtTilePosition(9, 9),0);
		testWorld.setTileValueAtTilePosition(9, 9, 3);
		assertEquals(testWorld.getTileValueAtTilePosition(9, 9),3);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void setTileValueAtTilePosition_illegalTileValue(){
		testWorld.setTileValueAtTilePosition(0, 0, 4);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void setTileValueAtTilePosition_illegalTilePosition(){
		testWorld.setTileValueAtTilePosition(-1,-1,3);
	}
	
	@Test
	public void canHaveAsTilePosition_trueCase(){
		assertTrue(world1.canHaveAsTilePosition(0, 0));
		assertTrue(world1.canHaveAsTilePosition(9, 9));
	}
	
	@Test
	public void canHaveAsTilePosition_falseCase(){
		assertFalse(world1.canHaveAsTilePosition(-1, 0));
		assertFalse(world1.canHaveAsTilePosition(10, 9));
	}

	@Test
	public void canHaveAsPixelLocation_trueCase(){
		assertTrue(world1.canHaveAsPixelLocation(0, 0));
		assertTrue(world1.canHaveAsPixelLocation(499, 499));
	}
	
	@Test
	public void canHaveAsPixelLocation_falseCase(){
		assertFalse(world1.canHaveAsPixelLocation(-1, 0));
		assertFalse(world1.canHaveAsPixelLocation(500, 500));
	}
	
	@Test
	public void isValidTileValue_trueCase(){
		assertTrue(World.isValidTileValue(0));
		assertTrue(World.isValidTileValue(3));
	}
	
	@Test
	public void isValidTileValue_falseCase(){
		assertFalse(World.isValidTileValue(-1));
		assertFalse(World.isValidTileValue(4));
	}
	
	@Test
	public void getWorldSizeInPixels(){
		int[] worldSize={500,500};
		assertEquals(world1.getWorldSizeInPixels()[0],worldSize[0]);
		assertEquals(world1.getWorldSizeInPixels()[1],worldSize[1]);
	}
	
	@Test
	public void getBottomLeftPixelOfTile_legalTile(){
		assertEquals(world1.getBottomLeftPixelOfTile(0, 0)[0],0);
		assertEquals(world1.getBottomLeftPixelOfTile(0, 0)[1],0);
		assertEquals(world1.getBottomLeftPixelOfTile(9, 9)[0],450);
		assertEquals(world1.getBottomLeftPixelOfTile(9, 9)[1],450);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void getBottomLeftPixelOfTile_illegalTile(){
		world1.getBottomLeftPixelOfTile(-1, 0);
	}
	
	@Test
	public void getTilePositionAtPixelLocation_legalPixelLocation(){
		assertEquals(world1.getTilePositionAtPixelLocation(0, 0)[0],0);
		assertEquals(world1.getTilePositionAtPixelLocation(0, 0)[1],0);
		assertEquals(world1.getTilePositionAtPixelLocation(10, 10)[0],0);
		assertEquals(world1.getTilePositionAtPixelLocation(10, 10)[1],0);
		assertEquals(world1.getTilePositionAtPixelLocation(499, 499)[0],9);
		assertEquals(world1.getTilePositionAtPixelLocation(499, 499)[1],9);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void getTilePositionAtPixelLocation_illegalPixelLocation(){
		world1.getTilePositionAtPixelLocation(-1, 0);
	}
	
	@Test
	public void getTilePositionsIn_legalPixelLocation(){
		assertEquals(world1.getTilePositionsIn(0,0,0,0)[0][0],0);
		assertEquals(world1.getTilePositionsIn(0,0,1,1)[0][0],0);
		assertEquals(world1.getTilePositionsIn(0,0,49,49)[0][0],0);
		assertEquals(world1.getTilePositionsIn(0,0,50,50)[0][0],0);
		assertEquals(world1.getTilePositionsIn(0,0,50,50)[1][0],1);
		assertEquals(world1.getTilePositionsIn(0,0,50,50)[2][0],0);
		assertEquals(world1.getTilePositionsIn(0,0,50,50)[3][0],1);
		assertEquals(world1.getTilePositionsIn(0,0,0,0)[0][1],0);
		assertEquals(world1.getTilePositionsIn(0,0,1,1)[0][1],0);
		assertEquals(world1.getTilePositionsIn(0,0,49,49)[0][1],0);
		assertEquals(world1.getTilePositionsIn(0,0,50,50)[0][1],0);
		assertEquals(world1.getTilePositionsIn(0,0,50,50)[1][1],0);
		assertEquals(world1.getTilePositionsIn(0,0,50,50)[2][1],1);
		assertEquals(world1.getTilePositionsIn(0,0,50,50)[3][1],1);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void getTilePositionsIn_illegalPixelLocation(){
		world1.getTilePositionsIn(0,0,-1,0);
	}
	
	@Test
	public void getTileValueAtPixelLocation_legalPixelLocation(){
		assertEquals(world1.getTileValueAtPixelLocation(0, 0),0);
		assertEquals(world1.getTileValueAtPixelLocation(10, 0),0);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void getTileValueAtPixelLocation_illegalPixelLocation(){
		world1.getTileValueAtPixelLocation(-1, 0);
	}
	
	@Test
	public void areaCoincidesWithTerrain_legalArguments(){
		testWorld.setTileValueAtTilePosition(1, 1, 1);
		testWorld.setTileValueAtTilePosition(2, 2, 2);
		testWorld.setTileValueAtTilePosition(3, 3, 3);
		assertTrue(testWorld.areaCoincidesWithTerrain(0, 0, 0, 0)[0]);
		assertTrue(testWorld.areaCoincidesWithTerrain(50, 50, 0, 0)[1]);
		assertTrue(testWorld.areaCoincidesWithTerrain(100, 100, 0, 0)[2]);
		assertTrue(testWorld.areaCoincidesWithTerrain(150, 150, 0, 0)[3]);
		assertTrue(testWorld.areaCoincidesWithTerrain(0, 0, 199, 199)[0]);
		assertTrue(testWorld.areaCoincidesWithTerrain(0, 0, 199, 199)[1]);
		assertTrue(testWorld.areaCoincidesWithTerrain(0, 0, 199, 199)[2]);
		assertTrue(testWorld.areaCoincidesWithTerrain(0, 0, 199, 199)[3]);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void areaCoincidesWithTerrain_illegalLocation(){
		world1.areaCoincidesWithTerrain(-1, 0, 0, 0);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void areaCoincidesWithTerrain_illegalDimension(){
		world1.areaCoincidesWithTerrain(0, 0, 600, 0);
	}
	
	@Test
	public void setDidPlayerWin_legalCase(){
		testWorld.setGameOver(true);
		testWorld.setDidPlayerWin(true);
		assertTrue(testWorld.getDidPlayerWin());
	}
	
	@Test
	public void setDidPlayerWin_illegalCase(){
		testWorld.setDidPlayerWin(true);
		assertFalse(testWorld.getDidPlayerWin());
	}
	
	@Test
	public void canHaveAsMazub_notStartedGame(){
		assertTrue(world1.canHaveAsMazub(null));
		assertTrue(world1.canHaveAsMazub(originMazub));
		assertTrue(world1.canHaveAsMazub(topRightCornerMazub));
		testMazub.setHorizontalLocation(600);
		assertFalse(world1.canHaveAsMazub(testMazub));
	}
	
	@Test
	public void canHaveAsMazub_startedGame(){
		assertFalse(startedWorld.canHaveAsMazub(null));
		assertTrue(startedWorld.canHaveAsMazub(originMazub));
		assertTrue(startedWorld.canHaveAsMazub(topRightCornerMazub));
		testMazub.setHorizontalLocation(600);
		assertFalse(startedWorld.canHaveAsMazub(testMazub));
		assertFalse(terminatedWorld.canHaveAsMazub(originMazub));
	}
	
	@Test
	public void setMazub_legalCase(){
		testWorld.setMazub(testMazub);
		assertEquals(testWorld.getMazub(),testMazub);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void setMazub_startedGame(){
		startedWorld.setMazub(testMazub);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void setMazub_notValidMazub(){
		testMazub.setHorizontalLocation(600);
		testWorld.setMazub(testMazub);
	}
	
	@Test
	public void isValidNbGameObjects_trueCase(){
		assertTrue(World.isValidNbGameObjects(100));
		assertTrue(World.isValidNbGameObjects(0));
	}
	
	@Test
	public void isValidNbGameObjects_falseCase(){
		assertFalse(World.isValidNbGameObjects(-10));
	}
	
	@Test
	public void addAsGameObject_mazubCase(){
		testWorld.addAsGameObject(testMazub);
		assertEquals(testWorld.getMazub(),testMazub);
		assertEquals(testMazub.getWorld(),testWorld);
	}
	
	@Test
	public void addAsGameObject_otherGameObjectLegalCase(){
		testWorld.addAsGameObject(slime1TestSchool1);
		assertTrue(testWorld.hasAsGameObject(slime1TestSchool1));
		assertEquals(slime1TestSchool1.getWorld(),testWorld);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void addAsGameObject_gameHasStartedCase(){
		testWorld.setGameHasStarted(true);
		testWorld.addAsGameObject(slime1TestSchool1);
	}
	
//	@Test (expected=IllegalArgumentException.class)
//	public void addAsGameObject_IllegalPositionCase(){
//		slime1TestSchool1.setVerticalLocation(-10);
//		testWorld.addAsGameObject(slime1TestSchool1);
//	}
	
	@Test (expected=IllegalArgumentException.class)
	public void addAsGameObject_illegalNbSchoolsCase(){
		world9Schools.addAsGameObject(slime1TestSchool1);
		world9Schools.addAsGameObject(slime1TestSchool2);
	}
	
	@Test 
	public void addAsGameObject_legalNbSchoolsCase(){
		world9Schools.addAsGameObject(slime1TestSchool1);
	}
	
	@Test 
	public void removeAsGameObject_legalCase(){
		testWorld.addAsGameObject(slime1TestSchool1);
		testWorld.removeAsGameObject(slime1TestSchool1);
		assertFalse(testWorld.hasAsGameObject(slime1TestSchool1));
		assertEquals(slime1TestSchool1.getWorld(),null);
	}
	
	
	
}
