package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;


/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class LessThan extends Comparison {

	/**
	 * @param left
	 * @param right
	 * @param sourceLocation
	 */
	public LessThan(Expression left, Expression right,
			SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}

	@Override
	public Boolean evaluate(Program program) {
		return (double)this.getExpressionLeft().evaluate(program) < 
		(double)this.getExpressionRight().evaluate(program);
	}

}
