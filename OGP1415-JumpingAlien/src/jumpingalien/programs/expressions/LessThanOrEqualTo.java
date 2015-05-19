package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.util.Util;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class LessThanOrEqualTo extends Comparison {

	/**
	 * @param left
	 * @param right
	 * @param sourceLocation
	 */
	public LessThanOrEqualTo(Expression left, Expression right,
			SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}

	@Override
	public Boolean evaluate(Program program) {
		if(this.getExpressionLeft().evaluate(program) != null && this.getExpressionRight().evaluate(program) != null){
		return Util.fuzzyLessThanOrEqualTo(((double)this.getExpressionLeft().evaluate(program)), 
				(double)this.getExpressionRight().evaluate(program));
		}
		else
			return false;
	}

}
