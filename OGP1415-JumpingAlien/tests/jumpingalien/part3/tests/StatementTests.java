package jumpingalien.part3.tests;

import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import jumpingalien.model.*;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.part3.programs.IProgramFactory.Kind;
import jumpingalien.part3.programs.IProgramFactory.SortDirection;
import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.expressions.*;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.*;
import jumpingalien.programs.statements.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class StatementTests {
	
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
		List<Statement> listOfStatements= new ArrayList<Statement>();
		listOfStatements.add(staticTestAssignment42);
		listOfStatements.add(new Break(null));
		listOfStatements.add(staticTestAssignment55);
		sequenceContainingBreak= new Sequence(listOfStatements,null);
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
	public void testAssignment_LegalCase() {
		Program program=new Program(staticTestAssignment42,new HashMap<String, Type<?>>());
		program.execute(5);
		assertTrue(program.getGlobalVariables().containsKey(staticTestString));
		assertTrue(program.getGlobalVariables().get(staticTestString) instanceof DoubleType);
		assertTrue(program.getGlobalVariables().get(staticTestString).getValue().equals(new Double(42)));
		assertEquals(program.getTimer(), 5-0.001, 0.00001);
		Assignment testAssignment=new Assignment(staticTestString, new DoubleType(), new DoubleConstant(50,null),null);
		Program program2=new Program(testAssignment,new HashMap<String, Type<?>>());
		program2.execute(5);
		testAssignment.execute(program2);
		assertTrue(program2.getGlobalVariables().get(staticTestString).getValue().equals(new Double(50)));
	}
	
	@Test
	public void testAssignment_IllegalTypeCase() {
		Assignment testAssignment= new Assignment(staticTestString, new BoolType(), staticDoubleConstant42, null);
		emptyProgram.setMainStatement(testAssignment);
		emptyProgram.execute(5);
		assertTrue(emptyProgram.hasStopped());
		assertEquals(emptyProgram.getTimer(), 5, 0.00001);
		assertFalse(emptyProgram.getGlobalVariables().containsKey(staticTestString));
	}
	
	@Test
	public void testAssignment_IllegalTimeCase() {
		emptyProgram.setTimer(-10);
		staticTestAssignment42.execute(emptyProgram);
		assertFalse(emptyProgram.getGlobalVariables().containsKey(staticTestString));
	}
	
	@Test
	public void testBreak_LegalCase(){
		While whileLoop = new While(new True(null), sequenceContainingBreak, null);
		Program program= new Program(whileLoop, new HashMap<String, Type<?>>());
		program.execute(5);
		assertEquals(program.getTimer(), 5-0.003, 0.00001);
		assertTrue(program.getGlobalVariables().containsKey(staticTestString));
		assertTrue(program.getGlobalVariables().get(staticTestString) instanceof DoubleType);
		assertTrue(program.getGlobalVariables().get(staticTestString).getValue().equals(new Double(42)));
		assertFalse(program.getGlobalVariables().containsKey(staticTestString2));
	}
	
	@Test
	public void testForEach_AnyObjectNoSortNoFilter(){
		emptyProgram.putGlobalVariable("forLoopVar", new GameObjectType());
		emptyProgram.putGlobalVariable("counter", new DoubleType(0));
		testPlant1.setProgram(emptyProgram);
		testWorld.addAsGameObject(testPlant1);
		testWorld.addAsGameObject(testPlant2);
		testWorld.addAsGameObject(testShark1);
		testWorld.addAsGameObject(testShark2);
		testWorld.addAsGameObject(testSlime1);
		testWorld.addAsGameObject(testSlime2);
		Expression<DoubleType> readCounter= new ReadVariable<DoubleType>("counter", new DoubleType(), null);
		Expression<DoubleType> forLoopExpression= new Addition(readCounter, new DoubleConstant(1,null), null);
		Statement forLoopBody = new Assignment("counter", new DoubleType(), forLoopExpression, null);
		ForEach forEachLoop = new ForEach("forLoopVar", Kind.ANY, null, null , SortDirection.ASCENDING, forLoopBody, null);
		emptyProgram.setMainStatement(forEachLoop);
		emptyProgram.execute(5);
		assertEquals(emptyProgram.getTimer(), 5-0.007, 0.00001);
		assertEquals(((Double)emptyProgram.getGlobalVariables().get("counter").getValue()).intValue(), 6);
	}
	
	@Test
	public void testForEach_AnyObjectNoSortWithFilter(){
		emptyProgram.putGlobalVariable("forLoopVar", new GameObjectType());
		emptyProgram.putGlobalVariable("counter", new DoubleType(0));
		testPlant1.setProgram(emptyProgram);
		testWorld.addAsGameObject(testPlant1);
		testWorld.addAsGameObject(testPlant2);
		testWorld.addAsGameObject(testShark1);
		testWorld.addAsGameObject(testShark2);
		testWorld.addAsGameObject(testSlime1);
		testWorld.addAsGameObject(testSlime2);
		Expression<DoubleType> readCounter= new ReadVariable<DoubleType>("counter", new DoubleType(), null);
		Expression<DoubleType> forLoopExpression= new Addition(readCounter, new DoubleConstant(1,null), null);
		Statement forLoopBody = new Assignment("counter", new DoubleType(), forLoopExpression, null);
		Expression<GameObjectType> readForLoopVar = new ReadVariable<GameObjectType>("forLoopVar", new GameObjectType(), null);
		Expression<BoolType> whereExpression= new IsPlant<GameObjectType>(readForLoopVar, null);
		ForEach forEachLoop = new ForEach("forLoopVar", Kind.ANY, whereExpression, null , SortDirection.ASCENDING, forLoopBody, null);
		emptyProgram.setMainStatement(forEachLoop);
		emptyProgram.execute(5);
		assertEquals(emptyProgram.getTimer(), 5-0.003, 0.00001);
		assertEquals(((Double) emptyProgram.getGlobalVariables().get("counter").getValue()).intValue(),2);
	}
	
	@Test
	public void testForEach_AnyObjectWithSortNoFilter(){
		emptyProgram.putGlobalVariable("forLoopVar", new GameObjectType());
		emptyProgram.putGlobalVariable("counter", new DoubleType(0));
		testPlant1.setProgram(emptyProgram);
		testWorld.addAsGameObject(testPlant1);
		testWorld.addAsGameObject(testPlant2);
		testWorld.addAsGameObject(testShark1);
		testWorld.addAsGameObject(testShark2);
		testWorld.addAsGameObject(testSlime1);
		testWorld.addAsGameObject(testSlime2);
		Expression<DoubleType> readCounter= new ReadVariable<DoubleType>("counter", new DoubleType(), null);
		Expression<DoubleType> counterIncrementExpression= new Addition(readCounter, new DoubleConstant(1,null), null);
		Statement counterIncrementStatement = new Assignment("counter", new DoubleType(), counterIncrementExpression, null);
		Expression<GameObjectType> readForLoopVar = new ReadVariable<GameObjectType>("forLoopVar", new GameObjectType(), null);
		Expression<DoubleType> sortExpression= new GetX(readForLoopVar, null);
		Expression<BoolType> counterLessThan4 = new LessThan(readCounter, new DoubleConstant(4,null), null);
		Expression<BoolType> getX0= new Equals<DoubleType>(sortExpression, new DoubleConstant(0,null), null);
		Expression<BoolType> ifCondition = new And(getX0, counterLessThan4, null);
		Statement breakStatement= new Break(null);
		Statement ifCounter4 = new If(ifCondition, counterIncrementStatement, breakStatement, null);
		ForEach forEachLoop = new ForEach("forLoopVar", Kind.ANY, null, sortExpression , SortDirection.ASCENDING, ifCounter4, null);
		emptyProgram.setMainStatement(forEachLoop);
		emptyProgram.execute(5);
		assertEquals(emptyProgram.getTimer(), 5-0.009, 0.00001);
		assertEquals(((Double) emptyProgram.getGlobalVariables().get("counter").getValue()).intValue(),3);
	}
	
	@Test
	public void testForEach_AnyObjectWithSortDescendingNoFilter(){
		emptyProgram.putGlobalVariable("forLoopVar", new GameObjectType());
		emptyProgram.putGlobalVariable("counter", new DoubleType(0));
		testPlant1.setProgram(emptyProgram);
		testWorld.addAsGameObject(testPlant1);
		testWorld.addAsGameObject(testPlant2);
		testWorld.addAsGameObject(testShark1);
		testWorld.addAsGameObject(testShark2);
		testWorld.addAsGameObject(testSlime1);
		testWorld.addAsGameObject(testSlime2);
		Expression<DoubleType> readCounter= new ReadVariable<DoubleType>("counter", new DoubleType(), null);
		Expression<DoubleType> counterIncrementExpression= new Addition(readCounter, new DoubleConstant(1,null), null);
		Statement counterIncrementStatement = new Assignment("counter", new DoubleType(), counterIncrementExpression, null);
		Expression<GameObjectType> readForLoopVar = new ReadVariable<GameObjectType>("forLoopVar", new GameObjectType(), null);
		Expression<DoubleType> sortExpression= new GetX(readForLoopVar, null);
		Expression<BoolType> counterLessThan4 = new LessThan(readCounter, new DoubleConstant(4,null), null);
		Expression<BoolType> getX10= new Equals<DoubleType>(sortExpression, new DoubleConstant(10,null), null);
		Expression<BoolType> ifCondition = new And(getX10, counterLessThan4, null);
		Statement breakStatement= new Break(null);
		Statement ifCounter4 = new If(ifCondition, counterIncrementStatement, breakStatement, null);
		ForEach forEachLoop = new ForEach("forLoopVar", Kind.ANY, null, sortExpression , SortDirection.DESCENDING, ifCounter4, null);
		emptyProgram.setMainStatement(forEachLoop);
		emptyProgram.execute(5);
		assertEquals(emptyProgram.getTimer(), 5-0.009, 0.00001);
		assertEquals(((Double) emptyProgram.getGlobalVariables().get("counter").getValue()).intValue(),3);
	}
	
	@Test
	public void testForEach_Slimes(){
		emptyProgram.putGlobalVariable("forLoopVar", new GameObjectType());
		emptyProgram.putGlobalVariable("counter", new DoubleType(0));
		testPlant1.setProgram(emptyProgram);
		testWorld.addAsGameObject(testPlant1);
		testWorld.addAsGameObject(testPlant2);
		testWorld.addAsGameObject(testShark1);
		testWorld.addAsGameObject(testShark2);
		testWorld.addAsGameObject(testSlime1);
		testWorld.addAsGameObject(testSlime2);
		Expression<DoubleType> readCounter= new ReadVariable<DoubleType>("counter", new DoubleType(), null);
		Expression<DoubleType> forLoopExpression= new Addition(readCounter, new DoubleConstant(1,null), null);
		Statement forLoopBody = new Assignment("counter", new DoubleType(), forLoopExpression, null);
		ForEach forEachLoop = new ForEach("forLoopVar", Kind.SLIME, null, null , SortDirection.ASCENDING, forLoopBody, null);
		emptyProgram.setMainStatement(forEachLoop);
		emptyProgram.execute(5);
		assertEquals(emptyProgram.getTimer(), 5-0.003, 0.00001);
		assertEquals(((Double)emptyProgram.getGlobalVariables().get("counter").getValue()).intValue(), 2);
	}
	
	@Test
	public void testForEach_Tiles(){
		emptyProgram.putGlobalVariable("forLoopVar", new GameObjectType());
		emptyProgram.putGlobalVariable("counter", new DoubleType(0));
		testPlant1.setProgram(emptyProgram);
		testWorld.addAsGameObject(testPlant1);
		testWorld.addAsGameObject(testPlant2);
		testWorld.addAsGameObject(testShark1);
		testWorld.addAsGameObject(testShark2);
		testWorld.addAsGameObject(testSlime1);
		testWorld.addAsGameObject(testSlime2);
		Expression<DoubleType> readCounter= new ReadVariable<DoubleType>("counter", new DoubleType(), null);
		Expression<DoubleType> forLoopExpression= new Addition(readCounter, new DoubleConstant(1,null), null);
		Statement forLoopBody = new Assignment("counter", new DoubleType(), forLoopExpression, null);
		ForEach forEachLoop = new ForEach("forLoopVar", Kind.TERRAIN, null, null , SortDirection.ASCENDING, forLoopBody, null);
		emptyProgram.setMainStatement(forEachLoop);
		emptyProgram.execute(5);
		assertEquals(emptyProgram.getTimer(), 5-0.101, 0.00001);
		assertEquals(((Double)emptyProgram.getGlobalVariables().get("counter").getValue()).intValue(), 100);
	}
	
	@Test
	public void ifTest_ifBodyCase(){
		Statement ifStatement = new If(new True(null), staticTestAssignment42, staticTestAssignment55, null);
		Program program=new Program(ifStatement,new HashMap<String, Type<?>>());
		program.execute(5);
		assertEquals(program.getTimer(), 5-0.002, 0.00001);
		assertTrue(program.getGlobalVariables().get(staticTestString).getValue().equals(new Double(42)));
	}
	
	@Test
	public void ifTest_elseBodyCase(){
		Statement ifStatement = new If(new False(null), staticTestAssignment42, staticTestAssignment55, null);
		Program program=new Program(ifStatement,new HashMap<String, Type<?>>());
		program.execute(5);
		assertEquals(program.getTimer(), 5-0.002, 0.00001);
		assertTrue(program.getGlobalVariables().get(staticTestString2).getValue().equals(new Double(5.5)));
	}
	
	@Test
	public void sequenceTest_emptySequenceCase(){
		Sequence emptySequence= new Sequence(null, null);
		emptyProgram.setMainStatement(emptySequence);
		emptyProgram.execute(5);
		assertEquals(emptyProgram.getTimer(), 5, 0.00001);
	}
	
	@Test
	public void sequenceTest_oneStatementCase(){
		List<Statement> listOfStatements= new ArrayList<Statement>();
		listOfStatements.add(staticTestAssignment42);
		Sequence testSequence=new Sequence(listOfStatements, null);
		emptyProgram.setMainStatement(testSequence);
		emptyProgram.execute(5);
		assertTrue(emptyProgram.getGlobalVariables().get(staticTestString).getValue().equals(new Double(42)));
		assertEquals(emptyProgram.getTimer(), 5-0.001, 0.00001);
	}
	
	@Test
	public void sequenceTest_multipleStatementCase(){
		List<Statement> listOfStatements= new ArrayList<Statement>();
		listOfStatements.add(staticTestAssignment42);
		listOfStatements.add(staticTestAssignment55);
		Sequence testSequence=new Sequence(listOfStatements, null);
		emptyProgram.setMainStatement(testSequence);
		emptyProgram.execute(5);
		assertTrue(emptyProgram.getGlobalVariables().get(staticTestString).getValue().equals(new Double(42)));
		assertTrue(emptyProgram.getGlobalVariables().get(staticTestString2).getValue().equals(new Double(5.5)));
		assertEquals(emptyProgram.getTimer(), 5-0.002, 0.00001);
	}
	
	@Test
	public void skipTest(){
		emptyProgram.setMainStatement(new Skip(null));
		emptyProgram.execute(5);
		assertEquals(emptyProgram.getTimer(), 5-0.001, 0.00001);
	}
	
	@Test
	public void StartDuck(){
		testBuzam.setProgram(emptyProgram);
		emptyProgram.setMainStatement(new StartDuck(null));
		emptyProgram.execute(5);
		assertEquals(emptyProgram.getTimer(), 5-0.001, 0.00001);
		assertTrue(testBuzam.isDucking());
	}
	
	@Test
	public void StopDuck(){
		testBuzam.setProgram(emptyProgram);
		testWorld.addAsGameObject(testBuzam);
		List<Statement> listOfStatements= new ArrayList<Statement>();
		listOfStatements.add(new StartDuck(null));
		listOfStatements.add(new StopDuck(null));
		Sequence sequence= new Sequence(listOfStatements,null);
		emptyProgram.setMainStatement(sequence);
		emptyProgram.execute(5);
		assertEquals(emptyProgram.getTimer(), 5-0.002, 0.00001);
		assertFalse(testBuzam.isDucking());
	}
	
	@Test
	public void StartJump(){
		testBuzam.setProgram(emptyProgram);
		emptyProgram.setMainStatement(new StartJump(null));
		emptyProgram.execute(5);
		assertEquals(emptyProgram.getTimer(), 5-0.001, 0.00001);
		assertTrue(testBuzam.isJumping());
	}
	
	@Test
	public void StopJump(){
		testBuzam.setProgram(emptyProgram);
		testWorld.addAsGameObject(testBuzam);
		List<Statement> listOfStatements= new ArrayList<Statement>();
		listOfStatements.add(new StartJump(null));
		listOfStatements.add(new StopJump(null));
		Sequence sequence= new Sequence(listOfStatements,null);
		emptyProgram.setMainStatement(sequence);
		emptyProgram.execute(5);
		assertEquals(emptyProgram.getTimer(), 5-0.002, 0.00001);
		assertEquals(testBuzam.getVerticalVelocity(),0,0.0001);
	}
	
	@Test
	public void StartRun(){
		testBuzam.setProgram(emptyProgram);
		emptyProgram.setMainStatement(new StartRun(new DirectionConstant(Direction.LEFT, null), null));
		emptyProgram.execute(5);
		assertEquals(emptyProgram.getTimer(), 5-0.001, 0.00001);
		assertEquals(testBuzam.getDirection(), Orientation.LEFT);
		assertTrue(testBuzam.isMovingHorizontally());
	}
	
	@Test
	public void StopRun(){
		testBuzam.setProgram(emptyProgram);
		testWorld.addAsGameObject(testBuzam);
		List<Statement> listOfStatements= new ArrayList<Statement>();
		listOfStatements.add(new StartRun(new DirectionConstant(Direction.LEFT, null), null));
		listOfStatements.add(new StopRun(new DirectionConstant(Direction.LEFT, null), null));
		Sequence sequence= new Sequence(listOfStatements,null);
		emptyProgram.setMainStatement(sequence);
		emptyProgram.execute(5);
		assertEquals(emptyProgram.getTimer(), 5-0.002, 0.00001);
		assertEquals(testBuzam.getDirection(), Orientation.LEFT);
		assertFalse(testBuzam.isMovingHorizontally());
	} 
	
	@Test
	public void StartRun_alreadyRunning(){
		testBuzam.setProgram(emptyProgram);
		testWorld.addAsGameObject(testBuzam);
		List<Statement> listOfStatements= new ArrayList<Statement>();
		listOfStatements.add(new StartRun(new DirectionConstant(Direction.LEFT, null), null));
		listOfStatements.add(new StartRun(new DirectionConstant(Direction.RIGHT, null), null));
		Sequence sequence= new Sequence(listOfStatements,null);
		emptyProgram.setMainStatement(sequence);
		emptyProgram.execute(5);
		assertEquals(emptyProgram.getTimer(), 5-0.002, 0.00001);
		assertEquals(testBuzam.getDirection(), Orientation.LEFT);
		assertTrue(testBuzam.isMovingHorizontally());
	} 
	
	@Test
	public void Wait_durationShorterThanDeltaTime(){
		emptyProgram.setMainStatement(new Wait(new DoubleConstant(1, null), null));
		emptyProgram.execute(5);
		assertEquals(emptyProgram.getTimer(), 5-1, 0.00001);
	}
	
	@Test
	public void Wait_durationShorterThanTimeUnit(){
		emptyProgram.setMainStatement(new Wait(new DoubleConstant(0.00001, null), null));
		emptyProgram.execute(5);
		assertEquals(emptyProgram.getTimer(), 5-0.001, 0.00001);
	}
	
	@Test
	public void Wait_durationLongerThanDeltaTime(){
		List<Statement> listOfStatements= new ArrayList<Statement>();
		listOfStatements.add(new Assignment("testVar", new DoubleType(), new DoubleConstant(5, null), null));
		listOfStatements.add(new Wait(new DoubleConstant(17, null), null));
		listOfStatements.add(new Assignment("testVar", new DoubleType(), new DoubleConstant(10, null), null));
		Sequence sequence= new Sequence(listOfStatements,null);
		emptyProgram.setMainStatement(sequence);
		emptyProgram.execute(5);
		assertEquals(emptyProgram.getTimer(), 5-17-0.001, 0.00001);
		assertTrue(emptyProgram.getGlobalVariables().get("testVar").getValue().equals(new Double(5)));
		emptyProgram.execute(5);
		assertEquals(emptyProgram.getTimer(), 5-12-0.001, 0.00001);
		emptyProgram.execute(5);
		assertEquals(emptyProgram.getTimer(), 5-7-0.001, 0.00001);
		emptyProgram.execute(5);
		assertEquals(emptyProgram.getTimer(), 5-2-0.002, 0.00001);
		assertTrue(emptyProgram.getGlobalVariables().get("testVar").getValue().equals(new Double(10)));
	}
	
	@Test
	public void Wait_durationNegative(){
		emptyProgram.setMainStatement(new Wait(new DoubleConstant(-10, null), null));
		emptyProgram.execute(5);
		assertTrue(emptyProgram.hasStopped());
	}
	
	@Test
	public void whileTest_sufficientTimeToComplete(){
		emptyProgram.putGlobalVariable("counter", new DoubleType(100));
		Expression<DoubleType> readCounter= new ReadVariable<DoubleType>("counter", new DoubleType(), null);
		Expression<BoolType> counterNotZero = new GreaterThan(readCounter, new DoubleConstant(0, null), null);
		Expression<DoubleType> whileBodyExpression= new Subtraction(readCounter, new DoubleConstant(1,null), null);
		Statement whileBody = new Assignment("counter", new DoubleType(), whileBodyExpression, null);
		While whileLoop = new While(counterNotZero, whileBody, null);
		emptyProgram.setMainStatement(whileLoop);
		emptyProgram.execute(5);
		assertEquals(emptyProgram.getTimer(), 5-0.201, 0.00001);
	}
	
	@Test
	public void whileTest_insufficientTimeToComplete(){
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
	
	
	
	
	
	

}
