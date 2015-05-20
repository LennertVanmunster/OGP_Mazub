package jumpingalien.part3.tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import jumpingalien.model.Orientation;
import jumpingalien.part3.programs.IProgramFactory.Direction;
import jumpingalien.programs.expressions.Addition;
import jumpingalien.programs.expressions.And;
import jumpingalien.programs.expressions.DirectionConstant;
import jumpingalien.programs.expressions.Division;
import jumpingalien.programs.expressions.DoubleConstant;
import jumpingalien.programs.expressions.Equals;
import jumpingalien.programs.expressions.False;
import jumpingalien.programs.expressions.True;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.statements.StartJump;
import jumpingalien.programs.statements.Statement;
import jumpingalien.programs.types.Type;
import jumpingalien.util.Util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExpressionsTests {

	private static Program program;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Map<String,Type> globalVariables = new HashMap<String, Type>();
		Statement statement =  new StartJump(null);
		program = new Program(statement, globalVariables);
	}

	@Before
	public void setUp() throws Exception {
	}
 
	//Addition:
	@Test
	public void testAddition_TrueCases() {
		Addition addition = new Addition(new DoubleConstant(3.1), new DoubleConstant(2.5), null);
		assertTrue(Util.fuzzyEquals(addition.evaluate(program), 5.6));
		addition = new Addition(new DoubleConstant(-3.1), new DoubleConstant(2.5), null);
		assertTrue(Util.fuzzyEquals(addition.evaluate(program), -0.6));
	}
	

	//And
	@Test
	public void testAnd_TrueCases() {
		And and = new And(new False(), new True(), null);
		assertTrue(and.evaluate(program) == false);
		and = new And(new True(), new True(), null);
		assertTrue(and.evaluate(program));
	}
	
	
	//DirectionConstant
	@Test
	public void testDirectionConstant_TrueCases() {
		DirectionConstant directionConstant = new DirectionConstant(Direction.LEFT, null);
		assertTrue(directionConstant.evaluate(program) == Orientation.LEFT);
		directionConstant = new DirectionConstant(Direction.RIGHT, null);
		assertTrue(directionConstant.evaluate(program) == Orientation.RIGHT);
		directionConstant = new DirectionConstant(Direction.DOWN, null);
		assertTrue(directionConstant.evaluate(program) == Orientation.DOWN);
		directionConstant = new DirectionConstant(Direction.UP, null);
		assertTrue(directionConstant.evaluate(program) == Orientation.UP);
	}
	
	//Division
	@Test
	public void testDivision_TrueCases() {
		Division division = new Division(new DoubleConstant(8.0), new DoubleConstant(2.0), null);
		assertTrue(division.evaluate(program) == 4.0);
		division = new Division(new DoubleConstant(-10.0), new DoubleConstant(2.5), null);
		assertTrue(division.evaluate(program) == -4.0);
	}
	
	//DoubleConstant
	@Test
	public void testDoubleConstant_TrueCases() {
		DoubleConstant doubleConstant = new DoubleConstant(1.29);
		assertTrue(doubleConstant.evaluate(program) == 1.29);
		doubleConstant = new DoubleConstant(-34.5645);
		assertTrue(doubleConstant.evaluate(program) == -34.5645);
		doubleConstant = new DoubleConstant(0);
		assertTrue(doubleConstant.evaluate(program) == 0);	
	}
	
	//Equals
	@Test
	public void testEquals_TrueCases() {
		Equals equals = new Equals(new DoubleConstant(8.0), new DoubleConstant(8.0), null);
		assertTrue(equals.evaluate(program));
		equals = new Equals(new False(), new True(), null);
		assertFalse(equals.evaluate(program));
		equals = new Equals(new DirectionConstant(Direction.LEFT), new DirectionConstant(Direction.LEFT), null);
		assertTrue(equals.evaluate(program));
		equals = new Equals(new DirectionConstant(Direction.LEFT), new DirectionConstant(Direction.LEFT), null);
		assertTrue(equals.evaluate(program));
	}
	
	//False
	@Test
	public void testFalse_TrueCases() {
		False false1 = new False();
		assertFalse(false1.evaluate(program));
	}
}
