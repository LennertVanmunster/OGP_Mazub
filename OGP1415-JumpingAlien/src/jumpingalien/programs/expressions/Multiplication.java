package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.DoubleType;

/**
* @version 1.0
* @authors Pieter Van Damme and Lennert Vanmunster
*/
public class Multiplication extends MathematicalExpression {

	/**
	 * @param left
	 * @param right
	 * @param sourceLocation
	 */
	public Multiplication(Expression<DoubleType> left, Expression<DoubleType> right,
			SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}


	@Override
	public DoubleType evaluate(Program program) {
		if(this.getStopProgram() || program.hasStopped()){
			program.stop();
			return null;
		}
		return new DoubleType(((DoubleType) this.getExpressionLeft().evaluate(program)).getValue() * 
				((DoubleType) this.getExpressionRight().evaluate(program)).getValue());
	}

}
