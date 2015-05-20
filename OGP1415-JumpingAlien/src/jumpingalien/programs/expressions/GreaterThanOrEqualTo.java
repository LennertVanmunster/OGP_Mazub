package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.BoolType;
import jumpingalien.programs.types.DoubleType;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class GreaterThanOrEqualTo extends Comparison<DoubleType> {

	/**
	 * @param left
	 * @param right
	 * @param sourceLocation
	 */
	public GreaterThanOrEqualTo(Expression<DoubleType> left, Expression<DoubleType> right,
			SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}

	@Override
	public BoolType evaluate(Program program) {
		if(this.getStopProgram()){
			program.stop();
		}
		DoubleType left = (DoubleType) this.getExpressionLeft().evaluate(program);
		DoubleType right = (DoubleType) this.getExpressionRight().evaluate(program);
		return new BoolType(left.getValue() >= right.getValue());
	}
	
	@Override
	public boolean checkType(Expression<DoubleType> expression) {
		return expression.getType() instanceof DoubleType;
	}

}
