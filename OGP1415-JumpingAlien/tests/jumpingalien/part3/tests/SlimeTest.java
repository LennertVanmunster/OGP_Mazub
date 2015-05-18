package jumpingalien.part3.tests;

import static jumpingalien.tests.util.TestUtils.spriteArrayForSize;
import static org.junit.Assert.*;
import jumpingalien.model.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class SlimeTest {
	
	
	private static School emptySchool;
	private static School school1;
	private static School school2;
	private static Slime slime1School1;
	private static Slime slime2School1;
	private static Slime slime1School2;
	private static Slime slime2School2;
	private static School terminatedSchool;
	private static Slime jumpingSlime;
	
	@BeforeClass
	public static void setUpBeforeClass(){
		emptySchool= new School();
		school1=new School();
		slime1School1= new Slime(0,0,spriteArrayForSize(2, 2),school1);
		slime2School1= new Slime(10,0,spriteArrayForSize(2,2),school1);
		jumpingSlime=new Slime(0,10,spriteArrayForSize(2, 2),school1);
		jumpingSlime.setJumping(true);
		school2= new School();
		slime1School2= new Slime(20,0,spriteArrayForSize(2, 2),school2);
		slime2School2= new Slime(30,0,spriteArrayForSize(2,2),school2);
		terminatedSchool=new School();
		terminatedSchool.terminate();
	}
	
	private static World testWorld;
	private static School testSchool1;
	private static Slime slime1TestSchool1;
	private static Slime slime2TestSchool1;
	private static Slime slime3TestSchool1;
	private static School testSchool2;
	private static Slime slime1TestSchool2;
	private static Slime slime2TestSchool2;
	
	@Before
	public void setUp(){
		testWorld=new World(50,10,10,500,500,9,9);
		testSchool1=new School();
		slime1TestSchool1= new Slime(40,0,spriteArrayForSize(2,2),testSchool1);
		slime2TestSchool1= new Slime(50,0,spriteArrayForSize(2,2),testSchool1);
		slime3TestSchool1= new Slime(60,0,spriteArrayForSize(2,2),testSchool1);
		testSchool2=new School();
		slime1TestSchool2= new Slime(70,0,spriteArrayForSize(2,2),testSchool2);
		slime2TestSchool2= new Slime(70,0,spriteArrayForSize(2,2),testSchool2);
		testWorld.addAsGameObject(slime1TestSchool1);
		testWorld.addAsGameObject(slime2TestSchool1);
		testWorld.addAsGameObject(slime3TestSchool1);
		testWorld.addAsGameObject(slime1TestSchool2);
		testWorld.addAsGameObject(slime2TestSchool2);
	}
	
	@Test
	public void canHaveAsHorizontalVelocity_trueCase() {
		assertTrue(slime1School1.canHaveAsHorizontalVelocity(0));
		assertTrue(slime1School1.canHaveAsHorizontalVelocity(Slime.getMaximumHorizontalVelocityAtSpawn()));
		assertTrue(slime1School1.canHaveAsHorizontalVelocity(-Slime.getMaximumHorizontalVelocityAtSpawn()));
		assertTrue(slime1School1.canHaveAsHorizontalVelocity(Slime.getInitialHorizontalVelocityAtSpawn()));
		assertTrue(slime1School1.canHaveAsHorizontalVelocity(-Slime.getInitialHorizontalVelocityAtSpawn()));
	}
	
	@Test
	public void canHaveAsHorizontalVelocity_falseCase(){
		assertFalse(slime1School1.canHaveAsHorizontalVelocity(Slime.getMaximumHorizontalVelocityAtSpawn()+1));
		assertFalse(slime1School1.canHaveAsHorizontalVelocity(-Slime.getMaximumHorizontalVelocityAtSpawn()-1));
		if(Slime.getInitialHorizontalVelocityAtSpawn()!=0){
			assertFalse(slime1School1.canHaveAsHorizontalVelocity(Slime.getInitialHorizontalVelocityAtSpawn()/2));
			assertFalse(slime1School1.canHaveAsHorizontalVelocity(-Slime.getInitialHorizontalVelocityAtSpawn()/2));
		}
	}
	
	@Test
	public void isPossibleInitialHorizontalVelocity_trueCase(){
		assertTrue(slime1School1.isPossibleInitialHorizontalVelocity(Slime.getInitialHorizontalVelocityAtSpawn()));
	}
	
	@Test
	public void isPossibleInitialHorizontalVelocity_falseCase(){
		assertFalse(slime1School1.isPossibleInitialHorizontalVelocity(Slime.getInitialHorizontalVelocityAtSpawn()+1));
	}
	
	@Test
	public void isPossibleMaximumHorizontalVelocity_trueCase(){
		assertTrue(slime1School1.isPossibleMaximumHorizontalVelocity(Slime.getMaximumHorizontalVelocityAtSpawn()));
	}
	
	@Test
	public void isPossibleMaximumHorizontalVelocity_falseCase(){
		assertFalse(slime1School1.isPossibleInitialHorizontalVelocity(Slime.getMaximumHorizontalVelocityAtSpawn()+1));
	}
	
	@Test
	public void getVerticalAcceleration_notJumpingCase(){
		assertEquals(slime1TestSchool1.getVerticalAcceleration(),0,0);
	}
	
	@Test
	public void getVerticalAcceleration_JumpingCase(){
		assertEquals(jumpingSlime.getVerticalAcceleration(),Slime.VERTICAL_ACCELERATION,0);
	}
	
	@Test
	public void canHaveAsVerticalAcceleration_trueCase(){
		assertTrue(slime1School1.canHaveAsVerticalAcceleration(0));
		assertTrue(jumpingSlime.canHaveAsVerticalAcceleration(Slime.VERTICAL_ACCELERATION));
	}
	
	@Test
	public void canHaveAsVerticalAcceleration_falseCase(){
		assertFalse(slime1School1.canHaveAsVerticalAcceleration(Slime.VERTICAL_ACCELERATION/2));
	}
	
	@Test
	public void canHaveAsHorizontalAcceleration_trueCase(){
		assertTrue(slime1School1.canHaveAsHorizontalAcceleration(Slime.getHorizontalAccelerationAtSpawn()));
	}
	
	@Test
	public void canHaveAsHorizontalAcceleration_falseCase(){
		assertFalse(slime1School1.canHaveAsHorizontalAcceleration(Slime.getHorizontalAccelerationAtSpawn()+2));
	}

	@Test
	public void canHaveAsDuckingState_trueCase(){
		assertTrue(slime1School1.canHaveAsDuckingState(false));
	}
	
	@Test
	public void canHaveAsDuckingState_falseCase(){
		assertFalse(slime1School1.canHaveAsDuckingState(true));
	}
	
	@Test
	public void isValidActionDuration_trueCase(){
		assertTrue(Slime.isValidActionDuration(Slime.getMinimumActionDuration()));
		assertTrue(Slime.isValidActionDuration(Slime.getMaximumActionDuration()));
		assertTrue(Slime.isValidActionDuration((Slime.getMinimumActionDuration()+Slime.getMaximumActionDuration())/2));
	}
	
	@Test
	public void isValidActionDuration_falseCase(){
		assertFalse(Slime.isValidActionDuration(Slime.getMinimumActionDuration()-5));
		assertFalse(Slime.isValidActionDuration(Slime.getMaximumActionDuration()+5));
	}
	
	@Test
	public void setCurrentActionDuration_legalCase(){
		slime1TestSchool1.setCurrentActionDuration(Slime.getMinimumActionDuration());
		assertEquals(slime1TestSchool1.getCurrentActionDuration(),Slime.getMinimumActionDuration(),0);
		slime1TestSchool1.setCurrentActionDuration(Slime.getMaximumActionDuration());
		assertEquals(slime1TestSchool1.getCurrentActionDuration(),Slime.getMaximumActionDuration(),0);
		slime1TestSchool1.setCurrentActionDuration((Slime.getMinimumActionDuration()+Slime.getMaximumActionDuration())/2);
		assertEquals(slime1TestSchool1.getCurrentActionDuration(),(Slime.getMinimumActionDuration()+Slime.getMaximumActionDuration())/2,0);
	}
	
	@Test
	public void removeHitPointsSchool(){
		slime1TestSchool1.removeHitPointsSchool(10);
		assertEquals(slime1TestSchool1.getHitPoints(),Slime.HIT_POINTS-10);
		assertEquals(slime2TestSchool1.getHitPoints(),Slime.HIT_POINTS-1);
		assertEquals(slime3TestSchool1.getHitPoints(),Slime.HIT_POINTS-1);
	}
	
	@Test
	public void joinSchool(){
		slime1TestSchool1.setHitPoints(50);
		slime2TestSchool1.setHitPoints(50);
		slime3TestSchool1.setHitPoints(50);
		slime1TestSchool2.setHitPoints(50);
		slime2TestSchool2.setHitPoints(50);
		slime3TestSchool1.joinSchool(testSchool2);
		assertEquals(slime3TestSchool1.getSchool(),testSchool2);
		assertEquals(slime1TestSchool1.getHitPoints(),51);
		assertEquals(slime2TestSchool1.getHitPoints(),51);
		assertEquals(slime1TestSchool2.getHitPoints(),49);
		assertEquals(slime2TestSchool2.getHitPoints(),49);
		assertEquals(slime3TestSchool1.getHitPoints(),50);
	}
	
	@Test
	public void canHaveAsNbImages_trueCase(){
		assertTrue(slime1School1.canHaveAsNbImages(2));
	}
	
	@Test
	public void canHaveAsNbImages_falseCase(){
		assertFalse(slime1School1.canHaveAsNbImages(3));
	}
	
	@Test
	public void terminate(){
		slime1TestSchool1.terminate();
		assertEquals(slime1TestSchool1.getWorld(),null);
		assertFalse(testWorld.hasAsGameObject(slime1TestSchool1));
		assertEquals(slime1TestSchool1.getSchool(),null);
		assertFalse(testSchool1.hasAsSlime(slime1TestSchool1));
		assertTrue(slime1TestSchool1.isTerminated());
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void setSchool_nullCase(){
		slime1TestSchool1.setSchool(null);
	}
	
	@Test
	public void setSchool_legalCase(){
		slime1TestSchool1.setSchool(testSchool2);
		assertEquals(slime1TestSchool1.getSchool(),testSchool2);
	}
	
	@Test
	public void canHaveAsSchool_trueCase(){
		assertTrue(slime1School1.canHaveAsSchool(school2));
		assertTrue(slime1School1.canHaveAsSchool(school1));
		assertTrue(slime1School1.canHaveAsSchool(null));
	}
	
	@Test
	public void canHaveAsSlime_falseCase(){
		assertFalse(slime1School1.canHaveAsSchool(terminatedSchool));
	}
	
	@Test
	public void hasProperSchool_trueCase(){
		assertTrue(slime1School1.hasProperSchool());
		
	}
}
