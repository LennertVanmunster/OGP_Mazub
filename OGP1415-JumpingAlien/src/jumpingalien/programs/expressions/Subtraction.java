/**
 * 
 */
package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.*;

/**
 * @author Pieter
 *
 */
public class Subtraction extends BinaryOperation {

	public Subtraction(Expression left, Expression right,
			SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}

	public Type getType(){
		return new DoubleType();
	}

	@Override
	public Object evaluate(Program program) {
		return (double)this.getExpressionLeft().evaluate(program) - 
		(double)this.getExpressionRight().evaluate(program);
	}
}
