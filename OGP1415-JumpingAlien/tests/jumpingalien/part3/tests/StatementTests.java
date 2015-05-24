package jumpingalien.part3.tests;

import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import jumpingalien.model.*;
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
		
	}


	@Test
	public void testAssignment_LegalCase() {
		Program program=new Program(staticTestAssignment42,new HashMap<String, Type<?>>());
		program.execute(5);
		assertTrue(program.getGlobalVariables().containsKey(staticTestString));
		assertTrue(program.getGlobalVariables().get(staticTestString) instanceof DoubleType);
		assertTrue(program.getGlobalVariables().get(staticTestString).getValue().equals(new Double(42)));
		Assignment testAssignment=new Assignment(staticTestString, new DoubleType(), new DoubleConstant(50,null),null);
		Program program2=new Program(testAssignment,new HashMap<String, Type<?>>());
		program2.execute(5);
		testAssignment.execute(program2);
		assertTrue(program2.getGlobalVariables().get(staticTestString).getValue().equals(new Double(50)));
	}
	
	@Test
	public void testAssignment_IllegalTypeCase() {
		Assignment testAssignment= new Assignment(staticTestString, new BoolType(), staticDoubleConstant42, null);
		testAssignment.execute(emptyProgram);
		assertTrue(emptyProgram.hasStopped());
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
		assertEquals(((Double) emptyProgram.getGlobalVariables().get("counter").getValue()).intValue(),3);
	}
	
	
	

}
