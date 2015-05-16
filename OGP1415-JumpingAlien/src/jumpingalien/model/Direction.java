package jumpingalien.model;

/**
 * An enumeration of horizontal directions.
 * 
 * @version  2.0
 * @author   Eric Steegmans
 */
public enum Direction {

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
		
	};
	
	
	/**
	 * Return the number for this direction for usage in calculations.
     *
	 */
	public abstract int getNumberForCalculations();
	
}