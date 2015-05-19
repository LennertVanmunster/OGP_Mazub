package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class Sqrt extends GetDoubleValueOf {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public Sqrt(Expression expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}

	@Override
	public Double evaluate(Program program) {
		return Math.sqrt((double) this.getExpression().evaluate(program));
	}

}
