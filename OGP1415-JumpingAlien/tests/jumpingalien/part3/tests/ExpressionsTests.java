package jumpingalien.part3.tests;

import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import jumpingalien.model.Buzam;
import jumpingalien.model.GameObject;
import jumpingalien.model.Mazub;
import jumpingalien.model.Orientation;
import jumpingalien.model.World;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.programs.expressions.Addition;
import jumpingalien.programs.expressions.And;
import jumpingalien.programs.expressions.DirectionConstant;
import jumpingalien.programs.expressions.Division;
import jumpingalien.programs.expressions.DoubleConstant;
import jumpingalien.programs.expressions.Equals;
import jumpingalien.programs.expressions.Expression;
import jumpingalien.programs.expressions.False;
import jumpingalien.programs.expressions.GameObjectExpression;
import jumpingalien.programs.expressions.GetHeigth;
import jumpingalien.programs.expressions.GetHitPoints;
import jumpingalien.programs.expressions.GetTile;
import jumpingalien.programs.expressions.GetWidth;
import jumpingalien.programs.expressions.GetX;
import jumpingalien.programs.expressions.GetY;
import jumpingalien.programs.expressions.True;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.statements.StartJump;
import jumpingalien.programs.statements.Statement;
import jumpingalien.programs.types.GameObjectType;
import jumpingalien.programs.types.ObjectType;
import jumpingalien.programs.types.Type;
import jumpingalien.util.Util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExpressionsTests {

	private static Program program;
	private static GameObject staticTestGameObject;
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
		staticTestGameObject = new Buzam(0, 499, program, spriteArrayForSize(3, 3));
		staticTestGameObject.setWorld(staticTestWorld);
		staticTestWorld.setBuzam((Buzam) staticTestGameObject);
		
	}

	@Before
	public void setUp() throws Exception {
	}
 
	//Addition:
	@Test
	public void testAddition_TrueCases() {
		Addition addition = new Addition(new DoubleConstant(3.1, null), new DoubleConstant(2.5, null), null);
		assertTrue(Util.fuzzyEquals(addition.evaluateLegalCase(program).getValue(), 5.6));
		addition = new Addition(new DoubleConstant(-3.1, null), new DoubleConstant(2.5, null), null);
		assertTrue(Util.fuzzyEquals(addition.evaluateLegalCase(program).getValue(), -0.6));
	}
	

	//And
	@Test
	public void testAnd_TrueCases() {
		And and = new And(new False(null), new True(null), null);
		assertTrue(and.evaluateLegalCase(program).getValue() == false);
		and = new And(new True(null), new True(null), null);
		assertTrue(and.evaluateLegalCase(program).getValue());
	}
	
	
	//DirectionConstant
	@Test
	public void testDirectionConstant_TrueCases() {
		DirectionConstant directionConstant = new DirectionConstant(Direction.LEFT, null);
		assertTrue(directionConstant.evaluateLegalCase(program).getValue() == Direction.LEFT);
		directionConstant = new DirectionConstant(Direction.RIGHT, null);
		assertTrue(directionConstant.evaluateLegalCase(program).getValue() == Direction.RIGHT);
		directionConstant = new DirectionConstant(Direction.DOWN, null);
		assertTrue(directionConstant.evaluateLegalCase(program).getValue() == Direction.DOWN);
		directionConstant = new DirectionConstant(Direction.UP, null);
		assertTrue(directionConstant.evaluateLegalCase(program).getValue() == Direction.UP);
	}
	
	//Division
	@Test
	public void testDivision_TrueCases() {
		Division division = new Division(new DoubleConstant(8.0, null), new DoubleConstant(2.0, null), null);
		assertTrue(division.evaluateLegalCase(program).getValue() == 4.0);
		division = new Division(new DoubleConstant(-10.0, null), new DoubleConstant(2.5, null), null);
		assertTrue(division.evaluateLegalCase(program).getValue() == -4.0);
	}
	
	//DoubleConstant
	@Test
	public void testDoubleConstant_TrueCases() {
		DoubleConstant doubleConstant = new DoubleConstant(1.29, null);
		assertTrue(doubleConstant.evaluateLegalCase(program).getValue() == 1.29);
		doubleConstant = new DoubleConstant(-34.5645, null);
		assertTrue(doubleConstant.evaluateLegalCase(program).getValue() == -34.5645);
		doubleConstant = new DoubleConstant(0, null);
		assertTrue(doubleConstant.evaluateLegalCase(program).getValue() == 0);	
	}
	
//	//Equals
//	@Test
//	public void testEquals_TrueCases() {
//		Equals equals = new Equals(new DoubleConstant(8.0, null), new DoubleConstant(8.0, null), null);
//		assertTrue(equals.evaluateLegalCase(program).getValue());
//		equals = new Equals(new False(null), new True(null), null);
//		assertFalse(equals.evaluateLegalCase(program).getValue());
//		equals = new Equals(new DirectionConstant(Direction.LEFT), new DirectionConstant(Direction.LEFT), null);
//		assertTrue(equals.evaluateLegalCase(program).getValue());
//		equals = new Equals(new DirectionConstant(Direction.LEFT), new DirectionConstant(Direction.LEFT), null);
//		assertTrue(equals.evaluateLegalCase(program).getValue());
//	}
	
	//False
	@Test
	public void testFalse_TrueCases() {
		False false1 = new False(null);
		assertFalse(false1.evaluateLegalCase(program).getValue());
	}
	
	//GetHeigth
	@Test
	public void testGetHeight_TrueCases() {
		Expression<GameObjectType> gameObject = new GameObjectExpression(staticTestGameObject);
		GetHeigth getHeight = new GetHeigth(gameObject, null);
		assertTrue(Util.fuzzyEquals(getHeight.evaluateLegalCase(program).getValue(),3));
	}
		
		
	//GetHitPoints
	@Test
	public void testGetHitPoints_TrueCases() {
		Expression<GameObjectType> gameObject = new GameObjectExpression(staticTestGameObject);
		GetHitPoints getHitPoints = new GetHitPoints(gameObject, null);
		assertTrue(Util.fuzzyEquals(getHitPoints.evaluateLegalCase(program).getValue(),500));
	}
	
	//GetTile
	@Test
	public void testGetTile_TrueCases() {
		GetTile getTile = new GetTile(new DoubleConstant(650, null), new DoubleConstant(450, null), null);
		int [] tile = {1,0};
		assertTrue(Arrays.equals(getTile.evaluateLegalCase(program).getValue(),tile));
	}
	
	//GetWidth
	@Test
	public void testGetWidth_TrueCases() {
		Expression<GameObjectType> gameObject = new GameObjectExpression(staticTestGameObject);
		GetWidth getWidth = new GetWidth(gameObject, null);
		assertTrue(Util.fuzzyEquals(getWidth.evaluateLegalCase(program).getValue(),3));
	}	
	
	//GetX
	@Test
	public void testGetX_TrueCases() {
		Expression<GameObjectType> gameObject = new GameObjectExpression(staticTestGameObject);
		GetX getX = new GetX(gameObject, null);
		assertTrue(Util.fuzzyEquals(getX.evaluateLegalCase(program).getValue(),0));
	}	
	
	//GetY
	@Test
	public void testGetY_TrueCases() {
		Expression<GameObjectType> gameObject = new GameObjectExpression(staticTestGameObject);
		GetY getY = new GetY(gameObject, null);
		assertTrue(Util.fuzzyEquals(getY.evaluateLegalCase(program).getValue(),499));
	}
	
		
		
		
		
		
		
		
}
