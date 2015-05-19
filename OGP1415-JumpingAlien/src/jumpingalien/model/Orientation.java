package jumpingalien.model;

import be.kuleuven.cs.som.annotate.Value;
import jumpingalien.part3.programs.IProgramFactory.Direction;

/**
 * An enumeration of horizontal directions.
 * 
 * @version  2.0
 * @author   Pieter Van Damme and Lennert Vanmunster
 */
@Value
public enum Orientation {

	LEFT {
		
		/**
		 * Return the number representing the negative horizontal direction for usage in calculations.
	     *
		 * @return -1
		 *       | result == -1
		 */
		public int getNumberForCalculations() {
			return -1;
		}
		
	},
	
	RIGHT {

		/**
		 * Return the symbol representing the positive horizontal direction for usage in calculations.
	     *
		 * @return 1
		 *       | result == 1
		 */
		public int getNumberForCalculations() {
			return 1;
		}
	},
	
	UP {
		
		/**
		 * Return the number representing the positive vertical direction for usage in calculations.
	     *
		 * @return 1
		 *       | result == 1
		 */
		public int getNumberForCalculations() {
			return 1;
		}
		
	},
	
	DOWN {
		
		/**
		 * Return the number representing the negative vertical direction for usage in calculations.
	     *
		 * @return -1
		 *       | result == -1
		 */
		public int getNumberForCalculations() {
			return -1;
		}
		
	},
	/**
	 * Dummy implementation to call the convert method.
	 */
	DUMMY {
		
		/**
		 * Return the number representing the dummy direction for usage in calculations. Never used!
	     *
		 * @return 1
		 *       | result == 1
		 *       
		 */
		public int getNumberForCalculations() {
			return 1;
		}
		
	},;
	
	
	/**
	 * Return the number for this direction for usage in calculations.
     *
	 */
	public abstract int getNumberForCalculations();
	
	/**
	 * Convert an enumeration value from the value class Direction from IProgramFactory
	 * to a value of the class Orientation.
	 * 
	 * @param 	direction
	 * 			The given value from the class Direction. 
	 * @return	|Direction.LEFT = Orientation.LEFT
	 * 			|Direction.RIGHT = Orientation.RIGHT
	 * 			|Direction.UP = Orientation.UP
	 * 			|Direction.DOWN = Orientation.DOWN
	 */
	public Orientation convertDirectionIProgramFactory(Direction direction){
		switch(direction){
		case LEFT:
			return Orientation.LEFT;
		case RIGHT:
			return Orientation.RIGHT;
		case UP:
			return Orientation.UP;
		case DOWN:
			return Orientation.DOWN;
		}
		return Orientation.RIGHT;
	}
	
	/**
	 * Convert an enumeration value from the value class Orientation 
	 * to a value of the class Direction from IProgramFactory.
	 * 
	 * @param 	direction
	 * 			The given value from the class Orientation. 
	 * @return	|Orientation.LEFT = Direction.LEFT
	 * 			|Orientation.RIGHT = Direction.RIGHT
	 * 			|Orientation.UP = Direction.UP
	 * 			|Orientation.DOWN = Direction.DOWN
	 * 			|Orientation.DUMMY = Direction.RIGHT
	 */
	public Direction convertOrientation(Orientation orientation){
		switch(orientation){
		case LEFT:
			return Direction.LEFT;
		case RIGHT:
			return Direction.RIGHT;
		case UP:
			return Direction.UP;
		case DOWN:
			return Direction.DOWN;
		case DUMMY:
			return Direction.RIGHT;
		}
		return Direction.RIGHT;
	}
}