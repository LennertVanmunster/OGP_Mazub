package jumpingalien.model;

import jumpingalien.part3.programs.IProgramFactory.Direction;

/**
 * An enumeration of horizontal directions.
 * 
 * @version  2.0
 * @author   Eric Steegmans
 */
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
}