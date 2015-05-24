package jumpingalien.part3.tests;

import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.*;

import java.util.HashMap;



import java.util.Map;

import jumpingalien.model.Buzam;
import jumpingalien.model.Plant;
import jumpingalien.model.School;
import jumpingalien.model.Shark;
import jumpingalien.model.Slime;
import jumpingalien.model.World;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.IProgramFactory.Kind;
import jumpingalien.part3.programs.IProgramFactory.SortDirection;
import jumpingalien.programs.expressions.*;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.statements.*;
import jumpingalien.programs.types.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ProgramTests {

	public static Program emptyProgram;
	public static DoubleType staticDouble;
	public static String staticTestString;
	public static String staticTestString2;
	public static DoubleConstant staticDoubleConstant42;
	public static DoubleConstant staticDoubleConstant55;
	public static Sequence sequenceContainingBreak;
	public static Assignment staticTestAssignment42;
	public static Assignment staticTestAssignment55;
	public static World testWorld;
	public static Slime testSlime1;
	public static Slime testSlime2;
	public static Plant testPlant1;
	public static Plant testPlant2;
	public static Shark testShark1;
	public static Shark testShark2;
	public static School testSchool;
	public static Buzam testBuzam;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		staticDouble = new DoubleType(42);
		staticTestString = "testString";
		staticTestString2 = "testString2";
		staticDoubleConstant42= new DoubleConstant(42, null);
		staticDoubleConstant55= new DoubleConstant(5.5, null);
		staticTestAssignment42=new Assignment(staticTestString, new DoubleType(), staticDoubleConstant42, null);
		staticTestAssignment55=new Assignment(staticTestString2, new DoubleType(), staticDoubleConstant55, null);
	}

	@Before
	public void setUp() throws Exception {
		emptyProgram= new Program();
		testWorld= new World(50,10,10,500,500,9,9);
		testSchool= new School();
		testSlime1= new Slime(0,0,spriteArrayForSize(2, 2),testSchool);
		testSlime2= new Slime(10,0, spriteArrayForSize(2, 2), testSchool);
		testPlant1= new Plant(0, 10, spriteArrayForSize(2, 2));
		testPlant2= new Plant(10, 10, spriteArrayForSize(2, 2));
		testShark1= new Shark(0,20, spriteArrayForSize(2, 2));
		testShark2= new Shark(10,20, spriteArrayForSize(2, 2));
		testBuzam= new Buzam(20, 0, spriteArrayForSize(2, 2));
	}
	
	@Test
	public void getandsetMainStatementTest(){
		emptyProgram.setMainStatement(staticTestAssignment42);
		assertEquals(emptyProgram.getMainStatement(), staticTestAssignment42);
	}
	
	@Test
	public void getAndSetGlobalVariablesTest_emptyMap(){
		Map<String,Type<?>> globalVariables = new HashMap<String, Type<?>>();
		emptyProgram.setGlobalVariables(globalVariables);
		assertTrue(emptyProgram.getGlobalVariables().isEmpty());
	}
	
	@Test
	public void getAndSetGlobalVariablesTest_nonEmptyMap(){
		Map<String,Type<?>> globalVariables = new HashMap<String, Type<?>>();
		globalVariables.put("testString", new DoubleType(42));
		emptyProgram.setGlobalVariables(globalVariables);
		assertEquals(((Double) emptyProgram.getGlobalVariables().get("testString").getValue()).intValue(), 42);
	}
	
	@Test
	public void putGlobalVariableTest_newVariable(){
		emptyProgram.putGlobalVariable("testString", new DoubleType(42));
		assertEquals(((Double) emptyProgram.getGlobalVariables().get("testString").getValue()).intValue(), 42);
	}
	
	@Test
	public void putGlobalVariableTest_overwriteVariable(){
		emptyProgram.putGlobalVariable("testString", new DoubleType(42));
		emptyProgram.putGlobalVariable("testString", new BoolType(true));
		assertEquals(((Boolean) emptyProgram.getGlobalVariables().get("testString").getValue()).booleanValue(), true);
	}
	
	@Test
	public void executeTest_normalConditions(){
		Program program=new Program(staticTestAssignment42,new HashMap<String, Type<?>>());
		program.execute(5);
		assertTrue(program.getGlobalVariables().containsKey(staticTestString));
		assertTrue(program.getGlobalVariables().get(staticTestString) instanceof DoubleType);
		assertTrue(program.getGlobalVariables().get(staticTestString).getValue().equals(new Double(42)));
		assertEquals(program.getTimer(), 5-0.001, 0.00001);
	}
	
	@Test
	public void executeTest_programHasStopped(){
		Program program=new Program(staticTestAssignment42,new HashMap<String, Type<?>>());
		program.stop();
		program.execute(5);
		assertFalse(program.getGlobalVariables().containsKey(staticTestString));
	}
	
	@Test
	public void executeTest_timeDepletedCase(){
		emptyProgram.putGlobalVariable("counter", new DoubleType(4000));
		Expression<DoubleType> readCounter= new ReadVariable<DoubleType>("counter", new DoubleType(), null);
		Expression<BoolType> counterNotZero = new GreaterThan(readCounter, new DoubleConstant(0, null), null);
		Expression<DoubleType> whileBodyExpression= new Subtraction(readCounter, new DoubleConstant(1,null), null);
		Statement whileBody = new Assignment("counter", new DoubleType(), whileBodyExpression, null);
		While whileLoop = new While(counterNotZero, whileBody, null);
		emptyProgram.setMainStatement(whileLoop);
		emptyProgram.execute(5);
		assertEquals(emptyProgram.getTimer(), 0, 0.00001);
		assertEquals(((DoubleType)emptyProgram.getGlobalVariables().get("counter")).getValue().intValue(), 1500);
		assertTrue(whileLoop.getCallSecondTime());
		emptyProgram.execute(5);
		assertEquals(emptyProgram.getTimer(), 2, 0.00001);
		assertEquals(((DoubleType)emptyProgram.getGlobalVariables().get("counter")).getValue().intValue(), 0);
	}
	
	@Test
	public void isWellFormedActionStatementsTest_trueCase(){
		emptyProgram.putGlobalVariable("counter", new DoubleType(4000));
		Expression<DoubleType> readCounter= new ReadVariable<DoubleType>("counter", new DoubleType(), null);
		Expression<BoolType> counterNotZero = new GreaterThan(readCounter, new DoubleConstant(0, null), null);
		Expression<DoubleType> whileBodyExpression= new Subtraction(readCounter, new DoubleConstant(1,null), null);
		Statement whileBody = new Assignment("counter", new DoubleType(), whileBodyExpression, null);
		While whileLoop = new While(counterNotZero, whileBody, null);
		emptyProgram.setMainStatement(whileLoop);
		assertTrue(emptyProgram.isWellFormedActionStatements(emptyProgram.getMainStatement()));
		Program emptyProgram2=new Program();
		emptyProgram2.putGlobalVariable("forLoopVar", new GameObjectType());
		emptyProgram2.putGlobalVariable("counter", new DoubleType(0));
		testPlant1.linkProgram(emptyProgram2);
		testWorld.addAsGameObject(testPlant1);
		testWorld.addAsGameObject(testPlant2);
		testWorld.addAsGameObject(testShark1);
		testWorld.addAsGameObject(testShark2);
		testWorld.addAsGameObject(testSlime1);
		testWorld.addAsGameObject(testSlime2);
		Expression<DoubleType> readCounter2= new ReadVariable<DoubleType>("counter", new DoubleType(), null);
		Expression<DoubleType> forLoopExpression= new Addition(readCounter2, new DoubleConstant(1,null), null);
		Statement forLoopBody = new Assignment("counter", new DoubleType(), forLoopExpression, null);
		ForEach forEachLoop = new ForEach("forLoopVar", Kind.ANY, null, null , SortDirection.ASCENDING, forLoopBody, null);
		emptyProgram2.setMainStatement(forEachLoop);
		assertTrue(emptyProgram2.isWellFormedActionStatements(emptyProgram2.getMainStatement()));
	}
	
	@Test
	public void isWellFormedActionStatementsTest_directFalseCase(){
		emptyProgram.putGlobalVariable("forLoopVar", new GameObjectType());
		emptyProgram.putGlobalVariable("counter", new DoubleType(0));
		testPlant1.linkProgram(emptyProgram);
		testWorld.addAsGameObject(testPlant1);
		testWorld.addAsGameObject(testPlant2);
		testWorld.addAsGameObject(testShark1);
		testWorld.addAsGameObject(testShark2);
		testWorld.addAsGameObject(testSlime1);
		testWorld.addAsGameObject(testSlime2);
		Statement forLoopBody = new StartRun(new DirectionConstant(Direction.RIGHT,null), null);
		ForEach forEachLoop = new ForEach("forLoopVar", Kind.ANY, null, null , SortDirection.ASCENDING, forLoopBody, null);
		emptyProgram.setMainStatement(forEachLoop);
		assertFalse(emptyProgram.isWellFormedActionStatements(emptyProgram.getMainStatement()));
	}
	
	@Test
	public void isWellFormedActionStatementsTest_indirectFalseCase(){
		emptyProgram.putGlobalVariable("forLoopVar", new GameObjectType());
		emptyProgram.putGlobalVariable("counter", new DoubleType(0));
		testPlant1.linkProgram(emptyProgram);
		testWorld.addAsGameObject(testPlant1);
		testWorld.addAsGameObject(testPlant2);
		testWorld.addAsGameObject(testShark1);
		testWorld.addAsGameObject(testShark2);
		testWorld.addAsGameObject(testSlime1);
		testWorld.addAsGameObject(testSlime2);
		Statement actionStatement = new StartRun(new DirectionConstant(Direction.RIGHT,null), null);
		Statement ifStatement = new If(new True(null), actionStatement, null, null);
		ForEach forEachLoop = new ForEach("forLoopVar", Kind.ANY, null, null , SortDirection.ASCENDING, ifStatement, null);
		emptyProgram.setMainStatement(forEachLoop);
		assertFalse(emptyProgram.isWellFormedActionStatements(emptyProgram.getMainStatement()));
	}
	
	@Test
	public void isWellFormedBreakStatementsTest_trueCases(){
		emptyProgram.putGlobalVariable("counter", new DoubleType(4000));
		Expression<DoubleType> readCounter= new ReadVariable<DoubleType>("counter", new DoubleType(), null);
		Expression<BoolType> counterNotZero = new GreaterThan(readCounter, new DoubleConstant(0, null), null);
		Expression<DoubleType> whileBodyExpression= new Subtraction(readCounter, new DoubleConstant(1,null), null);
		Statement whileBody = new Assignment("counter", new DoubleType(), whileBodyExpression, null);
		While whileLoop = new While(counterNotZero, whileBody, null);
		emptyProgram.setMainStatement(whileLoop);
		assertTrue(emptyProgram.isWellFormedBreakStatements(emptyProgram.getMainStatement()));
		Program emptyProgram2=new Program();
		testPlant1.linkProgram(emptyProgram2);
		testWorld.addAsGameObject(testPlant1);
		testWorld.addAsGameObject(testPlant2);
		testWorld.addAsGameObject(testShark1);
		testWorld.addAsGameObject(testShark2);
		testWorld.addAsGameObject(testSlime1);
		testWorld.addAsGameObject(testSlime2);
		ForEach forEachLoop = new ForEach("forLoopVar", Kind.ANY, null, null , SortDirection.ASCENDING, new Break(), null);
		emptyProgram2.setMainStatement(forEachLoop);
		assertTrue(emptyProgram2.isWellFormedBreakStatements(emptyProgram2.getMainStatement()));
		Statement ifStatement = new If(new True(null), new Break(), null, null);
		forEachLoop = new ForEach("forLoopVar", Kind.ANY, null, null , SortDirection.ASCENDING, ifStatement, null);
		emptyProgram2.setMainStatement(forEachLoop);
		assertTrue(emptyProgram2.isWellFormedBreakStatements(emptyProgram2.getMainStatement()));
		While whileLoop2 = new While(new True(null), ifStatement, null);
		emptyProgram2.setMainStatement(whileLoop2);
		assertTrue(emptyProgram2.isWellFormedBreakStatements(emptyProgram2.getMainStatement()));
	}
	
	@Test
	public void isWellFormedBreakStatementsTest_falseCase(){
		emptyProgram.setMainStatement(new Break());
		assertFalse(emptyProgram.isWellFormedBreakStatements(emptyProgram.getMainStatement()));
		Statement ifStatement = new If(new True(null), new Break(), null, null);
		emptyProgram.setMainStatement(ifStatement);
		assertFalse(emptyProgram.isWellFormedBreakStatements(emptyProgram.getMainStatement()));
	}
	
	@Test
	public void getAndSetTimerTest(){
		emptyProgram.setTimer(5);
		assertEquals(emptyProgram.getTimer(), 5, 0.00000001);
	}
	
	@Test
	public void decreaseTimerOneUnitTest(){
		emptyProgram.setTimer(5);
		emptyProgram.decreaseTimerOneUnit();
		assertEquals(emptyProgram.getTimer(), 5-0.001, 0.00000001);
	}
	
	@Test
	public void hasTimeForStatementTest(){
		emptyProgram.setTimer(5);
		assertTrue(emptyProgram.hasTimeForStatement());
		emptyProgram.setTimer(0.000001);
		assertTrue(emptyProgram.hasTimeForStatement());
		emptyProgram.setTimer(0);
		assertFalse(emptyProgram.hasTimeForStatement());
		emptyProgram.setTimer(-5);
		assertFalse(emptyProgram.hasTimeForStatement());
	}
	
	@Test
	public void isAndSetTimeDepletedTest(){
		emptyProgram.setTimeDepleted(true);
		assertTrue(emptyProgram.isTimeDepleted());
	}
	
	@Test
	public void setGameObjectTest_legalCase(){
		emptyProgram.setGameObject(testBuzam);
		assertEquals(emptyProgram.getGameObject(), testBuzam);
		emptyProgram.setGameObject(null);
		assertEquals(emptyProgram.getGameObject(), null);
		emptyProgram.terminate();
		emptyProgram.setGameObject(null);
		assertEquals(emptyProgram.getGameObject(), null);
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void setGameObjectTest_illegalCase(){
		testWorld.addAsGameObject(testBuzam);
		testBuzam.terminate();
		emptyProgram.setGameObject(testBuzam);
	}
	
	@Test
	public void canHaveAsGameObjectTest_trueCase(){
		assertTrue(emptyProgram.canHaveAsGameObject(testBuzam));
		assertTrue(emptyProgram.canHaveAsGameObject(null));
		emptyProgram.terminate();
		assertTrue(emptyProgram.canHaveAsGameObject(null));
	}
	
	@Test
	public void canHaveAsGameObjectTest_falseCase(){
		testWorld.addAsGameObject(testBuzam);
		testBuzam.terminate();
		assertFalse(emptyProgram.canHaveAsGameObject(testBuzam));
	}
	
	@Test
	public void hasProperGameObjectTest_trueCase(){
		emptyProgram.setGameObject(null);
		assertTrue(emptyProgram.hasProperGameObject());
		testBuzam.linkProgram(emptyProgram);
		assertTrue(emptyProgram.hasProperGameObject());
	}
	
	@Test
	public void hasProperGameObjectTest_falseCase(){
		emptyProgram.setGameObject(testBuzam);
		assertFalse(emptyProgram.hasProperGameObject());
	}
	
	@Test
	public void getWorldTest(){
		testWorld.addAsGameObject(testBuzam);
		emptyProgram.setGameObject(testBuzam);
		assertEquals(emptyProgram.getWorld(),testWorld);
		emptyProgram.setGameObject(null);
		assertEquals(emptyProgram.getWorld(),null);
	}
	
	@Test
	public void terminateTest_nullCase(){
		emptyProgram.terminate();
		assertTrue(emptyProgram.isTerminated());
		emptyProgram.terminate();
		assertTrue(emptyProgram.isTerminated());
	}
	
	@Test
	public void terminateTest_gameObjectCase(){
		emptyProgram.setGameObject(testBuzam);
		emptyProgram.terminate();
		assertTrue(emptyProgram.isTerminated());
		assertEquals(emptyProgram.getGameObject(), null);
		emptyProgram.terminate();
		assertTrue(emptyProgram.isTerminated());
	}
	
	

}
