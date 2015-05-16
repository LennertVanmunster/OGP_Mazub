/**
 * 
 */
package jumpingalien.programs.expressions;

import jumpingalien.part3.programs.SourceLocation;
import jumpingalien.programs.program.Program;
import jumpingalien.programs.types.DoubleType;
import jumpingalien.programs.types.Type;

/**
 * @author Pieter
 *
 */
public class Division extends BinaryOperation {

	/**
	 * @param left
	 * @param right
	 * @param sourceLocation
	 */
	public Division(Expression left, Expression right,
			SourceLocation sourceLocation) {
		super(left, right, sourceLocation);
	}
	
	public Type getType(){
		return new DoubleType();
	}

	@Override
	public Object evaluate(Program program) {
		return (double)this.getExpressionLeft().evaluate(program) / 
				(double)this.getExpressionRight().evaluate(program);
	}
}
