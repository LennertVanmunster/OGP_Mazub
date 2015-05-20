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
	public BoolType evaluate(Program program) {
		if(this.getStopProgram()){
			program.stop();
		}
		Type<?> left = this.getExpressionLeft().evaluate(program);
		Type<?> right = this.getExpressionRight().evaluate(program);
		return new BoolType(left.getValue() != right.getValue());
	}

	@Override
	public boolean checkType(Expression<T> expression) {
		return true;
	}

}
