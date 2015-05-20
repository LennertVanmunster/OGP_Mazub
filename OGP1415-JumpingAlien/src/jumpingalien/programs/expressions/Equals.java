package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.statements.Statement;
import jumpingalien.util.Util;


/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class Equals extends Comparison {

	/**
	 * @param left
	 * @param right
	 * @param sourceLocation
	 */
	public Equals(Expression left, Expression right,
			SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}

	@Override
	public Boolean evaluate(Program program) {
		Object left = this.getExpressionLeft().evaluate(program);
		Object right = this.getExpressionLeft().evaluate(program);
		if( left instanceof Double && right instanceof Double){
			return Util.fuzzyEquals((double)left, (double)right);
		}
		return this.getExpressionLeft().evaluate(program) == 
				this.getExpressionRight().evaluate(program);
	}

}
