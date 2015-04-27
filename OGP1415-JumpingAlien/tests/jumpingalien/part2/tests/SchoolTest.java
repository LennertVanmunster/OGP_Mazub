package jumpingalien.part2.tests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import jumpingalien.model.*;
import jumpingalien.util.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static jumpingalien.tests.util.TestUtils.*;


public class SchoolTest {

	private static School emptySchool;
	private static School firstSchool;
	private static School secondSchool;
	private static Slime firstSlimeFirstSchool;
	private static Slime secondSlimeFirstSchool;
	private static Slime firstSlimeSecondSchool;
	private static Slime secondSlimeSecondSchool;
	private static School terminatedSchool;
	private static School schoolWithNullSlime;
	
	@BeforeClass
	public static void setUpBeforeClass(){
		emptySchool= new School();
		firstSchool=new School();
		firstSlimeFirstSchool= new Slime(0,0,spriteArrayForSize(2, 2),firstSchool);
		secondSlimeFirstSchool= new Slime(10,0,spriteArrayForSize(2,2),firstSchool);
		secondSchool= new School();
		firstSlimeSecondSchool= new Slime(20,0,spriteArrayForSize(2, 2),secondSchool);
		secondSlimeSecondSchool= new Slime(30,0,spriteArrayForSize(2,2),secondSchool);
		terminatedSchool=new School();
		terminatedSchool.terminate();
	}
	
	private static School firstTestSchool;
	private static Slime firstSlimeFirstTestSchool;
	private static School secondTestSchool;
	private static Slime firstSlimeSecondTestSchool;
	
	@Before
	public void setUp(){
		firstTestSchool=new School();
		firstSlimeFirstTestSchool= new Slime(40,0,spriteArrayForSize(2,2),firstTestSchool);
		secondTestSchool=new School();
		firstSlimeSecondTestSchool= new Slime(50,0,spriteArrayForSize(2,2),secondTestSchool);
	}
	
	
	@Test
	public void hasAsSlime_SlimeInSchool() {
		assertTrue(firstSchool.hasAsSlime(firstSlimeFirstSchool));
	}
	
	@Test
	public void hasAsSlime_SlimeNotInSchool(){
		assertFalse(firstSchool.hasAsSlime(firstSlimeSecondSchool));
	}
	
	@Test
	public void canHaveAsSlime_nullCase(){
		assertFalse(firstSchool.canHaveAsSlime(null));
	}
	
	@Test
	public void canHaveAsSlime_terminatedCase(){
		assertFalse(terminatedSchool.canHaveAsSlime(firstSlimeFirstSchool));
	}
	
	@Test
	public void canHaveAsSlime_trueCase(){
		assertTrue(firstSchool.canHaveAsSlime(firstSlimeFirstSchool));
	}
	
	@Test
	public void isValidNbSlimes_trueCase(){
		assertTrue(firstSchool.isValidNbSlimes(5));
		assertTrue(firstSchool.isValidNbSlimes(0));
	}
	
	@Test
	public void isValidNbSlimes_falseCase(){
		assertFalse(firstSchool.isValidNbSlimes(-5));
	}
	
	@Test
	public void hasProperSlimes_trueCase(){
		assertTrue(firstSchool.hasProperSlimes());
	}
	
	@Test
	public void hasProperSlimes_slimeNotReferencingThisSchool(){
		firstSlimeFirstTestSchool.setSchool(secondTestSchool);
		assertFalse(firstTestSchool.hasProperSlimes());
	}
	
	@Test
	public void addAsSlime_legalCase(){
		secondTestSchool.removeAsSlime(firstSlimeSecondTestSchool);
		firstTestSchool.addAsSlime(firstSlimeSecondTestSchool);
		assertTrue(firstTestSchool.hasAsSlime(firstSlimeSecondTestSchool));
		assertEquals(firstSlimeSecondTestSchool.getSchool(),firstTestSchool);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addAsSlime_slimeAlreadyInSchool(){
		firstTestSchool.addAsSlime(firstSlimeSecondTestSchool);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void addAsSlime_terminatedCase(){
		terminatedSchool.addAsSlime(firstSlimeFirstTestSchool);
	}
	
	@Test 
	public void removeAsSlime_legalCase(){
		firstTestSchool.removeAsSlime(firstSlimeFirstTestSchool);
		assertFalse(firstTestSchool.hasAsSlime(firstSlimeFirstTestSchool));
		assertEquals(firstSlimeFirstTestSchool.getSchool(),null);
	}
	
	@Test
	public void removeAsSlime_slimeNotInSchool(){
		int startNbSlimes= firstTestSchool.getNbSlimes();
		firstTestSchool.removeAsSlime(firstSlimeSecondTestSchool);
		assertEquals(firstTestSchool.getNbSlimes(),startNbSlimes);
	}
	
	@Test
	public void getNbSlimes_hasSlimesCase(){
		assertEquals( firstSchool.getNbSlimes(),2);
	}
	
	@Test
	public void getNbSlimes_noSlimesCase(){
		assertEquals(emptySchool.getNbSlimes(),0);
	}
	
	@Test
	public void getAllSlimes_hasSlimesCase(){
		Set<Slime> slimesFirstSchool= new HashSet<Slime>();
		slimesFirstSchool.add(firstSlimeFirstSchool);
		slimesFirstSchool.add(secondSlimeFirstSchool);
		assertEquals(slimesFirstSchool,firstSchool.getAllSlimes());
	}
	
	@Test
	public void getAllSlimes_noSlimesCase(){
		Set<Slime> emptySet= new HashSet<Slime>();
		assertEquals(emptySet,emptySchool.getAllSlimes());
	}
	
	@Test
	public void terminate_hasSlimesCase(){
		firstTestSchool.terminate();
		assertEquals(firstTestSchool.getNbSlimes(),0);
		assertEquals(firstSlimeFirstTestSchool.getSchool(),null);
		assertTrue(firstTestSchool.isTerminated());
	}
	
	public void terminate_noSlimesCase(){
		emptySchool.terminate();
		assertEquals(emptySchool.getNbSlimes(),0);
		assertTrue(emptySchool.isTerminated());
	}
}
