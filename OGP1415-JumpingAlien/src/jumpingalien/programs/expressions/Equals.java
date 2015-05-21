package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.*;


/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class Equals extends Comparison<Type<?>> {

	/**
	 * @param left
	 * @param right
	 * @param sourceLocation
	 */
	public Equals(Expression<Type<?>> left, Expression<Type<?>> right,
			SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}

	@Override
	public BoolType evaluateLegalCase(Program program) {
		Type<?> left = this.getExpressionLeft().evaluateLegalCase(program);
		Type<?> right = this.getExpressionRight().evaluateLegalCase(program);
		return new BoolType(left.getValue() == right.getValue());
	}

	@Override
	public boolean checkType(Expression<Type<?>> expression) {
		return true;
	}

}
