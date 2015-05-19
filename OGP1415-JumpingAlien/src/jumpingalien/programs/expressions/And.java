package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class And extends BooleanOperation {

	/**
	 * @param left
	 * @param right
	 * @param sourceLocation
	 */
	public And(Expression left, Expression right, SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}
	
	@Override
	public Boolean evaluate(Program program) {
		return (boolean) this.getExpressionLeft().evaluate(program) &&
				(boolean) this.getExpressionRight().evaluate(program);
	}

}
