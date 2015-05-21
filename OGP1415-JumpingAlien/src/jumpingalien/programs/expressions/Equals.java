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
	public BoolType evaluate(Program program) {
		if(this.getStopProgram() || program.hasStopped()){
			program.stop();
			return null;
		}
		Type<?> left = this.getExpressionLeft().evaluate(program);
		Type<?> right = this.getExpressionRight().evaluate(program);
		return new BoolType(left.getValue() == right.getValue());
	}

	@Override
	public boolean checkType(Expression<Type<?>> expression) {
		return true;
	}

}
