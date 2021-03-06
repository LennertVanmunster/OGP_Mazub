package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.BoolType;
import jumpingalien.programs.types.DoubleType;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class LessThanOrEqualTo extends Comparison<DoubleType> {

	/**
	 * @param left
	 * @param right
	 * @param sourceLocation
	 */
	public LessThanOrEqualTo(Expression<DoubleType> left, Expression<DoubleType> right,
			SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}

	@Override
	public BoolType evaluateLegalCase(Program program) {
		DoubleType left = (DoubleType) this.getExpressionLeft().evaluateLegalCase(program);
		DoubleType right = (DoubleType) this.getExpressionRight().evaluateLegalCase(program);
		return new BoolType(left.getValue() <= right.getValue());
	}
	
	@Override
	public boolean checkType(Expression<DoubleType> expression) {
		return expression.getType() instanceof DoubleType;
	}


}
