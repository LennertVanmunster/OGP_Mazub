package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.BoolType;
import jumpingalien.programs.types.Type;


/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class NotEquals<T> extends Comparison<T> {

	/**
	 * @param left
	 * @param right
	 * @param sourceLocation
	 */
	public NotEquals(Expression<T> left, Expression<T> right,
			SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}

	@Override
	public BoolType evaluateLegalCase(Program program) {
		Type<?> left = this.getExpressionLeft().evaluateLegalCase(program);
		Type<?> right = this.getExpressionRight().evaluateLegalCase(program);
		return new BoolType(left.getValue() != right.getValue());
	}

	@Override
	public boolean checkType(Expression<T> expression) {
		return true;
	}

}
