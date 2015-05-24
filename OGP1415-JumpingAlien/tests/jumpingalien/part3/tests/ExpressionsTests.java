package jumpingalien.part3.tests;

import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import jumpingalien.model.Buzam;
import jumpingalien.model.GameObject;
import jumpingalien.model.Mazub;
import jumpingalien.model.Plant;
import jumpingalien.model.School;
import jumpingalien.model.Shark;
import jumpingalien.model.Slime;
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
import jumpingalien.programs.expressions.GreaterThan;
import jumpingalien.programs.expressions.GreaterThanOrEqualTo;
import jumpingalien.programs.expressions.IsAir;
import jumpingalien.programs.expressions.IsDead;
import jumpingalien.programs.expressions.IsDucking;
import jumpingalien.programs.expressions.IsJumping;
import jumpingalien.programs.expressions.IsMagma;
import jumpingalien.programs.expressions.IsMazub;
import jumpingalien.programs.expressions.IsMoving;
import jumpingalien.programs.expressions.IsPassable;
import jumpingalien.programs.expressions.IsPlant;
import jumpingalien.programs.expressions.IsShark;
import jumpingalien.programs.expressions.IsSlime;
import jumpingalien.programs.expressions.IsTerrain;
import jumpingalien.programs.expressions.IsWater;
import jumpingalien.programs.expressions.LessThan;
import jumpingalien.programs.expressions.LessThanOrEqualTo;
import jumpingalien.programs.expressions.Multiplication;
import jumpingalien.programs.expressions.Not;
import jumpingalien.programs.expressions.NotEquals;
import jumpingalien.programs.expressions.Null;
import jumpingalien.programs.expressions.Or;
import jumpingalien.programs.expressions.RandomDouble;
import jumpingalien.programs.expressions.SearchObject;
import jumpingalien.programs.expressions.Self;
import jumpingalien.programs.expressions.Sqrt;
import jumpingalien.programs.expressions.Subtraction;
import jumpingalien.programs.expressions.TileObjectExpression;
import jumpingalien.programs.expressions.True;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.statements.StartJump;
import jumpingalien.programs.statements.Statement;
import jumpingalien.programs.types.BoolType;
import jumpingalien.programs.types.DoubleType;
import jumpingalien.programs.types.DirectionType;
import jumpingalien.programs.types.GameObjectType;
import jumpingalien.programs.types.TileType;
import jumpingalien.programs.types.Type;
import jumpingalien.util.Util;



import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExpressionsTests {

	private static Program program;
	private static Program programPlant;
	private static Program programSlime;
	private static Program programShark;
	private static GameObject staticTestGameObject;
	private static GameObject staticTestMazub;
	private static GameObject staticTestPlant;
	private static GameObject staticTestSlime;
	private static GameObject staticTestShark;
	private static School emptySchool;
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
		programPlant = new Program(statement, globalVariables);
		programSlime = new Program(statement, globalVariables);
		programShark = new Program(statement, globalVariables);
		staticTestWorld = new World(500, 3, 3, 1000, 1000, 2, 2);
		staticTestWorld.setTileValueAtTilePosition( 0, 0, FEATURE_SOLID);
		staticTestWorld.setTileValueAtTilePosition( 1, 0, FEATURE_MAGMA);
		staticTestWorld.setTileValueAtTilePosition( 2, 0, FEATURE_WATER);
		staticTestGameObject = new Buzam(0, 499, program, spriteArrayForSize(3, 3));
		staticTestGameObject.setWorld(staticTestWorld);
		staticTestWorld.setBuzam((Buzam) staticTestGameObject);
		staticTestMazub = new Mazub(50, 499, spriteArrayForSize(3, 3));
		staticTestMazub.setWorld(staticTestWorld);
		staticTestWorld.setMazub( (Mazub) staticTestMazub);
		staticTestPlant = new Plant(0, 1394, programPlant, spriteArrayForSize(3, 3));
		staticTestWorld.addAsGameObject(staticTestPlant);
		emptySchool= new School();
		staticTestSlime = new Slime(150, 505, spriteArrayForSize(3, 3), emptySchool, programSlime);
		staticTestWorld.addAsGameObject(staticTestSlime);
		staticTestShark = new Shark(200, 505, programShark, spriteArrayForSize(3, 3));
		staticTestWorld.addAsGameObject(staticTestShark);
		
		
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
		addition = new Addition(new DoubleConstant(-4.2, null), new DoubleConstant(-1.7, null), null);
		assertTrue(Util.fuzzyEquals(addition.evaluateLegalCase(program).getValue(), -5.9));
		addition = new Addition(new DoubleConstant(0, null), new DoubleConstant(2.5, null), null);
		assertTrue(Util.fuzzyEquals(addition.evaluateLegalCase(program).getValue(), 2.5));
	}
	

	//And
	@Test
	public void testAnd_TrueCases() {
		And and = new And(new False(null), new True(null), null);
		assertTrue(and.evaluateLegalCase(program).getValue() == false);
		and = new And(new True(null), new True(null), null);
		assertTrue(and.evaluateLegalCase(program).getValue());
		and = new And(new False(null), new False(null), null);
		assertFalse(and.evaluateLegalCase(program).getValue());
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
		division = new Division(new DoubleConstant(-2.5, null), new DoubleConstant(-5.0, null), null);
		assertTrue(division.evaluateLegalCase(program).getValue() == 0.5);
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
	
	//Equals
	@Test
	public void testEquals_TrueCases() {
		Equals<DoubleType> equals = new Equals<DoubleType>(new DoubleConstant(8.0, null), new DoubleConstant(8.0, null), null);
		assertTrue(equals.evaluateLegalCase(program).getValue());
		Equals<BoolType> equals2 = new Equals<BoolType>(new False(null), new True(null), null);
		assertFalse(equals2.evaluateLegalCase(program).getValue());
		Equals<DirectionType> equals3 = new Equals<DirectionType>(new DirectionConstant(Direction.LEFT,null), new DirectionConstant(Direction.LEFT, null), null);
		assertTrue(equals3.evaluateLegalCase(program).getValue());
	}
	
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
	
	//GreaterThan
	@Test
	public void testGreaterThan_TrueCases() {
		GreaterThan greaterThan = new GreaterThan(new DoubleConstant(8.0, null), new DoubleConstant(2.0, null), null);
		assertTrue(greaterThan.evaluateLegalCase(program).getValue());
		greaterThan = new GreaterThan(new DoubleConstant(1.56, null), new DoubleConstant(10.543, null), null);
		assertFalse(greaterThan.evaluateLegalCase(program).getValue());
		greaterThan = new GreaterThan(new DoubleConstant(34.433, null), new DoubleConstant(34.433, null), null);
		assertFalse(greaterThan.evaluateLegalCase(program).getValue());
		greaterThan = new GreaterThan(new DoubleConstant(0.001, null), new DoubleConstant(0.0, null), null);
		assertTrue(greaterThan.evaluateLegalCase(program).getValue());
		
	}	
		
	//GreaterThanOrEqualTo
	@Test
	public void testGreaterThanOrEqualTo_TrueCases() {
		GreaterThanOrEqualTo greaterThanOrEqualTo = new GreaterThanOrEqualTo(new DoubleConstant(8.0, null), new DoubleConstant(2.0, null), null);
		assertTrue(greaterThanOrEqualTo.evaluateLegalCase(program).getValue());
		greaterThanOrEqualTo = new GreaterThanOrEqualTo(new DoubleConstant(1.56, null), new DoubleConstant(10.543, null), null);
		assertFalse(greaterThanOrEqualTo.evaluateLegalCase(program).getValue());
		greaterThanOrEqualTo = new GreaterThanOrEqualTo(new DoubleConstant(34.433, null), new DoubleConstant(34.433, null), null);
		assertTrue(greaterThanOrEqualTo.evaluateLegalCase(program).getValue());
		greaterThanOrEqualTo = new GreaterThanOrEqualTo(new DoubleConstant(0.001, null), new DoubleConstant(0.0, null), null);
		assertTrue(greaterThanOrEqualTo.evaluateLegalCase(program).getValue());
		
	}
	
	//IsAir
	@Test
	public void testIsAir_TrueCases() {
		int [] tile = {0,1};
		Expression<TileType> gameObject = new TileObjectExpression(tile);
		IsAir<TileType> isAir = new IsAir<TileType>(gameObject, null);
		assertTrue(isAir.evaluateLegalCase(program).getValue());
	}	
	
	//IsDead
	@Test
	public void testIsDead_TrueCases() {
		Expression<GameObjectType> gameObject = new GameObjectExpression(staticTestGameObject);	
		IsDead<GameObjectType> isDead = new IsDead<GameObjectType>(gameObject, null);
		assertFalse(isDead.evaluateLegalCase(program).getValue());
	}	
		
	//IsDucking
	@Test
	public void testIsDucking_TrueCases() {
		Expression<GameObjectType> gameObject = new GameObjectExpression(staticTestGameObject);	
		IsDucking<GameObjectType> isDucking = new IsDucking<GameObjectType>(gameObject, null);
		assertFalse(isDucking.evaluateLegalCase(program).getValue());
	}
		
	//IsJumping
	@Test
	public void testIsJumping_TrueCases() {
		Expression<GameObjectType> gameObject = new GameObjectExpression(staticTestGameObject);	
		IsJumping<GameObjectType> isJumping = new IsJumping<GameObjectType>(gameObject, null);
		assertFalse(isJumping.evaluateLegalCase(program).getValue());
		staticTestGameObject.startJump();
		assertTrue(isJumping.evaluateLegalCase(program).getValue());
		staticTestGameObject.endJump();
	}
	
	//IsMagma
	@Test
	public void testIsMagma_TrueCases() {
		int [] tile = {1,0};
		Expression<TileType> gameObject = new TileObjectExpression(tile);
		IsMagma<TileType> isMagma = new IsMagma<TileType>(gameObject, null);
		assertTrue(isMagma.evaluateLegalCase(program).getValue());
	}
	
	//IsMazub
	@Test
	public void testIsMazub_TrueCases() {
		Expression<GameObjectType> gameObject = new GameObjectExpression(staticTestGameObject);	
		IsMazub<GameObjectType> isMazub = new IsMazub<GameObjectType>(gameObject, null);
		assertFalse(isMazub.evaluateLegalCase(program).getValue());
		gameObject = new GameObjectExpression(staticTestMazub);
		isMazub = new IsMazub<GameObjectType>(gameObject, null);
		assertTrue(isMazub.evaluateLegalCase(program).getValue());
	}
	
	//IsMoving
	@Test
	public void testIsMoving_TrueCases() {
		Expression<GameObjectType> gameObject = new GameObjectExpression(staticTestGameObject);	
		DirectionConstant directionConstant = new DirectionConstant(Direction.LEFT, null);
		IsMoving<GameObjectType> isMoving = new IsMoving<GameObjectType>(gameObject, directionConstant, null);
		assertFalse(isMoving.evaluateLegalCase(program).getValue());
	}
	
	//IsPassable
	@Test
	public void testIsPassable_TrueCases() {
		int [] tile = {0,1};
		Expression<TileType> gameObject = new TileObjectExpression(tile);
		IsPassable<TileType> isPassable = new IsPassable<TileType>(gameObject, null);
		assertTrue(isPassable.evaluateLegalCase(program).getValue());
	}
		
	//IsPlant
	@Test
	public void testIsPlant_TrueCases() {
		Expression<GameObjectType> gameObject = new GameObjectExpression(staticTestGameObject);	
		IsPlant<GameObjectType> isPlant = new IsPlant<GameObjectType>(gameObject, null);
		assertFalse(isPlant.evaluateLegalCase(program).getValue());
		gameObject = new GameObjectExpression(staticTestPlant);	
		isPlant = new IsPlant<GameObjectType>(gameObject, null);
		assertTrue(isPlant.evaluateLegalCase(program).getValue());
	}
	
	//IsShark
	@Test
	public void testIsShark_TrueCases() {
		Expression<GameObjectType> gameObject = new GameObjectExpression(staticTestGameObject);	
		IsShark<GameObjectType> isShark = new IsShark<GameObjectType>(gameObject, null);
		assertFalse(isShark.evaluateLegalCase(program).getValue());
		gameObject = new GameObjectExpression(staticTestShark);	
		isShark = new IsShark<GameObjectType>(gameObject, null);
		assertTrue(isShark.evaluateLegalCase(program).getValue());
	}
	
	//IsSlime
	@Test
	public void testIsSlime_TrueCases() {
		Expression<GameObjectType> gameObject = new GameObjectExpression(staticTestGameObject);	
		IsSlime<GameObjectType> isSlime = new IsSlime<GameObjectType>(gameObject, null);
		assertFalse(isSlime.evaluateLegalCase(program).getValue());
		gameObject = new GameObjectExpression(staticTestSlime);	
		isSlime = new IsSlime<GameObjectType>(gameObject, null);
		assertTrue(isSlime.evaluateLegalCase(program).getValue());
	}
	
	//IsTerrain
	@Test
	public void testIsTerrain_TrueCases() {
		int [] tile = {0,1};
		Expression<TileType> gameObject = new TileObjectExpression(tile);
		IsTerrain<TileType> isTerrain = new IsTerrain<TileType>(gameObject, null);
		assertTrue(isTerrain.evaluateLegalCase(program).getValue());
	}
	
	//IsWater
	@Test
	public void testIsWater_TrueCases() {
		int [] tile = {2,0};
		Expression<TileType> gameObject = new TileObjectExpression(tile);
		IsWater<TileType> isWater = new IsWater<TileType>(gameObject, null);
		assertTrue(isWater.evaluateLegalCase(program).getValue());
	}
	
	//LessThan
	@Test
	public void testLessThan_TrueCases() {
		LessThan lessThan = new LessThan(new DoubleConstant(4.0, null), new DoubleConstant(5.0, null), null);
		assertTrue(lessThan.evaluateLegalCase(program).getValue());
		lessThan = new LessThan(new DoubleConstant(10.45, null), new DoubleConstant(10.451, null), null);
		assertTrue(lessThan.evaluateLegalCase(program).getValue());
		lessThan = new LessThan(new DoubleConstant(-0.45, null), new DoubleConstant(0, null), null);
		assertTrue(lessThan.evaluateLegalCase(program).getValue());
	}
	
	//LessThanOrEqual
	@Test
	public void testLessThanOrEqual_TrueCases() {
		LessThanOrEqualTo lessThanOrEqualTo = new LessThanOrEqualTo(new DoubleConstant(4.0, null), new DoubleConstant(5.0, null), null);
		assertTrue(lessThanOrEqualTo.evaluateLegalCase(program).getValue());
		lessThanOrEqualTo = new LessThanOrEqualTo(new DoubleConstant(10.451, null), new DoubleConstant(10.451, null), null);
		assertTrue(lessThanOrEqualTo.evaluateLegalCase(program).getValue());
		lessThanOrEqualTo = new LessThanOrEqualTo(new DoubleConstant(-0.451, null), new DoubleConstant(-0.451, null), null);
		assertTrue(lessThanOrEqualTo.evaluateLegalCase(program).getValue());
	}
	
	//Multiplication:
	@Test
	public void testMultiplication_TrueCases() {
		Multiplication multiplication = new Multiplication(new DoubleConstant(3.1, null), new DoubleConstant(2.5, null), null);
		assertTrue(Util.fuzzyEquals(multiplication.evaluateLegalCase(program).getValue(), 7.75));
		multiplication = new Multiplication(new DoubleConstant(0, null), new DoubleConstant(7.34, null), null);
		assertTrue(Util.fuzzyEquals(multiplication.evaluateLegalCase(program).getValue(), 0));
		multiplication = new Multiplication(new DoubleConstant(-5.32, null), new DoubleConstant(2.3, null), null);
		assertTrue(Util.fuzzyEquals(multiplication.evaluateLegalCase(program).getValue(), -12.236));
		multiplication = new Multiplication(new DoubleConstant(-3, null), new DoubleConstant(-2, null), null);
		assertTrue(Util.fuzzyEquals(multiplication.evaluateLegalCase(program).getValue(), 6));
	}
	
	//Not
	@Test
	public void testNot_TrueCases() {
		Not not = new Not(new False(null), null);
		assertTrue(not.evaluateLegalCase(program).getValue());
	}
	
	//NotEquals
	@Test
	public void testNotEquals_TrueCases() {
		NotEquals<DoubleType> notEquals = new NotEquals<DoubleType>(new DoubleConstant(8.0, null), new DoubleConstant(7.99, null), null);
		assertTrue(notEquals.evaluateLegalCase(program).getValue());
		NotEquals<BoolType> notEquals2 = new NotEquals<BoolType>(new False(null), new True(null), null);
		assertTrue(notEquals2.evaluateLegalCase(program).getValue());
		NotEquals<DirectionType> notEquals3 = new NotEquals<DirectionType>(new DirectionConstant(Direction.RIGHT,null), new DirectionConstant(Direction.LEFT, null), null);
		assertTrue(notEquals3.evaluateLegalCase(program).getValue());
	}
	
	//Null
	@Test
	public void testNull_TrueCases() {
		Null null1 = new Null(null);
		assertTrue(null1.evaluateLegalCase(program).getValue() == null);
	}
	
	//Or
	@Test
	public void testOr_TrueCases() {
		Or or = new Or(new False(null), new True(null), null);
		assertTrue(or.evaluateLegalCase(program).getValue());
		or = new Or(new False(null), new False(null), null);
		assertTrue(or.evaluateLegalCase(program).getValue() == false);
	}
	
	//RandomDouble
	@Test
	public void testRandomDouble_TrueCases() {
		RandomDouble randomDouble = new RandomDouble(new DoubleConstant(3.1, null),  null);
		assertTrue(Util.fuzzyLessThanOrEqualTo(randomDouble.evaluateLegalCase(program).getValue(), 3.1) 
				&& Util.fuzzyGreaterThanOrEqualTo(randomDouble.evaluateLegalCase(program).getValue(), 0));
	}
	
	//SearchObject
	@Test
	public void testSearchObject_TrueCases() {
		DirectionConstant directionConstant = new DirectionConstant(Direction.DOWN, null);
		SearchObject searchObject = new SearchObject(directionConstant,  null);
		int [] tile = {0,0};
		assertTrue(Arrays.equals((int[]) searchObject.evaluateLegalCase(program).getValue(), tile));
		directionConstant = new DirectionConstant(Direction.RIGHT, null);
		searchObject = new SearchObject(directionConstant,  null);
		assertTrue(searchObject.evaluateLegalCase(program).getValue() instanceof Mazub);
		directionConstant = new DirectionConstant(Direction.UP, null);
		searchObject = new SearchObject(directionConstant,  null);
		assertTrue(searchObject.evaluateLegalCase(program).getValue() instanceof Plant);
		directionConstant = new DirectionConstant(Direction.LEFT, null);
		searchObject = new SearchObject(directionConstant,  null);
		assertTrue(searchObject.evaluateLegalCase(program).getValue() == null);
	}
	
	//Self
	@Test
	public void testSelf_TrueCases() {
		Self self = new Self( null);
		assertTrue(self.evaluateLegalCase(program).getValue() instanceof Buzam);
		self = new Self( null);
		assertTrue(self.evaluateLegalCase(programShark).getValue() instanceof Shark);
	}
	
	//Sqrt
	@Test
	public void testSqrt_TrueCases() {
		Sqrt sqrt = new Sqrt(new DoubleConstant(100.0, null), null);
		assertTrue(Util.fuzzyEquals(sqrt.evaluateLegalCase(program).getValue(), 10.0));
		sqrt = new Sqrt(new DoubleConstant(4.0, null), null);
		assertTrue(Util.fuzzyEquals(sqrt.evaluateLegalCase(program).getValue(), 2.0));
	}
	
	//Sqrt
	@Test
	public void testSqrt_FalseCase() {
		Sqrt sqrt = new Sqrt(new DoubleConstant(-4.0, null), null);
		assertTrue(Util.fuzzyEquals(sqrt.evaluateLegalCase(program).getValue(), 2.0));
	}
	
	//Subtraction
	@Test
	public void testSubtraction_TrueCases() {
		Subtraction subtraction = new Subtraction(new DoubleConstant(3.1, null), new DoubleConstant(2.5, null), null);
		assertTrue(Util.fuzzyEquals(subtraction.evaluateLegalCase(program).getValue(), 0.6));
		subtraction = new Subtraction(new DoubleConstant(-3.1, null), new DoubleConstant(2.5, null), null);
		assertTrue(Util.fuzzyEquals(subtraction.evaluateLegalCase(program).getValue(), -5.6));
	}
	
	//True
	@Test
	public void testTrue_TrueCases() {
		True true1 = new True(null);
		assertTrue(true1.evaluateLegalCase(program).getValue());
	}
}
