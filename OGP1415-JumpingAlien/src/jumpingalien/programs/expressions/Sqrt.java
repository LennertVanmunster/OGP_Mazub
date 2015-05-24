package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.*;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class Sqrt extends UnaryOperation<DoubleType, DoubleType> {

	/**
	 * @param expression
	 * @param sourceLocation
	 */
	public Sqrt(Expression<DoubleType> expression, SourceLocation sourceLocation) {
		super(expression, sourceLocation);
	}

	@Override
	public DoubleType evaluateLegalCase(Program program) {
		double value =  Math.abs(((DoubleType)this.getExpression().evaluateLegalCase(program)).getValue());
		return new DoubleType(Math.sqrt(value));
	}

	@Override
	public boolean checkType(Expression<DoubleType> expression) {
		return expression.getType() instanceof DoubleType;
	}

	@Override
	public DoubleType getType() {
		return new DoubleType();
	}

}
